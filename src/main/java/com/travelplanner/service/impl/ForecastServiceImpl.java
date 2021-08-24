package com.travelplanner.service.impl;

import com.travelplanner.data.ForecastData;
import com.travelplanner.data.ForecastLineData;
import com.travelplanner.domain.ForecastDayEnum;
import com.travelplanner.service.ForecastService;
import com.travelplanner.service.gateway.ForecastGateway;
import com.travelplanner.service.gateway.dto.ForecastDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ForecastServiceImpl implements ForecastService
{
    private static final int FORECAST_START_HOUR = 12;
    private static final int FORECAST_END_HOUR = 18;

    private final ForecastGateway forecastGateway;
    private final Converter<ForecastDto, ForecastData> forecastDtoConverter;

    @Override
    public Optional<ForecastData> getForecastByCity(final String city, final ForecastDayEnum itineraryDay)
    {
        return forecastGateway.getForecastByCity(city)
                .map(forecastDtoConverter::convert)
                .map(forecast -> {
                    forecast.setLines(filterLinesByItineraryDate(forecast.getLines(), itineraryDay));
                    return forecast;
                })
                .filter(forecast -> !forecast.getLines().isEmpty());
    }

    private List<ForecastLineData> filterLinesByItineraryDate(final List<ForecastLineData> forecasts,
            final ForecastDayEnum itineraryDay)
    {
        var forecastDate = LocalDate.now().plusDays(itineraryDay.getDayIndex());
        var forecastTimeStart = LocalTime.of(FORECAST_START_HOUR, 0);
        var forecastTimeEnd = LocalTime.of(FORECAST_END_HOUR, 0);
        return forecasts.stream()
                .filter(forecastLine -> isForecastDate(forecastLine, forecastDate))
                .filter(forecastLine -> isWithinForecastTimeLimits(forecastLine, forecastTimeStart, forecastTimeEnd))
                .collect(toList());
    }

    private boolean isForecastDate(final ForecastLineData forecastLineDateTime, final LocalDate forecastDate)
    {
        return forecastDate.isEqual(getForecastDate(forecastLineDateTime).toLocalDate());
    }

    private boolean isWithinForecastTimeLimits(final ForecastLineData forecastLine,
            final LocalTime forecastTimeStart,
            final LocalTime forecastTimeEnd)
    {
        return forecastTimeStart.compareTo(getForecastDate(forecastLine).toLocalTime()) <= 0 &&
                forecastTimeEnd.compareTo(getForecastDate(forecastLine).toLocalTime()) >= 0;
    }

    private LocalDateTime getForecastDate(final ForecastLineData forecastLine)
    {
        return forecastLine.getDate();
    }
}

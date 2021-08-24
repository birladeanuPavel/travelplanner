package com.travelplanner.converter;

import com.travelplanner.data.ForecastData;
import com.travelplanner.data.ItineraryData;
import com.travelplanner.domain.Forecast;
import com.travelplanner.domain.Itinerary;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ItineraryConverter implements Converter<Itinerary, ItineraryData>
{
    private final Converter<Forecast, ForecastData> forecastConverter;

    @Override
    public ItineraryData convert(final Itinerary source)
    {
        var target = new ItineraryData();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setSummary(source.getSummary());
        target.setForecasts(convertForecasts(source.getForecasts()));
        return target;
    }

    private List<ForecastData> convertForecasts(final List<Forecast> source)
    {
        return source.stream()
                .map(forecastConverter::convert)
                .collect(Collectors.toList());
    }
}

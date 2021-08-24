package com.travelplanner.service.impl;

import com.travelplanner.data.ForecastData;
import com.travelplanner.data.ForecastLineData;
import com.travelplanner.data.ItineraryData;
import com.travelplanner.domain.Itinerary;
import com.travelplanner.repository.ItineraryRepository;
import com.travelplanner.service.ItineraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItineraryServiceImpl implements ItineraryService
{
    private static final int COLD_WEATHER_THRESHOLD = 5;
    private static final String COLD_SUGGESTION = "Please take a coat";
    private static final String RAIN_SUGGESTION = "Please take an umbrella";
    private static final String RAIN = "rain";
    private static final String GOOD_WEATHER_SUMMARY = "Weather is nice";

    private final ItineraryRepository itineraryRepository;
    private final Converter<Itinerary, ItineraryData> itineraryConverter;
    private final Converter<ItineraryData, Itinerary> itineraryReverseConverter;

    @Override
    public List<ItineraryData> getAll()
    {
        return itineraryRepository.findAll().stream()
                .map(itineraryConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public void save(final ItineraryData itinerary)
    {
        itinerary.setSummary(buildSummary(itinerary.getForecasts()));
        itineraryRepository.save(itineraryReverseConverter.convert(itinerary));
    }

    private String buildSummary(final List<ForecastData> forecasts)
    {
        var summary = new ArrayList<String>();
        if (isCold(forecasts))
        {
            summary.add(COLD_SUGGESTION);
        }
        if (isRainy(forecasts))
        {
            summary.add(RAIN_SUGGESTION);
        }
        return summary.isEmpty() ? GOOD_WEATHER_SUMMARY : String.join(",", summary);
    }

    private boolean isCold(final List<ForecastData> forecasts)
    {
        return forecasts.stream()
                .flatMap(forecastData -> forecastData.getLines().stream())
                .map(ForecastLineData::getTemp)
                .mapToDouble(Double::parseDouble)
                .anyMatch(temp -> temp < COLD_WEATHER_THRESHOLD);
    }

    private boolean isRainy(final List<ForecastData> forecasts)
    {
        return forecasts.stream()
                .flatMap(forecastData -> forecastData.getLines().stream())
                .map(ForecastLineData::getClouds)
                .map(String::toLowerCase)
                .anyMatch(clouds -> clouds.contains(RAIN));
    }

    @Override
    public Optional<ItineraryData> getById(final String id)
    {
        return itineraryRepository.findById(id).map(itineraryConverter::convert);
    }

    @Override
    public void deleteById(final String id)
    {
        itineraryRepository.deleteById(id);
    }
}

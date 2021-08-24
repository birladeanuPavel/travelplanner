package com.travelplanner.converter.reverse;

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
public class ItineraryReverseConverter implements Converter<ItineraryData, Itinerary>
{
    private final Converter<ForecastData, Forecast> forecastReverseConverter;

    @Override
    public Itinerary convert(final ItineraryData source)
    {
        var target = new Itinerary();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setSummary(source.getSummary());
        target.setForecasts(convertForecasts(source.getForecasts()));
        return target;
    }

    private List<Forecast> convertForecasts(final List<ForecastData> source)
    {
        return source.stream()
                .map(forecastReverseConverter::convert)
                .collect(Collectors.toList());
    }
}

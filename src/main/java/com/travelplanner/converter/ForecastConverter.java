package com.travelplanner.converter;

import com.travelplanner.data.ForecastData;
import com.travelplanner.data.ForecastLineData;
import com.travelplanner.domain.Forecast;
import com.travelplanner.domain.ForecastLine;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ForecastConverter implements Converter<Forecast, ForecastData>
{
    private final Converter<ForecastLine, ForecastLineData> forecastLineConverter;

    @Override
    public ForecastData convert(final Forecast source)
    {
        var target = new ForecastData();
        target.setCity(source.getCity());
        target.setCountry(source.getCountry());
        target.setLines(convertLines(source.getLines()));
        return target;
    }

    private List<ForecastLineData> convertLines(final List<ForecastLine> source)
    {
        return source.stream()
                .map(forecastLineConverter::convert)
                .collect(Collectors.toList());
    }
}

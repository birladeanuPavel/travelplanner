package com.travelplanner.converter.reverse;

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
public class ForecastReverseConverter implements Converter<ForecastData, Forecast>
{
    private final Converter<ForecastLineData, ForecastLine> forecastLineReverseConverter;

    @Override
    public Forecast convert(final ForecastData source)
    {
        var target = new Forecast();
        target.setCity(source.getCity());
        target.setCountry(source.getCountry());
        target.setLines(convertLines(source.getLines()));
        return target;
    }

    private List<ForecastLine> convertLines(final List<ForecastLineData> source)
    {
        return source.stream()
                .map(forecastLineReverseConverter::convert)
                .collect(Collectors.toList());
    }
}

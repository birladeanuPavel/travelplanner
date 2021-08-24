package com.travelplanner.converter.reverse;

import com.travelplanner.data.ForecastLineData;
import com.travelplanner.domain.ForecastLine;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ForecastLineReverseConverter implements Converter<ForecastLineData, ForecastLine>
{
    @Override
    public ForecastLine convert(final ForecastLineData source)
    {
        var target = new ForecastLine();
        target.setTemp(source.getTemp());
        target.setClouds(source.getClouds());
        target.setDate(source.getDate());
        return target;
    }
}

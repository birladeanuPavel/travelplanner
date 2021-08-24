package com.travelplanner.converter;

import com.travelplanner.data.ForecastLineData;
import com.travelplanner.domain.ForecastLine;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ForecastLineConverter implements Converter<ForecastLine, ForecastLineData>
{
    @Override
    public ForecastLineData convert(final ForecastLine source)
    {
        var target = new ForecastLineData();
        target.setTemp(source.getTemp());
        target.setClouds(source.getClouds());
        target.setDate(source.getDate());
        return target;
    }
}

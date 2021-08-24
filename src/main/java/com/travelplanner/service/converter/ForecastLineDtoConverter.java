package com.travelplanner.service.converter;

import com.travelplanner.data.ForecastLineData;
import com.travelplanner.service.gateway.dto.ForecastLineDto;
import com.travelplanner.service.gateway.dto.ForecastLineMainDto;
import com.travelplanner.service.gateway.dto.WeatherDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ForecastLineDtoConverter implements Converter<ForecastLineDto, ForecastLineData>
{
    @Override
    public ForecastLineData convert(final ForecastLineDto source)
    {
        var target = new ForecastLineData();
        target.setClouds(getWeatherClouds(source));
        target.setTemp(getTemp(source));
        target.setDate(source.getDate());
        return target;
    }

    private String getWeatherClouds(final ForecastLineDto source)
    {
        return getWeather(source).getMain();
    }

    private String getTemp(final ForecastLineDto source)
    {
        return getLineMain(source).getTemp();
    }

    private WeatherDto getWeather(final ForecastLineDto source)
    {
        return source.getWeather().get(0);
    }

    private ForecastLineMainDto getLineMain(final ForecastLineDto source)
    {
        return source.getMain();
    }
}

package com.travelplanner.service.converter;

import com.travelplanner.data.ForecastData;
import com.travelplanner.data.ForecastLineData;
import com.travelplanner.service.gateway.dto.CityDto;
import com.travelplanner.service.gateway.dto.ForecastDto;
import com.travelplanner.service.gateway.dto.ForecastLineDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ForecastDtoConverter implements Converter<ForecastDto, ForecastData>
{
    @Autowired
    private Converter<ForecastLineDto, ForecastLineData> forecastLineDtoConverter;

    @Override
    public ForecastData convert(final ForecastDto source)
    {
        var target = new ForecastData();
        target.setCity(getCity(source).getName());
        target.setCountry(getCity(source).getCountry());
        target.setLines(convertLines(source.getList()));
        return target;
    }

    private CityDto getCity(final ForecastDto source)
    {
        return source.getCity();
    }

    private List<ForecastLineData> convertLines(final List<ForecastLineDto> source)
    {
        return source.stream()
                .map(forecastLineDtoConverter::convert)
                .collect(Collectors.toList());
    }
}

package com.travelplanner.service.converter;

import com.travelplanner.data.ForecastLineData;
import com.travelplanner.service.gateway.dto.ForecastLineDto;
import com.travelplanner.service.gateway.dto.ForecastLineMainDto;
import com.travelplanner.service.gateway.dto.WeatherDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

import static com.travelplanner.TravelPlannerConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ForecastLineDtoConverterTest
{
    @InjectMocks
    private Converter<ForecastLineDto, ForecastLineData> underTest = new ForecastLineDtoConverter();

    private ForecastLineDto source;

    @BeforeEach
    void setUp()
    {
        source = new ForecastLineDto();
        source.setDate(DATE);
        source.setMain(buildForecastLineMain());
        source.setWeather(List.of(buildWeather()));
    }

    private ForecastLineMainDto buildForecastLineMain()
    {
        var lineMain = new ForecastLineMainDto();
        lineMain.setTemp(TEMP);
        return lineMain;
    }

    private WeatherDto buildWeather()
    {
        WeatherDto weather = new WeatherDto();
        weather.setMain(CLOUDS);
        return weather;
    }

    @Test
    void shouldConvert()
    {
        assertEquals(new ForecastLineData(TEMP, CLOUDS, DATE), underTest.convert(source));
    }
}
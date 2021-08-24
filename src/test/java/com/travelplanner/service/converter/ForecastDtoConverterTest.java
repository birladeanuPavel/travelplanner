package com.travelplanner.service.converter;

import com.travelplanner.data.ForecastData;
import com.travelplanner.data.ForecastLineData;
import com.travelplanner.service.gateway.dto.CityDto;
import com.travelplanner.service.gateway.dto.ForecastDto;
import com.travelplanner.service.gateway.dto.ForecastLineDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

import static com.travelplanner.TravelPlannerConstants.CITY;
import static com.travelplanner.TravelPlannerConstants.COUNTRY_CODE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ForecastDtoConverterTest
{
    @InjectMocks
    private Converter<ForecastDto, ForecastData> underTest = new ForecastDtoConverter();

    @Mock
    private Converter<ForecastLineDto, ForecastLineData> forecastLineDtoConverter;

    private ForecastDto source;
    private ForecastLineData forecastLineData;

    @BeforeEach
    void setUp()
    {
        source = new ForecastDto();
        source.setCity(buildCity());
        var forecastLineDto = new ForecastLineDto();
        source.setList(List.of(forecastLineDto));
        forecastLineData = new ForecastLineData();
        when(forecastLineDtoConverter.convert(forecastLineDto)).thenReturn(forecastLineData);
    }

    private CityDto buildCity()
    {
        var city = new CityDto();
        city.setName(CITY);
        city.setCountry(COUNTRY_CODE);
        return city;
    }

    @Test
    void shouldConvert()
    {
        assertEquals(new ForecastData(CITY, COUNTRY_CODE, List.of(forecastLineData)), underTest.convert(source));
    }
}
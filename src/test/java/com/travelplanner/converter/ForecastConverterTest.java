package com.travelplanner.converter;

import com.travelplanner.data.ForecastData;
import com.travelplanner.data.ForecastLineData;
import com.travelplanner.domain.Forecast;
import com.travelplanner.domain.ForecastLine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

import static com.travelplanner.TravelPlannerConstants.CITY;
import static com.travelplanner.TravelPlannerConstants.COUNTRY_CODE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ForecastConverterTest
{
    private Converter<Forecast, ForecastData> underTest;

    @Mock
    private Converter<ForecastLine, ForecastLineData> forecastLineConverter;

    private Forecast source;
    private ForecastLineData forecastLineData;

    @BeforeEach
    void setUp()
    {
        underTest = new ForecastConverter(forecastLineConverter);
        source = new Forecast();
        source.setCity(CITY);
        source.setCountry(COUNTRY_CODE);
        var forecastLine = new ForecastLine();
        source.setLines(List.of(forecastLine));
        forecastLineData = new ForecastLineData();
        when(forecastLineConverter.convert(forecastLine)).thenReturn(forecastLineData);
    }

    @Test
    void shouldConvert()
    {
        assertEquals(new ForecastData(CITY, COUNTRY_CODE, List.of(forecastLineData)), underTest.convert(source));
    }
}
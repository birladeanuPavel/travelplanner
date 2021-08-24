package com.travelplanner.converter.reverse;

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
class ForecastReverseConverterTest
{
    private Converter<ForecastData, Forecast> underTest;

    @Mock
    private Converter<ForecastLineData, ForecastLine> forecastLineReverseConverter;

    private ForecastData source;
    private ForecastLine forecastLine;

    @BeforeEach
    void setUp()
    {
        underTest = new ForecastReverseConverter(forecastLineReverseConverter);
        source = new ForecastData();
        source.setCity(CITY);
        source.setCountry(COUNTRY_CODE);
        var forecastLine = new ForecastLineData();
        source.setLines(List.of(forecastLine));
        this.forecastLine = new ForecastLine();
        when(forecastLineReverseConverter.convert(forecastLine)).thenReturn(this.forecastLine);
    }

    @Test
    void shouldConvert()
    {
        assertEquals(new Forecast(CITY, COUNTRY_CODE, List.of(forecastLine)), underTest.convert(source));
    }
}
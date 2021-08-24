package com.travelplanner.converter.reverse;

import com.travelplanner.data.ForecastLineData;
import com.travelplanner.domain.ForecastLine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.converter.Converter;

import static com.travelplanner.TravelPlannerConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ForecastLineReverseConverterTest
{
    private Converter<ForecastLineData, ForecastLine> underTest = new ForecastLineReverseConverter();

    private ForecastLineData source;

    @BeforeEach
    void setUp()
    {
        source = new ForecastLineData();
        source.setTemp(TEMP);
        source.setDate(DATE);
        source.setClouds(CLOUDS);
    }

    @Test
    void shouldConvert()
    {
        assertEquals(new ForecastLine(TEMP, CLOUDS, DATE), underTest.convert(source));
    }
}
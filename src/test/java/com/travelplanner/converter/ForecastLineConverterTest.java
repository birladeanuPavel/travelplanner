package com.travelplanner.converter;

import com.travelplanner.data.ForecastLineData;
import com.travelplanner.domain.ForecastLine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.converter.Converter;

import static com.travelplanner.TravelPlannerConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ForecastLineConverterTest
{
    private Converter<ForecastLine, ForecastLineData> underTest = new ForecastLineConverter();

    private ForecastLine source;

    @BeforeEach
    void setUp()
    {
        source = new ForecastLine();
        source.setTemp(TEMP);
        source.setDate(DATE);
        source.setClouds(CLOUDS);
    }

    @Test
    void shouldConvert()
    {
        assertEquals(new ForecastLineData(TEMP, CLOUDS, DATE), underTest.convert(source));
    }
}
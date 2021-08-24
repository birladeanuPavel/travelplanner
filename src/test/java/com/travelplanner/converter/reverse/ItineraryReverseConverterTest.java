package com.travelplanner.converter.reverse;

import com.travelplanner.data.ForecastData;
import com.travelplanner.data.ItineraryData;
import com.travelplanner.domain.Forecast;
import com.travelplanner.domain.Itinerary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

import static com.travelplanner.TravelPlannerConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItineraryReverseConverterTest
{
    private Converter<ItineraryData, Itinerary> underTest;

    @Mock
    private Converter<ForecastData, Forecast> forecastReverseConverter;

    private ItineraryData source;
    private Forecast forecast;

    @BeforeEach
    void setUp()
    {
        underTest = new ItineraryReverseConverter(forecastReverseConverter);
        source = new ItineraryData();
        source.setId(ITINERARY_ID);
        source.setName(ITINERARY_NAME);
        source.setSummary(ITINERARY_SUMMARY);
        var forecastData = new ForecastData();
        source.setForecasts(List.of(forecastData));
        this.forecast = new Forecast();
        when(forecastReverseConverter.convert(forecastData)).thenReturn(this.forecast);
    }

    @Test
    void shouldConvert()
    {
        assertEquals(new Itinerary(ITINERARY_ID, ITINERARY_NAME, ITINERARY_SUMMARY, List.of(forecast)), underTest.convert(source));
    }

}
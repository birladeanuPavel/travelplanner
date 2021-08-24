package com.travelplanner.converter;

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
class ItineraryConverterTest
{
    private Converter<Itinerary, ItineraryData> underTest;

    @Mock
    private Converter<Forecast, ForecastData> forecastConverter;

    private Itinerary source;
    private ForecastData forecastData;

    @BeforeEach
    void setUp()
    {
        underTest = new ItineraryConverter(forecastConverter);
        source = new Itinerary();
        source.setId(ITINERARY_ID);
        source.setName(ITINERARY_NAME);
        source.setSummary(ITINERARY_SUMMARY);
        var forecast = new Forecast();
        source.setForecasts(List.of(forecast));
        forecastData = new ForecastData();
        when(forecastConverter.convert(forecast)).thenReturn(forecastData);
    }

    @Test
    void shouldConvert()
    {
        assertEquals(new ItineraryData(ITINERARY_ID, ITINERARY_NAME, ITINERARY_SUMMARY, List.of(forecastData)), underTest.convert(source));
    }
}
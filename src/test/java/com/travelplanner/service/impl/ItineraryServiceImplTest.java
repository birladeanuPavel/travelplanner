package com.travelplanner.service.impl;

import com.travelplanner.data.ForecastData;
import com.travelplanner.data.ForecastLineData;
import com.travelplanner.data.ItineraryData;
import com.travelplanner.domain.Itinerary;
import com.travelplanner.repository.ItineraryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.Optional;

import static com.travelplanner.TravelPlannerConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItineraryServiceImplTest
{
    private static final String ID = "id";

    private ItineraryServiceImpl underTest;

    @Mock
    private ItineraryRepository itineraryRepository;

    @Mock
    private Converter<Itinerary, ItineraryData> itineraryConverter;

    @Mock
    private Converter<ItineraryData, Itinerary> itineraryReverseConverter;

    private Itinerary itinerary = new Itinerary();
    private ItineraryData itineraryData = new ItineraryData();

    @BeforeEach
    void setUp()
    {
        underTest = new ItineraryServiceImpl(itineraryRepository, itineraryConverter, itineraryReverseConverter);
    }

    @Test
    void shouldGetAll()
    {
        when(itineraryRepository.findAll()).thenReturn(List.of(itinerary));
        when(itineraryConverter.convert(itinerary)).thenReturn(itineraryData);
        assertEquals(List.of(itineraryData), underTest.getAll());
    }

    @Test
    void shouldSave()
    {
        when(itineraryReverseConverter.convert(itineraryData)).thenReturn(itinerary);
        underTest.save(itineraryData);
        assertEquals("Weather is nice", itineraryData.getSummary());
        verify(itineraryRepository).save(itinerary);
    }

    @Test
    void shouldSaveWithSummary()
    {
        itineraryData.setForecasts(buildBadWeatherForecast());
        when(itineraryReverseConverter.convert(itineraryData)).thenReturn(itinerary);
        underTest.save(itineraryData);
        assertEquals("Please take a coat,Please take an umbrella", itineraryData.getSummary());
        verify(itineraryRepository).save(itinerary);
    }

    private List<ForecastData> buildBadWeatherForecast()
    {
        return List.of(new ForecastData(CITY, COUNTRY_CODE,
                List.of(new ForecastLineData("4", CLOUDS, DATE),
                        new ForecastLineData(TEMP, "Rain", DATE))));
    }

    @Test
    void shouldGetById()
    {
        when(itineraryRepository.findById(ID)).thenReturn(Optional.of(itinerary));
        when(itineraryConverter.convert(itinerary)).thenReturn(itineraryData);
        assertEquals(Optional.of(itineraryData), underTest.getById(ID));
    }

    @Test
    void shouldDeleteById()
    {
        underTest.deleteById(ID);
        verify(itineraryRepository).deleteById(ID);
    }
}
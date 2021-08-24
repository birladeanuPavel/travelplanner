package com.travelplanner.service.impl;

import com.travelplanner.data.ForecastData;
import com.travelplanner.data.ForecastLineData;
import com.travelplanner.domain.ForecastDayEnum;
import com.travelplanner.service.gateway.ForecastGateway;
import com.travelplanner.service.gateway.dto.ForecastDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.travelplanner.TravelPlannerConstants.*;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ForecastServiceImplTest
{
    private final List<ForecastLineData> todayForecast = List.of(
            new ForecastLineData("24", CLOUDS, LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0))),
            new ForecastLineData("25", CLOUDS, LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 0))),
            new ForecastLineData("26", CLOUDS, LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 0))),
            new ForecastLineData("27", CLOUDS, LocalDateTime.of(LocalDate.now(), LocalTime.of(18, 0))),
            new ForecastLineData("28", CLOUDS, LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 0))));
    private final List<ForecastLineData> tomorrowForecast = List.of(
            new ForecastLineData("29", CLOUDS, LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(11, 0))),
            new ForecastLineData("30", CLOUDS, LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(12, 0))),
            new ForecastLineData("31", CLOUDS, LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(15, 0))),
            new ForecastLineData("32", CLOUDS, LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(18, 0))),
            new ForecastLineData("33", CLOUDS, LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(19, 0))));
    private final List<ForecastLineData> forecastLines = Stream.concat(todayForecast.stream(), tomorrowForecast.stream()).collect(toList());

    private ForecastServiceImpl underTest;

    @Mock
    private ForecastGateway forecastGateway;

    @Mock
    private Converter<ForecastDto, ForecastData> forecastDtoConverter;

    private ForecastDto forecastDto = new ForecastDto();
    private ForecastData forecastData = new ForecastData();

    @BeforeEach
    void setUp()
    {
        underTest = new ForecastServiceImpl(forecastGateway, forecastDtoConverter);
        when(forecastGateway.getForecastByCity(CITY)).thenReturn(Optional.of(forecastDto));
        forecastData.setCountry(COUNTRY_CODE);
        forecastData.setCity(CITY);
        forecastData.setLines(forecastLines);
        lenient().when(forecastDtoConverter.convert(forecastDto)).thenReturn(forecastData);
    }

    @Test
    void shouldGetTodayForecastByCity()
    {
        var result = underTest.getForecastByCity(CITY, ForecastDayEnum.TODAY);
        assertTrue(result.isPresent());
        assertEquals(new ForecastData(CITY, COUNTRY_CODE, getExpectedForecast(todayForecast)), result.get());
    }

    @Test
    void shouldGetTomorrowForecastByCity()
    {
        var result = underTest.getForecastByCity(CITY, ForecastDayEnum.TOMORROW);
        assertTrue(result.isPresent());
        assertEquals(new ForecastData(CITY, COUNTRY_CODE, getExpectedForecast(tomorrowForecast)), result.get());
    }

    private List<ForecastLineData> getExpectedForecast(final List<ForecastLineData> tomorrowForecast)
    {
        return tomorrowForecast.subList(1, 4);
    }

    @Test
    void shouldHandleNoDayAfterTomorrowForecastByCity()
    {
        assertFalse(underTest.getForecastByCity(CITY, ForecastDayEnum.DAY_AFTER_TOMORROW).isPresent());
    }

    @Test
    void shouldHandleNoForecast()
    {
        when(forecastGateway.getForecastByCity(CITY)).thenReturn(Optional.empty());
        assertTrue(underTest.getForecastByCity(CITY, ForecastDayEnum.TODAY).isEmpty());
    }
}
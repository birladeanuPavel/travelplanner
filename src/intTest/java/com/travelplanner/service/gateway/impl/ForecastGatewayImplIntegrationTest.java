package com.travelplanner.service.gateway.impl;

import com.travelplanner.service.gateway.ForecastGateway;
import com.travelplanner.service.gateway.dto.ForecastDto;
import com.travelplanner.service.gateway.dto.ForecastLineDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ForecastGatewayImplIntegrationTest
{
    public static final String CITY = "London";
    public static final String COUNTRY_CODE = "GB";

    @Autowired
    private ForecastGateway underTest;

    @Test
    void shouldGetForecastByCity()
    {
        var londonForecast = underTest.getForecastByCity(CITY);
        assertTrue(londonForecast.isPresent());
        assertValidCity(londonForecast.get());
        assertValidList(londonForecast.get());
    }

    private void assertValidCity(final ForecastDto londonForecast)
    {
        assertNotNull(londonForecast.getCity());
        Assertions.assertEquals(CITY, londonForecast.getCity().getName());
        Assertions.assertEquals(COUNTRY_CODE, londonForecast.getCity().getCountry());
    }

    private void assertValidList(final ForecastDto londonForecast)
    {
        assertNotNull(londonForecast.getList());
        assertFalse(londonForecast.getList().isEmpty());
        assertValidLine(londonForecast);
    }

    private void assertValidLine(final ForecastDto londonForecast)
    {
        var forecastLine = londonForecast.getList().get(0);
        assertNotNull(forecastLine.getDate());
        assertNotNull(forecastLine.getMain());
        assertNotNull(forecastLine.getMain().getTemp());
        assertNotNull(forecastLine.getWeather());
        assertFalse(forecastLine.getWeather().isEmpty());
        assertValidWeather(forecastLine);
    }

    private void assertValidWeather(final ForecastLineDto forecastLine)
    {
        var weather = forecastLine.getWeather().get(0);
        assertNotNull(weather.getMain());
    }

    @Test
    void shouldHandleNoResultForecastByCity()
    {
        assertTrue(underTest.getForecastByCity("1p23def123").isEmpty());
    }
}
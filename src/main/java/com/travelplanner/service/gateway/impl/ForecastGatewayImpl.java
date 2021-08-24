package com.travelplanner.service.gateway.impl;

import com.travelplanner.service.gateway.ForecastGateway;
import com.travelplanner.service.gateway.api.ForecastApi;
import com.travelplanner.service.gateway.dto.ForecastDto;
import com.travelplanner.service.gateway.dto.QueryDto;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ForecastGatewayImpl implements ForecastGateway
{
    private final ForecastApi forecastApi;

    @Override
    @Cacheable(value = "forecastByCityCache", key = "#city", unless = "#result == null")
    public Optional<ForecastDto> getForecastByCity(final String city)
    {
        try
        {
            return Optional.of(forecastApi.getForecastByCity(new QueryDto(city)));
        }
        catch (final FeignException.NotFound e)
        {
            log.warn(e.getMessage(), e);
        }
        return Optional.empty();
    }
}

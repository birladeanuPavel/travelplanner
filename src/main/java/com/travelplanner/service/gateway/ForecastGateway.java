package com.travelplanner.service.gateway;

import com.travelplanner.service.gateway.dto.ForecastDto;

import java.util.Optional;

public interface ForecastGateway
{
    Optional<ForecastDto> getForecastByCity(String city);
}

package com.travelplanner.service.gateway.api;

import com.travelplanner.service.gateway.dto.ForecastDto;
import com.travelplanner.service.gateway.dto.QueryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "forecastApi", url = "api.openweathermap.org/data/2.5/forecast")
public interface ForecastApi
{
    @GetMapping(produces = "application/json")
    ForecastDto getForecastByCity(@SpringQueryMap QueryDto query);
}

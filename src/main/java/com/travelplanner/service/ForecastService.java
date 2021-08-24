package com.travelplanner.service;

import com.travelplanner.data.ForecastData;
import com.travelplanner.domain.ForecastDayEnum;

import java.util.Optional;

public interface ForecastService
{
    Optional<ForecastData> getForecastByCity(String city, ForecastDayEnum itineraryDay);
}

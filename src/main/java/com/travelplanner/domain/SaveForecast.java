package com.travelplanner.domain;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class SaveForecast
{
    @NotEmpty
    private String city;
    @NotNull
    private ForecastDayEnum forecastDay;
}

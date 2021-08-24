package com.travelplanner.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Forecast
{
    private String city;
    private String country;
    private List<ForecastLine> lines;
}

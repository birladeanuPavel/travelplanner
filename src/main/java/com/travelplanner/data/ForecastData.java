package com.travelplanner.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForecastData
{
    private String city;
    private String country;
    private List<ForecastLineData> lines = new ArrayList<>();
}

package com.travelplanner.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItineraryData
{
    private String id;
    private String name;
    private String summary;
    private List<ForecastData> forecasts = new ArrayList<>();
}

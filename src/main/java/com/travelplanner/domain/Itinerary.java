package com.travelplanner.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Itinerary
{
    @Id
    private String id;
    private String name;
    private String summary;
    private List<Forecast> forecasts;
}

package com.travelplanner.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForecastLineData
{
    private String temp;
    private String clouds;
    private LocalDateTime date;
}

package com.travelplanner.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForecastLine
{
    private String temp;
    private String clouds;
    private LocalDateTime date;
}

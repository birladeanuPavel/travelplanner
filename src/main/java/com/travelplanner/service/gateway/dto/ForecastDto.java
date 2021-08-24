package com.travelplanner.service.gateway.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ForecastDto implements Serializable
{
    private static final long serialVersionUID = 1L;
    private CityDto city;
    private List<ForecastLineDto> list;
}

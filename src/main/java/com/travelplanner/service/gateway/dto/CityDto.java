package com.travelplanner.service.gateway.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CityDto implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String name;
    private String country;
}

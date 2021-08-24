package com.travelplanner.domain;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SaveItinerary
{
    @NotEmpty
    private String name;
}

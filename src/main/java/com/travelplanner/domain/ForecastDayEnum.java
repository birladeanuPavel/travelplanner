package com.travelplanner.domain;

import lombok.Getter;

@Getter
public enum ForecastDayEnum
{
    TODAY(0),
    TOMORROW(1),
    DAY_AFTER_TOMORROW(2),
    IN_THREE_DAYS(3),
    IN_FOUR_DAYS(4);

    private final int dayIndex;

    ForecastDayEnum(final int dayIndex)
    {
        this.dayIndex = dayIndex;
    }
}

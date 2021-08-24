package com.travelplanner.service;

import com.travelplanner.data.ItineraryData;

import java.util.List;
import java.util.Optional;

public interface ItineraryService
{
    List<ItineraryData> getAll();

    void save(ItineraryData itinerary);

    Optional<ItineraryData> getById(String id);

    void deleteById(String id);
}

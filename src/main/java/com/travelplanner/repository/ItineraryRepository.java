package com.travelplanner.repository;

import com.travelplanner.domain.Itinerary;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItineraryRepository extends MongoRepository<Itinerary, String>
{
}

package com.travelplanner.configuration;

import com.travelplanner.data.ItineraryData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class TravelPlannerConfiguration
{
    private static final String NEW_ITINERARY_NAME = "New itinerary";

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public ItineraryData newItinerary()
    {
        var itineraryData = new ItineraryData();
        itineraryData.setName(NEW_ITINERARY_NAME);
        return itineraryData;
    }
}

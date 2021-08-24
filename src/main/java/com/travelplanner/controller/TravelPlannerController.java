package com.travelplanner.controller;

import com.travelplanner.service.ItineraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class TravelPlannerController
{
    private final ItineraryService itineraryService;

    @GetMapping(value = "/")
    public String index(Model model)
    {
        model.addAttribute("itineraries", itineraryService.getAll());
        return "index";
    }
}

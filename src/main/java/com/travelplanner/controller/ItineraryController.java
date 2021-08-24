package com.travelplanner.controller;

import com.travelplanner.data.ItineraryData;
import com.travelplanner.domain.ForecastDayEnum;
import com.travelplanner.domain.SaveForecast;
import com.travelplanner.domain.SaveItinerary;
import com.travelplanner.service.ForecastService;
import com.travelplanner.service.ItineraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.EnumSet;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class ItineraryController
{
    private final ItineraryService itineraryService;
    private final ForecastService forecastService;
    private final ItineraryData newItinerary;

    @GetMapping(value = "/itinerary/{id}")
    public String getItineraryById(@PathVariable(value = "id") String id, Model model)
    {
        model.addAttribute("itinerary", itineraryService.getById(id).orElse(null));
        return "itinerary";
    }

    @GetMapping(value = "/itinerary/delete/{id}")
    public String deleteItineraryById(@PathVariable(value = "id") String id)
    {
        itineraryService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping(value = "/create-itinerary")
    public String getCreateItineraryPage()
    {
        return "create-itinerary";
    }

    @PostMapping(value = "/itinerary")
    public String saveItinerary(@Valid SaveItinerary saveItinerary, BindingResult bindingResult, HttpSession session)
    {
        if (bindingResult.hasErrors())
        {
            return "create-itinerary";
        }
        newItinerary.setName(saveItinerary.getName());
        if (newItinerary.getForecasts().isEmpty())
        {
            return "create-itinerary";
        }
        itineraryService.save(newItinerary);
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping(value = "/itinerary/reset")
    public String resetItinerary(HttpSession session)
    {
        session.invalidate();
        return "create-itinerary";
    }

    @PostMapping(value = "/forecast")
    public String saveForecast(@Valid SaveForecast saveForecast, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return "create-itinerary";
        }
        forecastService.getForecastByCity(saveForecast.getCity(), saveForecast.getForecastDay())
                .ifPresent(forecast -> newItinerary.getForecasts().add(forecast));
        return "create-itinerary";
    }

    @ModelAttribute(value = "forecastDays")
    public Set<ForecastDayEnum> forecastDays()
    {
        return EnumSet.allOf(ForecastDayEnum.class);
    }

    @ModelAttribute(value = "newItinerary")
    public ItineraryData newItinerary()
    {
        return newItinerary;
    }

    @ModelAttribute(value = "saveForecast")
    public SaveForecast saveForecast()
    {
        return new SaveForecast();
    }

    @ModelAttribute(value = "saveItinerary")
    public SaveItinerary saveItinerary()
    {
        return new SaveItinerary();
    }
}

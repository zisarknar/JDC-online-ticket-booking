package com.solt.jdc.boot.controllers;

import com.solt.jdc.boot.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TripController {

    private TripService tripService;

    @Autowired
    public void getTripService(TripService tripService) {
        this.tripService = tripService;
    }

    @RequestMapping("/trips")
    public String getAllTrip(Model model) {
        model.addAttribute("trips", tripService.getAllTrips());
        return "admin/trip/index";
    }

    @RequestMapping("/trips/{id}")
    public String getTrip(Model model, @PathVariable("id") int tripId){
        model.addAttribute("trip", tripService.getTrip(tripId));
        return "admin/trip/index";
    }
}

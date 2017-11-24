package com.solt.jdc.boot.controllers;

import com.solt.jdc.boot.domains.Trip;
import com.solt.jdc.boot.services.BusService;
import com.solt.jdc.boot.services.CustomerService;
import com.solt.jdc.boot.services.StationService;
import com.solt.jdc.boot.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TripController {

    @Autowired
    private TripService tripService;
    @Autowired
    private BusService busService;
    @Autowired
    private StationService stationService;

    @RequestMapping("/trips")
    public String getAllTrip(Model model) {
        model.addAttribute("trips", tripService.getAllTrips());
        return "admin/trip/index";
    }

    @RequestMapping("/trips/{id}")
    public String getTrip(Model model, @PathVariable("id") int tripId) {
        model.addAttribute("trip", tripService.getTrip(tripId));
        return "admin/trip/index";
    }

    @RequestMapping(value = "/trips/add", method = RequestMethod.GET)
    public String addTrip(Model model) {
        Trip trip = new Trip();
        model.addAttribute("newTrip", trip);
        model.addAttribute("allBus", busService.getAllBus());
        model.addAttribute("allStation", stationService.getAllStations());
        return "admin/trip/addNew";
    }

    @RequestMapping(value = "/trips/add", method = RequestMethod.POST)
    public String processAddTrip(@ModelAttribute("newTrip") Trip newTrip) {
        tripService.saveTrip(newTrip);
        return "redirect:/trips";
    }

    @RequestMapping(value = "/trip/update/{id}", method = RequestMethod.GET)
    public String updateTrip(@PathVariable("id") int tripId, Model model) {
        model.addAttribute("allBus", busService.getAllBus());
        model.addAttribute("allStation", stationService.getAllStations());
        model.addAttribute("trip", tripService.getTrip(tripId));
        return "admin/trip/update";
    }

    @RequestMapping(value = "/trip/update/{id}", method = RequestMethod.POST)
    public String processUpdateTrip(@ModelAttribute("trip") Trip updatedTrip, @PathVariable("id") int tripId){
        Trip currentTrip = tripService.getTrip(tripId);
        currentTrip.setBooking(updatedTrip.getBooking());
        currentTrip.setBusId(updatedTrip.getBusId());
        currentTrip.setDepTime(updatedTrip.getDepTime());
        currentTrip.setEstTime(updatedTrip.getEstTime());
        currentTrip.setTripCode(updatedTrip.getTripCode());
        currentTrip.setStatus(updatedTrip.isStatus());
        currentTrip.setUnitPrice(updatedTrip.getUnitPrice());
        tripService.updateTrip(currentTrip);
        return "redirect:/trips";
    }

    @RequestMapping("/trip/delete/{id}")
    public String deleteTrip(@PathVariable ("id") int tripId) {
        tripService.deleteTrip(tripId);
        return "redirect:/trips";
    }
}
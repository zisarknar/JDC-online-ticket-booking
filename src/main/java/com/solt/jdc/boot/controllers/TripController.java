package com.solt.jdc.boot.controllers;

import com.solt.jdc.boot.domains.Trip;
import com.solt.jdc.boot.services.BusService;
import com.solt.jdc.boot.services.CitiesService;
import com.solt.jdc.boot.services.StationService;
import com.solt.jdc.boot.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class TripController {

    @Autowired
    private TripService tripService;

    @Autowired
    private CitiesService citiesService;

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
        model.addAttribute("allcities", citiesService.getAllCities().stream().map(e -> e.getName()).collect(Collectors.toList()));
        return "admin/trip/addNew";
    }

    @RequestMapping(value = "/trips/add", method = RequestMethod.POST)
    public String processAddTrip(@ModelAttribute("newTrip") @Valid Trip newTrip, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/trip/addNew";
        }
        // mainController.disallowedFieldException(result);
        tripService.saveTrip(newTrip);
        return "redirect:/admin/trips";
    }

    @RequestMapping(value = "/trip/update/{id}", method = RequestMethod.GET)
    public String updateTrip(@PathVariable("id") int tripId, Model model) {
        model.addAttribute("allBus", busService.getAllBus());
        model.addAttribute("allStation", stationService.getAllStations());
        model.addAttribute("trip", tripService.getTrip(tripId));
        model.addAttribute("allcities", citiesService.getAllCities()
                .stream()
                .map(e -> e.getName())
                .collect(Collectors.toList()));
        return "admin/trip/update";
    }

    @RequestMapping(value = "/trip/update/{id}", method = RequestMethod.POST)
    public String processUpdateTrip(@ModelAttribute("trip") @Valid Trip updatedTrip,
                                    @PathVariable("id") int tripId, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/bus/update";
        }
        Trip currentTrip = tripService.getTrip(tripId);
        currentTrip.setBooking(updatedTrip.getBooking());
        currentTrip.setBusId(updatedTrip.getBusId());
        currentTrip.setDepTime(updatedTrip.getDepTime());
        currentTrip.setDepDate(updatedTrip.getDepDate());
        currentTrip.setEstTime(updatedTrip.getEstTime());
        currentTrip.setTripCode(updatedTrip.getTripCode());
        currentTrip.setStatus(updatedTrip.isStatus());
        currentTrip.setUnitPrice(updatedTrip.getUnitPrice());
        tripService.updateTrip(currentTrip);
        return "redirect:/admin/trips";
    }

    @RequestMapping("/trip/delete/{id}")
    public String deleteTrip(@PathVariable("id") int tripId) {
        tripService.deleteTrip(tripId);
        return "redirect:/admin/trips";
    }

   /* @InitBinder
    public void initializeBider(WebDataBinder binder) {

        binder.setAllowedFields("busId", "depTime", "depDate", "estTime", "tripCode",  "unitPrice","busId","stationId","source","destionation");
    }

        
    }*/


}

package com.solt.jdc.boot.controllers.frontControllers;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.solt.jdc.boot.domains.Bus;
import com.solt.jdc.boot.domains.Trip;
import com.solt.jdc.boot.services.BusService;
import com.solt.jdc.boot.services.CitiesService;
import com.solt.jdc.boot.services.TripService;
import com.solt.jdc.boot.utils.TripFinder;

@Controller
public class FrontTripSearchController {
	@Autowired
	private TripService tripService;
	
	@Autowired
	private BusService busService;
	
	@Autowired
	private CitiesService citiesService;

	@RequestMapping("/findBookingTrips")
	public String searchTrips(Model model) {
		TripFinder tripFinder = new TripFinder();
		model.addAttribute("allcities",
				citiesService.getAllCities().stream().map(e -> e.getName()).collect(Collectors.toList()));
		model.addAttribute("tripFinder", tripFinder);
		return "/frontend/findTrips";
	}

	@RequestMapping(value = "/busBinding/{id}", method = RequestMethod.POST)
	public String bindBus(@ModelAttribute("bus") Bus bus, @PathVariable("id") int tripId) {
		Trip trip = tripService.getTrip(tripId);
		Bus currentBus=busService.findById(trip.getBusId());
		currentBus.setTakenSeats(currentBus.getTakenSeats()+bus.getTakenSeats());
		
		busService.updateBus(currentBus);
		
		System.out.println(currentBus.getTakenSeats());
		return null;
	}
}

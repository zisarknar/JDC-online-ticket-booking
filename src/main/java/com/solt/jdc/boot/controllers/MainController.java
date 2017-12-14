package com.solt.jdc.boot.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.solt.jdc.boot.domains.Bus;
import com.solt.jdc.boot.domains.Trip;
import com.solt.jdc.boot.repositories.TripRepository;
import com.solt.jdc.boot.services.BusService;
import com.solt.jdc.boot.services.CitiesService;
import com.solt.jdc.boot.services.StationService;
import com.solt.jdc.boot.services.TripService;
import com.solt.jdc.boot.utils.TripFinder;

@Controller
public class MainController {

	@Autowired
	private TripService tripService;

	@Autowired
	private CitiesService citiesService;

	@Autowired
	private StationService stationService;

	@Autowired
	private BusService busService;

	@RequestMapping("/admin")
	public String getMain() {
		return "admin/index";
	}

	@GetMapping("/")
	public String getIndex(Model model) {
		TripFinder tripFinder = new TripFinder();
		model.addAttribute("allcities",
				citiesService.getAllCities().stream().map(e -> e.getName()).collect(Collectors.toList()));
		model.addAttribute("tripFinder", tripFinder);
		return "frontend/index";
	}

	@RequestMapping(value = "/trip/search", method = RequestMethod.POST)
	public String getTrip(@ModelAttribute("tripFinder") TripFinder tripFinder, RedirectAttributes redirect) {
		String source = tripFinder.getSource();
		String destination = tripFinder.getDestination();
		Date depDate = tripFinder.getDepDate();
		List<Trip> tripList = tripService.findTripByFilter(source, destination, depDate);

		List<Bus> busList = tripList.stream().map(e -> e.getBusId()).map(e -> busService.findById(e))
				.collect(Collectors.toList());

		List<Integer> maxSeatList = tripList.stream().map(e -> e.getBusId()).map(e -> busService.findById(e))
				.map(e -> e.getMaxSeats() - e.getTakenSeats()).collect(Collectors.toList());

		List<String> stationNameList = tripList.stream().map(e -> e.getStationId()).map(e -> stationService.findById(e))
				.map(e -> e.getName()).collect(Collectors.toList());

		List<List<Integer>> grandList = new ArrayList<>();

		// populating grandList with lists having the values from 1 to the max Seat
		for (int seat : maxSeatList) {
			List<Integer> list = new ArrayList<>();
			for (int i = 1; i <= seat; i++) {
				list.add(i);
			}
			grandList.add(list);
		}

		redirect.addFlashAttribute("tripList", tripList);
		redirect.addFlashAttribute("maxSeatList", maxSeatList);
		redirect.addFlashAttribute("stationNameList", stationNameList);
		redirect.addFlashAttribute("grandList", grandList);
		redirect.addFlashAttribute("busList", busList);

		// Command Object for bus
		Bus bus = new Bus();
		redirect.addFlashAttribute("bus", bus);

		return "redirect:/findBookingTrips";
	}

	protected void disallowedFieldException(BindingResult result) {
		String[] suppressedFields = result.getSuppressedFields();
		if (suppressedFields.length > 0) {
			throw new RuntimeException("Unable to bind disallowed fields");
		}
	}

}

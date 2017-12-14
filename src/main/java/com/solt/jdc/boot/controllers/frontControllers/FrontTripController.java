package com.solt.jdc.boot.controllers.frontControllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.solt.jdc.boot.domains.Station;
import com.solt.jdc.boot.domains.Trip;
import com.solt.jdc.boot.services.CitiesService;
import com.solt.jdc.boot.services.StationService;
import com.solt.jdc.boot.services.TripService;
import com.solt.jdc.boot.services.Impl.StationServiceImpl;
import com.solt.jdc.boot.utils.TripFinder;

@Controller
public class FrontTripController {
	@Autowired
	private CitiesService citiesService;
	
	@Autowired
	private TripService tripService;
	
	@Autowired
	private StationService stationService;

	@RequestMapping("/findTrips")
	public String findTripGet(Model model) {
		TripFinder tripFinder = new TripFinder();
		List<Trip> tripList=new ArrayList<>();
		if(model.containsAttribute("tripList")==false) {
			model.addAttribute("tripList", tripList);
		}
		model.addAttribute("tripFinder", tripFinder);
		model.addAttribute("allCities",
				citiesService.getAllCities().stream().map(e -> e.getName()).collect(Collectors.toList()));
		return "frontend/trip";
	}
	
	@RequestMapping(value="/search/trip/withoutdate")
	public String findTripPost(@ModelAttribute("tripFinder")TripFinder tripFinder,Model model,RedirectAttributes redirect) {
		String source=tripFinder.getSource();
		String destination=tripFinder.getDestination();
		List<Trip> tripList=tripService.findTripByRoute(source, destination);
		List<String> stationNameList=tripList.stream().map(e->e.getStationId()).map(e->stationService.findById(e)).map(e->e.getName()).collect(Collectors.toList());
		redirect.addFlashAttribute("stationNameList",stationNameList);
		redirect.addFlashAttribute("tripList", tripList);
		return "redirect:/findTrips";
		
	}
}

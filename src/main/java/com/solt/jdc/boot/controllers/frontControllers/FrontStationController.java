package com.solt.jdc.boot.controllers.frontControllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.solt.jdc.boot.domains.Bus;
import com.solt.jdc.boot.domains.Station;
import com.solt.jdc.boot.repositories.StationRepository;
import com.solt.jdc.boot.services.BusService;
import com.solt.jdc.boot.services.CitiesService;
import com.solt.jdc.boot.services.StationService;
import com.solt.jdc.boot.utils.StationFinder;

@Controller
@RequestMapping("/")
public class FrontStationController {
	@Autowired
	private StationService stationService;

	@Autowired
	private CitiesService citiesService;

	@Autowired
	private BusService busService;
	
	@RequestMapping("/findStation")
	public String findStationByCityGET(Model model) {
		StationFinder stationFinder = new StationFinder();
		List<Station> stationList=new ArrayList<>();
		if(model.containsAttribute("stationList")==false) {
			model.addAttribute("stationList", stationList);
		}
		model.addAttribute("allCities",
				citiesService.getAllCities().stream().map(e -> e.getName()).collect(Collectors.toList()));
		model.addAttribute("allStations", stationService.getAllStations().stream().map(e->e.getName()).collect(Collectors.toList()));
		model.addAttribute("stationFinder", stationFinder);
		return "frontend/station";
	}

	@RequestMapping(value = "/station/search", method = RequestMethod.POST)
	public String findStationByCityPOST(Model model, @ModelAttribute("stationFinder") StationFinder stationFinder,RedirectAttributes redirect) {
		String city = stationFinder.getCity();
		String stationName=stationFinder.getStationName();
		if(city.equals("-1")) {
			List<Station> stationList=stationService.findStationByName(stationName);
			stationList.stream().forEach(System.out::println);
			redirect.addFlashAttribute("stationList", stationList);
		}
		else if(stationName.equals("-1")) {
			List<Station> stationList=stationService.findStationByCity(city);
			redirect.addFlashAttribute("stationList", stationList);
		}
		else {
			List<Station> stationList=stationService.findStationByFilter(stationName, city);
			redirect.addFlashAttribute("stationList", stationList);
		}
		return "redirect:/findStation";
	}
}

package com.solt.jdc.boot.controllers;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.solt.jdc.boot.domains.Station;
import com.solt.jdc.boot.services.StationService;

@Controller
@RequestMapping("/station")
public class StationController {
	@Autowired
	private StationService stationService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addStationGET(Model model) {
		Station station = new Station();
		model.addAttribute("station", station);
		return "addStation";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addStationPOST(Model model, @ModelAttribute("station") Station station) {
		stationService.addStation(station);
		return "redirect:/station/stations";
	}

	@RequestMapping("/stations")
	public String getAllStations(Model model) {
		model.addAttribute("stations", stationService.getAllStations());
		return "stations";
	}

	@RequestMapping("/delete/{stationId}")
	public String deleteStation(@PathVariable("stationId") int stationId) {
		stationService.deleteStation(stationId);
		return "redirect:/station/stations";
	}

	@RequestMapping(value = "/update/{stationId}", method = RequestMethod.GET)
	public String updateStationGET(@PathVariable("stationId") int stationId, Model model) {
		model.addAttribute("station", stationService.findById(stationId));
		return "updateStationForm";
	}

	@RequestMapping(value = "/update/{stationId}", method = RequestMethod.POST)
	public String updateStationPOST(@PathVariable("stationId") int stationId,
			@ModelAttribute("station") Station newStation) {
		Station currentStation = stationService.findById(stationId);
		currentStation.setName(newStation.getName());
		currentStation.setPhoneNumber(newStation.getPhoneNumber());
		currentStation.setAddress(newStation.getAddress());
		stationService.updateStation(currentStation);
		return "redirect:/station/stations";
	}
}

package com.solt.jdc.boot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.solt.jdc.boot.domains.Cities;
import com.solt.jdc.boot.services.CitiesService;

@Controller
@RequestMapping("/cities")
public class CitiesController {
	@Autowired
	private CitiesService citiesService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addCityGET(Model model) {
		Cities cities = new Cities();
		model.addAttribute("cities", cities);
		return "admin/cities/addCities";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addCityPOST(@ModelAttribute("cities") Cities cities) {
		citiesService.createCity(cities);
		return "redirect:/cities/allcities";
	}

	@RequestMapping("/allcities")
	public String getAllCities(Model model) {
		model.addAttribute("allcities", citiesService.getAllCities());
		return "admin/cities/index";
	}

	@RequestMapping("/delete/{citiesId}")
	public String deleteCities(@PathVariable("citiesId") int citiesId) {
		citiesService.deleteCities(citiesId);
		return "redirect:/cities/allcities";
	}

	@RequestMapping(value = "/update/{citiesId}", method = RequestMethod.GET)
	public String updateCitiesGET(Model model,@PathVariable("citiesId")int citiesId) {
		model.addAttribute("cities",citiesService.findById(citiesId));
		return "admin/cities/updateCitiesForm";
	}
	
	@RequestMapping(value="/update/{citiesId}",method=RequestMethod.POST)
	public String updateCitiesPOST(@ModelAttribute("cities")Cities newCities,@PathVariable("citiesId")int citiesId) {
		Cities currentCities=citiesService.findById(citiesId);
		currentCities.setName(newCities.getName());
		citiesService.updateCities(currentCities);
		return "redirect:/cities/allcities";
	}
}

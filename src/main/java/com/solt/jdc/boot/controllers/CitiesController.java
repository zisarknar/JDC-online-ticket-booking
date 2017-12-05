package com.solt.jdc.boot.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.solt.jdc.boot.domains.Cities;
import com.solt.jdc.boot.services.CitiesService;

@Controller

@RequestMapping("/cities/")
public class CitiesController {
	@Autowired
	private CitiesService citiesService;
	
	@Autowired
	private MainController mainController;

	@RequestMapping(value = "/cities/add", method = RequestMethod.GET)
	public String addCityGET(Model model) {
		Cities cities = new Cities();
		model.addAttribute("cities", cities);
		return "admin/cities/addCities";
	}


	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addCityPOST(@ModelAttribute("cities") @Valid Cities cities,BindingResult result) {
		if(result.hasErrors()) {
    		return "admin/cities/addCities";
    	}
		List<Cities> citiesList=citiesService.getAllCities();
		
		if(citiesList.size()==0) {
			cities.setId(1);
		}
		else {
			cities.setId((citiesList.get(citiesList.size()-1)).getId()+1);
		}
		citiesService.createCity(cities);
		mainController.disallowedFieldException(result);
		return "redirect:/station/stations";
	}

	
	@RequestMapping("/cities")
	public String getAllCities(Model model) {
		model.addAttribute("allcities", citiesService.getAllCities());
		return "admin/cities/index";
	}

	@RequestMapping("/city/delete/{citiesId}")
	public String deleteCities(@PathVariable("citiesId") int citiesId) {
		citiesService.deleteCities(citiesId);
		return "redirect:/admin/cities";
	}


	
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateCitiesPOST(@ModelAttribute("cities") @Valid Cities newCities, BindingResult result) {
		if (result.hasErrors()) {
			return "/station/stations";
		}
		Cities currentCities = citiesService.findById(newCities.getId());
		currentCities.setName(newCities.getName());
		citiesService.updateCities(currentCities);
		return "redirect:/station/stations";
	}
	
	@ResponseBody
	@RequestMapping(value="/loadEntity/{id}")
	public Cities loadEntity(@PathVariable("id")int id) {
		return citiesService.findById(id);
	}
	
	@InitBinder
	public void initializeBinder(WebDataBinder binder) {
		binder.setAllowedFields("id","name");
	}
}

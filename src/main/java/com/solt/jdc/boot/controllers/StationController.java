package com.solt.jdc.boot.controllers;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
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

import com.solt.jdc.boot.domains.Address;
import com.solt.jdc.boot.domains.Cities;
import com.solt.jdc.boot.domains.Station;
import com.solt.jdc.boot.services.AddressService;
import com.solt.jdc.boot.services.CitiesService;
import com.solt.jdc.boot.services.StationService;

@Controller
@RequestMapping("/admin")
public class StationController {
<<<<<<< HEAD
    @Autowired
    private StationService stationService;

    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/stations/add", method = RequestMethod.GET)
    public String addStationGET(Model model) {
    	
        Station station = new Station();
        model.addAttribute("station", station);
        model.addAttribute("addresses", addressService.getAllAddress());
        
        return "admin/station/addStation";
    }

    @RequestMapping(value = "/stations/add", method = RequestMethod.POST)
    public String addStationPOST(Model model, @ModelAttribute("station") Station station) {
        stationService.addStation(station);
        return "redirect:/admin/stations";
    }
=======
	@Autowired
	private StationService stationService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private CitiesService citiesService;

	@Autowired
	private MainController mainController;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addStationGET(Model model) {
		Station station = new Station();
		model.addAttribute("station", station);
		model.addAttribute("addresses", addressService.getAllAddress());
		return "admin/station/addStation";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addStationPOST(Model model, @ModelAttribute("station") @Valid Station station, BindingResult result) {
		if (result.hasErrors()) {
			return "admin/station/addStation";
		}
		List<Station> stationList = stationService.getAllStations();
		if (stationList.size() == 0) {
			station.setId(1);
		} else {
			station.setId((stationList.get(stationList.size() - 1).getId() + 1));
		}
		mainController.disallowedFieldException(result);
		stationService.addStation(station);
		return "redirect:/station/stations";
	}

	@RequestMapping("/stations")
	public String getAllStations(Model model) {
		Station station = new Station();
		Address address = new Address();
		Cities cities = new Cities();

		// Tranferring empty objects to model
		model.addAttribute("station", station);
		model.addAttribute("address", address);
		model.addAttribute("cities", cities);

		// Transferring the required lists to model
		model.addAttribute("stations", stationService.getAllStations());
		model.addAttribute("addresses", addressService.getAllAddress());
		model.addAttribute("allcities", citiesService.getAllCities());

		return "admin/station/index";
	}
>>>>>>> feature/trip&cities_Binding

    @RequestMapping("/stations")
    public String getAllStations(Model model) {
        model.addAttribute("stations", stationService.getAllStations());
        return "admin/station/index";
    }

<<<<<<< HEAD
    @RequestMapping("/station/delete/{stationId}")
    public String deleteStation(@PathVariable("stationId") int stationId) {
        stationService.deleteStation(stationId);
        return "redirect:/admin/stations";
    }

    @RequestMapping(value = "/station/update/{stationId}", method = RequestMethod.GET)
    public String updateStationGET(@PathVariable("stationId") int stationId, Model model) {
        model.addAttribute("station", stationService.findById(stationId));
        model.addAttribute("addresses", addressService.getAllAddress());
        return "admin/station/updateStationForm";
    }

    @RequestMapping(value = "/station/update/{stationId}", method = RequestMethod.POST)
    public String updateStationPOST(@PathVariable("stationId") int stationId,
                                    @ModelAttribute("station") Station newStation) {
        Station currentStation = stationService.findById(stationId);
        currentStation.setName(newStation.getName());
        currentStation.setPhoneNumber(newStation.getPhoneNumber());
        currentStation.setAddress(newStation.getAddress());
        stationService.updateStation(currentStation);
        return "redirect:/admin/stations";
    }
=======
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateStationPOST(@ModelAttribute("station") @Valid Station newStation, BindingResult result) {
		if (result.hasErrors()) {
			return "admin/station/updateStationForm";
		}
		Station currentStation = stationService.findById(newStation.getId());
		currentStation.setName(newStation.getName());
		currentStation.setPhoneNumber(newStation.getPhoneNumber());
		currentStation.setAddress(newStation.getAddress());
		stationService.updateStation(currentStation);
		return "redirect:/station/stations";
	}

	@ResponseBody
	@RequestMapping(value = "loadEntity/{id}")
	public Station loadEntity(@PathVariable("id") int id) {
		return stationService.findById(id);
	}

	@InitBinder
	public void initializeBinder(WebDataBinder binder) {
		binder.setAllowedFields("id", "name", "phoneNumber", "address");
	}
>>>>>>> feature/trip&cities_Binding
}

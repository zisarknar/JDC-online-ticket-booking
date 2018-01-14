package com.solt.jdc.boot.controllers;

import com.solt.jdc.boot.domains.Address;
import com.solt.jdc.boot.domains.Cities;
import com.solt.jdc.boot.domains.Station;
import com.solt.jdc.boot.services.AddressService;
import com.solt.jdc.boot.services.CitiesService;
import com.solt.jdc.boot.services.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class StationController {

    @Autowired
    private StationService stationService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private CitiesService citiesService;

    @Autowired
    private MainController mainController;

    @RequestMapping(value = "/stations/add", method = RequestMethod.GET)
    public String addStationGET(Model model) {
        Station station = new Station();
        model.addAttribute("station", station);
        model.addAttribute("addresses", addressService.getAllAddress());
        return "admin/station/addStation";
    }

    @RequestMapping(value = "/stations/add", method = RequestMethod.POST)
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
        return "redirect:/admin/stations";
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
    
    

    @RequestMapping(value = "/station/update", method = RequestMethod.POST)
    public String updateStationPOST(@ModelAttribute("station") @Valid Station newStation, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/station/updateStationForm";
        }
        Station currentStation = stationService.findById(newStation.getId());
        currentStation.setName(newStation.getName());
        currentStation.setPhoneNumber(newStation.getPhoneNumber());
        currentStation.setAddress(newStation.getAddress());
        stationService.updateStation(currentStation);
        return "redirect:/admin/stations";
    }
    
    @RequestMapping("/station/delete/{id}")
    public String deleteStation(@PathVariable("id")int id) {
    	stationService.deleteStation(id);
    	return "redirect:/admin/stations";
    }

    @ResponseBody
    @RequestMapping(value = "/station/loadEntity/{id}")
    public Station loadEntity(Model model,@PathVariable("id") int id) {
    	
        Station station= stationService.findById(id);
        	model.addAttribute("station",station);
        return station;
    }

    @InitBinder
    public void initializeBinder(WebDataBinder binder) {
        binder.setAllowedFields("id", "name", "phoneNumber", "address");
    }
}

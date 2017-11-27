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
import com.solt.jdc.boot.services.AddressService;
import com.solt.jdc.boot.services.StationService;

@Controller
@RequestMapping("/admin")
public class StationController {
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

    @RequestMapping("/stations")
    public String getAllStations(Model model) {
        model.addAttribute("stations", stationService.getAllStations());
        return "admin/station/index";
    }

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
}

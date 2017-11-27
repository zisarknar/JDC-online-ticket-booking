package com.solt.jdc.boot.controllers;

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

import com.solt.jdc.boot.domains.Bus;
import com.solt.jdc.boot.services.BusService;
import com.solt.jdc.boot.services.BusTypeService;

@Controller
@RequestMapping("/bus")
public class BusController {
	@Autowired
	private BusService busService;

	@Autowired
	private BusTypeService busTypeService;
	
	@Autowired
	private MainController mainController;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addBusGET(Model model) {
		Bus bus = new Bus();
		model.addAttribute("bustypes", busTypeService.getAllBusTypes());
		model.addAttribute("bus", bus);
		return "admin/bus/index";

	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addBusPOST(Model model, @ModelAttribute("bus") @Valid Bus newBus,BindingResult result) {
		if(result.hasErrors()) {
    		return "admin/bus/index";
    	}
		mainController.disallowedFieldException(result);
		busService.createBus(newBus);
		return "redirect:/bus/buses";
	}
	
	@RequestMapping(value="/buses")
	public String getAllBuses(Model model) {
		model.addAttribute("buses", busService.getAllBus());
		return "admin/bus/index";
	}
	
	@RequestMapping(value="/delete/{busId}")
	public String deleteBus(@PathVariable("busId")int busId,Model model) {
		busService.deleteBus(busId);
		return "redirect:/bus/buses";
	}
	
	@RequestMapping(value="/update/{busId}",method=RequestMethod.GET)
	public String updateBusGET(Model model,@PathVariable("busId")int busId) {
		model.addAttribute("bustypes", busTypeService.getAllBusTypes());
		model.addAttribute("bus",busService.findById(busId));
		return "admin/bus/updateBusForm";
	}
	
	@RequestMapping(value="/update/{busId}",method=RequestMethod.POST)
	public String updateBusPOST(Model model,@ModelAttribute("bus") @Valid Bus newBus,@PathVariable("busId")int id,BindingResult result) {
		if(result.hasErrors()) {
    		return "admin/bus/updateBusForm";
    	}
		Bus currentBus=busService.findById(id);
		currentBus.setBusCode(newBus.getBusCode());
		currentBus.setBusCompany(newBus.getBusCompany());
		currentBus.setBusNumber(newBus.getBusNumber());
		currentBus.setMaxSeats(newBus.getMaxSeats());
		currentBus.setTakenSeats(newBus.getTakenSeats());
		currentBus.setBusType(newBus.getBusType());
		
		mainController.disallowedFieldException(result);
		busService.updateBus(currentBus);
		return "redirect:/bus/buses";
	}
	
	@InitBinder
	public void intializeBinder(WebDataBinder binder) {
		binder.setAllowedFields("busNumber","busCompany","busCode","maxSeats","takenSeats","busType");
	}
}

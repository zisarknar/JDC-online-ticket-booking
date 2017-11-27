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

import com.solt.jdc.boot.domains.BusType;
import com.solt.jdc.boot.services.BusTypeService;

@Controller
@RequestMapping(value = "/bustype")
public class BusTypeController {
	
	@Autowired
	private BusTypeService busTypeService;
	
	@Autowired
	private MainController mainController;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addBusTypeGet(Model model) {
		BusType busType = new BusType();
		model.addAttribute("busType", busType);
		return "admin/bus/index";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addBusTypePost(@ModelAttribute("busType") @Valid BusType busType,BindingResult result) {
		if(result.hasErrors()) {
    		return "admin/bustypes/addBusType";
    	}
		mainController.disallowedFieldException(result);
		busTypeService.addBusType(busType);
		return "redirect:/bus/index";
	}
	
	@RequestMapping(value="/bustypes")
	public String getAllBusTypes(Model model) {
		model.addAttribute("bustypes",busTypeService.getAllBusTypes());
		return "admin/bustypes/index";
	}
	
	@RequestMapping(value="/delete/{busTypeId}")
	public String deleteBus(Model model,@PathVariable("busTypeId")int busTypeId) {
		
		busTypeService.deleteBusType(busTypeService.findById(busTypeId));
		return "redirect:/bustype/bustypes";
	}
	
	@RequestMapping(value="/update/{busTypeId}",method=RequestMethod.GET)
	public String updateBusGet(Model model,@PathVariable("busTypeId")int busTypeId) {
		model.addAttribute("bustype", busTypeService.findById(busTypeId));
		return "admin/bustypes/updateform";
	}
	
	@RequestMapping(value="/update/{busTypeId}",method=RequestMethod.POST)
	public String updateBusPOST(@ModelAttribute("bustype") @Valid BusType newbusType,@PathVariable("busTypeId")int id,BindingResult result) {
		if(result.hasErrors()) {
    		return "admin/bustypes/updateform";
    	}
		mainController.disallowedFieldException(result);
		BusType currentBusType=busTypeService.findById(id);
		currentBusType.setType(newbusType.getType());
		
		
		busTypeService.updateBusType(currentBusType);
		return "redirect:/bustype/bustypes";
	}
	
	@InitBinder
	public void initializeBinder(WebDataBinder binder) {
		binder.setAllowedFields("type");
	}
	

}

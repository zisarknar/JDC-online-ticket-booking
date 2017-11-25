package com.solt.jdc.boot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.solt.jdc.boot.domains.BusType;
import com.solt.jdc.boot.services.BusTypeService;

@Controller
@RequestMapping("/admin")
public class BusTypeController {
	
	@Autowired
	private BusTypeService busTypeService;

	@RequestMapping(value = "/bustypes/add", method = RequestMethod.GET)
	public String addBusTypeGet(Model model) {
		BusType busType = new BusType();
		model.addAttribute("busType", busType);
		return "admin/bustypes/addBusType";
	}
	
	@RequestMapping(value="/bustypes/add",method=RequestMethod.POST)
	public String addBusTypePost(@ModelAttribute("busType")BusType busType) {
		busTypeService.addBusType(busType);
		return "redirect:/admin/bustypes";
	}
	
	@RequestMapping(value="/bustypes")
	public String getAllBusTypes(Model model) {
		model.addAttribute("bustypes",busTypeService.getAllBusTypes());
		return "admin/bustypes/index";
	}
	
	@RequestMapping(value="/bustype/delete/{busTypeId}")
	public String deleteBus(Model model,@PathVariable("busTypeId")int busTypeId) {
		
		busTypeService.deleteBusType(busTypeService.findById(busTypeId));
		return "redirect:/admin/bustypes";
	}
	
	@RequestMapping(value="/bustype/update/{busTypeId}",method=RequestMethod.GET)
	public String updateBusGet(Model model,@PathVariable("busTypeId")int busTypeId) {
		model.addAttribute("bustype", busTypeService.findById(busTypeId));
		return "admin/bustypes/updateform";
	}
	
	@RequestMapping(value="/bustype/update/{busTypeId}",method=RequestMethod.POST)
	public String updateBusPOST(@ModelAttribute("bustype")BusType newbusType,@PathVariable("busTypeId")int id) {
		BusType currentBusType=busTypeService.findById(id);
		currentBusType.setType(newbusType.getType());
		busTypeService.updateBusType(currentBusType);
		return "redirect:/admin/bustypes";
	}
	

}

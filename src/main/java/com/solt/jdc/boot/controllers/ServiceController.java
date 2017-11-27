package com.solt.jdc.boot.controllers;

import javax.servlet.http.HttpSession;
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

import com.solt.jdc.boot.domains.Services;
import com.solt.jdc.boot.services.BusService;
import com.solt.jdc.boot.services.ServicesService;

@Controller
@RequestMapping("/services")
public class ServiceController {
	@Autowired
	private ServicesService servicesService;
	
	@Autowired
	private BusService busService;
	
	@Autowired
	private MainController mainController;
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addServiceGET(Model model) {
		Services service=new Services();
		model.addAttribute("service", service);
		model.addAttribute("buses",busService.getAllBus());
		return "admin/services/addService";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addServicePOST(Model model,@ModelAttribute("service") @Valid Services service,BindingResult result) {
		if(result.hasErrors()) {
    		return "admin/services/addService";
    	}
		mainController.disallowedFieldException(result);
		servicesService.addService(service);
		return "redirect:/services/services";
	}
	
	@RequestMapping(value="/services")
	public String getAllServices(Model model) {
		model.addAttribute("services", servicesService.getAllServices());
		return "admin/services/index";
	}
	
	@RequestMapping(value="/delete/{serviceId}")
	public String deleteService(@PathVariable("serviceId")int serviceId,Model model) {
		servicesService.deleteService(serviceId);
		return "redirect:/services/services";
	}
	
	@RequestMapping(value="/update/{serviceId}",method=RequestMethod.GET)
	public String updateServiceGET(Model model,@PathVariable("serviceId")int serviceId) {
		model.addAttribute("service", servicesService.findById(serviceId));
		model.addAttribute("buses",busService.getAllBus());
		return "admin/services/updateServiceForm";
	}
	
	@RequestMapping(value="/update/{serviceId}",method=RequestMethod.POST)
	public String updateServicePOST(@ModelAttribute("service") @Valid Services newService,@PathVariable("serviceId")int serviceId,BindingResult result) {
		if(result.hasErrors()) {
    		return "admin/bus/updateServiceForm";
    	}
		Services currentService=servicesService.findById(serviceId);
		currentService.setServices(newService.getServices());
		currentService.setBus(newService.getBus());
		mainController.disallowedFieldException(result);
		servicesService.updateService(currentService);
		return "redirect:/services/services";
	}
	
	@InitBinder
	public void intializeBinder(WebDataBinder binder) {
		binder.setAllowedFields("services","bus");
	}
}

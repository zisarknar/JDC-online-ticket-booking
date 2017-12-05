package com.solt.jdc.boot.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.solt.jdc.boot.domains.Services;
import com.solt.jdc.boot.services.BusService;
import com.solt.jdc.boot.services.ServicesService;


@Controller
@RequestMapping("/admin")
public class ServiceController {
<<<<<<< HEAD
    @Autowired
    private ServicesService servicesService;

    @Autowired
    private BusService busService;

    @RequestMapping(value = "/services/add", method = RequestMethod.GET)
    public String addServiceGET(Model model) {
        Services service = new Services();
        model.addAttribute("service", service);
        model.addAttribute("buses", busService.getAllBus());
        return "admin/services/addService";
    }

    @RequestMapping(value = "/services/add", method = RequestMethod.POST)
    public String addServicePOST(Model model, @ModelAttribute("service") Services service) {
        servicesService.addService(service);
        return "redirect:/admin/services";
    }

    @RequestMapping(value = "/services")
    public String getAllServices(Model model) {
        model.addAttribute("services", servicesService.getAllServices());
        return "admin/services/index";
    }

    @RequestMapping(value = "/service/delete/{serviceId}")
    public String deleteService(@PathVariable("serviceId") int serviceId, Model model) {
        servicesService.deleteService(serviceId);
        return "redirect:/admin/services";
    }

    @RequestMapping(value = "/service/update/{serviceId}", method = RequestMethod.GET)
    public String updateServiceGET(Model model, @PathVariable("serviceId") int serviceId) {
        model.addAttribute("service", servicesService.findById(serviceId));
        model.addAttribute("buses", busService.getAllBus());
        return "admin/services/updateServiceForm";
    }

    @RequestMapping(value = "/service/update/{serviceId}", method = RequestMethod.POST)
    public String updateServicePOST(@ModelAttribute("service") Services newService, @PathVariable("serviceId") int serviceId) {
        Services currentService = servicesService.findById(serviceId);
        currentService.setServices(newService.getServices());
        currentService.setBus(newService.getBus());
        servicesService.updateService(currentService);
        return "redirect:/admin/services";
    }
=======
	@Autowired
	private ServicesService servicesService;
	
	@Autowired
	private BusService busService;
	
	@Autowired
	private MainController mainController;
	
	
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addServicePOST(Model model,@ModelAttribute("service") @Valid Services service,BindingResult result) {
		if(result.hasErrors()) {
    		return "admin/services/addService";
    	}
		List<Services> servicesList=servicesService.getAllServices();
		if(servicesList.size()==0) {
			service.setId(1);
		}
		else {
			Services serviceForId=servicesList.get(servicesList.size()-1);
			service.setId(serviceForId.getId()+1);
		}
		mainController.disallowedFieldException(result);
		servicesService.addService(service);
		return "redirect:/bus/buses";
	}
	
	
	
	@RequestMapping(value="/delete/{serviceId}")
	public String deleteService(@PathVariable("serviceId")int serviceId,Model model) {
		servicesService.deleteService(serviceId);
		return "redirect:/bus/buses";
	}
	
	
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String updateServicePOST(@ModelAttribute("service") @Valid Services newService,BindingResult result) {
		if(result.hasErrors()) {
    		return "admin/bus/updateServiceForm";
    	}
		Services currentService=servicesService.findById(newService.getId());
		currentService.setServices(newService.getServices());
		currentService.setBus(newService.getBus());
		servicesService.updateService(currentService);
		return "redirect:/bus/buses";
	}
	
	
	
	@ResponseBody
	@RequestMapping(value="/loadEntity/{id}")
	public Services loadEntity(@PathVariable("id")int id) {
		return servicesService.findById(id);
	}
	
	@InitBinder
	public void intializeBinder(WebDataBinder binder) {
		binder.setAllowedFields("id","services","bus");
	}
>>>>>>> feature/trip&cities_Binding
}

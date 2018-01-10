package com.solt.jdc.boot.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.solt.jdc.boot.domains.Customer;
import com.solt.jdc.boot.services.CustomerService;
import com.solt.jdc.boot.services.TripService;
import com.solt.jdc.boot.utils.IAuthenticationFacade;

@Controller
@RequestMapping("frontendBooking")
public class frontbooking {

	@Autowired 
	private TripService tripService;
	
	@Autowired
	private CustomerService customerService;
	
	
	 @Autowired
	 private IAuthenticationFacade iAuthenticationFacade;
	
	
	@RequestMapping("/trip/{id}")
    public String getTrip(Model model, @PathVariable("id") int tripFilterId) {
        model.addAttribute("trip", tripService.getTrip(tripFilterId));
        
        Authentication authentication = iAuthenticationFacade.getAuthentiation();
        String currentLoggedinUser= authentication.getName();
        
        model.addAttribute("customer",customerService.findByEmail(currentLoggedinUser));
        return "frontend/booking";
    }
	
	/*@RequestMapping("/customer/{'cusName'}")
	public String getCustomer(Model model,@Param("cusName")String cusName){
		Customer customer=customerService.findByusername(cusName);
		model.addAttribute("customer",customer);
		
		return "frontend/booking";
	}*/	
}

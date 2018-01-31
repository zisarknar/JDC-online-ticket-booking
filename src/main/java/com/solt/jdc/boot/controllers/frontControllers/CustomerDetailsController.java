package com.solt.jdc.boot.controllers.frontControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.solt.jdc.boot.domains.Customer;
import com.solt.jdc.boot.services.CustomerService;
import com.solt.jdc.boot.utils.IAuthenticationFacade;

@Controller
public class CustomerDetailsController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private IAuthenticationFacade iAuthenticationFacade;
	
	@RequestMapping("/customerdetails/account")
	public String customerDetails(Model model) {
		
		//Getting authentication
		Authentication authentication=iAuthenticationFacade.getAuthentiation();
		String loggedInUser=authentication.getName();
		
		
		//Finding customer from database
		Customer databaseCustomer=customerService.findByusername(loggedInUser);
		
		Customer socialCustomer=new Customer();
	
		if(databaseCustomer!=null) {
			model.addAttribute("customer", databaseCustomer);
		}
		else {
			
		}
		
		
		return "frontend/account";
	}
}

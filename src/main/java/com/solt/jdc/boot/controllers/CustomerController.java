package com.solt.jdc.boot.controllers;

import com.solt.jdc.boot.domains.Customer;
import com.solt.jdc.boot.services.CustomerService;
<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
=======

import javax.validation.Valid;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
>>>>>>> feature/trip&cities_Binding

@Controller
public class CustomerController {

<<<<<<< HEAD
	@GetMapping("/customerdetails")
	public String getCustomerDetails() {
		
		return "customerdetail/customerdetailpage";
	
	}
	
	
=======
    private CustomerService customerService;
    
    @Autowired
    private MainController mainController;

>>>>>>> feature/trip&cities_Binding
    @Autowired
    private CustomerService customerService;

    @RequestMapping("/customers")
    public String getAllCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "admin/customer/index";
    }

    @RequestMapping("/customer/{id}")
    public String getCustomer(Model model, @PathVariable("id") int customerId) {
        model.addAttribute("customer", customerService.getCustomer(customerId));
        return "admin/customer/index";
    }

    @RequestMapping(value = "/customers/add", method = RequestMethod.GET)
    public String addCustomer(Model model) {
        Customer customer = new Customer();
        model.addAttribute("newCustomer", customer);
        return "admin/customer/addNew";
    }

<<<<<<< HEAD
    @RequestMapping(value = "/customers/add", method = RequestMethod.POST)
    public String processAddCustomer(@ModelAttribute("newCustomer") Customer customer) {
=======
    @RequestMapping(value = "/customers/add" ,method = RequestMethod.POST)
    public String processAddCustomer(@ModelAttribute ("newCustomer") @Valid Customer customer,BindingResult result){
    	if(result.hasErrors()) {
    		return "admin/customer/addNew";
    	}
    	mainController.disallowedFieldException(result);
>>>>>>> feature/trip&cities_Binding
        customerService.saveCustomer(customer);
        return "redirect:/customers";
    }

    @RequestMapping(value = "/customer/update/{id}", method = RequestMethod.GET)
    public String updateCustomer(Model model, @PathVariable("id") int customerId) {
        model.addAttribute("updatedCustomer", customerService.getCustomer(customerId));
        return "admin/customer/update";
    }

    @RequestMapping(value = "/customer/update/{id}", method = RequestMethod.POST)
<<<<<<< HEAD
    public String processUpdateCustomer(@ModelAttribute("updatedCustomer") Customer updatedCustomer, @PathVariable("id") int customerId) {
=======
    public String processUpdateCustomer(@ModelAttribute("updatedCustomer") @Valid Customer updatedCustomer, @PathVariable("id") int customerId,BindingResult result ){
    	if(result.hasErrors()) {
    		return "admin/customer/update";
    	}
>>>>>>> feature/trip&cities_Binding
        Customer currentCustomer = customerService.getCustomer(customerId);
        currentCustomer.setUsername(updatedCustomer.getUsername());
        currentCustomer.setBooking(updatedCustomer.getBooking());
        currentCustomer.setFirstName(updatedCustomer.getFirstName());
        currentCustomer.setLastName(updatedCustomer.getLastName());
        currentCustomer.setEmail(updatedCustomer.getEmail());
        currentCustomer.setPhone(updatedCustomer.getPhone());
        currentCustomer.setPassword(updatedCustomer.getPassword());
        currentCustomer.setMatchPassword(updatedCustomer.getMatchPassword());
        currentCustomer.setNrcNumber(updatedCustomer.getNrcNumber());
<<<<<<< HEAD
=======
        currentCustomer.setDeactivated(updatedCustomer.isDeactivated());
        
>>>>>>> feature/trip&cities_Binding
        customerService.updateCustomer(currentCustomer);
        return "redirect:/customers";
    }

    @RequestMapping("/customer/delete/{id}")
    public String deleteCustomer(@PathVariable("id") int customerId) {
        customerService.deleteCustomer(customerId);
        return "redirect:/customers";
    }
    
    @InitBinder
    public void initializeBinder(WebDataBinder binder) {
    	binder.setAllowedFields("username","firstName","lastName","password","phone","email","nrcNumber","isDeactivated","booking");
    }

    @RequestMapping(value = "/customer/register", method = RequestMethod.GET)
    public String registerCustomer(Model model) {
        Customer regCustomer = new Customer();
        model.addAttribute("regCustomer", regCustomer);
        return "admin/register";
    }

    @RequestMapping(value = "/customer/register", method = RequestMethod.POST)
    public String processRegisterCustomer(@ModelAttribute("regCustomer") Customer regedCustomer) {
        customerService.saveCustomer(regedCustomer);
        return "redirect:/login";
    }
}

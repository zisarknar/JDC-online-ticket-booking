package com.solt.jdc.boot.controllers;

import com.solt.jdc.boot.domains.Customer;
import com.solt.jdc.boot.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class CustomerController {

    @Autowired
    private MainController mainController;

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

    @RequestMapping(value = "/customers/add", method = RequestMethod.POST)
    public String processAddCustomer(@ModelAttribute("newCustomer") @Valid Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/customer/addNew";
        }
        mainController.disallowedFieldException(result);
        customerService.saveCustomer(customer);
        return "redirect:/customers";
    }

    @RequestMapping(value = "/customer/update/{id}", method = RequestMethod.GET)
    public String updateCustomer(Model model, @PathVariable("id") int customerId) {
        model.addAttribute("updatedCustomer", customerService.getCustomer(customerId));
        return "admin/customer/update";
    }

    @RequestMapping(value = "/customer/update/{id}", method = RequestMethod.POST)
    public String processUpdateCustomer(@ModelAttribute("updatedCustomer") @Valid Customer updatedCustomer, @PathVariable("id") int customerId, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/customer/update";
        }
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
        binder.setAllowedFields("username", "firstName", 
        		"lastName", "password","matchPassword", "phone", "email", "nrcNumber","address");
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

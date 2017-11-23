package com.solt.jdc.boot.controllers;

import com.solt.jdc.boot.domains.Customer;
import com.solt.jdc.boot.repositories.CustomerRepository;
import com.solt.jdc.boot.services.CustomerService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public void getCustomerRepository(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping("/customers")
    public String getAllCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "admin/customer/index";
    }

    @RequestMapping("/customer/{id}")
    public String getCustomer(Model model, @PathVariable ("id") int customerId) {
        model.addAttribute("customer", customerService.getCustomer(customerId));
        return "admin/customer/index";
    }

    @RequestMapping(value = "/customers/add" ,method = RequestMethod.GET)
    public String addCustomer(Model model){
        Customer customer = new Customer();
        model.addAttribute("newCustomer", customer);
        return "admin/customer/addNew";
    }

    @RequestMapping(value = "/customers/add" ,method = RequestMethod.POST)
    public String processAddCustomer(@ModelAttribute ("newCustomer") Customer customer){
        customerService.saveCustomer(customer);
        return "redirect:/admin/customer/index";
    }

    @RequestMapping(value = "/customer/update/{id}", method = RequestMethod.GET)
    public String updateCustomer(Model model, @PathVariable ("id") int customerId) {
        model.addAttribute("updatedCustomer", customerService.getCustomer(customerId));
        return "admin/customer/update";
    }

    @RequestMapping(value = "/customer/update/{id}", method = RequestMethod.POST)
    public String processUpdateCustomer(@ModelAttribute("updatedCustomer") Customer updatedCustomer, @PathVariable("id") int customerId){
        Customer currentCustomer = customerService.getCustomer(customerId);
        currentCustomer.setUsername(updatedCustomer.getUsername());
        currentCustomer.setBooking(updatedCustomer.getBooking());
        currentCustomer.setFirstName(updatedCustomer.getFirstName());
        currentCustomer.setLastName(updatedCustomer.getLastName());
        currentCustomer.setEmail(updatedCustomer.getEmail());
        currentCustomer.setPassword(updatedCustomer.getPassword());
        currentCustomer.setNrcNumber(updatedCustomer.getNrcNumber());
        currentCustomer.setDeactivated(updatedCustomer.isDeactivated());
        customerService.updateCustomer(currentCustomer);
        return "redirect:/admin/customer/index";
    }

    @RequestMapping("/customer/delete/{id}")
    public String deleteCustomer(@PathVariable ("id") int customerId) {
        customerService.deleteCustomer(customerId);
        return "redirect:/customers";
    }


}

package com.solt.jdc.boot.controllers;

import com.solt.jdc.boot.repositories.CustomerRepository;
import com.solt.jdc.boot.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
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

    @RequestMapping("/customers/{id}")
    public String getCustomer(Model model, @PathVariable ("id") int customerId) {
        model.addAttribute("customer", customerService.getCustomer(customerId));
        return "admin/customer/index";
    }

}

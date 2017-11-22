package com.solt.jdc.boot.controllers;

import com.solt.jdc.boot.repositories.CustomerRepository;
import com.solt.jdc.boot.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public void getCustomerRepository(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping("/customers")
    public String customers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("customer_count", customerService.getCustomersCount());
        return "admin/customer/index";
    }

}

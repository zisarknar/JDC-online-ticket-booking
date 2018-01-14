package com.solt.jdc.boot.passwordforget.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.solt.jdc.boot.domains.Customer;
import com.solt.jdc.boot.passwordforget.CustomerRegistrationDto;
import com.solt.jdc.boot.services.CustomerService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller

@RequestMapping("/registration")
public class CustomerRegistrationController {
    @Autowired
   private CustomerService customerService;

    
    @ModelAttribute("customer")
    public CustomerRegistrationDto  userRegistrationDto() {
        return new CustomerRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model,HttpServletRequest request) {
        return "admin/register";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("customer") @Valid CustomerRegistrationDto customerDto,
                                      BindingResult result){

        Customer existing = customerService.findByEmail(customerDto.getEmail());
        if (existing != null){
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()){
            return "admin/register";
        }

        customerService.save(customerDto);
        return "redirect:/registration?success";
    }

}

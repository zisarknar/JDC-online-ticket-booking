package com.solt.jdc.boot.controllers;

import com.solt.jdc.boot.domains.Customer;
import com.solt.jdc.boot.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class CustomerController {

    @Autowired
    private MainController mainController;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping("/customerdetails")
    public String userDetails(Model model) {
        return "frontend/index";
    }

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/customers")
    public String getAllCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "admin/customer/index";
    }

    @RequestMapping("/customers/{id}")
    public String getCustomer(Model model, @PathVariable("id") int customerId) {
        model.addAttribute("updatedCustomer", customerService.getCustomer(customerId));
        return "admin/customer/viewCustomer";
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
        return "redirect:/admin/customers";
    }

    @RequestMapping(value = "/customers/update/{id}", method = RequestMethod.GET)
    public String updateCustomer(Model model, @PathVariable("id") int customerId) {
        model.addAttribute("updatedCustomer", customerService.getCustomer(customerId));
        return "admin/customer/viewCustomer";
    }

    @RequestMapping(value = "/customers/update/{id}", method = RequestMethod.POST)
    public String processUpdateCustomer(@ModelAttribute("updatedCustomer") @Valid Customer updatedCustomer, @PathVariable("id") int customerId, BindingResult result) {
        if (result.hasErrors()) {
            return "/admin/customer/index";
        }
        mainController.disallowedFieldException(result);
        Customer currentCustomer = customerService.getCustomer(customerId);
        currentCustomer.setUsername(updatedCustomer.getUsername());
        currentCustomer.setBookingList(updatedCustomer.getBookingList());
        currentCustomer.setFirstName(updatedCustomer.getFirstName());
        currentCustomer.setLastName(updatedCustomer.getLastName());
        currentCustomer.setEmail(updatedCustomer.getEmail());
        currentCustomer.setPhone(updatedCustomer.getPhone());
        currentCustomer.setPassword(bCryptPasswordEncoder.encode(updatedCustomer.getPassword()));
        currentCustomer.setMatchPassword(updatedCustomer.getMatchPassword());
        currentCustomer.setNrcNumber(updatedCustomer.getNrcNumber());
        currentCustomer.setAddress(updatedCustomer.getAddress());

        customerService.updateCustomer(currentCustomer);
        return "redirect:/customers/update/" + customerId;
    }

    @RequestMapping("/customers/delete/{id}")
    public String deleteCustomer(@PathVariable("id") int customerId) {
        customerService.deleteCustomer(customerId);
        return "redirect:/admin/customers";
    }

    @InitBinder
    public void initializeBinder(WebDataBinder binder) {
        binder.setAllowedFields("id", "username", "firstName", "lastName", "password", "phone", "matchPassword", "tempPassword", "email", "nrcNumber", "enabled", "booking", "address","role");

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

    @RequestMapping(value = "/customerdetails/account", method = RequestMethod.GET)
    public String viewProfile(ModelMap model) {
        model.addAttribute("customer", customerService.currentCustomer());
        return "frontend/account";
    }

    @RequestMapping(value = "/customerdetails/account/edit", method = RequestMethod.GET)
    public String editCustomerProfile(Model model) {
        model.addAttribute("customer", customerService.currentCustomer());
        return "/frontend/edit-account";
    }

    @RequestMapping(value = "/customerdetails/account/edit", method = RequestMethod.POST)
    public String processEditCustomerProfile(@ModelAttribute("customer") @Valid Customer updatedCustomer, BindingResult result) {
        if (result.hasErrors()) {
            return "/frontend/edit-account";
        }
        Customer currentCustomer = customerService.getCustomer(customerService.currentCustomer().getId());
        currentCustomer.setUsername(updatedCustomer.getUsername());
        currentCustomer.setBookingList(updatedCustomer.getBookingList());
        currentCustomer.setFirstName(updatedCustomer.getFirstName());
        currentCustomer.setLastName(updatedCustomer.getLastName());
        currentCustomer.setEmail(updatedCustomer.getEmail());
        currentCustomer.setPhone(updatedCustomer.getPhone());
        currentCustomer.setMatchPassword(updatedCustomer.getMatchPassword());
        currentCustomer.setNrcNumber(updatedCustomer.getNrcNumber());
        currentCustomer.setAddress(updatedCustomer.getAddress());
        mainController.disallowedFieldException(result);
        customerService.updateCustomer(currentCustomer);
        return "redirect:/customerdetails/account";
    }

    @RequestMapping(value = "/customerdetails/account/changepassword", method = RequestMethod.GET)
    public String changePassword(Model model) {
        Customer customer = customerService.currentCustomer();
        model.addAttribute("customer", customer);
        model.addAttribute("password", customer.getPassword());
        return "/frontend/change-password";
    }

    @RequestMapping(value = "/customerdetails/account/changepassword", method = RequestMethod.POST)
    public String processChangePassword(@ModelAttribute("customer") Customer updatedPassword, Model model, BindingResult result) {
        String currentPassword = customerService.currentCustomer().getPassword();
        if (!bCryptPasswordEncoder.matches(updatedPassword.getPassword(), currentPassword)) {
            model.addAttribute("passwordMsg", "Your current password is not valid!");
            return "/frontend/change-password";
        }
        Customer currentCustomer = customerService.getCustomer(customerService.currentCustomer().getId());
        System.out.println(updatedPassword.getMatchPassword());
        currentCustomer.setPassword(bCryptPasswordEncoder.encode(updatedPassword.getTempPassword()));
        customerService.updateCustomer(currentCustomer);
        return "redirect:/customerdetails/account";
    }

}

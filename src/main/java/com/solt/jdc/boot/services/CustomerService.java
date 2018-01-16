package com.solt.jdc.boot.services;


import com.solt.jdc.boot.domains.Customer;

import com.solt.jdc.boot.controllers.passwordforget.CustomerRegistrationDto;

import com.solt.jdc.boot.domains.UserRole;


import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomerService extends UserDetailsService {

    List<Customer> getAllCustomers();

    Customer getCustomer(int id);

    void saveCustomer(Customer customer);

    void deleteCustomer(int id);

    Customer updateCustomer(Customer customer);

    Customer findByusername(String username);

    //===========================

    Customer findByEmail(String email);

    Customer save(CustomerRegistrationDto registration);

    void updatePassword(String password, Integer userId);

    public UserDetails loadCustomerByUsername(String email);

    //=============================
    long getCustomerCount();

    Customer currentCustomer();

    UserRole getCustomerRole();
}

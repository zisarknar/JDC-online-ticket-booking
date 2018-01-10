package com.solt.jdc.boot.services;

import com.solt.jdc.boot.domains.Customer;
import com.solt.jdc.boot.domains.UserRole;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();

    Customer getCustomer(int id);

    void saveCustomer(Customer customer);

    void deleteCustomer(int id);

    Customer updateCustomer(Customer customer);
    
    Customer findByEmail(String email);
     UserRole getCustomerRole();
     Customer currentCustomer();
     Customer findByUsername(String username);
}

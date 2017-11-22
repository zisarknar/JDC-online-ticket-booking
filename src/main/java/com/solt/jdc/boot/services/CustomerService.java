package com.solt.jdc.boot.services;

import com.solt.jdc.boot.domains.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();
    int getCustomersCount();
}

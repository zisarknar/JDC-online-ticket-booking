package com.solt.jdc.boot.services.Impl;

import com.solt.jdc.boot.domains.Customer;
import com.solt.jdc.boot.repositories.CustomerRepository;
import com.solt.jdc.boot.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void getCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomer(int id) {
        return customerRepository.getOne(id);
    }

    @Override
    public void saveCustomer(Customer customer) {
        customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
        customer.isEnabled();
        customerRepository.saveAndFlush(customer);
    }

    @Override
    public void deleteCustomer(int id) {
        customerRepository.delete(id);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }
}

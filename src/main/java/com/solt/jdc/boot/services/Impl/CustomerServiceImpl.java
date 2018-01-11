package com.solt.jdc.boot.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.solt.jdc.boot.domains.Customer;
import com.solt.jdc.boot.domains.Role;
import com.solt.jdc.boot.domains.UserRole;
import com.solt.jdc.boot.repositories.CustomerRepository;
import com.solt.jdc.boot.services.CustomerService;
import com.solt.jdc.boot.services.UserRoleService;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    @Autowired
    private UserRoleService userRoleService;
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
        customer.setEnabled(true);
        customer.setRole_user(getCustomerRole());
        customerRepository.saveAndFlush(customer);
    }

    //moe
    @Override
    public UserRole getCustomerRole() {
        UserRole role = userRoleService.findByRole("ROLE_CUSTOMER");
        if (role != null) {
            return role;
        } else {
            role = new UserRole();
            role.setRole("ROLE_CUSTOMER");
            userRoleService.saveRoleUser(role);
            return role;
        }
    }

    @Override
    public void deleteCustomer(int id) {
        customerRepository.delete(id);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    //moe
    public String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());
        return authentication.getName();
    }

    @Override
    public Customer currentCustomer() {
        return customerRepository.findByUsername(getCurrentUserName());
    }

    @Override
    public Customer findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    @Override
    public long getCustomerCount() {
        return customerRepository.count();
    }
}

package com.solt.jdc.boot.services.Impl;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Service;

import com.solt.jdc.boot.domains.Customer;
import com.solt.jdc.boot.repositories.CustomerRepository;


@Service("customer_details_service")
public class CustomerDetailsService implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private Facebook facebook;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println(email);
        Customer customer = customerRepository.findByEmail(email);
        User user = new User(customer.getUsername(), customer.getPassword(), getAuthorities(customer));
        return user;
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(Customer customer) {
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(customer.getRole().getName());
        return authorities;
    }
}

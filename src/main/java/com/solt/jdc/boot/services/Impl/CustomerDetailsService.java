package com.solt.jdc.boot.services.Impl;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.solt.jdc.boot.domains.Customer;
import com.solt.jdc.boot.repositories.CustomerRepository;

@Service
public class CustomerDetailsService implements UserDetailsService {
	@Autowired
	private CustomerRepository customerRepository;
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Customer customer=customerRepository.findByEmail(email);
		
		return new User(customer.getUsername(), customer.getPassword(), getAuthorities(customer));
	}
	private static Collection<? extends GrantedAuthority> getAuthorities(Customer customer)
	{
	Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(customer.getRole_user().getRole());
	return authorities;
	}
}

package com.solt.jdc.boot.services.Impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solt.jdc.boot.domains.User;
import com.solt.jdc.boot.repositories.UserRepository;
import com.solt.jdc.boot.services.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User find(int id) {
		return userRepository.findOne(id);
	}

	@Override
	public void save(User user) {
		userRepository.save(user);
		
	}

	@Override
	public void delete(User user) {
		userRepository.delete(user);
		
	}

	@Override
	public void update(User user) {
		userRepository.save(user);
		
	}
	
	
}

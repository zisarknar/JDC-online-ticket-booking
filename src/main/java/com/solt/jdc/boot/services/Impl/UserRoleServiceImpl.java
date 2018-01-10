package com.solt.jdc.boot.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solt.jdc.boot.domains.Role;
import com.solt.jdc.boot.domains.UserRole;
import com.solt.jdc.boot.repositories.UserRoleRepository;
import com.solt.jdc.boot.services.UserRoleService;
@Service
public class UserRoleServiceImpl implements UserRoleService {
	@Autowired
	private UserRoleRepository userRoleRepository;
	@Override
	public List<UserRole> getAllRoleUser() {
		return userRoleRepository.findAll();
	}

	@Override
	public UserRole getRoleUser(int id) {
		return userRoleRepository.getOne(id);
	}

	@Override
	public void saveRoleUser(UserRole role) {
		userRoleRepository.save(role);
	}

	@Override
	public void deleteRoleUser(int id) {
		userRoleRepository.delete(id);
	}

	@Override
	public UserRole updateRoleUser(UserRole role) {
		return userRoleRepository.saveAndFlush(role);
	}

	@Override
	public UserRole findByRole(String role) {
		return userRoleRepository.findByRole(role);
	}

	

}

package com.solt.jdc.boot.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solt.jdc.boot.domains.Role;
import com.solt.jdc.boot.repositories.RoleRepository;
import com.solt.jdc.boot.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}

	@Override
	public Role getRole(int id) {
		return roleRepository.findOne(id);
	}

	@Override
	public void saveRole(Role role) {
		roleRepository.saveAndFlush(role);
		
	}

	@Override
	public void deleteRole(int id) {
		roleRepository.delete(id);
		
	}

	@Override
	public Role updateRole(Role role) {
		
		return roleRepository.saveAndFlush(role);
	}

	@Override
	public Role findByRole(String roleName) {
		return roleRepository.findByName(roleName);
	}

}

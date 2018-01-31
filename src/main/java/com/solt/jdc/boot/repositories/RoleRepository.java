package com.solt.jdc.boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.solt.jdc.boot.domains.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	Role findByName(String roleName);
}

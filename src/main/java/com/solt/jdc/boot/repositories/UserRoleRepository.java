package com.solt.jdc.boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.solt.jdc.boot.domains.Role;
import com.solt.jdc.boot.domains.UserRole;
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer>{
	UserRole findByRole(String role);
}

package com.solt.jdc.boot.services;

import java.util.List;

import com.solt.jdc.boot.domains.Role;
import com.solt.jdc.boot.domains.UserRole;

public interface UserRoleService {
	List<UserRole> getAllRoleUser();

	UserRole getRoleUser(int id);

    void saveRoleUser(UserRole role);

    void deleteRoleUser(int id);

    UserRole updateRoleUser(UserRole role);
    

	UserRole findByRole(String string);
}

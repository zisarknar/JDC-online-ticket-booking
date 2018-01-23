package com.solt.jdc.boot.services;


import java.util.List;

import com.solt.jdc.boot.domains.Role;


public interface RoleService {
    List<Role> getAllRoles();

    Role getRole(int id);

    void saveRole(Role trip);

    void deleteRole(int id);

    Role updateRole(Role trip);
    
    Role findByRole(String roleName);
}

package com.solt.jdc.boot.services;

import com.solt.jdc.boot.domains.RootAdmin;

import java.util.List;

public interface RootAdminService {

    List<RootAdmin> getAllRootAdmin();

    RootAdmin getRootAdmin(int id);

    void saveRootAdmin(RootAdmin rootAdmin);

    void deleteRootAdmin(int id);

    RootAdmin updateRootAdmin(RootAdmin rootAdmin);
}

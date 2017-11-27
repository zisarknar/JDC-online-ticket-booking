package com.solt.jdc.boot.services.Impl;

import com.solt.jdc.boot.domains.RootAdmin;
import com.solt.jdc.boot.repositories.RootAdminRepository;
import com.solt.jdc.boot.services.RootAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RootAdminServiceImpl implements RootAdminService {

    @Autowired
    private RootAdminRepository rootAdminRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<RootAdmin> getAllRootAdmin() {
        return rootAdminRepository.findAll();
    }

    @Override
    public RootAdmin getRootAdmin(int id) {
        return rootAdminRepository.getOne(id);
    }

    @Override
    public void saveRootAdmin(RootAdmin rootAdmin) {
        rootAdmin.setRootPassword(bCryptPasswordEncoder.encode(rootAdmin.getRootPassword()));
        rootAdmin.setRoot(true);
        rootAdminRepository.saveAndFlush(rootAdmin);
    }

    @Override
    public void deleteRootAdmin(int id) {
        rootAdminRepository.delete(id);
    }

    @Override
    public RootAdmin updateRootAdmin(RootAdmin rootAdmin) {
        return rootAdminRepository.saveAndFlush(rootAdmin);
    }
}

package com.solt.jdc.boot.services.Impl;

import com.solt.jdc.boot.domains.RootAdmin;
import com.solt.jdc.boot.repositories.RootAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipal;
import java.util.Collection;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private RootAdminRepository rootAdminRepository;

    @Override
    public UserDetails loadUserByUsername(String rootName) throws UsernameNotFoundException {
        RootAdmin root = rootAdminRepository.findByRootName(rootName);
        if (root == null) {
            throw new UsernameNotFoundException(rootName);
        }
        return new MyUserPrincipal(root);
    }

    private class MyUserPrincipal implements UserDetails {
        private RootAdmin rootAdmin;

        public MyUserPrincipal(RootAdmin rootAdmin){
            this.rootAdmin = rootAdmin;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return null;
        }

        @Override
        public String getPassword() {
            return this.rootAdmin.getRootPassword();
        }

        @Override
        public String getUsername() {
            return this.rootAdmin.getRootName();
        }

        @Override
        public boolean isAccountNonExpired() {
            return false;
        }

        @Override
        public boolean isAccountNonLocked() {
            return false;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return false;
        }

        @Override
        public boolean isEnabled() {
            return false;
        }
    }
}

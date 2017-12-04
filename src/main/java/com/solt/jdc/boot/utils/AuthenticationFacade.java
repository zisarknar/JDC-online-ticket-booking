package com.solt.jdc.boot.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade implements IAuthenticationFacade{

    @Override
    public Authentication getAuthentiation() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
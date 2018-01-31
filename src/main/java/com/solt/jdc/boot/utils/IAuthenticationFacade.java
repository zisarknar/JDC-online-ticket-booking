package com.solt.jdc.boot.utils;

import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {
    Authentication getAuthentiation();

    String getAuthenticatedUserName();
}

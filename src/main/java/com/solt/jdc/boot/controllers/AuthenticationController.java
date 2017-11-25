package com.solt.jdc.boot.controllers;

import com.solt.jdc.boot.utils.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class AuthenticationController {

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public String currentUserName(Authentication authentication) {
            return authenticationFacade.getAuthentiation().getName();
    }



}

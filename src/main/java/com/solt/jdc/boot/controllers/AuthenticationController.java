package com.solt.jdc.boot.controllers;

import com.solt.jdc.boot.utils.IAuthenticationFacade;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthenticationController {

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public String currentUserName(Authentication authentication) {
            return authenticationFacade.getAuthentiation().getName();
    }

    @RequestMapping("/login")
    public String getLoginPage(HttpServletRequest request,Model model){
    	
    	/* String referrer = request.getHeader("Referer");
    	 request.getSession().setAttribute("url_prior_login", referrer);
    	*/
    	
        return "admin/login";
    }
 
   /* @RequestMapping("/registration")
    	public String getRegistrationPage(HttpServletRequest request) {
    		return "admin/register";
    	}*/
}
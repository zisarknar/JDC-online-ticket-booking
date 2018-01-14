package com.solt.jdc.boot.handlers;

import com.solt.jdc.boot.domains.Trip;
import com.solt.jdc.boot.services.TripService;
import com.solt.jdc.boot.utils.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Collection;


public class SuccessHandler implements AuthenticationSuccessHandler   {
/*AuthenticationSuccessHandler*/ 
    @Autowired
    private IAuthenticationFacade iAuthenticationFacade;
    private static final SimpleGrantedAuthority CUSTOMER_AUTHORITY = new SimpleGrantedAuthority("ROLE_CUSTOMER");
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, 
    																Authentication authentication)
            throws IOException, ServletException {
        Authentication auth = iAuthenticationFacade.getAuthentiation();

      if (auth != null) {

            Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
            if (authorities.contains(CUSTOMER_AUTHORITY)) {
                redirectStrategy.sendRedirect(request, response, "/");
            } else {

                redirectStrategy.sendRedirect(request, response, "/admin");
            }

        } else {
            System.out.println("auth is null");
        }

    }//end of method
}//end of class





package com.solt.jdc.boot.handlers;

import com.solt.jdc.boot.utils.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class SuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private IAuthenticationFacade iAuthenticationFacade;
    private static final SimpleGrantedAuthority CUSTOMER_AUTHORITY = new SimpleGrantedAuthority("ROLE_CUSTOMER");
    
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

<<<<<<< HEAD
    // private static Logger logger = LoggerFactory.getLogger(SuccessHandler.class);
    
=======
>>>>>>> feature/third-week-features
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication arg2)
            throws IOException, ServletException {
        Authentication auth = iAuthenticationFacade.getAuthentiation();
<<<<<<< HEAD
        
		if (auth != null) {

			Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
			if (authorities.contains(CUSTOMER_AUTHORITY)) {
				//redirectStrategy.sendRedirect(request, response, "/customerdetails");
				redirectStrategy.sendRedirect(request,response,"/");
			} else {

				redirectStrategy.sendRedirect(request, response, "/admin");
			}

		}
		else {
			System.out.println("auth is null");
		}
        
    }//end of method
}//end of class






































	
/*UserDetails authUser=(UserDetails)auth.getPrincipal();
System.out.println(authUser.getUsername());
System.out.println(authUser.getPassword());*/





=======
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        if (authorities.contains(CUSTOMER_AUTHORITY)) {
            redirectStrategy.sendRedirect(request, response, "/customerdetails");
        } else {
            redirectStrategy.sendRedirect(request, response, "/admin");
        }
    }
}
>>>>>>> feature/third-week-features

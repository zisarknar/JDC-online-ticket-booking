
package com.solt.jdc.boot.handlers;

import com.solt.jdc.boot.utils.IAuthenticationFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class DeniedHandler implements AccessDeniedHandler {

    @Autowired
    private IAuthenticationFacade iAuthenticationFacade;

    private static Logger logger = LoggerFactory.getLogger(AccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        Authentication auth = iAuthenticationFacade.getAuthentiation();

        if (auth != null) {
            logger.info("User '" + auth.getName()
                    + "' attempted to access the protected URL: "
                    + request.getRequestURI());
        }

        response.sendRedirect(request.getContextPath() + "/403");
    }
}

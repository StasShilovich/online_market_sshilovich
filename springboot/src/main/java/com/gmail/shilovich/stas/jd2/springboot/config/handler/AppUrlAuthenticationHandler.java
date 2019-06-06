package com.gmail.shilovich.stas.jd2.springboot.config.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

public class AppUrlAuthenticationHandler implements AuthenticationSuccessHandler {

    private static Logger logger = LoggerFactory.getLogger(AppUrlAuthenticationHandler.class);

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            Authentication authentication
    ) throws IOException, ServletException {
        handle(httpServletRequest, httpServletResponse, authentication);
        clearAuthenticationAttributes(httpServletRequest);
        logger.info("Authentication successful");
    }


    private void handle(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final Authentication authentication)
            throws IOException {
        final String targetUrl = determineTargetUrl(authentication);
        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        logger.info("Session clear");
    }

    private String determineTargetUrl(Authentication authentication) {
        boolean isCustomerUser = false;
        boolean isAdministrator = false;
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            switch (grantedAuthority.getAuthority()) {
                case "ADMINISTRATOR":
                    isAdministrator = true;
                    break;
                case "CUSTOMER_USER":
                    isCustomerUser = true;
                    break;
            }
        }
        if (isCustomerUser) {
            logger.info("Role CUSTOMER_USER. Redirect on items");
            return "/private/items";
        } else if (isAdministrator) {
            logger.info("Role ADMINISTRATOR. Redirect on users");
            return "/private/users";
        } else {
            throw new IllegalStateException();
        }
    }
}


package com.security;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

/**
 * Used to create Authentications from Http Request parameters (such as username, password)
 * 
 * @author Anton Van Zyl
 * 
 */
public interface WildAuthenticationFactory {

	Authentication createAuthentication(HttpServletRequest request, SecurityContext securityContext);
}

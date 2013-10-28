package com.security;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

/**
 * Creates a Secure Login Token
 * 
 * @author Anton Van Zyl (CP311133)
 * 
 */
public class WildSecureAuthFactory implements WildAuthenticationFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see capitec.servlet.security.IBankAuthenticationFactory#createAuthentication(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Authentication createAuthentication(HttpServletRequest request, SecurityContext securityContext) {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (StringUtils.isEmpty(username) && StringUtils.isAlphanumeric(username)) {
			throw new BadCredentialsException("empty.login.details");
		}

		if (StringUtils.isEmpty(password) && StringUtils.isAlphanumeric(password)) {
			throw new BadCredentialsException("empty.login.details");
		}

		Authentication authToken = securityContext.getAuthentication();
		if (authToken != null && authToken.isAuthenticated()) {
			return authToken;
		}
		return new WildSecureLoginToken(username, password);
	}

}

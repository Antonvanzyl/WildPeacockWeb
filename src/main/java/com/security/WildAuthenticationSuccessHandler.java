package com.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

/**
 * AuthenticationSuccessHandler which can be configured with a default URL which users should be sent to upon successful authentication.
 * 
 * Extended to bind Identity to session
 * 
 * @author Anton Van Zyl
 * 
 */
public class WildAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	public WildAuthenticationSuccessHandler(String url) {
		super(url);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		super.onAuthenticationSuccess(request, response, authentication);
	}
}

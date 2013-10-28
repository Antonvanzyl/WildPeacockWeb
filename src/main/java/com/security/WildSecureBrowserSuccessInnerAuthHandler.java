package com.security;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author Anton Van Zyl
 * 
 */
public class WildSecureBrowserSuccessInnerAuthHandler implements AuthenticationSuccessHandler {

	private static final Logger log = LoggerFactory.getLogger(WildSecureBrowserSuccessInnerAuthHandler.class);

	private DefaultRedirectStrategy redirect = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		String menuOption = "/viewManage";

		log.debug("Redirecting to DefaultSavedRequest Url: " + menuOption);

		redirect.sendRedirect(request, response, menuOption);

	}

}

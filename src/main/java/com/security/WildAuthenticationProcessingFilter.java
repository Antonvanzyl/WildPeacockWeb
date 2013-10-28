package com.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;

/**
 * Intercepts authentication requests from the Controllers - i.e. when the user submits the login details to a controller, the controller must
 * forward/redirect to this handler so that authentication can be handled correctly by the spring security framework
 * 
 * @author Anton Van Zyl
 * 
 */
public class WildAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
	// ~ Static fields/initializers =====================================================================================

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private Map<String, WildOptionHandler> optionHandlers = new HashMap<String, WildOptionHandler>();
	private WildOptionHandler defaultHandler;

	// ~ Constructors ===================================================================================================

	public WildAuthenticationProcessingFilter() {
		super("/xxxxx");
	}

	public WildAuthenticationProcessingFilter(String defaultFilterProcessesUrl) {
		super(defaultFilterProcessesUrl);
	}

	// ~ Methods ========================================================================================================

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter#requiresAuthentication(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
		if (super.requiresAuthentication(request, response)) {
			String option = request.getParameter("option");
			return (getOptionHandler(option) != null);
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter#unsuccessfulAuthentication(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
	 */
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
			throws IOException, ServletException {

		WildOptionHandler handler = getOptionHandler(request.getParameter("option"));

		if (handler.isPrimaryAuthenticator()) {

			SecurityContextHolder.clearContext();

			getRememberMeServices().loginFail(request, response);
		}
		log.info("Authentication request failed: " + failed.toString());
		if (log.isDebugEnabled()) {
			log.debug("Delegating to authentication failure handler" + handler.getFailureHandler());
		}
		// we override parent as we do not want to destroy existing authentication
		handler.getFailureHandler().onAuthenticationFailure(request, response, failed);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter#successfulAuthentication(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain, org.springframework.security.core.Authentication)
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		if (log.isDebugEnabled()) {
			log.debug("Authentication success. Updating SecurityContextHolder to contain: " + authResult);
		}

		SecurityContextHolder.getContext().setAuthentication(authResult);

		getRememberMeServices().loginSuccess(request, response, authResult);

		// Fire event
		if (this.eventPublisher != null) {
			eventPublisher.publishEvent(new InteractiveAuthenticationSuccessEvent(authResult, this.getClass()));
		}

		WildOptionHandler handler = getOptionHandler(request.getParameter("option"));

		handler.getSuccessHandler().onAuthenticationSuccess(request, response, authResult);
	}

	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

		String option = request.getParameter("option");

		log.info("Security Controller [option=" + option + "]");

		WildOptionHandler handler = getOptionHandler(option);

		if (!request.getMethod().equalsIgnoreCase(handler.getMethod())) {
			log.debug("Request method incorrect [expected=" + handler.getMethod() + ";recieved=" + request.getMethod() + "]");
			throw new AuthenticationServiceException("Authentication method not supported: [expected=" + handler.getMethod() + ";recieved="
				+ request.getMethod() + "]");
		}

		SecurityContext securityContext = SecurityContextHolder.getContext();

		if (!handler.isPrimaryAuthenticator()) {

			// we expect this request to be authenticated

			if (securityContext == null) {
				throw new PreAuthenticatedCredentialsNotFoundException("Request has no security context");
			} else if (securityContext.getAuthentication() == null) {
				throw new PreAuthenticatedCredentialsNotFoundException("Request has no authentication");
			} else if (!securityContext.getAuthentication().isAuthenticated()) {
				throw new PreAuthenticatedCredentialsNotFoundException("Request is not authenticated");
			}
		}
		Authentication authentication = handler.getAuthenticationFactory().createAuthentication(request, securityContext);

		return this.getAuthenticationManager().authenticate(authentication);
	}

	public void setIBankOptionHandlers(List<WildOptionHandler> handlers) {

		for (WildOptionHandler iBankOptionHandler : handlers) {
			optionHandlers.put(iBankOptionHandler.getOption(), iBankOptionHandler);
		}
	}

	public void setDefaultHandler(WildOptionHandler defaultHandler) {
		this.defaultHandler = defaultHandler;
	}

	private WildOptionHandler getOptionHandler(String option) {

		if (StringUtils.isNotEmpty(option)) {
			return optionHandlers.get(option);
		} else if (defaultHandler != null) {
			return defaultHandler;
		}
		return null;
	}
}
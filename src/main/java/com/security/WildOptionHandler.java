package com.security;

import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/**
 * Container class for authentication handlers. Used by IBankAuthenticationProcessingFilter.
 * 
 * "option" is used to filter incoming requests (of form xxxxx?option="someLogin")
 * 
 * @author Anton Van Zyl
 * 
 */
public class WildOptionHandler {

	protected String option;

	protected String method = "POST";

	protected boolean primaryAuthenticator;

	protected WildAuthenticationFactory authenticationFactory;

	protected AuthenticationSuccessHandler successHandler;

	protected AuthenticationFailureHandler failureHandler;

	public WildOptionHandler() {
	}

	/**
	 * @return the option
	 */
	public String getOption() {
		return option;
	}

	/**
	 * @param option
	 *            the option to set
	 */
	public void setOption(String option) {
		this.option = option;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * @return the primaryAuthenticator
	 */
	public boolean isPrimaryAuthenticator() {
		return primaryAuthenticator;
	}

	/**
	 * @param primaryAuthenticator
	 *            the primaryAuthenticator to set
	 */
	public void setPrimaryAuthenticator(boolean primaryAuthenticator) {
		this.primaryAuthenticator = primaryAuthenticator;
	}

	/**
	 * @return the authenticationFactory
	 */
	public WildAuthenticationFactory getAuthenticationFactory() {
		return authenticationFactory;
	}

	/**
	 * @param authenticationFactory
	 *            the authenticationFactory to set
	 */
	public void setAuthenticationFactory(WildAuthenticationFactory authenticationFactory) {
		this.authenticationFactory = authenticationFactory;
	}

	/**
	 * @return the successHandler
	 */
	public AuthenticationSuccessHandler getSuccessHandler() {
		return successHandler;
	}

	/**
	 * @param successHandler
	 *            the successHandler to set
	 */
	public void setSuccessHandler(AuthenticationSuccessHandler handler) {
		this.successHandler = handler;
	}

	/**
	 * @param successHandlerUrl
	 *            the successHandler to set
	 */
	public void setSuccessHandlerUrl(String url) {
		this.successHandler = new WildAuthenticationSuccessHandler(url);

	}

	/**
	 * @return the failureHandler
	 */
	public AuthenticationFailureHandler getFailureHandler() {
		return failureHandler;
	}

	/**
	 * @param failureHandlerUrl
	 *            the failureHandler to set
	 */
	public void setFailureHandler(AuthenticationFailureHandler handler) {
		this.failureHandler = handler;
	}

	

	/**
	 * @param failureHandlerUrl
	 *            the failureHandler to set
	 */
	public void setFailureHandlerUrl(String url) {
		SimpleUrlAuthenticationFailureHandler handler = new SimpleUrlAuthenticationFailureHandler(url);
		handler.setUseForward(true);
		this.failureHandler = handler;
	}
}

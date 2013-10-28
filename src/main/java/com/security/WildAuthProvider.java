package com.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.manager.AuthorityManager;

/**
 * @author Gary Plante (cp312084)
 * 
 */
public class WildAuthProvider implements AuthenticationProvider, ApplicationContextAware {

	private ApplicationContext applicationContext;

	private AuthorityManager authorityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.authentication.AuthenticationProvider#authenticate(org.springframework.security.core.Authentication)
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		getServiceUserManager();

		if (!(authentication instanceof WildSecureLoginToken)) {
			throw new BadCredentialsException("login.incorrect.type");
		}

		WildSecureLoginToken loginToken = (WildSecureLoginToken) authentication;

		String username = loginToken.getUsername();
		String password = loginToken.getPassword();

		if (authorityManager.authenticateUser(username, password)) {
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			return (Authentication) new WildSecureLoginToken(loginToken, authorities);
		}
		throw new BadCredentialsException("login.incorrect");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.authentication.AuthenticationProvider#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return (Authentication.class.isAssignableFrom(authentication));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	private AuthorityManager getServiceUserManager() {

		if (authorityManager == null) {
			authorityManager = applicationContext.getBean("authorityManager", AuthorityManager.class);
		}
		return authorityManager;
	}

}

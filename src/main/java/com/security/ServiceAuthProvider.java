package com.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.servlet.ModelAndView;

import com.manager.AuthorityManager;

/**
 * @author Gary Plante (cp312084)
 * 
 */
public class ServiceAuthProvider implements AuthenticationProvider, ApplicationContextAware {

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

		String username = String.valueOf(authentication.getPrincipal());
		String password = String.valueOf(authentication.getCredentials());

		if (StringUtils.isEmpty(username) && StringUtils.isAlphanumeric(username)) {
			throw new BadCredentialsException("login.incorrect");
		}

		if (StringUtils.isEmpty(password) && StringUtils.isAlphanumeric(password)) {
			throw new BadCredentialsException("login.incorrect");
		}

		if (authorityManager.authenticateUser(username, password)) {
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			return (Authentication) new User(username,password,authorities);
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

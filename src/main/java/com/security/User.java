/**
 * File: User.java
 * Date: 04 Oct 2013
 * Author: Anton Van Zyl
 */
package com.security;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author Anton Van Zyl
 * 
 */
public class User extends AbstractAuthenticationToken {

	public User(Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
	}

	private static final long serialVersionUID = 7874352794059428316L;

	private Object credentials;
	private Object principal;

	public User(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal = principal;
		this.credentials = credentials;
		super.setAuthenticated(true);
	}

	@Override
	public Object getCredentials() {
		return credentials;
	}

	@Override
	public Object getPrincipal() {
		return principal;
	}

}

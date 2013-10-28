package com.security;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

/**
 * Specialised token
 * 
 * @author Anton Van Zyl
 * 
 */
public class WildSecureLoginToken extends AbstractAuthenticationToken {

	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	// ~ Instance fields ================================================================================================
	String username;
	String password;

	// ~ Constructors ===================================================================================================

	/**
	 * This constructor can be safely used by any code that wishes to create a <code>IBankSecureBrowserLoginToken</code>, as the
	 * {@link #isAuthenticated()} will return <code>false</code>.
	 * 
	 */
	public WildSecureLoginToken(String username, String password) {
		super(null);
		this.username = username;
		this.password = password;
		setAuthenticated(false);
	}

	/**
	 * This constructor should only be used by <code>AuthenticationManager</code> or <code>AuthenticationProvider</code> implementations that are
	 * satisfied with producing a trusted (i.e. {@link #isAuthenticated()} = <code>true</code>) authentication token.
	 * 
	 * @param principal
	 * @param credentials
	 * @param authorities
	 */
	public WildSecureLoginToken(WildSecureLoginToken browserLoginToken, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.username = browserLoginToken.getUsername();
		this.password = browserLoginToken.getPassword();
		setDetails(browserLoginToken.getDetails());
		super.setAuthenticated(true);
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	// ~ Methods ========================================================================================================

	@Override
	public void eraseCredentials() {
		super.eraseCredentials();
		this.username = null;
		this.password = null;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		if (isAuthenticated) {
			throw new IllegalArgumentException(
					"Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
		}
		super.setAuthenticated(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.Authentication#getCredentials()
	 */
	@Override
	public Object getCredentials() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.Authentication#getPrincipal()
	 */
	@Override
	public Object getPrincipal() {
		return null;
	}

}
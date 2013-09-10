/**
 * File: AuthorityManagerImpl.java
 * Date: 09 Sep 2013
 * Author: Anton Van Zyl
 */
package com.manager;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Anton Van Zyl
 * 
 */
@Component
@Transactional
public class AuthorityManagerImpl implements AuthorityManager {

	@Override
	public void authenticateUser(String username, String password) throws AccessDeniedException {

	}

}

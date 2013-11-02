package com.manager;

import org.springframework.security.access.AccessDeniedException;

/**
 * @author Anton Van Zyl
 * 
 */
public interface AuthorityManager {

	boolean authenticateUser(String username, String password) throws AccessDeniedException;
}

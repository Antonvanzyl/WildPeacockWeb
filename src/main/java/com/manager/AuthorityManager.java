package com.manager;

import org.springframework.security.access.AccessDeniedException;

public interface AuthorityManager {

	boolean authenticateUser(String username, String password) throws AccessDeniedException;
}

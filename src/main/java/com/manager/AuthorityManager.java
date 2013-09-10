package com.manager;

import org.springframework.security.access.AccessDeniedException;

public interface AuthorityManager {

	void authenticateUser(String username, String password) throws AccessDeniedException;
}

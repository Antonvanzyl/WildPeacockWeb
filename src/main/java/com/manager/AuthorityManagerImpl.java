/**
 * File: AuthorityManagerImpl.java
 * Date: 09 Sep 2013
 * Author: Anton Van Zyl
 */
package com.manager;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dao.db.AuthorityDao;
import com.entity.db.Authority;

/**
 * @author Anton Van Zyl
 * 
 */
@Component
@Transactional
public class AuthorityManagerImpl implements AuthorityManager {

	@Autowired
	private AuthorityDao authorityDao;

	@Override
	public void authenticateUser(String username, String password) throws AccessDeniedException {

		Authority authority = authorityDao.getRecordByUsername(username);
		if (authority == null) {
			throw new AccessDeniedException("Incorrect User Name");
		}

		if (StringUtils.equals(password, authority.getPassword())) {
			throw new AccessDeniedException("Incorrect password");
		}
	}

}

/**
 * File: User.java
 * Date: 04 Oct 2013
 * Author: Anton Van Zyl
 */
package com.servlet.model;

/**
 * @author Anton Van Zyl
 * 
 */
public class User {

	private String username;
	private boolean loggedIn;

	public User() {
	}

	public User(String username, boolean loggedIn) {
		this.username = username;
		this.loggedIn = loggedIn;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

}

/**
 * 
 */
package com.servlet.model;

import java.util.HashMap;

import com.types.UserType;

/**
 * @author Anton Van Zyl (CP311133)
 * 
 */
public class UserModel {

	private String username;
	private UserType userType;
	private HashMap<String, String> availableLinks;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public HashMap<String, String> getAvailableLinks() {
		return availableLinks;
	}

	public void setAvailableLinks(HashMap<String, String> availableLinks) {
		this.availableLinks = availableLinks;
	}

	public String getLinks() {

		StringBuilder builder = new StringBuilder();
		if (availableLinks != null) {
			for (String text : availableLinks.keySet()) {
				builder.append("<li ><a href=\"");

				builder.append(availableLinks.get(text));
				builder.append("\">");
				builder.append(text);
				builder.append("</a></li>");
			}
		}
		return builder.toString();

	}

}

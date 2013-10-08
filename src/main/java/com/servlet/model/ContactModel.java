/**
 * File: Contact.java
 * Date: 15 Sep 2013
 * Author: Anton Van Zyl
 */
package com.servlet.model;

/**
 * @author Anton Van Zyl
 * 
 */
public class ContactModel {

	private String name;
	private String number;
	private String question;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Contact [name=");
		builder.append(name);
		builder.append(", number=");
		builder.append(number);
		builder.append(", question=");
		builder.append(question);
		builder.append("]");
		return builder.toString();
	}

	public void clear() {
		this.name = null;
		this.number = null;
		this.question = null;
	}

}

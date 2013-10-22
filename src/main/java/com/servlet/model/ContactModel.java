/**
 * File: Contact.java
 * Date: 15 Sep 2013
 * Author: Anton Van Zyl
 */
package com.servlet.model;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;

/**
 * @author Anton Van Zyl
 * 
 */
public class ContactModel {

	private String name;
	private String number;

	private String email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void validate(BindingResult bindingResult) {
		if (StringUtils.isEmpty(this.name)) {
			bindingResult.rejectValue("name", "*Required", "*Required");
		}

		if (StringUtils.isNotEmpty(this.number) && !StringUtils.isNumeric(this.number)) {
			bindingResult.rejectValue("number", "*Incorrect", "*Incorrect, must start with zeor followed by 9 digits");
		}

		if (StringUtils.isEmpty(this.question)) {
			bindingResult.rejectValue("question", "*Required", "*Required");
		}

		try {
			InternetAddress address = new InternetAddress(email);
			address.validate();
		} catch (AddressException e) {
			bindingResult.rejectValue("email", "*Incorrect", "*Incorrect");
		}
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

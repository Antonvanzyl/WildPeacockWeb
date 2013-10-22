/**
 * File: UserAuthenticationController.java
 * Date: 04 Jun 2013
 * Author: Anton Van Zyl
 */
package com.servlet;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.servlet.model.ContactModel;

/**
 * @author Anton Van Zyl
 * 
 */
@Controller
public class InformationController {

	@Value("${mail.to.address}")
	private String toAddress;

	@Value("${mail.host}")
	private String host;

	@ModelAttribute("contact")
	public ContactModel getContact() {
		return new ContactModel();
	}

	@RequestMapping(value = "/contact", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView contact() {
		ModelAndView modelAndView = new ModelAndView("contact");

		return modelAndView;
	}

	@RequestMapping(value = "/message", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView sendMessage(@ModelAttribute("contact") ContactModel contact, BindingResult bindingResult) {

		contact.validate(bindingResult);
		if (bindingResult.hasErrors()) {
			return new ModelAndView("contact");
		}

		StringBuilder subjectBuilder = new StringBuilder();
		subjectBuilder.append("Website Question from");
		subjectBuilder.append(contact.getName());

		StringBuilder messageBuilder = new StringBuilder();
		messageBuilder.append("A question from ");
		messageBuilder.append(contact.getName());
		messageBuilder.append("\nContact Details:\n");
		messageBuilder.append("Email : ");
		messageBuilder.append(contact.getEmail());

		if (StringUtils.isNotBlank(contact.getNumber())) {
			messageBuilder.append("Mobile : ");
			messageBuilder.append(contact.getNumber());
		}

		messageBuilder.append("\nQuestion Message:\n\n");
		messageBuilder.append(contact.getQuestion());

		boolean success = sendEmail(contact.getEmail(), subjectBuilder.toString(), messageBuilder.toString());

		ModelAndView modelAndView = new ModelAndView("forward:/contact");
		modelAndView.addObject("sent", success);
		if (success) {
			contact.clear();
		}
		return modelAndView;
	}

	private boolean sendEmail(String fromAddress, String subject, String messageText) {

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.user", "myuser");
		properties.setProperty("mail.password", "mypwd");

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromAddress));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
			message.setSubject(subject);
			message.setText(messageText);

			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
			return true;
		} catch (MessagingException mex) {
			mex.printStackTrace();
			return false;
		}
	}
}

/**
 * File: MessagingManagerImpl.java
 * Date: 27 Oct 2013
 * Author: Anton Van Zyl
 */
package com.manager;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Anton Van Zyl
 * 
 */
@Component
public class MessagingManagerImpl implements MessagingManager {

	@Value("${mail.to.address}")
	private String toAddress;

	@Value("${mail.host}")
	private String host;

	@Value("${mail.host.user}")
	private String user;
	
	@Value("${mail.host.pass}")
	private String pass;

	@Override
	public boolean sendEmail(String fromAddress, String subject, String messageText) {

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.user", user);
		properties.setProperty("mail.password", pass);

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

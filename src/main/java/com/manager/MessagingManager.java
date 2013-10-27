package com.manager;

public interface MessagingManager {

	public abstract boolean sendEmail(String fromAddress, String subject, String messageText);

}
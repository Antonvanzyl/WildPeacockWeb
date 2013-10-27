/**
 * File: UserAuthenticationController.java
 * Date: 04 Jun 2013
 * Author: Anton Van Zyl
 */
package com.servlet;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.manager.MessagingManager;
import com.servlet.model.ContactModel;

/**
 * @author Anton Van Zyl
 * 
 */
@Controller
public class InformationController {

	@Autowired
	private MessagingManager messagingManager;

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

		boolean success = messagingManager.sendEmail(contact.getEmail(), subjectBuilder.toString(), messageBuilder.toString());

		ModelAndView modelAndView = new ModelAndView("forward:/contact");
		modelAndView.addObject("sent", success);
		if (success) {
			contact.clear();
		}
		return modelAndView;
	}

}

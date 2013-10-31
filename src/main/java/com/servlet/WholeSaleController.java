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
public class WholeSaleController  {
	
	@Autowired
	private MessagingManager messagingManager;

	@RequestMapping(value = "/wholesale_home", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView WholesaleHome() {
		ModelAndView modelAndView = new ModelAndView("wholesale/home");

		return modelAndView;
	}

	@RequestMapping(value = "/wholesale_products", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView WholesaleProducts() {
		ModelAndView modelAndView = new ModelAndView("wholesale/products");

		return modelAndView;
	}
	
	@RequestMapping(value = "/wholesale_contact", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView WholesaleContact() {
		ModelAndView modelAndView = new ModelAndView("wholesale/contact");
		modelAndView.addObject("contact",new ContactModel());
		return modelAndView;
	}
	
	@RequestMapping(value = "/wholesale_message", method = { RequestMethod.GET, RequestMethod.POST })
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

		ModelAndView modelAndView = new ModelAndView("forward:/wholeSale/contact");
		modelAndView.addObject("sent", success);
		if (success) {
			contact.clear();
		}
		return modelAndView;
	}
	
}

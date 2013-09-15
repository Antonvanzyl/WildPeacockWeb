/**
 * File: UserAuthenticationController.java
 * Date: 04 Jun 2013
 * Author: Anton Van Zyl
 */
package com.servlet;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.servlet.model.Contact;

/**
 * @author Anton Van Zyl
 * 
 */
@Controller
public class InformationController extends BaseController {

	@ModelAttribute("contact")
	public Contact getContact() {
		return new Contact();
	}

	@RequestMapping(value = "/contact", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView contact() {
		ModelAndView modelAndView = new ModelAndView("contact");

		return modelAndView;
	}

	@RequestMapping(value = "/message", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView sendMessage(@ModelAttribute("contact") Contact contact, BindingResult bindingResult) {

		if (StringUtils.isEmpty(contact.getName())) {
			bindingResult.rejectValue("name", "*Required", "*Required");
		}
		if (StringUtils.isEmpty(contact.getNumber())) {
			bindingResult.rejectValue("number", "*Required", "*Required");
		} else if (!StringUtils.isNumeric(contact.getNumber())) {
			bindingResult.rejectValue("number", "*Incorrect", "*Incorrect");
		}
		if (StringUtils.isEmpty(contact.getQuestion())) {
			bindingResult.rejectValue("question", "*Required", "*Required");
		}
		if (bindingResult.hasErrors()) {
			return new ModelAndView("contact");
		}

		ModelAndView modelAndView = new ModelAndView("forward:/contact");
		modelAndView.addObject("sent", true);
		System.out.println(contact);
		contact.clear();
		return modelAndView;
	}

}

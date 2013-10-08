/**
 * File: BaseController.java
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.manager.AuthorityManager;
import com.servlet.model.User;

/**
 * @author Anton Van Zyl
 * 
 */
@Controller
@SessionAttributes("User")
@RequestMapping(value = "/wildAdmin", method = { RequestMethod.GET, RequestMethod.POST })
public class AdminController {

	@Autowired
	private AuthorityManager authorityManager;

	@ModelAttribute("User")
	public User createUser() {
		return new User();
	}

	@RequestMapping
	public ModelAndView admin(@ModelAttribute("User") User user) {

		if (!user.isLoggedIn()) {
			return new ModelAndView("admin/login");
		}

		ModelAndView modelAndView = new ModelAndView("contact");

		return modelAndView;
	}

	@RequestMapping(value = "/submitLogin", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView submitLogin(@ModelAttribute("User") User user, BindingResult bindingResult) {

		if (StringUtils.isEmpty(user.getUsername())) {
			bindingResult.rejectValue("username", "*Required", "*Required");
		}

		if (StringUtils.isEmpty(user.getPassword())) {
			bindingResult.rejectValue("password", "*Required", "*Required");
		}

		if (bindingResult.hasErrors()) {
			user.clear();
			return new ModelAndView("admin/login");
		}

		if (!authorityManager.authenticateUser(user.getUsername(), user.getPassword())) {
			bindingResult.rejectValue("username", "*Required", "*Required");
			bindingResult.rejectValue("password", "*Required", "*Required");
			user.clear();
			return new ModelAndView("admin/login");
		}

		user.login();

		ModelAndView modelAndView = new ModelAndView("contact");

		return modelAndView;
	}

}

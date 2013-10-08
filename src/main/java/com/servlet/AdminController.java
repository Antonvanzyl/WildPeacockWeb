/**
 * File: BaseController.java
 * Date: 04 Jun 2013
 * Author: Anton Van Zyl
 */
package com.servlet;

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
public class AdminController extends BaseController {

	@Autowired
	private AuthorityManager authorityManager;
	
	@ModelAttribute("User")
	public User createUser() {
		return new User();
	}

	@RequestMapping(value = "/admin", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView admin(@ModelAttribute("User") User user) {

		if (!user.isLoggedIn()) {
			return new ModelAndView("redirect:adminLogin");
		}

		ModelAndView modelAndView = new ModelAndView("contact");

		return modelAndView;
	}

	@RequestMapping(value = "/adminLogin", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView adminLogin(@ModelAttribute("User") User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView("contact");

		return modelAndView;
	}

}

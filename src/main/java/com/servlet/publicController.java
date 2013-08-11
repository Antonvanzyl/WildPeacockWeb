/**
 * File: UserAuthenticationController.java
 * Date: 04 Jun 2013
 * Author: Anton Van Zyl
 */
package com.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Anton Van Zyl
 * 
 */
@Controller
public class publicController extends BaseController {

	@RequestMapping(value = "/home", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView("main");

		return modelAndView;
	}

	@RequestMapping(value = "/about", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView about() {
		ModelAndView modelAndView = new ModelAndView("main");

		return modelAndView;
	}

	@RequestMapping(value = "/contact", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView contact() {
		ModelAndView modelAndView = new ModelAndView("main");

		return modelAndView;
	}

}

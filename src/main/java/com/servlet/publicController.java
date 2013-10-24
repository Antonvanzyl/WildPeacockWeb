/**
 * File: UserAuthenticationController.java
 * Date: 04 Jun 2013
 * Author: Anton Van Zyl
 */
package com.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Anton Van Zyl
 * 
 */
@Controller
public class publicController {

	@RequestMapping(value = "/home", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView home() {

		ModelAndView modelAndView = new ModelAndView("index");

		return modelAndView;
	}

	@RequestMapping(value = "/retail_home", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView RetailHome() {

		ModelAndView modelAndView = new ModelAndView("retail_home");

		return modelAndView;
	}

	@RequestMapping(value = "/jspExceptionHandler", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView jspException(@RequestParam("errorType")String error) {
		ModelAndView modelAndView = new ModelAndView("exception");
		modelAndView.addObject("error", error);
		return modelAndView;
	}

}

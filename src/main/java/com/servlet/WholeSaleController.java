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
@RequestMapping("/wholesale")
public class WholeSaleController  {

	@RequestMapping(value = "/home", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView WholesaleHome() {
		ModelAndView modelAndView = new ModelAndView("wholesale/home");

		return modelAndView;
	}

	@RequestMapping(value = "/products", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView WholesaleProducts() {
		ModelAndView modelAndView = new ModelAndView("wholesale/products");

		return modelAndView;
	}
	
	@RequestMapping(value = "/contact", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView WholesaleContact() {
		ModelAndView modelAndView = new ModelAndView("wholesale/contact");

		return modelAndView;
	}
	
}

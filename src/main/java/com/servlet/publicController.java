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
		ModelAndView modelAndView = new ModelAndView("index");

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
	
	@RequestMapping(value = "/retail_contact", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView RetailContact() {
		ModelAndView modelAndView = new ModelAndView("retail_contact");

		return modelAndView;
	}
	
	@RequestMapping(value = "/retail_products", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView RetailProducts() {
		ModelAndView modelAndView = new ModelAndView("retail_products");

		return modelAndView;
	}
	
	@RequestMapping(value = "/retail_press", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView RetailPress() {
		ModelAndView modelAndView = new ModelAndView("retail_press");

		return modelAndView;
	}
	
	@RequestMapping(value = "/retail_home", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView RetailHome() {
		ModelAndView modelAndView = new ModelAndView("retail_home");

		return modelAndView;
	}
	
	@RequestMapping(value = "/wholesale_home", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView WholesaleHome() {
		ModelAndView modelAndView = new ModelAndView("wholesale_home");

		return modelAndView;
	}

	@RequestMapping(value = "/wholesale_products", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView WholesaleProducts() {
		ModelAndView modelAndView = new ModelAndView("wholesale_products");

		return modelAndView;
	}
	
	@RequestMapping(value = "/wholesale_contact", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView WholesaleContact() {
		ModelAndView modelAndView = new ModelAndView("wholesale_contact");

		return modelAndView;
	}
	
}

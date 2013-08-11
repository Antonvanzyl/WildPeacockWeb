/**
 * File: UserAuthenticationController.java
 * Date: 04 Jun 2013
 * Author: Anton Van Zyl
 */
package com.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Anton Van Zyl
 * 
 */
@Controller
@SessionAttributes("UserModel")
public class serviceAuthenticationController extends BaseController {

	@RequestMapping(value = "/sabnzbd", method = { RequestMethod.GET })
	public ModelAndView login() {

		ModelAndView modelAndView = new ModelAndView("redirect:www.google.com");

		return modelAndView;
	}

}

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
public class NewsController extends BaseController {

	@RequestMapping(value = "/news", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView news() {
		ModelAndView modelAndView = new ModelAndView("main");

		return modelAndView;
	}

	

}

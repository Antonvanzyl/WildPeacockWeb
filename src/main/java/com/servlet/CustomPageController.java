/**
 * File: UserAuthenticationController.java
 * Date: 04 Jun 2013
 * Author: Anton Van Zyl
 */
package com.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.manager.CustomPageManager;
import com.servlet.model.CustomPageModel;
import com.types.SiteSpaceType;

/**
 * @author Anton Van Zyl
 * 
 */
@Controller
public class CustomPageController {

	@Autowired
	private CustomPageManager customPageManager;

	@RequestMapping(value = "/viewCustomPage", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView home(@RequestParam("id") int id,@RequestParam("siteSpaceType") SiteSpaceType siteSpaceType) {

		ModelAndView modelAndView = new ModelAndView("customPage");
		CustomPageModel customPage = customPageManager.getCustomPage(id);
		modelAndView.addObject("customPage", customPage);
		modelAndView.addObject("siteSpaceType", siteSpaceType);
		return modelAndView;
	}

}

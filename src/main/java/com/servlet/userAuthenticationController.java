/**
 * File: UserAuthenticationController.java
 * Date: 04 Jun 2013
 * Author: Anton Van Zyl
 */
package com.servlet;

import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.servlet.model.LoginForm;
import com.servlet.model.UserModel;
import com.types.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Anton Van Zyl
 * 
 */
@Controller
@SessionAttributes("UserModel")
public class userAuthenticationController extends BaseController implements
		InitializingBean {

	private static final Logger log = LoggerFactory
			.getLogger(userAuthenticationController.class);

	final private HashMap<String, String> users = new HashMap<String, String>();

	@RequestMapping(value = "/login", method = { RequestMethod.POST })
	public ModelAndView login(@ModelAttribute("loginForm") LoginForm loginForm,
			Model model) {

		UserModel userModel = validateUser(loginForm.getUsername(),
				loginForm.getPassword());

		model.addAttribute("UserModel", userModel);

		ModelAndView modelAndView = new ModelAndView("redirect:home");

		return modelAndView;
	}

	private UserModel validateUser(String username, String password)
			throws AccessDeniedException {
		String value = users.get(username);

		if (value == null || !StringUtils.equals(password, value)) {
			throw new AccessDeniedException(
					"You do not have access to this site");
		}

		UserModel userModel = new UserModel();
		userModel.setUsername(username);
		HashMap<String, String> availableLinks = new HashMap<String, String>();

		if (StringUtils.equals(username, "admin")) {
			userModel.setUserType(UserType.ADMIN);
			availableLinks.put("Services", "#");
			availableLinks.put("sabnzbd", "sabnzbd");
		}
		
		if (StringUtils.equals(username, "wildpeacock")) {
			userModel.setUserType(UserType.CLIENT);
			availableLinks.put("Wild Peacock Website", "#");
		}

		userModel.setAvailableLinks(availableLinks);
		return userModel;
	}

	@RequestMapping(value = "/logout", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView logout(HttpServletRequest request,
			HttpServletResponse response, final SessionStatus sessionStatus) {

		sessionStatus.setComplete();

		// clear the session
		HttpSession session = request.getSession(false);
		if (session != null) {
			try {
				log.info("Invalidating session: " + session.getId());
				// the SessionDestroyedListener will do the logout
				session.invalidate();
			} catch (Exception e) {
				log.debug("Session already invalidated: " + session.getId());
			}
		}

		// clear the cookie(s)
		Cookie cookie = new Cookie("JSESSIONID", null);
		cookie.setPath(request.getContextPath());
		cookie.setMaxAge(0);
		response.addCookie(cookie);

		ModelAndView modelAndView = new ModelAndView("redirect:home");

		return modelAndView;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		users.put("admin", "intel2");
		users.put("wildpeacock", "wildpeacock");
	}

}

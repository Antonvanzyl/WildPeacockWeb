/**
 * File: UserAuthenticationController.java
 * Date: 04 Jun 2013
 * Author: Anton Van Zyl
 */
package com.servlet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.manager.PublishingManager;
import com.servlet.model.PublishRecordModel;
import com.types.PublishingSectionType;

/**
 * @author Anton Van Zyl
 * 
 */
@Controller
@RequestMapping("/news")
public class NewsController {

	@Autowired
	private PublishingManager publishingManager;

	private final int numberOfRecordsPerPage = 20;

	@RequestMapping( method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView news() {
		ModelAndView modelAndView = new ModelAndView("News/summary");
		List<PublishRecordModel> news = publishingManager.getPublishingRecords(PublishingSectionType.NEWS, 0, numberOfRecordsPerPage);
		modelAndView.addObject("news", news);
		modelAndView.addObject("currentPage", 1);
		modelAndView.addObject("totalRecords", publishingManager.getNewsSize());
		return modelAndView;
	}

	@RequestMapping(value = "/page", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView page(@RequestParam("page") int page) {
		int currentPage = page * numberOfRecordsPerPage;
		ModelAndView modelAndView = new ModelAndView("News/summary");
		List<PublishRecordModel> news = publishingManager.getPublishingRecords(PublishingSectionType.NEWS, currentPage,
				(currentPage + numberOfRecordsPerPage));
		

		modelAndView.addObject("currentPage", page);
		modelAndView.addObject("news", news);
		modelAndView.addObject("totalRecords", publishingManager.getNewsSize());
		return modelAndView;
	}
	
	@RequestMapping(value = "/detail", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView detail(@RequestParam("id") int id) {
		ModelAndView modelAndView = new ModelAndView("News/details");
		modelAndView.addObject("details",publishingManager.getPublishRecordAndAddReadCount(id));
		return modelAndView;
	}

}

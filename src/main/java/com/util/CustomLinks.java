/**
 * File: RetailCustomLinks.java
 * Date: 02 Nov 2013
 * Author: Anton Van Zyl
 */
package com.util;

import java.util.ArrayList;
import java.util.List;

import com.servlet.model.CustomPageModel;
import com.types.SiteSpaceType;

/**
 * @author Anton Van Zyl
 * 
 */
public class CustomLinks {

	final static List<Link> retailLinks = new ArrayList<Link>();
	final static List<Link> wholeSaleLinks = new ArrayList<Link>();

	public List<Link> getWholeSaleLinks() {
		return wholeSaleLinks;
	}

	public List<Link> getRetailLinks() {
		return retailLinks;
	}

	public static void loadRetailLinks(List<CustomPageModel> customPageModels) {
		retailLinks.clear();
		wholeSaleLinks.clear();

		for (CustomPageModel customPageModel : customPageModels) {

			Link link = new Link();
			link.setUrl("viewCustomPage?id=" + customPageModel.getId() + "&siteSpaceType=" + customPageModel.getSiteSpaceType());
			link.setLinkName(customPageModel.getPageName());

			if (customPageModel.getSiteSpaceType() == SiteSpaceType.RETAIL) {
				retailLinks.add(link);
			} else {
				wholeSaleLinks.add(link);
			}
		}

	}

}

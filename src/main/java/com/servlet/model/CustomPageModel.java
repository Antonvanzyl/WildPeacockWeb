/**
 * File: CustomPageModel.java
 * Date: 02 Nov 2013
 * Author: Anton Van Zyl
 */
package com.servlet.model;

import com.types.SiteSpaceType;

/**
 * @author Anton Van Zyl
 * 
 */
public class CustomPageModel {

	private int id;
	private String pageName;
	private String title;
	private String description;
	private SiteSpaceType siteSpaceType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SiteSpaceType getSiteSpaceType() {
		return siteSpaceType;
	}

	public void setSiteSpaceType(SiteSpaceType siteSpaceType) {
		this.siteSpaceType = siteSpaceType;
	}
}

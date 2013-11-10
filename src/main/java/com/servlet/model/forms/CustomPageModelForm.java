/**
 * File: CustomPageModelForm.java
 * Date: 07 Nov 2013
 * Author: Anton Van Zyl
 */
package com.servlet.model.forms;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;

import com.types.SiteSpaceType;

/**
 * @author Anton Van Zyl
 * 
 */
public class CustomPageModelForm {
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

	public void validate(BindingResult bindingResult) {

		if (StringUtils.isAlphanumericSpace(this.title)) {
			bindingResult.rejectValue("title", "", "*Must be Alpha Numeric ");
		} else if (StringUtils.length(this.title) > 64) {
			bindingResult.rejectValue("title", "", "*Too Long (64 char max)");
		}

		if (StringUtils.isAlphanumericSpace(this.pageName)) {
			bindingResult.rejectValue("subTitle", "", "*Must be Alpha Numeric ");
		} else if (StringUtils.length(this.pageName) > 64) {
			bindingResult.rejectValue("subTitle", "", "*Too Long (64 char max)");
		}

		if (StringUtils.isEmpty(this.getDescription())) {
			bindingResult.rejectValue("description", "", "*Required");
		} else if (StringUtils.length(this.getDescription()) > 8000) {
			bindingResult.rejectValue("description", "", "*Too Long (8000 char max)");
		}

	}
}

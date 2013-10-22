/**
 * File: CategoryModelForm.java
 * Date: 10 Oct 2013
 * Author: Anton Van Zyl
 */
package com.servlet.model.forms;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;

/**
 * @author Anton Van Zyl
 * 
 */
public class CategoryModelForm {

	private int parentId;
	private int id;
	private String name;
	private String description;

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void validate(BindingResult bindingResult) {
		if (StringUtils.isEmpty(this.name)) {
			bindingResult.rejectValue("name", "", "*Required");
		} else if (!StringUtils.isAlpha(this.name)) {
			bindingResult.rejectValue("name", "", "*Must Be Aplha");
		} else if (StringUtils.length(this.name) > 128) {
			bindingResult.rejectValue("name", "", "*Max lenght 128 char");
		}

		if (StringUtils.isEmpty(this.description)) {
			bindingResult.rejectValue("description", "", "*Required");
		} else if (!StringUtils.isAlpha(this.description)) {
			bindingResult.rejectValue("description", "", "*Must Be Aplha");
		} else if (StringUtils.length(this.description) > 128) {
			bindingResult.rejectValue("description", "", "*Max lenght 128 char");
		}
	}

}

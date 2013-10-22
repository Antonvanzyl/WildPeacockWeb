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
public class TagModelForm {

	private int id;
	private int parentId;
	private String name;

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
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void validate(BindingResult bindingResult) {

		if (StringUtils.isEmpty(name)) {
			bindingResult.rejectValue("name", "", "*Required");
		} else if (!StringUtils.isAlpha(this.name)) {
			bindingResult.rejectValue("name", "", "*Must Be Aplha");
		} else if (StringUtils.length(this.name) > 64) {
			bindingResult.rejectValue("name", "", "*Incorrect Length (Max 64 char)");
		}
	}

}

/**
 * File: CategoryModelForm.java
 * Date: 10 Oct 2013
 * Author: Anton Van Zyl
 */
package com.servlet.model.forms;

/**
 * @author Anton Van Zyl
 * 
 */
public class CategoryModelForm {

	private int parentId;
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

}
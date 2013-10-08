/**
 * File: ProductCategory.java
 * Date: 09 Sep 2013
 * Author: Anton Van Zyl
 */
package com.servlet.model;

/**
 * @author Anton Van Zyl
 * 
 */
public class ProductCategoryModel {

	private int categoryId;
	private String categoryName;

	public int getCategoryId() {
		return categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}

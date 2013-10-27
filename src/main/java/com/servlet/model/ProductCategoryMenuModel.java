/**
 * File: ProductCategory.java
 * Date: 09 Sep 2013
 * Author: Anton Van Zyl
 */
package com.servlet.model;

import java.util.List;

/**
 * @author Anton Van Zyl
 * 
 */
public class ProductCategoryMenuModel {

	private int categoryId;
	private String categoryName;
	List<ProductCategoryMenuModel> subCategories;

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<ProductCategoryMenuModel> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(List<ProductCategoryMenuModel> subCategories) {
		this.subCategories = subCategories;
	}

}

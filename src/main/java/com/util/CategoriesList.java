package com.util;

import java.util.List;

import com.servlet.model.ProductCategoryMenuModel;

public class CategoriesList {

	private static List<ProductCategoryMenuModel> categoryMenuModels;
	
	public CategoriesList() {
	}

	public CategoriesList(List<ProductCategoryMenuModel> newCategoryMenuModels) {
		categoryMenuModels = newCategoryMenuModels;
	}

	public List<ProductCategoryMenuModel> getCategoriesListItems() {
		return categoryMenuModels;
	}

	public static void setCategoryMenuModels(List<ProductCategoryMenuModel> newCategoryMenuModels) {
		categoryMenuModels = newCategoryMenuModels;
	}

}

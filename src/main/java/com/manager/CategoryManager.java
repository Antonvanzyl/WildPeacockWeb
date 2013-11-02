package com.manager;

import java.util.List;


import com.entity.db.Category;
import com.servlet.model.ProductCategoryMenuModel;
import com.servlet.model.ProductCategoryModel;
import com.servlet.model.forms.CategoryModelForm;


/**
 * @author Anton Van Zyl
 * 
 */
public interface CategoryManager {
	List<ProductCategoryModel> getAllMainCategories();

	public List<ProductCategoryModel> getAllCategories();

	public List<ProductCategoryMenuModel> getProductMenuCategories();

	public List<Integer> getAllCategoriesForId(int categoryId);

	public CategoryModelForm getCategory(int categoryId) throws Exception;

	public void addMainCategory(String categoryName, String description);

	public void addSubCategory(int parentCategory, String categoryName, String description) throws Exception;

	public void updateMainCategory(int id, String name, String description) throws Exception;

	public void updateSubCategory(int id, int parentCategory, String categoryName, String description) throws Exception;

	public void refreshCategories();

	Category findById(int categoryId);

}

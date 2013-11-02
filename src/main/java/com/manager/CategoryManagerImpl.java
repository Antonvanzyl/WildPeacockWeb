/**
 * File: CategoryManagerImpl.java
 * Date: 02 Nov 2013
 * Author: Anton Van Zyl
 */
package com.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dao.db.CategoryDao;
import com.entity.db.Category;
import com.servlet.model.ProductCategoryMenuModel;
import com.servlet.model.ProductCategoryModel;
import com.servlet.model.forms.CategoryModelForm;
import com.util.CategoriesList;

/**
 * @author Anton Van Zyl
 * 
 */
@Transactional
@Component
public class CategoryManagerImpl implements CategoryManager {

	private final Object lock = new Object();

	@Autowired
	private CategoryDao categoryDao;
	private final List<ProductCategoryMenuModel> memoryCategories = new ArrayList<ProductCategoryMenuModel>();

	@Override
	public List<ProductCategoryModel> getAllMainCategories() {
		final List<ProductCategoryModel> returnList = new ArrayList<ProductCategoryModel>();
		final List<Category> dbCategories = categoryDao.getAllMainCategories();
		for (Category category : dbCategories) {

			ProductCategoryModel productCategory = new ProductCategoryModel();
			productCategory.setCategoryId(category.getId());
			productCategory.setCategoryName(category.getName());
			returnList.add(productCategory);

		}
		return returnList;
	}

	@Override
	public List<ProductCategoryModel> getAllCategories() {
		final List<ProductCategoryModel> returnList = new ArrayList<ProductCategoryModel>();
		final List<Category> dbCategories = categoryDao.getAllCategories();
		for (Category category : dbCategories) {

			ProductCategoryModel productCategory = new ProductCategoryModel();
			productCategory.setCategoryId(category.getId());
			productCategory.setCategoryName(category.getName());
			productCategory.setCategoryDescription(category.getDescription());
			returnList.add(productCategory);

		}
		return returnList;
	}

	@Override
	public void addMainCategory(String categoryName, String description) {

		Category category = new Category();
		category.setCreated(new Date());
		category.setName(categoryName);
		category.setDescription(description);

		categoryDao.merge(category);

	}

	@Override
	public List<Integer> getAllCategoriesForId(int categoryId) {

		List<Integer> ids = new ArrayList<Integer>();

		final List<ProductCategoryMenuModel> tempCategories = new ArrayList<ProductCategoryMenuModel>();

		synchronized (lock) {
			tempCategories.addAll(memoryCategories);
		}

		for (ProductCategoryMenuModel productCategoryMenuModel : tempCategories) {
			if (productCategoryMenuModel.getCategoryId() == categoryId) {
				ids.add(productCategoryMenuModel.getCategoryId());

				if (productCategoryMenuModel.getSubCategories() == null) {
					continue;
				}
				for (ProductCategoryMenuModel subProductCategoryMenuModel : productCategoryMenuModel.getSubCategories()) {
					ids.add(subProductCategoryMenuModel.getCategoryId());
				}
			} else {
				if (productCategoryMenuModel.getSubCategories() == null) {
					continue;
				}
				for (ProductCategoryMenuModel subProductCategoryMenuModel : productCategoryMenuModel.getSubCategories()) {
					if (subProductCategoryMenuModel.getCategoryId() == categoryId) {
						ids.add(subProductCategoryMenuModel.getCategoryId());
						break;
					}
				}
			}

			if (!ids.isEmpty()) {
				break;
			}
		}

		return ids;
	}

	@Override
	public void addSubCategory(int parentCategoryId, String categoryName, String description) throws Exception {

		Category parentCategory = categoryDao.findById(parentCategoryId);

		if (parentCategory.getCategory() != null) {
			throw new Exception("Cannot add a category to a sub category");
		}

		Category category = new Category();
		category.setCreated(new Date());
		category.setName(categoryName);
		category.setDescription(description);
		category.setCategory(parentCategory);

		categoryDao.merge(category);

	}

	@Override
	public void updateMainCategory(int id, String name, String description) throws Exception {

		Category currentCategory = categoryDao.findById(id);
		if (currentCategory == null) {
			throw new Exception("Category does not exist");
		}

		currentCategory.setDescription(description);
		currentCategory.setName(name);

		categoryDao.merge(currentCategory);

	}

	@Override
	public void updateSubCategory(int id, int parentCategoryId, String categoryName, String description) throws Exception {

		Category currentCategory = categoryDao.findById(id);
		if (currentCategory == null) {
			throw new Exception("Category does not exist");
		}

		Category parentCategory = categoryDao.findById(parentCategoryId);

		if (parentCategory.getCategory() != null) {
			throw new Exception("Cannot add a category to a sub category");
		}

		currentCategory.setDescription(description);
		currentCategory.setName(categoryName);
		currentCategory.setCategory(parentCategory);

		categoryDao.merge(currentCategory);

	}

	@Override
	public CategoryModelForm getCategory(int categoryId) throws Exception {
		Category currentCategory = categoryDao.findById(categoryId);
		if (currentCategory == null) {
			throw new Exception("Category does not exist");
		}

		CategoryModelForm categoryModelForm = new CategoryModelForm();
		categoryModelForm.setId(currentCategory.getId());
		categoryModelForm.setName(currentCategory.getName());
		categoryModelForm.setDescription(currentCategory.getDescription());

		if (currentCategory.getCategory() != null) {
			categoryModelForm.setParentId(currentCategory.getCategory().getId());
		}

		return categoryModelForm;
	}

	@Override
	public List<ProductCategoryMenuModel> getProductMenuCategories() {
		final List<ProductCategoryMenuModel> returnList = new ArrayList<ProductCategoryMenuModel>();

		synchronized (lock) {
			returnList.addAll(memoryCategories);
		}

		return returnList;
	}

	@Override
	public void refreshCategories() {
		final List<ProductCategoryMenuModel> tempCategories = new ArrayList<ProductCategoryMenuModel>();

		final List<Category> dbCategories = categoryDao.getAllMainCategories();
		for (Category category : dbCategories) {

			ProductCategoryMenuModel productCategory = convert(category);

			productCategory.setCategoryId(category.getId());
			productCategory.setCategoryName(category.getName());

			List<ProductCategoryMenuModel> subCategories = new ArrayList<ProductCategoryMenuModel>();

			List<Category> dbSubCategories = categoryDao.getAllSubCategories(category.getId());
			for (Category subCat : dbSubCategories) {
				subCategories.add(convert(subCat));
			}
			productCategory.setSubCategories(subCategories);

			tempCategories.add(productCategory);

		}

		synchronized (lock) {
			memoryCategories.clear();
			memoryCategories.addAll(tempCategories);
			CategoriesList.setCategoryMenuModels(tempCategories);
		}

	}

	@Override
	public Category findById(int categoryId) {
		Category category = categoryDao.findById(categoryId);

		if (category == null) {
			throw new NullPointerException("No category found for id [" + categoryId + "]");
		}
		return category;
	}

	private ProductCategoryMenuModel convert(Category category) {
		ProductCategoryMenuModel productCategory = new ProductCategoryMenuModel();
		productCategory.setCategoryId(category.getId());
		productCategory.setCategoryName(category.getName());

		return productCategory;
	}
}

/**
 * 
 */
package com.manager;

import java.util.List;

import com.servlet.model.ProductCategoryModel;
import com.servlet.model.ProductModel;
import com.servlet.model.ProductTagModel;
import com.servlet.model.forms.CategoryModelForm;
import com.servlet.model.forms.ProductModelForm;
import com.servlet.model.forms.TagModelForm;

/**
 * @author CP311133
 * 
 */
public interface ProductManager {

	public List<ProductModel> getAllProducts(int startIndex, int count);

	public List<ProductModel> getCategoryProducts(int categoryId, int startIndex, int count);

	public List<ProductModel> getTagsProducts(int tagId, int startIndex, int count);

	void addMainCategory(String categoryName, String description);

	public void addSubCategory(int parentCategory, String categoryName, String description) throws Exception;

	public void addMainTag(String tagName);

	public void addSubTag(int parentTag, String tagName) throws Exception;

	void addProduct(ProductModelForm productModelForm);

	List<ProductCategoryModel> getAllMainCategories();

	List<ProductTagModel> getMainProductTags();

	public List<ProductCategoryModel> getAllCategories();

	public List<ProductTagModel> getAllProductTags();

	void refreshProducts();

	public CategoryModelForm getCategory(int categoryId) throws Exception;

	public void updateMainCategory(int id, String name, String description) throws Exception;

	public void updateSubCategory(int id, int parentCategory, String categoryName, String description) throws Exception;

	public TagModelForm getTags(int tagId) throws Exception;

	public void updateMainTag(int id, String name) throws Exception;

	public void updateSubTag(int id, int parentId, String name) throws Exception;
}

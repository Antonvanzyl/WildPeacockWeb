/**
 * 
 */
package com.manager;

import java.util.List;

import com.entity.db.Category;
import com.entity.db.Product;
import com.entity.db.Tag;
import com.servlet.model.ProductCategoryModel;
import com.servlet.model.ProductModel;
import com.servlet.model.ProductTagModel;

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

	void addProduct(Product product, List<Tag> tags, List<Category> categories);

	void refreshProducts();

	List<ProductCategoryModel> getAllMainCategories();

	List<ProductTagModel> getMainProductTags();

	public List<ProductCategoryModel> getAllCategories();

	public List<ProductTagModel> getAllProductTags();
}

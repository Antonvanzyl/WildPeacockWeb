/**
 * 
 */
package com.manager;

import java.util.List;

import com.servlet.model.Product;

/**
 * @author CP311133
 * 
 */
public interface ProductManager {

	public List<Product> getAllProducts(int startIndex, int count);

	public List<Product> getCategoryProducts(int categoryId, int startIndex, int count);

	public List<Product> getTagsProducts(int tagId, int startIndex, int count);

}

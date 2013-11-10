/**
 * 
 */
package com.manager;

import java.io.IOException;
import java.util.List;

import com.servlet.model.ProductModel;
import com.servlet.model.forms.ProductModelForm;

/**
 * @author CP311133
 * 
 */
public interface ProductManager {

	public List<ProductModel> getAllProducts(int startIndex, int count);

	public List<ProductModel> getCategoryProducts(int categoryId, int startIndex, int count);

	public List<ProductModel> getTagsProducts(int tagId, int startIndex, int count);

	int addProduct(ProductModelForm productModelForm) throws IOException;

	void refreshProducts();

	public ProductModel getProduct(int productId);

	public List<ProductModel> findProducts(String searchString, int i, int j);

	public void setProductImage(int productId, byte[] bytes);
}

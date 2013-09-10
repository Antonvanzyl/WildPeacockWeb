/**
 * File: ProductManagerImpl.java
 * Date: 09 Sep 2013
 * Author: Anton Van Zyl
 */
package com.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.servlet.model.Product;
import com.servlet.model.ProductCategory;
import com.servlet.model.ProductTag;

/**
 * @author Anton Van Zyl
 * 
 */
@Component
@Transactional
public class ProductManagerImpl implements ProductManager, InitializingBean {

	private List<Product> products = new ArrayList<Product>();

	@Override
	public List<Product> getAllProducts(int startIndex, int count) {

		List<Product> temp = new ArrayList<Product>();

		synchronized (products) {
			temp.addAll(products);
		}

		return temp;
	}

	@Override
	public List<Product> getCategoryProducts(int categoryId,int startIndex, int count) {

		List<Product> temp = new ArrayList<Product>();

		for (Product product : getProducts()) {
			if (product.getCategories() == null) {
				continue;
			}

			for (ProductCategory category : product.getCategories()) {
				if (category.getCategoryId() == categoryId) {
					temp.add(product);
					break;
				}
			}
		}

		return temp;
	}

	private List<Product> getProducts() {
		synchronized (products) {
			return products;
		}
	}

	@Override
	public List<Product> getTagsProducts(int tagId,int startIndex, int count) {
		List<Product> temp = new ArrayList<Product>();

		for (Product product : getProducts()) {
			if (product.getProductTags() == null) {
				continue;
			}

			for (ProductTag tag : product.getProductTags()) {
				if (tag.getTagId() == tagId) {
					temp.add(product);
					break;
				}
			}
		}

		return temp;
	}

	private void refreshProducts() {
		List<Product> temp = new ArrayList<Product>();

		Product product = new Product();
		product.setTitle("Samon");
		product.setSubTitle("Fish");
		product.setPrice(BigDecimal.TEN);
		product.setProductId(1);

		temp.add(product);

		synchronized (products) {
			products = temp;
		}

		// TODO must complete the db loading

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		refreshProducts();
	}

}

/**
 * File: ProductManagerImpl.java
 * Date: 09 Sep 2013
 * Author: Anton Van Zyl
 */
package com.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dao.db.ProductDao;
import com.dao.db.ProductTagsDao;
import com.entity.db.Category;
import com.entity.db.Product;
import com.entity.db.ProductTags;
import com.entity.db.ProductTagsId;
import com.entity.db.Tag;
import com.servlet.model.ProductCategoryModel;
import com.servlet.model.ProductModel;
import com.servlet.model.ProductTagModel;
import com.servlet.model.forms.ProductModelForm;

/**
 * @author Anton Van Zyl
 * 
 */
@Component
@Transactional
public class ProductManagerImpl implements ProductManager {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ProductTagsDao productTagsDao;

	@Autowired
	private CategoryManager categoryManager;
	
	@Autowired
	private TagManager tagManager;

	/*
	 * Memory items
	 */
	private final Object lock = new Object();
	private final List<ProductModel> memoryProducts = new ArrayList<ProductModel>();

	@Override
	public List<ProductModel> getAllProducts(int startIndex, int count) {

		List<ProductModel> temp = new ArrayList<ProductModel>();

		synchronized (lock) {
			temp.addAll(memoryProducts);
		}

		return temp;
	}

	@Override
	public List<ProductModel> getCategoryProducts(int categoryId, int startIndex, int count) {

		List<ProductModel> temp = new ArrayList<ProductModel>();

		List<Integer> selectedCategories = categoryManager.getAllCategoriesForId(categoryId);
		if (selectedCategories.isEmpty()) {
			return temp;
		}

		for (ProductModel product : getProducts()) {
			if (product.getCategory() == null) {
				continue;
			}
			if (selectedCategories.contains(product.getCategory().getCategoryId())) {
				temp.add(product);
			}
		}

		return temp;
	}

	@Override
	public List<ProductModel> findProducts(String searchString, int startIndex, int count) {

		List<ProductModel> temp = new ArrayList<ProductModel>();

		for (ProductModel product : getProducts()) {
			if (product.getCategory() == null) {
				continue;
			}

			if (StringUtils.containsAny(product.getDescription(), searchString)) {
				temp.add(product);
			} else if (StringUtils.containsAny(product.getTitle(), searchString)) {
				temp.add(product);
			} else if (StringUtils.containsAny(product.getSubTitle(), searchString)) {
				temp.add(product);
			} else if (product.getProductTags() != null) {
				for (ProductTagModel productTagModel : product.getProductTags()) {
					Tag tag = tagManager.findById(productTagModel.getTagId());
					if (StringUtils.containsAny(tag.getName(), searchString)) {
						temp.add(product);
						break;
					}
				}
			}
		}

		return temp;
	}

	private List<ProductModel> getProducts() {
		synchronized (lock) {
			return memoryProducts;
		}
	}

	@Override
	public List<ProductModel> getTagsProducts(int tagId, int startIndex, int count) {
		List<ProductModel> temp = new ArrayList<ProductModel>();

		for (ProductModel product : getProducts()) {
			if (product.getProductTags() == null) {
				continue;
			}

			for (ProductTagModel tag : product.getProductTags()) {
				if (tag.getTagId() == tagId) {
					temp.add(product);
					break;
				}
			}
		}

		return temp;
	}

	/**
	 * This will refresh the product list to the latest that is in the database
	 */
	@Override
	public void refreshProducts() {

		final List<ProductModel> temp = new ArrayList<ProductModel>();

		List<Product> dbProducts = productDao.getAllProducts();

		for (Product product : dbProducts) {

			Category category = product.getCategory();
			if (category == null) {
				continue;
			}

			Set<ProductTags> productTags = product.getProductTagses();
			if (productTags == null || productTags.isEmpty()) {
				continue;
			}

			ProductModel productModel = new ProductModel();

			productModel.setTitle(product.getTitle());
			productModel.setDescription(product.getDescription());
			productModel.setPrice(product.getPrice());
			productModel.setProductId(product.getId());
			productModel.setSubTitle(product.getSubTitle());

			// Category
			ProductCategoryModel productCategory = new ProductCategoryModel();
			productCategory.setCategoryId(category.getId());
			productCategory.setCategoryName(category.getName());
			productModel.setCategory(productCategory);

			// Tags
			List<ProductTagModel> tags = new ArrayList<ProductTagModel>();
			for (ProductTags productTag : productTags) {
				Tag tag = tagManager.findById(productTag.getId().getTagId());

				ProductTagModel productTagModel = new ProductTagModel();
				productTagModel.setTagId(tag.getId());
				productTagModel.setTagTitle(tag.getName());

				tags.add(productTagModel);
			}
			productModel.setProductTags(tags);

			temp.add(productModel);
		}

		

		synchronized (lock) {
			memoryProducts.clear();
			memoryProducts.addAll(temp);
		}

		categoryManager.refreshCategories();
		tagManager.refreshTags();

	}

	@Override
	public void addProduct(ProductModelForm productModelForm) {

		Product product = new Product();
		product.setTitle(productModelForm.getTitle());
		product.setSubTitle(productModelForm.getSubTitle());
		product.setDescription(productModelForm.getDescription());
		product.setPrice(productModelForm.getPrice());
		product.setCreated(new Date());
		if (StringUtils.isEmpty(productModelForm.getPhotoURL())) {
			product.setPhotoUrl("");
		} else {
			product.setPhotoUrl(productModelForm.getPhotoURL());
		}

		Category category = categoryManager.findById(productModelForm.getCategoryId());
		product.setCategory(category);

		product = productDao.merge(product);

		for (int tagId : productModelForm.getTagIds()) {
			ProductTags productTag = new ProductTags();
			productTag.setCreated(new Date());
			productTag.setProduct(product);

			Tag tag = tagManager.findById(tagId);
			if (tag == null) {
				throw new NullPointerException("No tag found for id [" + tagId + "]");
			}
			productTag.setTag(tag);

			ProductTagsId id = new ProductTagsId();
			id.setProductId(product.getId());
			id.setTagId(tag.getId());
			productTag.setId(id);

			productTagsDao.merge(productTag);
		}

		refreshProducts();
	}

	@Override
	public ProductModel getProduct(int productId) {

		List<ProductModel> temp = new ArrayList<ProductModel>();

		synchronized (lock) {
			temp.addAll(memoryProducts);
		}

		for (ProductModel productModel : temp) {
			if (productModel.getProductId() == productId) {
				return productModel;
			}
		}

		return null;
	}

}

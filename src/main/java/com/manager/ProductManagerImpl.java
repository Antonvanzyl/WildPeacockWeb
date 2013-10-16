/**
 * File: ProductManagerImpl.java
 * Date: 09 Sep 2013
 * Author: Anton Van Zyl
 */
package com.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dao.db.CategoryDao;
import com.dao.db.ProductDao;
import com.dao.db.ProductTagsDao;
import com.dao.db.TagDao;
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
	private CategoryDao categoryDao;

	@Autowired
	private TagDao tagDao;

	@Autowired
	private ProductTagsDao productTagsDao;

	private List<ProductModel> products = new ArrayList<ProductModel>();

	@Override
	public List<ProductModel> getAllProducts(int startIndex, int count) {

		List<ProductModel> temp = new ArrayList<ProductModel>();

		synchronized (products) {
			temp.addAll(products);
		}

		return temp;
	}

	@Override
	public List<ProductModel> getCategoryProducts(int categoryId, int startIndex, int count) {

		List<ProductModel> temp = new ArrayList<ProductModel>();

		for (ProductModel product : getProducts()) {
			if (product.getCategory() == null) {
				continue;
			}

			if (product.getCategory().getCategoryId() == categoryId) {
				temp.add(product);
				break;
			}
		}

		return temp;
	}

	private List<ProductModel> getProducts() {
		synchronized (products) {
			return products;
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
				Tag tag = tagDao.findById(productTag.getId().getTagId());

				ProductTagModel productTagModel = new ProductTagModel();
				productTagModel.setTagId(tag.getId());
				productTagModel.setTagTitle(tag.getName());

				tags.add(productTagModel);
			}
			productModel.setProductTags(tags);

			temp.add(productModel);
		}

		synchronized (products) {
			products = temp;
		}

	}

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

		refreshProducts();
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
		refreshProducts();
	}

	@Override
	public List<ProductTagModel> getMainProductTags() {
		List<Tag> tags = tagDao.getAllMainTags();

		List<ProductTagModel> returnTags = new ArrayList<ProductTagModel>();
		for (Tag tag : tags) {

			ProductTagModel productTagModel = new ProductTagModel();
			productTagModel.setTagId(tag.getId());
			productTagModel.setTagTitle(tag.getName());

			returnTags.add(productTagModel);
		}

		return returnTags;
	}

	@Override
	public List<ProductTagModel> getAllProductTags() {
		List<Tag> tags = tagDao.getAllTags();

		List<ProductTagModel> returnTags = new ArrayList<ProductTagModel>();
		for (Tag tag : tags) {

			ProductTagModel productTagModel = new ProductTagModel();
			productTagModel.setTagId(tag.getId());
			productTagModel.setTagTitle(tag.getName());

			returnTags.add(productTagModel);
		}

		return returnTags;
	}

	@Override
	public void addMainTag(String tagName) {

		Tag tag = new Tag();
		tag.setCreated(new Date());
		tag.setName(tagName);
		tagDao.merge(tag);

		refreshProducts();
	}

	@Override
	public void addSubTag(int parentTagId, String tagName) throws Exception {

		Tag parentTag = tagDao.findById(parentTagId);
		if (parentTag.getTag() != null) {
			throw new Exception("Cannot add a tag to a sub tag");
		}

		Tag tag = new Tag();
		tag.setCreated(new Date());
		tag.setName(tagName);
		tag.setTag(parentTag);
		tagDao.merge(tag);

		refreshProducts();
	}

	@Override
	public void addProduct(ProductModelForm productModelForm) {

		Product product = new Product();
		product.setTitle(productModelForm.getTitle());
		product.setSubTitle(productModelForm.getSubTitle());
		product.setDescription(productModelForm.getDescription());
		product.setPrice(productModelForm.getPrice());

		Category category = categoryDao.findById(productModelForm.getCategoryId());
		if (category == null) {
			throw new NullPointerException("No category found for id [" + productModelForm.getCategoryId() + "]");
		}
		product.setCategory(category);

		product = productDao.merge(product);

		for (int tagId : productModelForm.getTagIds()) {
			ProductTags productTag = new ProductTags();
			productTag.setCreated(new Date());
			productTag.setProduct(product);

			Tag tag = tagDao.findById(tagId);
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

}

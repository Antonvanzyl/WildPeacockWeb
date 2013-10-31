/**
 * File: Product.java
 * Date: 09 Sep 2013
 * Author: Anton Van Zyl
 */
package com.servlet.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Anton Van Zyl
 * 
 */
public class ProductModel {

	private int productId;
	private String title;
	private String subTitle;
	private String description;
	private BigDecimal price;
	private String photoUrl;
	private ProductCategoryModel category = new ProductCategoryModel();

	private List<ProductTagModel> productTags = new ArrayList<ProductTagModel>();

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public ProductCategoryModel getCategory() {
		return category;
	}

	public void setCategory(ProductCategoryModel category) {
		this.category = category;
	}

	public List<ProductTagModel> getProductTags() {
		return productTags;
	}

	public void setProductTags(List<ProductTagModel> productTags) {
		this.productTags = productTags;
	}
	
	public String getPhotoUrl() {
		return photoUrl;
	}
	
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

}

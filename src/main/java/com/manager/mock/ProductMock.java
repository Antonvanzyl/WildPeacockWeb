/**
 * File: ProductMock.java
 * Date: 16 Sep 2013
 * Author: Anton Van Zyl
 */
package com.manager.mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.servlet.model.Product;
import com.servlet.model.ProductCategory;
import com.servlet.model.ProductTag;

/**
 * @author Anton Van Zyl
 * 
 */
public class ProductMock {

	private static ProductCategory createCategory(int id, String categoryName) {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setCategoryId(id);
		productCategory.setCategoryName(categoryName);
		return productCategory;
	}

	private static ProductTag createProductTag(int id, String title) {
		ProductTag productTag = new ProductTag();
		productTag.setTagId(id);
		productTag.setTagTitle(title);
		return productTag;
	}

	private static Product createProduct(int id, String title, String subTitle, List<ProductCategory> productCategory,
			List<ProductTag> productTag) {
		Product product = new Product();
		product.setTitle(title);
		product.setSubTitle(subTitle);

		StringBuilder builder = new StringBuilder();
		for (int x = 0; x < 100; x++) {
			builder.append("Je suis juste un gardien de l'espace pour la description du produit");
		}

		product.setDescription(builder.toString());
		product.setPrice(BigDecimal.TEN);
		product.setProductId(id);
		product.setCategories(productCategory);
		product.setProductTags(productTag);
		return product;
	}

	public static List<Product> generateProducts() {
		List<Product> temp = new ArrayList<Product>();
		temp.addAll(generateWineProducts());
		temp.addAll(generateCheeseProducts());
		return temp;
	}

	private static List<Product> generateWineProducts() {
		List<Product> temp = new ArrayList<Product>();

		List<ProductCategory> wineList = new ArrayList<ProductCategory>();
		wineList.add(createCategory(1, "wine"));

		List<ProductTag> wineProductTag = new ArrayList<ProductTag>();
		wineProductTag.add(createProductTag(2, "White"));

		temp.add(createProduct(1, "Robbertson", "Chardonnay", wineList, wineProductTag));
		temp.add(createProduct(3, "RockField", "Chardonnay", wineList, wineProductTag));
		temp.add(createProduct(5, "Durbanbille", "Chardonnay", wineList, wineProductTag));
		temp.add(createProduct(7, "Two Oceans", "Chardonnay", wineList, wineProductTag));

		wineProductTag = new ArrayList<ProductTag>();
		wineProductTag.add(createProductTag(1, "Red"));
		temp.add(createProduct(2, "No Name", "Merloe", wineList, wineProductTag));
		temp.add(createProduct(4, "Var 69", "Merloe", wineList, wineProductTag));
		temp.add(createProduct(6, "Wellington", "Merloe", wineList, wineProductTag));

		return temp;
	}

	private static List<Product> generateCheeseProducts() {
		List<Product> temp = new ArrayList<Product>();

		List<ProductCategory> cheeseList = new ArrayList<ProductCategory>();
		cheeseList.add(createCategory(2, "cheese"));

		List<ProductTag> cheeseProductTag = new ArrayList<ProductTag>();
		cheeseProductTag.add(createProductTag(3, "cottage cheese"));

		temp.add(createProduct(8, "Ayibe", "cottage cheese", cheeseList, cheeseProductTag));
		temp.add(createProduct(9, "Caravane cheese", "cottage cheese", cheeseList, cheeseProductTag));
		temp.add(createProduct(10, "Bokmakiri cheese", "cottage cheese", cheeseList, cheeseProductTag));
		temp.add(createProduct(11, "Kwaito cheese", "cottage cheese", cheeseList, cheeseProductTag));

		cheeseProductTag = new ArrayList<ProductTag>();
		cheeseProductTag.add(createProductTag(3, "buffalo's milk "));

		temp.add(createProduct(12, "hhena", "buffalo's milk ", cheeseList, cheeseProductTag));
		temp.add(createProduct(13, "Chhurpi", "buffalo's milk ", cheeseList, cheeseProductTag));
		temp.add(createProduct(14, "Rubing", "buffalo's milk ", cheeseList, cheeseProductTag));
		temp.add(createProduct(15, "Nguri", "buffalo's milk ", cheeseList, cheeseProductTag));

		return temp;
	}

}

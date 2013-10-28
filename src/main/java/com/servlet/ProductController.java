/**
 * File: UserAuthenticationController.java
 * Date: 04 Jun 2013
 * Author: Anton Van Zyl
 */
package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.manager.ProductManager;
import com.servlet.model.ProductCategoryMenuModel;
import com.servlet.model.ProductModel;
import com.servlet.model.ProductTagMenuModel;
import com.servlet.sort.TagComparator;

/**
 * @author Anton Van Zyl
 * 
 */
@Controller
public class ProductController {

	@Autowired
	private ProductManager productManager;

	private final TagComparator tagComparator = new TagComparator();

	@RequestMapping(value = "/view_Retail_Products", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView home() {

		ModelAndView modelAndView = new ModelAndView("products/summary");

		List<ProductCategoryMenuModel> categories = productManager.getProductMenuCategories();

		int categoryId = 0;
		if (categories.size() > 0) {
			categoryId = categories.get(0).getCategoryId();
		}

		modelAndView.addObject("categories", categories);
		modelAndView.addObject("selectedCategoryId", categoryId);
		modelAndView.addObject("tagsScript", buildTagScript());

		return modelAndView;
	}

	@RequestMapping(value = "/getProducts", method = { RequestMethod.GET, RequestMethod.POST })
	public void getProducts(@RequestParam("categoryId") int categoryId, HttpServletResponse response) throws IOException {

		final StringBuilder builder = new StringBuilder();

		if (categoryId != 0) {
			List<ProductModel> products = productManager.getCategoryProducts(categoryId, 0, 1000);

			for (ProductModel productModel : products) {
				builder.append("<li class=\"item\">" + productModel.getTitle() + "</li>");
			}
		}

		if (StringUtils.isEmpty(builder.toString())) {
			builder.append("No Products to display...");
		}
		
		System.out.println(builder.toString());

		JSONObject jsonObject = new JSONObject();

		try {
			jsonObject.put("product", builder.toString());
		} catch (JSONException e) {
			throw new IOException(e);
		}

		response.setContentType("text/plain");
		response.getOutputStream().print(jsonObject.toString());
		response.getOutputStream().flush();

	}

	private String buildTagScript() {
		Map<ProductTagMenuModel, List<ProductTagMenuModel>> tags = productManager.getAllMenuProductTags();

		final StringBuilder builder = new StringBuilder();
		int count = 1;

		List<ProductTagMenuModel> menu = new ArrayList<ProductTagMenuModel>();
		menu.addAll(tags.keySet());

		Collections.sort(menu, tagComparator);

		for (ProductTagMenuModel productTagModel : menu) {

			String category = productTagModel.getTagTitle();
			builder.append(createTagItemScript(category, productTagModel));

			List<ProductTagMenuModel> subTags = tags.get(productTagModel);
			for (ProductTagMenuModel productTagSubMenuModel : subTags) {
				builder.append(",");
				builder.append(createTagItemScript(category, productTagSubMenuModel));
			}

			if (count < tags.size()) {
				builder.append(",");
			}

			count++;
		}

		return builder.toString();
	}

	private String createTagItemScript(String category, ProductTagMenuModel menuModel) {
		StringBuilder builder = new StringBuilder();
		builder.append("{");
		builder.append("label : \"");
		builder.append(menuModel.getTagTitle());
		builder.append("\", category : \"");
		builder.append(category);
		builder.append("\"}");
		return builder.toString();
	}

}

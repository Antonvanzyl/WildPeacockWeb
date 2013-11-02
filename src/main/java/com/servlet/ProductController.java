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

import javax.servlet.http.HttpServletRequest;
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

import com.manager.CategoryManager;
import com.manager.ProductManager;
import com.manager.TagManager;
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

	@Autowired
	private CategoryManager categoryManager;

	@Autowired
	private TagManager tagManager;

	private final TagComparator tagComparator = new TagComparator();

	@RequestMapping(value = "/view_Retail_Products", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView home(@RequestParam(required = false, value = "categoryId") Integer categoryId) {

		ModelAndView modelAndView = new ModelAndView("products/summary");

		List<ProductCategoryMenuModel> categories = categoryManager.getProductMenuCategories();
		if (categoryId == null && categories.size() > 0) {
			categoryId = categories.get(0).getCategoryId();
		}

		modelAndView.addObject("categories", categories);
		modelAndView.addObject("selectedCategoryId", categoryId);
		modelAndView.addObject("tagsScript", buildTagScript());

		return modelAndView;
	}

	@RequestMapping(value = "/getProducts", method = { RequestMethod.GET, RequestMethod.POST })
	public void getProducts(@RequestParam("categoryId") int categoryId, HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		String message = "";
		if (categoryId != 0) {
			List<ProductModel> products = productManager.getCategoryProducts(categoryId, 0, 1000);
			message = buildProductIcons(products, request);
		} else {
			message = "No Products currently for category...";
		}

		JSONObject jsonObject = new JSONObject();

		try {
			jsonObject.put("product", message);
		} catch (JSONException e) {
			throw new IOException(e);
		}

		response.setContentType("text/plain");
		response.getOutputStream().print(jsonObject.toString());
		response.getOutputStream().flush();

	}

	@RequestMapping(value = "/getProductsSearch", method = { RequestMethod.GET, RequestMethod.POST })
	public void getProductsSearch(@RequestParam("searchString") String searchString, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String message = "";
		if (StringUtils.isNotEmpty(searchString)) {
			List<ProductModel> products = productManager.findProducts(searchString, 0, 1000);
			message = buildProductIcons(products, request);
		} else {
			message = "No Products found...";
		}

		JSONObject jsonObject = new JSONObject();

		try {
			jsonObject.put("product", message);
		} catch (JSONException e) {
			throw new IOException(e);
		}

		response.setContentType("text/plain");
		response.getOutputStream().print(jsonObject.toString());
		response.getOutputStream().flush();

	}

	@RequestMapping(value = "/select_Retail_Products", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView selectRetailProduct(@RequestParam("productId") int productId) {

		ModelAndView modelAndView = new ModelAndView("products/details");
		ProductModel product = productManager.getProduct(productId);
		modelAndView.addObject("product", product);

		return modelAndView;
	}

	private String buildTagScript() {
		Map<ProductTagMenuModel, List<ProductTagMenuModel>> tags = tagManager.getAllMenuProductTags();

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

	private String buildProductIcons(List<ProductModel> products, HttpServletRequest request) {
		final StringBuilder builder = new StringBuilder();
		for (ProductModel productModel : products) {
			builder.append("<li class=\"item\" style=\"text-align: center;\">");
			builder.append("<a href=\"");
			builder.append(request.getContextPath());
			builder.append("/select_Retail_Products?productId=");
			builder.append(productModel.getProductId());
			builder.append("\">");
			builder.append(productModel.getTitle());
			builder.append("<br/>");
			builder.append(productModel.getSubTitle());
			builder.append("<br/><img alt=\"No Image\" src=\"");
			if (StringUtils.isNotEmpty(productModel.getPhotoUrl())) {
				builder.append(productModel.getPhotoUrl());
			} else {
				builder.append(request.getContextPath());
				builder.append("/resources/images/no-image.jpg");
			}
			builder.append("\" width=\"120px\" height=\"120px\" style=\"margin:1px;\" />");
			builder.append("</a>");
			builder.append("</li>");
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

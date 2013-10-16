/**
 * File: BaseController.java
 * Date: 04 Jun 2013
 * Author: Anton Van Zyl
 */
package com.servlet;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.manager.AuthorityManager;
import com.manager.ProductManager;
import com.manager.PublishingManager;
import com.servlet.model.ProductCategoryModel;
import com.servlet.model.ProductTagModel;
import com.servlet.model.User;
import com.servlet.model.forms.CategoryModelForm;
import com.servlet.model.forms.ProductModelForm;
import com.servlet.model.forms.PublishModelForm;
import com.servlet.model.forms.TagModelForm;

/**
 * @author Anton Van Zyl
 * 
 */
@Controller
@SessionAttributes("User")
@RequestMapping(value = "/wildAdmin", method = { RequestMethod.GET, RequestMethod.POST })
public class AdminController {

	@Autowired
	private AuthorityManager authorityManager;

	@Autowired
	private ProductManager productManager;

	@Autowired
	private PublishingManager publishingManager;

	@ModelAttribute("User")
	public User createUser() {
		return new User();
	}

	/**
	 * Authenticate
	 * 
	 * @param user
	 * @return
	 */

	@RequestMapping
	public ModelAndView admin(@ModelAttribute("User") User user) {

		if (!user.isLoggedIn()) {
			return new ModelAndView("admin/login");
		}

		return new ModelAndView("redirect:viewManage");
	}

	@RequestMapping(value = "/submitLogin", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView submitLogin(@ModelAttribute("User") User user, BindingResult bindingResult) {

		if (StringUtils.isEmpty(user.getUsername())) {
			bindingResult.rejectValue("username", "*Required", "*Required");
		}

		if (StringUtils.isEmpty(user.getPassword())) {
			bindingResult.rejectValue("password", "*Required", "*Required");
		}

		if (bindingResult.hasErrors()) {
			user.clear();
			return new ModelAndView("admin/login");
		}

		if (!authorityManager.authenticateUser(user.getUsername(), user.getPassword())) {
			bindingResult.rejectValue("username", "*Required", "*Required");
			bindingResult.rejectValue("password", "*Required", "*Required");
			user.clear();
			return new ModelAndView("admin/login");
		}

		user.login();

		return new ModelAndView("redirect:viewManage");
	}

	@RequestMapping(value = "/viewManage", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView viewManage(@ModelAttribute("User") User user, BindingResult bindingResult,
			@RequestParam(value = "message", required = false) String message) {

		if (!user.isLoggedIn()) {
			return new ModelAndView("admin/login");
		}

		ModelAndView modelAndView = new ModelAndView("admin/manage");
		if (message != null) {
			modelAndView.addObject("message", message);
		}
		return modelAndView;
	}

	/**
	 * Add Category
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/viewAddCategory", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView viewAddCategory(@ModelAttribute("User") User user) {

		if (!user.isLoggedIn()) {
			return new ModelAndView("admin/login");
		}
		List<ProductCategoryModel> mainCategories = productManager.getAllMainCategories();

		ModelAndView modelAndView = new ModelAndView("admin/category/AddCategory");
		modelAndView.addObject("mainCategories", mainCategories);
		modelAndView.addObject("categoryModelForm", new CategoryModelForm());
		return modelAndView;
	}

	@RequestMapping(value = "/submitAddCategory", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView submitAddCategory(@ModelAttribute("categoryModelForm") CategoryModelForm categoryModelForm,
			BindingResult bindingResult, @ModelAttribute("User") User user) throws Exception {

		if (!user.isLoggedIn()) {
			return new ModelAndView("admin/login");
		}

		categoryModelForm.validate(bindingResult);

		if (bindingResult.hasErrors()) {
			List<ProductCategoryModel> mainCategories = productManager.getAllMainCategories();
			ModelAndView modelAndView = new ModelAndView("admin/category/AddCategory");
			modelAndView.addObject("mainCategories", mainCategories);
			modelAndView.addObject("categoryModelForm", categoryModelForm);
			return modelAndView;
		}

		if (categoryModelForm.getParentId() <= 0) {
			productManager.addMainCategory(categoryModelForm.getName(), categoryModelForm.getDescription());
		} else {
			productManager.addSubCategory(categoryModelForm.getParentId(), categoryModelForm.getName(), categoryModelForm.getDescription());
		}

		ModelAndView modelAndView = new ModelAndView("redirect:viewManage");
		modelAndView.addObject("message", "Success - Adding a new category");

		return modelAndView;
	}

	/**
	 * Add Tag
	 * 
	 * @param user
	 * @return
	 */

	@RequestMapping(value = "/viewAddTag", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView viewAddTag(@ModelAttribute("User") User user) {

		if (!user.isLoggedIn()) {
			return new ModelAndView("admin/login");
		}

		List<ProductTagModel> mainTags = productManager.getMainProductTags();
		ModelAndView modelAndView = new ModelAndView("admin/tag/AddTag");
		modelAndView.addObject("mainTags", mainTags);
		modelAndView.addObject("tagModelForm", new TagModelForm());
		return modelAndView;
	}

	@RequestMapping(value = "/submitAddTag", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView submitAddTag(@ModelAttribute("tagModelForm") TagModelForm tagModelForm, BindingResult bindingResult,
			@ModelAttribute("User") User user) throws Exception {

		if (!user.isLoggedIn()) {
			return new ModelAndView("admin/login");
		}

		tagModelForm.validate(bindingResult);

		if (bindingResult.hasErrors()) {
			List<ProductTagModel> mainTags = productManager.getMainProductTags();
			ModelAndView modelAndView = new ModelAndView("admin/tag/AddTag");
			modelAndView.addObject("mainTags", mainTags);
			modelAndView.addObject("tagModelForm", tagModelForm);
			return modelAndView;
		}

		if (tagModelForm.getParentId() <= 0) {
			productManager.addMainTag(tagModelForm.getName());
		} else {
			productManager.addSubTag(tagModelForm.getParentId(), tagModelForm.getName());
		}

		ModelAndView modelAndView = new ModelAndView("redirect:viewManage");
		modelAndView.addObject("message", "Success - Adding a new Tag");
		return modelAndView;
	}

	/**
	 * Add Product
	 * 
	 * @param user
	 * @return
	 */

	@RequestMapping(value = "/viewAddProduct", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView viewAddProduct(@ModelAttribute("User") User user) {

		if (!user.isLoggedIn()) {
			return new ModelAndView("admin/login");
		}

		List<ProductTagModel> mainTags = productManager.getAllProductTags();
		List<ProductCategoryModel> mainCategories = productManager.getAllCategories();

		ModelAndView modelAndView = new ModelAndView("admin/product/AddProduct");
		modelAndView.addObject("categoryList", mainCategories);
		modelAndView.addObject("tagList", mainTags);

		modelAndView.addObject("productModelForm", new ProductModelForm());
		return modelAndView;
	}

	@RequestMapping(value = "/submitAddProduct", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView submitAddProduct(@ModelAttribute("productModelForm") ProductModelForm productModelForm,
			BindingResult bindingResult, @ModelAttribute("User") User user) {

		if (!user.isLoggedIn()) {
			return new ModelAndView("admin/login");
		}

		productModelForm.validate(bindingResult);

		if (bindingResult.hasErrors()) {
			List<ProductTagModel> mainTags = productManager.getAllProductTags();
			List<ProductCategoryModel> mainCategories = productManager.getAllCategories();

			ModelAndView modelAndView = new ModelAndView("admin/product/AddProduct");
			modelAndView.addObject("categoryList", mainCategories);
			modelAndView.addObject("tagList", mainTags);
			modelAndView.addObject("productModelForm", productModelForm);
			return modelAndView;
		}

		productManager.addProduct(productModelForm);

		ModelAndView modelAndView = new ModelAndView("redirect:viewManage");
		modelAndView.addObject("message", "Success - Adding a new Product");
		return modelAndView;

	}

	/**
	 * Add Publishing
	 * 
	 * @param user
	 * @return
	 */

	@RequestMapping(value = "/viewAddPublishing", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView viewAddPublishing(@ModelAttribute("User") User user) {

		if (!user.isLoggedIn()) {
			return new ModelAndView("admin/login");
		}

		ModelAndView modelAndView = new ModelAndView("admin/publishing/AddPublishing");
		modelAndView.addObject("publishModelForm", new PublishModelForm());
		return modelAndView;
	}

	@RequestMapping(value = "/submitAddPublishing", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView submitAddPublishing(@ModelAttribute("publishModelForm") PublishModelForm publishModelForm,
			BindingResult bindingResult, @ModelAttribute("User") User user) {

		if (!user.isLoggedIn()) {
			return new ModelAndView("admin/login");
		}

		publishModelForm.validate(bindingResult);

		if (bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("admin/publishing/AddPublishing");
			modelAndView.addObject("publishModelForm", publishModelForm);
			return modelAndView;
		}

		publishingManager.addPublishing(publishModelForm.getTitle(), publishModelForm.getDescription(), publishModelForm.getSubtitle(),
				publishModelForm.getEventDate(), publishModelForm.getSection());

		ModelAndView modelAndView = new ModelAndView("admin/AddPublishing");
		modelAndView.addObject("message", "Success - Adding a new " + publishModelForm.getSection() + " Success");
		return modelAndView;
	}

}

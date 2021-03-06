/**
 * File: BaseController.java
 * Date: 04 Jun 2013
 * Author: Anton Van Zyl
 */
package com.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.manager.AuthorityManager;
import com.manager.CategoryManager;
import com.manager.CustomPageManager;
import com.manager.ProductManager;
import com.manager.PublishingManager;
import com.manager.TagManager;
import com.servlet.model.ProductCategoryModel;
import com.servlet.model.ProductTagModel;
import com.servlet.model.PublishRecordModel;
import com.servlet.model.forms.CategoryModelForm;
import com.servlet.model.forms.CustomPageModelForm;
import com.servlet.model.forms.ProductModelForm;
import com.servlet.model.forms.PublishModelForm;
import com.servlet.model.forms.TagModelForm;
import com.util.Copyright;

/**
 * @author Anton Van Zyl
 * 
 */
@Controller
public class AdminController {

	private static final Logger log = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private AuthorityManager authorityManager;

	@Autowired
	private ProductManager productManager;

	@Autowired
	private PublishingManager publishingManager;

	@Autowired
	private CategoryManager categoryManager;

	@Autowired
	private CustomPageManager customPageManager;

	@Autowired
	private TagManager tagManager;

	/**
	 * Authenticate
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/wildAdmin", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView admin() {

		SecurityContext securityContext = SecurityContextHolder.getContext();

		final Authentication authToken = securityContext.getAuthentication();
		if (authToken != null && authToken.isAuthenticated()) {
			new ModelAndView("admin/manage");
		}

		return new ModelAndView("admin/login");
	}

	@RequestMapping(value = "/failedLogin", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView submitLogin() {

		ModelAndView modelAndView = new ModelAndView("admin/login");
		modelAndView.addObject("error", "Incorrect credentials provided");
		return modelAndView;
	}

	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			try {
				log.info("Invalidating session: " + session.getId());
				// the SessionDestroyedListener will do the logout
				session.invalidate();
			} catch (Exception e) {
				log.debug("Session already invalidated: " + session.getId());
			}
		}

		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(null);

		// clear the cookie(s)
		Cookie cookie = new Cookie("JSESSIONID", null);
		cookie.setPath(request.getContextPath());
		cookie.setMaxAge(0);
		response.addCookie(cookie);

		return new ModelAndView("admin/login");
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/viewManage", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView viewManage(@RequestParam(value = "message", required = false) String message) {

		ModelAndView modelAndView = new ModelAndView("admin/manage");
		if (message != null) {
			modelAndView.addObject("message", message);
			productManager.refreshProducts();
			publishingManager.loadPublishings();
			customPageManager.refreshCustomPages();
		}
		return modelAndView;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/refreshSiteDate", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView refreshSiteDate(@RequestParam(value = "message", required = false) String message) {

		log.info("Refresh of products requested");
		productManager.refreshProducts();
		publishingManager.loadPublishings();
		customPageManager.refreshCustomPages();

		ModelAndView modelAndView = new ModelAndView("redirect:viewManage");
		modelAndView.addObject("message", "Success - Adding a new category");

		return modelAndView;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/disable", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView disable() {
		Copyright.setAvailable(false);
		return new ModelAndView("admin/login");
	}

	@RequestMapping(value = "/enable", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView enable() {
		Copyright.setAvailable(true);
		return new ModelAndView("admin/login");
	}

	/**
	 * Add Category
	 * 
	 * @param user
	 * @return
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/viewAddCategory", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView viewAddCategory() {

		List<ProductCategoryModel> mainCategories = categoryManager.getAllMainCategories();

		ModelAndView modelAndView = new ModelAndView("admin/category/AddCategory");
		modelAndView.addObject("mainCategories", mainCategories);
		modelAndView.addObject("categoryModelForm", new CategoryModelForm());
		return modelAndView;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/submitAddCategory", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView submitAddCategory(@ModelAttribute("categoryModelForm") CategoryModelForm categoryModelForm,
			BindingResult bindingResult) throws Exception {

		categoryModelForm.validate(bindingResult);

		if (bindingResult.hasErrors()) {
			List<ProductCategoryModel> mainCategories = categoryManager.getAllMainCategories();
			ModelAndView modelAndView = new ModelAndView("admin/category/AddCategory");
			modelAndView.addObject("mainCategories", mainCategories);
			modelAndView.addObject("categoryModelForm", categoryModelForm);
			return modelAndView;
		}

		if (categoryModelForm.getParentId() <= 0) {
			categoryManager.addMainCategory(categoryModelForm.getName(), categoryModelForm.getDescription());
		} else {
			categoryManager
					.addSubCategory(categoryModelForm.getParentId(), categoryModelForm.getName(), categoryModelForm.getDescription());
		}

		ModelAndView modelAndView = new ModelAndView("redirect:viewManage");
		modelAndView.addObject("message", "Success - Adding a new category");

		return modelAndView;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/searchCategory", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView searchCategory() {

		List<ProductCategoryModel> categories = categoryManager.getAllCategories();
		ModelAndView modelAndView = new ModelAndView("admin/category/FindCategory");
		modelAndView.addObject("categories", categories);
		return modelAndView;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/editCategory", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView editCategory(@RequestParam("categoryId") int categoryId) throws Exception {

		List<ProductCategoryModel> mainCategories = categoryManager.getAllMainCategories();

		ModelAndView modelAndView = new ModelAndView("admin/category/EditCategory");
		modelAndView.addObject("mainCategories", mainCategories);

		CategoryModelForm categoryForm = categoryManager.getCategory(categoryId);
		modelAndView.addObject("categoryModelForm", categoryForm);

		return modelAndView;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/submitEditCategory", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView submitEditCategory(@ModelAttribute("categoryModelForm") CategoryModelForm categoryModelForm,
			BindingResult bindingResult) throws Exception {

		categoryModelForm.validate(bindingResult);

		if (bindingResult.hasErrors()) {
			List<ProductCategoryModel> mainCategories = categoryManager.getAllMainCategories();
			ModelAndView modelAndView = new ModelAndView("admin/category/EditCategory");
			modelAndView.addObject("mainCategories", mainCategories);
			modelAndView.addObject("categoryModelForm", categoryModelForm);
			return modelAndView;
		}

		if (categoryModelForm.getParentId() <= 0) {
			categoryManager.updateMainCategory(categoryModelForm.getId(), categoryModelForm.getName(), categoryModelForm.getDescription());
		} else {
			categoryManager.updateSubCategory(categoryModelForm.getId(), categoryModelForm.getParentId(), categoryModelForm.getName(),
					categoryModelForm.getDescription());
		}

		ModelAndView modelAndView = new ModelAndView("redirect:viewManage");
		modelAndView.addObject("message", "Success - Adding updated the category");

		return modelAndView;
	}

	/**
	 * Add Tag
	 * 
	 * @param user
	 * @return
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/viewAddTag", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView viewAddTag() {

		List<ProductTagModel> mainTags = tagManager.getMainProductTags();
		ModelAndView modelAndView = new ModelAndView("admin/tag/AddTag");
		modelAndView.addObject("mainTags", mainTags);
		modelAndView.addObject("tagModelForm", new TagModelForm());
		return modelAndView;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/submitAddTag", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView submitAddTag(@ModelAttribute("tagModelForm") TagModelForm tagModelForm, BindingResult bindingResult)
			throws Exception {

		tagModelForm.validate(bindingResult);

		if (bindingResult.hasErrors()) {
			List<ProductTagModel> mainTags = tagManager.getMainProductTags();
			ModelAndView modelAndView = new ModelAndView("admin/tag/AddTag");
			modelAndView.addObject("mainTags", mainTags);
			modelAndView.addObject("tagModelForm", tagModelForm);
			return modelAndView;
		}

		if (tagModelForm.getParentId() <= 0) {
			tagManager.addMainTag(tagModelForm.getName());
		} else {
			tagManager.addSubTag(tagModelForm.getParentId(), tagModelForm.getName());
		}

		ModelAndView modelAndView = new ModelAndView("redirect:viewManage");
		modelAndView.addObject("message", "Success - Adding a new Tag");
		return modelAndView;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/searchTag", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView searchTag() {

		List<ProductTagModel> tags = tagManager.getAllProductTags();
		ModelAndView modelAndView = new ModelAndView("admin/tag/FindTag");
		modelAndView.addObject("tags", tags);
		return modelAndView;

	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/editTag", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView editTag(@RequestParam("tagId") int tagId) throws Exception {

		List<ProductTagModel> mainTags = tagManager.getMainProductTags();
		ModelAndView modelAndView = new ModelAndView("admin/tag/EditTag");
		modelAndView.addObject("mainTags", mainTags);

		TagModelForm tagModelForm = tagManager.getTags(tagId);

		modelAndView.addObject("tagModelForm", tagModelForm);

		return modelAndView;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/submitEditTag", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView submitEditTag(@ModelAttribute("tagModelForm") TagModelForm tagModelForm, BindingResult bindingResult)
			throws Exception {

		tagModelForm.validate(bindingResult);

		if (bindingResult.hasErrors()) {
			List<ProductTagModel> mainTags = tagManager.getMainProductTags();
			ModelAndView modelAndView = new ModelAndView("admin/tag/EditTag");
			modelAndView.addObject("mainTags", mainTags);
			modelAndView.addObject("tagModelForm", tagModelForm);
			return modelAndView;
		}

		if (tagModelForm.getParentId() <= 0) {
			tagManager.updateMainTag(tagModelForm.getId(), tagModelForm.getName());
		} else {
			tagManager.updateSubTag(tagModelForm.getId(), tagModelForm.getParentId(), tagModelForm.getName());
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
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/viewAddProduct", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView viewAddProduct() {

		List<ProductTagModel> mainTags = tagManager.getAllProductTags();
		List<ProductCategoryModel> mainCategories = categoryManager.getAllCategories();

		ModelAndView modelAndView = new ModelAndView("admin/product/AddProduct");
		modelAndView.addObject("categoryList", mainCategories);
		modelAndView.addObject("tagList", mainTags);

		modelAndView.addObject("productModelForm", new ProductModelForm());
		return modelAndView;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/submitAddProduct", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView submitAddProduct(@ModelAttribute("productModelForm") ProductModelForm productModelForm, BindingResult bindingResult)
			throws IOException {

		productModelForm.validate(bindingResult);

		if (bindingResult.hasErrors()) {
			List<ProductTagModel> mainTags = tagManager.getAllProductTags();
			List<ProductCategoryModel> mainCategories = categoryManager.getAllCategories();

			ModelAndView modelAndView = new ModelAndView("admin/product/AddProduct");
			modelAndView.addObject("categoryList", mainCategories);
			modelAndView.addObject("tagList", mainTags);
			modelAndView.addObject("productModelForm", productModelForm);
			return modelAndView;
		}

		int productId = productManager.addProduct(productModelForm);

		ModelAndView modelAndView =  new ModelAndView("admin/product/SetProductPhoto");
		modelAndView.addObject("productId", productId);
		return modelAndView;
	}


	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/uploadProductPhoto", method = RequestMethod.POST)
	public ModelAndView handleFormUpload(@RequestParam("productId")int productId, @RequestParam("file") MultipartFile file) throws IOException {

		if (file != null && !file.isEmpty()) {
			if (file.getContentType() == "image/JPEG") {
				productManager.setProductImage(productId,file.getBytes());
				ModelAndView modelAndView = new ModelAndView("redirect:viewManage");
				modelAndView.addObject("message", "Success - Successfully added the product");
				return modelAndView;
			}
		}

		ModelAndView modelAndView = new ModelAndView("admin/product/SetProductPhoto");
		modelAndView.addObject("message", "You need to choose a jpeg image");
		return modelAndView;
	}

	/**
	 * Add Publishing
	 * 
	 * @param user
	 * @return
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/viewAddPublishing", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView viewAddPublishing() {

		ModelAndView modelAndView = new ModelAndView("admin/publishing/AddPublishing");
		modelAndView.addObject("publishModelForm", new PublishModelForm());
		return modelAndView;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/submitAddPublishing", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView submitAddPublishing(@ModelAttribute("publishModelForm") PublishModelForm publishModelForm,
			BindingResult bindingResult) throws ParseException {

		publishModelForm.validate(bindingResult);

		if (bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("admin/publishing/AddPublishing");
			modelAndView.addObject("publishModelForm", publishModelForm);
			return modelAndView;
		}

		publishingManager.addPublishing(publishModelForm.getTitle(), publishModelForm.getDescription(), publishModelForm.getSubtitle(),
				DateUtils.parseDate(publishModelForm.getEventDate(), "dd/MM/yyyy"), publishModelForm.getSection());

		ModelAndView modelAndView = new ModelAndView("redirect:viewManage");
		modelAndView.addObject("message", "Success - Adding a new " + publishModelForm.getSection() + " Success");
		return modelAndView;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/searchPublishing", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView searchPublishing() {

		List<PublishRecordModel> publishings = publishingManager.getAllPublishingRecords();
		ModelAndView modelAndView = new ModelAndView("admin/publishing/FindPublishing");
		modelAndView.addObject("publishings", publishings);
		return modelAndView;

	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/editPublishing", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView editPublishing(@RequestParam("id") int id) throws Exception {

		PublishModelForm publishRecordModel = publishingManager.getPublishRecord(id);

		ModelAndView modelAndView = new ModelAndView("admin/publishing/EditPublishing");
		modelAndView.addObject("publishModelForm", publishRecordModel);
		return modelAndView;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/submitEditPublishing", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView submitEditPublishing(@ModelAttribute("publishModelForm") PublishModelForm publishModelForm,
			BindingResult bindingResult) throws ParseException {

		publishModelForm.validate(bindingResult);

		if (bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("admin/publishing/EditPublishing");
			modelAndView.addObject("publishModelForm", publishModelForm);
			return modelAndView;
		}

		publishingManager.updatePublishing(publishModelForm.getId(), publishModelForm.getTitle(), publishModelForm.getDescription(),
				publishModelForm.getSubtitle(), DateUtils.parseDate(publishModelForm.getEventDate(), "dd/MM/yyyy"),
				publishModelForm.getSection());

		ModelAndView modelAndView = new ModelAndView("redirect:viewManage");
		modelAndView.addObject("message", "Success - Updated " + publishModelForm.getSection() + " Success");
		return modelAndView;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/deletePublishing", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView deletePublishing(@RequestParam("id") int id) throws Exception {

		publishingManager.deletePublishing(id);

		ModelAndView modelAndView = new ModelAndView("redirect:viewManage");
		modelAndView.addObject("message", "Success - publishing deleted");
		return modelAndView;

	}

	/**
	 * 
	 * @return
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/viewAddCustomPage", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView viewAddCustomPage() {

		ModelAndView modelAndView = new ModelAndView("admin/customPages/AddCustomPage");
		modelAndView.addObject("customPageModelForm", new CustomPageModelForm());

		return modelAndView;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/submitAddCustomPage", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView submitAddCustomPage(@ModelAttribute("customPageModelForm") CustomPageModelForm customPageModelForm,
			BindingResult bindingResult) throws ParseException {

		customPageModelForm.validate(bindingResult);

		if (bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("admin/customPages/AddCustomPage");
			modelAndView.addObject("customPageModelForm", customPageModelForm);
			return modelAndView;
		}

		customPageManager.addCustomPage(customPageModelForm.getTitle(), customPageModelForm.getPageName(),
				customPageModelForm.getDescription(), customPageModelForm.getSiteSpaceType());

		ModelAndView modelAndView = new ModelAndView("redirect:viewManage");
		modelAndView.addObject("message", "Success - Adding a new " + customPageModelForm.getPageName() + " Success");
		return modelAndView;
	}

}

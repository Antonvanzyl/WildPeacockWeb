package com.servlet.model.forms;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

public class ProductModelForm {

	private String title;
	private String subTitle;
	private String description;
	private BigDecimal price;

	private int categoryId;
	private int[] tagIds;

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

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int[] getTagIds() {
		return tagIds;
	}

	public void setTagIds(int[] tagIds) {
		this.tagIds = tagIds;
	}

	public void validate(BindingResult bindingResult) {

		if (StringUtils.isAlphanumericSpace(this.getTitle())) {
			bindingResult.rejectValue("title", "", "*Must be Alpha Numeric ");
		} else if (StringUtils.length(this.getTitle()) > 128) {
			bindingResult.rejectValue("title", "", "*Too Long (127 char max)");
		}

		if (StringUtils.isAlphanumericSpace(this.getSubTitle())) {
			bindingResult.rejectValue("subTitle", "", "*Must be Alpha Numeric ");
		} else if (StringUtils.length(this.getSubTitle()) > 64) {
			bindingResult.rejectValue("subTitle", "", "*Too Long (64 char max)");
		}

		if (StringUtils.isEmpty(this.getDescription())) {
			bindingResult.rejectValue("description", "", "*Required");
		} else if (StringUtils.length(this.getDescription()) > 4096) {
			bindingResult.rejectValue("description", "", "*Too Long (4096 char max)");
		}

		if (this.getPrice() == null || BigDecimal.ZERO.compareTo(this.getPrice()) > 0) {
			bindingResult.rejectValue("price", "", "*Required");
		}

		if (this.getCategoryId() <= 0) {
			bindingResult.rejectValue("categoryId", "", "*Required");
		}

		if (this.getTagIds() == null || this.getTagIds().length == 0) {
			bindingResult.rejectValue("tagIds", "", "*Required");
		}

	}

}

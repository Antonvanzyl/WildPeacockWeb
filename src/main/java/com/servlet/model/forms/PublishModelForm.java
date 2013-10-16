package com.servlet.model.forms;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;

import com.types.PublishingSectionType;

public class PublishModelForm {

	private String title;
	private String subtitle;
	private String description;
	private Date eventDate;
	private PublishingSectionType section;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public PublishingSectionType getSection() {
		return section;
	}

	public void setSection(PublishingSectionType section) {
		this.section = section;
	}

	public void validate(BindingResult bindingResult) {
		if (StringUtils.isEmpty(this.getTitle())) {
			bindingResult.rejectValue("title", "", "*Required");
		} else if (StringUtils.length(this.getTitle()) > 128) {
			bindingResult.rejectValue("title", "", "*Too Long (127 char max)");
		}

		if (StringUtils.isEmpty(this.getSubtitle())) {
			bindingResult.rejectValue("subtitle", "", "*Required");
		} else if (StringUtils.length(this.getSubtitle()) > 64) {
			bindingResult.rejectValue("subtitle", "", "*Too Long (64 char max)");
		}

		if (StringUtils.isEmpty(this.getDescription())) {
			bindingResult.rejectValue("description", "", "*Required");
		} else if (StringUtils.length(this.getDescription()) > 2048) {
			bindingResult.rejectValue("description", "", "*Too Long (2048 char max)");
		}

		if (eventDate == null) {
			bindingResult.rejectValue("eventDate", "", "*Required");
		}

	}

}

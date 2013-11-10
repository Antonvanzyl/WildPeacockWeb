package com.servlet.model.forms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.validation.BindingResult;

import com.types.PublishingSectionType;

public class PublishModelForm {

	private int id;
	private String title;
	private String subtitle;
	private String description;
	private String eventDate;
	private PublishingSectionType section;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getEventDate() {
		if (StringUtils.isEmpty(eventDate)) {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			eventDate = format.format(new Date());
		}

		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public PublishingSectionType getSection() {
		return section;
	}

	public void setSection(PublishingSectionType section) {
		this.section = section;
	}

	public void validate(BindingResult bindingResult) {
		if (StringUtils.isAlphanumericSpace(this.getTitle())) {
			bindingResult.rejectValue("title", "", "*Must be Alpha Numeric ");
		} else if (StringUtils.length(this.getTitle()) > 128) {
			bindingResult.rejectValue("title", "", "*Too Long (127 char max)");
		}

		if (StringUtils.isAlphanumericSpace(this.getSubtitle())) {
			bindingResult.rejectValue("subtitle", "", "*Must be Alpha Numeric ");
		} else if (StringUtils.length(this.getSubtitle()) > 64) {
			bindingResult.rejectValue("subtitle", "", "*Too Long (64 char max)");
		}

		if (StringUtils.isEmpty(eventDate)) {
			bindingResult.rejectValue("eventDate", "", "*Required");
		} else {
			try {
				DateUtils.parseDate(eventDate, "dd/MM/yyyy");
			} catch (ParseException e) {
				bindingResult.rejectValue("eventDate", "", "Must be dd/MM/yyyy format");
			}
		}

		if (StringUtils.isEmpty(this.getDescription())) {
			bindingResult.rejectValue("description", "", "*Required");
		} else if (StringUtils.length(this.getDescription()) > 4096) {
			bindingResult.rejectValue("description", "", "*Too Long (4096 char max)");
		}

		if (eventDate == null) {
			bindingResult.rejectValue("eventDate", "", "*Required");
		}

	}

}

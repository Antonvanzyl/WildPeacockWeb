/**
 * File: PublishRecord.java
 * Date: 09 Sep 2013
 * Author: Anton Van Zyl
 */
package com.servlet.model;

import java.util.Date;

import com.types.PublishingSectionType;

/**
 * @author Anton Van Zyl
 * 
 */
public class PublishRecordModel {

	private int id;
	private String title;
	private String subtitle;
	private String description;
	private Date eventDate;
	private PublishingSectionType publishingSectionType;

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

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public PublishingSectionType getPublishingSectionType() {
		return publishingSectionType;
	}
	
	public void setPublishingSectionType(PublishingSectionType publishingSectionType) {
		this.publishingSectionType = publishingSectionType;
	}
	
}

/**
 * 
 */
package com.entity.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author CP311133
 * 
 */
@Entity
@Table(name = "Publishing")
public class Publishing implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private Date created;
	private String title;
	private String subtitle;
	private String description;
	private Date eventDate;
	private String section;
	private int readCount;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false, length = 23)
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Column(name = "title", nullable = false, length = 128)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "subtitle", nullable = false, length = 64)
	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	@Column(name = "description", nullable = false, length = 2048)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "eventDate", nullable = false, length = 23)
	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	@Column(name = "section", nullable = false, length = 50)
	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	@Column(name = "read_Count", nullable = false)
	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

}

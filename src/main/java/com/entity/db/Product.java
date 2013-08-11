/**
 * 
 */
package com.entity.db;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author CP311133
 * 
 */
@Entity
@Table(name = "Product")
public class Product implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private Date created;
	private String title;
	private String subTitle;
	private String description;
	private Blob photoURL;
	private BigDecimal price;
	private String display;
	private Category category;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

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

	public Blob getPhotoURL() {
		return photoURL;
	}

	public void setPhotoURL(Blob photoURL) {
		this.photoURL = photoURL;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}

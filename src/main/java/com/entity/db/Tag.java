/**
 * 
 */
package com.entity.db;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author CP311133
 * 
 */
@Entity
@Table(name = "Product")
public class Tag implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private Tag tag;
	private String name;
	private Date created;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}

/**
 * 
 */
package com.entity.db;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author CP311133
 * 
 */
@Entity
@Table(name = "Category")
public class Category implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private Category parentCategory;
	private String name;
	private String description;
	private Date created;
	private Set<Category> categories = new HashSet<Category>();

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentId", nullable = false)
	public Category getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}

	@Column(name = "name", nullable = false, length = 128)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", nullable = false, length = 128)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false, length = 23)
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parentCategory")
	public Set<Category> getCategories() {
		return categories;
	}
	
	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

}

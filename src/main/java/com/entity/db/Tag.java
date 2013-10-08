package com.entity.db;

// Generated 08 Oct 2013 7:47:57 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Tag generated by hbm2java
 */
@Entity
@Table(name = "Tag", schema = "dbo", catalog = "WildPeacock")
public class Tag implements java.io.Serializable {

	private int id;
	private Tag tag;
	private String name;
	private Date created;
	private Set<Tag> tags = new HashSet<Tag>(0);
	private Set<ProductTags> productTagses = new HashSet<ProductTags>(0);

	public Tag() {
	}

	public Tag(int id, Tag tag, String name, Date created) {
		this.id = id;
		this.tag = tag;
		this.name = name;
		this.created = created;
	}

	public Tag(int id, Tag tag, String name, Date created, Set<Tag> tags, Set<ProductTags> productTagses) {
		this.id = id;
		this.tag = tag;
		this.name = name;
		this.created = created;
		this.tags = tags;
		this.productTagses = productTagses;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tagId", nullable = false)
	public Tag getTag() {
		return this.tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	@Column(name = "name", nullable = false, length = 64)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false, length = 23)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tag")
	public Set<Tag> getTags() {
		return this.tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tag")
	public Set<ProductTags> getProductTagses() {
		return this.productTagses;
	}

	public void setProductTagses(Set<ProductTags> productTagses) {
		this.productTagses = productTagses;
	}

}

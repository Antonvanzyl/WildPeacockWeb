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
public class ProductTags implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Product product;
	private Tag tag;
	private Date created;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}

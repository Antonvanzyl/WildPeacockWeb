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
@Table(name = "SystemAudit")
public class SystemAudit implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private int authorityId;
	private Date inserted;
	private String action;
	private String description;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "authorityId", nullable = false)
	public int getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(int authorityId) {
		this.authorityId = authorityId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "inserted", nullable = false, length = 23)
	public Date getInserted() {
		return inserted;
	}

	public void setInserted(Date inserted) {
		this.inserted = inserted;
	}

	@Column(name = "action", nullable = false, length = 50)
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Column(name = "description", nullable = false, length = 1024)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

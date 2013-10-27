/**
 * File: Tags.java
 * Date: 09 Sep 2013
 * Author: Anton Van Zyl
 */
package com.servlet.model;

/**
 * @author Anton Van Zyl
 * 
 */
public class ProductTagMenuModel {

	private int tagId;
	private int parentTagId;
	private String tagTitle;

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	public String getTagTitle() {
		return tagTitle;
	}

	public void setTagTitle(String tagTitle) {
		this.tagTitle = tagTitle;
	}

	public int getParentTagId() {
		return parentTagId;
	}

	public void setParentTagId(int parentTagId) {
		this.parentTagId = parentTagId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + parentTagId;
		result = prime * result + tagId;
		result = prime * result + ((tagTitle == null) ? 0 : tagTitle.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductTagMenuModel other = (ProductTagMenuModel) obj;
		if (parentTagId != other.parentTagId)
			return false;
		if (tagId != other.tagId)
			return false;
		if (tagTitle == null) {
			if (other.tagTitle != null)
				return false;
		} else if (!tagTitle.equals(other.tagTitle))
			return false;
		return true;
	}

}

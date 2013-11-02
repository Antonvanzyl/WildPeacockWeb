package com.manager;

import java.util.List;
import java.util.Map;

import com.entity.db.Tag;
import com.servlet.model.ProductTagMenuModel;
import com.servlet.model.ProductTagModel;
import com.servlet.model.forms.TagModelForm;

public interface TagManager {
	
	public void addMainTag(String tagName);

	public void addSubTag(int parentTag, String tagName) throws Exception;
	
	List<ProductTagModel> getMainProductTags();

	public List<ProductTagModel> getAllProductTags();
	
	public TagModelForm getTags(int tagId) throws Exception;

	public void updateMainTag(int id, String name) throws Exception;

	public void updateSubTag(int id, int parentId, String name) throws Exception;

	public Map<ProductTagMenuModel, List<ProductTagMenuModel>> getAllMenuProductTags();
	
	public void refreshTags();

	public Tag findById(int tagId);

}

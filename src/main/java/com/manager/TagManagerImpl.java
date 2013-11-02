package com.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dao.db.TagDao;
import com.entity.db.Tag;
import com.servlet.model.ProductTagMenuModel;
import com.servlet.model.ProductTagModel;
import com.servlet.model.forms.TagModelForm;

@Transactional
@Component
public class TagManagerImpl implements TagManager {

	@Autowired
	private TagDao tagDao;

	private final Object lock = new Object();
	private final Map<ProductTagMenuModel, List<ProductTagMenuModel>> memoryTags = new HashMap<ProductTagMenuModel, List<ProductTagMenuModel>>();

	@Override
	public Map<ProductTagMenuModel, List<ProductTagMenuModel>> getAllMenuProductTags() {

		final Map<ProductTagMenuModel, List<ProductTagMenuModel>> tempTags = new HashMap<ProductTagMenuModel, List<ProductTagMenuModel>>();

		synchronized (lock) {
			tempTags.putAll(memoryTags);
		}
		return tempTags;
	}

	@Override
	public List<ProductTagModel> getMainProductTags() {
		List<Tag> tags = tagDao.getAllMainTags();

		List<ProductTagModel> returnTags = new ArrayList<ProductTagModel>();
		for (Tag tag : tags) {

			ProductTagModel productTagModel = new ProductTagModel();
			productTagModel.setTagId(tag.getId());
			productTagModel.setTagTitle(tag.getName());

			returnTags.add(productTagModel);
		}

		return returnTags;
	}

	@Override
	public List<ProductTagModel> getAllProductTags() {
		List<Tag> tags = tagDao.getAllTags();

		List<ProductTagModel> returnTags = new ArrayList<ProductTagModel>();
		for (Tag tag : tags) {

			ProductTagModel productTagModel = new ProductTagModel();
			productTagModel.setTagId(tag.getId());
			productTagModel.setTagTitle(tag.getName());

			returnTags.add(productTagModel);
		}

		return returnTags;
	}

	@Override
	public TagModelForm getTags(int tagId) throws Exception {

		Tag tag = tagDao.findById(tagId);
		if (tag == null) {
			throw new Exception("Cannot add a tag to a sub tag");
		}

		TagModelForm modelForm = new TagModelForm();

		modelForm.setId(tag.getId());
		modelForm.setName(tag.getName());
		if (tag.getTag() != null) {
			modelForm.setParentId(tag.getTag().getId());
		}

		return modelForm;
	}

	@Override
	public void addMainTag(String tagName) {

		Tag tag = new Tag();
		tag.setCreated(new Date());
		tag.setName(tagName);
		tagDao.merge(tag);

	}

	@Override
	public void addSubTag(int parentTagId, String tagName) throws Exception {

		Tag parentTag = tagDao.findById(parentTagId);
		if (parentTag.getTag() != null) {
			throw new Exception("Cannot add a tag to a sub tag");
		}

		Tag tag = new Tag();
		tag.setCreated(new Date());
		tag.setName(tagName);
		tag.setTag(parentTag);
		tagDao.merge(tag);

	}

	@Override
	public void updateMainTag(int id, String name) throws Exception {

		Tag parentTag = tagDao.findById(id);
		if (parentTag == null) {
			throw new Exception("Tag does not exist");
		}
		parentTag.setName(name);
		tagDao.merge(parentTag);

	}

	@Override
	public void updateSubTag(int id, int parentId, String name) throws Exception {

		Tag currentTag = tagDao.findById(id);
		if (currentTag == null) {
			throw new Exception("Tag does not exist");
		}

		Tag parentTag = tagDao.findById(parentId);
		if (parentTag.getTag() != null) {
			throw new Exception("Cannot add a tag to a sub tag");
		}

		currentTag.setTag(parentTag);
		currentTag.setName(name);
		tagDao.merge(currentTag);

	}

	@Override
	public Tag findById(int tagId) {
		return tagDao.findById(tagId);
	}

	@Override
	public void refreshTags() {
		List<Tag> tags = tagDao.getAllMainTags();

		final Map<ProductTagMenuModel, List<ProductTagMenuModel>> tempTags = new HashMap<ProductTagMenuModel, List<ProductTagMenuModel>>();

		for (Tag tag : tags) {

			ProductTagMenuModel productTagModel = new ProductTagMenuModel();
			productTagModel.setTagId(tag.getId());
			productTagModel.setTagTitle(tag.getName());

			List<Tag> subTags = tagDao.getAllSubTags(tag.getId());
			List<ProductTagMenuModel> subTagsModels = new ArrayList<ProductTagMenuModel>();

			for (Tag subTag : subTags) {
				ProductTagMenuModel productSubTagModel = new ProductTagMenuModel();
				productSubTagModel.setTagId(subTag.getId());
				productSubTagModel.setTagTitle(subTag.getName());
				productSubTagModel.setParentTagId(subTag.getId());
				subTagsModels.add(productSubTagModel);
			}

			tempTags.put(productTagModel, subTagsModels);
		}

		synchronized (lock) {
			memoryTags.clear();
			memoryTags.putAll(tempTags);
		}

	}

}

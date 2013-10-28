/**
 * File: PublishingManagerImpl.java
 * Date: 09 Sep 2013
 * Author: Anton Van Zyl
 */
package com.manager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dao.db.PublishingDao;
import com.entity.db.Publishing;
import com.servlet.model.PublishRecordModel;
import com.servlet.model.forms.PublishModelForm;
import com.types.PublishingSectionType;

/**
 * @author Anton Van Zyl
 * 
 */
@Component
@Transactional
public class PublishingManagerImpl implements PublishingManager {

	@Autowired
	private PublishingDao publishingDao;

	private final Object lock = new Object();
	private final List<PublishRecordModel> memoryBlogPublishings = new ArrayList<PublishRecordModel>();
	private final List<PublishRecordModel> memoryNewsPublishings = new ArrayList<PublishRecordModel>();

	@Override
	public List<PublishRecordModel> getPublishingRecords(PublishingSectionType publishingSectionType, int startIndex, int count) {

		List<PublishRecordModel> returnList = new ArrayList<PublishRecordModel>();

		final List<PublishRecordModel> tempList = new ArrayList<PublishRecordModel>();
		synchronized (lock) {
			if (publishingSectionType == PublishingSectionType.BLOG) {
				tempList.addAll(memoryBlogPublishings);
			} else {
				tempList.addAll(memoryNewsPublishings);
			}
		}

		int index = 0;

		for (PublishRecordModel publishing : tempList) {
			if (index >= startIndex && index < (startIndex + count)) {
				returnList.add(publishing);
			}
			index++;
		}

		return returnList;
	}

	@Override
	public int getBlogSize() {
		synchronized (lock) {
			return memoryBlogPublishings.size();
		}
	}

	@Override
	public int getNewsSize() {
		synchronized (lock) {
			return memoryNewsPublishings.size();
		}
	}

	@Override
	public void loadPublishings() {

		final List<PublishRecordModel> tempBlogPublishings = new ArrayList<PublishRecordModel>();
		final List<PublishRecordModel> tempNewsPublishings = new ArrayList<PublishRecordModel>();

		List<Publishing> records = publishingDao.getAllRecords();
		for (Publishing publishing : records) {
			if (publishing.getSection() == PublishingSectionType.BLOG) {
				tempBlogPublishings.add(convert(publishing));
			} else {
				tempNewsPublishings.add(convert(publishing));
			}
		}

		synchronized (lock) {
			memoryBlogPublishings.clear();
			memoryNewsPublishings.clear();
			memoryBlogPublishings.addAll(tempBlogPublishings);
			memoryNewsPublishings.addAll(tempNewsPublishings);
		}

	}

	private PublishRecordModel convert(Publishing publishing) {

		if (publishing == null) {
			return null;
		}

		PublishRecordModel model = new PublishRecordModel();

		model.setDescription(publishing.getDescription());
		model.setEventDate(publishing.getEventDate());
		model.setId(publishing.getId());
		model.setSubtitle(publishing.getSubtitle());
		model.setTitle(publishing.getTitle());
		model.setPublishingSectionType(publishing.getSection());
		return model;
	}

	private PublishModelForm convertPublishModelForm(Publishing publishing) {

		if (publishing == null) {
			return null;
		}

		PublishModelForm model = new PublishModelForm();

		model.setDescription(publishing.getDescription());

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		model.setEventDate(format.format(publishing.getEventDate()));
		model.setId(publishing.getId());
		model.setSubtitle(publishing.getSubtitle());
		model.setTitle(publishing.getTitle());
		model.setSection(publishing.getSection());
		return model;
	}

	@Override
	public PublishModelForm getPublishRecord(int id) {

		Publishing publishing = publishingDao.findById(id);
		publishing.setReadCount(publishing.getReadCount() + 1);
		publishingDao.merge(publishing);
		return convertPublishModelForm(publishing);
	}

	@Override
	public PublishRecordModel getPublishRecordAndAddReadCount(int id) {

		Publishing publishing = publishingDao.findById(id);
		publishing.setReadCount(publishing.getReadCount() + 1);
		publishingDao.merge(publishing);
		return convert(publishing);
	}

	@Override
	public void addPublishing(String Title, String Description, String subTitle, Date eventDate, PublishingSectionType publishingSectionType) {
		Publishing publishing = new Publishing();
		publishing.setTitle(Title);
		publishing.setSubtitle(subTitle);
		publishing.setSection(publishingSectionType);
		publishing.setDescription(Description);
		publishing.setReadCount(0);
		publishing.setCreated(new Date());
		publishing.setEventDate(eventDate);

		publishingDao.merge(publishing);

		loadPublishings();
	}

	@Override
	public void updatePublishing(int id, String title, String description, String subtitle, Date eventDate, PublishingSectionType section) {

		Publishing publishing = publishingDao.findById(id);
		publishing.setTitle(title);
		publishing.setSubtitle(subtitle);
		publishing.setSection(section);
		publishing.setDescription(description);
		publishing.setEventDate(eventDate);

		publishingDao.merge(publishing);

		loadPublishings();
	}

	@Override
	public List<PublishRecordModel> getAllPublishingRecords() {

		List<PublishRecordModel> returnList = new ArrayList<PublishRecordModel>();

		synchronized (lock) {
			returnList.addAll(memoryBlogPublishings);
			returnList.addAll(memoryNewsPublishings);
		}

		return returnList;
	}

	@Override
	public void deletePublishing(int id) throws Exception {
		Publishing record = publishingDao.findById(id);

		if (record == null) {
			throw new Exception("Publishing does not exist");
		}

		publishingDao.delete(record);

		loadPublishings();
	}

}

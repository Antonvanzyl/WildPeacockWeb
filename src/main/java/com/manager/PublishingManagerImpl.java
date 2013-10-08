/**
 * File: PublishingManagerImpl.java
 * Date: 09 Sep 2013
 * Author: Anton Van Zyl
 */
package com.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dao.db.PublishingDao;
import com.entity.db.Publishing;
import com.servlet.model.PublishRecordModel;
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

	@Override
	public List<PublishRecordModel> getPublishingRecords(PublishingSectionType publishingSectionType, int startIndex, int count) {

		List<PublishRecordModel> returnList = new ArrayList<PublishRecordModel>();

		List<Publishing> records = publishingDao.getRecords(publishingSectionType, startIndex, count);
		for (Publishing publishing : records) {
			returnList.add(convert(publishing));
		}

		return returnList;
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

		return model;
	}

	@Override
	public PublishRecordModel getPublishRecord(int id) {

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
	}

}

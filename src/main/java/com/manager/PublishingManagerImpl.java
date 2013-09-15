/**
 * File: PublishingManagerImpl.java
 * Date: 09 Sep 2013
 * Author: Anton Van Zyl
 */
package com.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.servlet.model.PublishRecord;
import com.types.PublishingSectionType;

/**
 * @author Anton Van Zyl
 * 
 */
@Component
@Transactional
public class PublishingManagerImpl implements PublishingManager {

	@Override
	public List<PublishRecord> getPublishingRecords(PublishingSectionType publishingSectionType, int startIndex, int count) {

		List<PublishRecord> returnList = new ArrayList<PublishRecord>();

		for (int x = 0; x < count; x++) {
			returnList.add(mock());
		}

		return returnList;
	}

	@Override
	public PublishRecord getPublishRecord(int id) {
		return mock();
	}

	private PublishRecord mock() {
		PublishRecord publishRecord = new PublishRecord();
		publishRecord
				.setDescription("This saadadas daassd sdsf sf sddsdf sf sddsd f sf sf dssdsdfdfsddsf dsdfkjsdf kjsddf jsdhf sdhfkh sdfkjhsddjkf hdskfkhdsfhh gajhj iahdsaijhd ajklhiadklahsihd hajdijaijd oahd iuasoidj asiuhdoia idhasiod juioash diu ajsiodh iyasdio asisudhoi");
		publishRecord.setEventDate(new Date());
		publishRecord.setId(1);
		publishRecord.setSubtitle("This is the sub Title");
		publishRecord.setTitle("this is the title");
		return publishRecord;
	}
}

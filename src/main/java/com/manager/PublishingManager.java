package com.manager;

import java.util.Date;
import java.util.List;

import com.servlet.model.PublishRecordModel;
import com.types.PublishingSectionType;

public interface PublishingManager {

	List<PublishRecordModel> getPublishingRecords(PublishingSectionType publishingSectionType, int startIndex, int count);

	PublishRecordModel getPublishRecord(int id);

	void addPublishing(String Title, String Description, String subTitle, Date eventDate, PublishingSectionType publishingSectionType);

}

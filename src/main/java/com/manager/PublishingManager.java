package com.manager;

import java.util.Date;
import java.util.List;

import com.servlet.model.PublishRecordModel;
import com.servlet.model.forms.PublishModelForm;
import com.types.PublishingSectionType;

public interface PublishingManager {

	List<PublishRecordModel> getPublishingRecords(PublishingSectionType publishingSectionType, int startIndex, int count);

	PublishModelForm getPublishRecord(int id);

	void addPublishing(String Title, String Description, String subTitle, Date eventDate, PublishingSectionType publishingSectionType);

	List<PublishRecordModel> getAllPublishingRecords();

	void deletePublishing(int id) throws Exception;

	void updatePublishing(int id, String title, String description, String subtitle, Date eventDate, PublishingSectionType section);

	PublishRecordModel getPublishRecordAndAddReadCount(int id);

}

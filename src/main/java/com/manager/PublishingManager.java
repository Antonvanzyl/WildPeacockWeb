package com.manager;

import java.util.List;

import com.servlet.model.PublishRecord;
import com.types.PublishingSectionType;

public interface PublishingManager {
 
	List<PublishRecord> getPublishingRecords(PublishingSectionType publishingSectionType, int startIndex, int count);
	
	void addReadCount(int id);

}

/**
 * File: PublishingManagerImpl.java
 * Date: 09 Sep 2013
 * Author: Anton Van Zyl
 */
package com.manager;

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

		return null;
	}

	@Override
	public void addReadCount(int id) {

	}

}

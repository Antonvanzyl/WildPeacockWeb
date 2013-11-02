/**
 * File: CustomPageManagerImpl.java
 * Date: 02 Nov 2013
 * Author: Anton Van Zyl
 */
package com.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dao.db.CustomPageDao;
import com.entity.db.CustomPages;
import com.servlet.model.CustomPageModel;
import com.types.SiteSpaceType;
import com.util.CustomLinks;

/**
 * @author Anton Van Zyl
 * 
 */
@Transactional
@Component
public class CustomPageManagerImpl implements CustomPageManager {

	@Autowired
	private CustomPageDao customPageDao;

	private final Object lock = new Object();
	private final List<CustomPageModel> memoryPages = new ArrayList<CustomPageModel>();

	@Override
	public CustomPageModel getCustomPage(int id) {

		final List<CustomPageModel> tempPages = new ArrayList<CustomPageModel>();

		synchronized (lock) {
			tempPages.addAll(memoryPages);
		}

		for (CustomPageModel customPageModel : tempPages) {
			if (customPageModel.getId() == id) {
				return customPageModel;
			}
		}

		return null;
	}

	@Override
	public void addCustomPage(String title, String pageName, String description, SiteSpaceType siteSpaceType) {
		CustomPages cp = new CustomPages();

		cp.setTitle(title);
		cp.setPageName(pageName);
		cp.setDescription(description);
		cp.setSiteSpaceType(siteSpaceType);
		cp.setInserted(new Date());

		customPageDao.merge(cp);

	}

	@Override
	public void updateCustomPage(int id, String title, String pageName, String description, SiteSpaceType siteSpaceType) {

		CustomPages cp = customPageDao.findById(id);

		cp.setTitle(title);
		cp.setPageName(pageName);
		cp.setDescription(description);
		cp.setSiteSpaceType(siteSpaceType);

		customPageDao.merge(cp);
	}

	@Override
	public void deleteCustomPage(int id) {

		CustomPages cp = customPageDao.findById(id);
		customPageDao.delete(cp);

	}

	@Override
	public void refreshCustomPages() {
		final List<CustomPageModel> tempPages = new ArrayList<CustomPageModel>();

		List<CustomPages> customPages = customPageDao.getAllCustomPages();

		for (CustomPages customPage : customPages) {
			CustomPageModel customPageModel = new CustomPageModel();
			customPageModel.setId(customPage.getId());
			customPageModel.setTitle(customPage.getTitle());
			customPageModel.setPageName(customPage.getPageName());
			customPageModel.setDescription(customPage.getDescription());
			customPageModel.setSiteSpaceType(customPage.getSiteSpaceType());
			tempPages.add(customPageModel);
		}

		synchronized (lock) {
			memoryPages.clear();
			memoryPages.addAll(tempPages);
			CustomLinks.loadRetailLinks(memoryPages);
		}

	}

}

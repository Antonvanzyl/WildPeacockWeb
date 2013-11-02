package com.manager;

import com.servlet.model.CustomPageModel;
import com.types.SiteSpaceType;

public interface CustomPageManager {

	void addCustomPage(String title, String pageName, String description, SiteSpaceType siteSpaceType);

	void updateCustomPage(int id, String title, String pageName, String description, SiteSpaceType siteSpaceType);

	void deleteCustomPage(int id);

	void refreshCustomPages();

	CustomPageModel getCustomPage(int id);

}

/**
 * File: ProductLoadTask.java
 * Date: 04 Oct 2013
 * Author: Anton Van Zyl
 */
package com.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.manager.CustomPageManager;
import com.manager.ProductManager;
import com.manager.PublishingManager;
import com.util.ResourceLoaderTask;

/**
 * @author Anton Van Zyl
 * 
 */
public class ProductLoadTask extends ResourceLoaderTask {

	private static final Logger log = LoggerFactory.getLogger(ProductLoadTask.class);
	@Autowired
	private ProductManager productManager;
	
	@Autowired
	private PublishingManager publishingManager;
	
	@Autowired
	private CustomPageManager customPageManager;

	@Override
	protected void loadResource() throws Exception {
		log.info("Loading Products");
		productManager.refreshProducts();
		publishingManager.loadPublishings();
		customPageManager.refreshCustomPages();
	}

}

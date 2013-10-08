package com.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * This class is intended to be used with managers that have to cache resources obtained from a local source. This class will do the initial load of
 * the resource. The derived task will also use this to refresh the resource.
 * 
 * @author Anton Van Zyl
 * 
 */
public abstract class ResourceLoaderTask implements InitializingBean {

	private static final Logger log = LoggerFactory.getLogger(ResourceLoaderTask.class);

	protected void startResourceLoading() {
		try {
			loadResource();
		} catch (Exception e) {
			log.warn("Failed to load resource [error={}]", e);
		}
	}

	protected abstract void loadResource() throws Exception;

	/**
	 * Can be sub-classed to change the retry period
	 */
	protected long getPeriod() {
		return 30000L;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		startResourceLoading();
	}
}

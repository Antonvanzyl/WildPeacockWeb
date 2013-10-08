package com.util;

import org.jasypt.encryption.pbe.config.SimplePBEConfig;
import org.springframework.beans.factory.InitializingBean;

/**
 * Simple class that sets the base encryption parameters
 * 
 * @author Gary Plante (cp312084)
 * 
 */
public class JasyptPBEConfig extends SimplePBEConfig implements InitializingBean {

	public final static String PASS_PHRASE = "WildP3@C0ck";

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {

		this.setPassword(PASS_PHRASE);
		this.setAlgorithm("PBEWithMD5AndDES");
	}
}

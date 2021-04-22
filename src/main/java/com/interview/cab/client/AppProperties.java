package com.interview.cab.client;

import java.io.FileInputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.interview.cab.client.exception.InvalidInputException;

public class AppProperties {

	private static AppProperties INSTANCE;

	private static final Logger logger = LoggerFactory.getLogger(AppProperties.class);
	
	private Properties appProps;

	public static AppProperties getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new AppProperties();
		}

		return INSTANCE;
	}

	public void load() throws Exception {
		String appConfigPath = "app.properties";

		appProps = new Properties();
		try {
			appProps.load(new FileInputStream(appConfigPath));
		} catch (Exception e) {
			logger.error("Error occurred while reading app.properties", e);
			throw new InvalidInputException("Error occurred while reading app.propertiess");
		}
	}
	
	public String getProperty(String propName) {
		return appProps.getProperty(propName);
	}

}

package com.interview.cab.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.interview.cab.client.app.CabClientApp;
import com.interview.cab.client.exception.InvalidInputException;

/**
 * Util class
 *
 * @author Krupa N
 */
public class Utils {
	
	private static final Logger logger = LoggerFactory.getLogger(CabClientApp.class);
	
	public static boolean parseUseCacheFlag(String arg) throws InvalidInputException {
		try {
			if(!(arg.equalsIgnoreCase("true") || !arg.equalsIgnoreCase("false"))) {
				throw new InvalidInputException("Invalid value provided for cache flag. It should be true/false");
			}
			return Boolean.parseBoolean(arg.trim());
		} catch (Exception e) {
			logger.error("Error occurred while parsing the use-cache flag", e);
			throw new InvalidInputException("Error occurred while parsing the -cache flag!!");
		}
	}
}

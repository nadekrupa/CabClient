package com.interview.cab.client;

import java.io.IOException;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.interview.cab.client.exception.FailureResponseException;
import com.interview.cab.client.exception.InvalidInputException;
import com.interview.cab.client.http.CustomHttpClient;
import com.interview.cab.request.TripRequest;
import com.interview.cab.response.ClearCacheResponse;
import com.interview.cab.response.TripResponse;

/**
 * CabClient implementation
 *
 * @author Krupa Nade
 */
public class CabClientImpl implements CabClient {

	private static CustomHttpClient httpClient = new CustomHttpClient();

	private static final Logger logger = LoggerFactory.getLogger(CabClientImpl.class);

	@Override
	public TripResponse getTripInfo(TripRequest tripRequest, boolean useCache)
			throws FailureResponseException, InvalidInputException {
		try {
			AppProperties instance = AppProperties.getInstance();
			String host = instance.getProperty("cabService.host");
			String port = instance.getProperty("cabService.port");
			
			return httpClient.sendPost("http://" + host + ":" + port + instance.getProperty("cabService.fetchTripInfoApi")
					+ "?useCache=" + useCache, tripRequest, Collections.emptyMap(), TripResponse.class);
		} catch (IOException e) {
			logger.error("Error occurred calling remote API", e);
			throw new InvalidInputException(
					"Error occurred calling remote API. Please make sure that the service is up!");
		}
	}

	@Override
	public ClearCacheResponse clearCache() throws FailureResponseException, InvalidInputException {
		try {
			AppProperties instance = AppProperties.getInstance();
			String host = instance.getProperty("cabService.host");
			String port = instance.getProperty("cabService.port");

			return httpClient.sendGet("http://" + host + ":" + port + instance.getProperty("cabService.clearCacheApi"),
					Collections.emptyMap(), ClearCacheResponse.class);
		} catch (IOException e) {
			logger.error("Error occurred calling clear cache remot API", e);
			throw new InvalidInputException(
					"Error occurred calling clear cache remote API. Please make sure that the service is up!");
		}
	}

}

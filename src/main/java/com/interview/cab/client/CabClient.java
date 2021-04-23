package com.interview.cab.client;

import com.interview.cab.client.exception.FailureResponseException;
import com.interview.cab.client.exception.InvalidInputException;
import com.interview.cab.request.TripRequest;
import com.interview.cab.response.ClearCacheResponse;
import com.interview.cab.response.TripResponse;

/**
 * The client interface
 *
 * @author Krupa Nade
 */
public interface CabClient {
	
	TripResponse getTripInfo(TripRequest tripRequest, boolean useCache) throws FailureResponseException, InvalidInputException;
	
	ClearCacheResponse clearCache() throws FailureResponseException, InvalidInputException;

}

package com.interview.cab.request;

import java.util.ArrayList;
import java.util.List;

/**
 *  Class representing request body of /1/fetch/trip-info api
 *
 * @author Krupa N
 */
public class TripRequest {
	private List<TripResuestInfo> tripRequests = new ArrayList<TripResuestInfo>();

	public List<TripResuestInfo> getTripRequests() {
		return tripRequests;
	}

	public void setTripRequests(List<TripResuestInfo> tripRequests) {
		this.tripRequests = tripRequests;
	}
}

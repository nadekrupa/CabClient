package com.interview.cab.response;

import java.util.ArrayList;
import java.util.List;

/**
 *  Class representing response of /1/fetch/trip-info api
 *
 * @author Krupa N
 */
public class TripResponse {
	private List<TripInfo> tripInfos = new ArrayList<>();

	public List<TripInfo> getTripInfos() {
		return tripInfos;
	}

	public void setTripInfos(List<TripInfo> tripInfos) {
		this.tripInfos = tripInfos;
	}
}

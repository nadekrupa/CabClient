package com.interview.cab.response;

/**
 *  Class representing trip info in response of /1/fetch/trip-info api
 *
 * @author Krupa N
 */
public class TripInfo {
	private String cabId;
	private String pickupDate;
	private int tripCount;

	public String getCabId() {
		return cabId;
	}

	public void setCabId(String cabId) {
		this.cabId = cabId;
	}

	public String getPickupDate() {
		return pickupDate;
	}

	public void setPickupDate(String pickupDate) {
		this.pickupDate = pickupDate;
	}

	public int getTripCount() {
		return tripCount;
	}

	public void setTripCount(int tripCount) {
		this.tripCount = tripCount;
	}

}

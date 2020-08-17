package com.interview.cab.response;

/**
 *  Class representing response of /1/cache/clear api
 *
 * @author Krupa N
 */
public class ClearCacheResponse {
	private String result;

	public ClearCacheResponse() {
	}

	public ClearCacheResponse(String result) {
		super();
		this.result = result;
	}
	

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}

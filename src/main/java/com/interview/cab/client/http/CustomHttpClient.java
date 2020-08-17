package com.interview.cab.client.http;

import static org.apache.commons.collections4.MapUtils.isNotEmpty;

import java.io.IOException;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.cab.client.exception.FailureResponseException;

/**
 * A wrapper class of Http client to send http requests
 *
 * @author Krupa N
 */
public class CustomHttpClient {

	private final CloseableHttpClient httpClient = HttpClients.createDefault();
	
	private final ObjectMapper objectMapper;
	
	private final Logger logger = LoggerFactory.getLogger(CustomHttpClient.class);

	public CustomHttpClient() {
		objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public void close() throws IOException {
		httpClient.close();
	}

	public <T extends Object> T sendGet(String url, Map<String, String> headers, Class<T> type) throws FailureResponseException, ClientProtocolException, IOException {
		HttpGet request = new HttpGet(url);

		// add request headers
		if (isNotEmpty(headers)) {
			headers.forEach((k, v) -> request.addHeader(k, v));
		}

		try (CloseableHttpResponse response = httpClient.execute(request)) {
			if (response.getStatusLine().getStatusCode() == 200) {
				String json = EntityUtils.toString(response.getEntity());
				return objectMapper.readValue(json, type);
			}  else {
				logger.info("Response code: {}", response.getStatusLine().getStatusCode());
				throw new FailureResponseException("Recieved failure response from remote server");
			}
		}
	}

	public <T extends Object> T sendPost(String url, Object payload, Map<String, String> headers, Class<T> type) throws FailureResponseException, ClientProtocolException, IOException {

		HttpPost post = new HttpPost(url);

		// add request headers
		if (isNotEmpty(headers)) {
			headers.forEach((k, v) -> post.addHeader(k, v));
		}

		String body = objectMapper.writeValueAsString(payload);
		post.setEntity(new StringEntity(body, ContentType.create("application/json", Consts.UTF_8)));

		try (CloseableHttpResponse response = httpClient.execute(post)) {

			if (response.getStatusLine().getStatusCode() == 200) {
				String json = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
				return objectMapper.readValue(json, type);
			} else {
				logger.info("Response code: {}", response.getStatusLine().getStatusCode());
				throw new FailureResponseException("Recieved failure response from remote server");
			}
		}
	}
}

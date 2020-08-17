package com.interview.cab.client.app;

import static com.interview.cab.client.Utils.parseUseCacheFlag;
import static org.apache.commons.lang3.StringUtils.startsWithIgnoreCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.interview.cab.client.AppProperties;
import com.interview.cab.client.CabClient;
import com.interview.cab.client.CabClientImpl;
import com.interview.cab.client.exception.FailureResponseException;
import com.interview.cab.client.exception.InvalidInputException;
import com.interview.cab.client.parser.CsvFileParser;
import com.interview.cab.request.TripRequest;
import com.interview.cab.response.TripInfo;
import com.interview.cab.response.TripResponse;

/**
 * Main class to launch the client
 *
 * @author Krupa N
 */
public class CabClientApp {

	private static final String CLEAR_CACHE_ARG = "-clear-cache";
	private static final String DDATA_ARG_PREFIX = "-Dfile=";
	private static final String CACHE_ARG_PREFIX = "-cache=";
	
	private static final Logger logger = LoggerFactory.getLogger(CabClientApp.class);

	public static void main(String[] args) {
		//Initialize properties file
		AppProperties appProperties = AppProperties.getInstance();
		try {
			appProperties.load();
		} catch (Exception e) {
			System.exit(0);
		}
		
		if (args.length < 1) {
			printHelpText();
			System.exit(0);
		}

		try {
			String dataFileName;
			boolean useCache = false;
			TripRequest tripRequest = null;
			CabClient cabClient = new CabClientImpl();

			for (String arg : args) {

				if (startsWithIgnoreCase(arg, CACHE_ARG_PREFIX)) {
					useCache = parseUseCacheFlag(arg.substring(CACHE_ARG_PREFIX.length()));
				} else if (startsWithIgnoreCase(arg, DDATA_ARG_PREFIX)) {
					dataFileName = arg.substring(DDATA_ARG_PREFIX.length());
					tripRequest = new CsvFileParser().parseTripInfoCsvFile(dataFileName);
				} else if (startsWithIgnoreCase(arg, CLEAR_CACHE_ARG)) {
					System.out.println(cabClient.clearCache().getResult());
					return;
				}
			}

			if (tripRequest != null) {
				TripResponse response = cabClient.getTripInfo(tripRequest, useCache);
				printTripResponse(response);
			} else {
				printHelpText();
			}
		} catch (InvalidInputException | FailureResponseException e) {
			logger.error(e.getMessage());
			System.exit(0);
		}
	}

	private static void printTripResponse(TripResponse response) {
		for (TripInfo info : response.getTripInfos()) {
			System.out.println("Cab id:" + info.getCabId() + ", Trip Date: " + info.getPickupDate().toString()
					+ ", Trip count: " + info.getTripCount());
		}
	}

	private static void printHelpText() {
		System.out.println("Plesae provide correct arguments. \r\n"
				+ "To clear cache  : java -jar <jar file name> -clear-cache \r\n"
				+ "To get trip info: java -jar <jar file name> -Dfile=<csv file name> -cache=true/false");
	}
}

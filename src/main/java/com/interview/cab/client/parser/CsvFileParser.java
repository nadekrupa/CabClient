package com.interview.cab.client.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.interview.cab.client.exception.InvalidInputException;
import com.interview.cab.request.TripRequest;
import com.interview.cab.request.TripResuestInfo;

/**
 * Utility class to parse input csv file
 *
 * @author Krupa N
 */
public class CsvFileParser {
	
	public TripRequest parseTripInfoCsvFile(String fileName) throws InvalidInputException {
		List<TripResuestInfo> records = new ArrayList<TripResuestInfo>();

		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (line.contains("pickup date")) {
					continue;
				}

				if (!line.trim().isEmpty()) {
					String[] values = line.split(",");
					LocalDate.parse(values[1].trim());
					records.add(new TripResuestInfo(values[0].trim(), values[1].trim()));
				}
			}
		} catch (Exception e) {
			throw new InvalidInputException("Error occurred while reading the input CSV file!!");
		}
		
		TripRequest tripRequest = new TripRequest();
		tripRequest.setTripRequests(records);
		
		return tripRequest;
	}
}

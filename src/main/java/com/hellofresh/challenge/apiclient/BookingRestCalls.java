package com.hellofresh.challenge.apiclient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.hellofresh.challenge.apiclient.BaseRestClient;
import com.hellofresh.challenge.utility.CommonFunctions;

@SuppressWarnings("unchecked")

/**
 * Class BookingPage has functions related to Room Booking management
 * 
 * @author rajat.rastogi
 */
public class BookingRestCalls {

	BaseRestClient restClient;

	/**
	 * Constructor when called created object of BaseRestClient for required
	 * parameters.
	 */
	public BookingRestCalls() {
		restClient = new BaseRestClient(CommonFunctions.getValue("apiData.protocol").toString(),
				CommonFunctions.getValue("apiData.server").toString());
	}

	/**
	 * Function getAllBookings fetches all available bookings
	 * 
	 * @return : customize API response
	 */
	public JSONObject getAllBookings() {
		JSONObject apiData = new JSONObject();
		int statusCode = 0;

		try {
			String url = CommonFunctions.getValue("apiData.endPoint").toString();
			System.out.println("url=> " + url);
			HttpResponse getResponse = restClient.performGet(url);
			statusCode = getResponse.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				HttpEntity entity = getResponse.getEntity();

				JSONParser parser = new JSONParser();

				JSONObject bookingsData = (JSONObject) parser.parse(EntityUtils.toString(entity));

				apiData.put("response", bookingsData);
				apiData.put("statusCode", statusCode);
				apiData.put("responseTime", restClient.apiResponseTime);

				return apiData;
			} else {
				apiData.put("statusCode", statusCode);
				apiData.put("responseTime", restClient.apiResponseTime);

				return apiData;
			}

		} catch (Exception e) {
			System.out.println("Exception generate in making get bookings call. Error-->" + e);
			e.printStackTrace();
			return apiData;
		}
	}

	/**
	 * Function getBooking fetches available booking for a given ID
	 * 
	 * @return : customize API response
	 */
	public JSONObject getBooking(long id) {
		JSONObject apiData = new JSONObject();
		int statusCode = 0;

		try {
			String url = CommonFunctions.getValue("apiData.endPoint").toString() + "/" + id;
			System.out.println("url=> " + url);
			HttpResponse getResponse = restClient.performGet(url);
			statusCode = getResponse.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				HttpEntity entity = getResponse.getEntity();

				JSONParser parser = new JSONParser();

				JSONObject bookingsData = (JSONObject) parser.parse(EntityUtils.toString(entity));

				apiData.put("response", bookingsData);
				apiData.put("statusCode", statusCode);
				apiData.put("responseTime", restClient.apiResponseTime);

				return apiData;
			} else {
				apiData.put("statusCode", statusCode);
				apiData.put("responseTime", restClient.apiResponseTime);

				return apiData;
			}
		} catch (Exception e) {
			System.out.println("Exception generate in making get bookings call. Error-->" + e);
			e.printStackTrace();
			return apiData;
		}
	}

	/**
	 * Function createBooking creates a Room Booking by making POST call Room
	 * Booking data is read from the TestData.json file
	 * 
	 * @return customize API response
	 */
	public JSONObject createBooking() {
		String bookingData = CommonFunctions.getValue("apiData.newBookingData");
		JSONObject apiData = new JSONObject();
		int statusCode = 0;
		System.out.println("bookingData=>" + bookingData);
		try {
			HttpResponse postResponse = restClient.performPost(CommonFunctions.getValue("apiData.endPoint").toString(),
					bookingData);
			statusCode = postResponse.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_CREATED) {
				HttpEntity entity = postResponse.getEntity();
				JSONObject bookingsData = new JSONObject();
				JSONParser parser = new JSONParser();
				bookingsData = (JSONObject) parser.parse(EntityUtils.toString(entity));
				apiData.put("response", bookingsData);
				apiData.put("statusCode", statusCode);
				return apiData;

			} else {
				apiData.put("statusCode", statusCode);
				return apiData;
			}

		} catch (Exception e) {
			System.out.println("Exception generated while creating Booking. Error-->" + e);
			e.printStackTrace();
			return apiData;
		}

	}
}

package com.hellofresh.challenge.tests;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.hellofresh.challenge.apiclient.BookingRestCalls;
import com.hellofresh.challenge.utility.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

/**
 * Test Class Perform 3 basic tests:- 1. Verify new user registration by
 * generating Random data and validates user created. 2. Verify existing user is
 * able to login. 3. Verify checkout functionality.
 * 
 * @author rajat.rastogi
 *
 */
public class ApiTest {

	private long id;
	BookingRestCalls apiClient;
	static Logger log = Logger.getLogger(ApiTest.class.getName());

	@BeforeTest
	public void setup() {
		apiClient = new BookingRestCalls();
	}

	/**
	 * Test Method 1 for the Get all the bookinngs and verify at least 2 bookings
	 */
	@Test(priority = 1, enabled = true)
	public void getBookings(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"getBookings test - Fetch bookings and validate atleast 2 bookings are returned.");
		JSONObject apiCustomResponse = apiClient.getAllBookings();

		if (apiCustomResponse.get("statusCode").toString().equals("200")) {
			Assert.assertTrue(true);
			ExtentTestManager.test.log(LogStatus.PASS, "Status code is 200");
			JSONObject jObject = (JSONObject) apiCustomResponse.get("response");

			JSONArray jArray = (JSONArray) jObject.get("bookings");

			Assert.assertTrue(jArray.size() >= 2);
			ExtentTestManager.test.log(LogStatus.PASS,
					"Atleast 2 bookings are returned in response. Actual number of bookings are " + jArray.size());
			for (int i = 0; i < jArray.size(); i++) {
				JSONObject bookingDetail = (JSONObject) jArray.get(i);
				id = (long) bookingDetail.get("bookingid");

			}
		} else {
			ExtentTestManager.test.log(LogStatus.FAIL,
					"API Failed with response code : " + apiCustomResponse.get("statusCode").toString());

			Assert.assertFalse(true);
		}

	}

	/**
	 * Test Method 2 for the Get Booking API and verification
	 */
	@Test(priority = 2, enabled = true, dependsOnMethods = { "getBookings" })

	public void getBooking(Method method) {
		ExtentTestManager.startTest(method.getName(), "getBooking test - Fetch booking based on id");
		JSONObject apiCustomResponse = apiClient.getBooking(id);

		if (apiCustomResponse.get("statusCode").toString().equals("200")) {
			Assert.assertTrue(true);
			ExtentTestManager.test.log(LogStatus.PASS, "Status code is 200");
			JSONObject jObject = (JSONObject) apiCustomResponse.get("response");
			long returnedBookingId = (long) jObject.get("bookingid");

			Assert.assertEquals(returnedBookingId, id);
			ExtentTestManager.test.log(LogStatus.PASS,
					"Expected booking id is " + id + " and actual booking id is " + returnedBookingId);
		} else {
			ExtentTestManager.test.log(LogStatus.FAIL,
					"API Failed with response code : " + apiCustomResponse.get("statusCode").toString());

			Assert.assertFalse(true);
		}

	}

	/**
	 * Test Method 3 to create a booking
	 */

	@Test(priority = 3, enabled = true)
	public void createBookings(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"createBooking test - Creates a new booking by fetching data from TestData.json file.");
		JSONObject apiCustomResponse = apiClient.createBooking();

		if (apiCustomResponse.get("statusCode").toString().equals("201")) {
			Assert.assertTrue(true);
			ExtentTestManager.test.log(LogStatus.PASS, "Status code is 201");
			JSONObject bookingResponse = (JSONObject) apiCustomResponse.get("response");
			JSONObject bookingDetails = (JSONObject) bookingResponse.get("booking");

			ExtentTestManager.test.log(LogStatus.PASS, "Booking is created successfully for the person whose name is "
					+ bookingDetails.get("firstname").toString());
		} else {
			ExtentTestManager.test.log(LogStatus.FAIL,
					"API Failed with response code : " + apiCustomResponse.get("statusCode").toString());

			Assert.assertFalse(true);
		}

	}

}

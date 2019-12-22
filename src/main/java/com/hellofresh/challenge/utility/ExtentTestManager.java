package com.hellofresh.challenge.utility;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import java.util.HashMap;
import java.util.Map;

/**
 * class ExtentTestManager updates extent report on different actions such as
 * start of test
 * 
 * @author rajat.rastogi
 *
 */
public class ExtentTestManager {
	static Map extentTestMap = new HashMap();
	static ExtentReports extent = ExtentManager.getReporter();
	public static ExtentTest test;

	public static synchronized ExtentTest getTest() {
		return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
	}

	public static synchronized void endTest() {
		extent.endTest((ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId())));
	}

	public static synchronized ExtentTest startTest(String testName, String desc) {
		test = extent.startTest(testName, desc);
		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
		return test;
	}
}

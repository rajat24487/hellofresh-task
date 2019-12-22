package com.hellofresh.challenge.utility;

import com.relevantcodes.extentreports.ExtentReports;

/**
 * Class ExtentManager set the path for Extent report
 * 
 * @author rajat.rastogi
 *
 */
public class ExtentManager {
	private static ExtentReports extent;

	public synchronized static ExtentReports getReporter() {
		if (extent == null) {
			// Set HTML reporting file location
			String workingDir = System.getProperty("user.dir");
			extent = new ExtentReports(workingDir + "\\target\\ExtentReportResults.html", true);
		}
		return extent;
	}

}

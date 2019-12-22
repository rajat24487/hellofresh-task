package com.hellofresh.challenge.utility;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.LogStatus;

/**
 * Class TestListener implements ITestListener to modify test report on test
 * execution and failure
 * 
 * @author rajat.rastogi
 *
 */
public class TestListener extends BaseUiTest implements ITestListener {

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	static Logger log = Logger.getLogger(TestListener.class.getName());

	@Override
	public void onStart(ITestContext iTestContext) {
		log.info("I am in onStart method " + iTestContext.getName());
		iTestContext.setAttribute("WebDriver", this.webDriver);
	}

	@Override
	public void onFinish(ITestContext iTestContext) {
		log.info("I am in onFinish method " + iTestContext.getName());
		ExtentTestManager.endTest();
		ExtentManager.getReporter().flush();
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
		log.info("I am in onTestStart method " + getTestMethodName(iTestResult) + " start");
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		log.info("I am in onTestSuccess method " + getTestMethodName(iTestResult) + " succeed");
		ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		log.info("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");

		// Get driver from BaseTest and assign to local webDriver variable.
		Object testClass = iTestResult.getInstance();
		if (testClass.getClass().equals("com.hellofresh.challenge.utility.BaseUiTest")) {
			WebDriver webD = ((BaseUiTest) testClass).getDriver();

			// Take base64Screenshot screenshot.
			String base64Screenshot = "data:image/png;base64,"
					+ ((TakesScreenshot) webD).getScreenshotAs(OutputType.BASE64);

			// ExtentReports log and screenshot operations for failed tests.
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed",
					ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
		}
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		log.info("I am in onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");
		ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		log.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	}

}

package com.hellofresh.challenge.utility;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.hellofresh.challenge.driverclient.WebDriverSetup;

import org.testng.annotations.AfterClass;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

/**
 * Class WebDriverStep class to initiate WebDriver object based on browser value
 * 
 * @author rajat.rastogi
 *
 */
public class BaseUiTest {

	static Logger log = Logger.getLogger(BaseUiTest.class.getName());

	public WebDriver webDriver;

	/**
	 * Functions initializes WebDriver object based on browser value
	 * 
	 * @param browserType - browser on which tests are to be executed
	 * @param url         - Environment / URL of website on which tests are to be
	 *                    performed
	 */
	@Parameters({ "browserType", "url" })
	@BeforeClass
	public void initializeTestBaseSetup(@Optional("chrome") String browserType,
			@Optional("http://automationpractice.com/index.php") String url) {
		webDriver = WebDriverSetup.getWebDriver(browserType, url);

	}

	/**
	 * return WebDriver reference
	 * 
	 * @return
	 */
	public WebDriver getDriver() {
		return webDriver;
	}

	/**
	 * Tear Down method to close and quit web driver browser and session. It is
	 * executed at end of test
	 */
	@AfterClass
	public void tearDown() {
		log.info("Closing WebDriver");
		webDriver.quit();
	}

}
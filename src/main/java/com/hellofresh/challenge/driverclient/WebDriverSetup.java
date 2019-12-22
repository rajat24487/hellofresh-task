package com.hellofresh.challenge.driverclient;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Class provides basic functions for launching specified web browser and
 * initiating WebDriver object
 * 
 * @author rajat.rastogi
 */
public class WebDriverSetup {

	static Logger log = Logger.getLogger(WebDriverSetup.class);

	private static WebDriver webDriver;

	/**
	 * @param browserType : type of browser to be launched for automation like ,
	 *                    chrome, firefox.
	 * @param url         : home url of application
	 * @return webDriver Object of browser type passed in param
	 */
	public static WebDriver getWebDriver(String browserType, String url) {
		log.info("returning driver for browser:" + browserType);
		if (browserType.equalsIgnoreCase("firefox")) {
			initFirefoxDriver(url);
			return webDriver;
		} else if (browserType.equalsIgnoreCase("chrome")) {
			initChromeDriver(url);
			return webDriver;
		} else {
			initFirefoxDriver(url);
			return webDriver;
		}
	}

	/**
	 * @param appURL
	 * @return
	 */
	private static WebDriver initChromeDriver(String appURL) {
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();
		webDriver.manage().window().maximize();
		webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		webDriver.navigate().to(appURL);
		log.info("returning chrome driver...");
		return webDriver;
	}

	/**
	 * @param appURL
	 * @return
	 */
	private static WebDriver initFirefoxDriver(String appURL) {
		WebDriverManager.firefoxdriver().setup();
		webDriver = new FirefoxDriver();
		webDriver.manage().window().maximize();
		webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		webDriver.navigate().to(appURL);
		log.info("returning firefix driver...");
		return webDriver;
	}

}

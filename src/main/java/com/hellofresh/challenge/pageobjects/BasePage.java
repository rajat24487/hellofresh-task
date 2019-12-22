package com.hellofresh.challenge.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Abstract class to initiate Web Pages: support Page Object Model.
 * 
 * @author rajat.rastogi
 *
 */
public abstract class BasePage {

	static Logger log = Logger.getLogger(BasePage.class.getName());

	WebDriver webDriver;

	/**
	 * Constructor to initiate Page Objects using Selenium Init method.
	 * 
	 * @param webDriver
	 */
	public BasePage(WebDriver webDriver) {
		this.webDriver = webDriver;
		PageFactory.initElements(webDriver, this);
		log.info("Initiated webelement in Base class contructor for class: " + BasePage.class.getName());
	}

	/**
	 * Utility function wait for specific Element to be visible on UI.
	 * 
	 * @param element - web element for which wait is to performed.
	 * @param time    - wait time for element in seconds.
	 */
	public void waitForElementVisible(WebElement element, int time) {
		try {
			log.info("wait for " + element.toString() + " element to be visible");
			new WebDriverWait(webDriver, time).until(ExpectedConditions.visibilityOf(element));
			log.info("Element " + element.toString() + " is visible.");
		} catch (Exception e) {
			log.error("Exception generated while waiting for element " + element.toString());
			e.printStackTrace();
		}
	}

	/**
	 * Utility function wait for specific Element to be visible on UI.
	 * 
	 * @param element - web element for which wait is to performed.
	 * @param time    - wait time for element in seconds.
	 */
	public void waitForElementClickable(WebElement element, int time) {
		try {
			log.info("wait for " + element.toString() + " element to be visible");
			new WebDriverWait(webDriver, time).until(ExpectedConditions.elementToBeClickable(element));
			log.info("Element " + element.toString() + " is visible.");
		} catch (Exception e) {
			log.error("Exception generated while waiting for element " + element.toString());
			e.printStackTrace();
		}
	}

}

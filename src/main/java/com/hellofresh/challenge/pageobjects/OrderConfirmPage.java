package com.hellofresh.challenge.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * OrderConfirmPage Class contains web elements available on Order confirm Web
 * Page and their corresponding actions.
 * 
 * @author rajat.rastogi
 * 
 */
public class OrderConfirmPage extends BasePage {

	static Logger log = Logger.getLogger(OrderConfirmPage.class.getName());

	/**
	 * Parameterized Constructor for initializing OrderConfirmPage class object
	 * 
	 * @param webDriver : reference of WebDriver class
	 */
	public OrderConfirmPage(WebDriver webDriver) {
		super(webDriver);
		log.info("In OrderConfirmPage class contructor");
	}

	/** Variables to identify Web Elements On Order Confirm Page Page UI **/

	@FindBy(css = "h1")
	private WebElement heading;

	@FindBy(xpath = "//li[@class='step_done step_done_last four']")
	private WebElement lastStepEle;

	@FindBy(xpath = "//li[@id='step_end' and @class='step_current last']")
	private WebElement currentStepEle;

	@FindBy(xpath = "//*[@class='cheque-indent']/strong")
	private WebElement orderCompleteTextEle;

	/** Functions/Actions that could be performed on Web Elements **/

	/**
	 * Function wait for Order Confirmation Page to load.
	 */
	public void waitForPageLoad() {
		try {
			log.info("Waiting for Order Confirmation to load");
			waitForElementVisible(heading, 10);
		} catch (Exception e) {
			log.error("Exception occured while Waiting for Order Confirmation Page  to load. Error->" + e);
			e.printStackTrace();
		}
	}

	/**
	 * Function return current url of page
	 * 
	 * @return
	 */
	public String getCurrentUrl() {
		return webDriver.getCurrentUrl();
	}

	/**
	 * Function Return the Heading of current page.
	 * 
	 * @return : heading of page h1 as String
	 */
	public String getPageHeading() {
		String headingText = heading.getText();
		log.info("Returning account name " + headingText);
		return headingText;
	}

	/**
	 * Function Return order Confirm Text.
	 * 
	 * @return : order Confirm Text as String
	 */
	public String getOrderConfirmationText() {
		String orderConfirmText = orderCompleteTextEle.getText();
		log.info("Returning order Confirm Text " + orderConfirmText);
		return orderConfirmText;
	}

	/**
	 * Function return true, if last step element is displayed, else false
	 * 
	 * @return
	 */
	public boolean verifyLastStepDisplayed() {
		return lastStepEle.isDisplayed();
	}

	/**
	 * Function return true, if last current step element is displayed, else false
	 * 
	 * @return
	 */
	public boolean verifyCurrentStepIsLastStepDisplayed() {
		return currentStepEle.isDisplayed();
	}
}

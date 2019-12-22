package com.hellofresh.challenge.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * OrderPage Class contains web elements available on Order Page and their
 * corresponding actions.
 * 
 * @author rajat.rastogi
 * 
 */

public class OrderPage extends BasePage {

	static Logger log = Logger.getLogger(OrderPage.class.getName());

	/**
	 * Parameterized Constructor for initializing OrderPage class object
	 * 
	 * @param webDriver : reference of WebDriver class
	 */
	public OrderPage(WebDriver webDriver) {
		super(webDriver);
		log.info("In OrderPage class contructor");
	}

	/** Variables to identify Web Elements On Order Page UI **/

	@FindBy(css = "h1")
	private WebElement heading;

	@FindBy(xpath = "//*[contains(@class,'cart_navigation')]/a[@title='Proceed to checkout']")
	private WebElement proceedBtn;

	@FindBy(name = "processAddress")
	private WebElement processAddressBtn;

	@FindBy(id = "uniform-cgv")
	private WebElement tandCCheckBox;

	@FindBy(name = "processCarrier")
	private WebElement processCarrierBtn;

	@FindBy(className = "bankwire")
	private WebElement bankWirePaymentLink;

	@FindBy(xpath = "//*[@id='cart_navigation']/button")
	private WebElement orderConfirmBtn;

	/** Functions/Actions that could be performed on Web Elements **/

	/**
	 * Function wait for Order Page to load.
	 */
	public void waitForPageLoad() {
		try {
			log.info("Waiting for Order Page to load");
			waitForElementVisible(proceedBtn, 10);
		} catch (Exception e) {
			log.error("Exception occured while Waiting for Order Page to load. Error->" + e);
			e.printStackTrace();
		}
	}

	/**
	 * Function return current url of web page
	 * 
	 * @return
	 */
	public String getCurrentUrl() {
		return webDriver.getCurrentUrl();
	}

	/**
	 * Function checkout item from cart
	 */
	public OrderConfirmPage purchaseItemAddedInCart() {
		OrderConfirmPage ocp = new OrderConfirmPage(webDriver);
		try {
			log.info("click on proceed button");
			waitForElementVisible(proceedBtn, 10);
			proceedBtn.click();
			waitForElementVisible(processAddressBtn, 10);
			processAddressBtn.click();
			waitForElementVisible(tandCCheckBox, 10);
			log.info("click on T&C checkbox");
			tandCCheckBox.click();
			waitForElementVisible(processCarrierBtn, 10);
			processCarrierBtn.click();
			log.info("click on wire payment link");
			waitForElementVisible(bankWirePaymentLink, 10);
			bankWirePaymentLink.click();
			log.info("Confirm order by clicking on confirm button");
			waitForElementVisible(orderConfirmBtn, 10);
			orderConfirmBtn.click();
			ocp.waitForPageLoad();
			return ocp;
		} catch (Exception e) {
			log.error("Exception generated while purchasing item added in cart.");
			e.printStackTrace();
			return ocp;
		}
	}

}

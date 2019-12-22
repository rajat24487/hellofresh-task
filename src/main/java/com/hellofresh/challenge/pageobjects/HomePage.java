package com.hellofresh.challenge.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * HomPage Class contains web elements available on landing page and their
 * corresponding actions.
 * 
 * @author rajat.rastogi
 * 
 */
public class HomePage extends BasePage {

	static Logger log = Logger.getLogger(HomePage.class.getName());

	/**
	 * Parameterized Constructor for initializing HomePage class object
	 * 
	 * @param webDriver : reference of WebDriver class
	 */
	public HomePage(WebDriver webDriver) {
		super(webDriver);
		log.info("In HomePage class contructor");
	}

	/** Variables to identify Web Elements On Home Page UI **/
	@FindBy(className = "login")
	private WebElement signInBtn;

	/** Functions/Actions that could be performed on Web Elements **/

	/**
	 * Function click on 'Sign in' button on home page and return Sign/Login page
	 * object.
	 * 
	 * @return - Sign/login page object
	 */
	public LoginPage clickSignInBtn() {
		LoginPage lp = new LoginPage(webDriver);
		try {
			signInBtn.click();
			log.info("Sign In button clicked on HomePage.");
			log.info("Waiting for Login and Sign Page to Load");
			lp.waitForPageLoad();
			log.info("SignIn/Login Page loaded");
			log.info("Returning Login/SignIn Page object.");
			return lp;
		} catch (Exception e) {
			log.error("Exception occured while Clicking Sign In button on home page.");
			e.printStackTrace();
			return lp;
		}
	}
}

package com.hellofresh.challenge.pageobjects;

import java.util.Date;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.hellofresh.challenge.utility.CommonFunctions;

/**
 * LoginPage class contains web elements available on Login Page and their
 * helper functions.
 * 
 * @author rajat.rastogi
 *
 */
public class LoginPage extends BasePage {

	static Logger log = Logger.getLogger(LoginPage.class.getName());

	/**
	 * Parameterized Constructor for initializing LoginPage class object
	 * 
	 * @param webDriver : reference of WebDriver class
	 */
	public LoginPage(WebDriver webDriver) {
		super(webDriver);
	}

	/** Variables to identify Web Elements On Home Page UI **/

	@FindBy(id = "SubmitLogin")
	private WebElement loginBtn;

	@FindBy(id = "email_create")
	private WebElement createEmailTextbox;

	@FindBy(id = "SubmitCreate")
	private WebElement submitEmailBtn;

	@FindBy(id = "email")
	private WebElement userEmailTextField;

	@FindBy(id = "passwd")
	private WebElement userPasswordTextField;

	@FindBy(id = "SubmitLogin")
	private WebElement submitLoginBtn;

	/** Functions/Actions that could be performed on Web Elements **/

	/**
	 * Function wait for Login Page to load.
	 */
	public void waitForPageLoad() {
		try {
			log.info("Waiting for login button to be visible");
			waitForElementVisible(loginBtn, 10);
		} catch (Exception e) {
			log.error("Exception occured while Waiting for Login Page to load. Error->" + e);
			e.printStackTrace();
		}
	}

	/**
	 * Function return random email by adding timestamp
	 * 
	 * @param email
	 * @return - random email
	 */
	public String getRandomEmail() {
		String email = CommonFunctions.getValue("New_User_Details.email");
		String[] splitStr = email.split("\\@");
		String randomEmail = splitStr[0] + String.valueOf(new Date().getTime()) + "@" + splitStr[1];
		log.info("Returning random email as " + randomEmail);
		return randomEmail;
	}

	/**
	 * Function enters email in new user email field
	 * 
	 * @param newUserEmail - Email of new user as a string value
	 */
	public void enterNewUserEmail(String newUserEmail) {
		try {
			log.info("Entering user email as" + newUserEmail);
			createEmailTextbox.sendKeys(newUserEmail);
		} catch (Exception e) {
			log.error("Exception generated while entering email for new user. Error->" + e);
			e.printStackTrace();
		}
	}

	/**
	 * Function click on submit email button.
	 */
	public SignInPage clickBtnForSubmitNewEmail() {
		SignInPage sp = new SignInPage(webDriver);
		try {
			submitEmailBtn.click();
			log.info("Submit Email button clicked.");
			sp.waitForPageLoad();
			log.info("Returning SignIn page for new user");
			return sp;
		} catch (Exception e) {
			log.error("Exception occured while clicking submit button. Error->" + e);
			e.printStackTrace();
			return sp;
		}
	}

	/**
	 * Function logged in with existing user.
	 * 
	 * @param username - email of existing user
	 * @param password - password of existing user
	 */
	public MyAccountPage loginUser(String username, String password) {
		MyAccountPage mp = new MyAccountPage(webDriver);
		try {
			log.info("Entering username " + username + " in email field");
			userEmailTextField.sendKeys(username);
			log.info("Entering password " + password + " in password field");
			userPasswordTextField.sendKeys(password);
			submitLoginBtn.click();
			log.info("Clicked log in button after entering username and password.");
			return mp;
		} catch (Exception e) {
			log.error("Exception occured while logging with email" + username + " and password " + password
					+ " . Error->" + e);
			e.printStackTrace();
			return mp;
		}
	}
}

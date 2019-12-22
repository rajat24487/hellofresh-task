package com.hellofresh.challenge.tests;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.hellofresh.challenge.pageobjects.HomePage;
import com.hellofresh.challenge.pageobjects.LoginPage;
import com.hellofresh.challenge.pageobjects.MyAccountPage;
import com.hellofresh.challenge.pageobjects.OrderConfirmPage;
import com.hellofresh.challenge.pageobjects.OrderPage;
import com.hellofresh.challenge.pageobjects.SignInPage;
import com.hellofresh.challenge.utility.BaseUiTest;
import com.hellofresh.challenge.utility.CommonFunctions;
import com.hellofresh.challenge.utility.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

/**
 * Test Class Perform 3 basic tests:- 1. Verify new user registration by
 * generating Random data and validates user created. 2. Verify existing user is
 * able to login. 3. Verify checkout functionality.
 * 
 * @author rajat.rastogi
 *
 */
public class WebTest extends BaseUiTest {

	/** Page class references **/
	HomePage hp;
	LoginPage lp;
	SignInPage sp;
	MyAccountPage mp;
	OrderPage op;
	OrderConfirmPage ocp;

	/**
	 * Test Method 1 - Verify new user registration by generating Random data and
	 * validates user created
	 */
	@Test(priority = 1)
	public void signInTest(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"SignIn test - Perform New user Registration with Random values and verify user creation on My Account page");
		hp = new HomePage(webDriver);

		ExtentTestManager.test.log(LogStatus.INFO, "Click on Sign-in button on Home Page.");
		lp = hp.clickSignInBtn();
		ExtentTestManager.test.log(LogStatus.PASS, "Sign-In button is clicked on Home Page.");

		String newUserEmail = lp.getRandomEmail();
		ExtentTestManager.test.log(LogStatus.INFO, "Fill Email id " + newUserEmail + " for new user");
		lp.enterNewUserEmail(newUserEmail);
		ExtentTestManager.test.log(LogStatus.PASS, "Email id " + newUserEmail + " filled for new user");

		ExtentTestManager.test.log(LogStatus.INFO, "Click on Submit button");
		sp = lp.clickBtnForSubmitNewEmail();
		ExtentTestManager.test.log(LogStatus.PASS, "Submit button clicked");

		ExtentTestManager.test.log(LogStatus.INFO, "Fill New User Details");
		sp.enterRandomUserDetails();
		ExtentTestManager.test.log(LogStatus.PASS, "New User Details filled");

		ExtentTestManager.test.log(LogStatus.INFO, "Click on Register button");
		mp = sp.clickRegisterButton();
		ExtentTestManager.test.log(LogStatus.PASS, "Register button clicked");

		ExtentTestManager.test.log(LogStatus.INFO, "Verify heading of My Account page");
		Assert.assertEquals(mp.getPageHeading(), "MY ACCOUNT");
		ExtentTestManager.test.log(LogStatus.PASS, "Heading of My Account page is 'MY ACCOUNT'");

		ExtentTestManager.test.log(LogStatus.INFO, "Verify user name on My Account page");
		Assert.assertTrue(mp.getAccountName().toLowerCase().contains(sp.usersName.toLowerCase()));
		ExtentTestManager.test.log(LogStatus.PASS, "User name displayed on My account page: " + mp.getAccountName()
		+ " is equal to entered user name: " + sp.usersName);

		ExtentTestManager.test.log(LogStatus.INFO, "Verify Welcome text");
		Assert.assertTrue(mp.getwelcomeText().contains("Welcome to your account"));
		ExtentTestManager.test.log(LogStatus.PASS,
				"On My Account Page, Welcome Text contains : Welcome to your account");

		ExtentTestManager.test.log(LogStatus.INFO, "Verify log-out button");
		Assert.assertTrue(mp.verifyLogoutButton());
		ExtentTestManager.test.log(LogStatus.PASS, "'Logout' button is displayed on My Account Page.");

		ExtentTestManager.test.log(LogStatus.INFO, "Verify URL of My Account page");
		Assert.assertTrue(mp.getCurrentUrl().contains("controller=my-account"));
		ExtentTestManager.test.log(LogStatus.PASS, "URL of My Account Page contains 'controller=my-account'");

		ExtentTestManager.test.log(LogStatus.INFO, "Click on Log-out button");
		mp.clickSignOutBtn();
		ExtentTestManager.test.log(LogStatus.PASS, "Logout is successful");
	}

	/**
	 * Test Method 2- Verify existing user is able to login.
	 */
	@Test(priority = 2)
	public void logInTest(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"Login test - Perform Login with existing user created in above test and verify details on My Account page");

		ExtentTestManager.test.log(LogStatus.INFO, "Click on Sign-in button on Home Page.");
		lp = hp.clickSignInBtn();
		ExtentTestManager.test.log(LogStatus.PASS, "Sign-In button is clicked on Home Page.");

		String existingUserEmail = CommonFunctions.getValue("Existing_user.username");
		String existingUserPass = CommonFunctions.getValue("Existing_user.password");

		ExtentTestManager.test.log(LogStatus.INFO, "Fill User Details and click on login");
		mp = lp.loginUser(existingUserEmail, existingUserPass);
		ExtentTestManager.test.log(LogStatus.PASS, "username: " + existingUserEmail + " / password: " + existingUserPass
				+ " of existing user entered and Login button clicked successfully");

		ExtentTestManager.test.log(LogStatus.INFO, "Verify heading of My Account page");
		Assert.assertEquals(mp.getPageHeading(), "MY ACCOUNT");
		ExtentTestManager.test.log(LogStatus.PASS, "Heading of My Account page is 'MY ACCOUNT'");

		ExtentTestManager.test.log(LogStatus.INFO, "Verify user name on My Account page");
		Assert.assertTrue(mp.getAccountName().toLowerCase()
				.contains(CommonFunctions.getValue("Existing_user.fullname").toLowerCase()));
		ExtentTestManager.test.log(LogStatus.PASS, "User name displayed on My Account page " + mp.getAccountName()
		+ " is equal to   " + CommonFunctions.getValue("Existing_user.fullname"));

		ExtentTestManager.test.log(LogStatus.INFO, "Verify Welcome text");
		Assert.assertTrue(mp.getwelcomeText().contains("Welcome to your account"));
		ExtentTestManager.test.log(LogStatus.PASS,
				"On My Account Page, Welcome Text contains : Welcome to your account");

		ExtentTestManager.test.log(LogStatus.INFO, "Verify log-out button");
		Assert.assertTrue(mp.verifyLogoutButton());
		ExtentTestManager.test.log(LogStatus.PASS, "'Logout' button is displayed on My Account Page.");

		ExtentTestManager.test.log(LogStatus.INFO, "Verify URL of My Account page");
		Assert.assertTrue(mp.getCurrentUrl().contains("controller=my-account"));
		ExtentTestManager.test.log(LogStatus.PASS, "URL of My Account Page contains 'controller=my-account'");

		ExtentTestManager.test.log(LogStatus.INFO, "Click on Log-out button");
		mp.clickSignOutBtn();
		ExtentTestManager.test.log(LogStatus.PASS, "Logout is successful");
	}

	/**
	 * Test Method 3- Verify checkout functionality
	 */
	@Test(priority = 3)
	public void checkoutTest(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"Checkout Test- Test add a item in cart and purcahse it, then verify order confirmation");

		ExtentTestManager.test.log(LogStatus.INFO, "Click on Sign-in button on Home Page.");
		lp = hp.clickSignInBtn();
		ExtentTestManager.test.log(LogStatus.PASS, "Sign-In button is clicked on Home Page.");

		String existingUserEmail = CommonFunctions.getValue("Existing_user.username");
		String existingUserPass = CommonFunctions.getValue("Existing_user.password");

		ExtentTestManager.test.log(LogStatus.INFO, "Fill User Details and click on login");
		mp = lp.loginUser(existingUserEmail, existingUserPass);
		ExtentTestManager.test.log(LogStatus.PASS, "username: " + existingUserEmail + " / password: " + existingUserPass
				+ " of existing user entered and Login button clicked successfully");

		ExtentTestManager.test.log(LogStatus.INFO, "Add item to the cart");
		op = mp.addFadedShortSleeveTshirtInCart();
		ExtentTestManager.test.log(LogStatus.PASS, "Faded Short Sleeve T-shirt Item is added in cart.");

		ExtentTestManager.test.log(LogStatus.INFO, "Purchase added item from the cart");
		ocp = op.purchaseItemAddedInCart();
		ExtentTestManager.test.log(LogStatus.PASS, "Added item is purchased");

		ExtentTestManager.test.log(LogStatus.INFO, "Verify heading of Order Confirmation page");
		Assert.assertEquals(ocp.getPageHeading(), "ORDER CONFIRMATION");
		ExtentTestManager.test.log(LogStatus.PASS, "Heading of Order Confirmation page is 'ORDER CONFIRMATION'");

		ExtentTestManager.test.log(LogStatus.INFO, "Verify last step");
		Assert.assertTrue(ocp.verifyLastStepDisplayed());
		ExtentTestManager.test.log(LogStatus.PASS, "Last step is displayed.");

		ExtentTestManager.test.log(LogStatus.INFO, "Verify current step");
		Assert.assertTrue(ocp.verifyCurrentStepIsLastStepDisplayed());
		ExtentTestManager.test.log(LogStatus.PASS, "Current step is displyed");

		ExtentTestManager.test.log(LogStatus.INFO, "Verify URL of My Account page");
		Assert.assertTrue(ocp.getCurrentUrl().contains("controller=order-confirmation"));
		ExtentTestManager.test.log(LogStatus.PASS,
				"URL of Order Confirmation Page contains 'controller=order-confirmation'");
	}
}

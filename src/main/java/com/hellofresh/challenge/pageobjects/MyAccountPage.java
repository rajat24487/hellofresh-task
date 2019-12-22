package com.hellofresh.challenge.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * MyAccountPage Class contains web elements available on My Account Web Page
 * and their corresponding actions.
 * 
 * @author rajat.rastogi
 * 
 */
public class MyAccountPage extends BasePage {

	static Logger log = Logger.getLogger(MyAccountPage.class.getName());

	/**
	 * Parameterized Constructor for initializing MyAccountPage class object
	 * 
	 * @param webDriver : reference of WebDriver class
	 */
	public MyAccountPage(WebDriver webDriver) {
		super(webDriver);
		log.info("In MyAccountPage class contructor");
	}

	/** Variables to identify Web Elements On My Account Page UI **/

	@FindBy(css = "h1")
	private WebElement heading;

	@FindBy(className = "account")
	private WebElement accountNameText;

	@FindBy(className = "info-account")
	private WebElement infoAccountText;

	@FindBy(className = "logout")
	private WebElement logoutBtn;

	@FindBy(linkText = "Women")
	private WebElement womenTab;

	@FindBy(xpath = "//a[@title='Faded Short Sleeve T-shirts']/ancestor::li")
	private WebElement shortSleeveItem;

	@FindBy(name = "Submit")
	private WebElement addToCartBtn;

	@FindBy(xpath = "//a[@class='fancybox-item fancybox-close']")
	private WebElement popupImage;

	@FindBy(xpath = "//*[@id='layer_cart']//a[@class and @title='Proceed to checkout']")
	private WebElement proceedToCheckoutBtn;

	/** Functions/Actions that could be performed on Web Elements **/

	/**
	 * Function wait for My Account Page Page to load.
	 */
	public void waitForPageLoad() {
		try {
			log.info("Waiting for Account Page to load");
			waitForElementVisible(logoutBtn, 10);
		} catch (Exception e) {
			log.error("Exception occured while Waiting for Account Page to load. Error->" + e);
			e.printStackTrace();
		}
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
	 * Function Return the user first and last name.
	 * 
	 * @return : user first and last name as string
	 */
	public String getAccountName() {
		String accountName = accountNameText.getText();
		log.info("Returning account name " + accountName);
		return accountName;
	}

	/**
	 * Function return welcome text
	 * 
	 * @return - return welcome text as string
	 */
	public String getwelcomeText() {
		String welcomeText = infoAccountText.getText();
		log.info("Welcome Text is" + welcomeText);
		return welcomeText;
	}

	/**
	 * Function return true, if logout button is displayed, else false
	 * 
	 * @return
	 */
	public boolean verifyLogoutButton() {
		return logoutBtn.isDisplayed();
	}

	/**
	 * Function return true if logout button is displayed as false
	 * 
	 * @return
	 */
	public String getCurrentUrl() {
		return webDriver.getCurrentUrl();
	}

	/**
	 * Function click on sign out button and logout of current user
	 */
	public void clickSignOutBtn() {
		try {
			log.info("Click SignOut button.");
			logoutBtn.click();
		} catch (Exception e) {
			log.error("Exception occured while clicking sign out button. Error->" + e);
			e.printStackTrace();
		}
	}

	/**
	 * Function add Faded Short Sleeve T-shirt Item in cart
	 */
	public OrderPage addFadedShortSleeveTshirtInCart() {
		OrderPage op = new OrderPage(webDriver);
		try {
			log.info("Click Women Tab.");
			womenTab.click();

			JavascriptExecutor js = (JavascriptExecutor) webDriver;
			js.executeScript("arguments[0].scrollIntoView();", shortSleeveItem);

			log.info("Click Faded Shot Sleeve T-Shirt item");
			waitForElementClickable(shortSleeveItem, 10);
			shortSleeveItem.click();
			shortSleeveItem.click();

			try {
				log.info("Click Add to Cart button");
				Thread.sleep(2000);
				waitForElementVisible(addToCartBtn, 10);
				addToCartBtn.click();
			} catch (Exception e) {
				try {
					log.error("Exception generated while clicking on Submit button on checkout cart");
					waitForElementClickable(popupImage, 10);
					popupImage.click();
					log.info("Removed pop-up image, retyring to click submit button.");
					addToCartBtn.click();
				} catch (Exception ee) {
					log.error("Exception generated while clicking on Submit button - Removing pop-up image. Error-->"
							+ ee);
				}
			}

			log.info("Click Proceed to Checkout button");
			waitForElementClickable(proceedToCheckoutBtn, 10);
			proceedToCheckoutBtn.click();

			log.info("Added Faded Short Sleeve Tshirt Item in cart");
			op.waitForPageLoad();
			log.info("Returning Order Page reference.");
			return op;
		} catch (Exception e) {
			log.error("Exception occured while adding Faded Short Sleeve Tshirt Item in cart. Error->" + e);
			e.printStackTrace();
			return op;
		}

	}
}

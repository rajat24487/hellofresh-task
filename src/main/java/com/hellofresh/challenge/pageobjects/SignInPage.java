package com.hellofresh.challenge.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.hellofresh.challenge.utility.CommonFunctions;


/**
 * SignInPage Class contains web elements available on Page for registering new user and actions that could be performed on page
 * @author rajat.rastogi
 * 
 */
public class SignInPage extends BasePage {
	
	static Logger log = Logger.getLogger(SignInPage.class.getName());

	/**
	 * Parameterized Constructor for initializing SignInPage class object 
	 * @param webDriver : reference of WebDriver class
	 */
	public SignInPage(WebDriver webDriver) {		
		super(webDriver);
		log.info("In SignInPage class contructor");
	}
	
	/** Variables to identify Web Elements On SignIn Page UI**/
	@FindBy(id = "id_gender2")
	private WebElement mrsRadioBtn;

	@FindBy(id = "id_gender1")
	private WebElement mrRadioBtn;

	@FindBy(id = "customer_firstname")
	private WebElement firstnameField;	
	
	@FindBy(id = "customer_lastname")
	private WebElement lastnameField;
	
	@FindBy(id = "passwd")
	private WebElement passwordField;
	
	@FindBy(id = "days")
	private WebElement birthDaySelect;
	
	@FindBy(id = "months")
	private WebElement birthMonthSelect;
	
	@FindBy(id = "years")
	private WebElement birthYearSelect;
	
	@FindBy(id = "company")
	private WebElement companyField;
	
	@FindBy(id = "address1")
	private WebElement addressOneField;
	
	@FindBy(id = "address2")
	private WebElement addressTwoField;
	
	@FindBy(id = "city")
	private WebElement cityField;
	
	@FindBy(id = "id_state")
	private WebElement stateSelect;
	
	@FindBy(id = "postcode")
	private WebElement postalcodeField;
	
	@FindBy(id = "id_country")
	private WebElement countrySelect;
	
	@FindBy(id = "other")
	private WebElement additionalinfoField;
	
	@FindBy(id = "phone")
	private WebElement homephoneField;
	
	@FindBy(id = "phone_mobile")
	private WebElement mobileField;
	
	@FindBy(id = "alias")
	private WebElement aliasField;
	
	@FindBy(id = "submitAccount")
	private WebElement registerBtn;
	
	
	
	/** Functions/Actions that could be performed on Web Elements **/
	
	
	/**
	 * Function wait for SignIn Page to load.
	 */
	public void waitForPageLoad(){
		try {
			log.info("Waiting for SignIn Page to load");
			waitForElementVisible(mrsRadioBtn, 10);
		}
		catch (Exception e) {
			log.error("Exception occured while Waiting for SignIn Page to load. Error->"+e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Function selects mr / mrs gender
	 * @param gender : mr / mrs
	 */
	public void selectGender(String gender) {
		if(gender.toLowerCase().equals("male") || gender.toLowerCase().equals("mr")) {
			log.info("Selecting Mr.");
			mrRadioBtn.click();
		}
		else if(gender.toLowerCase().equals("female") || gender.toLowerCase().equals("mrs")) {
			log.info("Selecting Mrs.");
			mrsRadioBtn.click();
		}
		else{
			log.info("Wrong choice for gender "+gender);
			log.info("Wrong choice for gender "+gender);
		}
	}
	
	
	/**
	 * Function select Random date of birth
	 */
	public void selectRandomDateOfBirth() {
		String birthDayToSelect = CommonFunctions.getRandomStringFromSelect(birthDaySelect);
		Select birthDaySelectEle =  new Select(birthDaySelect);
		log.info("Selecting birth day:"+birthDayToSelect);
		birthDaySelectEle.selectByVisibleText(birthDayToSelect);
		
		String birthMonthToSelect = CommonFunctions.getRandomStringFromSelect(birthMonthSelect);
		Select birthMonthSelectEle =  new Select(birthMonthSelect);
		log.info("Selecting birth month:"+birthMonthToSelect);
		birthMonthSelectEle.selectByVisibleText(birthMonthToSelect);
		
		String birthYearToSelect = CommonFunctions.getRandomStringFromSelect(birthYearSelect);
		Select birthYearSelectEle =  new Select(birthYearSelect);
		log.info("Selecting birth year:"+birthYearToSelect);
		birthYearSelectEle.selectByVisibleText(birthYearToSelect);
	}
	
	
	/**
	 * Function selects random state from available state options
	 */
	public void selectRandomState() {
		String stateToSelect = CommonFunctions.getRandomStringFromSelect(stateSelect);
		Select stateSelectEle =  new Select(stateSelect);
		stateSelectEle.selectByVisibleText(stateToSelect);
		log.info("State selected is "+stateToSelect);
	}
	
	
	/**
	 * Function enter details for new user 
	 * @return - Sign/login page object
	 */
	public String usersName = "";
	public void enterRandomUserDetails() {
		try {
			String title = CommonFunctions.getValue("New_User_Details.title");
			log.info("Selecting title as "+title);
			selectGender(title);
			
			String firstName = CommonFunctions.generateRandomAlphaString(8);
			log.info("Entering First Name as "+firstName);
			firstnameField.sendKeys(firstName);
			
			String lastName = CommonFunctions.generateRandomAlphaString(9);
			log.info("Entering last Name as "+lastName);
			lastnameField.sendKeys(lastName);
			
			usersName = firstName +" "+ lastName;
			
			String password = CommonFunctions.generateRandomAlphaNumeric(6);
			log.info("Entering password as "+password);
			passwordField.sendKeys(password);
			
			log.info("Selecting Random date of Birth");
			selectRandomDateOfBirth();
			
			String companyName =  CommonFunctions.generateRandomAlphaString(10);
			log.info("Entering company name as"+companyName);
			companyField.sendKeys(companyName);
			
			String address1 = CommonFunctions.generateRandomAlphaString(10)+" , "+CommonFunctions.generateRandomAlphaNumeric(4);
			log.info("Entering address line 1 as"+address1);
			addressOneField.sendKeys(address1);
			
			String address2 = CommonFunctions.generateRandomAlphaString(7);
			log.info("Entering address line 2 as"+address2);
			addressTwoField.sendKeys(address2);
			
			String city = CommonFunctions.generateRandomAlphaString(5);
			log.info("Entering city as"+city);
			cityField.sendKeys(city);
			
			selectRandomState();
			
			String postalCode = ""+CommonFunctions.generateRandomNumeric(5);
			log.info("Entering postal as"+postalCode);
			postalcodeField.sendKeys(postalCode);
			
			String otherInfo = ""+CommonFunctions.generateRandomAlphaNumeric(10);
			log.info("Entering other info as"+otherInfo);
			additionalinfoField.sendKeys(otherInfo);
			
			String homePhone = CommonFunctions.generateRandomNumeric(10);
			log.info("Entering home phone as"+homePhone);
			homephoneField.sendKeys(homePhone);
			
			String mobilePhone = CommonFunctions.generateRandomNumeric(10);
			log.info("Entering mobile phone as"+mobilePhone);
			mobileField.sendKeys(mobilePhone);			
		}
		catch (Exception e) {
			log.error("Exception occured while entering data for new user. Error-->"+e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Function click REgister button and return My account page Object
	 * @return
	 */
	public MyAccountPage clickRegisterButton() {
	MyAccountPage mp = new MyAccountPage(webDriver);	
		try {
			registerBtn.click();
			log.info("Register new user button clicked");
			mp.waitForPageLoad();
			return mp;
		}
		catch(Exception e) {
			log.error("Exception occured while entering data for new user. Error-->"+e);
			e.printStackTrace();
			return mp;
		}
	}
}

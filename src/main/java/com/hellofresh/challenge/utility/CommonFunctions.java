package com.hellofresh.challenge.utility;

import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * Class ReadTestData has function to fetch data from json file.
 * 
 * @author rajat.rastogi
 *
 */
public class CommonFunctions {

	static Logger log = Logger.getLogger(CommonFunctions.class.getName());

	static String testDataFile = "src" + File.separator + "test" + File.separator + "resources" + File.separator
			+ "TestData.json";
	static String alphanumChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	static String numChars = "123456789";
	static String alphaChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	/**
	 * Function return value from json test data file for the specified key
	 * 
	 * @param key : key for which value is t be fetched example
	 *            'Existing_user.username'
	 * @return - Value for the specified key.
	 */
	public static String getValue(String key) {
		String[] splitKey = key.split("\\.");

		JSONParser jParser = new JSONParser();
		JSONObject jObj = null;
		try {
			jObj = (JSONObject) jParser.parse(new FileReader(testDataFile));
		} catch (Exception e) {
			log.error("Error while reading test data file TestData.json .Error-->" + e);
		}

		log.info("Json Object is " + jObj);
		String valueToReturn = "";

		for (int i = 0; i < splitKey.length; i++) {
			if (i == splitKey.length - 1) {
				valueToReturn = (String) jObj.get(splitKey[i]).toString();
				break;
			}
			jObj = (JSONObject) jObj.get(splitKey[i]);
			log.info("Json Object after iteration " + i);
		}
		log.info("Value fetched for key=>" + key + " is :" + valueToReturn);
		return valueToReturn;
	}

	/**
	 * Return random string combination of random alphabets and numbers of specified
	 * length
	 * 
	 * @param length
	 * @return
	 */
	public static String generateRandomAlphaNumeric(int length) {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		while (sb.length() < length) {
			int index = (int) (random.nextFloat() * alphanumChars.length());
			sb.append(alphanumChars.charAt(index));
		}
		String returnAlphaNum = sb.toString();
		log.info("Returning aplha number as" + returnAlphaNum);
		return returnAlphaNum;
	}

	/**
	 * Function return random numeric number of specified length
	 * 
	 * @param length
	 * @return
	 */
	public static String generateRandomNumeric(int length) {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		while (sb.length() < length) {
			int index = (int) (random.nextFloat() * numChars.length());
			sb.append(numChars.charAt(index));
		}
		String returnNum = sb.toString();
		log.info("Returning aplha number as" + returnNum);
		return returnNum;
	}

	/**
	 * Function return random string of specified length
	 * 
	 * @param length
	 * @return
	 */
	public static String generateRandomAlphaString(int length) {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		while (sb.length() < length) {
			int index = (int) (random.nextFloat() * alphaChars.length());
			sb.append(alphaChars.charAt(index));
		}
		String returnAlpha = sb.toString();
		log.info("Returning aplha number as" + returnAlpha);
		return returnAlpha;
	}

	/**
	 * Function return a value from set of String array.
	 * 
	 * @param values - Array of String values.
	 * @return
	 */
	public static String returnRandomValueFromStringArray(String[] values) {
		Random r = new Random();
		int randomNumber = r.nextInt(values.length);
		String returnValue = values[randomNumber];
		log.info("Returning random string from set of string as " + returnValue);
		return returnValue;
	}

	/**
	 * Function returns a random value from available set of values for a Select Web
	 * element
	 * 
	 * @param element - Select Web HTML element
	 * @return - Random select value
	 */
	public static String getRandomStringFromSelect(WebElement element) {
		Select selectEle = new Select(element);
		List<WebElement> elementList = selectEle.getOptions();
		List<String> elementsValueList = new LinkedList<String>();
		for (WebElement ele : elementList) {
			elementsValueList.add(ele.getText());
		}
		elementsValueList.remove("-");
		String[] elementsValueArray = elementsValueList.toArray(new String[elementsValueList.size()]);
		String stringToReturn = returnRandomValueFromStringArray(elementsValueArray);
		return stringToReturn;
	}

}
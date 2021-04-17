package com.finx.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.helpers.ISO8601DateFormat;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bson.Document;

//import org.openqa.grid.common.JSONConfigurationUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import jxl.read.biff.BiffException;

import com.tavant.base.DriverFactory;
import com.tavant.base.WebPage;
import com.tavant.kwutils.KWVariables;
import com.tavant.utils.TwfException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.DeserializationFeature;

//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
/*import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
//import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;*/

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.ISO8601Utils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoDatabase;


public class Util extends WebPage {

	String userIDObject = "//fin-exp-anonymous/user-management//register-modal-app//input[@id='reg_userId']\r\n";
	String mongoDBHost = "10.209.0.113";
	String outlookMailListBody = "//div[@id='divVLVIL']/div[@id='gc']";
	String accountCreationMailSubject = "Account Creation and Verification Link for FinExperience";
	String recoverPasswordMailSubject = "FinExperince Recover Password Assistant";
	static String RefernceIDValue;
	static boolean loanOnboarded=false;
	EncompassData encom=new EncompassData();
	static HashMap<Integer,HashMap<String,String>> noLiabilitiesMap=new HashMap<>();
	/**
	 * Add Failure Reason to Report
	 * 
	 * @param description
	 * @param actualValue
	 * @param expectedValue
	 * @return
	 * @throws TwfException
	 */
	public static void addExceptionToReport(String description, String actualValue, String expectedValue)
			throws TwfException {
		throw new TwfException(description + " :<font color=\"solid orange\">  Actual :[" + actualValue
				+ "]</font><font color=\"EE7600\"> Expected :[" + expectedValue + "]</font><br> <b>Step Details:</b> "
				+ "<br>");
	}

	public static boolean isValidDate(String inDate) throws Exception {
        try{
    	if(inDate.split("/").length==3 && inDate.split(":").length==3)
        return true;
    	
        }catch (Exception e) {
			// TODO: handle exception
		}
        return false;
    }
	
	public String formatEncompassFetcheddate(String date){
	     date = date.split(" ")[0];
	     String month = date.split("/")[0];
	     String day = date.split("/")[1];
	     String year = date.split("/")[2];
	      if(month.length()==1)
	    	  month = "0"+month;
	      if(day.length()==1)
	    	  day = "0"+day;
	     return year+"-"+month+"-"+day;
	    	
	    }
	/**
	 *
	 * @throws TwfException
	 *             wait for element.
	 * @throws IOException
	 * @throws BiffException
	 */
	public void waitForElement(String element) throws TwfException, BiffException, IOException {
		WebElement actual_element = getElementByUsing(element);
		(new WebDriverWait(DriverFactory.getDriver(), 180)) // timeout after 60
				.until(ExpectedConditions.visibilityOf(actual_element));
	}

	/**
	 * Method Name: waitForElements Purpose: To wait for elements to be
	 * displayed.. Webelement is passed as argument
	 * 
	 * @param wb
	 * @throws TwfException
	 * @throws BiffException
	 * @throws IOException
	 */
	public void waitForElements(WebElement wb) throws TwfException, BiffException, IOException {
		waitForElement(wb, 120000);
	}

	public static Boolean waitForElementToBeClikable(WebElement element, long timeInSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), timeInSeconds);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public Boolean waitForElementToBeInVisible(By element, long timeInSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), timeInSeconds);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static void click(String element) throws Exception{
		try{
			WebDriver driver=DriverFactory.getDriver();
			waitForElementToBeClikable(driver.findElement(By.xpath(element)),30);
			driver.findElement(By.xpath(element)).click();
		}
		catch(Exception e){
			e.printStackTrace();
			addExceptionToReport("Error in clicking element", "element", "");
		}
	}

	public static String getText(String element) throws Exception{
		String text="";
		try{
			WebDriver driver=DriverFactory.getDriver();
			waitForElementToBeClikable(driver.findElement(By.xpath(element)),20);
			text=driver.findElement(By.xpath(element)).getText();
		}
		catch(Exception e){
			e.printStackTrace();
			addExceptionToReport("Error in getting text from element", "element", "");
		}
		return text;
	}

	public static String getText(WebDriver driver,String element) throws Throwable{
        try {
        	Thread.sleep(1000);
            return driver.findElement(By.xpath(element)).getText();
        } catch (Exception e) {
            e.printStackTrace();
            addExceptionToReport("Exception in getting Text for "+element,"","");
        }
        return "";
    }

	public static String getAttribute(String attributeName,String element) throws Exception{
		WebDriver driver=DriverFactory.getDriver();
		String text="";
		try{
			waitForElementToBeClikable(driver.findElement(By.xpath(element)),20);
			text=driver.findElement(By.xpath(element)).getAttribute(attributeName);
			System.out.println("Text by attribute>>>"+text);
		}
		catch(Exception e){
			e.printStackTrace();
			addExceptionToReport("Error in getting attribute from element", "element", "");
		}
		return text;
	}

	public static void sendText(String element, String text) throws Exception{
		WebDriver driver=DriverFactory.getDriver();
		try{
			waitForElementToBeClikable(driver.findElement(By.xpath(element)),20);
			driver.findElement(By.xpath(element)).clear();
			driver.findElement(By.xpath(element)).sendKeys(text);
		}catch(Exception e){
			e.printStackTrace();
			addExceptionToReport("Error in Send text to element", "element", "");
		}
	}
	
	public Boolean verifyElementIsDisplayed(String elementString) throws TwfException {
		WebDriver driver = DriverFactory.getDriver();

		try {
			WebElement element = driver.findElement(By.xpath(elementString));
			if (element.isDisplayed()) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}

		return false;
	}
	
	public static void javaScriptClick(WebDriver driver, String xpath) throws Exception {
		try {
			WebElement ele=driver.findElement(By.xpath(xpath));
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click();", ele);
		} catch (Exception e) {
			e.printStackTrace();
			addExceptionToReport("Exception while clicking on element using javascript click"+xpath,"","");
		}
	}
	
	public static boolean isElementNotPresent(WebDriver driver, String xpath)throws Exception{
		System.out.println("Inside isElementNotPresent");
		if(driver.findElements(By.xpath(xpath)).size()>0){
			return false;
		}
		return true;
	}
	
//	public static void sendTextfromActionSteps(WebElement webElement, String text) throws Exception{
//		WebDriver driver=DriverFactory.getDriver();
//		try{
//			waitForElementToBeClikable(webElement,20);
//			webElement.clear();
//			webElement.sendKeys(text);
//		}catch(Exception e){
//			e.printStackTrace();
//			addExceptionToReport("Error in Send text to element", "element", "");
//		}
//	}
	/**
	 * Method NAme: refreshPage Purpose: To perform refresh page action
	 * 
	 * @throws TwfException
	 * @throws InterruptedException
	 */
	public void refreshPage() throws TwfException, InterruptedException {
		WebDriver driver = DriverFactory.getDriver();
		driver.navigate().refresh();
	}

	/**
	 * Method Name: generateRandomString Purpose: To generate Random String
	 * 
	 * @param args
	 * @throws TwfException
	 * @throws BiffException
	 * @throws IOException
	 */
	public void generateRandomString(String args) throws TwfException, BiffException, IOException {
		WebDriver driver = DriverFactory.getDriver();
		WebElement Element = driver.findElement(By.id("reg_userId"));
		String value = "Test" + System.currentTimeMillis() + "@gmail.com";
		Element.clear();
		Element.sendKeys(value);

	}

	/******************************************************************************
	 * 
	 * Inactive message verifying
	 *
	 * 
	 * 
	 * @author Kapil.Yadav
	 * 
	 * @version 0.1
	 * 
	 * @since 18-May-2017
	 * 
	 * @return None
	 * 
	 * @throws TwfException
	 * 
	 * @throws InterruptedException
	 * 
	 * @throws IOException
	 * 
	 * @throws BiffException
	 * 
	 ******************************************************************************/
	public void verifyTextandLinkText(String ExpectedText) throws InterruptedException, Exception {
		WebElement dateWidget = getElementByUsing("Finexp_Inactiveuser_LoginValidation_msg");
		String actuaclResult = dateWidget.getText().trim();
		if (!actuaclResult.contains(ExpectedText)) {
			addExceptionToReport("Test Failed", dateWidget.getText().trim(), ExpectedText);
		}

	}

	/**
	 * Method Name: randomValue Purpose: To create random value
	 * 
	 * @throws TwfException
	 * @throws InterruptedException
	 */
	public void randomValue() throws TwfException, InterruptedException {
		UUID.randomUUID().toString();

	}

	/**
	 * Method NAme:verifyText Purpose: To compate expected with actual text
	 * 
	 * @param ExpectedText
	 * @throws InterruptedException
	 * @throws Exception
	 */
	public void verifyText(String ExpectedText) throws InterruptedException, Exception {
		WebElement dateWidget = getElementByUsing("FinExp_SSO_CSR_landingPage");
		String actuaclResult = dateWidget.getText().trim();
		if (!actuaclResult.contains(ExpectedText)) {
			addExceptionToReport("Test Failed", dateWidget.getText().trim(), ExpectedText);
		}

	}

	/**
	 * Method Name: scrollToElement Purpose: To scroll to view element
	 * 
	 * @param ele
	 * @throws BiffException
	 * @throws IOException
	 * @throws TwfException
	 * @throws InterruptedException
	 */
	public void scrollToElement(WebElement ele) throws BiffException, IOException, TwfException, InterruptedException {
		WebDriver driver = DriverFactory.getDriver();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", ele);

	}

	/**
	 * Method Name: checkIfReachMeButtonInCallMePageIsDisabled Purpose: To check
	 * if Reach me button is disabled in Call Me Page
	 * 
	 * @param test
	 * @throws Exception
	 */
	public void checkIfReachMeButtonInCallMePageIsDisabled(String test) throws Exception {
		WebDriver driver = DriverFactory.getDriver();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		Object aa = executor.executeScript(
				"var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;",
				driver.findElement(By.xpath("//button[contains(.,'Reach Me')]")));
		if (aa.toString().contains("disabled") == false) {
			addExceptionToReport("Reach Me Button is enabled with Invalid values in fields.", "", "");
		}

	}

	/**
	 * Method Name: enterUserEmailId Purpose: To enter User email ID by
	 * generating a random number
	 * 
	 * @param test
	 * @throws Exception
	 */
//	public void enterUserEmailId(String test) throws Exception {
//		WebDriver driver = DriverFactory.getDriver();
//		String emailId = GenerateData.generateEmail(7);
//		driver.findElement(By.xpath(userIDObject)).sendKeys(emailId);
//
//	}

	/**
	 * Method Name: checkIfCreatAccountButtonInCreateAccountPageIsDisabled
	 * Purpose:To check if create Accouunt Button in create account page is
	 * disabled
	 * 
	 * @param test
	 * @throws Exception
	 */
	public void checkIfCreatAccountButtonInCreateAccountPageIsDisabled(String test) throws Exception {
		WebDriver driver = DriverFactory.getDriver();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		Object aa = executor.executeScript(
				"var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;",
				driver.findElement(By.xpath("//button[contains(.,'Reach Me')]")));
		if (aa.toString().contains("disabled") == false) {
			addExceptionToReport("Reach Me Button is enabled with Invalid values in fields.", "", "");
		}

	}

	/**
	 * Method: testInvalidMessage Purpose: To test Invalid Message in login page
	 * Comments: Create a login class file & place the same over there Also this
	 * method is not used anywhere.. the same can be removed
	 * 
	 * @param test
	 * @throws Exception
	 */
	public void testInvalidMessage(String test) throws Exception {

		Thread.sleep(5000);

		WebDriver driver = DriverFactory.getDriver();
		driver.findElement(By.id("login_userId")).sendKeys("");
		driver.findElement(By.id("login_password")).sendKeys("");
		driver.findElement(By.xpath("//login-modal-app/div//dynamic-form//div[@class[contains(.,'login')]]//button"))
				.click();
		String displayedtext = driver
				.findElement(By.xpath("//(//div[@class[contains(.,'alert alert-danger')]]/strong)[1] ")).getText();
	}

	/**
	 * Method Name: createActiveUserinQAAWS Purpose: To create an active user in
	 * QA AWS envrionment & reset the login attempt to 0
	 * 
	 * @param user,
	 *            status
	 */
	private void createUser(String userDetails, String userType) throws Exception {
		try {
			String[] data = userDetails.split("-");
			String Db_url = data[0];
			String user = data[1];
			String Db_Collection = KWVariables.getVariables().get("collectionName_dev");

			ObjectMapper mapper = new ObjectMapper();
			MongoClient mongoClient = new MongoClient(Db_url, 27017);
			DB test = mongoClient.getDB(Db_Collection);
			DBCollection dbc = test.getCollection("users");

			BasicDBObject query = new BasicDBObject();
			BasicDBObject query1 = new BasicDBObject();
			BasicDBObject query2 = new BasicDBObject();

			query.put("userId", user);
			query1.put("userStatus", userType);

			if (userType.trim().equalsIgnoreCase("ACTIVE")) {
				query2.put("failloginAttemptCount", 0);
			} else if (userType.trim().equalsIgnoreCase("LOCKED")) {
				query2.put("failloginAttemptCount", 3);
			}

			DBCursor dbcrsr = dbc.find(query);

			int size = dbcrsr.count();
			String createUserJson = "{\"password\" :\"we5csJrtgoSDb1zyKYkp4aN7BYu8SqnTkYU6/wSxAotUt1+9rDk=y52bWBKC\",\"userStatus\" : \"%f\",\"failloginAttemptCount\" : 0,\"name\" : \"NewUser\",\"econsent\" : true,\"mobileNo\" : \"465-464-4554\",\"userId\" : \"%s\"}";

			if (size == 0) {

				Map<String, Object> map = new HashMap<String, Object>();
				String revisedJson = createUserJson.replace("%s", user);
				map = mapper.readValue(revisedJson.replace("%f", userType.toUpperCase()),
						new TypeReference<Map<String, Object>>() {
						});
				BasicDBObject basicDBObj = new BasicDBObject();
				basicDBObj.putAll(map);
				dbc.insert(basicDBObj);

			} else {
				dbc.findAndModify(query, null, null, false, new BasicDBObject().append("$set", query1), true, false);
				dbc.findAndModify(query, null, null, false, new BasicDBObject().append("$set", query2), true, false);

			}
			mongoClient.close();

		} catch (Exception e) {

		}
	}

	/**
	 * Method Name: createActiveUser Purpose: To create Active user in Mongo DB
	 * 
	 * @param expectedUser
	 * @throws Exception
	 */

	public void createActiveUser(String user_variable) throws Exception {
		String expectedUserdetails = KWVariables.getVariables().get(user_variable);
		createUserWithDate(expectedUserdetails, "ACTIVE");
	}

	/**
	 * Method NAme: deActivateUser Purpose: to deactivate the user the DB
	 * 
	 * @param expectedUser
	 * @throws Exception
	 */
	public void deActivateUser(String user_variable) throws Exception {
		String expectedUserdetails = KWVariables.getVariables().get(user_variable);
		createUserWithDate(expectedUserdetails, "DEACTIVATED");
	}

	/**
	 * Method Name: createInactiveUser Purpose: To createInactive User in DB
	 * 
	 * @param expectedUser
	 * @throws Exception
	 */
	public void createInactiveUser(String user_variable) throws Exception {
		String expectedUserdetails = KWVariables.getVariables().get(user_variable);
		createUserWithDate(expectedUserdetails, "INACTIVE");
	}

	/**
	 * Method Name: lockTheUser Purpose: To lock the user
	 * 
	 * @param expectedUser
	 * @throws Exception
	 */
	public void lockTheUser(String user_variable) throws Exception {
		String expectedUserdetails = KWVariables.getVariables().get(user_variable);
		createUserWithDate(expectedUserdetails, "LOCKED");
	}

	/**
	 * Method Name: currencyConverter Purpose: for deleting dollar symbol and
	 * commas from digits
	 * 
	 * @throws TwfException
	 * @throws IOException
	 * @throws BiffException
	 * @throws InterruptedException
	 * 
	 * @throws Exception
	 */

	public long currencyConverter(String incomeAmount1) {
		long formatedIncome;

		incomeAmount1 = incomeAmount1.replaceAll(",", "").replace("$", "").trim();
		formatedIncome = Long.parseLong(incomeAmount1);

		return formatedIncome;

	}

	/**
	 * Method Name: activateUserByClickLinkInMail Purpose: To ativate the user
	 * by clicking activate link in mail
	 * 
	 * @param userName
	 * @throws Exception
	 */
	public void activateUserByClickLinkInMail(String user_variable) throws Exception {

		String userName = KWVariables.getVariables().get(user_variable);
		WebDriver driver = DriverFactory.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, 10);

		String outlookURL = KWVariables.getVariables().get("outlookUrl");
		String outlookPassword = KWVariables.getVariables().get("outlookPassword");

		driver.get(outlookURL);
		Thread.sleep(3000);
		// Login to outlook
		getElementByUsing("outlookUserNameField").sendKeys(userName);
		getElementByUsing("outlookPasswordField").sendKeys(outlookPassword);
		getElementByUsing("outLookSignInButton").click();

		// Check by Email Subject
		wait.until(ExpectedConditions.visibilityOf(getElementByUsing("outlookSearchBox")));
		getElementByUsing("outlookSearchBox").sendKeys(accountCreationMailSubject);
		getElementByUsing("outlookSearchIcon").click();

		// Fetch the mail box ordering value
		String displayedOrderType = getElementByUsing("outlookMailOrderingText").getText();
		if (displayedOrderType.trim().equalsIgnoreCase("Oldest on Top")) {
			getElementByUsing("outlookMailOrderingText").click();
		}

		Thread.sleep(3000);
		try {
			int mailCount = driver.findElements(By.xpath(outlookMailListBody)).size();
			if (mailCount == 0) {
				addExceptionToReport("No mails are delivered", "", "");
			}

			String parentWindow = driver.getWindowHandle();
			getElementByUsing("outlookFirstMailObject").click();
			Thread.sleep(2000);
			getElementByUsing("outLookMailVerifyMyAccountLink").click();
			Set<String> windowHandles = driver.getWindowHandles();
			for (String windowHandle : windowHandles) {
				if (windowHandle.trim().equalsIgnoreCase(parentWindow) == false) {
					driver.switchTo().window(windowHandle);
					break;
				}
			}

			driver.close();

		} catch (Exception e) {

		}

	}

	/**
	 * Method Name: resetPasswordByByClickLinkInMail Purpose: To resetPassword
	 * Byclicking link in mail
	 * 
	 * @param userName
	 * @throws Exception
	 */
	public void resetPasswordByByClickLinkInMail(String user_variable) throws Exception {

		String userName = KWVariables.getVariables().get(user_variable);
		WebDriver driver = DriverFactory.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, 10);

		System.out.println("Driver before switch >>>>" + driver.getWindowHandle());
		String parentWindow = driver.getWindowHandle();
		String outlookURL = KWVariables.getVariables().get("outlookUrl");
		String outlookPassword = KWVariables.getVariables().get("outlookPassword");

		driver.get(outlookURL);

		System.out.println("Testing flow");
		// Login to outlook
		wait.until(ExpectedConditions.visibilityOf(getElementByUsing("outlookUserNameField")));
		getElementByUsing("outlookUserNameField").sendKeys(userName);
		getElementByUsing("outlookPasswordField").sendKeys(outlookPassword);
		getElementByUsing("outLookSignInButton").click();

		Thread.sleep(5000);
		// Check by Email Subject
		wait.until(ExpectedConditions.visibilityOf(getElementByUsing("outlookSearchBox")));
		getElementByUsing("outlookSearchBox").sendKeys(recoverPasswordMailSubject);
		getElementByUsing("outlookSearchIcon").click();

		// Fetch the mail box ordering value
		String displayedOrderType = getElementByUsing("outlookMailOrderingText").getText();
		if (displayedOrderType.trim().equalsIgnoreCase("Oldest on Top")) {
			getElementByUsing("outlookMailOrderingText").click();
		}

		Thread.sleep(3000);
		try {
			int mailCount = driver.findElements(By.xpath(outlookMailListBody)).size();
			if (mailCount == 0) {
				addExceptionToReport("No mails are delivered", "", "");
			}

			Thread.sleep(2000);
			driver.findElement(By.linkText("Setup new password")).click();
			Thread.sleep(5000);

			Set<String> windowHandles = driver.getWindowHandles();
			for (String windowHandle : windowHandles) {
				if (windowHandle.trim().equalsIgnoreCase(parentWindow) == false) {
					driver.switchTo().window(windowHandle);
					break;
				}
			}

		} catch (Exception e) {

		}

	}

	/**
	 * Method Name: createNewLOUser Purpose: To create New LO User in DB
	 * 
	 * @param args
	 * @throws Exception
	 */
	public void createNewLOUser(String args) throws Exception {
		try {

			String create_data = KWVariables.getVariables().get(args);
			String Db_Collection2 = KWVariables.getVariables().get("collectionName_mockdb");
			System.out.println("DB name --> " + Db_Collection2);

			String[] data2 = create_data.split("-");
			String Db_url = data2[0];
			String user = data2[1];
			String user_name = user.trim().split("@")[0];

			ObjectMapper mapper = new ObjectMapper();
			MongoClient mongoClient = new MongoClient(Db_url, 27017);
			@SuppressWarnings("deprecation")
			DB test2 = mongoClient.getDB(Db_Collection2);
			DBCollection dbc = test2.getCollection("sso_users");
			DBCollection dbc2 = test2.getCollection("los_loanofficer");

			// Query for sso_users table
			BasicDBObject query = new BasicDBObject();
			BasicDBObject query1 = new BasicDBObject();

			query.put("userId", user);
			query1.put("password", "password");

			// Query for los_laonofficer table
			BasicDBObject query2 = new BasicDBObject();

			query2.put("userId", user);

			// Find user in sso_users table
			DBCursor dbcrsr = dbc.find(query);

			// JSON creation/updation for sso_users table
			int size = dbcrsr.count();

			String createUserJson = "{\"password\" :\"password\",\"userId\" : \"%s\"}";

			// Create new record for the user in sso_users table
			if (size == 0) {
				Map<String, Object> map = new HashMap<String, Object>();
				map = mapper.readValue(createUserJson.replace("%s", user), new TypeReference<Map<String, String>>() {
				});
				BasicDBObject basicDBObj = new BasicDBObject();
				basicDBObj.putAll(map);
				dbc.insert(basicDBObj);

				// Modify existing record for the user in sso_users table
			} else {
				dbc.findAndModify(query, null, null, false, new BasicDBObject().append("$set", query1), true, false);

			}

			// Find user in los-loan_officer table
			DBCursor dbcrsr1 = dbc2.find(query2);

			// JSON creation/updation for los_loanofficer table
			int size2 = dbcrsr1.count();
			String[] firstname1 = user.split("@");
			String firstname = firstname1[0].replace(".", "");
			System.out.println("firstname: " + firstname);
			String createUserJson2 = "{\"firstName\" : \"" + firstname
					+ "\",\"middleName\" : \"AA\",\"lastName\" : \"testLO\",\"userId\" : \"%a\",\"mobileNo\" : \"987-067-0000\",\"officePhoneNo\" : \"123-123-8908\",\"fax\" : \"123-123-1567\",\"streetAddressLine1\" : \"Los Angelas\",\"streetAddressLine2\" : \"Addr 2\",\"state\" : \"California\",\"city\" : \"Los Angelas\",\"county\" : \"Los Angelas\",\"zipcode\" : \"90001\",\"designation\" : \"LO Officer\",\"branchName\" : \"LO County\",\"nmlsId\" : \"90001\"}";

			// Create new record for the user in los_loanofficer table
			if (size2 == 0) {
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1 = mapper.readValue(createUserJson2.replace("%a", user), new TypeReference<Map<String, String>>() {
				});
				BasicDBObject basicDBObj2 = new BasicDBObject();
				basicDBObj2.putAll(map1);
				dbc2.insert(basicDBObj2);

				// Modify existing record for the user in los_loanofficer table
			} else {
				dbc.findAndModify(query, null, null, false, new BasicDBObject().append("$set", query2), true, false);

			}

			mongoClient.close();

		} catch (Exception e) {

		}
	}

	/**
	 * Method Name: removeExistingLOUser Purpose: To remove LO user from DB
	 * Purpose: To remove LO user from db Comment: This code needs refactoring
	 * 
	 * @param args
	 * @throws Exception
	 */
	public void removeExistingLOUser(String args) throws Exception {
		try {

			String data = KWVariables.getVariables().get(args);
			String Db_Collection1 = KWVariables.getVariables().get("collectionName_dev");
			String Db_Collection2 = KWVariables.getVariables().get("collectionName_lodb");
			String Db_Collection3 = KWVariables.getVariables().get("collectionName_mockdb");

			String[] data2 = data.split("-");
			String Db_url = data2[0];
			String user = data2[1];

			MongoClient mongoClient = new MongoClient(Db_url, 27017);
			@SuppressWarnings("deprecation")
			DB test1 = mongoClient.getDB(Db_Collection1);
			@SuppressWarnings("deprecation")
			DB test2 = mongoClient.getDB(Db_Collection2);
			@SuppressWarnings("deprecation")
			DB test3 = mongoClient.getDB(Db_Collection3);

			DBCollection dbc_sso_users = test3.getCollection("sso_users");
			DBCollection dbc_los_loanofficer = test3.getCollection("los_loanofficer");
			DBCollection dbc_loanofficer_published = test3.getCollection("loanofficer_published");
			DBCollection dbc_loanofficer = test2.getCollection("loanofficer");
			DBCollection dbc_internal_users = test1.getCollection("internal_users");

			DBCollection dbc_user_request = (DBCollection) test1.getCollection("user_request");

			// Query for loanofficer table
			BasicDBObject query = new BasicDBObject();
			query.put("userId", user);

			// Find user in loanofficer table
			DBCursor dbcrsr = dbc_loanofficer.find(query);
			int count = dbcrsr.count();

			// Remove record of the user from loanofficer table
			if (count == 1) {
				dbc_loanofficer.remove(query);
			}

			// Find user in internal users table
			DBCursor dbcrsr2 = dbc_internal_users.find(query);
			int count2 = dbcrsr2.count();

			// Remove record of the user from internal users table
		 if (count2 == 1) {
				dbc_internal_users.remove(query);
			}

			// Find user in sso_users table
			DBCursor dbcrsr3 = dbc_sso_users.find(query);
			int count3 = dbcrsr3.count();

			// Remove record of the user from sso_users table
		 if (count3 == 1) {
				dbc_sso_users.remove(query);
			}

			// Find user in los_loanofficer table
			DBCursor dbcrsr4 = dbc_los_loanofficer.find(query);
			int count4 = dbcrsr4.count();

			// Remove record of the user from los_loanofficer table
		 if (count4 == 1) {
				dbc_los_loanofficer.remove(query);
			}

			// Find user in user_request table
			DBCursor dbcrsr5 = dbc_user_request.find(query);
			int count5 = dbcrsr5.count();

			// Remove record of the user from user_request table
		if (count5 == 1) {
				dbc_user_request.remove(query);
			}

			// Find user in user_request table
			DBCursor dbcrsr6 = dbc_loanofficer_published.find(query);
			int count6 = dbcrsr6.count();

			// Remove record of the user from user_request table
		 if (count6 == 1) {
				dbc_user_request.remove(query);
			}

			mongoClient.close();

		} catch (Exception e) {

		}
	}

	/**
	 * Method Name: removeExistingConsumer PurposE: To remove existing consumer
	 * from table
	 * 
	 * @param args
	 * @throws Exception
	 */
	public void removeExistingConsumer(String args) throws Exception {
		try {

			String data = KWVariables.getVariables().get(args);
			String Db_Collection = KWVariables.getVariables().get("collectionName_dev");

			String[] data2 = data.split("-");
			String Db_url = data2[0];
			String consumer = data2[1];

			MongoClient mongoClient = new MongoClient(Db_url, 27017);
			@SuppressWarnings("deprecation")
			DB test = mongoClient.getDB(Db_Collection);
			DBCollection dbc_users = test.getCollection("users");

			// Query for users table
			BasicDBObject query = new BasicDBObject();

			query.put("userId", consumer);

			// Find user in users table
			DBCursor dbcrsr = dbc_users.find(query);
			int count = dbcrsr.count();

			// Remove record of the user from users table
		 if (count == 1) {
				dbc_users.remove(query);
			}

			mongoClient.close();

		} catch (Exception e) {

		}
	}

	public void removeExistingOutlookEmails(String user_variable) throws Exception {

		String userName = KWVariables.getVariables().get(user_variable);
		WebDriver driver = DriverFactory.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, 10);

		String outlookURL = KWVariables.getVariables().get("outlookUrl");
		String outlookPassword = KWVariables.getVariables().get("outlookPassword");

		driver.get(outlookURL);

		// Login to outlook
		wait.until(ExpectedConditions.visibilityOf(getElementByUsing("outlookUserNameField")));
		getElementByUsing("outlookUserNameField").sendKeys(userName);
		getElementByUsing("outlookPasswordField").sendKeys(outlookPassword);
		getElementByUsing("outLookSignInButton").click();

		int mailCount = driver.findElements(By.xpath(".//*[@id='divSubject']")).size();

		Actions clickAction = new Actions(driver);
		clickAction.contextClick(getElementByUsing("outLookMailInbox")).build().perform();
		driver.findElement(By.xpath("html/body/div[1]/div[24]/span[2]")).click();
		driver.findElement(By.xpath("html/body/div[20]/div/div[3]/div[2]/button[1]")).click();

		driver.close();

	}

	public void verifyErrorMessageInProfilePage(String expectedMessage) throws Exception {
		WebDriver driver = DriverFactory.getDriver();
		String expMsg = KWVariables.getVariables().get(expectedMessage);
		String displayedMessage = getElementByUsing("FinExp_UpdateProfile_UpdateMobile_ConfirmationMsg").getText();
		if (displayedMessage.contains(expMsg) == false) {
			addExceptionToReport("Expected Error Message is not displayed when Mobile Number is updated.",
					displayedMessage, expMsg);
		}

	}

	/**
	 * Method Name: createUserWithDate Purpose: To create user with required
	 * status in mongoDB
	 * 
	 * @param user,
	 *            status
	 * @throws Exception
	 */

	private void createUserWithDate(String userDetails, String userType) throws Exception {
		try {

			String[] data = userDetails.split("-");
			String Db_url = data[0];
			String user = data[1];
			String Db_Collection = KWVariables.getVariables().get("collectionName_dev");
			String userPassword = KWVariables.getVariables().get("newuser_password");
			String userProfileName = KWVariables.getVariables().get("newuser_profilename");

			ObjectMapper mapper = new ObjectMapper();
			MongoClient mongoClient = new MongoClient(Db_url, 27017);
			@SuppressWarnings("deprecation")
			DB abc = mongoClient.getDB(Db_Collection);
			Set<String> collections = abc.getCollectionNames();

			for (String collectionName : collections) {
			}
			DBCollection dbc = abc.getCollection("users");

			BasicDBObject query = new BasicDBObject();
			BasicDBObject query1 = new BasicDBObject();
			BasicDBObject query2 = new BasicDBObject();
			BasicDBObject query3 = new BasicDBObject();

			query.put("userId", user);
			query1.put("userStatus", userType);

			if (userType.trim().equalsIgnoreCase("ACTIVE")) {
				query2.put("failloginAttemptCount", 0);
			} else if (userType.trim().equalsIgnoreCase("LOCKED")) {
				query2.put("failloginAttemptCount", 3);
			}
			// Getting date in required format
			Date date = new Date();

			// Preparing query to update existing records
			query3.put("updatedDate", date);

			DBCursor dbcrsr = dbc.find(query);

			int size = dbcrsr.count();

			String createUserJson = "{\"password\" :\"%userPassword\", \"userStatus\" : \"%f\",\"failloginAttemptCount\" : 0,\"name\" : \"%userProfileName\",\"econsent\" : true,\"mobileNo\" : \"465-464-4554\",\"userId\" : \"%s\"}";

			if (size == 0) {

				Map<String, Object> map = new HashMap<String, Object>();
				String revisedJson = createUserJson.replace("%s", user).replace("%userPassword", userPassword)
						.replace("%userProfileName", userProfileName).replace("%f", userType.toUpperCase());

				try {
					// convert JSON string to Map
					map = mapper.readValue(revisedJson, new TypeReference<HashMap<String, Object>>() {
					});
					map.put("createdDate", date);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("map--------------" + map);
				try {
					BasicDBObject basicDBObj = new BasicDBObject();
					basicDBObj.putAll(map);
					dbc.insert(basicDBObj);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			else {

				dbc.findAndModify(query, null, null, false, new BasicDBObject().append("$set", query1), true, false);
				dbc.findAndModify(query, null, null, false, new BasicDBObject().append("$set", query2), true, false);
				dbc.findAndModify(query, null, null, false, new BasicDBObject().append("$set", query3), true, false);

			}
			mongoClient.close();

		} catch (Exception e) {

		}
	}

	/**
	 * Method Name: createUserWithDate Purpose: To create user with required
	 * status in mongoDB
	 * 
	 * @param user,
	 *            status
	 * @throws Exception
	 */

	public void removeConsumerAuditTrail(String args) throws Exception {
		try {

			String data = KWVariables.getVariables().get(args);
			String Db_Collection = KWVariables.getVariables().get("collectionName_audittrail");

			String[] data2 = data.split("-");
			String Db_url = data2[0];
			String consumer = data2[1];

			MongoClient mongoClient = new MongoClient(Db_url, 27017);
			@SuppressWarnings("deprecation")
			DB test = mongoClient.getDB(Db_Collection);
			DBCollection dbc_users = test.getCollection("audittrail");

			// Query for users table
			BasicDBObject query = new BasicDBObject();

			query.put("userId", consumer);

			// Find user in users table
			DBCursor dbcrsr = dbc_users.find(query);
			int count = dbcrsr.count();

			System.out.println("count of records is ---> " + count);
			for (int i = count; i > 0; i--) {
				dbc_users.remove(query);
			}

			mongoClient.close();

		} catch (Exception e) {

		}
	}

	public void getLoanNumber() throws TwfException {
		WebDriver driver = DriverFactory.getDriver();
		String loanNumber = driver.findElement(By.xpath("(//div[@class='loan-number'])[1]")).getText();
	}

	public String mailinator(String token) throws Exception {
		String expectedToken = "PM-" + token;

		WebDriver driver = DriverFactory.getDriver();

//		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
//		((JavascriptExecutor)driver).executeScript("window.open()");
//		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
//		driver.switchTo().window(tabs.get(1));
//		driver.get("https://www.mailinator.com");
  
		String envType = KWVariables.getVariables().get("MailBoxServiceProvider");

		String MailBoxUsername = KWVariables.getVariables().get("MailBoxUsername");
		String MailBoxPassword = KWVariables.getVariables().get("MailBoxPassword");

		String displayedToken = "";

		switch (envType.toUpperCase()) {
		
		case "YOPMAIL":
				System.out.println("Inside Yopmail");
				if(loanOnboarded==true){
					System.out.println("Inside Change");
					MailBoxUsername="loanonboard";
					loanOnboarded=false;
				}
				displayedToken = fetchTokenFromYopMail(driver, MailBoxUsername, expectedToken);
			break;
		case "MAILINATOR":
			System.out.println("Under mailinator ");

			displayedToken = fetchTokenFromMailinatorBox(driver, MailBoxUsername, expectedToken);

			break;

		case "SHARKLASER":

			displayedToken = fetchTokenFromSharkLaser(driver, MailBoxUsername, expectedToken);

			break;
			
		//---------------Sharath added -----------------------
		case "GMAIL" :
		displayedToken = fetchTokenFromGmail(driver, MailBoxUsername,MailBoxPassword, expectedToken);

		default:

			break;

		}

		if (displayedToken == null) {

			addExceptionToReport("Expected Token starting with " + expectedToken + " is not seen in mail box.", "", "");

		}

		return displayedToken;

	
	}
	
	private String fetchTokenFromYopMail(WebDriver driver, String loginUserName, String expectedToken)
			throws Exception {
        String parentWindowHandle = driver.getWindowHandle();
		((JavascriptExecutor)driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("http://www.yopmail.com/en/");
		
//		TimeUnit.MINUTES.sleep(10);
		driver.findElement(By.xpath("//input[@id='login']")).clear();
		driver.findElement(By.xpath("//input[@id='login']")).sendKeys(loginUserName);
		
		
		driver.findElement(By.xpath("//input[@value='Check Inbox']")).click();
//		System.out.println("mailCount: before" );
		  System.out.println("Inside selectMailInYopMail");
	        String yopMailHeaderXpath="//span[text()='%subject']";
	        String yopMailBodyXpath="//div[@id='mailmillieu']";
	        String header="PennyMac - Email Confirmation";
	        yopMailHeaderXpath=yopMailHeaderXpath.replace("%subject", header);
	        System.out.println("yopMailHeaderXpath:"+yopMailHeaderXpath);
	        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	        driver.switchTo().defaultContent();
	        driver.switchTo().frame("ifinbox");
	  System.out.println("size:"+driver.findElements(By.xpath(yopMailHeaderXpath)).size());
	//  try{
	  if(driver.findElements(By.xpath(yopMailHeaderXpath)).size()==1){
	              System.out.println("Inside one");
	              driver.switchTo().defaultContent();
	              Thread.sleep(2000);
	              driver.switchTo().frame("ifmail");
 //                 System.out.println("text in body:"+driver.findElement(By.xpath("//div[@id='mailmillieu']//p/b")).getText());
//	              driver.findElement(By.xpath(yopMailHeaderXpath)).clear();
	        }
	        else if(driver.findElements(By.xpath(yopMailHeaderXpath)).size()>1)
	        {
	              System.out.println("Inside more than one");
	              for(WebElement ele:driver.findElements(By.xpath(yopMailHeaderXpath))){
	                    int count=0; count++;
	                    ele.click();
	                    driver.switchTo().defaultContent();
	                    Thread.sleep(2000);
	                    driver.switchTo().frame("ifmail");
//	                    System.out.println("text in body:"+driver.findElement(By.xpath(yopMailBodyXpath)));
	                    break;
	                    
	              }
	        }

      String displayedToken =driver.findElement(By.xpath("//div[@id='mailmillieu']//p/b")).getText();
      driver.switchTo().defaultContent();
      
		if (displayedToken.substring(0, 6).trim().equalsIgnoreCase(expectedToken)) {

//			driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "1");
			driver.switchTo().window(parentWindowHandle);
			driver.switchTo().defaultContent();

			return displayedToken.substring(3, displayedToken.length());

		}
				return null;
		
	}
	

	private String fetchTokenFromMailinatorBox(WebDriver driver, String loginUserName, String expectedToken)
			throws Exception {
        System.out.println("Inside fetchTokenFromMailinatorBox");
//		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
//		driver.switchTo().window(tabs.get(0)).get("https://www.mailinator.com");
        String parentWindowHandle = driver.getWindowHandle();
		((JavascriptExecutor)driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("https://www.mailinator.com");
		
		TimeUnit.MINUTES.sleep(10);
		driver.findElement(By.id("inboxfield")).sendKeys(loginUserName);

		driver.findElement(By.xpath("//button[contains(.,'Go')]")).click();
		System.out.println("mailCount: before" );
		//int mailCount = driver.findElements(By.xpath("//ul[@id='inboxpane']/li")).size();
		int mailCount = driver.findElements(By.xpath("//div[@id='inboxpane']//tr[@class='even pointer ng-scope']")).size();
		System.out.println("mailCount:" + mailCount);
		for (int mailIndex = 1; mailIndex <= mailCount; mailIndex++) {

//			driver.findElement(By.xpath("//ul[@id='inboxpane']/li[" + String.valueOf(mailIndex)
//
//					+ "]/div/div[contains(.,'PennyMac - Email Confirmation')]")).click();
			
			driver.findElement(By.xpath("//div[@id='inboxpane']//tr[@class='even pointer ng-scope'][" + String.valueOf(mailIndex)

			+ "]//td[contains(.,'PennyMac - Email Confirmation')]")).click();
			//div[@id='inboxpane']//tr[@class='even pointer ng-scope'][1]//td[contains(.,'PennyMac - Email Confirmation')]

			Thread.sleep(5000);

			driver.switchTo().frame(driver.findElement(By.id("msg_body")));
			System.out.println("switched");
			// Thread.sleep(10000);
			System.out.println("-----------"
					+ driver.findElement(By.xpath(".//*[@id='body_wrapper']/p[3]/b")).getAttribute("value"));
			String displayedToken = driver.findElement(By.xpath(".//*[@id='body_wrapper']/p[3]/b")).getText();
			System.out.println("displayedToken:" + displayedToken);

			if (displayedToken.substring(0, 6).trim().equalsIgnoreCase(expectedToken)) {
//				driver.close();
				driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "1");
				driver.switchTo().window(parentWindowHandle);
				driver.switchTo().defaultContent();

				return displayedToken.substring(3, displayedToken.length());

			}

		}

		return null;

	}


	//---------------Sharath Added ------------------------------------------
	private String fetchTokenFromGmail(WebDriver driver, String loginUserName, String loginPassword, String expectedToken)
			throws Exception {
        System.out.println("Inside fetchTokenFromGmail>>>");
//		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
//		driver.switchTo().window(tabs.get(0)).get("https://www.gmail.com/");
        String parentWindowHandle = driver.getWindowHandle();
		((JavascriptExecutor)driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("https://www.gmail.com/");
		
		driver.findElement(By.id("identifierId")).click();

		driver.findElement(By.id("identifierId")).sendKeys(loginUserName);
		
		driver.findElement(By.xpath("//span[@class='RveJvd snByac']")).click(); 
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("//input[@class='whsOnd zHQkBf']")).sendKeys(loginPassword);
		
		 driver.findElement(By.xpath("//span[@class='RveJvd snByac']")).click();
		 Thread.sleep(6000);
		 
//		 driver.findElement(By.id(":23")).click();
//		 driver.findElement(By.xpath("//tr[contains(.,'NoReply')][1]")).click();
		 driver.findElement(By.xpath("(//span[@class='y2']//span[@class='Zt'])[1]")).click();
         System.out.println("Mail Clicked!!");
         Thread.sleep(4000);
		 String displayedToken = driver.findElement(By.xpath("//b[contains(.,'PM')]")).getText();
		 System.out.println("displayedToken:" + displayedToken);

		if (displayedToken.substring(0, 6).trim().equalsIgnoreCase(expectedToken)) {

//			driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "1");
			driver.switchTo().window(parentWindowHandle);
			driver.switchTo().defaultContent();

			return displayedToken.substring(3, displayedToken.length());

		}
		return null;
	}
		
	
	
	//--------------------Sharath Ended------------------------------
	
	private String fetchTokenFromSharkLaser(WebDriver driver, String loginUserName, String expectedToken)
			throws Exception {

		System.out.println("Inside fetchTokenFromSharkLaser");
		 String parentWindowHandle = driver.getWindowHandle();
			((JavascriptExecutor)driver).executeScript("window.open()");
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
		driver.get("https://www.sharklasers.com");

		driver.findElement(By.xpath("//div[@id='guerrilla_mail']//span[@id='inbox-id']")).click();

		driver.findElement(By.xpath("//div[@id='guerrilla_mail']//span[@id='inbox-id']/input")).sendKeys(loginUserName);

		driver.findElement(By.xpath("//button[@class='save button small']")).click();

		driver.findElement(By.xpath("//a[@title='Email']")).click();

		boolean found = false;

		while (driver.findElement(By.id("tick")).getText().contains("update")) {

			Thread.sleep(1000);

		}

		while (found == false) {

			try {

				driver.findElements(By.xpath("//table[@id='email_table']/tbody/tr[@class[contains(.,'unread')]]"))
						.size();

				driver.findElement(By.xpath("//table[@id='email_table']/tbody/tr[@class[contains(.,'unread')]]"))
						.click();

				String displayedToken = driver
						.findElement(By.xpath("//div/p[contains(.,'enter this code')]//following-sibling::p[1]"))
						.getText();

				if (displayedToken.substring(0, 6).trim().equalsIgnoreCase(expectedToken)) {

					driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "1");

					driver.switchTo().defaultContent();

					return displayedToken.substring(3, displayedToken.length());

				} else {

					driver.findElement(By.id("back_to_inbox_link")).click();

				}

			} catch (Exception e) {

				driver.findElement(By.xpath("//a[@title='Email']")).click();

			}

		}

		return null;

	}

	public void fetchTokenFromSharkTankers() throws Exception {

		WebDriver driver = DriverFactory.getDriver();

		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

		String parentDriver = driver.getWindowHandle();

		driver.switchTo().window(tabs.get(1)).get("https://www.mailinator.com");

		driver.switchTo().window(tabs.get(0));

	}

	public void checkIfButtonIsInEnabledState(WebDriver driver, String buttonName) throws Exception {

		JavascriptExecutor executor = (JavascriptExecutor) driver;

		Object aa = executor.executeScript(

				"var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;",

				driver.findElement(By.xpath("//button[contains(.,'" + buttonName + "')]")));

		if (aa.toString().contains("disabled") == false) {

			addExceptionToReport(buttonName + " Button is enabled with Invalid values in fields.", "", "");

		}

	}

	private String fetchRefenceID(WebDriver driver) throws Exception {
//		TimeUnit.MINUTES.sleep(5);
		System.out.println("inside fetchRefenceID:");
//		driver.findElement(By.xpath(".//img[contains(@src,'home11')]")).click();
		driver.findElement(By.xpath("//div[@class='logo-container']/button")).click();
		return getReferenceID(driver);

	}

	private String getReferenceID(WebDriver driver) throws Exception {
		Thread.sleep(5000);
		System.out.println("inside getReferenceID:");
		String refID = driver.findElement(By.xpath("(.//div[@class='loan-number'])[1]")).getText();
		return refID.split("#")[1].trim();

	}

	private String getLeadId(String referenceId) throws Exception {

		String envType = KWVariables.getVariables().get("Env"); 
		String Db_url = KWVariables.getVariables().get("Db_url"); 
		String Db_url_uat = KWVariables.getVariables().get("Db_url_UAT"); 
		String leadIDValue = null;
		MongoClient mc = null;
		// @SuppressWarnings("resource")
		try {
			if (envType.equalsIgnoreCase("qa")) 
			{
			mc = new MongoClient(Db_url, 27017);
			System.out.println("Entered if of qa");
			}
			else if (envType.equalsIgnoreCase("uat"))
			{
				MongoCredential credential = MongoCredential.createCredential("prequaluser", "prequal", "prequalpwd".toCharArray());
				mc = new MongoClient(new ServerAddress(Db_url_uat, 18902), Arrays.asList(credential));
			}
			else
			{
				
				Util.addExceptionToReport("DB env type is not matching ", "Actual is   "+envType, "Expected is qa/uat");
			}
			
			
			@SuppressWarnings("deprecation")
			DB db = mc.getDB("prequal");
			DBCollection dbc = db.getCollection("quoteinfo");
			BasicDBObject query1 = new BasicDBObject();
			query1.put("loanNumber", referenceId);
			DBCursor dbcrsr = dbc.find(query1);
			BasicDBObject record = (BasicDBObject) dbcrsr.next();
			if (record.get("leadId") != null) {
				leadIDValue = record.get("leadId").toString();
			}
			mc.close();
		} catch (Exception e) {
			mc.close();
			addExceptionToReport("Exception seen while fetching leadID for the reference ID " + referenceId
					+ " and exception is " + e.getMessage() + ";" + e.getStackTrace().toString(), "", "");
		}
		return leadIDValue;

	}

	private void addLoanNumberGUIDInDB(String referenceId, String loanNumber, String guiID) throws Exception {

		String Db_url = KWVariables.getVariables().get("Db_url");
		String Db_url_uat = KWVariables.getVariables().get("Db_url_UAT"); 
		String envType = KWVariables.getVariables().get("Env"); 
		MongoClient mc = null;
		
	try {
		System.out.println("Env type is:addLoanNumberGUIDInDB "+envType );

			
		if (envType.equalsIgnoreCase("qa")) 
		{
		mc = new MongoClient(Db_url, 27017);
		}
		else if (envType.equalsIgnoreCase("uat"))
		{
			MongoCredential credential = MongoCredential.createCredential("prequaluser", "prequal", "prequalpwd".toCharArray());
			mc = new MongoClient(new ServerAddress(Db_url_uat, 18902), Arrays.asList(credential));
		}
		else
		{
			
			Util.addExceptionToReport("DB env type is not matching ", "Actual is   "+envType, "Expected is qa/uat");
		}
	} catch (Exception ex){

		addExceptionToReport("Database connection error "
				 + ex.getMessage(), "", "");
	}
		@SuppressWarnings("deprecation")
		DB db = mc.getDB("prequal");
		DBCollection dbc = db.getCollection("quoteinfo");
		try {
			BasicDBObject query1 = new BasicDBObject();
			query1.put("referenceId", referenceId);
			DBCursor dbcrsr = dbc.find(query1);
			BasicDBObject record = (BasicDBObject) dbcrsr.next();
			if (record.get("referenceId").toString().equalsIgnoreCase(referenceId)) {
				BasicDBObject query2 = new BasicDBObject();
				query2.put("referenceId", loanNumber);

				query2.put("loanguid", guiID);
				dbc.update(query1, new BasicDBObject().append("$set", query2), false, true);
			}
			mc.close();
		} catch (Exception e) {
			addExceptionToReport("Exception observed while adding loan number & guid for the reference number "
					+ referenceId + " and exception is " + e.getMessage(), "", "");
		}

	}

	private String fetchLoanNumberGUIDFromVelocify(WebDriver driver, String leadIDValue) throws Exception {
		driver.switchTo().activeElement();
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
		driver.get(KWVariables.getVariables().get("VelocifyURL"));
		driver.switchTo().alert().accept();
		driver.manage().window().maximize();
		Util.waitForPageToLoad();
		Thread.sleep(2000);
		// Externalise velociy login credentials
		driver.findElement(By.id("usernameTextBox")).sendKeys("sharad.keer_x2@tavant.com");
		driver.findElement(By.id("passwordTextBox")).sendKeys("Velocify44!");
		driver.findElement(By.id("loginButton")).click();
		Thread.sleep(10000);
		driver.findElement(By.id("lmSearchInput")).click();
		driver.findElement(By.id("lmSearchInput")).sendKeys(leadIDValue);
		driver.findElement(By.id("lmSearchInput")).sendKeys(Keys.ENTER);
		Thread.sleep(10000);

		driver.findElement(By.xpath(".//a[@class='img edit']")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("cph_leadItemList_filterRbl_2")).click();
		Thread.sleep(50000);
		driver.findElement(By.id("cph_leadItemList_filterRbl_1")).click();
		Thread.sleep(50000);
		driver.findElement(By.id("cph_leadItemList_filterRbl_2")).click();
		Thread.sleep(5000);
		int sizeOfRows = driver.findElements(By.xpath(".//*[@id='leadItemLogTable']/tbody/tr/td[1]")).size();
		String loanGUI = "";
		for (int i = 1; i <= sizeOfRows; i++) {
			String rowData = driver.findElement(By.xpath(".//*[@id='leadItemLogTable']/tbody/tr[" + i + "]/td[1]"))
					.getText();
			System.out.println("rowData:" + rowData);
			if (rowData.equalsIgnoreCase("Exported")) {
				String loandata = driver.findElement(By.xpath(".//*[@id='leadItemLogTable']/tbody/tr[" + i + "]/td[2]"))
						.getText();

				Object obj = new JSONParser()
						.parse(loandata.substring(loandata.indexOf("{"), loandata.lastIndexOf("}") + 1));
				JSONObject jo = (JSONObject) obj;
				loanGUI = "LoanNumber:" + (String) jo.get("loanNumber") + ";LoanGuid:" + (String) jo.get("loanguid");

				break;

			}
		}
		System.out.println("lOAN iNFO:--------------->" + loanGUI);
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "w");
		return loanGUI;

	}

	public String readALineFromFile() throws Exception {

		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(encompassLoanFile));

		return br.readLine();

	}

	public String fetchGUID(WebDriver driver) throws Exception {
		String guID = "";
		String loanNumber = "";
		String referenceID = fetchRefenceID(driver);
		String leadIDValue = getLeadId(referenceID);
		System.out.println("leadIDValue>>>"+leadIDValue);
		String combinedStr = fetchLoanNumberGUIDFromVelocify(driver, leadIDValue);
		loanNumber = combinedStr.split(";")[0].split(":")[1];
		guID = combinedStr.split(";")[1].split(":")[1];
		guID = guID.substring(1, guID.length()-1);
		addLoanNumberGUIDInDB(referenceID, loanNumber, guID);
		Thread.sleep(3000);

		String guid = guID.replace("{", "").replace("}", "");
		System.out.println("guid:" + guid);
		String guiDLoanNumber = guid + "&&" + loanNumber;
		return guiDLoanNumber;
	}

	public String fetchAllAttributesOfWebElement(WebDriver driver, WebElement webElement) throws Exception {

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		Object aa = executor.executeScript(
				"var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;",
				webElement);
		return aa.toString();

	}
	String encompassLoanFile = System.getProperty("user.dir") + "\\src\\test\\resources\\EncompasLoans.txt"; 

	String encompassNullValueKey = "F:/Ditech projects/ditech/src/test/resources/encompassNullValueKey.txt";

	public void writeDataToFile(String newStr) throws Exception {
		String basePath = new File("").getAbsolutePath();

		String path = new File("src/test/resources/EncompasLoans.txt").getAbsolutePath();
		BufferedWriter bw = new BufferedWriter(new FileWriter(encompassLoanFile));
		bw.write(" ");
		bw.write(newStr);
		bw.close();
	}

	public String readALineFromFile(String fileName) throws Exception {

		BufferedReader br = new BufferedReader(new FileReader(encompassLoanFile));
		return br.readLine();

	}

	public  List<String> fetchDataFromNotepad1(String fileName) throws Exception{
	    List<String> dataList = new ArrayList<>();
	     
		String filePath = System.getProperty("user.dir");
		 filePath = filePath +"\\src\\test\\resources\\";
	     BufferedReader br = new BufferedReader(new FileReader(filePath+fileName));
	     String line = br.readLine();
	     
	     while(line!= null){
	    	   dataList.add(line.trim());
	           line = br.readLine();
	     }
	     
	     return dataList;
}

	
	public HashMap<String, String> fetchDataFromNotepad(String fileName) throws Exception {
		HashMap<String, String> notepadMap = new HashMap<String, String>();
		String path = new File("src/test/resources/").getAbsolutePath();

		BufferedReader br = new BufferedReader(new FileReader(path + "/" + fileName));
		String line = br.readLine();

		while (line != null) {

			notepadMap.put(line.split(";")[0], line.split(";")[1]);
			line = br.readLine();
		}
		br.close();
		return notepadMap;

	}
	
	/**
	 * Wait until Element is displayed
	 * 
	 * @param driver
	 * @param element
	 */
	public void waitTillElementIsDisplayed(WebDriver driver, WebElement element) {
		int pollIndex = 1;
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(240, TimeUnit.SECONDS)
					.pollingEvery(10, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
			wait.until(ExpectedConditions.visibilityOf(element));
			pollIndex = pollIndex + 1;
		} catch (Exception e) {

		}

	}
	

	public void javaScriptClickOn(String frameId) throws InterruptedException, TwfException, Exception {
		System.out.println("inside validateDeclarationQuestionAnswerIn1003 ");
		WebDriver driver = DriverFactory.getDriver();
		//driver.switchTo().frame(driver.findElement(By.id(frameId)));
		driver.switchTo().frame(0);
		WebElement element = driver.findElement(By.id("cm_name"));
		element.click();
//		JavascriptExecutor executor = (JavascriptExecutor)driver;
//		executor.executeScript("arguments[0].click();", element);
		

		System.out.println("After ");

		Thread.sleep(4000);

//		JavascriptExecutor jse = (JavascriptExecutor)driver;
//		jse.executeScript("document.getElementById('cm_name').click();");
	}

	public void fileUpload(String fileName,WebDriver driver) throws Exception {
//        WebDriver driver = DriverFactory.getDriver();
//        String fileName = step.getDataValue("Fnm3.2FileName");
        String filePath =System.getProperty("user.dir");
        System.out.println("Filename>>>>>"+fileName+"condition");
        filePath = filePath + "\\src\\test\\resources\\FilesToUpload\\" + fileName;
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].style='block';", getElementByUsing("ChooseFileInput"));
        getElementByUsing("ChooseFileInput").sendKeys(filePath);
        System.out.println("Send>>>");
  }
	
	
//	public String getLoanGUIDFromDB(String loanNumber) throws Exception {
//		String GUIDValue = null;
//		 loanNumber="7202411393";
//		System.out.println("Inside getLoanGUIDFromDB>>>>");
//		String Db_url = KWVariables.getVariables().get("DBUrl");
//		
//		MongoClient mongoClient = new MongoClient(Db_url, 27017);
//        String Db2="prequal";
//        Dashboard dashboard=new Dashboard();
//
//        System.out.println("Inside getLeadId>>>");
//        MongoCredential credential2 = MongoCredential.createCredential("prequaluser", "prequal", "prequalpwd".toCharArray());
//        MongoClient  mongoClient2 = new MongoClient(new ServerAddress(Db_url, 27017), Arrays.asList(credential2));
//        
//
//        try {
//        @SuppressWarnings("deprecation")
//        DB test2 = mongoClient2.getDB(Db2);
//        DBCollection dbc_quoteinfo = test2.getCollection("quoteinfo");
//        BasicDBObject query = new BasicDBObject();
//		query.put("loanNumber", loanNumber);
//		System.out.println("Query---------->>>>>>>>>");
//		DBCursor dbcrsr = dbc_quoteinfo.find(query);
//		BasicDBObject record = (BasicDBObject) dbcrsr.next();
//		System.out.println("lead if>>>>" + record.get("loanguid"));
//		if (record.get("loanguid") != null) {
//			GUIDValue = record.get("loanguid").toString();
//		}
////		MongoClient mongoClient = new MongoClient(Db_url, 27017);
//
////		@SuppressWarnings("deprecation")
////		DB test = mongoClient.getDB("prequal");
////		DBCollection dbc = test.getCollection("quoteinfo");
////		try {
////			BasicDBObject query = new BasicDBObject();
////			query.put("loanNumber", loanNumber);
////			System.out.println("Query---------->>>>>>>>>");
////			DBCursor dbcrsr = dbc.find(query);
////			BasicDBObject record = (BasicDBObject) dbcrsr.next();
////			System.out.println("lead if>>>>" + record.get("loanguid"));
////			if (record.get("loanguid") != null) {
////				GUIDValue = record.get("loanguid").toString();
////			}
////			
//			mongoClient.close();
//		} catch (Exception e) {
//			mongoClient.close();
//			addExceptionToReport("Exception seen while fetching leadID for the reference ID " + loanNumber
//					+ " and exception is " + e.getMessage(), "", "");
//		}
//		System.out.println("loanguid:---->" + GUIDValue);
//		return GUIDValue;
//	}
	
	public String getLoanGUIDFromDB(String loanNumber) throws Exception {
		String GUIDValue = null;
//		 loanNumber="7202411393";
//		LoginIntoApplication.envType="qa";
		System.out.println("Inside getLoanGUIDFromDB>>>>");
		String Db_url="";
		int Db_IPAddress = 0;
		System.out.println("env selection>>>"+KWVariables.getVariables().get("Env"));
		switch(KWVariables.getVariables().get("Env").toLowerCase()){
		case "qa":
		Db_url = KWVariables.getVariables().get("ConfigFlag_DBUrl_QA");
		Db_IPAddress=Integer.valueOf(KWVariables.getVariables().get("ipAddress_QA"));
		break;
		case "uat":
		Db_url = KWVariables.getVariables().get("ConfigFlag_DBUrl_UAT");
		Db_IPAddress=Integer.valueOf(KWVariables.getVariables().get("ipAddress_UAT"));;
		break;	
		case "taurus":
			Db_url = KWVariables.getVariables().get("ConfigFlag_DBUrl_TAURUS");
			Db_IPAddress=Integer.valueOf(KWVariables.getVariables().get("ipAddress_QA"));
		break;
		case "pisces":
			Db_url = KWVariables.getVariables().get("ConfigFlag_DBUrl_PISCES");
			Db_IPAddress=Integer.valueOf(KWVariables.getVariables().get("ipAddress_QA"));
		break;
		case "capricorn":
			Db_url = KWVariables.getVariables().get("ConfigFlag_DBUrl_CAPRICORN");
			Db_IPAddress=Integer.valueOf(KWVariables.getVariables().get("ipAddress_QA"));
		break;
		case "virgo":
			Db_url = KWVariables.getVariables().get("ConfigFlag_DBUrl_VIRGO");
			Db_IPAddress=Integer.valueOf(KWVariables.getVariables().get("ipAddress_QA"));
		break;
		case "leo":
			Db_url = KWVariables.getVariables().get("ConfigFlag_DBUrl_LEO");
			Db_IPAddress=Integer.valueOf(KWVariables.getVariables().get("ipAddress_QA"));
		break;
		case "aquarius":
			Db_url = KWVariables.getVariables().get("ConfigFlag_DBUrl_AQUARIUS");
			Db_IPAddress=Integer.valueOf(KWVariables.getVariables().get("ipAddress_QA"));
		break;
		case "aries":
			Db_url = KWVariables.getVariables().get("ConfigFlag_DBUrl_ARIES");
			Db_IPAddress=Integer.valueOf(KWVariables.getVariables().get("ipAddress_QA"));
		break;
		case "scorpio":
			Db_url = KWVariables.getVariables().get("ConfigFlag_DBUrl_SCORPIO");
			Db_IPAddress=Integer.valueOf(KWVariables.getVariables().get("ipAddress_QA"));
		break;
		case "libra":
			Db_url = KWVariables.getVariables().get("ConfigFlag_DBUrl_LIBRA");
			Db_IPAddress=Integer.valueOf(KWVariables.getVariables().get("ipAddress_QA"));
		break;
		case "gemini":
			Db_url = KWVariables.getVariables().get("ConfigFlag_DBUrl_GEMINI");
			Db_IPAddress=Integer.valueOf(KWVariables.getVariables().get("ipAddress_QA"));
		break;
		case "cancer":
			Db_url = KWVariables.getVariables().get("ConfigFlag_DBUrl_CANCER");
			Db_IPAddress=Integer.valueOf(KWVariables.getVariables().get("ipAddress_QA"));
		break;
		}
		MongoClient mongoClient = new MongoClient(Db_url, Db_IPAddress);
        String Db2="prequal";
        Dashboard dashboard=new Dashboard();

        System.out.println("Inside getLeadId>>>");
        MongoCredential credential2 = MongoCredential.createCredential("prequaluser", "prequal", "prequalpwd".toCharArray());
        MongoClient  mongoClient2 = new MongoClient(new ServerAddress(Db_url, Db_IPAddress), Arrays.asList(credential2));
        

        try {
        @SuppressWarnings("deprecation")
        DB test2 = mongoClient2.getDB(Db2);
        DBCollection dbc_quoteinfo = test2.getCollection("quoteinfo");
        BasicDBObject query = new BasicDBObject();
		query.put("loanNumber", loanNumber);
		System.out.println("Query---------->>>>>>>>>");
		DBCursor dbcrsr = dbc_quoteinfo.find(query);
		BasicDBObject record = (BasicDBObject) dbcrsr.next();
		System.out.println("lead if>>>>" + record.get("loanguid"));
		if (record.get("loanguid") != null) {
			GUIDValue = record.get("loanguid").toString();
		}
			mongoClient.close();
		} catch (Exception e) {
			mongoClient.close();
			addExceptionToReport("Exception seen while fetching leadID for the reference ID " + loanNumber
					+ " and exception is " + e.getMessage(), "", "");
		}
		System.out.println("loanguid:---->" + GUIDValue);
		return GUIDValue;
	}
	public static HashMap<String, String> getLoanDetailsFromDB(String loanNumber,String[] listOfFields) throws Exception {

		String envType = KWVariables.getVariables().get("Env"); 
		String Db_url = KWVariables.getVariables().get("Db_url"); 
		String Db_url_uat = KWVariables.getVariables().get("Db_url_UAT"); 
		HashMap<String, String> dataFromDB = new HashMap<String, String>();
		String value = null;
		MongoClient mc = null;
		String dbName="";
		// @SuppressWarnings("resource")
		try {
			if (envType.equalsIgnoreCase("qa")) 
			{
				mc = new MongoClient(Db_url, 27017);
				System.out.println("Created USER for DB qa");
				dbName="prequal";
			}
			else if (envType.equalsIgnoreCase("uat"))
			{
				System.out.println("DB URAL UAT>>>"+Db_url_uat);
				MongoCredential credential = MongoCredential.createCredential("mac_1_1_prequal_app_owner", "mac_1_1_prequal", "PmcDU#Pr3qL".toCharArray());
				System.out.println("Credential Created");
				mc = new MongoClient(new ServerAddress(Db_url_uat, 19902), Arrays.asList(credential));
				System.out.println("Created USER for DB UAT");
				dbName="mac_1_1_prequal";
			}
			else
			{
				Util.addExceptionToReport("DB env type is not matching ", "Actual is   "+envType, "Expected is qa/uat");
			}

			@SuppressWarnings("deprecation")
			DB db = mc.getDB(dbName);
			System.out.println("Credential Created");
			DBCollection dbc = db.getCollection("quoteinfo");
		
			BasicDBObject query1 = new BasicDBObject();
			query1.put("loanNumber", loanNumber);
			DBCursor dbcrsr = dbc.find(query1);
			BasicDBObject record = (BasicDBObject) dbcrsr.next();
			dbcrsr = dbc.find(query1);
			BasicDBObject record1 =(BasicDBObject) ((BasicDBObject) dbcrsr.next().get("fullApplication")).get("propertyDetails");
		//	System.out.println("Record>>>"+record);
			for(String field:listOfFields){
				value="";
				if (record.get(field) != null) 
					value = record.get(field).toString();
				if(value=="" && record1.get(field) != null)
					value=record1.get(field).toString();
				if(value.contains("."))
					value=value.split("\\.")[0];
				dataFromDB.put(field, value);
			}
			mc.close();
		} catch (Exception e) {
			mc.close();
			addExceptionToReport("Exception seen while fetching appType for the loan Number " + loanNumber
					+ " and exception is " + e.getMessage() + ";" + e.getStackTrace().toString(), "", "");
		}
		return dataFromDB;
	}

	public void appendResultToFile(String textToAppend) throws IOException{
		String filepath = System.getProperty("user.dir");
		filepath = filepath + "\\src\\test\\resources\\EncompassResult.txt";
		try{
		 BufferedWriter writer = new BufferedWriter(new FileWriter(filepath, true)); 
	     writer.newLine();   //Add new line
	     writer.write(textToAppend);
	     writer.close();}catch (Exception e) {
			System.out.println("could not append into file"+e);
		}
	}
	 public String getLoanFromSubmitScreen() throws BiffException, IOException, TwfException, InterruptedException{   
	        Thread.sleep(3000);
	        String loanNumber="";
	        System.out.println("inside getLoanFromSubmitScreen");
	        loanNumber=getElementByUsing("Loan_number").getText();
	        System.out.println("LoanNumber:>>>"+loanNumber);
	        if(loanNumber.contains("prequalification"))
	            loanNumber=getElementByUsing("Loan_number").getText().split("prequalification")[1].split("has")[0].trim();
	        else
	            loanNumber=getElementByUsing("Loan_number").getText().split("loan application")[1].split("has")[0].trim();
	       
	        if(loanNumber.startsWith("IN")){
	        	addExceptionToReport("Loan Number is not generating in submit screen displaying in IN format", loanNumber, "");
	        }
	        System.out.println("loanNumber from getLoanFromSubmitScreen>>>>>"+loanNumber);
	        return loanNumber;
	    }
//	private String updateDynamicValue(String data) throws Exception {
//		if (data.toLowerCase().contains("first name"))
//			data = data.split(":")[0] + ":" + Ten0Three.fname;
//		else if (data.toLowerCase().contains("email address"))
//			data = data.split(":")[0] + ":" + Ten0Three.EmailValidation;
//		// else if(data.toLowerCase().contains("address"))
//		// data=updateAddressDynamicValue(data);
//		System.out.println("updateDynamicValue data>>>" + data);
//		return data;
//
//	}
	
	public static HashMap<Integer, String> getTableHeaders(HSSFSheet sheet, int rowIndex) {
		HashMap<Integer, String> headerMap = new HashMap<Integer, String>();
		int colCount = sheet.getRow(rowIndex).getPhysicalNumberOfCells();
		System.out.println("Co Count>>>>" + colCount);
		for (int colIndex = 0; colIndex < colCount; colIndex++) {
			String colName = sheet.getRow(0).getCell(colIndex).toString();
			headerMap.put(colIndex, colName);
		}
		return headerMap;

	}
	public HashMap<String, HashMap<String, String>> buildEncompassMapFile() throws Exception {
		String filepath = System.getProperty("user.dir");
		if(KWVariables.getVariables().get("Env").equalsIgnoreCase("taurus")||KWVariables.getVariables().get("Env").equalsIgnoreCase("pisces")){
			filepath = filepath + "\\src\\test\\resources\\EncompassMapSheet_URLA.xls";
			System.out.println("Inside URLA");
		}
		else
			filepath = filepath + "\\src\\test\\resources\\EncompassMapSheet.xls";
		
//		filepath = filepath + "\\src\\test\\resources\\EncompassMapSheet.xls";
		File fileName = new File(filepath);
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(fileName));
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheetAt(0);
		int rowCount = 0;
		// int colCount = 0;
		rowCount = sheet.getPhysicalNumberOfRows();
		HashMap<Integer, String> headerMap = getTableHeaders(sheet, 0);
		HashMap<String, HashMap<String, String>> encompassTableMap = new HashMap<String, HashMap<String, String>>();
		for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
			String fieldKey = sheet.getRow(rowIndex).getCell(0).toString();
			HashMap<String, String> rowMap = new HashMap<String, String>();
			for (int colIndex = 1; colIndex < headerMap.size(); colIndex++) {
				HSSFCell cellValue = sheet.getRow(rowIndex).getCell(colIndex);
				String val = "";
				if (cellValue == null) {
					val = "";
				} else {
					if (cellValue.getCellType() == 0) {
						val = cellValue.toString().split("\\.")[0];
					} else {
						val = cellValue.toString();
					}
				}

				rowMap.put(headerMap.get(colIndex), val);
			}
			encompassTableMap.put(fieldKey, rowMap);
		}
		return encompassTableMap;
		

	}
	
	private String formatData(String data) {
		System.out.println("data inside formatData>>" + data + ">>>>");
		if (data.contains(":"))
			data = data.split(" ")[0].trim();
		else if (data.contains(".00"))
			data = data.split("\\.")[0].trim();
		return data;

	}
	public String verify1003FieldsFromEncompass(String guidPayLoad, String fieldsToBeChecked) throws Exception {
		System.out.println("verify1003FieldsFromEncompass!!!!!!>>>and fields"+fieldsToBeChecked);
		EncompassValidation env = new EncompassValidation();
		String errormessage = "";

		Dashboard dashboard = new Dashboard();
		Ten0Three ten0Three = new Ten0Three();

		String loanNumber = encom.EncomopassLoanNumber;
		System.out.println("verify1003FieldsFromEncompass loanNumber>>>>" + loanNumber);
		
		HashMap<String, HashMap<String, String>> encompassMap = buildEncompassMapFile();

		ResponseEntity<?> responseEntity =  env.fetchEncompassData(encom.EncomopassLoanNumber, guidPayLoad);
//		if (responseEntity.getStatusCode() != HttpStatus.OK) {
//			System.out.println("Fetching Property Info From Encompass failed for Loan Number " + loanNumber
//					+ String.valueOf(responseEntity.getStatusCode()) + "200");
//		}
		System.out.println("fieldsToBeChecked>>>" + fieldsToBeChecked);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String actualData;
		try {
			Loan mp = objectMapper.readValue(responseEntity.getBody().toString(), Loan.class);

			for (String field : fieldsToBeChecked.split(";")) {
				System.out.println("field>>>>" + field);
//				 field=updateDynamicValue(field);
	//			System.out.println("field after updateDynamicValue>>>>" +encompassMap);
	//			String encompassId = encompassMap.get(field.split(":")[0].trim()).get("Encompass Id");
				String encompassId = encompassMap.get(field.split(":")[0].trim()).get("Encompass Id");
				System.out.println("encompass id>>" + encompassId);
				String expectedData = mp.getAddress().get(encompassId.trim());
	//			String expectedData = mp.getAddress().get("1343");
				System.out.println("expectedData>>>" + expectedData);
				expectedData = formatData(expectedData);
				System.out.println("expectedData1>>>" + expectedData);
				if (expectedData.equals(""))
					expectedData = "null";
				if (field.length() > (field.split(":")[0].length() + 1)) {
					actualData = field.split(":")[1];
				} else
					actualData = "null";
				System.out.println(
						"Encompass Id:-" + encompassId + " expectedData:" + expectedData + " actualData:" + actualData);

				if (!expectedData.equalsIgnoreCase(actualData)) {
					errormessage = errormessage + "\n" + "Encompass Data not matching for field-->["+ field.split(":")[0] + "] Actual Value:-[" + expectedData + "] expected Value:-["+ actualData + "]";
//					 addExceptionToReport("Encompass Data not matching for field " + field,
//					 "Actual Value:-" + actualData, "expected Value:-" +
//					 expectedData);
				}
			}

//			System.out.println("errormessage>>>" + errormessage);

		} catch (Exception e) {
			System.out.println(e);
			addExceptionToReport("Failed to fetch Status Date Encompass for loan number " + loanNumber, e.toString(),
					"");
		}
		return errormessage;
	}
	
	/**
	 * Method Name: postRequest Purpose: To post HTTP Request
	 *
	 * @param url
	 * @param payLoad
	 * @return
	 */

	public ResponseEntity<?> postRequest(String url, String payLoad) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("x-api-key", "3dad59cf-3911-4f3a-a579-43729499ec82");
		RestTemplate rt = new RestTemplate();
		HttpEntity<String> entity = new HttpEntity<String>(payLoad, headers);
		ResponseEntity<?> re = rt.exchange(url, HttpMethod.POST, entity, String.class);
		if (re.getStatusCode() != HttpStatus.OK) {
			addExceptionToReport("Exception seen while post operation ",
					String.valueOf(re.getStatusCode()), "200");
		}
		return re;
	}

	
	
	
	public Loan fetchEncompassResponse(String url,String guidPayLoad) throws Exception {
	//	System.out.println("verify1003FieldsFromEncompass!!!!!!>>>and fields"+fieldsToBeChecked);
		EncompassValidation env = new EncompassValidation();
		String errormessage = "";
		
		Dashboard dashboard = new Dashboard();
		Ten0Three ten0Three = new Ten0Three();

		String loanNumber = encom.EncomopassLoanNumber;
		System.out.println("verify1003FieldsFromEncompass loanNumber>>>>" + loanNumber);
		
		HashMap<String, HashMap<String, String>> encompassMap = buildEncompassMapFile();

		ResponseEntity<?> responseEntity =  postRequest(url,guidPayLoad);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			Loan mp = objectMapper.readValue(responseEntity.getBody().toString(), Loan.class);
			return mp;
		} catch (Exception e) {
			System.out.println(e);
			addExceptionToReport("Failed to fetch Status Date Encompass for loan number " + loanNumber, e.toString(),
					"");
		}
		return null;
	}
	
	private String incrementEncompassIds(String EncompassId, String leftIncrement) {
		int incrementStartIndex = Integer.valueOf(leftIncrement);
		int index = Integer.parseInt((EncompassId.substring(incrementStartIndex, incrementStartIndex + 1)));
		EncompassId = EncompassId.substring(0, incrementStartIndex) + (index + 1)
				+ EncompassId.substring(incrementStartIndex + 1, EncompassId.length());
		return EncompassId;

	}
	
	public String verifyLiabilityDatawithEncompassData(String LiabilityPayload, String fieldsToBeChecked)
			throws Exception {
		System.out.println("inside verifyLiabilityDatawithEncompassData");
		//WebDriver driver = DriverFactory.getDriver();
		WebService web = new WebService();
		String loanNumber = encom.EncomopassLoanNumber;
		System.out.println("loanNumber>>>>" + loanNumber);
		String expectedData = "";
		String actualData = "";
		String errormessage="";
		HashMap<String, HashMap<String, String>> encompassMap = buildEncompassMapFile();
		HashMap<String, String> encompassIDsMap = new HashMap<String, String>();
		ResponseEntity<?> responseEntity = web.fetchLoanDetailsFromEncompass(loanNumber, LiabilityPayload);
		
		System.out.println(responseEntity);
		if (responseEntity.getStatusCode() != HttpStatus.OK) {
			addExceptionToReport("Fetching Property Info From Encompass failed for Loan Number " + loanNumber,
					String.valueOf(responseEntity.getStatusCode()), "200");
		}
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			
		Loan mp = objectMapper.readValue(responseEntity.getBody().toString(), Loan.class);
			for (String field : fieldsToBeChecked.split(";")) {
				String EncompassId = encompassMap.get(field.trim()).get("Encompass Id");
				encompassIDsMap.put(field.trim(), EncompassId);
			}
			
			int noOfLiabilities=noLiabilitiesMap.size();
				System.out.println("noOfLiabilities exception e>>>"+noOfLiabilities);
			for (int reoIndex = 1; reoIndex <= noOfLiabilities; reoIndex++) {
				for (String field : fieldsToBeChecked.split(";")) {
					System.out.println("field1>>>"+field);
					field=field.trim();
					System.out.println("field2>>>"+field);
					String EncompassId = encompassIDsMap.get(field);
					System.out.println("EncompassId>>>"+EncompassId);
					String leftIncrement = encompassMap.get(field).get("Left Increment");
					expectedData = mp.getAddress().get(EncompassId);
					System.out.println(field + ":" + EncompassId + ":" + expectedData);
					EncompassId = incrementEncompassIds(EncompassId, leftIncrement);
					encompassIDsMap.put(field, EncompassId);
					actualData= noLiabilitiesMap.get(reoIndex).get(field);
					System.out.println("actualData>>>>"+actualData);	
					expectedData = expectedData.trim();
					actualData = actualData.replace("$", "").replace(",", "").trim();
					System.out.println(expectedData + "      " + actualData);
					if (!actualData.equals(expectedData))
						errormessage = errormessage + "\n" + "Encompass Data not matching for field-->["+ field.split(":")[0] + "] Actual Value:-[" + actualData + "] expected Value:-["+ expectedData + "]";
						//addExceptionToReport("Encompass and UI data not matching for " + field, actualData,
						//		expectedData);
				}
				
			}
			System.out.println("errormessage>>>"+errormessage);
		} catch (Exception e) {
			addExceptionToReport("Failed to fetch Status Date Encompass for loan number " + loanNumber, e.toString(),
					"");
		}
		return errormessage;
	}


/**
	 * Build Exported Excel Data Map
	 *
	 * @param fileName
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
//	public static HashMap<String, String> buildExcelFileMap(String fileName) throws FileNotFoundException, IOException {
//
//		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(fileName));
//		HSSFWorkbook wb = new HSSFWorkbook(fs);
//		HSSFSheet sheet = wb.getSheetAt(0);
//		int rowCount = 0;
//		int colCount = 0;
//		rowCount = sheet.getPhysicalNumberOfRows();
//		HashMap<Integer, String> headerMap = getTableHeaders(sheet, 0);
//		System.out.println("headerMap>>>>"+headerMap);
//		System.out.println("rowCount>>>"+rowCount);
//		HashMap<String, HashMap<String, String>> encompassTableMap = new HashMap<String, HashMap<String, String>>();
//		HashMap<String, String> rowMap = new HashMap<String, String>();
//		for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
//			String fieldKey = sheet.getRow(rowIndex).getCell(0).toString();
//
//			for (int colIndex = 1; colIndex < headerMap.size(); colIndex++) {
//				HSSFCell cellValue = sheet.getRow(rowIndex).getCell(colIndex);
//				String val = "";
//				if (cellValue == null) {
//					val = "";
//				} else {
//					if (cellValue.getCellType() == 0) {
//						val = cellValue.toString().split("\\.")[0];
//					} else {
//						val = cellValue.toString();
//					}
//				}
//
//				//rowMap.put(headerMap.get(colIndex), val);
//				//System.out.println("rowMap>>>"+rowMap);
//				rowMap.put(fieldKey, val);
//			}
//
//			//System.out.println("encompassTableMap>>>"+encompassTableMap);
//		}
//		return rowMap;
//
//	}
	private static HashMap<Integer, String> getColumnNames(Sheet sheetName) {

		 

        HashMap<Integer, String> columnHeaderMap = new HashMap<Integer, String>();
        Row currentRow = sheetName.getRow(0);
        Iterator<Cell> cellIterator = currentRow.iterator();
        while (cellIterator.hasNext()) {
            Cell currentCell = cellIterator.next();
            columnHeaderMap.put(currentCell.getColumnIndex(), currentCell.getStringCellValue());
        }
//        System.out.println("columnHeaderMap>>>>"+columnHeaderMap);
        return columnHeaderMap;
    }
	
	public static HashMap<String, HashMap<String, String>> buildExcelFileMap(String file,String sheetName, String primaryKey) throws Throwable {
        HashMap<String, HashMap<String, String>> excelFileMap = new HashMap<String, HashMap<String, String>>();
       

 

       
        HashMap<Integer, String> columnHeaderMap = new HashMap<Integer, String>();

 

 

 

        try {
            FileInputStream fileInput = null;
            try {
                fileInput = new FileInputStream(new File(file));
            } catch (Exception e) {
                System.out.println("Exception while finding the file>>>>" + e.toString());
            }

 

 

 

            // @SuppressWarnings("resource")
            Workbook workbook = null;
            if (file.endsWith("xls")) {
                workbook = new HSSFWorkbook(fileInput);
            } else {
                System.out.println("Inside other>>>>");
                workbook = new XSSFWorkbook(fileInput);
            }
            Sheet sheet = workbook.getSheet(sheetName);
            columnHeaderMap = getColumnNames(sheet);
           
            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                if (currentRow.getRowNum() > 0) {
                    Iterator<Cell> cellIterator = currentRow.iterator();
                    HashMap<String, String> rowDataMap = new HashMap<String, String>();
                    while (cellIterator.hasNext()) {
                        Cell currentCell = cellIterator.next();

 

 

 

                        if (currentCell.getCellType() == Cell.CELL_TYPE_STRING) {
                            rowDataMap.put(columnHeaderMap.get(currentCell.getColumnIndex()),
                                    currentCell.getStringCellValue());
                           
                        } else if (currentCell.getCellType() == Cell.CELL_TYPE_BLANK) {
                             
                            rowDataMap.put(columnHeaderMap.get(currentCell.getColumnIndex()), "");
                        }
                        else {
                            rowDataMap.put(columnHeaderMap.get(currentCell.getColumnIndex()),
                                    String.valueOf((currentCell.getNumericCellValue())).split("\\.")[0]);
            
                        }
                    }
                    excelFileMap.put(rowDataMap.get(primaryKey), rowDataMap);
                }
            }

 


        } catch (Exception e) {
            addExceptionToReport("Exception in reading file:"+file+" Exception: "+e.toString(),"","");
        }
        return excelFileMap;
    }
	
	

	public String getSystemLocalTimeForFileName(String format){
		DateTimeFormatter formatType = DateTimeFormatter.ofPattern(format);
		LocalDateTime now = LocalDateTime.now();
		return formatType.format(now);	
	}
	
	
	
	public HashMap<String, HashMap<String, String>> buildEncompassMapFile(String file) throws Exception {
		String filepath = System.getProperty("user.dir");
		filepath = filepath + "\\src\\test\\resources\\"+file;
		File fileName = new File(filepath);
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(fileName));
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheetAt(0);
		int rowCount = 0;
		// int colCount = 0;
		rowCount = sheet.getPhysicalNumberOfRows();
		HashMap<Integer, String> headerMap = getTableHeaders(sheet, 0);
		HashMap<String, HashMap<String, String>> encompassTableMap = new HashMap<String, HashMap<String, String>>();
		for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
			String fieldKey = sheet.getRow(rowIndex).getCell(0).toString();
			HashMap<String, String> rowMap = new HashMap<String, String>();
			for (int colIndex = 1; colIndex < headerMap.size(); colIndex++) {
				HSSFCell cellValue = sheet.getRow(rowIndex).getCell(colIndex);
				String val = "";
				if (cellValue == null) {
					val = "";
				} else {
					if (cellValue.getCellType() == 0) {
						val = cellValue.toString().split("\\.")[0];
					} else {
						val = cellValue.toString();
					}
				}

				rowMap.put(headerMap.get(colIndex), val);
			}
			encompassTableMap.put(fieldKey, rowMap);
		}
		return encompassTableMap;
		

	}
	
	public static void enterValueInTextField(WebDriver driver, String xpath, String value) throws Throwable {
//       waitForElement(driver,xpath);
		Thread.sleep(5000);
        try {
            driver.findElement(By.xpath(xpath)).sendKeys(value);
        } catch (Exception e) {
            e.printStackTrace();
            addExceptionToReport("Exception while entering Text for element"+xpath,"","");
        }
    }
	
	
	public static void waitTimeForSpinner(WebDriver driver) throws Exception{
        System.out.println("Inside waitTimeForSpinner");
        int i;
        int maxTime = 600;//in seconds        
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        for(i=1;i<=maxTime;i++){
            if(driver.findElements(By.xpath("//img[contains(@src,'loader')]")).size()==0)
                break;
            Thread.sleep(1000);        
        }       
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        if(i>maxTime){
            addExceptionToReport("Page not loading waited for 20 mins", "", "");
        }
    }
	
	public static LinkedHashMap<String,String> build1003FieldValueLinkedMap(HashMap<String,String> questionMap ) throws Exception {
        System.out.println("Inside build1003FieldValueLinkedMap");
        System.out.println("questionMap:"+questionMap);
        LinkedHashMap<String,String> expectedAnswersMap = new LinkedHashMap<String,String>();
        try{

            for (String expectedQuestion : questionMap.keySet()){

                String fields = questionMap.get(expectedQuestion);
                String expFieldValue = "";
                if (fields.contains(":")) {
                    for (String test : fields.split(":")) {
                        if (test.contains("%")) {
                            String[] fieldDesc = test.split("%")[1].split("_");
                            String fieldName1 = fieldDesc[0];
                            expFieldValue = fieldDesc[1];
                            fieldName1 = returnFieldName(expectedAnswersMap,fieldName1,expectedQuestion);
                            expectedAnswersMap.put(fieldName1, expFieldValue);   
                        }
                    }
                }
                else {
                    if (fields.contains("%")) {
                        String[] fieldDesc = fields.split("%")[1].split("_");
                        System.out.println("fieldDesc>>" + fieldDesc);
                        String fieldName1 = fieldDesc[0];
                        expFieldValue = fieldDesc[1];
                        fieldName1 = returnFieldName(expectedAnswersMap,fieldName1,expectedQuestion);
                        expectedAnswersMap.put(fieldName1, expFieldValue);

                    } else {
                        expFieldValue = fields;
                        expectedAnswersMap.put(expectedQuestion, expFieldValue);                    
                    }
                }
            }
        }catch(Exception e){
            System.out.println("Exception >>");
            e.printStackTrace();
        }
    System.out.println("expectedAnswersMap>>"+expectedAnswersMap);
        return expectedAnswersMap;
    }
	
	private static String returnFieldName(HashMap<String,String> expectedAnswerMap,String key,String expectedQtn){


        System.out.println("Inside returnFieldName");
        System.out.println("expectedAnswerMap:"+expectedAnswerMap);
        System.out.println("key:"+key);
        System.out.println("expectedQtn:"+expectedQtn);
        if(expectedQtn.equalsIgnoreCase("Co-applicant's mailing address is")||expectedQtn.equalsIgnoreCase("Co-applicant's previous address was")||expectedAnswerMap.containsKey(key)){
            System.out.println("Inside here");
            return key+"fs_fs"+expectedQtn;
        }
        return key;
    }
	
	
	@Override
	public void checkPage() {
		// TODO Auto-generated method stub

	}

}

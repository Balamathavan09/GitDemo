package com.finx.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
//import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import com.tavant.base.DriverFactory;
import com.tavant.kwutils.CustomStep;
import com.tavant.utils.TwfException;

import jxl.read.biff.BiffException;



public class GetAQuote extends CustomStep {
	
	Logger myLogger= Logger.getLogger(com.finx.util.GetAQuote.class);
	Util utilities= new Util();
	GenerateData dummyData = new GenerateData();
	HashMap<String, String> map = new HashMap<String, String>();
	HashMap<String, String> errorMap = new HashMap<String, String>();
	
	private static final String Object = null;
	String rateQuoteIndex = "//div[@class='product-container']/div//div[@class='add-quote-message']";
	String rateQuoteList = "//div[@class='product-container']/div[1]//div/ul/li";
	String title1 = "//div[@class='product-container']/div[";
	String title2 = "]//div//h1/div[1]";
	String Password = ".//*[@id='login_password']"; 	
	String Email = ".//*[@id='login_userId']";
	String autoPromtBoxLogin = ".//div[@class='row input-grp']//button[contains(.,'Login')]";

	String autoPopuploginButton = "(//button[contains(.,'Login')])[3]"; 
	String loginPopUp = ".//*[@id='autop_name']";
	// String loginPopUp = ".//*[@id='autop_name']";

	String headerFieldValueObject = "//div[@class='admin-header-container']/div[@class='row']//div[@class='row'][%s]/div[1]//input";
	// String Qustion = "//div[@class[contains(.,'form-container tf-block-%s
	// active')]]//div[@class='form-group']//form/div[2]//dynamic-control/div/div//label";
	String Qustion = "(//div[@class[contains(.,'form-container tf-block-%s active')]]//div[@class='form-group']//label)[1]";
	// String fieldAtttibute = "//div[@class[contains(.,'form-container
	// tf-block-%s
	// active')]]//div[@class='form-group']//form/div[2]//dynamic-control/div";
	// String fieldAtttibute = "//div[@class[contains(.,'form-container
	// tf-block-%s
	// active')]]//div[@class='form-group']//form/div[2]//dynamic-control/div/div//div/div/div";
	String fieldAtttibute = "((//div[@class[contains(.,'form-container tf-block-%s active')]]//div[@data-type]))[1]";
	String helpTextFieldAttribute="((//div[@class[contains(.,'form-container tf-block-%s')]]//div[@data-type]))[1]";

	String OkayBUtton1 = "//div[@class[contains(.,'form-container tf-block-%s active')]]//div[@class='form-group']//form//button[@class[contains(.,'submit')]]";
	String radioButtonAns = "//div[@class[contains(.,'form-container tf-block-%s active')]]//div[@class='form-group']//form/div[2]//dynamic-control/div/div//div/div/button[contains(.,'%v')]";
	String OkayBUtton2 = "//div[@class[contains(.,'form-container tf-block-%s active')]]//div[@class='form-group']//form//button[@class[contains(.,'submit')]]";
	// String inputText = "//div[@class[contains(.,'form-container tf-block-%s
	// active')]]//div[@class='form-group']//form/div[2]//dynamic-control/div/div//md-input-container//input";
	String inputText = "//div[@class[contains(.,'form-container tf-block-%s active')]]//md-input-container//input";
	// String fieldValidationError = "//div[@class='alert alert-danger']/a";
	
	String errormsg_alert="//div[@class='alert alert-danger']";
	String fieldValidationError = "(//div[@class='alert alert-danger'])[2]";  
	String rateQuoteXpathTitle = "//div[@class='product-container']/div[%s]//div//h1/div[1]";
	String rateQuoteXpathEleName = "//div[@class='product-container']/div[%s]//div/ul/li[%v]/div[2]";
	String rateQuoteXpathEleValue = "//div[@class='product-container']/div[%s]//div/ul/li[%v]/div[1]";
	// String URL = "http://10.131.148.191:9090/#/productsScreen";
	String URL = "http://10.209.1.38:9090/#/productsScreen";
	String addToComcheckBox = "(//input[@name='chkHelper'])[%s]";
	String zipcodeDropDown = "(//div[@class[contains(.,'form-container tf-block-%n active')]]//div[@class='form-group']//form/div[2]//dynamic-control/div/div//dropdown-select/div/div/button)[%s]";
	String zipcodeDropDownSerach = "(//div[@class[contains(.,'form-container tf-block-%n active')]]//div[@class='form-group']//form/div[2]//dynamic-control/div/div//dropdown-select/div/div/ul/div/input)[%s]";
	// String zipCodeDropValue = "((//div[@class[contains(.,'form-container
	// tf-block-%n
	// active')]]//div[@class='form-group']//form/div[2]//dynamic-control/div/div//dropdown-select[%s]/div/div/ul/li)[1])";
	String zipCodeDropValue = "(//div[@class[contains(.,'form-container tf-block-%n active')]]//form/div[2]//location//dropdown-select)[%s]//div/ul/li[1]/a";
	String addToCompareButton = "//button[contains(.,'Add to Compare')]";
	// String downpaymentAlert = "//div[@class[contains(.,'form-container
	// tf-block-3 active')]]//div[@class='form-group']//div[@class='alert
	// alert-warning']";
	String helpText = "//div[contains(@class,'help-sec tf-block-%s')]//div[@class='help-center-section']/div[2]";
//	String createAccountPopUp = "//div[@class='modal fade prequal-modal in']//div[@class='modal-body']//a[contains(.,'Login')]";
	String createAccountPopUp = "//div[@class='modal-dialog']//div[@class='modal-body']//button[contains(.,'Login')]";  

	String Compare_QuotesButton = "//button[contains(.,'Compare Quotes 3')]";
	String logo = "//div[@class='logo']";


	public HashMap<String, String> buildDataMap(String testData) {
		System.out.println("entered build map");
		HashMap<String, String> qaMap = new HashMap<String, String>();
		String[] queAns = testData.split("&&");
		for (String qA : queAns) {
			qaMap.put(qA.split(";")[0].trim(), qA.split(";")[1].trim());
			System.out.println(" Key " + qA.split(";")[0]);
			System.out.println(" Value " + qA.split(";")[1]);
		}
		return qaMap;

	}

	public HashMap<Integer, String> buildDataMaporder(String testData) {
		HashMap<Integer, String> qaMap = new HashMap<Integer, String>();
		String[] queAns = testData.split("&&");
		for (String qA : queAns) {
			Integer a = Integer.parseInt(qA.split(";")[0]);

			qaMap.put(a, qA.split(";")[1]);
		}
		return qaMap;
	}

	public void beforePageLoad() throws TwfException {
		WebDriver driver = DriverFactory.getDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.mpPageReloaded='notYet';");
	}

	public void validatePositiveFlowSelectQuestion2Answer() throws InterruptedException, TwfException, Exception {
		
		myLogger.info("Validate +flow");
		
		System.out.println("Entered validatePositiveFlowSelectQuestion2Answer");
		WebDriver driver = DriverFactory.getDriver();
		System.out.println("inside validatePositiveFlowSelectQuestion2Answer ");
		String autoPromtInfo = step.getDataValue("question_ans_getQuote"); 

		/*
		 * if (selectQuestion2Answer(map, autoPromtInfo).contains("false")) {
		 * addExceptionToReport
		 * ("Expected Question in not enabled.Hence cannot move forward.", "",
		 * ""); }
		 */

		System.out.println("test data string :" + autoPromtInfo.split("%")[0]);
		HashMap<String, String> dataMap = new HashMap<String, String>();
		dataMap = buildDataMap(autoPromtInfo.split("%")[0]);
		String str = answerQuestion(dataMap, autoPromtInfo.split("%")[1]);

		if (str.contains("false")) {
			System.out.println(str);
			addExceptionToReport("Expected Question in not enabled.Hence cannot move forward.", "", str);

		}

	}

	public void validateNegativeFlowSelectQuestion2Answer() throws Exception {

		System.out.println(" Inside validateNegativeFlowSelectQuestion2Answer ");

		String autoPromtInfo = step.getDataValue("question_ans_getQuote");
		System.out.println("test data string :" + autoPromtInfo.split("%")[0]);
		HashMap<String, String> dataMap = new HashMap<String, String>();
		dataMap = buildDataMap(autoPromtInfo.split("%")[0]);

		System.out.println("dataMap is -- " + dataMap); 

		String expectedResult = answerQuestion(dataMap, autoPromtInfo.split("%")[1]);
		System.out.println("expectedResult is --> " + expectedResult);
		String expectedErrorMessage = "× The amount you can put down should not exceed property value you are providing.Change your down payment value.";
		System.out.println("expectedErrorMessage: " + expectedErrorMessage);
		if (expectedResult.contains("false")) {

			if (!(expectedResult.split(";")[1].trim().equalsIgnoreCase(expectedErrorMessage) == false)) {
				addExceptionToReport("Expected Error Message is not displayed", expectedResult.split(";")[1].trim(),
						expectedErrorMessage); 
			}
		} 
	}

	// validation 2

	public void validateNegativeFlow2SelectQuestion2Answer() throws Exception {
		// WebDriver driver = DriverFactory.getDriver();

		System.out.println("Inside validateNegativeFlow -- 2  -- SelectQuestion2Answer");
		String autoPromtInfo = step.getDataValue("question_ans_getQuote");

		System.out.println("test data string :" + autoPromtInfo.split("%")[0]);
		HashMap<String, String> dataMap = new HashMap<String, String>();
		dataMap = buildDataMap(autoPromtInfo.split("%")[0]);

		System.out.println("dataMap is -- " + dataMap);

		String expectedResult = answerQuestion(dataMap, autoPromtInfo.split("%")[1]);
		System.out.println("expectedResult is --> " + expectedResult);

		String expectedErrorMessage = "× Invalid zipcode, please provide state, city and county details";     
		System.out.println("expectedErrorMessage:" + expectedErrorMessage);
		if (expectedResult.contains("false")) {
			System.out.println("entered false");

			if (!(expectedResult.split(";")[1].trim().contains(expectedErrorMessage) == false)) {
				addExceptionToReport("Expected Error Message is not displayed", expectedResult.split(";")[1].trim(),
						expectedErrorMessage);
			}
		} else {
			addExceptionToReport("Expected Error Message is not displayed upon user entering wrong zipcode.", "", "");
		}

	}
	
	
	public String answerQuestion(HashMap<String, String> expectedMap, String autoPromtInfo) throws Exception
	{
		WebDriver driver = DriverFactory.getDriver();
	
		
		String status=null;
		
		for(int i=0;i< expectedMap.size();i++)
		{
			
			//If the control is in Questionaire
			if (driver.findElements(By.xpath(Qustion.replace("%s", String.valueOf(i)))).size() > 0) {			
					String attribute = driver.findElement(By.xpath(fieldAtttibute.replace("%s", String.valueOf(i)))).getAttribute("data-type");
					String qustion = driver.findElement(By.xpath(Qustion.replace("%s", String.valueOf(i)))).getText()
						.trim();
				
					if(qustion!=null)
					{
						myLogger.info("before select "+qustion);
						selectAttribute(driver, attribute, i, expectedMap, qustion);
						myLogger.info("after select "+qustion);
					}
					else
					{
						addExceptionToReport("The Question "+qustion+" is not visible or null", "", "");
					}
					
				if(expectedMap.get(qustion).contains("errormessage"))
				{
					
					if(utilities.waitForElementToBeClikable(driver.findElement(By.xpath(errormsg_alert)),15))
					{
						//Zip code
							String errorMessage=driver.findElement(By.xpath(errormsg_alert)).getText();
							if(errorMessage.contains("Invalid zipcode"))
							{
								String zipText[]=driver.findElement(By.xpath(errormsg_alert)).getText().split("\n");
								status="false;"+driver.findElement(By.xpath(errormsg_alert)).getText();
								break;
							}
							
							else if(errorMessage.contains("exceed property value"))
							{
								//Down Payment
								myLogger.info("Executiong the exceed property value");
								status="false;"+driver.findElement(By.xpath(errormsg_alert)).findElement(By.xpath("./div")).getText().trim();
								break;
							}
				
					}
				}
				
				status="true";
			}
			//Some prompt is open
			else
			{
				if ((autoPromtInfo.trim().equalsIgnoreCase("Skip")) || (autoPromtInfo.trim().equalsIgnoreCase("Continue"))) {
					if (driver.findElement(By.xpath(createAccountPopUp)).isDisplayed()) {
						if (autoPromtInfo.trim().equalsIgnoreCase("Skip")) {
							driver.findElement(By.xpath("//button[contains(.,'Skip')]")).click();  
						} else if ((autoPromtInfo.trim().equalsIgnoreCase("Continue"))) {
							clickOnLoginInCreateAccountPopUp(driver);
						}
					} 
				}				
				else if(autoPromtInfo.trim().equalsIgnoreCase("login"))
				{
					try
					{
						if (driver.findElement(By.xpath(loginPopUp)).isDisplayed()) {
							myLogger.info("Entering Details into the Login Prompt");
							if (autoPromtInfo.contains("login")) {
								enteredDetailsinAutoPopupLogin(driver);
							}
						}
					}
					catch(Exception e)
					{
						myLogger.info("Login Prompt not displayed");
					}	
				}
				
				//Zip code invalid error
				if (driver.findElements(By.xpath(".//*[@class='error']")).size() > 0) {
			
					status = "false;" + driver.findElement(By.xpath(".//*[@class='error']")).getText().trim();
					    break;
				} 
				
				i--;
				
			}//End of else
			

			
		}//End of for
		
		return status;
	}
	
	
	public String selectQuestion2Answer(HashMap<String, String> expectedMap, String autoPromtInfo)
			throws InterruptedException, Exception {
		System.out.println("entered selectQuestion2Answer");
		WebDriver driver = DriverFactory.getDriver();
		String status = "";
		int qusIndex = 0;

		int QusIndexlength = expectedMap.size();
		System.out.println("QusIndexlength size is  " + QusIndexlength);
		while (!driver.getCurrentUrl().contains(URL)) {
			// for (int qusIndex = 0; qusIndex < QusIndexlength ; qusIndex++) {

			System.out.println("qusIndex == " + qusIndex);
			/*
			if(driver.findElement(By.xpath("//button[contains(.,'Show me all quotes')])")).isDisplayed())       
			{
				break;
			}   */                
	
			if (driver.findElements(By.xpath(Qustion.replace("%s", String.valueOf(qusIndex)))).size() > 0) {

				String attribute = driver.findElement(By.xpath(fieldAtttibute.replace("%s", String.valueOf(qusIndex)))).getAttribute("data-type");
				System.out.println("  attribute " + attribute);

				String qustion = driver.findElement(By.xpath(Qustion.replace("%s", String.valueOf(qusIndex)))).getText()
						.trim();
				System.out.println("  qustion " + qustion);
				/*
				 * if (!(driver.findElement(By.xpath(Qustion.replace("%s",
				 * String.valueOf(QusIndex)))).getText().contains(qustion))) {
				 * addExceptionToReport("" , "", ""); }
				 */

				selectAttribute(driver, attribute, qusIndex, expectedMap, qustion);
				status = "true";

			} else {
				System.out.println("Entered the else loop");
				// System.out.println("Qtn count>>>>" +
				// driver.findElements(By.xpath(Qustion.replace("%s",
				// String.valueOf(qusIndex)))).size());

				System.out.println("autoPromtInfo is " + autoPromtInfo.trim());

				
				if ((autoPromtInfo.trim().equalsIgnoreCase("Skip")) || (autoPromtInfo.trim().equalsIgnoreCase("Continue"))) {

					System.out.println("inside " + autoPromtInfo); 

					if (driver.findElement(By.xpath(createAccountPopUp)).isDisplayed()) {

						System.out.println("Entered Create account pop up");

						System.out.println("autoPromtInfo--->" + autoPromtInfo);

						System.out.println("Skip");
						System.out.println(autoPromtInfo.equalsIgnoreCase("Skip"));
						if (autoPromtInfo.trim().equalsIgnoreCase("Skip")) {

							System.out.println(" Skip  ---");
							// clickOnLoginInCreateAccountPopUp(driver);
							driver.findElement(By.xpath("//button[contains(.,'Skip')]")).click();  
							
						} else if ((autoPromtInfo.trim().equalsIgnoreCase("Continue"))) {
							System.out.println(" continue  ---");   
							clickOnLoginInCreateAccountPopUp(driver);
						}
						
					 
     
						System.out.println("check1");
						break;
 
					} 
				} 
				 
				
				
				
				/**
				 * Zip Code Invalid Message shown -Invalid zipcode, please provide state, city and county details 
				 */
				System.out.println("check2");
				if (driver.findElements(By.xpath(fieldValidationError)).size() > 0) {
					System.out.println("check3");
					status = "false;" + driver.findElement(By.xpath(fieldValidationError)).getText().trim();
					  
				} else if (driver.findElements(By.xpath(".//*[@class='error']")).size() > 0) {
					System.out.println("check4");
					status = "false;" + driver.findElement(By.xpath(".//*[@class='error']")).getText().trim();
					    
				} 
				break;    
			}   
			   
			System.out.println(" check 5");
			
			try {
 
			/* if(!(driver.findElement(By.xpath(".//*[@class='content-right-inner']/div/i")).isDisplayed()))
			 {
				 System.out.println( " loan office search not display");
				 break;   
			 }  */ 
				  
				System.out.println("Inside Try.... " + qusIndex);	
				if (driver.findElement(By.xpath(loginPopUp)).isDisplayed()) {
					System.out.println("go to auto popup method-- hsdtkh " + autoPromtInfo);
					Thread.sleep(3000);
					System.out.println("autoPromtInfo " + autoPromtInfo);

					if (autoPromtInfo.contains("login")) {
						System.out.println("nikhil..................");
						enteredDetailsinAutoPopupLogin(driver);
					}
					if (autoPromtInfo.contains("Continue")) {
						System.out.println("Nikhil ");

						enteredDetailsinAutoPopupQuestionAnswerPopUp(driver);
					}

					if (autoPromtInfo.contains("Skip")) {
						System.out.println("Entered Skip condition");
						skipAutoPopupQuestionAnswerPopUp(driver);

					}

					if (autoPromtInfo.contains("Signup")) {
						signUpAutoPopupQuestionAnswerPopUp(driver);
					}

					Thread.sleep(3000);
					System.out.println("Test 1");
					// once issue is fixed remove below line
				//	driver.findElement(By.xpath("//div[@class='ps__scrollbar-y']")).sendKeys(Keys.UP);
					System.out.println("Test 2");
					// status = "true";
  
				}

			} catch (Exception e) {

			}  
 
			qusIndex++;

			try {
                System.out.println("inside try 2 " + qusIndex);
				if (driver.findElements(By.xpath(Qustion.replace("%s", String.valueOf(qusIndex)))).size() == 0) {
					if (driver.findElement(By.xpath("//get-quote/div[@class='loader-rect']")).isEnabled()) {
						System.out.println("Entered 2nd step");
						WebDriverWait wait = new WebDriverWait(driver, 10);
						wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='loader-sect']/img")))));
					}
				}

			} catch (Exception e) {
				
				
			}
			// Util.expliciteWait(driver, 30);
		}
		System.out.println("status = " + status);
		return status;
	}

	public void enteredDetailsinAutoPopupQuestionAnswerPopUp(WebDriver driver) throws InterruptedException, Exception {
		driver = DriverFactory.getDriver();
		System.out.println(" inside enteredDetailsinAutoPopupQuestionAnswerPopUp ");
		// WebElement name =
		// getElementByUsing("FinExp_prequal_popup_autop_name");
/*		driver.findElement(By.id("autop_name")).sendKeys(GenerateData.generateFirstName(10));
		driver.findElement(By.id("autop_userId")).sendKeys(GenerateData.generateEmail(12));
		driver.findElement(By.id("autop_mobileNo")).sendKeys(GenerateData.generateRandomNumber(10));*/
		
		
		String fname = step.getDataValue("editrole_first_name");
		String userId = step.getDataValue("UserName");
		String mobile = step.getDataValue("editrole_mobileNo"); 
		
		
		driver.findElement(By.id("autop_name")).sendKeys(fname);
		driver.findElement(By.id("autop_userId")).sendKeys(userId);
		driver.findElement(By.id("autop_mobileNo")).sendKeys(mobile); 
		
		
		

		// WebElement email =
		// getElementByUsing("FinExp_prequal_popup_autop_userId");
		// WebElement mobileNumber =
		// getElementByUsing("FinExp_prequal_popup_mobileNumber");
		driver.findElement(By.xpath(".//*[@id='myModal1']/div/div/div/div[2]/dynamic-form/form/div[4]/div/button"))
				.click();
		Thread.sleep(10000);
		System.out.println("clicked on continue button");
		//driver.findElement(By.xpath(".//*[@id='myModal1']/div/div/div/div[2]/div/a")).click();
		driver.findElement(By.xpath(".//*[@id='myModal1']//div/a[contains(.,'continue')]")).click();  
		
		System.out.println("clicked on continue link");
		// WebElement continueLink
		// =getElementByUsing("FinExp_Autoprompt_continuelink");
		// Thread.sleep(5000);
		// //name.click();
		// System.out.println("clicked");
		// //name.sendKeys(dummyData.generateFirstName(10));
		//
		//// email.sendKeys(dummyData.generateEmail(12));
		////
		//// mobileNumber.sendKeys(dummyData.generateRandomNumber(10));
		// WebElement Continue_button =
		// getElementByUsing("FinExp_prequal_popup_Continue");
		// Continue_button.click();
		// continueLink.click();

	}

	public void enteredDetailsinAutoPopupLogin(WebDriver driver) throws InterruptedException, Exception {

		driver = DriverFactory.getDriver();
		driver.findElement(By.xpath(autoPromtBoxLogin)).click();
		driver.findElement(By.xpath(Email)).sendKeys("test71automation@gmail.com");
		driver.findElement(By.xpath(Password)).sendKeys("Abc#1234");
		driver.findElement(By.xpath(autoPopuploginButton)).click();
  
		Thread.sleep(5000);

	}

	public void skipAutoPopupQuestionAnswerPopUp(WebDriver driver) throws InterruptedException, Exception {
		System.out.println("entered skip implementataion");
		driver = DriverFactory.getDriver();
		WebElement skipButton = getElementByUsing("FinExp_prequal_popup_skip");

		skipButton.click();
		System.out.println("clicked on skip button");
		Thread.sleep(5000);

	}

	public void captureRecommendedProductsDetails() throws InterruptedException, Exception {

		WebDriver driver = DriverFactory.getDriver();

		String title = null;
		int rateCardIndex = driver.findElements(By.xpath(rateQuoteIndex)).size();
		int rateCardList = driver.findElements(By.xpath(rateQuoteList)).size();

		HashMap<String, HashMap<String, String>> productRateQuoteMap = new HashMap<String, HashMap<String, String>>();
		HashMap<String, String> rateCodeHashMap = new HashMap<String, String>();
		// remove hardcoding of upper limit
		// i --> rateCardIndex
		// Dont hard code xpaths
		System.out.println("rate Card Index" + rateCardIndex);
		for (int i = 1; i <= rateCardIndex; i++) {

			title = driver.findElement(By.xpath(rateQuoteXpathTitle.replace("%s", String.valueOf(i)))).getText().trim();
			// rateCodeHashMap.put(title + i, title);

			// remove hardcoding of upper limit
			System.out.println("rate Card list " + rateCardList);
			for (int j = 1; j < rateCardList; j++) {
				String eleName = null;
				String eleValue = null;
				eleName = driver.findElement(By
						.xpath(rateQuoteXpathEleName.replace("%s", String.valueOf(i)).replace("%v", String.valueOf(j))))
						.getText().trim();

				eleValue = driver.findElement(By.xpath(
						rateQuoteXpathEleValue.replace("%s", String.valueOf(i)).replace("%v", String.valueOf(j))))
						.getText().trim();

				rateCodeHashMap.put(eleName, eleValue);

			}
			productRateQuoteMap.put(title + i, rateCodeHashMap);

		}

		for (String key1 : productRateQuoteMap.keySet()) {
			Map innerMap = productRateQuoteMap.get(key1);
			for (Object key2 : innerMap.keySet()) {
				String key3 = key2.toString();

				System.out.println("------------------------------------------------");
				System.out.println("key: " + key3 + " value: " + rateCodeHashMap.get(key3));
				System.out.println("------------------------------------------------");

			}
		}

	}   
	

	public void validateTableSort(String column_name) throws Exception {

		System.out.println(" Inside ");

		List<String> actual_role = verifyTableColumnValues(column_name, "Test");
		System.out.println("actual_role" + actual_role);
		List<String> Soted = new ArrayList<String>();
		System.out.println("actual_role" + actual_role);
		Soted = actual_role;
		System.out.println("Soted" + Soted);
		Collections.sort(Soted);
		boolean result = isTwoArrayListsWithSameValues(Soted, actual_role);
		System.out.println("result is " + result);
		if (!(result)) {
			addExceptionToReport(" Coulmn - " + column_name + " is not sorted  ",
					"  Coulmn - " + column_name + " is not sorted   ",
					"   Coulmn - " + column_name + " should  be  sorted  ");

		}

	}

	public boolean isTwoArrayListsWithSameValues(List<String> list1, List<String> list2) {
		if ((list1 == null && list2 == null)) {

			return false;
		} else if ((list1 == null && list2 != null) || (list1 != null && list2 == null)) {
			return false;
		} else if (list1.size() != list2.size()) {

			return false;
		}

		for (int counter = 0; counter < list2.size(); counter++) {

			// System.out.println("list1.get(counter)" + list1.get(counter));
			// System.out.println("list2.get(counter)" + list2.get(counter));
			if (!(list1.get(counter).equals(list2.get(counter)))) {
				return false;
			}
		}
		return true;
	}

	public List<String> verifyTableColumnValues(String expectedColumnName, String expectedColumnValue)
			throws Exception {
		String tableHeaderObject = "//div[@class='ui-datatable-tablewrapper']/table/thead/tr/th";
		String columnObject = "//div[@class='ui-datatable-tablewrapper']/table/thead/tr/th[%s]/span[@class='ui-column-title']";
		String tableBodyObject = "//div[@class='ui-datatable-tablewrapper']/table/tbody/tr";
		List<String> columnValuesList = new ArrayList<String>();
		HashMap<String, Integer> columnMap = new HashMap<String, Integer>();

		WebDriver driver = DriverFactory.getDriver();
		int columnCount = driver.findElements(By.xpath(tableHeaderObject)).size();

		for (int colIndex = 1; colIndex <= columnCount; colIndex++) {
			String displayedColName = driver.findElement(By.xpath(columnObject.replace("%s", String.valueOf(colIndex))))
					.getText();
			if (displayedColName.length() > 0) {
				columnMap.put(displayedColName, colIndex);
			}
		}

		columnValuesList = fetchAllColumnValues(tableBodyObject, columnMap.get(expectedColumnName));
		// compareColumnValues(columnValuesList,expectedColumnName);
		return columnValuesList;
	}

	public List<String> fetchAllColumnValues(String tableBodyObject, int columnIndex) throws Exception {
		String tableContentObject = "//div[@class='ui-datatable-tablewrapper']/table/tbody/tr[%s]/td[%c]/span[@class='ui-cell-data']";
		List<String> columnValuesList = new ArrayList<String>();
		WebDriver driver = getWebDriver();

		int rowCount = driver.findElements(By.xpath(tableBodyObject)).size();

		for (int rowIndex = 1; rowIndex <= rowCount; rowIndex++) {
			String revisedTableBodyObject = tableContentObject.replace("%s", String.valueOf(rowIndex));
			String displayedColumnValue = driver
					.findElement(By.xpath(revisedTableBodyObject.replace("%c", String.valueOf(columnIndex)))).getText();
			columnValuesList.add(displayedColumnValue);
		}
		// }
		System.out.println("columnValuesList" + columnValuesList);
		return columnValuesList;
	}

	public void compareColumnValues(List<String> displayedList, String expectedColumnValue) throws Exception {
		for (int colIndex = 0; colIndex < displayedList.size(); colIndex++) {
			if (displayedList.get(colIndex).trim().equalsIgnoreCase(expectedColumnValue.trim()) == false) {
				addExceptionToReport(
						"Expected Column Value is not matching with the Expected Value in row number " + colIndex + 1,
						displayedList.get(colIndex), expectedColumnValue.trim());
			}
		}
	}

	public void producatPricingComapre(String result) throws InterruptedException, Exception {

		System.out.println(" Inside producatPricingComapre");
		WebDriver driver = DriverFactory.getDriver();
		String status = "";

		boolean flag = true;

		for (int addCard = 1; addCard <= 3; addCard++) {

			driver.findElement(By.xpath(addToComcheckBox.replace("%s", String.valueOf(addCard)))).click();

		}
		driver.findElement(By.xpath(addToCompareButton)).click();

		driver.findElement(By.xpath(Compare_QuotesButton)).click();

	}

	public void productPricingComapreValidation(String result) throws InterruptedException, Exception {
		WebDriver driver = DriverFactory.getDriver();
		String status = "";

		System.out.println("inside productPricingComapreValidation   ");

		boolean flag = true;

		for (int addCard = 1; addCard <= 4; addCard++) {

			if (addCard == 4) {
				driver.findElement(By.xpath(addToComcheckBox.replace("%s", String.valueOf(addCard)))).click();
				flag = driver.findElement(By.xpath(addToComcheckBox.replace("%s", String.valueOf(addCard))))
						.isSelected();

				if (flag) {
					addExceptionToReport(
							"test whether user is not able to add product when there are 3 products chosen for comparison",
							"User is able to add product more than 3 ",
							"User is not able to add product when there are 3 products chosen for comparison");
				}

			}

			driver.findElement(By.xpath(addToComcheckBox.replace("%s", String.valueOf(addCard)))).click();

		}
		driver.findElement(By.xpath(addToCompareButton)).click();
		driver.findElement(By.xpath(Compare_QuotesButton)).click();

	}

	public String selectQuestion2AnswerChange(HashMap<String, String> expectedMap, String autoPromtInfo)
			throws InterruptedException, Exception {
		WebDriver driver = DriverFactory.getDriver();
		String status = "";

		int QusIndexlength = expectedMap.size();
		System.out.println("QusIndexlength size is  " + QusIndexlength);

		for (int qusIndex = 0; qusIndex < QusIndexlength; qusIndex++) {
			String attribute = driver.findElement(By.xpath(fieldAtttibute.replace("%s", String.valueOf(qusIndex))))
					.getAttribute("data-type");
			String qustion = driver.findElement(By.xpath(Qustion.replace("%s", String.valueOf(qusIndex)))).getText()
					.trim();

			selectAttribute(driver, attribute, qusIndex, expectedMap, qustion);

			status = "true";

			try {
				if (driver.findElement(By.xpath(loginPopUp)).isDisplayed()) {
					System.out.println("go to auto popup method");
					Thread.sleep(3000);
					if (autoPromtInfo.equalsIgnoreCase("login")) {
						enteredDetailsinAutoPopupLogin(driver);
					}
					if (autoPromtInfo.equalsIgnoreCase("Continue")) {
						enteredDetailsinAutoPopupQuestionAnswerPopUp(driver);
					}

					if (autoPromtInfo.equalsIgnoreCase("Skip")) {
						skipAutoPopupQuestionAnswerPopUp(driver);
					}

				}
			} catch (Exception e) {

			}

			// QusIndex1++;

			/*
			 * try {
			 * 
			 * if (driver.findElements(By.xpath(Qustion.replace("%s",
			 * String.valueOf(QusIndex1)))).size() == 0) { if
			 * (driver.findElement(By.xpath(
			 * "//get-quote/div[@class='loader-rect']")).isEnabled()) {
			 * System.out.println("Entered 2nd step"); WebDriverWait wait = new
			 * WebDriverWait(driver, 10);
			 * wait.until(ExpectedConditions.not(ExpectedConditions
			 * .visibilityOf(driver.findElement(By.xpath(
			 * "//div[@class='loader-sect']/img"))))); } }
			 * 
			 * } catch (Exception e) { }
			 */
			// Util.expliciteWait(driver, 30);
		}

		return status;
	}  
 
	public void validateAnswerChangeFlow() throws InterruptedException, TwfException, Exception { 
 
		WebDriver driver = DriverFactory.getDriver();
		System.out.println(" inside validateAnswerChangeFlow ");
		String autoPromtInfo = step.getDataValue("changeoption"); 
 
		/* 
		 * if (selectQuestion2Answer(map, autoPromtInfo).contains("false")) {
		 * addExceptionToReport
		 * ("Expected Question in not enabled.Hence cannot move forward.", "",
		 * ""); }
		 */

		System.out.println("test data string :" + autoPromtInfo.split("%")[0]);
		HashMap<String, String> dataMap = new HashMap<String, String>();
		dataMap = buildDataMap(autoPromtInfo.split("%")[0]);
		String str = selectQuestion2AnswerChange(dataMap, autoPromtInfo.split("%")[1]);

		if (str.contains("false")) {
			System.out.println(str);
			addExceptionToReport("Expected Question in not enabled.Hence cannot move forward.", "", str);

		}
		Thread.sleep(1000);
	//	driver.findElement(By.xpath("//div[@class='ps__scrollbar-y']")).sendKeys(Keys.);
		// System.out.println("scr " +  scr);
		//scr top: 244px; height: 140px;
		
	//	((JavascriptExecutor) driver).executeScript("window.scrollBy(0,70)"); 
		
		WebElement firstTimeHOmeBuyer=driver.findElement(By.xpath("//label[contains(text(),'I am a first time home buyer')]"));
		  
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)",firstTimeHOmeBuyer); 
		
		System.out.println("SCROLL BAR UP ");
		
		if(!utilities.waitForElementToBeClikable(firstTimeHOmeBuyer, 5))
		{
			addExceptionToReport("Element Buy a New Home not visible", "", str);
		}

		
		System.out.println("  Scroll up --");
		
		//driver.findElement(By.xpath("//div[@class='ps__scrollbar-y']")).sendKeys(Keys.DOWN); 
		
		// captureRecommendedProductsDetails();

	}

	public String verifyQuestionOrdering(HashMap<String, String> expectedMap, String autoPromtInfo,
			HashMap<Integer, String> SequenceMap, HashMap<String, String> HelpTextMap)
			throws InterruptedException, Exception {
		WebDriver driver = DriverFactory.getDriver();
		String status = "";

		int QusIndexlength = expectedMap.size();
		int sequenceMapLength = SequenceMap.size();
		int HelpTextMapLength = HelpTextMap.size();

		for (int qusIndex = 0; qusIndex < sequenceMapLength; qusIndex++) {

			System.out.println("QusIndex1  is --->" + qusIndex);
			String attribute = driver.findElement(By.xpath(helpTextFieldAttribute.replace("%s", String.valueOf(qusIndex))))
					.getAttribute("data-type");
			String question = driver.findElement(By.xpath(Qustion.replace("%s", String.valueOf(qusIndex)))).getText()
					.trim();
			String actualHelpText=null;
			try
			{
				actualHelpText = driver.findElement(By.xpath(helpText.replace("%s", String.valueOf(qusIndex))))
					.getText().trim();
			}
			catch (Exception e)
			{
				if(question.equalsIgnoreCase("I plan to buy a"))
				{
					actualHelpText="";
				}
				else
				{
					addExceptionToReport("help text element not found","","");
				}

			}
			String expectedHelpText = HelpTextMap.get(question).trim();

			System.out.println("actualHelpText  is   --> " + actualHelpText);
			System.out.println("expectedHelpText  is --> " + expectedHelpText);

			if (!(actualHelpText.contains(expectedHelpText))) {
				addExceptionToReport("Verifiation of HELP Text for question " + question + " failed.",
						"Actual Help Text  is " + actualHelpText, "expected Help Text is  " + expectedHelpText);
			}

			String expectedValue = SequenceMap.get(qusIndex);
			System.out.println("question  is   --> " + question);
			System.out.println("expectedValue  is --> " + expectedValue);

			System.out.println("------------------------------");
			if (!(expectedValue.contains(question))) {
				addExceptionToReport("Expected Question is not displayed in index " + qusIndex,
						"expected question is " + expectedValue, "actual question is  " + question);
			}

			selectAttribute(driver, attribute, qusIndex, expectedMap, question);
			status = "true";

			try {
				if (driver.findElement(By.xpath(loginPopUp)).isDisplayed()) {
					System.out.println("go to auto popup method");
					Thread.sleep(3000);
					if (autoPromtInfo.equalsIgnoreCase("login")) {
						enteredDetailsinAutoPopupLogin(driver);
					}
					if (autoPromtInfo.equalsIgnoreCase("Continue")) {
						enteredDetailsinAutoPopupQuestionAnswerPopUp(driver);
					}

					if (autoPromtInfo.equalsIgnoreCase("Skip")) {
						skipAutoPopupQuestionAnswerPopUp(driver);
					}
				}
			} catch (Exception e) {

			}
		}
		return status;
	}
	
	
	
	

	public void selectAttribute(WebDriver driver, String attribute, int qusIndex, HashMap<String, String> expectedMap,
			String qustion) throws InterruptedException, TwfException, BiffException, IOException {

		String val=String.valueOf((String) expectedMap.get(qustion));
		
		//For Zip code and Property validation , have appended the error message after entering the 
		if(val.contains("errormessage"))
		{
			val=val.split("#")[0];
		}
		
		
		if (attribute.equalsIgnoreCase("radioBtn")) {

		
			System.out.println("qustion is -- " + qustion + " =--val is" + val);
			driver.findElement(By.xpath(radioButtonAns.replace("%s", String.valueOf(qusIndex)).replace("%v", val)))
					.click();


		} else if (attribute.equalsIgnoreCase("currency")) {
			System.out.println("question is  " + qustion+"value is  " + val);
			
			if(qustion.contains("My total assets"))
			{
				driver.findElement(By.xpath(inputText.replace("%s", String.valueOf(qusIndex)))).click();
			}
			
			driver.findElement(By.xpath(inputText.replace("%s", String.valueOf(qusIndex)))).clear();
			driver.findElement(By.xpath(inputText.replace("%s", String.valueOf(qusIndex))))
					.sendKeys(val);
			driver.findElement(By.xpath(OkayBUtton1.replace("%s", String.valueOf(qusIndex)))).click();
  
			if (qustion.contains("I can put down")) {  

				System.out.println(" inside  down payment condition     ");
				driver.findElement(By.xpath(OkayBUtton1.replace("%s", String.valueOf(qusIndex)))).click();
			}
			
			
			  
	/*		try {
				
				System.out.println(   " Inside                                                                         try 1 ");
				driver.findElement(By.xpath(OkayBUtton1.replace("%s", String.valueOf(qusIndex)))).click();
				System.out.println(   " Inside                                                                         try 2 "); 
			} catch (Exception e) {
				System.out.println(" nikhil.......");  			
				if (driver.findElements(By.xpath("//div[@class='alert alert-danger']")).size() > 0) {
			

				    	driver.findElement(By.xpath(OkayBUtton1.replace("%s", String.valueOf(qusIndex)))).click();
				}         
            
			}   */        
 
		}       

		else if (attribute.equalsIgnoreCase("text")) {
			System.out.println("expectedMap.get(qustion): " + val);
			driver.findElement(By.xpath(inputText.replace("%s", String.valueOf(qusIndex)))).clear(); 
			driver.findElement(By.xpath(inputText.replace("%s", String.valueOf(qusIndex)))).sendKeys(val);  
			driver.findElement(By.xpath(OkayBUtton1.replace("%s", String.valueOf(qusIndex)))).click();
			
			

		} else if (attribute.equalsIgnoreCase("location")) {
			System.out.println("entered zipcode code");
			String ZipcodeDropDown1 = zipcodeDropDown.replace("%n", String.valueOf(qusIndex));
			
			//state drop down
			String zipcodeDropDownSerach1 = zipcodeDropDownSerach.replace("%n", String.valueOf(qusIndex));
			
			//state value in drop down
			String zipCodeDropValue1 = zipCodeDropValue.replace("%n", String.valueOf(qusIndex));
			
			
			System.out.println();
			String ans = val;
			System.out.println("qustion is " + qustion);
			System.out.println("ans is " + ans);

			String zipcode[] = ans.split(",");
			int zipcodedatalength = zipcode.length;
			System.out.println(": " + zipcodedatalength);

			System.out.println("zipcodedatalength " + zipcodedatalength);
			if (zipcodedatalength > 1) {

				System.out.println(" inside if condition + " + zipcodedatalength);
				for (int zipindex = 1; zipindex <= 3; zipindex++) {
					System.out.println(ZipcodeDropDown1.replace("%s", String.valueOf(zipindex)));
					
					//click the state drop donw
					driver.findElement(By.xpath(ZipcodeDropDown1.replace("%s", String.valueOf(zipindex)))).click();
					if (!(zipindex == 3)) {
						System.out.println("Entered zipindex == 3");
						//select the state text box
						System.out.println(zipcodeDropDownSerach1.replace("%s", String.valueOf(zipindex)));
						System.out.println("zipcode[zipindex]" + zipcode[zipindex]);
						
						//send the state value - eg alabama
						driver.findElement(By.xpath(zipcodeDropDownSerach1.replace("%s", String.valueOf(zipindex))))
								.sendKeys(zipcode[zipindex].trim());
						Thread.sleep(4000);
						
						//click on state value when filtered
						driver.findElement(By.xpath(zipCodeDropValue1.replace("%s", String.valueOf(zipindex)))).click();
						System.out.println("selected the option");
					}
					String result = driver
							.findElement(By.xpath(zipCodeDropValue1.replace("%s", String.valueOf(zipindex)))).getText();
					if (result.equalsIgnoreCase("No results found")) {
						addExceptionToReport("Expected Zip Code Value is not displayed in drop down.", result,
								zipcode[zipindex]);
					}

				}
							
				
			} else {
				System.out.println("entered else");
				System.out.println("zipcode[0]: " + zipcode[0]);
				driver.findElement(By.xpath(inputText.replace("%s", String.valueOf(qusIndex))))
						.sendKeys(zipcode[0].trim());

				System.out.println("zipcode    " + zipcode[0].trim());
				
				//Thread.sleep(3000);
				
				
				utilities.waitForElementToBeInVisible(By.xpath("html/body/fin-xp-app/fin-exp-anonymous/div"),10);
				
				// driver.findElement(By.xpath(".//label[contains(.,'I plan to
				// buy a property at')]")).click();

				/*
				 * System.out.println("*******************");
				 * driver.findElement(By.xpath(".//*[@for='getQuote.zipcode']"))
				 * .click(); Thread.sleep(5000);
				 */                 
			}     

			/*
			 * if(driver.findElement(By.xpath(OkayBUtton2.replace("%s",
			 * String.valueOf(qusIndex)))).isEnabled()){
			 * System.out.println("click on okay button");
			 * driver.findElement(By.xpath(OkayBUtton2.replace("%s",
			 * String.valueOf(qusIndex)))).click();
			 * 
			 * 
			 * }
			 */
			System.out.println("qusIndex"+qusIndex);
			driver.findElement(By.xpath(OkayBUtton2.replace("%s", String.valueOf(qusIndex)))).click();
		}

	}

	public void validateSequentialQuestionandHelpTextFlow() throws InterruptedException, TwfException, Exception {

		WebDriver driver = DriverFactory.getDriver();
		System.out.println(" inside validateSequentialQuestionandHelpTextFlow ");
		String autoPromtInfo = step.getDataValue("QuestionAnswer");
		// System.out.println("test data string :" +
		// autoPromtInfo.split("%")[0]);
		HashMap<String, String> dataMap = new HashMap<String, String>();
		dataMap = buildDataMap(autoPromtInfo.split("%")[0]);

		String HelpText = step.getDataValue("HelpText");
	//	System.out.println("HelpText data string :" + HelpText.split("%")[0]); 

		System.out.println("-----------11111---------");

		HashMap<String, String> HelpTextMap = new HashMap<String, String>();
		HelpTextMap = buildDataMap(HelpText.split("%")[0]);
		System.out.println("returned from build map");
	//	System.out.println("HelpTextMap: " + HelpTextMap);

		System.out.println("--------22222-------------");

		String SequenceQuestion = step.getDataValue("SequenceQuestion");
	//	System.out.println("SequenceQuestion data string :" + SequenceQuestion.split("%")[0]);

		System.out.println("---------------------");
		HashMap<Integer, String> SequencedataMap = new HashMap<Integer, String>();
		SequencedataMap = buildDataMaporder(SequenceQuestion.split("%")[0]);

		System.out.println(" After buildDataMaporder");
		
		getElementByUsing("FinExp_NewApplication").click();
		
		Thread.sleep(3000);    
		
		getElementByUsing("FinExp_RateQuoteLink").click(); 
		
		System.out.println("check..");
		
		String str = verifyQuestionOrdering(dataMap, autoPromtInfo.split("%")[1], SequencedataMap, HelpTextMap);

		
		if (str.contains("false")) {
			System.out.println(str);
			addExceptionToReport("Expected Question in not enabled.Hence cannot move forward.", "", str);

		}

	}

	public void signUpAutoPopupQuestionAnswerPopUp(WebDriver driver) throws InterruptedException, Exception {

		driver = DriverFactory.getDriver();
		WebElement skipButton = getElementByUsing("FinExp_prequal_popup_Sign_Up");
		WebElement signUp = getElementByUsing("FinExp_prequal_popup_Sign_Up_FN");
		skipButton.click();
		Thread.sleep(2000);
		WebElement name = getElementByUsing("FinExp_prequal_popup_Sign_Up_FN");
		name.sendKeys(GenerateData.generateFirstName(10));

	} 

	public void clickOnLoginInCreateAccountPopUp(WebDriver driver) throws Exception {
		System.out.println("Entered clickOnLoginInCreateAccountPopUp ");
		// driver.findElement(By.xpath(".//a[contains(.,'Login')]")).click();
		driver.findElement(By.xpath(".//*[@id='myModal1']//button[contains(.,'Login')]")).click();        		
		System.out.println("Vlicked on login button in pop up");
		utilities.waitForElement(By.xpath(Email),10);
		
		driver.findElement(By.xpath(Email)).clear();
		driver.findElement(By.xpath(Email)).sendKeys("test71automation@gmail.com");
		driver.findElement(By.xpath(Password)).sendKeys("Abc#1234"); 
		driver.findElement(By.xpath(autoPopuploginButton)).click();
		
		myLogger.info("Entered Email and Password");
 
		Thread.sleep(2000);  
 
	}  

	public void verifyDashboardData() throws InterruptedException, TwfException, Exception {
		System.out.println("entered verifyDashboardData");
		WebDriver driver = DriverFactory.getDriver();
		List<WebElement> rows = (List<WebElement>) driver.findElements(
				By.xpath(".//div[@class='container-fluid application-in-progress-sec']//div[@class='table-row']"));
		int countOfRecords = rows.size();
		System.out.println("countOfRecords- " + countOfRecords);
		String dataFromExcel = step.getDataValue("DashboardDataVerification");
		System.out.println("dataFromExcel--" + dataFromExcel);
		String[] datavalue = dataFromExcel.trim().split("%");
		int k = 1;
		try {
			for (int excel_index = 0, ui_index = 1; ui_index <= countOfRecords; ui_index++) {
				String refNumber = datavalue[excel_index].trim();
				System.out.println("refNumber: " + refNumber);

				String refLoanNumber = driver.findElement(
						By.xpath("(.//div[@class='container-fluid application-in-progress-sec']/div[2]/div[1]/div["
								+ ui_index + "]//div[@class='table-cell']/div[1]/div[2])[1]"))
						.getText();
				System.out.println("refLoanNumber = " + refLoanNumber);

				if (refLoanNumber.contains(refNumber)) {
					System.out.println("record found");

					for (int j = 2; j <= 8; j++) {
						if (j < 8) {
							String columnvalue = driver.findElement(By
									.xpath("(.//div[@class='container-fluid application-in-progress-sec']/div[2]/div[1]/div["
											+ ui_index + "]//div[@class='table-cell']/div[1]/div[2])[" + j + "]"))
									.getText();

							String columndata = datavalue[k++].trim();
							if (columnvalue.contains(columndata)) {
								System.out.println("columnvalue:" + columnvalue);
								System.out.println("columndata: " + columndata);

								System.out.println("data verified");
							} else {
								addExceptionToReport("Test Failed", "data did not match", " ");
							}
						} else if (j == 8) {
							System.out.println("entered j==8 loop");
							driver.findElement(By.xpath(".//button[contains(.,'Resume')]")).click();
							Thread.sleep(3000);
							String getStartedHeaderText = driver
									.findElement(By.xpath(".//div[@class='breadcrumb-section']//a[2]")).getText();
							if (getStartedHeaderText.contains("Apply Loan")) {
								System.out.println("Header verified");
								break;
							} else
								addExceptionToReport("Test Failed", "Header didn't match with expected", " ");
						}

					}
					System.out.println(" break from i loop ");
					break;

				}
 
			}

		}

		catch (Exception e) {
			System.out.println("failed to find the record");
			addExceptionToReport("failed to find the record", "", "");

		}
 
	}  

	@Override
	public void checkPage() {

		// TODO Auto-generated method stub

	}

}

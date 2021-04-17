package com.finx.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import jxl.read.biff.BiffException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tavant.base.DriverFactory;
import com.tavant.base.WebPage;
import com.tavant.kwutils.CustomStep;
import com.tavant.kwutils.KWVariables;
import com.tavant.kwutils.Step;
import com.tavant.utils.TwfException;

public class ProductPricing extends CustomStep {
	String rateQuoteIndex = "//div[@class='product-container']/div//div[@class='add-quote-message']";
	String rateQuoteList = "//div[@class='product-container']/div[1]//div/ul/li";
	String title1 = "//div[@class='product-container']/div[";
	String title2 = "]//div//h1/div[1]";
	String Password = "//div[@class='modal fade in']//div[@class='modal-dialog login-section modal-md']//div[@class='modal-body']//input[@id='login_password']";
	String Email = "//div[@class='modal fade in']//div[@class='modal-dialog login-section modal-md']//div[@class='modal-body']//input[@id='login_userId']";
	String autoPromtBoxLogin = ".//*[@id='myModal1']/div/div/div/div[2]/div/div[2]/button[1]";
	String autoPopuploginButton = "(//button[contains(.,'Login')])[3]";
	String loginPopUp = ".//*[@id='autop_name']";
	String headerFieldValueObject = "//div[@class='admin-header-container']/div[@class='row']//div[@class='row'][%s]/div[1]//input";
	String Qustion = "//div[@class[contains(.,'form-container tf-block-%s active')]]//div[@class='form-group']//form/div[2]//dynamic-control/div/div//label";
	String fieldAtttibute = "//div[@class[contains(.,'form-container tf-block-%s active')]]//div[@class='form-group']//form/div[2]//dynamic-control/div";
	String OkayBUtton1 = "//div[@class[contains(.,'form-container tf-block-%s active')]]//div[@class='form-group']//form/div[3]/div/button";
	String radioButtonAns = "//div[@class[contains(.,'form-container tf-block-%s active')]]//div[@class='form-group']//form/div[2]//dynamic-control/div/div//div/div/button[contains(.,'%v')]";
	String OkayBUtton2 = "//div[@class[contains(.,'form-container tf-block-%s active')]]//div[@class='form-group']//form/div[4]/div/button";
	String inputText = "//div[@class[contains(.,'form-container tf-block-%s active')]]//div[@class='form-group']//form/div[2]//dynamic-control/div/div//md-input-container//input";
	String fieldValidationError = "//div[@class='alert alert-warning']/div";
	String rateQuoteXpathTitle = "//div[@class='product-container']/div[%s]//div//h1/div[1]";
	String rateQuoteXpathEleName = "//div[@class='product-container']/div[%s]//div/ul/li[%v]/div[2]";
	String rateQuoteXpathEleValue = "//div[@class='product-container']/div[%s]//div/ul/li[%v]/div[1]";
	String URL = "http://10.131.148.191:9005/#/productsScreen";

	String addToComcheckBox = "(//input[@name='chkHelper'])[%s]";

	String addToCompareButton = "//button[contains(.,'Add to Compare')]";

	String Compare_QuotesButton = "//button[contains(.,'Compare Quotes 3')]";
	String logo = "//div[@class='logo']";
	GenerateData2 dummyData = new GenerateData2();

	HashMap<String, String> map = new HashMap<String, String>();
	HashMap<String, String> errorMap = new HashMap<String, String>();

	/**
	 * Method Name: ProductPricing 
	 * Purpose: Adding question and answer into the map
	 * 
	 */
	public ProductPricing() {
		map.put("I am looking to", "Buy a new home");
		map.put("I am a First Time Home Buyer", "Yes");
		map.put("Property Value", "50000");
		map.put("DownPayment Amount", "40000");
		map.put("My credit score is", "800+");
		map.put("I plan to buy property worth", "50000");
		map.put("I plan to buy a", "Single Family Residence");
		map.put("I will use this property as", "My Primary Home");
		map.put("Total Gross Income", "300000");
		map.put("My Monthly Expenses are", "2000");
		map.put("My Total assets are", "50000");
		map.put("I am a Veteran", "Yes");
		map.put("I can put down", "10000");
		map.put("I plan to buy a property at", "27615");
		map.put("My Annual Gross Income is", "50000");
		map.put("My preferred loan amortization type", "FIXED");
		map.put("Alternate Email", "Abcxyz@tavant.com");
		map.put(" I will use this property as", "My Primary Home");

		errorMap.put("My preferred loan amortization type", "FIXED");
		errorMap.put("Alternate Email", "Abcxyz@tavant.com");
		errorMap.put("I am looking to", "Buy a new home");
		errorMap.put("I am a First Time Home Buyer", "Yes");
		errorMap.put("Property Value", "50000");
		errorMap.put("DownPayment Amount", "50000");
		errorMap.put("My credit score is", "800+");
		errorMap.put("I plan to buy property worth", "50000");
		errorMap.put("I plan to buy a", "Single Family Residence");
		errorMap.put("I will use this property as", "My Primary Home");
		errorMap.put("Total Gross Income", "300000");
		errorMap.put("My Monthly Expenses are", "2000");
		errorMap.put("My Total assets are", "50000");
		errorMap.put("I am a Veteran", "Yes");
		errorMap.put("I can put down", "50000");
		errorMap.put("I plan to buy a property at", "27615");
		errorMap.put("My Annual Gross Income is", "50000");

	}

	/**
	 * Method Name: beforePageLoad Purpose: perform action before page load
	 * 
	 * @throws TwfException
	 *
	 */

	public void beforePageLoad() throws TwfException {
		WebDriver driver = DriverFactory.getDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.mpPageReloaded='notYet';");
	}

	/**
	 * Method Name: validatePositiveFlowSelectQuestion2Answer 
	 * Purpose: validate positive flow by answering to the questions
	 * 
	 * @param String
	 * @throws InterruptedException
	 * @throws TwfException
	 * @throws Exception
	 * @return
	 */

	public void validatePositiveFlowSelectQuestion2Answer(String autoPromtInfo)
			throws InterruptedException, TwfException, Exception {

		if (selectQuestion2Answer(map, autoPromtInfo).contains("false")) {
			addExceptionToReport(
					"Expected Question in not enabled.Hence cannot move forward.",
					"", "");
		}

		// captureRecommendedProductsDetails();

	}

	/**
	 * Method Name: validateNegativeFlowSelectQuestion2Answer
	 *  Purpose: validate error message for negative flow
	 * 
	 * @param String
	 * @throws Exception
	 * @return
	 */

	public void validateNegativeFlowSelectQuestion2Answer(String autoPromtInfo)
			throws Exception {
		String expectedResult = selectQuestion2Answer(errorMap, autoPromtInfo);
		String expectedErrorMessage = "The amount you can put down should not exceed property value you are providing.Change your down payment value.";
		if (expectedResult.contains("false")) {
			if (expectedResult.split(";")[1].trim().equalsIgnoreCase(
					expectedErrorMessage) == false) {
				addExceptionToReport("Expected Error Message is not displayed",
						expectedResult.split(";")[1].trim(),
						expectedErrorMessage);
			}
		} else {
			addExceptionToReport(
					"Expected Error Message is not displayed when put down amount is equal to Property price.",
					"", "");
		}
	}

	/**
	 * Method Name: selectQuestion2Answer
	 * Purpose: Select answer based on questions
	 * 
	 * @param hashmap
	 *            and string
	 * @throws InterruptedException
	 * @throws Exception
	 * @return
	 */

	public String selectQuestion2Answer(HashMap<String, String> expectedMap,
			String autoPromtInfo) throws InterruptedException, Exception {
		WebDriver driver = DriverFactory.getDriver();
		String status = "";
		int QusIndex = 0;

		while (!driver.getCurrentUrl().equalsIgnoreCase(URL)) {

			if (driver.findElements(
					By.xpath(Qustion.replace("%s", String.valueOf(QusIndex))))
					.size() > 0) {

				String attribute = driver.findElement(
						By.xpath(fieldAtttibute.replace("%s",
								String.valueOf(QusIndex)))).getAttribute(
						"ng-reflect-ng-switch");
				String qustion = driver
						.findElement(
								By.xpath(Qustion.replace("%s",
										String.valueOf(QusIndex)))).getText()
						.trim();

				Object value = expectedMap.get(qustion);
				if (attribute.equalsIgnoreCase("radioBtn")) {
					String val = String.valueOf((String) expectedMap
							.get(qustion));
					driver.findElement(
							By.xpath(radioButtonAns.replace("%s",
									String.valueOf(QusIndex))
									.replace("%v", val))).click();

					driver.findElement(
							By.xpath(OkayBUtton1.replace("%s",
									String.valueOf(QusIndex)))).click();

					Thread.sleep(3000);

				} else if (attribute.equalsIgnoreCase("currency")) {

					driver.findElement(
							By.xpath(inputText.replace("%s",
									String.valueOf(QusIndex)))).sendKeys(
							expectedMap.get(qustion));

					driver.findElement(
							By.xpath(OkayBUtton1.replace("%s",
									String.valueOf(QusIndex)))).click();

					Thread.sleep(3000);

				}

				else if (attribute.equalsIgnoreCase("text")) {

					driver.findElement(
							By.xpath(inputText.replace("%s",
									String.valueOf(QusIndex)))).sendKeys(
							expectedMap.get(qustion));

					driver.findElement(
							By.xpath(OkayBUtton1.replace("%s",
									String.valueOf(QusIndex)))).click();

					Thread.sleep(3000);

				} else if (attribute.equalsIgnoreCase("zipcode")) {

					driver.findElement(
							By.xpath(inputText.replace("%s",
									String.valueOf(QusIndex)))).sendKeys(
							expectedMap.get(qustion));
					driver.findElement(
							By.xpath(OkayBUtton2.replace("%s",
									String.valueOf(QusIndex)))).click();
					Thread.sleep(5000);
					driver.findElement(
							By.xpath(OkayBUtton2.replace("%s",
									String.valueOf(QusIndex)))).click();
					Thread.sleep(3000);

				}
				status = "true";

			} else {
				status = "false;"
						+ driver.findElement(By.xpath(fieldValidationError))
								.getText().trim();
				break;
			}

			try {
				if (driver.findElement(By.xpath(loginPopUp)).isDisplayed()) {
					Thread.sleep(3000);
					if (autoPromtInfo.equalsIgnoreCase("login")) {
						enteredDetailsinAutoPopupLogin(driver);
					}
					if (autoPromtInfo.equalsIgnoreCase("Continue")) {
						enteredDetailsinAutoPopupQuestionAnswerPopUp(driver);
					}

				}
			} catch (Exception e) {

			}

			QusIndex++;

			try {

				if (driver
						.findElements(
								By.xpath(Qustion.replace("%s",
										String.valueOf(QusIndex)))).size() == 0) {
					if (driver.findElement(
							By.xpath("//get-quote/div[@class='loader-rect']"))
							.isEnabled()) {
						WebDriverWait wait = new WebDriverWait(driver, 10);
						wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(driver.findElement(By
								.xpath("//div[@class='loader-sect']/img")))));
					}
				}

			} catch (Exception e) {
				addExceptionToReport("Entered exception", "", "");
			}
			// Util.expliciteWait(driver, 30);
		}

		return status;
	}

	/**
	 * Method Name: enteredDetailsinAutoPopupQuestionAnswerPopUp 
	 * Purpose: enter details in auto popup
	 * 
	 * @param driver
	 * @throws InterruptedException
	 * @throws Exception
	 * @return
	 */

	public void enteredDetailsinAutoPopupQuestionAnswerPopUp(WebDriver driver)
			throws InterruptedException, Exception {

		WebElement name = getElementByUsing("FinExp_prequal_popup_autop_name");
		WebElement email = getElementByUsing("FinExp_prequal_popup_autop_userId");
		WebElement mobileNumber = getElementByUsing("FinExp_prequal_popup_mobileNumber");
		name.sendKeys(dummyData.generateFirstName(10));
		email.sendKeys(dummyData.generateEmail(12));
		mobileNumber.sendKeys(dummyData.generateRandomNumber(10));
		WebElement Continue_button = getElementByUsing("FinExp_prequal_popup_Continue");
		Continue_button.click();

	}

	/**
	 * Method Name: enteredDetailsinAutoPopupLogin 
	 * Purpose: enter details in auto popup login
	 * 
	 * @param driver
	 * @throws InterruptedException
	 * @throws Exception
	 * @return
	 */
	public void enteredDetailsinAutoPopupLogin(WebDriver driver)
			throws InterruptedException, Exception {

		driver = DriverFactory.getDriver();
		driver.findElement(By.xpath(autoPromtBoxLogin)).click();
		driver.findElement(By.xpath(Email)).sendKeys(
				"prasann.patwardhan@tavant.com");
		driver.findElement(By.xpath(Password)).sendKeys("Abc1234#");
		driver.findElement(By.xpath(autoPopuploginButton)).click();

		Thread.sleep(5000);

	}

	/**
	 * Method Name: captureRecommendedProductsDetails 
	 * Purpose: Capture the details in the product and pricing details page
	 * 
	 * @throws InterruptedException
	 * @throws Exception
	 * @return
	 */
	public void captureRecommendedProductsDetails()
			throws InterruptedException, Exception {

		WebDriver driver = DriverFactory.getDriver();

		String title = null;
		int rateCardIndex = driver.findElements(By.xpath(rateQuoteIndex))
				.size();
		int rateCardList = driver.findElements(By.xpath(rateQuoteList)).size();

		HashMap<String, HashMap<String, String>> productRateQuoteMap = new HashMap<String, HashMap<String, String>>();
		HashMap<String, String> rateCodeHashMap = new HashMap<String, String>();
		// remove hardcoding of upper limit
		// i --> rateCardIndex
		// Dont hard code xpaths
		for (int i = 1; i <= rateCardIndex; i++) {

			title = driver
					.findElement(
							By.xpath(rateQuoteXpathTitle.replace("%s",
									String.valueOf(i)))).getText().trim();
			// rateCodeHashMap.put(title + i, title);

			// remove hardcoding of upper limit
			for (int j = 1; j < rateCardList; j++) {
				String eleName = null;
				String eleValue = null;
				eleName = driver
						.findElement(
								By.xpath(rateQuoteXpathEleName.replace("%s",
										String.valueOf(i)).replace("%v",
										String.valueOf(j)))).getText().trim();

				eleValue = driver
						.findElement(
								By.xpath(rateQuoteXpathEleValue.replace("%s",
										String.valueOf(i)).replace("%v",
										String.valueOf(j)))).getText().trim();

				rateCodeHashMap.put(eleName, eleValue);

			}
			productRateQuoteMap.put(title + i, rateCodeHashMap);

		}

		for (String key1 : productRateQuoteMap.keySet()) {
			Map innerMap = productRateQuoteMap.get(key1);
			for (Object key2 : innerMap.keySet()) {
				String key3 = key2.toString();

			}
		}

	}

	/**
	 * Method Name: validateTableSort 
	 * Purpose: Validate the table after sorting
	 * 
	 * @param column name
	 * @throws Exception
	 * @return
	 */
	public void validateTableSort(String column_name) throws Exception {

		List<String> actual_role = verifyTableColumnValues(column_name, "Test");
		List<String> Soted = new ArrayList<String>();
		Soted = actual_role;
		Collections.sort(Soted);
		boolean result = isTwoArrayListsWithSameValues(Soted, actual_role);
		if (!(result)) {
			addExceptionToReport(" Coulmn - " + column_name
					+ " is not sorted  ", "  Coulmn - " + column_name
					+ " is not sorted   ", "   Coulmn - " + column_name
					+ " should  be  sorted  ");

		}

	}

	/**
	 * Method Name: isTwoArrayListsWithSameValues 
	 * Purpose: Compare two array list
	 * 
	 * @param Lists of string
	 * @return
	 */
	public boolean isTwoArrayListsWithSameValues(List<String> list1,
			List<String> list2) {
		if ((list1 == null && list2 == null)) {

			return false;
		} else if ((list1 == null && list2 != null)
				|| (list1 != null && list2 == null)) {
			return false;
		} else if (list1.size() != list2.size()) {

			return false;
		}

		for (int counter = 0; counter < list2.size(); counter++) {

			if (!(list1.get(counter).equals(list2.get(counter)))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Method Name: verifyTableColumnValues 
	 * Purpose: Validate the table column values
	 * 
	 * @param column name
	 * @param expected column value
	 * @throws Exception
	 * @return
	 */

	public List<String> verifyTableColumnValues(String expectedColumnName,
			String expectedColumnValue) throws Exception {
		String tableHeaderObject = "//div[@class='ui-datatable-tablewrapper']/table/thead/tr/th";
		String columnObject = "//div[@class='ui-datatable-tablewrapper']/table/thead/tr/th[%s]/span[@class='ui-column-title']";
		String tableBodyObject = "//div[@class='ui-datatable-tablewrapper']/table/tbody/tr";
		List<String> columnValuesList = new ArrayList<String>();
		HashMap<String, Integer> columnMap = new HashMap<String, Integer>();

		WebDriver driver = DriverFactory.getDriver();
		int columnCount = driver.findElements(By.xpath(tableHeaderObject))
				.size();

		for (int colIndex = 1; colIndex <= columnCount; colIndex++) {
			String displayedColName = driver.findElement(
					By.xpath(columnObject.replace("%s",
							String.valueOf(colIndex)))).getText();
			if (displayedColName.length() > 0) {
				columnMap.put(displayedColName, colIndex);
			}
		}

		columnValuesList = fetchAllColumnValues(tableBodyObject,
				columnMap.get(expectedColumnName));
		// compareColumnValues(columnValuesList,expectedColumnName);
		return columnValuesList;
	}

	/**
	 * Method Name: fetchAllColumnValues 
	 * Purpose: Fetch all the column values
	 * 
	 * @param table object
	 * @param column index
	 * @throws Exception
	 * @return
	 */
	public List<String> fetchAllColumnValues(String tableBodyObject,
			int columnIndex) throws Exception {
		String tableContentObject = "//div[@class='ui-datatable-tablewrapper']/table/tbody/tr[%s]/td[%c]/span[@class='ui-cell-data']";
		List<String> columnValuesList = new ArrayList<String>();
		WebDriver driver = getWebDriver();

		int rowCount = driver.findElements(By.xpath(tableBodyObject)).size();

		for (int rowIndex = 1; rowIndex <= rowCount; rowIndex++) {
			String revisedTableBodyObject = tableContentObject.replace("%s",
					String.valueOf(rowIndex));
			String displayedColumnValue = driver.findElement(
					By.xpath(revisedTableBodyObject.replace("%c",
							String.valueOf(columnIndex)))).getText();
			columnValuesList.add(displayedColumnValue);
		}
		// }
		return columnValuesList;
	}
	/**
	 * Method Name: compareColumnValues 
	 * Purpose: compare the column values
	 * 
	 * @param displayedList
	 * @param expectedColumnValue
	 * @throws Exception
	 * @return
	 */

	public void compareColumnValues(List<String> displayedList,
			String expectedColumnValue) throws Exception {
		for (int colIndex = 0; colIndex < displayedList.size(); colIndex++) {
			if (displayedList.get(colIndex).trim()
					.equalsIgnoreCase(expectedColumnValue.trim()) == false) {
				addExceptionToReport(
						"Expected Column Value is not matching with the Expected Value in row number "
								+ colIndex + 1, displayedList.get(colIndex),
						expectedColumnValue.trim());
			}
		}
	}

	/**
	 * Method Name: producatPricingComapre
	 * Purpose: Compare product and pricing values
	 * 
	 * @param result
	 * @throws InterruptedException
	 * @throws Exception
	 * @return
	 */
	public void producatPricingComapre(String result)
			throws InterruptedException, Exception {
		WebDriver driver = DriverFactory.getDriver();
		String status = "";

		boolean flag = true;

		for (int addCard = 1; addCard <= 3; addCard++) {

			driver.findElement(
					By.xpath(addToComcheckBox.replace("%s",
							String.valueOf(addCard)))).click();

		}
		driver.findElement(By.xpath(addToCompareButton)).click();

		driver.findElement(By.xpath(Compare_QuotesButton)).click();

	}

	/**
	 * Method Name: productPricingComapreValidation 
	 * Purpose: validate product and pricing values
	 * 
	 * @param result
	 * @throws InterruptedException
	 * @throws Exception
	 * @return
	 */
	public void productPricingComapreValidation(String result)
			throws InterruptedException, Exception {
		WebDriver driver = DriverFactory.getDriver();
		String status = "";

		boolean flag = true;

		for (int addCard = 1; addCard <= 4; addCard++) {

			if (addCard == 4) {
				driver.findElement(
						By.xpath(addToComcheckBox.replace("%s",
								String.valueOf(addCard)))).click();
				flag = driver.findElement(
						By.xpath(addToComcheckBox.replace("%s",
								String.valueOf(addCard)))).isSelected();

				if (flag) {
					addExceptionToReport(
							"test whether user is not able to add product when there are 3 products chosen for comparison",
							"User is able to add product more than 3 ",
							"User is not able to add product when there are 3 products chosen for comparison");
				}

			}

			driver.findElement(
					By.xpath(addToComcheckBox.replace("%s",
							String.valueOf(addCard)))).click();

		}
		driver.findElement(By.xpath(addToCompareButton)).click();
		driver.findElement(By.xpath(Compare_QuotesButton)).click();

	}

	@Override
	public void checkPage() {

		// TODO Auto-generated method stub

	}

}

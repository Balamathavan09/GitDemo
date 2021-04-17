package com.finx.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import jxl.read.biff.BiffException;
import jxl.write.DateTime;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tavant.base.DriverFactory;
import com.tavant.base.WebPage;
import com.tavant.kwutils.CustomStep;
import com.tavant.kwutils.KWVariables;
import com.tavant.utils.TwfException;
import com.finx.util.Util;

public class CommonUtils extends CustomStep {

	Util Util = new Util();

	String ckEditorFrameObject = "//div[@class='$'][%]//div[@class='cke_contents cke_reset']/iframe ";

	
	/**
	 * Method Name: datepicker 
	 * Purpose: datepicker Selects and clicks the date on calendar
	 * 
	 * @param string
	 * @throws Exception
	 * @return
	 */
	
	public void datepicker(String data) throws Exception {
		String varDate, expectedState;
		String var = KWVariables.getVariables().get(data);
		HashMap<String, String> classMap = new HashMap<String, String>();
		String[] varArray = var.split(":");
		varDate = varArray[0];
		classMap.put("btn btn-sm btn-default", "active");
		classMap.put("btn btn-sm disabled btn-default", "disabled");
		expectedState = varArray[1];
		WebElement dateWidget = getElementByUsing("FinExp_CallBack_DatePicker_body");
		List<WebElement> columns = dateWidget
				.findElements(By.tagName("button"));
		boolean found = false;
		for (WebElement cell : columns) {
			if (cell.getText().equals(varDate)) {
				if (classMap.get(cell.getAttribute("class")).equals(
						expectedState)) {
					if (expectedState.trim().equalsIgnoreCase("active")) {
						cell.click();
						Thread.sleep(5000);
					}
					found = true;
					break;
				}
			}
		}
		if (found == false) {
			addExceptionToReport("Expected Date Value " + varDate
					+ " is not enabled.", "Date should be enabled",
					"Date should be enabled");
		}
	}

	/**
	 * Method Name: fileUpload 
	 * Purpose: Upload the file
	 * 
	 * @param file path
	 * @throws AWTException
	 * @throws TwfException
	 * @throws InterruptedException
	 * @throws BiffException
	 * @throws IOException
	 * @return
	 */
	
	public void fileUpload(String filePath) throws AWTException, TwfException,
			InterruptedException, BiffException, IOException {
		getElementByUsing("AdminLO_UploadButton").click();
		Thread.sleep(4000);
		StringSelection selection = new StringSelection(filePath);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, selection);
		Robot robot = new Robot();

		robot.delay(3000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.delay(3000);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		robot.keyPress(KeyEvent.VK_UP);
		robot.keyRelease(KeyEvent.VK_UP);
		robot.delay(3000);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.delay(3000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(3000);

	}
	/**
	 * Method Name: csrRequest 
	 * Purpose: send CSR request
	 * 
	 * @param args
	 * @throws TwfException
	 * @throws BiffException
	 * @throws IOException
	 * @return
	 */
	

	public void csrRequest(String args) throws BiffException, IOException,
			TwfException {

		getElementByUsing("FinExp_CSR_ViewRequest_notesTextbox").click();
		String strNote = "Note" + System.currentTimeMillis();
		getElementByUsing("FinExp_CSR_ViewRequest_notesTextbox").sendKeys(
				strNote);

		if (args.contains(strNote)) {
			System.out.println("Typed in comment section but not saving");
		} else {
			System.out.println("Typed in comment section");
			getElementByUsing("FinExp_CSR_ViewRequest_notesSave_button")
					.click();
			String strNoteValue = getElementByUsing(
					"FinExp_CSR_ViewProfile_Notes_table_firstRow").getText();
			if (strNoteValue.contains(strNote)) {
				System.out.println("Notes added in the first row");
			}

			else {
				addExceptionToReport("Test Failed", "Not sorted as expected",
						"Should be sorted in latest first order");
			}
		}
	}
	
	/**
	 * Method Name: waitForElement 
	 * Purpose: waitForElement Waits for the element until it is visible on page
	 * 
	 * @param element
	 * @throws TwfException
	 * @throws BiffException
	 * @throws IOException
	 * @return
	 */

	
	public void waitForElement(String element) throws BiffException,
			IOException, TwfException {
		WebElement actual_element = getElementByUsing(element);
		(new WebDriverWait(getWebDriver(), 60)) // timeout after 60
				.until(ExpectedConditions.visibilityOf(actual_element));
	}

	/******************************************************************************
	 * 
	 * credit Score Slider Bar
	 * 
	 * @author Kapil.Yadav
	 * 
	 * @version 0.1
	 * 
	 * @since 16-May-2017
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
	public static void navigatecreditScoreSliderBar(String creditScoredata)
			throws InterruptedException, Exception {
		String creditScore1 = KWVariables.getVariables().get(creditScoredata);
		WebDriver driver = DriverFactory.getDriver();
		WebElement scrol = driver.findElement(By
				.xpath("//div[contains(@role,'slider')]"));
		int creditScore = Integer.parseInt(creditScore1);
		int currentCreditScore = Integer.parseInt(scrol
				.getAttribute("aria-valuenow"));

		if (creditScore <= 579 && currentCreditScore <= 579)
			return;
		else if (creditScore >= currentCreditScore) {
			while (true) {
				scrol.sendKeys(Keys.RIGHT);
				currentCreditScore = Integer.parseInt(scrol
						.getAttribute("aria-valuenow"));

				if (currentCreditScore == creditScore
						|| currentCreditScore >= 979)
					break;
			}

		}

		else {

			scrol.sendKeys(Keys.LEFT);

			while (true) {
				scrol.sendKeys(Keys.LEFT);
				currentCreditScore = Integer.parseInt(scrol
						.getAttribute("aria-valuenow"));

				if (currentCreditScore == creditScore)
					break;
			}
		}

	}

	
	/**
	 * Method Name: selectFromDropdown 
	 * Purpose: Selects value from the dropdown
	 * 
	 * @param args
	 * @throws Exception
	 * @return
	 */


	public void selectFromDropdown(String args) throws Exception {
		WebDriver driver = DriverFactory.getDriver();
		getElementByUsing("FinExp_Admin_SearchLO_searchType_dropdown_button")
				.click();
		WebElement dropdownMenu = getElementByUsing("FinExp_Admin_SearchLO_searchType_dropdown_list");
		List<WebElement> listItems = dropdownMenu.findElements(By.tagName("a"));
		for (WebElement item : listItems) {
			if (item.getText().contains(args)) {
				item.click();
				break;
			}
		}
	}

	/**
	 * Method Name: verifyDisabledButton 
	 * Purpose: Check whether button is disabled or not
	 * 
	 * @param args
	 * @throws TwfException
	 * @throws BiffException
	 * @throws IOException
	 * @return
	 */

	

	public void verifyDisabledButton(String args) throws BiffException,
			IOException, TwfException {

		if ((getElementByUsing(args).isEnabled()) == true) {
			addExceptionToReport("Test Failed",
					"Button should be disabled for decimal inputs", args);
		}
	}
	
	/**
	 * Method Name: verifyEnabledButton 
	 * Purpose: Check whether button is enabled or not
	 * 
	 * @param args
	 * @throws TwfException
	 * @throws BiffException
	 * @throws IOException
	 * @return
	 */

	public void verifyEnabledButton(String args) throws BiffException,
			IOException, TwfException {

		if ((getElementByUsing(args).isEnabled()) == false) {
			addExceptionToReport("Test Failed", "Button should be enabled ",
					args);
		}
	}

	@Override
	public void checkPage() {
		// TODO Auto-generated method stub

	}

	/**
	 * @author kapil.yadav/Srinivas G.S method will insert data into i-frame
	 * 
	 * @param str
	 *            : frame name ,where will insert data
	 * @throws TwfException
	 * @throws InterruptedException
	 * @throws BiffException
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	public void typeCkeditor1(String str) throws TwfException,
			InterruptedException, BiffException, IOException,
			InvalidFormatException {

		String frameValue = "";
		String indexValue = "";

		String[] expectedSectionArray = str.split(";");

		for (String section : expectedSectionArray) {

			switch (section.toUpperCase()) {
			case "WORKINFO":
				frameValue = "work-info";
				indexValue = "1";
				break;
			case "ABOUTME":
				frameValue = "work-info";
				indexValue = "2";
				break;
			case "TESTIMONIALS":
				frameValue = "testimonial-inner";
				indexValue = "1";
				break;
			default:
				frameValue = "work-info half-width-sect";
				indexValue = "2";
				break;
			}

			String revisedFrameObject = ckEditorFrameObject.replace("$",
					frameValue).replace("%", indexValue);

			WebDriver driver = DriverFactory.getDriver();

			Thread.sleep(1000);
			driver.switchTo().frame(
					driver.findElement(By.xpath(revisedFrameObject)));

			WebElement web1 = driver.findElement(By
					.xpath("//body[@class[contains(.,'cke_editable')]]"));
			web1.clear();
			web1.sendKeys(GenerateData.generateRandomString(50));
			driver.switchTo().defaultContent();

		}

	}

	/**
	 * @author kapil.yadav/Srinivas G.S method will insert data into i-frame
	 * 
	 * @param str
	 *            : frame name ,where will insert data
	 * @throws TwfException
	 * @throws InterruptedException
	 * @throws BiffException
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	public void addWorkInfoDetails() throws TwfException, InterruptedException,
			BiffException, IOException, InvalidFormatException {

		String frameValue = "";
		String indexValue = "";
		String str = step.getDataValue("TextValueCombination");

		String[] expectedSectionArray = str.split(";");

		for (String section : expectedSectionArray) {

			String[] expectedSectionDetails = section.split(":");

			switch (expectedSectionDetails[0].toUpperCase()) {
			case "WORKINFO":
				frameValue = "work-info";
				indexValue = "1";
				break;
			case "ABOUTME":
				frameValue = "work-info";
				indexValue = "2";
				break;
			case "TESTIMONIALS":
				frameValue = "testimonial-inner";
				indexValue = "1";
				break;
			default:
				frameValue = "work-info half-width-sect";
				indexValue = "2";
				break;
			}
			String revisedFrameObject = ckEditorFrameObject.replace("$",
					frameValue).replace("%", indexValue);

			WebDriver driver = DriverFactory.getDriver();

			Thread.sleep(1000);
			driver.switchTo().frame(
					driver.findElement(By.xpath(revisedFrameObject)));

			WebElement web1 = driver.findElement(By
					.xpath("//body[@class[contains(.,'cke_editable')]]"));
			web1.clear();
			web1.sendKeys(expectedSectionDetails[1]);
			driver.switchTo().defaultContent();

		}

	}
	
	/**
	 * Method Name: verifyWorkInfoDetailsInConsumer 
	 * Purpose: Verify WorkInfo Details of LO in consumer portal after approval
	 * 
	 * @param args
	 * @throws TwfException
	 * @throws BiffException
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws InvalidFormatException
	 * @return
	 */

	public void verifyWorkInfoDetailsInConsumer() throws TwfException,
			InterruptedException, BiffException, IOException,
			InvalidFormatException {
		String str = step.getDataValue("VerifyTextValue");
		WebDriver driver = DriverFactory.getDriver();
		String frameValue = "";
		String indexValue = "";

		String[] expectedSectionArray = str.split(";");

		for (String section : expectedSectionArray) {

			String[] expectedSectionDetails = section.split(":");

			switch (expectedSectionDetails[0].toUpperCase()) {
			case "AREAOFEXPERTISE":
				String areaOfExpertiseText = driver
						.findElement(
								By.xpath(".//div[@class='row lo-details-container']//p"))
						.getText();
				if (areaOfExpertiseText
						.equalsIgnoreCase(expectedSectionDetails[1]) == false) {
					addExceptionToReport(
							"Work Info Field verification for the field "
									+ expectedSectionDetails[1] + " failed",
							areaOfExpertiseText, expectedSectionDetails[1]);
				}
				break;
			case "ABOUTME":
				String aboutMeText = driver.findElement(
						By.xpath(".//div[@class='about-lo-container']//p"))
						.getText();
				if (aboutMeText.equalsIgnoreCase(expectedSectionDetails[1]) == false) {
					addExceptionToReport(
							"Work Info Field verification for the field "
									+ expectedSectionDetails[1] + " failed",
							aboutMeText, expectedSectionDetails[1]);
				}
				break;
			case "TESTIMONIALS":
				String testimonialsText = driver.findElement(
						By.xpath(".//span[@class='cust-feedback']/p"))
						.getText();
				// String aboutMeText =
				// driver.findElement(By.xpath(".//div[@class='row lo-details-container']//p")).getText();
				if (testimonialsText
						.equalsIgnoreCase(expectedSectionDetails[1]) == false) {
					addExceptionToReport(
							"Work Info Field verification for the field "
									+ expectedSectionDetails[1] + " failed",
							testimonialsText, expectedSectionDetails[1]);
				}
				break;

			}

		}
	}
	
	/**
	 * Method Name: verifyWorkInfoDetailsAsConsumerOfLO 
	 * Purpose: Verify WorkInfo Details of LO in consumer portal without approval
	 * 
	 * @param args
	 * @throws TwfException
	 * @throws BiffException
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws InvalidFormatException
	 * @return
	 */


	public void verifyWorkInfoDetailsAsConsumerOfLO() throws TwfException,
			InterruptedException, BiffException, IOException,
			InvalidFormatException {
		String str = step.getDataValue("VerifyTextValue");
		WebDriver driver = DriverFactory.getDriver();
		String frameValue = "";
		String indexValue = "";

		String[] expectedSectionArray = str.split(";");

		for (String section : expectedSectionArray) {

			String[] expectedSectionDetails = section.split(":");

			switch (expectedSectionDetails[0].toUpperCase()) {
			case "AREAOFEXPERTISE":
				String areaOfExpertiseText = driver
						.findElement(
								By.xpath(".//div[@class='row lo-details-container']//p"))
						.getText();
				if (areaOfExpertiseText
						.equalsIgnoreCase(expectedSectionDetails[1]) == true) {
					addExceptionToReport(
							"Work Info Field verification for the field "
									+ expectedSectionDetails[1] + " failed",
							areaOfExpertiseText, expectedSectionDetails[1]);
				}
				break;
			case "ABOUTME":
				String aboutMeText = driver.findElement(
						By.xpath(".//div[@class='about-lo-container']//p"))
						.getText();
				if (aboutMeText.equalsIgnoreCase(expectedSectionDetails[1]) == true) {
					addExceptionToReport(
							"Work Info Field verification for the field "
									+ expectedSectionDetails[1] + " failed",
							aboutMeText, expectedSectionDetails[1]);
				}
				break;
			case "TESTIMONIALS":
				String testimonialsText = driver.findElement(
						By.xpath(".//span[@class='cust-feedback']/p"))
						.getText();
				if (testimonialsText
						.equalsIgnoreCase(expectedSectionDetails[1]) == true) {
					addExceptionToReport(
							"Work Info Field verification for the field "
									+ expectedSectionDetails[1] + " failed",
							testimonialsText, expectedSectionDetails[1]);
				}
				break;

			}

		}
	}

	/**
	 * Method Name: addMultipleTestimonials 
	 * Purpose: Add multiple Testimonials
	 * 
	 * @param args
	 * @throws TwfException
	 * @throws BiffException
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws InvalidFormatException
	 * @return
	 */
	
	public void addMultipleTestimonials() throws TwfException,
			InterruptedException, BiffException, IOException,
			InvalidFormatException {
		String str1 = step.getDataValue("VerifyTextValueinConsumer");
		String frameValue = "testimonial-inner";
		String indexValue = "";
		WebDriver driver = DriverFactory.getDriver();
		String[] expectedSectionArray = str1.split(";");
		int numberOfTestimonialsToBeAdded = Integer
				.parseInt(expectedSectionArray[3]);
		for (int i = 1; i <= numberOfTestimonialsToBeAdded; i++) {
			indexValue = String.valueOf(i);

			String addbutton = "(.//div[@class='add-btns']/div/button)[%]";

			String revisedFrameObject = ckEditorFrameObject.replace("$",
					frameValue).replace("%", indexValue);

			Thread.sleep(1000);
			driver.switchTo().frame(
					driver.findElement(By.xpath(revisedFrameObject)));
			String text = driver.findElement(
					By.xpath("//body[@class[contains(.,'cke_editable')]]/p"))
					.getText();
			Actions entertestimonials = new Actions(driver);
			WebElement web1 = driver.findElement(By
					.xpath("//body[@class[contains(.,'cke_editable')]]/p"));
			driver.findElement(
					By.xpath("//body[@class[contains(.,'cke_editable')]]/p"))
					.clear();
			Thread.sleep(5000);
			entertestimonials.sendKeys(web1, expectedSectionArray[0] + i)
					.build().perform();

			driver.switchTo().defaultContent();
			String byTextArea = "(.//div[@class='pull-right']//input[@ id='name'])[%]";
			String locationTextArea = "(.//div[@class='pull-right']//input[@ id='location'])[%]";
			driver.findElement(By.xpath(byTextArea.replace("%", indexValue)))
					.clear();
			driver.findElement(By.xpath(byTextArea.replace("%", indexValue)))
					.sendKeys(expectedSectionArray[1]);

			driver.findElement(
					By.xpath(locationTextArea.replace("%", indexValue)))
					.clear();
			driver.findElement(
					By.xpath(locationTextArea.replace("%", indexValue)))
					.sendKeys(expectedSectionArray[2]);
			if (i != 5) {
				driver.findElement(By.xpath(addbutton.replace("%", indexValue)))
						.click();
			}

		}

	}

	/**
	 * Method Name: deleteTetimoinals 
	 * Purpose: Delete multiple Testimonials
	 * 
	 * @throws TwfException
	 * @throws InterruptedException
	 * @return
	 */
	
	
	public void deleteTetimoinals() throws TwfException, InterruptedException {
		String text = "";
		String frameValue = "testimonial-inner";
		String indexValue = "";
		WebDriver driver = DriverFactory.getDriver();
		String str1 = step.getDataValue("DeletmultipleTestimonials");
		String[] expectedSectionArray = str1.split(";");
		for (int j = 0; j < expectedSectionArray.length; j++) {

			String str2 = expectedSectionArray[j];

			boolean found = false;
			for (int i = 1; i <= 5; i++) {
				indexValue = String.valueOf(i);
				driver = driver.switchTo().parentFrame();
				String revisedFrameObject = ckEditorFrameObject.replace("$",
						frameValue).replace("%", indexValue);
				Thread.sleep(1000);
				driver.switchTo().frame(
						driver.findElement(By.xpath(revisedFrameObject)));

				text = driver
						.findElement(
								By.xpath("//body[@class[contains(.,'cke_editable')]]/p"))
						.getText();
				if (text.equalsIgnoreCase(str2)) {
					String deleteButton = "//div[@class='testimonial-inner'][%s]//button[@class='delete-icon']";
					driver = driver.switchTo().parentFrame();
					driver.findElement(
							By.xpath(deleteButton.replace("%s", indexValue)))
							.click();

					driver.findElement(
							By.xpath(".//*[@id='btn_confirm_modal_Yes']"))
							.click();
					found = true;
					break;
				} /*
				 * else { addExceptionToReport( "Text didnt match " + text +
				 * " failed", str2, ""); }
				 */

			}
		}

	}

	/**
	 * Method Name: switchToFrameById 
	 * Purpose: Switch the control to frame using ID
	 * @param args
	 * @throws TwfException
	 * @throws BiffException
	 * @throws IOException
	 * @return
	 */
	
	
	public void switchToFrameById() throws TwfException, BiffException,
			IOException {

		WebDriver driver = DriverFactory.getDriver();
		String frameId = step.getValue();

		Util.waitForElements(driver.findElement(By.id(frameId)));

		driver.switchTo().frame(driver.findElement(By.id(frameId)));
	}

	
	/**
	 * Method Name: verifyTetimoinalsDataAsAdmin 
	 * Purpose: Verify Testimonials data as Admin
	 * @param args
	 * @throws TwfException
	 * @throws InterruptedException
	 * @return
	 */
	
	public void verifyTetimoinalsDataAsAdmin() throws TwfException,
			InterruptedException {
		String text = "";
		String frameValue = "testimonial-inner";
		String indexValue = "";
		WebDriver driver = DriverFactory.getDriver();
		String str1 = step.getDataValue("AdminVerifyTestimonials");
		String[] expectedSectionArray = str1.split(";");

		for (int i = 0; i < expectedSectionArray.length; i++) {
			indexValue = String.valueOf(i + 1);
			String str2 = expectedSectionArray[i];
			driver = driver.switchTo().parentFrame();
			String revisedFrameObject = ckEditorFrameObject.replace("$",
					frameValue).replace("%", indexValue);
			Thread.sleep(1000);
			driver.switchTo().frame(
					driver.findElement(By.xpath(revisedFrameObject)));

			text = driver.findElement(
					By.xpath("//body[@class[contains(.,'cke_editable')]]/p"))
					.getText();
			str2.trim();
			text.trim();
			if (!str2.equalsIgnoreCase(text)) {
				addExceptionToReport("Text didnt match " + text + " failed",
						str2, "");
			}
		}

	}

	/**
	 * Method Name: elementIsnotDisplayed 
	 * Purpose: Check whether element is displayed or not
	 * @param string
	 * @throws TwfException
	 * @throws IOException
	 * @throws BiffException
	 * @return
	 */
	
	
	public void elementIsnotDisplayed(String str) throws BiffException,
			IOException, TwfException {
		WebDriver driver = DriverFactory.getDriver();
		WebElement ele1 = getElementByUsing(str);
		boolean status = ele1.isDisplayed();
		if (status == true) {
			addExceptionToReport("element is dispalyed " + status
					+ " element should not be dispalyed", "", "");
		}
	}
	
	/**
	 * Method Name: verifyListValuesCallBack 
	 * Purpose: verify the drop down values in callback screen
	 * @param args
	 * @throws TwfException
	 */
	
	public void verifyListValuesCallBack() throws TwfException {
		WebDriver driver = DriverFactory.getDriver();
		String str = step.getDataValue("DropdownValuesOfCallBack");
		String[] datasplit = str.split(";");
		String[] expectedSectionArray = datasplit[0].split(":");
		for (int i = 0; i < expectedSectionArray.length; i++) {
			String ddValuesInUI = driver.findElement(
					By.xpath(datasplit[1] + "[" + (i + 1) + "]/a")).getText();
			String ddValuesFromExcel = expectedSectionArray[i];
			if (!ddValuesInUI.equalsIgnoreCase(ddValuesFromExcel)) {
				addExceptionToReport("Text didnot match " + ddValuesInUI
						+ " mismatch", "", "");
			}
		}

	}
	 
	/**
	 * Method Name: verifyTextLength 
	 * Purpose: Verify text length
	 * @throws TwfException
	 * @throws IOException
	 * @throws BiffException
	 * @return
	 */

	
	public void verifyTextLength() throws TwfException, BiffException,
			IOException {
		WebDriver driver = DriverFactory.getDriver();
		String testData = step.getDataValue("TextValueVerification");
		String[] datasplit = testData.split("%");
		String xpathOfElement = datasplit[1];
		driver.findElement(By.xpath(xpathOfElement)).sendKeys(datasplit[0]);
		String textBoxText = driver.findElement(By.xpath(xpathOfElement)).getAttribute("value");
		int lengthOfTheText = textBoxText.length();
		int maxLengthOfChracters = Integer.parseInt(datasplit[2]);
		if (lengthOfTheText != maxLengthOfChracters) {
			addExceptionToReport("Text didnt match " + lengthOfTheText
					+ " failed", datasplit[2], "");
		}

	}
	
	/**
	 * Method Name: verifyCreatedate 
	 * Purpose: Verify date of request creation
	 * @param String 
	 * @throws TwfException
	 * @throws IOException
	 * @throws BiffException
	 * @return
	 */


	public void verifyCreatedate(String str) throws TwfException,
			BiffException, IOException, ParseException {
		WebDriver driver = DriverFactory.getDriver();
		WebElement dateWidget = getElementByUsing(str);
		String dateInUI = dateWidget.getText();
		Date currentDate = new Date();
		SimpleDateFormat sm = new SimpleDateFormat("MM/dd/yyyy");
		String systemDate = sm.format(currentDate);
		if (!systemDate.contains(dateInUI)) {
			addExceptionToReport("Date didnot match " + systemDate + " failed",
					dateInUI, "");
		}

	}
	
	public void verifyWorkInfoDetailsInConsumerForSaveDraft()
			throws TwfException, InterruptedException, BiffException, IOException, InvalidFormatException {
		System.out.println("entered verifyWorkInfoDetailsInConsumer ");
		String str= step.getDataValue("VerifyTextValueinConsumer");
		WebDriver driver = DriverFactory.getDriver();
		String frameValue = "";
		String indexValue = "";
		
		String[] expectedSectionArray = str.split(";");
		
		for(String section:expectedSectionArray){
			
			String[] expectedSectionDetails = section.split(":");

				switch (expectedSectionDetails[0].toUpperCase()) {
				case "AREAOFEXPERTISE":
					String areaOfExpertiseText = driver.findElement(By.xpath(".//div[@class='row lo-details-container']//p")).getText();
					
					if(areaOfExpertiseText.equalsIgnoreCase(expectedSectionDetails[1])==true)
					{
						addExceptionToReport("Work Info Field verification for the field "+expectedSectionDetails[1]+" failed", areaOfExpertiseText, expectedSectionDetails[1]);
					}
					break;				
				case "ABOUTME":
					String aboutMeText = driver.findElement(By.xpath(".//div[@class='about-lo-container']//p")).getText();

					if(aboutMeText.equalsIgnoreCase(expectedSectionDetails[1])==true)
					{
						addExceptionToReport("Work Info Field verification for the field "+expectedSectionDetails[1]+" failed", aboutMeText, expectedSectionDetails[1]);
					}
					break;
				case "TESTIMONIALS":
					String testimonialsText = driver.findElement(By.xpath(".//span[@class='cust-feedback']/p")).getText();
					
					if(testimonialsText.equalsIgnoreCase(expectedSectionDetails[1])==true)
					{
						addExceptionToReport("Work Info Field verification for the field "+expectedSectionDetails[1]+" failed", testimonialsText, expectedSectionDetails[1]);
					}
					break;
			
			}
			
	}}
	/******************************************************************************
     * waiting for particular element
     * 
      *  
      * @author nikhil.patni 
      * @version 0.1
     * @return None
     * @throws InterruptedException
     * @throws TwfException
     ******************************************************************************/
     public void waitForElements() throws TwfException, BiffException, IOException {
             WebDriver driver = DriverFactory.getDriver(); 
             // System.out.println("Inside waitForElements");
             String t = step.getValue();
             waitForElement(getElementByUsing(t), 40000);
             System.out.println("waiting done for " + t);
     }   

     /******************************************************************************
      * waiting for particular element
      * 
       *  
       * @author nikhil.patni 
       * @version 0.1
      * @return None
      * @throws InterruptedException
      * @throws TwfException
      ******************************************************************************/
      public void waitForElements(String t) throws TwfException, BiffException, IOException {
              WebDriver driver = DriverFactory.getDriver(); 
              // System.out.println("Inside waitForElements");
             waitForElement(getElementByUsing(t), 80000);
              System.out.println("waiting done for " + t);
      }  
	
	public void beforePageLoad() throws TwfException {
		WebDriver driver = DriverFactory.getDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.mpPageReloaded='notYet';");
	} 

	public void switchToFrameByIdold() throws TwfException, BiffException,
	IOException {
System.out.println("Entered frame ");
		WebDriver driver = DriverFactory.getDriver();
		String frameId = step.getValue();
		System.out.println("frameId: "+frameId);
		List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
		int sizeofFrame = iframes.size();
		System.out.println("sizeofFrame: "+sizeofFrame);
		    for (WebElement iframe : iframes) {
		    	System.out.println("iframe  "+ iframe);
	        if (iframe.getAttribute("id").equals(frameId)) {
	        	System.out.println("Entred frame if");
	        	Util.waitForElements(driver.findElement(By.id(frameId)));
	        	driver.switchTo().frame(driver.findElement(By.id(frameId)));
	                System.out.println("out of if ");}
	    }
		System.out.println("out of switch");
	} 
	
	public void switchToFrameByIdnew() throws TwfException, BiffException,
    IOException,InterruptedException {
           System.out.println("Entered frame ");
           WebDriver driver = DriverFactory.getDriver();
           String frameId = step.getValue();
           int i=0;
           
           //wait until frame is available in html
           while(i<3)
           {
                  try
                  {
                        driver.findElement(By.id(frameId)).isDisplayed();
                        break;
                  }
                  catch(Exception e)
                  {
                        Thread.sleep(2000);
                        i++;
                        if(i==3)
                        {
                               addExceptionToReport(" The expected frame is not visible in asset Integration page", frameId, " ");
                        }
                  }
           }
           
           
           System.out.println("frameId: "+frameId);
           List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
           int sizeofFrame = iframes.size();
           for (WebElement iframe : iframes) {
            if (iframe.getAttribute("id").equals(frameId)) 
            {
                System.out.println("framed: "+iframe.getAttribute("id"));
                		Thread.sleep(2000);
                        driver.switchTo().frame(driver.findElement(By.id(frameId)));
                        System.out.println("Switched "+iframe.getAttribute("id"));
                        break;
            }
        }
    
    } 
	
	public static void defaContent() throws TwfException, InterruptedException{
		  WebDriver driver = DriverFactory.getDriver();
		  TimeUnit.MINUTES.sleep(1);
		  ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
	        tabs = new ArrayList<String>(driver.getWindowHandles());
//	        driver.switchTo().window(tabs.get(1)).close();  
	        driver.switchTo().window(tabs.get(2));  
			System.out.println("Switched!!>>"+driver.getTitle());
	}
	

	public static void defaContent1() throws TwfException, InterruptedException{
		  WebDriver driver = DriverFactory.getDriver();
		  TimeUnit.MINUTES.sleep(1);
		  for (String handle1 : driver.getWindowHandles()) {
		        System.out.println(handle1); 
		        driver.switchTo().window(handle1);     
		}
		  System.out.println("Title>>>"+driver.getTitle());
	}
	
	/**
	 * 
	 */
	public static void clickOnHomeButton() throws Exception{
		WebDriver driver = DriverFactory.getDriver();
		
		
		try{
			driver.findElement(By.id("headerdropdown")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[contains(@title,'Go To Dashboard')]/span[text()='Home']")).click();
		}catch(Exception e){
			System.out.println("Exception while navigating to hoe page>>>>"+e.toString());
		}
		
	}

}

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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import jxl.read.biff.BiffException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tavant.base.DriverFactory;
import com.tavant.base.WebPage;
import com.tavant.kwutils.KWVariables;
import com.tavant.utils.TwfException;

public class TpoPortal extends WebPage {

	/**
	 * Searches for the given user and deletes it.
	 * 
	 * @param args
	 * @throws IOException
	 * @throws InvalidFormatException
	 * @throws BiffException
	 * @throws TwfException
	 */
	public void deleteGivenUser(String args) throws BiffException,
			InvalidFormatException, IOException, TwfException {
		WebDriver driver = DriverFactory.getDriver();
		String params = KWVariables.getVariables().get(args);
		String userId = params.split(",")[0];
		String tablePrexpath = params.split(",")[1];
		int totalAccounts = driver.findElements(By.xpath(tablePrexpath)).size();
		// deleting given userid
		String uiUserId = "";
		int userFoundCount = 0;
		for (int i = 2; i <= totalAccounts; i++) {
			uiUserId = driver.findElement(
					By.xpath(tablePrexpath + "[" + i + "]/span[5]")).getText();
			if (uiUserId.equals(userId)) {
				userFoundCount = 1;
				driver.findElement(
						By.xpath(tablePrexpath + "[" + i + "]/span[1]/input"))
						.click();
				getElementByUsing("TPO_Account_Delete_Button").click();
				break;
			}
		}
		if (userFoundCount == 0) {
			Util.addExceptionToReport("Could not find the given user in ui : ",
					userId, userId);
		}
		// verifying deleted userid
		int newTotalAccounts = driver.findElements(By.xpath(tablePrexpath))
				.size();
		for (int i = 2; i <= newTotalAccounts; i++) {
			uiUserId = driver.findElement(
					By.xpath(tablePrexpath + "[" + i + "]/span[5]")).getText();
			if (uiUserId.equals(userId)) {
				Util.addExceptionToReport("Given user couldnot be deleted",
						"userid present in ui after deleting", userId);
			}
		}
	}
	/**
	 * Method Name: clickExtendRelockChangeButtons 
	 * Purpose: validate product and pricing values
	 * 
	 * @param result
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws BiffException
	 * @return
	 */
	public void clickExtendRelockChangeButtons(String args)
			throws BiffException, InvalidFormatException, IOException,
			TwfException {
		WebDriver driver = DriverFactory.getDriver();

		// driver.findElement(By.xpath(".//*[@id='tabs-4']//div[@class='button_set']//button[@class='button extend_rate']")).click();
		driver.findElement(
				By.xpath(".//*[@id='tabs-4']//div[@class='button_set']//button[@class='"
						+ args + "']")).click();
	}
	
	/**
	 * Method Name: fileUpload1 
	 * Purpose: upload the file
	 * 
	 * @param filepath
	 * @throws InterruptedException
	 * @throws AWTException
	 * @throws TwfException
	 * @return
	 */

	public void fileUpload1(String filePath) throws AWTException, TwfException,
			InterruptedException {

		WebDriver driver = DriverFactory.getDriver();

		driver.findElement(
				By.xpath(".//*[@id='mainPanel']/div/div[2]/div/div/div/div[1]/input"))
				.click();
		Thread.sleep(3000);

		StringSelection selection = new StringSelection(filePath);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, selection);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

	}
	
	/**
	 * Method Name: getCurrentDate 
	 * Purpose: get the current date
	 * 
	 * @param agrs
	 * @throws InterruptedException
	 * @throws BiffException
	 * @throws TwfException
	 * @throws InvalidFormatException
	 * @throws IOException
	 * @return
	 */

	public void getCurrentDate(String args) throws BiffException,
			InvalidFormatException, IOException, TwfException,
			InterruptedException {
		WebDriver driver = DriverFactory.getDriver();
		Thread.sleep(3000);
		DateFormat newDate = new SimpleDateFormat("dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date tommrrow = cal.getTime();
		String today = newDate.format(tommrrow);

		WebElement calDate = driver.findElement(By.id("ui-datepicker-div"));
		List<WebElement> columns = calDate.findElements(By.tagName("td"));

		for (WebElement cell : columns) {

			if (cell.getText().equals(today.split("0")[1])) {
				cell.click();
				break;
			}

		}
	}
	
	/**
	 * Method Name: clearEmpField 
	 * Purpose: clear emp field
	 * 
	 * @param agrs
	 * @throws BiffException
	 * @throws TwfException
	 * @throws IOException
	 * @return
	 */

	public void clearEmpField(String args) throws BiffException, IOException,
			TwfException {
		getElementByUsing("LMS_Add userRole_empName").clear();
	}
	/**
	 * Method Name: checkdate 
	 * Purpose: check the date
	 * 
	 * @param agrs
	 * @throws TwfException
	 * @return
	 */
	public void checkdate(String args) throws TwfException {
		WebDriver driver = DriverFactory.getDriver();
		DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
		Date date2 = new Date();
		int dateFound = 0;
		String today = dateFormat2.format(date2);
		String celldate = null;
		WebElement dateWidget = driver.findElement(By.id("tblLeaveHistory"));
		List<WebElement> columns = dateWidget.findElements(By.tagName("td"));
		for (WebElement cell : columns) {

			celldate = cell.getText();
			if (celldate.equals(today)) {
				dateFound = 1;
				break;
			}
		}
		if (dateFound == 0) {
			addExceptionToReport("Could not find the date ", celldate, today);
		}
	}
	
	/**
	 * Method Name: getMonthNumber 
	 * Purpose: get the month number
	 * 
	 * @throws ParseException
	 * @return
	 */

	private int getMonthNumber() throws ParseException {
		String monthName = "März";
		Date date = new SimpleDateFormat("MMMM", Locale.ENGLISH)
				.parse(monthName);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return 0;
	}
	/**
	 * Method Name: callBackResultVarification 
	 * Purpose: call back request verification
	 * 
	 * @param expectedEmail
	 * @throws Exception
	 * @return
	 */
	
	public void callBackResultVarification(String expectedEmail)
			throws Exception {
		WebDriver driver = DriverFactory.getDriver();

		if (isErrorMessageDisplayedOnCallBackSearch(driver) == false) {

			WebElement email_result = getElementByUsing("FinExp_CSR_Search_searchResults_firstResult_email");
			String email = email_result.getText();

			if (!(email.equalsIgnoreCase(expectedEmail))) {

				addExceptionToReport(
						" result email not Match with expected email",
						expectedEmail,
						" email should be match with expected email in  User/Application");

			}
		}

	}
	
	/**
	 * Method Name: isErrorMessageDisplayedOnCallBackSearch 
	 * Purpose: verify error message is displayed or not
	 * 
	 * @param driver
	 * @throws Exception
	 * @return
	 */

	public boolean isErrorMessageDisplayedOnCallBackSearch(WebDriver driver)
			throws Exception {
		String expectedErrorMessage = "No results found matching the search criteria entered";

		if (driver.findElements(By.xpath("//div[@class='no-records-container']/span")).size() > 0) {
			System.out.println("Expected is "+ expectedErrorMessage );
			System.out.println("Actual is "+ driver.findElement(By.xpath("//div[@class='no-records-container']/span[1]")).getText());
			if(expectedErrorMessage.equalsIgnoreCase(driver.findElement(By.xpath("//div[@class='no-records-container']/span[1]")).getText())== false){
				
			addExceptionToReport("No results are found after search operation.",
					getElementByUsing("FinExp_CSR_Search_searchResults_nothingFoundMsg").getText().trim(),
					" please enter valid search criteria keyword  ");
			}

			return true;
		}
		return false;
	}

	// Method for uploading file/images by using Robot Class

	/**
	 * Method Name: fileUpload 
	 * Purpose: Method for uploading file/images by using Robot Class
	 * 
	 * @param filepath
	 * @throws AWTException
	 * @throws TwfException
	 * @throws InterruptedException
	 * @throws BiffException
	 * @throws IOException

	 * @return
	 */
	
	public void fileUpload(String filePath) throws AWTException, TwfException,
			InterruptedException, BiffException, IOException {

		WebDriver driver = DriverFactory.getDriver();

		// driver.findElement(By.xpath("html/body/fin-exp-app/fin-exp-anonymous/main/lodetails-edit/div[2]/div/div[2]/div/div/div[2]/div/div[1]/lo-photo/div/p-fileupload/div/div[1]/button[1]")).click();

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

	// 
	/**
	 * Method Name: typeCkeditor 
	 * Purpose: Method for typing text in CK Editor
	 * 
	 * @param args
	 * @throws InvalidFormatException
	 * @throws TwfException
	 * @throws InterruptedException
	 * @throws BiffException
	 * @throws IOException

	 * @return
	 */
	public void typeCkeditor(String args) throws TwfException,
			InterruptedException, BiffException, IOException,
			InvalidFormatException

	{
		WebDriver driver = DriverFactory.getDriver();

		String elem, textValue;
		String[] parts;

		parts = args.split(":");
		elem = parts[0];
		textValue = parts[1];

		WebElement section = getElementByUsing(elem);

		WebElement iframe = section.findElement(By.tagName("iframe"));
		driver.switchTo().frame(iframe);

		WebElement body = driver.findElement(By.tagName("body"));
		body.clear();
		body.sendKeys(textValue);
		Thread.sleep(4000);

		driver.switchTo().defaultContent();
	}

	/**
	 * Method Name: navigateToPage 
	 * Purpose: navigate To Page
	 * 
	 * @param args
	 * @throws Exception
	 * @return
	 */
	
	public void navigateToPage(String args) throws Exception {
		WebDriver driver = DriverFactory.getDriver();
		String url = KWVariables.getVariables().get(args);
		driver.get(url);
	}

	@Override
	public void checkPage() {
		// TODO Auto-generated method stub

	}

}

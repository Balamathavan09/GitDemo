package com.finx.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.print.attribute.HashAttributeSet;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.tavant.base.DriverFactory;
import com.tavant.kwutils.CustomStep;
import com.tavant.kwutils.KWVariables;
import com.tavant.utils.TwfException;

import jxl.read.biff.BiffException;

public class Footer extends CustomStep {
	
	Util util = new Util();

	WebDriver driver;

	String headerFieldValueObject = "//div[@class='admin-header-container']/div[@class='row']//div[@class='row'][%s]/div[1]//input";
	String headerButtonObject = "//div[@class[contains(.,'btn-holder')]]/button[contains(.,'%s')]";
	String displayedHeaderValuesListObject = ".//*[@class[contains(.,'navbar-right')]]/li";
	String displayedHeaderValuesObject = ".//*[@class[contains(.,'navbar-right')]]/li[%s]/a";
	String footerFieldValueObject = "//div[@class='admin-header-container']/div[@class='row']/div[@class='admin-header-inner']/div[@class='input-holder']/div[@class='row'][%s]/div[1]//input";
	String footerFieldListValueObject = "//div[@class='admin-header-container']/div[@class='row']/div[@class='admin-header-inner']/div[@class='input-holder']/div[@class='row']";
	String footerCopyRightObject = "//div[@class='admin-header-container']/div[@class='row']/div[@class='admin-header-inner']/div[@class='input-holder']/div[@class='copyright-section']/div[2]//input";
	String footerPreviewListObject = "//div[@class='footer-left']/ul/li";
	String footerPreviewValuesObject = "//div[@class='footer-left']/ul/li[%s]/a";
	String adminHeaderRowObject = "//div[@class='admin-header-container']/div[@class='row']//div[@class='row']";
	String url = "http://10.131.148.191:9005/#";
	String footerFieldIndex = "(.//*[@class[contains(.,'footer-block-mobile')]]//a)[%s]";


	// WebDriver driver = DriverFactory.getDriver();
	/**
	 * Method Name: beforePageLoad
	 * Purpose: To perform before page load
	 * Comments: This is duplicate. Put it in utils class
	 * @throws TwfException
	 */
	public void beforePageLoad() throws TwfException {
		WebDriver driver = DriverFactory.getDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.mpPageReloaded='notYet';");

	}

	/**
	 * Method Name: fetchHeaderValuesFromHeaderPage
	 * Purpose: To fetch Header values from Header Page
	 * @param driver
	 * @return
	 * @throws Exception
	 */
	public HashMap<Integer, String> fetchHeaderValuesFromHeaderPage(WebDriver driver) throws Exception {
		HashMap<Integer, String> displayedHeaderMap = new HashMap<Integer, String>();
		/*int headerFieldCount = driver
				.findElements(By.xpath("//div[@class='admin-header-container']/div[@class='row']//div[@class='row']"))
				.size();*/
		int headerFieldCount = driver
		.findElements(By.xpath(adminHeaderRowObject)).size();
		for (int headerFieldIndex = 1; headerFieldIndex <= headerFieldCount; headerFieldIndex++) {
			String displayedHeaderValue = driver
					.findElement(By.xpath(headerFieldValueObject.replace("%s", String.valueOf(headerFieldIndex))))
					.getAttribute("ng-reflect-model");
			displayedHeaderMap.put(headerFieldIndex, displayedHeaderValue);

		}
		return displayedHeaderMap;
	}
	/**
	 * Method Name: fetchFooterValuesFromHeaderPage
	 * Purpose: To fetch footer values from Footer Page
	 * @param driver
	 * @return
	 * @throws Exception
	 */
	public HashMap<Integer, String> fetchFooterValuesFromHeaderPage(WebDriver driver) throws Exception {
		HashMap<Integer, String> displayedHeaderMap = new HashMap<Integer, String>();
		int headerFieldCount = driver
				.findElements(By.xpath(footerFieldListValueObject))
				.size();
		for (int headerFieldIndex = 1; headerFieldIndex <= headerFieldCount; headerFieldIndex++) {
			String displayedHeaderValue = driver
					.findElement(By.xpath(footerFieldValueObject.replace("%s", String.valueOf(headerFieldIndex))))
					.getAttribute("ng-reflect-model");
			displayedHeaderMap.put(headerFieldIndex, displayedHeaderValue);
		}
		return displayedHeaderMap;
	}

	/**
	 * Method Name: editHeaderValues
	 * Purpose: To edit header values
	 * @param driver
	 * @param existingMap
	 * @param shouldItBeReverted
	 * @return
	 */
	public HashMap<Integer, String> editHeaderValues(WebDriver driver, HashMap<Integer, String> existingMap,
			boolean shouldItBeReverted) {

		HashMap<Integer, String> editedValuesMap = new HashMap<Integer, String>();
		for (int elementIndex = 1; elementIndex <= existingMap.size(); elementIndex++) {
			driver.findElement(By.xpath(headerFieldValueObject.replace("%s", String.valueOf(elementIndex)))).clear();
			if (shouldItBeReverted == false) {
				editedValuesMap.put(elementIndex, elementIndex + existingMap.get(elementIndex));
				driver.findElement(By.xpath(headerFieldValueObject.replace("%s", String.valueOf(elementIndex))))
						.sendKeys(elementIndex + existingMap.get(elementIndex));
			} else {
				driver.findElement(By.xpath(headerFieldValueObject.replace("%s", String.valueOf(elementIndex))))
						.sendKeys(existingMap.get(elementIndex));
			}
		}
		return editedValuesMap;
	}
	/**
	 * Method Name: clickOnPreviewButton
	 * Purpose: To click on Preview Button
	 * @param driver
	 */
	public void clickOnPreviewButton(WebDriver driver) {
		driver.findElement(By.xpath(headerButtonObject.replace("%s", "Preview"))).click();
	}

	/**
	 * Method Name: clickOnPublishButton
	 * Purpose: To click on Publish Button
	 * @param driver
	 */
	public void clickOnPublishButton(WebDriver driver) {
		driver.findElement(By.xpath(headerButtonObject.replace("%s", "Publish"))).click();
	}

	/**
	 * Method Name:validateFooterPreviewAction
	 * Purpose: To validate footer Preview Action
	 * @param result
	 * @throws Exception
	 */
	public void validateFooterPreviewAction(String result) throws Exception {
		driver = DriverFactory.getDriver();
		HashMap<Integer, String> existingValuesMap = new HashMap<Integer, String>();
		HashMap<Integer, String> editedValuesMap = new HashMap<Integer, String>();
		HashMap<Integer, String> displayedHeaderMap = new HashMap<Integer, String>();
		String compareMessage = "";

		existingValuesMap = fetchFooterValuesFromHeaderPage(driver);
		editedValuesMap = editHeaderValues(driver, existingValuesMap, false);
		String winHandleBefore = driver.getWindowHandle();
		clickOnPreviewButton(driver);
		displayedHeaderMap = fetchDisplayedHeaderValues(driver);
		compareMessage = compareEditedWithDisplayedHeaderValues(editedValuesMap, displayedHeaderMap);
		driver.switchTo().window(winHandleBefore);
		// editHeaderValues(driver, existingValuesMap);
		if (compareMessage.trim().contains("false")) {
			addExceptionToReport(compareMessage.split(";")[1], "", "");
		}

	}
	/**
	 * Method Name: validateHeaderPublishAction
	 * Purpose: to validate Header Publish action
	 * @param result
	 * @throws Exception
	 */
	public void validateHeaderPublishAction(String result) throws Exception {
		driver = DriverFactory.getDriver();
		HashMap<Integer, String> existingValuesMap = new HashMap<Integer, String>();
		HashMap<Integer, String> editedValuesMap = new HashMap<Integer, String>();
		HashMap<Integer, String> displayedHeaderMap = new HashMap<Integer, String>();
		String compareMessage = "";
		existingValuesMap = fetchHeaderValuesFromHeaderPage(driver);
		editedValuesMap = editHeaderValues(driver, existingValuesMap, false);
		clickOnPublishButton(driver);
		driver.get(url);
		beforePageLoad();
		displayedHeaderMap = fetchDisplayedHeaderValuesAfterPublish(driver);
		compareMessage = compareEditedWithDisplayedHeaderValues(editedValuesMap, displayedHeaderMap);
		driver.get(url+"/internal/admin-header");
		beforePageLoad();
		editHeaderValues(driver, existingValuesMap, true);
		clickOnPublishButton(driver);
		// editHeaderValues(driver, existingValuesMap);
		if (compareMessage.trim().contains("false")) {
			addExceptionToReport(compareMessage.split(";")[1], "", "");
		}

	}
	/**
	 * Method Name: fetchDisplayedHeaderValues
	 * Purpose: To fetch displayed Header Values
	 * @param driver
	 * @return
	 */
	public HashMap<Integer, String> fetchDisplayedHeaderValues(WebDriver driver) {
		HashMap<Integer, String> displayedHeaderMap = new HashMap<Integer, String>();
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
			int displayedHeaderObjectCount = driver.findElements(By.xpath(displayedHeaderValuesListObject)).size();
			for (int displayedHeaderIndex = 1; displayedHeaderIndex <= displayedHeaderObjectCount; displayedHeaderIndex++) {
				String displayedHeaderValue = driver
						.findElement(By
								.xpath(displayedHeaderValuesObject.replace("%s", String.valueOf(displayedHeaderIndex))))
						.getText();
				displayedHeaderMap.put(displayedHeaderIndex, displayedHeaderValue);
			}
		}
		driver.close();
		return displayedHeaderMap;

	}

	/**
	 * Method Name: fetchDisplayedHeaderValuesAfterPublish
	 * Purpose: To fetch Header Values after Publish action
	 * @param driver
	 * @return
	 */
	public HashMap<Integer, String> fetchDisplayedHeaderValuesAfterPublish(WebDriver driver) {
		HashMap<Integer, String> displayedHeaderMap = new HashMap<Integer, String>();
		int displayedHeaderObjectCount = driver.findElements(By.xpath(displayedHeaderValuesListObject)).size();
		for (int displayedHeaderIndex = 1; displayedHeaderIndex <= displayedHeaderObjectCount; displayedHeaderIndex++) {
			String displayedHeaderValue = driver
					.findElement(
							By.xpath(displayedHeaderValuesObject.replace("%s", String.valueOf(displayedHeaderIndex))))
					.getText();
			displayedHeaderMap.put(displayedHeaderIndex, displayedHeaderValue);
		}
		return displayedHeaderMap;

	}
	/**
	 * Method Name: compareEditedWithDisplayedHeaderValues
	 * Purpose: To compare edited header values with displayed header values
	 * @param editedMap
	 * @param displayedMap
	 * @return
	 */
	public String compareEditedWithDisplayedHeaderValues(HashMap<Integer, String> editedMap,
			HashMap<Integer, String> displayedMap) {
		String compareMessage = "";

		if (editedMap.size() != displayedMap.size()) {
			compareMessage = "false;Edited Header Count value " + editedMap.size()
					+ " is not matching with the Displayed Header Count " + displayedMap.size();
			return compareMessage;
		}
		for (int mapIndex = 1; mapIndex <= editedMap.size(); mapIndex++) {
			if (editedMap.get(mapIndex).trim().equalsIgnoreCase(displayedMap.get(mapIndex)) == false) {
				compareMessage = "false;Edited Header value " + editedMap.get(mapIndex)
						+ " is not matching with the Displayed Header value " + displayedMap.get(mapIndex);
				return compareMessage;
			}
		}

		return "true";

	}
	
	/**
	 * Method Name: clickOnFooterLinks
	 * Purpose: Click on the footer link in dashboard and verify the title
	 * @param 
	 * @param
	 */
	
	public void clickOnFooterLinks() throws Exception {
		WebDriver driver = DriverFactory.getDriver();
		
		int headerFieldCount = 4;
	//	String currentPageHandle = driver.getWindowHandle();
		
		for (int headerValueIndex = 1; headerValueIndex <= headerFieldCount; headerValueIndex++) {
			System.out.println("Entered For Loop");
			WebElement displayedHeaderValue = driver.findElement(By.xpath(footerFieldIndex.replace("%s", String.valueOf(headerValueIndex))));
			displayedHeaderValue.click();
			String currentPageHandle = driver.getWindowHandle();

		}
	} 
	
	public void toVerifyLinkInTheFooter(String expectedURL, String link, String title) throws TwfException, BiffException, IOException, InterruptedException {
		System.out.println("INSIDE toVerifyLinkInTheFooter>>>>>");
		WebDriver driver = DriverFactory.getDriver();
//		try {
			TimeUnit.MINUTES.sleep(1);
			WebElement ele = getElementByUsing(link);
			util.scrollToElement(ele);
			ele.click();
			System.out.println("CLICKEDD>>>>>");
			ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(2));
//			Thread.sleep(6000);
			if (!(expectedURL.equals(driver.getCurrentUrl()))) {
				addExceptionToReport("Actual URL is not matching with expectedURL",
						"Actual URL is >>>> " + driver.getCurrentUrl(), "Expected URL is>>>> " + expectedURL);
			}
			util.waitTillElementIsDisplayed(driver, getElementByUsing(title));
//			getElementByUsing(title).isDisplayed();
            System.out.println("title>>>"+title);
			driver.close();
			driver.switchTo().window(tabs.get(0));
		//} catch (Exception e) {
	//		addExceptionToReport("Link is not available", "Expected Link is >>>> " + link, "");
	//	}

	}

	public void verifyNMLSConsumerAccessLink() throws BiffException, InvalidFormatException, TwfException, IOException, InterruptedException {
		toVerifyLinkInTheFooter(KWVariables.getVariables().get("NMLSConsumerAccessLinkURL"),
				"FinExp_Dashboard_NMLSAccessLink", "NMLSPageTitle");
	}

	public void verifyFormsLink() throws BiffException, InvalidFormatException, TwfException, IOException, InterruptedException {
		toVerifyLinkInTheFooter(KWVariables.getVariables().get("FormsLinkURL"), "FinExp_Dashboard_FormsLink",
				"FormsPageTitle");
	}

	public void verifyLegalLink() throws BiffException, InvalidFormatException, TwfException, IOException, InterruptedException {
		toVerifyLinkInTheFooter(KWVariables.getVariables().get("LegalLinkURL"), "FinExp_Dashboard_LegalLink",
				"LegalPageTitle");
	}

	public void verifyPrivacyLink() throws BiffException, InvalidFormatException, TwfException, IOException, InterruptedException {
		toVerifyLinkInTheFooter(KWVariables.getVariables().get("PrivacyLinkURL"), "FinExp_Dashboard_PrivacyPolicyLink",
				"PrivacyPageTitle");
	}

	@Override
	public void checkPage() {
		// TODO Auto-generated method stub

	}
}
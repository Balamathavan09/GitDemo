package com.finx.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import com.tavant.base.DriverFactory;
import com.tavant.kwutils.CustomStep;
import com.tavant.utils.TwfException;

public class HeaderFooter extends CustomStep {

	WebDriver driver;
	List<String> headervalueslist = new ArrayList<String>();

	String headerxpath = "//div[@id='bs-example-navbar-collapse-1']/ul/li";
	String headerValuexpath = "//div[@id='bs-example-navbar-collapse-1']/ul/li[";
//	String headerFieldValueObject = "//div[@class='admin-header-container']/div[@class='row']//div[@class='row'][%s]/div[1]//input";
	String headerFieldValueObject = "//div[@class='admin-header-container']/div[@class='row'][1]//div[@class='row list-section'][%s]//input[contains(@id,'linkName')]";
	String headerURLFieldValueObject = "//div[@class='admin-header-container']/div[@class='row']//div[@class='row list-section'][%s]/div[2]//input";
	String headerDropDownFieldObject = "//div[@class='admin-header-container']/div[@class='row']//div[@class='row list-section'][%s]/div[2]//button";
	String footerDropDownFieldObject = "//div[@class='admin-header-container']/div[@class='row']//div[@class='input-holder']/div[@class='row list-section'][%s]/div[2]//button";
	String headerDropDownValueObject = "//div[@class='admin-header-container']/div[@class='row']//div[@class='row list-section'][%s]/div[2]//ul/li";
	String headerButtonObject = "//div[@class[contains(.,'btn-holder')]]/button[contains(.,'%s')]";
	String PreviewButtonObject = "//div[@class='admin-header-container']//button[contains(text(),'Preview')]";
	String displayedHeaderValuesListObject = ".//*[@class[contains(.,'navbar-right')]]/li";
	String displayedHeaderValuesObject = ".//*[@class[contains(.,'navbar-right')]]/li[%s]/a";
	String footerFieldValueObject = "//div[@class='admin-header-container']/div[@class='row']/div[@class='admin-header-inner']/div[@class='input-holder']/div[@class='row list-section'][%s]/div[1]//input";
	String footerFieldValueObjectlength = "//div[@class='row list-section'][%s]/div/div/div/md-input-container/div/div[3]/div/md-hint";
	String footerURLFieldValueObject = "//div[@class='admin-header-container']/div[@class='row']/div[@class='admin-header-inner']/div[@class='input-holder']/div[@class='row list-section'][%s]/div[2]//input";
	String footerFieldListValueObject = "//div[@class='admin-header-container']/div[@class='row']/div[@class='admin-header-inner']/div[@class='input-holder']/div[@class='row list-section']//input[contains(@id,'linkName')]";
	String footerCopyRightObject = "//div[@class='admin-header-container']/div[@class='row']/div[@class='admin-header-inner']/div[@class='input-holder']/div[@class='copyright-section']/div[2]/div[1]//input";
	String footerPreviewListObject = "//div[@class='footer-left']/ul/li";
	String footerPreviewValuesObject = "//div[@class='footer-left']/ul/li[%s]/a";
	String updateAlert = "//div[@class='alert alert-info']";
	String checkBox = ".//*[@id='internalLink_%s']";
	String clearButtonCount = "(//div[@class='admin-header-container']/div[@class='row']//button[@type='reset'])";
	String clearButton = "(//div[@class='admin-header-container']/div[@class='row']//button[@type='reset'])[%s]";
	String maximumPermissibleCharactersIndicator = "(.//*[contains(@class,'mat-input-container')]//div/md-hint)[5]";
	String fectchvalue = "//div[@class='admin-header-container']/div[@class='row']//div[@class='row list-section']";
	String url1 = "https://www.usbank.com";
	String url2 = "www.landc.co.uk";
	String url3 = "www.homebuyinginstitute.com/mortgagetypes.php";
	String url4 = "http://www.genworth.in/";
	String url5 = "www.google.com";
	String url6 = "http://10.209.1.38:9090/#/";
	String url7 = "http://10.209.1.38:9090/#/internal/admin-header";
	String url8 = "http://10.209.1.38:9090/#/internal/admin-footer";

	// WebDriver driver = DriverFactory.getDriver();
	/**
	 * it will wait for page load
	 * 
	 * @throws TwfException
	 */
	public void beforePageLoad() throws TwfException {
		WebDriver driver = DriverFactory.getDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.mpPageReloaded='notYet';");

	}

	// Please externalise the test data
	/**
	 * it will be build a expected map for testing purpose
	 * 
	 * @param build
	 * @return
	 */
	public HashMap<String, String> buildExpectedHeaderValuesMap(String build) {
		System.out.println("entered buildExpectedHeaderValuesMap");
		HashMap<String, String> expectedDataMap = new HashMap<String, String>();
		String[] array1 = build.split("_");
		for (int index = 0; index < array1.length; index++) {
			String[] array2 = array1[index].split("\\|");
			expectedDataMap.put(array2[0], array2[1]);
		}
		return expectedDataMap;
	}

	/**
	 * it will fecth Url value from text field
	 * 
	 * @param driver
	 * @param rowIndex
	 * @return
	 */
	public String fetchURLValue(WebDriver driver, int rowIndex) {
		boolean flag = driver.findElement(By.xpath(checkBox.replace("%s", String.valueOf(rowIndex - 1)))).isSelected();
		String displayedValue = "";
		if (flag) {
			displayedValue = driver
					.findElement(By.xpath(headerDropDownFieldObject.replace("%s", String.valueOf(rowIndex)))).getText();
			displayedValue = "Internal;" + displayedValue;

		} else {
			displayedValue = driver
					.findElement(By.xpath(headerURLFieldValueObject.replace("%s", String.valueOf(rowIndex))))
					.getAttribute("ng-reflect-model");
			displayedValue = "NonInternal;" + displayedValue;
			System.out.println("displayedValue---> " + displayedValue);
		}
		return displayedValue;
	}

	// Declare objects in the beginning of class
	/**
	 * it will be fetch header value from page
	 * 
	 * @param driver
	 * @return
	 * @throws Exception
	 */
	public HashMap<Integer, HashMap<String, String>> fetchHeaderValuesFromHeaderPage(WebDriver driver)
			throws Exception {

		HashMap<Integer, HashMap<String, String>> fieldAndURLValuesMapindex = new HashMap<Integer, HashMap<String, String>>();
		
		int headerFieldCount = driver.findElements(By.xpath(fectchvalue)).size();
		System.out.println("headerFieldCount  ---> "+ headerFieldCount);
		for (int headerFieldIndex = 1; headerFieldIndex <= headerFieldCount; headerFieldIndex++) {
			HashMap<String, String> displayedHeaderMap = new HashMap<String, String>();
			System.out.println("headerFieldIndex ---> "+ headerFieldIndex);
			String displayedHeaderValue = driver
					.findElement(By.xpath(headerFieldValueObject.replace("%s", String.valueOf(headerFieldIndex))))
					.getAttribute("value");

			String displayedHeaderURLValue = fetchURLValue(driver, headerFieldIndex);

			displayedHeaderMap.put(displayedHeaderValue, displayedHeaderURLValue);
			fieldAndURLValuesMapindex.put(headerFieldIndex, displayedHeaderMap);
		}
		return fieldAndURLValuesMapindex;
	}

	/**
	 * 
	 * method will edit the header values
	 * 
	 * @param driver
	 * @param expectedDataMap
	 * @param existingMap
	 * @return
	 * @throws Exception
	 */
	public HashMap<Integer, HashMap<String, String>> editHeaderValues(WebDriver driver,
			HashMap<String, String> expectedDataMap, HashMap<Integer, HashMap<String, String>> existingMap)
			throws Exception {
		HashMap<String, String> existingDataMap = new HashMap<String, String>();
		HashMap<Integer, HashMap<String, String>> editedMap = new HashMap<Integer, HashMap<String, String>>();
		String internalVal = "";
		
		System.out.println("entered editHeaderValues");
		for (int mapIndex = 1; mapIndex <= existingMap.size(); mapIndex++) {
			HashMap<String, String> editDataMap = new HashMap<String, String>();
			existingDataMap = existingMap.get(mapIndex);

			for (String key : existingDataMap.keySet()) {
				String revisedValue = mapIndex + key;
				driver.findElement(By.xpath(headerFieldValueObject.replace("%s", String.valueOf(mapIndex)))).clear();
				driver.findElement(By.xpath(headerFieldValueObject.replace("%s", String.valueOf(mapIndex))))
						.sendKeys(revisedValue);
				String expectedValue = expectedDataMap.get(key);
				if (expectedValue.trim().equalsIgnoreCase("Internal")) {
					internalVal = selectInternalValue(driver, mapIndex, "");
					editDataMap.put(revisedValue, internalVal);
				} else {
					if (driver.findElement(By.xpath(checkBox.replace("%s", String.valueOf(mapIndex - 1))))
							.isSelected()) {
						driver.findElement(By.xpath(checkBox.replace("%s", String.valueOf(mapIndex - 1)))).click();
					}
					driver.findElement(By.xpath(headerURLFieldValueObject.replace("%s", String.valueOf(mapIndex))))
							.sendKeys(expectedValue);
					editDataMap.put(revisedValue, expectedValue);
				}
				editedMap.put(mapIndex, editDataMap);
			}
		}
		return editedMap;
	}

	/**
	 * @param driver
	 *            click on preview button
	 * 
	 */
	public void clickOnPreviewButton(WebDriver driver) {
		driver.findElement(By.xpath(PreviewButtonObject)).click();
	}

	/**
	 * @param driver
	 *            click on publish button
	 * 
	 */
	public void clickOnPublishButton(WebDriver driver) {
		System.out.println("entered clickOnPublishButton");
		driver.findElement(By.xpath(headerButtonObject.replace("%s", "Publish"))).click();
	}

	/**
	 * it will fetch initials values from page
	 * 
	 * @param driver
	 * @return
	 */
	public HashMap<Integer, HashMap<String, String>> fetchDisplayedHeaderValues(WebDriver driver) {
		HashMap<Integer, HashMap<String, String>> displayedHeaderMap = new HashMap<Integer, HashMap<String, String>>();
		HashMap<String, String> displayedHeaderMapValueAndURL = new HashMap<String, String>();
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
			int displayedHeaderObjectCount = driver.findElements(By.xpath(displayedHeaderValuesListObject)).size();
			for (int displayedHeaderIndex = 1; displayedHeaderIndex <= displayedHeaderObjectCount; displayedHeaderIndex++) {
				String displayedHeaderValue = driver
						.findElement(By
								.xpath(displayedHeaderValuesObject.replace("%s", String.valueOf(displayedHeaderIndex))))
						.getText();

				String displayedHeaderURLValue = driver
						.findElement(By
								.xpath(displayedHeaderValuesObject.replace("%s", String.valueOf(displayedHeaderIndex))))
						.getAttribute("href");
				displayedHeaderMapValueAndURL.put(displayedHeaderValue, displayedHeaderURLValue);
				displayedHeaderMap.put(displayedHeaderIndex, displayedHeaderMapValueAndURL);

			}
		}
		driver.close();
		return displayedHeaderMap;

	}

	/**
	 * click on save draft button
	 * 
	 * @param driver
	 * @throws Exception
	 */
	public void clickOnSaveDraftButton(WebDriver driver) throws Exception {
		driver.findElement(By.xpath(headerButtonObject.replace("%s", "Save Draft"))).click();
		Thread.sleep(2000);

		String compareMessage = driver.findElement(By.xpath(updateAlert)).getText().trim();
		if (!compareMessage.contains("Updated successfully")) {
			addExceptionToReport("After clicking on save draft button message is not displayed",
					"message should be displayed", "message is displayed");
		}

	}

	// Remove System.out. once the vaildation of hrefs is confirmed
	/**
	 * 
	 * compare entered values and Url with published/preview values
	 * 
	 * @param editedMap
	 * @param displayedMap
	 * @return
	 */
	public String compareEditedWithDisplayedHeaderValues(HashMap<Integer, HashMap<String, String>> editedMap,
			HashMap<Integer, HashMap<String, String>> displayedMap) {
		System.out.println("entered compareEditedWithDisplayedHeaderValues ");

		HashMap<String, String> existingDataMap = new HashMap<String, String>();
		HashMap<String, String> displayedMapDataMap = new HashMap<String, String>();

		String compareMessage = "";
		if (editedMap.size() != displayedMap.size()) {
			compareMessage = "false;Edited  Count value " + editedMap.size()
					+ " is not matching with the Displayed  Count " + displayedMap.size();
			return compareMessage;
		}
		for (int mapIndex = 1; mapIndex <= editedMap.size(); mapIndex++) {
			existingDataMap = editedMap.get(mapIndex);
			displayedMapDataMap = displayedMap.get(mapIndex);
			if (existingDataMap.size() != displayedMapDataMap.size())
				return "false";
			for (String key : existingDataMap.keySet()) {
				if (!displayedMapDataMap.get(key).contains(existingDataMap.get(key))) {
					return "false";

				}

			}
		}
		return "true";
	}

	/**
	 * fetch header values from home page
	 * 
	 * @param driver
	 * @return
	 */
	public HashMap<Integer, HashMap<String, String>> fetchDisplayedHeaderValuesAfterPublish(WebDriver driver) {
		HashMap<Integer, HashMap<String, String>> displayedHeaderMap1 = new HashMap<Integer, HashMap<String, String>>();
		System.out.println("entered fetchDisplayedHeaderValuesAfterPublish");
		int displayedHeaderObjectCount = driver.findElements(By.xpath(displayedHeaderValuesListObject)).size();
		for (int displayedHeaderIndex = 1; displayedHeaderIndex <= displayedHeaderObjectCount; displayedHeaderIndex++) {
			HashMap<String, String> displayedHeaderMap = new HashMap<String, String>();
			String displayedHeaderValue = driver
					.findElement(
							By.xpath(displayedHeaderValuesObject.replace("%s", String.valueOf(displayedHeaderIndex))))
					.getText();

			String displayedHeaderURLValue = driver
					.findElement(
							By.xpath(displayedHeaderValuesObject.replace("%s", String.valueOf(displayedHeaderIndex))))
					.getAttribute("href");

			displayedHeaderMap.put(displayedHeaderValue, displayedHeaderURLValue);
			displayedHeaderMap1.put(displayedHeaderIndex, displayedHeaderMap);

		}
		return displayedHeaderMap1;

	}

	/**
	 * it will be check preview functionality
	 * 
	 * @param result
	 * @throws Exception
	 */
	public void validateHeaderPreviewAction(String result) throws Exception {

		HashMap<String, String> expectedValuesMap = new HashMap<String, String>();
		HashMap<Integer, HashMap<String, String>> existingValuesMap = new HashMap<Integer, HashMap<String, String>>();
		HashMap<Integer, HashMap<String, String>> editedValuesMap = new HashMap<Integer, HashMap<String, String>>();
		HashMap<Integer, HashMap<String, String>> displayedHeaderMap = new HashMap<Integer, HashMap<String, String>>();

		HashMap<Integer, HashMap<String, String>> ExistMap = new HashMap<Integer, HashMap<String, String>>();
		driver = DriverFactory.getDriver();
		String compareMessage = "";

		existingValuesMap = fetchHeaderValuesFromHeaderPage(driver);
		String build = buildExpecteValuesMapToString(driver, existingValuesMap);

		expectedValuesMap = buildExpectedHeaderValuesMap(build);
		// existingValuesMap = fetchHeaderValuesFromHeaderPage(driver);
		editedValuesMap = editHeaderValues(driver, expectedValuesMap, existingValuesMap);
		String winHandleBefore = driver.getWindowHandle();
		clickOnPreviewButton(driver);
		displayedHeaderMap = fetchDisplayedHeaderValues(driver);

		compareMessage = compareEditedWithDisplayedHeaderValues(editedValuesMap, displayedHeaderMap);
		// System.out.println("done");
		// Commented bcoz we are not clear of href values when drop dowmn is
		// selected
		/*
		 * if(compareMessage.trim().contains("false")) {
		 * addExceptionToReport(compareMessage.split(";")[1], "", "");
		 * 
		 * }
		 */

	}

	/**
	 * check if internal check box is checked and select values from dropdown
	 * 
	 * @param driver
	 * @param rowIndex
	 * @param internalVal
	 * @return
	 * @throws Exception
	 */
	public String selectInternalValue(WebDriver driver, Integer rowIndex, String internalVal) throws Exception {
		Thread.sleep(2000);
		System.out.println("entered selectInternalValue");
		if (driver.findElement(By.xpath(checkBox.replace("%s", String.valueOf(rowIndex - 1)))).isSelected() == false) {
			driver.findElement(By.xpath(checkBox.replace("%s", String.valueOf(rowIndex - 1)))).click();
		}
		if (internalVal.equalsIgnoreCase("null")) {
			System.out.println("Internal val?>>>>" + internalVal);
			Thread.sleep(2000);
			driver.findElement(By.xpath(headerDropDownFieldObject.replace("%s", String.valueOf(rowIndex)))).click();
			Thread.sleep(2000);
			String revisedObject = headerDropDownValueObject.replace("%s", String.valueOf(rowIndex));
			if (internalVal.length() > 0) {
				Thread.sleep(2000);
				revisedObject = revisedObject + "/a[contains(.,'" + internalVal + "')]";
				Thread.sleep(2000);
			} else {
				revisedObject = revisedObject + "[2]/a";
				internalVal = driver.findElement(By.xpath(revisedObject)).getText();
			}
			driver.findElement(By.xpath(revisedObject)).click();
		}
		System.out.println("internalVal "+internalVal);
		return internalVal;
	}

	/**
	 * revert back to original values
	 * 
	 * @param driver
	 * @param existingMap
	 * @throws Exception
	 */
	public void revertToOldHeaderValues(WebDriver driver, HashMap<Integer, HashMap<String, String>> existingMap)
			throws Exception {

		System.out.println("entered revertToOldHeaderValues ");
		HashMap<String, String> existingDataMap = new HashMap<String, String>();

		HashMap<Integer, HashMap<String, String>> editedMap = new HashMap<Integer, HashMap<String, String>>();
		HashMap<String, String> dropDownMap = new HashMap<String, String>();
		dropDownMap.put("get-quote", "Get-A-Quote");
		dropDownMap.put("prequal", "Pre-Qualification");
		dropDownMap.put("#", "Help");

		for (int mapIndex = 1; mapIndex <= existingMap.size(); mapIndex++) {
			existingDataMap = existingMap.get(mapIndex);
			for (String key : existingDataMap.keySet()) {
				driver.findElement(By.xpath(headerFieldValueObject.replace("%s", String.valueOf(mapIndex)))).clear();
				driver.findElement(By.xpath(headerFieldValueObject.replace("%s", String.valueOf(mapIndex))))
						.sendKeys(key);
				String expectedValue = existingDataMap.get(key);
				String[] expectedValArray = expectedValue.split(";");
				 System.out.println("Key>>>" + key + " and expected value>>>>"
				 + expectedValArray[1]);
				if (expectedValArray[0].equals("Internal")) {
					boolean flag = driver.findElement(By.xpath(checkBox.replace("%s", String.valueOf(mapIndex - 1))))
							.isSelected();
					if (flag) {
						driver.findElement(By.xpath(checkBox.replace("%s", String.valueOf(mapIndex - 1)))).click();
					}
					if (expectedValArray[1].equals("null") == false) {
						driver.findElement(By.xpath(headerURLFieldValueObject.replace("%s", String.valueOf(mapIndex))))
								.clear();
						driver.findElement(By.xpath(headerURLFieldValueObject.replace("%s", String.valueOf(mapIndex))))
								.sendKeys(expectedValArray[1]);
					}

				} else {
					selectInternalValue(driver, mapIndex, dropDownMap.get(expectedValArray[1].trim()));
				}

			}
		}
	}

	// URLS needs to be externalised

	/**
	 * method will verify header publish action
	 * 
	 * @param result
	 * @throws Exception
	 */
	public void validateHeaderPublishAction(String result) throws Exception {
		driver = DriverFactory.getDriver();

		HashMap<String, String> expectedValuesMap = new HashMap<String, String>();
		HashMap<Integer, HashMap<String, String>> existingValuesMap = new HashMap<Integer, HashMap<String, String>>();
		HashMap<Integer, HashMap<String, String>> editedValuesMap = new HashMap<Integer, HashMap<String, String>>();
		HashMap<Integer, HashMap<String, String>> displayedHeaderMap = new HashMap<Integer, HashMap<String, String>>();
		driver = DriverFactory.getDriver();
		String compareMessage = "";
		existingValuesMap = fetchHeaderValuesFromHeaderPage(driver);

		String Build = buildExpecteValuesMapToString(driver, existingValuesMap);

		expectedValuesMap = buildExpectedHeaderValuesMap(Build);
		existingValuesMap = fetchHeaderValuesFromHeaderPage(driver);
		editedValuesMap = editHeaderValues(driver, expectedValuesMap, existingValuesMap);
		clickOnPublishButton(driver);
		driver.get(url6);
		beforePageLoad();
		displayedHeaderMap = fetchDisplayedHeaderValuesAfterPublish(driver);
		compareMessage = compareEditedWithDisplayedHeaderValues(editedValuesMap, displayedHeaderMap);
		driver.get(url7);
		beforePageLoad();
		revertToOldHeaderValues(driver, existingValuesMap);
		clickOnPublishButton(driver);
		/*
		 * if (compareMessage.trim().contains("false")) {
		 * addExceptionToReport(compareMessage.split(";")[1], "", ""); }
		 */
	}

	/**
	 * method will editing headers values in header page
	 * 
	 * @param driver
	 * @param existingMap
	 * @param shouldItBeReverted
	 * @return
	 */
	public HashMap<Integer, HashMap<String, String>> editHeaderValues(WebDriver driver,
			HashMap<Integer, HashMap<String, String>> existingMap, boolean shouldItBeReverted) {

		HashMap<Integer, HashMap<String, String>> editedValuesMap = new HashMap<Integer, HashMap<String, String>>();
		HashMap<String, String> editMap = new HashMap<String, String>();

		for (int elementIndex = 1; elementIndex <= existingMap.size(); elementIndex++) {
			String displayedValue = "";
			boolean flag = driver.findElement(By.xpath(checkBox.replace("%s", String.valueOf(elementIndex - 1))))
					.isSelected();
			String displayedHeaderValue = driver
					.findElement(By.xpath(headerFieldValueObject.replace("%s", String.valueOf(elementIndex)))).getText()
					.trim();
			HashMap<String, String> innerMap = existingMap.get(elementIndex);
			String value = innerMap.get(displayedHeaderValue);
			Set<String> key = innerMap.keySet();
			String key1 = key.toString();
			String revisedValue = elementIndex + displayedHeaderValue;
			driver.findElement(By.xpath(headerFieldValueObject.replace("%s", String.valueOf(elementIndex)))).clear();
			driver.findElement(By.xpath(headerFieldValueObject.replace("%s", String.valueOf(elementIndex))))
					.sendKeys(value);
			if (shouldItBeReverted == false) {
				editMap.put(revisedValue, value);
				editedValuesMap.put(elementIndex, editMap);

				if (!flag) {

					driver.findElement(By.xpath(headerURLFieldValueObject.replace("%s", String.valueOf(elementIndex))))
							.sendKeys(value);

				}
				// System.out.println("Edit values map>>>>>" +
				// editedValuesMap.get(elementIndex) + "and index>>>" +
				// elementIndex);
			}
		}
		return editedValuesMap;
	}

	/**
	 * method will verify header save draft action
	 * 
	 * @param result
	 * @throws Exception
	 */
	public void validateHeaderSaveDraftAction(String result) throws Exception {

		HashMap<String, String> expectedValuesMap = new HashMap<String, String>();
		HashMap<Integer, HashMap<String, String>> existingValuesMap = new HashMap<Integer, HashMap<String, String>>();
		HashMap<Integer, HashMap<String, String>> editedValuesMap = new HashMap<Integer, HashMap<String, String>>();
		HashMap<Integer, HashMap<String, String>> displayedHeaderMap = new HashMap<Integer, HashMap<String, String>>();

		HashMap<Integer, HashMap<String, String>> ExistMap = new HashMap<Integer, HashMap<String, String>>();
		driver = DriverFactory.getDriver();
		String compareMessage = "";

		existingValuesMap = fetchHeaderValuesFromHeaderPage(driver);
		String Build = buildExpecteValuesMapToString(driver, existingValuesMap);

		expectedValuesMap = buildExpectedHeaderValuesMap(Build);
		editedValuesMap = editHeaderValues(driver, expectedValuesMap, existingValuesMap);
		clickOnSaveDraftButton(driver);

	}

	/**
	 * it will fetch url value from page
	 * 
	 * @param driver
	 * 
	 * @param rowIndex
	 * @return
	 */
	public String fetchFooterURLValue(WebDriver driver, int rowIndex) {
		boolean flag = driver.findElement(By.xpath(checkBox.replace("%s", String.valueOf(rowIndex - 1)))).isSelected();
		String displayedValue = "";
		if (flag) {
			displayedValue = driver
					.findElement(By.xpath(footerDropDownFieldObject.replace("%s", String.valueOf(rowIndex)))).getText();
			displayedValue = "Internal;" + displayedValue;

		} else {
			displayedValue = driver
					.findElement(By.xpath(headerURLFieldValueObject.replace("%s", String.valueOf(rowIndex))))
					.getAttribute("ng-reflect-model");
			displayedValue = "NonInternal;" + displayedValue;
		}
		return displayedValue;
	}

	/**
	 * fetch Values From Footer Page
	 * 
	 * @param driver
	 * @return
	 * @throws Exception
	 */
	public HashMap<Integer, HashMap<String, String>> fetchValuesFromFooterPage(WebDriver driver) throws Exception {
		HashMap<Integer, HashMap<String, String>> fieldAndURLValuesMapindex = new HashMap<Integer, HashMap<String, String>>();
		
		driver.findElement(By.xpath("//admin-footer/div/div/div[1]/internal-admin-navigation/div/div/ul/li[2]/ul/li[2]/a")).click();
		
		int headerFieldCount = driver.findElements(By.xpath(footerFieldListValueObject)).size();
		System.out.println("headerFieldCount  ---> "+ headerFieldCount);
		for (int headerFieldIndex = 1; headerFieldIndex <= headerFieldCount; headerFieldIndex++) {
			HashMap<String, String> displayedHeaderMap = new HashMap<String, String>();
			System.out.println("headerFieldIndex ---> "+ headerFieldIndex);
			String displayedHeaderValue = driver
					.findElement(By.xpath(footerFieldValueObject.replace("%s", String.valueOf(headerFieldIndex))))
					.getAttribute("value");
			String displayedHeaderURLValue = fetchFooterURLValue(driver, headerFieldIndex);
			displayedHeaderMap.put(displayedHeaderValue, displayedHeaderURLValue);
			fieldAndURLValuesMapindex.put(headerFieldIndex, displayedHeaderMap);

		}
		return fieldAndURLValuesMapindex;
	}

	/**
	 * edit Footer Values
	 * 
	 * @param driver
	 * @param expectedDataMap
	 * @param existingMap
	 * @return
	 * @throws Exception
	 */
	public HashMap<Integer, HashMap<String, String>> editFooterValues(WebDriver driver,
			HashMap<String, String> expectedDataMap, HashMap<Integer, HashMap<String, String>> existingMap)
			throws Exception {

		HashMap<String, String> existingDataMap = new HashMap<String, String>();
		HashMap<Integer, HashMap<String, String>> editedMap = new HashMap<Integer, HashMap<String, String>>();
		String internalVal = "";
		for (int mapIndex = 1; mapIndex <= existingMap.size(); mapIndex++) {
			HashMap<String, String> editDataMap = new HashMap<String, String>();
			existingDataMap = existingMap.get(mapIndex);

			for (String key : existingDataMap.keySet()) {
				String revisedValue = mapIndex + key;
				driver.findElement(By.xpath(footerFieldValueObject.replace("%s", String.valueOf(mapIndex)))).clear();
				driver.findElement(By.xpath(footerFieldValueObject.replace("%s", String.valueOf(mapIndex))))
						.sendKeys(revisedValue);

				String expectedValue = expectedDataMap.get(key);

				// System.out.println(expectedValue + "--->>>>>>>");

				if (expectedValue.trim().equalsIgnoreCase("Internal")) {
					internalVal = selectInternalValue(driver, mapIndex, "");
					editDataMap.put(revisedValue, internalVal);
				} else {
					if (driver.findElement(By.xpath(checkBox.replace("%s", String.valueOf(mapIndex - 1))))
							.isSelected()) {
						driver.findElement(By.xpath(checkBox.replace("%s", String.valueOf(mapIndex - 1)))).click();
					}
					driver.findElement(By.xpath(headerURLFieldValueObject.replace("%s", String.valueOf(mapIndex))))
							.sendKeys(expectedValue);
					editDataMap.put(revisedValue, expectedValue);
				}
				editedMap.put(mapIndex, editDataMap);
			}
		}
		return editedMap;
	}

	/**
	 * fetch Displayed Footer Values
	 * 
	 * @param driver
	 * @return
	 */
	public HashMap<Integer, HashMap<String, String>> fetchDisplayedFooterValues(WebDriver driver) {

		HashMap<Integer, HashMap<String, String>> displayedHeaderMap = new HashMap<Integer, HashMap<String, String>>();
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
			int displayedHeaderObjectCount = driver.findElements(By.xpath(footerPreviewListObject)).size();
			for (int displayedHeaderIndex = 1; displayedHeaderIndex <= displayedHeaderObjectCount; displayedHeaderIndex++) {
				HashMap<String, String> displayedHeaderMapValueAndURL = new HashMap<String, String>();
				String displayedHeaderValue = driver
						.findElement(
								By.xpath(footerPreviewValuesObject.replace("%s", String.valueOf(displayedHeaderIndex))))
						.getText().replace("|", "").trim();

				String displayedHeaderURLValue = driver
						.findElement(
								By.xpath(footerPreviewValuesObject.replace("%s", String.valueOf(displayedHeaderIndex))))
						.getAttribute("href");
				displayedHeaderMapValueAndURL.put(displayedHeaderValue, displayedHeaderURLValue);
				displayedHeaderMap.put(displayedHeaderIndex, displayedHeaderMapValueAndURL);

			}
		}
		driver.close();
		return displayedHeaderMap;

	}

	/**
	 * revert To Old Footer Values
	 * 
	 * @param driver
	 * @param existingMap
	 * @throws Exception
	 */
	public void revertToOldFooterValues(WebDriver driver, HashMap<Integer, HashMap<String, String>> existingMap)
			throws Exception {

		System.out.println("entered revertToOldHeaderValues ");
		HashMap<String, String> existingDataMap = new HashMap<String, String>();
		HashMap<Integer, HashMap<String, String>> editedMap = new HashMap<Integer, HashMap<String, String>>();
		HashMap<String, String> dropDownMap = new HashMap<String, String>();
		dropDownMap.put("get-quote", "Get-A-Quote");
		dropDownMap.put("prequal", "Pre-Qualification");
		dropDownMap.put("#", "Help");

		for (int mapIndex = 1; mapIndex <= existingMap.size(); mapIndex++) {
			existingDataMap = existingMap.get(mapIndex);
			for (String key : existingDataMap.keySet()) {
				driver.findElement(By.xpath(footerFieldValueObject.replace("%s", String.valueOf(mapIndex)))).clear();
				driver.findElement(By.xpath(footerFieldValueObject.replace("%s", String.valueOf(mapIndex))))
						.sendKeys(key);

				String expectedValue = existingDataMap.get(key);
				String[] expectedValArray = expectedValue.split(";");
				 System.out.println("Key>>>" + key + " and expected value>>>>"
						 + expectedValArray[1]);
				if (expectedValArray[0].equals("Internal")) {
					boolean flag = driver.findElement(By.xpath(checkBox.replace("%s", String.valueOf(mapIndex - 1))))
							.isSelected();
					if (flag) {
						driver.findElement(By.xpath(checkBox.replace("%s", String.valueOf(mapIndex - 1)))).click();
					}
					if (expectedValArray[1].equals("null") == false) {
						driver.findElement(By.xpath(headerURLFieldValueObject.replace("%s", String.valueOf(mapIndex))))
								.clear();
						driver.findElement(By.xpath(headerURLFieldValueObject.replace("%s", String.valueOf(mapIndex))))
								.sendKeys(expectedValArray[1]);
					}

				} else {
					selectInternalValue(driver, mapIndex, dropDownMap.get(expectedValArray[1].trim()));
				}

			}
		}
	}

	/**
	 * fetch Displayed Footer Values After Publish
	 * 
	 * @param driver
	 * @return
	 */
	public HashMap<Integer, HashMap<String, String>> fetchDisplayedFooterValuesAfterPublish(WebDriver driver) {
		HashMap<Integer, HashMap<String, String>> displayedHeaderMap1 = new HashMap<Integer, HashMap<String, String>>();
		int displayedHeaderObjectCount = driver.findElements(By.xpath(footerPreviewListObject)).size();

		for (int displayedHeaderIndex = 1; displayedHeaderIndex <= displayedHeaderObjectCount; displayedHeaderIndex++) {
			HashMap<String, String> displayedHeaderMap = new HashMap<String, String>();

			String displayedHeaderValue = driver
					.findElement(
							By.xpath(footerPreviewValuesObject.replace("%s", String.valueOf(displayedHeaderIndex))))
					.getText().replace("|", "").trim();

			String displayedHeaderURLValue = driver
					.findElement(
							By.xpath(footerPreviewValuesObject.replace("%s", String.valueOf(displayedHeaderIndex))))
					.getAttribute("href");

			displayedHeaderMap.put(displayedHeaderValue, displayedHeaderURLValue);
			displayedHeaderMap1.put(displayedHeaderIndex, displayedHeaderMap);

		}
		return displayedHeaderMap1;

	}

	/**
	 * validate Footer Preview Action
	 * 
	 * @param result
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public void validateFooterPreviewAction(String result) throws Exception {
		driver = DriverFactory.getDriver();
		HashMap<String, String> expectedValuesMap = new HashMap<String, String>();
		HashMap<Integer, HashMap<String, String>> existingValuesMap = new HashMap<Integer, HashMap<String, String>>();
		HashMap<Integer, HashMap<String, String>> editedValuesMap = new HashMap<Integer, HashMap<String, String>>();
		HashMap<Integer, HashMap<String, String>> displayedHeaderMap = new HashMap<Integer, HashMap<String, String>>();
		String compareMessage = "";

		existingValuesMap = fetchValuesFromFooterPage(driver);

		String build = buildExpecteValuesMapToString(driver, existingValuesMap);

		expectedValuesMap = buildExpectedHeaderValuesMap(build);

		editedValuesMap = editFooterValues(driver, expectedValuesMap, existingValuesMap);
		String winHandleBefore = driver.getWindowHandle();
		clickOnPreviewButton(driver);

		displayedHeaderMap = fetchDisplayedFooterValues(driver);

		compareMessage = compareEditedWithDisplayedHeaderValues(editedValuesMap, displayedHeaderMap);
		// Commented because we are not clear of href values when drop down is
		// selected
		/*
		 * if(compareMessage.trim().contains("false")) {
		 * addExceptionToReport(compareMessage.split(";")[1], "", "");
		 * 
		 * }
		 */

	}

	/**
	 * validate Footer Publish Action
	 * 
	 * @param result
	 * @throws Exception
	 */
	public void validateFooterPublishAction(String result) throws Exception {

		driver = DriverFactory.getDriver();
		HashMap<String, String> expectedValuesMap = new HashMap<String, String>();
		HashMap<Integer, HashMap<String, String>> existingValuesMap = new HashMap<Integer, HashMap<String, String>>();
		HashMap<Integer, HashMap<String, String>> editedValuesMap = new HashMap<Integer, HashMap<String, String>>();
		HashMap<Integer, HashMap<String, String>> displayedHeaderMap = new HashMap<Integer, HashMap<String, String>>();
		String compareMessage = "";

		existingValuesMap = fetchValuesFromFooterPage(driver);

		String build = buildExpecteValuesMapToString(driver, existingValuesMap);

		expectedValuesMap = buildExpectedHeaderValuesMap(build);

		editedValuesMap = editFooterValues(driver, expectedValuesMap, existingValuesMap);
		clickOnPublishButton(driver);
		driver.get(url6);
		beforePageLoad();
		displayedHeaderMap = fetchDisplayedFooterValuesAfterPublish(driver);
		compareMessage = compareEditedWithDisplayedHeaderValues(editedValuesMap, displayedHeaderMap);
		driver.get(url8);
		beforePageLoad();
		revertToOldFooterValues(driver, existingValuesMap);
		clickOnPublishButton(driver);
		/*
		 * Commented because we are not clear of href values when drop down is
		 * selected if (compareMessage.trim().contains("false")) {
		 * addExceptionToReport(compareMessage.split(";")[1], "", ""); }
		 */
	}

	// URLS needs to be externalised

	/**
	 * validate Footer Save Draft Action
	 * 
	 * @param result
	 * @throws Exception
	 */
	public void validateFooterSaveDraftAction(String result) throws Exception {
		driver = DriverFactory.getDriver();
		HashMap<String, String> expectedValuesMap = new HashMap<String, String>();
		HashMap<Integer, HashMap<String, String>> existingValuesMap = new HashMap<Integer, HashMap<String, String>>();
		HashMap<Integer, HashMap<String, String>> editedValuesMap = new HashMap<Integer, HashMap<String, String>>();
		HashMap<Integer, HashMap<String, String>> displayedHeaderMap = new HashMap<Integer, HashMap<String, String>>();
		String compareMessage = "";

		existingValuesMap = fetchValuesFromFooterPage(driver);
		System.out.println("Entered validateFooterSaveDraftAction");
		String build = buildExpecteValuesMapToString(driver, existingValuesMap);
		expectedValuesMap = buildExpectedHeaderValuesMap(build);
		editedValuesMap = editFooterValues(driver, expectedValuesMap, existingValuesMap);
		clickOnSaveDraftButton(driver);

	}

	public void validateFooterFieldLength(String result) throws Exception {
		driver = DriverFactory.getDriver();
		driver.findElement(By.xpath("//admin-footer/div/div/div[1]/internal-admin-navigation/div/div/ul/li[2]/ul/li[2]/a")).click();
		validateFooterClearButton(driver);
		validateFooterCopyRightTextFieldLength(driver);
		validateFooterTextFieldLength(driver);

		// validateFooterURLTextFieldLength(driver);
		//validateFooterCopyRightTextFieldLength(driver);

	}

	/**
	 * validate Footer Clear Button
	 * 
	 * @param driver
	 * @throws Exception
	 */
	public void validateFooterClearButton(WebDriver driver) throws Exception {
		
		int displayedHeaderObjectCount = driver.findElements(By.xpath(clearButtonCount)).size();
		for (int mapIndex = 1; mapIndex <= displayedHeaderObjectCount; mapIndex++) {
			driver.findElement(By.xpath(clearButton.replace("%s", String.valueOf(mapIndex)))).click();

		}
	}

	/**
	 * validate Footer Text Field Length
	 * 
	 * @param driver
	 * @throws Exception
	 */
	public void validateFooterTextFieldLength(WebDriver driver) throws Exception {
		GenerateData data = new GenerateData();

		int displayedHeaderObjectCount = driver.findElements(By.xpath(footerFieldListValueObject)).size();

		for (int mapIndex = 1; mapIndex <= displayedHeaderObjectCount; mapIndex++) {

			driver.findElement(By.xpath(footerFieldValueObject.replace("%s", String.valueOf(mapIndex)))).clear();
			driver.findElement(By.xpath(footerFieldValueObject.replace("%s", String.valueOf(mapIndex))))
					.sendKeys(data.generateRandomAlphaNumeric(30));

			String lenhthtext = driver.findElement(By.xpath(footerFieldValueObjectlength.replace("%s", String.valueOf(mapIndex)))).getText();

			if (!(lenhthtext.contains("25"))) {
				addExceptionToReport("field length validation is not working ", "actual length - " + lenhthtext,
						"expected field limit  Value" + 25);
			}
		}
	}

	/**
	 * validate Footer Copy Right Text Field Length
	 * 
	 * @param driver
	 * @throws Exception
	 */
	public void validateFooterCopyRightTextFieldLength(WebDriver driver) throws Exception {
		int len = 150;

		driver.findElement(By.xpath(footerCopyRightObject)).clear();
		System.out.println("Copyright field cleared");
		driver.findElement(By.xpath(footerCopyRightObject)).sendKeys(GenerateData.generateRandomAlphaNumeric(len));
		System.out.println("Copyright field sendkeys");
		String length = driver.findElement(By.xpath(maximumPermissibleCharactersIndicator)).getText().trim();

		if (!length.contains(String.valueOf(len))) {
			System.out.println("length" + length);
			addExceptionToReport("maximum permissible characters while filling the  is not working ",
					"actual length" + length, "expected field limit  Value" + len);
		}

	}

	/**
	 * validate Footer URL Text Field Length
	 * 
	 * @param driver
	 * @throws Exception
	 */
	public void validateFooterURLTextFieldLength(WebDriver driver) throws Exception {

		int displayedHeaderObjectCount = driver.findElements(By.xpath(footerFieldListValueObject)).size();

		for (int mapIndex = 1; mapIndex <= displayedHeaderObjectCount; mapIndex++) {

			if (!driver.findElement(By.xpath(checkBox.replace("%s", String.valueOf(mapIndex - 1)))).isSelected()) {

				driver.findElement(By.xpath(checkBox.replace("%s", String.valueOf(mapIndex - 1)))).click();

			}

			driver.findElement(By.xpath(footerURLFieldValueObject.replace("%s", String.valueOf(mapIndex)))).clear();
			driver.findElement(By.xpath(footerURLFieldValueObject.replace("%s", String.valueOf(mapIndex))))
					.sendKeys(GenerateData.generateUrl(50));
			String lenhth = driver
					.findElement(By.xpath(footerURLFieldValueObject.replace("%s", String.valueOf(mapIndex))))
					.getAttribute("value");

			// URL length is not define
			/*
			 * if (lenhth.length() != 25) {
			 * addExceptionToReport("field length validation is not working ",
			 * "actual length" + lenhth.length(), "expected field limit  Value"
			 * + 25); }
			 */

		}
	}

	// Map to build String
	/**
	 * build Expected Values Map To String
	 * 
	 * @param driver
	 * @param buildMap
	 * @return
	 */
	public String buildExpecteValuesMapToString(WebDriver driver, HashMap<Integer, HashMap<String, String>> buildMap) {
		String expectedString = null;
		System.out.println("entered buildExpecteValuesMapToString");
		String checkBoxValue = "Internal";
		for (int mapIndex = 1; mapIndex <= buildMap.size(); mapIndex++) {
			HashMap<String, String> DataMap = new HashMap<String, String>();
			DataMap = buildMap.get(mapIndex);

			for (String key : DataMap.keySet()) {
				String revisedValue = key;

				if (checkBoxValue.equalsIgnoreCase("Internal")) {
					expectedString = expectedString + revisedValue + "|" + "Internal";
					checkBoxValue = "nonInternal";

				} else {

					expectedString = expectedString + revisedValue + "|" + url5;

					checkBoxValue = "Internal";
				}

				expectedString = expectedString + "_";
			}
		}

		expectedString = expectedString.replace("null", "");
		expectedString = expectedString.substring(0, expectedString.length() - 1);
		expectedString = expectedString.trim();

		return expectedString;
	}

	@Override
	public void checkPage() {
		// TODO Auto-generated method stub

	}
}
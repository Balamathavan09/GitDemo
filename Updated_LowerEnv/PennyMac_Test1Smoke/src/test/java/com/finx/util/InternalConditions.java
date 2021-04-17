package com.finx.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tavant.base.DriverFactory;
import com.tavant.kwutils.CustomStep;
import com.tavant.utils.TwfException;

public class InternalConditions extends CustomStep {
	String openSection="//div[@class='documents-header']/span[contains(.,'Open')]";
	String historySection="//div[@class='documents-header']/span[contains(.,'History')]";
	String conditionTabObj=".//*[@id='conditional-upload']/div[3]/div/div/p-accordion/div/p-accordiontab//p-header[contains(.,\"%s\")]//parent::a";
	String uploadSuccessAlert="//div[@id='conditional-upload']//b";
	String uploadSuccessMessage = "Document uploaded successfully.";
	String displayedQuestionsObj=".//*[@id='conditional-upload']/div[3]/div/div/p-accordion/div/p-accordiontab";
	String uploadFailedAlert="//div[@class='modal-title']";
	String uploadFailedMessage ="Oops, your file(s) didn't upload.";
	static String conditionValid;
	Util util = new Util();
	public void verifyInternalConditions() throws Exception {
		WebDriver driver = DriverFactory.getDriver();
		HashMap<String, String> conditionMap = new HashMap<String, String>();
		String tabNameMistach="";
		String conDitionMistach="",errorMessage="",tabMissing="";
		String data = step.getDataValue("ExpectedInternalConditions");
		int index = 1;
	for (String condition : data.split("##")){
			System.out.println("verifyInternalConditions!!!");
			System.out.println("Conditions>>>"+condition);
			conditionMap.clear();
			String[] dataArray = condition.split("%%");
			String dataUnderAllTab = "";
			for (String testdata : dataArray) {
				conditionMap.put(testdata.split(";")[0], testdata.split(";")[1]);
				dataUnderAllTab = dataUnderAllTab + "&" + testdata.split(";")[1];
			}
			conditionMap.put("All", dataUnderAllTab.substring(1, dataUnderAllTab.length()));
			
			Set<String> tabKeys = conditionMap.keySet();
			
			for (String tabName : tabKeys) {
				String expectedQuestionList = conditionMap.get(tabName);
				List<String> displayedQuestions = new ArrayList<>();
				try{
				driver.findElement(
						By.xpath(".//*[@id='conditional-upload']/div[2]/div/ul/li[contains(.,'" + tabName + "')]/a"))
						.click();				
				}catch(Exception e){
					tabMissing="<---Tab is missing----> "+tabName+" for Applicant"+(index);
				}
				int displayedQuestionCount = driver
						.findElements(
								By.xpath(".//*[@id='conditional-upload']/div[3]/div/div/p-accordion/div/p-accordiontab"))
						.size();
				if (displayedQuestionCount != expectedQuestionList.split("&").length) {
					tabNameMistach="Number of Conditions under " + tabName + " tab is not matching for Applicant-"+(index)+" ";
//							String.valueOf(displayedQuestionCount)+String.valueOf(expectedQuestionList.split(";").length)+"\n";
				}
				
				displayedQuestions = getDisplayedQuestions(driver, tabName, displayedQuestionCount);
				
					String[] expectedQtnArray = expectedQuestionList.split("&");
					for (int expectedQtnIndex = 0; expectedQtnIndex < expectedQtnArray.length; expectedQtnIndex++) {

						if (displayedQuestions.contains(expectedQtnArray[expectedQtnIndex]) == false) {
							conDitionMistach="<-----Expected Condition : " + expectedQtnArray[expectedQtnIndex] + " is not displayed in UI.";
						}
					}
			}			
			
			System.out.println("Index>>>"+index+"LEngth>>>"+data.split("##").length);
//			if (index < data.split("##").length) {
//	               System.out.println("Inside this>>>");
//	               getElementByUsing("Doc_CappTab").click();
//	           }
			index = index + 1;
			errorMessage=errorMessage+tabNameMistach+conDitionMistach+tabMissing+"\n";
			System.out.println("Leng>>"+errorMessage.trim().length());
		}
			
			if(errorMessage.trim().length()>1){
			addExceptionToReport("\n ERROR>>>>",errorMessage, "");
			}
	}

	public List<String> getDisplayedQuestions(WebDriver driver, String tabName, int questionCount) throws Exception {

		List<String> displayedQuestions = new ArrayList<>();
		for (int questionIndex = 1; questionIndex <= questionCount; questionIndex++) {
			List<WebElement> conditions = driver.findElements(
					By.xpath(".//*[@id='conditional-upload']/div[3]/div/div/p-accordion/div/p-accordiontab["
							+ questionIndex + "]/div[1]/a/p-header"));

			for (WebElement cond : conditions) {
				if (displayedQuestions.contains(cond.getText())) {
					addExceptionToReport(
							"Question " + cond.getText() + " is displayed more than once under tab " + tabName, "", "");
				}
				displayedQuestions.add(cond.getText());
			}
		}
		return displayedQuestions;

	}

	public void verifyHistorySectionAfterUpload(WebDriver driver, String conditionName, String fileName)
			throws Exception {
		driver.findElement(By.xpath(historySection)).click();
		System.out.println("Clicked on history section");// debug
        Thread.sleep(2000);
		int countOfConditionInHistory = driver.findElements(By.xpath(conditionTabObj.replace("%s", conditionName)))
				.size();

		if (countOfConditionInHistory < 1) {
			addExceptionToReport("Submitted condition is not reflecting in the History Section", "", conditionName);
		} else if (countOfConditionInHistory > 1) {
			addExceptionToReport("Submitted condition " + conditionName + " is shown multiple times in history section",
					"", "");
		} else {
			System.out.println("Condition " + conditionName + " is displayed under history section"); //debug
		}
		System.out.println("verified condition in history section");// debug
		WebElement conditionTabElement = driver.findElement(By.xpath(conditionTabObj.replace("%s", conditionName)));
		System.out.println("conditionTabElement!!!!!!>>>>"+conditionTabElement);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", conditionTabElement);
		System.out.println("scrolled");
		util.waitTillElementIsDisplayed(driver, conditionTabElement);
		if (!conditionTabElement.getAttribute("aria-expanded").equalsIgnoreCase("true")) {
			Thread.sleep(1000);
//			util.scrollToElement(conditionTabElement);
			conditionTabElement.click();
			System.out.println("Expanded tab in history section");// debug
		}
		
		String uploadedFileName = getElementByUsing("1003_uploadedFilenameInHistory").getText();
		System.out.println("uploadedFileName" + uploadedFileName);// debug

		if (!fileName.contains(uploadedFileName)) {
			addExceptionToReport("File names did not match", uploadedFileName, fileName);
		}
	}
	
	public void verifyOpenSectionAfterUpload(WebDriver driver, String conditionName, String fileName) throws Exception {
		
//		driver.findElement(By.xpath(openSection)).click();
		//uploadDocumentForInternalCondition(conditionName, fileName);
//		uploadInternalConditionDocument(driver,conditionName, fileName);
//		Thread.sleep(2000);
		util.fileUpload(fileName,driver);	
		getElementByUsing("1003_FileUploadSubmit_button").click();
		
//		try{
//		if(driver.findElement(By.xpath("//div[@class='smcx-modal-close']")).isDisplayed()){
//			driver.findElement(By.xpath("//div[@class='smcx-modal-close']")).click();
//		}
//		}catch(Exception e){
		System.out.println("uploadInternalConditionDocument Completed for" + conditionName + " and " + fileName);
//		Thread.sleep(4000);
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(uploadSuccessAlert)));
				
		if (!driver.findElement(By.xpath(uploadSuccessAlert)).getText().equalsIgnoreCase(uploadSuccessMessage)) {
			addExceptionToReport("Upload success message is incorrect",
					driver.findElement(By.xpath(uploadSuccessAlert)).getText(), uploadSuccessMessage);
		}
		System.out.println("Completed uploadSuccessAlert validation"); // debug
		System.out.println("Checking for the condition Name>>>>>"+conditionName);
		conditionValid=conditionName;
//		Thread.sleep(4000);
		if (driver.findElements(By.xpath(conditionTabObj.replace("%s", conditionName))).size() > 0 && !(conditionName.equals("General Document Upload"))) {
			addExceptionToReport(
					"Condition : " + conditionName + " is not removed from Open section after document upload", "", "");
		}
		System.out.println("Completed validation of condition removal"); // debug
		}
//	}
	public void validateConditionsDocumentUpload() throws Exception{
		
		WebDriver driver = DriverFactory.getDriver();
//		driver.findElement(By.xpath(openSection)).click();
		System.out.println("Clicked Open Section"); //debug
		Thread.sleep(2000);
		driver.findElement(By.xpath("//li//a[@title='All']")).click();
		System.out.println("Clicked All Section"); //debug
		int displayedConditionCount = driver.findElements(By.xpath(displayedQuestionsObj)).size();
		System.out.println("displayedConditionCount :"+displayedConditionCount); //debug
		List<String> displayedConditions = getDisplayedQuestions(driver, "All", displayedConditionCount);
		System.out.println("displayedConditions :"+displayedConditions); //debug
		String documentListData = step.getDataValue("DocumentsToUpload");
		System.out.println("fetched Document List data : "+documentListData); //debug
		String [] documentList = documentListData.split(";"); 
		System.out.println("Document!!!!!"+documentList.length);
//		if(documentList.length!=displayedConditionCount){
//			addExceptionToReport("Incorrect datapool values. Number of DocumentsToUpload is not matching the count displayed conditions", "", "");
//		}
		
		for(int i=0;i<documentList.length;i++){
			System.out.println("inside For loop--> i= "+i);//debug
			verifyOpenSectionAfterUpload(driver,displayedConditions.get(i),documentList[i]);
			Thread.sleep(2000);
//			verifyHistorySectionAfterUpload(driver,displayedConditions.get(i),documentList[i]);
			System.out.println("Completed For loop--> i= "+i);//debug
		}
		
	}

	
	
	public void validateInvalidDocumentUpload() throws Exception{
		System.out.println("validateIvalidDocumentUpload");
		WebDriver driver = DriverFactory.getDriver();
//		driver.findElement(By.xpath(openSection)).click();
		System.out.println("Clicked Open Section"); //debug
		Thread.sleep(2000);
		driver.findElement(By.xpath("//li//a[@title='All']")).click();
		System.out.println("Clicked All Section"); //debug
		int displayedConditionCount = driver.findElements(By.xpath(displayedQuestionsObj)).size();
		System.out.println("displayedConditionCount :"+displayedConditionCount); //debug
		if(displayedConditionCount == 0){
			addExceptionToReport("No document upload option is found.", "", "");
		}
		List<String> displayedConditions = getDisplayedQuestions(driver, "All", displayedConditionCount);
		System.out.println("displayedConditions :"+displayedConditions); //debug
		String documentListData = step.getDataValue("DocumentsToUpload");
		System.out.println("fetched Document List data : "+documentListData); //debug
		String [] documentList = documentListData.split(";"); 
		System.out.println("Document!!!!!"+documentList.length);
		
		for(int docIndex=0 ; docIndex < documentList.length; docIndex++ ){
		verifyInvalidDocumentUpload(driver,displayedConditions.get(0),documentList[docIndex]);
		driver.findElement(By.xpath("//button[contains(.,'Upload Again')]")).click();
		Thread.sleep(3000);
		}
	}
	
	
	public void verifyInvalidDocumentUpload(WebDriver driver, String conditionName, String fileName) throws Exception {
		System.out.println("verifyInvalidDocumentUpload");
		WebDriverWait wait = new WebDriverWait(driver, 10);
//		driver.findElement(By.xpath(openSection)).click();
		//uploadDocumentForInternalCondition(conditionName, fileName);
//		uploadInternalConditionDocument(driver,conditionName, fileName);
		Thread.sleep(2000);
		util.fileUpload(fileName,driver);	
		getElementByUsing("1003_FileUploadSubmit_button").click();	
		System.out.println("uploadInternalConditionDocument Completed for " + conditionName + " and " + fileName);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(uploadFailedAlert))));
				
		if (!driver.findElement(By.xpath(uploadFailedAlert)).getText().trim().equalsIgnoreCase(uploadFailedMessage)) {
			addExceptionToReport("Upload success message is incorrect",
					driver.findElement(By.xpath(uploadFailedAlert)).getText(), uploadFailedMessage);
		}
		System.out.println("Completed uploadSuccessAlert validation"); // debug
		System.out.println("Checking for the condition Name>>>>>"+conditionName);
		Thread.sleep(4000);
		System.out.println("Completed validation of condition removal"); // debug
	}
	
	public void verifyClearAllDocuments(WebDriver driver,String documentListData) throws Exception{
        System.out.println("Inside verifyClearAllDocuments");
        String[] documentList = documentListData.split(";");
        String selectedFileList="//div[@class='ui-fileupload-files']";
        for (String fileName : documentList) {
            util.fileUpload(fileName,driver);
        }
        Util.javaScriptClick(driver, "//button[text()='Clear All']");
        getElementByUsing("giftLetterClearAllButton").click();
        if(util.verifyElementIsDisplayed(selectedFileList)){
            addExceptionToReport("Error in clearing uploaded documents !", "", "");
        }       
    }
	
	public void verifyCloseSelectedDoc(WebDriver driver,String fileName) throws Exception{
        System.out.println("Inside verifyCloseSelectedDoc");
        util.fileUpload(fileName,driver);
        String selectedDocXpath=" //div[@class='ui-fileupload-files']//div[@class='ui-fileupload-row' and contains(.,'%f')]";
        if(driver.findElement(By.xpath(selectedDocXpath.replace("%f", fileName.trim()))).isDisplayed()){
            driver.findElement(By.xpath(selectedDocXpath.replace("%f", fileName.trim())+"//button")).click();
            System.out.println("File Closed");
        }else{
            addExceptionToReport("File is not displayed in selected file list!!", fileName, fileName);
        }
        if(!Util.isElementNotPresent(driver, selectedDocXpath.replace("%f", fileName.trim()))){
            addExceptionToReport("Error in closing file from selected file list", fileName, fileName);
        }
    }
	
	
	
	@Override
	public void checkPage() {
		// TODO Auto-generated method stub

	}
}

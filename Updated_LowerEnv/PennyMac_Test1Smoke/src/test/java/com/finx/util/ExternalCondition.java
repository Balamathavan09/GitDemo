package com.finx.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.mail.internet.AddressException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tavant.base.DriverFactory;
import com.tavant.kwutils.CustomStep;
import com.tavant.kwutils.KWVariables;

import net.sf.cglib.transform.impl.AddDelegateTransformer;

public class ExternalCondition extends CustomStep {

	String externalConditionpayLoad = "[{\"name\":\"%conditionName\",\"cndType\":1,\"cndTypeName\":\"%Type\",\"category\":\"\",\"description\":\"\",\"borrowerPair\":\"%pairName\"}]";
	//String externalConditionurl = "http://internal-tavant-encompass-cdp-alb-341137667.us-west-2.elb.amazonaws.com/Encompasswrapper/encompass/api/v2/loans/{guid}/conditions/preliminary/add";
	String externalConditionurl = "http://internal-pennymac-encompass-qa-1662691272.us-west-1.elb.amazonaws.com/encompasswrapper/encompass/api/v2/loans/{guid}/conditions/preliminary/add";
	String tabName = ".//*[@id='conditional-upload']/div[2]/div/ul/li[contains(.,'%tabName')]/a";
	String docEachListobj = ".//*[@id='conditional-upload']/div[3]/div/div/p-accordion/div/p-accordiontab[%index]/div[1]/a/p-header";
	String conditiontObj = ".//*[@id='conditional-upload']/div[3]/div/div/p-accordion/div/p-accordiontab/div[1]/a/p-header[contains(.,'%conditionNmae')]";
	String openSection = "//div[@class='documents-header']/span[contains(.,'Open')]";
	String historySection = "//div[@class='documents-header']/span[contains(.,'History')]";
	String uploadSuccessAlert = "//div[@id='conditional-upload']//b";
	String uploadFailedAlert = "//div[@class='modal-title']";
	String uploadSuccessMessage = "You have fulfilled this condition successfully!!";
	String uploadFailedMessage = "Upload failed";
	String conditionTabObj = ".//*[@id='conditional-upload']/div[3]/div/div/p-accordion/div/p-accordiontab//p-header[contains(.,\"%s\")]//parent::a";
	String displayedQuestionsObj = ".//*[@id='conditional-upload']/div[3]/div/div/p-accordion/div/p-accordiontab";

	Util util = new Util();

	public void addExternalCondition(String loanNumber, String conditionName, String conditionType, String borrowerPair)
			throws Exception {
		String guidNumber = util.getLoanGUIDFromDB(loanNumber);
		String payLoad = externalConditionpayLoad.replace("%conditionName", conditionName)
				.replace("%Type", conditionType).replace("%pairName", borrowerPair);
		WebService.postRequestExternalCondition(externalConditionurl, payLoad, guidNumber);

	}

	public void verifyPreliminaryConditionadded() throws Exception {
		WebDriver driver = DriverFactory.getDriver();
	//String loanNumber = KWVariables.getVariables().get("LoanNumberExternalCondition");
		String loanNumber ="7202228907";// remove later
		System.out.println("loanNumber?" + loanNumber);
		String conditionName = util.getSystemLocalTimeForFileName("yyyy_MM_dd_HH_mm_ss") + "_condition";
		System.out.println("conditionName>>>>" + conditionName);
		addExternalCondition(loanNumber, conditionName, "preliminary", "");
		navigateToExternalConditionLoanNumber(driver, loanNumber);
		getElementByUsing("newRequest").click();
		System.out.println("clicked>>");
		int waitCount = 5;
		boolean found = false;
		// if(driver.findElements(By.xpath(conditiontObj.replace("%conditionNmae",
		// conditionName))).size()>0){
		// found = true;
		// }
		System.out.println("Ist contion is checked???found>" + found);
		while (found == false && waitCount > 0) {
			TimeUnit.MINUTES.sleep(5);
			driver.navigate().refresh();
			System.out.println("wait done for 5 min???waitCount>>>" + waitCount);
			Thread.sleep(10000);
			if (driver.findElements(By.xpath(conditiontObj.replace("%conditionNmae", conditionName))).size() > 0) {
				found = true;
				System.out.println("found inside while ????" + found);
				break;
			}
			waitCount--;
		}
		if (!found) {
			addExceptionToReport("External Condition is not added after waiting 20 min", "", "");
		}

	}

	public void navigateToExternalConditionLoanNumber(WebDriver driver, String LoanNumber) throws Exception {	
		String viewdetailsObj = "(//div[contains(text(),'Loan # %ln')]//ancestor::div[@class[contains(.,'loan-status')]])[2]//a[@title='View Details']";
		getElementByUsing("Dashboard_Submitted_Tab").click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(viewdetailsObj.replace("%ln", LoanNumber))).click();
		Thread.sleep(3000);
		getElementByUsing("FinExp_LoanDetailsRequest").click();

	}

	public List<String> getDisplayedQuestions(WebDriver driver, String tabName, int questionCount) throws Exception {

		List<String> displayedQuestions = new ArrayList<>();
		for (int questionIndex = 1; questionIndex <= questionCount; questionIndex++) {
			List<WebElement> conditions = driver.findElements(
					By.xpath(".//*[@id='conditional-upload']/div[3]/div/div/p-accordion/div/p-accordiontab["
							+ questionIndex + "]/div[1]/a/p-header"));

			for (WebElement cond : conditions) {
				if (displayedQuestions.add(cond.getText()) == false) {
					addExceptionToReport(
							"Question " + cond.getText() + " is displayed more than once under tab " + tabName, "", "");
				}
			}
		}

		return displayedQuestions;

	}

	public void verifyUpload() throws Exception{
		WebDriver driver = DriverFactory.getDriver();
		String loanNumber = KWVariables.getVariables().get("LoanNumberExternalCondition");
		navigateToExternalConditionLoanNumber(driver, loanNumber);
		validateConditionsDocumentUpload();
	}
	public void verifyInvalidUpload() throws Exception{
		WebDriver driver = DriverFactory.getDriver();
		String loanNumber = KWVariables.getVariables().get("LoanNumberExternalCondition");
		navigateToExternalConditionLoanNumber(driver, loanNumber);
		validateIvalidDocumentUpload();
	}
	
	public void verifyRemoveAndClear() throws Exception{
		WebDriver driver = DriverFactory.getDriver();
		String loanNumber = KWVariables.getVariables().get("LoanNumberExternalCondition");
		navigateToExternalConditionLoanNumber(driver, loanNumber);
		driver.findElement(By.xpath("//li//a[@title='All']")).click();
		int displayedConditionCount = driver.findElements(By.xpath(displayedQuestionsObj)).size();
		if(displayedConditionCount == 0){
			addExceptionToReport("No upload option is displayed", "", "");
		}
		String documentListData = step.getDataValue("DocumentsToUpload");
		System.out.println("fetched Document List data : " + documentListData); // debug
		String[] documentList = documentListData.split(";");
		for (int docIndex = 0 ; docIndex < documentList.length ; docIndex++ ){
			util.fileUpload(documentList[docIndex],driver);
		}
		System.out.println("all upload done");
		
		validateRemoveAndClearAll(driver);
	}
	
	
	public void validateRemoveAndClearAll(WebDriver driver) throws Exception {
		String uploadedlistObj = "(//div[@class='ui-fileupload-row'])[%index]//button";
		ArrayList<String> ListOfUploadedDocBefore = getListOfDocumentUploaded(driver);
		int totalUploadedBefore = ListOfUploadedDocBefore.size();
		String documentTobeRemoved = ListOfUploadedDocBefore.get(totalUploadedBefore/2);
		
		System.out.println("documentTobeRemoved??"+documentTobeRemoved);
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", driver.findElement(By.xpath(uploadedlistObj.replace("%index",String.valueOf(totalUploadedBefore/2+1)))));
		
	//	driver.findElement(By.xpath(uploadedlistObj.replace("%index",String.valueOf(totalUploadedBefore/2)))).click();
		Thread.sleep(3000);
		ArrayList<String> ListOfUploadedDocAfter = getListOfDocumentUploaded(driver);
		
		for (String documentsAfterRemoval : ListOfUploadedDocAfter){
			if(documentsAfterRemoval.equals(documentTobeRemoved)){
				System.out.println("documentsAfterRemoval??"+documentsAfterRemoval);
				System.out.println("documentTobeRemoved??"+documentTobeRemoved);
				addExceptionToReport(documentTobeRemoved+ " is not removed after removing", "", "");
			}
		}
		
		getElementByUsing("1003_FileUploadclearAll_button").click();
		if(getListOfDocumentUploaded(driver).size()>0){
			addExceptionToReport("Documents is cleared off when clear off button is clicked", "", "");
		}
		
		
		
		
		
	}
	
	
	
	
	public ArrayList<String> getListOfDocumentUploaded(WebDriver driver) throws Exception{
		String uploadedlistObj= "//div[@class='ui-fileupload-row']";
		String eachDocument = "("+uploadedlistObj+")[%index]";
		ArrayList<String> listofDocuments = new ArrayList<>();
		int listofUploadedDocumentCount = driver.findElements(By.xpath(uploadedlistObj)).size();
		for(int docIndex = 1 ; docIndex <= listofUploadedDocumentCount ; docIndex++){
			String fileName = driver.findElement(By.xpath(eachDocument.replace("%index", String.valueOf(docIndex))+"//div[2]")).getText();
			System.out.println("fileName??"+fileName);
			listofDocuments.add(fileName);
		}
		System.out.println("listofDocuments>>>"+listofDocuments);
		return listofDocuments;
		
	}
	
	
	public void validateConditionsDocumentUpload() throws Exception {
		WebDriver driver = DriverFactory.getDriver();
		// driver.findElement(By.xpath(openSection)).click();
		getElementByUsing("newRequest").click();
		System.out.println("Clicked new Section"); // debug
		Thread.sleep(2000);
		driver.findElement(By.xpath("//li//a[@title='All']")).click();
		System.out.println("Clicked All Section"); // debug
		int displayedConditionCount = driver.findElements(By.xpath(displayedQuestionsObj)).size();
		System.out.println("displayedConditionCount :" + displayedConditionCount); // debug

		if (displayedConditionCount == 0) {
			addExceptionToReport("No upload option is displayed", "", "");
		}
		List<String> displayedConditions = getDisplayedQuestions(driver, "All", displayedConditionCount);
		 System.out.println("displayedConditions :"+displayedConditions);
		String documentListData = step.getDataValue("DocumentsToUpload");
		System.out.println("fetched Document List data : " + documentListData); // debug
		String[] documentList = documentListData.split(";");
		System.out.println("Document!!!!!" + documentList.length);

		for (int docIndex = 0; docIndex < documentList.length; docIndex++) {
			try {
				verifyOpenSectionAfterUpload(driver, displayedConditions.get(docIndex), documentList[docIndex]);
			} catch (Exception e) {
				e.printStackTrace();
				addExceptionToReport("Failed to Upload documnent", "", "");
			}
		}

	}
	
	public void verifyOpenSectionAfterUpload(WebDriver driver, String conditionName, String fileName) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		//driver.findElement(By.xpath(openSection)).click();
		getElementByUsing("newRequest").click();
		Thread.sleep(2000);
		util.fileUpload(fileName,driver);	
		getElementByUsing("1003_FileUploadSubmit_button").click();	
		System.out.println("uploadInternalConditionDocument Completed for " + conditionName + " and " + fileName);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(uploadSuccessAlert))));
				
		if (!driver.findElement(By.xpath(uploadSuccessAlert)).getText().equalsIgnoreCase(uploadSuccessMessage)) {
			addExceptionToReport("Upload success message is incorrect",
					driver.findElement(By.xpath(uploadSuccessAlert)).getText(), uploadSuccessMessage);
		}
		System.out.println("Completed uploadSuccessAlert validation"); // debug
		System.out.println("Checking for the condition Name>>>>>"+conditionName);
		Thread.sleep(4000);
		if (driver.findElements(By.xpath(conditionTabObj.replace("%s", conditionName))).size() > 0 && !(conditionName.equals("Miscellaneous documents"))) {
			addExceptionToReport(
					"Condition : " + conditionName + " is not removed from Open section after document upload", "", "");
		}
		System.out.println("Completed validation of condition removal"); // debug
	}
	
	public void validateIvalidDocumentUpload() throws Exception{
		System.out.println("validateIvalidDocumentUpload");
		WebDriver driver = DriverFactory.getDriver();
		getElementByUsing("newRequest").click();
		System.out.println("Clicked new Section"); //debug
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
	
	@Override
	public void checkPage() {
		// TODO Auto-generated method stub

	}

}

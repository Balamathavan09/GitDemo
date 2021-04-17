package com.finx.util;

import com.tavant.base.DriverFactory;
import com.tavant.kwutils.CustomStep;
import com.tavant.utils.TwfException;

import jxl.read.biff.BiffException;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.finx.util.*;
import com.google.common.util.concurrent.ExecutionError;

public class TenOThreeIncome extends CustomStep {
	
	static WebDriver driver;
	Ten0Three tenothree =  new Ten0Three();	
	Util util = new Util();
	
	String summaryIncome = ".//*[@class='property-addr']/div/div[1]//div[@class='desc-section']/span";
	String incomeAmount    = "(.//*[@class='property-addr']/div/div[1]//div[@class='desc-section']/span)[%s]";
	String incomeNameRow = "(.//*[@class='property-inner']//div[@class='tit row']/div[contains(@class, 'absolute-center')]/div)[%t]"; 
	String totalIncomeSummary = ".//div[contains(@class, 'total-section')]";
	String editIncomeButton = "(.//*[@class='property-inner']//div[@class='tit row']//i[contains(@class,'pencil')])[%s]";  
	String activeButton = "//button[@class='active-btn']";
	String nextinActiveQuestion =".//*[@class='form-container tf-block-%s']/div//dynamic-control";
	String question = "//div[@class='form-container tf-block-%s']//div/label";
	String OkButton = "//div[@class='form-container tf-block-%s']//button[@type='submit']";
	String deleteRow = "(.//*[@class='property-inner']//div[@class='tit row']//div[contains(@class,'icons text-right')]/span[contains(@class,'delete-icon')])[%s]";
	String filterElements = ".//*[@id='filterElements']/li[contains(.,'%s')]";
	String from_date    = "(.//*[@class='property-addr']/div/div[2]//div[@class='desc-section']/span)[%s]";
	String to_date    = "(.//*[@class='property-addr']/div/div[3]//div[@class='desc-section']/span)[%s]";
	  
	  
	/**
	 * Method Name: validateIncomeBusiness
	 * Purpose: To enter Business details in 1003 income section.
	 * @throws Exception
	 */
	
	public void validateIncomeBusiness() throws InterruptedException, TwfException, Exception {
		System.out.println("inside validateIncomeBusiness   . TenOThreeIncome class");
		driver = DriverFactory.getDriver();
		String incomeQuestionAnswer = step.getDataValue("Borrower_1003_Income_Business");
		 
		String filter = "Business";    
		String section = "Bus";
		addMultipleIncome(driver, incomeQuestionAnswer, filter, section); 
 
	} 
	

	/**
	 * Method Name: validateMilitaryPayAndEntitlements
	 *  Purpose: To enter
	 * MilitaryPayAndEntitlements details in 1003 income section.
	 * 
	 * @throws Exception
	 */

	public void validateMilitaryPayAndEntitlements() throws InterruptedException, TwfException, Exception {
		System.out.println("inside validateMilitaryPayAndEntitlements   . TenOThreeIncome class");
		driver = DriverFactory.getDriver();
		String incomeQuestionAnswer = step.getDataValue("1003_Income_MilitaryPayAndEntitlements");
		
		String filter = "Military pay";
		String section = "Mil";
		addMultipleIncome(driver, incomeQuestionAnswer, filter, section); 
		
  
	}  

	/**
	 * Method Name: validateIncomePension 
	 * Purpose: To enter pension details in
	 * 1003 income section.
	 * 
	 * @throws Exception
	 */

	public void validateIncomePension() throws InterruptedException, TwfException, Exception {
		System.out.println("inside validateIncomePension   . TenOThreeIncome class");

		driver = DriverFactory.getDriver();
		String filter = "Pension";
		String section = "Pension";  		
		
		String incomeQuestionAnswer = step.getDataValue("1003_Income_Pension");
		addMultipleIncome(driver, incomeQuestionAnswer, filter, section); 

	}  
	
	/**
	 * Method Name: validateIncomeSocialsecurity Purpose: To enter Social
	 * security details in 1003 income section.
	 * 
	 * @throws Exception
	 */

	public void validateIncomeSocialsecurity() throws InterruptedException, TwfException, Exception {
		System.out.println("inside validateIncomeSocialsecurity   . TenOThreeIncome class");
		driver = DriverFactory.getDriver();  
		String incomeQuestionAnswer = step.getDataValue("1003_Income_Socialsecurity");
		
		
		// addNewIncomeSummaryPage(driver);  
		
		String filter = "Social security";  
		String section = "Social security";
		util.scrollToElement(driver.findElement(By.xpath("//div[@class[contains(.,'form-container tf-block-0')]]")));
		
		addMultipleIncome(driver, incomeQuestionAnswer,filter,section);
		
		
		
		
	//	filterTab(driver, filter, section); 

	} 
	
	public void validateEmployment() throws InterruptedException, TwfException, Exception {
		System.out.println("inside validateEmployment   . TenOThreeIncome class");
		driver = DriverFactory.getDriver();
		String incomeQuestionAnswer = step.getDataValue("CoBorrowerIncomeQuestionAnswer");
		
		String filter = "Employment";
		String section = "Emp";
		addMultipleIncome(driver, incomeQuestionAnswer, filter, section); 
		
  
	} 

	/**
	 * Method Name: validateIncomeOtherIncome Purpose: To enter other details in
	 * 1003 income section.
	 * 
	 * @throws Exception
	 */

	public void validateIncomeCoborrower() throws InterruptedException, TwfException, Exception {
		System.out.println("inside validateIncomeOtherIncome   . TenOThreeIncome class");
		driver = DriverFactory.getDriver(); 
		String filter = "Others";   
		String section = "Child support";
		String incomeQuestionAnswer = step.getDataValue("CoBorrowerIncomeQuestionAnswer");
		
		addMultipleIncome(driver, incomeQuestionAnswer,filter,section);
		
		  
	} 
	
	public void validateIncomeOtherRentIncome() throws InterruptedException, TwfException, Exception {
		System.out.println("inside validateIncomeOtherIncome   . TenOThreeIncome class");
		driver = DriverFactory.getDriver(); 
		String filter = "Others";   
		String section = "Rent";
		String incomeQuestionAnswer = step.getDataValue("1003_Income_Rent");
		
		addMultipleIncome(driver, incomeQuestionAnswer,filter,section);  
	} 
	/**
	 * Method Name: filterTab 
	 * Purpose:  Switching and verify name of each INCOME section at a time of addition and switch back to All tab
	 * 1003 income section.
	 * @param section 
	 * @throws Exception
	 */
	 
	 
	public void filterTab(WebDriver driver, String filter, String section) throws Exception
	{
		
		System.out.println(" inside filterTab   method  ");		
		 Thread.sleep(3000);  
		long total_income = 0;
		String incomeAmount1, incomeRowSection;
		long formatedIncome = 0;		
		System.out.println("filter   " +  filter ); 
 
		driver.findElement(By.xpath(filterElements.replace("%s", String.valueOf(filter)))).click(); 
		Thread.sleep(3000);    
  
		// filterElement
		int numberOfIncome = driver.findElements(By.xpath(summaryIncome)).size();
		System.out.println("filterTab  -- > numberOfIncome   --> " + filter + "  --> " + numberOfIncome);
			   
		 	

	//		for (int income = 1; income <= numberOfIncome; income++) {
				int income =1;
				incomeAmount1 =  driver.findElement(By.xpath(incomeAmount.replace("%s", String.valueOf(income)))).getText();		
				incomeRowSection =  driver.findElement(By.xpath(incomeNameRow.replace("%t", String.valueOf(income)))).getText();				
				formatedIncome  = util.currencyConverter(incomeAmount1);    
				
				System.out.println("incomeRowSection is --> " +  incomeRowSection + " and  respective income  is -->  " + formatedIncome    ); 	
				total_income  = total_income +  formatedIncome;      
		
		if (filter.equalsIgnoreCase("Others"))

		{
			if (!(incomeRowSection.trim().contains("Alimony") || incomeRowSection.trim().contains("Annuity")
					|| incomeRowSection.trim().contains("Child support") || incomeRowSection.trim().contains("Interest or Dividend")
					|| incomeRowSection.trim().contains("Interest") || incomeRowSection.trim().contains("Rent")
					|| incomeRowSection.trim().contains("Retirement"))) {

				addExceptionToReport(" Other  incomeRowSection name is not coming", " ", " ");

			}

		}

		else if (!(incomeRowSection.contains(section))) {

			addExceptionToReport("incomeRowSection name is not coming", " ", " ");
		}

		// }
		
		totalIncomeCalculation();  

		driver.findElement(By.xpath("//a[contains(.,'All')]")).click();

	}  
	
	
	/**
	 * Method Name: addNewIncomeSummaryPage Purpose: add new income from summary
	 * page
	 * 
	 * @throws Exception
	 */

	public void addNewIncomeSummaryPage(WebDriver driver) throws InterruptedException, TwfException, Exception {
		System.out.println("inside addIncomeSummary   . TenOThreeIncome class");

		Thread.sleep(2000);
		getElementByUsing("1003_Income_AddIncomeSummarybutton").click();
		Thread.sleep(3000);
//		getElementByUsing("1003_Income_AddItManuallyButton").click();
//		Thread.sleep(2000);
			
	

	} 

	/**
	 * Method Name: totalIncomeCalculation Purpose: sum up all the income
	 * section's income and compare it with ToatalIncome
	 * 
	 * @throws Exception
	 */

	public void totalIncomeCalculation() throws InterruptedException, TwfException, Exception {
		 
		Thread.sleep(2000);
		System.out.println("inside totalIncomeCalculation   . TenOThreeIncome class");
	  
		
		long total_Income_Footer = 0;	
		long row_Income_Sum  = 0;   
	 	   

		int numberOfIncome = driver.findElements(By.xpath(summaryIncome)).size();

		System.out.println("numberOfIncome" + numberOfIncome);  		 
		String total_income_String;
		String totalIncomeSummary_String;

		for (int income = 1; income <= numberOfIncome; income++) {
			total_income_String = driver.findElement(By.xpath(incomeAmount.replaceAll("%s", String.valueOf(income)))).getText();
			System.out.println("TOTAL>>>>"+total_income_String);
			row_Income_Sum = row_Income_Sum + (util.currencyConverter(total_income_String));
			System.out.println("total_income is " + row_Income_Sum);
		}
		 	
				
		totalIncomeSummary_String = driver.findElement(By.xpath(totalIncomeSummary)).getText(); 		 
		System.out.println("totalIncomeSummary_String --> " +  totalIncomeSummary_String ); 
		
		total_Income_Footer  = util.currencyConverter(totalIncomeSummary_String);  
		System.out.println(" total_Income_Footer " +  total_Income_Footer );   
		
		
		if(!(total_Income_Footer == row_Income_Sum))     
		{
			addExceptionToReport("Income Calculation in InccomeSummary page", " " , "sum of all the rows income should match with Total Income  ");
		}   
	   
		 

	} 

	/**
	 * Method Name: addMultipleIncome Purpose: sum up all the income section's
	 * income and compare it with ToatalIncome
	 * 
	 * @param driver
	 * @param section 
	 * @param filter 
	 * @param pensionQuestion
	 * @throws Exception
	 */

	public void addMultipleIncome(WebDriver driver, String incomeQuestionAnswer, String filter, String section)
			throws InterruptedException, TwfException, Exception {
		System.out.println("inside addMultipleIncome   . TenOThreeIncome class");	
	
//		if(incomeQuestionAnswer.trim().contains("//"))
//		{
//		String[] IncomeQuestion = incomeQuestionAnswer.trim().split("//");
//
//		System.out.println("  Question size  " + IncomeQuestion.length);
//		 
//	
//		for (int index = 0; index < IncomeQuestion.length; index++)
//
//		{
//			Thread.sleep(1000);
//			tenothree.validateQuestionAnswerIn1003(IncomeQuestion[index]);			
//			
//			filterTab(driver, filter, section); 
//			if ( index != IncomeQuestion.length -1 )
//			addNewIncomeSummaryPage(driver);
//			
//		}
//		}
//		else
//		{
//			tenothree.validateQuestionAnswerIn1003(incomeQuestionAnswer.trim());
//		}
		HashMap<String, String> dataMap = new HashMap<>();
		int index = 0;
		for (String qtn : incomeQuestionAnswer.split("//")) {
			dataMap = tenothree.validateQuestionAnswerIn1003(qtn);
			
			index = index + 1;
			if (index < incomeQuestionAnswer.split("//").length) {
				addNewIncomeSummaryPage(driver);
			}

		}
		
		
	}
	 
	

	 
	
	/**
	 * Method Name: incomeSummaryPage  Purpose: Summary page verification
	 * 
	 * 
	 * @throws Exception 
	 */ 
	
  
//	public void incomeSummaryPage() throws InterruptedException, TwfException, Exception {
//		System.out.println("inside incomeSummaryPage   . TenOThreeIncome class");
//		Thread.sleep(2000); 
//		driver = DriverFactory.getDriver(); 		
//		long total_income = 0; 
//		long balance=0;
//		String incomeAmount1;
//		long formatedIncome; 
//		String incomeRowSection;
//		long total_Income_Footer;
//		String aciveButtonName;  
//		int numberOfQuestion; 
//		String income_type; 
//		
//		
//		String businessData = step.getDataValue("1003_Income_Business");
//		String miltaryData = step.getDataValue("1003_Income_MilitaryPayAndEntitlements");
//		String pensionData = step.getDataValue("1003_Income_Pension");
//		String socialData = step.getDataValue("1003_Income_Socialsecurity");
//		String otherData = step.getDataValue("1003_Income_Other");
//		
// 	
// 
//		String totalIncomeSummarytext = driver.findElement(By.xpath(totalIncomeSummary)).getText(); 	
//		totalIncomeSummary= totalIncomeSummarytext.replace("Total Income", "");
//		total_Income_Footer  = util.currencyConverter(totalIncomeSummary);    
//		
//		System.out.println(" **********total_Income_Footer ***********" +  total_Income_Footer ); 			
//		int numberOfIncome = driver.findElements(By.xpath("//*[@class='property-addr']/div/div[1]//div[@class='desc-section']")).size();
//  
//		System.out.println("number  Of  rows ------------" + numberOfIncome);   
//		 
//		
//		// Calculation for Total  Income 
//		for (int income = 1; income <= numberOfIncome; income++) {
//			System.out.println("entered for loop"+income);
//			//((JavascriptExecutor) driver).executeScript("scroll(0,500)");
//			WebElement element = driver.findElement(By.xpath(incomeAmount.replace("%s", String.valueOf(income))));
//			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);
//			System.out.println("###################Scrolled################### ");
//			
//			incomeAmount1 =  driver.findElement(By.xpath(incomeAmount.replace("%s", String.valueOf(income)))).getText();
//			System.out.println("incomeAmount1:----"+incomeAmount1);
//			if(driver.findElement(By.xpath("(//span[contains(.,'"+incomeAmount1+"')]//following::div[contains(.,'End Date')]//span)[1]")).isDisplayed()){
//				System.out.println("Inside incomeAmount1!!!"+balance);
//				formatedIncome=util.currencyConverter(incomeAmount1);
//				balance=balance+formatedIncome;
//				System.out.println("Balance>>>>"+balance);
//			}
////			incomeRowSection =  driver.findElement(By.xpath(incomeNameRow.replace("%t", String.valueOf(income)))).getText();
////			System.out.println("incomeRowSection:---"+incomeRowSection);
//			formatedIncome  = util.currencyConverter(incomeAmount1);
//			System.out.println("***********************");
//			 
//			//System.out.println("incomeRowSection is --> " +  incomeRowSection + " and  respective income  is -->  " + formatedIncome    ); 	
//			total_income  = total_income +  formatedIncome;  	 	
//			
//			System.out.println(  " try 1");
//			
//			/*if (incomeRowSection.trim().contains("Pension")) {
//				System.out.println("entered Pension looop");
//				
//				income_type = "Pension";    
//			
//			    editRowAndVerify(income_type,income, incomeRowSection, pensionData, formatedIncome );
//			    
//			}   
//			else if (incomeRowSection.trim().contains("Social security")) {
//				System.out.println("entered Social security looop");
//
//                 income_type = "Social security";     				
//				System.out.println(income_type   + "  " +  income + "  " + incomeRowSection + " " + formatedIncome );  
//				System.out.println(" socialData  == " + socialData);			
//			
//			    editRowAndVerify(income_type,income, incomeRowSection, socialData, formatedIncome );
//				
//			}
//			else if (incomeRowSection.trim().contains("Alimony")  ||  incomeRowSection.trim().contains("Annuity")  || incomeRowSection.trim().contains("Child support")   || 
//					incomeRowSection.trim().contains("Dividend")  || incomeRowSection.trim().contains("Interest")  || incomeRowSection.trim().contains("Rent") || 
//					incomeRowSection.trim().contains("Retirement")) { 
//				System.out.println("entered Others looop");
//
//                income_type = "Others";           				
//				System.out.println(income_type   + "  " +  income + "  " + incomeRowSection + " " + formatedIncome );  
//				System.out.println(" Others Data  == " + otherData);	 	  	
//			
//			    editRowAndVerify(income_type,income, incomeRowSection, otherData, formatedIncome );
//				
//			}  
//			else if (incomeRowSection.trim().contains("Bus")) {  
//				
//                income_type = "Business or self-employment";      				
//				System.out.println(income_type   + "  " +  income + "  " + incomeRowSection + " " + formatedIncome );  
//				System.out.println(" businessData  == " + businessData);	 		
//			
//			    editRowAndVerify(income_type,income, incomeRowSection, businessData, formatedIncome );
//				   
//			}         
//			  
//			// incomeMap.put(incomeRowSection, formatedIncome); */
//		 
//		}        
//		
//		System.out.println("total_income is " + total_income);	 
//		if(!(total_Income_Footer == total_income))
//		{
//			addExceptionToReport("Income Calculation in InccomeSummary page", " " , "sum of all the rows income should match with Total Income  ");
//		}      
//		
//	}  
//	
	
	
	/**
	 * Method Name: deleteAndVerifyIncome  
	 * Purpose:  for deleting perticular rows from income summary
	 * @throws TwfException 
	 * @throws IOException 
	 * @throws BiffException 
	 * @throws InterruptedException 
	 * 
	 * @throws Exception 
	 */ 
	
	public void deleteAndVerifyIncome() throws TwfException, BiffException, IOException, InterruptedException { 
	 	
		WebDriver driver = DriverFactory.getDriver();
		String rowsToBeDeleted = step.getValue();
		String incomeRowSection; 
		
		//getElementByUsing("1003_Income_backButtonSumarysection").click();   
		Thread.sleep(2000); 
		
		System.out.println("rowsToBeDeleted --> " + rowsToBeDeleted);

		int numberOfIncome = driver.findElements(By.xpath("//*[@class='property-addr']/div/div[1]//div[@class='desc-section']")).size();

		
		// deleting given income row
		for (int incomeRow = 1; incomeRow <= numberOfIncome; incomeRow++) {
			incomeRowSection = driver.findElement(By.xpath(incomeNameRow.replace("%t", String.valueOf(incomeRow))))
					.getText();

			if (incomeRowSection.contains(rowsToBeDeleted)) {
				// deleteRow
				
				driver.findElement(By.xpath(deleteRow.replace("%s", String.valueOf(incomeRow)))).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//div[@class='modal fade yellow-pop in']//button[@id='btn_confirm_modal_Yes']")).click();
				//getElementByUsing("1003_deleteConfirmationModelWindow").click();
			}
		}

		// Confirming for existance for deleted row
		Thread.sleep(2000);
		int numberOfIncomeAfterDelete = driver.findElements(By.xpath(summaryIncome)).size();
		for (int incomeRow = 1; incomeRow <= numberOfIncomeAfterDelete; incomeRow++) {
			incomeRowSection = driver.findElement(By.xpath(incomeNameRow.replace("%t", String.valueOf(incomeRow)))).getText();
			if (incomeRowSection.contains(rowsToBeDeleted)) {
				addExceptionToReport("Deleted row eixst in the summary ",
						rowsToBeDeleted + "  is existed after deletion ",
						rowsToBeDeleted + " should not appear in income summary rows");
			}
   
		}
   
	} 
	 
	
	
	
	//Dont DELETE .. Need for Future Reference
	private void editRowAndVerify(String income_type, int income, String incomeRowSection, String testData , long formatedIncome) throws TwfException, InterruptedException, BiffException, IOException {
		// TODO Auto-generated method stub  
		  
	//	HashMap<String, String> questionAnsMap = new HashMap<String, String>();  
				
		int numberOfQuestion; 
		String[] testQuestion = testData.trim().split("//"); 
		System.out.println(  income_type  + " Question size  is -- " + testQuestion.length);   
 
		for (int index = 0; index < testQuestion.length; index++) {
			
			System.out.println(" testQuestion[index]  -- " + testQuestion[index]);
			System.out.println(" formatedIncome -- " + formatedIncome ); 
			
		//	questionAnsMap = tenothree.buildDataMap(testQuestion[index]); 
			  
			 
			if (testQuestion[index].contains(Long.toString(formatedIncome))) {  
				System.out.println(" try 2");
				System.out.println(testQuestion[index]);  	 	 				
										
				
				// Active button verification
				 
				Thread.sleep(1000);  
				  driver.findElement(By.xpath(editIncomeButton.replace("%s", String.valueOf(income)))).click();
				  System.out.println(" try 3     after click on editIncomeButton ");     
				  Thread.sleep(1000); 
				  System.out.println("income_type:"+income_type);
				if (!(driver.findElement(By.xpath(activeButton)).getText().contains(income_type))) {
					addExceptionToReport("  verify for  My source of income  type ",
							" actuall is" + driver.findElement(By.xpath(activeButton)).getText(),
							" Expected income type is " +  income_type);

				}   
				    
				   
				  String[] Question  = testQuestion[index].split("&&");	
				  numberOfQuestion = Question.length;						  
				  System.out.println( "numberOfQuestion --> " +  numberOfQuestion);						  
				   
				for (int i = 1; i < numberOfQuestion; i++) {

					System.out.println("numberOfQuestion         ==========" + i);     
					Thread.sleep(1000); 
				  
			//		System.out.println(driver.findElement(By.xpath(question.replace("%s", String.valueOf(i)))).getText());
    
					util.scrollToElement(driver.findElement(By.xpath(question.replace("%s", String.valueOf(i)))));   
					// util.scrollToElement(driver.findElement(By.xpath(nextinActiveQuestion.replace("%s",
					// String.valueOf(i)))));
					System.out.println("check3");      
					Thread.sleep(2000); 
 
					// System.out.println(driver.findElement(By.xpath("//input[@placeholder='Monthly earnings']")).getText());
				}  
 
				getElementByUsing("1003_Income_backButtonSumarysection").click();
				System.out.println("check4");  
				// getElementByUsing("1003_ModelWindowYes").click(); 
				
				Thread.sleep(1000);   
			} 
     
		}  
		
	}  
 
	
	public void waitAndClickElement(WebDriver driver,WebElement element) throws TwfException, BiffException, IOException {
		System.out.println(" click and wait");  

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 10);
		 element = wait.until(ExpectedConditions.visibilityOf(element));
	//	element = wait.until(ExpectedConditions.elementToBeClickable(element));   
		executor.executeScript("arguments[0].click();", element);

	}   

	  
	 
	  
	public void incomeImportedFieldCheck() throws TwfException, BiffException, IOException {
        System.out.println(" inside incomeImportedFieldCheck   ----");   
        
        driver = DriverFactory.getDriver();    
        
        if(driver.findElements(By.xpath(".//div[contains(@class,'row login-sec confirmation-btns-sec')]/div//div[contains(.,'Unable to Fetch the data')]")).size() >0)
        {
               addExceptionToReport(" integration is not working" , "Unable to Fetch the data" , "Liability integration should be working");
                  
        }
        
        int numberOfIncome = driver.findElements(By.xpath("(.//*[@class='property-addr']/div/div[1]//div[@class='desc-section']/span)")).size();  
        
        if(numberOfIncome==0)
        {
               addExceptionToReport(" integration is not working" , "Unable to Fetch the data" , "Income not imported successfully");
        }
        
        System.out.println(" inside incomeImportedFieldCheck  2 ");    
        System.out.println("numberOfIncome" + numberOfIncome);        

        for (int income = 1; income <= numberOfIncome; income++) { 
               
               System.out.println( " inside  income  -->   " +  income ) ; 
               
               driver.findElement(By.xpath(incomeAmount.replaceAll("%s", String.valueOf(income)))).isDisplayed(); 
               driver.findElement(By.xpath(from_date.replaceAll("%s", String.valueOf(income)))).isDisplayed();
               driver.findElement(By.xpath(to_date.replaceAll("%s", String.valueOf(income)))).isDisplayed();                    
        } 
 }     


	public void incomeSummaryPage() throws InterruptedException, TwfException, Exception {
		System.out.println("inside incomeSummaryPage   . TenOThreeIncome class");
		Thread.sleep(2000); 
		driver = DriverFactory.getDriver(); 
		String total_income_String;
		long total_income = 0; 
		long balance=0;
		String incomeAmount1;
		long formatedIncome = 0; 
		String incomeRowSection;
		long total_Income_Footer;
		String aciveButtonName;  
		int numberOfQuestion; 
		String income_type; 
		long row_Income_Sum  = 0;  
		
		String businessData = step.getDataValue("1003_Income_Business");
		String miltaryData = step.getDataValue("1003_Income_MilitaryPayAndEntitlements");
		String pensionData = step.getDataValue("1003_Income_Pension");
		String socialData = step.getDataValue("1003_Income_Socialsecurity");
		String otherData = step.getDataValue("1003_Income_Other");
		
 	
        String endofEmp="(//div[@class='property-addr'])[%s]//div[@class='row']/div";
		String totalIncomeSummarytext = driver.findElement(By.xpath(totalIncomeSummary)).getText(); 	
		totalIncomeSummary= totalIncomeSummarytext.replace("Total Income", "");
		total_Income_Footer  = util.currencyConverter(totalIncomeSummary);    
		String ele="(//div[@class='desc-section']//div[contains(.,'End Date of')])[%s]//preceding::span[2]";
		System.out.println(" **********total_Income_Footer ***********" +  total_Income_Footer ); 			
		int numberOfIncome = driver.findElements(By.xpath("//*[@class='property-addr']/div/div[1]//div[@class='desc-section']")).size();
  
		System.out.println("number  Of  rows ------------" + numberOfIncome);   
		 
		
		// Calculation for Total  Income 
		for (int income = 1; income <= numberOfIncome; income++) {
			int i=driver.findElements(By.xpath(endofEmp.replace("%s", String.valueOf(income)))).size();
			if(i<=2){
			String p=driver.findElement(By.xpath(incomeAmount.replace("%s", String.valueOf(income)))).getText();
				System.out.println("Income with end date>>>>"+p);
				total_Income_Footer  = util.currencyConverter(p);    
				total_Income_Footer=total_Income_Footer+formatedIncome;
				System.out.println("Balance of incomeSummaryPage>>>>"+total_Income_Footer);
			}
		}
		}   
		
	  	/**
	 * validateCoapplicantIncomeQuestionAnswer
	 * @throws InterruptedException
	 * @throws TwfException
	 * @throws Exception
	 */
	public void validateCoapplicantIncomeQuestionAnswer() throws InterruptedException, TwfException, Exception {

		Thread.sleep(4000);
		String incomeQuestionAnswer = step.getDataValue("1003_Income_Business_Coapplicant");
		String filter = "Business";
		String section = "Bus";
		addMultipleIncome(driver, incomeQuestionAnswer, filter, section);

	}

	public void enterImportDetailsForIncome() throws Exception{
		WebDriver driver = DriverFactory.getDriver();	
		System.out.println("Inside enterImportDetailsForIncome>>");
		util.click(getElementByUsing("1003_Income_AddIncomeButton"));
		Thread.sleep(1000);
		util.click(getElementByUsing("1003_Assets_AuthorizationCheckBox"));
		util.click(getElementByUsing("1003_Income_ImportMyDataButton"));
		util.waitTimeForSpinner(driver);
//		TimeUnit.MINUTES.sleep(1);
		util.click(getElementByUsing("Income_OkayButton"));
		}
		
	public void enterImportDetailsForIncomeforCoApplicant() throws Exception{
//		iscreditforCoApplicant=true;
		System.out.println("Inside enterImportDetailsForIncomeforCoApplicant>>");
		enterImportDetailsForIncome();
		System.out.println("Came out of Import Income");
	}

	 
	
	@Override
	public void checkPage() {
		// TODO Auto-generated method stub
		
	}

}

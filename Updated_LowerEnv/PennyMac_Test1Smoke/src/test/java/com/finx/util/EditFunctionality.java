package com.finx.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;
import com.tavant.base.DriverFactory;
import com.tavant.kwutils.CustomStep;    
import com.tavant.utils.TwfException; 
import jxl.read.biff.BiffException; 
import org.openqa.selenium.Keys;

public class EditFunctionality extends CustomStep {
	private static final String Object = null;
	String rateQuoteIndex = "//div[@class='product-container']/div//div[@class='add-quote-message']";
	String rateQuoteList = "//div[@class='product-container']/div[1]//div/ul/li";
	String title1 = "//div[@class='product-container']/div[";
	String title2 = "]//div//h1/div[1]";
	String Password = "(.//*[@id='login_password'])[2]";
	String Email = "(.//*[@id='login_userId'])[2]";
	String autoPromtBoxLogin = ".//*[@id='myModal1']/div/div/div/div[2]/div/div[2]/button[1]";
	String autoPopuploginButton = "(//button[contains(.,'Login')])[3]";
	String loginPopUp = ".//*[@id='autop_name']";
	String headerFieldValueObject = "//div[@class='admin-header-container']/div[@class='row']//div[@class='row'][%s]/div[1]//input";
	String Qustion1 = "//div[@class[contains(.,'form-container tf-block-%s active')]]//div[@class='form-group']//form/div[2]//dynamic-control/div/div//label";
	String Qustion = "(//div[@class='form-container tf-block-%s active-section']//div/label)[1]";
	String sub_ans_text = "//div[@class='form-container tf-block-%s active-section']//dynamic-control//input[@placeholder='%placeholder']";
	String sub_ans_dropdown = "//div[@class='form-container tf-block-%s active-section']//dynamic-control/div/div//div/div/div//button[contains(.,'%t')]";
	String sub_ans_dropdown_search = "(//div[@class[contains(.,'form-container tf-block-%s active')]]//div[@class='form-group']//form//location//dropdown-select//ul/div/input)[%t]"; 
	String sub_ans_dropdown_value = ".//*[@class='dropdown open']/ul/li[contains(.,'%val')]";
	String sub_ans_checkbox = "//label[contains(text(),'%labeltext')]//following::input[1]";
	String sub_ans_radiobutton = "//div[@class[contains(.,'form-container tf-block-%s active')]]//dynamic-control//radio-button-selection//button[contains(.,'%radioButtonName')]";
	String Sub_ans_datepicker = ".//div/finx-from-to/div[contains(.,'%datelabel')]//my-date-picker/div/div/input";   	
	String dateOfBirth = "//div[@class[contains(.,'form-container tf-block-%s active')]]//input"; 
	String fieldAtttibute1 = "//div[@class[contains(.,'form-container tf-block-%s active')]]//div[@class='form-group']//form/div[2]//dynamic-control/div/div//div/div/div";
	String fieldAtttibute = "(//div[@class='form-container tf-block-%s active-section']//dynamic-control/div/div//div/div/div)[1]";
	String OkayBUtton1 = "//div[@class[contains(.,'form-container tf-block-%s active')]]//div[@class='form-group']//form//button[@class[contains(.,'submit')]]";
	String radioButtonAns1 = "//div[@class[contains(.,'form-container tf-block-%s active')]]//div[@class='form-group']//form/div[2]//dynamic-control/div/div//div/div/button[contains(.,'%v')]";
	String radioButtonAns = "//div[@class[contains(.,'form-container tf-block-%s active')]]//div[@class='form-group']//dynamic-control/div/div//div/div/button[contains(.,'%v')]";
	String OkayBUtton2 = "//div[@class[contains(.,'form-container tf-block-%s active')]]//div[@class='form-group']//form//button[@class[contains(.,'submit')]]";
	String inputText = "//div[@class[contains(.,'form-container tf-block-%s active')]]//div[@class='form-group']//form//dynamic-control//div/md-input-container//input";
	String fieldValidationError = "//*[@class='error']";
	String rateQuoteXpathTitle = "//div[@class='product-container']/div[%s]//div//h1/div[1]";
	String rateQuoteXpathEleName = "//div[@class='product-container']/div[%s]//div/ul/li[%v]/div[2]";
	String rateQuoteXpathEleValue = "//div[@class='product-container']/div[%s]//div/ul/li[%v]/div[1]";
	//String URL = "http://10.131.148.191:9090/#/productsScreen";
	String addToComcheckBox = "(//input[@name='chkHelper'])[%s]";
	String zipcodeDropDown = "(//div[@class[contains(.,'form-container tf-block-%n active')]]//div[@class='form-group']//form/div[3]//dynamic-control/div/div//dropdown-select/div/div/button)[%s]";
	String zipcodeDropDownSerach = "(//div[@class[contains(.,'form-container tf-block-%n active')]]//div[@class='form-group']//form/div[3]//dynamic-control/div/div//dropdown-select/div/div/ul/div/input)[%s]";
	String zipCodeDropValue = "((//div[@class[contains(.,'form-container tf-block-%n active')]]//div[@class='form-group']//form/div[3]//dynamic-control/div/div//dropdown-select[%s]/div/div/ul/li)[1])";
	String addToCompareButton = "//button[contains(.,'Add to Compare')]";
	//String helpText = ".//*[@class='help-sec tf-block-%s active-section']/div";
	String helpText = ".//div[@class[contains(.,'help-sec tf-block-%s')]]";   
	String createAccountPopUp = "//div[@class='modal fade prequal-modal in']//div[@class='modal-body']//a[contains(.,'Login')]";
  
	String Compare_QuotesButton = "//button[contains(.,'Compare Quotes 3')]";
	String logo = "//div[@class='logo']";
	String postLoginPopup = "(.//*[@id='myModal1']/div/div/div)[2]";  
	String URL = "http://10.209.1.38:9090/#/productsScreen";  
	String postLoginActiveuser_message = "Good to see you back! You already have an account with us, login to save your progress or skip for now.";
	String postLogin_Newuser_message = "Hi there.. You are not registered with us. Would you like to create account with us to continue saving your progress?";
	String postLogin_InactiveUser_message= "Hi there.. Your email is not verified with us. Do you wish to receive an verification link again on your email?"; 
	String postLogin_SendMeLink_message  = "Thank you. We have successfully sent you the account verification link on your email."; 
	String invalidAddressSkipButton="//button[@id='btn_confirm_modal_No' and contains(.,'Skip')]";
	
	//GenerateData2 dummyData = new GenerateData2();
	//Util utill = new Util(); 
	  
	//TenOThreeSectionSummary tenothreesectionsummary = new TenOThreeSectionSummary();

	HashMap<String, String> map = new HashMap<String, String>();
	HashMap<String, String> errorMap = new HashMap<String, String>();
	
	String[] propertSummaryPageQtns = {"I am purchasing a property for:Property value&Property value","The property is located at:Property location","I am purchasing a:Property type","I will use this property as:Property occupancy"};
	String[] getStartedCoapplicantSummaryPageQtns = {"My name is:Name&First name", "I was born on:Date of birth", "My marital status:Marital Status", "My contact details are:Contact Number" };
	String[] getStartedCPSPageQtns = {};
	static String status = "";  
	    int months= 0; 
	  
	
	/**
	 * Method Name: buildDataMap
	 *  Purpose: To create HashMap<String, String> from given String
	 *  
	 * @throws Exception
	 */ 

	public HashMap<String, String> buildDataMap(String testData) {

		// System.out.println("testData" + testData);
		HashMap<String, String> qaMap = new HashMap<String, String>();
		String[] queAns = testData.split("&&");
		for (String qA : queAns) {
			qaMap.put(qA.split(";")[0].trim(), qA.split(";")[1].trim());
			System.out.println("key   == " + qA.split(";")[0].trim());
			System.out.println("value == " + qA.split(";")[1].trim());

		}
		return qaMap;

	}
	
 
	/**
	 * Method Name: buildDataMap
	 *  Purpose: To create HashMap<Integer, String> from given String
	 *   
	 * @throws Exception
	 */ 

	public HashMap<Integer, String> buildDataMaporder(String testData) {
		HashMap<Integer, String> qaMap = new HashMap<Integer, String>();
		String[] queAns = testData.split("&&");
		for (String qA : queAns) {
			Integer a = Integer.parseInt(qA.split(";")[0].trim());
			qaMap.put(a, qA.split(";")[1].trim());

			System.out.println("Order key    == " + a);
			System.out.println("Order value  == " + qA.split(";")[1].trim());
		}
		return qaMap;
	}

	
	/**
	 * Method Name: validateQuestionAnswerIn1003GetStarted
	 *  Purpose: To fill 1003 getstarted question answer  
	 *   
	 * @throws Exception
	 */ 

	
	public void editQuestionToAnswerForIncome() throws InterruptedException, TwfException, Exception {
		System.out.println("editQuestionToAnswer");
		String quesAnsString = step.getDataValue("IncomeQuestionAnswer");

		//String quesAnsString = step.getDataValue("DeclarationQuestionAnswer");

	//String quesAnsString = step.getDataValue("PropertyQuestionAnswer");

		//String quesAnsString = step.getDataValue("question_ans");
		//String fieldMap = step.getDataValue("SummaryFieldMapping");
		// System.out.println("fieldMap************* "+ fieldMap);

		HashMap<String, String> dataMap = validateQuestionAnswerIn1003(quesAnsString);
		System.out.println(" modified Key Value " + dataMap.get("My current address is"));
		dataMap.keySet().forEach(key -> System.out.println(key + "->" + dataMap.get(key)));

		// commented due to defect 
		//tenothreesectionsummary.validateGetStartedSummaryPage(dataMap);

		// Added for CPS co-applicant summary verification
		Boolean cpsdetails = step.getDataValue("Module").toString().contains("CPS");
//
//		if (cpsdetails) {
//			tenothreesectionsummary.validateCoapplicantGetStartedSummaryPage(dataMap, 1, fieldMap);
//		}

	}                
       
	public void editQuestionToAnswerForProperty() throws InterruptedException, TwfException, Exception {
		System.out.println("editQuestionToAnswer");
		String quesAnsString = step.getDataValue("PropertyQuestionAnswer");

		//String quesAnsString = step.getDataValue("DeclarationQuestionAnswer");

	//String quesAnsString = step.getDataValue("PropertyQuestionAnswer");

		//String quesAnsString = step.getDataValue("question_ans");
		//String fieldMap = step.getDataValue("SummaryFieldMapping");
		// System.out.println("fieldMap************* "+ fieldMap);

		HashMap<String, String> dataMap = validateQuestionAnswerIn1003(quesAnsString);
		System.out.println(" modified Key Value " + dataMap.get("My current address is"));
		dataMap.keySet().forEach(key -> System.out.println(key + "->" + dataMap.get(key)));

		// commented due to defect 
		//tenothreesectionsummary.validateGetStartedSummaryPage(dataMap);

		// Added for CPS co-applicant summary verification
		Boolean cpsdetails = step.getDataValue("Module").toString().contains("CPS");
//
//		if (cpsdetails) {
//			tenothreesectionsummary.validateCoapplicantGetStartedSummaryPage(dataMap, 1, fieldMap);
//		}

	}    
	
	
	/**
	 * Method Name: validateQuestionAnswerIn1003CoApplicant
	 *  Purpose:   
	 *   
	 * @throws Exception 
	 */ 	
	
	public void validateQuestionAnswerIn1003CoApplicant() throws InterruptedException, TwfException, Exception {
		System.out.println("****************inside  coapplicant***************************");
		Thread.sleep(5000);
		String quesAnsString = step.getDataValue("coapplicant");
          System.out.println("quesAnsString for coapplicant -> " +  quesAnsString  );		
		
		String fieldMap = step.getDataValue("SummaryFieldMapping");
		// System.out.println("fieldMap************* "+ fieldMap); \

		if (quesAnsString.contains("//")) {
			String[] coapplicantdata = quesAnsString.trim().split("//");
			System.out.println(coapplicantdata.toString());
			System.out.println("coapplicantdata.length: " + coapplicantdata.length);
			for (int index = 0; index < coapplicantdata.length; index++) {
				HashMap<String, String> dataMap = validateQuestionAnswerIn1003(coapplicantdata[index]);
				System.out.println("came out from filling data method");
				// Summary page takes time to get displayed so added sleep
				Thread.sleep(5000);
				//tenothreesectionsummary.validateCoapplicantGetStartedSummaryPage(dataMap, index + 1, fieldMap);
			}
		}
		else
		{
			System.out.println(" Single Co-Applicant");
			validateQuestionAnswerIn1003(quesAnsString);  
		}
	}  
	
	
	
	/**
	 * Method Name: validateQuestionAnswerIn1003 Purpose:
	 * 
	 * @throws Exception
	 */
	public HashMap<String, String> validateQuestionAnswerIn1003(String quesAnsString)
			throws InterruptedException, TwfException, Exception {
		// System.out.println("inside validateQuestionAnswerIn1003 " + quesAnsString); 

		// String quesAnsString = step.getDataValue(column_value);
		// System.out.println("test data string :" + quesAnsString );
		HashMap<String, String> dataMap = new HashMap<String, String>();
		dataMap = buildDataMap(quesAnsString);
		System.out.println("after datamap");
		HashMap<String, String> reBuildDataMap = selectQuestion2AnswerIn1003(dataMap);

		if (status.contains("false")) {
			System.out.println(status);
			addExceptionToReport("Expected Question in not enabled.Hence cannot move forward.", "", status);

		}
		
		// test code
		return reBuildDataMap;
	}
	
	 
	/**
	 * Method Name: selectQuestion2AnswerIn1003 Purpose:
	 * 
	 * @throws Exception
	 */

	public HashMap<String, String> selectQuestion2AnswerIn1003(HashMap<String, String> expectedMap)
			throws InterruptedException, Exception {
		WebDriver driver = DriverFactory.getDriver();

		HashMap<String, String> expectedSelectAttributeMap = new HashMap<String, String>();
		// String status = "";

		int QusIndexlength = expectedMap.size();
		int blankCount = 0;
		String attribute = null;
		System.out.println("QusIndexlength size is  " + QusIndexlength);
		for (int qusIndex = 0; qusIndex < QusIndexlength; qusIndex++) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, 100);
				wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(fieldAtttibute.replace("%s", String.valueOf(qusIndex))))));

				attribute = driver.findElement(By.xpath(fieldAtttibute.replace("%s", String.valueOf(qusIndex)))).getAttribute("data-type").trim();

				String qustion = driver.findElement(By.xpath(Qustion.replace("%s", String.valueOf(qusIndex)))).getText().trim();
				expectedSelectAttributeMap = selectAttribute(driver, attribute, qusIndex, expectedMap, qustion);
				
			} catch (Exception e) {
				System.out.println(" Caught Exception " + e);
				/*
				 * System.out.println(" blankCount  -- " + blankCount);
				 * blankCount++; expectedSelectAttributeMap
				 * =selectAttribute(driver, attribute, qusIndex, expectedMap,
				 * "blank" + blankCount);
				 */
				status = "false";

				System.out.println(" e.printStackTrace();");
				e.printStackTrace();
				addExceptionToReport(" Expected question is not visible hence can not move forward    ", " ", " ");

			}
			status = "true";
		}

		System.out.println("status = " + status);
		status = "true";
		return expectedSelectAttributeMap;

	}

	
	/**
	 * Method Name: validationCheckIn1003
	 *  Purpose:   
	 *      
	 * @throws Exception 
	 */ 
	
	
	public void validationCheckIn1003() throws InterruptedException, TwfException, Exception {

		String quesAnsString = step.getDataValue("question_ans");
		// System.out.println("inside validationCheckIn1003 " +quesAnsString);
		// String quesAnsString = step.getDataValue(column_value);

		HashMap<String, String> dataMap = new HashMap<String, String>();
		dataMap = buildDataMap(quesAnsString);
		System.out.println("after datamap");
		String str = validateNegativeFlowTenOThree(dataMap);

		// System.out.println("str value is " + str);

		String expectedErrorMessage = "Invalid Input";
		String requiredFieldValidation = "Required Field";
		if (str.contains("false")) {

			if (str.split(";")[1].trim().equalsIgnoreCase(expectedErrorMessage) == false) {
				addExceptionToReport("Expected Error Message is not   displayed", str.split(";")[1].trim(),
						expectedErrorMessage);
			} else if (str.split(";")[1].trim().equalsIgnoreCase(requiredFieldValidation) == true) {
				addExceptionToReport("required Field Validation is not dispalyed  ", str.split(";")[1].trim(),
						requiredFieldValidation);
					
			}

		}
	}

	
	/**
	 * Method Name: validateNegativeFlowTenOThree
	 *  Purpose:   To cheeck for field validation errors
	 *       
	 * @throws Exception 
	 */ 
	
	
	
	public String validateNegativeFlowTenOThree(HashMap<String, String> expectedMap)
			throws InterruptedException, Exception {

		System.out.println(" inside validateNegativeFlowTenOThree   ");
		WebDriver driver = DriverFactory.getDriver();
		String status = "";

		int QusIndexlength = expectedMap.size();
		int blankCount = 0;
		System.out.println("QusIndexlength size is  " + QusIndexlength);
		for (int qusIndex = 0; qusIndex < QusIndexlength; qusIndex++) {

			if (driver.findElements(By.xpath(Qustion.replace("%s", String.valueOf(qusIndex)))).size() > 0) {

				String attribute = driver.findElement(By.xpath(fieldAtttibute.replace("%s", String.valueOf(qusIndex))))
						.getAttribute("data-type").trim();

				try {
					String qustion = driver.findElement(By.xpath(Qustion.replace("%s", String.valueOf(qusIndex))))
							.getText().trim();
					selectAttribute(driver, attribute, qusIndex, expectedMap, qustion);
				} catch (Exception e)

				{
					System.out.println(" Caught Exception " + e);
					addExceptionToReport("Test Data Error",	" Given  Test Data is not correct for attribute  " + attribute + " Exception is --> " + e,
							"Test Data should be  given properly ");

					/*
					 * System.out.println(" blankCount  -- " + blankCount);
					 * blankCount++; selectAttribute(driver, attribute,
					 * qusIndex, expectedMap,"blank"+blankCount);
					 */
				}

				status = "true";			}

			else {
				System.out.println(" entered else loop .............");

				if (driver.findElements(By.xpath(fieldValidationError)).size() > 0) {
					status = "false;" + driver.findElement(By.xpath(".//*[@class='error']")).getText().trim();
					System.out.println(driver.findElement(By.xpath(".//*[@class='error']")).getText());
					System.out.println("status is ----------------------------> " + status);
				}

				break;

			}

		}

		return status;

	}
	 

	public String verifyQuestionOrdering(HashMap<String, String> expectedMap, HashMap<Integer, String> SequenceMap,
			HashMap<String, String> HelpTextMap) throws InterruptedException, Exception {
		WebDriver driver = DriverFactory.getDriver();
		String status = "";

		int QusIndexlength = expectedMap.size();
		int sequenceMapLength = SequenceMap.size();
		int HelpTextMapLength = HelpTextMap.size();

		for (int qusIndex = 0; qusIndex < sequenceMapLength; qusIndex++) {

			System.out.println("------------------------------");
			System.out.println("QusIndex1  is --->" + qusIndex);
			String attribute = driver.findElement(By.xpath(fieldAtttibute.replace("%s", String.valueOf(qusIndex))))
					.getAttribute("data-type");

			String question = driver.findElement(By.xpath(Qustion.replace("%s", String.valueOf(qusIndex)))).getText()
					.trim();
			String actualHelpText = driver.findElement(By.xpath(helpText.replace("%s", String.valueOf(qusIndex))))
					.getText().trim();
			String expectedHelpText = HelpTextMap.get(question).trim();

			System.out.println("actualHelpText  is   --> " + actualHelpText);
			System.out.println("expectedHelpText  is --> " + expectedHelpText);

			if (!(actualHelpText.contains(expectedHelpText))) {
				addExceptionToReport("Verifiation of HELP Text for question " + question + " failed.", actualHelpText,
						expectedHelpText);
			}

			String expectedValue = SequenceMap.get(qusIndex);
			System.out.println("question  is   --> " + question);
			System.out.println("expected Question is --> " + expectedValue);

			if (!(expectedValue.contains(question))) {
				addExceptionToReport("Expected Question is not displayed in index " + qusIndex, expectedValue,
						question);
			}

			selectAttribute(driver, attribute, qusIndex, expectedMap, question);
			status = "true";
		}
		return status;
	}

	/**
	 * Method Name: checkDependent
	 *  Purpose: Add number of dependents for the Enter dependent(s) age question
	 * @param driver 
	 *  
	 * @throws Exception  
	 */  
  
	private void addDependents(WebDriver driver, String qustion, String dependent_ans) throws BiffException, IOException, TwfException {
		System.out.println("inside checkDependent");
		if (dependent_ans.contains(":")) {
			String[] dependent_Age = dependent_ans.split(":");
			int dependent_Age_length = dependent_Age.length;
			while (dependent_Age_length > 1) {
				getElementByUsing("FinExp_100_AddDependent_Button").click();

				dependent_Age_length--;
			}
			
			/* if(dependent_Age_length>2)
			 {
				// 
				 driver.findElement(By.xpath(dependentDeleteIcon.replace("%s", String.valueOf(dependent_Age_length-1)))).click();
			 }*/
			  
		}  		  
	
	}  

	
	/**
	 * Method Name: getMonths
	 *  Purpose:  getting months detsils from given test data.
	 *  Test Data - 	My current address is;ta%Street address line 1_abc koramangala: ta%Street address line 2_abc Koramangala:
		dd%State_Arizona:dd%City_Ajo:ta%Zipcode_12345:ta%Years_2:ta%Months_3:CB%This is my mailing address_This is my mailing address
		
	 * @param driver 
	 *  
	 * @throws Exception   
	 */ 	
	
	private int getMonths(WebDriver driver, String qustion, String ans)
    {
            int years = Integer.parseInt((ans.substring(ans.lastIndexOf("Years_") + 6, ans.lastIndexOf(":ta%Months (Optional)")).trim()));
            
            
            int months=0;
            if(ans.contains("CB%"))
            { 
                    months = Integer.parseInt((ans.substring(ans.lastIndexOf("(Optional)_") + 11, ans.lastIndexOf(":CB%")).trim()));       
                      
            } 
            else{   
                    months = Integer.parseInt(ans.substring(ans.lastIndexOf("(Optional)_")  + 11).trim()); 
            }
                            
            System.out.println(" months   are " + months  );
            
            return (years*12)+months; 
    }   

	
	
	
	/**
	 * Method Name: selectAttribute
	 *  Purpose:  fill questions and answer based on there attribute type
	* @param driver 
	 *  
	 * @throws Exception   
	 */ 
	
	
	public HashMap<String, String> selectAttribute(WebDriver driver, String attribute, int qusIndex, HashMap<String, String> expectedMap,
			String qustion) throws Exception {

		System.out.println("attribute is --> " +  attribute +"  -- qustion is --> " +  qustion + " index is -" +  qusIndex ); 
		System.out.println("ans --" +  expectedMap.get(qustion));  
		String State = null,City = null;  
		// int months = 0;    
		String address_Ans = null;   
 		 try {
		if (qustion.contains("Enter dependent(s) age")) {
			   addDependents(driver,qustion,expectedMap.get(qustion)); 
			}  
	    if(qustion.contains("My current address is"))   
			{		    	
	    	//System.out.println(" My current address is " + months);
		 	 months = getMonths(driver, qustion, expectedMap.get(qustion));			
		     //System.out.println( "months are " + months);
			}   
			        		
		if (qustion.contains("My previous address was")) {
			//System.out.println("months before previous address are " + months);
			months = months + getMonths(driver, qustion, expectedMap.get(qustion));
			//System.out.println("months after previous address was   are " + months);

		}   
 		 }
 		 catch (Exception e) {

        e.printStackTrace(); 
        System.out.println( "exception is -> " + e );
		}
		 
		if (attribute.equalsIgnoreCase("radioBtn")) { 

			String val = String.valueOf((String) expectedMap.get(qustion));				
			
			System.out.println(" inside " +  attribute  );      			 
			String  radioans  = expectedMap.get(qustion); 			 
			System.out.println("radioans " +  radioans );  			
			String[] sub_fields = radioans.split(":");  
 
		
			//System.out.println("sub_fields.length ----   "  + sub_fields.length);  
			if (sub_fields.length == 1) {

				if (!(driver.findElements(By.xpath(radioButtonAns.replace("%s", String.valueOf(qusIndex)).replace("%v", val)))
						.size() > 0)) {
					addExceptionToReport("Given Option is not available for " + qustion, val, " ");
				} 

				boolean radiobuttonstatus = driver.findElement(By.xpath(radioButtonAns.replace("%s", String.valueOf(qusIndex)).replace("%v", val))).isSelected();
				System.out.println("radiobuttonstatus-------------> val "+radiobuttonstatus+"----------- "+val);
				driver.findElement(By.xpath(radioButtonAns.replace("%s", String.valueOf(qusIndex)).replace("%v", val))).click();
			}    
			else 
			{
				for (int sub_field_index =0 ; sub_field_index <  sub_fields.length ; sub_field_index++)
				{ 
					 
			//		System.out.println( " sub_field_index  "+ sub_fields[sub_field_index]); 
					String[] field_type = (sub_fields[sub_field_index]).trim().split("%"); 
			//	    System.out.println("field_type " +  field_type[0] +  " and " +  field_type[1]);
					       
					if((field_type[0].trim()).equalsIgnoreCase("ta"))    
					{ 
						
					//	System.out.println( " Inside TA ... ");
					//	System.out.println("inside ta " + field_type[1]  );   
						String[] placeholder_value = (field_type[1].trim()).split("_");   
						String placeholder = placeholder_value[0].trim();	 		
						String value = placeholder_value[1]; 
						driver.findElement(By.xpath(sub_ans_text.replace("%s", String.valueOf(qusIndex)).replace("%placeholder", String.valueOf(placeholder)))).click();
						String textvalue = driver.findElement(By.xpath(sub_ans_text.replace("%s", String.valueOf(qusIndex)).replace("%placeholder", String.valueOf(placeholder)))).getText(); 
						System.out.println("textvalue: ----------->    placeholder: "+textvalue+ "  ----------->"+placeholder);
						driver.findElement(By.xpath(sub_ans_text.replace("%s", String.valueOf(qusIndex)).replace("%placeholder", String.valueOf(placeholder)))).sendKeys(Keys.ARROW_UP); 
						
						}
					else if((field_type[0].trim()).equalsIgnoreCase("RB"))
					{
						
					
				 	//System.out.println("inside RB............ " + field_type[1]  );   
				
						
						if (!(driver.findElements(By.xpath(radioButtonAns.replace("%s", String.valueOf(qusIndex)).replace("%v", field_type[1]))).size() > 0)) {
							addExceptionToReport("Given Option is not available for " + qustion, val, " ");
						}

						System.out.println(" check 1 "  + qusIndex   + "   " + field_type[1]);
						boolean RBstatus = driver.findElement(By.xpath(radioButtonAns.replace("%s", String.valueOf(qusIndex)).replace("%v", field_type[1]))).isSelected();
						System.out.println("RBstatus: ---------------->field_type[1] "+RBstatus  +" "+field_type[1]);
					  
					}     
 
				}

				driver.findElement(By.xpath(OkayBUtton1.replace("%s", String.valueOf(qusIndex)))).click();

			} 
		}
		
		
		else if (attribute.equalsIgnoreCase("currency")) {

			String currencyans = expectedMap.get(qustion); 
			//System.out.println("currencyans " + currencyans);
			String[] sub_fields = currencyans.split(":");

			//System.out.println("sub_fields.length ----   " + sub_fields.length);
			
			if (sub_fields.length == 1) {				
				
				//driver.findElement(By.xpath(inputText.replace("%s", String.valueOf(qusIndex)))).clear();  
				String currencyvalue = driver.findElement(By.xpath(inputText.replace("%s", String.valueOf(qusIndex)))).getAttribute("value"); 
				System.out.println("currencyvalue "+currencyvalue);
				
				// driver.findElement(By.xpath(OkayBUtton1.replace("%s", String.valueOf(qusIndex)))).click();
			}  
			else 
			{ 			
			for (int sub_field_index =0 ; sub_field_index <  sub_fields.length-1 ; sub_field_index++)
			{  
				 
		//		System.out.println( " sub_field_index  "+ sub_fields[sub_field_index]); 
				String[] field_type = (sub_fields[sub_field_index]).trim().split("%"); 
		//	    System.out.println("field_type " +  field_type[0] +  " and " +  field_type[1]);
				       
				if((field_type[0].trim()).equalsIgnoreCase("ta"))    
				{ 
					
				//	System.out.println( " Inside TA ... ");
				//	System.out.println("inside ta " + field_type[1]  );   
					String[] placeholder_value = (field_type[1].trim()).split("_");  
					String placeholder = placeholder_value[0].trim();	 		
					String value = placeholder_value[1]; 
					driver.findElement(By.xpath(sub_ans_text.replace("%s", String.valueOf(qusIndex)).replace("%placeholder", String.valueOf(placeholder)))).click();
					//driver.findElement(By.xpath(sub_ans_text.replace("%s", String.valueOf(qusIndex)).replace("%placeholder", String.valueOf(placeholder)))).clear();
					System.out.println(driver.findElement(By.xpath(sub_ans_text.replace("%s", String.valueOf(qusIndex)).replace("%placeholder", String.valueOf(placeholder)))).getText()); 
					driver.findElement(By.xpath(sub_ans_text.replace("%s", String.valueOf(qusIndex)).replace("%placeholder", String.valueOf(placeholder)))).sendKeys(Keys.ARROW_UP); 
					
					}   

			} 
			}  
			//
			
		/*	driver.findElement(By.xpath(inputText.replace("%s", String.valueOf(qusIndex)))).clear();
			driver.findElement(By.xpath(inputText.replace("%s", String.valueOf(qusIndex)))).sendKeys(expectedMap.get(qustion));
			driver.findElement(By.xpath(inputText.replace("%s", String.valueOf(qusIndex)))).sendKeys(Keys.ARROW_UP);*/

			// Summary wait
			Thread.sleep(3000);
			driver.findElement(By.xpath(OkayBUtton1.replace("%s", String.valueOf(qusIndex)))).click();

			if (qustion.contains("I can put a down payment of")) {
				System.out.println(" inside  down payment condition     ");
				driver.findElement(By.xpath(OkayBUtton1.replace("%s", String.valueOf(qusIndex)))).click();
			}
		}
  
		else if ((attribute.equalsIgnoreCase("checkbox")) || (attribute.equalsIgnoreCase("text")) || (attribute.equalsIgnoreCase("select"))  || (attribute.equalsIgnoreCase("phone")) || (attribute.equalsIgnoreCase("ssnField"))|| (attribute.equalsIgnoreCase("datepicker"))|| (attribute.equalsIgnoreCase("fromto"))|| (attribute.equalsIgnoreCase("location")))  {
			
			//System.out.println(" inside " +  attribute  );        
			 
			String  textans  = expectedMap.get(qustion); 
			if(attribute.equalsIgnoreCase("text")&&qustion.equalsIgnoreCase("Provide spouse details")){
				//System.out.println("eneterd");
				driver.switchTo().activeElement();
				driver.findElement(By.xpath("(//button[@id='btn_confirm_modal_Yes'])[2]")).click();
				Thread.sleep(5000);
				driver.switchTo().defaultContent();
			
			} 
			
			 
			//System.out.println("textans " +  textans );  
			 
			String[] sub_fields = textans.split(":");  	
			 
		//	System.out.println("sub_fields length " +  sub_fields.length);     
			int zipindex=0;  
			for (int sub_field_index =0 ; sub_field_index <  sub_fields.length ; sub_field_index++)
			{ 
				 
			//	System.out.println( " sub_field_index  "+ sub_fields[sub_field_index]); 
				String[] field_type = (sub_fields[sub_field_index]).trim().split("%"); 
			    //System.out.println("field_type " +  field_type[0] +  " and " +  field_type[1]);
				         
				if((field_type[0].trim()).equalsIgnoreCase("ta"))    
				{ 
				//	System.out.println("inside ta " + field_type[1]  );   
					String[] placeholder_value = (field_type[1].trim()).split("_");  
					String placeholder = placeholder_value[0].trim();	 		
					String value = placeholder_value[1]; 
					 
					if (placeholder.equalsIgnoreCase("Zipcode")) {						
						 
						System.out.println(" inside Zip ");
						//driver.findElement(By.xpath(sub_ans_text.replace("%s", String.valueOf(qusIndex)).replace("%placeholder", String.valueOf(placeholder)))).clear();
						System.out.println(driver.findElement(By.xpath(sub_ans_text.replace("%s", String.valueOf(qusIndex)).replace("%placeholder", String.valueOf(placeholder)))).getAttribute("value"));
					//	utill.waitForElements(driver.findElement(By.xpath(".//*[@class='loader-holder']/p")));
  
						// driver.findElement(By.xpath(sub_ans_text.replace("%s",
						// String.valueOf(qusIndex)).replace("%placeholder",
						// String.valueOf(placeholder)))).sendKeys(Keys.TAB);
						Thread.sleep(10000);   
  
						State = driver.findElement(By.xpath("(.//*[@class='zipcode-information']//button)[1]")).getAttribute("value");
						City = driver.findElement(By.xpath("(.//*[@class='zipcode-information']//button)[2]")).getAttribute("value");
 System.out.println("State:  City "+State +" "+City);
					
						if (qustion.contains("My current address is")) {  
							 
							address_Ans =expectedMap.get("My current address is"); 
							
							//System.out.println("address_Ans are before state and city " + address_Ans ); 
							expectedMap.put("My current address is", "ta%State_" + State + ":ta%City_" + City);
						} else if (qustion.contains("The property is located at")) {
							expectedMap.put("The property is located at", "ta%State_" + State + ":ta%City_" + City);
						}     
         
					} else {              						 
						
						if (qustion.contains("My current address is") && (!(placeholder.equalsIgnoreCase("Years"))) && (!(placeholder.equalsIgnoreCase("Months")))) {       

							address_Ans =expectedMap.get("My current address is");
 
							System.out.println("address_Ans for My current address is  if  placeholder is not  zipcode  " + address_Ans); 
						}
						else if(qustion.contains("My mailing address is") )
						{ 
							address_Ans =expectedMap.get("My mailing address is");
							System.out.println("address_Ans for My current address is   " + address_Ans); 
						}
						driver.findElement(By.xpath(sub_ans_text.replace("%s", String.valueOf(qusIndex)).replace("%placeholder", String.valueOf(placeholder)))).click();
						//driver.findElement(By.xpath(sub_ans_text.replace("%s", String.valueOf(qusIndex)).replace("%placeholder", String.valueOf(placeholder)))).clear();
						System.out.println("test address"+driver.findElement(By.xpath(sub_ans_text.replace("%s", String.valueOf(qusIndex)).replace("%placeholder", String.valueOf(placeholder)))).getAttribute("value"));
						driver.findElement(By.xpath(sub_ans_text.replace("%s", String.valueOf(qusIndex)).replace("%placeholder", String.valueOf(placeholder)))).sendKeys(Keys.ARROW_UP); 
								    
					}   
					
					if(placeholder.equalsIgnoreCase("Account with")){
						System.out.println("placeholder  is  Account with"); 
						System.out.println(driver.findElement(By.xpath(sub_ans_text.replace("%s", String.valueOf(qusIndex)).replace("%placeholder", String.valueOf(placeholder)))).getText());   
						
						 
						
					}
					
					
  
				}  
				else if((field_type[0].trim()).equalsIgnoreCase("dd"))    
				{
					System.out.println("inside dd -- " + field_type[1]  ); 
					String[] placeholder_value = field_type[1].split("_"); 
					String placeholder = placeholder_value[0];	 		
					String value = placeholder_value[1];     					
					driver.findElement(By.xpath(sub_ans_dropdown.replace("%s", String.valueOf(qusIndex)).replace("%t", String.valueOf(placeholder)))).click();
					zipindex++;    				  
					driver.findElement(By.xpath(sub_ans_dropdown_search.replace("%s", String.valueOf(qusIndex)).replace("%t", String.valueOf(zipindex)))).sendKeys(value);  
					// driver.findElement(By.xpath(sub_ans_dropdown.replace("%s", String.valueOf(qusIndex)).replace("%t", String.valueOf(placeholder)))).sendKeys(value);   
					
				//	clickOnElementWhenElementIsReady(driver.findElement(By.xpath(sub_ans_dropdown.replace("%s", String.valueOf(qusIndex)).replace("%t", String.valueOf(placeholder)))));

				//	driver.findElement(By.xpath(sub_ans_dropdown.replace("%s", String.valueOf(qusIndex)).replace("%t", String.valueOf(placeholder)))).click();
					clickOnElementWhenElementIsReady(driver.findElement(By.xpath(sub_ans_dropdown_value.replace("%val", String.valueOf(value)))));
					
				//	driver.findElement(By.xpath(sub_ans_dropdown_value.replace("%val", String.valueOf(value)))).click();  
			       
 
					} 
				else if((field_type[0].trim()).equalsIgnoreCase("cb"))       
				{					
				//	System.out.println("inside cc -- " + field_type[1]  ); 
					String[] placeholder_value = field_type[1].split("_"); 
					String placeholder = placeholder_value[0];	 		
					String value = placeholder_value[1].trim(); 
					
					System.out.println("placeholder " + placeholder + "value - " +  value ); 
					driver.findElement(By.xpath(sub_ans_checkbox.replace("%labeltext", placeholder))).click();
				//	driver.findElement(By.xpath(sub_ans_checkbox.replace("%labeltext", placeholder))).click();
			
					} 
				else if((field_type[0].trim()).equalsIgnoreCase("RB"))     
				{ 
					  
					// System.out.println("inside RB  -- " + field_type[1]  );  
                    String[] placeholder_value = field_type[1].split("_"); 
                    String placeholder = placeholder_value[0];                      
                    String value = placeholder_value[1].trim();  
                    driver.findElement(By.xpath(sub_ans_radiobutton.replace("%s", String.valueOf(qusIndex)).replace("%radioButtonName", value))).click();
                    Thread.sleep(1000);              

					} 
				else if((field_type[0].trim()).equalsIgnoreCase("DatePicker")|| (field_type[0].trim()).equalsIgnoreCase("fromto") )      
				{  
					 
					System.out.println("inside DatePicker  -- " + field_type[1]  );  
					String[] placeholder_value = field_type[1].split("_"); 
					String placeholder = placeholder_value[0];	 		
					String value = placeholder_value[1].trim(); 
					
					System.out.println("placeholder " + placeholder + "value - " +  value ); 			
					System.out.println("Date"+driver.findElement(By.xpath(Sub_ans_datepicker.replace("%datelabel", placeholder))).getAttribute("value")); 
					driver.findElement(By.xpath(Sub_ans_datepicker.replace("%datelabel", placeholder))).sendKeys(Keys.ARROW_UP); 
								 
					} 
							
				   
			}  

			// driver.findElement(By.xpath(inputText.replace("%s", String.valueOf(qusIndex)))).sendKeys(expectedMap.get(qustion));
			
			// Summary wait
			driver.findElement(By.xpath(OkayBUtton1.replace("%s", String.valueOf(qusIndex)))).click();
			Thread.sleep(4000); 
			
	/*		try {
			
			if (qustion.contains("My current address is") || qustion.contains("My mailing address is") || (qustion.contains("My previous address was"))) {  
				
				System.out.println("qustion before validate address " + qustion);
				System.out.println("answer before validate address " + expectedMap.get(qustion)); 
				System.out.println("address_Ans before validate address " + address_Ans);    
				
				validateAddress(driver, qustion, qusIndex, expectedMap.get(qustion), months, address_Ans);		   
			}  
			   
			}
			
			catch (Exception e) {
			 
				e.printStackTrace(); 
				System.out.println(" error is --> " + e ); 
			}*/

		} else if(attribute.equalsIgnoreCase("dateFormat"))   
		{			
			 System.out.println("inside datepicker" + expectedMap.get(qustion) );  
		//	 Thread.sleep(1000);   
			 // System.out.println("inside datepicker" + expectedMap.get(qustion) );  
			 driver.findElement(By.xpath(dateOfBirth.replace("%s", String.valueOf(qusIndex)))).click();
			 //driver.findElement(By.xpath(dateOfBirth.replace("%s", String.valueOf(qusIndex)))).clear();  
			 System.out.println("inside data format"+driver.findElement(By.xpath(dateOfBirth.replace("%s", String.valueOf(qusIndex)))).getText());		 
			 driver.findElement(By.xpath(dateOfBirth.replace("%s", String.valueOf(qusIndex)))).sendKeys(Keys.ARROW_UP);    
			 
			 //getElementByUsing("1003_GetStarted_datepicker_text").sendKeys(expectedMap.get(qustion));
			 Thread.sleep(3000); 
			 driver.findElement(By.xpath(OkayBUtton1.replace("%s", String.valueOf(qusIndex)))).click();
			  
			      
		} 	 
			 
		
		else if (attribute.equalsIgnoreCase("zipcode")) {			
			String ZipcodeDropDown1 = zipcodeDropDown.replace("%n", String.valueOf(qusIndex));
			String zipcodeDropDownSerach1 = zipcodeDropDownSerach.replace("%n", String.valueOf(qusIndex));
			String zipCodeDropValue1 = zipCodeDropValue.replace("%n", String.valueOf(qusIndex));
			String ans = expectedMap.get(qustion);
		//	System.out.println("qustion is " + qustion); 
		//	System.out.println("ans is " + ans); 
			  
			String zipcode[] = ans.split(",");
			int zipcodedatalength = zipcode.length; 
			
			System.out.println("zipcodedatalength " + zipcodedatalength);
			if (zipcodedatalength > 1) {
				
				System.out.println(" inside if condition + " + zipcodedatalength); 
				for (int zipindex = 1; zipindex <= 3; zipindex++) {

					driver.findElement(By.xpath(ZipcodeDropDown1.replace("%s", String.valueOf(zipindex)))).click();
					if (!(zipindex == 3)) {
						System.out.println(driver.findElement(By.xpath(zipcodeDropDownSerach1.replace("%s", String.valueOf(zipindex))))
								.getText());
					}
					String result = driver
							.findElement(By.xpath(zipCodeDropValue1.replace("%s", String.valueOf(zipindex)))).getText();
					if (result.equalsIgnoreCase("No results found")) {
						addExceptionToReport("Expected Zip Code Value is not displayed in drop down.", result,
								zipcode[zipindex]);
					}

					driver.findElement(By.xpath(zipCodeDropValue1.replace("%s", String.valueOf(zipindex)))).click();
				} 
			} else { 
				
			System.out.println("zipcode[0] --> " +  zipcode[0] ); 
				//driver.findElement(By.xpath(inputText.replace("%s", String.valueOf(qusIndex)))).clear();  
				System.out.println(driver.findElement(By.xpath(inputText.replace("%s", String.valueOf(qusIndex)))).getText()); 

				System.out.println("zipcode[0].trim()" + zipcode[0].trim());
				//driver.findElement(By.xpath(".//*[@for='getQuote.zipcode']")).click();
			//	System.out.println("click 1");
				Thread.sleep(9000);     
			} 

			
			Thread.sleep(3000);     
			driver.findElement(By.xpath(OkayBUtton2.replace("%s", String.valueOf(qusIndex)))).click();
	

			
		/*	if (!(driver.findElement(By.xpath(OkayBUtton2.replace("%s", String.valueOf(qusIndex)))).isEnabled())) {
				
				System.out.println("OkayBUtton2  is disabled ");

			}*/
				
			
			/*
			 * driver.findElement(By.xpath(OkayBUtton2.replace("%s",
			 * String.valueOf(QusIndex1)))).click(); Thread.sleep(50000);
			 */
			     
			 
		}    
		  
		
		return expectedMap;
	}  
	
	
	/**
	 * Method Name: validateAddress
	 *  Purpose:  to handle all the condition related to the My current address is questions
	*   
	 */ 
	
	
	private void validateAddress(WebDriver driver, String qustion, int qusIndex, String answer, int months, String ans) throws Exception {
		System.out.println( "Inside validateAddress method   ");
		System.out.println("answer --> " + ans );  
		System.out.println("ans --> " + ans );
		System.out.println("qustion.contains(My current address is)" + qustion.contains("My current address is"));
		System.out.println("(ans.contains(Zipcode  -- " + (ans.contains("Zipcode"))); 
		System.out.println("(ans.contains(CB%))   --  " + (ans.contains("CB%"))); 
		
		if (qustion.contains("My mailing address is") ||  qustion.contains("My current address is")) {    
			
			System.out.println( " inside  test111111111111111111111111" + qustion);
		
		 	getElementByUsing("1003_GetStarted_YesPleaseModelWindow").click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(OkayBUtton1.replace("%s", String.valueOf(qusIndex)))).click();
			
			System.out.println( " inside  test222222222222222");
		}       		   
    
		

		
		if (qustion.contains("My current address is") && (ans.contains("Zipcode") && (ans.contains("CB%")))) { 	
			System.out.println( " inside  test111111111111111111111111");

			getElementByUsing("1003_GetStarted_YesPleaseModelWindow").click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(OkayBUtton1.replace("%s", String.valueOf(qusIndex)))).click();   
			System.out.println( " inside  test111111111111111111111111");

		}
	

		if (qustion.contains("My current address is") && (!ans.contains("Zipcode") && (ans.contains("CB%")))) {
		
			driver.findElement(By.xpath(".//button[contains(.,'Skip ')]")).click();
			// getElementByUsing("1003_GetStarted_YesPleaseModelWindow").click();
			Thread.sleep(2000);
	
		} 
		   
		 System.out.println("months ARE --> " +  months);
			if ((qustion.contains("My current address is") || qustion.contains("My previous address was"))&& (months < 24)) {	
				Thread.sleep(2000);	
				getElementByUsing("FinExp_1003_AddAddressButton").click();
				Thread.sleep(1000); 
			}         
 		System.out.println("validateAddress  method end ");
	} 

	
	/**
	 * Method Name: validateSequentiandHelpTextFlow1003
	 *  Purpose:  to check for question answer help text and sequence of the questions
	*   
	 */ 
	
	
	public void validateSequentiandHelpTextFlow1003() throws InterruptedException, TwfException, Exception {

		WebDriver driver = DriverFactory.getDriver();
		System.out.println(" inside validateSequentiandHelpTextFlow1003 ");
		String autoPromtInfo = step.getDataValue("QuestionAnswer");
		// System.out.println("test data string :" + autoPromtInfo.split("%")[0]);
		HashMap<String, String> dataMap = new HashMap<String, String>();
		dataMap = buildDataMap(autoPromtInfo);

		String HelpText = step.getDataValue("HelpText");
		// System.out.println("HelpText data string :" + HelpText);
		System.out.println("--------------------- ");
		HashMap<String, String> HelpTextMap = new HashMap<String, String>();

		HelpTextMap = buildDataMap(HelpText);
		String SequenceQuestion = step.getDataValue("SequenceQuestion");
		// System.out.println("SequenceQuestion data string : " +
		// SequenceQuestion );

		System.out.println("---------------------");
		HashMap<Integer, String> SequencedataMap = new HashMap<Integer, String>();
		SequencedataMap = buildDataMaporder(SequenceQuestion);

		String str = verifyQuestionOrdering(dataMap, SequencedataMap, HelpTextMap);

		if (str.contains("false")) {
			System.out.println(str);
			addExceptionToReport("Expected Question in not enabled.Hence cannot move forward.", "", str);

		}

	}

	
	/**
	 * Method Name: validateQuestionAnswerIn1003Liability
	 *  Purpose:  to fill Liability details
	*   
	 */ 
	
	
	public void validateQuestionAnswerIn1003Liability() throws InterruptedException, TwfException, Exception {
		System.out.println("inside validateQuestionAnswerIn1003Liability ");

		String LiabilityQuestionAnswer = step.getDataValue("LiabilityQuestionAnswer");
		String Module = step.getDataValue("Module");

		HashMap<String, String> dataMap = validateQuestionAnswerIn1003(LiabilityQuestionAnswer);
		if (Module.equalsIgnoreCase("1003")) {
			System.out.println("Not Prequal");
			//tenothreesectionsummary.validateLiabilitySummaryPage(dataMap);
		} else {
			System.out.println("Prequal");
		}

	}
	
	
	
	/**
	 * Method Name: validateQuestionAnswerIn1003RealEstate 
	 * Purpose: to fill RealEstate details
	 * 
	 */

	public void validateQuestionAnswerIn1003RealEstate() throws InterruptedException, TwfException, Exception {
		System.out.println("inside validateQuestionAnswerIn1003RealEstate ");

		String realEstate = step.getDataValue("RealEstateQuestionAnswer");
		String Module = step.getDataValue("Module");
		System.out.println(" Real estate data -----");

		System.out.println(realEstate);

		HashMap<String, String> dataMap = validateQuestionAnswerIn1003(realEstate);
		if (Module.equalsIgnoreCase("1003")) {
			System.out.println("Not Prequal");
			//tenothreesectionsummary.validateRealEstateSummaryPage(dataMap);
		} else { 
			System.out.println("Prequal");
		}

	}

	
	/**
	 * Method Name: validatePropertyQuestionAnswerIn1003
	 *  Purpose: to fill  property  details
	 * 
	 */
	
	
	public void validatePropertyQuestionAnswerIn1003() throws InterruptedException, TwfException, Exception {
		System.out.println("inside validatePropertyQuestionAnswerIn1003 ");

		String PropertyQuestionAnswer = step.getDataValue("PropertyQuestionAnswer");
		HashMap<String, String> dataMap = validateQuestionAnswerIn1003(PropertyQuestionAnswer);
		String Module = step.getDataValue("Module");
//		if(Module.equalsIgnoreCase("1003"))
//		{
//			System.out.println("Not Prequal");
//			//tenothreesectionsummary.validatePropertySummaryPage(dataMap);
//		}
//		else
//		{
//			System.out.println("Prequal");
//		}  
		
		//new
		dataMap.keySet().forEach(key -> System.out.println(key + "->" + dataMap.get(key)));  
	}
	
	 

	
	
	/**
	 * Method Name: validateAssetQuestionAnswerIn1003
	 *  Purpose:
	 * 
	 */
	
	
	
	public void validateAssetQuestionAnswerIn1003() throws InterruptedException, TwfException, Exception{
		System.out.println("inside validateAssetQuestionAnswerIn1003 "); 
		    
		 WebDriver driver = DriverFactory.getDriver();
		String AssetQuestionAnswer = step.getDataValue("1003_Asset_Manual");  
		String Module = step.getDataValue("Module");		
		HashMap<String, String> dataMap = new HashMap<>();
		if(AssetQuestionAnswer.trim().contains("//"))
		{		
		String[] assetQusetions = AssetQuestionAnswer.trim().split("//");
		System.out.println("  Question size  " + assetQusetions.length);
		for (int index = 0; index < assetQusetions.length; index++)
		{
			dataMap =  validateQuestionAnswerIn1003(assetQusetions[index]);	
			System.out.println( " test .............. " +index);	
			
//			if(Module.equalsIgnoreCase("1003"))
//			{
//				System.out.println("Not Prequal");
//				tenothreesectionsummary.validateAssetSummaryPage(dataMap);
//			}
//			else
//			{ 
//				System.out.println("Prequal");
//			}
			//tenothreesectionsummary.validateAssetSummaryPage(dataMap);
			if ( index != assetQusetions.length -1 )
			addNewAssetSummaryPage(driver,index);    
		}  
		}
		else 
		{
			String assetQusetions = AssetQuestionAnswer.trim();
			dataMap =  validateQuestionAnswerIn1003(assetQusetions);	
//			if(Module.equalsIgnoreCase("1003"))
//			{
//				System.out.println("Not Prequal");
//				tenothreesectionsummary.validateAssetSummaryPage(dataMap);
//			}
//			else
//			{ 
//				System.out.println("Prequal");
//			}
		}
		
		
		//getElementByUsing("1003_Income_backButtonSumarysection").click();
		Thread.sleep(2000); 
		/*
		HashMap<String, String> dataMap =  validateQuestionAnswerIn1003(AssetQuestionAnswer); 
		validateAssetSummaryPage(dataMap);*/
	} 
	
	
	
	/**
	 * Method Name: addNewAssetSummaryPage
	 *  Purpose: 
	 * 
	 */
	
	
	
  
	public void addNewAssetSummaryPage(WebDriver driver, int currentAssetIndex)
			throws InterruptedException, TwfException, Exception {
		System.out.println("inside addIncomeSummary   . TenOThreeIncome class");
		int assetMaxLimit = 10;  
		Thread.sleep(2000);  
   
		//if ((getElementByUsing("1003_Assets_Summary_Add_Assets_Button").isEnabled()) 	&& (( currentAssetIndex +2 ) <  (assetMaxLimit+1))) {   
		if ((getElementByUsing("1003_Assets_Summary_Add_Assets_Button").isEnabled()))
		{  
			getElementByUsing("1003_Assets_Summary_Add_Assets_Button").click(); 
			Thread.sleep(3000);        
			getElementByUsing("1003_Income_AddItManuallyButton").click();     
			Thread.sleep(2000);
		} 
		else
		{
			addExceptionToReport("Add_Assets_Button is disabled ", "Add_Assets_Button is disabled",	"Add_Assets_Button should be enabled");
		}

	} 
	
	
	
	   
	public void validateIncomeQuestionAnswerIn1003() throws InterruptedException, TwfException, Exception {
		System.out.println("inside validateIncomeQuestionAnswerIn1003 ");

		String IncomeQuestionAnswer = step.getDataValue("1003_Income_Business");
		//stored return datamap
		HashMap<String, String> dataMap = validateQuestionAnswerIn1003(IncomeQuestionAnswer);
		
	}
	public static WebElement clickOnElementWhenElementIsReady(final WebElement locator) throws Exception {
		WebDriver driver = DriverFactory.getDriver();
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(180, TimeUnit.SECONDS)
				.pollingEvery(250, TimeUnit.MILLISECONDS).ignoring(Exception.class);

		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {

				locator.click();
				return locator;
			}
		});

		return element;
	};

	public void validateDeclarationQuestionAnswerIn1003() throws InterruptedException, TwfException, Exception {
		System.out.println("inside validateDeclarationQuestionAnswerIn1003 ");

		String DeclarationQuestionAnswer = step.getDataValue("DeclarationQuestionAnswer");
		validateQuestionAnswerIn1003(DeclarationQuestionAnswer);

	}

	/**
	 * Method Name: postLoginPrequal1003
	 *  Purpose: To verify Prequal and 1003 post login scenario for different kind of users like Active, Inactive, Locked and Not Registered
	 *  
	 * @throws Exception 
	 */
	 
	public void postLoginPrequal1003() throws InterruptedException, TwfException, Exception { 

		System.out.println(" inside  postLoginPrequal1003  ............. ");
		String quesAnsString =    step.getDataValue("question_ans");
		String postloginOptions = step.getDataValue("PostloginOptions");		
		WebDriver driver = DriverFactory.getDriver();  	
		HashMap<String, String> dataMap = new HashMap<String, String>();
		dataMap = buildDataMap(quesAnsString); 
		System.out.println("after datamap");

		int QusIndexlength = dataMap.size();
		int blankCount = 0;
		System.out.println("QusIndexlength size is  " + QusIndexlength);    
		for (int qusIndex = 0; qusIndex < QusIndexlength; qusIndex++) {
			System.out.println(" Check for ----------..............>>>  " + qusIndex);
			if (driver.findElements(By.xpath(Qustion.replace("%s", String.valueOf(qusIndex)))).size() > 0) {

				String attribute = driver.findElement(By.xpath(fieldAtttibute.replace("%s", String.valueOf(qusIndex)))).getAttribute("data-type").trim();
				try {
					String qustion = driver.findElement(By.xpath(Qustion.replace("%s", String.valueOf(qusIndex)))).getText().trim();
					System.out.println("  qustion -------->" + qustion);
					selectAttribute(driver, attribute, qusIndex, dataMap, qustion);
				} catch (Exception e) {
					System.out.println(" Caught Exception " + e);
					addExceptionToReport("Test Data Error",	" Given  Test Data is not correct for attribute  " + attribute + " Exception is --> " + e,
							"Test Data should be  given properly ");

				}
			}
 
			else {     
				 System.out.println(" entered else loop .............");			
				
				if (driver.findElements(By.xpath(postLoginPopup)).size() > 0) {				
									
					postLoginOption(postloginOptions);
					
				qusIndex--;      
			}		
			  
		
	} }}
	
	/**
	 * Method Name: postLoginOption
	 *  Purpose: To verify Prequal and 1003 post login scenario for different kind of users like Active, Inactive, Locked and Not Registered
	 *  
	 * @throws Exception 
	 */
	
	 public void postLoginOption(String postloginOptions) throws BiffException, IOException, TwfException, InterruptedException
	 {
		 String confirmation_msg = getElementByUsing("createAccountConfirmationMessageActiveUser").getText();
		 String expectedMsg = "";
		 switch(postloginOptions){     
			  
			
			case "Skip":				
				expectedMsg = postLoginActiveuser_message;				
				compareMessage(confirmation_msg,expectedMsg);		 
				getElementByUsing("postLoginPopupSkipButton").click();  
				Thread.sleep(3000);				  
				break; 
			 
			case "CreateAccountLater":			
				expectedMsg = postLogin_Newuser_message;				
				compareMessage(confirmation_msg,expectedMsg);				 
				getElementByUsing("postLoginPopupCreateAccountLaterButton").click();
				Thread.sleep(3000);  	 
				getElementByUsing("postLoginPopupCreateAccountYesButton").click();									
				break;	
				
			case "CreateAccountLaterChangeEmail":	 	
				
				 System.out.println("inside CreateAccountLaterChangeEmail");
				expectedMsg = postLogin_Newuser_message;				
				compareMessage(confirmation_msg,expectedMsg);				 
				getElementByUsing("postLoginPopupCreateAccountLaterButton").click();
				Thread.sleep(3000);  	 
				getElementByUsing("postLoginPopupCreateAccountChangeEmailButton").click();		 
				Thread.sleep(1000); 
				String changeEmail = step.getDataValue("ChangeEmailId");
				getElementByUsing("FinExp_emailText").clear();
				getElementByUsing("FinExp_emailText").sendKeys(changeEmail);   
				
				getElementByUsing("FinExp_Okay_Button").click(); 
				Thread.sleep(3000);   			    
				break;
				
			case "Create account now":	
				expectedMsg = postLogin_Newuser_message;			
				compareMessage(confirmation_msg,expectedMsg);
				getElementByUsing("postLoginPopupCreateAccountNowButton").click();
				Thread.sleep(3000);  	 
			 	createAccountPopUp();							
				break;  
		
           case "Login":						
        	   System.out.println(" Inside  Login ");  
				getElementByUsing("postLogin_Popup_Login_Button").click(); 
				Thread.sleep(2000); 
				getElementByUsing("FinExp_Login_password").sendKeys("Test&123"); 
				getElementByUsing("Login_Button").click(); 														
				break;	
				
				 
           case "InactiveSkip":	 	
        	   expectedMsg = postLogin_InactiveUser_message;         	  
        	   compareMessage(confirmation_msg,expectedMsg);		 
				getElementByUsing("postLoginPopupSkipButton").click();    
				Thread.sleep(3000);			  	  
				break;  
				
           case "Send me link":	 	 
        	   expectedMsg = postLogin_InactiveUser_message;         	  
        	   compareMessage(confirmation_msg,expectedMsg);		 
				getElementByUsing("postLoginPopupSendMeLinkButton").click();    
				Thread.sleep(3000);	
				 
				String infoScucessMessage = getElementByUsing("FinExp_SendMeLinkInfoSucessLink").getText();
				compareMessage(infoScucessMessage,postLogin_SendMeLink_message);	   				
				break;  
				
				
				
			 
		}    
		 
		 
		 
	 }
	
	private void compareMessage(String confirmation_msg, String expectedMsg) throws TwfException {
		if (!(confirmation_msg.contains(expectedMsg))) {
			addExceptionToReport("Post Login confirmation messages",confirmation_msg, expectedMsg);
		}	 
		    
	}

	public void createAccountPopUp() throws BiffException, IOException, TwfException, InterruptedException {
		System.out.println( " Inside createAccountPopUp"); 
		
		getElementByUsing("FinExp_Registration_password").sendKeys("Test%123");
		getElementByUsing("FinExp_Registration_confirmPassword").sendKeys("Test%123");
		getElementByUsing("FinExp_Registration_econsent").click();
		Thread.sleep(1000); 
		getElementByUsing("FinExp_Registration_create_button").click(); 
		Thread.sleep(1000);  
		System.out.println( " End  createAccountPopUp"); 
	} 

	     
	
	//Validate Details in Get Started Summary Page
	
	public void testGetStartedSummary(boolean isCPS) throws Exception{
		HashMap<String,String> dataMap = new HashMap<String,String>();
		HashMap<String,String> summarySheetMap = new HashMap<String,String>();
		//fetch data from data sheet
		dataMap = validateQuestionAnswerIn1003("question_ans");
		
		summarySheetMap = buildSummarySheetMap(dataMap, getStartedCoapplicantSummaryPageQtns);
		//call method to verify applicant
		if(isCPS){
			summarySheetMap = buildSummarySheetMap(dataMap,getStartedCPSPageQtns);
			//call method to verify coa-pplicant
		}		
		
		String coapplicant = "coaap1//coapp2";		
		String[] coappData = coapplicant.split("//"); 
		 
		for(String coapp:coappData){
			dataMap = validateQuestionAnswerIn1003(coapp);
			summarySheetMap = buildSummarySheetMap(dataMap, getStartedCoapplicantSummaryPageQtns); 
			//call method to verify coapplicant
		}
		 
		//To validate CPS 
		if(isCPS){
			summarySheetMap = buildSummarySheetMap(dataMap,getStartedCPSPageQtns);
		}else{
			summarySheetMap = buildSummarySheetMap(dataMap, getStartedCoapplicantSummaryPageQtns);
		}		
	}
	

	public void testPropetySummary() throws Exception{
		HashMap<String,String> dataMap = new HashMap<String,String>();
		HashMap<String,String> summarySheetMap = new HashMap<String,String>();
		//fetch data from data sheet
		dataMap = validateQuestionAnswerIn1003("test");
		summarySheetMap = buildSummarySheetMap(dataMap,propertSummaryPageQtns);
		
	}
	
	
	
	public HashMap<String,String> buildSummarySheetMap(HashMap<String,String> dataMap,String[] expectedQtns){
		String quesAnsString =    step.getDataValue("PropertyQuestionAnswer"); 
		HashMap<String, String> masterMap = new HashMap<String,String>();
		masterMap = buildDataMap(quesAnsString);
		HashMap<String,String> summarySheetMap = new HashMap<String,String>();
		Set<String> keySet = masterMap.keySet();
		//String[] expectedQtns = {"My name is:Name&First name", "I was born on:Date of birth", "My marital status:Marital Status", "My contact details are:Contact Number" };
		//String[] expectedQtns = {"I am purchasing a property for:Property value&Property value","The property is located at:Property location","I am purchasing a:Property type","I will use this property as:Property occupancy"};
	
		
		for(String qtn:expectedQtns){
			String question = qtn.split(":")[0];
			
			String answer = masterMap.get(question);			
			System.out.println("Question>>>>"+question+" And its answet>>>>"+answer);
			HashMap<String,String> expectedAnswers = buildAnswerMap(question, answer);			
			Set<String> expectedAnswer = expectedAnswers.keySet();
			
			for(String ans:expectedAnswer){	
				System.out.println("Answerrrrrr>>>>>"+ans);
				if(ans.startsWith("Qtn")){
					String expectedKey = "Qtn_"+qtn.split(":")[0];
					System.out.println("expectedKey>>>>>"+expectedKey);
					System.out.println("expectedans1>>>>"+expectedAnswers.get(expectedKey));
					summarySheetMap.put(qtn.split(":")[1],expectedAnswers.get(expectedKey));
				}else if(question.contains("contact details")){
					System.out.println("eXPECTED ANSWERS2>>>>>"+expectedAnswers);
					System.out.println("cONTACT NUMBER>>>>>"+getContactNumber(expectedAnswers));
					summarySheetMap.put(qtn.split(":")[1], getContactNumber(expectedAnswers));
				}else if(question.contains("located")){
					System.out.println("located>>>>>"+expectedAnswers);
					summarySheetMap.put(qtn.split(":")[1], expectedAnswers.get("State")+","+expectedAnswers.get("City"));
				}else{
					System.out.println("ANSWER3>>>>>"+expectedAnswers.get(qtn.split(":")[1].split("&")[1]));
					summarySheetMap.put(qtn.split(":")[1].split("&")[0], expectedAnswers.get(qtn.split(":")[1].split("&")[1]));
				}	
	
			}
		}
		
		return summarySheetMap;
	/*	Set<String> summarySheetValues = summarySheetMap.keySet();
		for(String summarySheet:summarySheetValues){
			System.out.println("Key Namr>>>>"+summarySheet+" and it values are>>>>"+summarySheetMap.get(summarySheet));
		}*/
	}
	
	
		
	// public void buildSummarySheetMap(HashMap<String,String> masterMap1){
	public void buildSummarySheetMap(){ 
		String quesAnsString =    step.getDataValue("PropertyQuestionAnswer"); 
		HashMap<String, String> masterMap = new HashMap<String,String>();
		masterMap = buildDataMap(quesAnsString);
		HashMap<String,String> summarySheetMap = new HashMap<String,String>();
		Set<String> keySet = masterMap.keySet();
		//String[] expectedQtns = {"My name is:Name&First name", "I was born on:Date of birth", "My marital status:Marital Status", "My contact details are:Contact Number" };
		String[] expectedQtns = {"I am purchasing a property for:Property value&Property value","The property is located at:Property location","I am purchasing a:Property type","I will use this property as:Property occupancy"};
		//TA %First name_ fname
		//12/12/1990
		//Unmarried
		//ta%Home phone_233-333-3333:ta%Mobile number_123-123-1232:ta%Email-ID_testInactive@tavant.com
		
		
		for(String qtn:expectedQtns){
			String question = qtn.split(":")[0];
			
			String answer = masterMap.get(question);			
			System.out.println("Question>>>>"+question+" And its answet>>>>"+answer);
			HashMap<String,String> expectedAnswers = buildAnswerMap(question, answer);			
			Set<String> expectedAnswer = expectedAnswers.keySet();
			
			for(String ans:expectedAnswer){	
				System.out.println("Answerrrrrr>>>>>"+ans);
				if(ans.startsWith("Qtn")){
					String expectedKey = "Qtn_"+qtn.split(":")[0];
					System.out.println("expectedKey>>>>>"+expectedKey);
					System.out.println("expectedans1>>>>"+expectedAnswers.get(expectedKey));
					summarySheetMap.put(qtn.split(":")[1],expectedAnswers.get(expectedKey));
				}else if(question.contains("contact details")){
					System.out.println("eXPECTED ANSWERS2>>>>>"+expectedAnswers);
					System.out.println("cONTACT NUMBER>>>>>"+getContactNumber(expectedAnswers));
					summarySheetMap.put(qtn.split(":")[1], getContactNumber(expectedAnswers));
				}else if(question.contains("located")){
					System.out.println("located>>>>>"+expectedAnswers);
					summarySheetMap.put(qtn.split(":")[1], expectedAnswers.get("State")+","+expectedAnswers.get("City"));
				}else{
					System.out.println("ANSWER3>>>>>"+expectedAnswers.get(qtn.split(":")[1].split("&")[1]));
					summarySheetMap.put(qtn.split(":")[1].split("&")[0], expectedAnswers.get(qtn.split(":")[1].split("&")[1]));
				}	
	
			}  
		}
		
		Set<String> summarySheetValues = summarySheetMap.keySet();
		for(String summarySheet:summarySheetValues){
			System.out.println("Key Namr>>>>"+summarySheet+" and it values are>>>>"+summarySheetMap.get(summarySheet));
		}
				  
	}     
	 
	  
	private String getContactNumber(HashMap<String,String> expectedMap){
		
		Set<String> keys = expectedMap.keySet();
		String contactNumber = "";
		for(String key:keys){
			if(key.contains("Home")){
				contactNumber = contactNumber +"/"+expectedMap.get(key)+"(H)";
			}
			if(key.contains("Mobile")){
				contactNumber = contactNumber +"/"+expectedMap.get(key)+"(M)";
			}
		}
		System.out.println("Contact Number>>>>>"+contactNumber);
		return contactNumber.substring(1, contactNumber.length());
	}
	
	private HashMap<String,String> buildAnswerMap(String expectedQtn,String expectedAnswer){
		HashMap<String,String> expectedAnswerMap = new HashMap<String,String>();
		if(expectedAnswer.contains("%")){
			String[] level1Answers = expectedAnswer.split(":");
			for(String level1Answer:level1Answers){
				System.out.println("Level1 answer>>>>"+level1Answer);
				expectedAnswerMap.put(level1Answer.split("%")[1].split("_")[0], level1Answer.split("%")[1].split("_")[1]); 
			}			
		}else{
			System.out.println("in else loop>>>>>"+expectedAnswer);
			expectedAnswerMap.put("Qtn_"+expectedQtn,expectedAnswer);
		} 
		return expectedAnswerMap;
	}    

  
	@Override
	public void checkPage() {

		// TODO Auto-generated method stub

	}

}

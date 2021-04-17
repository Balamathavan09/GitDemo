package com.finx.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tavant.base.DriverFactory;
import com.tavant.kwutils.CustomStep;
import com.tavant.kwutils.KWVariables;
import com.tavant.kwutils.Step;
import com.tavant.utils.TwfException;

public class Preview1003 extends CustomStep {
	static WebDriver driver;

	static HashMap<String, String> fieldValueGetStartedSection = new HashMap<String, String>();
	static HashMap<String, String> fieldValuePropertySection = new HashMap<String, String>();
	static HashMap<String, String> fieldValueIncomeSection = new HashMap<String, String>();
	static HashMap<String, String> fieldValueAssetsSection = new HashMap<String, String>();
	static HashMap<String, String> fieldValueLiabilitySection = new HashMap<String, String>();
	static HashMap<String, String> fieldValueRealEstateSection = new HashMap<String, String>();
	static HashMap<String, String> fieldValueDeclarationSection = new HashMap<String, String>();
//	static String FieldMappingFileName = System.getProperty("user.dir") + "\\src\\test\\resources\\FieldMapping.xlsx";
	static HashMap<String, HashMap<String, String>> FieldMappingMap;
	static HashMap<String, HashMap<String, String>> skipQtnMap;
	public static HashMap<String, HashMap<String, String>> summaryFieldMappingMap = new HashMap<String, HashMap<String, String>>();
	Util util = new Util();
    static boolean isSpouse=false;
	public HashMap<String, String> buildpreview(String expectedTest) {
		// String expectedTest =
		// "My name is; TA %First name_ test :ta%Middle name _t: ta%Last
		// name_test : RB %Suffix_Sr.&&My contact details are;ta%Home
		// phone_123-456-7890:ta%Mobile number_1234567890&&I was born
		// on;12/12/1990&&I am a first time home buyer;No";
		HashMap<String, String> dataMap = new HashMap<String, String>();

		String[] qtnArray = expectedTest.split("&&");

		for (int qtnIndex = 0; qtnIndex < qtnArray.length; qtnIndex++) {
			String[] subQtnArray = qtnArray[qtnIndex].split(";");

			if (subQtnArray[1].contains("%")) {
				String[] qtnFieldArray = subQtnArray[1].split(":");
				for (int i = 0; i < qtnFieldArray.length; i++) {
					dataMap.put((qtnFieldArray[i].split("%")[1].split("_")[0]).toLowerCase().trim(),
							qtnFieldArray[i].split("%")[1].split("_")[1]);

				}
			} else {
				// System.out.println("qtn1>>>>>"+subQtnArray[0].toLowerCase());
				dataMap.put(subQtnArray[0].toLowerCase().trim(), subQtnArray[1]);
			}

		}

		return dataMap;
	}

	// public void validateDataInPreviewPage() throws Exception {
	// System.out.println("validateDataInPreviewPage:");
	// WebDriver driver = DriverFactory.getDriver();
	// // Thread.sleep(100000);
	//
	// // HashMap<String, String> fieldValueEconsentSection = new
	// // HashMap<String, String>();
	//
	// HashMap<String, HashMap<String, String>> sectionHeaderWithData = new
	// HashMap<String, HashMap<String, String>>();
	// String sectionHeader = null;
	// String[] columnNames = null;
	// String[] columnNameOf1003 = { "question_ans", "PropertyQuestionAnswer",
	// "1003_Income_Business", "1003_Asset_Manual",
	// "LiabilityQuestionAnswer","DeclarationQuestionAnswer",
	// "RealEstateQuestionAnswer"
	// };
	// String[] columnNameOfPreQual = { "question_ans",
	// "PropertyQuestionAnswer", "1003_Income_Business",
	// "1003_Asset_Manual", "LiabilityQuestionAnswer",
	// "DeclarationQuestionAnswer" };
	//
	// String testDataValue = step.getDataValue("Module");
	// if (testDataValue.contains("1003")) {
	// System.out.println("----------entered 1003 loop---------");
	// columnNames = columnNameOf1003;
	// } else{
	// System.out.println("entered prequal");
	// columnNames = columnNameOfPreQual;}
	//
	// String displayedFieldName = null;
	// String displayedFieldValue = null;
	// String expectedValue = null;
	// // int i=1;
	// int countOfHeaders = driver
	// .findElements(
	// By.xpath("(.//*[@id='preview-prequal-main']/div/div[1]/div/div[1]/h3)"))
	// .size();
	// System.out.println("countOfHeaders size"+countOfHeaders);
	// for (int i = 1; i <= countOfHeaders; i++) {
	// System.out.println("i------------->"+i);
	// sectionHeader = driver
	// .findElement(
	// By.xpath("(.//*[@id='preview-prequal-main']/div/div[1]/div/div[1]/h3)["
	// + i + "]")).getText();
	// switch (sectionHeader) {
	// case "Get Started":
	// System.out
	// .println("222222entered get started222222******************");
	// List<WebElement> qtnCountofGetStarted = driver
	// .findElements(By
	// .xpath("(//div[@id='preview-prequal-main'])[1]//div[@class='qustion-holder']/div"));
	// String testData = step.getDataValue(columnNames[0]);
	// if (qtnCountofGetStarted.size() != 0 || testData.length() != 0) {
	// HashMap<String, String> expectedDataMap = buildpreview(testData);
	// for (int qtnIndex = 1; qtnIndex <= qtnCountofGetStarted
	// .size(); qtnIndex++) {
	// displayedFieldName = driver
	// .findElement(
	// By.xpath("((//div[@id='preview-prequal-main'])[1]//div[@class='qustion-holder'][1]//div/div[@class='field-section']/div[1])["
	// + String.valueOf(qtnIndex)
	// + "]")).getText().trim();
	// displayedFieldValue = driver
	// .findElement(
	// By.xpath("((//div[@id='preview-prequal-main'])[1]//div[@class='qustion-holder'][1]//div/div/following-sibling::div)["
	// + String.valueOf(qtnIndex)
	// + "]")).getText().trim();
	// fieldValueGetStartedSection.put(displayedFieldName,
	// displayedFieldValue);
	//
	// //compareDataInPreviewPageWithTestData(expectedDataMap,displayedFieldName,displayedFieldValue);
	//
	// }
	// }
	//
	// for(Entry<String,String>
	// m:fieldValueGetStartedSection.entrySet()){
	// //System.out.println("-------------&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&----------");
	// // System.out.println(m.getKey()+" ------------"+m.getValue());
	// }
	//
	// break;
	// case "Property":
	// System.out.println("entered propert");
	// List<WebElement> qtnCountofProperty = driver
	// .findElements(By
	// .xpath("(//div[@id='preview-prequal-main'])[2]//div[@class='qustion-holder']/div"));
	// System.out.println("qtnCountofGetStarted:---"
	// + qtnCountofProperty.size());
	// String testDataofProperty = step.getDataValue(columnNames[1]);
	// System.out.println("testData: " + testDataofProperty);
	// if (qtnCountofProperty.size() != 0
	// || testDataofProperty.length() != 0) {
	// HashMap<String, String> expectedDataMapforProperty =
	// buildpreview(testDataofProperty);
	//
	// for (int qtnIndex = 1; qtnIndex <= qtnCountofProperty
	// .size(); qtnIndex++) {
	// //System.out.println("entered for loop");
	// displayedFieldName = driver
	// .findElement(
	// By.xpath("((//div[@id='preview-prequal-main'])[2]//div[@class='qustion-holder'][1]//div/div[@class='field-section']/div[1])["
	// + String.valueOf(qtnIndex)
	// + "]")).getText().trim();
	// // System.out.println("displayedFieldName-->"+displayedFieldName);
	// displayedFieldValue = driver
	// .findElement(
	// By.xpath("((//div[@id='preview-prequal-main'])[2]//div[@class='qustion-holder'][1]//div/div/following-sibling::div)["
	// + String.valueOf(qtnIndex)
	// + "]")).getText().trim();
	// // System.out.println("displayedFieldName--->"+displayedFieldValue);
	// fieldValuePropertySection.put(displayedFieldName,
	// displayedFieldValue);
	// //compareDataInPreviewPageWithTestData(expectedDataMapforProperty,displayedFieldName,displayedFieldValue);
	//
	// }
	// }
	// for(Entry<String,String>
	// m:fieldValuePropertySection.entrySet()){
	// //System.out.println("-------------&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&----------");
	// System.out.println(m.getKey()+" ------------"+m.getValue());}
	// break;
	// case "Income":
	//
	// System.out.println("entered Income");
	// List<WebElement> qtnCountofIncome = driver
	// .findElements(By
	// .xpath("(//div[@id='preview-prequal-main'])[3]//div[@class='qustion-holder']/div"));
	// System.out.println("qtnCountofGetStarted:---"
	// + qtnCountofIncome.size());
	// String testDataofIncome = step.getDataValue(columnNames[2]);
	// System.out.println("testData: " + testDataofIncome);
	// if (qtnCountofIncome.size() != 0
	// || testDataofIncome.length() != 0) {
	// HashMap<String, String> expectedDataMapforIncome =
	// buildpreview(testDataofIncome);
	//
	// for (int qtnIndex = 1; qtnIndex <= qtnCountofIncome.size(); qtnIndex++) {
	// //System.out.println("entered for loop");
	// displayedFieldName = driver
	// .findElement(
	// By.xpath("((//div[@id='preview-prequal-main'])[3]//div[@class='qustion-holder'][1]//div/div[@class='field-section']/div[1])["
	// + String.valueOf(qtnIndex)
	// + "]")).getText().trim();
	// // System.out.println("displayedFieldName-->"+displayedFieldName);
	// displayedFieldValue = driver
	// .findElement(
	// By.xpath("((//div[@id='preview-prequal-main'])[3]//div[@class='qustion-holder'][1]//div/div/following-sibling::div)["
	// + String.valueOf(qtnIndex)
	// + "]")).getText().trim();
	// // System.out.println("displayedFieldName--->"+displayedFieldValue);
	// fieldValueIncomeSection.put(displayedFieldName,
	// displayedFieldValue);
	// //compareDataInPreviewPageWithTestData(expectedDataMapforIncome,displayedFieldName,displayedFieldValue);
	//
	// }
	// }
	// for(Entry<String,String> m:fieldValueIncomeSection.entrySet()){
	// //System.out.println("-------------&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&----------");
	// System.out.println(m.getKey()+" ------------"+m.getValue());}
	// break;
	// //
	//
	// case "Assets":
	// System.out.println("entered Assets");
	// List<WebElement> qtnCountofAssets = driver
	// .findElements(By
	// .xpath("(//div[@id='preview-prequal-main'])[4]//div[@class='qustion-holder']/div"));
	// System.out.println("qtnCountofAssert:---"
	// + qtnCountofAssets.size());
	// String testDataofAssets = step.getDataValue(columnNames[3]);
	// if (qtnCountofAssets.size() != 0
	// || testDataofAssets.length() != 0) {
	// HashMap<String, String> expectedDataMapforAssets =
	// buildpreview(testDataofAssets);
	//
	// for (int qtnIndex = 1; qtnIndex <= qtnCountofAssets.size(); qtnIndex++) {
	// displayedFieldName = driver
	// .findElement(
	// By.xpath("((//div[@id='preview-prequal-main'])[4]//div[@class='qustion-holder'][1]//div/div[@class='field-section']/div[1])["
	// + String.valueOf(qtnIndex)
	// + "]")).getText().trim();
	// displayedFieldValue = driver
	// .findElement(
	// By.xpath("((//div[@id='preview-prequal-main'])[4]//div[@class='qustion-holder'][1]//div/div/following-sibling::div)["
	// + String.valueOf(qtnIndex)
	// + "]")).getText().trim();
	// fieldValueAssetsSection.put(displayedFieldName,
	// displayedFieldValue);
	// //compareDataInPreviewPageWithTestData(expectedDataMapforAssets,displayedFieldName,displayedFieldValue);
	//
	// }
	// }
	//
	// for(Entry<String,String>
	// m:fieldValueAssetsSection.entrySet()){
	// //System.out.println("-------------&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&----------");
	// System.out.println(m.getKey()+" ------------"+m.getValue());}
	// break;
	// //
	// case "Liabilities":
	// System.out.println("entered Liabilities");
	// List<WebElement> qtnCountofLiability = driver
	// .findElements(By
	// .xpath("(//div[@id='preview-prequal-main'])[5]//div[@class='qustion-holder']/div"));
	// System.out.println(qtnCountofLiability.size());
	// String testDataofLiability = step.getDataValue(columnNames[4]);
	// System.out.println("test data in liabilities"+testDataofLiability);
	// if (qtnCountofLiability.size() != 0
	// || testDataofLiability.length() != 0) {
	// HashMap<String, String> expectedDataMapforliability =
	// buildpreview(testDataofLiability);
	//
	// for (int qtnIndex = 1; qtnIndex <= qtnCountofLiability
	// .size(); qtnIndex++) {
	// displayedFieldName = driver
	// .findElement(
	// By.xpath("((//div[@id='preview-prequal-main'])[5]//div[@class='qustion-holder'][1]//div/div[@class='field-section']/div[1])["
	// + String.valueOf(qtnIndex)
	// + "]")).getText().trim();
	// System.out.println("displayedFieldName-->"+displayedFieldName);
	// displayedFieldValue = driver
	// .findElement(
	// By.xpath("((//div[@id='preview-prequal-main'])[5]//div[@class='qustion-holder'][1]//div/div/following-sibling::div)["
	// + String.valueOf(qtnIndex)
	// + "]")).getText().trim();
	// System.out.println("displayedFieldName--->"+displayedFieldValue);
	// fieldValueLiabilitySection.put(displayedFieldName,
	// displayedFieldValue);
	// //compareDataInPreviewPageWithTestData(expectedDataMapforliability,displayedFieldName,displayedFieldValue);
	//
	// }
	// }
	// for(Entry<String,String>
	// m:fieldValueLiabilitySection.entrySet()){
	// //System.out.println("-------------&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&----------");
	// System.out.println(m.getKey()+" ------------"+m.getValue());}
	// break;
	// //
	//
	// case "Real Estate":
	// System.out.println("entered RealEstate");
	// List<WebElement> qtnCountofRealEstate = driver
	// .findElements(By
	// .xpath("(//div[@id='preview-prequal-main'])[6]//div[@class='qustion-holder']/div"));
	// System.out.println(qtnCountofRealEstate.size());
	// System.out.println("columnNames[6]"+columnNames[6]);
	// String testDataofRealEstate = step.getDataValue(columnNames[6]);
	// System.out.println("testDataofRealEstate: "+testDataofRealEstate);
	// System.out.println("testDataofRealEstate.length()"+testDataofRealEstate.length());
	// if (qtnCountofRealEstate.size() != 0
	// || testDataofRealEstate.length() != 0) {
	// System.out.println("if loop RealEstate");
	// //HashMap<String,String> expectedDataMapforRealEstate
	// =buildpreview(testDataofRealEstate);
	//
	// for (int qtnIndex = 1; qtnIndex <= qtnCountofRealEstate
	// .size(); qtnIndex++) {
	// System.out.println("entered for loop of real estate:");
	// displayedFieldName = driver
	// .findElement(
	// By.xpath("((//div[@id='preview-prequal-main'])[6]//div[@class='qustion-holder'][1]//div/div[@class='field-section']/div[1])["
	// + String.valueOf(qtnIndex)
	// + "]")).getText().trim();
	// System.out.println("displayedFieldName--->"+displayedFieldValue);
	//
	// displayedFieldValue = driver
	// .findElement(
	// By.xpath("((//div[@id='preview-prequal-main'])[6]//div[@class='qustion-holder'][1]//div/div/following-sibling::div)["
	// + String.valueOf(qtnIndex)
	// + "]")).getText().trim();
	// System.out.println("displayedFieldName--->"+displayedFieldValue);
	//
	//
	// fieldValueRealEstateSection.put(displayedFieldName,
	// displayedFieldValue);
	// //compareDataInPreviewPageWithTestData(expectedDataMapforRealEstate,displayedFieldName,displayedFieldValue);
	//
	// System.out.println("Came out of real estate loop");
	// }
	// }
	//
	//
	// for(Entry<String,String>
	// m:fieldValueRealEstateSection.entrySet()){
	// System.out.println("-------------&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&----------");
	// System.out.println(m.getKey()+" ------------"+m.getValue());}
	// break;
	//
	// case "Declarations":
	// System.out.println("entered Declaration ");
	// if (testDataValue.equalsIgnoreCase("1003")) {
	// List<WebElement> qtnCountofDeclaration = driver
	// .findElements(By
	// .xpath("(//div[@id='preview-prequal-main'])[7]//div[@class='qustion-holder']/div"));
	// System.out.println("qtnCountofDeclaration"+qtnCountofDeclaration);
	// System.out.println("columnNames[6]"+columnNames[5]);
	// String testDataofDeclaration = step
	// .getDataValue(columnNames[5]);
	// //System.out.println("testDataofDeclaration: "+testDataofDeclaration);
	//
	// if (qtnCountofDeclaration.size() != 0
	// || testDataofDeclaration.length() != 0) {
	// //HashMap<String,String> expectedDataMapforDeclaration =
	// buildpreview(testDataofDeclaration);
	//
	//
	// for (int qtnIndex = 1; qtnIndex <= qtnCountofDeclaration
	// .size(); qtnIndex++) {
	// //System.out.println("entered for loop");
	// displayedFieldName = driver
	// .findElement(
	// By.xpath("((//div[@id='preview-prequal-main'])[7]//div[@class='qustion-holder'][1]//div/div[@class='field-section']/div[1])["
	// + String.valueOf(qtnIndex)
	// + "]")).getText().trim();
	//
	// displayedFieldValue = driver
	// .findElement(
	// By.xpath("((//div[@id='preview-prequal-main'])[7]//div[@class='qustion-holder'][1]//div/div/following-sibling::div)["
	// + String.valueOf(qtnIndex)
	// + "]")).getText().trim();
	//
	// fieldValueDeclarationSection.put(
	// displayedFieldName, displayedFieldValue);
	// //compareDataInPreviewPageWithTestData(expectedDataMapforDeclaration,displayedFieldName,displayedFieldValue);
	//
	// }
	// }
	// } else {
	// List<WebElement> qtnCountofDeclarationPreQual = driver
	// .findElements(By
	// .xpath("(//div[@id='preview-prequal-main'])[6]//div[@class='qustion-holder']/div"));
	//
	// String testDataofDeclaration = step
	// .getDataValue(columnNames[5]);
	//
	// if (qtnCountofDeclarationPreQual.size() != 0
	// || testDataofDeclaration.length() != 0) {
	// HashMap<String,String> expectedDataMapforDeclaration =
	// buildpreview(testDataofDeclaration);
	// for (int qtnIndex = 1; qtnIndex <= qtnCountofDeclarationPreQual
	// .size(); qtnIndex++) {
	// displayedFieldName = driver
	// .findElement(
	// By.xpath("((//div[@id='preview-prequal-main'])[6]//div[@class='qustion-holder'][1]//div/div[@class='field-section']/div[1])["
	// + String.valueOf(qtnIndex)
	// + "]")).getText().trim();
	//
	// displayedFieldValue = driver
	// .findElement(
	// By.xpath("((//div[@id='preview-prequal-main'])[6]//div[@class='qustion-holder'][1]//div/div/following-sibling::div)["
	// + String.valueOf(qtnIndex)
	// + "]")).getText().trim();
	//
	// fieldValueDeclarationSection.put(
	// displayedFieldName, displayedFieldValue);
	// //compareDataInPreviewPageWithTestData(expectedDataMapforDeclaration,displayedFieldValue,displayedFieldValue);
	//
	// }
	//
	// }
	//
	// }
	// for(Entry<String,String>
	// m:fieldValueDeclarationSection.entrySet()){
	// //
	// System.out.println("-------------&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&----------");
	// // System.out.println(m.getKey()+" ------------"+m.getValue());}
	// }
	// break;
	// //
	// //
	//
	// }
	//
	//
	//
	// }
	// }

	public static HashMap<String, String> getDynamicFieldValues(String fieldValue,boolean isSpouse) {
		String fieldNames = "Best Contact Number;Alternate Number (Optional);Email;Street address line 1;Street address line 2 (Optional)";
		HashMap<String, String> expectedFieldMap = new HashMap<String, String>();

		for (String s1 : fieldNames.split(";")) {
			String expFieldName = fieldValue.substring(fieldValue.lastIndexOf(s1) + s1.length() + 1).split(":")[0];
			String expFieldValue = fieldValue.substring(fieldValue.indexOf(s1) + s1.length() + 1).split(":")[0];
			System.out.println("Expected field naem under dynamic map>>>>"+expFieldName+" and value>>>"+expFieldValue);
			if(isSpouse){
			if(s1.contains("Email")){
				System.out.println("Inside mail");
				expectedFieldMap.put("Email ID", expFieldValue);
			}
			if(s1.contains("Street address line 1")){
				System.out.println("Inside Street address line 1");
				expectedFieldMap.put("Street Address Line 1", expFieldValue);
			}
			if(s1.contains("Street address line 2 (Optional)")){
				System.out.println("Inside Street address line 2 (Optional)");
				expectedFieldMap.put("Street Address Line 2", expFieldValue);
			}
			}
			switch (expFieldName.trim()) {
			case "Home":
				expectedFieldMap.put("Home Phone", expFieldValue);
				break;

			case "Mobile":
//				if(isSpouse){
					expectedFieldMap.put("Mobile Phone", expFieldValue);
//				}else{
//					expectedFieldMap.put("Mobile Number", expFieldValue);
//				}
				break;

			default:
				break;
			}
		}
		return expectedFieldMap;
	}
	
	private boolean  isQtnToBeSkipped(String qtn,String answer){
		if(skipQtnMap.containsKey(qtn)){
			if(skipQtnMap.get(qtn).get("Value").trim().equalsIgnoreCase(answer)){
				return true;
			}
		}
		return false;
	}

	/**
	 * Method Name: validateDiplayedAndExpected Purpose: To compare the expected
	 * and actual value in print and preview page
	 * 
	 */
	public void validateDiplayedAndExpected(WebDriver driver, HashMap<String, String> questionMap, String name,
			String sectionHeader, String sectionType, String fieldValueXpath) throws Throwable {
		System.out.println("inside validateDiplayedAndExpected>>>>>>" + questionMap);
		String[] fieldsDoNotValidate = { "Do you have a second mortgage, home equity loan or HELOC on this property?","Mixed-Use Property. If you will occupy the property, will you set aside space within the property to operate your own business? (Optional)","Status of my previous property was","I'd like to add a co-borrower","I'd like to add my spouse as a co-borrower","In my current property, I","state", "city", "To","I want to apply for joint credit","I own jointly with","I own jointly with (Optional)","I have dependents","Other Pacific Islander (Optional)",
				"Do you have other liens on the property? (Optional)","I have had an ownership interest in this business since","Are you authorized to provide information on behalf of co-applicant ?","Would you like to be connected with a local agent?",
				"I'd like to add spouse as a co-applicant", "Percentage of ownership", "Tribe name (Optional)","Are you working with a real estate agent?","Property is located in","For correspondence on your loan, please provide your agent's contact information.",
				" Held jointly with (Optional)","This is my mailing address", "Choose one from the below", "Choose sone from the below","Account Details","My other property is located at",
				" Description (Optional)","Hispanic or Latino (Optional)","Property is rental", "Tribe name (Optional)", "Your demographic information (Optional)","Years","Accept","Are you a permanent resident alien?","Other Asian (Optional)",
				"Months (Optional)", "Are you seeking assistance from a real estate agent?","My other property is a","Other Hispanic or Latino (Optional)",
				"Asian (Optional)","Native Hawaiian or Other Pacific Islander (Optional)","Is this property the applicant(s) primary residence?", "Is this property held in a trust?","I live with applicant","I want to do a joint credit pull","My relationship with co-applicant is","The purpose of collecting this information is to help ensure that all applicants are treated fairly and that the housing needs of communities and neighborhoods are being fulfilled. For residential mortgage lending, federal law requires that we ask applicants for their demographic information (ethnicity, sex, and race) in order to monitor our compliance with equal credit opportunity, fair housing, and home mortgage disclosure laws. You are not required to provide this information, but are encouraged to do so. The law provides that we may not discriminate on the basis of this information, or on whether you choose to provide it. However, if you choose not to provide the information and you have made this application in person, federal regulations require us to note your ethnicity, sex, and race on the basis of visual observation or surname. The law also provides that we may not discriminate on the basis of age or marital status information you provide in this application. Instructions: You may select one or more \"Hispanic or Latino\" origins and one or more designations for \"Race.\" If you do not wish to provide some or all of this information, select the applicable check box."
/*last rows needs to be deleted*/,"I have (or my deceased spouse has) served, or I am currently serving, in the United States Armed Forces.","Existing mortgage balance","Unit# (Optional)","I'd like to add someone else as a co-borrower","I have (or my deceased spouse has) served, or I am currently serving, in the United States Armed Forces?"};//"Street address line 1","Street address line 2 (Optional)"

		String error = "";
		String[] dynamicQtns = { "My contact details are"};

		List<String> fieldsList = new ArrayList<>();
		List<String> dynamicQtnList = new ArrayList<>();

		for (String s : fieldsDoNotValidate) {
			fieldsList.add(s);
		}

		for (String s : dynamicQtns) {
			dynamicQtnList.add(s);
		}
		String FieldMappingFileName="";
		if(KWVariables.getVariables().get("Env").equals("taurus")||KWVariables.getVariables().get("Env").equalsIgnoreCase("pisces"))
			FieldMappingFileName = System.getProperty("user.dir") + "\\src\\test\\resources\\FieldMapping_URLA.xlsx";
		else
			FieldMappingFileName = System.getProperty("user.dir") + "\\src\\test\\resources\\FieldMapping.xlsx";
		
		FieldMappingMap = Util.buildExcelFileMap(FieldMappingFileName,"Sheet1","1003 Field Name");
		 skipQtnMap = Util.buildExcelFileMap(FieldMappingFileName,"SkipOptions","1003 Field Name");
//		 System.out.println("Mappp>>>>"+skipQtnMap);
		 String actualXpath=fieldValueXpath;
		for (String expectedQtn : questionMap.keySet()) {
			boolean skipQtn = false;
			
			if(sectionHeader.contains("Property")){
				fieldValueXpath=actualXpath;
				if(expectedQtn.contains("#")){
					System.out.println("Expected in property>>>"+expectedQtn);
				String propertyIndex=expectedQtn.split("#")[1];
				
				fieldValueXpath=fieldValueXpath.replace("%propertyIndex", propertyIndex);
				System.out.println("Property Xpath>>>>"+fieldValueXpath);
				}
				else{
					fieldValueXpath=fieldValueXpath.replace("%propertyIndex", "1");
				}
			}
			String fields = questionMap.get(expectedQtn);
			System.out.println("Labelss>>>>>"+expectedQtn+"Fieldsssss>>>>>" + fields);
			String expectedFieldName = "";
			String actualValue = "";
			String expFieldValue = "";
			boolean skipVerification = false;
			// boolean isDynamcQtn = false;
			if (fieldsList.contains(expectedQtn))
				skipVerification = true;
			if (expectedQtn.trim().equalsIgnoreCase("I am a first time home buyer")) {
				if (fields.equalsIgnoreCase("No")) {
					skipVerification = true;
				}
			}else if(expectedQtn.trim().equalsIgnoreCase("My other property is")){
				
			}
			

			if (!skipVerification) {

				if (dynamicQtnList.contains(expectedQtn)) {
					HashMap<String, String> dynamicMap = getDynamicFieldValues(fields,isSpouse);
					for (String qtn : dynamicMap.keySet()) {
						actualValue = util.getText(driver, fieldValueXpath.replace("%sectionName", sectionHeader)
								.replace("%n", name).replace("%fname", qtn));
						expFieldValue = dynamicMap.get(qtn);
						System.out.println("Dynamic Field values>>>" + expFieldValue + " and displayed value>>>"
								+ actualValue + " for fiels>>>" + qtn);
						actualValue = actualValue.replace("-", "").replace("$", "").replace(",", "").replace(".00", "").trim();
						expFieldValue = expFieldValue.replace("-", "").replace("$", "");

						if (!actualValue.equalsIgnoreCase(expFieldValue.trim())) {
							error = error + "Mismatch in field name as the displayed value" + actualValue
									+ "is not matching with expected value" + expFieldValue;
						}
					}

				} else if (fields.contains(":")) {
					for (String s : fields.split(":")) {
						if (s.contains("%")) {
							String[] fieldDesc = s.split("%")[1].split("_");
							String fieldName = fieldDesc[0];
							System.out.println("fieldNameunder%%%%11>>>"+fieldName);
							if (!fieldsList.contains(fieldName)) {
								expFieldValue = fieldDesc[1];
								if(!isQtnToBeSkipped(sectionType + "_"
										+expectedQtn.trim() +"_" +fieldName, expFieldValue)){
									
									System.out.println("expFieldValue after skip question>>>" + expFieldValue + "Value" + sectionType + "_" + expectedQtn.trim() + "_" + fieldName);
									if(fieldName.trim().equalsIgnoreCase("Does this include insurance, taxes and fees?")&&expFieldValue.trim().equalsIgnoreCase("Yes")){
										System.out.println("Inside insurance, taxes and fee");
										expFieldValue="0";
									}
									System.out.println("After if!!!");
									expectedFieldName = FieldMappingMap
											.get(sectionType + "_" + expectedQtn.trim() + "_" +fieldName.trim())
											.get("Other Field Name");
									
									System.out.println("expectedFieldName>>>" + expectedFieldName+"EXCEL NAME>>>"+sectionType + "_" + expectedQtn.trim() + "_" + fieldName);
									actualValue = util.getText(driver,
											fieldValueXpath.replace("%sectionName", sectionHeader).replace("%n", name)
													.replace("%fname", expectedFieldName));
									
								
								}else{
									skipQtn = true;
								}
								if(!skipQtn){
									if(fieldName.equalsIgnoreCase("I own jointly with (Optional)")){
										System.out.println("Inside jointly app");
										expFieldValue="One of the applicant";
									}
									if(fieldName.equalsIgnoreCase("I want to refinance my existing mortgage to")&&(expFieldValue.contains("Lower my monthly payment"))){
										System.out.println("Inside lower mortage");
										expFieldValue="Refinance : lower mortgage payment";
									}
									if(fieldName.equalsIgnoreCase("I have received the gift/grant")&&(expFieldValue.contains("I have received the gift/grant"))){
										System.out.println("I have received the gift/grant>>>>>");
										expFieldValue="Yes";
									}
									System.out.println("Fields>>>>"+fieldName+"Actual1sss>>" + actualValue + "ExpectedValue1>>" + expectedFieldName
											+ "Value>>>" + sectionType + "_" + expectedQtn.trim() + "_" + fieldName);
								}	
							}
							
						
						}
						if(!skipQtn){
							actualValue = actualValue.replace("-", "").replace("$", "").replace(",", "").replace(".00", "").replace("/", "").trim();
							expFieldValue = expFieldValue.replace("-", "").replace("$", "");

							if (!actualValue.equalsIgnoreCase(expFieldValue.trim())) {
								error = error + "Mismatch in field name as the displayed value" + actualValue
										+ "is not matching with expected value" + expFieldValue;
							}
						}
//						System.out.println("actual>>>"+actualValue+"Expected>>"+expFieldValue);
					}
				}

				else {
					if (fields.contains("%")) {
						String[] fieldDesc = fields.split("%")[1].split("_");
						String fieldName = fieldDesc[0];
						System.out.println("fieldName22>>>" + fieldName);
						if (!fieldsList.contains(fieldName)) {
			
							expFieldValue = fieldDesc[1];
							System.out.println("expFieldValue1>>>" + expFieldValue);
							expectedFieldName = FieldMappingMap
									.get(sectionType + "_" + expectedQtn.trim() + "_" + fieldName)
									.get("Other Field Name");
							System.out.println("2222>>>>"+sectionType + "_" + expectedQtn.trim() + "_" + fieldName);
							actualValue = util.getText(driver, fieldValueXpath.replace("%sectionName", sectionHeader)
									.replace("%n", name).replace("%fname", expectedFieldName));
							System.out.println("XpathPro>>>>"+fieldValueXpath.replace("%sectionName", sectionHeader)
									.replace("%n", name).replace("%fname", expectedFieldName));
						}
					} else {
						
						expFieldValue = fields;
						if (!fieldsList.contains(expectedQtn)) {
							if (fields.equalsIgnoreCase("Yes")
									&& expectedQtn.equalsIgnoreCase("I am a first time home buyer")) {
								expFieldValue = "Buy a new home";
							}
							if (fields.equalsIgnoreCase("Best Contact Number")
									&& expectedQtn.equalsIgnoreCase("I am a first time home buyer")) {
								expFieldValue = "Buy a new home";
							}
							if (fields.equalsIgnoreCase("Lower my monthly payment")
									&& expectedQtn.equalsIgnoreCase("I want to refinance my existing mortgage to")) {
								expFieldValue = "Refinance : lower mortgage payment";
							}
							if (fields.equalsIgnoreCase("Lower my payment or Payoff faster")
									&& expectedQtn.equalsIgnoreCase("I want to refinance my existing mortgage to")) {
								expFieldValue = "Refinance : Lower my payment/Payoff faster";
							}
							if (fields.equalsIgnoreCase("Use my equity to take cash out or consolidate debt")
									&& expectedQtn.equalsIgnoreCase("I want to refinance my existing mortgage to")) {
								expFieldValue = "Refinance : take additional cash/payoff debts";
							}
							if((fields.equalsIgnoreCase("My primary residence")&&//KWVariables.getVariables().get("Env").equalsIgnoreCase("taurus")&&
											expectedQtn.equalsIgnoreCase("I am using the property as"))){
								expFieldValue = "My primary home";
							}
							if(expectedQtn.equalsIgnoreCase("HELOC#1")|| expectedQtn.equalsIgnoreCase("HELOC#2")||
									expectedQtn.equalsIgnoreCase("I have received the gift/grant#1")|| expectedQtn.equalsIgnoreCase("I have received the gift/grant#2")){
								expFieldValue="Yes";
							}
							expectedFieldName = FieldMappingMap.get(sectionType + "_" + expectedQtn)
									.get("Other Field Name");
							actualValue = Util.getText(driver, fieldValueXpath.replace("%sectionName", sectionHeader)
									.replace("%n", name).replace("%fname", expectedFieldName));
							System.out.println("3333>>>>"+"EXCEL NAME>>>"+sectionType + "_" + expectedQtn.trim());
						}
					}
					actualValue = actualValue.replace("-", "").replace("$", "").replace(",", "").replace(".00", "").trim();
					expFieldValue = expFieldValue.replace("-", "").replace("$", "").replace(",", "").replace(".", "");
					System.out.println("actualValue!!!!>>>"+actualValue+"expFieldValue!!!!>>>"+expFieldValue.trim());
					if (!actualValue.equalsIgnoreCase(expFieldValue.trim())) {
						error = error + "Mismatch in field name as the displayed value" + actualValue
								+ "is not matching with expected value" + expFieldValue;
					}
				}
				switch (expectedQtn) {
				case "Enter dependent(s) age":
					actualValue = actualValue.replace("Years", "").trim();
				}
				actualValue = actualValue.replace("-", "").replace("$", "").replace(",", "").replace(".00", "").trim();
				expFieldValue = expFieldValue.replace("-", "").replace("$", "");
				if (expFieldValue.equalsIgnoreCase("In his own property"))
					expFieldValue = "In my own property";
				if (error.length() > 0) {
					addExceptionToReport("Errorr>>>>", error, "");
				}
			}
		}
	}

	private void validateSpouseDetailsInGetStartedSection(WebDriver driver,HashMap<String, String> dataMap) throws Throwable {
		String[] fieldNames = { "My name is_First name", "My name is_Middle name (Optional)", "My name is_Last name",
				"My name is_Suffix (Optional)", "My current address is_Street address line 1",
				"My current address is_Street address line 2 (Optional)","My contact details are_Best Contact Number","My contact details are_Alternate Number (Optional)","My contact details are_Email","I was born on"};
		String fieldValueXpath = "//div[@class='preview-prequal-main-in' and contains(.,'%sectionName')]//div[@class='applicant-name' and contains(.,'%n')]//following-sibling::div[@class='qustion-holder']//div[normalize-space(text())='%fname']//following-sibling::div";
		String error="";
		String[] dynamicQtns = { "My contact details are","My current address is"};

		List<String> fieldsList = new ArrayList<>();
		List<String> dynamicQtnList = new ArrayList<>();

		for (String s : dynamicQtns) {
			dynamicQtnList.add(s);
		}
		System.out.println("Inside validateSpouseDetailsInGetStartedSection>>>");
		for (String expectedQtn : dataMap.keySet()) {
			System.out.println("Expected Wtns>>>>"+expectedQtn);
			for (String s : fieldNames) {
				System.out.println("Expected field names>>>>"+s);
				if (s.contains(expectedQtn)) {					
					String fields = dataMap.get(expectedQtn);
					System.out.println("Fields under >>>>"+fields);
					if (dynamicQtnList.contains(expectedQtn)) {
						HashMap<String, String> dynamicMap = getDynamicFieldValues(fields,isSpouse);
						System.out.println("Dynamic map>>>"+dynamicMap);
						for (String qtn : dynamicMap.keySet()) {
							System.out.println("QTN>>>"+qtn);
							String actualValue = util.getText(driver, fieldValueXpath.replace("%sectionName", "Get Started")
									.replace("%n", Ten0Three.fname).replace("%fname", "Spouse "+qtn));
							String expFieldValue = dynamicMap.get(qtn);
							System.out.println("Dynamic Field values inside spouse>>>" + expFieldValue + " and displayed value>>>"
									+ actualValue + " for fiels>>>" + "Spouse "+qtn);
							if (!actualValue.equalsIgnoreCase(expFieldValue.trim())) {
								error = error + "Mismatch in field name as the displayed value" + actualValue
										+ "is not matching with expected value" + expFieldValue;
							}
					}
					}else if (fields.contains(":")) {
						for (String s1 : fields.split(":")) {
							if (s1.contains("%")) {
								String[] fieldDesc = s1.split("%")[1].split("_");
								String fieldName = fieldDesc[0];
								System.out.println("fieldName>>>" + fieldName);
								if (!s.contains(expectedQtn + "_" + fieldName)) {
									String expFieldValue = fieldDesc[1];
									String expectedFieldName = FieldMappingMap
											.get("CoApplicant" + "_" + expectedQtn.trim() + "_" + fieldName)
											.get("Other Field Name");

									System.out.println("expectedFieldName>>>" + expectedFieldName);
									String actualValue = util.getText(driver,
											fieldValueXpath.replace("%sectionName", "Get Started").replace("%n", Ten0Three.fname)
													.replace("%fname", "Spouse " +expectedFieldName));
									System.out.println("Actual1>>" + actualValue + "ExpectedValue1>>" + expectedFieldName
											+ "Value>>>" + "CoApplicant" + "_" + expectedQtn.trim() + "_" + fieldName);
													
								}
						
							}
							
						}
					}

					else
					{
						if (fields.contains("%")) {
							String[] fieldDesc = fields.split("%")[1].split("_");
							String fieldName = fieldDesc[0];
							String expFieldValue = fieldDesc[1];
							if (s.contains(expectedQtn + "_" + fieldName)) {
								String expectedFieldName =  FieldMappingMap
										.get("CoApplicant" + "_" + expectedQtn + "_" + fieldName).get("Other Field Name");
								if(expectedFieldName.contains("Address")){
									expectedFieldName = expectedFieldName.split("(")[1].replace(")","");
									
								}
								String actualValue = util.getText(driver,
										fieldValueXpath.replace("%sectionName", "Get Started")
												.replace("%n", Ten0Three.fname).replace("%fname", "Spouse " +expectedFieldName));
							
								System.out.println("Spouse Expected Field Name>>>"+expectedFieldName+" and epected value???"+expFieldValue+" and actual value"+actualValue);
							}

						}
						switch (expectedQtn) {
						case "I was born on":
							System.out.println("Yeaaahh inside born||");
							fields = dataMap.get(expectedQtn);
							if(isSpouse){
							String actualValue = util.getText(driver,
									fieldValueXpath.replace("%sectionName", "Get Started")
											.replace("%n", Ten0Three.fname).replace("%fname", "Spouse Date Of Birth"));
							System.out.println("Displayed Field!!!"+actualValue+"Expected Field"+fields);
							 	if(!actualValue.equalsIgnoreCase(fields)){
							 		addExceptionToReport("Mismatch in CoApplicant Section", actualValue, fields);
							 	}
							}

							break;
							
						}
					}
					
			
					
				}
			}
		}

	}

	public void validateDataInPreviewPage() throws Throwable {

		Ten0Three ten = new Ten0Three();
		ten.fname = "AMY";
		ten.coApplicantFname = "ANDY";
		WebDriver driver = DriverFactory.getDriver();
        System.out.println("Applicant name>>"+ten.fname+"Co-Applicant name>>"+ten.coApplicantFname);
        util.click("//a[contains(.,'View More')]");
		String sectionHeaderXpath = "//div[@class='preview-prequal-main-in']";
		int countOfHeaders = driver.findElements(By.xpath(sectionHeaderXpath)).size();
		System.out.println("Inside previewSummaryValidation>>>>>");
		System.out.println("countOfHeaders size" + driver.findElements(By.xpath(sectionHeaderXpath)).size());
		String revSectionHeaderXpath = sectionHeaderXpath + "//h3";
		List<WebElement> sectionHeaders = driver.findElements(By.xpath(revSectionHeaderXpath));
//		Ten0Three.propertydataMap.put("Source of gift#1","Relative");
//		Ten0Three.propertydataMap.put("Amount to be drawn#1","5000");
//		Ten0Three.propertydataMap.put("Source of gift#1","Relative");
//		Ten0Three.propertydataMap.put("Lender name#1","Lender One");
//		Ten0Three.propertydataMap.put("Monthly Payment#1","2000");
//		Ten0Three.propertydataMap.put("Credit limit (Optional)#1","2000");
//		Ten0Three.propertydataMap.put("HELOC#1","HELOC");
//		Ten0Three.propertydataMap.put("Amount#1","1000");
//		Ten0Three.propertydataMap.put("Type#1","Cash Gift");
//		Ten0Three.propertydataMap.put("I have received the gift/grant#1","I have received the gift/grant");
//		Ten0Three.propertydataMap.put("Amount#2","2000");
//		Ten0Three.propertydataMap.put("I have received the gift/grant#2","I have received the gift/grant");
//		Ten0Three.propertydataMap.put("Type#2","Grant");
//		Ten0Three.propertydataMap.put("Source of gift#2","Employer");
		
		
		// System.out.println("coBorDetailsMap>>" +
		// MultiBorrowerGetStarted.coBorDetailsMap);
		// System.out.println("multiBorGetStartedFinalMap>>" +
		// MultiBorrowerGetStarted.multiBorGetStartedFinalMap);
		String fieldValueXpath = "//div[@class='preview-prequal-main-in' and contains(.,'%sectionName')]//div[@class='applicant-name' and contains(.,'%n')]//following-sibling::div[@class='qustion-holder']//div[normalize-space(text())='%fname']//following-sibling::div";
		String propetyfieldValueXpath = "(//div[@class='preview-prequal-main-in' and contains(.,'%sectionName')]//div[@class='applicant-name' and contains(.,'%n')]//following-sibling::div[@class='qustion-holder']//div[normalize-space(text())='%fname']//following-sibling::div)[%propertyIndex]";
		String multiEntryfieldValueXpath = "//div[@class='preview-prequal-main-in' and contains(.,'%sectionName')]//div[@class='applicant-name' and contains(.,'%n') and contains(.,%index)]//following-sibling::div[@class='qustion-holder']//div[text()='%fname']//following-sibling::div";
		for (WebElement eachSectionHeader : sectionHeaders) {
			String sectionHeader = eachSectionHeader.getText().trim();
			String sectionType = "";
			System.out.println("sectionHeader>>" + sectionHeader);
			LinkedHashMap<String, String> questionMap = null;
			switch (sectionHeader) {
			case "Get Started":
				System.out.println("Inside Summary Validation Get Started");

				if (!(ten.fname.isEmpty())) {
					String expectedQuestion = step.getDataValue("question_ans");
					LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
					dataMap = ten.buildDataMap1(expectedQuestion);
					sectionType = "Applicant";
					questionMap = dataMap;
					validateDiplayedAndExpected(driver, questionMap, ten.fname, sectionHeader, sectionType,
							fieldValueXpath);
					System.out.println("Came>>>>");
					questionMap.clear();
				}
				if (!(ten.coApplicantFname==null)) {
				
					System.out.println("Inside CoApplicantttt GetStarted>>>>");
					String expectedQuestion = step.getDataValue("coapplicant");
					LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
					dataMap = ten.buildDataMap1(expectedQuestion);
					sectionType = "CoApplicant";
					questionMap = dataMap;
					System.out.println("QuestionMap>>"+questionMap);
					if(questionMap.get("My relationship with co-borrower is".trim()).equalsIgnoreCase("Spouse".trim())){
						System.out.println("Coapplican>>>");
						isSpouse=true;
						validateSpouseDetailsInGetStartedSection(driver, questionMap);
						isSpouse = false;
					}
					validateDiplayedAndExpected(driver, questionMap, ten.coApplicantFname, sectionHeader, sectionType,
							fieldValueXpath);
				}
				break;
			case "Property":
				 if (!(ten.fname.isEmpty())) {
				 sectionType = "Property";
				 String expectedQuestion = step.getDataValue("PropertyQuestionAnswer");
				 
				 System.out.println("EXPECTED>>>>"+expectedQuestion);
				 LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
				 dataMap = ten.buildDataMap1(expectedQuestion);
				 dataMap.remove("Provide gift details");
				 dataMap.remove("Details of other loan");
				 questionMap = dataMap;
				 if(!KWVariables.getVariables().get("Env").equals("taurus")||KWVariables.getVariables().get("Env").equalsIgnoreCase("pisces")){
					 Ten0Three.propertydataMap.clear();
					 System.out.println("Cleared>>>"+Ten0Three.propertydataMap);
				 }
				 Ten0Three.propertydataMap.putAll(dataMap);
				 questionMap=Ten0Three.propertydataMap;
				 System.out.println("Inside Summary Validation Property"+questionMap);
				 validateDiplayedAndExpected(driver, questionMap, "",
				 sectionHeader, sectionType, propetyfieldValueXpath);
				 }
			 break;
			 case "Declarations":
			 System.out.println("Inside Summary Validation Declarations");
			 LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
			 sectionType = "Declarations";			 
			 if (!(ten.fname.isEmpty())) {
			 String expectedQuestion = step.getDataValue("DeclarationQuestionAnswer");
			 dataMap = ten.buildDataMap1(expectedQuestion);
			 sectionType = "Declarations";
			 questionMap = dataMap;
			 validateDiplayedAndExpected(driver, questionMap, ten.fname,
			 sectionHeader, sectionType, fieldValueXpath);
			 dataMap.clear();
			 } 
			 if (!(ten.coApplicantFname==null)) {
						System.out.println("Inside CoApplicantttt>>>>");
						String expectedQuestion = step.getDataValue("Coapplicant_Declarations");
						dataMap = ten.buildDataMap1(expectedQuestion);
						questionMap = dataMap;
						validateDiplayedAndExpected(driver, questionMap, ten.coApplicantFname, sectionHeader, sectionType,
								fieldValueXpath);
			}
			 break;
			// case "Credit Information":
			// System.out.println("Inside Summary Validation Credit
			// Information");
			// sectionType = "Credit";
			// HashMap<String, LinkedHashMap<String, String>> creditMap =
			// MultiBorrowerGetStarted.multiBorFinalMap
			// .get("mbCreditDetailsMap").get(name);
			// for (String index : creditMap.keySet()) {
			// questionMap = creditMap.get(index);
			// String revMultiEntryfieldValueXpath =
			// multiEntryfieldValueXpath.replace("%index", index);
			// validateDiplayedAndExpected(driver, questionMap, name,
			// sectionHeader, sectionType,
			// revMultiEntryfieldValueXpath);
			// }
			// break;
			 case "Income":
			 System.out.println("Inside Summary Validation Income");
			 sectionType = "Income";
			 int count=0;
			 String expectedQuestion = step.getDataValue("Borrower_1003_Income_Business");
			 System.out.println("Income String>>>"+expectedQuestion);
			 LinkedHashMap<String, String> datamap=new LinkedHashMap<>();
			 LinkedHashMap<String, String> incomeMap=new LinkedHashMap<String, String>();
			 if (!(ten.fname.isEmpty())) {			 
				 if(expectedQuestion.isEmpty()){
					 System.out.println("Income Imported");
				 } 
		
				 else{
					 for(String str:expectedQuestion.split("//")){
						 count++;
						 datamap.put(String.valueOf(count), str);
					 }
					for(int index=1;index<=datamap.size();index++){
						incomeMap= ten.buildDataMap1(datamap.get(String.valueOf(index)));
						questionMap=incomeMap;
						 String revMultiEntryfieldValueXpath = multiEntryfieldValueXpath.replace("%index", String.valueOf(index));
						 System.out.println("revMultiEntryfieldValueXpath>>>>"+multiEntryfieldValueXpath.replace("%index", String.valueOf(index)));
						 validateDiplayedAndExpected(driver, questionMap, ten.fname, sectionHeader, sectionType,
								 revMultiEntryfieldValueXpath);
						System.out.println("\n Map of First income!!!!"+index+" Data>>>"+questionMap);
					}
					datamap.clear();
					questionMap.clear();
				 }	
			 }
			 if (!(ten.coApplicantFname==null)) {
				 count=0;
				 expectedQuestion = step.getDataValue("CoBorrowerIncomeQuestionAnswer");
				 if(expectedQuestion.isEmpty()){
					 System.out.println("Income Imported");
				 } 
				 else{
				 for(String str:expectedQuestion.split("//")){
					 count++;
					 datamap.put(String.valueOf(count), str);
					 System.out.println("str>>>>"+str);
				 }
				for(int index=1;index<=datamap.size();index++){
					incomeMap= ten.buildDataMap1(datamap.get(String.valueOf(index)));
					questionMap=incomeMap;
					 String revMultiEntryfieldValueXpath = multiEntryfieldValueXpath.replace("%index", String.valueOf(index));
					 System.out.println("revMultiEntryfieldValueXpath>>>>"+multiEntryfieldValueXpath.replace("%index", String.valueOf(index)));
					 validateDiplayedAndExpected(driver, questionMap, ten.coApplicantFname, sectionHeader, sectionType,
							 revMultiEntryfieldValueXpath);
				}
			 }	
			 
			 }
			 break;
			 case "Assets":
			 System.out.println("Inside Summary Validation Assets");
			 sectionType = "Asset";
			 if (!(ten.fname.isEmpty())) {
				 expectedQuestion = step.getDataValue("1003_Asset_Manual");
					if(expectedQuestion.isEmpty()){
						 System.out.println("Asset Imported");
					 }
//					HashMap<String, String> dataMap = new HashMap<String, String>();
					else{
				dataMap = ten.buildDataMap1(expectedQuestion);
				questionMap = dataMap;		
				 validateDiplayedAndExpected(driver, questionMap, ten.fname,
				 sectionHeader, sectionType,
				 fieldValueXpath);
					}
			 }
			 if (!(ten.coApplicantFname==null)) {
				 expectedQuestion = step.getDataValue("1003_Asset_Manual");
					if(expectedQuestion.isEmpty()){
						 System.out.println("Asset Imported");
					 }
//					HashMap<String, String> dataMap = new HashMap<String, String>();
					else{
				dataMap = ten.buildDataMap1(expectedQuestion);
				questionMap = dataMap;		
				 validateDiplayedAndExpected(driver, questionMap, ten.coApplicantFname,
				 sectionHeader, sectionType,
				 fieldValueXpath);
					}
			 }
			 break;
			 case "Real Estate":
			 System.out.println("Inside Summary Validation Real Estate");
//			 HashMap<String, String> dataMap = new HashMap<String, String>();	
			 if (!(ten.fname.isEmpty())){
			 expectedQuestion = step.getDataValue("RealEstateQuestionAnswer");
			 dataMap = ten.buildDataMap1(expectedQuestion);
			 sectionType = "RealEstate";	 
			 questionMap = dataMap;
			 validateDiplayedAndExpected(driver, questionMap, ten.fname,
			 sectionHeader, sectionType, fieldValueXpath);
			 questionMap.clear();
			 } 
			 if (!(ten.coApplicantFname==null)) {
					System.out.println("Inside CoApplicantttt>>>>");
					expectedQuestion = step.getDataValue("RealEstateQuestionAnswerCoapplicant");
					dataMap = ten.buildDataMap1(expectedQuestion);
					questionMap = dataMap;
					validateDiplayedAndExpected(driver, questionMap, ten.coApplicantFname, sectionHeader, sectionType,
							fieldValueXpath);
		}
			 break;

//			case "Line Amount":
//			 System.out.println("Inside Summary Validation Line Amount");
//			  if (!(ten.fname.isEmpty())) {
//			 String expectedQuestion = step.getDataValue("Decision");
//			 dataMap = ten.buildDataMap(expectedQuestion);
//			 sectionType = "LineAmount";	 
//			 questionMap = dataMap;
//			 validateDiplayedAndExpected(driver, questionMap, ten.fname,
//			 sectionHeader, sectionType, fieldValueXpath);
//			 questionMap.clear();
//			 } 
//			 break;	
			default:
				break;
			}
		}
		ten.fname=ten.coApplicantFname=null;
	}

	public void compareDataInPreviewPageWithTestData(HashMap<String, String> map, String displayedFieldName,
			String displayedFieldValue) throws TwfException {
		System.out.println("map: " + map);
		System.out.println("displayedFieldName: " + displayedFieldName);
		System.out.println("displayedFieldValue: " + displayedFieldValue);
		String expectedValue = map.get(displayedFieldName.toLowerCase()).trim();
		System.out.println("expectedValue: " + expectedValue);

		if (!expectedValue.equalsIgnoreCase(displayedFieldValue)) {
			// System.out.println("*********************************************");
			addExceptionToReport("Expected data didnot match with the dispalyed data", expectedValue,
					displayedFieldValue);
		}
	}

	/**
	 * Method Name: returnHashmaps PurposE: To return Preview screen details
	 * (All details) for existing user for applications from Loan details -
	 * Dashboard applications
	 * 
	 * @param expected
	 *            section name from the Dashboard Preview screen for comparison
	 * 
	 */
	public HashMap<String, String> returnHashmaps(String map) {

		HashMap<String, String> requiredSectionMap = new HashMap<String, String>();
		switch (map) {
		case "Get Started":
			requiredSectionMap.putAll(fieldValueGetStartedSection);
			break;
		case "Property":
			requiredSectionMap.putAll(fieldValuePropertySection);
			break;
		case "Income":
			requiredSectionMap.putAll(fieldValueIncomeSection);
			break;
		case "Assets":
			requiredSectionMap.putAll(fieldValueAssetsSection);
			break;
		case "Liabilities":
			requiredSectionMap.putAll(fieldValueLiabilitySection);
			break;
		case "Real Estate":
			requiredSectionMap.putAll(fieldValueRealEstateSection);
			break;
		case "Declarations":
			requiredSectionMap.putAll(fieldValueDeclarationSection);
			break;
		}
		return requiredSectionMap;
	}

	@Override
	public void checkPage() {
		// TODO Auto-generated method stub

	}
}

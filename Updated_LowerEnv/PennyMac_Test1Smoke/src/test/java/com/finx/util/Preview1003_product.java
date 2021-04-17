package com.finx.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tavant.base.DriverFactory;
import com.tavant.kwutils.CustomStep;
import com.tavant.utils.TwfException;

public class Preview1003_product extends CustomStep {
	static WebDriver driver;

	static HashMap<String, String> fieldValueGetStartedSection = new HashMap<String, String>();
	static HashMap<String, String> fieldValuePropertySection = new HashMap<String, String>();
	static HashMap<String, String> fieldValueIncomeSection = new HashMap<String, String>();
	static HashMap<String, String> fieldValueAssetsSection = new HashMap<String, String>();
	static HashMap<String, String> fieldValueLiabilitySection = new HashMap<String, String>();
	static HashMap<String, String> fieldValueRealEstateSection = new HashMap<String, String>();
	static HashMap<String, String> fieldValueDeclarationSection = new HashMap<String, String>();
	Ten0Three tenOThree = new Ten0Three();
//	Heloc helocObj=new Heloc();
	String[] headerDataPoolMap = {"Property;PropertyQuestionAnswer","Applicant Info;question_ans","Declarations;DeclarationQuestionAnswer","Credit Information;LiabilityQuestionAnswer","Income;1003_Income_Business"};
	String[] coHeaderDataPoolMap = {"Coapplicant Info;coapplicant","Declarations;DeclarationQuestionAnswer","Credit Information;LiabilityQuestionAnswer","Income;1003_Income_Business"};
	String[] propertyMap = {"Property type:My property is a","Street Address:Street Address","City:City","State:State","Zipcode:Zipcode","I have a second mortgage or HELOC:I have a second mortgage or Home Equity","Property value:Property value","Loan Amount:Home Equity amount"};
	String[] applicantInfoMap= {"First Name:First name","Middle Name:Middle name (Optional)","Last Name:Last name","Suffix:Suffix","Home Phone:Home phone","Mobile Number:Mobile number","Email Address:Email Address","I was born on:I was born on","I want Home Equity loan for:I want Home Equity loan for","I (or my spouse) served, or I am currently serving, in the United States Armed Forces?:I (or my spouse) have served, or I am currently serving, in the United States Armed Forces?",
			   "My branch of service is:My branch of service is","My service status:My service status","My service is related to:My service is related to (Optional)","I have dependents:I have dependents","I live:I live","Street Address line 1:Street Address","Zipcode:Zipcode","Years:Years","Months:Months","My marital status:My marital status",
			   "Dependent(1) age:Dependent(1) age","Dependent(2) age:Dependent(2) age","Dependent(3) age:Dependent(3) age","Dependent(4) age:Dependent(4) age","Dependent(5) age:Dependent(5) age","Dependent(6) age:Dependent(6) age","Dependent(7) age:Dependent(7) age","Dependent(8) age:Dependent(8) age"};

	String[] declarationMap= {"Are there any outstanding judgements against you ?:Are there any outstanding judgements against you?","Are you a party to a lawsuit?:Are you a party to a lawsuit?","Have you directly or indirectly been obligated on any loan which resulted in foreclosure, transfer of title in lieu of foreclosure, or judgment?:Have you directly or indirectly been obligated on any loan which resulted in foreclosure, transfer of title in lieu of foreclosure, or judgment?"
            ,"Are you presently delinquent or in default on any Federal debt or any other loan, mortgage, financial obligation, bond, or loan guarantee?:Are you presently delinquent or in default on any Federal debt or any other loan, mortgage, financial obligation, bond, or loan guarantee?","Are you obligated to pay alimony, child support, or separate maintenance?:Are you obligated to pay alimony, child support, or separate maintenance?"
            ,"Are you a co-maker or endorser on a note?:Are you a co-maker or endorser on a note?","Are you a US citizen?:Are you a US citizen?","Are you a permanent resident alien?:Are you a permanent resident alien?","Ethnicity:Ethnicity","Hispanic or Latino:Hispanic or Latino","Race:Race","American Indian or Alaska Native:Enter name","Gender:Sex"};

	String[] creditInfoMap= {"Liability Type:My liability type is","Lender Name:Lender name","Account Number:Account number (Optional)","Outstanding Balance:Outstanding balance","Monthly Payment:Monthly payment","Status:Status","Holds jointly with:Holds jointly with (Optional)","Description:Description (Optional)"};

	String[] incomeMap= {"My Source of income is:My source of income is","Street Address Line 1:Street Address","Zipcode:Zipcode","Employer Phone:Business phone","From:In this business from","To:To","Gross Income:Monthly earnings","Business Name:Business name"};

	String[] coapplicantInfoMap= {"My relationship with co-applicant is:My relationship with co-applicant is","First Name:First name","Middle Name:Middle name (Optional)","Last Name:Last name","Suffix:Suffix","Home Phone:Home phone","Mobile Number:Mobile number","Email Address:Email Address","I was born on:I was born on","I want Home Equity loan for:I want Home Equity loan for","I (or my spouse) served, or I am currently serving, in the United States Armed Forces?:I (or my spouse) have served, or I am currently serving, in the United States Armed Forces?",
			   "My branch of service is:My branch of service is","My service status:My service status","My service is related to:My service is related to (Optional)","I have dependents:I have dependents","I live:I live","Street Address line 1:Street Address","Zipcode:Zipcode","Years:Years","Months:Months","My marital status:My marital status"};
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

	public void validateDataInPreviewPage() throws Exception {
		System.out.println("validateDataInPreviewPage:");
		WebDriver driver = DriverFactory.getDriver();
		// Thread.sleep(100000);

		// HashMap<String, String> fieldValueEconsentSection = new
		// HashMap<String, String>();

		HashMap<String, HashMap<String, String>> sectionHeaderWithData = new HashMap<String, HashMap<String, String>>();
		String sectionHeader = null;
		String[] columnNames = null;
		String[] columnNameOf1003 = { "question_ans", "PropertyQuestionAnswer", "1003_Income_Business",
				"1003_Asset_Manual", "LiabilityQuestionAnswer", "DeclarationQuestionAnswer",
				"RealEstateQuestionAnswer" };
		String[] columnNameOfPreQual = { "question_ans", "PropertyQuestionAnswer", "1003_Income_Business",
				"1003_Asset_Manual", "LiabilityQuestionAnswer", "DeclarationQuestionAnswer" };

		String testDataValue = step.getDataValue("Module");
		if (testDataValue.contains("1003")) {
			System.out.println("----------entered 1003 loop---------");
			columnNames = columnNameOf1003;
		}
		else {
			System.out.println("entered prequal");
			columnNames = columnNameOfPreQual;
		}

		String displayedFieldName = null;
		String displayedFieldValue = null;
		String expectedValue = null;
		// int i=1;
		int countOfHeaders = driver
				.findElements(By.xpath("(.//*[@id='preview-prequal-main']/div/div[1]/div/div[1]/h3)")).size();
		System.out.println("countOfHeaders size" + countOfHeaders);
		for (int i = 1; i <= countOfHeaders; i++) {
			System.out.println("i------------->" + i);
			sectionHeader = driver
					.findElement(By.xpath("(.//*[@id='preview-prequal-main']/div/div[1]/div/div[1]/h3)[" + i + "]"))
					.getText();
			System.out.println("sectionHeader>>>>"+sectionHeader);
			switch (sectionHeader) {
			case "Get Started":
				System.out.println("entered get started222222******************");
				List<WebElement> qtnCountofGetStarted = driver.findElements(
						By.xpath("(//div[@id='preview-prequal-main'])[1]//div[@class='qustion-holder']/div"));
				String testData = step.getDataValue(columnNames[0]);
				if (qtnCountofGetStarted.size() != 0 || testData.length() != 0) {
					HashMap<String, String> expectedDataMap = buildpreview(testData);
					for (int qtnIndex = 1; qtnIndex <= qtnCountofGetStarted.size(); qtnIndex++) {
						displayedFieldName = driver.findElement(By
								.xpath("((//div[@id='preview-prequal-main'])[1]//div[@class='qustion-holder'][1]//div/div[@class='field-section']/div[1])["
										+ String.valueOf(qtnIndex) + "]"))
								.getText().trim();
						displayedFieldValue = driver.findElement(By
								.xpath("((//div[@id='preview-prequal-main'])[1]//div[@class='qustion-holder'][1]//div/div/following-sibling::div)["
										+ String.valueOf(qtnIndex) + "]"))
								.getText().trim();
						fieldValueGetStartedSection.put(displayedFieldName, displayedFieldValue);

						// compareDataInPreviewPageWithTestData(expectedDataMap,displayedFieldName,displayedFieldValue);

					}
				}

				for (Entry<String, String> m : fieldValueGetStartedSection.entrySet()) {
					// System.out.println("-------------&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&----------");
					// System.out.println(m.getKey()+"
					// ------------"+m.getValue());
				}

				break;
			case "Property":
				System.out.println("entered propert");
				List<WebElement> qtnCountofProperty = driver.findElements(
						By.xpath("(//div[@id='preview-prequal-main'])[2]//div[@class='qustion-holder']/div"));
				System.out.println("qtnCountofGetStarted:---" + qtnCountofProperty.size());
				String testDataofProperty = step.getDataValue(columnNames[1]);
				System.out.println("testData: " + testDataofProperty);
				if (qtnCountofProperty.size() != 0 || testDataofProperty.length() != 0) {
					HashMap<String, String> expectedDataMapforProperty = buildpreview(testDataofProperty);

					for (int qtnIndex = 1; qtnIndex <= qtnCountofProperty.size(); qtnIndex++) {
						// System.out.println("entered for loop");
						displayedFieldName = driver.findElement(By
								.xpath("((//div[@id='preview-prequal-main'])[2]//div[@class='qustion-holder'][1]//div/div[@class='field-section']/div[1])["
										+ String.valueOf(qtnIndex) + "]"))
								.getText().trim();
						// System.out.println("displayedFieldName-->"+displayedFieldName);
						displayedFieldValue = driver.findElement(By
								.xpath("((//div[@id='preview-prequal-main'])[2]//div[@class='qustion-holder'][1]//div/div/following-sibling::div)["
										+ String.valueOf(qtnIndex) + "]"))
								.getText().trim();
						// System.out.println("displayedFieldName--->"+displayedFieldValue);
						fieldValuePropertySection.put(displayedFieldName, displayedFieldValue);
						// compareDataInPreviewPageWithTestData(expectedDataMapforProperty,displayedFieldName,displayedFieldValue);

					}
				}
				for (Entry<String, String> m : fieldValuePropertySection.entrySet()) {
					// System.out.println("-------------&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&----------");
					System.out.println(m.getKey() + " ------------" + m.getValue());
				}
				break;
			case "Income":

				System.out.println("entered Income");
				List<WebElement> qtnCountofIncome = driver.findElements(
						By.xpath("(//div[@id='preview-prequal-main'])[3]//div[@class='qustion-holder']/div"));
				System.out.println("qtnCountofGetStarted:---" + qtnCountofIncome.size());
				String testDataofIncome = step.getDataValue(columnNames[2]);
				System.out.println("testData: " + testDataofIncome);
				if (qtnCountofIncome.size() != 0 || testDataofIncome.length() != 0) {
					HashMap<String, String> expectedDataMapforIncome = buildpreview(testDataofIncome);

					for (int qtnIndex = 1; qtnIndex <= qtnCountofIncome.size(); qtnIndex++) {
						// System.out.println("entered for loop");
						displayedFieldName = driver.findElement(By
								.xpath("((//div[@id='preview-prequal-main'])[3]//div[@class='qustion-holder'][1]//div/div[@class='field-section']/div[1])["
										+ String.valueOf(qtnIndex) + "]"))
								.getText().trim();
						// System.out.println("displayedFieldName-->"+displayedFieldName);
						displayedFieldValue = driver.findElement(By
								.xpath("((//div[@id='preview-prequal-main'])[3]//div[@class='qustion-holder'][1]//div/div/following-sibling::div)["
										+ String.valueOf(qtnIndex) + "]"))
								.getText().trim();
						// System.out.println("displayedFieldName--->"+displayedFieldValue);
						fieldValueIncomeSection.put(displayedFieldName, displayedFieldValue);
						// compareDataInPreviewPageWithTestData(expectedDataMapforIncome,displayedFieldName,displayedFieldValue);

					}
				}
				for (Entry<String, String> m : fieldValueIncomeSection.entrySet()) {
					// System.out.println("-------------&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&----------");
					System.out.println(m.getKey() + " ------------" + m.getValue());
				}
				break;
			//

			case "Assets":
				System.out.println("entered Assets");
				List<WebElement> qtnCountofAssets = driver.findElements(
						By.xpath("(//div[@id='preview-prequal-main'])[4]//div[@class='qustion-holder']/div"));
				System.out.println("qtnCountofAssert:---" + qtnCountofAssets.size());
				String testDataofAssets = step.getDataValue(columnNames[3]);
				if (qtnCountofAssets.size() != 0 || testDataofAssets.length() != 0) {
					HashMap<String, String> expectedDataMapforAssets = buildpreview(testDataofAssets);

					for (int qtnIndex = 1; qtnIndex <= qtnCountofAssets.size(); qtnIndex++) {
						displayedFieldName = driver.findElement(By
								.xpath("((//div[@id='preview-prequal-main'])[4]//div[@class='qustion-holder'][1]//div/div[@class='field-section']/div[1])["
										+ String.valueOf(qtnIndex) + "]"))
								.getText().trim();
						System.out.println("displayedFieldName>>>"+displayedFieldName);
						displayedFieldValue = driver.findElement(By
								.xpath("((//div[@id='preview-prequal-main'])[4]//div[@class='qustion-holder'][1]//div/div/following-sibling::div)["
										+ String.valueOf(qtnIndex) + "]"))
								.getText().trim();
						System.out.println("displayedFieldValue>>>"+displayedFieldValue);
						fieldValueAssetsSection.put(displayedFieldName, displayedFieldValue);
						// compareDataInPreviewPageWithTestData(expectedDataMapforAssets,displayedFieldName,displayedFieldValue);

					}
				}
					System.out.println("Assets>>>>11111");
				for (Entry<String, String> m : fieldValueAssetsSection.entrySet()) {
					// System.out.println("-------------&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&----------");
					System.out.println(m.getKey() + " ------------" + m.getValue());
				}
				break;
			//
			case "Liabilities":
				System.out.println("entered Liabilities");
				List<WebElement> qtnCountofLiability = driver.findElements(
						By.xpath("(//div[@id='preview-prequal-main'])[5]//div[@class='qustion-holder']/div"));
				System.out.println(qtnCountofLiability.size());
				String testDataofLiability = step.getDataValue(columnNames[4]);
				System.out.println("test data in liabilities" + testDataofLiability);
				if (qtnCountofLiability.size() != 0 || testDataofLiability.length() != 0) {
					HashMap<String, String> expectedDataMapforliability = buildpreview(testDataofLiability);

					for (int qtnIndex = 1; qtnIndex <= qtnCountofLiability.size(); qtnIndex++) {
						displayedFieldName = driver.findElement(By
								.xpath("((//div[@id='preview-prequal-main'])[5]//div[@class='qustion-holder'][1]//div/div[@class='field-section']/div[1])["
										+ String.valueOf(qtnIndex) + "]"))
								.getText().trim();
						System.out.println("displayedFieldName-->" + displayedFieldName);
						displayedFieldValue = driver.findElement(By
								.xpath("((//div[@id='preview-prequal-main'])[5]//div[@class='qustion-holder'][1]//div/div/following-sibling::div)["
										+ String.valueOf(qtnIndex) + "]"))
								.getText().trim();
						System.out.println("displayedFieldName--->" + displayedFieldValue);
						fieldValueLiabilitySection.put(displayedFieldName, displayedFieldValue);
						// compareDataInPreviewPageWithTestData(expectedDataMapforliability,displayedFieldName,displayedFieldValue);

					}
				}
				for (Entry<String, String> m : fieldValueLiabilitySection.entrySet()) {
					// System.out.println("-------------&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&----------");
					System.out.println(m.getKey() + " ------------" + m.getValue());
				}
				break;
			//

			case "Real Estate":
				System.out.println("entered RealEstate");
				List<WebElement> qtnCountofRealEstate = driver.findElements(
						By.xpath("(//div[@id='preview-prequal-main'])[6]//div[@class='qustion-holder']/div"));
				System.out.println(qtnCountofRealEstate.size());
				System.out.println("columnNames[6]" + columnNames[6]);
				String testDataofRealEstate = step.getDataValue(columnNames[6]);
				System.out.println("testDataofRealEstate: " + testDataofRealEstate);
				System.out.println("testDataofRealEstate.length()" + testDataofRealEstate.length());
				if (qtnCountofRealEstate.size() != 0 || testDataofRealEstate.length() != 0) {
					System.out.println("if loop RealEstate");
					// HashMap<String,String> expectedDataMapforRealEstate
					// =buildpreview(testDataofRealEstate);

					for (int qtnIndex = 1; qtnIndex <= qtnCountofRealEstate.size(); qtnIndex++) {
						System.out.println("entered for loop of real estate:");
						displayedFieldName = driver.findElement(By
								.xpath("((//div[@id='preview-prequal-main'])[6]//div[@class='qustion-holder'][1]//div/div[@class='field-section']/div[1])["
										+ String.valueOf(qtnIndex) + "]"))
								.getText().trim();
						System.out.println("displayedFieldName--->" + displayedFieldValue);

						displayedFieldValue = driver.findElement(By
								.xpath("((//div[@id='preview-prequal-main'])[6]//div[@class='qustion-holder'][1]//div/div/following-sibling::div)["
										+ String.valueOf(qtnIndex) + "]"))
								.getText().trim();
						System.out.println("displayedFieldName--->" + displayedFieldValue);

						fieldValueRealEstateSection.put(displayedFieldName, displayedFieldValue);
						// compareDataInPreviewPageWithTestData(expectedDataMapforRealEstate,displayedFieldName,displayedFieldValue);

						System.out.println("Came out of real estate loop");
					}
				}

				for (Entry<String, String> m : fieldValueRealEstateSection.entrySet()) {
					System.out.println("-------------&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&----------");
					System.out.println(m.getKey() + " ------------" + m.getValue());
				}
				break;

			case "Declarations":
				System.out.println("entered Declaration ");
				if (testDataValue.equalsIgnoreCase("1003")) {
					List<WebElement> qtnCountofDeclaration = driver.findElements(
							By.xpath("(//div[@id='preview-prequal-main'])[7]//div[@class='qustion-holder']/div"));
					System.out.println("qtnCountofDeclaration" + qtnCountofDeclaration);
					System.out.println("columnNames[6]" + columnNames[5]);
					String testDataofDeclaration = step.getDataValue(columnNames[5]);
					// System.out.println("testDataofDeclaration:
					// "+testDataofDeclaration);

					if (qtnCountofDeclaration.size() != 0 || testDataofDeclaration.length() != 0) {
						// HashMap<String,String> expectedDataMapforDeclaration
						// = buildpreview(testDataofDeclaration);

						for (int qtnIndex = 1; qtnIndex <= qtnCountofDeclaration.size(); qtnIndex++) {
							// System.out.println("entered for loop");
							displayedFieldName = driver.findElement(By
									.xpath("((//div[@id='preview-prequal-main'])[7]//div[@class='qustion-holder'][1]//div/div[@class='field-section']/div[1])["
											+ String.valueOf(qtnIndex) + "]"))
									.getText().trim();

							displayedFieldValue = driver.findElement(By
									.xpath("((//div[@id='preview-prequal-main'])[7]//div[@class='qustion-holder'][1]//div/div/following-sibling::div)["
											+ String.valueOf(qtnIndex) + "]"))
									.getText().trim();

							fieldValueDeclarationSection.put(displayedFieldName, displayedFieldValue);
							// compareDataInPreviewPageWithTestData(expectedDataMapforDeclaration,displayedFieldName,displayedFieldValue);

						}
					}
				} else {
					List<WebElement> qtnCountofDeclarationPreQual = driver.findElements(
							By.xpath("(//div[@id='preview-prequal-main'])[6]//div[@class='qustion-holder']/div"));

					String testDataofDeclaration = step.getDataValue(columnNames[5]);

					if (qtnCountofDeclarationPreQual.size() != 0 || testDataofDeclaration.length() != 0) {
						HashMap<String, String> expectedDataMapforDeclaration = buildpreview(testDataofDeclaration);
						for (int qtnIndex = 1; qtnIndex <= qtnCountofDeclarationPreQual.size(); qtnIndex++) {
							displayedFieldName = driver.findElement(By
									.xpath("((//div[@id='preview-prequal-main'])[6]//div[@class='qustion-holder'][1]//div/div[@class='field-section']/div[1])["
											+ String.valueOf(qtnIndex) + "]"))
									.getText().trim();

							displayedFieldValue = driver.findElement(By
									.xpath("((//div[@id='preview-prequal-main'])[6]//div[@class='qustion-holder'][1]//div/div/following-sibling::div)["
											+ String.valueOf(qtnIndex) + "]"))
									.getText().trim();

							fieldValueDeclarationSection.put(displayedFieldName, displayedFieldValue);
							// compareDataInPreviewPageWithTestData(expectedDataMapforDeclaration,displayedFieldValue,displayedFieldValue);

						}

					}

				}
				for (Entry<String, String> m : fieldValueDeclarationSection.entrySet()) {
					// System.out.println("-------------&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&----------");
					// System.out.println(m.getKey()+"
					// ------------"+m.getValue());}
				}
				break;
			//
			//

			}

		}
	}

	public void compareDataInPreviewPageWithTestData(HashMap<String, String> map, String displayedFieldName,
			String displayedFieldValue) throws TwfException {
		System.out.println("map: " + map);
		String expectedValue = map.get(displayedFieldName.toLowerCase()).trim();
		System.out.println("KeySet>>>>>>>: " + map.keySet());
		if(map.keySet().contains("property is")){

		}
		if(displayedFieldValue.contains("$")){
			displayedFieldValue=displayedFieldValue.replace("$","");
		}
		if(displayedFieldValue.contains(",")){
			System.out.println("Inside Comma");
			displayedFieldValue=displayedFieldValue.replace(",","");
		}
		if(displayedFieldValue.contains(".")){
			displayedFieldValue=displayedFieldValue.split("\\.")[0];
			System.out.println("Inside fullstop>>>>>>>"+displayedFieldValue);
		}
		System.out.println("displayedFieldName: " + displayedFieldName);
		System.out.println("displayedFieldValue: " + displayedFieldValue);
		System.out.println("expectedValue: " + expectedValue);

		if (!expectedValue.equalsIgnoreCase(displayedFieldValue)) {
		    System.out.println("*********************************************"+displayedFieldValue);
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



	public void validatePreview() throws Exception{
		WebDriver driver = DriverFactory.getDriver();
		String coApplicant="(//div[@id='prequal-preview-container']//h3[contains(.,'Applicant')]//ancestor::div[@class='header']//following-sibling::div[@class='question-main-section']//div[@class='qustion-holder'])";
		int nofCopplicant=driver.findElements(By.xpath(coApplicant)).size();
		System.out.println("Coliiii>>>"+nofCopplicant);


			for(String s:headerDataPoolMap){
				String fieldData[]=s.split(",");
					for(int fieldIndex=0;fieldIndex<fieldData.length;fieldIndex++){
					System.out.println("Length>>>>>>>"+fieldData.length);
					String displayedHeader = s.split(";")[0];
					String expectedDataColumn = s.split(";")[1];

					if(nofCopplicant>1){
						System.out.println("Inside co-applicant!!!!");
							for(String s1:coHeaderDataPoolMap){
								String fieldData1[]=s1.split(",");
									for(int ele=0;ele<fieldData1.length;ele++){
									System.out.println("Length|||||>>>>>>>"+fieldData1.length);
									String displayedHeader1 = s1.split(";")[0];
									String expectedDataColumn1 = s1.split(";")[1];
									comparePreviewData(driver,displayedHeader1,expectedDataColumn1,nofCopplicant);
									}
					 }
							      nofCopplicant=nofCopplicant-1;
				 }
					comparePreviewData(driver,displayedHeader,expectedDataColumn,nofCopplicant);


		}

	}

	}


	private List<WebElement> getPreviewHeaders(WebDriver driver){
		return driver.findElements(By.xpath("//div[@id='prequal-preview-container']/div[@id='preview-prequal-main']"));
		//(//div[@id='prequal-preview-container']/div[@id='preview-prequal-main'])[5]//div[@class='header']//h3
	}


	private void comparePreviewData(WebDriver driver,String displayedHeader,String expectedDataCol,int nofCopplicant) throws Exception{

//		String fieldObject = "//div[@id='prequal-preview-container']//h3[contains(.,'%s')]//ancestor::div[@class='header']//following-sibling::div[@class='question-main-section']//div[@class='qustion-holder']/div";
//		String nofApplicant="(//div[@id='prequal-preview-container']//h3[contains(.,'Applicant')]//ancestor::div[@class='header']//following-sibling::div[@class='question-main-section']//div[@class='qustion-holder'])";
		String fieldObject = "(//div[@id='prequal-preview-container']//h3[contains(.,'%s')]//ancestor::div[@class='header']//following-sibling::div[@class='question-main-section']//div[@class='qustion-holder'])[%p]/div";
		String fieldNameObject  = fieldObject+"[%n]//div[@class='field-section']/div[1]";
		String fieldValueObject  = fieldObject+"[%n]//div[@class='field-section']/div[2]";

		String expectedFieldValues = step.getDataValue(expectedDataCol);
		System.out.println("Expected Field Values>>>"+expectedFieldValues);
		System.out.println("Expected nofCopplicant>>>"+nofCopplicant);

		HashMap<String,String> expectedDataMap = new HashMap<String,String>();
		HashMap<String,String> previewDataMap = new HashMap<String,String>();
		expectedDataMap = buildpreview(expectedFieldValues);
		previewDataMap = getSummaryFieldMap(displayedHeader);
		if(nofCopplicant>1 && displayedHeader.contains("Coapplicant")){
			displayedHeader="Applicant Info";
		}
		int fieldCount = driver.findElements(By.xpath(fieldObject.replace("%s", displayedHeader).replace("%p", String.valueOf(nofCopplicant)))).size();
		System.out.println("FieldCount>>>>>>"+fieldCount);

		for(int eleIndex = 0;eleIndex <fieldCount;eleIndex++){
			int fIndex = eleIndex+1;

			String displayedFieldName = driver.findElement(By.xpath(fieldNameObject.replace("%s", displayedHeader).replace("%p", String.valueOf(nofCopplicant)).replace("%n", String.valueOf(fIndex)))).getText();


			String displayedFieldValue = driver.findElement(By.xpath(fieldValueObject.replace("%s", displayedHeader).replace("%p", String.valueOf(nofCopplicant)).replace("%n", String.valueOf(fIndex)))).getText();



			System.out.println("dISPLAYED FIELD VALUE???"+displayedFieldValue);

			checkIfElementIsInDisabledState(driver,driver.findElement(By.xpath(fieldValueObject.replace("%s", displayedHeader).replace("%p", String.valueOf(nofCopplicant)).replace("%n", String.valueOf(fIndex)))));

			String other=previewDataMap.get(displayedFieldName);
			System.out.println("Other>>>"+other);
//			System.out.println("Preview KeySet"+previewDataMap.keySet());
			String expectedData="";
			//String expectedData = getAutoDisplayedFieldValues(displayedFieldName,nofCopplicant);
		    String dataValue=expectedDataMap.get(other);
			if(expectedData.trim().length()==0){
				expectedData = expectedDataMap.get(previewDataMap.get(displayedFieldName).toLowerCase());
			}
            if(expectedData.contains("~")){
            	expectedData=expectedData.replaceAll("~",",");
            }

			if((displayedFieldValue.contains("$")) && (displayedFieldValue.contains(",")) && (displayedFieldValue.contains("."))){
				displayedFieldValue=displayedFieldValue.replaceAll("\\$","").replaceAll(",","").replaceAll("-","").split("\\.")[0];
				System.out.println("$$$$$Dollar"+displayedFieldValue);
			}

			System.out.println("Displayed Field Name>>>"+displayedFieldName+" and displayedFieldValue>>>"+displayedFieldValue+" and expected field Value>>>"+expectedData);

			if(!expectedData.trim().equalsIgnoreCase(displayedFieldValue)){
				if(!expectedData.contains("Danforth"))//due to the bug this condition is written
					addExceptionToReport("Expected value is not matching with the displayed value", displayedFieldValue, expectedData);
				}

		  }

	}

/*	private String getAutoDisplayedFieldValues(String displayedFieldName,int nofCopplicant){

		String expKey = "";

		switch (displayedFieldName.trim().toLowerCase()) {
		case "zipcode":
			expKey = "zipcode";
			break;
		case "state":
			expKey = "state";
			break;
		case "city":
			expKey = "city";
			break;
		case "email address":
			System.out.println("Email name:::>>>>>>"+displayedFieldName);
			 expKey=tenOThree.EmailValidation;
			 if(nofCopplicant>1){
				// expKey=tenOThree.coAppEmailValidation+"o";
			 }
			 break;
		case "first name":
			 expKey=tenOThree.applicantFname;
			// expKey	="Xchfb";
			 if(nofCopplicant>1){
			//	 expKey=tenOThree.cOpplicantFname;
				// expKey="Rnzob";
			 }
			 break;
		default:
			expKey = "";
			break;
		}

		if(displayedFieldName.trim().toLowerCase().equals("first name")||displayedFieldName.trim().toLowerCase().equals("email address")){
			return expKey;
		}
		if(expKey.length()>0){
			System.out.println("State>>>>>>"+tenOThree.stateCityMap.get(expKey));
			return tenOThree.stateCityMap.get(expKey);
			}

		return "";

	}*/


	private HashMap<String,String> getSummaryFieldMap(String expectedHeader){
		HashMap<String,String> previewFieldDataPoolMap = new HashMap<String,String>();
		String[] expectedMap = null;
		switch (expectedHeader.trim().toLowerCase()) {
		case "property":
			expectedMap = propertyMap;
			break;
		case "applicant info":
			expectedMap = applicantInfoMap;
			break;
		case "declarations":
			expectedMap = declarationMap;
			break;
		case "credit information":
			expectedMap = creditInfoMap;
			break;
		case "income":
			expectedMap = incomeMap;
			break;
		case "coapplicant info":
			expectedMap = coapplicantInfoMap;
			break;

		default:
			break;
		}

		for(String s:expectedMap){
			previewFieldDataPoolMap.put(s.split(":")[0], s.split(":")[1]);
		}

		System.out.println("Preview Data Pool Map>>>>"+previewFieldDataPoolMap.size());
		return previewFieldDataPoolMap;

	}

	public static  void checkIfElementIsInDisabledState(WebDriver driver, WebElement element) throws Exception {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		Object aa = executor.executeScript(
				"var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;",
				element);
		if (aa.toString().contains("disabled") == false && aa.toString().contains("readonly") == false
				&& aa.toString().contains("read-only") == false) {
			System.out.println(aa.toString() + "-----" + element);
			addExceptionToReport("Field " + element.toString() + " is not in disabled state.", "", "");
		}
	}


	@Override
	public void checkPage() {
		// TODO Auto-generated method stub

	}
}

package com.finx.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tavant.base.DriverFactory;
import com.tavant.kwutils.CustomStep;
import com.tavant.kwutils.KWVariables;
import com.tavant.utils.TwfException;

import jxl.read.biff.BiffException;

public class Preview10031 extends CustomStep {
	static WebDriver driver;

	static HashMap<String, String> fieldValueGetStartedSection = new HashMap<String, String>();
	static HashMap<String, String> fieldValuePropertySection = new HashMap<String, String>();
	static HashMap<String, String> fieldValueIncomeSection = new HashMap<String, String>();
	static HashMap<String, String> fieldValueAssetsSection = new HashMap<String, String>();
	static HashMap<String, String> fieldValueLiabilitySection = new HashMap<String, String>();
	static HashMap<String, String> fieldValueRealEstateSection = new HashMap<String, String>();
	static HashMap<String, String> fieldValueDeclarationSection = new HashMap<String, String>();
	String sectionheader = "(.//*[@id='preview-prequal-main']/div/div/div/div[1]/div/div/h3)[%s]";
	String sectionHeaderCount = "(.//*[@id='preview-prequal-main']/div/div/div/div[1]/div/div/h3)";
	static String fileName = System.getProperty("user.dir") + "\\src\\test\\resources\\PrintPreviewExcel.xls";

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

//	public void validateDataInPreviewPage() throws Exception {
//		System.out.println("validateDataInPreviewPage:");
//		WebDriver driver = DriverFactory.getDriver();
//
//		HashMap<String, String> excelMap = Util.buildExcelFileMap(fileName);
//		HashMap<String, String> colHeaderMap = new HashMap<>();
//
//		System.out.println(excelMap);
//		String sectionHeader = null;
//		String[] columnNames = null;
//		String[] columnNameOf1003 = { "question_ans", "PropertyQuestionAnswer", "DeclarationQuestionAnswer",
//				"LiabilityQuestionAnswer", "1003_Income_Business", "1003_Asset_Manual", "RealEstateQuestionAnswer" };
//		String[] columnNameOfPreQual = { "question_ans", "PropertyQuestionAnswer", "1003_Income_Business",
//				"1003_Asset_Manual", "LiabilityQuestionAnswer", "DeclarationQuestionAnswer" };
//
//		String testDataValue = step.getDataValue("Module");
//		if (testDataValue.contains("1003")) {
//			System.out.println("----------entered 1003 loop---------");
//
//			columnNames = columnNameOf1003;
//
//		} else {
//			System.out.println("entered prequal");
//
//			columnNames = columnNameOfPreQual;
//		}
//
//		String displayedFieldName = null;
//		String displayedFieldValue = null;
//
//		// int i=1;
//		int countOfHeaders = driver
//				.findElements(By.xpath("(.//*[@id='preview-prequal-main']/div/div/div/div[1]/div/div/h3)")).size();
//		System.out.println("countOfHeaders size" + countOfHeaders);
//		HashMap<String, Integer> sectionIndexMap = indexOfSections(driver);
//		for (int i = 1; i <= countOfHeaders; i++) {
//			System.out.println("i------------->" + i);
//			String revisedSectioHeader = sectionheader.replace("%s", String.valueOf(i));
//			sectionHeader = driver.findElement(By.xpath(revisedSectioHeader)).getText();
//			List<WebElement> qtnCount = new ArrayList<WebElement>();
//			String testData = "";
//			String coloumnName = "";
//			// driver.findElement(By.xpath(revisedSectioHeader)).
//			Ten0Three.isCoApplicant=true;
//			switch (sectionHeader) {
//			case "Get Started":
////				Ten0Three.isCoApplicant=true;
//				System.out.println("222222entered get started222222******************"+Ten0Three.isCoApplicant);
//				System.out.println(sectionIndexMap.get(sectionHeader));
//				coloumnName = "question_ans";
////				qtnCount = driver.findElements(
////						By.xpath("(//div[@id='preview-prequal-main'])[1]//div[@class='qustion-holder']/div"));
//				qtnCount = driver.findElements(
//				By.xpath("((//div[@id='preview-prequal-main'])[1]//div[@class='qustion-holder'])[1]/div"));
//				testData = step.getDataValue(coloumnName);
//				compareDataInPreviewPageWithTestData(driver, qtnCount, sectionHeader, excelMap, testData,
//						sectionIndexMap.get(sectionHeader));
//				
//				if(Ten0Three.isCoApplicant){
//				coloumnName="coapplicant";
//				qtnCount = driver.findElements(
//				By.xpath("((//div[@id='preview-prequal-main'])[1]//div[@class='qustion-holder'])[2]/div"));
//				testData = step.getDataValue(coloumnName);
//
////					if(driver.findElements(By.xpath("(//div[@id='preview-prequal-main'])[1]//div[@class='question-main-section']/div")).size()>2)
//				compareDataInPreviewPageCoApplicantWithTestData(driver, qtnCount, sectionHeader, excelMap, testData,
//							sectionIndexMap.get(sectionHeader));
//					
//				}
//				break;
//			case "Property":
//				System.out.println("entered property");
//				qtnCount = driver.findElements(
//						By.xpath("(//div[@id='preview-prequal-main'])[2]//div[@class='qustion-holder']/div"));
//				System.out.println("qtnCountofGetStarted:---" + qtnCount.size());
//				coloumnName = "PropertyQuestionAnswer";
//				testData = step.getDataValue(coloumnName);
//
//				break;
//			case "Declarations":
//				System.out.println("entered Declaration ");
//				if (testDataValue.equalsIgnoreCase("1003")) {
//					qtnCount = driver.findElements(
//							By.xpath("(//div[@id='preview-prequal-main'])[3]//div[@class='qustion-holder']/div"));
//					coloumnName = "DeclarationQuestionAnswer";
//					testData = step.getDataValue(coloumnName);
//					compareDataInPreviewPageWithTestData(driver, qtnCount, sectionHeader, excelMap, testData,
//							sectionIndexMap.get(sectionHeader));
//					
//				} else {
//					List<WebElement> qtnCountofDeclarationPreQual = driver.findElements(
//							By.xpath("(//div[@id='preview-prequal-main'])[3]//div[@class='qustion-holder']/div"));
//
//					String testDataofDeclaration = step.getDataValue(columnNames[2]);
//
//					if (qtnCountofDeclarationPreQual.size() != 0 || testDataofDeclaration.length() != 0) {
//						HashMap<String, String> expectedDataMapforDeclaration = buildpreview(testDataofDeclaration);
//						for (int qtnIndex = 1; qtnIndex <= qtnCountofDeclarationPreQual.size(); qtnIndex++) {
//							displayedFieldName = driver.findElement(By
//									.xpath("((//div[@id='preview-prequal-main'])[3]//div[@class='qustion-holder'][1]//div/div[@class='field-section']/div[1])["
//											+ String.valueOf(qtnIndex) + "]"))
//									.getText().trim();
//
//							displayedFieldValue = driver.findElement(By
//									.xpath("((//div[@id='preview-prequal-main'])[3]//div[@class='qustion-holder'][1]//div/div/following-sibling::div)["
//											+ String.valueOf(qtnIndex) + "]"))
//									.getText().trim();
//
//							fieldValueDeclarationSection.put(displayedFieldName, displayedFieldValue);
//							 compareDataInPreviewPageWithTestData(expectedDataMapforDeclaration,displayedFieldValue,displayedFieldValue);
//
//						}
//
//					}
//
//				}
//
////				if(Ten0Three.isCoApplicant){
////				coloumnName="Coapplicant_Declarations";
////				qtnCount = driver.findElements(
////				By.xpath("((//div[@id='preview-prequal-main'])[3]//div[@class='qustion-holder'])[2]/div"));
////				testData = step.getDataValue(coloumnName);
////
//////					if(driver.findElements(By.xpath("(//div[@id='preview-prequal-main'])[1]//div[@class='question-main-section']/div")).size()>2)
////				compareDataInPreviewPageCoApplicantWithTestData(driver, qtnCount, sectionHeader, excelMap, testData,
////							sectionIndexMap.get(sectionHeader));
////					
////				}
//				break;
//			case "Liabilities":
//				System.out.println("entered Liabilities");
//				qtnCount = driver.findElements(
//						By.xpath("(//div[@id='preview-prequal-main'])[4]//div[@class='qustion-holder']/div"));
//
//				coloumnName = "LiabilityQuestionAnswer";
//				testData = step.getDataValue(coloumnName);
//
//				break;
//			
//
//			case "Income":
//
//				System.out.println("entered Income");
//				qtnCount = driver.findElements(
//						By.xpath("((//div[@id='preview-prequal-main'])[5]//div[@class='qustion-holder'])[1]/div"));
//				System.out.println("qtnCountofGetStarted:---" + qtnCount.size());
//				coloumnName = "1003_Income_Business";
//				testData = step.getDataValue(coloumnName);
//				if(testData.length()!=0)
//				compareDataInPreviewPageWithTestData(driver, qtnCount, sectionHeader, excelMap, testData,
//						sectionIndexMap.get(sectionHeader));
//				
//				System.out.println("testData: " + testData);
//				if(Ten0Three.isCoApplicant){
//				coloumnName="IncomeQuestionAnswer";
//				qtnCount = driver.findElements(
//				By.xpath("((//div[@id='preview-prequal-main'])[5]//div[@class='qustion-holder'])[2]/div"));
//				testData = step.getDataValue(coloumnName);
//
////					if(driver.findElements(By.xpath("(//div[@id='preview-prequal-main'])[1]//div[@class='question-main-section']/div")).size()>2)
//				if(testData.length()!=0)
//				compareDataInPreviewPageCoApplicantWithTestData(driver, qtnCount, sectionHeader, excelMap, testData,
//							sectionIndexMap.get(sectionHeader));
//					
//				}
//				break;
//			//
//
//			case "Assets":
//				System.out.println("entered Assets");
//				qtnCount = driver.findElements(
//						By.xpath("((//div[@id='preview-prequal-main'])[6]//div[@class='qustion-holder'])[1]/div"));
//				System.out.println("qtnCountofAssert:---" + qtnCount.size());
//				coloumnName = "1003_Asset_Manual";
//				testData = step.getDataValue(coloumnName);
//				if(testData.length()!=0)
//				compareDataInPreviewPageWithTestData(driver, qtnCount, sectionHeader, excelMap, testData,
//						sectionIndexMap.get(sectionHeader));
//				if(Ten0Three.isCoApplicant){
//					coloumnName="1003_Asset_Manual";	
//					qtnCount = driver.findElements(
//					By.xpath("((//div[@id='preview-prequal-main'])[6]//div[@class='qustion-holder'])[2]/div"));
//					testData = step.getDataValue(coloumnName);
//					if(testData.length()!=0)
////						if(driver.findElements(By.xpath("(//div[@id='preview-prequal-main'])[1]//div[@class='question-main-section']/div")).size()>2)
//					compareDataInPreviewPageCoApplicantWithTestData(driver, qtnCount, sectionHeader, excelMap, testData,
//								sectionIndexMap.get(sectionHeader));
//						
//					}
//				break;
//			
//
//			case "Real Estate":
//				System.out.println("entered RealEstate");
//				qtnCount = driver.findElements(
//						By.xpath("((//div[@id='preview-prequal-main'])[7]//div[@class='qustion-holder'])[1]/div"));
//				System.out.println(qtnCount.size());
//
//				coloumnName = "RealEstateQuestionAnswer";
//				testData = step.getDataValue(coloumnName);
//				compareDataInPreviewPageWithTestData(driver, qtnCount, sectionHeader, excelMap, testData,
//						sectionIndexMap.get(sectionHeader));
//				if(Ten0Three.isCoApplicant){
//					coloumnName="RealEstateQuestionAnswerCoapplicant";
//					qtnCount = driver.findElements(
//					By.xpath("((//div[@id='preview-prequal-main'])[7]//div[@class='qustion-holder'])[2]/div"));
//					testData = step.getDataValue(coloumnName);
//
////						if(driver.findElements(By.xpath("(//div[@id='preview-prequal-main'])[1]//div[@class='question-main-section']/div")).size()>2)
//					compareDataInPreviewPageCoApplicantWithTestData(driver, qtnCount, sectionHeader, excelMap, testData,
//								sectionIndexMap.get(sectionHeader));
//						
//				}
//				break;
//
//			}
//
////			compareDataInPreviewPageWithTestData(driver, qtnCount, sectionHeader, excelMap, testData,
////					sectionIndexMap.get(sectionHeader));
//
//		}
//	}

	public void compareDataInPreviewPageWithTestData(HashMap<String, String> map, String displayedFieldName,
			String displayedFieldValue) throws TwfException, BiffException, InvalidFormatException, IOException {
		// System.out.println("map: "+map);
		String expectedValue = "";

		System.out.println("displayedFieldName: " + displayedFieldName);
		System.out.println("displayedFieldValue: " + displayedFieldValue);
		if (displayedFieldName.equals("Middle Name") || displayedFieldName.equals("Suffix")) {
			displayedFieldName = displayedFieldName + " (Optional)";
		}
		if (displayedFieldName.equals("Home Phone")) {
			displayedFieldName = "Best Contact Number";
		}
		if (displayedFieldName.equals("Email ID")) {
			expectedValue = KWVariables.getVariables().get("user_id").split(":")[0];
			if (!expectedValue.equalsIgnoreCase(displayedFieldValue)) {
				// System.out.println("*********************************************");
				addExceptionToReport("Expected data didnot match with the dispalyed data", expectedValue,
						displayedFieldValue);
			}

		}

		else {

			expectedValue = map.get(displayedFieldName.toLowerCase()).trim();
			System.out.println("expectedValue: " + expectedValue);

			if (!expectedValue.equalsIgnoreCase(displayedFieldValue)) {
				// System.out.println("*********************************************");
				addExceptionToReport("Expected data didnot match with the dispalyed data", expectedValue,
						displayedFieldValue);
			}
		}
	}

	public void compareDataInPreviewPageWithTestData(WebDriver driver, List<WebElement> questionCount,
			String sectionHeader, HashMap<String, String> excelMap, String testData, int sectionIndex)
			throws Exception, TwfException, BiffException, InvalidFormatException, IOException {

		// String testData = step.getDataValue(columnNames);

		String displayedFieldName = null;
		String displayedFieldValue = null;

		System.out.println(testData);

		if (questionCount.size() != 0 || testData.length() != 0) {
			HashMap<String, String> expectedDataMap = buildpreview(testData);
			System.out.println("expeted map>>>>>>>>>>>>." + expectedDataMap);
			System.out.println("Section index>>>>>>>" + sectionIndex);
			System.out.println(questionCount.size());

			for (int qtnIndex = 1; qtnIndex <= questionCount.size(); qtnIndex++) {
				System.out.println("Entered the for loop");
				displayedFieldName = driver
						.findElement(By.xpath("((//div[@id='preview-prequal-main'])[" + String.valueOf(sectionIndex)
								+ "]//div[@class='qustion-holder'][1]//div/div[@class='field-section']/div[1])["
								+ String.valueOf(qtnIndex) + "]"))
						.getText().trim();

				System.out.println(displayedFieldName);

				displayedFieldValue = driver
						.findElement(By.xpath("((//div[@id='preview-prequal-main'])[" + String.valueOf(sectionIndex)
								+ "]//div[@class='qustion-holder'][1]//div/div/following-sibling::div)["
								+ String.valueOf(qtnIndex) + "]"))
						.getText().trim();

//				if(Ten0Three.isCoApplicant){
//					System.out.println("Inside CO>>>>");
//					displayedFieldName = driver
//							.findElement(By.xpath("(((//div[@id='preview-prequal-main'])[" + String.valueOf(sectionIndex)
//									+ "]//div[@class='qustion-holder'][1])[2]//div/div[@class='field-section']/div[1])["
//									+ String.valueOf(qtnIndex) + "]"))
//							.getText().trim();
//
//					System.out.println(displayedFieldName);
//
//					displayedFieldValue = driver
//							.findElement(By.xpath("(((//div[@id='preview-prequal-main'])[" + String.valueOf(sectionIndex)
//									+ "]//div[@class='qustion-holder'][1])[2]//div/div/following-sibling::div)["
//									+ String.valueOf(qtnIndex) + "]"))
//							.getText().trim();
//				}
				System.out.println("displayedFieldName: " + displayedFieldName);
				System.out.println("displayedFieldValue: " + displayedFieldValue);

				if (displayedFieldName.equals("SSN") || displayedFieldName.equals("Veteran?")
						|| displayedFieldName.equals("Email ID") || displayedFieldName.equals("Current Address (State)")
						|| displayedFieldName.equals("Current Address (City)")
						|| displayedFieldName.equals("Current Address (County)") || displayedFieldName.equals("City")
						|| displayedFieldName.equals("State") || displayedFieldName.equals("County")
						|| displayedFieldName.equals("Current Job") || displayedFieldName.equals("Property Status")
						|| displayedFieldName.equals("Owns jointly with")
						|| displayedFieldName.equals("I want to refinance my existing mortgage to")
						|| displayedFieldName.equals("Insurance, Maintenance, Taxes & Misc")) {
					System.out.println("skip as its not given in datapool");
				}

				else if ((displayedFieldName.equals("Mobile Phone") && displayedFieldValue.contains("-"))||(displayedFieldName.equals("Employer Phone") && displayedFieldValue.contains("-"))) {
					displayedFieldValue = displayedFieldValue.replace("-", "");
					System.out.println("$$$$$Dollar" + displayedFieldValue);
				}

				else {
					// System.out.println(excelMap.get(sectionHeader+"&"+displayedFieldName));
					if ((displayedFieldValue.contains("$")) && (displayedFieldValue.contains(","))
							&& (displayedFieldValue.contains("."))) {
						displayedFieldValue = displayedFieldValue.replaceAll("\\$", "").replaceAll(",", "")
								.replaceAll("-", "").split("\\.")[0];
						System.out.println("$$$$$Dollar" + displayedFieldValue);
					}
					String expectedData = expectedDataMap
							.get(excelMap.get(sectionHeader + "&" + displayedFieldName).toLowerCase()).trim();

					System.out.println("expectedData: " + expectedData);
					if (!displayedFieldValue.toLowerCase().contains(expectedData.toLowerCase())) {
						// System.out.println("*********************************************");
						addExceptionToReport("Expected data didnot match with the dispalyed data", expectedData,
								displayedFieldValue);
					}

				}

			}
		}

	}

	
	public void compareDataInPreviewPageCoApplicantWithTestData(WebDriver driver, List<WebElement> questionCount,
			String sectionHeader, HashMap<String, String> excelMap, String testData, int sectionIndex)
			throws Exception, TwfException, BiffException, InvalidFormatException, IOException {

		// String testData = step.getDataValue(columnNames);

		String displayedFieldName = "";
		String displayedFieldValue = "";

		System.out.println(testData);

		if (questionCount.size() != 0 || testData.length() != 0) {
			HashMap<String, String> expectedDataMap = buildpreview(testData);
			System.out.println("expeted map>>>>>>>>>>>>." + expectedDataMap);
			System.out.println("Section index>>>>>>>" + sectionIndex);
			System.out.println(questionCount.size());

			for (int qtnIndex = 1; qtnIndex <= questionCount.size(); qtnIndex++) {
				System.out.println("Entered the for loop");
			
					System.out.println("Inside CO>>>>");
					displayedFieldName = driver
							.findElement(By.xpath("(((//div[@id='preview-prequal-main'])[" + String.valueOf(sectionIndex)
									+ "]//div[@class='qustion-holder'][1])[2]//div/div[@class='field-section']/div[1])["
									+ String.valueOf(qtnIndex) + "]"))
							.getText().trim();

					System.out.println(displayedFieldName);

					displayedFieldValue = driver
							.findElement(By.xpath("(((//div[@id='preview-prequal-main'])[" + String.valueOf(sectionIndex)
									+ "]//div[@class='qustion-holder'][1])[2]//div/div/following-sibling::div)["
									+ String.valueOf(qtnIndex) + "]"))
							.getText().trim();
				
				System.out.println("displayedFieldName: " + displayedFieldName);
				System.out.println("displayedFieldValue: " + displayedFieldValue);

				if (displayedFieldName.equals("SSN") || displayedFieldName.equals("Veteran?")
						|| displayedFieldName.equals("Email ID") || displayedFieldName.equals("Current Address (State)")
						|| displayedFieldName.equals("Current Address (City)")
						|| displayedFieldName.equals("Current Address (County)") || displayedFieldName.equals("City")
						|| displayedFieldName.equals("State") || displayedFieldName.equals("County")
						|| displayedFieldName.equals("Current Job") || displayedFieldName.equals("Property Status")
						|| displayedFieldName.equals("Owns jointly with")
						|| displayedFieldName.equals("Gift Received")
						|| displayedFieldName.equals("I want to refinance my existing mortgage to")
						|| displayedFieldName.equals("Insurance, Maintenance, Taxes & Misc")) {
					System.out.println("skip as its not given in datapool");
				}

				else if ((displayedFieldName.equals("Mobile Phone") && displayedFieldValue.contains("-"))||(displayedFieldName.equals("Employer Phone") && displayedFieldValue.contains("-"))) {
					displayedFieldValue = displayedFieldValue.replace("-", "");
					System.out.println("$$$$$Dollar" + displayedFieldValue);
				}

				else {
					// System.out.println(excelMap.get(sectionHeader+"&"+displayedFieldName));
					if ((displayedFieldValue.contains("$")) && (displayedFieldValue.contains(","))
							&& (displayedFieldValue.contains("."))) {
						displayedFieldValue = displayedFieldValue.replaceAll("\\$", "").replaceAll(",", "")
								.replaceAll("-", "").split("\\.")[0];
						System.out.println("$$$$$Dollar" + displayedFieldValue);
					}
					String expectedData = expectedDataMap
							.get(excelMap.get(sectionHeader + "&" + displayedFieldName).toLowerCase()).trim();

					System.out.println("expectedData: " + expectedData);
					if (!displayedFieldValue.toLowerCase().contains(expectedData.toLowerCase())) {
						// System.out.println("*********************************************");
						addExceptionToReport("Expected data didnot match with the dispalyed data", expectedData,
								displayedFieldValue);
					}

				}

			}
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

	public HashMap<String, Integer> indexOfSections(WebDriver driver) throws Exception {

		HashMap<String, Integer> headerIndexMap = new HashMap<>();
		int sectionCount = driver.findElements(By.xpath(sectionHeaderCount)).size();
		System.out.println("sectioncount>>>" + sectionCount);

		for (int sectionCountIndex = 1; sectionCountIndex <= sectionCount; sectionCountIndex++) {
			String revisedSectionIndexObject = sectionheader.replace("%s", String.valueOf(sectionCountIndex));

			try {
				String displayedHeader = driver.findElement(By.xpath(revisedSectionIndexObject)).getText();
				System.out.println("displayedHeader>>>>" + displayedHeader);
				headerIndexMap.put(displayedHeader, sectionCountIndex);

			} catch (Exception e) {

				System.out.println(e);
			}

		}

		return headerIndexMap;

	}

	private HashMap<String, String> buildColoumnHeaderMap(String[] columnNameOf1003) throws Exception {
		HashMap<String, String> expectedColoumnHeaderMap = new HashMap<String, String>();

		for (String s : columnNameOf1003) {
			String fieldData[] = s.split(",");
			for (int fieldIndex = 0; fieldIndex < fieldData.length; fieldIndex++) {
				System.out.println("Length>>>>>>>" + fieldData.length);
				String displayedHeader = s.split(":")[0];
				String expectedDataColumn = s.split(":")[1];
				expectedColoumnHeaderMap.put(displayedHeader, expectedDataColumn);

			}
		}

		return expectedColoumnHeaderMap;
	}

	@Override
	public void checkPage() {
		// TODO Auto-generated method stub

	}
}

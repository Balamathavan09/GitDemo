package com.finx.util;

import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.tavant.base.DriverFactory;
import com.tavant.kwutils.CustomStep;
import com.tavant.kwutils.KWVariables;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TenOThreeSectionSummary extends CustomStep {
	
	
	private static final String JOINTLY = "Jointly"; 

	public void validateGetStartedSummaryPage(HashMap<String, String> expectedDataMap) throws Exception {
		String[] expectedQtns = { "My name is", "I was born on", "My marital status", "My contact details are" };
		String fieldMap = "My name is:First name&1003_GetStarted_Summary_Applicant_Name;My contact details are:Contact number&1003_GetStarted_Summary_Applicant_Mobile_Value;I was born on:1003_GetStarted_Summary_Applicant_DOB;My marital status:1003_GetStarted_Summary_Applicant_Marital_Status";
		HashMap<String, String> summaryPageMap = new HashMap<String, String>();

		String MobileNumberValue = null, HomePhoneValue = null;
		String[] fieldsData = fieldMap.split(";");
		for (int fieldIndex = 0; fieldIndex < fieldsData.length; fieldIndex++) {

			summaryPageMap.put(fieldsData[fieldIndex].split(":")[0], fieldsData[fieldIndex].split(":")[1]);
		}

		for (int qtnIndex = 0; qtnIndex < expectedQtns.length; qtnIndex++) {

			String answer = expectedDataMap.get(expectedQtns[qtnIndex]);
			String expectedFieldName = summaryPageMap.get(expectedQtns[qtnIndex]).split("&")[0];
			System.out.println("ExpectedFieldName  --" + expectedFieldName);
			if (answer.contains(":")) {
				String[] answerSet = answer.split(":");
				for (int ans = 0; ans < answerSet.length; ans++) {
					if (answerSet[ans].contains(expectedFieldName)) {
						String expectedAnswer = answerSet[ans].split("%")[1].split("_")[1];
						System.out.println("expectedAnswer---------" + expectedAnswer);
						String displayedAnswer = getElementByUsing(
								summaryPageMap.get(expectedQtns[qtnIndex]).split("&")[1]).getText();
						System.out.println("displayedAnswer---------" + displayedAnswer);
						if (expectedAnswer.trim().equalsIgnoreCase(displayedAnswer.trim()) == false) {
							addExceptionToReport("Expected Value is not matching with displayed value of field "
									+ expectedFieldName + "in Summary Page.", displayedAnswer, expectedAnswer);
						}
					}
				}

				if (expectedFieldName.equals("Contact number")) {
					for (int ans1 = 0; ans1 < answerSet.length; ans1++) {
						if (answerSet[ans1].contains("Mobile")) {
							MobileNumberValue = answerSet[ans1].split("%")[1].split("_")[1].concat("(M)");
						}
						if (answerSet[ans1].contains("Home")) {
							HomePhoneValue = answerSet[ans1].split("%")[1].split("_")[1].concat("(H) /");
						}

					}
					String expectedAnswer = HomePhoneValue.concat(MobileNumberValue).trim();
					// System.out.println("Contact Number expected value is ---"
					// + expectedAnswer);
					String displayedAnswer = getElementByUsing(summaryPageMap.get(expectedQtns[qtnIndex]).split("&")[1])
							.getText();
					// System.out.println("displayedAnswer---------"+
					// displayedAnswer);
					if (expectedAnswer.trim().equalsIgnoreCase(displayedAnswer.trim()) == false) {
						addExceptionToReport("Expected Value is not matching with displayed value of field "
								+ expectedFieldName + "in Summary Page.", displayedAnswer, expectedAnswer);
					}
				}

			} else {
				String expectedAnswer = getElementByUsing(summaryPageMap.get(expectedQtns[qtnIndex])).getText();
				System.out.println("expectedAnswer---------" + expectedAnswer);
				System.out.println("displayedAnswer---------" + answer);
				if (answer.trim().equalsIgnoreCase(expectedAnswer.trim()) == false) {
					addExceptionToReport("Expected Value is not matching with displayed value of field "
							+ expectedFieldName + "in Summary Page.", expectedAnswer, answer);
				}

			}

		}
		// For verifying Header Value on Summary page

		// String answer2 = expectedDataMap.get("My name is");
		// String expectedHeaderName =
		// answer2.split("%")[1].split(":")[0].split("_")[1];
		String expectedHeaderName = "Applicant";
		System.out.println("expectedHeaderName----" + expectedHeaderName);
		String actualHeaderName = getElementByUsing("1003_GetStarted_Summary_Applicant_Name_Header").getText();
		System.out.println("actualHeaderName----" + actualHeaderName);
		if (expectedHeaderName.trim().equalsIgnoreCase(actualHeaderName.trim()) == false) {
			addExceptionToReport("Expected Value is not matching with displayed value of field " + expectedHeaderName
					+ "in Summary Page.", actualHeaderName, expectedHeaderName);
		}

	}

	public void validatePropertySummaryPage(HashMap<String, String> expectedDataMap) throws Exception {
		String[] Prop_expectedQtns = { "I am purchasing a property for", "The property is located at",
				"I am purchasing a", "I will use this property as" };
		String fieldMap = "I am purchasing a property for:Property value&1003_Property_Summary_Property_Value;The property is located at:State&1003_Property_Summary_Property_location;I am purchasing a:1003_Property_Summary_Property_Type;I will use this property as:1003_Property_Summary_Property_Occupancy";

		System.out.println("inside property summary");
		HashMap<String, String> summaryPageMap = new HashMap<String, String>();

		String[] fieldsData = fieldMap.split(";");
		String expectedStateValue = null, expectedCityValue = null;

		for (int fieldIndex = 0; fieldIndex < fieldsData.length; fieldIndex++) {
			summaryPageMap.put(fieldsData[fieldIndex].split(":")[0], fieldsData[fieldIndex].split(":")[1]);
		}

		for (int qtnIndex = 0; qtnIndex < Prop_expectedQtns.length; qtnIndex++) {
			String answerComb = expectedDataMap.get(Prop_expectedQtns[qtnIndex]);
			System.out.println("answerComb " + answerComb);
			String expectedFieldName = summaryPageMap.get(Prop_expectedQtns[qtnIndex]).split("&")[0];
			System.out.println("expectedFieldName" + expectedFieldName);

			if (answerComb.contains(":")) {

				String[] answerSet = answerComb.split(":");
				for (int ans = 0; ans < answerSet.length; ans++) {

					if (expectedFieldName.trim().equals("State")) {
						for (int i = 0; i < answerSet.length; i++) {
							if (answerSet[i].contains("State"))
								expectedStateValue = answerSet[i].split("%")[1].split("_")[1];

							if (answerSet[i].contains("City"))
								expectedCityValue = answerSet[i].split("%")[1].split("_")[1];

						}
						String expectedValue = expectedStateValue.concat(", ").concat(expectedCityValue);
						System.out.println("expectedStateCity combined Value---" + expectedValue);

						String actualValue = getElementByUsing(
								summaryPageMap.get(Prop_expectedQtns[qtnIndex]).split("&")[1]).getText();
						System.out.println("actualValue combined Value ---" + actualValue);

						if (expectedValue.trim().equalsIgnoreCase(actualValue.trim()) == false) {
							addExceptionToReport("Expected Value is not matching with displayed value of field "
									+ expectedFieldName + "in Summary Page.", actualValue, expectedValue);
						}
					}

					else if (answerSet[ans].contains(expectedFieldName)) {
						String expectedValue = answerSet[ans].split("%")[1].split("_")[1];
						System.out.println("expectedAnswer---------" + expectedValue);
						String actualValue = getElementByUsing(
								summaryPageMap.get(Prop_expectedQtns[qtnIndex]).split("&")[1]).getText();
						if (actualValue.contains("$"))
							actualValue = actualValue.replaceAll(",", "").replace("$", "").trim();
						System.out.println("actualValue---------" + actualValue);

						if (expectedValue.trim().equalsIgnoreCase(actualValue.trim()) == false) {
							addExceptionToReport("Expected Value is not matching with displayed value of field "
									+ expectedFieldName + "in Summary Page.", actualValue, expectedValue);
						}
					}
				}
			}

			else {
				if (answerComb.contains("%")) {
					String expectedValue = answerComb.split("%")[1].split("_")[1];

					// System.out.println("expectedAnswer---------" +
					// expectedValue);
					String actualValue = getElementByUsing(
							summaryPageMap.get(Prop_expectedQtns[qtnIndex]).split("&")[1]).getText();
					if (actualValue.contains("$"))
						actualValue = actualValue.replaceAll(",", "").replace("$", "").trim();
					// System.out.println("actualValue---------" + actualValue);

					if (expectedValue.trim().equalsIgnoreCase(actualValue.trim()) == false) {
						addExceptionToReport("Expected Value is not matching with displayed value of field "
								+ expectedFieldName + "in Summary Page.", actualValue, expectedValue);
					}
				} else {
					String expectedValue = answerComb;
					// System.out.println("ExpectedValue before renaming---"+
					// expectedValue);
					// CHANGE
					if (answerComb.equals("Others")) {
						expectedValue = expectedDataMap.get("Choose one from the below").toString();
						System.out.println(
								"Expected Value for key found in expecteddatamap Others***** " + expectedValue);
					}

					// CHANGE

					switch (expectedValue.trim()) {
					case "My primary home":
						expectedValue = "Primary home";
						break;
					case "A vacation home":
						expectedValue = "Vacation home";
						break;
					case "An investment":
						expectedValue = "Investment";
						break;
					}
					// System.out.println("ExpectedValue after renaming---"+
					// expectedValue);

					// System.out.println("expectedAnswer---------" +
					// expectedValue);
					String actualValue = getElementByUsing(summaryPageMap.get(Prop_expectedQtns[qtnIndex])).getText();
					if (actualValue.contains("&"))
						actualValue = actualValue.split("&")[1];
					// System.out.println("actualValue---------" + actualValue);

					if (expectedValue.trim().equalsIgnoreCase(actualValue.trim()) == false) {
						addExceptionToReport("Expected Value is not matching with displayed value of field "
								+ expectedFieldName + "in Summary Page.", actualValue, expectedValue);
					}

				}

			}

		}
	}

	public void validateAssetSummaryPage(HashMap<String, String> expectedDataMap) throws Exception {
		WebDriver driver = DriverFactory.getDriver();

		// Fieldname or Ques + & + FieldValue-xpath
		String[] Assets_Ques = {"Holds jointly with&1003_Assets_Summary_Account_Type_Header",
				"My asset type&1003_Assets_Summary_Assets_Type",
				"Account number&1003_Assets_Summary_Account_Number_Value", "Amount&1003_Assets_Summary_Amount_Value" };
		String JointAccount = "One of the applicant";
		String JointAccountwithOthers = "Other", Amount = null;
		for (int ques = 0; ques < Assets_Ques.length; ques++) {
			String Fieldname = Assets_Ques[ques].split("&")[0];
			String actualValueXpath = Assets_Ques[ques].split("&")[1];
			System.out.println("Searching key for fieldname***** " + Fieldname);
			for (Map.Entry<String, String> i : expectedDataMap.entrySet()) {

				if (i.getKey().trim().equals(Fieldname.trim())) {

					System.out.println("Key found for field in expecteddatamap***** " + i.getKey());
					String expectedValue = i.getValue();
					System.out.println("Expected Value for key found in expecteddatamap***** " + expectedValue);
					String actualValue = getElementByUsing(actualValueXpath).getText();
					System.out.println("Actual Value for key found in expecteddatamap***** " + actualValue);

					if (i.getValue().equals("Others")) {
						expectedValue = expectedDataMap.get("Choose one from the below").toString();
						System.out.println(
								"Expected Value for key found in expecteddatamap Others***** " + expectedValue);
					}

					if (expectedValue.trim().equalsIgnoreCase(actualValue.trim()) == false) {
						addExceptionToReport("Expected Value is not matching with displayed value of field " + Fieldname
								+ "in Summary Page.", actualValue, expectedValue);
					}
				}

				if (i.getValue().trim().contains(Fieldname.trim())) {
					if (i.getValue().contains(":")) {
						String[] fieldname_array = i.getValue().split(":");
						for (int count = 0; count < fieldname_array.length; count++) {
							if (fieldname_array[count].contains(Fieldname)) {
								String expectedValue = fieldname_array[count].split("%")[1].split("_")[1];
								String actualValue = getElementByUsing(actualValueXpath).getText();

								if (actualValue.trim().equals(JointAccount.trim()))
									actualValue = "Joint";
								if ((expectedValue.trim().equals(JointAccount.trim()))
										|| (expectedValue.trim().equals(JointAccountwithOthers.trim())))
									expectedValue = "Joint";
								if (expectedValue.contains("$"))
									expectedValue = expectedValue.replaceAll(",", "").replace("$", "").trim();
								if (actualValue.contains("$"))
									actualValue = actualValue.replaceAll(",", "").replace("$", "").trim();

								if (Fieldname.trim().equals("Amount")) {
								}

								System.out.println(
										"Expected Value for key found in expecteddatamap***** " + expectedValue);

								System.out.println("Actual Value for key found in expecteddatamap***** " + actualValue);

								if (expectedValue.trim().equalsIgnoreCase(actualValue.trim()) == false) {
									addExceptionToReport("Expected Value is not matching with displayed value of field "
											+ Fieldname + "in Summary Page.", actualValue, expectedValue);
								}
							}
						}
					}
				}

			}

		}
		// String TotalXpath =
		// "//div[@class='co-applicant-main']/div[1]/div[2]/div[1]/div[2]/div[2]/div/div[3]/div/span";
		String AmountFieldXpath = "//div[@class='co-applicant-main']/div[1]/div[2]//div[1]/div[%s]/div[2]/div/div[3]/div/span";
		String GiftAmountFieldXpath = "//div[@class='co-applicant-main']//div[contains(.,'Gift Amount')]/following-sibling::span";
		String AssetType = "//div[@class='co-applicant-main']/div[1]/div[2]//div[1]/div[%s]//div[contains(text(),'Assets type')]/following-sibling::span";
		int cnt = 0;
		cnt = driver.findElements(By.xpath("//div[@class = 'tit row']//span[@class='edit-icon']")).size();
		System.out.println("Count of record " + cnt);

		Double TotalFieldExpectedValue = 0.0;
		for (int i = 1; i <= cnt; i++) {
			// driver.findElement(By.xpath(editIncomeButton.replace("%s",
			// String.valueOf(i)))).click();
			if (driver.findElement(By.xpath(AssetType.replace("%s", String.valueOf(i)))).getText().equals("Gift"))
				Amount = driver.findElement(By.xpath(GiftAmountFieldXpath.replace("%s", String.valueOf(i)))).getText();
			else
				Amount = driver.findElement(By.xpath(AmountFieldXpath.replace("%s", String.valueOf(i)))).getText();
			System.out.println("Amount:==============> "+Amount);

			String Amount1 = Amount.replaceAll(",", "").replace("$", "").trim();
			System.out.println("Amount is========== " + Amount1);
			TotalFieldExpectedValue = TotalFieldExpectedValue + Double.parseDouble(Amount1);
			System.out.println("TotalFieldExpectedValue     " + TotalFieldExpectedValue);
			System.out.println("Amount Sum is " + TotalFieldExpectedValue);
		}

		String TotalFieldExpectedValueString = Double.toString(TotalFieldExpectedValue);
		System.out.println("TotalFieldExpectedValueString     " + TotalFieldExpectedValueString);
		String TotalFieldActualValueString = getElementByUsing("1003_Assets_Summary_Total_Assets_Amount").getText()
				.replaceAll(",", "").replace("$", "").replace("Total Assets", "").trim();
		System.out.println("TotalFieldActualValueString     " + TotalFieldActualValueString);
		Double TotalFieldActualValue = Double.parseDouble(TotalFieldActualValueString);
		System.out.println("TotalFieldActualValue     " + TotalFieldActualValue);
		if (TotalFieldExpectedValue.equals(TotalFieldActualValue) == false)
			addExceptionToReport("Expected Value is not matching with displayed value of field Amount in Summary Page.",
					TotalFieldActualValueString, TotalFieldExpectedValueString);
	}

	public void validateRealEstateSummaryPage(HashMap<String, String> expectedDataMap) throws Exception {

		// Fieldname or Ques + & + FieldValue-xpath
		String[] RE_Ques = { "Holds jointly with&1003_Assets_Summary_Account_Type_Header",
				"Street address line 1&1003_RealEstate_Summary_Property_Address_Value",
				"Property value&1003_RealEstate_Summary_Property_Value",
				"Outstanding balance&1003_RealEstate_Summary_Outstanding_Balance_Value" };
		for (int ques = 0; ques < RE_Ques.length; ques++) {
			String Fieldname = RE_Ques[ques].split("&")[0];
			String actualValueXpath = RE_Ques[ques].split("&")[1];
			System.out.println("Searching key for fieldname***** " + Fieldname);
			for (Map.Entry<String, String> i : expectedDataMap.entrySet()) {

				if (Fieldname.equalsIgnoreCase("Holds jointly with")) {
					String actualValue = getElementByUsing(actualValueXpath).getText();
					String expectedValue = "Joint";
					if (expectedValue.trim().equalsIgnoreCase(actualValue.trim()) == false) {
						addExceptionToReport("Expected Value is not matching with displayed value of field " + Fieldname
								+ "in Summary Page.", actualValue, expectedValue);
					}
				}
				if (i.getKey().trim().equals(Fieldname.trim())) {
					System.out.println("Key found for field in expecteddatamap***** " + i.getKey());
					String expectedValue = i.getValue();
					System.out.println("Expected Value for key found in expecteddatamap***** " + expectedValue);
					String actualValue = getElementByUsing(actualValueXpath).getText();
					System.out.println("Actual Value for key found in expecteddatamap***** " + actualValue);

					if (expectedValue.trim().equalsIgnoreCase(actualValue.trim()) == false) {
						addExceptionToReport("Expected Value is not matching with displayed value of field " + Fieldname
								+ "in Summary Page.", actualValue, expectedValue);
					}
				}

				if (i.getValue().trim().contains(Fieldname.trim())) {
					if (i.getValue().contains(":")) {
						String[] fieldname_array = i.getValue().split(":");
						for (int count = 0; count < fieldname_array.length; count++) {
							if (fieldname_array[count].contains(Fieldname)) {
								String expectedValue = fieldname_array[count].split("%")[1].split("_")[1];
								String actualValue = getElementByUsing(actualValueXpath).getText();

								if (expectedValue.contains("$"))
									expectedValue = expectedValue.replaceAll(",", "").replace("$", "").trim();
								if (actualValue.contains("$"))
									actualValue = actualValue.replaceAll(",", "").replace("$", "").trim();
								System.out.println(
										"Expected Value for key found in expecteddatamap***** " + expectedValue);

								System.out.println("Actual Value for key found in expecteddatamap***** " + actualValue);

								if (expectedValue.trim().equalsIgnoreCase(actualValue.trim()) == false) {
									addExceptionToReport("Expected Value is not matching with displayed value of field "
											+ Fieldname + "in Summary Page.", actualValue, expectedValue);
								}
							}
						}
					}
				}
			}
		}

	}

	public void validateLiabilitySummaryPage(HashMap<String, String> expectedDataMap) throws Exception {
		WebDriver driver = DriverFactory.getDriver();

		// Fieldname or Ques + & + FieldValue-xpath
		String[] Liability_Ques = { "Holds jointly with&1003_Assets_Summary_Account_Type_Header",
				"Lender name&1003_Assets_Summary_Bank_Name_Header",
				"My liability type is&1003_Liability_Summary_Liability_Type_Value",
				"Account number&1003_Liability_Summary_Account_Number_Value",
				"Monthly payment&1003_Liability_Summary_Monthly_Payment_Value",
				"Outstanding balance&1003_Liability_Summary_Outstanding_Balance_Value",
				"Status&1003_Liability_Summary_Status_Value" };

		String JointAccount = "One of the applicant";
		String JointAccountwithOthers = "Other";
		String AmountValue = null;

		HashMap<String, String> summaryPageMap = new HashMap<String, String>();
		for (int ques = 0; ques < Liability_Ques.length; ques++) {
			String Fieldname = Liability_Ques[ques].split("&")[0];
			String actualValueXpath = Liability_Ques[ques].split("&")[1];
			// System.out.println("Searching key for fieldname***** " +
			// Fieldname);
			for (Map.Entry<String, String> i : expectedDataMap.entrySet()) {

				if (i.getKey().trim().equals(Fieldname.trim())) {

					String expectedValue = i.getValue();
					System.out.println("Expected Value for key found in expecteddatamap***** " + expectedValue);
					String actualValue = getElementByUsing(actualValueXpath).getText();
					System.out.println("Actual Value for key found in expecteddatamap***** " + actualValue);
					//System.out.println("Value is ----> " + i.getValue());

					if (i.getValue().trim().contains("Others")) {
						expectedValue = expectedDataMap.get("Choose one from below").toString();
						System.out.println(
								"***Expected Value for key found in expecteddatamap Others***** " + expectedValue);
					}

					if (expectedValue.trim().equalsIgnoreCase(actualValue.trim()) == false) {
						addExceptionToReport("Expected Value is not matching with displayed value of field " + Fieldname
								+ "in Summary Page.", actualValue, expectedValue);
					}
				}

				if (i.getValue().trim().contains(Fieldname.trim())) {
					if (i.getValue().contains(":")) {
						String[] fieldname_array = i.getValue().split(":");
						for (int count = 0; count < fieldname_array.length; count++) {
							if (fieldname_array[count].contains(Fieldname)) {
								String expectedValue = fieldname_array[count].split("%")[1].split("_")[1];
								System.out.println("expectedValue: "+expectedValue);
								String actualValue = getElementByUsing(actualValueXpath).getText();
								System.out.println("actualValue: "+actualValue);

								if (actualValue.trim().equals(JointAccount.trim()))
									actualValue = "Joint";
								if ((expectedValue.trim().equals(JointAccount.trim()))
										|| (expectedValue.trim().equals(JointAccountwithOthers.trim())))
									expectedValue = "Joint";
								if (expectedValue.contains("$"))
									expectedValue = expectedValue.replaceAll(",", "").replace("$", "").trim();
								if (actualValue.contains("$"))
									actualValue = actualValue.replaceAll(",", "").replace("$", "").trim();

								if (Fieldname.trim().equals("Amount"))
									AmountValue = expectedValue;

								System.out.println(
										"Expected Value for key found in expecteddatamap***** " + expectedValue);

								System.out.println("Actual Value for key found in expecteddatamap***** " + actualValue);

								if (expectedValue.trim().equalsIgnoreCase(actualValue.trim()) == false) {
									addExceptionToReport("Expected Value is not matching with displayed value of field "
											+ Fieldname + "in Summary Page.", actualValue, expectedValue);
								}
							}
						}
					}
				}

			}

		}
		// String TotalXpath =
		// "//div[@class='co-applicant-main']/div[1]/div[2]/div[1]/div[2]/div[2]/div/div[3]/div/span";
		// To find the number of records for calculation
		String OutstandingFieldXpath = "//div[@class='co-applicant-main']/div[1]/div[2]//div[1]/div[%s]/div[2]/div/div[3]/div/span";
		String MonthlyPaymentFieldXpath = "//div[@class='co-applicant-main']/div[1]/div[2]//div[1]/div[%s]/div[2]/div/div[4]/div/span";
		String StatusXpath = "//div[@class='co-applicant-main']/div[1]/div[2]//div[1]/div[%s]//div[contains(text(),'Status')][1]/following-sibling::span";
		int cnt = 0;
		String statusOfRecord = null;
		cnt = driver.findElements(By.xpath("//div[@class = 'tit row']//span[@class='edit-icon']")).size();
System.out.println("cnt: "+cnt);
		// Calculation check for Total Monthly Payment
		Double TotalMonthlyPaymentFieldExpectedValue = 0.0;

		for (int i = 1; i <= cnt; i++) {
			statusOfRecord = driver.findElement(By.xpath(StatusXpath.replace("%s", String.valueOf(i)))).getText();
			if (statusOfRecord.equalsIgnoreCase("Include")) {
				String Amount = driver.findElement(By.xpath(MonthlyPaymentFieldXpath.replace("%s", String.valueOf(i))))
						.getText();
				String MonthlyPaymentFieldValue = Amount.replaceAll(",", "").replace("$", "").trim();
				// System.out.println("Amount is " + MonthlyPaymentFieldValue);
				TotalMonthlyPaymentFieldExpectedValue = TotalMonthlyPaymentFieldExpectedValue
						+ Double.parseDouble(MonthlyPaymentFieldValue);
			}
			System.out.println("TotalMonthlyPaymentFieldExpectedValue     " + TotalMonthlyPaymentFieldExpectedValue);
			System.out.println("Amount Sum is " + TotalMonthlyPaymentFieldExpectedValue);
		}

		String TotalMonthlyPaymentFieldExpectedValueString = Double.toString(TotalMonthlyPaymentFieldExpectedValue);
		System.out.println("TotalMonthlyPaymentFieldExpectedValueString: =============="+TotalMonthlyPaymentFieldExpectedValueString);
		String TotalMonthlyPaymentFieldActualValueString = getElementByUsing(
				"1003_Liability_Summary_Total_Monthly_Payment").getText().replaceAll(",", "").replace("$", "").replace("Total Monthly Payment", "").trim();
		System.out.println("TotalMonthlyPaymentFieldActualValueString: --------------"+TotalMonthlyPaymentFieldActualValueString);
		Double TotalMonthlyPaymentFieldActualValue = Double.parseDouble(TotalMonthlyPaymentFieldActualValueString);
		System.out.println("TotalMonthlyPaymentFieldActualValue: -------------"+TotalMonthlyPaymentFieldActualValue);
System.out.println("Before if");
		if (TotalMonthlyPaymentFieldExpectedValue.equals(TotalMonthlyPaymentFieldActualValue) == false){
			System.out.println(TotalMonthlyPaymentFieldExpectedValue);
			System.out.println(TotalMonthlyPaymentFieldActualValue);
			addExceptionToReport("Expected Value is not matching with displayed value of field Amount in Summary Page.",
					TotalMonthlyPaymentFieldActualValueString, TotalMonthlyPaymentFieldExpectedValueString);
		System.out.println("after if");}

		// Calculation check for Total Outstanding Balance
		Double TotalOutstandingBalanceFieldExpectedValue = 0.0;
		for (int i = 1; i <= cnt; i++) {
			String Amount = driver.findElement(By.xpath(OutstandingFieldXpath.replace("%s", String.valueOf(i))))
					.getText();
			String OutstandingBalanceFieldValue = Amount.replaceAll(",", "").replace("$", "").trim();
			System.out.println("Amount is " + OutstandingBalanceFieldValue);
			TotalOutstandingBalanceFieldExpectedValue = TotalOutstandingBalanceFieldExpectedValue
					+ Double.parseDouble(OutstandingBalanceFieldValue);
			System.out.println(TotalOutstandingBalanceFieldExpectedValue);
			/*
			 * System.out.println(
			 * "TotalOutstandingBalanceFieldExpectedValue     " +
			 * TotalOutstandingBalanceFieldExpectedValue);
			 * System.out.println("Amount Sum is " +
			 * TotalOutstandingBalanceFieldExpectedValue);
			 */}

		String TotalOutstandingBalanceFieldExpectedValueString = Double
				.toString(TotalOutstandingBalanceFieldExpectedValue);
		System.out.println("TotalOutstandingBalanceFieldExpectedValueString: "+TotalOutstandingBalanceFieldExpectedValueString);
	
		String TotalOutstandingBalanceFieldActualValueString = getElementByUsing(
				"1003_Liability_Summary_Total_Outstanding_Balance").getText().replaceAll(",", "").replace("$", "").replace("Total Outstanding Balance", "")
						.trim();
		System.out.println("TotalOutstandingBalanceFieldActualValueString"+TotalOutstandingBalanceFieldActualValueString);
		Double TotalOutstandingBalanceFieldActualValue = Double
				.parseDouble(TotalOutstandingBalanceFieldActualValueString);
		System.out.println(TotalOutstandingBalanceFieldActualValue);
		 //System.out.println("TotalOutstandingBalanceFieldActualValue " +
		// TotalOutstandingBalanceFieldActualValue);
		System.out.println(TotalOutstandingBalanceFieldExpectedValue);
		System.out.println(TotalOutstandingBalanceFieldActualValue);
		if (TotalOutstandingBalanceFieldExpectedValue.equals(TotalOutstandingBalanceFieldActualValue) == false)
			addExceptionToReport("Expected Value is not matching with displayed value of field Amount in Summary Page.",
					TotalOutstandingBalanceFieldActualValueString, TotalOutstandingBalanceFieldExpectedValueString);

		validateLiabilityFilterSummaryPage(expectedDataMap);
	}

	public void validateLiabilityFilterSummaryPage(HashMap<String, String> expectedDataMap) throws Exception {
		WebDriver driver = DriverFactory.getDriver();
		String expectedValue = null;

		// validateQuestionAnswerIn1003Liability();

		System.out.println("inside filters");
		
		Set<String> keys = expectedDataMap.keySet();
		for(String key:keys){
			System.out.println("Key under declaration page>>>>>>"+key);
		}

		for (Map.Entry<String, String> i : expectedDataMap.entrySet()) {
			if (i.getKey().contains("Liability details")) {
				String JointValue = i.getValue();
				System.out.println("Joint type --> " + JointValue);
				/*if (i.getValue().contains("Holds jointly with")) {
					if (i.getValue().contains("One of the applicant")) {
						System.out.println("Matched Applicant Joint");
						getElementByUsing("1003_Liability_Summary_Joint_With_Applicant_Filter").click();
						Thread.sleep(3000);
						String[] fieldname_array = i.getValue().split(":");
						for (int count = 0; count < fieldname_array.length; count++) {
							System.out.println("fieldname_array[count]--- " + fieldname_array[count]);
							if (fieldname_array[count].contains("Account number")) {
								expectedValue = fieldname_array[count].split("%")[1].split("_")[1];
								System.out.println("Expected value for JointWithApplicant filter " + expectedValue);
							}
						}
						String actualValue = getElementByUsing("1003_Liability_Summary_Account_Number_Value").getText();
						System.out.println("Actual value for JointWithApplicant filter " + actualValue);

						if (expectedValue.equals(actualValue) == false)
							addExceptionToReport(
									"Expected Value is not matching with displayed value of field Amount in Summary Page.",
									actualValue, expectedValue);

					}
					if (i.getValue().contains("Other")) {
						System.out.println("Matched Other Joint");
						getElementByUsing("1003_Liability_Summary_Joint_With_Others_Filter").click();
						Thread.sleep(3000);
						String[] fieldname_array = i.getValue().split(":");
						for (int count = 0; count < fieldname_array.length; count++) {
							if (fieldname_array[count].contains("Account number")) {
								expectedValue = fieldname_array[count].split("%")[1].split("_")[1];
								System.out.println("Expected value for FilterJointWithOthers filter " + expectedValue);
							}
						}
						String actualValue = getElementByUsing("1003_Liability_Summary_Account_Number_Value").getText();
						System.out.println("Actual value for FilterJointWithOthers filter " + actualValue);

						if (expectedValue.equals(actualValue) == false)
							addExceptionToReport(
									"Expected Value is not matching with displayed value of FilterJointWithApplicant in Summary Page.",
									actualValue, expectedValue);

					}
				} else*/ {
					//getElementByUsing("1003_Liability_Summary_Individual_Filter").click();
					driver.findElement(By.xpath(".//*[@id='filterElements']/li[2]/a")).click();
					Thread.sleep(3000);
					String[] fieldname_array = i.getValue().split(":");
					for (int count = 0; count < fieldname_array.length; count++) {
						if (fieldname_array[count].contains("Account number")) {
							expectedValue = fieldname_array[count].split("%")[1].split("_")[1];
							System.out.println("Expected value for FilterIndividual filter " + expectedValue);
						}
					}
					String actualValue = getElementByUsing("1003_Liability_Summary_Account_Number_Value").getText();
					System.out.println("Actual value for FilterIndividual filter " + actualValue);

					if (expectedValue.equals(actualValue) == false)
						addExceptionToReport(
								"Expected Value is not matching with displayed value of FilterIndividual in Summary Page.",
								actualValue, expectedValue);
				}
			}
		}
		getElementByUsing("1003_Liability_Summary_All_Filter").click();
	}

public void validateCoapplicantGetStartedSummaryPage(HashMap<String, String> expectedDataMap, int index, String fieldMap2) throws Exception {
		
	
	System.out.println("inside validateCoapplicantGetStartedSummaryPage ");
		new DriverFactory();
		WebDriver driver = DriverFactory.getDriver();
		String editIcon = ".//div[%s]/div[@class='tit row']//div[contains(@class,'icons text-right')]/i[@class='fa fa-pencil']";
		String deleteIcon = ".//div[%s]/div[@class='tit row']//div[contains(@class,'icons text-right')]/i[@class='fa fa-trash-o']";
		//String[] expectedQtns = new String[4], expectedQtns2;
		HashMap<String, String> fieldXpathSummary = new HashMap<String, String>();
		HashMap<String, String> summaryPageMap = new HashMap<String, String>();
		
		fieldXpathSummary.put("coApplicantNameHeader", ".//div[%s]/div[@class='tit row']//div[contains(@class,'pad')]");
		fieldXpathSummary.put("firstNameTitleXpath", "(.//div[%s]/div[@class='property-addr']/div//div[contains(@class,'desc-section')]/div[1])[1]");
		fieldXpathSummary.put("firstNameValueXpath", "(.//div[%s]/div[@class='property-addr']/div//div[contains(@class,'desc-section')]/span[1])[1]");
		fieldXpathSummary.put("dateOfBirthTitleXpath","(.//div[%s]/div[@class='property-addr']/div//div[contains(@class,'desc-section')]/div[1])[2]");
		fieldXpathSummary.put("dateOfBirthValueXpath","(.//div[%s]/div[@class='property-addr']/div//div[contains(@class,'desc-section')]/span[1])[2]");
		fieldXpathSummary.put("maritalStatusTitleXpath","(.//div[%s]/div[@class='property-addr']/div//div[contains(@class,'desc-section')]/div[1])[3]");
		fieldXpathSummary.put("maritalStatusValueXpath","(.//div[%s]/div[@class='property-addr']/div//div[contains(@class,'desc-section')]/span[1])[3]");
		fieldXpathSummary.put("contactNumberTitleXpath","(.//div[%s]/div[@class='property-addr']/div//div[contains(@class,'desc-section')]/div[1])[4]");
		fieldXpathSummary.put("contactNumberValueXpath","(.//div[%s]/div[@class='property-addr']/div//div[contains(@class,'desc-section')]/span[1])[4]");
//		if(step.getDataValue("Module").toString().contains("CPS"))
//		{
//		//expectedQtns = new String[] {"Provide spouse details", "Spouse date of birth is", "My marital status", "Spouse contact details are" };
//		fieldMap = "Provide spouse details:First name&firstNameValueXpath;Spouse contact details are:Contact number&contactNumberValueXpath;Spouse date of birth is:dateOfBirthValueXpath;My marital status:maritalStatusValueXpath";
//		}
//		else
//		{
//		//expectedQtns = new String[] { "My name is", "I was born on", "My marital status", "My contact details are" };
//		fieldMap = "My name is:First name&firstNameValueXpath;My contact details are:Contact number&contactNumberValue;I was born on:dateOfBirthValueXpath;My marital status:maritalStatusValueXpath";
//		}
		
		//String fieldMap2 = step.getDataValue("SummaryFieldMapping");
		
		System.out.println("fieldMap2 "+ fieldMap2);
		String[] expectedQtns2 = new String[4];

		String MobileNumberValue = null, HomePhoneValue = null;
		String[] fieldsData = fieldMap2.split(";");
		
		for(int coappIndex = 1; coappIndex <= index; coappIndex++)
		{
		for (int fieldIndex = 0; fieldIndex < fieldsData.length; fieldIndex++) 
		{
			summaryPageMap.put(fieldsData[fieldIndex].split(":")[0], fieldsData[fieldIndex].split(":")[1]);
			expectedQtns2[fieldIndex] = fieldsData[fieldIndex].split(":")[0];
			System.out.println("Test 4------------------------- " + fieldIndex);
		}
		
		System.out.println("expectedQtns2 "+ expectedQtns2);
				
		for (int qtnIndex = 0; qtnIndex < expectedQtns2.length; qtnIndex++) {

			String answer = expectedDataMap.get(expectedQtns2[qtnIndex]);
			String expectedFieldName = summaryPageMap.get(expectedQtns2[qtnIndex]).split("&")[0];
			System.out.println("ExpectedFieldName  --" + expectedFieldName);
			if (answer.contains(":")) {
				String[] answerSet = answer.split(":");
				for (int ans = 0; ans < answerSet.length; ans++) {
					if (answerSet[ans].contains(expectedFieldName)) {
						String expectedAnswer = answerSet[ans].split("%")[1].split("_")[1];
						String elementXpath = summaryPageMap.get(expectedQtns2[qtnIndex]).split("&")[1];
						String displayedAnswer = driver.findElement(By.xpath(fieldXpathSummary.get(elementXpath).replace("%s", String.valueOf(coappIndex+1)))).getText();
						System.out.println("expectedAnswer---------" + expectedAnswer);
						System.out.println("displayedAnswer---------" + displayedAnswer);
						if (expectedAnswer.trim().equalsIgnoreCase(displayedAnswer.trim()) == false) {
							addExceptionToReport("Expected Value is not matching with displayed value of field "
									+ expectedFieldName + "in Summary Page.", displayedAnswer, expectedAnswer);
						}
					}
				}

				if (expectedFieldName.trim().equals("Contact number")) {
					for (int ans1 = 0; ans1 < answerSet.length; ans1++) {
						String fieldvalue = answerSet[ans1].split("%")[1].split("_")[1];
						if (answerSet[ans1].contains("Mobile")) {
							MobileNumberValue = fieldvalue.concat("(M)");
							System.out.println("MobileNumberValue:-------------"+MobileNumberValue);
						}
						if (answerSet[ans1].contains("Home")) {
							HomePhoneValue = fieldvalue.concat("(H) /");
							System.out.println("HomePhoneValue-----------------:"+HomePhoneValue);

						}

					}
					String expectedAnswer = HomePhoneValue.concat(MobileNumberValue).trim();
					System.out.println("expectedAnswer: "+expectedAnswer);
					String elementXpath = summaryPageMap.get(expectedQtns2[qtnIndex]).split("&")[1];
					String displayedAnswer = driver.findElement(By.xpath(fieldXpathSummary.get(elementXpath).replace("%s", String.valueOf(coappIndex+1)))).getText();
					System.out.println("displayedAnswer: "+displayedAnswer);
					System.out.println("expectedAnswer---------" + expectedAnswer);
					System.out.println("displayedAnswer---------" + displayedAnswer);
					if (expectedAnswer.trim().equalsIgnoreCase(displayedAnswer.trim()) == false) {
						addExceptionToReport("Expected Value is not matching with displayed value of field "
								+ expectedFieldName + "in Summary Page.", displayedAnswer, expectedAnswer);
					}
				}

			} else {
				String elementXpath = summaryPageMap.get(expectedQtns2[qtnIndex]);
				String displayedAnswer = driver.findElement(By.xpath(fieldXpathSummary.get(elementXpath).replace("%s", String.valueOf(coappIndex+1)))).getText();
				System.out.println("expectedAnswer---------" + answer);
				System.out.println("displayedAnswer---------" + displayedAnswer);
				if (answer.trim().equalsIgnoreCase(displayedAnswer.trim()) == false) {
					addExceptionToReport("Expected Value is not matching with displayed value of field "
							+ expectedFieldName + "in Summary Page.", displayedAnswer, answer);
				}

			}

		}
		
		//Verification of the Titles and buttons on Summary Screen
		
		driver.findElement(By.xpath(fieldXpathSummary.get("firstNameTitleXpath").replace("%s", String.valueOf(coappIndex)))).isDisplayed();
		driver.findElement(By.xpath(fieldXpathSummary.get("dateOfBirthTitleXpath").replace("%s", String.valueOf(coappIndex)))).isDisplayed();
		driver.findElement(By.xpath(fieldXpathSummary.get("contactNumberTitleXpath").replace("%s", String.valueOf(coappIndex)))).isDisplayed();
		driver.findElement(By.xpath(fieldXpathSummary.get("maritalStatusTitleXpath").replace("%s", String.valueOf(coappIndex)))).isDisplayed();
		driver.findElement(By.xpath(editIcon.replace("%s", String.valueOf(coappIndex+1)))).isDisplayed();
		driver.findElement(By.xpath(deleteIcon.replace("%s", String.valueOf(coappIndex +1)))).isDisplayed();
		
		
		// For verifying Header Value on Summary page
		
		 String expectedHeaderName = "Co Applicant("+index+")";;
		 System.out.println("expectedHeaderName----" + expectedHeaderName);
		 String actualHeaderName = driver.findElement(By.xpath(fieldXpathSummary.get("coApplicantNameHeader").replace("%s", String.valueOf(coappIndex+1)))).getText(); 
		 System.out.println("actualHeaderName----" + actualHeaderName); 
		 if(expectedHeaderName.trim().equalsIgnoreCase(actualHeaderName.trim()) == false) {
		 addExceptionToReport("Expected Value is not matching with displayed value of field " + expectedHeaderName + "in Summary Page.", actualHeaderName,
		 expectedHeaderName); }
		 
		 System.out.println("test finished*****************************");
		 
		}
		System.out.println("test finished*****************************end of loop");
	}

public void validateSummarySheetDetails(HashMap<String,String> summarySheetMap,boolean isDeclarationPage,int applicantCount)throws Exception{
    System.out.println("enterd validateSummarySheetDetails ");
    WebDriver driver = DriverFactory.getDriver();
    Set<String> keys = summarySheetMap.keySet();
//    int applicantCount = 0;
    System.out.println("SUMAAARRRAAYYYYY>>"+summarySheetMap);
    //String displayedTitleObject = "//div[@class[contains(.,'property-details-section')]]/div[@class='row']//div[@class='property-inner'][%s]/div[@class='tit row']//div[@class[contains(.,'absolute')]]";
    String displayedTitleObject = "";
    String displayedTitle  = "";
    if(isDeclarationPage){
           displayedTitleObject = "//div[@class='content-title-sec']//h2";
           displayedTitle = driver.findElement(By.xpath(displayedTitleObject)).getText();
           applicantCount = 1;
    }else{
           applicantCount =driver.findElements(By.xpath("//div[@class[contains(.,'property-details-section')]]/div[@class='row']//div[contains(@class,'property-inner')]")).size();
    //     displayedTitleObject= "//div[@class[contains(.,'property-details-section')]]/div[@class='row']//div[@class='property-inner '][%s]/div[@class='tit row']//div[@class[contains(.,'absolute')]]";
           displayedTitleObject= "//div[@class[contains(.,'property-details-section')]]/div[@class='row']//div[@class[contains(.,'property-inner')]][%s]/div[@class[contains(.,'tit')]]//div[@class[contains(.,'absolute')]]";  
           displayedTitle = driver.findElement(By.xpath(displayedTitleObject.replace("%s", String.valueOf(applicantCount)))).getText();           
    }
 
    String expectedTitle = ""; 
    if(summarySheetMap.containsKey("Title")){ 
           if(summarySheetMap.containsKey(JOINTLY)){
                  expectedTitle = summarySheetMap.get("Title").trim()+"Joint";
                  System.out.println("Under Jointly>>>>"+expectedTitle);
           }else{
                  expectedTitle = summarySheetMap.get("Title").trim();
           }
           
    }
    if(displayedTitle.replace("\n","").trim().contains(expectedTitle) == false){
           System.out.println("displayedTitle>>>>>"+displayedTitle);
           addExceptionToReport("In the Summary Page , expected title value "+expectedTitle+" is not matching with displayed title "+displayedTitle+" under row number "+applicantCount, "", "");
    }
	System.out.println("Ten0Three fname:"+Ten0Three.fname);
	System.out.println("Ten0Three coApplicantFname:"+Ten0Three.coApplicantFname);
	
	String expName="";
    for(String key:keys){
           System.out.println("Key>>>>>"+key);
           if(key.equalsIgnoreCase("Title") == false && key.equalsIgnoreCase(JOINTLY) == false){
          
           String columnNameHeaderObject = "//div[@class[contains(.,'property-details-section')]]/div[@class[contains(.,'row')]]//div[@class[contains(.,'property-inner')]][%r]//div[@class='desc-section']/div[contains(text(),'%n')]";  
           String revisedHeaderObject = columnNameHeaderObject.replace("%r", String.valueOf(1)).replaceAll("%n", key.trim());//String.valueOf(applicantCount)
           System.out.println("Revised header object>>>>>"+revisedHeaderObject);
           try{
           //       String displayedValue = driver.findElement(By.xpath(revisedHeaderObject+"//following-sibling::span")).getText();
        	   String displayedValue = driver.findElement(By.xpath(revisedHeaderObject+"//p")).getText();
                  System.out.println("Displayed value>>>>>"+displayedValue);
                  if(displayedValue.contains("$")){
                        displayedValue = displayedValue.replace("$", "").replaceAll(",", "");
                  }                    
                  
              	if(applicantCount==1 && key.equalsIgnoreCase("name")){
					expName=Ten0Three.fname;   	
					if(displayedValue.trim().equalsIgnoreCase(expName.trim())==false)
						addExceptionToReport("Mismatch in Applicant Name", displayedValue, expName);
				}
				else if(applicantCount==2 && key.equalsIgnoreCase("name")){
					expName=Ten0Three.coApplicantFname; 
					if(displayedValue.trim().equalsIgnoreCase(expName.trim())==false)
						addExceptionToReport("Mismatch in CoApplicant Name", displayedValue, expName);
				}
				else if(summarySheetMap.get(key).trim().toLowerCase().contains(displayedValue.trim().toLowerCase()) == false){                 
                        
                        addExceptionToReport("In the Summary Page , expected value "+summarySheetMap.get(key)+" is not matching with the displayed value "+displayedValue+" of the field "+key, "", "");
                  }
           }catch(Exception e){
                  addExceptionToReport("Exception found during verifying summary page>>>>"+e, "", "");
           
           }
           
           
    }
}

}




public void validateSummarySheetDetailsold(HashMap<String,String> summarySheetMap,boolean isDeclarationPage)throws Exception{
	System.out.println("Entered validateSummarySheetDetails method");
	
	
	WebDriver driver = DriverFactory.getDriver();
	Set<String> keys = summarySheetMap.keySet();
	int applicantCount = 0;
	//String displayedTitleObject = "//div[@class[contains(.,'property-details-section')]]/div[@class='row']//div[@class='property-inner'][%s]/div[@class='tit row']//div[@class[contains(.,'absolute')]]";
	String displayedTitleObject = "";
	String displayedTitle  = "";
	if(isDeclarationPage){
		displayedTitleObject = "//div[@class='content-title-sec']//h2";
		displayedTitle = driver.findElement(By.xpath(displayedTitleObject)).getText();
		applicantCount = 1;
	}else{
		applicantCount =driver.findElements(By.xpath("//div[@class[contains(.,'property-details-section')]]/div[@class='row']//div[@class='property-inner']")).size();
		//displayedTitleObject= "//div[@class[contains(.,'property-details-section')]]/div[@class='row']//div[@class='property-inner'][%s]/div[@class='tit row']//div[@class[contains(.,'absolute')]]";
		displayedTitle = driver.findElement(By.xpath(displayedTitleObject.replace("%s", String.valueOf(applicantCount)))).getText();

	}
	//String displayedTitleObject = "//div[@class='content-title-sec']//h2";
	//String displayedTitle = driver.findElement(By.xpath(displayedTitleObject.replace("%s", String.valueOf(applicantCount)))).getText();
	String expectedTitle = "";
	if(summarySheetMap.containsKey("Title")){
		if(summarySheetMap.containsKey(JOINTLY)){
			expectedTitle = summarySheetMap.get("Title").trim()+"Joint";
			System.out.println("Under Jointly>>>>"+expectedTitle);
		}else{
			expectedTitle = summarySheetMap.get("Title").trim();
		}
		
	}
	System.out.println("Entered validateSummarySheetDetails method and displayedTitle>>>>>"+displayedTitle);
	if(displayedTitle.replace("\n","").trim().contains(expectedTitle) == false){
		System.out.println("displayedTitle>>>>>"+displayedTitle);
		addExceptionToReport("In the Summary Page , expected title value "+expectedTitle+" is not matching with displayed title "+displayedTitle+" under row number "+applicantCount, "", "");
	}
	
	for(String key:keys){
		if(key.equalsIgnoreCase("Title") == false && key.equalsIgnoreCase(JOINTLY) == false){
		//String columnNameHeaderObject = "//div[@class[contains(.,'property-details-section')]]/div[@class='row']//div[@class='property-inner'][%r]/div[@class='property-addr']//div[@class='desc-section']/div[text()='%n']";
			//Comment JOSE//String columnNameHeaderObject = "//div[@class[contains(.,'property-details-section')]]/div[@class[contains(.,'row')]]//div[@class[contains(.,'property-inner')]][%r]/div[@class='property-addr']//div[@class='desc-section']/div[text()='%n']";	
			String columnNameHeaderObject = "//div[@class[contains(.,'property-details-section')]]/div[@class[contains(.,'row')]]//div[@class[contains(.,'property-inner')]][%r]//div[@class='desc-section']/div[text()='%n']";	

			String revisedHeaderObject = columnNameHeaderObject.replace("%r", String.valueOf(applicantCount)).replaceAll("%n", key.trim());
		System.out.println("Revised header object>>>>>"+revisedHeaderObject);
		try{
			String displayedValue = driver.findElement(By.xpath(revisedHeaderObject+"//following-sibling::span")).getText();
			System.out.println("Displayed value>>>>>"+displayedValue);
			if(displayedValue.contains("$")){
				displayedValue = displayedValue.replace("$", "").replaceAll(",", "");
			}
			
			
			if(summarySheetMap.get(key).trim().toLowerCase().contains(displayedValue.trim().toLowerCase()) == false){	
				System.out.println("summarySheetMap.get(key).trim().toLowerCase():======================> "+summarySheetMap.get(key).trim().toLowerCase());
				System.out.println("displayedValue:=====================> "+displayedValue);
				addExceptionToReport("In the Summary Page , expected value "+summarySheetMap.get(key)+" is not matching with the displayed value "+displayedValue+" of the field "+key, "", "");
			}
		}catch(Exception e){
			addExceptionToReport("Exception found during verifying summary page>>>>"+e, "", "");
		
		}
		
		
	}
}

}


	@Override
	public void checkPage() {
		// TODO Auto-generated method stub

	}
}

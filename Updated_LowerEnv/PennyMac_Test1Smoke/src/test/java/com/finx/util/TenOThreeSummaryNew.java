package com.finx.util;

import java.util.HashMap;

import com.tavant.kwutils.CustomStep;

public class TenOThreeSummaryNew extends CustomStep {
	
	public HashMap<String, String> buildExpectedData(String expectedTest) {
		// String expectedTest =
		// "My name is; TA %First name_ test    :ta%Middle name _t: ta%Last name_test : RB %Suffix_Sr.&&My contact details are;ta%Home phone_123-456-7890:ta%Mobile number_1234567890&&I was born on;12/12/1990&&I am a first time home buyer;No";
		HashMap<String, String> dataMap = new HashMap<String, String>();

		String[] qtnArray = expectedTest.split("&&");

		for (int qtnIndex = 0; qtnIndex < qtnArray.length; qtnIndex++) {
			String[] subQtnArray = qtnArray[qtnIndex].split(";");

			if (subQtnArray[1].contains("%")) {
				String[] qtnFieldArray = subQtnArray[1].split(":");
				for (int i = 0; i < qtnFieldArray.length; i++) {
					dataMap.put((qtnFieldArray[i].split("%")[1].split("_")[0])
							.toLowerCase().trim(),
							qtnFieldArray[i].split("%")[1].split("_")[1]);

				}
			} else {
				// System.out.println("qtn1>>>>>"+subQtnArray[0].toLowerCase());
				dataMap.put(subQtnArray[0].toLowerCase().trim(), subQtnArray[1]);
			}

		}

		return dataMap;
	}
	
	public void validateGetStartedSummaryPage(HashMap<String, String> expectedDataMap) throws Exception {
		String[] expectedQtns = { "My name is", "I was born on", "My marital status", "My contact details are" };
		String fieldMap = "My name is:First name&1003_GetStarted_Summary_Applicant_Name;My contact details are:Contact number&1003_GetStarted_Summary_Applicant_Mobile_Value;I was born on:1003_GetStarted_Summary_Applicant_DOB;My marital status:1003_GetStarted_Summary_Applicant_Marital_Status";

		System.out.println(" Expected datamap " + expectedDataMap);
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

	@Override
	public void checkPage() {
		// TODO Auto-generated method stub
		
	}

}

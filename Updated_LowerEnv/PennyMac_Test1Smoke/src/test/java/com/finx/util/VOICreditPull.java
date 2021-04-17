package com.finx.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;
import javax.xml.soap.Detail;

//import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.SystemPropertyUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
//import com.lowagie.text.pdf.PdfReader;
//import com.lowagie.text.pdf.parser.PdfTextExtractor;
import com.tavant.base.DriverFactory;
import com.tavant.kwutils.CustomStep;
import com.tavant.utils.TwfException;

import jxl.read.biff.BiffException;
public class VOICreditPull extends CustomStep {
	private static HashMap<String, HashMap<String, String>> repositoryMap = new HashMap<String, HashMap<String, String>>();
	private static HashMap<String, HashMap<String, String>> dataSourceMap = new HashMap<String, HashMap<String, String>>();
	private static HashMap<String, HashMap<String, String>> creditHistoryMap = new HashMap<String, HashMap<String, String>>();
	private static HashMap<String, HashMap<String, HashMap<String, String>>> annualIncomeMap = new HashMap<String, HashMap<String, HashMap<String, String>>>();
	private static HashMap<String, LinkedList<HashMap<String, String>>> applicantNameDetailsMap1 = new HashMap<String, LinkedList<HashMap<String, String>>>();
	private static HashMap<String, HashMap<String, String>> employerMap = new HashMap<String, HashMap<String, String>>();
	private static HashMap<String, HashMap<String, String>> employerOrderDetailMap = new HashMap<String, HashMap<String, String>>();
	private static HashMap<String, String> voiAppMap = new HashMap<String, String>();
	private static String applicant;
	private static String applicant1;
	static String filepath = System.getProperty("user.dir") + "//downloadFiles//";
	static ArrayList<String> creditBureau = new ArrayList<>();
	private static HashMap<String, HashMap<String, String>> createFieldIDMap = new HashMap<>();
	static HashMap<String, HashMap<String, String>> creditconsumerMap = new HashMap<>();
	static 	HashMap<String, HashMap<String, String>> creditScoreRange = new HashMap<String, HashMap<String, String>>();
	static 	HashMap<String, HashMap<String, HashMap<String, String>>> creditScoredetailsMap = new HashMap<>();
	static 	HashMap<String, HashMap<String, Integer>> creditLiabilityMap = new HashMap<String, HashMap<String, Integer>>();
	static 	HashMap<String, String> bankruptcydetailsMap = new HashMap<>();
	static 	HashMap<String,HashMap<String,String>> rankPercentMap = new 	HashMap<String,HashMap<String,String>>();
	static 	HashMap<String, String> otherdetailsMap = new HashMap<>();
	static ArrayList<String> factaresult = new ArrayList<>();
	static String loanNumber="";
	Util util = new Util();
	
	public VOICreditPull() {
		// TODO Auto-generated constructor stub
		factaresult.clear();
	}


	/**
	 * Purpose : get the credit pull record of particular creditor
	 * 
	 * @param firstName
	 * @param lastName
	 * @param ssn
	 * @param street
	 * @param zipcode
	 * @param state
	 * @param city
	 * @param creditorName
	 * @return
	 * @throws Exception
	 * @author vishal.shrivastava
	 */
	public HashMap<String, HashMap<String, String>> getDetailIndividualCreditPull(
			HashMap<String, String> applicantdetailsMap) throws Exception {
		String response = WebService.getCreditPullResponseFromEncompass(applicantdetailsMap.get("First name"),
				applicantdetailsMap.get("Last name"), applicantdetailsMap.get("ssn"),
				applicantdetailsMap.get("Street Address"), applicantdetailsMap.get("Zipcode"),
				applicantdetailsMap.get("state"), applicantdetailsMap.get("city"));
		return getCreditPullRecords(response);
	}

	public HashMap<String, HashMap<String, String>> getDetailJointCreditPull(
			HashMap<String, String> applicantdetailsMap, HashMap<String, String> CoapplicantdetailsMap)
			throws Exception {
		String response = WebService.getCreditPullResponseFromEncompass_Joint(applicantdetailsMap.get("First name"),
				applicantdetailsMap.get("Last name"), applicantdetailsMap.get("ssn"),
				applicantdetailsMap.get("Street Address"), applicantdetailsMap.get("Zipcode"),
				applicantdetailsMap.get("state"), applicantdetailsMap.get("city"),
				CoapplicantdetailsMap.get("First name"), CoapplicantdetailsMap.get("Last name"),
				CoapplicantdetailsMap.get("ssn"), CoapplicantdetailsMap.get("Street Address"),
				CoapplicantdetailsMap.get("Zipcode"), CoapplicantdetailsMap.get("state"),
				CoapplicantdetailsMap.get("city"));
		return getCreditPullRecords(response);
	}

	/**
	 * purpose : parse the jason and get creditMap for credit pull verification
	 * 
	 * @param jasonString
	 * @return
	 * @throws JSONException
	 * @author vishal.shrivastava
	 */

	HashMap<String, HashMap<String, String>> getCreditPullRecordsOLD(String jasonString) throws JSONException {
		JSONObject obj = new JSONObject(jasonString);
		// String
		// maskedval1="XXXXXXXXXXXXXXXXX",maskedval2="XXXXXXXXXXXXXXXX-X";

		Map<Integer, HashMap<String, String>> recordMap = new HashMap<>();
		HashMap<String, HashMap<String, String>> creditorMap = new HashMap<>();
		JSONArray jasonArrayList = obj.getJSONArray("response").getJSONObject(0).getJSONArray("responsedata")
				.getJSONObject(0).getJSONObject("creditresponse").getJSONArray("creditliability");
		for (int i = 0; i < jasonArrayList.length(); i++) {
			HashMap<String, String> attributeifCreditors = new HashMap<>();
			String accountNumber = (String) jasonArrayList.getJSONObject(i).get("accountIdentifier");

			// String maskedAccountNumber;
			// if(!accountNumber.contains("-"))
			// maskedAccountNumber= maskedval1+
			// accountNumber.substring(accountNumber.length()-3,
			// accountNumber.length());
			// else
			// maskedAccountNumber= maskedval2+
			// accountNumber.substring(accountNumber.length()-3,
			// accountNumber.length());
			// System.out.println("maskedAccountNumber>>"+maskedAccountNumber);
			attributeifCreditors.put("Account number",
					accountNumber.substring(accountNumber.length() - 3, accountNumber.length()));

			String liabilityType = (String) jasonArrayList.getJSONObject(i).get("accountType");
			attributeifCreditors.put("My liability type is", liabilityType.substring(0, 1).toUpperCase()
					+ liabilityType.substring(1, liabilityType.length()).toLowerCase());
			// if(jasonArrayList.getJSONObject(i).get("accountOwnershipType").toString().contains("JOINT"))
			// attributeifCreditors.put("Holds jointly with",
			// (String)
			// jasonArrayList.getJSONObject(i).get("accountOwnershipType"));
			attributeifCreditors.put("Lender name",
					(String) jasonArrayList.getJSONObject(i).getJSONObject("creditor").get("name"));

			if (jasonArrayList.getJSONObject(i).has("unpaidBalanceAmount")) {
				attributeifCreditors.put("Outstanding balance",
						(String) jasonArrayList.getJSONObject(i).get("unpaidBalanceAmount"));
			}
			if (jasonArrayList.getJSONObject(i).has("monthlyPaymentAmount")) {
				// int mothlyPayment = (Integer)
				// jasonArrayList.getJSONObject(i).get("monthlyPaymentAmount");
				int mothlyPayment = Integer
						.valueOf((String) jasonArrayList.getJSONObject(i).get("monthlyPaymentAmount"));
				if (mothlyPayment > 0)
					attributeifCreditors.put("Monthly payment",
							(String) jasonArrayList.getJSONObject(i).get("monthlyPaymentAmount"));
				else
					attributeifCreditors.put("Monthly payment", "");
			}
			recordMap.put(i + 1, attributeifCreditors);
		}
		for (Integer recordSequence : recordMap.keySet()) {
			if (recordMap.get(recordSequence).get("Outstanding balance") != null
					&& Integer.valueOf(recordMap.get(recordSequence).get("Outstanding balance")) > 100)
				creditorMap.put(recordMap.get(recordSequence).get("Lender name") + "_"
						+ recordMap.get(recordSequence).get("Account number"), recordMap.get(recordSequence));
		}

		HashMap<String, String> creditscore = new HashMap<>();
		creditscore.put("borrower", getCreditScore(jasonString, "borrower"));
		creditscore.put("coborrower", getCreditScore(jasonString, "coborrower"));
		creditorMap.put("creditscrore", creditscore);
		// System.out.println(creditorMap);
		return creditorMap;
	}

	/**
	 * purpose : parse the jason and get creditMap for credit pull verification
	 * 
	 * @param jasonString
	 * @return
	 * @throws JSONException
	 * @author vishal.shrivastava
	 */

	HashMap<String, HashMap<String, String>> getCreditPullRecords(String jsonString) throws JSONException {
		JSONObject obj = new JSONObject(jsonString);
		// String
		// maskedval1="XXXXXXXXXXXXXXXXX",maskedval2="XXXXXXXXXXXXXXXX-X";

		Map<Integer, HashMap<String, String>> recordMap = new HashMap<>();
		HashMap<String, HashMap<String, String>> creditorMap = new HashMap<>();
		JSONArray jsonArrayList = obj.getJSONArray("response").getJSONObject(0).getJSONArray("responsedata")
				.getJSONObject(0).getJSONObject("creditresponse").getJSONArray("creditliability");

		// int mapIndex = 0;
		HashMap<String, String> creditscore = new HashMap<>();
		for (int index = 0; index < jsonArrayList.length(); index++) {
			JSONObject jsonObj = (JSONObject) jsonArrayList.get(index);

			if (jsonObj.has("unpaidBalanceAmount")) {
				int unpaidBalanceAmount = Integer.valueOf((String) jsonObj.get("unpaidBalanceAmount"));

				if (unpaidBalanceAmount > 100) {
					HashMap<String, String> attributeifCreditors = new HashMap<>();
					String accountNumber = (String) jsonObj.get("accountIdentifier");
					accountNumber = accountNumber.substring(accountNumber.length() - 3, accountNumber.length());
					attributeifCreditors.put("Account number",
							accountNumber.substring(accountNumber.length() - 3, accountNumber.length()));
					String liabilityType = (String) jsonObj.get("accountType");
					liabilityType = liabilityType.substring(0, 1).toUpperCase()
							+ liabilityType.substring(1, liabilityType.length()).toLowerCase();
					attributeifCreditors.put("My liability type is", liabilityType);
					String lenderName = (String) jsonObj.getJSONObject("creditor").get("name");
					String accountOwnerShipType = (String) jsonObj.get("accountOwnershipType");
					attributeifCreditors.put("Holds jointly with", accountOwnerShipType);
					attributeifCreditors.put("Lender name", lenderName);
					attributeifCreditors.put("Outstanding balance", String.valueOf(unpaidBalanceAmount));
					if (jsonObj.has("monthlyPaymentAmount")) {
						// int mothlyPayment = (Integer)
						// jasonArrayList.getJSONObject(i).get("monthlyPaymentAmount");
						int mothlyPayment = Integer.valueOf((String) jsonObj.get("monthlyPaymentAmount"));
						if (mothlyPayment > 0)
							attributeifCreditors.put("Monthly payment", (String) jsonObj.get("monthlyPaymentAmount"));
						else
							attributeifCreditors.put("Monthly payment", "");
					}
					creditorMap.put(lenderName + "_" + accountNumber + "_" + liabilityType, attributeifCreditors);
				}
			}

		}
		creditscore.put("borrower", getCreditScore(jsonString, "borrower"));
		creditscore.put("coborrower", getCreditScore(jsonString, "coborrower"));
		creditorMap.put("creditscrore", creditscore);
		// System.out.println(creditorMap);
		return creditorMap;
	}

	String getCreditScore(String JasonString, String owner) throws JSONException {
		String OwnerCode = "0";
		if (owner.toLowerCase().equals("coborrower"))
			OwnerCode = "1";
		System.out.println("OwnerCode>>>" + OwnerCode);
		JSONObject obj = new JSONObject(JasonString);
		JSONArray jasonArrayList = obj.getJSONArray("response").getJSONObject(0).getJSONArray("responsedata")
				.getJSONObject(0).getJSONObject("creditresponse").getJSONArray("creditscore");
		ArrayList<Integer> creditscoreList = new ArrayList<>();
		for (int i = 0; i < jasonArrayList.length(); i++) {
			if (!jasonArrayList.getJSONObject(i).get("modelNameType").toString().contains("ENHANCED_DAS")
					&& jasonArrayList.getJSONObject(i).get("borrowerID").toString().split("-")[1].equals(OwnerCode)) {
				System.out.println(jasonArrayList.getJSONObject(i).get("value").toString().replace("+", ""));
				if (Integer.valueOf(jasonArrayList.getJSONObject(i).get("value").toString().replace("+", "")) > 0)
					creditscoreList.add(
							Integer.valueOf(jasonArrayList.getJSONObject(i).get("value").toString().replace("+", "")));
			}
		}
		Collections.sort(creditscoreList);
		String res = "-";
		if (creditscoreList.size() == 3)
			res = String.valueOf(creditscoreList.get(1));
		else if (creditscoreList.size() == 2)
			res = String.valueOf(creditscoreList.get(0));
		else if (creditscoreList.size() == 1)
			res = String.valueOf(creditscoreList.get(0));
		return res;
	}

	public void verifyBorrowerCreditPull() throws Exception {
		verifyIndividualCreditPull("borrower");
	}

	public void verifyCoApplicantCreditPull() throws Exception {
		verifyIndividualCreditPull("coapplicant");
	}

	public void verifyIndividualCreditPull(String borrowerType) throws Exception {
		WebDriver driver = DriverFactory.getDriver();
		String noOfRecordsObeject = "//div[@class='property-inner']";
		String eachRecordObject = "(" + noOfRecordsObeject + ")[%index]";
		int totalNoOfRecords = driver.findElements(By.xpath(noOfRecordsObeject)).size();
		System.out.println("totalNoOfRecords>>>" + totalNoOfRecords);
		HashMap<String, String> applicantdetailsMap = getApplicantsDetails(borrowerType);
		System.out.println("verifyIndividualCreditPull applicantdetailsMap>>" + applicantdetailsMap);
		HashMap<String, HashMap<String, String>> records = getDetailIndividualCreditPull(applicantdetailsMap);
		System.out.println("verifyIndividualCreditPull records>>>" + records);
		System.out.println("verifyIndividualCreditPull records>>>" + records);
		verifyCreditScore(driver, records.get("creditscrore").get("borrower"), borrowerType);
		for (int recordIndex = 1; recordIndex <= totalNoOfRecords; recordIndex++) {
			String eachRecordObjectRevised = eachRecordObject.replace("%index", String.valueOf(recordIndex));
			Thread.sleep(5000);
			String joiningdateAsUniqueKey = getUniqueKeyFromEachRecord(driver, eachRecordObjectRevised);
			System.out.println("joiningdateAsUniqueKey>>" + joiningdateAsUniqueKey);
			HashMap<String, String> creditMap = records.get(joiningdateAsUniqueKey);
			driver.findElement(By.xpath(eachRecordObjectRevised + "//span[@class='edit-icon']")).click();
			verifyEachRecord(driver, creditMap, recordIndex);
			getElementByUsing("TopMenu_Liabilities").click();
		}
	}

	public void verifyEachRecord(WebDriver driver, HashMap<String, String> incomeMap, int recordSequence)
			throws Exception {
		for (String fieldName : incomeMap.keySet()) {
			if (incomeMap.get(fieldName) != null)
				validateValuesInLiabilityPage(fieldName, incomeMap.get(fieldName), recordSequence);
		}
	}

	public void validateValuesInLiabilityPage(String fieldName, String expectedvalue, int recordNo) throws Exception {
		System.out.println("fieldName>>>" + fieldName);
		System.out.println("expectedvalue>>" + expectedvalue);
		WebDriver driver = DriverFactory.getDriver();
		String radioObject = "//label[contains(.,'%placeholder')]//parent::div//following-sibling::button[@class='active-btn']";
		String textObject = "//input[@placeholder[contains(.,'%placeholder')]]";
		String actualvalue = "";
		if (fieldName.contains("Holds jointly with")) {
			verifyJointAccountSelection(driver, fieldName, expectedvalue, recordNo);
			return;
		}
		if (driver.findElements(By.xpath(textObject.replace("%placeholder", fieldName.trim()))).size() > 0) {
			actualvalue = driver.findElement(By.xpath(textObject.replace("%placeholder", fieldName.trim())))
					.getAttribute("value").replace("$", "").replace(",", "");
		} else if (driver.findElements(By.xpath(radioObject.replace("%placeholder", fieldName.trim()))).size() > 0) {
			actualvalue = driver.findElement(By.xpath(radioObject.replace("%placeholder", fieldName.trim()))).getText();
		}
		System.out.println("validateValuesInIncomePage actualvalue>>>" + actualvalue);
		if (!actualvalue.contains(expectedvalue)) {
			addExceptionToReport("Mismatch in verification of Income" + " for record sequence " + recordNo, actualvalue,
					expectedvalue);
		}

	}

	public void verifyJointAccountSelection(WebDriver driver, String fieldName, String expectedValue,
			int recordSequence) throws TwfException {
		String radioSelect = "//h6[contains(.,'%placeholder')]//parent::div//button[@class='active-btn']";
		if (expectedValue.toLowerCase().contains("joint")) {
			if (!(driver.findElements(By.xpath(radioSelect.replace("%placeholder", fieldName.trim()))).size() > 0))
				addExceptionToReport("Joint option is not selected in UI for record sequence " + recordSequence, "",
						"");
		} else {
			if (driver.findElements(By.xpath(radioSelect.replace("%placeholder", fieldName.trim()))).size() > 0)
				addExceptionToReport("Joint option is selected in UI which is not expected for the record sequence "
						+ recordSequence, "", "");
		}

	}

	public HashMap<String, String> getApplicantsDetails(String applicantType) throws Exception {
		System.out.println("inside getApplicantsDetails>>>");
		Ten0Three ten0Three = new Ten0Three();
		HashMap<String, String> applicantdetails = new HashMap<>();
		String applicantData = step.getDataValue("question_ans");
		System.out.println("applicantData????"+applicantData);
		String ssn = step.getDataValue("FinExp_SSN");
		System.out.println("ssn???"+ssn);
		if (applicantType.toLowerCase().contains("coborrower")) {
			applicantData = step.getDataValue("coapplicant");
			ssn = step.getDataValue("FinExp_SSN_Joint");
		} else if (applicantType.toLowerCase().contains("coapplicant")) {
			applicantData = step.getDataValue("coapplicant");
			ssn = step.getDataValue("CoFinExp_SSN");
		}
		HashMap<String, String> tempMap = ten0Three.buildDataMap(applicantData);
		System.out.println("temp???"+applicantData);
		for (String key : tempMap.get("My current address is").split(":")) {
			System.out.println("key>>>>>>??" + key);
			applicantdetails.put(key.split("%")[1].split("_")[0], key.split("%")[1].split("_")[1]);
		}
		for (String key : tempMap.get("My name is").split(":")) {
			System.out.println("key>>>>>>??" + key);
			applicantdetails.put(key.split("%")[1].split("_")[0], key.split("%")[1].split("_")[1]);
		}
		applicantdetails.put("ssn", ssn);
		// applicantdetails.put("co_ssn", co_ssn);
		System.out.println("applicantdetails>>>\n" + applicantdetails);
		return applicantdetails;
	}

	public String getUniqueKeyFromEachRecord(WebDriver driver, String eachrecordxpathObject) throws Exception {
		Util util = new Util();
		String accountNumberObject = eachrecordxpathObject
				+ "//div[@class='desc-section']//div[contains(.,'Account Number')]//parent::div/span";
		String liabilityTypeObject = eachrecordxpathObject
				+ "//div[@class='desc-section']//div[contains(.,'Liability type')]//parent::div/span";
		String creditornameObject = eachrecordxpathObject + "//div[@class='dasd title-sect']";
		util.scrollToElement(driver.findElement(By.xpath(creditornameObject)));
		String AccountNumber = driver.findElement(By.xpath(accountNumberObject)).getText().trim();
		String uniqueKeyObject = driver.findElement(By.xpath(creditornameObject)).getText().trim() + "_"
				+ AccountNumber.substring(AccountNumber.length() - 3, AccountNumber.length()) + "_"
				+ driver.findElement(By.xpath(liabilityTypeObject)).getText().trim();
		return uniqueKeyObject;
	}

	public void verifyJointCreditPull() throws Exception {
		WebDriver driver = DriverFactory.getDriver();
		String noOfRecordsObeject = "//div[@class='property-inner']";
		String eachRecordObject = "(" + noOfRecordsObeject + ")[%index]";
		int totalNoOfRecords = driver.findElements(By.xpath(noOfRecordsObeject)).size();
		System.out.println("totalNoOfRecords>>>" + totalNoOfRecords);
		HashMap<String, String> applicantdetailsMap = getApplicantsDetails("borrower");
		HashMap<String, String> CoapplicantdetailsMap = getApplicantsDetails("coborrower");
		System.out.println("verifyIndividualCreditPull applicantdetailsMap>>\n" + applicantdetailsMap);
		System.out.println("verifyIndividualCreditPull CoapplicantdetailsMap>>\n" + CoapplicantdetailsMap);
		HashMap<String, HashMap<String, String>> records = getDetailJointCreditPull(applicantdetailsMap,
				CoapplicantdetailsMap);
		System.out.println("verifyIndividualCreditPull records>>>" + records);
		verifyCreditScore(driver, records.get("creditscrore").get("borrower"), "Borrower");
		for (int recordIndex = 1; recordIndex <= totalNoOfRecords; recordIndex++) {
			String eachRecordObjectRevised = eachRecordObject.replace("%index", String.valueOf(recordIndex));
			Thread.sleep(5000);
			String joiningdateAsUniqueKey = getUniqueKeyFromEachRecord(driver, eachRecordObjectRevised);
			System.out.println("joiningdateAsUniqueKey>>" + joiningdateAsUniqueKey);
			HashMap<String, String> creditMap = records.get(joiningdateAsUniqueKey);
			driver.findElement(By.xpath(eachRecordObjectRevised + "//span[@class='edit-icon']")).click();
			verifyEachRecord(driver, creditMap, recordIndex);
			getElementByUsing("TopMenu_Liabilities").click();
		}
		Thread.sleep(3000);
		getElementByUsing("1003_CoApplicant_label").click();
		Thread.sleep(3000);
		verifyCreditScore(driver, records.get("creditscrore").get("coborrower"), "Co-Borrower");
	}

	public void verifyCreditScore(WebDriver driver, String expectedScore, String TypeOfBorrower)
			throws TwfException, BiffException, IOException {
		String creditScoreBorrower = expectedScore;
		String actualCreditScore = getElementByUsing("Credit_score").getText().trim();
		System.out.println("actualCreditScore>>>" + actualCreditScore);
		System.out.println("expectedScore>>>" + expectedScore);
		if (!getElementByUsing("Credit_score").getText().trim().equals(creditScoreBorrower)) {
			addExceptionToReport("Credit score is not Matching for " + TypeOfBorrower, actualCreditScore,
					expectedScore);
		}

	}

	public void getcreditconsumerMap(String jsonString) throws JSONException {
		JSONObject obj = new JSONObject(jsonString);
		HashMap<String, HashMap<String, String>> creditorMap = new HashMap<>();
		JSONArray creditconsumerreferralArrayList = obj.getJSONArray("response").getJSONObject(0)
				.getJSONArray("responsedata").getJSONObject(0).getJSONObject("creditresponse")
				.getJSONArray("creditconsumerreferral");
		
		ArrayList<String> creditBureau = new ArrayList<>();
		for (int creditIndex = 0; creditIndex < creditconsumerreferralArrayList.length(); creditIndex++) {
			HashMap<String, String> details = new HashMap<>();
			JSONObject contactdetailsObject = creditconsumerreferralArrayList.getJSONObject(creditIndex);
			details.put("address", (String) contactdetailsObject.get("streetAddress"));
			details.put("city", (String) contactdetailsObject.get("city"));
			details.put("state", (String) contactdetailsObject.get("state"));
			details.put("zip", (String) contactdetailsObject.get("postalCode"));
			details.put("company name", (String) contactdetailsObject.get("name"));
			JSONArray contactPoint = creditconsumerreferralArrayList.getJSONObject(creditIndex).getJSONArray("contactdetail")
					.getJSONObject(0).getJSONArray("contactpoint");	
			for (int i = 0; i < contactPoint.length(); i++) {
				details.put(((String) contactPoint.getJSONObject(i).get("type")).toLowerCase(),
						((String) contactPoint.getJSONObject(i).get("value")).replace("(", "").replace(")", "").replaceAll("-", "").replaceAll(" ", "").trim());
			//	System.out.println("phone????"+(String) contactPoint.getJSONObject(i).get("value"));
			}
			
			
			creditBureau.add((String) contactdetailsObject.get("name"));
			creditconsumerMap.put((String) contactdetailsObject.get("name"), details);
		}


	}
	public void getRanges(String jsonString) throws JSONException {
		String[] moduleMap = { "EquifaxBeacon5.0;EQUIFAX INFORMATION SVCS", "ExperianFairIsaac;EXPERIAN",
				"FICORiskScoreClassic98;TRANS UNION" };
		JSONObject obj = new JSONObject(jsonString);
		JSONArray range = obj.getJSONArray("response").getJSONObject(0).getJSONArray("key");
		List<String> l1 = new ArrayList<>();
		HashMap<String, String> moduleMap1 = new HashMap<String, String>();
		for (int i = 0; i < moduleMap.length; i++) {
			l1.add(moduleMap[i]);
			moduleMap1.put(moduleMap[i].split(";")[0], moduleMap[i].split(";")[1]);
		}
		for (int keyIndex = 0; keyIndex < range.length(); keyIndex++) {
			HashMap<String, String> scoreMap = new HashMap<String, String>();
			String bureauName = (String) range.getJSONObject(keyIndex).get("name");
			if (moduleMap1.keySet().contains(bureauName.split("_")[0])) {
				String valueType = bureauName.split("_")[1].toLowerCase();
				scoreMap.put(valueType, (String) range.getJSONObject(keyIndex).get("value"));
				if (creditScoreRange.keySet().contains(moduleMap1.get(bureauName.split("_")[0]))) {
					HashMap<String, String> cm1 = creditScoreRange.get(moduleMap1.get(bureauName.split("_")[0]));
					cm1.put(valueType, (String) range.getJSONObject(keyIndex).get("value"));
				} else {
					creditScoreRange.put(moduleMap1.get(bureauName.split("_")[0]), scoreMap);
				}
			}
		}
		

	}

	public void getCreditScore(String jsonString) throws JSONException {
		JSONObject obj = new JSONObject(jsonString);
		JSONArray creditscore = obj.getJSONArray("response").getJSONObject(0).getJSONArray("responsedata")
				.getJSONObject(0).getJSONObject("creditresponse").getJSONArray("creditscore");
		String[] moduleMap = { "EQUIFAX INFORMATION SVCS;EQUIFAX_BEACON_5_0", "EXPERIAN;EXPERIAN_FAIR_ISAAC",
				"TRANS UNION;FICO_RISK_SCORE_CLASSIC_04" };
		for (int creditscoreIndex = 0; creditscoreIndex < creditscore.length(); creditscoreIndex++) {
			String moduleTypeNme = ((String) creditscore.getJSONObject(creditscoreIndex).get("modelNameType"));
			for (String modules : moduleMap) {
				if (moduleTypeNme.contains(modules.split(";")[1])) {
					String borrowerId = ((String) creditscore.getJSONObject(creditscoreIndex).get("borrowerID"))
							.split("-")[1];
					HashMap<String, String> detailsMap = new HashMap<>();
					detailsMap.put("score",
							((String) creditscore.getJSONObject(creditscoreIndex).get("value")).replace("+", ""));
					detailsMap.put("date", (String) creditscore.getJSONObject(creditscoreIndex).get("date"));
					detailsMap.put("factainquiriesindicator",
							(String) creditscore.getJSONObject(creditscoreIndex).get("factaInquiriesIndicator"));
					JSONArray factorArray = creditscore.getJSONObject(creditscoreIndex).getJSONArray("factor");
					for (int factorIndex = 0; factorIndex < factorArray.length(); factorIndex++) {
						String factor = (String) factorArray.getJSONObject(factorIndex).get("text");
						//factorArray.getJSONObject(factorIndex).get("code") + ":"
						detailsMap.put("factor-" + (factorIndex + 1), factor);
					}

					HashMap<String, HashMap<String, String>> borrowersMap = new HashMap<>();
					if (borrowerId.equals("0"))
						borrowersMap.put("borrower", detailsMap);
					else if (borrowerId.equals("1"))
						borrowersMap.put("co-borrower", detailsMap);

					creditScoredetailsMap.put(modules.split(";")[0], borrowersMap);

				}

			}

		}
	}

	private List<String> getCreditRepositoryList(JSONObject liabilityObj) throws Exception {
		List<String> repoList = new ArrayList<String>();
		JSONArray creditRepo = liabilityObj.getJSONArray("creditrepository");
		for (int repoIndex = 0; repoIndex < creditRepo.length(); repoIndex++) {
			repoList.add(String.valueOf(creditRepo.getJSONObject(repoIndex).get("sourceType")));

		}
		return repoList;
	}

	private HashMap<String, String> getLateCountList(JSONObject liabilityObj) throws Exception {
		HashMap<String, String> lateCountMap = new HashMap<String, String>();
		JSONObject creditRepo = liabilityObj.getJSONObject("latecount");
		for (int index = 0; index < creditRepo.names().length(); index++) {
			lateCountMap.put(String.valueOf(creditRepo.names().get(index)),
					String.valueOf(creditRepo.get(String.valueOf(creditRepo.names().get(index)))));

		}
		return lateCountMap;
	}

	public void getCreditLiabilityInfomation(String jsonString) throws Exception {
		String[] moduleMap = { "EQUIFAX;EQUIFAX INFORMATION SVCS", "EXPERIAN;EXPERIAN", "TRANS_UNION;TRANS UNION" };
		HashMap<String, String> moduleMapping = new HashMap<String, String>();
		for (String s : moduleMap) {
			String[] a = s.split(";");
			moduleMapping.put(a[0], a[1]);
		}
		JSONObject obj = new JSONObject(jsonString);
		//HashMap<String, HashMap<String, HashMap<String, String>>> creditScoreMap = new HashMap<>();

		JSONArray creditLiability = obj.getJSONArray("response").getJSONObject(0).getJSONArray("responsedata")
				.getJSONObject(0).getJSONObject("creditresponse").getJSONArray("creditliability");
		
		for (int liabilityIndex = 0; liabilityIndex < creditLiability.length(); liabilityIndex++) {

			JSONObject liabilityObj = creditLiability.getJSONObject(liabilityIndex);
			List<String> repoList = getCreditRepositoryList(liabilityObj);
			HashMap<String, String> lcMap = getLateCountList(liabilityObj);

			for (String repo : repoList) {
				HashMap<String, Integer> repoCountMap = new HashMap<String, Integer>();
				if (creditLiabilityMap.containsKey(moduleMapping.get(repo))) {
					repoCountMap = creditLiabilityMap.get(moduleMapping.get(repo));
				}
				for (String k : lcMap.keySet()) {
					String days = k.substring(1, k.length()).trim();

					String val = lcMap.get(k);
					if (repoCountMap.containsKey(days)) {
						int revisedVal = repoCountMap.get(days) + Integer.valueOf(val);
						repoCountMap.put(days, revisedVal);
					} else {
						repoCountMap.put(days, Integer.valueOf(val));
					}

				}

				creditLiabilityMap.put(moduleMapping.get(repo), repoCountMap);
			}
			fetchCreditScorePercen(liabilityObj);
			
		}
		getRepoFromCreditInquiry(jsonString);
		//System.out.println(createFieldIDMap);
	}
	
	public void getRepoFromCreditInquiry(String jsonString) throws Exception {
		JSONObject obj = new JSONObject(jsonString);
		try {
			JSONArray creditInquiry = obj.getJSONArray("response").getJSONObject(0).getJSONArray("responsedata")
					.getJSONObject(0).getJSONObject("creditresponse").getJSONArray("creditinquiry");
			for (int inquiryIndex = 0; inquiryIndex < creditInquiry.length(); inquiryIndex++) {
				fetchCreditScorePercen(creditInquiry.getJSONObject(inquiryIndex));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	

	public void getBankruptcydetails(String jsonString) throws JSONException {
		JSONObject obj = new JSONObject(jsonString);
		JSONArray bankruptcydetailsObj = obj.getJSONArray("response").getJSONObject(0).getJSONArray("responsedata")
				.getJSONObject(0).getJSONObject("creditresponse").getJSONArray("creditpublicrecord");
		try {
			for (int bankruptcyIndex = 0; bankruptcyIndex < bankruptcydetailsObj.length(); bankruptcyIndex++) {
				String borrowerId = ((String) bankruptcydetailsObj.getJSONObject(bankruptcyIndex)
						.getJSONArray("borrowerID").getJSONObject(0).get("borrowerID")).split("-")[1];
				if (borrowerId.equals("0")) {
					bankruptcydetailsMap.put("type",
							((String) bankruptcydetailsObj.getJSONObject(bankruptcyIndex).get("type")).split("_")[2]);

					bankruptcydetailsMap.put("filedDate",
							(String) bankruptcydetailsObj.getJSONObject(bankruptcyIndex).get("filedDate"));
					break;
				}

			}
		} catch (Exception e) {}
		
	}

	public void getOtherCreditInfo(String jsonString) throws JSONException {
		JSONObject obj = new JSONObject(jsonString);
		JSONArray otherdetailsObj = obj.getJSONArray("response").getJSONObject(0).getJSONArray("responsedata")
				.getJSONObject(0).getJSONObject("creditresponse").getJSONArray("creditsummary");
		JSONArray creditLimitAmountobj = obj.getJSONArray("response").getJSONObject(0).getJSONArray("responsedata")
				.getJSONObject(0).getJSONObject("creditresponse").getJSONArray("creditliability");
		for (int creditSummaryIndex = 0; creditSummaryIndex < otherdetailsObj.length(); creditSummaryIndex++) {
			JSONArray dataset = otherdetailsObj.getJSONObject(creditSummaryIndex).getJSONArray("dataset");
			for (int dataIndex = 0; dataIndex < dataset.length(); dataIndex++) {
				String noOfTradeLineObjName = (String) dataset.getJSONObject(dataIndex).get("name");
				if (noOfTradeLineObjName.contains("_TotalTradelinesCount")) {
					otherdetailsMap.put("TotalTradelinesCount", (String) dataset.getJSONObject(dataIndex).get("value"));
					break;
				}
			}
		}
		int highestCreditLimit = 0;
		for (int creditLimit = 0; creditLimit < creditLimitAmountobj.length(); creditLimit++) {
			JSONObject creditLimitvalobj = creditLimitAmountobj.getJSONObject(creditLimit);

			if (creditLimitvalobj.has("creditLimitAmount")) {
				int creditLimitval = Integer.valueOf((String) creditLimitvalobj.get("creditLimitAmount"));
				if (creditLimitval > highestCreditLimit)
					highestCreditLimit = creditLimitval;
			}
		}
		String val=String.valueOf(highestCreditLimit);
		if(!val.contains("."))
			val = String.valueOf(highestCreditLimit)+".00";
		otherdetailsMap.put("Highest Credit Limit", val);
	
	}
	
	
	
	public void fetchCreditScorePercen(JSONObject liabilityObj) throws Exception {
		for (int seqIndex = 0; seqIndex < liabilityObj.getJSONArray("creditFileID").length(); seqIndex++) {
			JSONObject createFieldIDObj = liabilityObj.getJSONArray("creditFileID").getJSONObject(seqIndex);
			String sourceType = (String) createFieldIDObj.get("creditRepositorySourceType");
			String creditScoreID = ((String) createFieldIDObj.get("creditScoreID"));
			//System.out.println("creditScoreID???" + creditScoreID);
			creditScoreID = creditScoreID.split(" ")[creditScoreID.split(" ").length - 1];
			String borrowerId = (String) createFieldIDObj.get("borrowerID");
			HashMap<String, String> sourceScoreMap = new HashMap<String, String>();
			switch (borrowerId.split("-")[1].trim()) {
			case "0":
				sourceScoreMap.put("Type", "borrower");
				sourceScoreMap.put("sourceType", sourceType);
				createFieldIDMap.put(creditScoreID, sourceScoreMap);
				break;
			case "1":
				sourceScoreMap.put("Type", "co-borrower");
				sourceScoreMap.put("sourceType", sourceType);
				createFieldIDMap.put(creditScoreID, sourceScoreMap);
				break;
			}
		}
	}
	
	public void getRankpercent(String jsonString) throws Exception{
		String[] moduleMap = { "EQUIFAX;EQUIFAX INFORMATION SVCS", "EXPERIAN;EXPERIAN", "TRANS_UNION;TRANS UNION" };
		HashMap<String, String> moduleMapping = new HashMap<String, String>();
		for (String s : moduleMap) {
			String[] a = s.split(";");
			moduleMapping.put(a[0], a[1]);
		}
		JSONObject obj = new JSONObject(jsonString);
		JSONArray range = obj.getJSONArray("response").getJSONObject(0).getJSONArray("key");
		HashMap<String, String> values = new HashMap<>();
		for(int rankIndex = 0 ; rankIndex < range.length() ; rankIndex++){
			String nameObj = (String)range.getJSONObject(rankIndex).get("name");
			if(nameObj.contains("CreditScoreRankPercent"))
			values.put(nameObj.split("_")[2],(String)range.getJSONObject(rankIndex).get("value"));
			
		}
		for(String key:values.keySet()){
			//System.out.println("key?????"+key);
			HashMap<String,String> sourceTypeMap = createFieldIDMap.get(key);
			//System.out.println("sourceTypeMap???"+sourceTypeMap);			
			HashMap<String,String> tempMap = new HashMap<String,String>();
			tempMap.put("Type", sourceTypeMap.get("Type").toLowerCase());
			tempMap.put("rank%", values.get(key));
			String sourceType = sourceTypeMap.get("sourceType"); 			
			rankPercentMap.put(moduleMapping.get(sourceType), tempMap);
		}	
}


	public void verifyValues(String parameterNameInEncompass, String encompassValus, String responseValue)throws Exception{
		if(Util.isValidDate(encompassValus)){
			encompassValus= util.formatEncompassFetcheddate(encompassValus);
		}
		System.out.println(parameterNameInEncompass + " encompassValus:["+encompassValus+"]  responseValue:["+responseValue+"]");
		if(!encompassValus.equals(responseValue)){	
			String result = parameterNameInEncompass + " encompassValus:["+encompassValus+"]  responseValue:["+responseValue+"]";
			factaresult.add(result);
		}
	}
	public static ArrayList<String> getFactaResult() throws Exception{
		return factaresult;
	}

	public void verifyFactaValues(String jsonString,HashMap<String, HashMap<String, String>> encompassmap, Map<String, String> encompassresponse) throws Exception {
		getcreditconsumerMap(jsonString);
		getRanges(jsonString);
		getCreditScore(jsonString);
		getCreditLiabilityInfomation(jsonString);
		try{
		getBankruptcydetails(jsonString);
		}catch (Exception e) {
			System.out.println("getBankruptcydetails not found");
		}
		getRankpercent(jsonString);
		getOtherCreditInfo(jsonString);		
		for (String bureauName : creditconsumerMap.keySet()) {
			for (String parameterName : creditconsumerMap.get(bureauName).keySet()) {
				String key = bureauName + " " + parameterName.toLowerCase();
				String expectedvalue = encompassresponse.get(encompassmap.get(key).get("FieldsId"));
				String actualval = creditconsumerMap.get(bureauName).get(parameterName);
				verifyValues(key,expectedvalue,actualval);
				
				//System.out.println("expectedvalue???" + expectedvalue + "???actualval???" + actualval);
			}
		}
		for (String bureauName : creditScoreRange.keySet()) {
			for (String parameterName : creditScoreRange.get(bureauName).keySet()) {
				String key = bureauName + " " + parameterName.toLowerCase();
				String expectedvalue = encompassresponse.get(encompassmap.get(key).get("FieldsId"));
				String actualval = creditScoreRange.get(bureauName).get(parameterName);
				verifyValues(key,expectedvalue,actualval);
				
				//System.out.println("expectedvalue???" + expectedvalue + "???actualval???" + actualval);
			}
		}

		for (String bureauName : creditScoredetailsMap.keySet()) {
			for (String borrowerType : creditScoredetailsMap.get(bureauName).keySet()) {
				for (String parametername : creditScoredetailsMap.get(bureauName).get(borrowerType).keySet()) {
					String key = bureauName + " "+ borrowerType.toLowerCase() + " " + parametername.toLowerCase();
					String expectedvalue = encompassresponse.get(encompassmap.get(key).get("FieldsId"));
					String actualval = creditScoredetailsMap.get(bureauName).get(borrowerType).get(parametername);
					
					verifyValues(key,expectedvalue,actualval);
					//System.out.println("expectedvalue???" + expectedvalue + "???actualval???" + actualval);
				}
			}
		}

		for (String bureauName : creditLiabilityMap.keySet()) {
			for (String parameterName : creditLiabilityMap.get(bureauName).keySet()) {
				String key = bureauName + " " + parameterName.toLowerCase();
				//System.out.println("key???" + key);
				String expectedvalue = encompassresponse.get(encompassmap.get(key).get("FieldsId"));
				String actualval = String.valueOf(creditLiabilityMap.get(bureauName).get(parameterName));
				verifyValues(key,expectedvalue,actualval);
				//System.out.println("expectedvalue???" + expectedvalue + "???actualval???" + actualval);
			}
		}

		for (String parameterName : rankPercentMap.keySet()) {
			String key = parameterName + " " + rankPercentMap.get(parameterName).get("Type") + " " + "rank%";
		//	System.out.println("key???" + key);
 		//	System.out.println("encompassmap11111"+encompassmap.get(key).get("FieldsId"));
			String expectedvalue = encompassresponse.get(encompassmap.get(key).get("FieldsId"));
			String actualval = String.valueOf(rankPercentMap.get(parameterName).get("rank%"));
			verifyValues(key,expectedvalue,actualval);
			//System.out.println("expectedvalue???" + expectedvalue + "???actualval???" + actualval);
		}

		for (String parameterName : otherdetailsMap.keySet()) {
			//System.out.println("parameterName???" + parameterName);
			String expectedvalue = encompassresponse.get(encompassmap.get(parameterName).get("FieldsId"));
			String actualval = String.valueOf(otherdetailsMap.get(parameterName));
			verifyValues(parameterName,expectedvalue,actualval);
			//System.out.println("expectedvalue???" + expectedvalue + "???actualval???" + actualval);
		}

//		System.out.println("creditconsumerMap??"+creditconsumerMap);
//		 System.out.println("creditScoreRange????"+creditScoreRange);
//		 System.out.println("creditScoredetailsMap????"+creditScoredetailsMap);
//		 System.out.println("creditLiabilityMap????"+creditLiabilityMap);
//		 System.out.println("bankruptcydetailsMap???"+bankruptcydetailsMap);
//		 System.out.println("rankPercentMap????"+rankPercentMap);
//		 System.out.println("otherdetailsMap???"+otherdetailsMap);
			
		
//		for(String key : getFactaResult()){
//			System.out.println(key);
//		}
		
		
		
	System.out.println("done");	
		
	}
	public Map<String, String> getFactaResponseFromEncompass() throws Exception {
		//String loanNumber = "7202202377";
		// String guidPayLoad="";
		
		System.out.println("loanNumber inside getFactaResponseFromEncompass"+loanNumber);
		String factapayLoad = "[\"DISCLOSURE.X41\",\"DISCLOSURE.X42\",\"DISCLOSURE.X43\",\"DISCLOSURE.X44\",\"DISCLOSURE.X45\",\"DISCLOSURE.X640\",\"DISCLOSURE.X46\",\"DISCLOSURE.X47\",\"DISCLOSURE.X48\",\"DISCLOSURE.X49\",\"DISCLOSURE.X50\",\"1414\",\"DISCLOSURE.X51\",\"DISCLOSURE.X635\",\"DISCLOSURE.X53\",\"DISCLOSURE.X54\",\"DISCLOSURE.X55\",\"DISCLOSURE.X56\",\"DISCLOSURE.X175\",\"1415\",\"DISCLOSURE.X52\",\"DISCLOSURE.X636\",\"DISCLOSURE.X57\",\"DISCLOSURE.X58\",\"DISCLOSURE.X59\",\"DISCLOSURE.X60\",\"DISCLOSURE.X178\",\"DISCLOSURE.X1\",\"DISCLOSURE.X2\",\"DISCLOSURE.X3\",\"DISCLOSURE.X4\",\"DISCLOSURE.X5\",\"DISCLOSURE.X638\",\"DISCLOSURE.X6\",\"DISCLOSURE.X7\",\"DISCLOSURE.X8\",\"DISCLOSURE.X9\",\"DISCLOSURE.X10\",\"67\",\"DISCLOSURE.X11\",\"DISCLOSURE.X631\",\"DISCLOSURE.X13\",\"DISCLOSURE.X14\",\"DISCLOSURE.X15\",\"DISCLOSURE.X16\",\"DISCLOSURE.X173\",\"60\",\"DISCLOSURE.X12\",\"DISCLOSURE.X632\",\"DISCLOSURE.X17\",\"DISCLOSURE.X18\",\"DISCLOSURE.X19\",\"DISCLOSURE.X20\",\"DISCLOSURE.X176\",\"DISCLOSURE.X21\",\"DISCLOSURE.X22\",\"DISCLOSURE.X23\",\"DISCLOSURE.X24\",\"DISCLOSURE.X25\",\"DISCLOSURE.X639\",\"DISCLOSURE.X26\",\"DISCLOSURE.X27\",\"DISCLOSURE.X28\",\"DISCLOSURE.X29\",\"DISCLOSURE.X30\",\"1450\",\"DISCLOSURE.X31\",\"DISCLOSURE.X633\",\"DISCLOSURE.X33\",\"DISCLOSURE.X34\",\"DISCLOSURE.X35\",\"DISCLOSURE.X36\",\"DISCLOSURE.X174\",\"1452\",\"DISCLOSURE.X32\",\"DISCLOSURE.X634\",\"DISCLOSURE.X37\",\"DISCLOSURE.X38\",\"DISCLOSURE.X39\",\"DISCLOSURE.X40\",\"DISCLOSURE.X177\",\"2324\",\"2325\",\"2326\",\"2328\",\"2329\",\"2331\",\"2332\",\"2333\",\"2334\",\"2558\",\"2559\",\"2560\",\"2564\",\"2567\",\"2568\"]";
		WebService web = new WebService();
		ResponseEntity<?> responseEntity = web.fetchEncompassData(loanNumber, factapayLoad);
		System.out.println("responseEntity???"+responseEntity.getBody().toString());
		if (responseEntity.getStatusCode() != HttpStatus.OK) {
			addExceptionToReport("Fetching Property Info From Encompass failed for Loan Number " + loanNumber
					+ String.valueOf(responseEntity.getStatusCode()) + "200", "", "");
		}
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		ResponseRecord mp = objectMapper.readValue(responseEntity.getBody().toString(), ResponseRecord.class);
		return mp.getAddress();
	}
	
	
	
	
	public void verifyFacataIndividualCreditPullForApplicant() throws Exception {
		//WebDriver driver = DriverFactory.getDriver();
		factaresult.clear();
		Thread.sleep(300000);
		Dashboard dashboard = new Dashboard();
		loanNumber = dashboard.naviagteToDashBoardAndFetchLoanNumber();
//		loanNumber ="7202263946";
		System.out.println("Loan Number????"+loanNumber);
		HashMap<String, String> applicantdetailsMap = getApplicantsDetails("borrower");
		System.out.println("verifyIndividualCreditPull applicantdetailsMap>>" + applicantdetailsMap);
		verifyfactaforIndividualCreditPull(applicantdetailsMap);
		System.out.println("result"+factaresult);
		if(factaresult.size()>0){
			addExceptionToReport("Mismatch in value:-", factaresult+"", "");
		}
		
	}
	public void verifyFacataJointCreditPull() throws Exception {
		factaresult.clear();
		Dashboard dashboard = new Dashboard();
		Thread.sleep(300000);
		loanNumber = dashboard.naviagteToDashBoardAndFetchLoanNumber();
//		loanNumber ="7202274374";
		System.out.println("Loan Number????"+loanNumber);
		HashMap<String, String> applicantdetailsMap = getApplicantsDetails("borrower");
		HashMap<String, String> CoapplicantdetailsMap = getApplicantsDetails("coborrower");
		verifyfactaforJointCreditPull(applicantdetailsMap,CoapplicantdetailsMap);
		if(factaresult.size()>0){
			addExceptionToReport("Mismatch in value:-", factaresult+"", "");
		}
	}
	public void verifyFacataIndividualCreditPullForBothApplicant() throws Exception {
		factaresult.clear();
		Thread.sleep(300000);
		Dashboard dashboard = new Dashboard();
		loanNumber = dashboard.naviagteToDashBoardAndFetchLoanNumber();
		System.out.println("Loan Number????"+loanNumber);		
		HashMap<String, String> applicantdetailsMap = getApplicantsDetails("borrower");
		HashMap<String, String> CoapplicantdetailsMap = getApplicantsDetails("coapplicant");
		verifyfactaforIndividualCreditPull(applicantdetailsMap);
		verifyfactaforIndividualCoapplicant(CoapplicantdetailsMap);
		if(factaresult.size()>0){
			addExceptionToReport("Mismatch in value:-", factaresult+"", "");
		}
	}
	public void verifyfactaforIndividualCreditPull(HashMap<String, String>applicantdetailsMap) throws Exception{
		HashMap<String, HashMap<String, String>> encompassmap = util.buildEncompassMapFile("FactaEncompass.xls");
		Map<String, String> encompassresponse = getFactaResponseFromEncompass();
		System.out.println("done encompassresponse");
		String jsonString = WebService.getCreditPullResponseFromEncompass(applicantdetailsMap.get("First name"),
				applicantdetailsMap.get("Last name"), applicantdetailsMap.get("ssn"),
				applicantdetailsMap.get("Street address line 1"), applicantdetailsMap.get("Zip Code"),
				applicantdetailsMap.get("state"), applicantdetailsMap.get("city"));
		verifyFactaValues(jsonString, encompassmap, encompassresponse);
	}
	
	public void verifyfactaforJointCreditPull(HashMap<String, String>applicantdetailsMap, HashMap<String, String> CoapplicantdetailsMap) throws Exception{
		HashMap<String, HashMap<String, String>> encompassmap = util.buildEncompassMapFile("FactaEncompass.xls");
		Map<String, String> encompassresponse = getFactaResponseFromEncompass();
		String jsonString = WebService.getCreditPullResponseFromEncompass_Joint(applicantdetailsMap.get("First name"),
				applicantdetailsMap.get("Last name"), applicantdetailsMap.get("ssn"),
				applicantdetailsMap.get("Street address line 1"), applicantdetailsMap.get("Zip Code"),
				applicantdetailsMap.get("state"), applicantdetailsMap.get("city"),
				CoapplicantdetailsMap.get("First name"), CoapplicantdetailsMap.get("Last name"),
				CoapplicantdetailsMap.get("ssn"), CoapplicantdetailsMap.get("Street address line 1"),
				CoapplicantdetailsMap.get("Zip Code"), CoapplicantdetailsMap.get("state"),
				CoapplicantdetailsMap.get("city"));
		verifyFactaValues(jsonString, encompassmap, encompassresponse);
	}
	
	public void verifyfactaforIndividualCoapplicant(HashMap<String, String>CoapplicantdetailsMap) throws Exception{
		HashMap<String, HashMap<String, String>> encompassmap = util.buildEncompassMapFile("FactaEncompass.xls");
		Map<String, String> encompassresponse = getFactaResponseFromEncompass();		
		String jsonString = WebService.getCreditPullResponseFromEncompass(CoapplicantdetailsMap.get("First name"),
				CoapplicantdetailsMap.get("Last name"), CoapplicantdetailsMap.get("ssn"),
				CoapplicantdetailsMap.get("Street Address"), CoapplicantdetailsMap.get("Zipcode"),
				CoapplicantdetailsMap.get("state"), CoapplicantdetailsMap.get("city"));
		for (String bureauName : creditScoredetailsMap.keySet()) {
			for (String borrowerType : creditScoredetailsMap.get(bureauName).keySet()) {
				for (String parametername : creditScoredetailsMap.get(bureauName).get(borrowerType).keySet()) {
					String key = bureauName + " co-" + borrowerType.toLowerCase() + " " + parametername.toLowerCase();
					String expectedvalue = encompassresponse.get(encompassmap.get(key).get("FieldsId"));
					String actualval = creditScoredetailsMap.get(bureauName).get(borrowerType).get(parametername);
					verifyValues(key,expectedvalue,actualval);
				}
			}
		}
//		for(String key : getFactaResult()){
//			System.out.println(key);
//		}
		
		

	}
	
	
	
	
	
	
	
	@Override
	public void checkPage() {
		// TODO Auto-generated method stub
		
	}
}
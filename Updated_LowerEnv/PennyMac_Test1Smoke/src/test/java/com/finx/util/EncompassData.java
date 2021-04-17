package com.finx.util;

import java.io.IOException;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.impl.client.HttpClients;
//import org.jboss.netty.util.internal.SystemPropertyUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tavant.base.DriverFactory;
import com.tavant.kwutils.CustomStep;
import com.tavant.kwutils.KWVariables;
import com.tavant.utils.TwfException;

import jxl.read.biff.BiffException;
public class EncompassData extends CustomStep {

//	Util util = new Util();
	//Ten0Three tenoThree= new Ten0Three();

	String getStartedPayLoad = "[\"cX.SOURCEOFLOAN\",\"cX.TCPAFlAGforPrimaryBORR\",\"cX.TCPAPhnForPri\",\"4000\",\"4002\",\"4001\",\"4003\",\"66\",\"955\",\"FR0112\",\"954\",\"1402\",\"1490\",\"934\",\"19\",\"156\",\"VAVOB.X72\",\"FR0115\",\"FR0104\",\"FR0108\",\"FR0107\",\"FR0106\",\"52\",\"1240\",\"FR0124\",\"FR0315\",\"FR0307\",\"FR0306\",\"65\",\"1416\",\"fR0108\",\"54\",\"1419\",\"1417\",\"fR0326\"]";//\"1822\",
	//String getStartedPayLoad = "[\"4000\",\"4001\",\"4002\",\"4003\",\"66\",\"1490\",\"1822\",\"1402\",\"934\",\"19\",\"fR0108\",\"156\",\"954\",\"955\",\"VAVOB.X72\",\"FR0115\",\"FR0104\",\"FR0112\",\"FR0124\",\"52\",\"1416\",\"1419\"]";
	String getStartedPayLoad_URLA="";
			//"[\"URLA.X123\",\"URLA.X124\",\"URLA.X17\",\"53\",\"FR0126\",\"BR0122\",\"BR0222\",\"FR0312\",\"FR0324\",\"URLA.X197\",\"URLA.X11\",\"CX.NBS.NAME\",\"CX.NBS.PHONE\",\"CX.NBS.CELL\",\"CX.NBS.ADDRESS\",\"CX.NBS.Zip\",\"CX.NBS.STATE\",\"CX.NBS.CITY\",\"cX.SOURCEOFLOAN\",\"4000\",\"4002\",\"4001\",\"4003\",\"66\",\"FR0112\",\"955\",\"1402\",\"1490\",\"934\",\"19\",\"156\",\"FR0115\",\"FR0104\",\"FR0108\",\"FR0107\",\"FR0106\",\"52\",\"1240\",\"FR0124\",\"FR0315\",\"FR0307\",\"FR0306\",\"65\",\"1416\",\"fR0108\",\"54\",\"1419\",\"1417\",\"URLA.X1\",\"URLA.X125\",\"URLA.X8\",\"URLA.X19\",\"FR0327\",\"FR0316\",\"FR0127\",\"URLA.X165\",\"URLA.X14\",\"fR0128\",\"fR0116\",\"1819\",\"fR0326\"]";//\"1822\",;\"URLA.X1\",
	String propertyPayLoad = "[\"1821\",\"136\",\"1041\",\"1335\",\"1811\",\"12\",\"13\",\"14\",\"15\",\"140\"]";
	String propertyPayLoad_URLA = "";
	String assetPayLoad = "[\"dD0108\",\"dD0102\",\"dD0110\",\"dD0111\",\"dD0124\"]";
	String multiPleassetPayLoad = "[\"dD0108\",\"dD0102\",\"dD0110\",\"dD0111\",\"dD0124\"]";
	String realEstatePayLoad = "[\"fM0118\",\"FM0141\",\"fM0104\",\"fM0108\",\"FM0124\",\"fM0119\",\"fM0117\",\"fM0116\",\"FM0121\"]";
	String declarationPayLoad = "[\"1524\",\"169\",\"265\",\"172\",\"1057\",\"463\",\"173\",\"174\",\"171\",\"965\",\"466\",\"418\",\"4158\",\"4210\",\"4125\",\"1527\",\"4130\",\"4194\"]";//,\"4147\"
	String declarationPayLoad_URLA = "";
	String liabilitiesPayLoad = "[\"fL0108\",\"fL0102\",\"fL0110\",\"fL0113\",\"fL0111\",\"fL0117\",\"fL0115\"]";
	String incomePayLoadBusiness = "[\"cX.VOIREPORTIDBOR\",\"bE0102\",\"bE0117\",\"bE0119\",\"bE0104\",\"bE0114\",\"bE0115\",\"bE0107\",\"bE0106\",\"bE0105\",\"bE0111\",\"bE0112\"]";//,\"bE0011\"
	String multipleIncomePayLoad="[\"bE0102\",\"bE0117\",\"bE0119\",\"bE0104\",\"bE0114\",\"bE0115\",\"bE0107\",\"bE0106\",\"bE0105\",\"bE0111\",\"bE0112\",\"146\",\"149\",\"152\"]";//,\"bE0011\"
	String incomePayLoadMilitary = "[\"bE0202\",\"bE0207\",\"bE0219\",\"bE0217\",\"bE0211\",\"bE0214\",\"bE0204\"]";
	
	String loanEditControlPayLoad="[\"CX.PORTALLOANEDITCONTROL\"]";
	
	String getStartedCoAppPayLoad = "[\"4004\",\"4006\",\"98\",\"1268\",\"1403\",\"fR0215\",\"fR0204\",\"fR0208\",\"fR0206\",\"fR0212\",\"1519\",\"1522\",\"1520\",\"84\"]";
	String getStartedCoAppPayLoad_URL="";
	String declarationsCoAppPayLoad = "[\"175\",\"178\",\"1197\",\"464\",\"179\",\"177\",\"467\",\"4213\",\"4198\"]";
	String incomeCoAppPayload="[\"cX.VOIREPORTIDCOBOR\",\"cE0102\",\"cE0107\",\"cE0104\",\"cE0106\"]";
	String realEstateCoAppPayload="[\"fM0218\",\"fM0241\",\"fM0204\",\"fM0208\",\"fM0219\",\"fM0217\",\"fM0216\",\"fM0221\",\"fM0224\"]";
	String assetsCoAppPayload="[\"dD0208\",\"dD0202\",\"dD0210\",\"dD0211\",\"dD0224\"]";
	//String incomePayLoad = "[\"BE0115\",\"BE0102\",\"145\",\"bE0102\",\"BE0004\",\"BE0017\",\"bE0107\",\"BE0006\",\"BE0005\",\"BE0010\",\"BE0113\",\"BE0016\",\"BE0133\",\"bE0119\",\"BE0020\",\"BE0021\",\"BE0022\",\"BE0023\",\"bE0117\",\"BE0027\",\"bE0111\",\"bE0114\"]";
//	String apiKey = "438C9CA0-0A86-4ED1-8A88-0BBA601A657E";
	String apiKey = "00785e7d-4082-4a7e-9c99-5c8185c73d8c";				
	String endSessionUrl = "";
	String fetchGUIDURL = "";
	String upDateLoanStatusURL = "";
	String guiIdPayload = "[{\"FilterCriteria\": {\"FieldId\": \"Loan.LoanNumber\",\"FieldValue\": \"$\", \"FieldMatchType\": \"contains\",\"FieldDataType\": \"string\"},\"RequiredFields\": [ \"Loan.Guid\"]}]";
	String guiIDurl = "http://pennymac-encompass-qa-alb-21350874.us-west-1.elb.amazonaws.com/encompasswrapper/encompass/api/v2/pipeline";
	String loanDetailsURL ="http://internal-cdl-wrapper-stg-encompass-lb-1548058553.us-west-1.elb.amazonaws.com/encompasswrapper/encompass/api/v2/loans/{GUID}/retrieveLoan?getFieldsForAllBorrowerPairs=false&getAllFieldsForBorrowerPair=false&modulename=na";
	String beginSessionUrl = "http://internal-cdl-wrapper-stg-encompass-lb-1548058553.us-west-1.elb.amazonaws.com/encompasswrapper/encompass/api/v1/usersessions/usersession/begin";
	String documentRetrivalURL="http://internal-cdl-wrapper-stg-encompass-lb-1548058553.us-west-1.elb.amazonaws.com/encompasswrapper/encompass/api/v2/loans/{GUID}/documents";
	static String EncomopassLoanNumber=null;
	private void endSession(String cookie) {
		HttpHeaders endSessionHeader = getHeader(cookie);
		RestTemplate rt = new RestTemplate();
		HttpEntity<String> entity = new HttpEntity<String>(endSessionHeader);
		ResponseEntity<?> re = rt.exchange(endSessionUrl, HttpMethod.POST, entity, String.class);
		System.out.println("Response of End Session>>>>" + re.toString());
	}

	private HttpHeaders getHeader(String cookie) {
		HttpHeaders fetchGUIDHeader = new HttpHeaders();
		fetchGUIDHeader.setContentType(MediaType.APPLICATION_JSON);
		fetchGUIDHeader.add("x-api-key", apiKey);
		 fetchGUIDHeader.add("Cookie", cookie);
		return fetchGUIDHeader;
	}

	private HttpHeaders getHeader() {
		HttpHeaders fetchGUIDHeader = new HttpHeaders();
		fetchGUIDHeader.setContentType(MediaType.APPLICATION_JSON);
		fetchGUIDHeader.add("x-api-key", apiKey);
		System.out.println("GUI HeADER>>>>" + fetchGUIDHeader);
		return fetchGUIDHeader;
	}

	public String fetchSessionCookie() {
		HttpHeaders beginSessionHeader = new HttpHeaders();
		beginSessionHeader.setContentType(MediaType.APPLICATION_JSON);
		beginSessionHeader.add("x-api-key", apiKey);
		RestTemplate rt = new RestTemplate();
		HttpEntity<String> entity = new HttpEntity<String>(beginSessionHeader);

		ResponseEntity<?> re = rt.exchange(beginSessionUrl, HttpMethod.POST, entity, String.class);
		HttpHeaders resHeaders = re.getHeaders();
		List<String> resHeaders1 = resHeaders.get("Set-Cookie");
		HashMap<String, String> headerMap = new HashMap<String, String>();
		for (int i = 0; i < resHeaders1.size(); i++) {
			String[] headerValues = resHeaders1.get(i).split(";");
			for (String header : headerValues) {
			//	System.out.println("Header incookie>>>>"+header);
				headerMap.put(header.split("=")[0], header.split("=")[1]);
			}
		}

		return StringUtils.join("encompass-wrapper-session-id=", headerMap.get("encompass-wrapper-session-id"),
				";AWSELB=", headerMap.get("AWSELB"));
	}

	public void beginSession(){
		HttpHeaders beginSessionHeader = new HttpHeaders();
		beginSessionHeader.setContentType(MediaType.APPLICATION_JSON);
		beginSessionHeader.add("x-api-key", apiKey);
		RestTemplate rt = new RestTemplate();
		HttpEntity<String> entity = new HttpEntity<String>(beginSessionHeader);
		ResponseEntity<?> re = rt.exchange(beginSessionUrl, HttpMethod.POST, entity, String.class);
		System.out.println("Begin session>>>>"+re.getStatusCode());
	}

	public void fetchLoanGUIDFromEncompassConsumerPortal() throws Exception {
		HashMap<String, String> loanGUIMap = new HashMap<String, String>();
		 //("7201741806","{d917e890-ced4-45a5-bc61-c82b0195f495}");
		System.out.println("Entered the loop");
		// compareGetStartedUIDetailsWithEncompass("7201741806","{d917e890-ced4-45a5-bc61-c82b0195f495}",
		// "");
		compareGetStartedUIDetailsWithEncompass("7201741833", "{65eaf9ad-a1ef-4802-b5c0-51d63be5a666}", "");
	}

	private void compareGetStartedUIDetailsWithEncompass(String loanNumber, String guiID, String expectedData)
			throws Exception {
		HashMap<String, String> expectedUIDataMap = new HashMap<String, String>();
		HashMap<String, String> encompassDataMap = new HashMap<String, String>();
		 expectedUIDataMap = buildExpectedTestDataMap(expectedData);
		 encompassDataMap = buildGetStartedEncompassMap(loanNumber, guiID);
		// encompassDataMap= buildPropertyEncompassMap(loanNumber, guiID);
		encompassDataMap = buildAssetEncompassMap(loanNumber, guiID);
		/*
		 * Set<String> keys = expectedUIDataMap.keySet();
		 *
		 * for(String key:keys){ String expectedValue =
		 * expectedUIDataMap.get(key); if(encompassDataMap.containsKey(key) ==
		 * false){ addExceptionToReport("Expected Key "+key+
		 * " is not available in Encompass Map.", "", ""); }
		 * if(expectedValue.trim().equalsIgnoreCase(encompassDataMap.get(key))
		 * == false){ addExceptionToReport("Expected Value "+expectedValue+
		 * " is not matching with Encompass data "+encompassDataMap.get(key)+
		 * " for the field "+key, "", ""); }
		 *
		 * }
		 */
		/*
		 * System.out.println("fIRST NAME>>>"
		 * +encompassDataMap.get("Mailingaddress_Zipcode")); System.out.println(
		 * "First name>>>>"+encompassDataMap.get("First name"));
		 * System.out.println("Middle name>>>>>"+encompassDataMap.get(
		 * "Middle name")); System.out.println("Last name>>>>>"
		 * +encompassDataMap.get("Last name"));
		 * System.out.println("Suffix>>>>"+encompassDataMap.get("Suffix"));
		 * System.out.println("Home phone>>>>>"+encompassDataMap.get(
		 * "Home phone")); System.out.println("Mobile number>>>>"
		 * +encompassDataMap.get("Mobile number")); System.out.println(
		 * "My referral source is>>>>"+encompassDataMap.get(
		 * "My referral source is")); System.out.println("I was born on>>>>"
		 * +encompassDataMap.get("I was born on")); System.out.println(
		 * "I am a first time home buyer>>>>"+encompassDataMap.get(
		 * "I am a first time home buyer")); System.out.println(
		 * "What is the purpose of this loan?>>>>"+encompassDataMap.get(
		 * "What is the purpose of this loan?")); System.out.println(
		 * "What state is your property located in?>>>>"+encompassDataMap.get(
		 * "What state is your property located in?")); System.out.println(
		 * "I (or my deceased spouse) served, or I am currently serving, in the United States Armed Forces?????>>>>>>"
		 * +encompassDataMap.get(
		 * "I (or my deceased spouse) served, or I am currently serving, in the United States Armed Forces?"
		 * )); System.out.println("My branch of service is>>>>"
		 * +encompassDataMap.get("My branch of service is"));
		 * System.out.println("My service status>>>>"+encompassDataMap.get(
		 * "My service status")); System.out.println(
		 * "My service is related to>>>>"+encompassDataMap.get(
		 * "My service is related to")); System.out.println("I live>>>>"
		 * +encompassDataMap.get("I live")); System.out.println(
		 * "Street address line 1>>>>"+encompassDataMap.get(
		 * "Street address line 1")); System.out.println(
		 * "Street address line 2>>>>"+encompassDataMap.get(
		 * "Street address line 2"));
		 * System.out.println("Zipcode>>>>"+encompassDataMap.get("Zipcode"));
		 * System.out.println("Years>>>>"+encompassDataMap.get("Years"));
		 * System.out.println("Motnhs>>>>"+encompassDataMap.get("Months"));
		 * System.out.println("My marital status>>>>"+encompassDataMap.get(
		 * "My marital status")); System.out.println(
		 * "Mailingaddress_Street address line 1>>>>"+encompassDataMap.get(
		 * "Mailingaddress_Street address line 1"));
		 * System.out.println("Mailingaddress_Zipcodes>>>>"+encompassDataMap.get
		 * ("Mailingaddress_Zipcode"));
		 *
		 * System.out.println("I am purchasing a property for>>>"
		 * +encompassDataMap.get("I am purchasing a property for"));
		 * System.out.println("My property worth is>>>"+encompassDataMap.get(
		 * "My property worth is")); System.out.println("My property is a>>>"
		 * +encompassDataMap.get("My property is a")); System.out.println(
		 * "The number of units in the property are>>>"+encompassDataMap.get(
		 * "The number of units in the property are")); System.out.println(
		 * "My existing balance on mortgage to be paid>>>"+encompassDataMap.get(
		 * "My existing balance on mortgage to be paid")); System.out.println(
		 * "I can put a down payment of>>>"+encompassDataMap.get(
		 * "I can put a down payment of")); System.out.println(
		 * "I will use this property as>>>"+encompassDataMap.get(
		 * "I will use this property as"));
		 * System.out.println("Zipcode>>>"+encompassDataMap.get("Zipcode"));
		 * System.out.println("State>>>"+encompassDataMap.get("State"));
		 * System.out.println("City>>>"+encompassDataMap.get("City"));
		 * System.out.println("County>>>"+encompassDataMap.get("County"));
		 * System.out.println(
		 * "The balance on this second mortgage or HELOC is>>>"
		 * +encompassDataMap.get(
		 * "The balance on this second mortgage or HELOC is"));
		 */

		System.out.println("My asset type>>>>" + encompassDataMap.get("My asset type"));
		System.out.println("Account with>>>>" + encompassDataMap.get("Account with"));
		System.out.println("Account number>>>>" + encompassDataMap.get("Account number"));
		System.out.println("Amount>>>>" + encompassDataMap.get("Amount"));
		System.out.println("Holds jointly with>>>>" + encompassDataMap.get("Holds jointly with"));
	}

	private HashMap<String, String> buildExpectedTestDataMap(String expectedData) {
		String[] expData1 = expectedData.split("&&");
		HashMap<String, String> dataMap = new HashMap<String, String>();
		for (String data : expData1) {
			String prefix = "";
			String key = "";
			if (data.split(";")[0].equalsIgnoreCase("My mailing address is")) {
				prefix = "MailingAddress_";

			}
			if (data.split(";")[1].contains("%")) {
				String[] expData2 = data.split(";")[1].split(":");
				for (String data1 : expData2) {
					key = data1.split("%")[1].split("_")[0].trim();
					if (prefix.length() > 0) {
						key = prefix + key;
					} else
						dataMap.put(key, data1.split("%")[1].split("_")[1].trim());
				}
			} else {
				dataMap.put(data.split(";")[0].trim(), data.split(";")[1].trim());
			}

		}
		return dataMap;
	}

	public HashMap<String, String> buildLoanGUIDFromEncompassConsumerPortal() throws Exception {

		RestTemplate rt1 = new RestTemplate();
		HttpEntity<String> entity1 = new HttpEntity<>("{}", getHeader());
		HashMap<String, String> loanGUIMap = new HashMap<String, String>();
		fetchGUIDURL = "http://pennymac-encompass-dev-alb-2090947775.us-west-1.elb.amazonaws.com/encompasswrapper/encompass/api/v2/loans/create";
		try {
			ResponseEntity<?> re1 = rt1.exchange(fetchGUIDURL, HttpMethod.POST, entity1, String.class);
			if (re1.getStatusCode() != HttpStatus.OK) {
				addExceptionToReport("Fetching GUID & Loan number From Encompass failed ",
						String.valueOf(re1.getStatusCode()), "200");
			}
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			try {
				Loan mp = (Loan) objectMapper.readValue(re1.getBody().toString(), Loan.class);
				objectMapper.readValue(re1.getBody().toString(), MainParser.class);
				loanGUIMap.put("loanNumber", mp.getLoanNumber());
				loanGUIMap.put("loanGUID", mp.getLoanGuid());
				return loanGUIMap;
			} catch (Exception e) {
				addExceptionToReport(
						"Failed to fetch GUID & loan number from Encompass for loan number " + e.toString(), "", "");
			}
		} catch (final HttpClientErrorException e) {
			addExceptionToReport("Exception seen during parsing Fetch GUI json " + e.toString(), "", "");
		}
		return loanGUIMap;
	}

	/**
	 * Method Name: fetchGUIDFromEncompassForGivenLoanNumber Purpose: To fetch
	 * GUID of a Loan Number from Encompass
	 *
	 * @param loanNumber
	 * @return
	 * @throws Exception
	 */
/*	public String fetchGUIDFromEncompassForGivenLoanNumber(String loanNumber) throws Exception {

		System.out.println("Loan NUmber" + loanNumber);
		guiIdPayload = guiIdPayload.replace("$", loanNumber);

		System.out.println("guiIdPayload " + guiIdPayload);

		ResponseEntity<?> responseEntity = postRequest(guiIDurl, guiIdPayload);

		if (responseEntity.getStatusCode() != HttpStatus.OK) {
			addExceptionToReport("Fetching GUID From Encompass failed for Loan Number " + loanNumber,
					String.valueOf(responseEntity.getStatusCode()), "200");
		}

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {

			MainParser mp = objectMapper.readValue(responseEntity.getBody().toString(), MainParser.class);
			objectMapper.readValue(responseEntity.getBody().toString(), MainParser.class);
			return mp.getLoans().get(0).getLoanGuid();
		} catch (Exception e) {
			addExceptionToReport("Failed to fetch GUID from Encompass for loan number " + loanNumber, e.toString(), "");
		}

		return null;
	}*/

	public HashMap<String, String> buildGetStartedEncompassMap(String loanNumber, String guiID) throws Exception {

		System.out.println("Inside Encomapss getstarted");
		HashMap<String, String> getStarteEncompassdMap = new HashMap<String, String>();

		Loan mp = fetchEncompassData(loanNumber, guiID, getStartedPayLoad);
System.out.println("==================Entered asjfksahfjkf=================");
//		getStarteEncompassdMap.put("First name", mp.getFirstName() == null ? "null" : mp.getFirstName().trim());
//		getStarteEncompassdMap.put("Middle name (Optional)", mp.getMiddleName() == null ? "null" : mp.getMiddleName().trim());
		//getStarteEncompassdMap.put("Last name", mp.getLastName() == null ? "null" : mp.getLastName().trim());
//		getStarteEncompassdMap.put("Suffix (Optional)", mp.getsuffix() == null ? "null" : mp.getsuffix().trim());
//		getStarteEncompassdMap.put("Home phone (Optional)", mp.getphoneNumber() == null ? "null" : mp.getphoneNumber().trim());
//		getStarteEncompassdMap.put("Mobile number",
//				mp.getGetStartedMobileNumber() == null ? "null" : mp.getGetStartedMobileNumber().trim());
//		getStarteEncompassdMap.put("My referral source is",
//				mp.getGetStartedReferralSource() == null ? "null" : mp.getGetStartedReferralSource().trim());
//		getStarteEncompassdMap.put("I was born on",
//				mp.getGetStartedDOB() == null ? "null" : mp.getGetStartedDOB().trim());
//		getStarteEncompassdMap.put("Are you a first-time homebuyer?",
//				mp.getGetStarteFirstTimeHomeBuyer() == null ? "null" : mp.getGetStarteFirstTimeHomeBuyer().trim());
//		getStarteEncompassdMap.put("What is the purpose of this loan?",
//				mp.getGetStartedLoanPurpose() == null ? "null" : mp.getGetStartedLoanPurpose().trim());
//		getStarteEncompassdMap.put("Property is located in",
//				mp.getGetStartedZipCode() == null ? "null" : mp.getGetStartedZipCode().trim());
//		getStarteEncompassdMap.put(
//				"I have (or my deceased spouse has) served, or I am currently serving, in the United States Armed Forces?",
//				mp.getGetStartedWorkingInArmedForces() == null ? "null"
//						: mp.getGetStartedWorkingInArmedForces().trim());
//		getStarteEncompassdMap.put("My branch of service is",
//				mp.getGetStartedBranchOfService() == null ? "null" : mp.getGetStartedBranchOfService().trim());
//		getStarteEncompassdMap.put("My service status",
//				mp.getGetStartedServiceStatus() == null ? "null" : mp.getGetStartedServiceStatus().trim());
//		getStarteEncompassdMap.put("My service is related to",
//				mp.getGetStartedServiceRelatedTo() == null ? "null" : mp.getGetStartedServiceRelatedTo().trim());
//		getStarteEncompassdMap.put("In my current property, I", mp.getGetStartedILive() == null ? "null" : mp.getGetStartedILive().trim());
//		getStarteEncompassdMap.put("Street address line 1",
//				mp.getGetStartedStreetAddress() == null ? "null" : mp.getGetStartedStreetAddress().trim());
//		getStarteEncompassdMap.put("Street address line 2",
//				mp.getGetStartedStreetAddress() == null ? "null" : mp.getGetStartedStreetAddress().trim());
//		getStarteEncompassdMap.put("Zipcode",
//				mp.getGetStartedZipCode() == null ? "null" : mp.getGetStartedZipCode().trim());
//		getStarteEncompassdMap.put("Years",
//				mp.getGetStartedNumberOfYearsOfStay() == null ? "null" : mp.getGetStartedNumberOfYearsOfStay().trim());
//		getStarteEncompassdMap.put("Months (Optional)",
//				mp.getGetStartedMonthsOfStay() == null ? "null" : mp.getGetStartedMonthsOfStay().trim());
//		getStarteEncompassdMap.put("My marital status",
//				mp.getGetStartedMaritialStatus() == null ? "null" : mp.getGetStartedMaritialStatus().trim());
//		getStarteEncompassdMap.put("Mailingaddress_Street address line 1",
//				mp.getGetStartedMailingAddressStreet() == null ? "null"
//						: mp.getGetStartedMailingAddressStreet().trim());
//		getStarteEncompassdMap.put("Mailingaddress_Zipcode",
//				mp.getGetStartedMailingAddressZip() == null ? "null" : mp.getGetStartedMailingAddressZip().trim());
		//getStarteEncompassdMap.put("Source of loan" , mp.getSourceOfLoan() == null ? "null" : mp.getSourceOfLoan().trim());
		return getStarteEncompassdMap;
	}

	private HashMap<String, String> buildPropertyEncompassMap(String loanNumber, String guiID) throws Exception {
		System.out.println("Enteredfkljsdjfkjgdjhldfh===========");
		HashMap<String, String> propertyEncompassMap = new HashMap<String, String>();
		Loan mp = fetchEncompassData(loanNumber, guiID, propertyPayLoad);

//		propertyEncompassMap.put("Estimated Property Value",
//				mp.getPropertyPropertyPurchaseFor() == null ? "null" : mp.getPropertyPropertyPurchaseFor().trim());
//		propertyEncompassMap.put("I am purchasing a",
//				mp.getPurchasingProperty() == null ? "null" : mp.getPurchasingProperty().trim());
//		propertyEncompassMap.put("I am purchasing a",
//				mp.getPropertyPurchasing() == null ? "null" : mp.getPropertyPurchasing().trim());
//		propertyEncompassMap.put("The number of units in the property are",
//				mp.getPropertyNumberOfUnits() == null ? "null" : mp.getPropertyNumberOfUnits().trim());
//		propertyEncompassMap.put("My existing balance on mortgage to be paid",
//				mp.getPropertyMortgageToBePaid() == null ? "null" : mp.getPropertyMortgageToBePaid().trim());
//		propertyEncompassMap.put("I can put a down payment of",
//			mp.getPropertyDownPayment() == null ? "null" : mp.getPropertyDownPayment().trim());
//		propertyEncompassMap.put("I will use this property as",
//				mp.getPropertyUsingAs() == null ? "null" : mp.getPropertyUsingAs().trim());
//		propertyEncompassMap.put("Zipcode", mp.getPropertyZipCode() == null ? "null" : mp.getPropertyZipCode().trim());
//		propertyEncompassMap.put("State", mp.getPropertyState() == null ? "null" : mp.getPropertyState().trim());
//		propertyEncompassMap.put("City", mp.getPropertyCity() == null ? "null" : mp.getPropertyCity().trim());
//		propertyEncompassMap.put("County", mp.getPropertyCounty() == null ? "null" : mp.getPropertyCounty().trim());
//		//propertyEncompassMap.put("The balance on this second mortgage or HELOC is",
//				mp.getPropertyBalanceOnMortgageOrHELOC() == null ? "null"
//						: mp.getPropertyBalanceOnMortgageOrHELOC().trim());

		return propertyEncompassMap;
	}

	private HashMap<String, String> buildAssetEncompassMap(String loanNumber, String guiID) throws Exception {
		HashMap<String, String> assetEncompassMap = new HashMap<String, String>();
		Loan mp = fetchEncompassData(loanNumber, guiID, assetPayLoad);
//
//		assetEncompassMap.put("My asset type", mp.getAssetType() == null ? "null" : mp.getAssetType().trim());
//		assetEncompassMap.put("Account with",
//				mp.getAssetAccountWith() == null ? "null" : mp.getAssetAccountWith().trim());
//		assetEncompassMap.put("Account number (Optional)",
//				mp.getAssetAccountNumber() == null ? "null" : mp.getAssetAccountNumber().trim());
//		//assetEncompassMap.put("Description (Optional)",
//				//mp.getAssetDescription() == null ? "null" : mp.getAssetDescription().trim());
//		assetEncompassMap.put("Amount", mp.getAssetAmount() == null ? "null" : mp.getAssetAmount().trim());
//		assetEncompassMap.put("Holds jointly with",
//				mp.getAssetsHoldsJointlyWith() == null ? "null" : mp.getAssetsHoldsJointlyWith().trim());

		return assetEncompassMap;
	}

	private HashMap<String, String> buildRealEstateEncompassMap(String loanNumber, String guiID) throws Exception {

		HashMap<String, String> realEstateMap = new HashMap<String, String>();
		Loan mp = fetchEncompassData(loanNumber, guiID, realEstatePayLoad);

//		realEstateMap.put("My property type is",
//				mp.getRealEstatePropertyType() == null ? "null" : mp.getRealEstatePropertyType().trim());
//		realEstateMap.put("My property is a",
//				mp.getRealEstatePropertyIs() == null ? "null" : mp.getRealEstatePropertyIs().trim());		
//		realEstateMap.put("Street address line 1",
//				mp.getRealEstateAddress() == null ? "null" : mp.getRealEstateAddress().trim());
//		realEstateMap.put("Street address line 2",
//				mp.getRealEstateAddress() == null ? "null" : mp.getRealEstateAddress().trim());
//		realEstateMap.put("Zip Code", mp.getRealEstateZipcode() == null ? "null" : mp.getRealEstateZipcode().trim());
//		realEstateMap.put("Property status",
//				mp.getRealEstatePropertyStatus() == null ? "null" : mp.getRealEstatePropertyStatus().trim());
//		realEstateMap.put("Property value",
//				mp.getRealEstatePropertyValue() == null ? "null" : mp.getRealEstatePropertyValue().trim());
//		realEstateMap.put("Outstanding balance",
//				mp.getRealEstateOutstandingBalance() == null ? "null" : mp.getRealEstateOutstandingBalance().trim());
//		realEstateMap.put("Monthly rental income",
//				mp.getRealEstateMonthlyRentalIncome() == null ? "null" : mp.getRealEstateMonthlyRentalIncome().trim());
//		realEstateMap.put("Monthly mortgage payments", mp.getRealEstateMonthlyMortgagePayments() == null ? "null"
//				: mp.getRealEstateMonthlyMortgagePayments().trim());
//		realEstateMap.put("Insurance, maintenance, taxes & misc", mp.getRealEstateInsuranceMaintenanceTaxes() == null
//				? "null" : mp.getRealEstateInsuranceMaintenanceTaxes().trim());

		return realEstateMap;

	}

	private HashMap<String, String> buildDeclarationEncompassMap(String loanNumber, String guiID) throws Exception {
System.out.println("******entered buildDeclarationEncompassMap*********");
		HashMap<String, String> declarationMap = new HashMap<String, String>();
		Loan mp = fetchEncompassData(loanNumber, guiID, declarationPayLoad);

//		declarationMap.put("Are there any outstanding judgments against you?",
//				mp.gethasOutstandingJudgementsBorrower() == null ? "null"
//						: mp.gethasOutstandingJudgementsBorrower().trim());
//		declarationMap.put("Have you been declared bankrupt within the past 7 years?",
//				mp.getisDeclaredBankruptBorrower() == null ? "null" : mp.getisDeclaredBankruptBorrower().trim());
//		declarationMap.put("Are you a party to a lawsuit?",
//				mp.getisPartyLawsuitBorrower() == null ? "null" : mp.getisPartyLawsuitBorrower().trim());
//		declarationMap.put(
//				"Have you directly or indirectly been obligated on any loan which resulted in foreclosure, transfer of title in lieu of foreclosure, or judgment?",
//				mp.gethasForeclosureJudgmentBorrower() == null ? "null"
//						: mp.gethasForeclosureJudgmentBorrower().trim());
//		declarationMap.put(
//				"Are you presently delinquent or in default on any Federal debt or any other loan, mortgage, financial obligation, bond, or loan guarantee?",
//				mp.getisLoanGuaranteeBorrower() == null ? "null" : mp.getisLoanGuaranteeBorrower().trim());
//		declarationMap.put("Are you obligated to pay alimony, child support, or separate maintenance?",
//				mp.getisObligatedToPayMaintenanceBorrower() == null ? "null"
//						: mp.getisObligatedToPayMaintenanceBorrower().trim());
//		declarationMap.put("Is any part of the down payment borrowed?",
//				mp.getisDownPaymentBorrowedBorrower() == null ? "null" : mp.getisDownPaymentBorrowedBorrower().trim());
//		declarationMap.put("Are you a co-maker or endorser on a note?",
//				mp.getisComakerEndorserBorrower() == null ? "null" : mp.getisComakerEndorserBorrower().trim());
//		declarationMap.put("Are you a US citizen?",
//				mp.getDeclarationUSCitizen() == null ? "null" : mp.getDeclarationUSCitizen().trim());
//		declarationMap.put("Are you a permanent resident alien?", mp.getDeclarationPermanentResidentAlien() == null
//				? "null" : mp.getDeclarationPermanentResidentAlien().trim());
//		declarationMap.put("Do you intend to occupy the property as your primary residence?",
//				mp.getDeclarationIntendToOccupyProperty() == null ? "null"
//						: mp.getDeclarationIntendToOccupyProperty().trim());
//		declarationMap.put("Hispanic or Latino",
//				mp.getDeclationHispanic() == null ? "null" : mp.getDeclationHispanic().trim());
//		declarationMap.put("Other Hispanic or Latino (Optional)",
//				mp.getDeclationOtherHispanic() == null ? "null" : mp.getDeclationOtherHispanic().trim());
//		System.out.println("ready to executed psicifc");
//		declarationMap.put("Other Pacific Islander (Optional)",
//				mp.getDeclationPacific() == null ? "null" : mp.getDeclationPacific().trim());
//		System.out.println("out of ");
		return declarationMap;
	}
	
	private HashMap<String, String> buildLiabililtiesEncompassMap(String loanNumber, String guiID) throws Exception {

		HashMap<String, String> liabilityMap = new HashMap<String, String>();
		Loan mp = fetchEncompassData(loanNumber, guiID, liabilitiesPayLoad);

		liabilityMap.put("My liability type is",
				mp.getLiabailitiesType() == null ? "null"
						: mp.getLiabailitiesType().trim());
		liabilityMap.put("Lender name",
				mp.getLiabailitiesLenderName() == null ? "null" : mp.getLiabailitiesLenderName().trim());
		liabilityMap.put("Account number (Optional)",
				mp.getLiabailitiesAccountNumber() == null ? "null" : mp.getLiabailitiesAccountNumber().trim());
		liabilityMap.put(
				"Outstanding balance",
				mp.getLiabailitiesOutstandingBalance() == null ? "null"
						: mp.getLiabailitiesOutstandingBalance().trim());
		liabilityMap.put(
				"Monthly payment",
				mp.getLiabailitiesMonthlyPayment() == null ? "null" : mp.getLiabailitiesMonthlyPayment().trim());
		liabilityMap.put("Status",
				mp.getLiabailitiesStatus() == null ? "null"
						: mp.getLiabailitiesStatus().trim());
		liabilityMap.put("Holds jointly with (Optional)",
				mp.getLiabailitiesHoldsJointlyWith() == null ? "null" : mp.getLiabailitiesHoldsJointlyWith().trim());
//		declarationMap.put("Are you a co-maker or endorser on a note?",
//				mp.getisComakerEndorserBorrower() == null ? "null" : mp.getisComakerEndorserBorrower().trim());
//		declarationMap.put("Are you a US citizen?",
//				mp.getDeclarationUSCitizen() == null ? "null" : mp.getDeclarationUSCitizen().trim());
//		declarationMap.put("Are you a permanent resident alien?", mp.getDeclarationPermanentResidentAlien() == null
//				? "null" : mp.getDeclarationPermanentResidentAlien().trim());
//		declarationMap.put("Do you intend to occupy the property as your primary residence?",
//				mp.getDeclarationIntendToOccupyProperty() == null ? "null"
//						: mp.getDeclarationIntendToOccupyProperty().trim());
//		declarationMap.put("Hispanic or Latino",
//				mp.getDeclationHispanic() == null ? "null" : mp.getDeclationHispanic().trim());
//		declarationMap.put("Other Hispanic or Latino (Optional)",
//				mp.getDeclationOtherHispanic() == null ? "null" : mp.getDeclationOtherHispanic().trim());
//		System.out.println("ready to executed psicifc");
//		declarationMap.put("Other Pacific Islander (Optional)",
//				mp.getDeclationPacific() == null ? "null" : mp.getDeclationPacific().trim());
//		System.out.println("out of ");
		return liabilityMap;
	}

	private HashMap<String, String> buildIncomeSectionEncompassMapBusiness(String loanNumber, String guiID) throws Exception {
		HashMap<String, String> incomeMap = new HashMap<String, String>();
		System.out.println("loanNumber:"+loanNumber);
		System.out.println("guiID:"+guiID);
		System.out.println("incomePayLoadBusiness:"+incomePayLoadBusiness);

		Loan mp = fetchEncompassData(loanNumber, guiID, incomePayLoadBusiness);
System.out.println("Enteerd buildIncomeSectionEncompassMap");
		//incomeMap.put("Business or self-employment", mp.getIncomeBusinessOrSelfEmployment() == null ? "null"
				//: mp.getIncomeBusinessOrSelfEmployment().trim());
		//incomeMap.put("Employment",
				//mp.getIncomeSectionEmployment() == null ? "null" : mp.getIncomeSectionEmployment().trim());
//		incomeMap.put("Source of Income",
//				mp.getIncomeBusinessOrSelfEmployment() == null ? "null" : mp.getIncomeBusinessOrSelfEmployment().trim());
//		incomeMap.put("Business name",
//				mp.getIncomeSectionBusinessName() == null ? "null" : mp.getIncomeSectionBusinessName().trim());
//		incomeMap.put("Business phone",
//				mp.getIncomeSectionBusinessPhone() == null ? "null" : mp.getIncomeSectionBusinessPhone().trim());
//		incomeMap.put("Percentage of ownership", mp.getIncomeSectionPercentageOwnerShip() == null ? "null"
//				: mp.getIncomeSectionPercentageOwnerShip().trim());
//		incomeMap.put("I have had full/partial ownership in this business since",
//				mp.getIncomeSectionBusinessFrom() == null ? "null" : mp.getIncomeSectionBusinessFrom().trim());
//		incomeMap.put("To", mp.getIncomeSectionBusinessTo() == null ? "null" : mp.getIncomeSectionBusinessTo().trim());
//		incomeMap.put("Monthly earnings",
//				mp.getIncomeSectionMonthlyEarnings() == null ? "null" : mp.getIncomeSectionMonthlyEarnings().trim());
//		incomeMap.put("Street address line 1",
//				mp.getIncomeAddressLine1() == null ? "null" : mp.getIncomeAddressLine1().trim());
		//incomeMap.put("Street address line 2 (Optional)",
				//mp.getIncomeAddressLine1() == null ? "null" : mp.getIncomeAddressLine1().trim());
		//incomeMap.put("Employer phone",
				//mp.getIncomeEmployerPhone() == null ? "null" : mp.getIncomeEmployerPhone().trim());
		//incomeMap.put("Zip Code", mp.getIncomeZipCode() == null ? "null" : mp.getIncomeZipCode().trim());
		
		System.out.println("thasgfkhesg0000000000000000000000000000000");
		return incomeMap;
		//incomeMap.put("Employer name", mp.getIncomeEmployerName() == null ? "null" : mp.getIncomeEmployerName().trim());
		
		//incomeMap.put("State", mp.getIncomeState() == null ? "null" : mp.getIncomeState().trim());
		//incomeMap.put("City", mp.getIncomeCity() == null ? "null" : mp.getIncomeCity().trim());
		///incomeMap.put("Job title", mp.getIncomeJobTitle() == null ? "null" : mp.getIncomeJobTitle().trim());
		//incomeMap.put("In this employment from",
				//mp.getIncomeEmploymentYear() == null ? "null" : mp.getIncomeEmploymentYear().trim());
		//incomeMap.put("To", mp.getIncomeEmploymentYear() == null ? "null" : mp.getIncomeEmploymentYear().trim());
		//incomeMap.put("Duration in this profession",
				//mp.getIncomeDurationInProfession() == null ? "null" : mp.getIncomeDurationInProfession().trim());
		//incomeMap.put("Years", mp.getIncomeEmploymentYear() == null ? "null" : mp.getIncomeEmploymentYear().trim());
		//incomeMap.put("Months", mp.getIncomeEmploymentMonth() == null ? "null" : mp.getIncomeEmploymentMonth().trim());
		//incomeMap.put("Base income",
				//mp.getIncomeSectionBaseIncome() == null ? "null" : mp.getIncomeSectionBaseIncome().trim());
		//incomeMap.put("Overtime",
				//mp.getIncomeSectionOverTime() == null ? "null" : mp.getIncomeSectionOverTime().trim());
		//incomeMap.put("Bonus", mp.getIncomeSectionBonus() == null ? "null" : mp.getIncomeSectionBonus().trim());
		//incomeMap.put("Commission",
				//mp.getIncomeSectionComission() == null ? "null" : mp.getIncomeSectionComission().trim());
		//incomeMap.put("Other", mp.getIncomeSectionOther() == null ? "null" : mp.getIncomeSectionOther().trim());
		

	}
	
	private HashMap<String, String> buildIncomeSectionEncompassMapForMilitary(String loanNumber, String guiID) throws Exception {
		HashMap<String, String> incomeMapMilitary = new HashMap<String, String>();
		Loan mp = fetchEncompassData(loanNumber, guiID, incomePayLoadMilitary);
System.out.println("Enteerd buildIncomeSectionEncompassMap");
		//incomeMap.put("Business or self-employment", mp.getIncomeBusinessOrSelfEmployment() == null ? "null"
				//: mp.getIncomeBusinessOrSelfEmployment().trim());
		//incomeMap.put("Employment",
				//mp.getIncomeSectionEmployment() == null ? "null" : mp.getIncomeSectionEmployment().trim());
//incomeMapMilitary.put("My source of income is",
//				mp.getIncomeBusinessOrSelfEmployment() == null ? "null" : mp.getIncomeBusinessOrSelfEmployment().trim());
//incomeMapMilitary.put("Employer name", mp.getIncomeEmployerName() == null ? "null" : mp.getIncomeEmployerName().trim());
//incomeMapMilitary.put("Street address line 1",
//				mp.getIncomeMilitaryAddressLine1() == null ? "null" : mp.getIncomeMilitaryAddressLine1().trim());
//incomeMapMilitary.put("Street address line 2 (Optional)",
//				mp.getIncomeMilitaryAddressLine1() == null ? "null" : mp.getIncomeMilitaryAddressLine1().trim());
//incomeMapMilitary.put("Employer phone",
//				mp.getIncomeMilitaryEmployerPhone() == null ? "null" : mp.getIncomeMilitaryEmployerPhone().trim());
//incomeMapMilitary.put("ZipCode", mp.getIncomeMilitaryZipCode() == null ? "null" : mp.getIncomeMilitaryZipCode().trim());		
//incomeMapMilitary.put("I have been in this business since",
//			mp.getIncomeSectionMilitaryBusinessFrom() == null ? "null" : mp.getIncomeSectionMilitaryBusinessFrom().trim());
//incomeMapMilitary.put("To", mp.getIncomeSectionMilitaryBusinessTo() == null ? "null" : mp.getIncomeSectionMilitaryBusinessTo().trim());
//incomeMapMilitary.put("Monthly earnings",
//				mp.getIncomeSectionMilitaryMonthlyEarnings() == null ? "null" : mp.getIncomeSectionMilitaryMonthlyEarnings().trim());
		//System.out.println("thasgfkhesg0000000000000000000000000000000");
		return incomeMapMilitary;
		//incomeMap.put("State", mp.getIncomeState() == null ? "null" : mp.getIncomeState().trim());
		//incomeMap.put("City", mp.getIncomeCity() == null ? "null" : mp.getIncomeCity().trim());
		///incomeMap.put("Job title", mp.getIncomeJobTitle() == null ? "null" : mp.getIncomeJobTitle().trim());
		//incomeMap.put("In this employment from",
				//mp.getIncomeEmploymentYear() == null ? "null" : mp.getIncomeEmploymentYear().trim());
		//incomeMap.put("To", mp.getIncomeEmploymentYear() == null ? "null" : mp.getIncomeEmploymentYear().trim());
		//incomeMap.put("Duration in this profession",
				//mp.getIncomeDurationInProfession() == null ? "null" : mp.getIncomeDurationInProfession().trim());
		//incomeMap.put("Years", mp.getIncomeEmploymentYear() == null ? "null" : mp.getIncomeEmploymentYear().trim());
		//incomeMap.put("Months", mp.getIncomeEmploymentMonth() == null ? "null" : mp.getIncomeEmploymentMonth().trim());
		//incomeMap.put("Base income",
				//mp.getIncomeSectionBaseIncome() == null ? "null" : mp.getIncomeSectionBaseIncome().trim());
		//incomeMap.put("Overtime",
				//mp.getIncomeSectionOverTime() == null ? "null" : mp.getIncomeSectionOverTime().trim());
		//incomeMap.put("Bonus", mp.getIncomeSectionBonus() == null ? "null" : mp.getIncomeSectionBonus().trim());
		//incomeMap.put("Commission",
				//mp.getIncomeSectionComission() == null ? "null" : mp.getIncomeSectionComission().trim());
		//incomeMap.put("Other", mp.getIncomeSectionOther() == null ? "null" : mp.getIncomeSectionOther().trim());
		

	}

//	public Loan fetchEncompassData(String loanNumber, String guiID, String payload) throws Exception {
//     System.out.println("Entered fetchEncompassData");
//     System.out.println("\n GUID>>>>>>>"+guiID.replace("{", "").replace("}", ""));
//		Map<String, String> uriVariables = new HashMap<>();
//		ClientHttpRequestFactory requestFactory = new
//			      HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
//		RestTemplate loanDetailTemplate = new RestTemplate(requestFactory);
//		//uriVariables.put("GUID", guiID.replace("{", "").replace("}", ""));
//		String revGUID = guiID.replace("{", "").replace("}", "").trim();
//		System.out.println("rEV guid>>>>"+revGUID);
//		uriVariables.put("GUID", "{"+revGUID+"}");
//		System.out.println("rEV guid12>>>>"+revGUID);
//		Loan mp = new Loan();
//		beginSession();
//		HttpEntity<String> loanDetailEntity = new HttpEntity<>(payload, getHeader());
//		try {
//	//		System.out.println("loabdetailsUrl>>>>"+loanDetailsURL);
//			System.out.println("Entered 1");
//			System.out.println("mAP sIZE????"+uriVariables.size());
//			System.out.println("GUID VALUE>>>>"+uriVariables.get("GUID"));
//
//			//loanDetailsURL = "http://pmconsumer-encompass-uat-alb-626899483.us-west-1.elb.amazonaws.com/encompasswrapper/encompass/api/v2/loans/{GUID}/retrieveLoan?getFieldsForAllBorrowerPairs=false&getAllFieldsForBorrowerPair=false&modulename=na";
//			
//			 System.out.println("\n loanDetailsURL>>>"+loanDetailsURL);
//			URI expanded = new UriTemplate(loanDetailsURL).expand(uriVariables);
//			System.out.println("eXPANDED>>>"+expanded.getRawPath());
//			//URLDecoder.decode(expanded.toString(), StandardCharsets.UTF_8.name()));;
//			System.out.println("URL AFTER SUBSTITUTE>>>"+URLDecoder.decode(loanDetailsURL, StandardCharsets.UTF_8.name()));
//			
//			 ResponseEntity<?> loanDetailResponse = null;
//			 try{
//				 loanDetailResponse =loanDetailTemplate.exchange(URLDecoder.decode(loanDetailsURL, StandardCharsets.UTF_8.name()), HttpMethod.POST,loanDetailEntity, String.class,uriVariables);
//				 //loanDetailResponse = loanDetailTemplate.exchange(loanDetailsURL, HttpMethod.POST, loanDetailEntity, String.class,uriVariables);
//			 }catch(Exception e1){
//				 System.out.println("Exception 1>>>>"+e1.toString());
//			 }
//			
//           
//			/*ResponseEntity<?> loanDetailResponse = loanDetailTemplate.exchange(loanDetailsURL, HttpMethod.POST,
//					loanDetailEntity, String.class, uriVariables);*/
//			System.out.println("loan details repose>>>>" + loanDetailResponse.getStatusCode());
//			if (loanDetailResponse.getStatusCode() != HttpStatus.OK) {
//				addExceptionToReport("Fetching loan details from  Encompass failed for Loan Number " + loanNumber,
//						String.valueOf(loanDetailResponse.getStatusCode()), "200");
//			}
//			ObjectMapper objectMapper = new ObjectMapper();
//			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//			try {
//				System.out.println(" Entered try block");
//				mp = objectMapper.readValue(loanDetailResponse.getBody().toString(), Loan.class);
//				System.out.println("jsdafhj"+mp);
//				objectMapper.readValue(loanDetailResponse.getBody().toString(), MainParser.class);
//			} catch (Exception e) {
//				addExceptionToReport(
//						"Failed to fetch GUID & loan number from Encompass for loan number " + e.toString(), "", "");
//			}
//
//		} catch (Exception e) {
//			System.out.println("Exception>>>>" + e.toString());
//
//			addExceptionToReport("Exception during fetching loan details from Encompass. Loan Number is " + loanNumber,
//					"", "");
//		}
//
//		return mp;
//	}

	
	
	
	
	
	/**
	 * Method Name: mapReferenceIdWithGUID Purpose: To fetch
	 * GUID of a Loan Number from Velocify using reference ID and Update GUID and Loan Number in DB
	 * Login into application and click on 'I am Done' button for Getstatred section
	 * @param 
	 * @return
	 * @throws Exception
	 */
	
	
	
	public void mapReferenceIdWithGUID()throws Exception{
		WebDriver driver = DriverFactory.getDriver();
		Util util = new Util();
		String details=util.fetchGUID(driver);
	    util.writeDataToFile(details);
	    String loanNumber = details.split("&&")[1];
		String guiId = "{"+details.split("&&")[0]+"}";
		driver.navigate().refresh();
		Thread.sleep(5000);
//		LoginIntoApplication login = new LoginIntoApplication();
//		driver.get(KWVariables.getVariables().get("PenMacUrl"));
//		driver.manage().window().maximize();
//		driver.findElement(By.xpath("//button[@class[contains(.,'head-login-btn')]]")).click();
//		login.loginIntoApplication("user_id");
//		Thread.sleep(10000);
		//driver.switchTo().activeElement();
		// driver.findElement(By.xpath(".//img[contains(@src,'home11')]")).click();
        //Thread.sleep(5000);
		driver.switchTo().activeElement();
		String LoanNumber = driver.findElement(By.xpath("(.//div[@class='loan-number'])[1]")).getText();
		
        Thread.sleep(2000);		
		String actCardResumeButton = "//div[contains(text(),'%LN')]/ancestor::div[1]/ancestor::div[1]/following-sibling::div//button[contains(text(),'Resume')]";
		driver.findElement(By.xpath(actCardResumeButton.replace("%LN",loanNumber))).click();
		Thread.sleep(100000);
		driver.findElement(By.xpath("//button[contains(.,'m Done')]")).click();
        
        Thread.sleep(5000);
		//Util util = new Util();
		
		//System.out.println("quesAnsString"+quesAnsString);
//		HashMap <String,String> dataFromUI = tenoThree.buildExpectedDataMap(quesAnsString);
//		for (Map.Entry<String, String> entry : dataFromUI.entrySet()) {
//			System.out.println("Entered for each loop");
//		
//		    System.out.println(entry.getKey()+" 77777777777777777777777>>>>>>>>>>>>>>>> "+entry.getValue());
//		}
//		
//		HashMap<String, String> getStartedEncompassMap=buildGetStartedEncompassMap(loanNumber,guiId);
//		
//		Set<String> encomapssKeys=getStartedEncompassMap.keySet();
//		
//		for(String key: encomapssKeys)
//		{
//
//			System.out.println(key+">>>>>>>"+getStartedEncompassMap.get(key));
//		}
//		comparingTwoHashMap(dataFromUI,getStartedEncompassMap);
		
		
	}
	
	
	
	public String compareUIdataWithEncompassgetstarted(String loanNumber,String guiId) throws Exception{
		Ten0Three tenoThree= new Ten0Three();
		//		 String details = util.readALineFromFile();
//		 System.out.println("String details"+details);
//		 String error="No verification for This section in current testcase";
//		 String loanNumber = details.split("&&")[1].trim();
//			String guiId = details.split("&&")[0].trim();
//			guiId="09e03193-3c63-4221-8f13-1f36358c53eb";
			guiId= "{"+guiId+"}";
			guiId = guiId.trim();
		System.out.println("compareUIdataWithEncompassgetstarted!!!!>>>");
		String quesAnsString = step.getDataValue("question_ans");
		HashMap <String,String> dataFromUI = tenoThree.buildExpectedDataMap(quesAnsString);		
		HashMap<String, String> getStartedEncompassMap=buildGetStartedEncompassMap(loanNumber,guiId);	
		for(String key:getStartedEncompassMap.keySet()){
			System.out.println("Keys>>>>>"+key+" get started encompass val>>>>"+getStartedEncompassMap.get(key));
		}
		System.out.println("getStartedEncompassMap>>>>>>"+getStartedEncompassMap);
		if(getStartedEncompassMap.equals("")){
			return null;
		}
		else{
		comparingTwoHashMap(dataFromUI,getStartedEncompassMap);
		return quesAnsString;
		}
	}
	
	public void compareUIdataWithEncompassgetstartedEdit() throws Exception{
		Util util = new Util();
		Ten0Three tenoThree= new Ten0Three();
		String details = util.readALineFromFile();
		 String loanNumber = details.split("&&")[1].trim();
			String guiId = details.split("&&")[0].trim();
			guiId= "{"+guiId+"}";
			guiId = guiId.trim();
		String quesAnsString = step.getDataValue("edit_GetStarted");
		HashMap <String,String> dataFromUI = tenoThree.buildExpectedDataMap(quesAnsString);		
		HashMap<String, String> getStartedEncompassMap=buildGetStartedEncompassMap(loanNumber,guiId);		
		Set<String> encomapssKeys=getStartedEncompassMap.keySet();
		comparingTwoHashMap(dataFromUI,getStartedEncompassMap);
		
	}
	
	public String compareUIdataWithEncompassProperty(String loanNumber,String guiId) throws Exception{

		Ten0Three tenoThree= new Ten0Three();
//		 String details = util.readALineFromFile();
//		 String loanNumber = details.split("&&")[1].trim();
//			String guiId = details.split("&&")[0].trim();
//			guiId="09e03193-3c63-4221-8f13-1f36358c53eb";
			guiId= "{"+guiId+"}";
			guiId = guiId.trim();
		String quesAnsString = step.getDataValue("PropertyQuestionAnswer");
		HashMap <String,String> dataFromUI = tenoThree.buildExpectedDataMap(quesAnsString);
		
		HashMap<String, String> propertyEncompassMap=buildPropertyEncompassMap(loanNumber,guiId);
		
		Set<String> encomapssKeys=propertyEncompassMap.keySet();
		if(propertyEncompassMap.equals("")){
			return null;
		}
		else{
		comparingTwoHashMap(dataFromUI,propertyEncompassMap);
		return quesAnsString;
		}
	}
	
	public void compareUIdataWithEncompassPropertyEdit() throws Exception{
		Util util = new Util();
		Ten0Three tenoThree= new Ten0Three(); 
		String details = util.readALineFromFile();
		 String loanNumber = details.split("&&")[1].trim();
			String guiId = details.split("&&")[0].trim();
			guiId= "{"+guiId+"}";
			guiId = guiId.trim();
		String quesAnsString = step.getDataValue("edit_Property");
		HashMap <String,String> dataFromUI = tenoThree.buildExpectedDataMap(quesAnsString);
		
		HashMap<String, String> propertyEncompassMap=buildPropertyEncompassMap(loanNumber,guiId);
		
		Set<String> encomapssKeys=propertyEncompassMap.keySet();
		

		comparingTwoHashMap(dataFromUI,propertyEncompassMap);
		//System.out.println("Encompass data verification is completed");
	}
	
	public String compareUIdataWithEncompassDeclaration(String loanNumber,String guiId) throws Exception{

		Ten0Three tenoThree= new Ten0Three();
//		 String details = util.readALineFromFile();
//		 String loanNumber = details.split("&&")[1].trim();
//			String guiId = details.split("&&")[0].trim();
//			guiId="09e03193-3c63-4221-8f13-1f36358c53eb";
			guiId= "{"+guiId+"}";
			guiId = guiId.trim();
		String quesAnsString = step.getDataValue("DeclarationQuestionAnswer");
		HashMap <String,String> dataFromUI = tenoThree.buildExpectedDataMap(quesAnsString);
		
		HashMap<String, String> propertyEncompassMap=buildDeclarationEncompassMap(loanNumber,guiId);
		
		Set<String> encomapssKeys=propertyEncompassMap.keySet();
		if(propertyEncompassMap.equals("")){
			return null;
		}
		else{
		comparingTwoHashMap(dataFromUI,propertyEncompassMap);
		return quesAnsString;
		}
	}
	
	public void editCompareUIdataWithEncompassDeclaration() throws Exception{
		Util util = new Util();
		Ten0Three tenoThree= new Ten0Three();
		String details = util.readALineFromFile();
		 String loanNumber = details.split("&&")[1].trim();
			String guiId = details.split("&&")[0].trim();
			guiId= "{"+guiId+"}";
			guiId = guiId.trim();
		String quesAnsString = step.getDataValue("edit_Declarations");
		
		HashMap <String,String> dataFromUI = tenoThree.buildExpectedDataMap(quesAnsString);
		
		HashMap<String, String> propertyEncompassMap=buildDeclarationEncompassMap(loanNumber,guiId);
	
		Set<String> encomapssKeys=propertyEncompassMap.keySet();
		comparingTwoHashMap(dataFromUI,propertyEncompassMap);
	}
	
	public String compareUIdataWithEncompassIncome(String loanNumber,String guiId) throws Exception{

		Ten0Three tenoThree= new Ten0Three();
//		 String details = util.readALineFromFile();
//		 String loanNumber = details.split("&&")[1].trim();
//			String guiId = details.split("&&")[0].trim();
//			guiId="09e03193-3c63-4221-8f13-1f36358c53eb";
			guiId= "{"+guiId+"}";
			guiId = guiId.trim();
		String quesAnsString = step.getDataValue("1003_Income_Business");
		HashMap <String,String> dataFromUI = tenoThree.buildExpectedDataMap(quesAnsString);
		
		HashMap<String, String> IncomeEncompassMap=buildIncomeSectionEncompassMapBusiness(loanNumber,guiId);
		
		
		if(IncomeEncompassMap.equals("")){
			return null;
		}
		else{
		comparingTwoHashMap(dataFromUI,IncomeEncompassMap);
		return quesAnsString;
		}
	}
	
	public void editCompareUIdataWithEncompassIncome() throws Exception{
		Util util = new Util();
		Ten0Three tenoThree= new Ten0Three();
		String details = util.readALineFromFile();
		 String loanNumber = details.split("&&")[1].trim();
			String guiId = details.split("&&")[0].trim();
			guiId= "{"+guiId+"}";
			guiId = guiId.trim();
		String quesAnsString = step.getDataValue("edit_Income");
		HashMap <String,String> dataFromUI = tenoThree.buildExpectedDataMap(quesAnsString);
		
		HashMap<String, String> IncomeEncompassMap=buildIncomeSectionEncompassMapBusiness(loanNumber,guiId);
		
		Set<String> encomapssKeys=IncomeEncompassMap.keySet();
		comparingTwoHashMap(dataFromUI,IncomeEncompassMap);
	}
	
	
	public String compareUIdataWithEncompassRealEstate() throws Exception{
		Util util = new Util();
		Ten0Three tenoThree= new Ten0Three(); 
		String details = util.readALineFromFile();
		 String loanNumber = details.split("&&")[1].trim();
			String guiId = details.split("&&")[0].trim();
			guiId="09e03193-3c63-4221-8f13-1f36358c53eb";
			guiId= "{"+guiId+"}";
			guiId = guiId.trim();
		String quesAnsString = step.getDataValue("RealEstateQuestionAnswer");
		HashMap <String,String> dataFromUI = tenoThree.buildExpectedDataMap(quesAnsString);
		
		HashMap<String, String> RealEstateEncompassMap=buildRealEstateEncompassMap(loanNumber,guiId);
		System.out.println("out of compare");
		
		if(RealEstateEncompassMap.equals("")){
			return null;
		}
		else{
		comparingTwoHashMap(dataFromUI,RealEstateEncompassMap);
		return quesAnsString;
		}
		
	}	
	
	public void editCompareUIdataWithEncompassRealEstate() throws Exception{
		Util util = new Util();
		Ten0Three tenoThree= new Ten0Three();
		 String details = util.readALineFromFile();
		 String loanNumber = details.split("&&")[1].trim();
			String guiId = details.split("&&")[0].trim();
			guiId= "{"+guiId+"}";
			guiId = guiId.trim();
		String quesAnsString = step.getDataValue("edit_RealEstate");
		HashMap <String,String> dataFromUI = tenoThree.buildExpectedDataMap(quesAnsString);
		
		HashMap<String, String> RealEstateEncompassMap=buildRealEstateEncompassMap(loanNumber,guiId);
		
		
		comparingTwoHashMap(dataFromUI,RealEstateEncompassMap);
		System.out.println("out of compare");
	}
	
	public String compareUIdataWithEncompassLiabilities() throws Exception{
		Util util = new Util();
		Ten0Three tenoThree= new Ten0Three();
		String details = util.readALineFromFile();
		 String loanNumber = details.split("&&")[1].trim();
			String guiId = details.split("&&")[0].trim();
			guiId="09e03193-3c63-4221-8f13-1f36358c53eb";
			guiId= "{"+guiId+"}";
			guiId = guiId.trim();
		String quesAnsString = step.getDataValue("LiabilityQuestionAnswer");
		HashMap <String,String> dataFromUI = tenoThree.buildExpectedDataMap(quesAnsString);
		
		HashMap<String, String> IncomeEncompassMap=buildLiabililtiesEncompassMap(loanNumber,guiId);
		if(IncomeEncompassMap.equals("")){
			return null;
		}
		else{
		comparingTwoHashMap(dataFromUI,IncomeEncompassMap);
		return quesAnsString;
		}
	}
	
	public void compareUIdataWithEncompassAssets() throws Exception{
		Util util = new Util();
		Ten0Three tenoThree= new Ten0Three();
		String details = util.readALineFromFile();
		 String loanNumber = details.split("&&")[1].trim();
			String guiId = details.split("&&")[0].trim();
			guiId="09e03193-3c63-4221-8f13-1f36358c53eb";
			guiId= "{"+guiId+"}";
			guiId = guiId.trim();
		String quesAnsString = step.getDataValue("1003_Asset_Manual");
		HashMap <String,String> dataFromUI = tenoThree.buildExpectedDataMap(quesAnsString);
		
		HashMap<String, String> IncomeEncompassMap=buildAssetEncompassMap(loanNumber,guiId);
		
		
		comparingTwoHashMap(dataFromUI,IncomeEncompassMap);
	}	
	
	public void editCompareUIdataWithEncompassAssets() throws Exception{
		Util util = new Util();
		Ten0Three tenoThree= new Ten0Three();
		String details = util.readALineFromFile();
		 String loanNumber = details.split("&&")[1].trim();
			String guiId = details.split("&&")[0].trim();
			guiId= "{"+guiId+"}";
			guiId = guiId.trim();
		String quesAnsString = step.getDataValue("edit_Asset");
		HashMap <String,String> dataFromUI = tenoThree.buildExpectedDataMap(quesAnsString);
		
		HashMap<String, String> IncomeEncompassMap=buildAssetEncompassMap(loanNumber,guiId);
		
		
		comparingTwoHashMap(dataFromUI,IncomeEncompassMap);
	}
	
	private void comparingTwoHashMap(HashMap<String, String> dataFromUI,
			HashMap<String, String> encompassDataMap) throws TwfException, ParseException {
		  
			System.out.println("enterd compared t2 hashmap");
			
		    if (dataFromUI != null && dataFromUI != null) { //&& dataFromUI.size() == encompassDataMap.size()
		        for (Map.Entry m : encompassDataMap.entrySet()) {
		            String keyFromFirstMap = (String) m.getKey();
		            System.out.println("Key From UI----->"+keyFromFirstMap);
		            String valueFromFirstMap = (String) m.getValue();
		            System.out.println("Value from UI--->"+valueFromFirstMap);
		            String valueFromSecondMap = encompassDataMap.get(keyFromFirstMap);
		            System.out.println("Value from encompass-->"+valueFromSecondMap);
		            if(valueFromSecondMap.equals("")){
	    		    	addExceptionToReport("Unable to retrive value from the encompass for the field", keyFromFirstMap, "");
	    		    }
		            if(keyFromFirstMap.equalsIgnoreCase("Source of loan")){
		            	System.out.println("valueFromFirstMap:"+valueFromFirstMap);
		            	System.out.println("valueFromSecondMap:"+valueFromSecondMap);

		            	if(!valueFromFirstMap.equalsIgnoreCase(valueFromSecondMap)){
		            		addExceptionToReport("Source of loan", valueFromFirstMap, valueFromSecondMap);
		            	}
		            }
		            if(keyFromFirstMap.equalsIgnoreCase("My service status")||keyFromFirstMap.equalsIgnoreCase("My branch of service is")){
		            	valueFromFirstMap = valueFromFirstMap.replaceAll(" ", "").replace("s", "S").trim();
		            }
		            if(keyFromFirstMap.equalsIgnoreCase("I will use this property as")&&valueFromFirstMap.equalsIgnoreCase("A vacation home")){
		            	valueFromFirstMap = "SecondHome";
		            }
		            if(keyFromFirstMap.equalsIgnoreCase("I have been in this business since")){
			        	 valueFromSecondMap= valueFromSecondMap.substring(0, 10);
			        	 System.out.println("valueFromSecondMap:"+valueFromSecondMap);
			         }
		            if(keyFromFirstMap.equalsIgnoreCase("Estimated Property Value")||keyFromFirstMap.equalsIgnoreCase("Monthly earnings")||keyFromFirstMap.equalsIgnoreCase("Estimated Property Value")||keyFromFirstMap.equalsIgnoreCase("Monthly payment")||keyFromFirstMap.equalsIgnoreCase("Amount")||keyFromFirstMap.equalsIgnoreCase("Monthly mortgage payments")||keyFromFirstMap.equalsIgnoreCase("Property value")||keyFromFirstMap.equalsIgnoreCase("Outstanding balance")){
//		            	SimpleDateFormat sdfSource = new SimpleDateFormat("MM/dd/yyyy");
//		            	 Date date = sdfSource.parse(valueFromSecondMap);
//		            	 SimpleDateFormat sdfDestination = new SimpleDateFormat("MM/dd/yyyy");
//		            	 valueFromSecondMap = sdfDestination.format(date);
		            	//valueFromSecondMap = sdfDestination.format(valueFromSecondMap);
		   	 valueFromSecondMap= valueFromSecondMap.replace(".00", "");
			         }

		            if(keyFromFirstMap.equalsIgnoreCase("My property is a")){
		            	valueFromFirstMap=valueFromFirstMap.replaceAll(" ", "");
		            }
		            
			            	if(valueFromFirstMap.equalsIgnoreCase("Yes")){
			        	   valueFromFirstMap="Y";}
			           else if(valueFromFirstMap.equalsIgnoreCase("No")){
				        	   valueFromFirstMap="N";
				           }
			            	if(keyFromFirstMap.equalsIgnoreCase("Mobile number")||keyFromFirstMap.equalsIgnoreCase("Home phone (Optional)")){
			            		valueFromFirstMap= valueFromFirstMap.replaceAll("-", "");
			            	}
			            	
			            	if(keyFromFirstMap.equalsIgnoreCase("Do you intend to occupy the property as your primary residence?")){
			            		valueFromFirstMap= "No";
			            	}
			            	if(keyFromFirstMap.equalsIgnoreCase("My asset type")){
			            		valueFromFirstMap= valueFromFirstMap+"Account"	;
			            		System.out.println("valueFromFirstMap: "+valueFromFirstMap);
			            		}
//			            	
			            	if(!keyFromFirstMap.equalsIgnoreCase("To")&&!keyFromFirstMap.equalsIgnoreCase("Business name")&&!keyFromFirstMap.contains("Hispanic or Latino (Optional)")&&keyFromFirstMap.equalsIgnoreCase("Other Hispanic or Latino (Optional)")&&!keyFromFirstMap.contains("Ethnicity")&&!keyFromFirstMap.contains("Your demographic information")&&!keyFromFirstMap.equalsIgnoreCase("Other Hispanic or Latino (Optional)")&&!keyFromFirstMap.equalsIgnoreCase("Other Pacific Islander (Optional)")&&!keyFromFirstMap.equalsIgnoreCase("Down payment")&&!keyFromFirstMap.equalsIgnoreCase("I was born on")&&!keyFromFirstMap.equalsIgnoreCase("I am purchasing a")&&!keyFromFirstMap.equalsIgnoreCase("I will use this property as")&&!keyFromFirstMap.equalsIgnoreCase("Years")&&!keyFromFirstMap.equalsIgnoreCase("Street address line 1")&&!keyFromFirstMap.equalsIgnoreCase("Referral name/description (Optional)")&&!keyFromFirstMap.equalsIgnoreCase("I have dependents")&&!keyFromFirstMap.equalsIgnoreCase("This is my mailing address")&&!keyFromFirstMap.equalsIgnoreCase("Street address line 2 (Optional)")&&!keyFromFirstMap.equalsIgnoreCase("Months (Optional)")&&!keyFromFirstMap.equalsIgnoreCase("Extension")&&!keyFromFirstMap.equalsIgnoreCase("Source of Income")&&!keyFromFirstMap.equalsIgnoreCase("Zip Code")){	
		            	if (!valueFromFirstMap.equals(valueFromSecondMap)) {
		            	addExceptionToReport("Data didnot match between UI and Encompass", valueFromFirstMap,valueFromSecondMap);
		            
		            	}}}}
				   	

//        	if(keyFromFirstMap.equalsIgnoreCase("Street address line 1")){
//        		String streetlinetwo= valueFromFirstMap;
//        		System.out.println("streetlinetwo"+streetlinetwo);
//        		if(keyFromFirstMap.equalsIgnoreCase("Street address line 2")){
//        		valueFromFirstMap=streetlinetwo+","+valueFromFirstMap;
//        		System.out.println("street--------->"+valueFromFirstMap);}
//        	}
        	//if(Street address line 1)
        	//&&!keyFromFirstMap.equalsIgnoreCase("Other Hispanic or Latino (Optional)")
    //if(!keyFromFirstMap.contains("Hispanic or Latino (Optional)")&&keyFromFirstMap.equalsIgnoreCase("Other Hispanic or Latino (Optional)")&&!keyFromFirstMap.contains("Ethnicity")&&!keyFromFirstMap.contains("Your demographic information")&& !keyFromFirstMap.contains("The purpose of collecting this information is to help")&& !keyFromFirstMap.equalsIgnoreCase("Race")&&!keyFromFirstMap.equalsIgnoreCase("Are you a U.S. citizen?")&&!keyFromFirstMap.equalsIgnoreCase("Asian (Optional)")&&!keyFromFirstMap.equalsIgnoreCase("Native Hawaiian or Other Pacific Islander (Optional)")&&!keyFromFirstMap.equalsIgnoreCase("Have you had an ownership interest in a property in the last three years?")&&!keyFromFirstMap.equalsIgnoreCase("Tribe name (Optional)")&&!keyFromFirstMap.equalsIgnoreCase("Have you had property foreclosed upon or given title or deed in lieu thereof in the last 7 years?")&&!keyFromFirstMap.equalsIgnoreCase("Sex")&&!keyFromFirstMap.equalsIgnoreCase("Other Asian (Optional)")&&!keyFromFirstMap.equalsIgnoreCase("I am purchasing a")&&!keyFromFirstMap.equalsIgnoreCase("Other Pacific Islander (Optional)")&&!keyFromFirstMap.equalsIgnoreCase("Down payment")&&!keyFromFirstMap.equalsIgnoreCase("Referral name/Description (Optional)")&&!keyFromFirstMap.equalsIgnoreCase("Street address line 2 (Optional)")&&!keyFromFirstMap.equalsIgnoreCase("This is my mailing address")&&!keyFromFirstMap.equalsIgnoreCase("Street address line 1")&&!keyFromFirstMap.equalsIgnoreCase("I have dependents")&&!keyFromFirstMap.equalsIgnoreCase("In what type of property do you currently reside?")&&!keyFromFirstMap.equalsIgnoreCase("Zip Code")){
 //if(!keyFromFirstMap.contains("Description (Optional")&&!keyFromFirstMap.contains("Holds jointly with (Optional)")&&!keyFromFirstMap.contains("")){
     //if(!keyFromFirstMap.equalsIgnoreCase("Source of Income")&&!keyFromFirstMap.equalsIgnoreCase("Street address line 2 (Optional)")&&!keyFromFirstMap.equalsIgnoreCase("Percentage of ownership")){//!keyFromFirstMap.equalsIgnoreCase("Holds jointly with (Optional)")&&!keyFromFirstMap.equalsIgnoreCase("Description (Optional)")&&!keyFromFirstMap.equalsIgnoreCase("Status")&&!keyFromFirstMap.equalsIgnoreCase("")&&!keyFromFirstMap.equalsIgnoreCase("Street address line 1")&&!keyFromFirstMap.equalsIgnoreCase("Street address line 2 (Optional)")){
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public HashMap<String, String> printGetStartedEncompassData(String Loannumber, String GUID) throws Exception
	{
		System.out.println("entered print");

	
	HashMap<String, String> getStartedEncompassMap=buildGetStartedEncompassMap(Loannumber,GUID);//("7201816318","{e3032d09-5bcf-4f28-aa7e-5adeaa687e7e}");
		//HashMap<String, String> getStartedEncompassMap=buildPropertyEncompassMap("7201816318","{e3032d09-5bcf-4f28-aa7e-5adeaa687e7e}");
		//HashMap<String, String> getStartedEncompassMap=buildDeclarationEncompassMap("7201816318","{e3032d09-5bcf-4f28-aa7e-5adeaa687e7e}");
		//HashMap<String, String> getStartedEncompassMap= buildAssetEncompassMap("7201816318","{e3032d09-5bcf-4f28-aa7e-5adeaa687e7e}");
		//HashMap<String, String> getStartedEncompassMap= buildRealEstateEncompassMap("7201816318","{e3032d09-5bcf-4f28-aa7e-5adeaa687e7e}");
		//HashMap<String, String> getStartedEncompassMap= buildRealEstateEncompassMap("7201816318","{e3032d09-5bcf-4f28-aa7e-5adeaa687e7e}");
		//HashMap<String, String> getStartedEncompassMap=buildLiabililtiesEncompassMap("7201816318","{e3032d09-5bcf-4f28-aa7e-5adeaa687e7e}");
		//HashMap<String, String> getStartedEncompassMap=buildIncomeSectionEncompassMapForMilitary(Loannumber,GUID);

	Set<String> encomapssKeys=getStartedEncompassMap.keySet();

	for(String key: encomapssKeys)
	{

		System.out.println(key+">>>>>>>"+getStartedEncompassMap.get(key));
	}

	return getStartedEncompassMap;
	




	}
	

	public  Loan fetchEncompassData(String loanNumber, String guiID, String payload) throws Exception {
        System.out.println("Entered fetchEncompassData");
        System.out.println("\n GUID>>>>>>>"+guiID.replace("{", "").replace("}", ""));
                 Map<String, String> uriVariables = new HashMap<>();
                 ClientHttpRequestFactory requestFactory = new
                             HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
                 RestTemplate loanDetailTemplate = new RestTemplate();
                 //uriVariables.put("GUID", guiID.replace("{", "").replace("}", ""));
                 String revGUID = guiID.replace("{", "").replace("}", "").trim();
                 System.out.println("rEV guid>>>>"+revGUID);
                 uriVariables.put("GUID", "{"+revGUID+"}");
                 System.out.println("rEV guid12>>>>"+revGUID);
                 Loan mp = new Loan();
                 beginSession();
                 String sessionCookie = fetchSessionCookie();
                		 
                 HttpEntity<String> loanDetailEntity = new HttpEntity<>(payload,getHeader(sessionCookie));
                 try {
                       URI expanded = new UriTemplate(loanDetailsURL).expand(uriVariables);
                       ResponseEntity<?> loanDetailResponse = null;
                       
                        try{
                              loanDetailResponse = loanDetailTemplate.exchange(expanded, HttpMethod.POST, loanDetailEntity, String.class);
                              System.out.println("loan details repose>>>>" + loanDetailResponse.getStatusCode());
                       }catch(HttpServerErrorException e1){
                              
                               System.out.println("Exception 1>>>>"+e1.toString());
                              System.out.println("Exception 2>>>>"+e1.getResponseBodyAsString());
                       }
                       
                       
                       if (loanDetailResponse.getStatusCode() != HttpStatus.OK) {
                              /*addExceptionToReport("Fetching loan details from  Encompass failed for Loan Number " + loanNumber,
                                            String.valueOf(loanDetailResponse.getStatusCode()), "200");*/
                       }
                       ObjectMapper objectMapper = new ObjectMapper();
                        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                       try {
                              System.out.println(" Entered try block");
                              mp = objectMapper.readValue(loanDetailResponse.getBody().toString(), Loan.class);
                         //     System.out.println("jsdafhj"+mp);
                              objectMapper.readValue(loanDetailResponse.getBody().toString(), MainParser.class);
                       } catch (Exception e) {
                              /*addExceptionToReport(
                                            "Failed to fetch GUID & loan number from Encompass for loan number " + e.toString(), "", "");*/
                       }

                 } catch (Exception e) {
                       System.out.println("Exception>>>>" + e.toString());

                       /*addExceptionToReport("Exception during fetching loan details from Encompass. Loan Number is " + loanNumber,
                                     "", "");*/
                 }

                 return mp;
          }

	
//	public void ValidateEncompassSections() throws Exception{
//		System.out.println("Inside ValidateEncompassSections!!!!!>>>");
//		String errorMessage="";	
//		EncomopassLoanNumber=util.getLoanFromSubmitScreen();
////		EncomopassLoanNumber="7202193685";
//		String loanGUID="{"+util.getLoanGUIDFromDB(EncomopassLoanNumber)+"}";
//		System.out.println("Loan Number from screen>>>"+EncomopassLoanNumber);
//		Thread.sleep(15000);
//		//utill.createFileForEncomassResult();
//		try{
//		errorMessage=errorMessage+"Applicant Info\n"+compareUIdataWithEncompassgetstarted(EncomopassLoanNumber,loanGUID);
//		errorMessage=errorMessage+"\nProperty\n"+compareUIdataWithEncompassProperty(EncomopassLoanNumber,loanGUID);
//		errorMessage=errorMessage+"\nDeclarations\n"+compareUIdataWithEncompassDeclaration(EncomopassLoanNumber,loanGUID);
//		errorMessage=errorMessage+"\nIncome\n"+compareUIdataWithEncompassIncome(EncomopassLoanNumber,loanGUID);
//		errorMessage=errorMessage+"\nLiability\n"+compareUIdataWithEncompassLiabilities();
//		errorMessage=errorMessage+"\nE-consent\n"+compareUIdataWithEncompassRealEstate();
//		System.out.println("Error Message>>>"+errorMessage);
//		util.appendResultToFile("Loan number is "+EncomopassLoanNumber+"\n"+errorMessage);
//		}catch (Exception e) {
//			System.out.println("failed in verifying sections  "+e);
//			addExceptionToReport("failed in verifying sections:- "+e, "", "");
//		}
//		if(!errorMessage.equals("")){
//			addExceptionToReport("Loan number is "+EncomopassLoanNumber+"\n"+errorMessage, "", "");
//		}
//		
//	}
	
	public String verifyEncompassFieldValidationForApplicantInfo()throws Exception{
		Util util = new Util();
		System.out.println("inside verifyEncompassFieldValidationForApplicantInfo ");
		String fieldsToBeChecked=step.getDataValue("Encompass_applicant");
//		driver=DriverFactory.getDriver();
//		waitForEncompassUpdate(driver);
		String error="No verification for This section in current testcase";
		if(!fieldsToBeChecked.equals("")){
			if(KWVariables.getVariables().get("Env").equalsIgnoreCase("taurus")||KWVariables.getVariables().get("Env").equalsIgnoreCase("pisces")){
//				System.out.println("\n Value of payload:>>>"+KWVariables.getVariables().get("getStartedPayLoad_URLA"));
				error= util.verify1003FieldsFromEncompass(KWVariables.getVariables().get("getStartedPayLoad_URLA"),fieldsToBeChecked);}
			else
				error= util.verify1003FieldsFromEncompass(getStartedPayLoad,fieldsToBeChecked);
		}
		return error;
	}
	
	public String verifyEncompassFieldValidationForRealEstate()throws Exception{
		Util util = new Util();
		System.out.println("inside verifyEncompassFieldValidationForApplicantInfo ");
		String fieldsToBeChecked=step.getDataValue("Encompass_estate");
//		driver=DriverFactory.getDriver();
//		waitForEncompassUpdate(driver);
		String error="No verification for This section in current testcase";
		if(!fieldsToBeChecked.equals(""))
		error= util.verify1003FieldsFromEncompass(realEstatePayLoad,fieldsToBeChecked);
		return error;
	}
	
	public String verifyEncompassFieldValidationForProprty()throws Exception{
		Util util = new Util();
		System.out.println("inside verifyEncompassFieldValidationForProprty ");
		String fieldsToBeChecked=step.getDataValue("Encompass_property");
		String error="No verification for This section in current testcase";
		if(!fieldsToBeChecked.equals("")){
			if(KWVariables.getVariables().get("Env").equalsIgnoreCase("taurus")||KWVariables.getVariables().get("Env").equalsIgnoreCase("pisces")){
				System.out.println("\n Value of payload:>>>"+KWVariables.getVariables().get("propertyPayLoad_URLA"));
				error= util.verify1003FieldsFromEncompass(KWVariables.getVariables().get("propertyPayLoad_URLA"),fieldsToBeChecked);}
			else
				error= util.verify1003FieldsFromEncompass(propertyPayLoad,fieldsToBeChecked);
//		}
	//	 error= util.verify1003FieldsFromEncompass(propertyPayLoad,fieldsToBeChecked);
		}
		return error;
	}
	
	public String verifyEncompassFieldValidationForDeclarations()throws Exception{
		Util util = new Util();
		System.out.println("inside verifyEncompassFieldValidationForApplicantInfo ");
		String fieldsToBeChecked=step.getDataValue("Encompass_declarations");
		String error="No verification for This section in current testcase";
		if(!fieldsToBeChecked.equals("")){
			if(KWVariables.getVariables().get("Env").equalsIgnoreCase("taurus")||KWVariables.getVariables().get("Env").equalsIgnoreCase("pisces")){
				System.out.println("\n Value of payload:>>>"+KWVariables.getVariables().get("declarationPayLoad_URLA"));
				error= util.verify1003FieldsFromEncompass(KWVariables.getVariables().get("declarationPayLoad_URLA"),fieldsToBeChecked);}
			else
				error= util.verify1003FieldsFromEncompass(declarationPayLoad,fieldsToBeChecked);
		}
		  // error= util.verify1003FieldsFromEncompass(declarationPayLoad,fieldsToBeChecked);}
		return error;
	}
	public String verifyEncompassFieldValidationForIncome()throws Exception{
		Util util = new Util();
		System.out.println("inside verifyEncompassFieldValidationForIncome ");
		String fieldsToBeChecked=step.getDataValue("Encompass_income");
		String error="No verification for This section in current testcase";
		if(!fieldsToBeChecked.equals(""))
		  error= util.verify1003FieldsFromEncompass(incomePayLoadBusiness,fieldsToBeChecked);
		return error;
	}
	
	public String verifyEncompassFieldValidationForMultiPleIncome()throws Exception{
		Util util = new Util();
		System.out.println("inside verifyEncompassFieldValidationForIncome ");
		String fieldsToBeChecked=step.getDataValue("Encompass_income");
		String error="No verification for This section in current testcase";
		if(!fieldsToBeChecked.equals(""))
		  error= util.verify1003FieldsFromEncompass(multipleIncomePayLoad,fieldsToBeChecked);
		return error;
	}
	
	public void verifyEncompassLoanEditControl(String loanStatus)throws Exception{
		Util util = new Util();
		TimeUnit.MINUTES.sleep(2);
		System.out.println("inside verifyEncompassLoanEditControl WAITED>> ");
//			EncomopassLoanNumber=util.getLoanFromSubmitScreen();
		System.out.println("Loan Number from screen>>>"+EncomopassLoanNumber);
		String fieldsToBeChecked=null;
		if(loanStatus.toLowerCase().contains("submit")){
			System.out.println("Loan Status: Submitted for Loan Edit Control");
			fieldsToBeChecked="Loan Edit Control:Encompass;";
		}
		else
		{
			System.out.println("Loan Status: In-Progress for Loan Edit Control");
			fieldsToBeChecked="Loan Edit Control:Consumer Portal;";
		}
		String error="No verification for This section in current testcase";
		try{
			if(!fieldsToBeChecked.equals(""))
				error= util.verify1003FieldsFromEncompass(loanEditControlPayLoad,fieldsToBeChecked);
		}
		catch (Exception e) {
			System.out.println("failed in verifying LoanEditControl  "+e);
			addExceptionToReport("failed in verifying LoanEditControl:- "+e, "", "");
		}
	}
	
//	public String verifyEncompassFieldValidationForRealEstate()throws Exception{
//		Util util = new Util();
//		System.out.println("inside verifyEncompassFieldValidationForEconsent ");
//		String fieldsToBeChecked=step.getDataValue("Encompass_estate");
//		String error="No verification for This section in current testcase";
//		if(!fieldsToBeChecked.equals(""))
//		   error= util.verify1003FieldsFromEncompass(realEstatePayLoad,fieldsToBeChecked);
//		return error;
//	}
	
	
	public String verifyEncompassFieldValidationForLiability()throws Exception{
		Util util = new Util();
		System.out.println("inside verifyEncompassFieldValidationForLiability ");
		String fieldsToBeChecked=step.getDataValue("Encompass_Liability");
		String error="No verification for This section in current testcase";
		if(!fieldsToBeChecked.equals(""))
		    error= util.verifyLiabilityDatawithEncompassData(liabilitiesPayLoad,fieldsToBeChecked);
		return error;
	}
	
//	public String verifyEncompassFieldValidationForEconsent()throws Exception{
//		System.out.println("inside verifyEncompassFieldValidationForEconsent ");
//		String fieldsToBeChecked=step.getDataValue("Encompass_econsent");
//		String error="No verification for This section in current testcase";
//		if(!fieldsToBeChecked.equals(""))
//		   error= util.verify1003FieldsFromEncompass(guidPayLoadEnconsent,fieldsToBeChecked);
//		return error;
//	}
	
	public String verifyEncompassFieldValidationForAsset()throws Exception{
		Util util = new Util();
		System.out.println("inside verifyEncompassFieldValidationForAssets ");
		String fieldsToBeChecked=step.getDataValue("Encompass_asset");
//		driver=DriverFactory.getDriver();
//		waitForEncompassUpdate(driver);
		String error="No verification for This section in current testcase";
		if(!fieldsToBeChecked.equals(""))
		error= util.verify1003FieldsFromEncompass(assetPayLoad,fieldsToBeChecked);
		return error;
	}
	
	public String verifyEncompassFieldValidationForMultiPleAsset()throws Exception{
		Util util = new Util();
		System.out.println("inside verifyEncompassFieldValidationForAssets ");
		String fieldsToBeChecked=step.getDataValue("Encompass_asset");
//		driver=DriverFactory.getDriver();
//		waitForEncompassUpdate(driver);
		String error="No verification for This section in current testcase";
		if(!fieldsToBeChecked.equals(""))
		error= util.verify1003FieldsFromEncompass(multiPleassetPayLoad,fieldsToBeChecked);
		return error;
	}
	
	
	
	
	public void ValidateEncompassSections() throws Exception{
		Util util = new Util();
		String errorMessage="";	
		EncomopassLoanNumber=util.getLoanFromSubmitScreen();
//QA		EncomopassLoanNumber="7202411393";
//		EncomopassLoanNumber="7202615807";//in pisces CO2-7202740649 CO1-7202740477
		System.out.println("Loan Number from screen>>>"+EncomopassLoanNumber);
//		Thread.sleep(15000);
		//utill.createFileForEncomassResult();
//		try{
			if(KWVariables.getVariables().get("Env").equalsIgnoreCase("taurus")||KWVariables.getVariables().get("Env").equalsIgnoreCase("pisces")){
				errorMessage=errorMessage+"Applicant Info\n"+verifyEncompassFieldValidationForApplicantInfo();
				errorMessage=errorMessage+"\nProperty\n"+verifyEncompassFieldValidationForProprty();
				errorMessage=errorMessage+"\nDeclarations\n"+verifyEncompassFieldValidationForDeclarations();
			}
			else{
			errorMessage=errorMessage+"Applicant Info\n"+verifyEncompassFieldValidationForApplicantInfo();
			errorMessage=errorMessage+"\nProperty\n"+verifyEncompassFieldValidationForProprty();
			errorMessage=errorMessage+"\nDeclarations\n"+verifyEncompassFieldValidationForDeclarations();
			errorMessage=errorMessage+"\nIncome\n"+verifyEncompassFieldValidationForIncome();
			errorMessage=errorMessage+"\nE-consent\n"+verifyEncompassFieldValidationForRealEstate();
			errorMessage=errorMessage+"\nAssets\n"+verifyEncompassFieldValidationForAsset();
			}
//add it manually option is not there//errorMessage=errorMessage+"\nLiability\n"+verifyEncompassFieldValidationForLiability();//there is no manually added liability

		System.out.println("Error Message>>>"+errorMessage);
		if(errorMessage.contains("Encompass Data not matching for field")){
			addExceptionToReport("For the loanNumber"+EncomopassLoanNumber, errorMessage, "");
		}
//		util.appendResultToFile("Loan number is "+EncomopassLoanNumber+"\n"+errorMessage);
//		}catch (Exception e) {
//			System.out.println("failed in verifying sections  "+e);
//			addExceptionToReport("failed in verifying sections:- "+e, "", "");
//		}
		
	}

	public void ValidateMultiPleEncompassSections() throws Exception{
		Util util = new Util();
		String errorMessage="";	
//		EncomopassLoanNumber=util.getLoanFromSubmitScreen();
//		EncomopassLoanNumber="7202328847";
		System.out.println("Loan Number from screen>>>"+EncomopassLoanNumber);
		Thread.sleep(15000);
		try{
//		errorMessage=errorMessage+"\nIncome\n"+verifyEncompassFieldValidationForMultiPleIncome();
		errorMessage=errorMessage+"\nAssets\n"+verifyEncompassFieldValidationForMultiPleAsset();
		System.out.println("Error Message>>>"+errorMessage);

		}catch (Exception e) {
			System.out.println("failed in verifying sections  "+e);
			addExceptionToReport("failed in verifying sections:- "+e, "", "");
		}		
	}
	
	public ResponseEntity<?> validateDocumentEncompass() throws Exception{
		Util util = new Util();
		String errorMessage="";	
		
		Map<String, String> uriVariables = new HashMap<>();
		ResponseEntity<?> loanDetailResponse = null;		
		RestTemplate loanDetailTemplate = new RestTemplate();
//		EncomopassLoanNumber="7202612491";
		System.out.println("validateDocumentEncompass screen>>>"+EncomopassLoanNumber);
		
		System.out.println("inside verifyEncompassFieldValidationForApplicantInfo ");
		String docuMentToBeChecked=step.getDataValue("DocumentsToUpload");
		String revGUID="{"+util.getLoanGUIDFromDB(EncomopassLoanNumber)+"}";
//		TimeUnit.MINUTES.sleep(5);

		System.out.println("revGUID>>>>>>"+revGUID);
		uriVariables.put("GUID",revGUID);

		HttpEntity<String> loanDetailEntity = new HttpEntity<>(getHeader());
		System.out.println("Expanded>>");
		URI expanded = new UriTemplate(documentRetrivalURL).expand(uriVariables);
		try {
			loanDetailResponse = loanDetailTemplate.exchange(expanded, HttpMethod.GET, loanDetailEntity, String.class);
			System.out.println("Response>>>>>>"+loanDetailResponse);
			
			List<String> fillMap =  new ArrayList<String>();
			List<String> fillMap1 =  new ArrayList<String>();
	
				for(String s: loanDetailResponse.toString().split("}")){
					if(s.contains("\"documentName\"")){
						String f=s.split(Pattern.quote("\"documentName\""))[1];
						String paymentMode=f.split(",")[0].split(":")[1];
						fillMap1.add(paymentMode.replaceAll("\"", "").trim());
							System.out.println("key>>>>>>"+paymentMode.replaceAll("\"", ""));
					}		
				}
				for(String key:docuMentToBeChecked.split(";")){
					System.out.println("Document in datapool>>>>"+key);
					fillMap.add(key.trim());
					System.out.println("Freeee>>>"+Collections.frequency(fillMap1, key));
//					if(!fillMap.toString().trim().equalsIgnoreCase(fillMap1.toString().trim())){
//						addExceptionToReport("Document Name uploaded is different then displayed", fillMap.toString(), fillMap1.toString());
//					}
					if(!(Collections.frequency(fillMap1, key)==2)){
						addExceptionToReport("Mismatch in no. of times document is uploaded", fillMap.toString(), fillMap1.toString());
					}
				}			
		} catch (HttpServerErrorException e) {
			addExceptionToReport("Exception seen while fetching the loan details response " + e.toString(), "", "");
		}
		
		if (loanDetailResponse.getStatusCode() != HttpStatus.OK) {
			addExceptionToReport("Fetching loan details from  Encompass failed for Loan Number " + EncomopassLoanNumber,
					String.valueOf(loanDetailResponse.getStatusCode()), "200");
		}

		return loanDetailResponse;
	}
	
	
		/**
	 * Encompass Validation of Getstarted
	 * @return
	 * @throws Exception
	 */
	public String verifyEncompassFieldValidationForGetStartedCoApplicantInfo()throws Exception{
		Util util = new Util();
		System.out.println("inside verifyEncompassFieldValidationForCoApplicantInfo ");
		String fieldsToBeChecked=step.getDataValue("Encompass_CoAppGetStarted");
//		driver=DriverFactory.getDriver(); //		waitForEncompassUpdate(driver);
		String error="No verification for This section in current testcase";
		if(!fieldsToBeChecked.equals("")){
			if(KWVariables.getVariables().get("Env").equalsIgnoreCase("taurus")||KWVariables.getVariables().get("Env").equalsIgnoreCase("pisces")){
				System.out.println("\n Value of payload:>>>"+KWVariables.getVariables().get("getStartedCoAppPayLoad_URL"));
				error= util.verify1003FieldsFromEncompass(KWVariables.getVariables().get("getStartedCoAppPayLoad_URL"),fieldsToBeChecked);}
			else
				error= util.verify1003FieldsFromEncompass(getStartedCoAppPayLoad,fieldsToBeChecked);
		}
//		if(!fieldsToBeChecked.equals(""))
//		error= util.verify1003FieldsFromEncompass(getStartedCoAppPayLoad,fieldsToBeChecked);
		return error;
	}

	/**
	 * Encomapss validaiton of Declarations
	 * @return
	 * @throws Exception
	 */
	public String verifyEncompassFieldValidationForDeclarationCoApplicantInfo()throws Exception{
		Util util = new Util();
		System.out.println("inside verifyEncompassFieldValidationForCoApplicantInfo ");
		String fieldsToBeChecked=step.getDataValue("Encompass_CoAppDeclarations");
//		driver=DriverFactory.getDriver();
//		waitForEncompassUpdate(driver);
		String error="No verification for This section in current testcase";
		if(!fieldsToBeChecked.equals("")){
			if(KWVariables.getVariables().get("Env").equalsIgnoreCase("taurus")||KWVariables.getVariables().get("Env").equalsIgnoreCase("pisces")){
				System.out.println("\n Value of payload:>>>"+KWVariables.getVariables().get("declarationsCoAppPayLoad_URLA"));
				error= util.verify1003FieldsFromEncompass(KWVariables.getVariables().get("declarationsCoAppPayLoad_URLA"),fieldsToBeChecked);}
			else
				error= util.verify1003FieldsFromEncompass(declarationsCoAppPayLoad,fieldsToBeChecked);
		}
//		if(!fieldsToBeChecked.equals(""))
//		error= util.verify1003FieldsFromEncompass(declarationsCoAppPayLoad,fieldsToBeChecked);
		return error;
	}

	/**
	 * Encomapss validaiton of Income
	 * @return
	 * @throws Exception
	 */
	public String verifyEncompassFieldValidationForIncomeCoApplicantInfo()throws Exception{
		Util util = new Util();

		String fieldsToBeChecked=step.getDataValue("Encompass_CoAppIncome");
//		driver=DriverFactory.getDriver();
//		waitForEncompassUpdate(driver);
		String error="No verification for This section in current testcase";
		if(!fieldsToBeChecked.equals(""))
		error= util.verify1003FieldsFromEncompass(incomeCoAppPayload,fieldsToBeChecked);
		return error;
	}

	/**
	 * Encomapss validaiton of Real Estate
	 * @return
	 * @throws Exception
	 */
	public String verifyEncompassFieldValidationForRealEstateCoApplicantInfo()throws Exception{
		Util util = new Util();

		String fieldsToBeChecked=step.getDataValue("Encompass_CoAppRealEstate");
//		driver=DriverFactory.getDriver();
//		waitForEncompassUpdate(driver);
		String error="No verification for This section in current testcase";
		if(!fieldsToBeChecked.equals(""))
		error= util.verify1003FieldsFromEncompass(realEstateCoAppPayload,fieldsToBeChecked);
		return error;
	}

	/**
	 * Encomapss validaiton of Assets
	 * @return
	 * @throws Exception
	 */
	public String verifyEncompassFieldValidationForAssetsCoApplicantInfo()throws Exception{
		Util util = new Util();

		String fieldsToBeChecked=step.getDataValue("Encompass_CoAppAssets");
//		driver=DriverFactory.getDriver();
//		waitForEncompassUpdate(driver);
		String error="No verification for This section in current testcase";
		if(!fieldsToBeChecked.equals(""))
		error= util.verify1003FieldsFromEncompass(assetsCoAppPayload,fieldsToBeChecked);
		return error;
	}
	/**
	 *
	 * ValidateEncompassSectionsForCoApplicant
	 * @throws Exception
	 */

	public void validateEncompassSectionsForCoApplicant() throws Exception{
		Util util = new Util();
		String errorMessage="";
    	EncomopassLoanNumber=util.getLoanFromSubmitScreen();
    	System.out.println(EncomopassLoanNumber);
//		EncomopassLoanNumber="7202740649";//CO4-7202585507 CO3-7202588438,CO2-7202586300,CO5-7202586392,CO6-7202597433,CO7-7202587083,CO8-7202587026
		System.out.println("Loan Number from screen>>>"+EncomopassLoanNumber);
//		Thread.sleep(15000);

//		try{
		if(KWVariables.getVariables().get("Env").equalsIgnoreCase("taurus")||KWVariables.getVariables().get("Env").equalsIgnoreCase("pisces")){
			errorMessage=errorMessage+"Co-Borrower GetStarted Info\n"+verifyEncompassFieldValidationForGetStartedCoApplicantInfo();
			errorMessage=errorMessage+"\nCo-Borrower Declarations\n"+verifyEncompassFieldValidationForDeclarationCoApplicantInfo();
		}
		else{
			errorMessage=errorMessage+"GetStarted Info\n"+verifyEncompassFieldValidationForGetStartedCoApplicantInfo();
			errorMessage=errorMessage+"\nDeclarations\n"+verifyEncompassFieldValidationForDeclarationCoApplicantInfo();
			errorMessage=errorMessage+"\nIncome\n"+verifyEncompassFieldValidationForIncomeCoApplicantInfo();
			errorMessage=errorMessage+"\nRealEstate\n"+verifyEncompassFieldValidationForRealEstateCoApplicantInfo();
			errorMessage=errorMessage+"\nAssets\n"+verifyEncompassFieldValidationForAssetsCoApplicantInfo();
		}
//add it manually option is not there//errorMessage=errorMessage+"\nLiability\n"+verifyEncompassFieldValidationForLiability();//there is no manually added liability

		System.out.println("Error Message>>>"+errorMessage);
//		util.appendResultToFile("Loan number is "+EncomopassLoanNumber+"\n"+errorMessage);
//		}catch (Exception e) {
//			System.out.println("failed in verifying sections  "+e);
//			addExceptionToReport("failed in verifying sections:- "+e, "", "");
//		}
//		if(!errorMessage.equals("")){
//			addExceptionToReport("Loan number is "+EncomopassLoanNumber+"\n"+errorMessage, "", "");
//		}

		if(errorMessage.contains("Encompass Data not matching for field")){
			addExceptionToReport("For the loanNumber"+EncomopassLoanNumber, errorMessage, "");
		}
	}

//	public String getLoanTovalidateEncompass() throws Exception{
//		Thread.sleep(5000);
//		System.out.println("inside getLoanFromSubmitScreen");
//		String loanNumber=getElementByUsing("Loan_Number").getText().split("Your loan prequalification")[1].split("")[0].trim();
//		System.out.println("loanNumber from getLoanFromSubmitScreen>>>>>"+loanNumber);
//		return loanNumber;
//
//	}
	
	public void loanNumberValidation() throws BiffException, IOException, TwfException, InterruptedException{
		Util util = new Util();
		util.getLoanFromSubmitScreen();
	}
	
	
	@Override
	public void checkPage() {
		// TODO Auto-generated method stub

	}

}

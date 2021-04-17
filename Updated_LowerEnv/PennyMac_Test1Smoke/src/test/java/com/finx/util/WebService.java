package com.finx.util;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
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
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.HttpHeader;
import com.gargoylesoftware.htmlunit.javascript.host.security.FederatedCredential;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.tavant.base.DriverFactory;
import com.tavant.kwutils.CustomStep;
import com.tavant.kwutils.KWVariables;

public class WebService extends CustomStep {

	Util util = new Util();
	static HashMap<String, String> payLoadFieldMap = new HashMap<String, String>();
	static boolean isLoanLock = false;
	String upDateLoanStatusPayLoad = "{\"CX.BROKERLOANSTATUS\":\"Loan Created\", \"3142\":\"6/30/2017\" }";
	String upDateLoanSummery = "{\"CX.BROKERLOANSTATUS\":\"Loan Created\",\"762\":\"07/21/2017\",\"3\": \"4.001\",\"4\":\"96\",\"353\":\"2.322\",\"976\":\"8.270\",\"1540\":\"10.111\",\"VASUMM.X23\":\"850\",\"MORNET.X67\":\"Full Documentation\",\"3142\":\"07/17/2017\",\"1041\":\"Attached\",\"LE1.X33\":\"07/21/2017\",\"356\":\"$26,087\",\"1811\":\"PrimaryResidence\",\"2301\":\"07/17/2017\",\"CX.PRICING.IMPOUNDS\":\"Not Waived\",\"608\":\"Fixed\",\"1612\":\"John Smith\",\"4000\":\"John\",\"4002\":\"Smith\",\"748\":\"09/03/2017\",\"TPO.X61\":\"Tavant Dev\",\"TPO.X74\":\"Tavant QA\",\"1172\":\"VA\"}";
	String upDateCancelled = "{\"CX.BROKERLOANSTATUS\":\"Cancelled\",\"749\":\"07/07/2017\",\"762\":\"07/21/2017\",\"3\": \"4.001\"}";
	String upDateDeclined = "{\"CX.BROKERLOANSTATUS\":\"Declined\",\"749\":\"07/07/2017\",\"762\":\"07/21/2017\",\"3\": \"4.001\"}";
	String IncomeguiIdPayload = "[\"CX.BROKERLOANSTATUS\", \"101\",\"110\",\"103\",\"112\",\"104\",\"113\"]";
	String upDateFunded = "[{\"operation\":\"replace\",\"changes\":{\"CX.BROKERLOANSTATUS\":\"Funded\",\"762\":\"07/21/2017\",\"3\": \"5.555\",\"1109\":1110001.11}}]";
	String guiIdPayload = "[{\"FilterCriteria\": {\"FieldId\": \"Loan.LoanNumber\",\"FieldValue\": \"$\", \"FieldMatchType\": \"contains\",\"FieldDataType\": \"string\"},\"RequiredFields\": [ \"Loan.Guid\"]}]";
	String loanDetailPayload = "[\"19\"]";
	static String GUID = "";
	String factapayLoad = "[\"DISCLOSURE.X41\",\"DISCLOSURE.X42\",\"DISCLOSURE.X43\",\"DISCLOSURE.X44\",\"DISCLOSURE.X45\",\"DISCLOSURE.X640\",\"DISCLOSURE.X46\",\"DISCLOSURE.X47\",\"DISCLOSURE.X48\",\"DISCLOSURE.X49\",\"DISCLOSURE.X50\",\"1414\",\"DISCLOSURE.X51\",\"DISCLOSURE.X635\",\"DISCLOSURE.X53\",\"DISCLOSURE.X54\",\"DISCLOSURE.X55\",\"DISCLOSURE.X56\",\"DISCLOSURE.X175\",\"1415\",\"DISCLOSURE.X52\",\"DISCLOSURE.X636\",\"DISCLOSURE.X57\",\"DISCLOSURE.X58\",\"DISCLOSURE.X59\",\"DISCLOSURE.X60\",\"DISCLOSURE.X178\",\"DISCLOSURE.X1\",\"DISCLOSURE.X2\",\"DISCLOSURE.X3\",\"DISCLOSURE.X4\",\"DISCLOSURE.X5\",\"DISCLOSURE.X638\",\"DISCLOSURE.X6\",\"DISCLOSURE.X7\",\"DISCLOSURE.X8\",\"DISCLOSURE.X9\",\"DISCLOSURE.X10\",\"67\",\"DISCLOSURE.X11\",\"DISCLOSURE.X631\",\"DISCLOSURE.X13\",\"DISCLOSURE.X14\",\"DISCLOSURE.X15\",\"DISCLOSURE.X16\",\"DISCLOSURE.X173\",\"60\",\"DISCLOSURE.X12\",\"DISCLOSURE.X632\",\"DISCLOSURE.X17\",\"DISCLOSURE.X18\",\"DISCLOSURE.X19\",\"DISCLOSURE.X20\",\"DISCLOSURE.X176\",\"DISCLOSURE.X21\",\"DISCLOSURE.X22\",\"DISCLOSURE.X23\",\"DISCLOSURE.X24\",\"DISCLOSURE.X25\",\"DISCLOSURE.X639\",\"DISCLOSURE.X26\",\"DISCLOSURE.X27\",\"DISCLOSURE.X28\",\"DISCLOSURE.X29\",\"DISCLOSURE.X30\",\"1450\",\"DISCLOSURE.X31\",\"DISCLOSURE.X633\",\"DISCLOSURE.X33\",\"DISCLOSURE.X34\",\"DISCLOSURE.X35\",\"DISCLOSURE.X36\",\"DISCLOSURE.X174\",\"1452\",\"DISCLOSURE.X32\",\"DISCLOSURE.X634\",\"DISCLOSURE.X37\",\"DISCLOSURE.X38\",\"DISCLOSURE.X39\",\"DISCLOSURE.X40\",\"DISCLOSURE.X177\",\"2324\",\"2325\",\"2326\",\"2328\",\"2329\",\"2331\",\"2332\",\"2333\",\"2334\",\"2558\",\"2559\",\"2560\",\"2564\",\"2567\",\"2568\"]";
	String upDateLoanStatusFundedPayLoad = "[{\"operation\":\"replace\",\"changes\":{\"CX.BROKERLOANSTATUS\":\"Funded\",\"762\":\"07/21/2017\",\"3\": \"5.555\",\"1109\":1110001.11}}]";
	String upDateLoanStatusCancelledPayLoad = "[{\"operation\":\"replace\",\"changes\":{\"CX.BROKERLOANSTATUS\":\"Cancelled\",\"762\":\"07/21/2017\",\"3\": \"5.555\",\"1109\":1110001.11}}]";
	String upDateLoanStatusDeclinedPayLoad = "[{\"operation\":\"replace\",\"changes\":{\"CX.BROKERLOANSTATUS\":\"Declined\",\"762\":\"07/21/2017\",\"3\": \"5.555\",\"1109\":1110001.11}}]";
	String upDateLoanStatusInitialCreditReviewPayLoad = "[{\"operation\":\"replace\",\"changes\":{\"CX.BROKERLOANSTATUS\":\"Initial Credit Review\",\"762\":\"07/21/2018\",\"3\": \"5.555\",\"1109\":1110001.11}}]";
	String upDateLoanStatusConditonalApprovalPayLoad = "[{\"operation\":\"replace\",\"changes\":{\"CX.BROKERLOANSTATUS\":\"Conditional Approval\",\"762\":\"07/21/2018\",\"3\": \"5.555\",\"1109\":1110001.11}}]";
	String upDateLoanStatusFinalApprovalPayLoad = "[{\"operation\":\"replace\",\"changes\":{\"CX.BROKERLOANSTATUS\":\"Final Approval\",\"762\":\"07/21/2018\",\"3\": \"5.555\",\"1109\":1110001.11}}]";
	String upDateLoanStatusCDSentPayLoad = "[{\"operation\":\"replace\",\"changes\":{\"CX.BROKERLOANSTATUS\":\"CD Sent\",\"762\":\"07/23/2019\",\"3\": \"5.555\",\"1109\":1110001.11}}]";
	String upDateLoanStatusReadyForDocsPayLoad = "[{\"operation\":\"replace\",\"changes\":{\"CX.BROKERLOANSTATUS\":\"Ready for Docs\",\"762\":\"07/21/2018\",\"3\": \"5.555\",\"1109\":1110001.11}}]";
	String upDateLoanStatusDocsOutPayLoad = "[{\"operation\":\"replace\",\"changes\":{\"CX.BROKERLOANSTATUS\":\"Docs Out\",\"762\":\"07/21/2018\",\"3\": \"5.555\",\"1109\":1110001.11}}]";
	String upDatePayloadBodyTemplate = "[{\"operation\":\"replace\",\"changes\":$}]";
	String guiIdPayload1 = "[{\"FilterCriteria\": {\"FieldId\": \"Loan.LoanNumber\",\"FieldValue\": \"$\", \"FieldMatchType\": \"contains\",\"FieldDataType\": \"string\"},\"RequiredFields\": [\"CX.BROKERLOANSTATUS\", \"11\",\"12\",\"13\",\"14\",\"15\",\"1109\",\"4000\",\"4002\",\"4003\",\"749\",\"136\",\"356\",\"3\",\"608\",\"1172\",\"4\",\"16\",\"995\"]}]";
	static String loanNumberOnboard;
	static String loanGuidOnboard;
//	String apiKey = "438C9CA0-0A86-4ED1-8A88-0BBA601A657E";
	 String apiKey = "00785e7d-4082-4a7e-9c99-5c8185c73d8c";
	String beginSessionUrl = "http://internal-pennymac-encompass-qa-1662691272.us-west-1.elb.amazonaws.com/encompasswrapper/encompass/api/v1/usersessions/usersession/begin";
	static String endSessionUrl = "http://$//encompass/api/v2/usersessions/usersession/end";
	static String fetchGUIDURL = "http://$//encompass/api/v2/pipeline";
	// static String loanDetailsURL =
	// "http://ditech-encompass-uat-lb-271423941.us-east-2.elb.amazonaws.com/Encompasswrapper/encompass/api/v2/loans/{GUID}/retrieveLoan?getFieldsForAllBorrowerPairs=false&getAllFieldsForBorrowerPair=false&modulename=na";
	// "http://$//encompass/api/v1/loans/{GUID}/retrieveLoan?getFieldsForAllBorrowerPairs=true&getAllFieldsForBorrowerPair=true&moduleName=1003";
	static String loanDetailsURL = "http://internal-tavant-encompass-cdp-alb-341137667.us-west-2.elb.amazonaws.com/Encompasswrapper/encompass/api/v2/loans/{GUID}/retrieveLoan?getFieldsForAllBorrowerPairs=false&getAllFieldsForBorrowerPair=false";
	
	static String upDateLoanStatusURL = "http://$//encompass/api/v1/loans/{GUID}";
	static String documentViewURL = "http://$//encompass/api/v1/loans/{GUID}/documents";
	static String docContentURL = "http://$//encompass/api/v1/loans/{GUID}/documents?doctype=loandoc&documentId={DOCID}";
	static String acessTokenUrl = "https://sandbox1-finconnect.tavant.com/fchub/oauth/token?grant_type=client_credentials";
	static String fetchVOIdetailsUrl = "https://sandbox1-finconnect.tavant.com/fchub/rest/verify/income/submit";
	static String bodyTofetchdataFromEncompass = "{\"operationType\": \"Submit\",\"request\": {\"reportType\": \"Other\",\"lenderCaseIdentifier\": \"e12f5e62-a7af-4cca-9add-83534c584bf5\",\"borrowerId\": \"B12346\",\"requestType\": \"Individual\",\"reportTypeOtherDescription\": \"VOI\",\"loanApplication\": {\"borrower\": [{\"borrowerPrintPositionType\": \"BORROWER\",\"borrowerSSN\": \"%SSN\",\"borrowerLastname\": \"DELINQUENT\",\"borrowerFirstname\": \"DAVID\",\"borrowerId\": \"B12346\"}]},\"reportRequestActionType\": \"Submit\"},\"credentials\": {\"userName\": \"999TT72916\",\"password\": \"00CizkrrkRYuY\"},\"mismoversionID\": \"2.3.1\"}";

	static String acessTokenUrlForCreditpull = "https://stg-finconnect.tavant.com/fchub/oauth/token?grant_type=client_credentials";
	static String fetchcreditpulldetailsUrl = "https://stg-finconnect.tavant.com/fchub/rest/credit/v2/submitrequest";

	static String bodyFormatForJointCreditPull = "{\"request\":[{\"creditReportType\":\"MERGE\",\"equifaxIndicator\":\"Y\",\"transUnionIndicator\":\"Y\",\"borrowerData\":[{\"borrowerPrintPositionType\":\"BORROWER\",\"borrowerSSN\":\"%SSN\",\"residence\":[{\"address\":{\"zip5\":\"%zip\",\"streetAddress\":\"%street\",\"state\":\"%state\",\"city\":\"%city\"},\"residenceType\":\"CURRENT\"}],\"borrowerLastname\":\"%lastname\",\"borrowerFirstname\":\"%firstname\",\"borrowerId\":\"IN000479-0\"},{\"borrowerPrintPositionType\":\"CO_BORROWER\",\"borrowerSSN\":\"%co_SSN\",\"residence\":[{\"address\":{\"zip5\":\"%co_zip\",\"streetAddress\":\"%co_street\",\"state\":\"%co_state\",\"city\":\"%co_city\"},\"residenceType\":\"CURRENT\"}],\"borrowerLastname\":\"%co_lastname\",\"borrowerFirstname\":\"%co_firstname\",\"borrowerId\":\"IN000479-1\"}],\"creditRequestType\":\"JOINT\",\"creditReportRequestActionType\":\"SUBMIT\",\"borrowerId\":\"IN000479-0 IN000479-1\",\"experianIndicator\":\"Y\",\"creditRequestID\":\"CR74586676-8151-447b-a48a-733c6f683db8\",\"creditRequestDateTime\":\"2018-11-05T16:52:01\"}],\"productCode\":\"CLC_CDC_1\",\"lenderCaseIdentifier\":\"IN000479\",\"requestingPartyRequestedByName\":\"\",\"requestingPartyBranchIdentifier\":\"\",\"key\":[{\"name\":\"PDFFile\",\"value\":true},{\"name\":\"HTMLFile\",\"value\":false},{\"name\":\"TextFile\",\"value\":false}]}";
	static String bodyToFetchdataFromEncompassforCreditpull = "{\"request\":[{\"creditReportType\":\"MERGE\",\"equifaxIndicator\":\"Y\",\"transUnionIndicator\":\"Y\",\"borrowerData\":[{\"borrowerPrintPositionType\":\"BORROWER\",\"borrowerSSN\":\"%SSN\",\"residence\":[{\"address\":{\"zip5\":\"%zip\",\"streetAddress\":\"%street\",\"state\":\"%state\",\"city\":\"%city\"},\"residenceType\":\"CURRENT\"}],\"borrowerLastname\":\"%lastname\",\"borrowerFirstname\":\"%firstname\",\"borrowerId\":\"IN000478-0\"}],\"creditRequestType\":\"INDIVIDUAL\",\"creditReportRequestActionType\":\"SUBMIT\",\"borrowerId\":\"IN000478-0\",\"experianIndicator\":\"Y\",\"creditRequestID\":\"CR74586676-8151-447b-a48a-733c6f683db8\",\"creditRequestDateTime\":\"2018-11-05T16:52:01\"}],\"productCode\":\"CLC_CDC_1\",\"lenderCaseIdentifier\":\"IN000478\",\"requestingPartyRequestedByName\":\"\",\"requestingPartyBranchIdentifier\":\"\",\"key\":[{\"name\":\"PDFFile\",\"value\":true},{\"name\":\"HTMLFile\",\"value\":false},{\"name\":\"TextFile\",\"value\":false}]}";

	// http://pennymac-encompass-product-qa-1675011018.us-west-1.elb.amazonaws.com/encompasswrapperbrokerproduct/encompass/api/v1

	String qaURLAddress = "pennymac-encompass-product-qa-1675011018.us-west-1.elb.amazonaws.com/encompasswrapperbrokerproduct";
	String uatURLAddress = "pennymac-encompass-uat-1597060274.us-west-1.elb.amazonaws.com";
	String devURLAddress = "encompass-poc-lb-1126134239.us-west-1.elb.amazonaws.com";
	String performanceURLAddress = "encompass-poc-lb-1126134239.us-west-1.elb.amazonaws.com";
	String internalURLAddress = "internal-pennymac-prod-encompass-514458499.us-west-1.elb.amazonaws.com";
	String qaHFURLAddress = "encompass-R1-hotfix-qa-537884192.us-west-1.elb.amazonaws.com/encompasswrapper-r1-hotfix";
	String creatLoanURL = "";

	public WebService() {
		String envType = null;
		try {
			// envType = KWVariables.getVariables().get("Env");
			envType = "QA";
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String address = "";

		switch (envType.toLowerCase()) {
		case "qa":
			address = qaURLAddress;
			break;
		case "qa-hf":
			address = qaHFURLAddress;
			break;
		case "dev":
			address = devURLAddress;
			break;
		case "uat":
			address = uatURLAddress;
			break;
		case "uat-hf":
			address = uatURLAddress;
			break;
		case "performance":
			address = performanceURLAddress;
			break;
		default:
			address = internalURLAddress;
			break;
		}
		buildEndPointURLs(address);

	}

	private void buildEndPointURLs(String address) {
		beginSessionUrl = beginSessionUrl.replace("$", address);
		endSessionUrl = endSessionUrl.replace("$", address);
		fetchGUIDURL = fetchGUIDURL.replace("$", address);
		loanDetailsURL = loanDetailsURL.replace("$", address);
		upDateLoanStatusURL = upDateLoanStatusURL.replace("$", address);
		documentViewURL = documentViewURL.replace("$", address);
		docContentURL = docContentURL.replace("$", address);
	}

	public String buildPayLoad(String payLoadDetails) {
		buildPayLoadFieldMap();
		String[] payLoadArray = payLoadDetails.split(";");
		String payloadValue = "";
		for (String payload : payLoadArray) {
			String loanFieldName = payload.split(":")[0];
			String loanFieldValue = payload.split(":")[1];
			payloadValue = payloadValue + "\"" + payLoadFieldMap.get(loanFieldName) + "\":\"" + loanFieldValue + "\""
					+ ",";
		}

		if (payloadValue != null && payloadValue.length() > 0) {
			payloadValue = payloadValue.substring(0, payloadValue.length() - 1);
			payloadValue = "{" + payloadValue + "}";
		}
		return payloadValue;

	}

	/**
	 * Method Name: postRequest Purpose: To post HTTP Request
	 *
	 * @param url
	 * @param payLoad
	 * @return
	 */

	public ResponseEntity<?> postRequest(String url, String payLoad, String loanNumber) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate rt = new RestTemplate();
		HttpEntity<String> entity = new HttpEntity<String>(payLoad, headers);
		ResponseEntity<?> re = rt.exchange(url, HttpMethod.POST, entity, String.class);
		if (re.getStatusCode() != HttpStatus.OK) {
			addExceptionToReport("Fetching GUID From Encompass failed for Loan Number " + loanNumber,
					String.valueOf(re.getStatusCode()), "200");
		}
		return re;
	}

	/**
	 *
	 */

	/**
	 * To Fetch Loan Status From Encompass
	 *
	 * @param loanNumber
	 * @return
	 * @throws Exception
	 */
	/*
	 * public String fetchLoanStatusFormEncompass(String loanNumber) throws
	 * Exception { guiIdPayload1 = guiIdPayload1.replace("$", loanNumber);
	 * String loanStatus = "";
	 * 
	 * ResponseEntity<?> responseEntity = postRequest(fetchGUIDURL,
	 * guiIdPayload1, loanNumber);
	 * 
	 * ObjectMapper objectMapper = new ObjectMapper();
	 * objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
	 * false); try { MainParser mp =
	 * objectMapper.readValue(responseEntity.getBody().toString(),
	 * MainParser.class);
	 * objectMapper.readValue(responseEntity.getBody().toString(),
	 * MainParser.class); loanStatus =
	 * mp.getLoans().get(0).getBROKERLOANSTATUS().trim();
	 * 
	 * return loanStatus.trim();
	 * 
	 * } catch (Exception e) {
	 * addExceptionToReport("Failed to fetch Loan Status from Encompass for loan number "
	 * + loanNumber, e.toString(), ""); }
	 * 
	 * return null;
	 * 
	 * }
	 */

	/**
	 * To Fetch Borrower Name From Encompass
	 *
	 * @param loanNumber
	 * @return
	 * @throws Exception
	 */
	/*
	 * public String fetchBorrowerNameFormEncompass(String loanNumber) throws
	 * Exception {
	 * 
	 * guiIdPayload1 = guiIdPayload1.replace("$", loanNumber); String firstName
	 * = ""; String lastName = "";
	 * 
	 * ResponseEntity<?> responseEntity = postRequest(fetchGUIDURL,
	 * guiIdPayload1, loanNumber);
	 * 
	 * ObjectMapper objectMapper = new ObjectMapper();
	 * objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
	 * false); try {
	 * 
	 * MainParser mp =
	 * objectMapper.readValue(responseEntity.getBody().toString(),
	 * MainParser.class);
	 * objectMapper.readValue(responseEntity.getBody().toString(),
	 * MainParser.class); firstName =
	 * mp.getLoans().get(0).getFirstName().trim(); lastName =
	 * mp.getLoans().get(0).getLastName().trim(); return firstName + " " +
	 * lastName;
	 * 
	 * } catch (Exception e) {
	 * addExceptionToReport("Failed to fetch Borrower NameForm from Encompass for loan number "
	 * + loanNumber, e.toString(), ""); }
	 * 
	 * return null;
	 * 
	 * }
	 */

	/**
	 * To Fetch Loan Amount Form Encompass
	 *
	 * @param loanNumber
	 * @return
	 * @throws Exception
	 */
	/*
	 * public String fetchLoanAmountFormEncompass(String loanNumber) throws
	 * Exception { guiIdPayload1 = guiIdPayload1.replace("$", loanNumber);
	 * String LoanAmount = ""; ResponseEntity<?> responseEntity =
	 * postRequest(fetchGUIDURL, guiIdPayload1, loanNumber); ObjectMapper
	 * objectMapper = new ObjectMapper();
	 * objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
	 * false); try {
	 * 
	 * MainParser mp =
	 * objectMapper.readValue(responseEntity.getBody().toString(),
	 * MainParser.class);
	 * objectMapper.readValue(responseEntity.getBody().toString(),
	 * MainParser.class); LoanAmount =
	 * mp.getLoans().get(0).getLoanAmount().trim(); return LoanAmount.trim();
	 * 
	 * } catch (Exception e) {
	 * addExceptionToReport("Failed to fetch Loan Amount from Encompass for loan number "
	 * + loanNumber, e.toString(), ""); }
	 * 
	 * return null; }
	 */

	/**
	 * To Fetch Status Date From Encompass
	 *
	 * @param loanNumber
	 * @return
	 * @throws Exception
	 */
	/*
	 * public String fetchStatusDateFormEncompass(String loanNumber) throws
	 * Exception { guiIdPayload1 = guiIdPayload1.replace("$", loanNumber);
	 * String statusDate = ""; ResponseEntity<?> responseEntity =
	 * postRequest(fetchGUIDURL, guiIdPayload1, loanNumber);
	 * 
	 * ObjectMapper objectMapper = new ObjectMapper();
	 * objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
	 * false); try {
	 * 
	 * MainParser mp =
	 * objectMapper.readValue(responseEntity.getBody().toString(),
	 * MainParser.class);
	 * objectMapper.readValue(responseEntity.getBody().toString(),
	 * MainParser.class); statusDate =
	 * mp.getLoans().get(0).getStatusDate().trim();
	 * 
	 * return statusDate;
	 * 
	 * } catch (Exception e) {
	 * addExceptionToReport("Failed to fetch Status Date Encompass for loan number "
	 * + loanNumber, e.toString(), ""); }
	 * 
	 * return null; }
	 */

	/**
	 * To Fetch Property Address From Encompass
	 *
	 * @param loanNumber
	 * @return
	 * @throws Exception
	 */
	/*
	 * public String fetchPropertyAddressFormEncompass(String loanNumber) throws
	 * Exception { guiIdPayload1 = guiIdPayload1.replace("$", loanNumber);
	 * String address = ""; ResponseEntity<?> responseEntity =
	 * postRequest(fetchGUIDURL, guiIdPayload1, loanNumber);
	 * 
	 * ObjectMapper objectMapper = new ObjectMapper();
	 * objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
	 * false); try {
	 * 
	 * MainParser mp =
	 * objectMapper.readValue(responseEntity.getBody().toString(),
	 * MainParser.class);
	 * objectMapper.readValue(responseEntity.getBody().toString(),
	 * MainParser.class);
	 * 
	 * address = mp.getLoans().get(0).getaddress11() == null ? address : address
	 * + " " + mp.getLoans().get(0).getaddress11().trim(); address =
	 * mp.getLoans().get(0).getaddress12() == null ? address : address + " " +
	 * mp.getLoans().get(0).getaddress12().trim(); address =
	 * mp.getLoans().get(0).getaddress14() == null ? address : address + " " +
	 * mp.getLoans().get(0).getaddress14().trim(); address =
	 * mp.getLoans().get(0).getaddress15() == null ? address : address + " " +
	 * mp.getLoans().get(0).getaddress15().trim(); address =
	 * mp.getLoans().get(0).getaddress13() == null ? address : address + " " +
	 * mp.getLoans().get(0).getaddress13().trim();
	 * 
	 * return address.trim();
	 * 
	 * } catch (Exception e) {
	 * addExceptionToReport("Failed to fetch Property Address from Encompass for loan number "
	 * + loanNumber, e.toString(), ""); }
	 * 
	 * return null;
	 * 
	 * }
	 */

	public HashMap<String, String> buildPayLoadFieldMap() {
		String[] payLoadMapArray = { "loanStatus:CX.BROKERLOANSTATUS", "noteRate:3", "docType:MORNET.X67",
				"loanType:1172", "propertyType:1041", "lastDisclosureDate:LE1.X33", "appraisedValue:356",
				"occupancyType:1811", "amortizationType:608", "loanTerm:4", "fico:VASUMM.X23", "totalLoanAmount:1109" };
		for (int payloadIndex = 0; payloadIndex < payLoadMapArray.length; payloadIndex++) {
			payLoadFieldMap.put(payLoadMapArray[payloadIndex].split(":")[0],
					payLoadMapArray[payloadIndex].split(":")[1]);
		}
		return payLoadFieldMap;
	}
	// -----------------------------------------------------------------------------------------------------------------------------------------------------

	public String fetchLoanGUIDFromEncompass(String cookie, String loanNumber) throws Exception {

		RestTemplate rt1 = new RestTemplate();
		HttpEntity<String> entity1 = new HttpEntity<String>(guiIdPayload.replace("$", loanNumber), getHeader());
		String guiID = "";
		try {
			ResponseEntity<?> re1 = rt1.exchange(fetchGUIDURL, HttpMethod.POST, entity1, String.class);
			if (re1.getStatusCode() != HttpStatus.OK) {
				addExceptionToReport("Fetching GUID From Encompass failed for Loan Number " + loanNumber,
						String.valueOf(re1.getStatusCode()), "200");
			}
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			try {
				MainParser mp = objectMapper.readValue(re1.getBody().toString(), MainParser.class);
				guiID = mp.getLoans().get(0).getLoanGuid();
			} catch (Exception e) {
				addExceptionToReport("Failed to fetch GUID from Encompass for loan number " + e.toString(), "", "");
			}
		} catch (final HttpClientErrorException e) {
			addExceptionToReport("Exception seen during parsing Fetch GUI json " + e.toString(), "", "");
		}
		return guiID;
	}

	public void retrieveLoan(String sessionCookie, String loanGUID, String loanNumber) throws Exception {

		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("GUID", loanGUID);
		RestTemplate loanDetailTemplate = new RestTemplate();
		HttpEntity<String> loanDetailEntity = new HttpEntity<String>(IncomeguiIdPayload, getHeader());
		try {
			ResponseEntity<?> loanDetailResponse = loanDetailTemplate.exchange(loanDetailsURL, HttpMethod.POST,
					loanDetailEntity, String.class, uriVariables);
			if (loanDetailResponse.getStatusCode() != HttpStatus.OK) {
				addExceptionToReport("Fetching loan details from  Encompass failed for Loan Number " + loanNumber,
						String.valueOf(loanDetailResponse.getStatusCode()), "200");
			}
		} catch (final HttpClientErrorException e) {
			addExceptionToReport("Exception during fetching loan details from Encompass. Loan Number is " + loanNumber,
					"", "");
		}
	}

	private void endSession() {
		HttpHeaders endSessionHeader = getHeader();
		RestTemplate rt = new RestTemplate();
		HttpEntity<String> entity = new HttpEntity<String>(endSessionHeader);
		ResponseEntity<?> re = rt.exchange(endSessionUrl, HttpMethod.POST, entity, String.class);
	}

	private HttpHeaders getHeader() {
		HttpHeaders fetchGUIDHeader = new HttpHeaders();
		fetchGUIDHeader.setContentType(MediaType.APPLICATION_JSON);
		fetchGUIDHeader.add("x-api-key", apiKey);
		// fetchGUIDHeader.add("Cookie", cookie);
		return fetchGUIDHeader;
	}

	// private HttpHeaders getHeader(String headerDetails) {
	// HttpHeaders fetchGUIDHeader = new HttpHeaders();
	// fetchGUIDHeader.setContentType(MediaType.APPLICATION_JSON);
	// for(String s:headerDetails.split("&")){
	// fetchGUIDHeader.add(s.split(";")[0], s.split(";")[1]);
	// }
	// return fetchGUIDHeader;
	// }
	public String fetchSessionCookie() throws Exception {
		HttpHeaders beginSessionHeader = new HttpHeaders();
		beginSessionHeader.setContentType(MediaType.APPLICATION_JSON);
		beginSessionHeader.add("x-api-key", apiKey);
		RestTemplate rt = new RestTemplate();
		HttpEntity<String> entity = new HttpEntity<String>(beginSessionHeader);
		HashMap<String, String> headerMap = new HashMap<String, String>();
		try {
			ResponseEntity<?> re = rt.exchange(beginSessionUrl, HttpMethod.POST, entity, String.class);
			HttpHeaders resHeaders = re.getHeaders();
			List<String> resHeaders1 = resHeaders.get("Set-Cookie");

			for (int i = 0; i < resHeaders1.size(); i++) {
				String[] headerValues = resHeaders1.get(i).split(";");
				for (String header : headerValues) {
					headerMap.put(header.split("=")[0], header.split("=")[1]);
				}
			}
		} catch (Exception e) {
			addExceptionToReport("Exception seen while fetching the session cookie " + e.toString(), "", "");
		}

		return StringUtils.join("encompass-wrapper-session-id=", headerMap.get("encompass-wrapper-session-id"),
				";AWSELB=", headerMap.get("AWSELB"));
	}

	public void updateLoanStatus(String loanNumber, String payLoad) throws Exception {
		String cookie = fetchSessionCookie();
		String loanGUID = fetchLoanGUIDFromEncompass(cookie, loanNumber);
		retrieveLoan(cookie, loanGUID, loanNumber);
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("GUID", loanGUID);
		RestTemplate restTemplate = new RestTemplate();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		restTemplate.setRequestFactory(requestFactory);

		HttpEntity<String> entity1 = new HttpEntity<String>(payLoad, getHeader());
		try {
			ResponseEntity<?> re1 = restTemplate.exchange(upDateLoanStatusURL, HttpMethod.PATCH, entity1, String.class,
					uriVariables);
			if (re1.getStatusCode() != HttpStatus.OK) {
				addExceptionToReport("Updating loan status in  Encompass failed for Loan Number " + loanNumber,
						String.valueOf(re1.getStatusCode()), "200");
			}

			if (re1.getBody().toString().trim().equalsIgnoreCase("true") == false) {
				addExceptionToReport("Loan status is not changed to active for the loan number " + loanNumber, "", "");
			}
		} catch (final HttpClientErrorException e) {
			addExceptionToReport("Exception during the crea " + loanNumber, "", "");
		}
		endSession();
	}

	public void changeLoanStatusToFunded(String loanNumber) throws Exception {
		updateLoanStatus(loanNumber, upDateLoanStatusFundedPayLoad);
	}

	public void changeLoanStatusToCancelled(String loanNumber) throws Exception {
		updateLoanStatus(loanNumber, upDateLoanStatusCancelledPayLoad);
	}

	public void changeLoanStatusToDeclined(String loanNumber) throws Exception {
		updateLoanStatus(loanNumber, upDateLoanStatusDeclinedPayLoad);
	}

	public void changeLoanStatusToInternalCreditReview(String loanNumber) throws Exception {
		updateLoanStatus(loanNumber, upDateLoanStatusInitialCreditReviewPayLoad);
	}

	public void changeLoanStatusToConditionalApproval(String loanNumber) throws Exception {
		updateLoanStatus(loanNumber, upDateLoanStatusConditonalApprovalPayLoad);
	}

	public void changeLoanStatusToFinalApproval(String loanNumber) throws Exception {
		updateLoanStatus(loanNumber, upDateLoanStatusFinalApprovalPayLoad);
	}

	public void changeLoanStatusToCDSent(String loanNumber) throws Exception {
		updateLoanStatus(loanNumber, upDateLoanStatusCDSentPayLoad);
	}

	public void changeLoanStatusToReadyForDocs(String loanNumber) throws Exception {
		updateLoanStatus(loanNumber, upDateLoanStatusReadyForDocsPayLoad);
	}

	public void changeLoanStatusToDocsOut(String loanNumber) throws Exception {
		updateLoanStatus(loanNumber, upDateLoanStatusDocsOutPayLoad);
	}

	public ResponseEntity<?> fetchLoanDetailsFromEncompass(String loanNumber, String payload) throws Exception {
		String loanGUID = "{" + util.getLoanGUIDFromDB(loanNumber) + "}";

		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("GUID", loanGUID);
		System.out.println("loanGUID>>>" + loanGUID);

		Loan mp = new Loan();
		// beginSession();
		String sessionCookie = fetchSessionCookie();

		// HttpEntity<String> loanDetailEntity = new
		// HttpEntity<>(payload,getHeader(sessionCookie));
		try {
			URI expanded = new UriTemplate(loanDetailsURL).expand(uriVariables);
			ResponseEntity<?> loanDetailResponse = null;

			try {
				// loanDetailResponse = loanDetailTemplate.exchange(expanded,
				// HttpMethod.POST, loanDetailEntity, String.class);
				System.out.println("loan details repose>>>>" + loanDetailResponse.getStatusCode());
			} catch (HttpServerErrorException e1) {

				System.out.println("Exception 1>>>>" + e1.toString());
				System.out.println("Exception 2>>>>" + e1.getResponseBodyAsString());
			}

			if (loanDetailResponse.getStatusCode() != HttpStatus.OK) {
				/*
				 * addExceptionToReport("Fetching loan details from  Encompass failed for Loan Number "
				 * + loanNumber,
				 * String.valueOf(loanDetailResponse.getStatusCode()), "200");
				 */
			}
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			try {
				System.out.println(" Entered try block");
				mp = objectMapper.readValue(loanDetailResponse.getBody().toString(), Loan.class);
				// System.out.println("jsdafhj"+mp);
				objectMapper.readValue(loanDetailResponse.getBody().toString(), MainParser.class);
			} catch (Exception e) {
				/*
				 * addExceptionToReport(
				 * "Failed to fetch GUID & loan number from Encompass for loan number "
				 * + e.toString(), "", "");
				 */
			}

		} catch (Exception e) {
			System.out.println("Exception>>>>" + e.toString());

			/*
			 * addExceptionToReport("Exception during fetching loan details from Encompass. Loan Number is "
			 * + loanNumber, "", "");
			 */
		}

		return null;
	}

	/**
	 * To Validate uploaded Document in Encompass
	 *
	 * @param loanNumber
	 * @throws Exception
	 */
	public Map<String, Object> checkUploadDocument(String loanNumber) throws Exception {
		Map<String, Object> docMap = new HashMap<String, Object>();
		Map<String, String> uriVariables = new HashMap<>();
		RestTemplate loanDetailTemplate = new RestTemplate();
		String cookie = fetchSessionCookie();
		String loanGUID = fetchLoanGUIDFromEncompass(cookie, loanNumber);
		uriVariables.put("GUID", loanGUID);
		HttpEntity<String> docDetailEntity = new HttpEntity<String>("", getHeader());
		try {
			ResponseEntity<?> loanDetailResponse = loanDetailTemplate.exchange(documentViewURL, HttpMethod.GET,
					docDetailEntity, String.class, uriVariables);
			ObjectMapper objectMapper = new ObjectMapper();
			String responseStr = loanDetailResponse.getBody().toString();
			List<Map<String, Object>> listMap = objectMapper.readValue(responseStr,
					new TypeReference<List<Map<String, Object>>>() {
					});
			return listMap.get(0);
		} catch (final HttpClientErrorException e) {
			addExceptionToReport("Exception during fetching loan details from Encompass. Loan Number is " + loanNumber
					+ " exception " + e.getMessage(), "", "");
		}
		endSession();
		return null;
	}

	private ArrayList<String> getListOfDocuments(List<Map<String, Object>> response) {
		ArrayList<String> documents = new ArrayList<>();
		for (Map<String, Object> documentList : response) {

			for (String document : documentList.keySet()) {
				if (document.contains("documentName")) {
					documents.add((String) documentList.get(document));
				}
			}
		}

		return documents;

	}

	public void checkUploadedDocContents(String loanNumber, Map<String, Object> docMap, String expectedContent)

			throws Exception {

		Map<String, String> uriVariables = new HashMap<>();

		String cookie = fetchSessionCookie();

		String loanGUID = fetchLoanGUIDFromEncompass(cookie, loanNumber);

		RestTemplate loanDetailTemplate = new RestTemplate();

		uriVariables.put("DOCID", docMap.get("documentId").toString());

		uriVariables.put("GUID", loanGUID);

		HttpEntity<String> docDetailEntity = new HttpEntity<String>("", getHeader());

		try {

			ResponseEntity<?> loanDetailResponse = loanDetailTemplate.exchange(docContentURL, HttpMethod.GET,

					docDetailEntity, String.class, uriVariables);

			ObjectMapper objectMapper = new ObjectMapper();

			String responseStr = loanDetailResponse.getBody().toString();

			String test1 = responseStr.replace("\n", ";").replace("\r", "");

			if (test1.substring(0, test1.length() - 1).equalsIgnoreCase(expectedContent)) {

			} else {

				addExceptionToReport("File content didnot match", "", "");

			}

		} catch (final HttpClientErrorException e) {

			addExceptionToReport("Exception during fetching loan details from Encompass. Loan Number is " + loanNumber

					+ " exception " + e.getMessage(), "", "");

		}

		endSession();

	}

	public List<Map<String, Object>> fetchUploadedDocumentsFromEncompass(String loanNumber) throws Exception {

		Map<String, Object> docMap = new HashMap<String, Object>();

		Map<String, String> uriVariables = new HashMap<>();

		RestTemplate loanDetailTemplate = new RestTemplate();

		String cookie = fetchSessionCookie();

		GUID = fetchLoanGUIDFromEncompass(cookie, loanNumber);

		uriVariables.put("GUID", GUID);

		HttpEntity<String> docDetailEntity = new HttpEntity<String>("", getHeader());

		try {

			ResponseEntity<?> loanDetailResponse = loanDetailTemplate.exchange(documentViewURL, HttpMethod.GET,
					docDetailEntity, String.class, uriVariables);

			ObjectMapper objectMapper = new ObjectMapper();

			String responseStr = loanDetailResponse.getBody().toString();

			List<Map<String, Object>> listMap = objectMapper.readValue(responseStr,
					new TypeReference<List<Map<String, Object>>>() {
					});

			return listMap;

		}

		catch (final HttpClientErrorException e) {

			addExceptionToReport("Exception during fetching loan details from Encompass. Loan Number is " + loanNumber
					+ " exception " + e.getMessage(),

					"", "");

		}

		endSession();

		return null;

	}

	/**
	 *
	 *
	 *
	 * @param docId
	 *
	 * @return
	 *
	 * @throws Exception
	 *
	 */

	private String retrieveDocContents(String sessionCookie, String docId) throws Exception {

		// String docContentURL =
		// "http://pennymac-encompass-qa-441336515.us-west-1.elb.amazonaws.com/encompasswrapper/encompass/api/v2/loans/{GUID}/documents?doctype=loandoc&documentId={DOCID}";

		Map<String, String> uriVariables = new HashMap<>();

		System.out.println("guid>>>>" + GUID);

		uriVariables.put("GUID", GUID);

		uriVariables.put("DOCID", docId);

		RestTemplate loanDetailTemplate = new RestTemplate();

		HttpEntity<String> docDetailEntity = new HttpEntity<String>("", getHeader());

		try {

			ResponseEntity<?> loanDetailResponse = loanDetailTemplate.exchange(docContentURL, HttpMethod.GET,

					docDetailEntity, String.class, uriVariables);

			try {

				System.out.println("loan details Response>>>>" + loanDetailResponse.getBody().toString());

			} catch (Exception e) {

				System.out.println("Exception dueing content>>>>" + e.getMessage());

			}

			return loanDetailResponse.getBody().toString();

		} catch (final HttpClientErrorException e) {

			addExceptionToReport("Exception during fetching loan details from Encompass. Loan Number is", "", "");

		}

		return null;

	}

	public HashMap<String, HashMap<String, String>> fetchMulitpleDocUploadDetailsFromEncompass(String loanNumber)
			throws Exception {

		List<Map<String, Object>> listDocs = fetchUploadedDocumentsFromEncompass(loanNumber);

		HashMap<String, HashMap<String, String>> uploadedDocDetailMap = new HashMap<String, HashMap<String, String>>();

		String sessionCookie = fetchSessionCookie();

		for (Map<String, Object> doc : listDocs) {

			HashMap<String, String> docDetails = new HashMap<String, String>();

			String displayedDocName = doc.get("documentName").toString();

			String displayedDocCategory = doc.get("documentCategory").toString();

			String displayedDocumentId = doc.get("documentId").toString();

			System.out.println("displayedDocumentId>>>>>" + displayedDocumentId);

			String displayedDocumentAddedBy = doc.get("documentAddedBy").toString();

			String displayedDocAdded = doc.get("dateAdded").toString();

			String displayedDocContent = retrieveDocContents(sessionCookie, displayedDocumentId);

			docDetails.put("AddedBy", displayedDocumentAddedBy);

			docDetails.put("AddedDate", displayedDocAdded);

			docDetails.put("docContent", displayedDocContent);

			String key = displayedDocCategory + ":" + displayedDocName;

			key = key.split("_")[2].split("\\.")[0];

			uploadedDocDetailMap.put(key, docDetails);

		}

		return uploadedDocDetailMap;

	}

	public static ResponseEntity<?> patchRequest(String url, String payLoad, String guidNumber) throws Exception {
		HttpClient client = HttpClients.createDefault();
		HttpHeaders headers = new HttpHeaders();
		guidNumber = "{" + guidNumber + "}";
		System.out.println("payLoad???" + payLoad);
		System.out.println("url???" + url);
		headers.add("x-api-key", "00785e7d-4082-4a7e-9c99-5c8185c73d8c");
		headers.add("Content-Type", "application/json");
		RestTemplate rt = new RestTemplate();
		rt.setRequestFactory(new HttpComponentsClientHttpRequestFactory(client));
		HttpEntity<String> entity = new HttpEntity<String>(payLoad, headers);
		ResponseEntity<?> re = rt.exchange(url, HttpMethod.PATCH, entity, String.class, guidNumber);
		System.out.println("RESPONSE >>>" + re);
		if (re.getStatusCode() != HttpStatus.OK) {
			addExceptionToReport("Failed to update API", String.valueOf(re.getStatusCode()), "200");
		}

		return re;
	}

	public static ResponseEntity<?> postRequestExternalCondition(String url, String payLoad, String guidNumber)
			throws Exception {

		HttpClient client = HttpClients.createDefault();
		// RestTemplate template= new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		guidNumber = "{" + guidNumber + "}";
		System.out.println("payLoad???" + payLoad);
		System.out.println("url???" + url);
		headers.add("x-api-key", "00785e7d-4082-4a7e-9c99-5c8185c73d8c");
		headers.add("Content-Type", "application/json");
		RestTemplate rt = new RestTemplate();
		rt.setRequestFactory(new HttpComponentsClientHttpRequestFactory(client));
		HttpEntity<String> entity = new HttpEntity<String>(payLoad, headers);
		ResponseEntity<?> re = rt.exchange(url, HttpMethod.POST, entity, String.class, guidNumber);
		System.out.println("RESPONSE >>>" + re);
		if (re.getStatusCode() != HttpStatus.OK) {
			addExceptionToReport("Failed to update API", String.valueOf(re.getStatusCode()), "200");
		}

		return re;
	}

	/**
	 * 
	 */

	public HashMap<String, String> getEncompassResponseBodyDetails(HttpHeaders header, String url, String payload,
			String fields) throws Exception {

		HashMap<String, String> responseMap = new HashMap<String, String>();
		HashMap<String, HashMap<String, String>> encompassMap = util.buildEncompassMapFile();
		Map<String, String> uriVariables = new HashMap<>();
		ResponseEntity<?> loanDetailResponse = null;
		RestTemplate loanDetailTemplate = new RestTemplate();
		String[] expectedFlds = fields.split(";");
		HttpEntity<String> loanDetailEntity = new HttpEntity<>(payload, header);
		URI expanded = new UriTemplate(url).expand(uriVariables);
		try {
			loanDetailResponse = loanDetailTemplate.exchange(expanded, HttpMethod.POST, loanDetailEntity, String.class);
			System.out.println("RESSSPPOONNSEEEE>>>>" + loanDetailResponse);
		} catch (HttpServerErrorException e) {
			addExceptionToReport(
					"Exception seen while fetching response for the url " + url + " and exception is " + e.toString(),
					"", "");
		}
		if (loanDetailResponse.getStatusCode() != HttpStatus.OK) {
			addExceptionToReport("Fetching response for the url " + url,
					String.valueOf(loanDetailResponse.getStatusCode()), "200");
		}
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			System.out.println("Response>>>>" + loanDetailResponse.getBody().toString());
			MainParser mp = objectMapper.readValue(loanDetailResponse.getBody().toString(), MainParser.class);
			for (String s : expectedFlds) {
				if (!encompassMap.containsKey(s)) {
					addExceptionToReport("Expected Field " + s + " is not available in Encompass Mapping file.", "",
							"");
				}

				responseMap.put(s, mp.getAddress().get(encompassMap.get(s).get("Encompass Id")));
			}
		} catch (Exception e) {
			addExceptionToReport("Failed to fetch GUID & loan number from Encompass for loan number " + e.toString(),
					"", "");
		}

		return responseMap;
	}

	private void testPatch(HttpHeaders header, String url, String payload, String loanGUID) throws Exception {
		HttpEntity<String> loanDetailEntity = new HttpEntity<String>(payload, header);
		RestTemplate loanDetailTemplate = new RestTemplate();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setConnectTimeout(10000);
		requestFactory.setReadTimeout(10000);

		loanDetailTemplate.setRequestFactory(requestFactory);
		try {
			ResponseEntity<Void> loanDetailResponse = loanDetailTemplate.exchange(url, HttpMethod.PATCH,
					loanDetailEntity, Void.class);
			if (loanDetailResponse.getStatusCode() != HttpStatus.OK) {
				addExceptionToReport("Fetching loan details from  Encompass failed for Loan Number ",
						String.valueOf(loanDetailResponse.getStatusCode()), "200");
			}
		} catch (final HttpClientErrorException e) {
			addExceptionToReport("Exception during fetching loan details from in the method testPatch. Loan Number is "
					+ e.toString() + "", "", "");
		}
	}

	public static ResponseEntity<?> postRequestNew(String url, String payLoad, String authorisationvalue)
			throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		// headers.add("Authorization", "Basic
		// ZmluZXhwZXJpZW5jZTpGIW4zeHBlcmllbmNl");
		// headers.add("Authorization", "Basic
		// ZmNfeHBlcmllbmNlX2NvbjpGaW5AWHBlcmllbmNlMUE=");
		headers.add("Authorization", authorisationvalue);
		RestTemplate rt = new RestTemplate();
		HttpEntity<String> entity = new HttpEntity<String>(payLoad, headers);
		ResponseEntity<?> re = rt.exchange(url, HttpMethod.POST, entity, String.class);
		if (re.getStatusCode() != HttpStatus.OK) {
			addExceptionToReport("sending request is failed ", String.valueOf(re.getStatusCode()), "200");
		}

		return re;
	}

	public static String fetchAcessTokenEncompass(String url, String authorisationValue) throws Exception {
		ResponseEntity<?> responseEntity = postRequestNew(url, "", authorisationValue);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			ResponseRecord mp = objectMapper.readValue(responseEntity.getBody().toString(), ResponseRecord.class);
			return mp.getAddress().get("access_token");
		} catch (Exception e) {
			addExceptionToReport("Failed to fetch Acess Token from Encompass", e.toString(), "");
		}
		return null;

	}

	public static ResponseEntity<String> getResponseFromAPI(String url, String payLoad, String acessToken)
			throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		System.out.println("Nearer???" + "Bearer " + acessToken);
		headers.add("Authorization", "Bearer " + acessToken);
		RestTemplate rt = new RestTemplate();
		ResponseEntity<String> re = null;
		HttpEntity<String> entity = new HttpEntity<String>(payLoad, headers);
		try {
			re = rt.exchange(url, HttpMethod.POST, entity, String.class);
			if (re.getStatusCode() != HttpStatus.OK) {
				addExceptionToReport("Fetching response From Encompass failed with acess Token" + acessToken,
						String.valueOf(re.getStatusCode()), "200");
			}
		} catch (Exception e) {
			System.out.println("EXCEPTION>>>>" + e.toString());
		}

		return re;
	}

	public static String getCreditPullResponseFromEncompass(String firstName, String lastname, String SSN,
			String street, String zipcode, String state, String city) throws Exception {
		String body = bodyToFetchdataFromEncompassforCreditpull.replace("%SSN", SSN).replace("%firstname", firstName)
				.replace("%lastname", lastname).replace("%street", street).replace("%zip", zipcode)
				.replace("%state", state).replace("%city", city);
		String accessToken = fetchAcessTokenEncompass(acessTokenUrlForCreditpull,
				"Basic ZmNfcGVubnltYWNfY29uOmZjX3Blbm55bWFjX2NvbkAxQQ==");
		System.out.println("accessToken>>>" + accessToken);
		ResponseEntity<String> responseEntity = getResponseFromAPI(fetchcreditpulldetailsUrl, body, accessToken);
		try {
			return responseEntity.getBody().toString();
		} catch (Exception e) {
			addExceptionToReport("Failed to fetch response from api", e.toString(), "");
		}
		return null;
	}

//	public HashMap<String, String> checkCreatLoan() throws Exception {
//		String url="http://internal-cdl-wrapper-stg-encompass-lb-1548058553.us-west-1.elb.amazonaws.com/encompasswrapper/encompass/api/v2/loans/create";
//		String payload = "{\"14\":\"%s\",\"4000\":\"%n\",\"4002\":\"%l\",\"1240\":\"%m\",\"1490\":\"%p\",\"15\":\"%z\",\"19\":\"%r\",\"3968\":\"%e\",\"CX.MAC.LAST4SSNOFPRIMARYBORR\":\"%b\",\"CX.MAC.LAST4SSNOFCOBORR\":\"%cb\",\"4004\":\"%cfn\",\"4006\":\"%cln\",\"1268\":\"%ce\",\"CX.PORTALRLSEMAIL\":\"%qu\",\"1401\":\"%cf\"}";
//		HttpHeaders header = null;
//		header = getHeader("x-api-key;" + apiKey);
//		HashMap<String, String> dataMap = new HashMap<String, String>();
//
//		dataMap = Ten0Three.buildDataMap(KWVariables.getVariables().get("LoanOnboardData"));
//		System.out.println("DataMap>>>>" + dataMap.keySet());
//		String payLoad = payload.replace("%s", dataMap.get("Property State")).replace("%n", dataMap.get("First Name"))
//				.replace("%l", dataMap.get("Last Name")).replace("%m", dataMap.get("Email ID"))
//				.replace("%z", dataMap.get("Subject Property Zip Code")).replace("%r", dataMap.get("Loan Purpose"))
//				.replace("%p", dataMap.get("Mobile Number")).replace("%e", dataMap.get("LO_Email")).replace("%b", dataMap.get("Borrower_SSN"))
//				.replace("%cb",dataMap.get("CoBorrower_SSN")).replace("%cfn", dataMap.get("CoBorrower First Name")).replace("%cln", dataMap.get("CoBorrower Last Name")).replace("%ce",dataMap.get("CoBorrower Email ID")).replace("%qu", dataMap.get("RLS_Email")).replace("%cf", "Conventional 10 Fixed");
//		System.out.println("PAYYLL>>>>>" + payLoad);
//		HashMap<String, String> encompassValMap = getEncompassResponseBodyDetails(header, url, payLoad,
//				"LoanNumber_Retrival;LoanGUID_Retrival");
//		loanNumberOnboard=encompassValMap.get("LoanNumber_Retrival").replace("\"","").replace("\"","");
//		loanGuidOnboard=encompassValMap.get("LoanNumber_Retrival").replace("\"","").replace("\"","");
//		System.out.println("\n LLOANUMBER GENERATED>>>>>" + loanNumberOnboard);
//		System.out.println("\n going inside offline status" + loanNumberOnboard);
//		offlineStatus();
//		return null;
//
//	}
	public void offlineStatus() throws Exception{
		System.out.println("\n CAME Inside>>>");
		String url1 = "";
		switch(KWVariables.getVariables().get("Env")){
		case "qa":
			 url1 = "https://aries.pnmacgears.com/finexpusermgmt/authorize";
			break;
		case "uat":
			 url1 = "https://pnmac-cdp-uat.tavant.com/finexpusermgmt/authorize";
			break;
		case "virgo":
			 url1 = "https://virgo.pnmacgears.com/finexpusermgmt/authorize";
			break;
		case "libra":
			 url1 = "https://libra.pnmacgears.com/finexpusermgmt/authorize";
			break;
		case "scorpio":
			 url1 = "https://scorpio.pnmacgears.com/finexpusermgmt/authorize";
			break;
		case "capricorn":
			 url1 = "https://capricon.pnmacgears.com/finexpusermgmt/authorize";
			break;
		case "aquarius":
			 url1 = "https://aquarius.pnmacgears.com/finexpusermgmt/authorize";
			break;
		case "aries":
			 url1 = "https://aries.pnmacgears.com/finexpusermgmt/authorize";
			break;
		case "pisces":
			 url1 = "https://pisces.pnmacgears.com/finexpusermgmt/authorize";
			break;
		case "gemini":
			 url1 = "https://gemini.pnmacgears.com/finexpusermgmt/authorize";
			break;
		case "leo":
			 url1 = "https://leo.pnmacgears.com/finexpusermgmt/authorize";
			break;
		case "cancer":
			 url1 = "https://cancer.pnmacgears.com/finexpusermgmt/authorize";
			break;
		}
		String payload1 = "{\"userId\" : \"apiuser\",\"secretKey\" : \"Qwerty$4\"}";
		String loanGuid=loanGuidOnboard;
		System.out.println("Inside offlineStatus>>>>"+loanGuid);
		HttpHeaders fetchGUIDHeader = new HttpHeaders();
		fetchGUIDHeader.setContentType(MediaType.APPLICATION_JSON);

		HashMap<String, String> encompassValMap1 = getEncompassResponseBodyDetails(fetchGUIDHeader, url1, payload1,
				"LoanNumber_AccessToken");
	
		System.out.println("Auth token???" + encompassValMap1.get("LoanNumber_AccessToken"));
		String url2 = "";
		switch(KWVariables.getVariables().get("Env")){
		case "qa":
			 url2 = "https://pnmac-cdp-qa.tavant.com/finexpprequal/onboardLoanApplication";
			break;
		case "uat":
			 url2 = "https://pnmac-cdp-uat.tavant.com/finexpprequal/onboardLoanApplication";
			break;
		case "virgo":
			 url2 = "https://virgo.pnmacgears.com/finexpprequal/onboardLoanApplication";
			break;
		case "libra":
			 url2 = "https://libra.pnmacgears.com/finexpprequal/onboardLoanApplication";
			break;
		case "scorpio":
			 url2 = "https://scorpio.pnmacgears.com/finexpprequal/onboardLoanApplication";
			break;
		case "capricorn":
			 url2 = "https://capricon.pnmacgears.com/finexpprequal/onboardLoanApplication";
			break;
		case "aquarius":
			 url2 = "https://aquarius.pnmacgears.com/finexpprequal/onboardLoanApplication";
			break;
		case "aries":
			 url2 = "https://aries.pnmacgears.com/finexpprequal/onboardLoanApplication";
			break;
		case "pisces":
			 url2 = "https://pisces.pnmacgears.com/finexpprequal/onboardLoanApplication";
			break;
		case "gemini":
			 url2 = "https://gemini.pnmacgears.com/finexpprequal/onboardLoanApplication";
			break;
		case "leo":
			 url2 = "https://leo.pnmacgears.com/finexpprequal/onboardLoanApplication";
			break;
		case "cancer":
			 url2 = "https://cancer.pnmacgears.com/finexpprequal/onboardLoanApplication";
			break;
		}
		String payload2 = "{\"loanguid\":"
				+ loanGuid.replace("{", "\"").replace("}", "\"") + "}";
		System.out.println("Payload2>>>" + payload2);
		fetchGUIDHeader.add("authToken", encompassValMap1.get("LoanNumber_AccessToken"));

		HashMap<String, String> encompassValMap2 = getEncompassResponseBodyDetails(fetchGUIDHeader, url2, payload2,
				"LoanCreation_Status");
		System.out.println(encompassValMap2.get("LoanCreation_Status"));
		
		// System.out.println(encompassValMap.get("LoanNumber_Retrival")+" and
		// "+encompassValMap.get("LoanGUID_Retrival"));
	}

	public void beginSession() {
		HttpHeaders beginSessionHeader = new HttpHeaders();
		beginSessionHeader.setContentType(MediaType.APPLICATION_JSON);
		beginSessionHeader.add("x-api-key", apiKey);
		RestTemplate rt = new RestTemplate();
		HttpEntity<String> entity = new HttpEntity<String>(beginSessionHeader);
		ResponseEntity<?> re = rt.exchange(beginSessionUrl, HttpMethod.POST, entity, String.class);
		System.out.println("Begin session>>>>" + re.getStatusCode());
	}

	private HttpHeaders getHeader(String cookie) {
		HttpHeaders fetchGUIDHeader = new HttpHeaders();
		fetchGUIDHeader.setContentType(MediaType.APPLICATION_JSON);
		fetchGUIDHeader.add("x-api-key", apiKey);
		fetchGUIDHeader.add("Cookie", cookie);
		return fetchGUIDHeader;
	}

	public ResponseEntity<?> fetchEncompassData(String loanNumber, String payload) throws Exception {
		String loanDetailsURL = "http://internal-pennymac-encompass-qa-1662691272.us-west-1.elb.amazonaws.com/encompasswrapper/encompass/api/v1/loans/{GUID}/retrieveLoan?getFieldsForAllBorrowerPairs=false&getAllFieldsForBorrowerPair=false&modulename=na";
		System.out.println("Entered fetchEncompassData");
		String guiID = util.getLoanGUIDFromDB(loanNumber);

		// String guiID ="db394c4c-d818-416a-9319-c9a406cfcff3";

		Map<String, String> uriVariables = new HashMap<>();
		ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(
				HttpClients.createDefault());
		RestTemplate loanDetailTemplate = new RestTemplate();
		uriVariables.put("GUID", "{" + guiID + "}");
		System.out.println("GUID>>>>" + guiID);
		ResponseRecord mp = new ResponseRecord();
		beginSession();
		String sessionCookie = fetchSessionCookie();
		HttpEntity<String> loanDetailEntity = new HttpEntity<>(payload, getHeader(sessionCookie));
		URI expanded = new UriTemplate(loanDetailsURL).expand(uriVariables);

		System.out.println("expanded????" + expanded);
		ResponseEntity<?> loanDetailResponse = null;

		loanDetailResponse = loanDetailTemplate.exchange(expanded, HttpMethod.POST, loanDetailEntity, String.class);

		System.out.println("loan details repose>>>>" + loanDetailResponse);

		return loanDetailResponse;

	}

	public static String getCreditPullResponseFromEncompass_Joint(String firstName, String lastname, String SSN,
			String street, String zipcode, String state, String city, String co_firstName, String co_lastname,
			String co_SSN, String co_street, String co_zipcode, String co_state, String co_city) throws Exception {
		System.out.println("getCreditPullResponseFromEncompass_Joint>>>>");

		System.out.println("firstName??" + firstName + " lastname??" + lastname + " SSN??" + SSN + " street??" + street
				+ " zipcode??" + zipcode + " state??" + state + " city??" + city);
		System.out.println(
				"cofirstName??" + co_firstName + " co_lastname??" + co_lastname + " co_SSN??" + co_SSN + " co_street??"
						+ co_street + " zipcode??" + co_zipcode + " co_state??" + co_state + " co_city??" + co_city);
		String body = bodyFormatForJointCreditPull.replace("%SSN", SSN).replace("%firstname", firstName)
				.replace("%lastname", lastname).replace("%street", street).replace("%zip", zipcode)
				.replace("%state", state).replace("%city", city);

		body = body.replace("%co_SSN", co_SSN).replace("%co_firstname", co_firstName)
				.replace("%co_lastname", co_lastname).replace("%co_street", co_street).replace("%co_zip", co_zipcode)
				.replace("%co_state", co_state).replace("%co_city", co_city);

		// System.out.println("body>>>\n"+body);
		String accessToken = fetchAcessTokenEncompass(acessTokenUrlForCreditpull,
				"Basic ZmNfcGVubnltYWNfY29uOmZjX3Blbm55bWFjX2NvbkAxQQ==");
		System.out.println("accessToken>>>" + accessToken);
		ResponseEntity<String> responseEntity = getResponseFromAPI(fetchcreditpulldetailsUrl, body, accessToken);
		try {
			return responseEntity.getBody().toString();
		} catch (Exception e) {

			e.printStackTrace();
			addExceptionToReport("Failed to fetch response from api", e.toString(), "");
		}
		return null;
	}

	public HashMap<String, String> loanLock() throws Exception {
		System.out.println("Inside loanLock>>>>>");
		String url1 = "http://pnmac-cdp-qa.tavant.com/finexpusermgmt/authorize";
		String payload1 = "{\"userId\" : \"apiuser\",\"secretKey\" : \"Qwerty$4\"}";
		WebDriver driver = DriverFactory.getDriver();
		Dashboard dashboard = new Dashboard();
		String loanNumber=dashboard.naviagteToDashBoardAndFetchLoanNumber();
//		String loanNumber = "7202273575";
		System.out.println("LOAN NUMBER IN LOAN LOCK" + loanNumber);
//		System.out.println("LOAN SIZE>>>>"+driver.findElement(By.xpath("(//div[contains(@class,'loan-status-card-details')])")).getSize());
		String loanGUID = util.getLoanGUIDFromDB(loanNumber);
		HttpHeaders fetchGUIDHeader = new HttpHeaders();
		HttpHeaders fetchGUIDHeader1 = new HttpHeaders();
		fetchGUIDHeader.setContentType(MediaType.TEXT_PLAIN);

		HashMap<String, String> encompassValMap1 = getEncompassResponseBodyDetails(fetchGUIDHeader, url1, payload1,
				"LoanNumber_AccessToken");
		String url2 = "http://pnmac-cdp-qa.tavant.com/finexpprequal/loanapplication/lock";
		String payload2 = "{\"loanguid\":\"" + loanGUID + "\"}";
		fetchGUIDHeader1.add("authToken", encompassValMap1.get("LoanNumber_AccessToken"));

		isLoanLock = true;
		if (isLoanLock == true) {
			testPatch(fetchGUIDHeader1, url2, payload2, loanGUID);
		}
		dashboard.validateAfterLoanlock(loanNumber);
		return null;
	}
	public void validateConfigFlagCobrrowerInvite() throws Exception {
		String GUIDValue = null;
		String Db_url="";
		int Db_IPAddress = 0;
//		 loanNumber="7202411393";
//		LoginIntoApplication.envType="qa";
		System.out.println("Inside validateConfigFlagCobrrowerInvite>>>>");
		switch(LoginIntoApplication.envType.toLowerCase()){
		case "qa":
		Db_url = KWVariables.getVariables().get("ConfigFlag_DBUrl_QA");
		Db_IPAddress=Integer.valueOf(KWVariables.getVariables().get("ipAddress_QA"));
		break;
		case "uat":
		Db_url = KWVariables.getVariables().get("ConfigFlag_DBUrl_UAT");
		Db_IPAddress=Integer.valueOf(KWVariables.getVariables().get("ipAddress_UAT"));;
		break;	
		}
		MongoClient mongoClient = new MongoClient(Db_url, Db_IPAddress);
        String Db2="config";

        System.out.println("Inside getLeadId>>>");
        MongoCredential credential2 = MongoCredential.createCredential("configuser", "config", "configpwd".toCharArray());
        MongoClient  mongoClient2 = new MongoClient(new ServerAddress(Db_url, Db_IPAddress), Arrays.asList(credential2));
        
        @SuppressWarnings("deprecation")
        DB test2 = mongoClient2.getDB(Db2);
        DBCollection dbc_quoteinfo = test2.getCollection("keystore");
        BasicDBObject query = new BasicDBObject();
		query.put("key", "SEND_EMAIL_ON_SUBMIT");
		System.out.println("Query---------->>>>>>>>>");
		DBCursor dbcrsr = dbc_quoteinfo.find(query);
		BasicDBObject record = (BasicDBObject) dbcrsr.next();
		System.out.println("Value in datatbase>>>>" + record.get("value"));
		if (record.get("value").equals("false")) {
			GUIDValue = record.get("value").toString();
			addExceptionToReport("Exception seen while fetching configFlag for the Co-Borrower Invite Email " + ""
					+ " and exception is ", "", "");
		}
			mongoClient.close();
			
	}
	
	
	public HashMap<String, String> checkCreatLoanforOffline(String user) throws Exception {
		String payload,payLoad="";
		String url="http://internal-cdl-wrapper-stg-encompass-lb-1548058553.us-west-1.elb.amazonaws.com/encompasswrapper/encompass/api/v2/loans/create";
		HashMap<String, String> dataMap = new HashMap<String, String>();
		dataMap = Ten0Three.buildDataMap(Ten0Three.offlinedata);
		HttpHeaders header = null;
		if(KWVariables.getVariables().get("Env").equalsIgnoreCase("uat")||KWVariables.getVariables().get("Env").equalsIgnoreCase("pisces")){
			apiKey=KWVariables.getVariables().get("xapikey_UAT");
		}
		else
			apiKey=KWVariables.getVariables().get("xapikey_QA");
		
		header = getHeader("x-api-key;" + apiKey);
		if(user.equalsIgnoreCase("Borrower")){
			System.out.println("checkCreatLoanforBorrower under if>>>>");
			System.out.println("DataMap>>>>" + dataMap.keySet());
			payload = "{\"14\":\"%s\",\"4000\":\"%n\",\"4002\":\"%l\",\"1240\":\"%m\",\"1490\":\"%p\",\"15\":\"%z\",\"19\":\"%r\",\"3968\":\"%e\",\"CX.MAC.LAST4SSNOFPRIMARYBORR\":\"%b\",\"CX.PORTALRLSEMAIL\":\"%qu\",\"1401\":\"%cf\",\"1109\":\"%xyz\"}";
			payLoad = payload.replace("%s", dataMap.get("Property State")).replace("%n", dataMap.get("First Name"))
					.replace("%l", dataMap.get("Last Name")).replace("%m", dataMap.get("Email ID")).replace("%p", dataMap.get("Mobile Number"))
					.replace("%z", dataMap.get("Subject Property Zip Code")).replace("%r", dataMap.get("Loan Purpose"))
					.replace("%e", dataMap.get("LO_Email")).replace("%b", dataMap.get("Borrower_SSN"))
					.replace("%qu", dataMap.get("RLS_Email")).replace("%cf", "Conventional 10 Fixed").replace("%xyz", dataMap.get("Loan Amount"));
			System.out.println("PAYYLL>>>>>" + payLoad);
		}
		else{
			System.out.println("checkCreatLoanfor Co-Borrower under if>>>>");
			payload = "{\"14\":\"%s\",\"4000\":\"%n\",\"4002\":\"%l\",\"1240\":\"%m\",\"1490\":\"%p\",\"15\":\"%z\",\"19\":\"%r\",\"3968\":\"%e\",\"CX.MAC.LAST4SSNOFPRIMARYBORR\":\"%b\",\"CX.MAC.LAST4SSNOFCOBORR\":\"%cb\",\"4004\":\"%cfn\",\"4006\":\"%cln\",\"1268\":\"%ce\",\"CX.PORTALRLSEMAIL\":\"%qu\",\"1401\":\"%cf\",\"1109\":\"%xyz\"}";
			payLoad = payload.replace("%s", dataMap.get("Property State")).replace("%n", dataMap.get("First Name"))
					.replace("%l", dataMap.get("Last Name")).replace("%m", dataMap.get("Email ID"))
					.replace("%z", dataMap.get("Subject Property Zip Code")).replace("%r", dataMap.get("Loan Purpose"))
					.replace("%p", dataMap.get("Mobile Number")).replace("%e", dataMap.get("LO_Email")).replace("%b", dataMap.get("Borrower_SSN"))
					.replace("%cb",dataMap.get("CoBorrower_SSN")).replace("%cfn", dataMap.get("CoBorrower First Name")).replace("%cln", dataMap.get("CoBorrower Last Name")).replace("%ce",dataMap.get("CoBorrower Email ID")).replace("%qu", dataMap.get("RLS_Email")).replace("%cf", "Conventional 10 Fixed").replace("%xyz", dataMap.get("Loan Amount"));
			System.out.println("PAYYLL COBORRPWER>>>>>" + payLoad);
		}
		
		
		
		HashMap<String, String> encompassValMap = getEncompassResponseBodyDetails(header, url, payLoad,
				"LoanNumber_Retrival;LoanGUID_Retrival");
//		String url1 = "https://pnmac-cdp-qa.tavant.com/finexpusermgmt/authorize";
//		String payload1 = "{\"userId\" : \"apiuser\",\"secretKey\" : \"Qwerty$4\"}";
		loanNumberOnboard=encompassValMap.get("LoanNumber_Retrival").replace("\"","").replace("\"","");
		loanGuidOnboard=encompassValMap.get("LoanGUID_Retrival").replace("\"","").replace("\"","");
		System.out.println("\n LLOANUMBER GENERATED>>>>>" + loanNumberOnboard);
		System.out.println("\n going inside offline status" + loanGuidOnboard);
		offlineStatus();
		return null;

	}

	public void validateESignDisclosure() throws Exception{
		WebDriver driver = DriverFactory.getDriver();
		driver.findElement(By.xpath("//h3[contains(.,'E-Sign / E-Disclosure Requests')]")).click();
		TimeUnit.MINUTES.sleep(1);
		
		System.out.println("validateESignDisclosure>>>>>");
		
//		Util.waitForPageToLoad("60000");
		driver.switchTo().frame("guest-WIDGET_HOST-sandbox-1");
		driver.findElement(By.xpath("(//div[@class='main-content']//footer/button[contains(.,'complete tasks')])[1]")).click();
		System.out.println("Clicked on the name");
		Util.waitTimeForSpinner(driver);
//		driver.switchTo().frame("guest-WIDGET_HOST-sandbox-1");
		driver.findElement(By.xpath("//a[contains(text(),'Review Electronic Consent')]")).click();
		util.scrollToElement(driver.findElement(By.xpath("//button[text()='Agree']")));
		util.click("//button[text()='Agree']");
		System.out.println("Clicked>>");
	}
	
	
	@Override
	public void checkPage() {
		// TODO Auto-generated method stub

	}

}

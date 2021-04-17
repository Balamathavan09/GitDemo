package com.finx.util;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.impl.client.HttpClients;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.tavant.base.DriverFactory;
import com.tavant.kwutils.CustomStep;
import com.tavant.kwutils.KWVariables;


/**
 * 
 * @author sweata.shaw
 *
 */
public class EncompassValidation extends CustomStep {
	String apiKey = "";
	String beginSessionUrl = "";
	String loanDetailsURL = "";
	String getStartedPayLoad = "[\"cX.SOURCEOFLOAN\",\"CX.TCPAFlAGforPrimaryBORR\",\"CX.TCPAPhnForPri\",\"4000\",\"4002\",\"4001\",\"4003\",\"66\",\"955\",\"FR0112\",\"954\",\"1402\",\"1490\",\"934\",\"19\",\"156\",\"VAVOB.X72\",\"FR0115\",\"FR0104\",\"FR0108\",\"FR0107\",\"FR0106\",\"52\",\"1240\",\"FR0124\",\"FR0315\",\"FR0307\",\"FR0306\",\"65\",\"1416\",\"fR0108\",\"54\"]";//\"1822\",
	
	/**
	 * 
	 * @throws Exception
	 */
	private void beginSession() throws Exception {
		HttpHeaders beginSessionHeader = new HttpHeaders();
//		System.out.println("begin Session entered>>>>");
		beginSessionHeader.setContentType(MediaType.APPLICATION_JSON);
//		LoginIntoApplication.envType="qa";
		switch(KWVariables.getVariables().get("Env").toLowerCase()){
		case "qa":
		case "taurus":
		case "virgo":
        case "libra":
        case "scorpio":
        case "capricorn":
        case "aquarius":
        case "gemini":
        case "leo":
        case "aries":
        case "cancer":{
			beginSessionUrl=KWVariables.getVariables().get("beginSesssionURL_QA");
			apiKey=KWVariables.getVariables().get("xapikey_QA");
			loanDetailsURL=KWVariables.getVariables().get("loanDetailsURL_QA");  
			System.out.println("Inside qa>>"+beginSessionUrl+" "+apiKey+" "+loanDetailsURL);
		}
		break;
		case "uat":
		case "pisces":{
			beginSessionUrl=KWVariables.getVariables().get("beginSesssionURL_UAT");
			apiKey=KWVariables.getVariables().get("xapikey_UAT");
			loanDetailsURL=KWVariables.getVariables().get("loanDetailsURL_UAT");
			System.out.println("\n Inside Pisces>>"+beginSessionUrl+" "+apiKey+" "+loanDetailsURL);
		}
		break;
	}
		beginSessionHeader.add("x-api-key", apiKey);
		RestTemplate rt = new RestTemplate();
		HttpEntity<String> entity = new HttpEntity<String>(beginSessionHeader);
		try {
			rt.exchange(beginSessionUrl, HttpMethod.POST, entity, String.class);
		} catch (Exception e) {
			addExceptionToReport("Exception seen while starting the session and exception is " + e.toString(), "", "");
		}

	}

	/**
	 * 
	 * @return
	 */
	public String fetchSessionCookie() throws Exception {
		HttpHeaders beginSessionHeader = new HttpHeaders();
		beginSessionHeader.setContentType(MediaType.APPLICATION_JSON);
		switch(KWVariables.getVariables().get("Env").toLowerCase()){
		case "qa":
		case "taurus":
		case "virgo":
        case "libra":
        case "scorpio":
        case "capricorn":
        case "aquarius":
        case "aries":{
			beginSessionUrl=KWVariables.getVariables().get("beginSesssionURL_QA");
			apiKey=KWVariables.getVariables().get("xapikey_QA");
			loanDetailsURL=KWVariables.getVariables().get("loanDetailsURL_QA");  
			System.out.println("Inside qa>>"+beginSessionUrl+" "+apiKey+" "+loanDetailsURL);
		}
		break;
		case "uat":
		case "pisces":{
			beginSessionUrl=KWVariables.getVariables().get("beginSesssionURL_UAT");
			apiKey=KWVariables.getVariables().get("xapikey_UAT");
			loanDetailsURL=KWVariables.getVariables().get("loanDetailsURL_UAT"); 
		}
		break;
	}
		beginSessionHeader.add("x-api-key", apiKey);
//		System.out.println("under fetchSessionCookie>>>"+apiKey+"beginSessionHeader>>"+beginSessionHeader);
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

	/**
	 * 
	 * @param cookie
	 * @return
	 */
	private HttpHeaders getHeader(String cookie) {
		HttpHeaders fetchGUIDHeader = new HttpHeaders();
		fetchGUIDHeader.setContentType(MediaType.APPLICATION_JSON);
		fetchGUIDHeader.add("x-api-key", apiKey);
		fetchGUIDHeader.add("Cookie", cookie);
		return fetchGUIDHeader;
	}

	/**
	 * 
	 */
	/**
	 * 
	 * @param loanNumber
	 * @param guiID
	 * @param payload
	 * @return
	 * @throws Exception
	 */
	public ResponseEntity<?> fetchEncompassData(String loanNumber,String payload) throws Exception {
		Util util = new Util();
		Map<String, String> uriVariables = new HashMap<>();
		ResponseEntity<?> loanDetailResponse = null;		
		RestTemplate loanDetailTemplate = new RestTemplate();
		String revGUID="{"+util.getLoanGUIDFromDB(loanNumber)+"}";
		uriVariables.put("GUID",revGUID);
		Loan mp = new Loan();
		beginSession();
		
		String sessionCookie = fetchSessionCookie();
//		System.out.println("PAYLOAD>>"+payload);
		HttpEntity<String> loanDetailEntity = new HttpEntity<>(payload, getHeader(sessionCookie));
		URI expanded = new UriTemplate(loanDetailsURL).expand(uriVariables);
		System.out.println("EXPANDED>>"+expanded);
		try {
			loanDetailResponse = loanDetailTemplate.exchange(expanded, HttpMethod.POST, loanDetailEntity, String.class);
			
		} catch (HttpServerErrorException e) {
			addExceptionToReport("Exception seen while fetching the loan details response " + e.toString(), "", "");
		}

		if (loanDetailResponse.getStatusCode() != HttpStatus.OK) {
			addExceptionToReport("Fetching loan details from  Encompass failed for Loan Number " + loanNumber,
					String.valueOf(loanDetailResponse.getStatusCode()), "200");
		}
		ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
       try {
              System.out.println(" Entered try block");
              System.out.println("Body>>>>"+loanDetailResponse.getBody().toString());
              Loan ln = objectMapper.readValue(loanDetailResponse.getBody().toString(), Loan.class);
              
       } catch (Exception e) {
              addExceptionToReport(
                            "Failed to fetch GUID & loan number from Encompass for loan number " + e.toString(), "", "");
       }
		return loanDetailResponse;
	}

	/**
	 * 
	 */

	
	@Override
	public void checkPage() {
		// TODO Auto-generated method stub

	}

}

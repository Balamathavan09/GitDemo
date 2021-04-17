package com.finx.util;

import java.awt.Robot;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import java.util.Set;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.SystemOutLogger;
import org.apache.xerces.dom.NodeImpl;
//import org.jboss.netty.buffer.ChannelBuffer;
//import org.jboss.netty.handler.codec.embedder.DecoderEmbedder;
//import org.jboss.netty.handler.codec.http.HttpContentDecoder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

//import com.companyname.projectname.report.ReportJsonUtil;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.seleniumhq.jetty9.http.HttpContent;
import org.springframework.http.ResponseEntity;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import jxl.read.biff.BiffException;
//import mx4j.tools.remote.http.HTTPConnection;

import com.google.common.base.Function;

import com.tavant.base.DriverFactory;

import com.tavant.kwutils.CustomStep;

import com.tavant.utils.TwfException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class JsonUtil extends CustomStep  implements NodeList{
 
	static WebDriver driver;

	String filepath = System.getProperty("user.dir") + "\\src\\test\\resources\\3.2File\\";
	String colClick = "//th[contains(.,'%s')]";
	String recordOnPage = "//div[@class='ui-datatable-tablewrapper']/table/tbody/tr";
	// String guiIDurl = "http://10.131.148.188/api/pipeline";

	// String guiIDurl = "http://10.131.148.188/encompass/api/v1/pipeline";
	String guiIDurl = "https://sandbox-finconnect.tavant.com/fchub/oauth/token?grant_type=client_credentials";
	// String guiIdPayload = "[{\"FilterCriteria\": {\"FieldId\":
	// \"Loan.LoanNumber\",\"FieldValue\": \"$\", \"FieldMatchType\":
	// \"contains\",\"FieldDataType\": \"string\"},\"RequiredFields\": [
	// \"Loan.Guid\"]}]";
	String guiIdPayload = "{}";

	String guiIdPayload1 = "[{\"FilterCriteria\": {\"FieldId\": \"Loan.LoanNumber\",\"FieldValue\": \"$\", \"FieldMatchType\": \"contains\",\"FieldDataType\": \"string\"},\"RequiredFields\": [\"CX.BROKERLOANSTATUS\", \"11\",\"12\",\"13\",\"14\",\"15\",\"1109\",\"4000\",\"4002\",\"749\",\"136\",\"356\",\"3\",\"608\",\"1172\",\"4\",\"995\"]}]";

	// String upDateLoanStatusURL
	// ="http://10.131.148.188/api/loan/$/updateLoanDetails";
	String upDateLoanStatusURL = "http://10.131.148.188/encompass/api/v1/loan/$/updateLoanDetails";

	String pipeLineURL = "http://10.131.148.188/api/pipeline";
	String upDateLoanStatusPayLoad = "{\"CX.BROKERLOANSTATUS\":\"Loan Created\", \"3142\":\"6/30/2017\",\"762\":\"07/21/2017\",\"3\": \"4.001\",\"4\":\"96\",\"353\":\"2.322\",\"976\":\"8.270\",\"1540\":\"10.111\",\"VASUMM.X23\":\"850\",\"MORNET.X67\":\"Full Documentation\",\"3142\":\"07/17/2017\",\"1041\":\"Attached\",\"LE1.X33\":\"07/21/2017\",\"356\":\"$26,087\",\"1811\":\"PrimaryResidence\",\"2301\":\"07/17/2017\",\"353\":\"13.544\",\"2293\":\"Waived\",\"608\":\"Fixed\",\"1109\":1110001.11,\"1612\":\"John Smith\",\"4000\":\"John\",\"4002\":\"Smith\",\"748\":\"09/03/2017\",\"TPO.X61\":\"Tavant Dev\",\"TPO.X74\":\"Tavant QA\",\"1172\":\"VA\"}";
	String tableHeaderObject = "//div[@class='ui-datatable-tablewrapper']/table/thead/tr/th";
	String columnObject = "//div[@class='ui-datatable-tablewrapper']/table/thead/tr/th[%s]/span[@class='ui-column-title']";
	String tableBodyObject = "//div[@class='ui-datatable-tablewrapper']/table/tbody/tr";
	
	
	
	
	//Nikhil
	
	/*protected NodeImpl rootNode; // Where the search started
    protected String tagName;   // Or "*" to mean all-tags-acceptable
    protected Vector nodes;
	
    *//** Constructor. *//*
    public JsonUtil(NodeImpl rootNode, String tagName) {
           this.rootNode = rootNode;
           this.tagName  = tagName;
           nodes = new Vector(); 
    }*/ 
	
	
	
	
	
	

	/**
	 * Add Failure Reason to Report
	 * 
	 * @param description
	 * @param actualValue
	 * @param expectedValue
	 * @return
	 * @throws TwfException
	 */
	public static void addExceptionToReport(String description, String actualValue, String expectedValue)
			throws TwfException {
		throw new TwfException(description + " :<font color=\"solid orange\">  Actual :[" + actualValue
				+ "]</font><font color=\"EE7600\"> Expected :[" + expectedValue + "]</font><br> <b>Step Details:</b> "
				+ "<br>");
	}


	


	public static WebElement waitForSpineer(final WebElement locator) throws Exception {
		WebDriver driver = DriverFactory.getDriver();
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(180, TimeUnit.SECONDS)
				.pollingEvery(250, TimeUnit.MILLISECONDS).ignoring(Exception.class);
		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return locator;
			}
		});

		return element;
	};

	

	
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

	/**
	 * wait for page loading
	 * 
	 */
	public static void beforePageLoad() throws TwfException, BiffException, IOException {
		WebDriver driver = DriverFactory.getDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.mpPageReloaded='notYet';");

	}

	
	


	public void postRequest(String url, String payLoad) throws Exception {
		//getAuthenticationToken();
		/*HttpHeaders headers = new HttpHeaders();
	
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setConnection(connection);
		
		
		RestTemplate rt = new RestTemplate();
		//HttpEntity<String> entity1 = new HttpEntity<String>(url);
		HttpEntity<String> entity = new HttpEntity<String>(payLoad, headers);		
		ResponseEntity<?> re = rt.exchange(url, HttpMethod.POST, entity, String.class);

		System.out.println("re " + re);*/
		
		
	/*	HttpURLConnection hc;
	
	        String authorization = "finexperience:F!n3xperience";
	        URL address = new URL(url);
	        hc = (HttpURLConnection) address.openConnection();


	        hc.setDoOutput(true);
	        hc.setDoInput(true);
	        hc.setUseCaches(false);

	

	        if (authorization != null) {
	            byte[] encodedBytes;
	            encodedBytes =  Base64.encode(authorization);
	           // encodedBytes = Base64.encode(authorization.getBytes());
	            authorization = "Basic " + encodedBytes;
	            System.out.println("authorization>>>"+authorization);
	            hc.setRequestProperty("Authorization", authorization);
	        }
	        System.out.println(hc.getResponseMessage());
	       System.out.println("respnse code>>>>"+hc.getResponseCode());*/

	}
	
	
	private static String getAuthenticationToken(String url,String authtoken) throws Exception {
        {
              
                    //String url = "";
                    URL obj = new URL(url);
                    	
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    con.setRequestMethod("POST");
                    con.setRequestProperty("Accept", "application/json");
                    con.setDoOutput(true);
                    
                    String authorization = null;
                    String userName = "finexperience";
                    String password = "F!n3xperience";
                    if(authtoken.length() == 0){
                    	authorization = userName + ":" + password;
                        String encoding = Base64.getEncoder().encodeToString(authorization.getBytes("utf-8"));
                        con.setRequestProperty("Authorization", "Basic " + encoding);
                    }else{
                    	authorization = "Bearer "+authtoken;
                    	con.setRequestProperty("Authorization", authorization);
                    }
                     
                    String authCode = "";
               
                    int responseCode = con.getResponseCode();
                    System.out.println("Response Code : " + responseCode);
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    
                    while ((inputLine = in.readLine()) != null) {
                          response.append(inputLine);
                    }
                    
                    ObjectMapper objectMapper = new ObjectMapper();
                     objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                           try {
                                  
                           
                                  MainParser mp = objectMapper.readValue(response.toString(), MainParser.class);
                                  
                                  objectMapper.readValue(response.toString(), MainParser.class);
                           
                                authCode = mp.getAccess_token();
                                System.out.println("auth code>>>"+authCode);
                           }
                           catch(Exception e) {
                                 System.out.println("Exception >>>"+e); 
                           }

                    
                    in.close();
                    System.out.println(response.toString());
                    
                    return authCode;
        }
  }

	public void testPost(String url,String authCode) throws Exception{
		
		   URL obj = new URL(url);
       	
           HttpURLConnection con = (HttpURLConnection) obj.openConnection();
           con.setRequestMethod("POST");
           con.setRequestProperty("Accept", "application/json");
           con.setDoOutput(true);
           String authorization = null;
		
           authorization = "Bearer "+authCode;
       	   con.setRequestProperty("Authorization", authorization);
       	  
       	 int responseCode = con.getResponseCode();
         System.out.println("Response Code : " + responseCode);
	}

	
	// 
	
    public ResponseEntity<?> postRequest(String url,String authToken,String payLoad) throws Exception{
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if(authToken.length() == 0) {
               authToken = generateHeaderAuthorizationToken("fc_xperience_con","Fin@Xperience1A");
               headers.add("Authorization", authToken); 
        }else{
        	 headers.add("Authorization","Bearer "+ authToken); 
        }
       
        RestTemplate rt = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<String>(payLoad, headers);         
        ResponseEntity<?> re = rt.exchange(url, HttpMethod.POST, entity, String.class);
        return re;
        
  } 
  
  public String generateHeaderAuthorizationToken(String userName,String password) throws Exception{
        String authorization = userName + ":" + password;
   String authCode = "";
   String encoding = Base64.getEncoder().encodeToString(authorization.getBytes("utf-8"));
   return "Basic "+encoding;
  }
  
  public String getAuthenticationToken() throws Exception{
	  
	  System.out.println(" inside getAuthenticationToken ");
      //    ResponseEntity<?> re = postRequest("https://sandbox-finconnect.tavant.com/fchub/oauth/token?grant_type=client_credentials","","");
	     ResponseEntity<?> re = postRequest("https://stg-finconnect.tavant.com/fchub/oauth/token?grant_type=client_credentials","","");
	   
        ObjectMapper objectMapper = new ObjectMapper(); 
        String authCode  = null;
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
        	
               MainParser mp = objectMapper.readValue(re.getBody().toString(), MainParser.class);
               objectMapper.readValue(re.getBody().toString(), MainParser.class);
               return mp.getAccess_token();
                

        } catch (Exception e) {
               System.out.println(" check- 7");
               System.out.println("test case got failed ");
        }
       return  authCode;
  }
   
/*  public HashMap<Integer, HashMap<String, HashMap<String, String>>>  testPDF(String authtoken) throws Exception{
	  String payLoad = "{\"operationType\": \"Submit\",\"request\": {\"reportType\": \"Other\",\"lenderCaseIdentifier\": \"%s\",\"borrowerId\": \"B12346\",\"requestType\": \"Individual\",\"reportTypeOtherDescription\": \"VOI\",\"loanApplication\": {\"borrower\": [{ \"borrowerPrintPositionType\": \"BORROWER\", \"borrowerSSN\": \"799005103\",\"borrowerLastname\": \"DELINQUENT\",\"borrowerFirstname\": \"DAVID\",\"borrowerId\": \"B12346\" }] }, \"reportRequestActionType\": \"Submit\" },\"credentials\": { \"userName\": \"999TT72916\", \"password\": \"00CizkrrkRYuY\" },\"mismoversionID\": \"2.3.1\"}";
	  ResponseEntity<?> re = postRequest("https://stg-finconnect.tavant.com/fchub/rest/verify/income/submit",authtoken,payLoad.replace("%s", authtoken));
	  HashMap<Integer,HashMap<String,HashMap<String,String>>> rootMap = new HashMap<Integer,HashMap<String,HashMap<String,String>>>();
	  ObjectMapper objectMapper = new ObjectMapper();       
      objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      try {
    	//  System.out.println(" re.getBody().toString()  --- "  +  re.getBody().toString());
            // MainParser mp = objectMapper.readValue(re.getBody().toString(), MainParser.class);
             //System.out.println("rESPONSE>>>>"+re.getBody().toString());
            // objectMapper.readValue(re.getBody().toString(), MainParser.class);
             //System.out.println("xml>>>>"+mp.getTransactionDetails().size());
          //   System.out.println("xml1>>>>"+mp.getTransactionDetails().get(0).getProductCode());
    	  
    	  MainParser mp = objectMapper.readValue(re.getBody().toString(), MainParser.class);
    	  System.out.println("rESPONSE>>>>"+re.getBody().toString());
    	  System.out.println("is empty>>>>"+mp.getTransactionDetails().getCorrelationId());
    	   
    	  ResponseDTO td = objectMapper.readValue(re.getBody().toString(), ResponseDTO.class);    	
    	  System.out.println("Borrower ID>>>>"+td.getResponse()[0].getResponsedata()[0].getVoiresponse().getBorrower()[0].getBorrowerID());
    	  System.out.println("Borrower first name>>>>"+td.getResponse()[0].getResponsedata()[0].getVoiresponse().getBorrower()[0].getFirstName());
    	  System.out.println("responding last name>>>>"+td.getResponse()[0].getResponsedata()[0].getVoiresponse().getBorrower()[0].getLastName());
    	  System.out.println("ssn>>>>"+td.getResponse()[0].getResponsedata()[0].getVoiresponse().getBorrower()[0].getSsn());
    	//  System.out.println("document>>>>     "+td.getResponse()[0].getResponsedata()[0].getVoiresponse().getEmbeddedfile()[1].getDocument().getContent()[0].toString());
    	  //System.out.println("Product Id>>>>"+td.getTransactionDetailExtn().getProductCode());
    	  String document = td.getResponse()[0].getResponsedata()[0].getVoiresponse().getEmbeddedfile()[1].getDocument().getContent()[0].toString();
    	  
    	  rootMap =  buildXMLMaplatest(document);
    	  
    	 
    	  //Nikhil Patni 
    	  
    	   
    	  
    	   
      }
      catch (Exception e) {  
	    	System.out.println("SAXException " +  e); 
	    }  
		  
          
    	  String document = td.getResponse()[0].getResponsedata()[0].getVoiresponse().getEmbeddedfile()[1].getDocument().getContent()[0].toString();   	 
    	    	   
    		// For xml FILE INPUT
    	 * DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();		
        	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    		Document doc = (Document) dBuilder.parse(document);
    	  
    	  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    	  DocumentBuilder db = null;    		
    	  System.out.println( "test3");
    	      	   
   	  db = dbf.newDocumentBuilder();
	InputSource is = new InputSource();
	is.setCharacterStream(new StringReader(document));
	try {
		org.w3c.dom.Document doc1 = db.parse(is);
		String xmlContentvalues = doc1.getDocumentElement().getTextContent();
		// System.out.println("xmlContentvalues is " +
		// xmlContentvalues);

		System.out.println("Root element : -->	" + doc1.getDocumentElement().getNodeName()); System.out.println("MESSAGE element MESSAGE 1 : -->	"
				+ (doc1.getElementsByTagName("MESSAGE").item(0)).getTextContent());
		System.out.println("MESSAGE element2 : -->	" + (doc1.getNodeName()));
		System.out.println("MESSAGE element MESSAGE 2 : -->	"	+ (doc1.getElementsByTagName("MESSAGE").item(1)).getTextContent());

		
		childElementOfNodes(doc1,"TSVEMPLOYER_V100");					
		childElementOfNodes(doc1,"STATUS"); 
		
		
		 NodeList nl = (NodeList) doc1.getElementsByTagName("TSVEMPLOYER_V100");
	     System.out.println( "parentNode......... length   is --> " + nl.getLength());  
	    for (int i = 0; i < nl.getLength(); i++) {
	    	 
	    	System.out.println(" inside FoR LOOP " +  i );
	    	     String NodeFirst = doc1.getElementsByTagName("TSVEMPLOYER_V100").item(0).getNodeName();
	    	     String NodeFirst1 = doc1.getElementsByTagName("TSVEMPLOYER_V100").item(0).getNodeValue();
	    	     System.out.println("NodeFirst  "  +  NodeFirst ); 
	    	     System.out.println("NodeFirst1  "  +  NodeFirst1 );
	 		  
	    	}
	}
		catch (Exception e) {  
	    	System.out.println("SAXException " +  e); 
	    }
		

		System.out.println("2222");	    
      return rootMap;
        	   
       } 
    	*/
    	//  verifyPDFData(document.getTSVERMSGSRSV1())
    	  
 
      
  
   
  public HashMap<String, String>  testPDF(String authtoken) throws Exception{
	  String payLoad = "{\"operationType\": \"Submit\",\"request\": {\"reportType\": \"Other\",\"lenderCaseIdentifier\": \"%s\",\"borrowerId\": \"B12346\",\"requestType\": \"Individual\",\"reportTypeOtherDescription\": \"VOI\",\"loanApplication\": {\"borrower\": [{ \"borrowerPrintPositionType\": \"BORROWER\", \"borrowerSSN\": \"799005103\",\"borrowerLastname\": \"DELINQUENT\",\"borrowerFirstname\": \"DAVID\",\"borrowerId\": \"B12346\" }] }, \"reportRequestActionType\": \"Submit\" },\"credentials\": { \"userName\": \"999TT72916\", \"password\": \"00CizkrrkRYuY\" },\"mismoversionID\": \"2.3.1\"}";
	  ResponseEntity<?> re = postRequest("https://stg-finconnect.tavant.com/fchub/rest/verify/income/submit",authtoken,payLoad.replace("%s", authtoken));
	  HashMap<String, String> rootMap = new HashMap<String, String>();
	  ObjectMapper objectMapper = new ObjectMapper();       
      objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      try {
    	//  System.out.println(" re.getBody().toString()  --- "  +  re.getBody().toString());
            // MainParser mp = objectMapper.readValue(re.getBody().toString(), MainParser.class);
             //System.out.println("rESPONSE>>>>"+re.getBody().toString());
            // objectMapper.readValue(re.getBody().toString(), MainParser.class);
             //System.out.println("xml>>>>"+mp.getTransactionDetails().size());
          //   System.out.println("xml1>>>>"+mp.getTransactionDetails().get(0).getProductCode());
    	  
    	/*  MainParser mp = objectMapper.readValue(re.getBody().toString(), MainParser.class);
    	  System.out.println("rESPONSE>>>>"+re.getBody().toString());
    	  System.out.println("is empty>>>>"+mp.getTransactionDetails().getCorrelationId());*/
    	   
    	  ResponseDTO td = objectMapper.readValue(re.getBody().toString(), ResponseDTO.class);    	
    	 /* System.out.println("Borrower ID>>>>"+td.getResponse()[0].getResponsedata()[0].getVoiresponse().getBorrower()[0].getBorrowerID());
    	  System.out.println("Borrower first name>>>>"+td.getResponse()[0].getResponsedata()[0].getVoiresponse().getBorrower()[0].getFirstName());
    	  System.out.println("responding last name>>>>"+td.getResponse()[0].getResponsedata()[0].getVoiresponse().getBorrower()[0].getLastName());
    	  System.out.println("ssn>>>>"+td.getResponse()[0].getResponsedata()[0].getVoiresponse().getBorrower()[0].getSsn());
    	//  System.out.println("document>>>>     "+td.getResponse()[0].getResponsedata()[0].getVoiresponse().getEmbeddedfile()[1].getDocument().getContent()[0].toString());*/
    	  //System.out.println("Product Id>>>>"+td.getTransactionDetailExtn().getProductCode());
    	  String document = td.getResponse()[0].getResponsedata()[0].getVoiresponse().getEmbeddedfile()[1].getDocument().getContent()[0].toString();
    	  
    	  System.out.println( "  document  0 -- " +  document); 
    	  
    	   
    	  
    	  HashMap<String,HashMap<String,String>> rootMap1 =  buildXMLMaplatest(document);
    	  
    	  
    	  Set<String> childKeySet1 = rootMap1.keySet();
			
			
			for(String childkEY:childKeySet1){
				
				
				
				System.out.println("CHILD KEYS>>>"+childkEY); 
				
				rootMap1.get(childkEY).get("NAME1"); 
				
				System.out.println("NAME1  value is	---	"+rootMap1.get(childkEY).get("NAME1"));
				System.out.println("STATE  value is	---	"+rootMap1.get(childkEY).get("STATE"));
				System.out.println("CITY  value is --- "+rootMap1.get(childkEY).get("CITY"));
				System.out.println("ADDR1  value is --- "+rootMap1.get(childkEY).get("ADDR1")); 
				System.out.println("POSTALCODE  value is ---"+rootMap1.get(childkEY).get("POSTALCODE"));
				System.out.println("JobTitle   value is ---- "+rootMap1.get(childkEY).get("POSITION-TITLE")); 
				System.out.println("EMPLOYEESTATUS:MESSAGE  IS  value is --- "+rootMap1.get(childkEY).get("EMPLOYEESTATUS:MESSAGE")); 
			 	 System.out.println("Most Recent Hire Date-   is  --> " +  dateFormat(rootMap1.get(childkEY).get("DTMOSTRECENTHIRE")) );
			 	 System.out.println("employment From Date       is  --> " +  dateFormat(rootMap1.get(childkEY).get("DTORIGINALHIRE")));  
			 	 
				 
			}   
			
			
    	  System.out.println();
    	 
    	  //Nikhil Patni  
    	  
    	   
    	  
    	   
      }
      catch (Exception e) {  
	    	System.out.println("SAXException " +  e); 
	    }  
		  
          
    	/*  String document = td.getResponse()[0].getResponsedata()[0].getVoiresponse().getEmbeddedfile()[1].getDocument().getContent()[0].toString();   	 
    	    	   
    		// For xml FILE INPUT
    	 * DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();		
        	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    		Document doc = (Document) dBuilder.parse(document);
    	  
    	  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    	  DocumentBuilder db = null;    		
    	  System.out.println( "test3");
    	      	   
   	  db = dbf.newDocumentBuilder();
	InputSource is = new InputSource();
	is.setCharacterStream(new StringReader(document));
	try {
		org.w3c.dom.Document doc1 = db.parse(is);
		String xmlContentvalues = doc1.getDocumentElement().getTextContent();
		// System.out.println("xmlContentvalues is " +
		// xmlContentvalues);

		System.out.println("Root element : -->	" + doc1.getDocumentElement().getNodeName()); System.out.println("MESSAGE element MESSAGE 1 : -->	"
				+ (doc1.getElementsByTagName("MESSAGE").item(0)).getTextContent());
		System.out.println("MESSAGE element2 : -->	" + (doc1.getNodeName()));
		System.out.println("MESSAGE element MESSAGE 2 : -->	"	+ (doc1.getElementsByTagName("MESSAGE").item(1)).getTextContent());

		
		childElementOfNodes(doc1,"TSVEMPLOYER_V100");					
		childElementOfNodes(doc1,"STATUS"); 
		
		
		 NodeList nl = (NodeList) doc1.getElementsByTagName("TSVEMPLOYER_V100");
	     System.out.println( "parentNode......... length   is --> " + nl.getLength());  
	    for (int i = 0; i < nl.getLength(); i++) {
	    	 
	    	System.out.println(" inside FoR LOOP " +  i );
	    	     String NodeFirst = doc1.getElementsByTagName("TSVEMPLOYER_V100").item(0).getNodeName();
	    	     String NodeFirst1 = doc1.getElementsByTagName("TSVEMPLOYER_V100").item(0).getNodeValue();
	    	     System.out.println("NodeFirst  "  +  NodeFirst ); 
	    	     System.out.println("NodeFirst1  "  +  NodeFirst1 );
	 		  
	    	}
	}
		catch (Exception e) {  
	    	System.out.println("SAXException " +  e); 
	    }
		

		System.out.println("2222");	  */  
      return rootMap;
        	   
       } 
    	 
  
  
  
  
  
  
  
  
  
  
  public static HashMap<String,HashMap<String,String>> buildXMLMaplatest(String test) throws Exception {
	  
	  System.out.println(" Inside buildXMLMaplatest");  

		String[] followingSiblingNodes = {"DTTRANSACTION","TSVEMPLOYER_V100","TSVEMPLOYEE_V100","TSVBASECOMP","TSVANNUALCOMP","TSVCOMPADJ","TSVOFFWORK","COMPLETENESS","SRVRTID","DEMOTRN","TSVBENEFITS_V100"};

		//String referenceXPath = "//TSVTWNSELECTRS/TSVRESPONSE_V100/TSVEMPLOYEE_V100[SSN='799005107']";
		String referenceXPath = "//TSVTWNSELECTRS/TSVRESPONSE_V100";
		String referenceNode = "TSVEMPLOYEE_V1001";
	
		
		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(test));

		org.w3c.dom.Document xmldoc = db.parse(is); 
		
		System.out.println( " xmldoc     --------" + xmldoc.getXmlVersion()); 
		System.out.println( " xmldoc     --------" + xmldoc.getXmlEncoding()); 
		System.out.println( " xmldoc     --------" + xmldoc.getNodeName());  
		
		System.out.println("             ");
		
		System.out.println(" After xml doc..........."); 
		
		
		// declaration 
		
		HashMap<Integer,HashMap<String,HashMap<String,String>>> rootMap = new HashMap<Integer,HashMap<String,HashMap<String,String>>>(); 
		//HashMap<String,HashMap<String,String>> xmlMap = new HashMap<String,HashMap<String,String>>();
		//HashMap<String,String> employeeMap = new HashMap<String,String>();
		
		//Build Employee Map
		NodeList nl1 = buildNodeList(xmldoc,referenceXPath);
		
		System.out.println( "NodeList size n1  "  + nl1.getLength());   
		
		for(int employerIndex = 1;employerIndex <= nl1.getLength();employerIndex++) {
			// System.out.println("tested loope");
			HashMap<String,HashMap<String,String>> xmlMap = new HashMap<String,HashMap<String,String>>();
			String revisedRefXpath = null;
			revisedRefXpath = referenceXPath+"["+String.valueOf(employerIndex)+"]";

			
			
			for(int fnNodeIndex = 0;fnNodeIndex < followingSiblingNodes.length;fnNodeIndex++) {
				String revisedXpath = null;
				revisedXpath =	revisedRefXpath+"/"+followingSiblingNodes[fnNodeIndex];;
				System.out.println("rvised rf xpath>>>>>"+revisedXpath);
				xmlMap = buildChildNodeMap(xmldoc,revisedXpath,followingSiblingNodes[fnNodeIndex],xmlMap);			
			}
			rootMap.put(employerIndex, xmlMap); 
		}
		
		System.out.println("Emplyers Count>>>>     1          "+rootMap.get(1));
		
		System.out.println("Emplyers Count>>>>    2            "+rootMap.get(2)); 
		System.out.println("Emplyers Count>>>>    3            "+rootMap.get(3));
		
		System.out.println("Emplyers Count>>>>    4            "+rootMap.get(4)); 
		
		System.out.println("employer size --" + rootMap.size()); 
		
		System.out.println("NAME1......  value is	---	"+rootMap.get(1).get("TSVEMPLOYER_V1001").get("NAME1")); 
		System.out.println("NAME2......  value is	---	"+rootMap.get(2).get("TSVEMPLOYER_V1001").get("NAME1"));  
		  
		/*for(String employeeKeys:rootMap.get(1).keySet()){
			System.out.println("Employee Index>>>>"+employeeKeys);
			
		}*/
		
		
		// HashMap<String,String> xmlNodeValueMap = new HashMap<String,String>();
		HashMap<String,HashMap<String,String>> xmlUiKeyValueMap = new HashMap<String,HashMap<String,String>>();
	
		String XMLJointkEY;
		for(int i =1 ; i<=rootMap.size(); i++)
		
		//for(int i =1 ; i<2; i++)  
		{ 
		
		//	System.out.println("                       " +i); 
/*	    System.out.println(" Emloyer " + i + " details are     ------------------------- "); 			
		System.out.println("NAME1  value is	---	"+rootMap.get(i).get("TSVEMPLOYER_V1001").get("NAME1"));
		System.out.println("STATE  value is	---	"+rootMap.get(i).get("TSVEMPLOYER_V1001").get("STATE"));
		System.out.println("CITY  value is --- "+rootMap.get(i).get("TSVEMPLOYER_V1001").get("CITY"));
		System.out.println("ADDR1  value is --- "+rootMap.get(i).get("TSVEMPLOYER_V1001").get("ADDR1")); 
		System.out.println("POSTALCODE  value is ---"+rootMap.get(i).get("TSVEMPLOYER_V1001").get("POSTALCODE"));
		System.out.println("JobTitle   value is ---- "+rootMap.get(i).get("TSVEMPLOYEE_V1001").get("POSITION-TITLE")); 
		System.out.println("EMPLOYEESTATUS:MESSAGE  IS  value is --- "+rootMap.get(i).get("TSVEMPLOYEE_V1001").get("EMPLOYEESTATUS:MESSAGE")); 
	 	 System.out.println("Most Recent Hire Date-   is  --> " +  dateFormat(rootMap.get(i).get("TSVEMPLOYEE_V1001").get("DTMOSTRECENTHIRE")) );
	 	 System.out.println("employment From Date       is  --> " +  dateFormat(rootMap.get(i).get("TSVEMPLOYEE_V1001").get("DTORIGINALHIRE")));  */
	 	 
	 	/* String Name = rootMap.get(i).get("TSVEMPLOYER_V1001").get("NAME1");
	 	 String From = dateFormat(rootMap.get(i).get("TSVEMPLOYEE_V1001").get("DTMOSTRECENTHIRE")); */
	 	 
	  
	     
	 //   System.out.println("XMLJointkEY     ---------------" + XMLJointkEY);   
	 	 
/*	 	xmlNodeValueMap.put("NAME1", rootMap.get(i).get("TSVEMPLOYER_V1001").get("NAME1"));
	 	xmlNodeValueMap.put("STATE", rootMap.get(i).get("TSVEMPLOYER_V1001").get("STATE"));
	 	xmlNodeValueMap.put("CITY", rootMap.get(i).get("TSVEMPLOYER_V1001").get("CITY"));
	 	xmlNodeValueMap.put("ADDR1", rootMap.get(i).get("TSVEMPLOYER_V1001").get("ADDR1"));
	 	xmlNodeValueMap.put("POSTALCODE", rootMap.get(i).get("TSVEMPLOYER_V1001").get("POSTALCODE"));
	 	xmlNodeValueMap.put("POSITION-TITLE", rootMap.get(i).get("TSVEMPLOYEE_V1001").get("POSITION-TITLE"));
	 	xmlNodeValueMap.put("EMPLOYEESTATUS:MESSAGE", rootMap.get(i).get("TSVEMPLOYEE_V1001").get("EMPLOYEESTATUS:MESSAGE"));
	 	xmlNodeValueMap.put("DTMOSTRECENTHIRE", dateFormat(rootMap.get(i).get("TSVEMPLOYEE_V1001").get("DTMOSTRECENTHIRE")));
	 	xmlNodeValueMap.put("DTORIGINALHIRE", dateFormat(rootMap.get(i).get("TSVEMPLOYEE_V1001").get("DTORIGINALHIRE")));*/
	 	
	 	  
	 	//xmlUiKeyValueMap
	 	
	 	 
	 	 
	 	//  System.out.println("employment To Date       is  --> " +  dateFormat(rootMap.get(i).get("TSVEMPLOYEE_V1001").get("DTENDEMPLOYMENT")) ); 
			Set<String> keys = rootMap.get(i).keySet();
			HashMap<String,String> xmlNodeValueMap = new HashMap<String,String>(); 
			for(String key:keys){
				
				//System.out.println("KEY IS>>>"+key);
				Set<String> childKeySet = rootMap.get(i).get(key).keySet();
				
				
				for(String childkEY:childKeySet){
					
					
					xmlNodeValueMap.put(childkEY, rootMap.get(i).get(key).get(childkEY));
				//	System.out.println("CHILD KEYS>>>"+childkEY); 
				//	System.out.println("its value is >>>>"+rootMap.get(i).get(key).get(childkEY));
					     
					  
				}  
				
				
				
				
				
				//Set<String> gcKeys = xmlNodeValueMap.keySet();
				
				
				// System.out.println("gcKeys size " +  gcKeys.size()); 
	/*			for(String gcKey:gcKeys){
					
					//xmlNodeValueMap.put(childkEY, rootMap.get(i).get(key).get(childkEY));
					System.out.println("CHILD KEYS>>>"+gcKeys); 
				//	System.out.println("its value is >>>>"+xmlNodeValueMap.get(gcKey));
					     
					 
				} */
			    
			} 
			
			String Name = xmlNodeValueMap.get("NAME1");
			 String From = dateFormat(xmlNodeValueMap.get("DTMOSTRECENTHIRE"));
			 XMLJointkEY = Name +From ;
			 
			System.out.println("XMLJointkEY ----> " +  XMLJointkEY + i); 
			
			System.out.println("  Name 1 is  ----------- >" + xmlNodeValueMap.get("NAME1"));  
	 	 
			xmlUiKeyValueMap.put(XMLJointkEY, xmlNodeValueMap);  
	 	 
	 	  
	 	    
		} 
	 	   
		/*Set<Integer> keySet1 = rootMap.keySet();
		
		for(Integer i:keySet1) {  
			Set<String> keySet = rootMap.get(i).keySet();
			
			for(String key:keySet) {
				HashMap<String, String> childMap = xmlMap.get(key);
				
				Set<String> keySet2 = childMap.keySet();
				
				for(String key1:keySet2) {
				//	System.out.println("Master value is >>>>   " +i+ "     Value is >>>>    "+childMap.get(key1) + "   and node name is >>>   "+key1+"   Parent is >>>  "+key);
					System.out.println(" Parent is >>> " +  key  + "  Value is >>>> "+childMap.get(key1)  + " and node name is >>> "  + key1  );
					
					employeeMap.put(key1, childMap.get(key1)); 
				}
			}   
			  
		}*/
		return xmlUiKeyValueMap;  
  
	} 
  
      public static  String dateFormat(String date1) throws ParseException
      {
    	  DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");  
    	   	  
    	  System.out.println("date1 is --> " + date1 );
    	   String yyyy    =  date1.substring(0, 4);
    	   String mm   =  date1.substring(4, 6);
    	   String dd   =  date1.substring(6, 8); 
    	    
    	     String expectedDate = mm+"/"+dd+"/"+yyyy;
    	   
		  return expectedDate;  
    	     
    	     
      } 
  
  
  
  // NodeList 
  
	public static NodeList buildNodeList(org.w3c.dom.Document doc,String expectedXpath) throws Exception{

		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		
		XPathExpression expr = xpath.compile(expectedXpath);
		
		  
		 
		NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		return nl;
	}
	
	
	//buildChildNodeMap
	
	public static HashMap<String,HashMap<String,String>> buildChildNodeMap(org.w3c.dom.Document xmldoc,String revisedXpath,String expectedNodeName,HashMap<String,HashMap<String,String>> xmlMap) throws Exception {
		int childNodeListSize = buildNodeList(xmldoc,revisedXpath).getLength();
		
		for(int childNodeIndex = 1;childNodeIndex <= childNodeListSize;childNodeIndex++) {
			String revisedXpath1 = null;
			String key = null;
			HashMap<String,String> childNodeMap = new HashMap<String,String>();
			if(childNodeIndex == 1) {
				revisedXpath1 = revisedXpath+"[1]";
				key = expectedNodeName+"1";
				
			}else {
				revisedXpath1 = revisedXpath+"["+String.valueOf(childNodeIndex)+"]";
				key = expectedNodeName+String.valueOf(childNodeIndex);
			}
			 
			
			childNodeMap = buildMap(buildNodeList(xmldoc,revisedXpath1));
			xmlMap.put(key, childNodeMap);
		}
		return xmlMap;
	}
	
	public static HashMap<String,HashMap<String,String>> buildChildNodeMap(org.w3c.dom.Document xmldoc,String revisedXpath,String expectedNodeName) throws Exception {
		int childNodeListSize = buildNodeList(xmldoc,revisedXpath).getLength();
		HashMap<String,HashMap<String,String>> xmlMap = new HashMap<String,HashMap<String,String>>();
		for(int childNodeIndex = 1;childNodeIndex <= childNodeListSize;childNodeIndex++) {
			String revisedXpath1 = null;
			String key = null;
			HashMap<String,String> childNodeMap = new HashMap<String,String>();
			if(childNodeIndex == 1) {
				revisedXpath1 = revisedXpath+"[1]";
				key = expectedNodeName+"1";
				
			}else {
				revisedXpath1 = revisedXpath+"["+String.valueOf(childNodeIndex)+"]";
				key = expectedNodeName+String.valueOf(childNodeIndex);
			}
			System.out.println("Keys in buildchildnodemap>>>>"+key);

			childNodeMap = buildMap(buildNodeList(xmldoc,revisedXpath1));
			//System.out.println("kEY vlue of child node map>>>"+key);
			xmlMap.put(key, childNodeMap);
		}
		return xmlMap;
	}
	public static HashMap<String,String> buildMap(NodeList nl) throws Exception{
        HashMap<String,String> dataMap = new HashMap<String,String>();
        for(int i = 0; i < nl.item(0).getChildNodes().getLength();i++ ) {
               int childCount = 0;
               String nodeName = "";
               String nodeValue = "";
           //     System.out.println("testing nde name>>>"+nl.item(0).getChildNodes().item(i).getNodeName());
  
        if(!nl.item(0).getChildNodes().item(i).getNodeName().equalsIgnoreCase("#text")) {
                     childCount = nl.item(0).getChildNodes().item(i).getChildNodes().getLength();
                     nodeName = nl.item(0).getChildNodes().item(i).getNodeName();
                     
               
                     if(childCount > 1) {
                            for(int j = 0; j < childCount;j++) {
                            if(!nl.item(0).getChildNodes().item(i).getChildNodes().item(j).getNodeName().equalsIgnoreCase("#text")) {
                                  String childNodeName = nl.item(0).getChildNodes().item(i).getChildNodes().item(j).getNodeName();
                                  String childNodeValue = nl.item(0).getChildNodes().item(i).getChildNodes().item(j).getTextContent();
                                   dataMap.put(nodeName+":"+childNodeName, childNodeValue);
                                  }
                                  
                            }
                     }else {
                            nodeValue = nl.item(0).getChildNodes().item(i).getTextContent();
                            dataMap.put(nodeName, nodeValue);
                     }
                     
               }else if(nl.item(0).getChildNodes().getLength() == 1){
                     nodeName = nl.item(0).getNodeName();
                     nodeValue = nl.item(0).getTextContent();
                     
                     System.out.println( "nde nme>>>."+nodeName+" and its respective node value>>>>"+nodeValue);
                     dataMap.put(nodeName, nodeValue);
  
                      
               }
        }
        return dataMap;
  }

  
  
      
	  
  
  
   
  private void childElementOfNodes(org.w3c.dom.Document doc1, String parentNode) {
	  System.out.println("Parent Node is -->  " +  parentNode); 
	  
	  NodeList nl = (NodeList) doc1.getElementsByTagName(parentNode);
		System.out.println( parentNode   + " length   is --> " + nl.getLength()); 
		for (int i = 0; i < nl.getLength(); i++) {
			String childElement = (doc1.getElementsByTagName(parentNode).item(0)).getTextContent();
			System.out.println("childElement of " + i + " is --> " + childElement);
		}  
	
}  







	public void testWebService() throws Exception {

		System.out.println(" inside  testWebService ");
		String authToken = getAuthenticationToken();
		System.out.println("authToken ---> " + authToken);
		HashMap<String, String> rootMap = testPDF(authToken); 	
		
		
		 		 		
		HashMap<String, String> uiXmlMap = dataMapUiandXml();
	}
 
  
	
	
	
	
	private HashMap<String, String> dataMapUiandXml() {
		// TODO Auto-generated method stub

		System.out.println(" inside dataMapUiandXml ");

		String[] UIFields = { "Employer name", "Street address line 1", "Street address line 2", "Zipcode", "State","City", "County", "Extension", "Employer phone", "Job title", "Job title", "Overtime", "Bonus",
				"Commission", "Other", "Monthly gross income" };
		String[] XMLFields = { "NAME1", "ADDR1", "TSVBASE", "TSVOTHER", "TSVCOMMISSION", "TSVOVERTIME", "TSVBONUS","TSVTOTAL", "POSTALCODE", "POSITION-TITLE", "DTORIGINALHIRE", "DTENDEMPLOYMENT", "DTMOSTRECENTHIRE",
				"EMPLOYEESTATUS:MESSAGE" };

		HashMap<String, String> incomeObjectMap = new HashMap<String, String>();
		incomeObjectMap.put("Employer name", "NAME1");
		incomeObjectMap.put("Street address line 1", "ADDR1");
		incomeObjectMap.put("Zipcode", "POSTALCODE");
		incomeObjectMap.put("STATE", "State");
		incomeObjectMap.put("CITY", "City");
		incomeObjectMap.put("Job title", "POSITION-TITLE"); 
		  
		return incomeObjectMap;
		
		/*	Set<Integer> keySet1 = rootMap.keySet();
		
		for(Integer i:keySet1) {
			Set<String> keySet = rootMap.get(i).keySet();
			
			for(String key:keySet) {
				HashMap<String, String> childMap = xmlMap.get(key);
				
				Set<String> keySet2 = childMap.keySet();
				
				for(String key1:keySet2) {
				//	System.out.println("Master value is >>>>   " +i+ "     Value is >>>>    "+childMap.get(key1) + "   and node name is >>>   "+key1+"   Parent is >>>  "+key);
					System.out.println(" Parent is >>> " +  key  + "  Value is >>>> "+childMap.get(key1)  + " and node name is >>> "  + key1  );
				}
			}
		}
*/
	}



	public void testWebService123233() throws Exception {

		
		/*String authToken = getAuthenticationToken("https://sandbox-finconnect.tavant.com/fchub/oauth/token?grant_type=client_credentials","");
		testPost("https://sandbox-finconnect.tavant.com/fchub/rest/verify/income/submit", authToken);
		System.out.println("auth token in tst web service>>>"+authToken);*/
		
		String authToken = getAuthenticationToken("https://stg-finconnect.tavant.com/fchub/oauth/token?grant_type=client_credentials","");
		testPost("https://stg-finconnect.tavant.com/fchub/rest/verify/income/submit", authToken);
		System.out.println("auth token in tst web service>>>"+authToken);  
		
		
		/*System.out.println(" check- 1");
		postRequest(guiIDurl, "");
		ResponseEntity<?> responseEntity = postRequest(guiIDurl, "");
		System.out.println(" check- 2");
		if (responseEntity.getStatusCode() != HttpStatus.OK) {
			System.out.println(" check- 3");
			addExceptionToReport("Fetching GUID From Encompass failed for Loan Number ",
					String.valueOf(responseEntity.getStatusCode()), "200");
		}
		System.out.println(" check- 4");
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			System.out.println(" check- 5");

			MainParser mp = objectMapper.readValue(responseEntity.getBody().toString(), MainParser.class);
			objectMapper.readValue(responseEntity.getBody().toString(), MainParser.class);
			System.out.println(" check- 6");
			System.out.println("objectMapper-->" + responseEntity.getBody().toString());

		} catch (Exception e) {
			System.out.println(" check- 7");
			System.out.println("test case got failed ");
		}

	}*/
	}

	public int getLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Node item(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}


 


	@Override
	public void checkPage() {
		// TODO Auto-generated method stub
		
	}

}

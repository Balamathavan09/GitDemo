package com.finx.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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
import com.tavant.utils.TwfException;

public class Velocify extends CustomStep {
	static String leadIdValue=null;
	List<String> twoFields =  new ArrayList<String>();
	String[] fixedField = {"First Name:","Last Name:","Sales Group:","Cell Phone:","Email:"};
public void hitVelocifyUrl() throws Exception{
		
		Util util=new Util();
		System.out.println("inside hitVelocifyUrl");
		String[] autoPopulatedFields = {"Email:","Zip Code:","State:","City:","County:","Property City:","Property State:","Day Phone:"};
		
		WebDriver driver = DriverFactory.getDriver();
		driver.manage().window().maximize();
		System.out.println("Inside hitVelocifyUrl>>>>>>");
		
		String parentWindowHandle = driver.getWindowHandle();
		((JavascriptExecutor)driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get(KWVariables.getVariables().get("VelocifyURL"));
//		driver.get("https://lm.velocify.com/Web/Login.aspx");
	

		getElementByUsing("Velocify_Username").sendKeys(KWVariables.getVariables().get("Velocify_username"));
		Thread.sleep(1000);
		getElementByUsing("Velocify_Password").sendKeys(KWVariables.getVariables().get("Velocify_password"));
		driver.findElement(By.xpath(".//*[@id='loginButton']")).click();
		Thread.sleep(5000);
		
		getLeadId();
		System.out.println("Loan leadIDValue is>>>>>>"+leadIdValue);
		util.waitForElement("Velocify_Search");
		Thread.sleep(2000);
		getElementByUsing("Velocify_Search").sendKeys(leadIdValue);
		Thread.sleep(3000);
		getElementByUsing("Velocify_Search").sendKeys(Keys.ENTER);
		Thread.sleep(4000);
		getElementByUsing("Velocify_Edit").click();
		Thread.sleep(5000);


		
		HashMap<String,HashMap<String,String>> dataMap = new HashMap<String,HashMap<String,String>>();
		Ten0Three tenoThree= new Ten0Three();
//		for(String dataCol:dataColumns){
			HashMap<String,String> colDataMap = new HashMap<String,String>();
			 colDataMap = tenoThree.buildDataMap(KWVariables.getVariables().get("callMeData_NewUser"));
			 colDataMap.put("Sales Group",tenoThree.salesGroup);
		     System.out.println("ColDataMap>>>>"+colDataMap+"LENGTH!!!!!>>"+colDataMap.keySet().size());
//		}
	
//		List<String> list=util.fetchDataFromNotepad1("VelocifyFieldMapping.txt");
		
		HashMap<String, String> fieldMap = new HashMap<String,String>();
		HashMap<String, String> velMap = new HashMap<String,String>();
		
		for(String key:colDataMap.keySet()){
			String expectedFieldName =  colDataMap.get(key.trim());
			System.out.println("SALESGROUP IN VE>>"+tenoThree.salesGroup);
			System.out.println("Loan expectedFieldName >>>>>>"+expectedFieldName+"LENGTH>>>"+key.trim().length());
			System.out.println("Loan leadIDValfadfasdfue is331>>>>>>"+colDataMap.get(key.trim()));
				 String displayedFieldValue = fetchVelocifyFieldValues(driver,key+":");
				 System.out.println("Loan leadIDValfadfasdfue is431>>>>>>"+displayedFieldValue);
				 
           	 System.out.println("Inside 11111111111"+expectedFieldName);
					compareValues(expectedFieldName, displayedFieldValue, key);
		}
		
		}


	private String fetchVelocifyFieldValues(WebDriver driver,String fieldName) throws Exception{
		Util util=new Util();
//		 System.out.println("Inside fetchVelocifyField tabName---->>>"+tabName);
		 System.out.println("Inside fetchVelocifyField fieldName---->>>"+fieldName);
        if(fieldName.equalsIgnoreCase("Cell Phone Number:")){
        	fieldName="Cell Phone:";
        }
		twoFields.add("First Name:");twoFields.add("Last Name:");twoFields.add("Sales Group:");twoFields.add("Cell Phone:");twoFields.add("Email:");
		
			if(twoFields.contains(fieldName)){	
				System.out.println("FIELDNAME>>>>>>>>"+driver.findElement(By.xpath("(//span[text()='"+fieldName+"']//parent::th//following-sibling::td//input)[1]")).getAttribute("value").toString());
				return driver.findElement(By.xpath("(//span[text()='"+fieldName+"']//parent::th//following-sibling::td//input)[1]")).getAttribute("value");
			}
		 
		
		return driver.findElement(By.xpath("//span[text()='"+fieldName+"']//parent::th//following-sibling::td/input")).getAttribute("value");	
	}
	
	
	
	
	public String getLeadId() throws Exception {
//		String Db_url = KWVariables.getVariables().get("DBUrl");
		String Db_url="";
		int Db_IPAddress = 0;
//		MongoClient mongoClient = new MongoClient(Db_url, 27017);
		Ten0Three ten=new Ten0Three();
        System.out.println("Inside getLeadId>>>>"+ten.EmailId);
		Thread.sleep(4000);
//		@SuppressWarnings("deprecation")
//		DB test = mongoClient.getDB("callback");
//		DBCollection dbc = test.getCollection("callback");
//        String email="sweata.shaw@gmail.com";
//		try {
			
			
			System.out.println("env selection>>>"+KWVariables.getVariables().get("Env"));
			switch(KWVariables.getVariables().get("Env").toLowerCase()){
			case "qa":
			Db_url = KWVariables.getVariables().get("ConfigFlag_DBUrl_QA");
			Db_IPAddress=Integer.valueOf(KWVariables.getVariables().get("ipAddress_QA"));
			break;
			case "uat":
			Db_url = KWVariables.getVariables().get("ConfigFlag_DBUrl_UAT");
			Db_IPAddress=Integer.valueOf(KWVariables.getVariables().get("ipAddress_UAT"));;
			break;	
			case "taurus":
				Db_url = KWVariables.getVariables().get("ConfigFlag_DBUrl_TAURUS");
				Db_IPAddress=Integer.valueOf(KWVariables.getVariables().get("ipAddress_QA"));
			break;
			case "pisces":
				Db_url = KWVariables.getVariables().get("ConfigFlag_DBUrl_PISCES");
				Db_IPAddress=Integer.valueOf(KWVariables.getVariables().get("ipAddress_QA"));
			break;
			case "capricorn":
				Db_url = KWVariables.getVariables().get("ConfigFlag_DBUrl_CAPRICORN");
				Db_IPAddress=Integer.valueOf(KWVariables.getVariables().get("ipAddress_QA"));
			break;
			case "virgo":
				Db_url = KWVariables.getVariables().get("ConfigFlag_DBUrl_VIRGO");
				Db_IPAddress=Integer.valueOf(KWVariables.getVariables().get("ipAddress_QA"));
			break;
			case "leo":
				Db_url = KWVariables.getVariables().get("ConfigFlag_DBUrl_LEO");
				Db_IPAddress=Integer.valueOf(KWVariables.getVariables().get("ipAddress_QA"));
			break;
			case "aquarius":
				Db_url = KWVariables.getVariables().get("ConfigFlag_DBUrl_AQUARIUS");
				Db_IPAddress=Integer.valueOf(KWVariables.getVariables().get("ipAddress_QA"));
			break;
			case "aries":
				Db_url = KWVariables.getVariables().get("ConfigFlag_DBUrl_ARIES");
				Db_IPAddress=Integer.valueOf(KWVariables.getVariables().get("ipAddress_QA"));
			break;
			case "scorpio":
				Db_url = KWVariables.getVariables().get("ConfigFlag_DBUrl_SCORPIO");
				Db_IPAddress=Integer.valueOf(KWVariables.getVariables().get("ipAddress_QA"));
			break;
			case "libra":
				Db_url = KWVariables.getVariables().get("ConfigFlag_DBUrl_LIBRA");
				Db_IPAddress=Integer.valueOf(KWVariables.getVariables().get("ipAddress_QA"));
			break;
			case "gemini":
				Db_url = KWVariables.getVariables().get("ConfigFlag_DBUrl_GEMINI");
				Db_IPAddress=Integer.valueOf(KWVariables.getVariables().get("ipAddress_QA"));
			break;
			case "cancer":
				Db_url = KWVariables.getVariables().get("ConfigFlag_DBUrl_CANCER");
				Db_IPAddress=Integer.valueOf(KWVariables.getVariables().get("ipAddress_QA"));
			break;
			}
			MongoClient mongoClient = new MongoClient(Db_url, Db_IPAddress);
	        String Db2="callback";
	        Dashboard dashboard=new Dashboard();

	        System.out.println("Inside getLeadId>>>");
	        MongoCredential credential2 = MongoCredential.createCredential("ro_user", "admin", "ro_us3r".toCharArray());
	        MongoClient  mongoClient2 = new MongoClient(new ServerAddress(Db_url, Db_IPAddress), Arrays.asList(credential2));
	        

	        try {
	        @SuppressWarnings("deprecation")
	        DB test2 = mongoClient2.getDB(Db2);
	        DBCollection dbc_quoteinfo = test2.getCollection("callback");
	        BasicDBObject query = new BasicDBObject();
	        BasicDBObject query1 = new BasicDBObject();
	        query.put("emailId",ten.EmailId);
	        query1.put("createdDate",-1);
			System.out.println("Query---------->>>>>>>>>");
			DBCursor dbcrsr =dbc_quoteinfo.find(query).sort(query1);
//			DBCursor dbcrsr = dbc_quoteinfo.find(query);
			BasicDBObject record = (BasicDBObject) dbcrsr.next();
			System.out.println("lead if>>>>" + record.get("leadId"));
			if (record.get("leadId") != null) {
				leadIdValue = record.get("leadId").toString();
				System.out.println("leadIdValue>>>>>>!!!!!****"+leadIdValue);
			}
				mongoClient.close();
			} catch (Exception e) {
				mongoClient.close();
				addExceptionToReport("Exception seen while fetching leadID for the reference ID " + "sweata.shaw@gamil.com"
						+ " and exception is " + e.getMessage(), "", "");
			}
			return leadIdValue;
			
			
			
			
			
			
			
			
//			BasicDBObject query = new BasicDBObject();
//			query.put("emailId",ten.EmailId);
//			System.out.println("mail>>>>"+ten.EmailId);
//			System.out.println("Query---------->>>>>>>>>");
//			DBCursor dbcrsr = dbc.find(query);
//			BasicDBObject record = (BasicDBObject) dbcrsr.next();
//			System.out.println("userID if>>>>" + record.get("leadId"));
//			if (record.get("leadId") != null) {
//				leadIdValue = record.get("leadId").toString();
//				System.out.println("leadIdValue>>>>>>!!!!!****"+leadIdValue);
//			}
//			
//			mongoClient.close();
//		} catch (Exception e) {
//			mongoClient.close();
//			addExceptionToReport("Exception seen while fetching leadID for the reference ID " + "sweata.shaw@gamil.com"
//					+ " and exception is " + e.getMessage(), "", "");
//		}
//		return leadIdValue;

   }
	

	public void compareValues(String expectedFieldValue,String displayedFieldValue,String velocifyField) throws Exception{
		Ten0Three ten=new Ten0Three();
		
		if(velocifyField.equalsIgnoreCase("Email")){
			expectedFieldValue=ten.EmailId;
			System.out.println("INNSSIIDEEEE>>>>>>compareValues"+expectedFieldValue);
		}
	
		if(velocifyField.equalsIgnoreCase("Sales Group")&&expectedFieldValue.contains("Purchasing")){
			expectedFieldValue="Purchase";
		}
 
		if(velocifyField.equalsIgnoreCase("Sales Group")&&expectedFieldValue.equalsIgnoreCase("Refinancing")){
			if(Ten0Three.isNewUserCallMe){
				expectedFieldValue="Portfolio";
				Ten0Three.isNewUserCallMe=false;
			}
			else
				expectedFieldValue="NCA Refinance";
		}
		
		if(displayedFieldValue.trim().replaceAll("-", "").replace(")", "").replace("(", "").replace(" ", "").contains(expectedFieldValue.trim().replaceAll("-", "").replace(" ", ""))){
			
			System.out.println("Values displayed in velocify>>>>"+ displayedFieldValue +">>>>>>for the field names>>>>>"+expectedFieldValue);
		}
		else
			addExceptionToReport("value displayed in the velocify>>>"+ displayedFieldValue.replaceAll("-","").replace(")","").replace("(","") +" is not matching with the expected value>>>"+ expectedFieldValue.replaceAll("-", "") +" for>>"+ velocifyField , "", "");	
		
	}

	@Override
	public void checkPage() {
		// TODO Auto-generated method stub
		
	}
	
	
	}

	
	


package com.finx.util;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;
import com.tavant.base.DriverFactory;
import com.tavant.kwutils.CustomStep;
import com.tavant.kwutils.KWVariables;
import com.tavant.utils.TwfException;

import jxl.read.biff.BiffException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;

public class Ten0Three extends CustomStep {
	private static final String Object = null;
	static ArrayList rateQuoteList = new ArrayList<String>();
	static boolean toValidate = false;
	static boolean isCoApplicant=false;
	static boolean isPrefilled=false;
	static boolean iscreditforCoApplicant=false;
	static int lastIndex;
	int expMapSize = 0;
	static boolean scrollRequired=false;
	static String offlinedata;
	public static String documentListData="";
	String rateQuoteIndex = "//div[@class='product-container']/div//div[@class='add-quote-message']";
	String title1 = "//div[@class='product-container']/div[";
	String title2 = "]//div//h1/div[1]";
	String Password = "(.//*[@id='login_password'])[2]";
	String Email = "(.//*[@id='login_userId'])[2]";
	String autoPromtBoxLogin = ".//*[@id='myModal1']/div/div/div/div[2]/div/div[2]/button[1]";
	String autoPopuploginButton = "(//button[contains(.,'Login')])[3]";
	String loginPopUp = ".//*[@id='autop_name']";
	String headerFieldValueObject = "//div[@class='admin-header-container']/div[@class='row']//div[@class='row'][%s]/div[1]//input";
	String Qustion1 = "//div[@class[contains(.,'form-container tf-block-%s active')]]//div[@class='form-group']//form/div[2]//dynamic-control/div/div//label";
	String Qustion = "(//div[@class='form-container tf-block-%s active-section']//div/label)[1]";
	// String Qustion = "(//div[@class='form-container
	// tf-block-%s']//div/label)[1]";
	String sub_ans_text = "//div[@class='form-container tf-block-%s active-section']//dynamic-control//input[@placeholder='%placeholder']";
	String sub_ans_dropdown = "//div[@class='form-container tf-block-%s active-section']//dynamic-control/div/div//div/div/div//button[contains(.,'%t')]";
	String sub_ans_dropdown_search = "(//div[@class[contains(.,'form-container tf-block-%s active')]]//div[@class='form-group']//form//location//dropdown-select//ul/div/input)[%t]";
	String sub_ans_dropdown_value = ".//*[@class='dropdown open']/ul/li[contains(.,'%val')]";
	String sub_ans_checkbox = "//label[contains(text(),'%labeltext')]//following::input[1]";
	String sub_ans_checkbox1 = "//div[@class[contains(.,'form-container tf-block-%s active')]]//label[text()=\"%labeltext\"]//following::input[@type='checkbox'][1]";//HareeshUpdated
	String sub_ans_radiobutton = "//div[@class[contains(.,'form-container tf-block-%s active')]]//dynamic-control//radio-button-selection//button[contains(.,'%radioButtonName')]";
	String Sub_ans_datepicker = ".//div/finx-from-to/div[contains(.,'%datelabel')]//my-date-picker/div/div/input";
	String dateOfBirth = "//div[@class[contains(.,'form-container tf-block-%s active')]]//input";
	String activeQtnIndex = "//div[@class[contains(.,'form-container tf-block-%s active')]]";
	String qtnIndex = "(//div[@class='form-container tf-block-%s']//div/label)[1]";
	String fieldAtttibute1 = "//div[@class[contains(.,'form-container tf-block-%s active')]]//div[@class='form-group']//form/div[2]//dynamic-control/div/div//div/div/div";
	String fieldAtttibute = "(//div[@class='form-container tf-block-%s active-section']//dynamic-control/div/div//div/div/div)[1]";
	String OkayBUtton1 = "//div[@class[contains(.,'form-container tf-block-%s active')]]//div[@class='form-group']//form//button[@class[contains(.,'submit')]]";
	String radioButtonAns1 = "//div[@class[contains(.,'form-container tf-block-%s active')]]//div[@class='form-group']//form/div[2]//dynamic-control/div/div//div/div/button[contains(.,'%v')]";
	// String radioButtonAns = "//div[@class[contains(.,'form-container
	// tf-block-%s
	// active')]]//div[@class='form-group']//dynamic-control/div/div//div/div/button[contains(.,'%v')]";
	String radioButtonAns = "//div[@class[contains(.,'form-container tf-block-%s')]]//div[@class='form-group']//dynamic-control/div/div//div/div/button[contains(.,'%v')]";
	String OkayBUtton2 = "//div[@class[contains(.,'form-container tf-block-%s active')]]//div[@class='form-group']//form//button[@class[contains(.,'submit')]]";
	String inputText = "//div[@class[contains(.,'form-container tf-block-%s active')]]//div[@class='form-group']//form//dynamic-control//div/md-input-container//input";
	String fieldValidationError = "//*[@class='error']";
	String rateQuoteXpathTitle = "//div[@class='product-container']/div[%s]//div//h1/div[1]";
	String rateQuoteXpathEleName = "//div[@class='product-container']/div[%s]//div/ul/li[%v]/div[2]";
	String rateQuoteXpathEleValue = "//div[@class='product-container']/div[%s]//div/ul/li[%v]/div[1]";
	// String URL = "http://10.131.148.191:9090/#/productsScreen";
	String addToComcheckBox = "(//input[@name='chkHelper'])[%s]";
	String zipcodeDropDown = "(//div[@class[contains(.,'form-container tf-block-%n active')]]//div[@class='form-group']//form/div[3]//dynamic-control/div/div//dropdown-select/div/div/button)[%s]";
	String zipcodeDropDownSerach = "(//div[@class[contains(.,'form-container tf-block-%n active')]]//div[@class='form-group']//form/div[3]//dynamic-control/div/div//dropdown-select/div/div/ul/div/input)[%s]";
	String zipCodeDropValue = "((//div[@class[contains(.,'form-container tf-block-%n active')]]//div[@class='form-group']//form/div[3]//dynamic-control/div/div//dropdown-select[%s]/div/div/ul/li)[1])";
	String addToCompareButton = "//button[contains(.,'Add to Compare')]";
	// String helpText = ".//*[@class='help-sec tf-block-%s
	// active-section']/div";
	String helpText = ".//div[@class[contains(.,'help-sec tf-block-%s')]]";
	String createAccountPopUp = "//div[@class='modal fade prequal-modal in']//div[@class='modal-body']//a[contains(.,'Login')]";
	String dropDownFieldObject = "//div[@class='form-container tf-block-%s active-section']//dynamic-control/div/div//div/div/div//dropdown-select//div[contains(.,'%v')]//following-sibling::div//button";
	String Compare_QuotesButton = "//button[contains(.,'Compare Quotes 3')]";
	String logo = "//div[@class='logo']";
	String progressBar = "//div[contains(@class,'percentage-indicator')]";
	String postLoginPopup = "(.//*[@id='myModal1']/div/div/div)[2]";
	String URL = "http://10.209.1.38:9090/#/productsScreen";
	String postLoginActiveuser_message = "Good to see you back! You already have an account with us, login to save your progress or skip for now.";
	String postLogin_Newuser_message = "Hi there.. You are not registered with us. Would you like to create account with us to continue saving your progress?";
	String postLogin_InactiveUser_message = "Hi there.. Your email is not verified with us. Do you wish to receive an verification link again on your email?";
	String postLogin_SendMeLink_message = "Thank you. We have successfully sent you the account verification link on your email.";
	String invalidAddressSkipButton = "//button[@id='btn_confirm_modal_No' and contains(.,'Skip')]";
	String spouseQtnModalWindow = "(//button[@id='btn_confirm_modal_Yes'])[2]";
	String inactiveQtnIndex = "(//div[@class[contains(.,'form-container tf-block-%s')]]//label)[1]";
	// String dd = "//div[@class='form-container tf-block-%s
	// active-section']//dynamic-control/div/div//div/div/div//button[@value='%t']";
	public static String importAssetMessage="(//div[@class='body-tit'])[1]"; 
	String dd = "//div[@class='form-container tf-block-%s active-section']//dynamic-control/div/div//div/div/div//div[@aria-labelledby='%t']//button";
	String urla_dd="//div[@class='form-container tf-block-%s active-section']//dynamic-control/div/div//div/div/div//div[@class='dropdown']//button[@value='%t']";
	String rdd = "//div[@class='form-container tf-block-%s active-section']//input[@placeholder='%p']//following::button[1]";
	String sub_ans_dropdown_value1 = "//div[@class='form-container tf-block-%s active-section']//li/a[text()='%val']";
    static String EmailId;
    static String salesGroup;
    static boolean incompleSection=false;
    static String val="";
    static boolean coborrLoanonboard=false;
    public static LinkedHashMap<String, String> propertydataMap = new LinkedHashMap<String, String>();
    static boolean preFilledValue = false;
	String sub_ans_dropdown_value2 = "(//input[@placeholder='%p']//following::a[text()='%val'][1])";
	private static final String TITLE = "Title";
	static String callMeData="";
	// EncompassData encompassData = new EncompassData();
	GenerateData2 dummyData = new GenerateData2();
	static Util utill = new Util();
	InternalConditions internal=new InternalConditions();
	static LinkedHashMap<String,String> name=new LinkedHashMap<>();
	TenOThreeSectionSummary tenothreesectionsummary = new TenOThreeSectionSummary();
	Dashboard dashboard=new Dashboard();
	HashMap<String, String> map = new HashMap<String, String>();
	HashMap<String, String> encompassValidatedataMap = new HashMap<String, String>();
	HashMap<String, String> errorMap = new HashMap<String, String>();

	String[] getStartedCPSPageQtns = {};
	static String fname = null;
	static String coApplicantFname = null;
	static String isNewUser = null;
	static boolean isNewUserCallMe = false;
	static boolean flag=true;
	public static String okayButton="(//div[@id='confirm-modal']//div[@class='body-tit' and contains(.,'We have successfully')])[1]//..//button[contains(text(),'Okay')]";
	public static String addItManuallyImportButton="//button[text()=' Add it Manually']";
	static String uspsFlag="false", standardisedAddress="";
//	String[] propertSummaryPageQtns = { "Estimated Property Value:Estimated Property Value&Estimated Property Value",
//			"The property is located at:Property Location", "My property is a:Property Type",
//			"I am using the property as:Property Occupancy" };
	
	String[] propertSummaryPageQtns = { "Estimated Property Value:Estimated Property Value&Estimated Property Value",
			"The property is located at:Property Location", "I am purchasing a:Property Type",
			"I will use this property as:Property Occupancy" };
	String[] propertSummaryRefinancePageQtns={"Estimated Property Value:Estimated Property Value&Estimated Property Value",
			"The property is located at:Property Location", "My property is a:Property Type",
			"I am using the property as:Property Occupancy"};
	
	String[] getStartedCoapplicantCPSSummaryPageQtns = { "Provide spouse details:Name&First name", "Spouse date of birth is:Date of Birth",
			"My marital status:Marital Status", "Spouse Contact Details:Contact Number" };
	
	String[] getStartedCoapplicantSummaryPageQtns = { "My name is:Name&First name", "I was born on:Date of Birth",
			"My marital status:Marital Status", "My contact details are:Contact Number" };
	String[] liabilitySummaryPageQtns = { "My liability type is:Liability type",
			"Liability details:Account Number&Account number",
			"Liability details:Outstanding Balance&Outstanding balance",
			"Liability details:Monthly Payment&Monthly payment", "Liability details:Status&Status" };
	String[] realEstateSummaryPageQtns = { "The property is located at:Street Address Line1&Street address line 1",
			"Property details:Property Value&Property value",
			"Property details:Outstanding Balance&Outstanding balance" };
	String[] declarationSummartPageQtns = { "Are you a US citizen?:US Citizen",
			"Are there any outstanding judgements against you ?:Judgment",
			"Have you had property foreclosed upon or given title or deed in lieu thereof in the last 7 years?:Foreclosure",
			"Have you been declared bankrupt within the past 7 years?:Bankruptcy",
			"Are you presently delinquent or in default on any Federal debt or any other loan, mortgage, financial obligation, bond, or loan guarantee?:Federal debt default" };
	String[] assetSummaryPageQtns = { "My asset type:Assets type",
			"Provide asset details:Account Number&Account number (Optional)", "Provide asset details:Amount& Amount" };
	String[] giftAssetSummaryPageQtns1 = { "My asset type:Assets type",
	"Provide asset details:Amount& Amount" };
	
	String[] giftAssetSummaryPageQtns = { "Provide asset details:Amount& Amount" };
	static String status = "";
	int months = 0;
	static HashMap<String,String> getStartedDataMap = new HashMap<>();
	static HashMap<String,String> getStartedCoAppDataMap = new HashMap<>();
	static ArrayList cPSList = new ArrayList<String>();
	static int fNameIndex = 0;
	/**
	 * Method Name: buildDataMap Purpose: To create HashMap<String, String> from
	 * given String
	 * 
	 * @throws Exception
	 */

	public static HashMap<String, String> buildDataMap(String testData) throws Exception {

		HashMap<String, String> qaMap = new HashMap<String, String>();
		String[] queAns = testData.split("&&");
		for (String qA : queAns) {
			qaMap.put(qA.split(";")[0].trim(), qA.split(";")[1].trim());
		}
				
		// HashMap<String,String> dataFromEncompass=
		// encompassData.buildGetStartedEncompassMap("7201770762","{7711cc10-4707-4f32-b60f-c0d6f30866bb}");
		// comparingTwoHashMap(dataFromEncompass,qaMap);
		return qaMap;

	}
	public static LinkedHashMap<String, String> buildDataMap1(String testData) throws Exception {

		LinkedHashMap<String, String> qaMap = new LinkedHashMap<String, String>();
		String[] queAns = testData.split("&&");
		for (String qA : queAns) {
			qaMap.put(qA.split(";")[0].trim(), qA.split(";")[1].trim());
		}
				
		// HashMap<String,String> dataFromEncompass=
		// encompassData.buildGetStartedEncompassMap("7201770762","{7711cc10-4707-4f32-b60f-c0d6f30866bb}");
		// comparingTwoHashMap(dataFromEncompass,qaMap);
		return qaMap;

	}
	/**
	 * Method Name: buildDataMap Purpose: To create HashMap<Integer, String>
	 * from given String
	 * 
	 * @throws Exception
	 */

	public HashMap<Integer, String> buildDataMaporder(String testData) {
		HashMap<Integer, String> qaMap = new HashMap<Integer, String>();
		String[] queAns = testData.split("&&");
		for (String qA : queAns) {
			Integer a = Integer.parseInt(qA.split(";")[0].trim());
			qaMap.put(a, qA.split(";")[1].trim());
		}
		return qaMap;
	}

	/**
	 * Method Name: validateQuestionAnswerIn1003GetStarted Purpose: To fill 1003
	 * getstarted question answer
	 * 
	 * @throws Exception
	 */

	public void validateQuestionAnswerIn1003GetStarted() throws InterruptedException, TwfException, Exception {
		String quesAnsString = step.getDataValue("question_ans");
		System.out.println("\n Question String:>>>"+quesAnsString);
		isNewUser = step.getDataValue("NewUser");

		if(!step.getDataValue("Rate Quote Validation Questions").isEmpty()){
			System.out.println("Enter Rate Quote Validation Questions map build");
			String[] rateQuoteQuestions=step.getDataValue("Rate Quote Validation Questions").split("&&");
			for(String rateQuestion:rateQuoteQuestions){
				rateQuoteList.add(rateQuestion);
			}
		}
		if(KWVariables.getVariables().get("Env").equalsIgnoreCase("taurus")||KWVariables.getVariables().get("Env").equalsIgnoreCase("pisces")){
			scrollRequired=true;
		}
		System.out.println("Built Rate Quote List");
		// String fieldMap = step.getDataValue("SummaryFieldMapping");
		getStartedDataMap = validateQuestionAnswerIn1003(quesAnsString);
		//new static variable
		if(standardisedAddress.length()>0){			
			String prevValue = getStartedDataMap.get("My current address is" );
			System.out.println("standardisedAddress>>>"+standardisedAddress);
			String streetAddress = prevValue.split(":")[0].split("_")[1];
			getStartedDataMap.put("My current address is", prevValue.replace(streetAddress, standardisedAddress));
			standardisedAddress = "";
		}
}

	static String Firstname = "";
	public static boolean isCPS;
	private void comparingTwoHashMap(HashMap<String, String> dataFromUI, HashMap<String, String> encompassDataMap)
			throws TwfException {
		// TODO Auto-generated method stub
		System.out.println("enterd compared t2 hashmap");
		if (dataFromUI != null && encompassDataMap != null) { // &&
																// dataFromUI.size()
																// ==
																// encompassDataMap.size()
			for (Map.Entry m : dataFromUI.entrySet()) {
				String keyFromFirstMap = (String) m.getKey();
				System.out.println("Key From UI----->" + keyFromFirstMap);
				String valueFromFirstMap = (String) m.getValue();
				System.out.println("Value from UI--->" + valueFromFirstMap);
				String valueFromSecondMap = encompassDataMap.get(keyFromFirstMap);
				System.out.println("Value from encompass-->" + valueFromSecondMap);

				if (keyFromFirstMap.equalsIgnoreCase("My service status")
						|| keyFromFirstMap.equalsIgnoreCase("My branch of service is")) {
					valueFromFirstMap = valueFromFirstMap.replaceAll(" ", "").replace("s", "S").trim();
				}
				if (keyFromFirstMap.equalsIgnoreCase("I was born on")
						|| keyFromFirstMap.equalsIgnoreCase("I have been in this business since")
						|| keyFromFirstMap.equalsIgnoreCase("To")) {
					valueFromSecondMap = valueFromSecondMap.substring(0, 10);
					System.out.println("valueFromSecondMap:" + valueFromSecondMap);
				}
				if (keyFromFirstMap.equalsIgnoreCase("Monthly earnings")
						|| keyFromFirstMap.equalsIgnoreCase("Estimated Property Value")
						|| keyFromFirstMap.equalsIgnoreCase("Monthly payment")
						|| keyFromFirstMap.equalsIgnoreCase("Amount")
						|| keyFromFirstMap.equalsIgnoreCase("Monthly mortgage payments")
						|| keyFromFirstMap.equalsIgnoreCase("Property value")
						|| keyFromFirstMap.equalsIgnoreCase("Outstanding balance")) {
					valueFromSecondMap = valueFromSecondMap.replace(".00", "");
				}

				if (keyFromFirstMap.equalsIgnoreCase("My property is a")) {
					valueFromFirstMap = valueFromFirstMap.replaceAll(" ", "");
				}

				if (valueFromFirstMap.equalsIgnoreCase("Yes")) {
					valueFromFirstMap = "Y";
				} else if (valueFromFirstMap.equalsIgnoreCase("No")) {
					valueFromFirstMap = "N";
				}
				if (keyFromFirstMap.equalsIgnoreCase("Mobile number")
						|| keyFromFirstMap.equalsIgnoreCase("Home phone (Optional)")) {
					valueFromFirstMap = valueFromFirstMap.replaceAll("-", "");
				}
				if (keyFromFirstMap.equalsIgnoreCase("I will use this property as")
						&& valueFromFirstMap.equalsIgnoreCase("A vacation home")) {
					valueFromFirstMap = "SecondHome";
				}
				if (keyFromFirstMap
						.equalsIgnoreCase("Do you intend to occupy the property as your primary residence?")) {
					valueFromFirstMap = "No";
				}
				if (keyFromFirstMap.equalsIgnoreCase("My asset type")) {
					valueFromFirstMap = valueFromFirstMap + "Account";
					System.out.println("valueFromFirstMap: " + valueFromFirstMap);
				}
				if (!keyFromFirstMap.equalsIgnoreCase("Extension")
						&& !keyFromFirstMap.equalsIgnoreCase("Source of Income")
						&& !keyFromFirstMap.equalsIgnoreCase("ZipCode")) {
					System.out.println("entered askjal");
					if (!valueFromFirstMap.equals(valueFromSecondMap)) {
						addExceptionToReport("Data didnot match between UI and Encompass", valueFromFirstMap,
								valueFromSecondMap);
					}
				}
			}
		}
		System.out.println("Out of loop");

	}

	public HashMap<String, String> buildExpectedDataMap(String quesAnsString1) {

		String[] expData1 = quesAnsString1.split("&&");
		HashMap<String, String> dataMapfromUI = new HashMap<String, String>();
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
						System.out.println("Data>>>>" + data1);
					dataMapfromUI.put(key, data1.split("%")[1].split("_")[1].trim());
				}
			} else {
				dataMapfromUI.put(data.split(";")[0].trim(), data.split(";")[1].trim());
			}

		}
		return dataMapfromUI;

	}


	/**
	 * Method Name: validateQuestionAnswerIn1003CoApplicant Purpose:
	 * 
	 * @throws Exception
	 */

	public void validateQuestionAnswerIn1003CoApplicant() throws InterruptedException, TwfException, Exception {
		System.out.println("****************inside  coapplicant***************************");
		Thread.sleep(8000);
		String quesAnsString = step.getDataValue("coapplicant");
		isCoApplicant=true;
		System.out.println("quesAnsString for coapplicant -> " + quesAnsString);
		utill.click(getElementByUsing("1003_GetStarted_YesIAmbutton"));

		if (quesAnsString.contains("//")) {
			String[] coapplicantdata = quesAnsString.trim().split("//");
			System.out.println(coapplicantdata.toString());
			System.out.println("coapplicantdata.length: " + coapplicantdata.length);
			for (int index = 0; index < coapplicantdata.length; index++) {
				getStartedCoAppDataMap = validateQuestionAnswerIn1003(coapplicantdata[index]);
				System.out.println("came out from filling data method");
	
				Thread.sleep(5000);
				// tenothreesectionsummary.validateCoapplicantGetStartedSummaryPage(dataMap,
				// index + 1, fieldMap);
			}
		} else {
			System.out.println(" Single Co-Applicant");
			getStartedCoAppDataMap=validateQuestionAnswerIn1003(quesAnsString);
		}
		isCoApplicant=false;
	}

	/**
	 * Method Name: validateQuestionAnswerIn1003 Purpose:
	 * 
	 * @throws Exception
	 */
	public HashMap<String, String> validateQuestionAnswerIn1003(String quesAnsString)
			throws InterruptedException, TwfException, Exception {
		HashMap<String, String> dataMap = new HashMap<String, String>();
		dataMap = buildDataMap(quesAnsString);
		HashMap<String, String> reBuildDataMap = selectQuestion2AnswerIn1003(dataMap);
		if (status.contains("false")) {
			addExceptionToReport("Expected Question in not enabled.Hence cannot move forward.", "", status);

		}

		// test code
		return reBuildDataMap;
	}

	/**
	 * Method Name: selectQuestion2AnswerIn1003 Purpose:
	 * 
	 * @throws Exception
	 */

	public HashMap<String, String> selectQuestion2AnswerIn1003(HashMap<String, String> expectedMap)
			throws InterruptedException, Exception {
		WebDriver driver = DriverFactory.getDriver();

		HashMap<String, String> expectedSelectAttributeMap = new HashMap<String, String>();
		// String status = "";
		expMapSize = expectedMap.size();
		int QusIndexlength = expectedMap.size();
		int blankCount = 0;
		String attribute = null;
		System.out.println("QusIndexlength size is  " + QusIndexlength);
//		if(incompleSection==true)
//			for (int qusIndex = lastIndex+1; qusIndex < QusIndexlength+lastIndex+1; qusIndex++) {
//				try {
//					WebDriverWait wait = new WebDriverWait(driver, 60);
//					scrollTillActiveQtnIsDisplayed(driver, qusIndex);
//	
//					wait.until(ExpectedConditions.visibilityOf(
//							driver.findElement(By.xpath(fieldAtttibute.replace("%s", String.valueOf(qusIndex))))));
//					attribute = driver.findElement(By.xpath(fieldAtttibute.replace("%s", String.valueOf(qusIndex))))
//							.getAttribute("data-type").trim();
//					String qustion = driver.findElement(By.xpath(Qustion.replace("%s", String.valueOf(qusIndex)))).getText()
//							.trim();
//					System.out.println("qustion>>>>>"+qustion);
//					System.out.println("attribute>>>>>"+attribute);
//					expectedSelectAttributeMap = selectAttribute(driver, attribute, qusIndex, expectedMap, qustion);
//					
//				} catch (Exception e) {
//					System.out.println(" Caught Exception " + e);
//					status = "false";
//	
//					System.out.println(" e.printStackTrace();");
//					e.printStackTrace();
//					addExceptionToReport(" Expected question is not visible hence can not move forward    ", " ", " ");
//	
//				}
//		
//			status = "true";
//			}
//		else
		for (int qusIndex = 0; qusIndex < expMapSize; qusIndex++) {
//			int lastindex=0;
				System.out.println("inside else of complete section>>>>");
				try {
					WebDriverWait wait = new WebDriverWait(driver, 100);
					scrollTillActiveQtnIsDisplayed(driver, qusIndex);
					if(qusIndex==2 && scrollRequired==true){
						clickOnOkButtonAfterEachQuestion(driver, 0, expectedMap.size());
						utill.scrollToElement(driver.findElement(By.xpath(inactiveQtnIndex.replace("%s", String.valueOf(qusIndex)))));
						scrollRequired=false;
					}
//					if(isPrefilled==true){
//						System.out.println("Inside isPrefilled ))))((((!!!!");
//						scrollToCheckfilledValues(driver, qusIndex);
//					}
					wait.until(ExpectedConditions.visibilityOf(
							driver.findElement(By.xpath(fieldAtttibute.replace("%s", String.valueOf(qusIndex))))));
					attribute = driver.findElement(By.xpath(fieldAtttibute.replace("%s", String.valueOf(qusIndex))))
							.getAttribute("data-type").trim();
					String qustion = driver.findElement(By.xpath(Qustion.replace("%s", String.valueOf(qusIndex)))).getText()
							.trim();
					System.out.println("qustion>>>>>"+qustion);
					System.out.println("attribute>>>>>"+attribute);
					if (qustion.trim().equalsIgnoreCase("Provide gift details")
							|| qustion.trim().equalsIgnoreCase("Details of other loan")
							|| qustion.trim().equalsIgnoreCase("Details of the new loan or existing line of credit")) {
						System.out.println("inside gift>>>");
//						if (expectedMap.containsKey("Provide gift details"))
//							expectedMap.remove("Provide gift details");
//					if (expectedMap.containsKey("Details of the new loan"))
//						expectedMap.remove("Details of the new loan");
						qusIndex = addMultipleValues(driver, qustion, expectedMap.get(qustion), qusIndex,
								expMapSize);
						System.out.println("Cameo utside>>ss");
					} else if (qustion.trim().equalsIgnoreCase("Upload gift explanation letter")) {
						System.out.println("Inside else if of gift");
						giftLetterUploadDocs(driver);
						clickOnOkButtonAfterEachQuestion(driver, qusIndex, expMapSize);
						System.out.println("Qtnafterchange>>"+qusIndex);
					} //Details of the new loan or existing line of credit

					else{
//					System.out.println("QtnIndex befores el,ect"+qusIndex+"Question:"+qustion+"expectedMaplengh"+expectedMap);
					expectedSelectAttributeMap = selectAttribute(driver, attribute, qusIndex, expectedMap, qustion);
					}
					System.out.println("Property Data Map>>>>"+Ten0Three.propertydataMap);
				} catch (Exception e) {
					
					status = "false";
	
					System.out.println(" e.printStackTrace();"+expectedMap);
					if(expectedMap.get("My relationship with co-borrower is").equalsIgnoreCase("Spouse")){
						System.out.println("Skip this question");
						scrollTillActiveQtnIsDisplayed(driver, qusIndex+1);
						break;
					}
					else{
					System.out.println(" Caught Exception " + e);
					e.printStackTrace();
					addExceptionToReport(" Expected question is not visible hence can not move forward    ", " ", " ");
					}
				}
		
			status = "true";
			lastIndex=qusIndex;
			System.out.println("\n INDEXXXXX>>>>>"+qusIndex);
	//		utill.scrollToElement(driver.findElement(By.xpath(Qustion.replace("%s", String.valueOf(qusIndex+1)))));
	//		System.out.println("Scrolled to question index>>>"+driver.findElement(By.xpath(Qustion.replace("%s", String.valueOf(qusIndex)))).getText());
		}

		System.out.println("status = " + status);
		status = "true";
		return expectedSelectAttributeMap;

	}

	
		
	
	private boolean scrollTillActiveQtnIsDisplayed(WebDriver driver, int qusIndex) throws Exception {
		boolean isDisplayed = false;
		try {
			if (driver.findElement(By.xpath(activeQtnIndex.replace("%s", String.valueOf(qusIndex)))).isDisplayed()) {
				System.out.println("active qtn is displayed");

				return true;
			}

		} catch (Exception e) {
			while (isDisplayed == false) {
				String qtn = "//div[@class='form-container tf-block-%s']//form";
				String label=qtn+"//label";
				System.out.println("for disabled qtn>>>>"
						+ driver.findElement(By.xpath(qtn.replace("%s", String.valueOf(qusIndex)))).getText());
				System.out.println("qtn label for disabled qtn>>>>"
						+ driver.findElement(By.xpath(label.replace("%s", String.valueOf(qusIndex)))).getText());
				Actions action = new Actions(driver);
				 
				if(driver.findElement(By.xpath(label.replace("%s", String.valueOf(qusIndex)))).getText().trim().equalsIgnoreCase("I want to do a joint credit pull".trim())){
					System.out.println("Inside I want to do>>");
	//				action.moveToElement(driver.findElement(By.xpath(qtn.replace("%s", String.valueOf(qusIndex))))).doubleClick().build().perform();
					utill.scrollToElement(driver.findElement(By.xpath(label.replace("%s", String.valueOf(qusIndex)))));
					System.out.println("Moved>>");
					break;
				}
				else{
				action.moveToElement(driver.findElement(By.xpath(qtn.replace("%s", String.valueOf(qusIndex)))))
						.doubleClick().build().perform();
				System.out.println("Action double clicked");
				}
				isDisplayed = true;
			}

			// div[@class[contains(.,'form-container tf-block-12
			// active')]]//label/following-sibling::dynamic-form/form

		}
		return true;
	}

	private boolean scrollToCheckfilledValues(WebDriver driver, int qusIndex) throws Exception {
		boolean isDisplayed = false;
		Util util = new Util();
//		qusIndex=0;
		try {
			String qtn = "//div[@class='form-container tf-block-%s']//form";
			util.scrollToElement(driver.findElement(By.xpath(qtn.replace("%s", String.valueOf(qusIndex)))));
			if (driver.findElement(By.xpath(activeQtnIndex.replace("%s", String.valueOf(qusIndex)))).isDisplayed()) {
				System.out.println("active qtn is displayed");
				return true;
			}

		} catch (Exception e) {
			while (isDisplayed == false) {
				String qtn = "//div[@class='form-container tf-block-%s']//form";
				System.out.println("qtn label for disabled qtn>>>>"+driver.findElement(By.xpath(qtn.replace("%s", String.valueOf(qusIndex)))).getText());
				Actions action = new Actions(driver);
				action.moveToElement(driver.findElement(By.xpath(qtn.replace("%s", String.valueOf(qusIndex)))))
						.doubleClick().build().perform();
				
				System.out.println("Action double clicked");

				isDisplayed = true;
			}

			// div[@class[contains(.,'form-container tf-block-12
			// active')]]//label/following-sibling::dynamic-form/form

		}
		return true;
	}
	
	/**
	 * Method Name: validationCheckIn1003 Purpose:
	 * 
	 * @throws Exception
	 */

	public void validationCheckIn1003() throws InterruptedException, TwfException, Exception {

		String quesAnsString = step.getDataValue("question_ans");
		isNewUser = step.getDataValue("NewUser");

		HashMap<String, String> dataMap = new HashMap<String, String>();
		dataMap = buildDataMap(quesAnsString);
		System.out.println("after datamap");
		String str = validateNegativeFlowTenOThree(dataMap);

		// System.out.println("str value is " + str);

		String expectedErrorMessage = "Invalid Input";
		String requiredFieldValidation = "Required Field";
		if (str.contains("false")) {

			if (str.split(";")[1].trim().equalsIgnoreCase(expectedErrorMessage) == false) {
				addExceptionToReport("Expected Error Message is not   displayed", str.split(";")[1].trim(),
						expectedErrorMessage);
			} else if (str.split(";")[1].trim().equalsIgnoreCase(requiredFieldValidation) == true) {
				addExceptionToReport("required Field Validation is not dispalyed  ", str.split(";")[1].trim(),
						requiredFieldValidation);

			}

		}
	}

	/**
	 * Method Name: validateNegativeFlowTenOThree Purpose: To cheeck for field
	 * validation errors
	 * 
	 * @throws Exception
	 */

	public String validateNegativeFlowTenOThree(HashMap<String, String> expectedMap)
			throws InterruptedException, Exception {

		WebDriver driver = DriverFactory.getDriver();
		String status = "";

		int QusIndexlength = expectedMap.size();
		int blankCount = 0;
		System.out.println("QusIndexlength size is  " + QusIndexlength);
		for (int qusIndex = 0; qusIndex < QusIndexlength; qusIndex++) {

			if (driver.findElements(By.xpath(Qustion.replace("%s", String.valueOf(qusIndex)))).size() > 0) {

				String attribute = driver.findElement(By.xpath(fieldAtttibute.replace("%s", String.valueOf(qusIndex))))
						.getAttribute("data-type").trim();

				try {
					String qustion = driver.findElement(By.xpath(Qustion.replace("%s", String.valueOf(qusIndex))))
							.getText().trim();
					selectAttribute(driver, attribute, qusIndex, expectedMap, qustion);
				} catch (Exception e)

				{
					System.out.println(" Caught Exception " + e);
					addExceptionToReport("Test Data Error",
							" Given  Test Data is not correct for attribute  " + attribute + " Exception is --> " + e,
							"Test Data should be  given properly ");

					/*
					 * System.out.println(" blankCount  -- " + blankCount);
					 * blankCount++; selectAttribute(driver, attribute,
					 * qusIndex, expectedMap,"blank"+blankCount);
					 */
				}

				status = "true";
			}

			else {
				System.out.println(" entered else loop .............");

				if (driver.findElements(By.xpath(fieldValidationError)).size() > 0) {
					status = "false;" + driver.findElement(By.xpath(".//*[@class='error']")).getText().trim();
					System.out.println(driver.findElement(By.xpath(".//*[@class='error']")).getText());
					System.out.println("status is ----------------------------> " + status);
				}

				break;

			}

		}

		return status;

	}

	public String verifyQuestionOrdering(HashMap<String, String> expectedMap, HashMap<Integer, String> SequenceMap,
			HashMap<String, String> HelpTextMap) throws InterruptedException, Exception {
		WebDriver driver = DriverFactory.getDriver();
		String status = "";

		int QusIndexlength = expectedMap.size();
		int sequenceMapLength = SequenceMap.size();
		int HelpTextMapLength = HelpTextMap.size();

		for (int qusIndex = 0; qusIndex < sequenceMapLength; qusIndex++) {

			System.out.println("------------------------------");
			System.out.println("QusIndex1  is --->" + qusIndex);
			String attribute = driver.findElement(By.xpath(fieldAtttibute.replace("%s", String.valueOf(qusIndex))))
					.getAttribute("data-type");

			String question = driver.findElement(By.xpath(Qustion.replace("%s", String.valueOf(qusIndex)))).getText()
					.trim();
			String actualHelpText = driver.findElement(By.xpath(helpText.replace("%s", String.valueOf(qusIndex))))
					.getText().trim();
			String expectedHelpText = HelpTextMap.get(question).trim();

			System.out.println("actualHelpText  is   --> " + actualHelpText);
			System.out.println("expectedHelpText  is --> " + expectedHelpText);

			if (!(actualHelpText.contains(expectedHelpText))) {
				addExceptionToReport("Verifiation of HELP Text for question " + question + " failed.", actualHelpText,
						expectedHelpText);
			}

			String expectedValue = SequenceMap.get(qusIndex);
			System.out.println("question  is   --> " + question);
			System.out.println("expected Question is --> " + expectedValue);

			if (!(expectedValue.contains(question))) {
				addExceptionToReport("Expected Question is not displayed in index " + qusIndex, expectedValue,
						question);
			}

			selectAttribute(driver, attribute, qusIndex, expectedMap, question);
			status = "true";
		}
		return status;
	}

	/**
	 * Method Name: checkDependent Purpose: Add number of dependents for the
	 * Enter dependent(s) age question
	 * 
	 * @param driver
	 * 
	 * @throws Exception
	 */

	private void addDependents(WebDriver driver, String qustion, String dependent_ans)
			throws BiffException, IOException, TwfException {
		System.out.println("inside checkDependent");
		if (dependent_ans.contains(":")) {
			String[] dependent_Age = dependent_ans.split(":");
			int dependent_Age_length = dependent_Age.length;
			while (dependent_Age_length > 1) {
				getElementByUsing("FinExp_100_AddDependent_Button").click();

				dependent_Age_length--;
			}

			/*
			 * if(dependent_Age_length>2) { //
			 * driver.findElement(By.xpath(dependentDeleteIcon.replace("%s",
			 * String.valueOf(dependent_Age_length-1)))).click(); }
			 */

		}

	}

	/**
	 * Method Name: getMonths Purpose: getting months detsils from given test
	 * data. Test Data - My current address is;ta%Street address line 1_abc
	 * koramangala: ta%Street address line 2_abc Koramangala:
	 * dd%State_Arizona:dd%City_Ajo:ta%Zipcode_12345:ta%Years_2:ta%Months_3:CB%This
	 * is my mailing address_This is my mailing address
	 * 
	 * @param driver
	 * 
	 * @throws Exception
	 */

	private int getMonths(WebDriver driver, String qustion, String ans) {
		int years = Integer
				.parseInt((ans.substring(ans.lastIndexOf("Years_") + 6, ans.lastIndexOf(":ta%Months")).trim()));
		int months = 0;
		if (ans.contains("CB%")) {

			months = Integer.parseInt((ans.substring(ans.lastIndexOf("Months_") + 7, ans.lastIndexOf(":CB%")).trim()));
		} else {
			months = Integer.parseInt(ans.substring(ans.lastIndexOf("Months_") + 7).trim());
		}

		return (years * 12) + months;
	}

	/**
	 * Method Name: selectAttribute Purpose: fill questions and answer based on
	 * there attribute type
	 * 
	 * @param driver
	 * 
	 * @throws Exception
	 */

	public HashMap<String, String> selectAttributeOld(WebDriver driver, String attribute, int qusIndex,
			HashMap<String, String> expectedMap, String qustion) throws Exception {

		System.out
				.println("attribute is --> " + attribute + "  -- qustion is --> " + qustion + " index is -" + qusIndex);
		System.out.println("ans --" + expectedMap.get(qustion));
		String State = null, City = null;
		// int months = 0;
		String address_Ans = null;
		try {
			if (qustion.contains("Enter dependent(s) age")) {
				addDependents(driver, qustion, expectedMap.get(qustion));
			}
			if (qustion.contains("My current address is")) {
				System.out.println(" My current address is " + months);
				months = getMonths(driver, qustion, expectedMap.get(qustion));
				System.out.println("months are " + months);
			}

			if (qustion.contains("My previous address was")) {
				System.out.println("months before previous address are " + months);
				months = months + getMonths(driver, qustion, expectedMap.get(qustion));
				System.out.println("months after previous address was   are " + months);

			}
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("exception is -> " + e);
		}

		if (attribute.equalsIgnoreCase("radioBtn")) {

			String val = String.valueOf((String) expectedMap.get(qustion));

			System.out.println(" inside " + attribute);
			String radioans = expectedMap.get(qustion);
			System.out.println("radioans " + radioans);
			String[] sub_fields = radioans.split(":");

			System.out.println("sub_fields.length ----   " + sub_fields.length);
			if (sub_fields.length == 1) {

				if (!(driver
						.findElements(
								By.xpath(radioButtonAns.replace("%s", String.valueOf(qusIndex)).replace("%v", val)))
						.size() > 0)) {
					addExceptionToReport("Given Option is not available for " + qustion, val, " ");
				}

				driver.findElement(By.xpath(radioButtonAns.replace("%s", String.valueOf(qusIndex)).replace("%v", val)))
						.click();
			} else {
				for (int sub_field_index = 0; sub_field_index < sub_fields.length; sub_field_index++) {

					// System.out.println( " sub_field_index "+
					// sub_fields[sub_field_index]);
					String[] field_type = (sub_fields[sub_field_index]).trim().split("%");
					// System.out.println("field_type " + field_type[0] + " and
					// " + field_type[1]);

					if ((field_type[0].trim()).equalsIgnoreCase("ta")) {

						// System.out.println( " Inside TA ... ");
						// System.out.println("inside ta " + field_type[1] );
						String[] placeholder_value = (field_type[1].trim()).split("_");
						String placeholder = placeholder_value[0].trim();
						String value = placeholder_value[1];
						driver.findElement(By.xpath(sub_ans_text.replace("%s", String.valueOf(qusIndex))
								.replace("%placeholder", String.valueOf(placeholder)))).click();
						driver.findElement(By.xpath(sub_ans_text.replace("%s", String.valueOf(qusIndex))
								.replace("%placeholder", String.valueOf(placeholder)))).sendKeys(value);
						driver.findElement(By.xpath(sub_ans_text.replace("%s", String.valueOf(qusIndex))
								.replace("%placeholder", String.valueOf(placeholder)))).sendKeys(Keys.ARROW_UP);

					} else if ((field_type[0].trim()).equalsIgnoreCase("RB")) {

						System.out.println("inside RB............ " + field_type[1]);

						if (!(driver.findElements(By.xpath(
								radioButtonAns.replace("%s", String.valueOf(qusIndex)).replace("%v", field_type[1])))
								.size() > 0)) {
							addExceptionToReport("Given Option is not available for " + qustion, val, " ");
						}

						System.out.println(" check 1 " + qusIndex + "   " + field_type[1]);
						driver.findElement(By.xpath(
								radioButtonAns.replace("%s", String.valueOf(qusIndex)).replace("%v", field_type[1])))
								.click();

					}

				}

				driver.findElement(By.xpath(OkayBUtton1.replace("%s", String.valueOf(qusIndex)))).click();

			}
		}

		else if (attribute.equalsIgnoreCase("currency")) {

			String currencyans = expectedMap.get(qustion);
			System.out.println("currencyans " + currencyans);
			String[] sub_fields = currencyans.split(":");

			System.out.println("sub_fields.length ----   " + sub_fields.length);

			if (sub_fields.length == 1) {

				driver.findElement(By.xpath(inputText.replace("%s", String.valueOf(qusIndex)))).clear();
				driver.findElement(By.xpath(inputText.replace("%s", String.valueOf(qusIndex)))).sendKeys(currencyans);
				// driver.findElement(By.xpath(OkayBUtton1.replace("%s",
				// String.valueOf(qusIndex)))).click();
			} else {
				for (int sub_field_index = 0; sub_field_index < sub_fields.length - 1; sub_field_index++) {

					// System.out.println( " sub_field_index "+
					// sub_fields[sub_field_index]);
					String[] field_type = (sub_fields[sub_field_index]).trim().split("%");
					// System.out.println("field_type " + field_type[0] + " and
					// " + field_type[1]);

					if ((field_type[0].trim()).equalsIgnoreCase("ta")) {

						// System.out.println( " Inside TA ... ");
						// System.out.println("inside ta " + field_type[1] );
						String[] placeholder_value = (field_type[1].trim()).split("_");
						String placeholder = placeholder_value[0].trim();
						String value = placeholder_value[1];
						driver.findElement(By.xpath(sub_ans_text.replace("%s", String.valueOf(qusIndex))
								.replace("%placeholder", String.valueOf(placeholder)))).click();
						driver.findElement(By.xpath(sub_ans_text.replace("%s", String.valueOf(qusIndex))
								.replace("%placeholder", String.valueOf(placeholder)))).clear();
						driver.findElement(By.xpath(sub_ans_text.replace("%s", String.valueOf(qusIndex))
								.replace("%placeholder", String.valueOf(placeholder)))).sendKeys(value);
						driver.findElement(By.xpath(sub_ans_text.replace("%s", String.valueOf(qusIndex))
								.replace("%placeholder", String.valueOf(placeholder)))).sendKeys(Keys.ARROW_UP);

					}

				}
			}
			//

			/*
			 * driver.findElement(By.xpath(inputText.replace("%s",
			 * String.valueOf(qusIndex)))).clear();
			 * driver.findElement(By.xpath(inputText.replace("%s",
			 * String.valueOf(qusIndex)))).sendKeys(expectedMap.get(qustion));
			 * driver.findElement(By.xpath(inputText.replace("%s",
			 * String.valueOf(qusIndex)))).sendKeys(Keys.ARROW_UP);
			 */

			// Summary wait
			Thread.sleep(3000);
			driver.findElement(By.xpath(OkayBUtton1.replace("%s", String.valueOf(qusIndex)))).click();

			if (qustion.contains("I can put a down payment of")) {
				System.out.println(" inside  down payment condition     ");
				driver.findElement(By.xpath(OkayBUtton1.replace("%s", String.valueOf(qusIndex)))).click();
			}
		}

		else if ((attribute.equalsIgnoreCase("checkbox")) || (attribute.equalsIgnoreCase("text"))
				|| (attribute.equalsIgnoreCase("select")) || (attribute.equalsIgnoreCase("phone"))
				|| (attribute.equalsIgnoreCase("ssnField")) || (attribute.equalsIgnoreCase("datepicker"))
				|| (attribute.equalsIgnoreCase("fromto")) || (attribute.equalsIgnoreCase("location"))) {

			System.out.println(" inside " + attribute);

			String textans = expectedMap.get(qustion);
			if (attribute.equalsIgnoreCase("text") && qustion.equalsIgnoreCase("Provide spouse details")) {
				System.out.println("eneterd");
				driver.switchTo().activeElement();
				driver.findElement(By.xpath("(//button[@id='btn_confirm_modal_Yes'])[2]")).click();
				Thread.sleep(5000);
				driver.switchTo().defaultContent();

			}

			System.out.println("textans " + textans);

			String[] sub_fields = textans.split(":");

			// System.out.println("sub_fields length " + sub_fields.length);
			int zipindex = 0;
			for (int sub_field_index = 0; sub_field_index < sub_fields.length; sub_field_index++) {

				// System.out.println( " sub_field_index "+
				// sub_fields[sub_field_index]);
				String[] field_type = (sub_fields[sub_field_index]).trim().split("%");
				System.out.println("field_type " + field_type[0] + " and " + field_type[1]);

				if ((field_type[0].trim()).equalsIgnoreCase("ta")) {
					// System.out.println("inside ta " + field_type[1] );
					String[] placeholder_value = (field_type[1].trim()).split("_");
					String placeholder = placeholder_value[0].trim();
					String value = placeholder_value[1];

					if (placeholder.equalsIgnoreCase("Zipcode")) {

						System.out.println(" inside Zip ");
						driver.findElement(By.xpath(sub_ans_text.replace("%s", String.valueOf(qusIndex))
								.replace("%placeholder", String.valueOf(placeholder)))).clear();
						driver.findElement(By.xpath(sub_ans_text.replace("%s", String.valueOf(qusIndex))
								.replace("%placeholder", String.valueOf(placeholder)))).sendKeys(value);
						// utill.waitForElements(driver.findElement(By.xpath(".//*[@class='loader-holder']/p")));

						// driver.findElement(By.xpath(sub_ans_text.replace("%s",
						// String.valueOf(qusIndex)).replace("%placeholder",
						// String.valueOf(placeholder)))).sendKeys(Keys.TAB);
						Thread.sleep(10000);

						State = driver.findElement(By.xpath("(.//*[@class='zipcode-information']//button)[1]"))
								.getAttribute("value");
						City = driver.findElement(By.xpath("(.//*[@class='zipcode-information']//button)[2]"))
								.getAttribute("value");

						if (qustion.contains("My current address is")) {

							address_Ans = expectedMap.get("My current address is");

							System.out.println("address_Ans are before state and city " + address_Ans);
							expectedMap.put("My current address is", "ta%State_" + State + ":ta%City_" + City);
						} else if (qustion.contains("The property is located at")) {
							expectedMap.put("The property is located at", "ta%State_" + State + ":ta%City_" + City);
						}

					} else {

						if (qustion.contains("My current address is") && (!(placeholder.equalsIgnoreCase("Years")))
								&& (!(placeholder.equalsIgnoreCase("Months")))) {

							address_Ans = expectedMap.get("My current address is");

							System.out
									.println("address_Ans for My current address is  if  placeholder is not  zipcode  "
											+ address_Ans);
						} else if (qustion.contains("My mailing address is")) {
							address_Ans = expectedMap.get("My mailing address is");
							System.out.println("address_Ans for My current address is   " + address_Ans);
						}
						driver.findElement(By.xpath(sub_ans_text.replace("%s", String.valueOf(qusIndex))
								.replace("%placeholder", String.valueOf(placeholder)))).click();
						driver.findElement(By.xpath(sub_ans_text.replace("%s", String.valueOf(qusIndex))
								.replace("%placeholder", String.valueOf(placeholder)))).clear();
						driver.findElement(By.xpath(sub_ans_text.replace("%s", String.valueOf(qusIndex))
								.replace("%placeholder", String.valueOf(placeholder)))).sendKeys(value);
						driver.findElement(By.xpath(sub_ans_text.replace("%s", String.valueOf(qusIndex))
								.replace("%placeholder", String.valueOf(placeholder)))).sendKeys(Keys.ARROW_UP);

					}

					if (placeholder.equalsIgnoreCase("Account with")) {
						System.out.println("placeholder  is  Account with");
						driver.findElement(By.xpath(sub_ans_text.replace("%s", String.valueOf(qusIndex))
								.replace("%placeholder", String.valueOf(placeholder)))).sendKeys(Keys.TAB);

					}

				} else if ((field_type[0].trim()).equalsIgnoreCase("dd")) {
					System.out.println("inside dd -- " + field_type[1]);
					String[] placeholder_value = field_type[1].split("_");
					String placeholder = placeholder_value[0];
					String value = placeholder_value[1];
					driver.findElement(By.xpath(sub_ans_dropdown.replace("%s", String.valueOf(qusIndex)).replace("%t",
							String.valueOf(placeholder)))).click();
					zipindex++;
					driver.findElement(By.xpath(sub_ans_dropdown_search.replace("%s", String.valueOf(qusIndex))
							.replace("%t", String.valueOf(zipindex)))).sendKeys(value);
					// driver.findElement(By.xpath(sub_ans_dropdown.replace("%s",
					// String.valueOf(qusIndex)).replace("%t",
					// String.valueOf(placeholder)))).sendKeys(value);

					// clickOnElementWhenElementIsReady(driver.findElement(By.xpath(sub_ans_dropdown.replace("%s",
					// String.valueOf(qusIndex)).replace("%t",
					// String.valueOf(placeholder)))));

					// driver.findElement(By.xpath(sub_ans_dropdown.replace("%s",
					// String.valueOf(qusIndex)).replace("%t",
					// String.valueOf(placeholder)))).click();
					clickOnElementWhenElementIsReady(driver
							.findElement(By.xpath(sub_ans_dropdown_value.replace("%val", String.valueOf(value)))));

					// driver.findElement(By.xpath(sub_ans_dropdown_value.replace("%val",
					// String.valueOf(value)))).click();

				} else if ((field_type[0].trim()).equalsIgnoreCase("cb")) {
					// System.out.println("inside cc -- " + field_type[1] );
					String[] placeholder_value = field_type[1].split("_");
					String placeholder = placeholder_value[0];
					String value = placeholder_value[1].trim();

					System.out.println("placeholder " + placeholder + "value - " + value);
					driver.findElement(By.xpath(sub_ans_checkbox.replace("%labeltext", placeholder))).click();
					// driver.findElement(By.xpath(sub_ans_checkbox.replace("%labeltext",
					// placeholder))).click();

				} else if ((field_type[0].trim()).equalsIgnoreCase("RB")) {

					// System.out.println("inside RB -- " + field_type[1] );
					String[] placeholder_value = field_type[1].split("_");
					String placeholder = placeholder_value[0];
					String value = placeholder_value[1].trim();
					driver.findElement(By.xpath(sub_ans_radiobutton.replace("%s", String.valueOf(qusIndex))
							.replace("%radioButtonName", value))).click();
					Thread.sleep(1000);

				} else if ((field_type[0].trim()).equalsIgnoreCase("DatePicker")
						|| (field_type[0].trim()).equalsIgnoreCase("fromto")) {

					System.out.println("inside DatePicker  -- " + field_type[1]);
					String[] placeholder_value = field_type[1].split("_");
					String placeholder = placeholder_value[0];
					String value = placeholder_value[1].trim();

					System.out.println("placeholder " + placeholder + "value - " + value);
					driver.findElement(By.xpath(Sub_ans_datepicker.replace("%datelabel", placeholder))).sendKeys(value);
					driver.findElement(By.xpath(Sub_ans_datepicker.replace("%datelabel", placeholder)))
							.sendKeys(Keys.ARROW_UP);

				}

			}

			// driver.findElement(By.xpath(inputText.replace("%s",
			// String.valueOf(qusIndex)))).sendKeys(expectedMap.get(qustion));

			// Summary wait
			driver.findElement(By.xpath(OkayBUtton1.replace("%s", String.valueOf(qusIndex)))).click();
			Thread.sleep(4000);

			try {

				if (qustion.contains("My current address is") || qustion.contains("My mailing address is")
						|| (qustion.contains("My previous address was"))) {

					System.out.println("qustion before validate address " + qustion);
					System.out.println("answer before validate address " + expectedMap.get(qustion));
					System.out.println("address_Ans before validate address " + address_Ans);

					validateAddress(driver, qustion, qusIndex, expectedMap.get(qustion), months, address_Ans);
				}

			}

			catch (Exception e) {

				e.printStackTrace();
				System.out.println(" error is --> " + e);
			}

		} else if (attribute.equalsIgnoreCase("dateFormat")) {
			System.out.println("inside datepicker" + expectedMap.get(qustion));
			// Thread.sleep(1000);
			// System.out.println("inside datepicker" + expectedMap.get(qustion)
			// );
			driver.findElement(By.xpath(dateOfBirth.replace("%s", String.valueOf(qusIndex)))).click();
			driver.findElement(By.xpath(dateOfBirth.replace("%s", String.valueOf(qusIndex)))).clear();
			driver.findElement(By.xpath(dateOfBirth.replace("%s", String.valueOf(qusIndex))))
					.sendKeys(expectedMap.get(qustion));
			driver.findElement(By.xpath(dateOfBirth.replace("%s", String.valueOf(qusIndex)))).sendKeys(Keys.ARROW_UP);

			// getElementByUsing("1003_GetStarted_datepicker_text").sendKeys(expectedMap.get(qustion));
			Thread.sleep(3000);
			driver.findElement(By.xpath(OkayBUtton1.replace("%s", String.valueOf(qusIndex)))).click();

		}

		else if (attribute.equalsIgnoreCase("zipcode")) {
			String ZipcodeDropDown1 = zipcodeDropDown.replace("%n", String.valueOf(qusIndex));
			String zipcodeDropDownSerach1 = zipcodeDropDownSerach.replace("%n", String.valueOf(qusIndex));
			String zipCodeDropValue1 = zipCodeDropValue.replace("%n", String.valueOf(qusIndex));
			String ans = expectedMap.get(qustion);
			// System.out.println("qustion is " + qustion);
			// System.out.println("ans is " + ans);

			String zipcode[] = ans.split(",");
			int zipcodedatalength = zipcode.length;

			System.out.println("zipcodedatalength " + zipcodedatalength);
			if (zipcodedatalength > 1) {

				System.out.println(" inside if condition + " + zipcodedatalength);
				for (int zipindex = 1; zipindex <= 3; zipindex++) {

					driver.findElement(By.xpath(ZipcodeDropDown1.replace("%s", String.valueOf(zipindex)))).click();
					if (!(zipindex == 3)) {
						driver.findElement(By.xpath(zipcodeDropDownSerach1.replace("%s", String.valueOf(zipindex))))
								.sendKeys(zipcode[zipindex].trim());
					}
					String result = driver
							.findElement(By.xpath(zipCodeDropValue1.replace("%s", String.valueOf(zipindex)))).getText();
					if (result.equalsIgnoreCase("No results found")) {
						addExceptionToReport("Expected Zip Code Value is not displayed in drop down.", result,
								zipcode[zipindex]);
					}

					driver.findElement(By.xpath(zipCodeDropValue1.replace("%s", String.valueOf(zipindex)))).click();
				}
			} else {
				System.out.println("entered zipcode========================");
				// System.out.println("zipcode[0] --> " + zipcode[0] );
				driver.findElement(By.xpath(inputText.replace("%s", String.valueOf(qusIndex)))).clear();
				driver.findElement(By.xpath(inputText.replace("%s", String.valueOf(qusIndex))))
						.sendKeys(zipcode[0].trim());

				System.out.println("zipcode[0].trim()" + zipcode[0].trim());
				// driver.findElement(By.xpath(".//*[@for='getQuote.zipcode']")).click();
				// System.out.println("click 1");
				Thread.sleep(15000);
			}

			System.out.println("out of zipcode ==================");
			Thread.sleep(5000);
			driver.findElement(By.xpath(OkayBUtton2.replace("%s", String.valueOf(qusIndex)))).click();
			Thread.sleep(5000);
			System.out.println("clicked on okay");

			/*
			 * if (!(driver.findElement(By.xpath(OkayBUtton2.replace("%s",
			 * String.valueOf(qusIndex)))).isEnabled())) {
			 * 
			 * System.out.println("OkayBUtton2  is disabled ");
			 * 
			 * }
			 */

			/*
			 * driver.findElement(By.xpath(OkayBUtton2.replace("%s",
			 * String.valueOf(QusIndex1)))).click(); Thread.sleep(50000);
			 */

		}

		return expectedMap;
	}

	/**
	 * Method Name: validateAddress Purpose: to handle all the condition related
	 * to the My current address is questions
	 * 
	 */

	private void validateAddress(WebDriver driver, String qustion, int qusIndex, String answer, int months, String ans)
			throws Exception {
		System.out.println("Inside validateAddress method   ");
		System.out.println("answer --> " + ans);
		System.out.println("ans --> " + ans);
		System.out.println("qustion.contains(My current address is)" + qustion.contains("My current address is"));
		System.out.println("(ans.contains(Zipcode  -- " + (ans.contains("Zipcode")));
		System.out.println("(ans.contains(CB%))   --  " + (ans.contains("CB%")));

		if (qustion.contains("My mailing address is")) {

			getElementByUsing("1003_GetStarted_YesPleaseModelWindow").click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(OkayBUtton1.replace("%s", String.valueOf(qusIndex)))).click();
		}

		if (qustion.contains("My current address is") && (ans.contains("Zipcode") && (ans.contains("CB%")))) {
			getElementByUsing("1003_GetStarted_YesPleaseModelWindow").click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(OkayBUtton1.replace("%s", String.valueOf(qusIndex)))).click();
		}

		if (qustion.contains("My current address is") && (!ans.contains("Zipcode") && (ans.contains("CB%")))) {

			driver.findElement(By.xpath(".//button[contains(.,'Skip ')]")).click();
			driver.findElement(By.xpath(".//button[contains(.,'Keep original address')]")).click();

			// getElementByUsing("1003_GetStarted_YesPleaseModelWindow").click();
			Thread.sleep(2000);
		}

		// // Added below for Address standardization(standardized address) --
		// Sharath
		// else if (qustion.contains("My current address is") &&
		// (!ans.contains("Zipcode")&& (ans.contains("CB%"))) {
		//
		// driver.findElement(By.xpath(".//button[contains(.,'Keep original
		// address')]")).click();
		// // getElementByUsing("1003_GetStarted_YesPleaseModelWindow").click();
		// Thread.sleep(2000);
		//
		// }
		// Added above for Address standardization -- Sharath

		System.out.println("months ARE --> " + months);
		if ((qustion.contains("My current address is") || qustion.contains("My previous address was"))
				&& (months < 24)) {
			Thread.sleep(2000);
			getElementByUsing("FinExp_1003_AddAddressButton").click();
			Thread.sleep(1000);
		}
		System.out.println("validateAddress  method end ");
	}

	/**
	 * Method Name: validateSequentiandHelpTextFlow1003 Purpose: to check for
	 * question answer help text and sequence of the questions
	 * 
	 */

	public void validateSequentiandHelpTextFlow1003() throws InterruptedException, TwfException, Exception {

		WebDriver driver = DriverFactory.getDriver();
		System.out.println(" inside validateSequentiandHelpTextFlow1003 ");
		String autoPromtInfo = step.getDataValue("QuestionAnswer");
		// System.out.println("test data string :" +
		// autoPromtInfo.split("%")[0]);
		HashMap<String, String> dataMap = new HashMap<String, String>();
		dataMap = buildDataMap(autoPromtInfo);

		String HelpText = step.getDataValue("HelpText");
		// System.out.println("HelpText data string :" + HelpText);
		System.out.println("--------------------- ");
		HashMap<String, String> HelpTextMap = new HashMap<String, String>();

		HelpTextMap = buildDataMap(HelpText);
		String SequenceQuestion = step.getDataValue("SequenceQuestion");
		// System.out.println("SequenceQuestion data string : " +
		// SequenceQuestion );

		System.out.println("---------------------");
		HashMap<Integer, String> SequencedataMap = new HashMap<Integer, String>();
		SequencedataMap = buildDataMaporder(SequenceQuestion);

		String str = verifyQuestionOrdering(dataMap, SequencedataMap, HelpTextMap);

		if (str.contains("false")) {
			System.out.println(str);
			addExceptionToReport("Expected Question in not enabled.Hence cannot move forward.", "", str);

		}

	}

	/**
	 * Method Name: validateQuestionAnswerIn1003Liability Purpose: to fill
	 * Liability details
	 * 
	 */

	public void validateQuestionAnswerIn1003Liability() throws InterruptedException, TwfException, Exception {
		System.out.println("inside validateQuestionAnswerIn1003Liability ");

		String LiabilityQuestionAnswer = step.getDataValue("LiabilityQuestionAnswer");
		String Module = step.getDataValue("Module");

		HashMap<String, String> dataMap = validateQuestionAnswerIn1003(LiabilityQuestionAnswer);
		if (Module.equalsIgnoreCase("1003")) {
			System.out.println("Not Prequal");
			// tenothreesectionsummary.validateLiabilitySummaryPage(dataMap);
		} else {
			System.out.println("Prequal");
		}

	}

	/**
	 * Method Name: validateQuestionAnswerIn1003RealEstate Purpose: to fill
	 * RealEstate details
	 * 
	 */

//	public void validateQuestionAnswerIn1003RealEstate() throws InterruptedException, TwfException, Exception {
//		System.out.println("inside validateQuestionAnswerIn1003RealEstate ");
//
//		String realEstate = step.getDataValue("RealEstateQuestionAnswer");
//		String Module = step.getDataValue("Module");
//		System.out.println(" Real estate data -----");
//		utill.click(getElementByUsing("1003_RealEstateButton"));
//		System.out.println(realEstate);
//
//		HashMap<String, String> dataMap = validateQuestionAnswerIn1003(realEstate);
//		if (Module.equalsIgnoreCase("1003")) {
//			System.out.println("Not Prequal");
//			// tenothreesectionsummary.validateRealEstateSummaryPage(dataMap);
//		} else {
//			System.out.println("Prequal");
//		}
//
//	}
	
	public void validateQuestionAnswerIn1003RealEstate() throws InterruptedException, TwfException, Exception {
		System.out.println("inside validateQuestionAnswerIn1003RealEstate ");

		String realEstate = step.getDataValue("RealEstateQuestionAnswer");
		String Module = step.getDataValue("Module");
		System.out.println(" Real estate data -----");
		utill.click(getElementByUsing("1003_RealEstateButton"));
		System.out.println(realEstate);

		HashMap<String, String> dataMap = validateQuestionAnswerIn1003(realEstate);
		if (Module.equalsIgnoreCase("1003")) {
			System.out.println("Not Prequal");
			// tenothreesectionsummary.validateRealEstateSummaryPage(dataMap);
		} else {
			System.out.println("Prequal");
		}

	}

	/**
	 * Method Name: editValidateQuestionAnswerIn1003RealEstate Purpose: to edit
	 * RealEstate details
	 * 
	 */

	/**
	 * Method Name: validatePropertyQuestionAnswerIn1003 Purpose: to fill
	 * property details
	 * 
	 */

	public void validatePropertyQuestionAnswerIn1003() throws InterruptedException, TwfException, Exception {
		System.out.println("inside validatePropertyQuestionAnswerIn1003 ");
		WebDriver driver=DriverFactory.getDriver();
		
		/*JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,0)", "");*/
		Util util = new Util();
		util.scrollToElement(driver
				.findElement(By.xpath("//label[text()='Estimated Property Value']")));
		if(!step.getDataValue("Rate Quote Validation Questions").isEmpty()){
			System.out.println("Enter Rate Quote Validation Questions map build");
			String[] rateQuoteQuestions=step.getDataValue("Rate Quote Validation Questions").split("&&");
			for(String rateQuestion:rateQuoteQuestions){
				rateQuoteList.add(rateQuestion);
			}
		}
		String PropertyQuestionAnswer = step.getDataValue("PropertyQuestionAnswer");
		System.out.println("DATA>>> "+PropertyQuestionAnswer);
		HashMap<String, String> dataMap = validateQuestionAnswerIn1003(PropertyQuestionAnswer);

		String Module = step.getDataValue("Module");
		if (Module.equalsIgnoreCase("1003")) {
			HashMap<String, String> summarySheetMap = buildSummarySheetMap(dataMap, propertSummaryPageQtns, "property",
					PropertyQuestionAnswer);
			dataMap.keySet().forEach(key -> System.out.println(key + "->" + dataMap.get(key)));
			// tenothreesectionsummary.validateSummarySheetDetails(summarySheetMap,false);
		}
		
		if (Module.equalsIgnoreCase("1003RE")) {
			HashMap<String, String> summarySheetMap = buildSummarySheetMap(dataMap, propertSummaryRefinancePageQtns, "property",
					PropertyQuestionAnswer);
			dataMap.keySet().forEach(key -> System.out.println(key + "->" + dataMap.get(key)));
			// tenothreesectionsummary.validateSummarySheetDetails(summarySheetMap,false);
		}

		// String PropertyQuestionAnswer =
		// step.getDataValue("PropertyQuestionAnswer");
		// HashMap<String, String> dataMap =
		// validateQuestionAnswerIn1003(PropertyQuestionAnswer);
		// String Module = step.getDataValue("Module");
		// if(Module.equalsIgnoreCase("1003"))
		// {
		// System.out.println("Not Prequal");
		//
		//
		//
		//
		// tenothreesectionsummary.validateSummarySheetDetails(summarySheetMap,false);
		// //tenothreesectionsummary.validateSummarySheetDetails(dataMap);
		// }
		// else
		// {
		// System.out.println("Prequal");
		// }
		//
		// //new
		// dataMap.keySet().forEach(key -> System.out.println(key + "->" +
		// dataMap.get(key)));
	}

	/**
	 * Method Name: validateAssetQuestionAnswerIn1003 Purpose:
	 * 
	 */

	public void validateAssetQuestionAnswerIn1003() throws InterruptedException, TwfException, Exception {

		WebDriver driver = DriverFactory.getDriver();
		String assetQuestionAnswer = step.getDataValue("1003_Asset_Manual");
		String module = step.getDataValue("Module");
		HashMap<String, String> dataMap = new HashMap<>();
		int index = 0;
		for (String qtn : assetQuestionAnswer.split("//")) {
			dataMap = validateQuestionAnswerIn1003(qtn);
			System.out.println("DataMap in aasset>>>"+dataMap);
			String[] refQtn = assetSummaryPageQtns;
			
			  if(dataMap.get("My asset type").trim().equalsIgnoreCase("dd%Select_Others")&&(LoginIntoApplication.envType.equalsIgnoreCase("taurus"))||LoginIntoApplication.envType.equalsIgnoreCase("pisces")){
			  refQtn = giftAssetSummaryPageQtns; 
			  }
			  else{
				  if(dataMap.get("My asset type").trim().equalsIgnoreCase("Gift")){
					  refQtn = giftAssetSummaryPageQtns1; 
				  }
			  }
			HashMap<String, String> summarySheetMap = buildSummarySheetMap(dataMap, refQtn, "assets", qtn);
			if (module.trim().equalsIgnoreCase("1003")) {
				tenothreesectionsummary.validateSummarySheetDetails(summarySheetMap, false,1);
			}
			index = index + 1;
			if (index < assetQuestionAnswer.split("//").length) {
				addNewAssetSummaryPage(driver, index);
			}

		}

		System.out.println("Completed validation of AssetQuestionAnswer");

	}

	/**
	 * Method Name: addNewAssetSummaryPage Purpose:
	 * 
	 */

	public void addNewAssetSummaryPage(WebDriver driver, int currentAssetIndex)
			throws InterruptedException, TwfException, Exception {
		System.out.println("inside addIncomeSummary   . TenOThreeIncome class");
		int assetMaxLimit = 10;
		Thread.sleep(2000);

		// if
		// ((getElementByUsing("1003_Assets_Summary_Add_Assets_Button").isEnabled())
		// && (( currentAssetIndex +2 ) < (assetMaxLimit+1))) {
		if ((getElementByUsing("1003_Assets_Summary_Add_Assets_Button").isEnabled())) {
			getElementByUsing("1003_Assets_Summary_Add_Assets_Button").click();
			Thread.sleep(3000);
//			getElementByUsing("1003_Income_AddItManuallyButton").click();
			Thread.sleep(2000);
		} else {
			addExceptionToReport("Add_Assets_Button is disabled ", "Add_Assets_Button is disabled",
					"Add_Assets_Button should be enabled");
		}

	}

	public void validateIncomeQuestionAnswerIn1003() throws InterruptedException, TwfException, Exception {
		System.out.println("inside validateIncomeQuestionAnswerIn1003 ");

		String IncomeQuestionAnswer = step.getDataValue("Borrower_1003_Income_Business");
		// stored return datamap
		HashMap<String, String> dataMap = validateQuestionAnswerIn1003(IncomeQuestionAnswer);

	}

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

	public void validateDeclarationQuestionAnswerIn1003() throws InterruptedException, TwfException, Exception {
		System.out.println("inside validateDeclarationQuestionAnswerIn1003 ");

		String DeclarationQuestionAnswer = step.getDataValue("DeclarationQuestionAnswer");
		validateQuestionAnswerIn1003(DeclarationQuestionAnswer);

	}

	/**
	 * Method Name: postLoginPrequal1003 Purpose: To verify Prequal and 1003
	 * post login scenario for different kind of users like Active, Inactive,
	 * Locked and Not Registered
	 * 
	 * @throws Exception
	 */

	public void postLoginPrequal1003() throws InterruptedException, TwfException, Exception {

		System.out.println(" inside  postLoginPrequal1003  ............. ");
		String quesAnsString = step.getDataValue("question_ans");
		isNewUser = step.getDataValue("NewUser");
		String postloginOptions = step.getDataValue("PostloginOptions");
		WebDriver driver = DriverFactory.getDriver();
		HashMap<String, String> dataMap = new HashMap<String, String>();
		dataMap = buildDataMap(quesAnsString);
		System.out.println("after datamap");

		int QusIndexlength = dataMap.size();
		int blankCount = 0;
		System.out.println("QusIndexlength size is  " + QusIndexlength);
		for (int qusIndex = 0; qusIndex < QusIndexlength; qusIndex++) {
			System.out.println(" Check for ----------..............>>>  " + qusIndex);
			if (driver.findElements(By.xpath(Qustion.replace("%s", String.valueOf(qusIndex)))).size() > 0) {

				String attribute = driver.findElement(By.xpath(fieldAtttibute.replace("%s", String.valueOf(qusIndex))))
						.getAttribute("data-type").trim();
				try {
					String qustion = driver.findElement(By.xpath(Qustion.replace("%s", String.valueOf(qusIndex))))
							.getText().trim();
					System.out.println("  qustion -------->" + qustion);
					selectAttribute(driver, attribute, qusIndex, dataMap, qustion);
				} catch (Exception e) {
					System.out.println(" Caught Exception " + e);
					addExceptionToReport("Test Data Error",
							" Given  Test Data is not correct for attribute  " + attribute + " Exception is --> " + e,
							"Test Data should be  given properly ");

				}
			}

			else {
				System.out.println(" entered else loop .............");

				if (driver.findElements(By.xpath(postLoginPopup)).size() > 0) {

					postLoginOption(postloginOptions);

					qusIndex--;
				}

			}
		}
	}

	/**
	 * Method Name: postLoginOption Purpose: To verify Prequal and 1003 post
	 * login scenario for different kind of users like Active, Inactive, Locked
	 * and Not Registered
	 * 
	 * @throws Exception
	 */

	public void postLoginOption(String postloginOptions)
			throws BiffException, IOException, TwfException, InterruptedException {
		String confirmation_msg = getElementByUsing("createAccountConfirmationMessageActiveUser").getText();
		String expectedMsg = "";
		switch (postloginOptions) {

		case "Skip":
			expectedMsg = postLoginActiveuser_message;
			compareMessage(confirmation_msg, expectedMsg);
			getElementByUsing("postLoginPopupSkipButton").click();
			Thread.sleep(3000);
			break;

		case "CreateAccountLater":
			expectedMsg = postLogin_Newuser_message;
			compareMessage(confirmation_msg, expectedMsg);
			getElementByUsing("postLoginPopupCreateAccountLaterButton").click();
			Thread.sleep(3000);
			getElementByUsing("postLoginPopupCreateAccountYesButton").click();
			break;

		case "CreateAccountLaterChangeEmail":

			System.out.println("inside CreateAccountLaterChangeEmail");
			expectedMsg = postLogin_Newuser_message;
			compareMessage(confirmation_msg, expectedMsg);
			getElementByUsing("postLoginPopupCreateAccountLaterButton").click();
			Thread.sleep(3000);
			getElementByUsing("postLoginPopupCreateAccountChangeEmailButton").click();
			Thread.sleep(1000);
			String changeEmail = step.getDataValue("ChangeEmailId");
			getElementByUsing("FinExp_emailText").clear();
			getElementByUsing("FinExp_emailText").sendKeys(changeEmail);

			getElementByUsing("FinExp_Okay_Button").click();
			Thread.sleep(3000);
			break;

		case "Create account now":
			expectedMsg = postLogin_Newuser_message;
			compareMessage(confirmation_msg, expectedMsg);
			getElementByUsing("postLoginPopupCreateAccountNowButton").click();
			Thread.sleep(3000);
			createAccountPopUp();
			break;

		case "Login":
			System.out.println(" Inside  Login ");
			getElementByUsing("postLogin_Popup_Login_Button").click();
			Thread.sleep(2000);
			getElementByUsing("FinExp_Login_password").sendKeys("Test&123");
			getElementByUsing("Login_Button").click();
			break;

		case "InactiveSkip":
			expectedMsg = postLogin_InactiveUser_message;
			compareMessage(confirmation_msg, expectedMsg);
			getElementByUsing("postLoginPopupSkipButton").click();
			Thread.sleep(3000);
			break;

		case "Send me link":
			expectedMsg = postLogin_InactiveUser_message;
			compareMessage(confirmation_msg, expectedMsg);
			getElementByUsing("postLoginPopupSendMeLinkButton").click();
			Thread.sleep(3000);

			String infoScucessMessage = getElementByUsing("FinExp_SendMeLinkInfoSucessLink").getText();
			compareMessage(infoScucessMessage, postLogin_SendMeLink_message);
			break;

		}

	}

	private void compareMessage(String confirmation_msg, String expectedMsg) throws TwfException {
		if (!(confirmation_msg.contains(expectedMsg))) {
			addExceptionToReport("Post Login confirmation messages", confirmation_msg, expectedMsg);
		}

	}

	public void createAccountPopUp() throws BiffException, IOException, TwfException, InterruptedException {
		System.out.println(" Inside createAccountPopUp");

		getElementByUsing("FinExp_Registration_password").sendKeys("Test%123");
		getElementByUsing("FinExp_Registration_confirmPassword").sendKeys("Test%123");
		getElementByUsing("FinExp_Registration_econsent").click();
		Thread.sleep(1000);
		getElementByUsing("FinExp_Registration_create_button").click();
		Thread.sleep(1000);
		System.out.println(" End  createAccountPopUp");
	}

	// Validate Details in Get Started Summary Page

	public void testGetStartedSummary(boolean isCPS) throws Exception {
		HashMap<String, String> dataMap = new HashMap<String, String>();
		HashMap<String, String> summarySheetMap = new HashMap<String, String>();
		// fetch data from data sheet
		dataMap = validateQuestionAnswerIn1003("question_ans");

		summarySheetMap = buildSummarySheetMap(dataMap, getStartedCoapplicantSummaryPageQtns);
		// call method to verify applicant
		if (isCPS) {
			summarySheetMap = buildSummarySheetMap(dataMap, getStartedCPSPageQtns);
			// call method to verify coa-pplicant
		}

		String coapplicant = "coaap1//coapp2";
		String[] coappData = coapplicant.split("//");

		for (String coapp : coappData) {
			dataMap = validateQuestionAnswerIn1003(coapp);
			summarySheetMap = buildSummarySheetMap(dataMap, getStartedCoapplicantSummaryPageQtns);
			// call method to verify coapplicant
		}

		// To validate CPS
		if (isCPS) {
			summarySheetMap = buildSummarySheetMap(dataMap, getStartedCPSPageQtns);
		} else {
			summarySheetMap = buildSummarySheetMap(dataMap, getStartedCoapplicantSummaryPageQtns);
		}
	}

	public void testPropetySummary() throws Exception {
		HashMap<String, String> dataMap = new HashMap<String, String>();
		HashMap<String, String> summarySheetMap = new HashMap<String, String>();
		// fetch data from data sheet
		dataMap = validateQuestionAnswerIn1003("test");
		summarySheetMap = buildSummarySheetMap(dataMap, propertSummaryPageQtns);

	}

	public HashMap<String, String> buildSummarySheetMap(HashMap<String, String> dataMap, String[] expectedQtns)
			throws Exception {
		String quesAnsString = step.getDataValue("PropertyQuestionAnswer");
		HashMap<String, String> masterMap = new HashMap<String, String>();
		masterMap = buildDataMap(quesAnsString);
		HashMap<String, String> summarySheetMap = new HashMap<String, String>();
		Set<String> keySet = masterMap.keySet();
		// String[] expectedQtns = {"My name is:Name&First name", "I was born
		// on:Date of birth", "My marital status:Marital Status", "My contact
		// details are:Contact Number" };
		// String[] expectedQtns = {"I am purchasing a property for:Property
		// value&Property value","The property is located at:Property
		// location","I am purchasing a:Property type","I will use this property
		// as:Property occupancy"};

		for (String qtn : expectedQtns) {
			String question = qtn.split(":")[0];

			String answer = masterMap.get(question);
			System.out.println("Question>>>>" + question + " And its answet>>>>" + answer);
			HashMap<String, String> expectedAnswers = buildAnswerMap(question, answer);
			Set<String> expectedAnswer = expectedAnswers.keySet();

			for (String ans : expectedAnswer) {
				System.out.println("Answerrrrrr>>>>>" + ans);
				if (ans.startsWith("Qtn")) {
					String expectedKey = "Qtn_" + qtn.split(":")[0];
					System.out.println("expectedKey>>>>>" + expectedKey);
					System.out.println("expectedans1>>>>" + expectedAnswers.get(expectedKey));
					summarySheetMap.put(qtn.split(":")[1], expectedAnswers.get(expectedKey));
				} else if (question.contains("contact details")) {
					System.out.println("eXPECTED ANSWERS2>>>>>" + expectedAnswers);
					System.out.println("cONTACT NUMBER>>>>>" + getContactNumber(expectedAnswers));
					summarySheetMap.put(qtn.split(":")[1], getContactNumber(expectedAnswers));
				} else if (question.contains("located")) {
					System.out.println("located>>>>>" + expectedAnswers);
					summarySheetMap.put(qtn.split(":")[1],
							expectedAnswers.get("State") + "," + expectedAnswers.get("City"));
				} else {
					System.out.println("ANSWER3>>>>>" + expectedAnswers.get(qtn.split(":")[1].split("&")[1]));
					summarySheetMap.put(qtn.split(":")[1].split("&")[0],
							expectedAnswers.get(qtn.split(":")[1].split("&")[1]));
				}

			}
		}

		return summarySheetMap;
		/*
		 * Set<String> summarySheetValues = summarySheetMap.keySet(); for(String
		 * summarySheet:summarySheetValues){ System.out.println("Key Namr>>>>"
		 * +summarySheet+" and it values are>>>>"+summarySheetMap.get(
		 * summarySheet)); }
		 */
	}

	// public void buildSummarySheetMap(HashMap<String,String> masterMap1){
	public void buildSummarySheetMap() throws Exception {
		System.out.println("entered buildSummarySheetMap ===property");
		String quesAnsString = step.getDataValue("PropertyQuestionAnswer");
		HashMap<String, String> masterMap = new HashMap<String, String>();
		masterMap = buildDataMap(quesAnsString);
		HashMap<String, String> summarySheetMap = new HashMap<String, String>();
		Set<String> keySet = masterMap.keySet();
		// String[] expectedQtns = {"My name is:Name&First name", "I was born
		// on:Date of birth", "My marital status:Marital Status", "My contact
		// details are:Contact Number" };
		String[] expectedQtns = { "Estimated Property Value:Property value&Property value",
				"The property is located at:Property location", "I am purchasing a:Property type",
				"I will use this property as:Property occupancy" };
		// TA %First name_ fname
		// 12/12/1990
		// Unmarried
		// ta%Home phone_233-333-3333:ta%Mobile
		// number_123-123-1232:ta%Email-ID_testInactive@tavant.com

		for (String qtn : expectedQtns) {
			String question = qtn.split(":")[0];

			String answer = masterMap.get(question);
			System.out.println("Question>>>>" + question + " And its answet>>>>" + answer);
			HashMap<String, String> expectedAnswers = buildAnswerMap(question, answer);
			Set<String> expectedAnswer = expectedAnswers.keySet();

			for (String ans : expectedAnswer) {
				System.out.println("Answerrrrrr>>>>>" + ans);
				if (ans.startsWith("Qtn")) {
					String expectedKey = "Qtn_" + qtn.split(":")[0];
					System.out.println("expectedKey>>>>>" + expectedKey);
					System.out.println("expectedans1>>>>" + expectedAnswers.get(expectedKey));
					summarySheetMap.put(qtn.split(":")[1], expectedAnswers.get(expectedKey));
				} else if (question.contains("contact details")) {
					System.out.println("eXPECTED ANSWERS2>>>>>" + expectedAnswers);
					System.out.println("cONTACT NUMBER>>>>>" + getContactNumber(expectedAnswers));
					summarySheetMap.put(qtn.split(":")[1], getContactNumber(expectedAnswers));
				} else if (question.contains("located")) {
					System.out.println("located>>>>>" + expectedAnswers);
					summarySheetMap.put(qtn.split(":")[1],
							expectedAnswers.get("State") + "," + expectedAnswers.get("City"));
				} else {
					System.out.println("ANSWER3>>>>>" + expectedAnswers.get(qtn.split(":")[1].split("&")[1]));
					summarySheetMap.put(qtn.split(":")[1].split("&")[0],
							expectedAnswers.get(qtn.split(":")[1].split("&")[1]));
				}

			}
		}

		Set<String> summarySheetValues = summarySheetMap.keySet();
		for (String summarySheet : summarySheetValues) {
			System.out.println(
					"Key Namr>>>>" + summarySheet + " and it values are>>>>" + summarySheetMap.get(summarySheet));
		}

	}

	private String getContactNumber(HashMap<String, String> expectedMap) {

		Set<String> keys = expectedMap.keySet();
		String contactNumber = "";
		String bestContactNumber = "";
		String alternateNumber = "";
		for (String key : keys) {
			if (key.contains("Best Contact Number")) {
                bestContactNumber= expectedMap.get(key);
			}
			if (key.contains("Alternate Number (Optional)")) {
				alternateNumber= expectedMap.get(key);
			}
		}

		System.out.println("bestContactNumber is" + bestContactNumber);
		System.out.println("alternateNumber is" + alternateNumber);
		for (String key : keys){
		if (bestContactNumber.length() == 0) {
			if(expectedMap.get(key).contains("Home") && key.contains("Alternate Number (Optional)"))
			{
			contactNumber = alternateNumber + "(H)";
			}
			else if(expectedMap.get(key).contains("Mobile") && key.contains("Alternate Number (Optional)"))
			{
			contactNumber = alternateNumber + "(M)";	
			}
		} else if (alternateNumber.length() == 0) {
			if(expectedMap.get(key).contains("Home") && key.contains("Best Contact Number"))
			{
			contactNumber = bestContactNumber + "(H)";
			}
			else if(expectedMap.get(key).contains("Mobile") && key.contains("Best Contact Number"))
			{
			contactNumber = bestContactNumber + "(M)";	
			}
		
		} else if (bestContactNumber.length() > 0 && alternateNumber.length() > 0) {
			if(expectedMap.get(key).contains("Home") && key.contains("Best Contact Number")) 
            {
			contactNumber = bestContactNumber + "(H) /" + alternateNumber + "(M)";
		    }
            else if(expectedMap.get(key).contains("Mobile") && key.contains("Best Contact Number"))
            {
            	contactNumber = alternateNumber + "(H) /" + bestContactNumber + "(M)";
            }
		}
		}
		System.out.println("contactNumber is" + contactNumber);
		
		return contactNumber;

		/*
		 * System.out.println("Contact Number>>>>>"+contactNumber); return
		 * contactNumber.substring(1, contactNumber.length());
		 */
		}

//	private HashMap<String, String> buildAnswerMap(String expectedQtn, String expectedAnswer) {
//		HashMap<String, String> expectedAnswerMap = new HashMap<String, String>();
//		if (expectedAnswer.contains("%")) {
//			String[] level1Answers = expectedAnswer.split(":");
//			for (String level1Answer : level1Answers) {
//				System.out.println("Level1 answer>>>>" + level1Answer);
//				expectedAnswerMap.put(level1Answer.split("%")[1].split("_")[0],
//						level1Answer.split("%")[1].split("_")[1]);
//			}
//		} else {
//			System.out.println("in else loop>>>>>" + expectedAnswer);
//			expectedAnswerMap.put("Qtn_" + expectedQtn, expectedAnswer);
//		}
//		return expectedAnswerMap;
//	}

	private HashMap<String, String> buildAnswerMap(String expectedQtn, String expectedAnswer) {
		System.out.println("Inside buildAnswerMap"); 
		System.out.println("expectedQtn>>"+expectedQtn);
		System.out.println("expectedAnswer>>"+expectedAnswer);
        HashMap<String, String> expectedAnswerMap = new HashMap<String, String>();
        if(expectedAnswer.contains("dd")){
            System.out.println("Inside dd>>>>"+expectedAnswer.split("_")[1]+"question"+expectedQtn);
            expectedAnswerMap.put(expectedQtn, expectedAnswer.split("_")[1]);
        }
        else{
        if (expectedAnswer.contains("%")) {
            String[] level1Answers = expectedAnswer.split(":");
            for (String level1Answer : level1Answers) {
                System.out.println("Level1 answer>>>>" + level1Answer.split("%")[1].split("_")[0]+"!!!!ssssssssss"+level1Answer.split("%")[1].split("_")[1]);
                expectedAnswerMap.put(level1Answer.split("%")[1].split("_")[0],
                        level1Answer.split("%")[1].split("_")[1]);
                System.out.println("Inside expectedAnswerMap ??"+expectedAnswerMap);
            }
        } else {
            System.out.println("in else loop>>>>>" + expectedAnswer);
            expectedAnswerMap.put("Qtn_" + expectedQtn, expectedAnswer);
            System.out.println("Inside else expectedAnswerMap??"+expectedAnswerMap);
        }
        }
        System.out.println("expectedAnswerMap>>"+expectedAnswerMap);
        return expectedAnswerMap;
    }
	
	
	private void clickOnSkipButton(WebDriver driver){
		//driver.findElement(By.xpath("//button[@id[contains(.,'No')] and contains(.,'Skip')]")).click();
		driver.findElement(By.xpath("((//div[@aria-hidden='false']//div[@class='modal-body']//div)[1]//div)[3]//button[contains(.,'Skip')]")).click();
	}

	public static void clickOnButtonWithvalue(WebDriver driver, String buttonvalue){
		String buttonpopupObject="((//div[@aria-hidden='false']//div[@class='modal-body']//div)[1]//div)[3]//button[contains(.,'%val')]";
		driver.findElement(By.xpath(buttonpopupObject.replace("%val",buttonvalue ))).click();
	}
	
	private void actionOnPopUpWindow(WebDriver driver,String displayedText){

		System.out.println("displayedText>>>"+displayedText);
		if(displayedText.contains("Your property's standardized address is"))
			displayedText="Your property's standardized address is";
		else if(displayedText.contains("They would be sharing a single account and thus would have access to each others information. Are you ok to proceed?"))
			displayedText="They would be sharing a single account and thus would have access to each others information. Are you ok to proceed?";
		switch (displayedText.trim()) {
		case "The address provided is invalid, please provide a valid address.":		
			clickOnSkipButton(driver);
			break;
		case "Several addresses matched the information provided. Please enter a valid address, or verify street number or input the building unit.":		
			clickOnSkipButton(driver);
			break;
		case "Your property's standardized address is":
			uspsFlag="true";
			clickOnButtonWithvalue(driver,"original");
			break;
		case "Since you're married and live in a Community Property State, we'll need your spouse's details.":
			clickOnButtonWithvalue(driver,"Okay");
			break;
		case "You have been at your current address for less than 2 years, list your previous addresses":
			clickOnButtonWithvalue(driver,"Add");
			break;
		case "By selecting this option, you’d like to proceed with the application without providing ACH details.":
			clickOnButtonWithvalue(driver,"Okay");

			clickOnButtonWithvalue(driver,"Okay");
			break;
		case "They would be sharing a single account and thus would have access to each others information. Are you ok to proceed?":
			clickOnButtonWithvalue(driver,"Yes");
			break;
		case "You will lose unsaved information. Do you want to continue ?":
			clickOnButtonWithvalue(driver, "Yes");
		default:
			break;
		}
	}	
	
	private void handlePopUpWindow(WebDriver driver){
        try{
            String displayedText = driver.findElement(By.xpath("//div[@aria-hidden='false']//div[@class='modal-body']//div[@class='body-tit']")).getText();
            System.out.println("Displayted text>>>>"+displayedText);
 //           driver.findElement(By.xpath("((//div[@aria-hidden='false']//div[@class='modal-body']//div)[1]//div)[3]//button[contains(.,'Okay')]")).click();
            actionOnPopUpWindow(driver, displayedText);
        }catch(Exception e){

        }
    }
	
//	public HashMap<String, String> selectAttribute(WebDriver driver, String attribute, int qusIndex,
//            HashMap<String, String> expectedMap, String qustion) throws Exception {
//        String State = null, City = null;
//        String val = expectedMap.get(qustion);
//        boolean isDownPaymentOptionDisplayed = qustion.contains("I can put a down payment of");
//        boolean isQuestionRelatedToSpouse = qustion.equalsIgnoreCase("Provide spouse details");
//        System.out.println("isQuestionRelatedToSpouse>>>>"+isQuestionRelatedToSpouse);
//        boolean dependent = qustion.contains("Enter dependent(s) age");
//        System.out.println("Attribute name>>>>" + attribute);
//        preFilledValue=false;
//
////		if(cPSList.contains(qustion)){
////			preFilledValue = true;
////			System.out.println("CPS Validation set to true for Question>>>"+qustion);
////		}
////		
////		toValidate=false;
////		if(rateQuoteList.contains(qustion)){
////			toValidate = true;
////			System.out.println("RateQuote Validation set to true for Question>>>"+qustion);
////		}
//        switch (attribute.toLowerCase()) {
//        case "radiobtn":
//            System.out.println("Entered radiobtn");
//            String[] sub_fields = val.split(":");
//            String radioButtonXPath = radioButtonAns.replace("%s", String.valueOf(qusIndex)).replace("%v", val);
//            if (sub_fields.length == 1) {
////            if(toValidate){
////					Thread.sleep(1000);
////					System.out.println("RAdio Button State>>>>"+getRadioButtonState(driver,radioButtonXPath));
////					if(!getRadioButtonState(driver,radioButtonXPath).contains("active")){
////						toValidate=false;
////						addExceptionToReport("Rate Quote Value not pre-populated in Portal for under question "+qustion, "", "");
////					}
////					toValidate=false;
////					System.out.println("Radio Button validated as part of Rate Quote Validation");
////				}
//                selectRadioButton(driver, radioButtonXPath);
//                if(val.equalsIgnoreCase("Married")){
//                    if(driver.findElements(By.xpath("((//div[@aria-hidden='false']//div[@class='modal-body']//div)[1]//div)[2]")).size()>0)
//                           handlePopUpWindow(driver);
//                    Thread.sleep(2000);
//                    
//                    
//             }
//                if (qustion.equalsIgnoreCase("Do you have other liens on the property? (Optional)"))
//                    driver.findElement(By.xpath(OkayBUtton1.replace("%s", String.valueOf(qusIndex)))).click();
//
// 
//
//            } else {
//                expectedMap = selectMultipleOptionUnderQuestion(driver, expectedMap, qustion, qusIndex, val,
//                        expectedMap.size());
//
// 
//
//            }
//            int revisedQtnIndex = qusIndex + 1;
//            if (revisedQtnIndex < expectedMap.size()) {
//                try {
//
// 
//
//                    if (driver.findElement(By.xpath(Qustion.replace("%s", String.valueOf(revisedQtnIndex))))
//                            .isDisplayed()) {
//                        break;
//                    }
//                } catch (Exception e) {
//                    // selectRadioButton(driver, radioButtonXPath);
//                }
//            }
//            System.out.println("Its done radiobtn");
//            break;
//        case "currency":
//            System.out.println("entered selectAttribute currency switch case???val"+val);
//if(toValidate){
//				System.out.println("Inside toValidate in currency for Rate Quote Validation");
//				String placeHolder=qustion;
//				String expData=expectedMap.get(qustion);
//				if(expData.contains("ta")){
//					placeHolder=expData.split("_")[0].split("%")[1];
//					expData=expData.split("_")[1];
//				}
//				System.out.println("Placeholder:"+placeHolder);
//				System.out.println("ExpectedData for toVAlidate:"+expData);
//				String actData=fetchValuesFromTextField(driver, qusIndex, placeHolder);
//				actData=actData.replace("$", "").trim();
//				actData=actData.split("\\.")[0];
//				if(!(actData.equalsIgnoreCase(expData))){
//					toValidate=false;
//					addExceptionToReport("Rate Quote Value not pre-populated in Portal for "+placeHolder, actData, expData);
//				}
//				toValidate=false;
//				System.out.println(placeHolder+"validated as part of Rate Quote Validation");
//			}
//			else
// 
//
//
//            enterValuesInCurrencyField(driver, qusIndex, val, isDownPaymentOptionDisplayed);
//            clickOnOkButtonAfterEachQuestion(driver, qusIndex, expectedMap.size());
//            System.out.println("Its done currency");
//            break;
//        case "dateformat":
//            enterValuesInDateField(driver, qusIndex, val);
//            clickOnOkButtonAfterEachQuestion(driver, qusIndex, expectedMap.size());
//            break;
//
// 
//
//        default:
//        /*    if (isQuestionRelatedToSpouse) {
//                if(driver.findElements(By.xpath("((//div[@aria-hidden='false']//div[@class='modal-body']//div)[1]//div)[2]")).size()>0)
//                    handlePopUpWindow(driver);
//                Thread.sleep(3000);
//                //driver.findElement(By.xpath(spouseQtnModalWindow)).click();
//                //driver.switchTo().activeElement();
//                //driver.findElement(By.xpath(spouseQtnModalWindow)).click();
//                // Thread.sleep(2000);
//                driver.switchTo().defaultContent();
//            } */
//             if (dependent) {
//                addDependents(driver, qustion, expectedMap.get(qustion), qusIndex, expectedMap);
//
// 
//
//            }
//
// 
//
//            else {
//                expectedMap = selectMultipleOptionUnderQuestion(driver, expectedMap, qustion, qusIndex, val,
//                        expectedMap.size());
// //               clickOnOkButtonAfterEachQuestion(driver, qusIndex, expectedMap.size());
//                // clickOnOKButtonIfExists(driver,qusIndex,expectedMap.size());
//            }
//
// 
//
//            break;
//        }
//
// 
//
//        return expectedMap;
//    }

	public HashMap<String, String> selectAttribute(WebDriver driver, String attribute, int qusIndex,
            HashMap<String, String> expectedMap, String qustion) throws Exception {
        String State = null, City = null;
        String val = expectedMap.get(qustion);
        boolean isDownPaymentOptionDisplayed = qustion.contains("I can put a down payment of");
        boolean isQuestionRelatedToSpouse = qustion.equalsIgnoreCase("Provide spouse details");
        System.out.println("isQuestionRelatedToSpouse>>>>"+isQuestionRelatedToSpouse);
        boolean dependent = qustion.contains("Enter dependent(s) age");
        System.out.println("Attribute name>>>>" + attribute);
        preFilledValue=false;

		if(cPSList.contains(qustion)){
			preFilledValue = true;
			System.out.println("CPS Validation set to true for Question>>>"+qustion);
		}
		
		toValidate=false;
		if(rateQuoteList.contains(qustion)){
			toValidate = true;
			System.out.println("RateQuote Validation set to true for Question>>>"+qustion);
		}
        switch (attribute.toLowerCase()) {
        case "radiobtn":
            System.out.println("Entered radiobtn");
            String[] sub_fields = val.split(":");
            String radioButtonXPath = radioButtonAns.replace("%s", String.valueOf(qusIndex)).replace("%v", val);
            System.out.println("Xpath>>>"+radioButtonXPath);
            if (sub_fields.length == 1) {
            if(toValidate){
					Thread.sleep(1000);
					System.out.println("RAdio Button State>>>>"+getRadioButtonState(driver,radioButtonXPath));
					if(!getRadioButtonState(driver,radioButtonXPath).contains("active")){
						toValidate=false;
						addExceptionToReport("Rate Quote Value not pre-populated in Portal for under question "+qustion, "", "");
					}
					toValidate=false;
					System.out.println("Radio Button validated as part of Rate Quote Validation");
				}
                selectRadioButton(driver, radioButtonXPath);
                if(val.equalsIgnoreCase("Married")){
                    if(driver.findElements(By.xpath("((//div[@aria-hidden='false']//div[@class='modal-body']//div)[1]//div)[2]")).size()>0)
                           handlePopUpWindow(driver);
                    Thread.sleep(3000);
                    
                    
             }
                if (qustion.equalsIgnoreCase("Do you have other liens on the property? (Optional)"))
                    driver.findElement(By.xpath(OkayBUtton1.replace("%s", String.valueOf(qusIndex)))).click();

 

            } else {
                expectedMap = selectMultipleOptionUnderQuestion(driver, expectedMap, qustion, qusIndex, val,
                        expectedMap.size());

 

            }
            int revisedQtnIndex = qusIndex + 1;
            if (revisedQtnIndex < expectedMap.size()) {
                try {

 

                    if (driver.findElement(By.xpath(Qustion.replace("%s", String.valueOf(revisedQtnIndex))))
                            .isDisplayed()) {
                        break;
                    }
                } catch (Exception e) {
                    // selectRadioButton(driver, radioButtonXPath);
                }
            }
            System.out.println("Its done radiobtn");
            break;
        case "currency":
            System.out.println("entered selectAttribute currency switch case???val"+val);
if(toValidate){
				System.out.println("Inside toValidate in currency for Rate Quote Validation");
				String placeHolder=qustion;
				String expData=expectedMap.get(qustion);
				if(expData.contains("ta")){
					placeHolder=expData.split("_")[0].split("%")[1];
					expData=expData.split("_")[1];
				}
				System.out.println("Placeholder:"+placeHolder);
				System.out.println("ExpectedData for toVAlidate:"+expData);
				String actData=fetchValuesFromTextField(driver, qusIndex, placeHolder);
				actData=actData.replace("$", "").trim();
				actData=actData.split("\\.")[0];
				if(!(actData.equalsIgnoreCase(expData))){
					toValidate=false;
					addExceptionToReport("Rate Quote Value not pre-populated in Portal for "+placeHolder, actData, expData);
				}
				toValidate=false;
				System.out.println(placeHolder+"validated as part of Rate Quote Validation");
			}
		else{
			if (!val.contains(":")) {
				System.out.println("inside case 1 currency");
		//		val=val.split("_")[1];
				enterValuesInCurrencyField(driver, qusIndex, val, isDownPaymentOptionDisplayed);
				clickOnOkButtonAfterEachQuestion(driver, qusIndex, expectedMap.size());
			}
		
			else{
				expectedMap = selectMultipleOptionUnderQuestion(driver, expectedMap, qustion, qusIndex, val,
						expectedMap.size());
			System.out.println("Came back t o currency>>");
			}
		}
 
//            enterValuesInCurrencyField(driver, qusIndex, val, isDownPaymentOptionDisplayed);
//            clickOnOkButtonAfterEachQuestion(driver, qusIndex, expectedMap.size());
            System.out.println("Its done currency");
            break;
        case "dateformat":
            enterValuesInDateField(driver, qusIndex, val);
            clickOnOkButtonAfterEachQuestion(driver, qusIndex, expectedMap.size());
            break;

 

        default:
        /*    if (isQuestionRelatedToSpouse) {
                if(driver.findElements(By.xpath("((//div[@aria-hidden='false']//div[@class='modal-body']//div)[1]//div)[2]")).size()>0)
                    handlePopUpWindow(driver);
                Thread.sleep(3000);
                //driver.findElement(By.xpath(spouseQtnModalWindow)).click();
                //driver.switchTo().activeElement();
                //driver.findElement(By.xpath(spouseQtnModalWindow)).click();
                // Thread.sleep(2000);
                driver.switchTo().defaultContent();
            } */
             if (dependent) {
                addDependents(driver, qustion, expectedMap.get(qustion), qusIndex, expectedMap);

 

            }

 

            else {
                expectedMap = selectMultipleOptionUnderQuestion(driver, expectedMap, qustion, qusIndex, val,
                        expectedMap.size());
                // clickOnOKButtonIfExists(driver,qusIndex,expectedMap.size());
            }

 

            break;
        }

 

        return expectedMap;
    }
	
	
	
	/**
	 * Method Name: checkDependent Purpose: Add number of dependents for the
	 * Enter dependent(s) age question
	 * 
	 * @param driver
	 * 
	 * @throws Exception
	 */

	private void addDependents(WebDriver driver, String question, String dependent_ans, int qusIndex,
			HashMap<String, String> dataMap) throws Exception {

		if (dependent_ans.contains(":")) {
			String[] dependent_Age = dependent_ans.split(":");
			// System.out.println("dependent_Age " + dependent_Age.length );
			int dependent_Age_length = dependent_Age.length;
			while (dependent_Age_length > 1) {
				getElementByUsing("FinExp_100_AddDependent_Button").click();

				dependent_Age_length--;
			}
			selectMultipleOptionUnderQuestion(driver, dataMap, question, qusIndex, dependent_ans, dependent_Age.length);
			/*
			 * if(dependent_Age_length>2) { //
			 * driver.findElement(By.xpath(dependentDeleteIcon.replace("%s",
			 * String.valueOf(dependent_Age_length-1)))).click(); }
			 */

		}

	}

	private HashMap<String, String> selectMultipleOptionUnderQuestion(WebDriver driver, HashMap<String, String> dataMap,
			String question, Integer qusIndex, String options, Integer totalQtnCount) throws Exception {
		String[] optionList = options.split(":");
		String refFieldType = "";
		for (String option : optionList) {
			String[] field_type = option.trim().split("%");
			String[] placeholder_value = (field_type[1].trim()).split("_");
			String placeholder = placeholder_value[0].trim();
			String value = placeholder_value[1];
			String stateCity = "";
			String fieldType = field_type[0].trim().toLowerCase();
			encompassValidatedataMap.put(placeholder, value);
			if (fieldType.equalsIgnoreCase("RB")) {
				fieldType = "radiobutton";
			}
			refFieldType = fieldType;

			switch (fieldType) {
			case "ta":
				if (placeholder.trim().equalsIgnoreCase("First name") && isNewUser.trim().equalsIgnoreCase("Yes")) {
					int len = 5;
					fNameIndex = fNameIndex+1;
					value = RandomStringUtils.random(len, true, false);
					if(fNameIndex>1){
						coApplicantFname = value;
						name.put(String.valueOf(fNameIndex), value);
						fNameIndex=0;
					}else{
						fname = value;
						name.put(String.valueOf(fNameIndex), value);
					}
					
					System.out.println("Inside randon  " + fname);
					dataMap.put(placeholder, value);
				}
				else if (placeholder.trim().equalsIgnoreCase("First name") && isNewUser.trim().equalsIgnoreCase("No")) {
					fNameIndex = fNameIndex+1;
					if(fNameIndex>1){
						coApplicantFname = value;
						name.put(String.valueOf(fNameIndex), value);
						fNameIndex=0;
					}else{
						fname = value;
						name.put(String.valueOf(fNameIndex), value);
					}
					System.out.println("Inside else if fname  " + fname);
					System.out.println("Applicant Map>>>"+name);
					dataMap.put(placeholder, value);
				}				System.out.println("toVAlidate inside entering text>>>"+toValidate);
				if(toValidate){
					System.out.println("Inside toValidate for Textfield for Rate Quote Validation");
					String placeHolder=question;
					String expData=dataMap.get(question);
					if(expData.contains("ta")){
						placeHolder=expData.split("_")[0].split("%")[1];
						expData=expData.split("_")[1];
					}
					System.out.println("Placeholder:"+placeHolder);
					System.out.println("ExpectedData for toVAlidate:"+expData);
					String actData=fetchValuesFromTextField(driver, qusIndex, placeHolder);
					if(!(actData.equalsIgnoreCase(expData))){
						toValidate=false;
						addExceptionToReport("Rate Quote Value not pre-populated in Portal for "+placeHolder, actData, expData);
					}
					toValidate=false;
					System.out.println(placeHolder+"validated as part of Rate Quote Validation");
				}
				stateCity = enterValuesInTextField(driver, qusIndex, placeholder, value);
				if (stateCity != null) {
					dataMap.put(question, stateCity);
				}
				break;
			case "rb":
				selectRadioButton(driver, radioButtonAns.replace("%s", String.valueOf(qusIndex)).replace("%v", value));
				break;
			case "dd":
				selectValuesInDropDownList(driver, qusIndex, placeholder, value);
//				System.out.println("Came out of selectMultipleOptionUnderQuestion>>");
				break;
			case "rdd":
				selectRelativeValuesInDropDownList(driver, qusIndex, placeholder, value);
				break;
			case "cb":
				clickOnCheckBox(driver, value,qusIndex);
				break;
			case "radiobutton":
				selectRadioButtonOption(driver, qusIndex, value);
				break;
			case "datepicker":
				setValuesInDateField(driver, placeholder, value);
				break;
			case "fromto":
				setValuesInDateField(driver, placeholder, value);
				break;
			default:
				break;
			}

		}
		System.out.println("Came out of switch" + refFieldType);
		if (refFieldType.trim().equalsIgnoreCase("radiobutton") == false
				|| refFieldType.trim().equalsIgnoreCase("rb") == false) {
			System.out.println("Refernce fields type>>>>" + refFieldType);
//			Thread.sleep(1000);
//			if(!refFieldType.trim().equalsIgnoreCase("dd")){
//			System.out.println("!!!");
			clickOnOkButtonAfterEachQuestion(driver, qusIndex, totalQtnCount);
//			}
		}
		System.out.println("It didn't entered !!");
		return dataMap;
	}

	private void enterValuesInCurrencyField(WebDriver driver, Integer qusIndex, String value,
			boolean isDownPaymentOptionDisplayed) throws Exception {
		try {
	//		System.out.println("Innn!!!  enterValuesInCurrencyField///");
			driver.findElement(By.xpath(inputText.replace("%s", String.valueOf(qusIndex)))).clear();
	//		Thread.sleep(2000);
			System.out.println("val before"+value);
			
			value = value.split("_")[1];
			System.out.println("revised val???"+value);
			driver.findElement(By.xpath(inputText.replace("%s", String.valueOf(qusIndex)))).sendKeys(value);
			System.out.println("Values!!!!>>>"+value);
			Thread.sleep(2000);
			
			// Downpayment functionality is hence commenting
//			if (isDownPaymentOptionDisplayed) {
//				try {
//					driver.findElement(By.xpath(OkayBUtton1.replace("%s", String.valueOf(qusIndex)))).click();
//				} catch (Exception e) {
//					addExceptionToReport("Ok Button is not displayed under Down Payment Option.", "", "");
//				}
//
//			}
		} catch (Exception e) {
			addExceptionToReport("Currency Field under question Index " + qusIndex + " is not displayed.", "", "");
		}

	}

//	private void clickOnOkButtonAfterEachQuestion(WebDriver driver, Integer qusIndex, Integer totalQuestionCount)
//			throws Exception {
//		WebElement okButton = driver.findElement(By.xpath(OkayBUtton1.replace("%s", String.valueOf(qusIndex))));
//		if(incompleSection==true){
//			totalQuestionCount=totalQuestionCount+lastIndex+1;
//		}
////		Thread.sleep(5000);
//		if (okButton.isDisplayed() == false) {
//			utill.scrollToElement(okButton);
//		}
//		// okButton.click();
//		clickOnOkButton(driver, okButton);
////		Thread.sleep(5000);
//		int revisedQusIndex = qusIndex + 1;
//		if (revisedQusIndex < totalQuestionCount) {
//			try {
//				 
//				if (driver.findElement(By.xpath(Qustion.replace("%s", String.valueOf(revisedQusIndex))))
//						.isDisplayed()) {
//
//				}
//			} catch (Exception e) {
//				try {
//					 System.out.println("Inside catch also it came!!....");
//					driver.switchTo().activeElement();
//
//					if (driver.findElements(By.xpath("//confirm-modal-new//button")).size() > 0) {
//						String displayedtitle = driver
//								.findElement(By.xpath("(//confirm-modal-new//div[@class='body-tit'])[1]")).getText();
//						System.out.println("displayedtitle>>>>>" + displayedtitle);
//						String invalidAddressMsg = KWVariables.getVariables().get("InvalidAddressAlert");
//						String unableToValidateAddressMsg = KWVariables.getVariables()
//								.get("UnableToValidateAddressAlert");
//
//						if(((displayedtitle.contains("Your property's standardized address"))&&(displayedtitle.contains("Your original address"))))
//						{
//							System.out.println("this addresss it came>>>");
//							uspsFlag="true";
//							standardisedAddress=displayedtitle.split("Your property's standardized address is \" ")[1].split(",")[0].trim();
//						}
//						if (displayedtitle.contains(unableToValidateAddressMsg)
//								|| displayedtitle.contains(invalidAddressMsg)) {
//							driver.findElement(By.xpath("(//confirm-modal-new//button[2])[1]")).click();
//							waitForPageToLoad();
////							Thread.sleep(2000);
//
//						} else {
//							System.out.println("Inside else>>>>");
//							driver.findElement(By.xpath("(//confirm-modal-new//button[1])[1]")).click();
//							driver.switchTo().defaultContent();
////							Thread.sleep(2000);
//							// okButton.click();
//						}
//					}
//					driver.switchTo().activeElement();
//
//				} catch (Exception e3) {
//					System.out.println("exception in clickon ok button>>>>" + e3.toString());
//					String postloginOptions = step.getDataValue("PostloginOptions");
//					postLoginOption(postloginOptions);
//
//				}
//
//			}
//		}
//
//	}

	private void clickOnOkButtonAfterEachQuestion(WebDriver driver, Integer qusIndex, Integer totalQuestionCount)
			throws Exception {
		System.out.println("\n Inside clickOnOkButtonAfterEachQuestion>>>");
		try{
		WebElement okButton = driver.findElement(By.xpath(OkayBUtton1.replace("%s", String.valueOf(qusIndex))));
		if (okButton.isDisplayed() == false) {
			utill.scrollToElement(okButton);
		}
		clickOnOkButton(driver, okButton);
		}catch(Exception e){
			
		}

		int revisedQusIndex = qusIndex + 1;
		if (revisedQusIndex < totalQuestionCount) {
//			try {
			try {
				if (!driver.findElement(By.xpath(Qustion.replace("%s", String.valueOf(revisedQusIndex))))
						.isDisplayed()) {
					
//						System.out.println("Came Inside new try");
//						driver.switchTo().activeElement();

//						if (driver.findElements(By.xpath("//confirm-modal-new//button")).size() > 0) {
////							try{
//							throw new Exception("Handle Pop-up Window");
//							}
					}
				}catch(Exception e1){
//								System.out.println("It came Indide new catch");
								handlePopUpWindow(driver);
					}

				}
//			} catch (Exception e) {
				
						
						/*String displayedtitle = driver
								.findElement(By.xpath("//confirm-modal-new//div[@class='body-tit']")).getText();
						System.out.println("displayedtitle>>>>>" + displayedtitle);
						String invalidAddressMsg = KWVariables.getVariables().get("InvalidAddressAlert");
						String unableToValidateAddressMsg = KWVariables.getVariables()
								.get("UnableToValidateAddressAlert");

						if(((displayedtitle.contains("Your property's standardized address"))&&(displayedtitle.contains("Your original address"))))
						{
							uspsFlag="true";
							standardisedAddress=displayedtitle.split("Your property's standardized address is \" ")[1].split(",")[0].trim();
						}
						if (displayedtitle.contains(unableToValidateAddressMsg)
								|| displayedtitle.contains(invalidAddressMsg)) {
							driver.findElement(By.xpath("(//confirm-modal-new//button[2])[1]")).click();
							waitForPageToLoad();
							Thread.sleep(2000);

						} else {
							System.out.println("Inside else>>>>");
							driver.findElement(By.xpath("(//confirm-modal-new//button[1])[1]")).click();
							driver.switchTo().defaultContent();
							Thread.sleep(2000);
							// okButton.click();
						}
						*/
//					}
//					driver.switchTo().activeElement();

	//			} catch (Exception e3) {
	//				System.out.println("exception in clickon ok button>>>>" + e3.toString());
	//				String postloginOptions = step.getDataValue("PostloginOptions");
	//				postLoginOption(postloginOptions);

	//			}

//			}
		}

//	}
	
	
	
	
	private boolean clickOnOkButton(WebDriver driver, WebElement okButton) throws Exception {
		okButton.click();
		/*
		 * Thread.sleep(5000); try{ if(okButton.isDisplayed()){
		 * System.out.println("Double clicked ok"); try{ Actions action = new
		 * Actions(driver);
		 * action.moveToElement(okButton).click().build().perform();
		 * Thread.sleep(3000); System.out.println("Clicked twice");
		 * }catch(Exception e1){
		 * 
		 * System.out.println("e1111?????"+e1.toString()); }
		 * 
		 * //okButton.click(); } }catch(Exception e){ return true; }
		 */
		return true;
	}

	private void enterValuesInDateField(WebDriver driver, Integer qusIndex, String value) {
		driver.findElement(By.xpath(dateOfBirth.replace("%s", String.valueOf(qusIndex)))).click();
		driver.findElement(By.xpath(dateOfBirth.replace("%s", String.valueOf(qusIndex)))).sendKeys(value);
		;
	}


	private String fetchValuesFromTextField(WebDriver driver, Integer qusIndex, String placeHolderName)
			throws Exception {
		try{
			return driver.findElement(By.xpath(
					sub_ans_text.replace("%s", String.valueOf(qusIndex)).replace("%placeholder", placeHolderName))).getAttribute("value");
		}catch(Exception e){
			addExceptionToReport(
					"Expected placeholder " + placeHolderName + " is not displayed under Question whose Index is "
							+ qusIndex + " and Exception is " + e.getMessage().toString(),
							"", "");
		}
		return "";
	}

	private String enterValuesInTextField(WebDriver driver, Integer qusIndex, String placeHolderName, String value)
			throws Exception {
		String state = null;
		String city = null;
		Thread.sleep(1000);
		try {
			System.out.println("Question Index>>>>" + qusIndex + " and place holder name>>>>" + placeHolderName
					+ " and its value>>>>" + value);
			driver.findElement(By.xpath(
					sub_ans_text.replace("%s", String.valueOf(qusIndex)).replace("%placeholder", placeHolderName)))
					.click();
			driver.findElement(By.xpath(
					sub_ans_text.replace("%s", String.valueOf(qusIndex)).replace("%placeholder", placeHolderName)))
					.clear();
			driver.findElement(By.xpath(
					sub_ans_text.replace("%s", String.valueOf(qusIndex)).replace("%placeholder", placeHolderName)))
					.sendKeys(value);
			if (placeHolderName.trim().equalsIgnoreCase("Zip Code (Optional)")) {
//				utill.waitForElements(driver.findElement(By.xpath(".//*[@class='loader-holder']/p")));
//				Thread.sleep(10000);
				utill.waitTimeForSpinner(driver);
				state = driver.findElement(By.xpath("(.//*[@class='zipcode-information']//button)[1]"))
						.getAttribute("value");
				city = driver.findElement(By.xpath("(.//*[@class='zipcode-information']//button)[2]"))
						.getAttribute("value");
				System.out.println("ta%State_" + state + ":ta%City_" + city);

				return "ta%State_" + state + ":ta%City_" + city;
			}
			if (placeHolderName.trim().equalsIgnoreCase("Zip Code")) {
				utill.waitTimeForSpinner(driver);
			}
		} catch (Exception e) {
			addExceptionToReport(
					"Expected placeholder " + placeHolderName + " is not displayed under Question whose Index is "
							+ qusIndex + " and Exception is " + e.getMessage().toString(),
					"", "");
		}
		return null;
	}

private String getRadioButtonState(WebDriver driver, String xpathExpression) throws Exception{
		try {		

			return driver.findElement(By.xpath(xpathExpression)).getAttribute("class");
		} catch (Exception e) {
			addExceptionToReport("Radio Button " + xpathExpression + " is not displayed.", "", "");
		}
		return "";
	}
	
	private void selectRadioButton(WebDriver driver, String xpathExpression) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			System.out.println("Enteerd selectRadioButton");
			Actions action = new Actions(driver);
//			wait.until(ExpectedConditions.visibilityOf(
//					driver.findElement(By.xpath(xpathExpression))));
			Thread.sleep(2000);
			action.moveToElement(driver.findElement(By.xpath(xpathExpression))).click().build().perform();
//			utill.waitTimeForSpinner(driver);
			
			// driver.findElement(By.xpath(xpathExpression)).click();
		} catch (Exception e) {
			addExceptionToReport("Radio Button " + xpathExpression + " is not displayed.", "", "");
		}
	}

	private void selectValuesInDropDownList(WebDriver driver, Integer qusIndex, String placeholder,
			String expectedValue) throws Exception {
		String revisedDDObject="";
		String searchInputField="";
		if(KWVariables.getVariables().get("Env").equalsIgnoreCase("taurus")||KWVariables.getVariables().get("Env").equalsIgnoreCase("pisces")){
			System.out.println("Dropdown>>"+urla_dd.replace("%s", String.valueOf(qusIndex)).replace("%t",
					String.valueOf(placeholder)));
			 revisedDDObject = urla_dd.replace("%s", String.valueOf(qusIndex)).replace("%t",
					String.valueOf(placeholder.trim()));
			// searchInputField = revisedDDObject + "//..//ul";
		
				driver.findElement(By.xpath(revisedDDObject)).click();
				System.out.println("Clicked the dropdown button");
//				Thread.sleep(2000);
			try{
				try {
					System.out.println("sub_ans_dropdown_value1>>>>"+sub_ans_dropdown_value1.replace("%s", String.valueOf(qusIndex)).replace("%val", expectedValue.trim()));
					driver.findElement(By.xpath(sub_ans_dropdown_value1.replace("%s", String.valueOf(qusIndex)).replace("%val", expectedValue.trim()))).click();
					System.out.println("Clicked the dropdown option");
				} catch (Exception e) {
					addExceptionToReport("Expected value " + expectedValue
							+ " is not displayed in drop down list under place holder " + placeholder, "", "");
				}
			} catch (Exception e) {
					addExceptionToReport("Placeholder " + placeholder + " is not displayed under Question whose Index is  "
							+ String.valueOf(qusIndex), "", "");
			}
		}
//		else{
//		System.out.println("Inside selectValuesInDropDownList>>>");
//		 revisedDDObject = dd.replace("%s", String.valueOf(qusIndex)).replace("%t",
//				String.valueOf(placeholder.toLowerCase()));
//		 searchInputField = revisedDDObject + "//following-sibiling::ul/div/input";
//		 try {
//				Thread.sleep(2000);
//				try {
//					driver.findElement(By.xpath(sub_ans_dropdown_value1.replace("%s", String.valueOf(qusIndex)).replace("%val", expectedValue.trim()))).click();
//				} catch (Exception e) {
//					addExceptionToReport("Expected value " + expectedValue
//							+ " is not displayed in drop down list under place holder " + placeholder, "", "");
//				}
//			} catch (Exception e) {
//				addExceptionToReport("Placeholder " + placeholder + " is not displayed under Question whose Index is  "
//						+ String.valueOf(qusIndex), "", "");
//			}

//		}
	//	System.out.println("Came out of radio button>>");
	}

	private void selectRelativeValuesInDropDownList(WebDriver driver, Integer qusIndex, String relativeplaceholder,
			String expectedValue) throws Exception {
		// to uniqely identify the value which we are passing for relative
		// dropdown -rdd
		relativeplaceholder = relativeplaceholder.replace("?", "");
		String revisedDDObject = rdd.replace("%s", String.valueOf(qusIndex)).replace("%p", relativeplaceholder);
		// String searchInputField =
		// revisedDDObject+"//following-sibiling::ul/div/input";
		try {
			driver.findElement(By.xpath(revisedDDObject)).click();
			Thread.sleep(2000);
			try {
				
				int index=driver.findElements(By.xpath(
						sub_ans_dropdown_value2.replace("%p", relativeplaceholder).replace("%val", expectedValue)))
						.size();
				System.out.println("INDEX>>>>"+index);
				System.out.println("SUBSSS>>>"+(sub_ans_dropdown_value2+"[%index]").replace("%p", relativeplaceholder).replace("%val", expectedValue).replace("%index",String.valueOf(index)));
				driver.findElement(By.xpath(
						(sub_ans_dropdown_value2+"[%index]").replace("%p", relativeplaceholder).replace("%val", expectedValue).replace("%index",String.valueOf(index))))
						.click();
			
			} catch (Exception e) {
				addExceptionToReport("Expected value " + expectedValue
						+ " is not displayed in drop down list under place holder " + relativeplaceholder, "", "");
			}
		} catch (Exception e) {
			addExceptionToReport("Placeholder " + relativeplaceholder
					+ " is not displayed under Question whose Index is  " + String.valueOf(qusIndex), "", "");
		}
	}

	private void clickOnCheckBox(WebDriver driver, String placeholder) {
		driver.findElement(By.xpath(sub_ans_checkbox.replace("%labeltext", placeholder))).click();
	}

	private void clickOnCheckBox(WebDriver driver, String placeholder, Integer index) throws Exception {
		try {
			System.out.println("CheckBox Xpath:"
					+ sub_ans_checkbox1.replace("%s", String.valueOf(index)).replace("%labeltext", placeholder));
//			Util.click(sub_ans_checkbox1.replace("%s", String.valueOf(index)).replace("%labeltext", placeholder));
			driver.findElement(By.xpath(sub_ans_checkbox1.replace("%s", String.valueOf(index)).replace("%labeltext", placeholder))).click();
		} catch (Exception e) {
			addExceptionToReport("Expected check box " + placeholder + " is not selected.Its exception is "
					+ e.getStackTrace().toString(), "", "");
		}

	}
	
	private void selectRadioButtonOption(WebDriver driver, Integer qusIndex, String expectedValue) throws Exception {
		try {
			driver.findElement(By.xpath(sub_ans_radiobutton.replace("%s", String.valueOf(qusIndex))
					.replace("%radioButtonName", expectedValue))).click();
		} catch (Exception e) {
			addExceptionToReport(
					"Radio Button option " + expectedValue + " under question index " + qusIndex + " selection failed.",
					"", "");
		}

	}

	private void setValuesInDateField(WebDriver driver, String placeholder, String expectedValue) throws Exception {
		try {
			driver.findElement(By.xpath(Sub_ans_datepicker.replace("%datelabel", placeholder))).sendKeys(expectedValue);
		} catch (Exception e) {
			addExceptionToReport("Setting values in date field " + placeholder + " failed.", "", "");
		}

	}

	public HashMap<String, String> buildSummarySheetMap(HashMap<String, String> dataMap, String[] expectedQtns,
			String sheetName, String expectedQtnAnswers) throws Exception {
		System.out.println("-------------------entered buildSummarySheetMap--------------------");
		String quesAnsString = "";
		switch (sheetName.toLowerCase().trim()) {
		case "getstarted":
			quesAnsString = step.getDataValue("question_ans");
			break;
		case "property":
			quesAnsString = step.getDataValue("PropertyQuestionAnswer");
			System.out.println("quesAnsString:" + quesAnsString);
			break;

		case "realestate":
			quesAnsString = step.getDataValue("RealEstateQuestionAnswer");
			break;

		case "declarations":
			quesAnsString = expectedQtnAnswers;
			break;

		case "assets":
			quesAnsString = step.getDataValue("1003_Asset_Manual");
			break;

		case "income":
			quesAnsString = step.getDataValue("1003_Income_Business");
			break;

		default:
			break;
		}

		HashMap<String, String> masterMap = new HashMap<String, String>();
		HashMap<String, String> summarySheetMap = new HashMap<String, String>();
		System.out.println("expectedQtns>>"+expectedQtns);
		for (String qtn : expectedQtns) {
			System.out.println("qtn>>>>" + qtn);
			String question = qtn.split(":")[0];
			System.out.println("question>>"+question);
			String answer = dataMap.get(question);
			System.out.println("answer>>"+answer);
			HashMap<String, String> expectedAnswers = buildAnswerMap(qtn.split(":")[1], answer);//buildAnswerMap(question, answer);
			Set<String> expectedAnswer = expectedAnswers.keySet();
			if (sheetName.trim().equalsIgnoreCase("getstarted") && qtn.contains("My name is")) {
				System.out.println("First name in summar>>>>>" + fname);
				if (fname != null) {
					expectedAnswers.put("First name", fname);
				} else {
					System.out.println(expectedAnswers.get("First name"));
				}

			}

			for (String ans : expectedAnswer) {
				System.out.println("ans??????" + ans);
				if (ans.startsWith("Qtn")) {
					String expectedKey = "Qtn_" + qtn.split(":")[0];
					System.out.println("Expceted Key>>>"+expectedKey+"Map Value>>"+expectedAnswers.get(ans));
					summarySheetMap.put(qtn.split(":")[1].trim(),expectedAnswers.get(ans));
				} else if (question.contains("contact details")||(question.contains("Spouse Contact Details"))) {

					summarySheetMap.put(qtn.split(":")[1].trim(), getContactNumber(expectedAnswers));
				} else if (question.contains("located") ) {//|| question.contains("asset type")
					if (sheetName.trim().equalsIgnoreCase("REALESTATE")) {//|| sheetName.trim().equalsIgnoreCase("ASSETS")
						masterMap = buildDataMap(quesAnsString);
						System.out.println("Question under real estate>>>>" + question);
						System.out.println("quesAnsString under real estate>>>>" + quesAnsString);
						for (String key1 : masterMap.keySet()) {
							System.out.println("under real estate keys>>>>>" + key1);
						}
						answer = masterMap.get(question);
						summarySheetMap.put(qtn.split(":")[1].split("&")[0].trim(),
								buildAnswerMap(question, answer).get(qtn.split(":")[1].split("&")[1]));
					} else {
						summarySheetMap.put(qtn.split(":")[1].trim(),
								expectedAnswers.get("State") + ", " + expectedAnswers.get("City"));
					}

				} 
				else if(qtn.contains("&")){
					summarySheetMap.put(qtn.split(":")[1].split("&")[0].trim(),expectedAnswers.get(qtn.split(":")[1].split("&")[1].trim()));
					System.out.println("TEST 1 KEY>>>>"+qtn.split(":")[1].split("&")[0].trim()+"Value1>>>>"+expectedAnswers.get(qtn.split(":")[1].split("&")[1].trim()));
				}
				else{
					summarySheetMap.put(qtn.split(":")[1].split("&")[0].trim(),expectedAnswers.get(qtn.split(":")[1].split("&")[0].trim()));
					System.out.println("TEST 2 KEY>>>>"+qtn.split(":")[1].split("&")[0].trim()+"Value1>>>>"+expectedAnswers.get(qtn.split(":")[1].split("&")[0].trim()));
				}
			}
		}

		return summarySheetMap;
		/*
		 * Set<String> summarySheetValues = summarySheetMap.keySet(); for(String
		 * summarySheet:summarySheetValues){ System.out.println("Key Namr>>>>"
		 * +summarySheet+" and it values are>>>>"+summarySheetMap.get(
		 * summarySheet)); }
		 */
	}

	public void compareDisplayedFieldValue(WebDriver driver, String attribute, int qusIndex,
			HashMap<String, String> expectedMap, String qustion) throws Exception {
		String val = expectedMap.get(qustion);
		boolean isDownPaymentOptionDisplayed = qustion.contains("I can put a down payment of");
		boolean isQuestionRelatedToSpouse = qustion.equalsIgnoreCase("Provide spouse details");
		switch (attribute.toLowerCase()) {
		case "radiobtn":
			String[] sub_fields = val.split(":");
			String radioButtonXPath = radioButtonAns.replace("%s", String.valueOf(qusIndex)).replace("%v", val);
			if (isRadioButtonSelected(driver, radioButtonXPath) == false) {
				addExceptionToReport(
						"Radio Button " + val + " under question whose index is " + qusIndex + " is not selected.", "",
						"");
			}
			if (sub_fields.length > 1) {
				compareMultipleOptionValuesUnderQuestion(driver, expectedMap, qustion, qusIndex, val,
						expectedMap.size());
			}
			break;
		case "currency":
			compareValuesInCurrencyField(driver, qusIndex, val, isDownPaymentOptionDisplayed);
			clickOnOkButtonAfterEachQuestion(driver, qusIndex, expectedMap.size());
			break;
		case "dateformat":
			System.out.println("entered dateformat ");
			compareValuesInDateField(driver, qusIndex, val);
			clickOnOkButtonAfterEachQuestion(driver, qusIndex, expectedMap.size());
			break;
		case "location":
			System.out.println("entered location ");
			compareValuesInDropDownField(driver, qusIndex, val);
			clickOnOkButtonAfterEachQuestion(driver, qusIndex, expectedMap.size());
			break;

		default:
			if (isQuestionRelatedToSpouse) {
				driver.switchTo().activeElement();
				driver.findElement(By.xpath(spouseQtnModalWindow)).click();
				// Thread.sleep(2000);
				driver.switchTo().defaultContent();
			} else {
				compareMultipleOptionValuesUnderQuestion(driver, expectedMap, qustion, qusIndex, val,
						expectedMap.size());
				// clickOnOKButtonIfExists(driver,qusIndex,expectedMap.size());
			}

			break;
		}

	}

	private void compareMultipleOptionValuesUnderQuestion(WebDriver driver, HashMap<String, String> dataMap,
			String question, Integer qusIndex, String options, Integer totalQtnCount) throws Exception {
		String[] optionList = options.split(":");
		String refFieldType = "";
		for (String option : optionList) {
			String[] field_type = option.trim().split("%");
			String[] placeholder_value = (field_type[1].trim()).split("_");
			String placeholder = placeholder_value[0].trim();
			String value = placeholder_value[1];
			String stateCity = "";
			String fieldType = field_type[0].trim().toLowerCase();
			System.out.println("Place holder name>>>>" + placeholder + " and place holder value>>>>" + value
					+ " field tupe>>>>" + fieldType);
			if (fieldType.equalsIgnoreCase("RB")) {
				fieldType = "radiobutton";
			}
			refFieldType = fieldType;

			switch (fieldType) {
			case "ta":
				compareExpectedWithDisplayedValueForTextField(driver, qusIndex, placeholder, value);
				break;
			case "rb":
				boolean isRadioButtonSelected = isRadioButtonSelected(driver,
						radioButtonAns.replace("%s", String.valueOf(qusIndex)).replace("%v", value));
				if (isRadioButtonSelected == false) {
					addExceptionToReport(
							"Radio Button " + value + " is not selected under the question whose Index is " + qusIndex,
							"", "");
				}
				break;
			case "dd":
				compareValuesInDropDownList(driver, qusIndex, placeholder, value);
				break;
			case "cb":
				clickOnCheckBox(driver, value);
				break;
			case "radiobutton":
				compareRadioButtonOptionValue(driver, qusIndex, value);
				break;
			case "datepicker":
				compareValuesInDateField(driver, placeholder, value);
				break;
			case "fromto":
				compareValuesInDateField(driver, placeholder, value);
				break;
			default:
				break;
			}

		}

		if (refFieldType.trim().equalsIgnoreCase("radiobutton") == false
				|| refFieldType.trim().equalsIgnoreCase("rb") == false) {
			System.out.println("Refernce fields type>>>>" + refFieldType);
			Thread.sleep(1000);
			clickOnOkButtonAfterEachQuestion(driver, qusIndex, totalQtnCount);
		}

	}

	private boolean isRadioButtonSelected(WebDriver driver, String xpathExpression) throws Exception {
		try {
			String displayedAttr = driver.findElement(By.xpath(xpathExpression)).getAttribute("class");
			driver.findElement(By.xpath(xpathExpression)).click();
			if (displayedAttr.contains("active") == false) {
				return false;
			}

		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private void compareValuesInCurrencyField(WebDriver driver, Integer qusIndex, String value,
			boolean isDownPaymentOptionDisplayed) throws Exception {
		try {
			String displayedValue = driver.findElement(By.xpath(inputText.replace("%s", String.valueOf(qusIndex))))
					.getAttribute("value");
			if (displayedValue.trim().equalsIgnoreCase(value.trim())) {
				addExceptionToReport("Expected currency value " + value
						+ " is not displayed under question whose Index is " + qusIndex, "", "");
			}
		} catch (Exception e) {
			addExceptionToReport("Currency Field under question Index " + qusIndex + " is not displayed.", "", "");
		}

	}

	private void compareValuesInDateField(WebDriver driver, Integer qusIndex, String value) throws Exception {

		if (driver.findElement(By.xpath(dateOfBirth.replace("%s", String.valueOf(qusIndex)))).getAttribute("value")
				.trim().equalsIgnoreCase(value) == false) {
			addExceptionToReport(
					"Expected Date Value " + value + " is not displayed under question whose index is " + qusIndex, "",
					"");
		}

	}

	private void compareValuesInDropDownField(WebDriver driver, Integer qusIndex, String values) throws Exception {
		String[] valueArray = values.split(":");
		for (String value : valueArray) {
			String fieldName = value.split("%")[1].split("_")[0];
			String fieldValue = value.split("%")[1].split("_")[1];
			String displayedValue = driver.findElement(By.xpath(dropDownFieldObject
					.replace("%s", String.valueOf(qusIndex)).replace("%v", String.valueOf(fieldName))))
					.getAttribute("value");
			if (displayedValue.trim().equalsIgnoreCase(fieldValue) == false) {
				addExceptionToReport("Expected value " + fieldValue + " is not matching with displayed value "
						+ displayedValue + " under the question whose Index " + qusIndex, "", "");
			}
		}
	}

	private void compareExpectedWithDisplayedValueForTextField(WebDriver driver, Integer qusIndex,
			String placeHolderName, String value) throws Exception {
		String displayedValue = null;
		try {
			if (placeHolderName.trim().equalsIgnoreCase("zipcode")
					|| placeHolderName.trim().equalsIgnoreCase("Employer phone (Optional)")
					|| placeHolderName.trim().equalsIgnoreCase("Base income")
					|| placeHolderName.trim().equalsIgnoreCase("Overtime (Optional)")
					|| placeHolderName.trim().equalsIgnoreCase("Bonus (Optional)")
					|| placeHolderName.trim().equalsIgnoreCase("Commission (Optional)")
					|| placeHolderName.trim().equalsIgnoreCase("Other (Optional)")
					|| placeHolderName.trim().equalsIgnoreCase("Home phone (Optional)")
					|| placeHolderName.trim().equalsIgnoreCase("Mobile number")) {
				displayedValue = driver.findElement(By.xpath(
						sub_ans_text.replace("%s", String.valueOf(qusIndex)).replace("%placeholder", placeHolderName)))
						.getAttribute("value");
			} else {
				displayedValue = driver.findElement(By.xpath(
						sub_ans_text.replace("%s", String.valueOf(qusIndex)).replace("%placeholder", placeHolderName)))
						.getAttribute("title");
			}

			if (displayedValue.trim().equalsIgnoreCase(value.trim()) == false) {
				addExceptionToReport("Expected value " + value + " is not matching with the displayed value "
						+ displayedValue + " for the field " + placeHolderName + " under the question whose index is "
						+ String.valueOf(qusIndex), "", "");
			}
		} catch (Exception e) {
			addExceptionToReport(
					"Expected placeholder " + placeHolderName + " is not displayed under Question whose Index is "
							+ qusIndex + " and Exception is " + e.getMessage().toString(),
					"", "");
		}

	}

	private void compareValuesInDropDownList(WebDriver driver, Integer qusIndex, String placeholder,
			String expectedValue) throws Exception {
		String revisedDDObject = dd.replace("%s", String.valueOf(qusIndex)).replace("%t",
				String.valueOf(expectedValue));
		try {
			if (driver.findElement(By.xpath(revisedDDObject)).isDisplayed() == false) {
				addExceptionToReport("Placeholder " + placeholder + " is not displayed under Question whose Index is  "
						+ String.valueOf(qusIndex), "", "");
			}
		} catch (Exception e) {
			addExceptionToReport("Placeholder " + placeholder + " is not displayed under Question whose Index is  "
					+ String.valueOf(qusIndex), "", "");
		}
	}

	private void compareRadioButtonOptionValue(WebDriver driver, Integer qusIndex, String expectedValue)
			throws Exception {
		try {
			if (isRadioButtonSelected(driver, sub_ans_radiobutton.replace("%s", String.valueOf(qusIndex))
					.replace("%radioButtonName", expectedValue)) == false) {
				addExceptionToReport("Radio Button " + expectedValue + " is not selected under Question " + qusIndex,
						"", "");
			}
		} catch (Exception e) {
			addExceptionToReport(
					"Radio Button option " + expectedValue + " under question index " + qusIndex + " selection failed.",
					"", "");
		}

	}

	private void compareValuesInDateField(WebDriver driver, String placeholder, String expectedValue) throws Exception {
		try {
			String displayedValue = driver.findElement(By.xpath(Sub_ans_datepicker.replace("%datelabel", placeholder)))
					.getAttribute("value");
			if (displayedValue.trim().equalsIgnoreCase(expectedValue) == false) {
				addExceptionToReport("Expected Date " + expectedValue + " is not matching with displayed value "
						+ displayedValue + " under the field " + placeholder, "", "");
			}
		} catch (Exception e) {
			addExceptionToReport("Setting values in date field " + placeholder + " failed.", "", "");
		}

	}

	public void compareQuestionAnswerIn1003GetStarted() throws InterruptedException, TwfException, Exception {
		System.out.println("compareQuestionAnswerIn1003GetStarted:");
		String quesAnsString = step.getDataValue("question_ans");
		isNewUser = step.getDataValue("NewUser");
		verifyQuestionAnswerIn1003(quesAnsString);

	}

	public void compareQuestionAnswerIn1003Property() throws InterruptedException, TwfException, Exception {
		System.out.println("compareQuestionAnswerIn1003GetStarted:");
		String quesAnsString = step.getDataValue("PropertyQuestionAnswer");
		verifyQuestionAnswerIn1003(quesAnsString);

	}

	public void compareQuestionAnswerIn1003Income() throws InterruptedException, TwfException, Exception {
		System.out.println("compareQuestionAnswerIn1003GetStarted:");
		String quesAnsString = step.getDataValue("IncomeQuestionAnswer");
		verifyQuestionAnswerIn1003(quesAnsString);

	}

	public void compareQuestionAnswerIn1003Assets() throws InterruptedException, TwfException, Exception {
		System.out.println("compareQuestionAnswerIn1003GetStarted:");
		String quesAnsString = step.getDataValue("1003_Asset_Manual");
		verifyQuestionAnswerIn1003(quesAnsString);

	}

	/**
	 * Method Name: validateQuestionAnswerIn1003 Purpose:
	 * 
	 * @throws Exception
	 */
	public HashMap<String, String> verifyQuestionAnswerIn1003(String quesAnsString)
			throws InterruptedException, TwfException, Exception {
		HashMap<String, String> dataMap = new HashMap<String, String>();
		dataMap = buildDataMap(quesAnsString);
		System.out.println("after datamap");
		HashMap<String, String> reBuildDataMap = compareQuestion2AnswerIn1003(dataMap);

		if (status.contains("false")) {
			System.out.println(status);
			addExceptionToReport("Expected Question in not enabled.Hence cannot move forward.", "", status);

		}

		// test code
		return reBuildDataMap;
	}

	public void verifyUSPSValidation(String expValue) throws Throwable{
		System.out.println("USPS Expected"+expValue);
		System.out.println("USPS Actual"+uspsFlag);
		if(uspsFlag.equalsIgnoreCase(expValue)){
			System.out.println("USPS Validated");
			uspsFlag="false";
		}
		else{
			uspsFlag="false";
			addExceptionToReport("USPS Validation Error", "", "false");
		}
	}
	
	public HashMap<String, String> compareQuestion2AnswerIn1003(HashMap<String, String> expectedMap)
			throws InterruptedException, Exception {
		WebDriver driver = DriverFactory.getDriver();

		HashMap<String, String> expectedSelectAttributeMap = new HashMap<String, String>();
		// String status = "";

		int QusIndexlength = expectedMap.size();
		int blankCount = 0;
		String attribute = null;
		System.out.println("QusIndexlength size is  " + QusIndexlength);
		Util util = new Util();
		util.scrollToElement(driver
				.findElement(By.xpath("(//div[@class='form-container tf-block-0 active-section']//div/label)[1]")));
		for (int qusIndex = 0; qusIndex < QusIndexlength; qusIndex++) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, 100);
				wait.until(ExpectedConditions.visibilityOf(
						driver.findElement(By.xpath(fieldAtttibute.replace("%s", String.valueOf(qusIndex))))));

				attribute = driver.findElement(By.xpath(fieldAtttibute.replace("%s", String.valueOf(qusIndex))))
						.getAttribute("data-type").trim();

				String qustion = driver.findElement(By.xpath(Qustion.replace("%s", String.valueOf(qusIndex)))).getText()
						.trim();
				compareDisplayedFieldValue(driver, attribute, qusIndex, expectedMap, qustion);

			} catch (Exception e) {
				System.out.println(" Caught Exception " + e);
				/*
				 * System.out.println(" blankCount  -- " + blankCount);
				 * blankCount++; expectedSelectAttributeMap
				 * =selectAttribute(driver, attribute, qusIndex, expectedMap,
				 * "blank" + blankCount);
				 */
				status = "false";

				System.out.println(" e.printStackTrace();");
				e.printStackTrace();
				addExceptionToReport(" Expected question is not visible hence can not move forward    ", " ", " ");

			}
			status = "true";
		}

		System.out.println("status = " + status);
		status = "true";
		return expectedSelectAttributeMap;

	}

	public void switchToDefaultWindow() throws TwfException, InterruptedException {
		System.out.println(" inside switchToDefaultWindow ");
		WebDriver driver = DriverFactory.getDriver();
		driver.switchTo().defaultContent();
		Thread.sleep(4000);

	}

	public void validateEditedValues(WebDriver driver, String colName) throws Exception {
		HashMap<String, String> testMap = new HashMap<String, String>();
		testMap = buildDataMap(step.getDataValue(colName));
		fetchSectionData(testMap);
	}

	public void fetchSectionData(HashMap<String, String> expectedTestData) throws Exception {
		WebDriver driver = DriverFactory.getDriver();
		utill.scrollToElement(driver.findElement(By.tagName("header")));
		String qtnLabelobject = "(//div[@class='ps-content']/div[%s]//div[@class='row']//div[@class='form-group']//label)[1]";
		String qtnAtrribute = "(//div[@class='ps-content']/div[%s]//div[@class='row']//div[@class='form-group']//label)[1]/parent::div";
		String radioButtonObject = "(//div[@class='ps-content']/div[%s]//div[@class='row']//div[@class='form-group']//label)[1]/following-sibling::radio-button-selection//button[@class[contains(.,'active')]]";
		String radioButtonDisabledObject = "(//div[@class='ps-content']/div[%s]//div[@class='row']//div[@class='form-group']//label)[1]/following-sibling::dynamic-form//button[@class[contains(.,'active')]]";
		String formButtonObject = "((//div[@class='ps-content']/div[%s]//div[@class='row']//div[@class='form-group']//label)[1]/parent::div[1]//div[@class[contains(.,'col')]])[1]//button[@class[contains(.,'active')]]";
		int noOfQuestions = driver.findElements(By.xpath("//div[@class='ps-content']/div")).size();

		for (int qtnIndex = 1; qtnIndex < noOfQuestions; qtnIndex++) {
			String radioButtonValue = "";
			utill.scrollToElement(driver.findElement(By.xpath(qtnLabelobject.replace("%s", String.valueOf(qtnIndex)))));
			String qtnLabel = driver.findElement(By.xpath(qtnLabelobject.replace("%s", String.valueOf(qtnIndex))))
					.getText();
			String attribute = driver.findElement(By.xpath(qtnAtrribute.replace("%s", String.valueOf(qtnIndex))))
					.getAttribute("class");
			if (attribute.contains("radio")) {
				utill.scrollToElement(
						driver.findElement(By.xpath(radioButtonObject.replace("%s", String.valueOf(qtnIndex)))));
				radioButtonValue = driver
						.findElement(By.xpath(radioButtonObject.replace("%s", String.valueOf(qtnIndex)))).getText();
				if (!radioButtonValue.trim().equalsIgnoreCase(expectedTestData.get(qtnLabel).trim())) {
					addExceptionToReport(
							"Edited value " + expectedTestData.get(qtnLabel) + " is not matching with displayed value "
									+ radioButtonValue + " under the question " + qtnLabel,
							"", "");
				}
			} else if (qtnLabel.equalsIgnoreCase("In my current property, I")) {
				utill.scrollToElement(driver
						.findElement(By.xpath(radioButtonDisabledObject.replace("%s", String.valueOf(qtnIndex)))));
				radioButtonValue = driver
						.findElement(By.xpath(radioButtonDisabledObject.replace("%s", String.valueOf(qtnIndex))))
						.getText();
				if (!radioButtonValue.trim().equalsIgnoreCase(expectedTestData.get(qtnLabel).trim())) {
					addExceptionToReport(
							"Edited value " + expectedTestData.get(qtnLabel) + " is not matching with displayed value "
									+ radioButtonValue + " under the question " + qtnLabel,
							"", "");
				}
			} else if (attribute.contains("form")) {
				String multioptions = expectedTestData.get(qtnLabel);
				int noOfFields = getNumberOfFieldsUnderEachSection(driver, qtnIndex);
				if (!multioptions.contains("_")) {
					multioptions = qtnLabel + ";" + multioptions;
				}
				getFieldLabel(driver, qtnIndex, noOfFields, multioptions);
			}
		}

		try {
			utill.scrollToElement(driver.findElement(By.xpath(
					"//div[@class[contains(.,'active')]]//div[@class='form-group']//form//button[@class[contains(.,'submit')]]")));
			Thread.sleep(3000);

			// driver.findElement(By.xpath("//div[@class='ps-content']/div["+String.valueOf(noOfQuestions-1)+"]//button[@type='submit']")).click();
			System.out.println("Entering click");

			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(By.xpath("//div[@class='ps-content']/div["
					+ String.valueOf(noOfQuestions - 1) + "]//button[@type='submit']"))).doubleClick().build()
					.perform();
			try {
				if (driver.findElement(By.xpath("//button[contains(.,'m Done')]")).isDisplayed()) {

				}
			} catch (Exception e1) {
				System.out.println("Entering clicking button");

				action.sendKeys(Keys.ENTER).build().perform();
			}

			// driver.findElement(By.xpath("//div[@class[contains(.,'active')]]//div[@class='form-group']//form//button[@class[contains(.,'submit')]]")).click();
			Thread.sleep(3000);
		} catch (Exception e) {
			if (driver.findElement(By.xpath(qtnAtrribute.replace("%s", String.valueOf(noOfQuestions - 1))))
					.getAttribute("class").contains("form")) {
				// driver.findElement(By.xpath(formButtonObject.replace("%s",
				// String.valueOf(noOfQuestions-1)))).click();
				driver.findElement(By.xpath("//div[@class='ps-content']/div[" + String.valueOf(noOfQuestions - 1)
						+ "]//button[@type='submit']")).click();
				System.out.println("Entering click");
				try {
					if (driver.findElement(By.xpath("//button[contains(.,'m Done')]")).isDisplayed()) {

					}
				} catch (Exception e1) {
					System.out.println("Entering clicking button");
					Actions action = new Actions(driver);
					action.sendKeys(Keys.ENTER).build().perform();
				}

			} else {
				driver.findElement(By.xpath(radioButtonObject.replace("%s", String.valueOf(noOfQuestions - 1))))
						.click();
			}
		}
	}

	private Integer getNumberOfFieldsUnderEachSection(WebDriver driver, int sectionIdex) {
		String qtnLabelobject = "(//div[@class='ps-content']/div[%s]//div[@class='row']//div[@class='form-group']//label)[1]/parent::div[1]//div[@class[contains(.,'col')]]";
		return driver.findElements(By.xpath(qtnLabelobject.replace("%s", String.valueOf(sectionIdex)))).size();

	}

	private void getFieldLabel(WebDriver driver, int sectionIndex, int fieldCount, String options) throws Exception {
		String fieldLabelObject = "((//div[@class='ps-content']/div[%s]//div[@class='row']//div[@class='form-group']//label)[1]/parent::div[1]//div[@class[contains(.,'col')]])[%f]";
		String textFieldName = "((//div[@class='ps-content']/div[%s]//div[@class='row']//div[@class='form-group']//label)[1]/parent::div[1]//div[@class[contains(.,'col')]])[%f]//input";
		String buttonObject = "((//div[@class='ps-content']/div[%s]//div[@class='row']//div[@class='form-group']//label)[1]/parent::div[1]//div[@class[contains(.,'col')]])[%f]//button[@class[contains(.,'active')]]";
		String buttonLabelObject = "((//div[@class='ps-content']/div[%s]//div[@class='row']//div[@class='form-group']//label)[1]/parent::div[1]//div[@class[contains(.,'col')]])[%f]//h6";
		String buttonLabel1Object = "//div[@class='ps-content']/div[%s]//div[@class='row']//div[@class='form-group']//label";
		String multipleFieldTypeObject = "(((//div[@class='ps-content']/div[%s]//div[@class='row']//div[@class='form-group']//label)[1]/parent::div[1]//div[@class[contains(.,'col')]])[%f]//div)[3]";
		String multipleFieldInputTypeObject = "(((//div[@class='ps-content']/div[%s]//div[@class='row']//div[@class='form-group']//label)[1]/parent::div[1]//div[@class[contains(.,'col')]])[%f]//div)[3]/input";
		String multipleFieldDropownTypeLabelObject = "(((//div[@class='ps-content']/div[%s]//div[@class='row']//div[@class='form-group']//label)[1]/parent::div[1]//div[@class[contains(.,'col')]])[%f]//div)[3]/preceding-sibling::div";
		String multipleFieldDropownTypeValueObject = "(((//div[@class='ps-content']/div[%s]//div[@class='row']//div[@class='form-group']//label)[1]/parent::div[1]//div[@class[contains(.,'col')]])[%f]//div)[3]/button";
		HashMap<String, String> expectedDataMap = new HashMap<String, String>();
		String[] toskipFieldNames = { "state", "city", "county (optional)" };
		List<String> toSkipList = Arrays.asList(toskipFieldNames);
		if (options.contains("_")) {
			String[] optionList = options.split(":");
			for (String option : optionList) {
				String[] field_type = option.trim().split("%");
				String[] placeholder_value = (field_type[1].trim()).split("_");
				String placeholder = placeholder_value[0].trim();
				String value = placeholder_value[1].trim();
				System.out.println("Place holder>>>>" + placeholder);
				expectedDataMap.put(placeholder, value);
			}
		} else {
			expectedDataMap.put(options.split(";")[0], options.split(";")[1]);
		}
		expectedDataMap.put("First name", fname);

		for (String key : expectedDataMap.keySet()) {
			System.out.println("Keys in map>>>>>>" + key + " and its values are >>>" + expectedDataMap.get(key));
		}

		System.out.println("Options>>>>" + options);

		System.out.println("Field Count>>>" + fieldCount);

		for (int fieldIndex = 1; fieldIndex <= fieldCount; fieldIndex++) {

			String attributes = utill.fetchAllAttributesOfWebElement(driver,
					driver.findElement(By.xpath(fieldLabelObject.replace("%s", String.valueOf(sectionIndex))
							.replace("%f", String.valueOf(fieldIndex)))));
			String fieldName = "";
			String fieldValue = "";
			System.out.println("attributes at level 1>>>>" + attributes);
			if (attributes.contains("data-type")) {
				String dataType = driver.findElement(By.xpath(fieldLabelObject
						.replace("%s", String.valueOf(sectionIndex)).replace("%f", String.valueOf(fieldIndex))))
						.getAttribute("data-type");
				System.out.println("Data Type>>>>0" + dataType);
				switch (dataType.toLowerCase()) {
				case "text":
					fieldName = driver.findElement(By.xpath(textFieldName.replace("%s", String.valueOf(sectionIndex))
							.replace("%f", String.valueOf(fieldIndex)))).getAttribute("placeholder");
					System.out.println("Field Name>>>>" + fieldName);
					if (fieldName.trim().equalsIgnoreCase("MM/DD/YYYY")) {
						fieldName = "I was born on";
					}
					fieldValue = driver.findElement(By.xpath(textFieldName.replace("%s", String.valueOf(sectionIndex))
							.replace("%f", String.valueOf(fieldIndex)))).getAttribute("value").replace("%", "");

					if (!fieldValue.trim().equalsIgnoreCase(expectedDataMap.get(fieldName))) {
						addExceptionToReport("Edited value " + expectedDataMap.get(fieldName)
								+ " is not matching with displayed value " + fieldValue + " for the field " + fieldName,
								"", "");
					}
					System.out.println("Tested value>>>>" + fieldValue + " and " + expectedDataMap.get(fieldName));
					break;

				case "radiobtn":
					try {
						utill.scrollToElement(driver
								.findElement(By.xpath(buttonLabelObject.replace("%s", String.valueOf(sectionIndex))
										.replace("%f", String.valueOf(fieldIndex)))));
						fieldName = driver.findElement(By.xpath(buttonLabelObject
								.replace("%s", String.valueOf(sectionIndex)).replace("%f", String.valueOf(fieldIndex))))
								.getText();
						utill.scrollToElement(
								driver.findElement(By.xpath(buttonObject.replace("%s", String.valueOf(sectionIndex))
										.replace("%f", String.valueOf(fieldIndex)))));
						fieldValue = driver.findElement(By.xpath(buttonObject
								.replace("%s", String.valueOf(sectionIndex)).replace("%f", String.valueOf(fieldIndex))))
								.getText().replace("%", "");
					} catch (Exception e) {
						utill.scrollToElement(driver
								.findElement(By.xpath(buttonLabel1Object.replace("%s", String.valueOf(sectionIndex))
										.replace("%f", String.valueOf(fieldIndex)))));
						fieldName = driver.findElement(By.xpath(buttonLabel1Object
								.replace("%s", String.valueOf(sectionIndex)).replace("%f", String.valueOf(fieldIndex))))
								.getText();
						fieldValue = driver.findElement(By.xpath(buttonObject
								.replace("%s", String.valueOf(sectionIndex)).replace("%f", String.valueOf(fieldIndex))))
								.getText().replace("%", "");
					}
					System.out.println("FIELD NAME>>>>" + fieldName + " and its value is>>>>" + fieldValue
							+ " under radio button");
					if (!fieldValue.trim().equalsIgnoreCase(expectedDataMap.get(fieldName).trim())) {
						addExceptionToReport("Edited value " + expectedDataMap.get(fieldName)
								+ " is not matching with displayed value " + fieldValue + " for the field " + fieldName,
								"", "");
					}
					break;

				case "phone":
					fieldName = driver.findElement(By.xpath(textFieldName.replace("%s", String.valueOf(sectionIndex))
							.replace("%f", String.valueOf(fieldIndex)))).getAttribute("placeholder");
					fieldValue = driver.findElement(By.xpath(textFieldName.replace("%s", String.valueOf(sectionIndex))
							.replace("%f", String.valueOf(fieldIndex)))).getAttribute("value");
					if (!fieldValue.trim().equalsIgnoreCase(expectedDataMap.get(fieldName).trim())) {
						addExceptionToReport("Edited value " + expectedDataMap.get(fieldName)
								+ " is not matching with displayed value " + fieldValue + " for the field " + fieldName,
								"", "");
					}
					break;
				case "checkbox":
					/*
					 * fieldName =
					 * driver.findElement(By.xpath(textFieldName.replace("%s",
					 * String.valueOf(sectionIndex)).replace("%f",
					 * String.valueOf(fieldIndex)))).getAttribute("placeholder")
					 * ; fieldValue =
					 * driver.findElement(By.xpath(textFieldName.replace("%s",
					 * String.valueOf(sectionIndex)).replace("%f",
					 * String.valueOf(fieldIndex)))).getAttribute("value");
					 * if(!fieldValue.trim().equalsIgnoreCase(expectedDataMap.
					 * get(fieldName).trim())){
					 * addExceptionToReport("Edited value "+expectedDataMap.get(
					 * fieldName)+" is not matching with displayed value "
					 * +fieldValue+" for the field "+fieldName, "", ""); }
					 */
				case "currency":
					System.out.println("Entered currency");
					try {
						fieldName = driver.findElement(By.xpath(textFieldName
								.replace("%s", String.valueOf(sectionIndex)).replace("%f", String.valueOf(fieldIndex))))
								.getAttribute("placeholder");
						System.out.println("Field Name>>>>" + fieldName);
						fieldValue = driver.findElement(By.xpath(textFieldName
								.replace("%s", String.valueOf(sectionIndex)).replace("%f", String.valueOf(fieldIndex))))
								.getAttribute("value");
						if (!fieldValue.trim().equalsIgnoreCase(expectedDataMap.get(fieldName.trim()))) {
							addExceptionToReport("Edited value " + expectedDataMap.get(fieldName)
									+ " is not matching with displayed value " + fieldValue + " for the field "
									+ fieldName, "", "");
						}
					} catch (Exception e) {

					}
					System.out.println("Tested value>>>>" + fieldValue + " and " + expectedDataMap.get(fieldName));
					break;

				default:
					break;
				}

			} else if (attributes.contains("role") || attributes.contains("aria")) {
				String classValue = driver.findElement(By.xpath(multipleFieldTypeObject
						.replace("%s", String.valueOf(sectionIndex)).replace("%f", String.valueOf(fieldIndex))))
						.getAttribute("class");
				switch (getAttributeDataType(classValue).toLowerCase()) {
				case "text":
					fieldName = driver.findElement(By.xpath(multipleFieldInputTypeObject
							.replace("%s", String.valueOf(sectionIndex)).replace("%f", String.valueOf(fieldIndex))))
							.getAttribute("placeholder");
					fieldValue = driver.findElement(By.xpath(multipleFieldInputTypeObject
							.replace("%s", String.valueOf(sectionIndex)).replace("%f", String.valueOf(fieldIndex))))
							.getAttribute("value");
					if (!fieldValue.trim().equalsIgnoreCase(expectedDataMap.get(fieldName).trim())) {
						addExceptionToReport("Edited value " + expectedDataMap.get(fieldName)
								+ " is not matching with displayed value " + fieldValue + " for the field " + fieldName,
								"", "");
					}
					break;

				case "dropdown":
					/*
					 * Thread.sleep(3000);
					 * utill.scrollToElement(driver.findElement(By.xpath(
					 * multipleFieldDropownTypeLabelObject.replace("%s",
					 * String.valueOf(sectionIndex)).replace("%f",
					 * String.valueOf(fieldIndex))))); fieldName =
					 * driver.findElement(By.xpath(
					 * multipleFieldDropownTypeLabelObject.replace("%s",
					 * String.valueOf(sectionIndex)).replace("%f",
					 * String.valueOf(fieldIndex)))).getText(); fieldValue =
					 * driver.findElement(By.xpath(
					 * multipleFieldDropownTypeValueObject.replace("%s",
					 * String.valueOf(sectionIndex)).replace("%f",
					 * String.valueOf(fieldIndex)))).getAttribute("value");
					 * if(toSkipList.contains(fieldName.toLowerCase())==false){
					 * if(!fieldValue.trim().equalsIgnoreCase(expectedDataMap.
					 * get(fieldName).trim())){
					 * addExceptionToReport("Edited value "+expectedDataMap.get(
					 * fieldName)+" is not matching with displayed value "
					 * +fieldValue+" for the field "+fieldName, "", ""); } }
					 */

					break;

				case "checkbox":
					/*
					 * fieldName =
					 * driver.findElement(By.xpath(multipleFieldInputTypeObject.
					 * replace("%s", String.valueOf(sectionIndex)).replace("%f",
					 * String.valueOf(fieldIndex)))).getAttribute("placeholder")
					 * ; fieldValue =
					 * driver.findElement(By.xpath(multipleFieldInputTypeObject.
					 * replace("%s", String.valueOf(sectionIndex)).replace("%f",
					 * String.valueOf(fieldIndex)))).getAttribute("value");
					 * if(!fieldValue.trim().equalsIgnoreCase(expectedDataMap.
					 * get(fieldName).trim())){
					 * addExceptionToReport("Edited value "+expectedDataMap.get(
					 * fieldName)+" is not matching with displayed value "
					 * +fieldValue+" for the field "+fieldName, "", ""); }
					 */
				default:
					break;
				}

			}

		}

	}
	// String fieldObject =
	// "(//div[@class='ps-content']/div[%s]//div[@class='row']//div[@class='form-group']//label)[1]/parent::div[%f]//div[@class[contains(.,'col')]]";
	// driver.findElement(arg0)

	// }

	private String getAttributeDataType(String dataType) {
		String revisedDataType = null;
		if (dataType.contains("text")) {
			revisedDataType = "text";
		} else if (dataType.trim().equalsIgnoreCase("radioBtn")) {
			revisedDataType = "radioBtn";
		} else if (dataType.equalsIgnoreCase("phone")) {
			revisedDataType = "phone";
		} else if (dataType.equalsIgnoreCase("location")) {
			revisedDataType = "location";
		} else if (dataType.trim().equalsIgnoreCase("dropdown")) {
			revisedDataType = "dropdown";
		} else if (dataType.trim().contains("input")) {
			revisedDataType = "text";
		} else if (dataType.trim().equalsIgnoreCase("dateFormat")) {
			revisedDataType = "text";
		} else if (dataType.trim().equalsIgnoreCase("checkbox")) {
			revisedDataType = "checkbox";
		}
		return revisedDataType;

	}

	// *******************************************************************************************************

	public void editAndValidateValuesIn1003(WebDriver driver, String sectionName) throws Exception {
		String referenceColumn = "";
		switch (sectionName.toLowerCase()) {
		case "getstarted":
			referenceColumn = "edit_GetStarted";
			break;

		case "property":
			referenceColumn = "edit_Property";
			break;

		case "declarations":
			referenceColumn = "edit_Declarations";
			break;

		case "income":
			referenceColumn = "edit_Income";
			break;

		case "assets":
			referenceColumn = "edit_Asset";
			break;

		case "realestate":
			referenceColumn = "edit_RealEstate";
			break;

		default:
			break;
		}
		System.out.println("Reference column>>>>>" + referenceColumn);
		Declarations dec = new Declarations();

		String quesAnsString = step.getDataValue(referenceColumn);
		System.out.println("Qtnanswer>>>>" + quesAnsString);
		if (referenceColumn.trim().equalsIgnoreCase("edit_Declarations")) {
			dec.validateDeclarationIn1003(quesAnsString);
		} else {
			HashMap<String, String> dataMap = validateQuestionAnswerIn1003(quesAnsString);
		}

		// Thread.sleep(6000);
		// getElementByUsing("1003_GetStarted_EditIcon").click();
		// try{
		// getElementByUsing("1003_GetStarted_EditIcon").click();
		// }catch(Exception e){
		//
		// }
		System.out.println("Clicked on Edit Icon button in summary page");
		Thread.sleep(6000);
		// validateEditedValues(driver,referenceColumn);
	}

	public void editQuestionAndAnswerFor1003GetStarted() throws InterruptedException, TwfException, Exception {
		WebDriver driver = DriverFactory.getDriver();
		/*
		 * String quesAnsString = step.getDataValue("edit_GetStarted");
		 * HashMap<String, String> dataMap =
		 * validateQuestionAnswerIn1003(quesAnsString); Thread.sleep(3000);
		 * getElementByUsing("1003_GetStarted_EditIcon").click();
		 * Thread.sleep(3000); validateEditedValues(driver,"edit_GetStarted");
		 */
		editAndValidateValuesIn1003(driver, "GetStarted");

	}

	public void editQuestionAndAnswerFor1003Property() throws InterruptedException, TwfException, Exception {
		WebDriver driver = DriverFactory.getDriver();
		editAndValidateValuesIn1003(driver, "Property");
	}

	public void editValidateDeclarationsQuestionAnswer() throws InterruptedException, TwfException, Exception {
		WebDriver driver = DriverFactory.getDriver();
		editAndValidateValuesIn1003(driver, "Declarations");
	}

	public void editValidateIncomeBusiness() throws InterruptedException, TwfException, Exception {
		WebDriver driver = DriverFactory.getDriver();
		editAndValidateValuesIn1003(driver, "income");
	}

	public void editValidateAssetQuestionAnswerIn1003() throws InterruptedException, Exception {

		/*
		 * System.out.println("inside editValidateAssetQuestionAnswerIn1003");
		 * String AssetQuestionAnswer = step.getDataValue("AnswerchangingFlow");
		 * 
		 * Thread.sleep(4000);
		 * validateQuestionAnswerIn1003(AssetQuestionAnswer);
		 */
		WebDriver driver = DriverFactory.getDriver();
		editAndValidateValuesIn1003(driver, "assets");

	}

	public void editValidateQuestionAnswerIn1003RealEstate() throws InterruptedException, TwfException, Exception {
		WebDriver driver = DriverFactory.getDriver();
		editAndValidateValuesIn1003(driver, "realestate");
	}

	public void validateEditedValuesTrail() throws Exception {
		WebDriver driver = DriverFactory.getDriver();
		String colName = "DeclarationQuestionAnswer";
		HashMap<String, String> testMap = new HashMap<String, String>();
		testMap = buildDataMap(step.getDataValue(colName));
		fetchSectionData(testMap);
	}

	public void editValidateQuestionAnswerIn1003CoApplicant() throws InterruptedException, TwfException, Exception {
		System.out.println("****************inside  edit coapplicant***************************");
		Thread.sleep(5000);
		String quesAnsString = step.getDataValue("edit_coapplicant");
		System.out.println("quesAnsString for coapplicant -> " + quesAnsString);
        
		// String fieldMap = step.getDataValue("SummaryFieldMapping");
		// System.out.println("fieldMap************* "+ fieldMap); \

		if (quesAnsString.contains("//")) {
			String[] coapplicantdata = quesAnsString.trim().split("//");
			System.out.println(coapplicantdata.toString());
			System.out.println("coapplicantdata.length: " + coapplicantdata.length);
			for (int index = 0; index < coapplicantdata.length; index++) {
				HashMap<String, String> dataMap = validateQuestionAnswerIn1003(coapplicantdata[index]);
				System.out.println("came out from filling data method");
				// Summary page takes time to get displayed so added sleep
				Thread.sleep(5000);
				// tenothreesectionsummary.validateCoapplicantGetStartedSummaryPage(dataMap,
				// index + 1, fieldMap);
			}
		} else {
			System.out.println(" Single Co-Applicant");
			validateQuestionAnswerIn1003(quesAnsString);
		}
	}
	
	public void editandValidateFilledAnswerIn1003CoApplicant() throws InterruptedException, TwfException, Exception {
		System.out.println("****************editandValidateFilledAnswerIn1003CoApplicant***************************");
		
		Thread.sleep(5000);
		String quesAnsString = step.getDataValue("edit_coapplicant");
		System.out.println("quesAnsString for coapplicant -> " + quesAnsString);
        
			System.out.println(" Single Co-Applicant");
			validateQuestionAnswerIn1003(quesAnsString);
//			isPrefilled=true;
//			validateQuestionAnswerIn1003(quesAnsString);
	}
	public String selectflowOption(String flow) throws TwfException, InterruptedException{
		WebDriver driver = DriverFactory.getDriver();
		System.out.println("inside selectflowOption");
		String dropdown="//div[contains(.,'I am Inte')]//following::button[@role='Dropdown' and @id='control.id']";
		driver.findElement(By.xpath(dropdown)).click();
		driver.findElement(By.xpath(dropdown+"/..//ul/li[contains(.,'%s')]".replace("%s", flow))).click();
		driver.findElement(By.xpath(dropdown+"/..//ul/li[contains(.,'%s')]".replace("%s", flow))).getText();
		Thread.sleep(6000);
		System.out.println("FLOWWW>>>"+driver.findElement(By.xpath(dropdown+"/..//ul/li[contains(.,'%s')]".replace("%s", flow))).getText());
		return flow;
	}
	public void getValueforCallMe(){
		callMeData=step.getDataValue("callMeData");
		System.out.println("Inside callMeValidation"+step.getDataValue("callMeData"));
	}
	public void callMeValidation(String NewUser)throws Exception{
		String error="";
		WebDriver driver = DriverFactory.getDriver();
		String p="//div[@class='modal-body']//*[@placeholder='%s']";

        String expectedMessage="We'll be in touch soon! A loan representative will contact you shortly to help you with all your loan needs.";
		HashMap<String, String> dataMap = new HashMap<String, String>();
		dataMap=buildDataMap(callMeData);

		
		if(!driver.findElement(By.xpath("//input[@id='leadid_tcpa_disclosure']//following::label")).getText().equalsIgnoreCase(KWVariables.getVariables().get("CallMe_Disclaimer"))){
			error="Mismatch in Disclaimer"+driver.findElement(By.xpath("//label[@for='leadid_tcpa_disclosure']")).getText()+" "+KWVariables.getVariables().get("CallMe_Disclaimer");
		}
		
		if(NewUser.split(",")[0].equalsIgnoreCase("No")){
			 EmailId =driver.findElement(By.xpath("//input[@id='cm_email']")).getAttribute("title");
				System.out.println("fgshdfgh>>>>>!!!"+EmailId+"fjgvfsh>>"+NewUser);
		
		}
		else
	
		System.out.println("DataMap>>>>"+dataMap.keySet());
		for(String key:dataMap.keySet()){
			Thread.sleep(2000);
			driver.findElement(By.xpath(p.replace("%s",key))).click();
			if(NewUser.split(",")[0].trim().equalsIgnoreCase("Yes")&& key.contains("Email")){
				   int len = 5;
	                String rnd = RandomStringUtils.random(len, true, false).toLowerCase();
	                rnd=rnd.toLowerCase()+"123@gmail.com";
	                EmailId = rnd;
	                driver.findElement(By.xpath(p.replace("%s",key))).sendKeys(EmailId);
	                isNewUserCallMe=true;
	        }
			else{
			driver.findElement(By.xpath(p.replace("%s",key))).sendKeys(dataMap.get(key));
			System.out.println("Keyyy>>>>>"+p.replace("%s",key)+"Value>>>>>>"+dataMap.get(key));
			}
		}
		 salesGroup=selectflowOption(NewUser.split(",")[1]);
		try{	
		driver.findElement(By.xpath("//button[contains(.,'Call me')]")).click();
		Thread.sleep(1000);
			if(!driver.findElement(By.xpath("//div[@class='success-msg']")).getText().equalsIgnoreCase(expectedMessage)){
				error=error+"Mismatch in succuess message"+driver.findElement(By.xpath("//div[@class='success-msg']")).getText()+" "+expectedMessage;
			}
		}
		catch(Exception e){
			addExceptionToReport("Call Me Button is not enabled", "", "");
		}
		finally{
			if(error.length()>0){
				addExceptionToReport(error, "", "");
			}
		}
	}


/**
	 * Method Name: validateQuestionAnswerIn1003RealEstate Purpose: to fill
	 * RealEstate details
	 *
	 */

	public void validateQuestionAnswerIn1003RealEstateCoApplicant() throws InterruptedException, TwfException, Exception {
		System.out.println("inside validateQuestionAnswerIn1003RealEstate Coapp");

		String realEstate = step.getDataValue("RealEstateQuestionAnswerCoapplicant");
		String Module = step.getDataValue("Module");
		System.out.println(" Real estate data -----");

		System.out.println(realEstate);
		
		HashMap<String, String> dataMap = validateQuestionAnswerIn1003(realEstate);
		if (Module.equalsIgnoreCase("1003")) {
			System.out.println("Not Prequal");
			// tenothreesectionsummary.validateRealEstateSummaryPage(dataMap);
		} else {
			System.out.println("Prequal");
		}

	}

	
	private void setValues(WebDriver driver,String key,String value,String dataType){
		String p="//div[@class='last-step-form']//input[@placeholder='%s']";
		String dd="//div[contains(.,'%d')]//button[@id='control.id']";
		switch (dataType.toLowerCase()) {
		case "txt":
			driver.findElement(By.xpath(p.replace("%s",key))).click();
			driver.findElement(By.xpath(p.replace("%s",key))).sendKeys(value);
			System.out.println("Key>>>"+key+"Value>>>"+value);
			break;

		case "dd":
			driver.findElement(By.xpath(dd.replace("%d", key))).click();
			driver.findElement(By.xpath(dd.replace("%d", key)+"//..//ul//li[contains(.,'%e')]".replace("%e",value))).click();
			break;
		default:
			break;
		}
	}
	public void coBorrowerOnlineValidation() throws Throwable{
		flag=false;
		loanOnboardValidation();
		flag=true;
	}
	
	public void loanOnboardValidationforCoBorrower() throws Throwable{
		coborrLoanonboard=true;
		loanOnboardValidation();
		coborrLoanonboard=false;
	}
	public void loanOnboardValidation() throws Throwable{
		WebDriver driver = DriverFactory.getDriver();
//		TimeUnit.MINUTES.sleep(1);
		Preview1003 preview=new Preview1003();
		String oOwQues="Please enter last 4 digits of %user"+"'s SSN:";
		try{
		if(flag==true){
		System.out.println("Inside LoanOnboardValidation!!!!!");
		HashMap<String, String> dataMap = new HashMap<String, String>();
		
		dataMap=buildDataMap(KWVariables.getVariables().get("LoanOnboardData"));
		System.out.println("DataMap>>>>"+dataMap);
		Thread.sleep(5000);
		for(String s:dataMap.keySet()){
			if(coborrLoanonboard==true){
				
				if(s.equalsIgnoreCase("CoBorrower_SSN")){
					System.out.println("For Co-Borrower Validation!!");
					System.out.println("Compare>>>"+driver.findElement(By.xpath("//div[@class='out-of-wallet-ssn']//label")).getText().trim()+"Another One"+oOwQues.replace("%user", dataMap.get("CoBorrower First Name")));
					if(!driver.findElement(By.xpath("//div[@class='out-of-wallet-ssn']//label")).getText().trim().equalsIgnoreCase(oOwQues.replace("%user", dataMap.get("CoBorrower First Name"))))
						addExceptionToReport("Mismatch in OOW screen that is displayed for CoBorrower", "", "");
					System.out.println("Inside CoSSN>>>>");
					Thread.sleep(4000);
					driver.findElement(By.id("authCode_B")).sendKeys(dataMap.get(s));
					coborrLoanonboard=false;
					break;
				}
			}
				else{
					
				if(s.equalsIgnoreCase("Borrower_SSN")){
					System.out.println("For Borrower Validation!!");
					System.out.println("Compare>>>"+driver.findElement(By.xpath("//div[@class='out-of-wallet-ssn']//label")).getText().trim()+"Another One"+oOwQues.replace("%user", dataMap.get("First Name")));
					if(!driver.findElement(By.xpath("//div[@class='out-of-wallet-ssn']//label")).getText().trim().equalsIgnoreCase(oOwQues.replace("%user", dataMap.get("First Name"))))
						addExceptionToReport("Mismatch in OOW screen that is displayed for Borrower", "", "");
					System.out.println("Inside SSN>>>>");
					Thread.sleep(4000);
					driver.findElement(By.id("authCode_B")).sendKeys(dataMap.get(s));
					break;
				}
			}
			
		}		

		driver.findElement(By.xpath("//button[contains(.,'Submit')]")).click();
		TimeUnit.MINUTES.sleep(1);
		driver.findElement(By.xpath("//a[contains(@title,'View More')]")).click();
		dataValidationAfterLoanOnboard(driver,dataMap);
		}
		else{
			System.out.println("Inside CoBorrower Invite Email");
			String data=step.getDataValue("FinExp_confirmSSN_Joint");
			System.out.println("DATAAA>>>>"+data);	
			String ssn=data.substring(data.length()-4);
			System.out.println("SSNN>>>>"+ssn);	
			driver.findElement(By.id("authCode_B")).sendKeys(ssn);
			driver.findElement(By.xpath("//button[contains(.,'Submit')]")).click();
			TimeUnit.MINUTES.sleep(1);
			driver.findElement(By.xpath("//a[contains(@title,'View More')]")).click();
		}
		}catch(Exception e){
			addExceptionToReport("Incorrect SSN is provided in OOW Page", "", "");
		}
	}
	
	
	private void dataValidationAfterLoanOnboard(WebDriver driver, HashMap<String, String> dataMap) throws TwfException {
		
		System.out.println("Inside dataValidationAfterLoanOnboard");
		String field="//div[@class='field-section']"; 
		String fieldName="(//div[@class='field-section']//div[1])[%n]";
		String fieldValue="(//div[@class='field-section']//div[2])[%n]";

		
		int fieldLength=driver.findElements(By.xpath(field)).size();
		System.out.println("Size>>"+fieldLength);
		ArrayList<String> fields=new ArrayList<String>();
		for(int i=1;i<=fieldLength;i++){
				
				String k=driver.findElement(By.xpath(fieldName.replace("%n", String.valueOf(i).toString()))).getText();
				String val=driver.findElement(By.xpath(fieldValue.replace("%n", String.valueOf(i).toString()))).getText();
				
				if(fields.contains(k))
					k="CoBorrower "+k;
					else{
						System.out.println("added");
						fields.add(k);
					}
				if(k.equalsIgnoreCase("Mobile Phone")){
						k="Mobile Number";
				}
				if(k.equalsIgnoreCase("Zip Code")){
					k="Subject Property Zip Code";
			   }
				if(k.equalsIgnoreCase("Loan Purpose")&&dataMap.get(k).equalsIgnoreCase("NoCash-Out Refinance")){
					System.out.println("Inside Refinace");
					dataMap.remove(k);
					dataMap.put("Loan Purpose", "Refinance");
					dataMap.put("I want to refinance my existing mortgage to", "Refinance : lower mortgage payment");
			   }
				System.out.println("k:"+k);
				System.out.println("fields:"+fields);
				System.out.println("Key--->"+k+"\nDisplayed Value--->"+dataMap.get(k)+"Expected value--->"+val);
				if(!dataMap.get(k).equalsIgnoreCase(val)){
					addExceptionToReport("Mismatch in field value after loan onboard", dataMap.get(k), val);
				}
		}
	}
	
	public void homeButtontodashboard() throws Exception{
		WebDriver driver = DriverFactory.getDriver();
		Util util=new Util();
		Thread.sleep(3000);
		System.out.println("Inside homeButtontodashboard");
		try{
		util.waitTillElementIsDisplayed(driver, getElementByUsing("homePageButton"));
		driver.findElement(By.xpath("//a[@title='Go To Dashboard']")).click();
		Thread.sleep(5000);
		}catch(Exception e){
			addExceptionToReport("Unable to click dashboard button", "", "");
		}
	}
	
	public void enterImportDetailsForCredit() throws TwfException, BiffException, IOException{
		
		String ApplicantSSN=step.getDataValue("FinExp_SSN");
		WebDriver driver = DriverFactory.getDriver();
		utill.click(getElementByUsing("1003_Liabilities_AddButton"));
		
		if(iscreditforCoApplicant==true){
		   ApplicantSSN=step.getDataValue("Liability_CoApplicant_SSN");
		}
		System.out.println("Inside enterImportDetailsForCredit>>"+ApplicantSSN);
		getElementByUsing("1003_Assets_AuthorizationCheckBox").click();
		getElementByUsing("FinExp_SSN").sendKeys(ApplicantSSN);
		getElementByUsing("FinExp_confirmSSN").sendKeys(ApplicantSSN);
		
		getElementByUsing("1003_Liability_ImportMyDataButton").click();
		
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='credit-pull-joint']")));
		System.out.println("Message>>"+driver.findElement(By.xpath("//div[@class='credit-pull-joint']")).getText());
		getElementByUsing("PreQual_Asserts_OkayButton").click();
		
	}
	 
	public void enterImportDetailsForCreditforCoApplicant() throws TwfException, BiffException, IOException{
		iscreditforCoApplicant=true;
		System.out.println("Inside enterImportDetailsForCreditforCoApplicant>>");
		enterImportDetailsForCredit();
	}
	
	public void enterImportDetailsForAsset() throws Throwable{
        
    System.out.println("Inside enterImportDetailsForAsset>>>>");
    String bankSearchTextBoxXpath="//input[@placeholder[contains(.,'Search')]]";
	String selectBankXpath="//*[text()='%name']";
	String userNameXpath="//label[contains(.,'ser')]//following-sibling::input";
	String passwordXpath="//label[contains(.,'word')]//following-sibling::input";
	String submitButtonXpath="//span[text()='Submit']";
	String importAssetButton="//button[contains(.,'Import Assets')]";
	String authorizeCheckBox="//label[contains(.,'I/We authorize')]";
	String importNowButton="//button[text()='Import Now']";
	String finishAndShareXpath="//button[contains(.,'Share')]";
	String checkBox="//span[@class='checkmark']";
	String AssetSummaryButton="//button[contains(.,'Assets Summary')]";
    String confirmImportMessage="";
    WebDriver driver=DriverFactory.getDriver();
    Thread.sleep(3000);
    Util.click(authorizeCheckBox);
    Util.click(importAssetButton);
   // Util.click(importNowButton);
//    Thread.sleep(2000);
    driver.switchTo().frame("AccountChekIFRAME");
    Util.enterValueInTextField(driver, bankSearchTextBoxXpath, KWVariables.getVariables().get("AssetImportBankName"));
    Util.click(selectBankXpath.replace("%name", KWVariables.getVariables().get("AssetImportBankName")));
    driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='Bank Login Form']")));
    Util.enterValueInTextField(driver, userNameXpath, KWVariables.getVariables().get("AssetImportUserName"));
    Util.enterValueInTextField(driver, passwordXpath, KWVariables.getVariables().get("AssetImportPassword"));
    Util.click(submitButtonXpath);
    driver.switchTo().defaultContent();
    driver.switchTo().frame("AccountChekIFRAME");
//    TimeUnit.MINUTES.sleep(1);
//    Thread.sleep(6000);
    WebDriverWait wait = new WebDriverWait(driver, 180);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(checkBox)));
    System.out.println("More than 1 minute time");
  //  Util.click(checkBox);
  //  Util.click(finishAndShareXpath);
    WebElement element = driver.findElement(By.xpath(checkBox));
    JavascriptExecutor executor = (JavascriptExecutor)driver;
    executor.executeScript("arguments[0].click();", element);
    System.out.println("It clicked on checkbox");
    Thread.sleep(1000);
    WebElement element1 = driver.findElement(By.xpath(finishAndShareXpath));
    JavascriptExecutor executor1 = (JavascriptExecutor)driver;
    executor1.executeScript("arguments[0].click();", element1);
//    driver.findElement(By.xpath(checkBox)).click();
//    driver.findElement(By.xpath(finishAndShareXpath)).click();
    System.out.println("It clicked on checkbox and xpath");
    TimeUnit.MINUTES.sleep(2);

    driver.switchTo().defaultContent();
//     ele = driver.findElement(By.xpath("//div[@id='page-content']/h1/br")).getText();
//    Util.waitTillElementIsDisplayed(driver,ele);
//    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AssetSummaryButton)));
    utill.scrollToElement(driver.findElement(By.xpath(AssetSummaryButton)));
    System.out.println("Scrolled to get asset summary");
    Util.click(AssetSummaryButton);
    System.out.println("Finished");
    
}

	public void validateImportAssetMessage(String val) throws Throwable {
		
        System.out.println("Entered validateImportAssetMessage");
        WebDriver driver=DriverFactory.getDriver();
        String expMessage=KWVariables.getVariables().get(val);
        //String revMessageXpath = importIncomeMessage.replace("%v", message);
        WebDriverWait wait=new WebDriverWait(driver, 160);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(importAssetMessage)));        
        
        String actualText = driver.findElement(By.xpath(importAssetMessage)).getText();
        
        
        if (!(actualText.equalsIgnoreCase(expMessage))) {
              addExceptionToReport("Expected message is not displayed after asset import", actualText, expMessage);
        }
        
        if(val.equalsIgnoreCase("ImportAssetSuccessMsg")){                                     
              Util.click(okayButton);
        }else if(val.equalsIgnoreCase("ImportAssetFailureMsg")){
              Util.click(addItManuallyImportButton);
        }
  }
	

	public void getPercentageCoverage() throws TwfException{
		WebDriver driver;
		try {
			driver = DriverFactory.getDriver();
			val =  driver.findElement(By.xpath(progressBar)).getText();
			System.out.println("Current Progress : "+val);
		} catch (Exception e) {
			addExceptionToReport("Unable to get the Progress Bar details and the exception is :"+e.getStackTrace().toString(),"","");
		}

	}
	
	public void validatePercentageCoverage(String sectionName) throws TwfException{
		WebDriver driver;
		
		try {
			driver=DriverFactory.getDriver();
			Thread.sleep(3000);
			String dispValue = driver.findElement(By.xpath(progressBar)).getText(); 
			String expectedValue = KWVariables.getVariables().get(sectionName);
			if(Integer.valueOf(expectedValue.split("%")[0]) != Math.abs(Integer.valueOf(dispValue.split("%")[0])-Integer.valueOf(val.split("%")[0]))){
				addExceptionToReport("Incorrect Progress Percentage ","Actual percentage Change in Progress Bar: "+(Integer.valueOf(dispValue .split("%")[0])-Integer.valueOf(val.split("%")[0])),"Expected percentage Change in Progress Bar: "+expectedValue .split("%")[0]);
			}
			else {
				System.out.println("Actual percentage Change : "+(Integer.valueOf(dispValue .split("%")[0])-Integer.valueOf(val.split("%")[0]))+"%");
				System.out.println("Expected percentage Change :"+expectedValue .split("%")[0]+"%");
			}
		} catch (Exception e) {
			addExceptionToReport("Unable to validate the Progress Bar details and the exception is :"+e.getStackTrace().toString(),"","");
		}

	}

	public void clearFieldAndSaveSection() throws Exception{

		System.out.println("Check point 1");

		WebDriver driver=DriverFactory.getDriver();
		String tabobj="//div[@class='tab-title ']//span[contains(.,'%t')]";
		String obj="//label[contains(.,'%q')]";
		String txt="//label[contains(.,'%q')]//following::input[@placeholder[contains(.,'%p')]][1]";
		String question=null;
		String ans=null;

		String quesAnsString= step.getDataValue("half");
		String tab=quesAnsString.split(":")[0];
		driver.findElement(By.xpath(tabobj.replace("%t", tab.trim()))).click();
		Thread.sleep(3000);

//		if(driver.findElements(By.xpath("(//i[@class='fa fa-pencil'])[1]")).size()>0) {
//			driver.findElement(By.xpath("(//i[@class='fa fa-pencil'])[1]")).click();
//		}
//		else {
			driver.findElement(By.xpath("//i[@class='edit-icon']")).click();
//		}


		question=quesAnsString.split(":")[1];
		ans=quesAnsString.split(":")[2];
		System.out.println("Check point 2");

		utill.scrollToElement(driver.findElement(By.xpath(obj.replace("%q", question))));
		driver.findElement(By.xpath(txt.replace("%q", question).replace("%p", ans))).clear();
		driver.findElement(By.xpath(txt.replace("%q", question).replace("%p", ans))).sendKeys(Keys.TAB);

		System.out.println("Check point 3");

		String next=step.getDataValue("next");
		System.out.println("next>>>"+next);

		utill.scrollToElement(driver.findElement(By.xpath(obj.replace("%q", next.split(";")[0]))));	

		HashMap<String, String> dataMap = buildDataMap(next);

		String attribute = null;
		Thread.sleep(1000);

		System.out.println("Check point 4");
		int qusIndex=Integer.valueOf(driver.findElement(By.xpath("(//div[@class[contains(.,'active-section')]])[1]")).getAttribute("class").substring(24,26).trim());
		System.out.println("qusIndex : "+qusIndex);



		attribute = driver.findElement(By.xpath(fieldAtttibute.replace("%s", String.valueOf(qusIndex)))).getAttribute("data-type").trim();
		System.out.println("Check point 5");
		String qustion = driver.findElement(By.xpath(Qustion.replace("%s", String.valueOf(qusIndex)))).getText().trim();
		System.out.println("Check point 6");
		System.out.println("attribute : "+attribute);
		System.out.println("qusIndex : "+qusIndex);
		System.out.println("dataMap : "+dataMap);
		System.out.println("qustion : "+qustion);

		HashMap<String, String>	expectedSelectAttributeMap = selectAttribute(driver, attribute, qusIndex, dataMap, qustion);
		System.out.println("Check point 7");

		Thread.sleep(5000);

	}
	
	public void validateQuestionAnswerIn1003IncompeteGetStarted() throws InterruptedException, TwfException, Exception {
		String quesAnsString = step.getDataValue("question_ans").split("##")[0].trim();
		System.out.println("First String::>>>"+quesAnsString);
		isNewUser = step.getDataValue("NewUser");
		// String fieldMap = step.getDataValue("SummaryFieldMapping");
		getStartedDataMap = validateQuestionAnswerIn1003(quesAnsString);
		//new static variable
		if(standardisedAddress.length()>0){			
			String prevValue = getStartedDataMap.get("My current address is" );
			System.out.println("standardisedAddress>>>"+standardisedAddress);
			String streetAddress = prevValue.split(":")[0].split("_")[1];
			getStartedDataMap.put("My current address is", prevValue.replace(streetAddress, standardisedAddress));
			standardisedAddress = "";
		}
//		dashboard.resumeAfterFetchingLoanNumberfromDashboardPage(Ten0Three.fname);
	}
	
	public void validationforIncompleteSection() throws InterruptedException, TwfException,Exception{
		Thread.sleep(5000);
		incompleSection=true;
		String quesAnsString = step.getDataValue("question_ans").split("##")[1].trim();
		System.out.println("Second String::>>>"+quesAnsString);
		getStartedDataMap = validateQuestionAnswerIn1003(quesAnsString);
//		if(standardisedAddress.length()>0){			
//			String prevValue = getStartedDataMap.get("My current address is" );
//			System.out.println("standardisedAddress>>>"+standardisedAddress);
//			String streetAddress = prevValue.split(":")[0].split("_")[1];
//			getStartedDataMap.put("My current address is", prevValue.replace(streetAddress, standardisedAddress));
//			standardisedAddress = "";
//		}
	}
	
	
	public void dataRetrive(){
		System.out.println("Inside dataRetrive under Tenothree>>>");
		String value=step.getDataValue("LoanOnboardingTestData").trim();
		offlinedata=value;
		System.out.println("Value>>>"+offlinedata);
	}

	public void submitPageSSNforBorrower() throws BiffException, IOException, TwfException, Exception{
//		utill.sendTextfromActionSteps();
//		utill.sendTextfromActionSteps();FinExp_proceeedButton
		WebDriver driver=DriverFactory.getDriver();
		utill.clearAndSendKeys(getElementByUsing("FinExp_SSN"), step.getDataValue("FinExp_SSN"));
		utill.clearAndSendKeys(getElementByUsing("FinExp_confirmSSN"), step.getDataValue("FinExp_confirmSSN"));
		utill.click(getElementByUsing("FinExp_isAuthorized"));
		utill.click(getElementByUsing("FinExp_proceeedButton"));
		Thread.sleep(2000);//
		utill.click(getElementByUsing("Submit_ApplicationButton"));
		utill.waitTimeForSpinner(driver);
	}
	
	public void submitPageSSNforCoBorrower() throws BiffException, IOException, TwfException, Exception{
//		utill.sendTextfromActionSteps();
//		utill.sendTextfromActionSteps();FinExp_proceeedButton
		WebDriver driver=DriverFactory.getDriver();
		utill.clearAndSendKeys(getElementByUsing("FinExp_SSN_Applicant"), step.getDataValue("FinExp_SSN_Applicant"));
		utill.clearAndSendKeys(getElementByUsing("FinExp_confirmSSN_Applicant"), step.getDataValue("FinExp_SSN_Applicant"));
		utill.clearAndSendKeys(getElementByUsing("FinExp_SSN_Joint"), step.getDataValue("FinExp_SSN_Joint"));
		utill.clearAndSendKeys(getElementByUsing("FinExp_confirmSSN_Joint"), step.getDataValue("FinExp_SSN_Joint"));
		utill.click(getElementByUsing("FinExp_AuthoriseBox_Applicant"));
		utill.click(getElementByUsing("FinExp_AuthoriseBox_CoApplicant"));
		utill.click(getElementByUsing("FinExp_proceeedButton"));
		Thread.sleep(2000);//
		utill.click(getElementByUsing("Submit_ApplicationButton"));
		utill.waitTimeForSpinner(driver);
	}
	
	
	public int addMultipleValues(WebDriver driver, String question, String answers, Integer qusIndex,
			Integer questionCount) throws Exception {
		System.out.println("Iniside addMultipleValues");
		String buttonXpath = "(//button[text()='%t'])[1]";

		HashMap<String, String> linkFieldMap = getLinkAndFieldName(question);
		HashMap<String, String> multilpleMap = new HashMap<String, String>();
		int index = 1;
		for (String qtn : answers.split("//")) {
			if (index > 1) {
				System.out.println("Inside new Entry");
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();",
						driver.findElement(By.xpath(buttonXpath.replace("%t", linkFieldMap.get("linkName")))));
				Thread.sleep(2000);
				// verifyDeleteMultipleValue(driver,qusIndex,question);
				// js.executeScript("arguments[0].click();",
				// driver.findElement(By.xpath(buttonXpath.replace("%t",
				// linkFieldMap.get("linkName")))));
				expMapSize++;
			}
			Thread.sleep(1000);
			for (String fld : qtn.split(":")) {
				String attribute = fld.split("%")[0];
				String placeHolderName = fld.split("%")[1].split("_")[0];
				String value = fld.split("%")[1].split("_")[1];
				switch (attribute.toLowerCase()) {
				case "ta":
					enterValuesInTextField(driver, qusIndex, placeHolderName, value);
					multilpleMap.put(placeHolderName + "#" + index, value);
					break;
				case "dd":
					selectValuesInDropDownList(driver, qusIndex, placeHolderName, value);
//					System.out.println("Outside dropdown");
					multilpleMap.put(placeHolderName + "#" + index, value);
					break;
				case "cb":
					System.out.println("Inside checkbox>>");
					clickOnCheckBox(driver, value,qusIndex);
					System.out.println("Outside checkbox");
					multilpleMap.put(placeHolderName + "#" + index, value);
					break;
				case "rb":
					System.out.println("Inside radio>>");
					selectRadioButton(driver,
							radioButtonAns.replace("%s", String.valueOf(qusIndex)).replace("%v", value));
					break;
				default:
					break;
				}
			}
			index = index + 1;
			qusIndex = qusIndex + 1;
		}
		System.out.println("Click on Okay>>");
		clickOnOkButtonAfterEachQuestion(driver, qusIndex - 1, questionCount);
		System.out.println("It Clicked on Okay Button");
		propertydataMap.putAll(multilpleMap);
		System.out.println("propertydataMap>>" + propertydataMap);
		return qusIndex - 1;

	}
	
	private static HashMap<String, String> getLinkAndFieldName(String questionName) {
		HashMap<String, String> linkFieldNameMap = new HashMap<String, String>();

		switch (questionName.toLowerCase()) {
		case "provide gift details":
			linkFieldNameMap.put("linkName", "Add Another Gift/Grant");
			linkFieldNameMap.put("fieldName", "Gift Details #");
			break;
        case "details of other loan":
        case "details of the new loan or existing line of credit":
            linkFieldNameMap.put("linkName", "Add Another Loan");
            linkFieldNameMap.put("fieldName", "Loan Details #");
            break;
		default:
			break;
		}
		return linkFieldNameMap;
	}
	
	private void giftLetterUploadDocs(WebDriver driver) throws Exception {
		System.out.println("Inside giftLetterUploadDocs");
		Ten0Three.documentListData = step.getDataValue("DocumentsToUpload");
		if(documentListData!=null && documentListData.length()>0){
			System.out.println("documentListData:"+documentListData);
			uploadGiftLetter(driver,documentListData);
			internal.verifyClearAllDocuments(driver, documentListData);
		}
		else
			expMapSize++;
	}
	
	private void uploadGiftLetter(WebDriver driver,String documentListData) throws Exception {
		System.out.println("Inside uploadGiftLetter");
		System.out.println("documentListData>>" + documentListData);
		String[] documentList = documentListData.split(";");
		String[] validFileFormats = { "jpe", "jpg", "jpeg", "tif", "pdf", "txt", "docx", "doc", "xps", "emf" };
		List<String> fileFormats = new ArrayList<>();
		for (String s : validFileFormats) {
			fileFormats.add(s);
		}
		WebDriverWait wait = new WebDriverWait(driver, 10);
		String successMessageXpath = "//div[@class='alert alert-info' and contains(.,'%msg')]";
		successMessageXpath = successMessageXpath.replace("%msg",
				KWVariables.getVariables().get("UploadDocumentSuccessMessage"));
		System.out.println("Actual>>"+getElementByUsing("giftLetterdescription").getText().trim().replace("[\\n\\r]", ""));
		System.out.println("Expected>>"+KWVariables.getVariables().get("UploadGiftLetterDescription"));
		if (!getElementByUsing("giftLetterdescription").getText().trim().replace("[\\n\\r]", "")
				.equalsIgnoreCase(KWVariables.getVariables().get("UploadGiftLetterDescription").trim())) {
			addExceptionToReport("Description displayed in upload gift letter page is wrong!!",
					getElementByUsing("giftLetterdescription").getText(), "");


		}
		for (String fileName : documentList) {
			if (fileName.contains("closeFile"))
				internal.verifyCloseSelectedDoc(driver, fileName);
			else {
				utill.fileUpload(fileName,driver);
				getElementByUsing("giftDocUploadSubmitButton").click();
				if (!fileFormats.contains(fileName.split("\\.")[1]))
					verifyGiftInvalidDocumentUpload(driver, "Gift Letter", fileName);
				else if (fileName.contains("invalidFileSize"))
					verifyGiftInvalidDocumentUpload(driver, "Gift Letter", fileName);
				else {
					Thread.sleep(2000);
					wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(successMessageXpath))));
					if (!utill.verifyElementIsDisplayed(successMessageXpath)) {
						addExceptionToReport("Document upload success message is not displayed !!", "", fileName);
					} else {
						driver.findElement(By.xpath(successMessageXpath + "//a")).click();
						verifyUploadedGiftLetters(driver, fileName);
					}
				}

			}
		}
	}
	
	
	public void verifyGiftInvalidDocumentUpload(WebDriver driver, String conditionName, String fileName)
			throws Exception {
		System.out.println("verifyInvalidDocumentUpload");
		String failureMessage = KWVariables.getVariables().get("UploadDocumentFailureMessage");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		String failedAlertXpath = "//div[@class='alert alert-danger']";
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(failedAlertXpath))));
		if (!driver.findElement(By.xpath(failedAlertXpath + "//div[@class='modal-title']")).getText().trim()
				.equalsIgnoreCase(failureMessage)) {
//			addExceptionToReport("Upload failure message is incorrect",
//					driver.findElement(By.xpath(internalCondition.uploadFailedAlert)).getText(), failureMessage);
		}
		driver.findElement(By.xpath(failedAlertXpath + "//a")).click();
	}
	
	
	private void verifyUploadedGiftLetters(WebDriver driver, String expFileName) throws Exception {
		System.out.println("inside verifyUploadedGiftLetters");
		String actFileNames = getElementByUsing("uploadedFilesList").getText().trim();
		if (!actFileNames.contains(expFileName)) {
			addExceptionToReport("Uploaded document for gift letter is not displayed!!", actFileNames, expFileName);
		}
	}
	
	
	
	
	public void editAndUpdateMaritalStatus(String maritalStatus) throws Exception{
		Ten0Three.fname="John";
        System.out.println("Inside editAndUpdateMaritalStatus");
 //       System.out.println("authorisedUsersList>>"+authorisedUsersList);
        WebDriver driver=DriverFactory.getDriver();
        Util.waitTimeForSpinner(driver);
        getElementByUsing("TopMenu_GetStarted").click();
        String editButtonXpath="//div[@class='property-addr' and contains(.,'%name')]//preceding::i[@class='edit-icon']";
 //       for(int i=0;i<authorisedUsersList.size();i++){
        System.out.println("edit Marital fname>>>"+Ten0Three.fname.trim());
              Util.javaScriptClick(driver,editButtonXpath.replace("%name", Ten0Three.fname.trim()));
              validateUpdatedUnMarriedQuestion(driver,maritalStatus);
//        }
  }
  public void validateUpdatedUnMarriedQuestion(WebDriver driver,String option) throws Exception{
        Thread.sleep(2000);
        System.out.println("inside validateUpdateMaritalStatusAndUnmarriedAddendum"+option);
        String unMarriedAddendumLabel="//label[text()='Is there a person who is not your legal spouse but who currently has real property rights similar to those of a legal spouse? (Optional)']";
        String maritalFieldValue="//button[contains(text(),'%s')]";
        Util.waitTimeForSpinner(driver);
        Thread.sleep(1000);
        System.out.println("After edit xpath>>"+maritalFieldValue.replace("%s", option));
        utill.scrollToElement(driver.findElement(By.xpath(maritalFieldValue.replace("%s", option))));
        System.out.println("It scrolled");
        utill.javaScriptClick(driver,maritalFieldValue.replace("%s", option));
        utill.waitTimeForSpinner(driver);
        handleMaritalUpdatePopUp(driver,"No");
  if(!utill.verifyElementIsDisplayed(unMarriedAddendumLabel))//||!driver.findElement(By.xpath(maritalFieldValue.replace("%s", "Unmarried"))).getAttribute("class").equalsIgnoreCase("active-btn")
              addExceptionToReport("Error when user click on No for marital status update popup !!", "", "");
  		System.out.println("Came here1>>>>");
        Util.javaScriptClick(driver,maritalFieldValue.replace("%s", option));
        System.out.println("Came here2>>>>");
        Util.waitTimeForSpinner(driver);
        handleMaritalUpdatePopUp(driver,"Yes");
        Util.waitTimeForSpinner(driver);
        System.out.println("Clicked yes popup-also>>");
        getElementByUsing("TopMenu_Property").click();
 //       System.out.println("Clicked on Get-Started header");
        handlePopUpWindow(driver);
        Thread.sleep(5000);
        if(!utill.verifyElementIsDisplayed("//button[contains(.,'m Done')]"))//HareeshUpdated
              addExceptionToReport("Error !! User is not navigated to Getstarted summary page when user click on Yes for marital status update popup !!", "", "");
        
  }
  private static void handleMaritalUpdatePopUp(WebDriver driver,String value) throws Exception{
        String displayedText = driver.findElement(By.xpath("(//div[@class='body-tit'])[1]")).getText();
        String expectedText="We will be deleting the information that's been provided. Are you sure you want to change marital status?";
        if(displayedText.equalsIgnoreCase(expectedText)){
            clickOnButtonWithvalue(driver, value);
        }else
              addExceptionToReport("Wrong message displayed !!", displayedText, expectedText);
  }


  
	
	@Override
	public void checkPage() {

		// TODO Auto-generated method stub

	}
	
	
}

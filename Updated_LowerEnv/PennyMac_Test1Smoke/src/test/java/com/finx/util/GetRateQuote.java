package com.finx.util;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tavant.base.DriverFactory;
import com.tavant.kwutils.CustomStep;
import com.tavant.kwutils.KWVariables;
import com.tavant.utils.TwfException;


public class GetRateQuote extends CustomStep{

	String loanType="//button[@id='%val']";
	String propValue="//input[@placeholder='Property Value']";
	String propLoanBal="//input[@placeholder='Loan Balance']";
	String propDownPay="//input[@placeholder='Down Payment']";
	String propLoc="//input[@placeholder='Zip Code']";
	String benefit="//button[contains(text(),'%val')]";
	String cashBack="//input[@placeholder='Cash Back']";
	String vaLoanYesNo="//div[contains(text(),'Is your current loan a VA loan')]/following-sibling::div/button[contains(text(),'%val')]";
	String fhaLoanYesNo="//div[contains(text(),'Is your current loan an FHA loan')]/following-sibling::div/button[contains(text(),'%val')]";
	String creditScore="//div[contains(text(),'What is your approximate credit score')]/following-sibling::div/button[contains(text(),'%val')]";
	String servedMilitary="//div[contains(text(),'Have you or your spouse ever served in the U.S. military')]/following-sibling::div/button[contains(text(),'%val')]";

	String questionLocator="//div[@class='question-layout undefined']/div[@class='question']";
	String ques2AnsLocTextBox="//div[@class='question-layout undefined']/div[contains(text(),'%q')]//parent::div//input";
	String isItRadioButtonLocator="//div[@class='question-layout undefined']/div[contains(text(),'%q')]//parent::div//button";
	String ques2AnsLocRadioButton="//div[@class='question-layout undefined']/div[contains(text(),'%q')]//parent::div//button[text()='%a']";
	String nextButton="//button[text()='Next']";

	String leftPanelRows="//div[@class='content']/div[@class='row']";
	String leftPanelLabelsSize="//div[@class='content']/div[@class='row'][%i]//label";
	String leftPanelLabelLoc="//div[@class='content']/div[@class='row'][%i]//div[@class='item'][%j]/label";
	String leftPanelLabelValueLoc="";

	String rateQuotes="//div[@class='featured-products-holder']/div";
	String firstRateQuote="//div[@class='featured-products-holder']/div[1]//button[text()='Select']";
	String selectedRateQuoteMonthlyPayment="//div[@class='featured-products-holder']/div[1]//div[@class='product-details']//div[@class='product-payment']//span[@class='payment']";
	String selectedRateQuoteOtherDetails="//div[@class='featured-products-holder']/div[1]//div[@class='product-details']//div[@class='product-info']/div[contains(@class,'product')]";
	String selectedRateQuoteOtherDetailskeyValue="//div[@class='featured-products-holder']/div[1]//div[@class='product-details']//div[@class='product-info']/div[contains(@class,'product')][%i]/span[%j]";
	String applyFullAppForFirstRateQuote="//h3[contains(text(),'Apply now')]//ancestor::li//img[contains(@class,'arrow')]";
	String applyPrequalForFirstRateQuote="//h3[contains(text(),'Get Pre-Approved')]//ancestor::li//img[contains(@class,'arrow')]";
	String loginButtonOnPennyMacPage="//input[@id='submit-button']";

	HashMap<String,String> rateQuote1003Map=new HashMap<String, String>();
	HashMap<String, String> dataMap = new HashMap<String, String>();
	HashMap<String, String> selectedRateQuote = new HashMap<String, String>();
	HashMap<String, String> dataFromDB = new HashMap<String, String>();
	@Override
	public void checkPage() {
		// TODO Auto-generated method stub

	}
	
	
	
	
	
	
	

	public void navigateToRateQuoteURL() throws Throwable{
		System.out.println("Entered enterRateQuoteFields");
		WebDriver driver = DriverFactory.getDriver();
		String envType = KWVariables.getVariables().get("Env");
		switch(envType.toLowerCase()){
		case "qa":
			driver.get(KWVariables.getVariables().get("rateQuoteURL_QA"));
			driver.manage().window().maximize();
			break;
		case "uat":
			driver.get(KWVariables.getVariables().get("rateQuoteURL_UAT"));
			driver.manage().window().maximize();
			break;
		case "prod":
			driver.get(KWVariables.getVariables().get("rateQuoteURL_PROD"));
			driver.manage().window().maximize();
			break;
		case "virgo":
			driver.get(KWVariables.getVariables().get("rateQuoteURL_VIRGO"));
			driver.manage().window().maximize();
			break;
		case "libra":
			driver.get(KWVariables.getVariables().get("rateQuoteURL_LIBRA"));
			driver.manage().window().maximize();
			break;
		case "scorpio":
			driver.get(KWVariables.getVariables().get("rateQuoteURL_SCORPIO"));
			driver.manage().window().maximize();
			break;
		case "capricorn":
			driver.get(KWVariables.getVariables().get("rateQuoteURL_QA_CAPRICON"));
			driver.manage().window().maximize();
			break;
		case "aquarius":
			driver.get(KWVariables.getVariables().get("rateQuoteURL_QA_AQUARIUS"));
			driver.manage().window().maximize();
			break;
		case "aries":
			driver.get(KWVariables.getVariables().get("rateQuoteURL_QA_ARIES"));
			driver.manage().window().maximize();
			break;
		case "pisces":
			driver.get(KWVariables.getVariables().get("rateQuoteURL_QA_PISCES"));
			driver.manage().window().maximize();
			break;
		}
	}

	public void enterRateQuoteFields() throws Throwable{
		System.out.println("Entered enterRateQuoteFields");
		WebDriver driver = DriverFactory.getDriver();
		try{
			String rateQuoteQuesAnString=step.getDataValue("question_ans")+"&&"+step.getDataValue("PropertyQuestionAnswer")+"&&"+step.getDataValue("Additional Data");
			String question="",expAnswer="",TenOThreeQuestion="";
			buildRateQuoteMappingwith1003();
			dataMap = Ten0Three.buildDataMap(rateQuoteQuesAnString);

			Util.click(loanType.replace("%val", dataMap.get("What is the purpose of this loan?").toLowerCase()));
			do{
				question=Util.getText(questionLocator);
				System.out.println("Question"+question);
				TenOThreeQuestion=rateQuote1003Map.get(question);
				System.out.println("TenOThreeQuestion"+TenOThreeQuestion);
				expAnswer=dataMap.get(TenOThreeQuestion);
				System.out.println("expAnswer"+expAnswer);
				if(expAnswer.contains("ta"))
					expAnswer=expAnswer.split("_")[1].trim();
				if((driver.findElements(By.xpath(isItRadioButtonLocator.replace("%q", question))).size())>1)
					Util.click(ques2AnsLocRadioButton.replace("%q", question).replace("%a", expAnswer));
				else{
					Util.sendText(ques2AnsLocTextBox.replace("%q", question), expAnswer);
					Thread.sleep(1000);
					clickNexButton();
				}
				Thread.sleep(1000);
			}while(driver.findElements(By.xpath("//div[@class='question-layout undefined']")).size()>0);
		}
		catch(Exception e){
			e.printStackTrace();
			addExceptionToReport("Error in enter Rate Quote", "", "");

		}
	}

	public void validateRateQuoteLeftPanel() throws Throwable{
		System.out.println("Entered validateRateQuoteLeftPanel");
		WebDriver driver = DriverFactory.getDriver();
		String question="",expAnswer="",TenOThreeQuestion="",labelValue="";
		String rateQuoteQuesAnString=step.getDataValue("question_ans")+"&&"+step.getDataValue("PropertyQuestionAnswer")+"&&"+step.getDataValue("Additional Data");
		dataMap = Ten0Three.buildDataMap(rateQuoteQuesAnString);
		buildRateQuoteMappingwith1003();
		int labelRowSize=driver.findElements(By.xpath(leftPanelRows)).size();
		for(int i=1;i<=labelRowSize;i++){
			String labelOnLeftPanelLoc=leftPanelLabelsSize.replace("%i", String.valueOf(i));
			int labelOnLeftPanelSize=driver.findElements(By.xpath(labelOnLeftPanelLoc)).size();
			for(int j=1;j<=labelOnLeftPanelSize;j++){
				String leftPanelLabel=Util.getText((leftPanelLabelLoc.replace("%i", String.valueOf(i)).replace("%j", String.valueOf(j)))).trim();
				System.out.println("Left Panel Label>>>"+leftPanelLabel);
				if(i==1){
					leftPanelLabelValueLoc="//div[@class='item']//label[text()='%s']//following-sibling::div/input//following-sibling::div//span[@class='Select-value-label']";
					labelValue=Util.getText(leftPanelLabelValueLoc.replace("%s", leftPanelLabel));
				}
				else {
					leftPanelLabelValueLoc="//div[@class='item']//label[text()='%s']//following-sibling::input";
					labelValue=Util.getAttribute("value",leftPanelLabelValueLoc.replace("%s", leftPanelLabel));
					Thread.sleep(2000);
				}
				if(labelValue.contains("$"))
					labelValue=labelValue.replace("$","");
				System.out.println("Left Panel Label Value>>>"+labelValue);
				if(!(leftPanelLabel.equalsIgnoreCase("Property Use"))){
					TenOThreeQuestion=rateQuote1003Map.get(leftPanelLabel);
					System.out.println("TenOThreeQuestion"+TenOThreeQuestion);
					expAnswer=dataMap.get(TenOThreeQuestion);

					if(expAnswer.contains("ta"))
						expAnswer=expAnswer.split("_")[1].trim();
					System.out.println("expAnswer"+expAnswer);

					if(!(expAnswer.equalsIgnoreCase(labelValue))){
						addExceptionToReport("Validation failed in Rate Quote Left Panel", labelValue, expAnswer);
					}
				}
			}
		}
	}

	public HashMap<String, String> getFirstRateQuoteDetails() throws Throwable{
		System.out.println("Entered getFirstRateQuoteDetails");
		WebDriver driver = DriverFactory.getDriver();
		if(driver.findElements(By.xpath(rateQuotes)).size()>1){
			System.out.println("One or More Rate Quotes Present");

		}
		else
			addExceptionToReport("Rate Quotes not available after entering all data", "", "");
		selectedRateQuote.put("Monthly Payment", Util.getText(selectedRateQuoteMonthlyPayment));
		int selectedRateQuoteOtherDetailsSize=driver.findElements(By.xpath(selectedRateQuoteOtherDetails)).size();
		for(int i=1;i<=selectedRateQuoteOtherDetailsSize;i++){
			String key=Util.getText(selectedRateQuoteOtherDetailskeyValue.replace("%j", "1").replace("%i", String.valueOf(i)));
			String value=Util.getText(selectedRateQuoteOtherDetailskeyValue.replace("%j", "2").replace("%i", String.valueOf(i)));
			selectedRateQuote.put(key,value);
		}
		return selectedRateQuote;
	}

	public void selectFirstRateQuoteAndNavigatetoPortal() throws Throwable{
		System.out.println("Entered getFirstRateQuoteDetails");
		WebDriver driver = DriverFactory.getDriver();
		Util.click(firstRateQuote);
		String rateQuoteQuesAnString=step.getDataValue("question_ans")+"&&"+step.getDataValue("PropertyQuestionAnswer")+"&&"+step.getDataValue("Additional Data");
		dataMap = Ten0Three.buildDataMap(rateQuoteQuesAnString);
		if(dataMap.get("Application Type").equalsIgnoreCase("Pre-qual"))
			Util.click(applyPrequalForFirstRateQuote);
		else
			Util.click(applyFullAppForFirstRateQuote);
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(loginButtonOnPennyMacPage))));
		System.out.println("Navigated to Penny Mac from Rate Quote URL");
	}
	public void clickNexButton() throws Throwable {
		Util.click(nextButton);
	}
	public void buildRateQuoteMappingwith1003() throws Throwable{
		rateQuote1003Map=Ten0Three.buildDataMap(step.getDataValue("1003 fields to Rate Quote Mapping"));
		/*System.out.println();
		for (String name: rateQuote1003Map.keySet()){
			String key = name.toString();
			String value = rateQuote1003Map.get(name).toString();  
			System.out.println("rateQuote1003Map"+key + ":" + value);  
		}*/
	}
	public void getLoanDetailsFromDB() throws Throwable{
		Util util = new Util();
		String loanNumber=util.getLoanFromSubmitScreen();
		String rateQuoteQuesAnString=step.getDataValue("question_ans")+"&&"+step.getDataValue("PropertyQuestionAnswer")+"&&"+step.getDataValue("Additional Data");
		buildRateQuoteMappingwith1003();
		dataMap = Ten0Three.buildDataMap(rateQuoteQuesAnString);
		//"7202215147";
		String[] listOFFields;
		String[] listOFFieldsForPurchase={"applicationType","purposeCategory","propertyValue","downPaymentAmount"};
		String[] listOFFieldsForRefinance={"applicationType","purposeCategory","propertyValue","cashOutAmount","mortgageDebtBalance"};
		if(dataMap.get("What is the purpose of this loan?").equalsIgnoreCase("purchase")){
			dataFromDB=Util.getLoanDetailsFromDB(loanNumber,listOFFieldsForPurchase);
			listOFFields=listOFFieldsForPurchase;
		}
		else {
			dataFromDB=Util.getLoanDetailsFromDB(loanNumber,listOFFieldsForRefinance);
			listOFFields=listOFFieldsForRefinance;
		}
		for (String name: dataFromDB.keySet()){
			String key = name.toString();
			String value = dataFromDB.get(name).toString();  
			System.out.println("dataFromDB"+key + ":" + value);  
		}

		for(String field:listOFFields){
			String expValue=dataMap.get((rateQuote1003Map.get("DB_"+field)));
			if(expValue.contains("ta"))
				expValue=expValue.split("_")[1];
			expValue=expValue.replace(",", "").trim();
			String actValue=dataFromDB.get(field);
			if(!actValue.equalsIgnoreCase(expValue))
				addExceptionToReport("Data entered in Rate Quote is not matching with data in DB", actValue, expValue);
		}

	}
}

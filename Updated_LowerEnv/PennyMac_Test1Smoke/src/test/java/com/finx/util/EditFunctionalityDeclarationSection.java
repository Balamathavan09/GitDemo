package com.finx.util;


import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;
import com.tavant.base.DriverFactory;
import com.tavant.kwutils.CustomStep;
import com.tavant.utils.TwfException;  

public class EditFunctionalityDeclarationSection extends CustomStep {
	String URL = "http://10.131.148.191:9090/#/loan-application";
	String question = "(//div[@class[contains(.,'active-section')]]//dynamic-form/form//dynamic-control//div/label)[%s]"; 
	//String answerNo = "(//div[@class[contains(.,'active-section')]]//dynamic-form/form//div[@data-type='input-switch']//div[@class[contains(.,'ui-inputswitch-off')]])[%s]";
	String answerNo = "(//div[@class[contains(.,'active-section')]]//dynamic-form/form//div[@data-type='radioBtn']//div/button[2])[%s]";
	String answerYes = "(//div[@class[contains(.,'active-section')]]//dynamic-form/form//div[@data-type='radioBtn']//div/button[1])[%s]"; 
	  
	//String answerYes = "((//div[@class[contains(.,'active-section')]]//dynamic-form/form//div[@data-type='input-switch']//span[@class[contains(.,'ui-inputswitch-onlabel')]])[%s])[1]"; 
	//String answerYes = "(//div[@class[contains(.,'active-section')]]//span[@class[contains(.,'ui-inputswitch-on')]])[%s]";  
//	String answerYes = "(//div[@class='ui-inputswitch-on'])[%s]/span";  
	String OkayBUtton1 = "//div[@class[contains(.,'form-container tf-block-%s active')]]//div[@class='form-group']//form//button[@class[contains(.,'submit')]]"; 
	String activeBlock = "//div[@class='ps-content']/div[@class[contains(.,'tf-block-%s active-section')]]";
	String multiDrop = "//div[@class[contains(.,'active-section')]]//dynamic-form/form//multidropdown//p-multiselect//div/label[contains(text(),'%placeholder')]";  
	//String multiDropvalue = "//div[@class[contains(.,'active-section')]]//dynamic-form/form//multidropdown//p-multiselect//div/label[contains(text(),'%placeholder')]";  
	String multiDropvalue =".//div[@class[contains(.,'active-section')]]//dynamic-control//multiselect-select//button[contains(.,'%placeholder')]";   
	String attribute1 ="(.//div[@class='form-container tf-block-%s active-section']//dynamic-form//dynamic-control/div/div/div/div/div)[1]";
	String radioAns ="(.//div[@class='form-container tf-block-%s active-section']//dynamic-control//radio-button-selection//button[contains(.,'%placeholder')])[1]";
	String sub_ans_text =".//div[@class[contains(.,'active-section')]]//dynamic-control//md-input-container//input[@placeholder='%placeholder']";
	
//		   
// 	GenerateData2 dummyData = new GenerateData2();    
Util utill = new Util();
	
	public HashMap<String, String> buildDataMap(String testData) {
		HashMap<String, String> qaMap = new HashMap<String, String>(); 
		String[] queAns = testData.split("&&");
		for (String qA : queAns) {
			qaMap.put(qA.split(";")[0].trim(), qA.split(";")[1].trim()); 
			System.out.println("key   == " +qA.split(";")[0].trim());
			System.out.println("value == " +qA.split(";")[1].trim()); 

		}  
		return qaMap; 

	}

	public void beforePageLoad() throws TwfException {
		WebDriver driver = DriverFactory.getDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.mpPageReloaded='notYet';");
	}
	
	public void editDeclarationsQuestionAnswer() throws InterruptedException, TwfException, Exception {	

		System.out.println("inside EditDeclarationsQuestionAnswer ");
		String DeclarationsQuestionAnswer = step.getDataValue("DeclarationQuestionAnswer");
		
		Thread.sleep(4000);   			
		validateDeclarationIn1003(DeclarationsQuestionAnswer); 
		 
	}
	

	

	void validateDeclarationIn1003(String quesAnsString) throws Exception {		
		HashMap<String, String> dataMap = new HashMap<String, String>();
		dataMap = buildDataMap(quesAnsString); 
		
		String str = selectQuestion2AnswerInDeclaration(dataMap);		 
		if (str.contains("false")) { 
			System.out.println(str);
			addExceptionToReport("Expected Question in not enabled.Hence cannot move forward.", "", str);
  
		} 
		 
	}

	private String selectQuestion2AnswerInDeclaration(HashMap<String, String> dataMap) throws Exception {
		WebDriver driver = DriverFactory.getDriver();
		String status = "";
		int questionCount = 0;
		String answer = ""; 
		String temp_question ="";
		String attribute = null;

		for (int activeBlockindex = 0; (driver.findElements(By.xpath(activeBlock.replace("%s", String.valueOf(activeBlockindex)))).size() > 0); activeBlockindex++)   		
		{ 
			attribute = driver.findElement(By.xpath(attribute1.replace("%s", String.valueOf(activeBlockindex)))).getAttribute("data-type").trim();
			 System.out.println( " inside attribute --> " +   attribute  );		    			
			System.out.println("blockindex --- > " + activeBlockindex);       
			Thread.sleep(1000); 
		//	questionCount = driver.findElements(By.xpath("//div[@class[contains(.,'active-section')]]//dynamic-form/form//div[@class[contains(.,'title-section')]]//label")).size();
			questionCount = driver.findElements(By.xpath("//div[@class[contains(.,'active-section')]]//dynamic-form/form//dynamic-control//div/label")).size();    
			System.out.println(" questionCount -----> " + questionCount);   
			    
			Thread.sleep(1000);         
			for (int qusIndex = 1; qusIndex <= questionCount; qusIndex++) {
 
				String currentQuestion = driver.findElement(By.xpath(question.replace("%s", String.valueOf(qusIndex)))).getText().trim();
				temp_question =currentQuestion;
				System.out.println("question --> " + currentQuestion);
				System.out.println("ans --" + dataMap.get(currentQuestion));
				answer = dataMap.get(currentQuestion);
 
				if (answer.equalsIgnoreCase("No")) {

				 	boolean statusNO = driver.findElement(By.xpath(answerNo.replace("%s", String.valueOf(qusIndex)))).getAttribute("class").contains("active-btn"); 
				 	System.out.println("statusNO: "+statusNO);
				 	
//				 	if(currentQuestion.contains("Are you obligated to pay"))  
//				 	{ 
//				 		utill.scrollToElement(driver.findElement(By.xpath(OkayBUtton1.replace("%s", String.valueOf(activeBlockindex)))));  
//				 	}
					//  utill.scrollToElement(driver.findElement(By.xpath(answerNo.replace("%s", String.valueOf(qusIndex)))));   
					System.out.println(" clicked on No for " + currentQuestion);
				} else if(answer.equalsIgnoreCase("Yes")){

//					if(currentQuestion.contains("Are you obligated to pay")) 
//				 	{ 
//				 		utill.scrollToElement(driver.findElement(By.xpath(OkayBUtton1.replace("%s", String.valueOf(activeBlockindex)))));    
//					}  

					boolean statusYes = driver.findElement(By.xpath(answerYes.replace("%s", String.valueOf(qusIndex)))).getAttribute("class").contains("active-btn");
					System.out.println("statusYes: "+statusYes);

					System.out.println("           before click on Yes");
					// driver.findElement(By.xpath(answerYes.replace("%s",
					// String.valueOf(qusIndex)))).click();

					System.out.println(" clicked on Yes for " + currentQuestion);

				} else if (answer.contains("RB")) {  
					System.out.println("inside RB "); 
					String[] sub_fields = answer.split("%");
					String placeholder = sub_fields[1].trim(); 
					boolean statusRB =driver.findElement(By.xpath(radioAns.replace("%s", String.valueOf(activeBlockindex))
							.replace("%placeholder", String.valueOf(placeholder)))).getAttribute("class").contains("active-btn");
					System.out.println("          radio button"+statusRB);
				}  
				
				//TA%Enter name_fINex
				else if (answer.contains("TA")) {  
					System.out.println("inside TA "); 
					String[] ans = answer.split("%");
					String[] sub_fields = ans[1].split("_");
					String placeholder = sub_fields[0].trim(); 
					String value = sub_fields[1].trim(); 
					String textdata = driver.findElement(By.xpath(sub_ans_text.replace("%placeholder", String.valueOf(placeholder)))).getAttribute("value");  
					System.out.println("textdata: "+textdata);
					 
				}  				 
				else if (currentQuestion.contains("Your demographic information")
						|| currentQuestion.contains("The purpose of collecting ")) {
					System.out.println("inside Your demographic information ");
					//driver.findElement(By.xpath(OkayBUtton1.replace("%s", String.valueOf(activeBlockindex)))).click();
				} else {

					System.out.println(" answer in multidrop --> " + answer);
					String[] sub_fields = answer.split(":");
					// ta|First Name_prasann:ta|Middle Name_abc:dd%Suffix_Sr

					System.out.println("sub_fields length " + sub_fields.length);

					for (int sub_field_index = 0; sub_field_index < sub_fields.length; sub_field_index++) {

						System.out.println(" sub_field_index  " + sub_fields[sub_field_index]);
						String selectedvalue = driver.findElement(By.xpath(multiDropvalue.replace("%placeholder", String.valueOf(sub_fields[sub_field_index])))).getText();
						 
						 	System.out.println("selectedvalue:--------------->"+selectedvalue);				            
						/*String[] field_type = (sub_fields[sub_+field_index]).trim().split("%");
						System.out.println("field_type " + field_type[0] + " and " + field_type[1]);	 				
						       
						if ((field_type[0].trim()).equalsIgnoreCase("dd")) {  
							System.out.println("inside dd -- " + field_type[1]);
							String[] placeholder_value = field_type[1].split("_");
							String placeholder = placeholder_value[0];
							String value = placeholder_value[1];

							System.out.println("placeholder " + placeholder);
							System.out.println("value " + value);
							clickOnElementWhenElementIsReady(driver.findElement(
									By.xpath(multiDrop.replace("%placeholder", String.valueOf(placeholder)))));						
						}*/
					} 
					
				//	driver.findElement(By.xpath(multiDrop.replace("%placeholder", String.valueOf(qusIndex)))).click();
					System.out.println(" Answered  for " + currentQuestion);
					  
				} 
			}   
			 //
			
			if (!((temp_question.contains("Your demographic information"))||temp_question.contains("Sex") )) { 
				driver.findElement(By.xpath(OkayBUtton1.replace("%s", String.valueOf(activeBlockindex)))).click();
				//System.out.println("selectedoption: "+selectedoption);
				Thread.sleep(1000);
			}     
   
			status = "true";

		} 

		return status;

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

	@Override
	public void checkPage() {

		// TODO Auto-generated method stub

	}

}

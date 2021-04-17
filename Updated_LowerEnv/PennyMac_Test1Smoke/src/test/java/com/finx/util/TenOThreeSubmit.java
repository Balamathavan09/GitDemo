package com.finx.util;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.finx.util.*;
import com.tavant.base.DriverFactory;
import com.tavant.kwutils.CustomStep;
import com.tavant.utils.TwfException;

public class TenOThreeSubmit extends CustomStep {
	
	static WebDriver driver;
	Ten0Three tenothree =  new Ten0Three();	
	Declarations declaration = new Declarations();
	Util util = new Util();
	
	
	String pendingSections =".//*[@class='pending-section-title']";  
	String sectionindex ="(.//*[@class='pending-section-title']/a)[%s]";
	String checkSection ="//div[@class='pending-section-title']//a[contains(.,'%s')]"; 
	  
	
	
	
	/**
	 * Method Name: pendingSummarySections
	 *  Purpose: To validate and fill  pending sections through summary submit section
	 * 
	 * @throws Exception
	 */
	
	public void pendingSummarySections() throws InterruptedException, TwfException, Exception{
		System.out.println("inside pendingSummarySections ");  
		driver = DriverFactory.getDriver();   
		int pendingseccount = driver.findElements(By.xpath(pendingSections)).size(); 			
		boolean flag =false;
		String sucess_Proceed_message = "Thank you for providing your loan information. Please complete the next steps in order to submit your application.";
		
		 
		 
		
		String module = step.getDataValue("Module");	 
		if(module.contains("1003"))  
		{
			flag =true;
			System.out.println("flag  ----> " + flag );
			
		}  
				 
		
		String section;
		WebElement sectionele;
		
		for (int index = 1; index <= pendingseccount; index++) {

			if (!(getElementByUsing("1003_Submit_success-proceed").getText().contains(sucess_Proceed_message))) {
				addExceptionToReport("sucess_Proceed_message",  "      ", sucess_Proceed_message + "  should be display  ");  
			} 

			sectionele = driver.findElement(By.xpath(sectionindex.replace("%s", "1")));
			section = sectionele.getText();

			System.out.println("section name " + index + " is --> " + section );
			incompleteSectionFilling(section, sectionele, flag,module);
 
		} 		  
	}
	
	
	/**
	 * Method Name: validatesectionfield
	 *  Purpose: To validate that already filled section should not display in summary/ submit screen. 
	 * 
	 * @throws Exception 
	 */
	
	
	public void validatesectionfield(String sec) throws TwfException {
		System.out.println(" inside   validatesectionfield   ");
		String str = driver.findElement(By.xpath(pendingSections)).getText();

		System.out.println(str);
		if (str.contains(sec)) {
 
			addExceptionToReport("validatesectionfield", sec + " field  is displaying ", " ");
		} 
  
	}  
	  
	
	/**
	 * Method Name: incompleteSectionFilling
	 *  Purpose: To navigate and fill   incomplete section through  summary/ Submit screen. 
	 * 
	 * @throws Exception 
	 */
	 private void incompleteSectionFilling(String section, WebElement sectionele, boolean flag, String module) throws InterruptedException, TwfException, Exception{
		    switch(section){   
		      
		case "Property":
			sectionele.click();
			System.out.println("Property...............?");
			Thread.sleep(6000);   
		//	tenothree.validatePropertyQuestionAnswerIn1003(); 			
					
			String propertyQuestionAnswer = step.getDataValue("PropertyQuestionAnswer");	
			System.out.println("PropertyQuestionAnswer  --> " + propertyQuestionAnswer  ); 
			getElementByUsing("FinExp_propertyEditIcon").click();
			Thread.sleep(3000);
		   tenothree.validateQuestionAnswerIn1003(propertyQuestionAnswer); 
			getElementByUsing("1003_GetStarted_IAmDone").click();  
			Thread.sleep(2000);
			getElementByUsing("TopMenu_Submit").click();
			Thread.sleep(2000); 
			validatesectionfield("Property");  
 
  
			break; 
		case "Property Details": 
			sectionele.click();
			System.out.println("Property...............?");
			Thread.sleep(6000);   
		//	tenothree.validatePropertyQuestionAnswerIn1003(); 			
					
			String propertyQuestions = step.getDataValue("PropertyQuestionAnswer");	
			System.out.println("PropertyQuestionAnswer  --> " + propertyQuestions  ); 
		   tenothree.validateQuestionAnswerIn1003(propertyQuestions);   
			getElementByUsing("1003_GetStarted_IAmDone").click();  
			Thread.sleep(2000);
			getElementByUsing("TopMenu_Submit").click();
			Thread.sleep(2000); 
			validatesectionfield("Property");  
 
  
			break;
		case "Income": 
			sectionele.click(); 
			Thread.sleep(4000);
			getElementByUsing("1003_Income_AddIncomeButton").click();
			Thread.sleep(2000);
			
			if(flag)
			getElementByUsing("1003_Income_AddItManuallyButton").click();
			Thread.sleep(2000);
			String incomeQuestionAnswer = step.getDataValue("1003_Income_Business");
			tenothree.validateQuestionAnswerIn1003(incomeQuestionAnswer);
			Thread.sleep(2000);
			getElementByUsing("1003_GetStarted_IAmDone").click();
			System.out.println("***************************");
			Thread.sleep(2000);
			getElementByUsing("TopMenu_Submit").click();
			Thread.sleep(2000);
			validatesectionfield("Income"); 

			break;

		case "Assets":
			System.out.println("*************Assets****************");
			sectionele.click();
			Thread.sleep(4000);
			getElementByUsing("1003_Assets_Button").click();
			Thread.sleep(2000);
			if(flag)
			getElementByUsing("1003_Property_AddItManuallyButton").click();
			Thread.sleep(2000);
//			String answer = step.getDataValue("1003_Asset_Manual");
//			System.out.println("answer:"+answer);
			String assetQuestionAnswer = step.getDataValue("1003_Asset_Manual");
			System.out.println("assetQuestionAnswer: "+assetQuestionAnswer);
			
			System.out.println("*********** assetQuestionAnswer *******");
			
			tenothree.validateQuestionAnswerIn1003(assetQuestionAnswer);
			System.out.println(" after  assetQuestionAnswer *******");
			Thread.sleep(10000); 
			System.out.println("before i am done");
			getElementByUsing("1003_GetStarted_IAmDone").click();
			Thread.sleep(2000);
			System.out.println("after  i am done");
			getElementByUsing("TopMenu_Submit").click();
			Thread.sleep(2000);
			validatesectionfield("Assets");     
			System.out.println("********************");
			break;

		case "Liabilities":

			System.out.println("Liabilities");
			sectionele.click();
			Thread.sleep(4000);
			//getElementByUsing("1003_Liabilities_AddLiabilityButton").click();
			getElementByUsing("1003_Liabilities_NoLiabilityButton").click();
			Thread.sleep(2000);
//			if(flag) 
//			getElementByUsing("1003_Liability_AddItManuallyButton").click();
//			Thread.sleep(2000);
//			String liabilityQuestionAnswer = step.getDataValue("LiabilityQuestionAnswer");			
//			tenothree.validateQuestionAnswerIn1003(liabilityQuestionAnswer);
//			getElementByUsing("1003_GetStarted_IAmDone").click();
//			Thread.sleep(2000);
	getElementByUsing("TopMenu_Submit").click();
//			Thread.sleep(2000);
//			validatesectionfield("Liabilities"); 
			System.out.println("End Liabilities");
			break;

		case "Real Estate":

			System.out.println("Real Estate--");
			sectionele.click();
			Thread.sleep(4000);
			getElementByUsing("1003_RealEstateButton").click();
			Thread.sleep(2000);
			String realEstateQuestionAnswer = step.getDataValue("RealEstateQuestionAnswer");			
			tenothree.validateQuestionAnswerIn1003(realEstateQuestionAnswer);
			getElementByUsing("1003_GetStarted_IAmDone").click();
			Thread.sleep(2000);
			getElementByUsing("TopMenu_Submit").click();
			Thread.sleep(2000);
		//	validatesectionfield("Real Estate"); 
			break;   
 
		case "Declarations":
			System.out.println("Declarations");
			sectionele.click();    
			Thread.sleep(3000);  
			
			getElementByUsing("1003_Declarations_Button").click();
			Thread.sleep(1000);
			String declarationQuestionAnswer = step.getDataValue("DeclarationQuestionAnswer");			
			if(module.trim().equalsIgnoreCase("prequal"))
			{
				tenothree.validateQuestionAnswerIn1003(declarationQuestionAnswer);
			}
			else
			{
			declaration.validateDeclarationIn1003(declarationQuestionAnswer);   
			}
		//	tenothree.validateQuestionAnswerIn1003(declarationQuestionAnswer); 
			//Thread.sleep(3000);
			
			getElementByUsing("1003_GetStarted_IAmDone").click();
			Thread.sleep(2000);		   
			  
			getElementByUsing("TopMenu_Submit").click();
			 Thread.sleep(2000);
			// validatesectionfield("Declarations");
 
			break;

		case "EConsent":
			System.out.println("EConsent");
			sectionele.click();       
			Thread.sleep(4000);
			getElementByUsing("IConsentButton").click();
			Thread.sleep(2000);	
			getElementByUsing("1003_GetStarted_IAmDone").click();
			Thread.sleep(2000);		
			System.out.println(" after EConsent"); 
			
		//	getElementByUsing("TopMenu_Submit").click();
			Thread.sleep(2000);

		//	validatesectionfield("EConsent");

			break; 
  
		} 
	}
	     
 	
	@Override
	public void checkPage() {
		// TODO Auto-generated method stub
		
	}

	  
}


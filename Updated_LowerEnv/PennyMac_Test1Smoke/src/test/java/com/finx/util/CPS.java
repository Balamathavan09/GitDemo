package com.finx.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tavant.base.DriverFactory;
import com.tavant.kwutils.CustomStep;
import com.tavant.utils.TwfException;

public class CPS extends CustomStep{
	TenOThreeSectionSummary tenothreesectionsummary= new TenOThreeSectionSummary();
	Ten0Three ten0Three = new Ten0Three();
	String radioButtonAns = "//label[contains(.,\"%label\")]/parent::div//form//button[contains(.,'%v')]";
	//String radioButtonAns = "//label[contains(.,\"%label\")]/parent::div//form//button[@class='active-btn']";
	String OkayBUtton1 = "//label[contains(.,'%label')]/parent::div//form//div[contains(@class,'submit')]/button";
	//String sectionValues="//label[contains(.,'%label')]/parent::div//form//label[contains(.,'%field')]";
	String sectionValues= "//label[contains(.,\"%label\")]/parent::div//input[@placeholder='%field']";
	String[] expectedSpouseDetailQtns = { "Provide spouse details", "Spouse contact details are", "Spouse date of birth is", "Spouse address is","I'd like to add spouse as a co-applicant"};
	String attributeXPath = "(//label[contains(.,\"%s\")]//following-sibling::dynamic-form//dynamic-control/div/div//div/div/div)[1]";
	public void spouseDetailsVerification() throws TwfException, InterruptedException, BiffException, IOException{
		System.out.println("Entered spouseDetailsVerification");
		String expectedTestData = step.getDataValue("question_ans");
		WebDriver driver = DriverFactory.getDriver();
		driver.findElement(By.xpath(".//div[@class='icons text-right']/i")).click();
		Thread.sleep(5000);
		WebElement ele = driver.findElement(By.xpath("//label[contains(.,'Provide spouse details')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", ele);
		Thread.sleep(5000);
		HashMap<String, String> dataMap = new HashMap<String, String>();
		//dataMap = ten0Three.buildDataMap(expectedTestData);
		//Set<String> qtnKey = dataMap.keySet();
		
		for(String expectedQtn:expectedSpouseDetailQtns){			
			String[] expectedAnswers = dataMap.get(expectedQtn).split(":");
			for(String expectedAnswer:expectedAnswers){
				String fieldName = "";				
				String fieldAnswer = "";
				String fieldDataType = "";			
				String displayedAnswer = "";
				if(expectedQtn.trim().equalsIgnoreCase("Spouse date of birth is")){
					fieldName = "MM/DD/YYYY";
					fieldAnswer = expectedAnswer;
					fieldDataType = "ta";
				}else if(expectedQtn.trim().equalsIgnoreCase("I'd like to add spouse as a co-applicant")){
					fieldAnswer = expectedAnswer;
				}else{
					fieldName = expectedAnswer.split("%")[1].split("_")[0];
					fieldAnswer = expectedAnswer.split("%")[1].split("_")[1];
					fieldDataType = expectedAnswer.split("%")[0];
				}			
				String qtnAttr = attributeXPath.replace("%s", expectedQtn);
				if(driver.findElement(By.xpath(qtnAttr.trim())).getAttribute("data-type").trim().equalsIgnoreCase("radioBtn")){
					fieldDataType = "rb";
				}				
				switch (fieldDataType.trim().toLowerCase()) {
				
				case "rb":				
					String attribute = driver.findElement(By.xpath(radioButtonAns.replace("%label", expectedQtn).replace("%v", fieldAnswer))).getAttribute("class");
					if(attribute.contains("active")){
						displayedAnswer = fieldAnswer;
					}else{
						displayedAnswer="";
					}
					break;
				case "dd":
					//UI team to provide a proper attribute value to State,City drop down
					String expectedXpath = null;
					if(fieldName.trim().equalsIgnoreCase("City")){
						expectedXpath = "(//label[contains(.,'Spouse address is')]/parent::div//button[contains(@id,'control.id')])[2]";
					}else{
						expectedXpath = "(//label[contains(.,'Spouse address is')]/parent::div//button[contains(@id,'control.id')])[1]";
					}
					displayedAnswer = driver.findElement(By.xpath(expectedXpath)).getAttribute("value");
					break;
					
				default:
					displayedAnswer = driver.findElement(By.xpath(sectionValues.replace("%label", expectedQtn).replace("%field", fieldName.trim()).trim())).getAttribute("value");
					break;
				}	
				
				if(displayedAnswer.trim().equalsIgnoreCase(fieldAnswer.trim()) == false){
					addExceptionToReport("Expected Answer is not matching with the displayed answer for the field "+fieldName+"under "+expectedQtn+" section after applicant edit operation.", displayedAnswer, fieldAnswer);
				}
			}
			
			
			//TA %First name_ aaaa 
		}}
			
	     

			public void editQuestionAndAnswerForDeclarations() throws InterruptedException, Exception {
				System.out.println("editQuestionAndAnswerForDeclarations");
				WebDriver driver = DriverFactory.getDriver();
				String quesAnsString = step.getDataValue("AnswerchangingFlow");
				//String fieldMap = step.getDataValue("SummaryFieldMapping");
				HashMap<String, String> dataMap = ten0Three.validateQuestionAnswerIn1003(quesAnsString);
			}
			
			
			
			
			
		
		
		
		
		/*String quesAnsString= step.getDataValue("cpsSpouseDetails");
		HashMap<String, String> dataMap = new HashMap<String, String>();
		dataMap = Ten0Three.buildDataMap(quesAnsString);
		System.out.println(dataMap.size());
		
	for (Map.Entry<String, String> entry : dataMap.entrySet())
	{ String expectedQtns = entry.getKey();
	System.out.println("expectedQtns: "+expectedQtns);
	
		    	String[] value = dataMap.get(expectedQtns).split(":");
		    	for(int j=0; j<value.length;j++){
		    		System.out.println("j"+j);
		    		String question = expectedQtns;
		    		System.out.println("question"+question);
	
					String[] field_type = value[j].split("%"); 
							       
							if((field_type[0].trim()).equalsIgnoreCase("ta"))    
							{ 
								System.out.println("entered text arrea"); 
								String[] placeholder_value = (field_type[1].trim()).split("_");  
								String placeholder = placeholder_value[0].trim();	 		
							
								String valueoftextbox = placeholder_value[1]; 
								System.out.println("valueoftextbox: "+valueoftextbox); 
								tribute("value").trim();
								System.out.println("textvalue: "+textvalue);String xpathOfQuestionBlock = sectionValues.replace("%label", question).replace("%field", placeholder.trim()).trim();
								String textvalue=  driver.findElement(By.xpath(xpathOfQuestionBlock)).getAt
										
										if(!textvalue.equalsIgnoreCase((valueoftextbox).trim())){
									addExceptionToReport("Expected Value is not matching with displayed value of field", textvalue, valueoftextbox);
							}}
							else if((field_type[0].trim()).equalsIgnoreCase("RB"))
							{
								
							
						 	System.out.println("inside RB............ " + field_type[1]  );   
						 	
					
								String status = driver.findElement(By.xpath(radioButtonAns.replace("%label", question).replace("%v", field_type[1]))).getAttribute("class");
								
								System.out.println(status);
								if(status.contains("active")){
									System.out.println("matched");
								}
								else 
									System.out.println("fail");
								
								System.out.println("ending for loop j");
								
							  
							}     
							
							System.out.println("clciked");
						}
		    	//driver.findElement(By.xpath(OkayBUtton1.replace("%label", question))).click();
		    	//driver.findElement(By.xpath(OkayBUtton1.replace("%s", String.valueOf(qusIndex)))).click();
					
				System.out.println("ouy of for loop");    

							
							
							
							
						}
		    	//driver.findElement(By.xpath(OkayBUtton1.replace("%label", question))).click();
		    	//driver.findElement(By.xpath(OkayBUtton1.replace("%s", String.valueOf(qusIndex)))).click();*/
					

		    	
		    	
	
		    

		
		
		
		
		
		
		
		
		
		
		//String textvalue=driver.findElement(By.xpath(sectionValues.replace("%label", String.valueOf(questionnameFromExcel)).replace("%field", verifySpouseDetails.get(key)))).getText();
		
		
//		String[] expectedQtns = { "Provide spouse details", "Spouse contact details are", "Spouse date of birth is", "Spouse address is", "I'd like to add spouse as a co-applicant"};
//		String fieldMap = step.getDataValue("cpsSpouseDetails");
//		
//		HashMap<String, HashMap<String, String>> questionAnswerPair = new HashMap<String, HashMap<String, String>>();
//		String [] questionandAnswerSplit = fieldMap.split("&&");
//		String[] fieldsData = null;
//		String[] placeholder = null;
//		//String[] listOfPlaceHolderName = null;
//				for(int j=0;j<questionandAnswerSplit.length;j++){
//					HashMap<String, String> spousedetails = new HashMap<String, String>();
//					//System.out.println("enetered j loop"+j);
//					//System.out.println("questionandAnswerSplit.length: "+questionandAnswerSplit.length);
//					//System.out.println("questionandAnswerSplit"+j+questionandAnswerSplit[j]);
//					fieldsData = questionandAnswerSplit[j].split(";");
//					String questionText = fieldsData[0];
//					
//						//System.out.println("fieldsData[1]: "+fieldsData[1]);
//						
//					String[] answerForSpouse = fieldsData[1].split(":");	
//					System.out.println("answerForSpouse-length"+answerForSpouse.length);
//					for(int placeholdername =0; placeholdername<answerForSpouse.length;placeholdername++){
//						//System.out.println("**********************");
//					placeholder = answerForSpouse[placeholdername].split("_");
//					System.out.println(placeholder.length);
//					System.out.println("placeholder[i]"+placeholder[0]);
//					System.out.println("placeholder[p]"+placeholder[1]);
//					spousedetails.put(placeholder[0], placeholder[1]);
//					questionAnswerPair.put(questionText, spousedetails);
//					//continue;
//					}
//					
//					System.out.println("fieldsData[]"+j+fieldsData[j]);
//				}
//				
//				for(int numberOfQuestions =0; numberOfQuestions<expectedQtns.length;numberOfQuestions++){
//					System.out.println("Enterd verification");
//					String questionnameFromExcel = fieldsData[numberOfQuestions];
//					System.out.println("questionnameFromExcel:"+questionnameFromExcel);
//					HashMap<String, String> verifySpouseDetails = new HashMap<String, String>(); 
//					verifySpouseDetails= questionAnswerPair.get(questionnameFromExcel);
////					String placeholdername= listOfPlaceHolderName[numberOfQuestions];
////					System.out.println("placeholdername: "+placeholdername);
//				if(expectedQtns[numberOfQuestions].equalsIgnoreCase(questionnameFromExcel))	
//				{
//					//for(int placeholderloop = 0;placeholderloop<=verifySpouseDetails.size();placeholderloop++){
//					for(String key:verifySpouseDetails.keySet()){
//							
//					String textvalue=driver.findElement(By.xpath(sectionValues.replace("%label", String.valueOf(questionnameFromExcel)).replace("%field", verifySpouseDetails.get(key)))).getText();
//					System.out.println("textvalue: "+textvalue);
//					if(verifySpouseDetails.get(key).equalsIgnoreCase(textvalue)){
//						System.out.println("Both matched");
//					}
//					else
//						System.out.println("did not match");
//				}}}
//		
		
		

	

	@Override
	public void checkPage() {
		// TODO Auto-generated method stub
		
	}

	
	
}

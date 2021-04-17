package com.finx.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.apache.poi.util.SystemOutLogger;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tavant.kwutils.CustomStep;


public class MultipleValidationEncompass extends CustomStep{
	static int otherIncomeCountForAPair = 0,incomeCountForAPair=0;
	static Util util=new Util();
	static EncompassValidation env = new EncompassValidation();
	public static LinkedHashMap<String, LinkedHashMap<String,LinkedHashMap<String, String>>> incomeFinalEncMap = new LinkedHashMap<String, LinkedHashMap<String,LinkedHashMap<String, String>>>();
	public static HashMap<String, String> borPairOtherIncomeMap= new HashMap<String, String>();
	public static LinkedHashMap<String,LinkedHashMap<String, LinkedHashMap<String, String>>> mbIncomeDetailsMap = new LinkedHashMap<String,LinkedHashMap<String, LinkedHashMap<String, String>>>();
	public static HashMap<String, HashMap<String, String>> encompassValuesForQtnsMap = new LinkedHashMap<String, HashMap<String, String>>();
	public static  HashMap<String, HashMap<String, String>> encompassMap = new HashMap<String, HashMap<String, String>>();
	public static  HashMap<String, HashMap<String, String>> ansSpecificFieldIdMap = new HashMap<String, HashMap<String, String>>();	
	
	public static String returnFieldValue(String actValue,String borName,String fieldName,LinkedHashMap<String, String> expDetailsMap)throws Throwable{
		System.out.println("Inside returnFieldValue");
		String reqFieldName="",expNewVal="";
//		Ten0Three.fname="John";
		System.out.println("fieldName:"+fieldName);
		switch(fieldName){
		case "Source of Income":
			reqFieldName=expDetailsMap.get(fieldName);
			expNewVal=encompassValuesForQtnsMap.get(reqFieldName).get("EncompassValue");
			break;
		case "I am employed by a family member, property seller, real estate agent, or other party in the transaction":
		case "This is my current business":
		case "This is my current job":
		case "Percentage of ownership":
			reqFieldName=fieldName;
			expNewVal=encompassValuesForQtnsMap.get(reqFieldName).get("EncompassValue");
			break;
		case "Select":
			if(actValue.equalsIgnoreCase("Borrower")||actValue.equalsIgnoreCase("CoBorrower")){
				if(Ten0Three.fname.equalsIgnoreCase(borName))
					expNewVal="Borrower";
				else
					expNewVal="CoBorrower";
			}
			else{
				reqFieldName=expDetailsMap.get(fieldName);
				System.out.println("reqFieldName:"+reqFieldName);
				expNewVal=encompassValuesForQtnsMap.get("Income_"+reqFieldName).get("EncompassValue");
			}
			break;
		case "My asset type":
//		case "Select from other income":
		case "Select from other Assets":
			if(actValue.equalsIgnoreCase("Borrower")||actValue.equalsIgnoreCase("CoBorrower")||actValue.equalsIgnoreCase("Both")){
				if(expDetailsMap.containsKey("Held jointly with(Optional)"))
					expNewVal="Both";
				else if(Ten0Three.fname.equalsIgnoreCase(borName))
					expNewVal="Borrower";
				else
					expNewVal="CoBorrower";
			}
			else{
	
				reqFieldName=expDetailsMap.get(fieldName);
				expNewVal=encompassValuesForQtnsMap.get(reqFieldName).get("EncompassValue");
			}
			break;
		case "Held jointly with(Optional)":
			reqFieldName=fieldName;
			expNewVal=encompassValuesForQtnsMap.get(reqFieldName).get("EncompassValue");
			break;
		}
		if(expNewVal.equalsIgnoreCase("NA"))
			expNewVal="";
		System.out.println("expNewVal:"+expNewVal+"fieldName:"+fieldName);
		return expNewVal;
	}


	public static LinkedHashMap<String,LinkedHashMap<String, String>> buildQuestionMap(String qtnDetails) {
		String index="0";
		String str="Source of Income;Employment&&My employment details;ta%Employer name_EmployerOne:ta%Street address_1 Danforth Avenue:ta%Zip Code_45202:ta%Unit# (Optional)_ 23:ta%Employer phone (Optional)_5498658798&&Additional details;ta%Job title_Business Anlyst:CB%I am employed by a family member, property seller, real estate agent, or other party in the transaction_I am employed by a family member, property seller, real estate agent, or other party in the transaction:DatePicker%Start Date of Employment_12/12/2005:DatePicker%End Date of Employment_12/12/2015:ta%Years_10:ta%Months (Optional)_3&&My monthly earnings are;ta%Monthly earnings_1000:ta%Overtime (Optional)_2000:ta%Bonus (Optional)_3000:ta%Commission (Optional)_4000:ta%Other (Optional)_5000//Source of Income;Business or Self-employment&&My business details;ta%Business name_BusONE:ta%Street address_1 Danforth Avenue:ta%Zip Code_56009:ta%Unit# (Optional)_ 23:rb%Percentage of ownership_ Less than 25%:ta%Business phone_ 4454455555&&Business details;CB%This is my current business_This is my current business:DatePicker%I have had an ownership interest in this business since_12/12/2007:ta%Monthly Adjusted Gross Income_23323//Source of Income;Others&&Select from other income;dd%Select_Automobile Allowance&&Monthly Amount;ta%Monthly Amount_3300";
		int currentInex=1;
		int notcurrent=1;
		LinkedHashMap<String, LinkedHashMap<String, String>> quesAnsMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
		LinkedHashMap<String, LinkedHashMap<String, String>> simplequesAnsMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
		LinkedHashMap<String, LinkedHashMap<String, String>> notcurrquesAnsMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
		LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>> finalquesAnsMap = new LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>>();
		
		for (String eachSet : qtnDetails.split("//")) {
			int intindex=Integer.parseInt(index)+1;
			index=String.valueOf(intindex);
			LinkedHashMap<String, String> quesAnsdetail = new LinkedHashMap<String, String>();
			for (String eachCoBorQuesAns : eachSet.split("&&")) {
				String eachQuestion = eachCoBorQuesAns.split(";")[0].trim();
				String eachAnswer = eachCoBorQuesAns.split(";")[1].trim();
				quesAnsdetail.put(eachQuestion, eachAnswer);
			}
			quesAnsMap.put(index, quesAnsdetail);
		}
		System.out.println("Map>>>>"+quesAnsMap);
		if(qtnDetails.contains("Source of Income")){
			System.out.println("Inside Income>>>>");
			for(int i=1;i<=quesAnsMap.size();i++){
			   System.out.println("Key>>>"+i+"Value"+quesAnsMap.get(String.valueOf(i)));
			   String value=quesAnsMap.get(String.valueOf(i)).toString();
			   System.out.println("Value>>>"+value);
			   if(value.contains("current")){
	//			currentInex=1;
				System.out.println("True Index>>>"+i);
				System.out.println("Map having current values"+quesAnsMap.get(String.valueOf(i)));
				simplequesAnsMap.put(String.valueOf(currentInex), quesAnsMap.get(String.valueOf(i)));
				currentInex=currentInex+1;
			}
			   else{
	//			   	notcurrent=1;
				   System.out.println("False Index>>>"+i);
					System.out.println("Not current values"+quesAnsMap.get(String.valueOf(i)));
					notcurrquesAnsMap.put(String.valueOf(notcurrent), quesAnsMap.get(String.valueOf(i)));
					notcurrent=notcurrent+1;
			   }
			}
			System.out.println("Simple Map>>>>"+simplequesAnsMap+"\n Not current>>>"+notcurrquesAnsMap);
			System.out.println("Size of Final Map>>>"+simplequesAnsMap.size()+"Not Currect size>>>"+notcurrquesAnsMap.size());
			int ss=simplequesAnsMap.size();
			if(simplequesAnsMap.size()!=quesAnsMap.size()){
				for(int i=1;i<=notcurrquesAnsMap.size();i++){
					simplequesAnsMap.put(String.valueOf(ss+i), notcurrquesAnsMap.get(String.valueOf(i)));
				}
			}
			System.out.println("Final Map>>>>>>"+simplequesAnsMap);
			quesAnsMap=simplequesAnsMap;
			}
		return quesAnsMap;
	}

	public void validateMultipleIncomeEncompass() throws Throwable{
		Ten0Three.name.put("1", "John");
		Ten0Three.name.put("2", "Mary");
		int size=Ten0Three.name.size();
		for(int i=1;i<=size;i++){
		System.out.println("Inside validateMultipleIncomeEncompass>>>>");
		buildEncompassMapping("Sheet1");
		LinkedHashMap<String, LinkedHashMap<String, String>> incomeDetailsMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
		String allIncomeData=step.getDataValue("Borrower_1003_Income_Business");
		incomeDetailsMap=buildQuestionMap(allIncomeData);
		if(i>1){
			incomeDetailsMap.clear();
			allIncomeData=step.getDataValue("CoBorrowerIncomeQuestionAnswer");
			incomeDetailsMap=buildQuestionMap(allIncomeData);
			System.out.println("Inside IncomeDetails for Coborrower>>"+incomeDetailsMap);
		}
		mbIncomeDetailsMap.put(Ten0Three.name.get(String.valueOf(i)), incomeDetailsMap);
		encompassIncome(Ten0Three.name.get(String.valueOf(i)),i,incomeDetailsMap,false);
		}
	}
	public static void encompassIncome(String borName,int index,HashMap<String, LinkedHashMap<String, String>> incomeMap,boolean isCoApp) throws Throwable{
		LinkedHashMap<String,LinkedHashMap<String, String>> allIncomeDetails=new LinkedHashMap<String,LinkedHashMap<String, String>>();
		int incrementalValue=0;
		String multincomeIndex="0";
		String otherIncome = "";
		String monthLy="0";
		 int value=Integer.valueOf(monthLy);
		boolean isOtherIncome = false;
		String payload="[\"",err="",payloadEndString="\"]";
		System.out.println("encompassValuesForQtnsMap:"+encompassValuesForQtnsMap);
		 for(String countOfIncome:incomeMap.keySet()){
			 LinkedHashMap<String, String> fieldIDQtnMap=new LinkedHashMap<String, String>();
			 HashMap<String, String> eachIncomeMap=incomeMap.get(countOfIncome);
			 LinkedHashMap<String, String> eachIncome=Util.build1003FieldValueLinkedMap(eachIncomeMap);
			 for(String eachQtn:eachIncome.keySet()){
				 System.out.println("eachQtn:"+eachQtn);
				 if(eachQtn.equalsIgnoreCase("Loss (check if applicable)")){
					 System.out.println("Loss in Business");
					 fieldIDQtnMap.put("Loss (check if applicable)","Loss (check if applicable)");
					 continue;
				 }
				 String fieldName="",eachFieldId1="";
				 boolean isSourceOfIncomeQtn = false;
				 if(eachQtn.equalsIgnoreCase("Source of Income")){
					 isSourceOfIncomeQtn = true;
					 fieldName=eachQtn;
					 if(eachIncome.get(eachQtn).equalsIgnoreCase("Others"))
						 otherIncomeCountForAPair = otherIncomeCountForAPair+1;
					 else if(!(eachIncome.get(eachQtn).contains("Military pay and entitlements"))){
						 isOtherIncome=false;
						 incomeCountForAPair=incomeCountForAPair+1;
					 }
					 else
						 isOtherIncome=false;
				 }
				 else
					 fieldName=eachIncome.get("Source of Income")+"_"+eachQtn;
				 String fieldId="";
				 System.out.println("eachIncome:"+eachIncome);
				 System.out.println("fieldName:"+fieldName);
				 System.out.println("");
				 if(eachQtn.equalsIgnoreCase("Source of Income")&&(eachIncome.get("Source of Income").equalsIgnoreCase("Others")||eachIncome.get("Source of Income").contains("Military")))
					 fieldId="NA";
				 else
					 fieldId=encompassMap.get(fieldName).get("Encompass Id");
				 System.out.println("fieldId:"+fieldId);
				 if(fieldId.equalsIgnoreCase("NA"))
					 continue;
				 incrementalValue=Integer.valueOf(encompassMap.get(fieldName).get("Right Increment"));

				 System.out.println("fieldId:"+fieldId);
				 System.out.println("fieldName:"+fieldName);



				 if(fieldName.startsWith("Others")||eachIncome.get("Source of Income").equalsIgnoreCase("Others")){
					 isOtherIncome = true;
					 if(!borPairOtherIncomeMap.containsKey(fieldName)){
						 System.out.println("FieldName>>>>"+fieldName);
						 borPairOtherIncomeMap.put(fieldName, fieldId); 
						
						 String incomeIndex ="";
						 try{
							 incomeIndex = String.valueOf(Integer.valueOf(borPairOtherIncomeMap.get(fieldName+"_Index"))+1);
						 }catch(Exception e){
							 if(fieldName.equalsIgnoreCase("Others_My monthly benefits are")||fieldName.equalsIgnoreCase("Others_Monthly earnings")||fieldName.equalsIgnoreCase("Others_Monthly Amount")){
											 if(incomeIndex.equals("")){
												 incomeIndex="0";
												 incomeIndex=incomeIndex+multincomeIndex;
											 }
							
											 System.out.println("Came Inside>>>"+incomeIndex);
											 multincomeIndex=String.valueOf(Integer.valueOf(incomeIndex)+1);
											 System.out.println("Came Inside multi>>>"+multincomeIndex);
											 incomeIndex=multincomeIndex;
										 }
							 else
							 incomeIndex = "1";                              
						 }
						
						 //if condition[Separteindex]
						 //incomeIndex=multincomeIndex
						 borPairOtherIncomeMap.put(fieldName+"_Index",incomeIndex);

					 }else
					 {
						 int fieldIndex = Integer.valueOf(borPairOtherIncomeMap.get(fieldName+"_Index"))+1;
						
						 if(fieldName.equalsIgnoreCase("Others_My monthly benefits are")||fieldName.equalsIgnoreCase("Others_Monthly earnings")||fieldName.equalsIgnoreCase("Others_Monthly Amount")){
							 value=value+1;
							 fieldIndex=Integer.valueOf(multincomeIndex)+value;
							 System.out.println("Came Inside else>>>"+fieldIndex+"Value>>"+value);
						 }
						 //if(){
						// fieldIndex=multincomeIndex+1;
					// }
						 borPairOtherIncomeMap.put(fieldName+"_Index", String.valueOf(fieldIndex));

					 }
					 System.out.println("fieldId 1:"+fieldId);
				 }
				 System.out.print("Borrower pair income map>>>"+borPairOtherIncomeMap);
				 if(fieldId!=null||fieldId.length()>0){
					 String refField = "";
					 for(String eachFieldId:fieldId.split("\",\"")){
						 /*if(eachFieldId.startsWith("bE") && isCoApp){
                            eachFieldId=eachFieldId.replace("bE", "cE");
                        }   */                  
						 System.out.println("eachFieldId:"+eachFieldId);
						 if(isOtherIncome){
							 //eachFieldId=calculateEncompassIDWithIncrement(eachFieldId,String.valueOf(otherIncomeCountForAPair),incrementalValue);
							 System.out.println("val1:"+borPairOtherIncomeMap.get(fieldName+"_Index"));
							 System.out.println("val2:"+fieldName);
							 eachFieldId1=calculateEncompassIDWithIncrement(eachFieldId,borPairOtherIncomeMap.get(fieldName+"_Index"),incrementalValue);
						
							 otherIncome=otherIncome+"\",\""+eachFieldId1;
							 refField = refField+"\",\""+eachFieldId1;
						 }else{
							 System.out.println("countOfIncome here:"+countOfIncome);
							 System.out.println("otherIncomeCountForAPair:"+otherIncomeCountForAPair);
							 //  String countOfIncome1 = String.valueOf(Integer.valueOf(countOfIncome)-otherIncomeCountForAPair);
							 if(isSourceOfIncomeQtn){
								 /*  try{
                                    System.out.println("val:"+borPairOtherIncomeMap.get("Source of Income_Index"));
                                    countOfIncome1 = String.valueOf(Integer.valueOf(borPairOtherIncomeMap.get("Source of Income_Index"))+1);
                                }catch(Exception e){
                                    countOfIncome1="1";
                                }*/
								 //borPairOtherIncomeMap.put("Source of Income_Index",countOfIncome1);
								 borPairOtherIncomeMap.put("Source of Income_Index",String.valueOf(incomeCountForAPair));

							 }
							 eachFieldId1=calculateEncompassIDWithIncrement(eachFieldId,String.valueOf(incomeCountForAPair),incrementalValue);
							 //eachFieldId1=calculateEncompassIDWithIncrement(eachFieldId,countOfIncome1,incrementalValue);
						 }

						 if(index>1)
						 {
//							 index=1;
							 System.out.println("Inside Coborrower>>>>>");
							 eachFieldId1=eachFieldId1;
						 }

						 payload=payload+eachFieldId1+"\",\"";
						 fieldIDQtnMap.put(eachFieldId1,fieldName);
					 }
					 if(isOtherIncome){
						 System.out.println("otherIncome bef:"+refField);
						 if(refField.startsWith("\",\""))
							 refField=refField.substring(3, refField.length());
						 System.out.println("otherIncome aft:"+refField);
						 borPairOtherIncomeMap.put(fieldName, refField);
					 }
				 }
				 else
				 {
					 err=err+eachQtn+",";
				 }
				 //fieldIDQtnMap.put(eachFieldId1,fieldName);
			 }
			 System.out.println("payload:"+payload);
			 allIncomeDetails.put(countOfIncome, fieldIDQtnMap);
		 }
		 incomeFinalEncMap.put(borName,allIncomeDetails);
		 System.out.println("incomeFinalEncMap:"+incomeFinalEncMap);
		 payload=payload.substring(0, payload.length()-3)+payloadEndString;
		 System.out.println("payload final:"+payload);
		 if(err.length()>0){
			 System.out.println("err:"+err);
			 addExceptionToReport("Error in building payload for following fields:"+err, "", "");
		 }
		 compareEncompassDataIncome(payload,borName);
	}


	public static String calculateEncompassIDWithIncrement(String eachFieldId,String countOfIncome,int incrementalValue){
		int eachFieldIdInt = 0;
		System.out.println("incrementalValue:"+incrementalValue);
		System.out.println("countOfIncome:"+countOfIncome);
		System.out.println("eachFieldId:"+eachFieldId);
		eachFieldIdInt=Integer.valueOf(eachFieldId.substring(eachFieldId.length()-3, eachFieldId.length()));
		String newIncVal=String.valueOf(eachFieldIdInt+(incrementalValue*(Integer.valueOf(countOfIncome)-1)));
		System.out.println("newIncVal:"+newIncVal);
		eachFieldId=eachFieldId.substring(0,eachFieldId.length()-newIncVal.length())+newIncVal;
		return eachFieldId;
	}



	public static Loan callEncompassApi(String payload) throws Exception{
		System.out.println("Inside callEncompassApi");
		String EncomopassLoanNumber=util.getLoanFromSubmitScreen();
//		String EncomopassLoanNumber="7202740477";//CO1-7202598463,CO4-7202585507 CO3-7202588438,CO2-7202586300,CO5-7202586392,CO6-7202597433,CO7-7202587083,CO8-7202587026
		System.out.println("Inside call API method loan Number>>"+EncomopassLoanNumber);
		WebService web = new WebService();
		ResponseEntity<?> responseEntity = env.fetchEncompassData(EncomopassLoanNumber, payload);


		System.out.println("responseEntity:"+responseEntity.getBody().toString());
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Loan mp = objectMapper.readValue(responseEntity.getBody().toString(), Loan.class);
		return mp;
	}





	public static void compareEncompassDataIncome(String payload,String eachBor) throws Throwable{
		System.out.println("Inside compareEncompassDataIncome");
		String err="";
		Loan mp=callEncompassApi(payload);
		//  for(String eachBor:incomeFinalEncMap.keySet()){
		for(String eachIncome:incomeFinalEncMap.get(eachBor).keySet()){
			LinkedHashMap<String, String> expEachIncomeDetails=Util.build1003FieldValueLinkedMap(mbIncomeDetailsMap.get(eachBor).get(eachIncome));
			System.out.println("expEachIncomeDetails:"+expEachIncomeDetails);
			for(String eachFieldId:incomeFinalEncMap.get(eachBor).get(eachIncome).keySet()){
				System.out.println("eachFieldId:"+eachFieldId);
				if(eachFieldId.equalsIgnoreCase("Loss (check if applicable)"))
					continue;
				String expValue="";
				//                System.out.println("map:"+incomeFinalEncMap.get(eachBor).get(eachIncome));
				String fieldName=incomeFinalEncMap.get(eachBor).get(eachIncome).get(eachFieldId).trim();
				if(fieldName.contains("_"))
					fieldName=fieldName.split("_")[1];
				System.out.println("fieldName:"+fieldName);
				System.out.println("inTyp:"+expEachIncomeDetails.get("Source of Income"));
				if(fieldName.contains("Source of Income") && !(expEachIncomeDetails.get("Source of Income").equalsIgnoreCase("Business or Self-employment")))
					continue;
				System.out.println("here");
				System.out.println("res:"+mp.getAddress().get(eachFieldId.trim()));
				String actValue=mp.getAddress().get(eachFieldId).trim().split("\\.")[0].replace(" 12:00:00 AM", "");
				System.out.println("actValue:"+actValue);
				expValue=returnFieldValue(actValue,eachBor,fieldName,expEachIncomeDetails);
				if(expValue.length()==0 || expValue==null)
					expValue=expEachIncomeDetails.get(fieldName);
				expValue=expValue.trim().split("\\.")[0];
				if(expValue.contains("/")){
					System.out.println("DateFormat:>>"+expValue);
					SimpleDateFormat df = new SimpleDateFormat("M/d/yyyy");
                    Date date = new SimpleDateFormat("MM/dd/yyyy").parse(expValue);
                   String  fieldValue = df.format(date);
                   String  modifiedValue = fieldValue;
                   expValue=modifiedValue;
                   System.out.println("DateFormat Modified:>>"+expValue);
				}
				System.out.println("expValue:"+expValue);
				System.out.println("incomeFinalEncMap here:"+incomeFinalEncMap.get(eachBor).get(eachIncome));
				if(incomeFinalEncMap.get(eachBor).get(eachIncome).containsKey("Loss (check if applicable)") && fieldName.equalsIgnoreCase("Monthly Adjusted Gross Income"))
				{
					System.out.println("minus val");
					expValue="-"+expValue;
				}
				if(!actValue.equalsIgnoreCase(expValue))
					err=err+"FieldId:"+eachFieldId+"Actual:"+actValue+"Expected:"+expValue+",";
			}
		}
		if(err.length()>0){
			//System.out.println("Mismatch in encompass data for the following fields: UserName >>"+eachBor+"\n"+err);
			addExceptionToReport("Mismatch in encompass data for the following fields: UserName >>"+eachBor+"\n"+err, "", "");
		}
	}

	public static void buildEncompassMapping(String sheetname) throws Throwable {
		System.out.println("Inside buildEncompassMapping");
		String encompassMappingDataFileName = System.getProperty("user.dir") + "\\src\\test\\resources\\EncompassMapSheet_URLA.xls";
		encompassMap = Util.buildExcelFileMap(encompassMappingDataFileName, sheetname, "Applicantion Field Name");
		System.out.println("done for one");
		//ansSpecificFieldIdMap=Util.buildExcelFileMap(encompassMappingDataFileName, "AnswerSpecificFieldId", "QuestionAnswerName");
		encompassValuesForQtnsMap=Util.buildExcelFileMap(encompassMappingDataFileName, "EncompassValuesForQtns","FieldValue");
	System.out.println("done");
	}

	@Override
	public void checkPage() {
		// TODO Auto-generated method stub

	}

}

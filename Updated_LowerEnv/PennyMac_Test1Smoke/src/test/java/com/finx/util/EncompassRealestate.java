package com.finx.util;

import java.util.HashMap;
import java.util.LinkedHashMap;

import com.tavant.kwutils.CustomStep;

public class EncompassRealestate extends CustomStep{

	public static LinkedHashMap<String, LinkedHashMap<String,LinkedHashMap<String, String>>> reFinalEncMap = new LinkedHashMap<String, LinkedHashMap<String,LinkedHashMap<String, String>>>();
	static int reCountForAPair=0;
	public static LinkedHashMap<String,LinkedHashMap<String, LinkedHashMap<String, String>>> mbREDetailsMap = new LinkedHashMap<String,LinkedHashMap<String, LinkedHashMap<String, String>>>();
	
	public void validateMultipleREEncompass() throws Throwable{
//		Ten0Three.fname="John";
//		Ten0Three.name.put("1", "John");
//		Ten0Three.name.put("2", "Mary");
		int size=Ten0Three.name.size();
		for(int i=1;i<=size;i++){
		System.out.println("Inside validateMultipleREEncompass>>>>");
		MultipleValidationEncompass.buildEncompassMapping("RealEstate");
		System.out.println("done building mapping");
		LinkedHashMap<String, LinkedHashMap<String, String>> reDetailsMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
		String allREData=step.getDataValue("RealEstateQuestionAnswer");
		if(i>1)
		  allREData=step.getDataValue("RealEstateQuestionAnswerCoapplicant");
		reDetailsMap=MultipleValidationEncompass.buildQuestionMap(allREData);
		mbREDetailsMap.put(Ten0Three.name.get(String.valueOf(i)), reDetailsMap);
		System.out.println("RealEstate Details Map>>>>"+reDetailsMap);
		encompassRealEstate(Ten0Three.name.get(String.valueOf(i)),i,reDetailsMap,false);
		}
	}
	
	public static void encompassRealEstate(String borName,int index,HashMap<String, LinkedHashMap<String, String>> reMap,boolean isCoApp) throws Throwable{
		LinkedHashMap<String,LinkedHashMap<String, String>> allREDetails=new LinkedHashMap<String,LinkedHashMap<String, String>>();
		int incrementalValue=0;
		reFinalEncMap.clear();
		String payload="[\"",err="",payloadEndString="\"]";
		for(String countOfRE:reMap.keySet()){
			LinkedHashMap<String, String> fieldIDQtnMap=new LinkedHashMap<String, String>();
			
			HashMap<String, String> eachREMap=reMap.get(countOfRE);
			System.out.println("countOfRE:"+countOfRE);
			System.out.println("eachREMap:"+reMap.get(countOfRE));
			LinkedHashMap<String, String> eachRE=Util.build1003FieldValueLinkedMap(eachREMap);
			reCountForAPair=reCountForAPair+1;
			for(String eachQtn:eachRE.keySet()){
				System.out.println("eachQtn:"+eachQtn);
				String fieldName="",eachFieldId1="";
//				System.out.println("MAPPPP>>>"+MultipleValidationEncompass.encompassMap);
				fieldName=eachQtn;
				System.out.println("fieldName:"+fieldName);
				if(fieldName.equalsIgnoreCase("Choose one from the below") && (eachRE.get(fieldName).equalsIgnoreCase("Add a different property")))
					continue;	
				String fieldId=MultipleValidationEncompass.encompassMap.get(fieldName).get("Encompass Id");
				if(fieldId.equalsIgnoreCase("NA"))
					continue;
				incrementalValue=Integer.valueOf(MultipleValidationEncompass.encompassMap.get(fieldName).get("Right Increment"));

				System.out.println("fieldId:"+fieldId);
				System.out.println("fieldName:"+fieldName);

				if(fieldId!=null||fieldId.length()>0){
					String refField = "";
					for(String eachFieldId:fieldId.split("\",\"")){
						System.out.println("eachFieldId:"+eachFieldId);
						eachFieldId1=MultipleValidationEncompass.calculateEncompassIDWithIncrement(eachFieldId,String.valueOf(reCountForAPair),incrementalValue);
						refField = refField+"\",\""+eachFieldId1;
						if(index>1)
						{
							eachFieldId1=eachFieldId1;
						}
						if(!(payload.contains(eachFieldId1))){
							payload=payload+eachFieldId1+"\",\"";
							fieldIDQtnMap.put(eachFieldId1,fieldName);
						}
					}
				}
				else
				{
					err=err+eachQtn+",";
				}
			}
			System.out.println("payload:"+payload);
			allREDetails.put(countOfRE, fieldIDQtnMap);
		}
		reFinalEncMap.put(borName,allREDetails);
		System.out.println("My property type is:"+reFinalEncMap);
		payload=payload.substring(0, payload.length()-3)+payloadEndString;
		System.out.println("payload RE final:"+payload);
		if(err.length()>0){
			System.out.println("err:"+err);
			addExceptionToReport("Error in building payload for Other Properties/Real Estate for following fields:"+err, "", "");
		}
		compareEncompassDataRE(payload);
	}


	public static void compareEncompassDataRE(String payload) throws Throwable{
		System.out.println("Inside compareEncompassDataRE");
		String err="";
		Loan mp=MultipleValidationEncompass.callEncompassApi(payload);
		for(String eachBor:reFinalEncMap.keySet()){
			for(String eachRE:reFinalEncMap.get(eachBor).keySet()){
				LinkedHashMap<String, String> expEachREDetails=Util.build1003FieldValueLinkedMap(mbREDetailsMap.get(eachBor).get(eachRE));
				for(String eachFieldId:reFinalEncMap.get(eachBor).get(eachRE).keySet()){
					String expValue="";
					System.out.println("eachFieldId:"+eachFieldId);
					String fieldName=reFinalEncMap.get(eachBor).get(eachRE).get(eachFieldId);
					if(fieldName.contains("_"))
						fieldName=fieldName.split("_")[1];
					System.out.println("fieldName:"+fieldName);
					if(fieldName.equalsIgnoreCase("My property is a") && (expEachREDetails.get(fieldName).equalsIgnoreCase("Others")))
						continue;	
					String actValue=mp.getAddress().get(eachFieldId).split("\\.")[0];
					System.out.println("actValue:"+actValue);
					System.out.println("expEachREDetails:"+expEachREDetails);
					expValue=returnFieldValue(actValue.trim(),eachBor.trim(),fieldName.trim(),expEachREDetails);
					if(!(expValue.length()>0) || expValue==null){
						System.out.println("here to get value");
						expValue=expEachREDetails.get(fieldName);
					}
					System.out.println("expValue:"+expValue);
					if(!actValue.equalsIgnoreCase(expValue)){
						System.out.println("Error !! FieldId:"+eachFieldId+"Actual:"+actValue+"Expected:"+expValue);
						err=err+"FieldId:"+eachFieldId+"Actual:"+actValue+"Expected:"+expValue+",";
					}
				}
			}
			if(err.length()>0){
			//	System.out.println("Mismatch in encompass data for the following fields: UserName >>"+eachBor+"\n"+err);
			addExceptionToReport("Mismatch in encompass data for the following fields for UserName >>"+eachBor+"\n"+err, "", "");
			}

		}

	}

	public static String returnFieldValue(String actValue,String borName,String fieldName,LinkedHashMap<String, String> expDetailsMap)throws Throwable{
		System.out.println("Inside returnFieldValue!!!!");
//		Ten0Three.fname="John";
		String reqFieldName="",expNewVal="";
		switch(fieldName){
		case "My property is a":
		case "Choose one from the below":
//			if(actValue.trim().equalsIgnoreCase("Borrower")||actValue.equalsIgnoreCase("CoBorrower")||actValue.equalsIgnoreCase("Both")){
//			//	if(actValue.equalsIgnoreCase("Borrower")||actValue.equalsIgnoreCase("CoBorrower")){
//					if(Ten0Three.fname.equalsIgnoreCase(borName))		
//					expNewVal="Borrower";
//					else if(actValue.equalsIgnoreCase("Both")){
//						if(expDetailsMap.get("I own jointly with").equalsIgnoreCase("Joint with Applicant"))
//							expNewVal="Both";
//					}
//					else
//					expNewVal="CoBorrower";
//			//	}
//				
//			}
//			else{
//				if(fieldName.equalsIgnoreCase("My property is a") && (expDetailsMap.get(fieldName).equalsIgnoreCase("Others"))){
//
//				}
//				else {
//					reqFieldName=expDetailsMap.get(fieldName);
//					expNewVal=MultipleValidationEncompass.encompassValuesForQtnsMap.get(reqFieldName).get("EncompassValue");
//				}
//			}
			if(actValue.equalsIgnoreCase("Borrower")||actValue.equalsIgnoreCase("CoBorrower")){
				if(Ten0Three.fname.trim().equalsIgnoreCase(borName.trim()))		
				expNewVal="Borrower";
				else
				expNewVal="CoBorrower";
			}
			else if(actValue.equalsIgnoreCase("Both")){
				if(expDetailsMap.get("I own jointly with").equalsIgnoreCase("Joint with Applicant"))
					expNewVal="Both";
			}
			else{
				reqFieldName=expDetailsMap.get(fieldName);
				System.out.println("reqFieldName:"+reqFieldName);
				expNewVal=MultipleValidationEncompass.encompassValuesForQtnsMap.get(reqFieldName).get("EncompassValue");
			}
			System.out.println("Ftom 1st switch and expected Value Real"+expNewVal+"Actual"+actValue+"Fname"+Ten0Three.fname+"BorName>>"+borName);
			break;
		case "My other property is a":
		case "Property status":
			reqFieldName=expDetailsMap.get(fieldName);
			expNewVal=MultipleValidationEncompass.encompassValuesForQtnsMap.get(reqFieldName).get("EncompassValue");
			break;
		case "I own jointly with":
			expNewVal=MultipleValidationEncompass.encompassValuesForQtnsMap.get(reqFieldName).get("EncompassValue");
			if(expDetailsMap.get("I own jointly with").equalsIgnoreCase("Joint with Applicant"))
				expNewVal="Both";
			else{
				if(Ten0Three.fname.equalsIgnoreCase(borName))
					expNewVal="Borrower";
				else
					expNewVal="CoBorrower";
			}
			break;
		}
		
		if(expNewVal.equalsIgnoreCase("NA"))
			expNewVal="";
		System.out.println("expNewVal:"+expNewVal+"fieldName:"+fieldName);
		return expNewVal;
	}
	@Override
	public void checkPage() {
		// TODO Auto-generated method stub

	}
}

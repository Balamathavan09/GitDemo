package com.finx.util;

import java.util.HashMap;
import java.util.LinkedHashMap;

import com.tavant.kwutils.CustomStep;

public class MultipleAssetEncompassValidation extends CustomStep{

	public static LinkedHashMap<String, LinkedHashMap<String,LinkedHashMap<String, String>>> assetFinalEncMap = new LinkedHashMap<String, LinkedHashMap<String,LinkedHashMap<String, String>>>();
	static Util util=new Util();
	static int otherAssetCountForAPair = 0,assetCountForAPair=0;
	public static LinkedHashMap<String,LinkedHashMap<String, LinkedHashMap<String, String>>> mbAssetDetailsMap = new LinkedHashMap<String,LinkedHashMap<String, LinkedHashMap<String, String>>>();
	
	public void validateMultipleAssetEncompass() throws Throwable{
//		Ten0Three.fname="John";
//		Ten0Three.name.put("1", "John");
//		Ten0Three.name.put("2", "Mary");
		int size=Ten0Three.name.size();
		for(int i=1;i<=size;i++){
		System.out.println("Inside validateMultipleAssetEncompass>>>>");
		MultipleValidationEncompass.buildEncompassMapping("Asset");
		System.out.println("done building mapping");
		LinkedHashMap<String, LinkedHashMap<String, String>> assetDetailsMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
		String allAssetData=step.getDataValue("1003_Asset_Manual");
		assetDetailsMap=MultipleValidationEncompass.buildQuestionMap(allAssetData);
		mbAssetDetailsMap.put(Ten0Three.name.get(String.valueOf(i)), assetDetailsMap);
		System.out.println("Asset Details Map>>>>"+assetDetailsMap);
		encompassAsset(Ten0Three.name.get(String.valueOf(i)),i,assetDetailsMap,false);
		}
	}
	
	public static void encompassAsset(String borName,int index,HashMap<String, LinkedHashMap<String, String>> assetMap,boolean isCoApp) throws Throwable{
		System.out.println("Inside encompassAsset");
		LinkedHashMap<String,LinkedHashMap<String, String>> allAssetDetails=new LinkedHashMap<String,LinkedHashMap<String, String>>();
		int incrementalValue=0;
		assetFinalEncMap.clear();
		boolean isOtherAsset = false;
		String payload="[\"",err="",payloadEndString="\"]";
		
		for(String countOfAsset:assetMap.keySet()){
			LinkedHashMap<String, String> fieldIDQtnMap=new LinkedHashMap<String, String>();
			HashMap<String, String> eachAssetMap=assetMap.get(countOfAsset);
			LinkedHashMap<String, String> eachAsset=Util.build1003FieldValueLinkedMap(eachAssetMap);
			System.out.println("eachAsset:"+eachAsset);
			if(eachAsset.get("Select").equalsIgnoreCase("Others")){
				System.out.println("Indside select for Others it came>>>");
				otherAssetCountForAPair = otherAssetCountForAPair+1;
				isOtherAsset=true;
			}
			else{
				isOtherAsset=false;
				assetCountForAPair=assetCountForAPair+1;
			}

			for(String eachQtn:eachAsset.keySet()){
				System.out.println("eachQtn:"+eachQtn);
				String fieldName="",eachFieldId1="";
				if(isOtherAsset && eachQtn.equalsIgnoreCase("Select"))
					continue;
				if(isOtherAsset && !(eachQtn.equalsIgnoreCase("Select"))){
					System.out.println("Inside here");
					fieldName=eachAsset.get("Select")+"_"+eachQtn;
				}
				else
					fieldName=eachQtn;

				System.out.println("fieldName:"+fieldName+"\n Field ID:>"+MultipleValidationEncompass.encompassMap.get(fieldName).get("Encompass Id"));
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
						/*if(eachQtn.equalsIgnoreCase("Select")){
                        eachFieldId1=calculateEncompassIDWithIncrement(eachFieldId,String.valueOf(otherAssetCountForAPair+assetCountForAPair),incrementalValue);
                            refField = refField+"\",\""+eachFieldId1;
                        }
                        else */if(isOtherAsset){
                        	eachFieldId1=MultipleValidationEncompass.calculateEncompassIDWithIncrement(eachFieldId,String.valueOf(otherAssetCountForAPair),incrementalValue);
                        	refField = refField+"\",\""+eachFieldId1;
                        }else{
                        	eachFieldId1=MultipleValidationEncompass.calculateEncompassIDWithIncrement(eachFieldId,String.valueOf(assetCountForAPair),incrementalValue);
                        	refField = refField+"\",\""+eachFieldId1;
                        }

                        if(index>1)
                        {
                        	eachFieldId1=eachFieldId1;
                        }

                        payload=payload+eachFieldId1+"\",\"";
                        fieldIDQtnMap.put(eachFieldId1,fieldName);
					}
				}
				else
				{
					err=err+eachQtn+",";
				}
			}
			System.out.println("payload:"+payload);
			allAssetDetails.put(countOfAsset, fieldIDQtnMap);
		}
		assetFinalEncMap.put(borName,allAssetDetails);
		System.out.println("assetFinalEncMap:"+assetFinalEncMap);
		payload=payload.substring(0, payload.length()-3)+payloadEndString;
		System.out.println("payload Asset final:"+payload);
		if(err.length()>0){
			System.out.println("err:"+err);
			addExceptionToReport("Error in building payload for Asset for following fields:"+err, "", "");
		}
		compareEncompassDataAsset(payload);
	}

	public static void compareEncompassDataAsset(String payload) throws Throwable{
		System.out.println("Inside compareEncompassDataAsset");
		Loan mp=MultipleValidationEncompass.callEncompassApi(payload);
		String err="";
		for(String eachBor:assetFinalEncMap.keySet()){
			for(String eachAsset:assetFinalEncMap.get(eachBor).keySet()){
				LinkedHashMap<String, String> expEachAssetDetails=Util.build1003FieldValueLinkedMap(mbAssetDetailsMap.get(eachBor).get(eachAsset));
				System.out.println("expEachIncomeDetails:"+expEachAssetDetails);
				for(String eachFieldId:assetFinalEncMap.get(eachBor).get(eachAsset).keySet()){
					String expValue="";
					System.out.println("eachFieldId:"+eachFieldId);
					String fieldName=assetFinalEncMap.get(eachBor).get(eachAsset).get(eachFieldId);
					System.out.println("fieldName before:"+fieldName+">>");
					
					if(fieldName.contains("fs_fs")){
						System.out.println("inside split");
						String prefixVal=fieldName.split("fs_fs")[0].split("_")[1].trim();
						fieldName=fieldName.split("fs_fs")[1].trim();
						System.out.println("prefixVal:"+prefixVal);
						fieldName=prefixVal+"fs_fs"+fieldName;
					}
					else if(fieldName.contains("_"))
						fieldName=fieldName.split("_")[1].trim();
					String actValue=mp.getAddress().get(eachFieldId).split("\\.")[0];

					System.out.println("actValue:"+actValue);
					System.out.println("fieldName:"+fieldName+">>");
					System.out.println("expEachAssetDetails:"+expEachAssetDetails);
					expValue=returnFieldValue(actValue,eachBor,fieldName,expEachAssetDetails);
					if(!(expValue.length()>0) || expValue==null){
						System.out.println("here to get value");
						expValue=expEachAssetDetails.get(fieldName);
						if(expValue.contains("/")){
							expValue=expValue.replace("/", "");
						}
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
		System.out.println("Inside returnFieldValue"+borName+"Actaul Value>>>"+actValue);
//		Ten0Three.fname="John";
		String reqFieldName="",expNewVal="";
		System.out.println("fieldName:"+fieldName);
		System.out.println("expDetailsMap:"+expDetailsMap);
		fieldName=fieldName.trim();
		switch(fieldName){
		case "Select":
		case "Selectfs_fsSelect from other Assets":
			if(actValue.equalsIgnoreCase("Borrower")||actValue.equalsIgnoreCase("CoBorrower")){
				if(Ten0Three.fname.equalsIgnoreCase(borName))		
				expNewVal="Borrower";
				else
				expNewVal="CoBorrower";
			}
			else if(actValue.equalsIgnoreCase("Both")){
				if(expDetailsMap.get("Held jointly with(Optional)").equalsIgnoreCase("Co-Borrower/Borrower"))
					expNewVal="Both";
			}
			else{
				System.out.println("Inside asset expected>>>"+expDetailsMap+"FieldName>>"+fieldName);
				reqFieldName=expDetailsMap.get(fieldName);
				System.out.println("reqFieldName:"+reqFieldName);
				expNewVal=MultipleValidationEncompass.encompassValuesForQtnsMap.get(reqFieldName).get("EncompassValue");
			}
			break;
		case "Held jointly with(Optional)":
			reqFieldName=fieldName;
			expNewVal=MultipleValidationEncompass.encompassValuesForQtnsMap.get(reqFieldName).get("EncompassValue");
			if(expDetailsMap.get("Held jointly with(Optional)").equalsIgnoreCase("Co-Borrower/Borrower"))
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

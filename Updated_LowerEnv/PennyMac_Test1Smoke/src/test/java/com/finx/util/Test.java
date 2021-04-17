package com.finx.util;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TimeZone;

public class Test {
	
	
	public static void testNull(){
		String setting = null;
		
		if(setting!=null){
			System.out.println("Non Zero");
		}else{
			System.out.println("Zero");
		}
	}
	
	
	
	
	public String getCurrentDate(){
		Format f = new SimpleDateFormat("MM/dd/yyyy");
		 Date today = new Date();
		((DateFormat) f).setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        String PST = f.format(today);
        System.out.println("USA Date = "+PST);
		return PST.trim();
	}
	
	
	public static void main(String[] args){
		/*String errorMessage=""+""+""+""+"\n";
		System.out.println(errorMessage.length());*/
		Test obj=new Test();
		obj.getCurrentDate();
	}

}

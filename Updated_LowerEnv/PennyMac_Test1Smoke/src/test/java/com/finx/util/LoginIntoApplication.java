package com.finx.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tavant.base.DriverFactory;
import com.tavant.kwutils.CustomStep;
import com.tavant.kwutils.KWVariables;
import com.tavant.utils.TwfException;

import jxl.read.biff.BiffException;

public class LoginIntoApplication extends CustomStep {
	Util util= new Util();
	 static String envType;
	Ten0Three ten=new Ten0Three();
	static InternalConditions inter=new InternalConditions();
	static WebService web=new WebService();
	public void urlIdentification() throws TwfException, BiffException, InvalidFormatException, IOException{
		WebDriver driver = DriverFactory.getDriver();
		String envUrl=driver.getCurrentUrl();
		envType = KWVariables.getVariables().get("Env");
		System.out.println("CurrentURL>>>"+envUrl);
		
	}
	public void loginIntoApplication(String args) throws Exception {
		System.out.println("Entered login");
		WebDriver driver = DriverFactory.getDriver();// Thread.sleep(10000);
            String data = KWVariables.getVariables().get(args);
            envType = KWVariables.getVariables().get("Env");
           // LandingPage createLoan = new LandingPage();
            // Thread.sleep(20000);
            String[] loginCredentials = data.split(":");
            String strUserName = loginCredentials[0];
            System.out.println("strUserName"+strUserName);
            String strPassword = loginCredentials[1];
            System.out.println("strPassword"+strPassword);

            System.out.println("Credentials" + strUserName+",,,"+strPassword);

            String coseText = "";
            switch(envType.toLowerCase()){
            case "qa":
            case "virgo":
            case "libra":
            case "scorpio":
            case "capricorn":
            case "aquarius":
            case "aries":
            case "leo":
            case "gemini":
            case "cancer":	
            case "taurus":
            {
            	   getElementByUsing("FinExp_Login_Username_IDS").clear();
            	   getElementByUsing("FinExp_Login_Username_IDS").sendKeys(strUserName);
                   getElementByUsing("FinExp_Login_Username_IDS").sendKeys(Keys.TAB);
                   getElementByUsing("FinExp_Login_Password_IDS").sendKeys(strPassword);
                   getElementByUsing("FinExp_LoginButtonIDS").click();
                   driver.manage().window().maximize();
                   System.out.println("under QA");
 //                  Thread.sleep(5000);
 //                  System.out.println("Waited for 5 sec");
                  String placeholder = driver.findElement(By.xpath(".//*[@id='tfaEmail']")).getAttribute("placeholder");
                  String placeholderText[] = placeholder.split(" ");
                  placeholder = placeholderText[2];
    
                  String otpValue[]=driver.findElement(By.xpath(".//*[@id='temp-mfa-token']")).getAttribute("value").split("-");
                  coseText=otpValue[1];
                  getElementByUsing("FinExp_LoginTokentext").sendKeys(coseText);
                  getElementByUsing("FinExp_LoginTokentext").sendKeys(Keys.TAB);
                  getElementByUsing("FinExp_LoginTokentext").sendKeys(Keys.TAB);
                  Thread.sleep(5000);
                  getElementByUsing("verifyButton").click(); 
                  Thread.sleep(10000);
            }
            break;
            case "uat":
            case "prod":
            case "pisces":{
               getElementByUsing("FinExp_Login_Username_IDS").clear();
         	   getElementByUsing("FinExp_Login_Username_IDS").sendKeys(strUserName);
               getElementByUsing("FinExp_Login_Username_IDS").sendKeys(Keys.TAB);
               getElementByUsing("FinExp_Login_Password_IDS").sendKeys(strPassword);
               getElementByUsing("FinExp_LoginButtonIDS").click();
               driver.manage().window().maximize();
              System.out.println("under UAT");
              String placeholder = driver.findElement(By.xpath(".//*[@id='tfaEmail']")).getAttribute("placeholder");
              String placeholderText[] = placeholder.split(" ");
              placeholder = placeholderText[2];
              Thread.sleep(5000);
              coseText = util.mailinator(placeholder);
              getElementByUsing("FinExp_LoginTokentext").sendKeys(coseText);
              getElementByUsing("FinExp_LoginTokentext").sendKeys(Keys.ENTER);
              getElementByUsing("FinExp_LoginTokentext").sendKeys(Keys.TAB);
              Thread.sleep(2000);
              getElementByUsing("verifyButton").click(); 
              Thread.sleep(2000);
        }
            break;
            }
            Util.waitTimeForSpinner(driver);
 //           util.click(getElementByUsing("FinExp_NewApplication"));;
      }


	public static void loginToYopMail(String loginUserName) throws Throwable{
		WebDriver driver=DriverFactory.getDriver();
        System.out.println("Inside loginToYopMail"+loginUserName);
//        ="loanonboard";
       ((JavascriptExecutor)driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get("http://www.yopmail.com/en/");
        System.out.println("loginUserName:"+loginUserName);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].value = '';", driver.findElement(By.xpath("//input[@id='login']")));
        System.out.println("Cleared>>");
   //     js.executeScript("arguments[0].value = '" "';", );
        driver.findElement(By.xpath("//input[@id='login']")).sendKeys(loginUserName);
  //      Util.enterValueInTextField(driver, "//input[@id='login']", "");
 //       Util.enterValueInTextField(driver, "//input[@id='login']", loginUserName);
        driver.findElement(By.xpath("//input[@value='Check Inbox']")).click();
        Thread.sleep(5000);
        selectMailInYopMail(driver,tabs);
  }

	public static void selectMailInYopMail(WebDriver driver,ArrayList<String> tabs) throws Throwable{
        System.out.println("Inside selectMailInYopMail");
        String yopMailHeaderXpath="//span[text()='%subject']";
        String yopMailBodyXpath="//div[@id='mailmillieu']";
        String header="pennymacfinxperience@tavant.com";
        yopMailHeaderXpath=yopMailHeaderXpath.replace("%subject", header);
        System.out.println("yopMailHeaderXpath:"+yopMailHeaderXpath);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.switchTo().defaultContent();
        driver.switchTo().frame("ifinbox");
  System.out.println("size:"+driver.findElements(By.xpath(yopMailHeaderXpath)).size());
//  try{
  if(driver.findElements(By.xpath(yopMailHeaderXpath)).size()==1){
              System.out.println("Inside one");
              driver.switchTo().defaultContent();
              Thread.sleep(2000);
              driver.switchTo().frame("ifmail");
              driver.findElement(By.xpath("//a[contains(.,'Show pictures')]")).click();
         	 Thread.sleep(2000);
         	 try{
           		driver.findElement(By.xpath("//img[@title='eDoc Sign']")).click();
                 driver.switchTo().defaultContent();
           		 }
           		 catch(Exception e){
           			documentUploadValidationMail(driver);
           		 }
//              driver.findElement(By.xpath(yopMailHeaderXpath)).clear();
        }
        else if(driver.findElements(By.xpath(yopMailHeaderXpath)).size()>1)
        {
              System.out.println("Inside more than one");
              for(WebElement ele:driver.findElements(By.xpath(yopMailHeaderXpath))){
                    int count=0; count++;
                    ele.click();
                    driver.switchTo().defaultContent();
                    Thread.sleep(2000);
                    driver.switchTo().frame("ifmail");
              		driver.findElement(By.xpath("//a[contains(.,'Show pictures')]")).click();
              		 Thread.sleep(2000);
              		 try{
              		driver.findElement(By.xpath("//img[@title='eDoc Sign']")).click();
                    driver.switchTo().defaultContent();
                    break;
              		 }
              		 catch(Exception e){
              			documentUploadValidationMail(driver);
              			break;
              		 }
              }
        }
		System.out.println("mail clicked");
		TimeUnit.MINUTES.sleep(1);
        tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(2));  
		System.out.println("Switched!!>>"+driver.getTitle());
  }

	
	public static void documentUploadValidationMail(WebDriver driver) throws TwfException, InterruptedException{
//		WebDriver driver=DriverFactory.getDriver();
//		web.loanNumberOnboard="7202522497";
//		inter.conditionValid="Provide Purchase Contract signed by All Parties";
		String docOndition="//p[contains(.,'%con')]";
		String docLoan="//p[contains(.,'%loan')]";
		System.out.println("DocumentUploadValidationMail>>>");
		boolean isDisplayed=false;
//		driver.findElement(By.xpath("//input[@id='login']")).clear();
		driver.switchTo().defaultContent();
        Thread.sleep(2000);
        driver.switchTo().frame("ifmail");
        System.out.println("From other classes>>"+ web.loanNumberOnboard+"Internal Condition>>"+inter.conditionValid);
//        try{
        String disloanNo=driver.findElement(By.xpath(docLoan.replace("%loan", web.loanNumberOnboard))).getText();
        String condition=driver.findElement(By.xpath(docOndition.replace("%con", inter.conditionValid))).getText();
        System.out.println("Conditions>>>"+condition+"LoanNumber>>"+disloanNo);
        if(!driver.findElement(By.xpath(docLoan.replace("%loan", web.loanNumberOnboard))).isDisplayed() && !driver.findElement(By.xpath(docOndition.replace("%con", inter.conditionValid))).isDisplayed()){
        	addExceptionToReport("Mismatch in loanNumber and condition of document Uploaded", "", "");
        }
//        }catch(Exception e){
 //       	addExceptionToReport("Mismatch in loanNumber and condition of document Uploaded", "", "");
 //       }
  //     System.out.println("From other classes>>"+ web.loanNumberOnboard+"Internal Condition>>"+inter.conditionValid);
	}
	
  public void newUserValidation() throws BiffException, InvalidFormatException, IOException, Exception{
	  WebDriver driver = DriverFactory.getDriver();
	  String value;
	  System.out.println("inside newUserValidation");
	  util.waitTimeForSpinner(driver);
	  driver.manage().window().maximize();
	  util.click(getElementByUsing("Register_Button"));
	  LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
	  String input="(//label[contains(text(),'%s')]//following-sibling::input[@class])[1]";
	  dataMap=ten.buildDataMap1(KWVariables.getVariables().get("NewUserData"));
	  System.out.println("DATAMAP>>>"+dataMap);
	  for(String s: dataMap.keySet()){
		  if(s.equals("Username")){{
			  
		  }
			  int len=8;
			  value = RandomStringUtils.random(len, true, false);
			  System.out.println("Value>>>>>"+value);
			  driver.findElement(By.xpath(input.replace("%s", s))).sendKeys(value);
		  }
		  else
		  driver.findElement(By.xpath(input.replace("%s", s))).sendKeys(dataMap.get(s));
		  
		  System.out.println("PRINT>>>"+input.replace("%s", s)+"VALUE>>>"+dataMap.get(s));
		  if(driver.findElement(By.xpath("//input[@value='Continue']")).isEnabled()){
			  driver.findElement(By.xpath("//input[@value='Continue']")).click();
			  System.out.println("Clicked");
		  }
	  } Thread.sleep(5000);
	  System.out.println("MESSAGE>>>"+driver.findElement(By.xpath("//input[@id='temp-mfa-token']")).getText());
	  System.out.println("Text<<<<"+driver.findElement(By.xpath(".//*[@id='temp-mfa-token']")).getAttribute("placeholder"));
	  String t =  (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].value;",driver.findElement(By.xpath("//input[@id='temp-mfa-token']")));
	  System.out.println("T>>>>>"+t);
	  String otpValue[]=t.split("-");
	  String coseText = "";
      coseText=otpValue[1];
	  String placeholder = driver.findElement(By.xpath(".//*[@id='tfaEmail']")).getAttribute("placeholder");
      String placeholderText[] = placeholder.split(" ");
      placeholder = placeholderText[2];
 //     coseText = util.mailinator(placeholder);
      getElementByUsing("FinExp_LoginTokentext").sendKeys(coseText);
      getElementByUsing("FinExp_LoginTokentext").sendKeys(Keys.ENTER);
      getElementByUsing("FinExp_LoginTokentext").sendKeys(Keys.TAB);
      Thread.sleep(2000);
      getElementByUsing("verifyButton_NewUser").click(); 
      Thread.sleep(2000);
      getElementByUsing("SkipPhoneVerify_Button").click();
      Thread.sleep(2000);
      String data="";
      if(step.getDataValue("NewUserPrequal").equals("Refinance")){
    	  System.out.println("Inside Prequal");
    	  data=KWVariables.getVariables().get("DropDownQuestionsRefinance");
      }
      else if(step.getDataValue("NewUserPrequal").equals("Purchase")){
    	   data=KWVariables.getVariables().get("DropDownQuestionsPurchase");
      }
      else
    	  data=KWVariables.getVariables().get("DropDownQuestions");
      selectDropdownQuestion(data.split("#")[0]);
      newUservalidateQuestionAnswer(driver,data.split("#")[1]);
  }

	private void newUservalidateQuestionAnswer(WebDriver driver, String qusAns) throws Exception {
    Thread.sleep(8000);
    String str="//button[contains(.,'%a')]";
	for(String s:qusAns.split(";")){
		driver.findElement(By.xpath(str.replace("%a", s))).click();
	}
}

	private void selectDropdownQuestion(String data) throws Exception {
		System.out.println("Inside selectDropdownQuestion>>>"+data);
		WebDriver driver = DriverFactory.getDriver();
		int len=driver.findElements(By.xpath("//div[@class='dropdown']")).size();
		String qustn="//div[@id='security-question-%s' and @class='dropdown']/a";
		String ans="//div[@id='security-question-answer-%s']/input[1]";
		HashMap<String, String> dataMap = new HashMap<String, String>();
		dataMap = Ten0Three.buildDataMap(data);
		for(int nofqustn=0; nofqustn<len;nofqustn++){
			driver.findElement(By.xpath(qustn.replace("%s", String.valueOf(nofqustn)))).click();
			for(String s:dataMap.keySet()){
				System.out.println("XPATH>>>>>"+qustn.replace("dropdown", "dropdown open").replace("%s", String.valueOf(nofqustn))+"//following-sibling::ul/li[contains(.,'%q')]".replace("%q",s));
				driver.findElement(By.xpath(qustn.replace("dropdown", "dropdown open").replace("%s", String.valueOf(nofqustn))+"//following-sibling::ul/li[contains(.,'%q')]".replace("%q",s))).click();
				driver.findElement(By.xpath(ans.replace("%s", String.valueOf(nofqustn)))).sendKeys(dataMap.get(s));
				dataMap.remove(s);
//				Thread.sleep(5000);
				break;
			}
		System.out.println("Clicked");
		}
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//button[contains(.,'Continue')]")).click();
}

	
	
	public void coBorrowerIntivationMail() throws InterruptedException, TwfException{
		WebDriver driver = DriverFactory.getDriver();
        String parentWindowHandle = driver.getWindowHandle();
		((JavascriptExecutor)driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("https://www.mailinator.com");
		String loginUserName="coborrower";
		TimeUnit.MINUTES.sleep(10);
		driver.findElement(By.id("addOverlay")).sendKeys(loginUserName);

		driver.findElement(By.xpath("//button[contains(.,'GO')]")).click();
		System.out.println("mailCount: before" );
		//int mailCount = driver.findElements(By.xpath("//ul[@id='inboxpane']/li")).size();
		int mailCount = driver.findElements(By.xpath("//div[@id='inboxpane']//tr[@class='even pointer ng-scope']")).size();
		System.out.println("mailCount:" + mailCount);
		for (int mailIndex = 1; mailIndex <= mailCount; mailIndex++) {
			driver.findElement(By.xpath("//div[@id='inboxpane']//tr[@class='even pointer ng-scope'][" + String.valueOf(mailIndex)

			+ "]//td[contains(.,'PennyMac - Email Confirmation')]")).click();
		}

	}
	
	public static void verifyAllMail(String loginUserName) throws Throwable{
		WebDriver driver=DriverFactory.getDriver();
		String mailTemplate="";
		String body="";
		String typeOfMail=loginUserName.split(":")[0];
		String name=loginUserName.split(":")[2];
		loginUserName=loginUserName.split(":")[1];
		String fname="//*[text()='%n,']";
        System.out.println("Inside loginToYopMail"+loginUserName+"Mail Topic>>>"+typeOfMail+"Name>>>"+name);
        String header="";
       ((JavascriptExecutor)driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get("http://www.yopmail.com/en/");
        System.out.println("loginUserName:"+loginUserName);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].value = '';", driver.findElement(By.xpath("//input[@id='login']")));
        System.out.println("Cleared>>");
        driver.findElement(By.xpath("//input[@id='login']")).sendKeys(loginUserName);
        driver.findElement(By.xpath("//input[@value='Check Inbox']")).click();
        TimeUnit.MINUTES.sleep(1);
        driver.findElement(By.xpath("//span[@class='slientext']")).click();
        Thread.sleep(2000);
        String yopMailHeaderXpath="(//a/span[contains(text(),'%subject')])[1]";
        String yopMailBodyXpath="//div[@id='mailmillieu']";//
        switch(typeOfMail.toLowerCase().trim()){
        case "reminder":
        		header="(QA 1.0)Thank You for Taking the First Step with PennyMac";
        		
        	break;
        case "callmemail":
        		System.out.println("Under callme Validation");
        		header="Thank You for Taking the First Step with PennyMac";
        		body=KWVariables.getVariables().get("callMeTemplate");
        	break;
        case "offline":
        	header="Your Personal Link - Step 1";
        	body=KWVariables.getVariables().get("offlineTemplate");
        	break;
        case "econsent":
        	System.out.println("Inside e-consent");
        	header="Your Personal Link - Step 1";
        	body=KWVariables.getVariables().get("econsentTemplate");
        	break;
        case "submission":
        	header="PennyMac: Application Submitted";
        	break;
        case "welcome":
        	header="PennyMac: Track your status 24/7";
        	body=KWVariables.getVariables().get("WelcomeTemplate");
        	break;
        }
 
        yopMailHeaderXpath=yopMailHeaderXpath.replace("%subject", header);
        System.out.println("yopMailHeaderXpath:"+yopMailHeaderXpath);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.switchTo().defaultContent();
        driver.switchTo().frame("ifinbox");
        System.out.println("size:"+driver.findElements(By.xpath(yopMailHeaderXpath)).size());
        driver.findElement(By.xpath(yopMailHeaderXpath)).click();

	              System.out.println("Inside one of veriyMail");
	              driver.switchTo().defaultContent();
	              Thread.sleep(2000);
	              driver.switchTo().frame("ifmail");
	              driver.findElement(By.xpath("//a[contains(.,'Show pictures')]")).click();
	              List<WebElement> list= driver.findElements(By.xpath("//table[@class='container']//td[contains(@align,'left')]"));
	              for(int i=1; i<list.size();i++){
	            	  mailTemplate=mailTemplate+list.get(i).getText();
	              }
//	              System.out.println("All values>>>"+mailTemplate);
	             if(!driver.findElement(By.xpath(fname.replace("%n", name.trim()))).isDisplayed()){
	            	 addExceptionToReport("Mismatch is the name for the email in displayed and expected value", name, "");
	             }
	             if(mailTemplate.contains("URL:")){
	            	 String url=driver.findElement(By.xpath("//a[contains(.,'Onboard')]")).getText();
	            	 body=body.replace("%link%", url);
	             }
	             if(!(mailTemplate.contains(body))){
//	            	 System.out.println("\n inside body>>>>"+body+"\n Inside MailTemplate>>>"+mailTemplate);
	            	 addExceptionToReport("Mismatch in displayed and expected mail template", "\n TEMPLATE>"+mailTemplate, "\n EXCEL DATA>"+body);
	             }
	         	 Thread.sleep(2000);
	  }
	
	public void loginforIDS() throws Exception{
		WebDriver driver=DriverFactory.getDriver();
		util.waitTimeForSpinner(driver);
		driver.manage().window().maximize();
		urlIdentification();
		util.click(getElementByUsing("FinExp_Login_HeaderButton"));
		
		util.click(getElementByUsing("FinExp_Login_Login"));
		util.waitTimeForSpinner(driver);
	}
//	}	
	
	public void clickOnIAmDoneButton() throws TwfException, BiffException, IOException, InterruptedException{
		WebDriver driver=DriverFactory.getDriver();
		util.click(getElementByUsing("1003_GetStarted_IAmDone"));
		Thread.sleep(1000);
	}
	
	public void clearYopMail(String username) throws TwfException, InterruptedException{
		WebDriver driver=DriverFactory.getDriver();
		driver.get("http://www.yopmail.com/en/");
		System.out.println("PageTitle>>>"+driver.getTitle());
//		    try{
		for(String str: username.split("#")){
				System.out.println("Str value>>>"+str);
				driver.findElement(By.xpath("//*[@id='login']")).clear();
		    	driver.findElement(By.xpath("//*[@id='login']")).sendKeys(str);
		     Thread.sleep(1000);
		     driver.findElement(By.xpath("//*[@id='f']/table/tbody//input[contains(@title,'Check')]")).click();
		     driver.switchTo().frame("ifinbox");
		     driver.findElement(By.xpath("//span[@class='igif lmenudelfl']")).click();
		     driver.findElement(By.linkText("Empty Inbox")).click();
		     driver.navigate().to("http://www.yopmail.com/en/");
		     driver.switchTo().defaultContent();
		     System.out.println("PageTitle111>>>"+driver.getTitle());
		}
	}
	
	
	@Override
	public void checkPage() {
		// TODO Auto-generated method stub
		
	}

}

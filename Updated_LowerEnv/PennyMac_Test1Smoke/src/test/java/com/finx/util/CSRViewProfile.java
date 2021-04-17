package com.finx.util;

import java.awt.Desktop.Action;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.tavant.base.DriverFactory;
import com.tavant.kwutils.CustomStep;
import com.tavant.kwutils.KWVariables;
import com.tavant.utils.TwfException;

import jxl.read.biff.BiffException;

public class CSRViewProfile extends CustomStep {
	
	public void clickOnViewProfileButton() throws TwfException, InterruptedException{
		System.out.println("entered clickOnViewProfileButton");
		WebDriver driver = DriverFactory.getDriver();
		
		
		try{
		driver.findElement(By.xpath(".//button[contains(.,'Go to Profile')]")).click();
		System.out.println("clicked");

		}   
		
		catch(Exception e){
			System.out.println("Exception"+e);
			
		}
		
/*		try{
				Actions action = new Actions(driver);
		WebElement viewProfilebutton = driver.findElement(By.xpath(".//button[contains(.,'Go to Profile')]"));
		//action.click();
		action.moveToElement(viewProfilebutton).click().perform();
		//action.click(viewProfilebutton).build().perform();
		//action.
		System.out.println("clicked");
		Thread.sleep(5000);}
		catch(Exception e){
			System.out.println("Exception"+e);
			
		}*/

	}

	public void searchCreatedGetQuoteinCSR(String userdetails) throws TwfException, InterruptedException, Exception, InvalidFormatException, IOException{
		System.out.println("entered searchCreatedGetQuoteinCSR");
		WebDriver driver = DriverFactory.getDriver();
		Dashboard db = new Dashboard();		
		String expectedUserdetails= KWVariables.getVariables().get(userdetails);
		String userId = expectedUserdetails.trim().split("-")[1].trim();
		String CSRurl = KWVariables.getVariables().get("SSOURL");
		String loginEmail = KWVariables.getVariables().get("CSR_SSO_Login_email");
		String loginPassword = KWVariables.getVariables().get("CSR_SSO_Login_password");
		System.out.println("Values fetched from dataset ---> "+ loginEmail + "  "+ loginPassword);
		
		
			String getQuoteLn_status = db.fetchInquiryIDfromDB(expectedUserdetails);
			String inquiryID = getQuoteLn_status.trim().split("-")[0].trim();
			System.out.println("inquiryID ---> "+ inquiryID);
			driver.navigate().to(CSRurl);
			getElementByUsing("FinExp_SSO_Login_email").sendKeys(loginEmail);
			getElementByUsing("FinExp_SSO_Login_password").sendKeys(loginPassword);
			getElementByUsing("FinExp_SSO_Login_button").click();
			getElementByUsing("FinExp_CSR_Search_searchBar").sendKeys(inquiryID);
			getElementByUsing("FinExp_CSR_Search_searchButton").click();
			String ActualEmail = getElementByUsing("FinExp_CSR_Search_searchResults_firstResult_email").getText();
			if(ActualEmail.trim().equalsIgnoreCase(userId)== false)
			{
				addExceptionToReport("Incorrect data displayed for the user", ActualEmail, userId);
			}
			}
	
	@Override
	public void checkPage() {
		// TODO Auto-generated method stub
		
	}

}

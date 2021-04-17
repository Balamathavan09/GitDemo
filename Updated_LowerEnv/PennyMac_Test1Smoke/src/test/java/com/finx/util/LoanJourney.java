package com.finx.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.tavant.base.DriverFactory;
import com.tavant.kwutils.CustomStep;

public class LoanJourney extends CustomStep {
	String loanJourneyUrl = "http://internal-pennymac-encompass-qa-1662691272.us-west-1.elb.amazonaws.com/encompasswrapper/encompass/api/v2/loans/{guid}";
	String loanViewDetailsLink = "//div[contains(text(),'%LN')]/ancestor::div[1]/ancestor::div[1]/following-sibling::div//a[@class='details-link']";

	Util util = new Util();
	Dashboard dashboard=new Dashboard();

	String loanStatusCompleted="//div[@class='journey-sec']//div[contains(@class,'completed')][%jourNo]/div[contains(@class,'text')]/div[1]"; 

	public void updateLoanStatusJourneyAndVerify()throws Throwable{
		System.out.println("Inside updateLoanStatusJourney>>>");
		String loanNumber = "7202230714"; // remove hard coded value later
		String guidNumber = util.getLoanGUIDFromDB(loanNumber);
		String loanStatusLocator="";
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		String curDate = formatter.format(date);	
		System.out.println("Cure dAte>>>>"+curDate);
		String payLoadFormat = "[{\"changes\":{%n},\"operation\":\"replace\"}]";
		String[] expLoanJour={"Application Processing","Document Collection","Application & Appraisal Review","Loan Approved","Closing","Loan Funded"};
		String[] loanJourStatus = {"\"CX.APPLICATIONPROGRESSSTATUS:CX.APPLICATIONPROGRESSCMPDTE","\"CX.DOCUMENTCOLLECTIONSTATUS:\"CX.DOCUMENTCOLLECTIONCMPDATE","\"CX.DOCUMENTATIONREVIEWSTATUS:\"CX.DOCUMENTATIONREVIEWCMPDAT","\"CX.LOANAPPROVEDSTATUS:\"CX.LOANAPPROVEDCMPDATE","\"CX.CLOSINGSCHEDULEDSTATUS:\"CX.CLOSINGSCHEDULEDCOMPDATE","\"CX.LOANCLOSEDSTATUS:\"CX.LOANCLOSEDCOMPDATE"};
		String dynamicPayLoad = "";
		String dynamicPayLoadFinal ="";

		for(int i = 1;i < loanJourStatus.length; i++){	
			if(i== 1){
				dynamicPayLoad = loanJourStatus[0].split(":")[0]+"\""+":"+"\"Y\""+","+"\""+loanJourStatus[0].split(":")[1]+"\""+":"+"\""+curDate+"\""+","+loanJourStatus[i].split(":")[0]+"\""+":"+"\"Y\"";
			}else{
				dynamicPayLoad = dynamicPayLoad+","+loanJourStatus[i-1].split(":")[1] +"\""+":"+"\""+curDate+"\"";
				dynamicPayLoad = dynamicPayLoad+","+loanJourStatus[i].split(":")[0]+"\""+":"+"\"Y\"";
			}
			dynamicPayLoadFinal = payLoadFormat.replace("%n", dynamicPayLoad.substring(0, dynamicPayLoad.length()));
			System.out.println("Payload for Loan Journey>>>"+dynamicPayLoadFinal);
			WebService.patchRequest(loanJourneyUrl, dynamicPayLoadFinal, guidNumber);
			loanStatusLocator=loanStatusCompleted.replaceAll("%jourNo",String.valueOf(i));
			System.out.println("loanStatusLocator>>>"+loanStatusLocator);
			verifyLoanJourneyStatus(loanNumber,expLoanJour[i-1],loanStatusLocator);
		}

		dynamicPayLoad = dynamicPayLoad+","+loanJourStatus[loanJourStatus.length-1].split(":")[1] +"\""+":"+"\""+curDate+"\"";
	//	System.out.println("Last Payload for Loan Journey>>>"+ payLoadFormat.replace("%n", dynamicPayLoad.substring(1, dynamicPayLoad.length())));
		dynamicPayLoadFinal=payLoadFormat.replace("%n", dynamicPayLoad.substring(0, dynamicPayLoad.length()));
		System.out.println("Payload for Loan Journey>>>"+dynamicPayLoadFinal);
		WebService.patchRequest(loanJourneyUrl, dynamicPayLoadFinal, guidNumber);
		loanStatusLocator=loanStatusCompleted.replaceAll("%jourNo",String.valueOf(expLoanJour.length));
		verifyLoanJourneyStatus(loanNumber,expLoanJour[expLoanJour.length-1],loanStatusLocator);
	}

	public void verifyLoanJourneyStatus(String loanNumber,String expLoanJournStatus,String loanStatusLocator) throws Throwable{
		System.out.println("Inside getLoanJourneyStatus>>>");
		WebDriver driver = DriverFactory.getDriver();

		getElementByUsing("Dashboard_Submitted_Tab").isDisplayed();
		getElementByUsing("Dashboard_Submitted_Tab").click();
		driver.findElement(By.xpath(loanViewDetailsLink.replace("%LN", loanNumber))).click();
		int waitCount = 2;
		boolean found = false;
		String loanStatusLocatorText="";
		System.out.println("Waiting for Loan Journey Status to get updated>>>" + found);
		while (found == false && waitCount > 0) {
			Thread.sleep(2000);
			for(int i=1;i<=8;i++){
				System.out.println("waiting>>>");
				TimeUnit.MINUTES.sleep(1);
				driver.navigate().refresh();
				getElementByUsing("Dashboard_Submitted_Tab").isDisplayed();
				getElementByUsing("Dashboard_Submitted_Tab").click();
				driver.findElement(By.xpath(loanViewDetailsLink.replace("%LN", loanNumber))).click();
				try{
					loanStatusLocatorText=driver.findElement(By.xpath(loanStatusLocator)).getText();
					break;
				}
				catch(Exception e){
					continue;
				}
			}
			waitCount--;
			if (loanStatusLocatorText.contains(expLoanJournStatus)) {
				found = true;
				System.out.println("found inside while ????" + found);
				break;
			}
		}
		if (!found) {
			addExceptionToReport("Loan Journey not updated in UI after waiting 15 min", "", "");
		}

		//dashboard.validateYourJourney("Application Processing");
		driver.navigate().refresh();
		System.out.println("Loan Journey Status>>>"+loanStatusLocatorText);
	}
	@Override
	public void checkPage() {
		// TODO Auto-generated method stub

	}
}
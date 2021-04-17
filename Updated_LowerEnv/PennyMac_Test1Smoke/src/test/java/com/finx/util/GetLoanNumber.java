package com.finx.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.tavant.base.DriverFactory;
import com.tavant.kwutils.CustomStep;
import com.tavant.utils.TwfException;

public class GetLoanNumber extends CustomStep{
	public void getLoanNumber() throws TwfException{
		System.out.println("entered getLoanNumber code");
		WebDriver driver = DriverFactory.getDriver();
		String loanNumber =driver.findElement(By.xpath("(//div[@class='loan-number'])[1]")).getText();
		System.out.println("loanNumber: "+loanNumber);
	}

	@Override
	public void checkPage() {
		// TODO Auto-generated method stub
		
	}
}

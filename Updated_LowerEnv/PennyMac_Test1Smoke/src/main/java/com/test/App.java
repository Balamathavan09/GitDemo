package com.test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tavant.base.DriverFactory;
import com.tavant.utils.TwfException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws TwfException
    {
        System.out.println( "Hello World!" );
        
        DriverFactory.getDriver().get("http://www.google.com");
		List<WebElement> totalImages = DriverFactory.getDriver().findElements(By.tagName("img"));
		System.out.println("Total number of image on the page : "+totalImages.size());
		
		for (int i=0; i<totalImages.size(); i++)
		{
			String elementText = totalImages.get(i).getText();
			System.out.println(elementText);
		}
        
    }
}

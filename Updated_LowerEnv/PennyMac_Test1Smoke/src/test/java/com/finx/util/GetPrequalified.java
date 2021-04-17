package com.finx.util;


import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.tavant.base.DriverFactory;
import com.tavant.kwutils.CustomStep;
import com.tavant.utils.TwfException;

public class GetPrequalified extends CustomStep {

	String path1 = "//div[@id='main-sec']/div[@class[contains(.,'form-container tf-block-";
	String qustion2 = " active')]]//div[@class='form-group']//form/div[2]//dynamic-control/div/div//label";
	String fieldAtttibute2 = " active')]]//div[@class='form-group']//form/div[2]//dynamic-control/div";
	String OkayButton2 = " active')]]//div[@class='form-group']//form/div[3]/div/button";
	String OkayButton3 = " active')]]//div[@class='form-group']//form/div[4]/div/button";
	String inputText2 = " active')]]//div[@class='form-group']//form/div[2]//dynamic-control/div/div//md-input-container//input";
	String selectAns2 = " active')]]//div[@class='form-group']//form/div[2]//dynamic-control/div/div//div/div/button[contains(.,'";
	String selectAns3 = "')]";

	String rateQuoteIndex = "//div[@class='product-container']/div//div[@class='add-quote-message']";
	String rateQuoteList = "//div[@class='product-container']/div[1]//div/ul/li";
	String rateQuoteXpath = "//div[@class='product-container']/div[";
	String title1 = "//div[@class='product-container']/div[";
	String title2 = "]//div//h1/div[1]";

	GenerateData dummyData = new GenerateData();
  
	public void beforePageLoad() throws TwfException {
		WebDriver driver = DriverFactory.getDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.mpPageReloaded='notYet';");

	}

	public void selectQuestion2Answer(String ExpectedText) throws InterruptedException, Exception {
		WebDriver driver = DriverFactory.getDriver();
		int i = 0;

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("I am looking to", "Buy a new home");
		map.put("I am a First Time Home Buyer", "Yes");
		map.put("Property Value", "50000");
		map.put("DownPayment Amount", "40000");
		map.put("My credit score is", "800+");
		map.put("I plan to buy a property at", "27615");
		map.put("I plan to buy a", "Single Family Residence");
		map.put("I will use this property as", "My Primary Home");
		map.put("Total Gross Income", "300000");
		map.put("Monthly Expenses", "2000");
		map.put("Total Assests", "50000");
		map.put("I am a Veteran", "Yes");
		map.put("I plan to buy a property worth", "27615");

		for (i = 1; i < 12; i++) { 

			/// WebElement dateWidget =qustion1+i+qustion2;
			String attribute = driver.findElement(By.xpath(path1 + i + fieldAtttibute2))
					.getAttribute("ng-reflect-ng-switch");
			String qustion = driver.findElement(By.xpath(path1 + i + qustion2)).getText().trim();

			Object value = map.get(qustion);
			System.out.println("value = " + value);

			if (attribute.equalsIgnoreCase("radioBtn")) {
				System.out.println("inside radioBtn");
				String val = String.valueOf((String) map.get(qustion));
				driver.findElement(By.xpath(path1 + i + selectAns2 + val + selectAns3)).click();
				System.out.println("click on okay button");
				driver.findElement(By.xpath(path1 + i + OkayButton2)).click();
				Thread.sleep(3000);

			} else if (attribute.equalsIgnoreCase("currency")) {

				System.out.println("inside currency");
				driver.findElement(By.xpath(path1 + i + inputText2)).sendKeys(map.get(qustion));
				System.out.println("click on okay button");
				driver.findElement(By.xpath(path1 + i + OkayButton2)).click();
				Thread.sleep(3000);

			} else if (attribute.equalsIgnoreCase("zipcode")) {
				System.out.println("inside zipcode");
				// int val = Integer.valueOf((String) map.get(qustion));
				driver.findElement(By.xpath(path1 + i + inputText2)).sendKeys(map.get(qustion));

				// driver.findElement(By.xpath(path1 + i +
				// inputText2)).sendKeys(Keys.TAB);

				// driver.findElement(By.xpath("(.//*[@id='dropdownMenu1'])[1]")).click();
				// driver.findElement(By.xpath("//a[contains(.,'Alaska')]")).click();
				// driver.findElement(By.xpath("(.//*[@id='dropdownMenu1'])[2]")).click();
				// driver.findElement(By.xpath("//a[contains(.,'Adak')]")).click();
				// driver.findElement(By.xpath("(.//*[@id='dropdownMenu1'])[3]")).click();
				// driver.findElement(By.xpath("//a[contains(.,'Aleutians West
				// (CA)')]")).click();

				driver.findElement(By.xpath(path1 + i + OkayButton3)).click();
				Thread.sleep(5000);
				System.out.println("last line of currency");
				System.out.println("click on okay button");
				driver.findElement(By.xpath(path1 + i + OkayButton3)).click();
				Thread.sleep(3000);

			}

			beforePageLoad();
			Thread.sleep(3000);

			System.out.println(driver.getCurrentUrl());
			if (driver.getCurrentUrl().equalsIgnoreCase("http://10.131.148.191:9005/#/productsScreen")) {
				System.out.println("inside captureRecommendedProductsDetails ");
				System.out.println(driver.getCurrentUrl());
				captureRecommendedProductsDetails();
				break;
			}

		}
	}

	public void selectQuestion2AnswerPopup(String ExpectedText) throws InterruptedException, Exception {
		WebDriver driver = DriverFactory.getDriver();
		int i = 0;

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("I am looking to", "Buy a new home");
		map.put("I am a First Time Home Buyer", "Yes");
		map.put("Property Value", "50000");
		map.put("DownPayment Amount", "40000");
		map.put("My credit score is", "800+");
		map.put("I plan to buy a property at", "12345");
		map.put("I plan to buy a", "Single Family Residence");
		map.put("I will use this property as", "My Primary Home");
		map.put("Total Gross Income", "300000");
		map.put("Monthly Expenses", "2000");
		map.put("Total Assests", "50000");
		map.put("I am a Veteran", "Yes");

		for (i = 0; !driver.getCurrentUrl().equalsIgnoreCase("http://10.131.148.191:9005/#/productsScreen"); i++) {

			/// WebElement dateWidget =qustion1+i+qustion2;
			String attribute = driver.findElement(By.xpath(path1 + i + fieldAtttibute2))
					.getAttribute("ng-reflect-ng-switch");
			String qustion = driver.findElement(By.xpath(path1 + i + qustion2)).getText().trim();

			Object value = map.get(qustion);
			System.out.println("value = " + value);

			if (attribute.equalsIgnoreCase("radioBtn")) {
				System.out.println("inside radioBtn");
				String val = String.valueOf((String) map.get(qustion));
				driver.findElement(By.xpath(path1 + i + selectAns2 + val + selectAns3)).click();
				System.out.println("click on okay button");
				driver.findElement(By.xpath(path1 + i + OkayButton2)).click();
				Thread.sleep(3000);

			} else if (attribute.equalsIgnoreCase("currency")) {

				System.out.println("inside currency");
				driver.findElement(By.xpath(path1 + i + inputText2)).sendKeys(map.get(qustion));
				System.out.println("click on okay button");
				driver.findElement(By.xpath(path1 + i + OkayButton2)).click();
				Thread.sleep(3000);

			} else if (attribute.equalsIgnoreCase("zipcode")) {
				System.out.println("inside zipcode");
				// int val = Integer.valueOf((String) map.get(qustion));
				driver.findElement(By.xpath(path1 + i + inputText2)).sendKeys(map.get(qustion));
				driver.findElement(By.xpath(path1 + i + inputText2)).sendKeys(Keys.TAB);

				Select dropdown = new Select(driver.findElement(By.xpath("(.//*[@id='dropdownMenu1'])[1]")));
				dropdown.selectByIndex(2);

				Thread.sleep(5000);
				System.out.println("last line of currency");
				System.out.println("click on okay button");
				driver.findElement(By.xpath(path1 + i + OkayButton3)).click();
				Thread.sleep(3000);

			}

			if (driver.findElement(By.xpath(".//*[@id='autop_name']")).isDisplayed()) {
				System.out.println("go to auto popup method");
				Thread.sleep(3000);

				if (ExpectedText.equalsIgnoreCase("login"))
					enteredDetailsinAutoPopupLogin();

			}
		}
		System.out.println("end method popup");
	}

	public void enteredDetailsinAutoPopupQuestionAnswerPopUp() throws InterruptedException, Exception {

		WebDriver driver = DriverFactory.getDriver();
		WebElement name = getElementByUsing("FinExp_prequal_popup_autop_name");

		WebElement email = getElementByUsing("FinExp_prequal_popup_autop_userId");
		WebElement mobileNumber = getElementByUsing("FinExp_prequal_popup_mobileNumber");
		name.sendKeys(dummyData.generateFirstName(10));
		email.sendKeys(dummyData.generateEmail(12));
		mobileNumber.sendKeys(dummyData.generateRandomNumber(10));
		WebElement Continue_button = getElementByUsing("FinExp_prequal_popup_Continue");
		Continue_button.click();

	}

	public void enteredDetailsinAutoPopupLogin() throws InterruptedException, Exception {

		WebDriver driver = DriverFactory.getDriver();

		// WebElement login_button =
		// getElementByUsing("FinExp_prequal_popup_Login");
		// login_button.click();

		driver.findElement(By.xpath(".//*[@id='myModal1']/div/div/div/div[2]/div/div[2]/button[1]")).click();
		driver.findElement(By.xpath("(.//*[@id='login_userId'])[2]")).sendKeys("prasann.patwardhan@tavant.com");
		driver.findElement(By.xpath("(.//*[@id='password'])[3]")).sendKeys("Abc1234#");
		driver.findElement(By.xpath("(//button[contains(.,'Login')])[3]")).click();

	}

	public void captureRecommendedProductsDetails() throws InterruptedException, Exception {

		WebDriver driver = DriverFactory.getDriver();

		String title = null;
		int rateCardIndex = driver.findElements(By.xpath(rateQuoteIndex)).size();
		int rateCardList = driver.findElements(By.xpath(rateQuoteList)).size();

		HashMap<String, HashMap<String, String>> productRateQuoteMap = new HashMap<String, HashMap<String, String>>();
		HashMap<String, String> rateCodeHashMap = new HashMap<String, String>();
		// remove hardcoding of upper limit
		// i --> rateCardIndex
		// Dont hard code xpaths
		System.out.println("rate Card Index" + rateCardIndex);
		for (int i = 1; i <= rateCardIndex; i++) {

			title = driver.findElement(By.xpath(rateQuoteXpath + i + "]//div//h1/div[1]")).getText().trim();
			// rateCodeHashMap.put(title + i, title);

			// remove hardcoding of upper limit
			System.out.println("rate Card list " + rateCardList);
			for (int j = 1; j < rateCardList; j++) {
				String eleName = null;
				String eleValue = null;
				eleName = driver.findElement(By.xpath(rateQuoteXpath + i + "]//div/ul/li[" + j + "]/div[2]")).getText()
						.trim();
				eleValue = driver.findElement(By.xpath(rateQuoteXpath + i + "]//div/ul/li[" + j + "]/div[1]")).getText()
						.trim();

				rateCodeHashMap.put(eleName, eleValue);

			}
			productRateQuoteMap.put(title + i, rateCodeHashMap);

		}

		System.out.println("end captureRecommendedProductsDetails");

		// for (String key : rateCodeHashMap.keySet()) {
		// System.out.println("------------------------------------------------");
		// System.out.println("key: " + key + " value: " +
		// rateCodeHashMap.get(key));
		// System.out.println("------------------------------------------------");
		// }

		// productRateQuoteMap.keySet().size();

		// for(int k = 1;k <= productRateQuoteMap.size();k++){

		// productRateQuoteMap.keySet().iterator().hasNext()

		// resultMap =productRateQuoteMap.get(key)

		// }

		for (String key1 : productRateQuoteMap.keySet()) {
			Map innerMap = productRateQuoteMap.get(key1);
			for (Object key2 : innerMap.keySet()) {
				String key3 = key2.toString();

				System.out.println("------------------------------------------------");
				System.out.println("key: " + key3 + " value: " + rateCodeHashMap.get(key3));
				System.out.println("------------------------------------------------");

			}
		}

	}

	@Override
	public void checkPage() {

		// TODO Auto-generated method stub

	}

}

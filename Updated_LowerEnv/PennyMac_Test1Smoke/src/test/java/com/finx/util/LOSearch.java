package com.finx.util;

import java.util.HashMap;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.tavant.base.DriverFactory;
import com.tavant.base.WebPage;
import com.tavant.kwutils.CustomStep;
import com.tavant.kwutils.Step;

public class LOSearch extends CustomStep  {

	String searchName = "//div[@class='officer-list-holder']//div[contains(@class,'name-header')]";
	String searchZipcode ="//div[@class='officer-list-holder']//div[contains(@class,'street-address1')][3]";
	String searchCity ="//div[@class='officer-list-holder']//div[contains(@class,'street-address1')][2]";
	String searchResult = "//div[@class='filter-text']/span";
	String searchDetails = "//div[@class='officer-list-holder']/div[%s]";
	String SearchCriteriaField = "//div[@class='filter-links pull-right']/a";
	String SearchCriteriaFieldCounty = "//div[@class='filter-links pull-right'][2]/a";
	String searchDetailsCount = "//div[@class='officer-list-holder']/div";

	/**
	 * Method Name: verifyLOSearchResults
	 * Purpose: To verifu LO search Results
	 * @param searchValue
	 * @throws Exception
	 */
	public void verifyLOSearchResults() throws Exception {
		WebDriver driver = DriverFactory.getDriver();
		String criteria = null;
		String loSearchSelectData = fetchLOData().trim();
		String[] loSearchSelectDataPair = loSearchSelectData.split(";");
		System.out.println("loSearchSelectDataPair "+ loSearchSelectDataPair);
		
		for(int i=0;i<loSearchSelectDataPair.length;i++)
		{
		String[] serachV=loSearchSelectDataPair[i].split(":");
		String expectedSearchType = serachV[0];
		String expectedSearchValue = serachV[1];
		System.out.println("expectedSearchType ---> "+ expectedSearchType);
		System.out.println("expectedSearchValue ---> "+ expectedSearchValue);
		getElementByUsing("LO_Search_TextBox").clear();
		getElementByUsing("LO_Search_TextBox").sendKeys(expectedSearchValue);
		getElementByUsing("LO_Search_SearchButton").click();
		waitForElement(driver.findElement(By.xpath(searchDetails.replace("%s", "1"))), 40000);
		
		if(expectedSearchType.trim().equalsIgnoreCase("county"))
		criteria = driver.findElement(By.xpath(SearchCriteriaFieldCounty)).getText();	
		else
		criteria = driver.findElement(By.xpath(SearchCriteriaField)).getText();
		
		String actualSearchCriteria = criteria.split(":")[0];
		
		if(actualSearchCriteria.trim().equalsIgnoreCase(expectedSearchType.trim())==false){
			addExceptionToReport("Search Criteria is not matching", actualSearchCriteria.toLowerCase().trim(), expectedSearchType.toLowerCase().trim());
		}
		
		String tempResult = driver.findElement(By.xpath(searchResult)).getText();		
		int expectedSearchCount = Integer.parseInt(tempResult);
	
		//Compare search Count
		int displayedSearchCount = driver.findElements(By.xpath(searchDetailsCount)).size();
		if(displayedSearchCount!=expectedSearchCount){
			addExceptionToReport("Search Count is not matching with expected Search Count", String.valueOf(displayedSearchCount), String.valueOf(expectedSearchCount));
		}
		//Replace if conditions by Switch case
		if (expectedSearchType.equalsIgnoreCase("Name")) {
			searchByName(driver, expectedSearchCount, expectedSearchValue);			
		}
		if (expectedSearchType.equalsIgnoreCase("Zipcode")) {
			searchByZipcode(driver, expectedSearchCount, expectedSearchValue);			
		}
		if (expectedSearchType.equalsIgnoreCase("County")) {
			searchByCounty(driver, expectedSearchCount, expectedSearchValue);			
		}
		if (expectedSearchType.equalsIgnoreCase("State")) {
			searchByState(driver, expectedSearchCount, expectedSearchValue);			
		}
		if (expectedSearchType.equalsIgnoreCase("City")) {
			searchByCity(driver,expectedSearchCount, expectedSearchValue);		
		} 
		}

	}
	/**
	 * Method Name: searchByName
	 * Purpose: To perform search operation By Name field
	 * @param driver
	 * @param count
	 * @param expectedName
	 * @throws Exception
	 */
	public void searchByName(WebDriver driver, int count,String expectedName) throws Exception{
		for (int serachResultCount = 1; serachResultCount <= count; serachResultCount++) {
			String displayedName = driver.findElement(By.xpath(searchName.replace("%s", String.valueOf(serachResultCount))))
					.getText();
			if(displayedName.trim().toLowerCase().contains(expectedName.toLowerCase()) == false){
				addExceptionToReport("Expected Name is not displayed after Search By Name Action in the search card "+String.valueOf(serachResultCount), displayedName.trim().toLowerCase(),expectedName.trim().toLowerCase());
			}
			
		}
	}

	/**
	 * Method Name: searchByZipcode
	 * Purpose: To perform search By ZipCOde field
	 * @param driver
	 * @param count
	 * @param expectedName
	 * @throws Exception
	 */
	public void searchByZipcode(WebDriver driver, int count,String expectedName) throws Exception{
		for (int serachResultCount = 1; serachResultCount <= count; serachResultCount++) {
			String displayedName = driver.findElement(By.xpath(searchZipcode)).getText();
			System.out.println("displayedName ---> "+ displayedName);
			if(displayedName.trim().contains(expectedName) == false){
				addExceptionToReport("Expected Zip Code is not displayed after Search By Zip Code Action in the search card "+String.valueOf(serachResultCount), displayedName.trim().toLowerCase(),expectedName.trim().toLowerCase());
			}
			
		}
	}
	/**
	 * Method Name: searchByCity
	 * Purpose: To perform Search By City value
	 * @param driver
	 * @param count
	 * @param expectedName
	 * @throws Exception
	 */
	public void searchByCity(WebDriver driver, int count,String expectedName) throws Exception{
		for (int serachResultCount = 1; serachResultCount <= count; serachResultCount++) {
			String displayedName = driver.findElement(By.xpath(searchCity))
					.getText();
			System.out.println("displayedName ---> "+ displayedName);
			if(displayedName.trim().contains(expectedName) == false){
				addExceptionToReport("Expected City is not displayed after Search By City Action in the search card "+String.valueOf(serachResultCount), displayedName.trim().toLowerCase(),expectedName.trim().toLowerCase());
			}
			
		}
	}
	/**
	 * Method Name: searchByCounty
	 * Purpose: To perform Search By County value
	 * @param driver
	 * @param count
	 * @param expectedName
	 * @throws Exception
	 */
	public void searchByCounty(WebDriver driver, int count,String expectedName) throws Exception{
		for (int serachResultCount = 1; serachResultCount <= count; serachResultCount++) {
			String displayedName = driver.findElement(By.xpath(searchZipcode))
					.getText();
			System.out.println("displayedName ---> "+ displayedName);
			if(displayedName.trim().contains(expectedName) == false){
				addExceptionToReport("Expected County is not displayed after Search By County Action in the search card "+String.valueOf(serachResultCount), displayedName.trim().toLowerCase(),expectedName.trim().toLowerCase());
			}
			
		}
	}

	/**
	 * Method Name: searchByState
	 * Purpose: To perform search by state operation
	 * @param driver
	 * @param count
	 * @param expectedName
	 * @throws Exception
	 */
	public void searchByState(WebDriver driver, int count,String expectedName) throws Exception{
		for (int serachResultCount = 1; serachResultCount <= count; serachResultCount++) {
			String displayedName = driver.findElement(By.xpath(searchCity))
					.getText();
			if(displayedName.trim().contains(expectedName) == false){
				addExceptionToReport("Expected State is not displayed after Search By State Action in the search card "+String.valueOf(serachResultCount), displayedName.trim().toLowerCase(),expectedName.trim().toLowerCase());
			}
			
		}
	}
	
	
	public String fetchLOData() throws Exception {
		String loSearchSelectData = step.getDataValue("LOSearchCriteria").trim()  ;
		return loSearchSelectData;
	}

	@Override
	public void checkPage() {
		// TODO Auto-generated method stub

	}
}
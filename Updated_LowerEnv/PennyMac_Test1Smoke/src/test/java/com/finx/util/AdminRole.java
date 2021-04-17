package com.finx.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tavant.base.DriverFactory;
import com.tavant.base.WebPage;
import com.tavant.kwutils.CustomStep;
import com.tavant.utils.TwfException;

import jxl.read.biff.BiffException;
// import net.sourceforge.htmlunit.corejs.javascript.ast.SwitchCase;


public class AdminRole extends CustomStep {

	static WebDriver driver;  
	String editrole_MicrositeURL;
	 

	String editButton = "(//button[contains(.,'Edit')])[%s]";
	String assign_button = "//a[contains(.,'%s')]/ancestor::*[self::tr]/td[5]/span/button";
	//String roleCheckBox = ".//*[@class='ui-chkbox-icon ui-c fa fa-check']";
	String roleCheckBox = ".//*[@class='ui-chkbox-icon ui-clickable fa fa-check']";   
	String multiSelectRoleObject  = "//div[@class='ui-multiselect-items-wrapper']/ul/li";
	String dropDownRoleLabelObject = "//div[@class='ui-multiselect-items-wrapper']/ul/li[%s]/label";
	String dropDownRoleCheckBoxObject = "//div[@class='ui-multiselect-items-wrapper']/ul/li[%s]/div/div[2]";
	String roleColumnObject = ".//*[@class='undefined']/tbody/tr/td[2]";
	String roleDropDownValueObject = "//div[@class='ui-multiselect-items-wrapper']/ul/li/label";
	String tableHeaderObject = "//div[@class='ui-datatable-tablewrapper']/table/thead/tr/th";
	String columnObject = "//div[@class='ui-datatable-tablewrapper']/table/thead/tr/th[%s]/span[@class='ui-column-title']";
	String tableBodyObject = "//div[@class='ui-datatable-tablewrapper']/table/tbody/tr";
	String tableContentObject = "//div[@class='ui-datatable-tablewrapper']/table/tbody/tr[%s]/td[%c]/span[@class='ui-cell-data']";
	String uiPaginatorNext = ".//*[@class='ui-paginator-next ui-paginator-element ui-state-default ui-corner-all']";
	String rowStatus = "(//td[4])[%s]";    
 
	
	/**
	 * Method Name: roleFilterRolecolumnCheck
	 * Purpose: To validate whether the ROLE Column values match with the value selected in Filter drop down.
	 * @throws Exception
	 */
	public void roleFilterRolecolumnCheck() throws Exception {
		WebDriver driver = DriverFactory.getDriver();
		String role = step.getValue(); 
		//Thread.sleep(1000);
		columnRoleFilter(driver, role);
		getElementByUsing("RoleSerachButton").click();
		validateSearchResultsInRoleColumn(driver, role);
	}

	/**
	 * Method Name: columnRoleFilter
	 * Purpose: Select the expected Role
	 * @param driver
	 * @param role
	 * @throws Exception
	 */
	public void columnRoleFilter(WebDriver driver, String role) throws Exception {
		String[] roleArray = role.split(";");
		
		Thread.sleep(1000); 
	 	getElementByUsing("Admin_RoleFilter").click();  

		// UNchecking the existing roles
		unCheckRole(driver);

		// Selecting the Roles in Role filter
		for (int roleIndex = 0; roleIndex < roleArray.length; roleIndex++) {
			if (roleArray[roleIndex].trim().equalsIgnoreCase("ALL")) {
				 
				
				System.out.println("TEST");
				driver.findElement(By.xpath("(//div[@class='ui-chkbox-box ui-widget ui-corner-all ui-state-default'])[1]")).click(); 
				break; 
			} else {
				clickOnRole(driver,roleArray[roleIndex]);
			}
		} 

	}
	/**
	 * Method Name: editLOUser
	 * @param driver
	 * @throws Exception
	 */
	public void editLOUser(WebDriver driver) throws Exception {
		String role = step.getDataValue("Roles");
		String[] roleArray = role.split(";");
		getElementByUsing("Admin_RoleFilter").click();
		// UNchecking the existing roles
		unCheckRole(driver);
		
		System.out.println(" uncheckd roles done "); 

		// Selecting the Roles in Role filter
		for (int roleIndex = 0; roleIndex < roleArray.length; roleIndex++) {
			if (roleArray[roleIndex].trim().equalsIgnoreCase("ALL") || roleArray[roleIndex].trim().equalsIgnoreCase("LO")) {
				clickOnRole(driver,roleArray[roleIndex]);
				getElementByUsing("Userrole_LastNameLabel").click();
				editLOUserNameMicrositeURL();
				//LOuser(roleArray);
			}
		}
	}

	/**
	 * Method Name: roleFilter
	 * @throws Exception
	 */
	public void roleFilter() throws Exception {

		WebDriver driver = DriverFactory.getDriver();
	//	editLOUser(driver);
		// String role = step.getValue();
		String role = step.getDataValue("Roles");
		 
		System.out.println( "role   is  =  " + role );
  
		String[] roleArray = role.split(";"); 
	//	getElementByUsing("Admin_RoleFilter").click(); 

		driver.findElement(By.xpath("//span[@class='ui-clickable fa fa-fw fa-caret-down']")).click();  


		// UNchecking the existing roles
		unCheckRole(driver);
		
		System.out.println( roleArray.length);  

		
		System.out.println(" try1 ");
		// Selecting the Roles in Role filter
		for (int roleIndex = 0; roleIndex < roleArray.length; roleIndex++) {

			if (roleArray[roleIndex].trim().equalsIgnoreCase("ALL")) {
				System.out.println("TEST");
				driver.findElement(By.xpath("(//div[@class='ui-chkbox-box ui-widget ui-corner-all ui-state-default'])[1]")).click(); 
				break;     
			} else {

				System.out.println(" clicking on " + roleArray[roleIndex] ); 
			
				clickOnRole(driver,roleArray[roleIndex]);
				if (roleArray[roleIndex].trim().equalsIgnoreCase("LO"))
				{
					System.out.println(" inside Lo ");
					
					getElementByUsing("editrole_UserName").sendKeys(step.getDataValue("UserName"));    
					getElementByUsing("Userrole_LastNameLabel").click();   
				}   
			}
		} 

		// Handling the Microsoft User name and URL for LO USER
		// getElementByUsing("Userrole_LastNameLabel").click();
		// editLOUser(driver);   

	}

	/**
	 * MethodName: roleFilterSorting
	 * Purpose: To validate whether roles are Sorted 
	 * @throws Exception
	 */
	public void roleFilterSorting() throws Exception {
		WebDriver driver = DriverFactory.getDriver();
		String revisedLabelObject = multiSelectRoleObject+"/label";
		List<WebElement> we = driver.findElements(By.xpath(revisedLabelObject));
		//String str1 = driver.findElement(By.xpath("//div[@class='ui-multiselect-items-wrapper']/ul/li")).getText();
		List<String> actual_role = new ArrayList<String>();
		List<String> Soted = new ArrayList<String>();
		
		for (int index = 0; index < we.size(); index++) {
			WebElement we1 = we.get(index);
			String s1 = we1.getText();
			actual_role.add(we1.getText());
		}

		Soted = actual_role;
		Collections.sort(Soted);


		boolean result = isTwoArrayListsWithSameValues(Soted, actual_role);
		if (!(result)) {
			addExceptionToReport("Verify whether values under Role Filter dropdown is properly sorted.", "Filter values are not sorted.",
					"Filter values should be sorted.");

		}

	}

	/**
	 * Method Name: editLOUserNameMicrositeURL
	 * Purpose: To edit LO User & Microsite URL
	 * @param roleArray
	 * @throws BiffException
	 * @throws IOException
	 * @throws TwfException
	 */
	public void editLOUserNameMicrositeURL() throws BiffException, IOException, TwfException {
		// LO User Specific
		/*for (int roleIndex = 0; roleIndex < roleArray.length; roleIndex++) {
			if (roleArray[roleIndex].contains("LO")) {
				getElementByUsing("editrole_UserName").sendKeys(step.getDataValue("UserName"));
				getElementByUsing("editrole_MicrositeURLLabel").click();

				editrole_MicrositeURL = getElementByUsing("editrole_MicrositeURL").getText();

				if (!(getElementByUsing("editrole_MicrositeURL").isEnabled())) {
					addExceptionToReport("editrole MicrositeURL  Text Box",
							"editrole MicrositeURL" + getElementByUsing("editrole_MicrositeURL").getText()
									+ " site should be read only field",
							"editrole MicrositeURL  " + getElementByUsing("editrole_MicrositeURL").getText()
									+ " site should be read only field");
				}

			}
		}*/
		getElementByUsing("editrole_UserName").sendKeys(step.getDataValue("UserName"));
		getElementByUsing("editrole_MicrositeURLLabel").click();

		editrole_MicrositeURL = getElementByUsing("editrole_MicrositeURL").getText();

		if (!(getElementByUsing("editrole_MicrositeURL").isEnabled())) {
			addExceptionToReport("editrole MicrositeURL  Text Box",
					"editrole MicrositeURL" + getElementByUsing("editrole_MicrositeURL").getText()
							+ " site should be read only field",
					"editrole MicrositeURL  " + getElementByUsing("editrole_MicrositeURL").getText()
							+ " site should be read only field");
		}

	}

	/**
	 * Method Name: getRoleArrayIndex
	 * @param driver
	 * @param expectedRole
	 * @throws BiffException
	 * @throws IOException
	 * @throws TwfException
	 */
	public void clickOnRole(WebDriver driver,String expectedRole) throws BiffException, IOException, TwfException {
		boolean found = false;
		int elementCount = driver.findElements(By.xpath(multiSelectRoleObject)).size();
		for (int elementIndex = 1; elementIndex <= elementCount; elementIndex++) {			
			if (driver.findElement(	By.xpath(dropDownRoleLabelObject.replace("%s", String.valueOf(elementIndex)))).getText().equalsIgnoreCase(expectedRole)) {
				
				driver.findElement(	By.xpath(dropDownRoleCheckBoxObject.replace("%s", String.valueOf(elementIndex)))).click();
				found = true;
				break;
			}
		}
		if(found == false){
			addExceptionToReport("Expected Role "+expectedRole+" is not displayed in drop downn.", "", "");
		}
	}

	/**
	 * Method Name: validateSearchResultsInRoleTable
	 * @param driver
	 * @param role
	 * @throws TwfException
	 * @throws InterruptedException
	 */
	public void validateSearchResultsInRoleColumn(WebDriver driver, String expectedRole) throws TwfException, InterruptedException {
		 Thread.sleep(4000);  
				 
	 	while (driver.findElements(By.xpath(uiPaginatorNext)).size() > 0) {
		 
			List<WebElement> list = driver.findElements(By.xpath(roleColumnObject)); 
			int rowIndex = 1;  
			for (WebElement webElement : list) {

				String text = webElement.getText();

				if (expectedRole.trim().contains("All")) {

					if (!(text.contains("Admin") || (text.contains("CSR") || (text.contains("LO"))))) {
						addExceptionToReport("Role Filter  ",
								"Actual Value " + text + "is displayed under Role Column in row number " + rowIndex,
								"Expected Value is   Admin or CSR or LO ");
					}

				} else if (!(text.contains(expectedRole.trim()))) {
					addExceptionToReport("Role Filter  ",
							"Actual Value " + text + "is displayed under Role Column in row number " + rowIndex,
							"Expected Value is " + expectedRole);
				}
				rowIndex++; 
			}

			driver.findElement(By.xpath(uiPaginatorNext)).click();
		}
	}
  
/*	*//**
	 * Method Name: searchRoleTable
	 * Purpose: To validate search table results
	 * @throws Exception
	 *//*
	public void searchRoleTable() throws Exception {

		String role = step.getValue();

		List<String> list = fetchDisplayedValuesUnderColumn("Role", "Test");
		for (String str : list) {
			if (!(str.contains(role))) {
				addExceptionToReport("Role Filter Admin ",
						"Admin value not availble in Role column for " + role + " Role Filter ",
						"Admin value should be availble in Role column for " + role + "  Role Filter ");
			}
		}
	}*/ 
 
	/**
	 * Method Name: validateTabelSort
	 * Purpose: To validate whether the values under the respective column are propely sorted
	 * @throws Exception
	 */

	public void validateTableSort() throws Exception { 
		
		System.out.println("   inside Admin "); 
		WebDriver driver = DriverFactory.getDriver();
		String column_name = step.getValue();
		List<String> actual = new ArrayList<String>();
		List<String> compare = new ArrayList<String>();

		
		while (driver.findElements(By.xpath(uiPaginatorNext)).size() > 0) {
			actual = fetchDisplayedValuesUnderColumn(column_name, "Test");
			compare.addAll(actual);
			driver.findElement(By.xpath(uiPaginatorNext)).click();
		}
		
		
		System.out.println(" try 1 ");

		actual = compare;
		System.out.println("actual     - " + actual);
		Collections.sort(compare);
		System.out.println(); 
		System.out.println("compare    - " + compare); 

	//	driver.findElement(By.xpath(uiPaginatorNext)).click();
		boolean result = isTwoArrayListsWithSameValues(compare, actual);    
		

		if (!(result)) {
			addExceptionToReport(" Coulmn - " + column_name + " is not sorted  ",
					"  Coulmn - " + column_name + " is not sorted   ",
					"   Coulmn - " + column_name + " should  be  sorted  ");

		}  
	}

	/**
	 * Method Name: isTwoArrayListsWithSameValues
	 * Purpose: To validate whether 2 arrays are with the same values
	 * @param list1
	 * @param list2
	 * @return
	 */
	public boolean isTwoArrayListsWithSameValues(List<String> list1, List<String> list2) {
		if ((list1 == null && list2 == null)) {

			return false;
		} else if ((list1 == null && list2 != null) || (list1 != null && list2 == null)) {
			return false;
		} else if (list1.size() != list2.size()) {

			return false;
		}

		for (int counter = 0; counter < list2.size(); counter++) {
			if (!(list1.get(counter).equals(list2.get(counter)))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Method Name: unCheckRole
	 * Purpose: To uncheck Roles which are already checked, just to ensure it is reset
	 * @param driver
	 * @throws Exception
	 */
	public void unCheckRole(WebDriver driver) throws Exception {

		int checked_role = driver.findElements(By.xpath(roleCheckBox)).size();
		
		System.out.println("checked_role  size  = " + checked_role); 
		if (checked_role > 0) {
			if (checked_role == 4) {
				driver.findElement(By.xpath(roleCheckBox)).click();
			} else {
				
				
				for (int checkedrole = checked_role; checkedrole > 0; checkedrole--) {
					System.out.println( "else for/////");
					driver.findElement(By.xpath(roleCheckBox)).click();  
				}
			} 
		}  
	}

	/**
	 * Method Name: fetchDisplayedTableColumnValues
	 * Purpose: to fetch the column names of the table
	 * @param expectedColumnName
	 * @param expectedColumnValue
	 * @return
	 * @throws Exception
	 */
	public List<String> fetchDisplayedValuesUnderColumn(String expectedColumnName, String expectedColumnValue)
			throws Exception {

		List<String> columnValuesList = new ArrayList<String>();
		HashMap<String, Integer> columnMap = new HashMap<String, Integer>();

		WebDriver driver = DriverFactory.getDriver();
		int columnCount = driver.findElements(By.xpath(tableHeaderObject)).size();

		for (int colIndex = 1; colIndex <= columnCount; colIndex++) {
			String displayedColName = driver.findElement(By.xpath(columnObject.replace("%s", String.valueOf(colIndex)))).getText();
			if (displayedColName.length() > 0) {
				columnMap.put(displayedColName, colIndex);
			}
		}

		columnValuesList = fetchAllColumnValues(tableBodyObject, columnMap.get(expectedColumnName));
		return columnValuesList;
	}

	public void verifyTableColumnValuesAfterFilterOperation(String expectedColumnName, String expectedColumnValue)
			throws Exception {

		String tableHeaderObject = "//div[@class='ui-datatable-tablewrapper']/table/thead/tr/th";
		String columnObject = "//div[@class='ui-datatable-tablewrapper']/table/thead/tr/th[%s]/span[@class='ui-column-title']";
		String tableBodyObject = "//div[@class='ui-datatable-tablewrapper']/table/tbody/tr";
		List<String> columnValuesList = new ArrayList<String>();
		HashMap<String, Integer> columnMap = new HashMap<String, Integer>();

		WebDriver driver = DriverFactory.getDriver();
		int columnCount = driver.findElements(By.xpath(tableHeaderObject)).size();

		for (int colIndex = 1; colIndex <= columnCount; colIndex++) {
			String displayedColName = driver.findElement(By.xpath(columnObject.replace("%s", String.valueOf(colIndex))))
					.getText();
			if (displayedColName.length() > 0) {
				columnMap.put(displayedColName, colIndex);
			}
		}

		columnValuesList = fetchAllColumnValues(tableBodyObject, columnMap.get(expectedColumnName));
	}

	/**
	 * Method Name: fetchAllColumnValues
	 * Purpose: To fetch all the values under a respective column
	 * @param tableBodyObject
	 * @param columnIndex
	 * @return
	 * @throws Exception
	 */
	public List<String> fetchAllColumnValues(String tableBodyObject, int columnIndex) throws Exception {
	
		List<String> columnValuesList = new ArrayList<String>();
		WebDriver driver = getWebDriver();

		int rowCount = driver.findElements(By.xpath(tableBodyObject)).size();

		for (int rowIndex = 1; rowIndex <= rowCount; rowIndex++) {
			String revisedTableBodyObject = tableContentObject.replace("%s", String.valueOf(rowIndex));
			String displayedColumnValue = driver
					.findElement(By.xpath(revisedTableBodyObject.replace("%c", String.valueOf(columnIndex)))).getText();
			columnValuesList.add(displayedColumnValue);
		}
		return columnValuesList;
	}

	/**
	 * Method Name: compareColumnValues
	 * Purpose: to compare column values
	 * @param displayedList
	 * @param expectedColumnValue
	 * @throws Exception
	 */
	public void compareColumnValues(List<String> displayedList, String expectedColumnValue) throws Exception {
		for (int colIndex = 0; colIndex < displayedList.size(); colIndex++) {
			if (displayedList.get(colIndex).trim().equalsIgnoreCase(expectedColumnValue.trim()) == false) {
				addExceptionToReport(
						"Expected Column Value is not matching with the Expected Value in row number " + colIndex + 1,
						displayedList.get(colIndex), expectedColumnValue.trim());
			}
		}
	}



	/**
	 * Method Name: activeInactiveUser
	 * Purpose: to activate a Inactive sUser
	 * @throws Exception
	 */
	
	public void activeInactiveUser() throws Exception {
		WebDriver driver = DriverFactory.getDriver();
		String Status = step.getDataValue("Status");

		System.out.println("Status is --> " + Status);

		if (Status.contains("Active")) { 

			if ((driver.findElements(By.xpath(".//*[@class='mat-slide-toggle ng-valid ng-touched ng-dirty']")) 
					.size() > 0))
				// getElementByUsing("Userrole_ActiveStatus").click();
				getElementByUsing("Userrole_InactiveStatus").click();

		} else {
			//  getElementByUsing("Userrole_InactiveStatus").click();  
			//   getElementByUsing("Userrole_ActiveStatus").click();
			
			System.out.println(" else ");
			//driver.findElement(By.xpath(".//mat-slide-toggle[@class='mat-slide-toggle ng-valid ng-touched ng-dirty mat-checked']/label/span")).click();; 
			
			driver.findElement(By.xpath("//span[contains(.,' Active')]")).click();   
			  
		}

	} 
	/**
	 * 
	 * @throws Exception
	 */
	public void statusCheck() throws Exception {
		WebDriver driver = DriverFactory.getDriver();
		String Status = step.getValue();
		List<String> actual_status = fetchDisplayedValuesUnderColumn("Status", "Test");

		for (String string : actual_status) {
			if (!(string.contains(Status))) {

				addExceptionToReport(" Status column value Check ", "Autual displayed status is" + actual_status + " ",
						"Expected status is" + Status + " ");
			}

		}
	}

	public void micrositeStatusCheck() throws Exception {
		WebDriver driver = DriverFactory.getDriver();
		String Status = step.getValue();
		List<String> actual_status = fetchDisplayedValuesUnderColumn("Microsite Status", "Test");

		for (String string : actual_status) {
			if (!(string.contains(Status))) {

				addExceptionToReport(" Microsite Status column value Check ",
						"Autual displayed Microsite status is" + actual_status + " ",
						"Expected Microsite status is" + Status + " ");
			}

		}
	}

	public void requestStatusCheck() throws Exception {
		WebDriver driver = DriverFactory.getDriver();
		String request = step.getValue();
		List<String> actual_request = fetchDisplayedValuesUnderColumn("Request", "Test");

		for (String string : actual_request) {
			if (!(string.contains(request))) {

				addExceptionToReport(" Request column value Check ", "Autual displayed Request is" + actual_request + " ", "Expected Request is" + request + " ");
			}

		}
	} 

	/**
	 * Method Name: editButtonClick
	 * Purpose: to click on Edit Button
	 * 
	 * @throws Exception
	 */
	public void editButtonClick() throws Exception {

		
		System.out.println( " inside  editButtonClick"); 
		WebDriver driver = DriverFactory.getDriver();
		String email1 = "csr@finx.com";
		String email2 = "lo@tavant.com";
		String email3 = "admin@tavant.com";
		String email4 = "administrator@finx.com"; 
		
		boolean flag=false;	
		Nikhil: 			
		while (driver.findElements(By.xpath(uiPaginatorNext)).size() > 0 && !flag) {    
			int index = 1;    
			List<String> email_ID = fetchDisplayedValuesUnderColumn("Email ID", "Test");
		//	List<String> Status = fetchDisplayedValuesUnderColumn("Status", "Test");
			    
			
			
		for (String string : email_ID) {
			String row_button = driver.findElement(By.xpath(assign_button.replace("%s", String.valueOf(string)))).getText();
			String row_status = driver.findElement(By.xpath(rowStatus.replace("%s", String.valueOf(index)))).getText();
			
			System.out.println( " Index = " + index   +   "   row_button =   " +  row_button  + "  row_status   = " + row_status    + "   string   =   "  +    string);  
					  
			boolean flag2 = (row_button.contentEquals("Edit"));  
			boolean flag3 = !(row_status.contentEquals("Pending"));    

			if (flag2  && flag3) { 
				if (!((string.equalsIgnoreCase(email1)) || (string.equalsIgnoreCase(email2))
						|| (string.equalsIgnoreCase(email3) || (string.equalsIgnoreCase(email4))))) {

				//	driver.findElement(By.xpath(editButton.replace("%s", String.valueOf(index)))).click();
					 driver.findElement(By.xpath(assign_button.replace("%s", String.valueOf(string)))).click();  
					 flag=true;					
					 break Nikhil;    
  
				}   
			/*	else  
				{ 
					addExceptionToReport("Non ediatable Email ID's" , "Given Email id  " +  string + " used for some other purpose", " Please give Email id  other then " + string);
				} */    
				  
			}    
			index ++;  
		}		
	
		driver.findElement(By.xpath(uiPaginatorNext)).click(); 
		}     
	
	}
	    
public void paginations() throws Exception {		
		
		driver = DriverFactory.getDriver();
		while (driver.findElements(By.xpath(".//*[@class='ui-paginator-next ui-paginator-element ui-state-default ui-corner-all']")).size() > 0) {
			driver.findElement(By.xpath(".//*[@class='ui-paginator-next ui-paginator-element ui-state-default ui-corner-all']")).click();
		}       
	}
 
   
    
 
	@Override
	public void checkPage() {
		// TODO Auto-generated method stub

	}

}

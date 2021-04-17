package com.finx.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.tavant.base.DriverFactory;
import com.tavant.kwutils.CustomStep;
import com.tavant.kwutils.KWVariables;
import com.tavant.utils.TwfException;

public class Dashboard extends CustomStep {

	String actCardAppType = "//div[contains(text(),'%LN')]/ancestor::div[1]/ancestor::div[1]/following-sibling::div/div/span";
	String actCardDetailsElements = "//div[contains(text(),'%LN')]/ancestor::div[1]/ancestor::div[2]//div[@class='loan-label-section'][1]";
	// div[contains(text(),'')]/ancestor::div[1]/ancestor::div[1]/following-sibling::div//div[@class='loan-label-section'][1]
	// %s is to be replaced for following div from div containing Loan Number
	// (3-6 range)
	String actCardAppLabel = "(//div[contains(text(),'%LN')]/ancestor::div[1]/ancestor::div[1]/following-sibling::div//div[@class='loan-label-section'][1])[%s]";
	String actCardAppLabelValue = "(//div[contains(text(),'%LN')]/ancestor::div[1]/ancestor::div[1]/following-sibling::div//div[@class='loan-value-section'][1])[%s]";

	// Buttons or link for the particular loan status card
	String actCardResumeButton = "//div[contains(text(),'%LN')]/ancestor::div[1]/ancestor::div[1]/following-sibling::div//button[contains(text(),'Resume')]";
	String actCardViewDetailsLink = "//div[contains(text(),'%LN')]/ancestor::div[1]/ancestor::div[1]/following-sibling::div//a[@class='details-link']";
	String actualCard = "//div[@class='loan-number' and contains(text(),'%Loan Number')]";
	String LN_status1 = null;
	Boolean loancreatedEncompass = false;
	static String moduleValue = null;
	Util util = new Util();
//	Ten0Three ten=new Ten0Three();
	//WebDriver driver;
	/**
	 * Method Name: validateLandingPageDashboard PurposE: 1) call method to To
	 * collect details of a application (In-progress) or (Submitted) from
	 * dashboard landing page 2) call method to Validate journey status on Loan
	 * details 3) call method to Validate the Loan Summary details
	 *
	 * @param EnquiryID
	 *            from Application
	 * @throws Exception
	 */

	public void validateLandingPageDashboard(String userdetails) throws Exception {

		System.out.println("inside validateLandingPageDashboard ");
		String expuserdetails = KWVariables.getVariables().get(userdetails);
		System.out.println("expuserdetails---> " + expuserdetails);
		String loanNumber = null, Status = null;

		WebDriver driver = DriverFactory.getDriver();
		JavascriptExecutor je = (JavascriptExecutor) driver;
		// String LN_status[] = fetchInquiryIDfromDB(expuserdetails).split("-");
		// loanNumber = LN_status[0].trim();
		// Status = LN_status[1].trim().toLowerCase();
		//
		// System.out.println("Loan nUmber>>>"+loanNumber);
		// System.out.println("Loan status>>>"+Status);

		if (LN_status1 == null) {
			String LN_status[] = fetchInquiryIDfromDB(expuserdetails).split("-");
			loanNumber = LN_status[0].trim();
			Status = LN_status[1].trim().toLowerCase();
		} else {
			String LN_status[] = LN_status1.split("-");
			System.out.println("LN_status ---> " + LN_status);
			loanNumber = LN_status[0].trim();
			Status = LN_status[1].trim().toLowerCase();
		}

		int num = driver.findElements(By.xpath(actCardDetailsElements.replace("%LN", loanNumber))).size();

		if (num == 0)
			getElementByUsing("Go_To_Dashboard_button1111188888").click();

		switch (Status) {
		case "inprogress":
			getElementByUsing("Dashboard_Inprogress_Tab").isDisplayed();
			getElementByUsing("Dashboard_Inprogress_Tab").click();
			break;
		case "submitted":
			getElementByUsing("Dashboard_Submitted_Tab").isDisplayed();
			getElementByUsing("Dashboard_Submitted_Tab").click();
			break;
		case "rateQuote":
			getElementByUsing("Dashboard_RateQuote_Tab").isDisplayed();
			getElementByUsing("Dashboard_RateQuote_Tab1").click();
			break;
		}
		System.out.println("LoanNumber now is111---> " + loanNumber);

		// To collect Dashboard loan details
		HashMap<String, String> dashboardCardDetails = collectAppDashboardDetails(loanNumber);

		je.executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath(actualCard.replace("%Loan Number", String.valueOf(loanNumber)))));

		// To compare Dashboard Landing page details
		createExpectedDataMapDashboard(dashboardCardDetails, expuserdetails, Status, loanNumber);

		// driver.findElement(By.xpath(actCardViewDetailsLink.replace("%LN",
		// loanNumber))).click();

		// Commented as currently not in scope of Fairway
		// To validate Journey status
		// validateApplicationExistingUser(dashboardCardDetails);

		// To verify the Resume button functionality
		if (Status.equalsIgnoreCase("inprogress")) {
			System.out.println("test1");
			getElementByUsing("Go_To_Dashboard_button").click();
			Thread.sleep(2000);
			System.out.println("test2");
			driver.findElement(By.xpath(actCardResumeButton.replace("%LN", loanNumber))).click();
			System.out.println("test3");
			getElementByUsing("1003_GetStarted_header").isDisplayed();
			System.out.println("test4");
			Thread.sleep(2000);

		}
	}

	/**
	 * Method Name: collectAppDashboardDetails PurposE: To collect details of a
	 * application (In-progress) or (Submitted) from dashboard landing page
	 *
	 * @param none
	 * @throws Exception
	 */

	public HashMap<String, String> collectAppDashboardDetails(String loanNumber) throws Exception {

		System.out.println("inside collectAppDashboardDetails ");
		WebDriver driver = DriverFactory.getDriver();
		JavascriptExecutor je = (JavascriptExecutor) driver;
		// Data of particular loan status card

		je.executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath(actualCard.replace("%Loan Number", String.valueOf(loanNumber)))));
		System.out.println("found the card");
		HashMap<String, String> expCardDetails = new HashMap<String, String>();

		expCardDetails.put("Loan #", loanNumber);
		// expCardDetails.put("Application",
		// driver.findElement(By.xpath(actCardAppType.replace("%LN",
		// String.valueOf(loanNumber)))).getText());
		System.out.println("Card details --->" + expCardDetails);

		int countOfDetails = driver.findElements(By.xpath(actCardDetailsElements.replace("%LN", loanNumber))).size();
		System.out.println("countOfDetails--->" + countOfDetails);
		int i = 0;
		while (i < countOfDetails) {
			String labelName = driver
					.findElement(
							By.xpath(actCardAppLabel.replace("%LN", loanNumber).replace("%s", String.valueOf(i + 1))))
					.getText();
			String labelValue = driver
					.findElement(By.xpath(
							actCardAppLabelValue.replace("%LN", loanNumber).replace("%s", String.valueOf(i + 1))))
					.getText();
			expCardDetails.put(labelName, labelValue);
			i = i + 1;
		}

		System.out.println(expCardDetails);
		return expCardDetails;

	}

	/**
	 * Method Name: validateApplicationExistingUser PurposE: To validate
	 * Prequal/1003 application dashboard details for existing user for
	 * applications
	 *
	 * @param hashmap
	 *            with the Loan details from Dashboard landing page
	 * @throws Exception
	 */
	public void validateApplicationExistingUser(HashMap<String, String> dashboardDetails) throws Exception {

		System.out.println("entered method validateApplicationExistingUser***********");

		String appType = dashboardDetails.get("Application").trim();
		String appStatus = dashboardDetails.get("Status").trim();

		if (appStatus.trim().equalsIgnoreCase("Inprogress".trim())) {
			if (appType.trim().equalsIgnoreCase("Full Application".trim()))
				inProgressApplication("1003");
			else
				inProgressApplication("prequal");

		} else {
			if (appType.trim().equalsIgnoreCase("Full Application".trim()))
				SubmittedApplication("1003");
			else
				SubmittedApplication("prequal");
		}

	}

	/**
	 * Method Name: validateLoanSummaryDetails PurposE: To validate Loan Summary
	 * high leveldetails for existing user for applications from Loan details -
	 * Dashboard
	 *
	 * @param expected
	 *            hashmap from the application Get Started data for comparison
	 * @throws Exception
	 */

	public void validateLoanSummaryDetails() throws Exception {
		System.out.println("Inside validateLoanSummaryDetails ");
		WebDriver driver = DriverFactory.getDriver();
		String lsHeadersXpath = "//div[@class='loan-summary-container']/div/div[%s]/div[@class='loan-list']/div[1]";
		String lsValuesXpath = "//div[@class='loan-summary-container']/div/div[%s]/div[@class='loan-list']/div[2]";
		String lsList = "//div[@class='loan-summary-container']/div/div/div/div[1]";

		HashMap<String, String> loanSummarydetails = new HashMap<String, String>();

		getElementByUsing("Loan_Summary_Header").isDisplayed();
		int loanSummaryCount = driver.findElements(By.xpath(lsList)).size();

		for (int i = 0; i < (loanSummaryCount - 1); i++) {
			String lsFieldHeader = driver.findElement(By.xpath(lsHeadersXpath.replace("%s", String.valueOf(i + 1))))
					.getText();
			System.out.println(" Loan Summary Field Header " + lsFieldHeader);

			String lsFieldValue = driver.findElement(By.xpath(lsValuesXpath.replace("%s", String.valueOf(i + 1))))
					.getText();
			System.out.println(" Loan Summary Field Header " + lsFieldValue);
			loanSummarydetails.put(lsFieldHeader, lsFieldValue);
		}

		System.out.println("Loan Summary ---> " + loanSummarydetails);
		getElementByUsing("Loan_Summary_View_details").click();
		fetchLoanSummaryWithPreview();

	}

	/**
	 * Method Name: fetchLoanSummaryWithPreview PurposE: To fetch Loan Summary
	 * View details (All details) for existing user for applications from Loan
	 * details - Dashboard applications
	 *
	 * @param none
	 * @throws Exception
	 */

	public void fetchLoanSummaryWithPreview() throws Exception {
		System.out.println("Inside fetchLoanSummaryWithPreview ");
		WebDriver driver = DriverFactory.getDriver();
		String sectionHeaderXpath = ".//*[@id='preview-prequal-main'][%s]/div/div[1]/div/div/h3";
		String sectionInfoPanelXpath = "/ancestor::div[@class='header']/following-sibling::div[@class='question-main-section']//div[@class='inner-detail-sect']";
		String applicantNameXpath = "/ancestor::div[@class='header']/following-sibling::div//div[@class='applicant-name']";
		String allFieldNameXpath = "//ancestor::div[@class='header']/following-sibling::div//div[@class='field-section']/div[1]";
		String fieldNameXpath = "//ancestor::div[@class='header']/following-sibling::div//div[%fieldcount]/div[@class='field-section']/div[1]";
		String fieldValueXpath = "//ancestor::div[@class='header']/following-sibling::div//div[%fieldcount]/div[@class='field-section']/div[1]/following-sibling::div";

		int sectionHeaderSize = driver.findElements(By.xpath(sectionHeaderXpath.replace("[%s]", ""))).size();
		String[] sectionHeader = new String[sectionHeaderSize];

		for (int i = 0; i < sectionHeaderSize; i++) {
			HashMap<String, String> sectionDetails = new HashMap<>();
			// To make a common xpath for a particular Section
			String sectionXpath = sectionHeaderXpath.replace("%s", String.valueOf(i + 1));

			sectionHeader[i] = driver.findElement(By.xpath(sectionXpath)).getText();

			// Add section name to Hashmap
			sectionDetails.put("Section", driver.findElement(By.xpath(sectionXpath)).getText());
			System.out.println("Section Header ---> " + driver.findElement(By.xpath(sectionXpath)).getText());

			int sectionDetailsPresentCount = driver.findElements(By.xpath(sectionXpath.concat(sectionInfoPanelXpath)))
					.size();

			if (sectionDetailsPresentCount != 0) {
				System.out.println("Section details Present");
				// Add appplicant name to Hashmap
				sectionDetails.put("Applicant",
						driver.findElement(By.xpath(sectionXpath.concat(applicantNameXpath))).getText());
				// System.out.println("Applicant Name - "+ i +" ---> "+
				// sectionDetails);

				// To make a common xpath for all fields in a particular Section
				int noOfFieldsInSection = driver.findElements(By.xpath(sectionXpath.concat(allFieldNameXpath))).size();
				for (int j = 0; j < noOfFieldsInSection; j++) {
					String fieldName = driver
							.findElement(By.xpath(
									sectionXpath.concat(fieldNameXpath.replace("%fieldcount", String.valueOf(j + 1)))))
							.getText();
					String fieldValue = driver
							.findElement(By.xpath(
									sectionXpath.concat(fieldValueXpath.replace("%fieldcount", String.valueOf(j + 1)))))
							.getText();
					sectionDetails.put(fieldName, fieldValue);
					// System.out.println(" Actual Hashmap" + i+1 + " is --> "+
					// sectionDetails);
				}

				switch (sectionDetails.get("Section").trim()) {
				case "Get Started":
					HashMap<String, String> GetStartedLoanSummaryDetails = new HashMap<>();
					GetStartedLoanSummaryDetails.putAll(sectionDetails);
					sectionDetails.clear();
					System.out.println(" Actual GetStartedLoanSummaryDetails is --> " + GetStartedLoanSummaryDetails);
					compareDashboardLoanDetailsPage(GetStartedLoanSummaryDetails);
					break;
				case "Property":
					HashMap<String, String> PropertyLoanSummaryDetails = new HashMap<>();
					PropertyLoanSummaryDetails.putAll(sectionDetails);
					sectionDetails.clear();
					System.out.println(" Actual PropertyLoanSummaryDetails is --> " + PropertyLoanSummaryDetails);
					compareDashboardLoanDetailsPage(PropertyLoanSummaryDetails);
					break;
				case "Income":
					HashMap<String, String> IncomeLoanSummaryDetails = new HashMap<>();
					IncomeLoanSummaryDetails.putAll(sectionDetails);
					sectionDetails.clear();
					System.out.println(" Actual IncomeLoanSummaryDetails is --> " + IncomeLoanSummaryDetails);
					compareDashboardLoanDetailsPage(IncomeLoanSummaryDetails);
					break;
				case "Assets":
					HashMap<String, String> AssetsLoanSummaryDetails = new HashMap<>();
					AssetsLoanSummaryDetails.putAll(sectionDetails);
					sectionDetails.clear();
					System.out.println(" Actual AssetsLoanSummaryDetails is --> " + AssetsLoanSummaryDetails);
					compareDashboardLoanDetailsPage(AssetsLoanSummaryDetails);
					break;
				case "Liabilities":
					HashMap<String, String> LiabilitiesLoanSummaryDetails = new HashMap<>();
					LiabilitiesLoanSummaryDetails.putAll(sectionDetails);
					sectionDetails.clear();
					System.out.println(" Actual LiabilitiesLoanSummaryDetails is --> " + LiabilitiesLoanSummaryDetails);
					break;
				case "Real Estate":
					HashMap<String, String> RealEstateLoanSummaryDetails = new HashMap<>();
					RealEstateLoanSummaryDetails.putAll(sectionDetails);
					sectionDetails.clear();
					System.out.println(" Actual RealEstateLoanSummaryDetails is --> " + RealEstateLoanSummaryDetails);
					break;
				case "Declarations":
					HashMap<String, String> DeclarationsLoanSummaryDetails = new HashMap<>();
					DeclarationsLoanSummaryDetails.putAll(sectionDetails);
					sectionDetails.clear();
					System.out
							.println(" Actual DeclarationsLoanSummaryDetails is --> " + DeclarationsLoanSummaryDetails);
					break;
				}

			} else
				System.out.println("Section details Not Present");

		}

		// To verify the Back to Loan details option
		getElementByUsing("Loan_Summary_Back_To_Loan_Details").click();
		getElementByUsing("Go_To_Dashboard_button").click();

	}

	/**
	 * Method Name: compareDashboardLoanDetailsPage PurposE: To compare Loan
	 * Summary View details (All details) for existing user for applications
	 * from Loan details - Dashboard applications
	 *
	 * @param expected
	 *            hashmap from the application Preview screen for comparison
	 * @throws Exception
	 */

	public void compareDashboardLoanDetailsPage(HashMap<String, String> actDashboardLoanDetails) throws Exception {
		System.out.println("inside compareDashboardLoanDetailsPage ");
		Preview1003 previewData = new Preview1003();

		System.out.println("Data from preview" + previewData.fieldValueGetStartedSection.toString());

		HashMap<String, String> expDashboardLoanDetails = new HashMap<>();
		switch (actDashboardLoanDetails.get("Section").trim()) {
		case "Get Started":
			System.out.println("get Started Section comparison on preview");
			expDashboardLoanDetails.putAll(previewData.fieldValueGetStartedSection);
			System.out.println("Expected Data ---> " + expDashboardLoanDetails);

			for (String k : expDashboardLoanDetails.keySet()) {
				if ((actDashboardLoanDetails.get(k).equalsIgnoreCase(expDashboardLoanDetails.get(k))) == false)
					addExceptionToReport("Value for " + actDashboardLoanDetails.get(k) + "not matching",
							actDashboardLoanDetails.get(k), expDashboardLoanDetails.get(k));
			}
			break;

		case "Property":
			System.out.println("Property Section comparison on preview");
			expDashboardLoanDetails.putAll(previewData.fieldValuePropertySection);
			for (String k : expDashboardLoanDetails.keySet()) {
				if ((actDashboardLoanDetails.get(k).equalsIgnoreCase(expDashboardLoanDetails.get(k))) == false)
					addExceptionToReport("Value for " + actDashboardLoanDetails.get(k) + "not matching",
							actDashboardLoanDetails.get(k), expDashboardLoanDetails.get(k));
			}
			break;
		case "Income":
			System.out.println("Income Section comparison on preview");
			expDashboardLoanDetails.putAll(previewData.fieldValueIncomeSection);
			for (String k : expDashboardLoanDetails.keySet()) {
				if ((actDashboardLoanDetails.get(k).equalsIgnoreCase(expDashboardLoanDetails.get(k))) == false)
					addExceptionToReport("Value for " + actDashboardLoanDetails.get(k) + "not matching",
							actDashboardLoanDetails.get(k), expDashboardLoanDetails.get(k));
			}
			break;
		case "Assets":
			System.out.println("Assets Section comparison on preview");
			expDashboardLoanDetails.putAll(previewData.fieldValueAssetsSection);
			for (String k : expDashboardLoanDetails.keySet()) {
				if ((actDashboardLoanDetails.get(k).equalsIgnoreCase(expDashboardLoanDetails.get(k))) == false)
					addExceptionToReport("Value for " + actDashboardLoanDetails.get(k) + "not matching",
							actDashboardLoanDetails.get(k), expDashboardLoanDetails.get(k));
			}
			break;
		case "Liabilities":
			System.out.println("Liabilities Section comparison on preview");
			expDashboardLoanDetails.putAll(previewData.fieldValueLiabilitySection);
			for (String k : expDashboardLoanDetails.keySet()) {
				if ((actDashboardLoanDetails.get(k).equalsIgnoreCase(expDashboardLoanDetails.get(k))) == false)
					addExceptionToReport("Value for " + actDashboardLoanDetails.get(k) + "not matching",
							actDashboardLoanDetails.get(k), expDashboardLoanDetails.get(k));
			}
			break;
		case "Real Estate":
			System.out.println("Real Estate Section comparison on preview");
			expDashboardLoanDetails.putAll(previewData.fieldValueRealEstateSection);
			for (String k : expDashboardLoanDetails.keySet()) {
				if ((actDashboardLoanDetails.get(k).equalsIgnoreCase(expDashboardLoanDetails.get(k))) == false)
					addExceptionToReport("Value for " + actDashboardLoanDetails.get(k) + "not matching",
							actDashboardLoanDetails.get(k), expDashboardLoanDetails.get(k));
			}
			break;
		case "Declarations":
			System.out.println("Declarations Section comparison on preview");
			expDashboardLoanDetails.putAll(previewData.fieldValueDeclarationSection);
			for (String k : expDashboardLoanDetails.keySet()) {
				if ((actDashboardLoanDetails.get(k).equalsIgnoreCase(expDashboardLoanDetails.get(k))) == false)
					addExceptionToReport("Value for " + actDashboardLoanDetails.get(k) + "not matching",
							actDashboardLoanDetails.get(k), expDashboardLoanDetails.get(k));
			}
			break;
		}

	}

	/**
	 * Method Name: SubmittedApplication PurposE: To validate Prequal/1003
	 * application details for user with application Inprogress only
	 *
	 * @param status
	 *            of the application
	 * @throws Exception
	 */

	public void inProgressApplication(String module) throws Exception {
		if (module.trim().equalsIgnoreCase("1003")) {
			System.out.println("Full Application - Dashboard Loan details page displayed");
			validateYourJourney("Full Application in progress");
		} else {
			System.out.println("Pre-qual - Dashboard Loan details page displayed");
			validateYourJourney("Pre-qualification in progress");
		}
	}

	/**
	 * Method Name: SubmittedApplication PurposE: To validate Prequal/1003
	 * application details for user with application Submitted only
	 *
	 * @param status
	 *            of the application
	 * @throws Exception
	 */
	public void SubmittedApplication(String module) throws Exception {

		if (module.trim().equalsIgnoreCase("1003".trim())) {
			System.out.println("Full Application - Dashboard Loan details page displayed");
			validateYourJourney("Full Application Submitted");
		} else {
			System.out.println("Pre-qual - Dashboard Loan details page displayed");
			validateYourJourney("Pre-qualification Submitted");
		}
	}

	/**
	 * Method Name: validateInProgressOneApplicationNewUser PurposE: To validate
	 * Prequal/1003 application dashboard details for new user with one
	 * application (Inprogress/Submitted) only
	 *
	 * @param status
	 *            of the application, module name
	 * @throws Exception
	 */
	public void validateOneApplicationNewUser() throws Exception {

		System.out.println("entered method validateInProgressApplicationNewUser***********");
		try {
			String appStatus = step.getDataValue("AppStatusDashboard");
			System.out.println("appStatus ---> " + appStatus);

			String appType = step.getDataValue("Module");
			System.out.println("appType ---> " + appType);

			getElementByUsing("Loan_details_Hi_User").isDisplayed();
			getElementByUsing("Details_Page_Header").isDisplayed();

			if (appStatus.trim().equalsIgnoreCase("Inprogress".trim())) {
				System.out.println("Appstatus matched to In progress");
				if (appType.trim().equalsIgnoreCase("1003".trim()))
					inProgressApplication("1003");
				else
					inProgressApplication("prequal");

			} else {
				System.out.println("Appstatus matched to Submitted");
				if (appType.trim().equalsIgnoreCase("1003".trim()))
					SubmittedApplication("1003");
				else
					SubmittedApplication("prequal");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Method Name: validateYourJourney PurposE: To validate Prequal/1003
	 * application loan details - journey status
	 *
	 * @param none
	 * @throws Exception
	 */
	public void validateYourJourney(String expectedAppStatus) throws Exception {

		System.out.println("entered method validateYourJourney***********");

		WebDriver driver = DriverFactory.getDriver();
		int milestonesCountInActive = 0, milestonesCountActive = 0, totalMilestonesCount = 0;

		String ActualJourneyStatusXpathActive = "//div[@class='stepper-outer']//div[@class='steppers completed active-proccessing']/div[2]/div[1]";
		String ActualJourneyStatusXpathAllInactive = "//div[@class='stepper-outer']//div[@class='steppers']/div[2]/div[1]";
		String ActualJourneyStatusXpathInactive = "//div[@class='stepper-outer']//div[@class='steppers'][%s]/div[2]/div[1]";

		milestonesCountInActive = driver.findElements(By.xpath(ActualJourneyStatusXpathAllInactive)).size();
		System.out.println("milestonesCount Inactive ------>" + milestonesCountInActive);

		milestonesCountActive = driver.findElements(By.xpath(ActualJourneyStatusXpathActive)).size();
		System.out.println("milestonesCount Active------->" + milestonesCountActive);

		totalMilestonesCount = milestonesCountActive + milestonesCountInActive;
		System.out.println("Total journey status count *******" + totalMilestonesCount);

		// Declaring prequal journey status string size with run time milestone
		// count
		String[] JourneyStatuses = new String[totalMilestonesCount];

		for (int i = 1; i < totalMilestonesCount; i++) {
			JourneyStatuses[i] = driver
					.findElement(By.xpath(ActualJourneyStatusXpathInactive.replace("%s", String.valueOf(i)))).getText();
			System.out.println("JourneyStatuses ----> " + JourneyStatuses[i]);
		}
		String actualJourneyStatusActive = driver.findElement(By.xpath(ActualJourneyStatusXpathActive)).getText();
		System.out.println("actualJourneyStatusActive ---> " + actualJourneyStatusActive);
		System.out.println("expectedAppStatus ---> " + expectedAppStatus);

		if (actualJourneyStatusActive.trim().contains(expectedAppStatus.trim()) == false) {
			addExceptionToReport("Journey Status is not Active", actualJourneyStatusActive, expectedAppStatus);
		}

	}

	/**
	 * Method Name: renameUserQuoteinforecord PurposE: To map existing
	 * Prequal/1003 detail records from quoteinfo to other existing user
	 *
	 * @param user
	 *            details
	 * @throws Exception
	 */

	public void renameUserQuoteinforecord(String userDetails) throws Exception, InvalidFormatException, IOException {
		System.out.println("****entered renameUserQuoteinforecord****");
		String expectedUserdetails = KWVariables.getVariables().get(userDetails);
		System.out.println("expectedUserdetails ----> " + expectedUserdetails);
		String[] data = expectedUserdetails.split("-");
		String userId = data[1];
		String DB_url = data[0];
		System.out.println("userId ---> " + userId);
		System.out.println("DB_url ---> " + DB_url);
		String dummy_userId = "akansha.jain@tavant.com";
		String DB_collectionName = "prequal";

		MongoClient mongoclient = new MongoClient(DB_url, 27017);
		@SuppressWarnings("deprecation")
		DB db = mongoclient.getDB(DB_collectionName);
		Set<String> Collections = db.getCollectionNames();
		for (String collectionNames : Collections)
			System.out.println("Collection ---> " + collectionNames);

		DBCollection dbc = db.getCollection("quoteinfo");

		BasicDBObject query = new BasicDBObject();
		BasicDBObject query1 = new BasicDBObject();
		query.put("userId", userId);
		query1.put("userId", dummy_userId);

		DBCursor cursor = dbc.find(query);
		DBCursor cursor1 = cursor;

		System.out.println(
				" Number of applications found in quoteinfo for the user " + userId + "---> " + cursor.count());

		while (cursor1.hasNext()) {
			System.out.println(cursor1.next());
		}

		if (cursor.count() != 0) {
			System.out.println("cursor.count() --> " + cursor.count());
			// dbc.findAndModify(query, null, null, false, new
			// BasicDBObject().append("$set",query1), true, false);
			dbc.update(query, new BasicDBObject().append("$set", query1), false, true);
			System.out.println("**** Modified ****");
		} else {
			System.out.println("No records in quoteinfo for " + userId);
		}

		cursor.close();
		cursor1.close();

		mongoclient.close();
	}

	/**
	 * Method Name: createExpectedDataMapDashboard PurposE: To create expected
	 * data map from DB for Dashboard landing page
	 *
	 * @param dashboard
	 *            card details containing Loan number & user details
	 * @throws Exception
	 */

	@SuppressWarnings("deprecation")
	public void createExpectedDataMapDashboard(HashMap<String, String> dashboardCardDetails, String expectedUserdetails,
			String Status, String loanNumber1) throws Exception {
		System.out.println("inside createExpectedDataMapDashboard ");
		try {
			System.out.println("User details ---> " + expectedUserdetails);
			String[] data = expectedUserdetails.split("-");
			// String user = data[1];
			// String Db_url = data[0];
			String user = expectedUserdetails;
			String Db_url = KWVariables.getVariables().get("DBUrl");
			String DB_collection = "prequal";
			MongoClient mclient = new MongoClient(Db_url, 27017);

			DB db = mclient.getDB(DB_collection);
			DBCollection dbc = db.getCollection("quoteinfo");

			BasicDBObject query = new BasicDBObject();
			BasicDBObject query1 = new BasicDBObject();
			// String loanNumber1 = dashboardCardDetails.get("Loan #");
			System.out.println("loanNumber1  --->  " + loanNumber1);
			// String status = dashboardCardDetails.get("Status");

			System.out.println("Status  ---> " + Status);

			query1.put("userId", user);
			System.out.println("loancreatedEncompass value is --> " + loancreatedEncompass.booleanValue());

			if (loancreatedEncompass.booleanValue() == true)
				query.put("loanNumber", loanNumber1);
			else
				query.put("referenceId", loanNumber1);

			System.out.println("query ---> " + query.toString());
			DBCursor dbcr = dbc.find(query);
			DBObject record = dbcr.next();

			System.out.println("record ---> " + record);
			HashMap<String, String> expectedDataMap = new HashMap<>();

			expectedDataMap.put("Loan #", loanNumber1);

			// To fetch purpose value from DB document
			if (!record.containsField("purpose")) {
				expectedDataMap.put("Loan Purpose", "-");
			} else {
				String loanPurposeValue = record.get("purpose").toString().trim();
				System.out.println("loanPurposeValue ---> " + loanPurposeValue);
				if (loanPurposeValue.contains("Buy a new home"))
					expectedDataMap.put("Loan Purpose", "Purchase");
				else
					expectedDataMap.put("Loan Purpose", "Refinance");
			}
			// To fetch Applicant from DB document

			BasicDBObject prequalData1 = (BasicDBObject) record.get("preQual");
			BasicDBObject prequalData2 = (BasicDBObject) prequalData1.get("personalDetails");

			String firstname = prequalData2.get("firstName").toString();
			String secondname = prequalData2.get("lastName").toString();

			expectedDataMap.put("Applicant", firstname.concat(" ").concat(secondname));

			// To fetch Status from document

			if (Status.equalsIgnoreCase("PARTIAL"))
				expectedDataMap.put("Status", "Inprogress");

			else
				expectedDataMap.put("Status", "Submitted");
			System.out.println(expectedDataMap);

			// To fetch Loan Amount from DB document
			BasicDBObject prequalData = (BasicDBObject) record.get("preQual");

			if (record.get("journey").toString().trim().equalsIgnoreCase("Prequal"))
				expectedDataMap.put("Application", "Short Application");
			else if (moduleValue.trim().equalsIgnoreCase("1003"))
				expectedDataMap.put("Application", "Full Application");

			if ((dashboardCardDetails.get("Application").equalsIgnoreCase("Full Application"))
					&& (dashboardCardDetails.get("Status").equalsIgnoreCase("Inprogress")))
				expectedDataMap.put("Loan Amount", prequalData.get("loanAmount").toString());

			System.out.println("Map for Loan Summary gist ---> " + expectedDataMap);

			compareDashboardLandingPageDetails(dashboardCardDetails, expectedDataMap);
			loancreatedEncompass = false;
			mclient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void compareDashboardLandingPageDetails(HashMap<String, String> actDashboardCardDetails,
			HashMap<String, String> expDashboardCardDetails) throws Exception {

		System.out.println("inside compareDashboardLandingPageDetails ");
		for (String k : actDashboardCardDetails.keySet()) {
			System.out.println("Key is --> " + k);
			System.out.println("expDashboardCardDetails ---> " + expDashboardCardDetails.get(k));
			System.out.println("actDashboardCardDetails ---> " + actDashboardCardDetails.get(k));
			if ((expDashboardCardDetails.get(k).equalsIgnoreCase(actDashboardCardDetails.get(k))) == false) {
				addExceptionToReport("Value for " + actDashboardCardDetails.get(k) + "not matching",
						actDashboardCardDetails.get(k), expDashboardCardDetails.get(k));
			}
		}

	}

	/**
	 * Method Name: fetchInquiryID PurposE: To create expected data map from DB
	 * for Dashboard landing page
	 *
	 * @param dashboard
	 *            card details containing Loan number & user details
	 * @throws Exception
	 */

	@SuppressWarnings("deprecation")
	public String fetchInquiryIDfromDB(String expectedUserdetails) throws Exception {
		System.out.println("inside fetchInquiryIDfromDB ");

		String data[] = expectedUserdetails.split("-");
		// String user = data[1];
		// String Db_url = data[0];
		String user = expectedUserdetails;
		String Db_url = KWVariables.getVariables().get("DBUrl");
		System.out.println("inside fetchInquiryIDfromDB Values " + user + "   " + Db_url);
		MongoCredential credential = MongoCredential.createCredential("frwy_finexpprequal_app_owner",
				"frwy_finexpprequal", "FrWyn3xql#xp3r3".toCharArray());
		MongoClient mongoClient = new MongoClient(new ServerAddress("10.210.65.117", 19902), Arrays.asList(credential));
		// MongoDatabase db = mongoClient.getDatabase( "frwy_finexusermgmt" );
		// MongoClient mc = new MongoClient(Db_url, 27017);

		@SuppressWarnings("deprecation")
		DB db = mongoClient.getDB("frwy_finexpprequal");
		DBCollection dbc = db.getCollection("quoteinfo");

		BasicDBObject query1 = new BasicDBObject();
		BasicDBObject query2 = new BasicDBObject();
		query1.put("userId", user);
		query2.put("createdDate", -1);

		// query2.put("createdDate",);
		DBCursor dbcsr = dbc.find(query1).sort(query2);
		BasicDBObject record = (BasicDBObject) dbcsr.next();
		System.out.println("Record value>>>>>" + record);
		// System.out.println(" record is ---> "+ record);

		String loanNumber1 = null;
		if (record.containsKey("loanNumber") && record.get("loanNumber") != null) {
			String data2 = record.get("loanNumber").toString();
			System.out.println("data2 ---> " + data2);

			if (!data2.isEmpty()) {
				loanNumber1 = record.get("loanNumber").toString();
				loancreatedEncompass = true;
			}
		} else {
			System.out.println("In else block");
			loanNumber1 = record.get("referenceId").toString();
			loancreatedEncompass = false;
		}
		System.out.println("loanNumber is --> " + loanNumber1);

		String status = record.get("status").toString();
		System.out.println("Status is --> " + status);

		String LN_status = loanNumber1.concat("-").concat(status);
		mongoClient.close();
		return LN_status;
	}

	@SuppressWarnings("deprecation")
	public String fetchInquiryID(String expectedUserdetails) throws Exception {
		System.out.println("inside fetchInquiryID ");
		String loanNumber = null;
		String DB_collection = "prequal";
		String successMsg = getElementByUsing("Submit_successful_msg").getText();
		System.out.println("success msg ---> " + successMsg);
		String inquiryID = successMsg.substring(64, 72);
		System.out.println("INQUIRY ID is ---> " + inquiryID);
		String Db_url = KWVariables.getVariables().get("Db_url");

		MongoClient mclient = new MongoClient(Db_url, 27017);

		DB db = mclient.getDB(DB_collection);
		DBCollection dbc = db.getCollection("quoteinfo");

		BasicDBObject query1 = new BasicDBObject();
		query1.put("referenceId", inquiryID);

		DBCursor dbcsr = dbc.find(query1);
		BasicDBObject inquiryData = (BasicDBObject) dbcsr.next();
		System.out.println("Record from DB ---> " + inquiryData);
		if (inquiryData.containsKey("loanNumber"))
			loanNumber = inquiryData.get("loanNumber").toString();
		else
			loanNumber = inquiryID;

		String status = inquiryData.get("status").toString();
		System.out.println("Status is --> " + status);

		String LN_status1 = loanNumber.concat("-").concat(status);

		mclient.close();
		return LN_status1;

	}
	// ***************************************************************************************************************************************************************************************

	/**
	 * \
	 *
	 *
	 */

	public void resumeAfterFetchingLoanNumberfromDashboardPage(String firstName) throws Exception{
		WebDriver driver = DriverFactory.getDriver();
		fetchLoanNumberFromDashBoardPage(driver,firstName);
		clickOnResumeButtonInDashBoardPage(driver,firstName);
	}

	public void navigateToViewDetailsPageAfterFetchingDetailsFromDashBoardPage(String firstName)throws Exception{
		System.out.println("Inside navigateToViewDetailsPageAfterFetchingDetailsFromDashBoardPage>>>");
		WebDriver driver = DriverFactory.getDriver();
		fetchLoanNumberFromDashBoardPage(driver,firstName);
		getLoanAmountFromDashBoard(driver,firstName);
		clickOnViewDetailsLink(driver,firstName);

	}

	public void navigateToViewDetailsPageAfterFetchingDetailsFromDashBoardSubmittedPage(String firstName)throws Exception{
		WebDriver driver = DriverFactory.getDriver();
		fetchLoanNumberFromDashBoardSubmittedPage(driver,firstName);
		getLoanAmountFromDashBoard(driver,firstName);
		clickOnViewDetailsLink(driver,firstName);

	}

	public String getCurrentDate(){
		Format f = new SimpleDateFormat("MM/dd/yyyy");
		 Date today = new Date();
		((DateFormat) f).setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        String PST = f.format(today);
        System.out.println("USA Date = "+PST);
		return PST.trim();
	}

	public void fetchLoanNumberFromDashBoardPage(WebDriver driver,String firstName) throws Exception {
		System.out.println("Entered fetchLoanNumberFromDashBoardPage");
		EncompassData encmp=new EncompassData();
//		driver.navigate().refresh();
		String loanDetailObj = "//div[contains(@class,'loan-list-section')]/div[2]/div[1]/div[%n]/div[1]//div[text()='%s']/following-sibling::div";
        String loanIdObj = "//div[contains(@class,'loan-list-section')]/div[2]/div[1]/div[%n]/div[1]//following-sibling::div[@class='loan-number']";
        String filestartDate="//div[contains(@class,'loan-list-section')]/div[2]/div[1]/div[%n]/div[1]//following-sibling::div[contains(@class,'loan-label-section') and contains(.,'File Started Date')]//following-sibling::div";
        String resumeButtonObj = "//div[contains(@class,'loan-list-section')]/div[2]/div[1]/div[%n]//button[contains(.,'Resume')]";
        
        WebDriverWait wait = new WebDriverWait(driver, 100);
        getElementByUsing("Dashboard_Inprogress_Tab").click();
        System.out.println("Came to Inprogress!!!");
    	wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//div[contains(@class,'loan-list-section')]"))));
		int  dashBoardIndex = getDashBoardIndex(driver, firstName);
		System.out.println("dashBoardIndex>>>"+dashBoardIndex);
		String displayedLoanNumber = driver.findElement(By.xpath(loanIdObj.replace("%n", String.valueOf(dashBoardIndex)))).getText().split("#")[1].trim();
		String displayedApplicant = driver.findElement(By.xpath(loanDetailObj.replace("%n", String.valueOf(dashBoardIndex)).replace("%s", "Applicant"))).getText().trim();
		String displayedPurpose = driver.findElement(By.xpath(loanDetailObj.replace("%n", String.valueOf(dashBoardIndex)).replace("%s", "Loan Purpose"))).getText().trim();
		String displayedStatus = driver.findElement(By.xpath(loanDetailObj.replace("%n", String.valueOf(dashBoardIndex)).replace("%s", "Status"))).getText().trim();
		String dateValiadte=driver.findElement(By.xpath(filestartDate.replace("%n", String.valueOf(dashBoardIndex)))).getText();
		EncompassData.EncomopassLoanNumber=displayedLoanNumber;
		System.out.println("LoanNumber Displayed in dashboard>>>"+displayedLoanNumber+"Value of File Start Date is>>>>"+dateValiadte);
		if(displayedLoanNumber.contains("IN")){
			if(!(dateValiadte.trim().equalsIgnoreCase("-".trim()))){
			addExceptionToReport("Exception in Inprogress getting LoanNumber as Startd File Date is>>", dateValiadte, "-");
			}
		}
		else if(!(dateValiadte.trim().equalsIgnoreCase(getCurrentDate()))){
			addExceptionToReport("Exception in Inprogress getting Startd File Date as LoanNumber got generated>>"+displayedLoanNumber, "-",getCurrentDate().toString());
		}
		if(!driver.findElement(By.xpath(resumeButtonObj.replace("%n", String.valueOf(dashBoardIndex)))).isDisplayed()){
			addExceptionToReport("Resume Button is not displayed for the Loan Number "+displayedLoanNumber, "", "");
		}
		

	}

	public void fetchLoanNumberFromDashBoardSubmittedPage(WebDriver driver,String firstName) throws Exception {
		System.out.println("Entered fetchLoanNumberFromDashBoardSubmittedPage");
		String loanDetailObj = "//div[contains(@class,'loan-list-section')]/div[2]/div[1]/div[%n]/div[1]//div[text()='%s']/following-sibling::div";
        String loanIdObj = "//div[contains(@class,'loan-list-section')]/div[2]/div[1]/div[%n]/div[1]//following-sibling::div[@class='loan-number']";
        String resumeButtonObj = "//div[contains(@class,'loan-list-section')]/div[2]/div[1]/div[%n]//button[contains(.,'Resume')]";
        String filestartDate="//div[contains(@class,'loan-list-section')]/div[2]/div[1]/div[%n]/div[1]//following-sibling::div[contains(@class,'loan-label-section') and contains(.,'File Started Date')]//following-sibling::div";

		int  dashBoardIndex = getDashBoardIndex(driver, firstName);
		String displayedLoanNumber = driver.findElement(By.xpath(loanIdObj.replace("%n", String.valueOf(dashBoardIndex)))).getText().split("#")[1].trim();
		String displayedApplicant = driver.findElement(By.xpath(loanDetailObj.replace("%n", String.valueOf(dashBoardIndex)).replace("%s", "Applicant"))).getText().trim();
		String displayedPurpose = driver.findElement(By.xpath(loanDetailObj.replace("%n", String.valueOf(dashBoardIndex)).replace("%s", "Loan Purpose"))).getText().trim();
		String displayedStatus = driver.findElement(By.xpath(loanDetailObj.replace("%n", String.valueOf(dashBoardIndex)).replace("%s", "Status"))).getText().trim();
		String dateValiadte=driver.findElement(By.xpath(filestartDate.replace("%n", String.valueOf(dashBoardIndex)))).getText();
		if(displayedLoanNumber.contains("IN")){
			if(!(dateValiadte.trim().equalsIgnoreCase("-".trim()))){
			addExceptionToReport("Exception in submitted getting LoanNumber as Startd File Date is>>", dateValiadte, "-");
			}
		}
		else if(!(dateValiadte.trim().equalsIgnoreCase(getCurrentDate()))){
			addExceptionToReport("Exception in submitted getting Startd File Date as LoanNumber got generated>>"+displayedLoanNumber, dateValiadte, getCurrentDate());
		}
	}


	public String getLoanAmountFromDashBoard(WebDriver driver,String firstName) throws Exception{
		System.out.println("Entered getLoanAmountFromDashBoard method to get the loan amount");
		String loanDetailObj = "//div[contains(@class,'loan-list-section')]/div[2]/div[1]/div[%n]/div[1]//div['%s']/following-sibling::div";
		int  dashBoardIndex = getDashBoardIndex(driver, firstName);
		return driver.findElement(By.xpath(loanDetailObj.replace("%n", String.valueOf(dashBoardIndex).replace("%s", "Loan Amount")))).getText();

	}


	public void clickOnViewDetailsLink(WebDriver driver,String firstName) throws Exception{
		System.out.println("Entered clickOnViewDetailsLink method to click on view details link");
		String viewDetailsLinkObj = "//div[contains(@class,'loan-list-section')]/div[2]/div[1]/div[%n]/div[1]//a[@title='View Details']";
		int  dashBoardIndex = getDashBoardIndex(driver, firstName);
		driver.findElement(By.xpath(viewDetailsLinkObj.replace("%n", String.valueOf(dashBoardIndex)))).click();
	}
	public void clickOnResumeButtonInDashBoardPage(WebDriver driver,String firstName) throws Exception{
		System.out.println("clickOnResumeButtonInDashBoardPage");

		String resumeButtonObj = "//div[contains(@class,'loan-list-section')]/div[2]/div[1]/div[%n]//button[contains(.,'Resume')]";
	//	WebDriver driver = DriverFactory.getDriver();
		int dashBoardIndex = getDashBoardIndex(driver,firstName);
		driver.findElement(By.xpath(resumeButtonObj.replace("%n", String.valueOf(dashBoardIndex)))).click();
//		util.waitTillElementIsDisplayed(driver, getElementByUsing("1003_GetStarted_IAmDone"));
	}

	public int getDashBoardIndex(WebDriver driver, String applicantName) throws Exception {


		//String applicantObject = "//div[contains(@class,'loan-list-section')]/div[2]/div[1]/div[%n]/div[1]//following-sibling::div[contains(text(),'Applicant')]/following-sibling::div";

		String applicantObject = "//div[contains(@class,'loan-list-section')]/div[2]/div[1]/div[%n]/div[1]//div[text()='Applicant']/following-sibling::div";
		String loanCount = "//div[contains(@class,'loan-status-card-details-section active')]";
		int noOfLoans = driver.findElements(By.xpath(loanCount)).size();
		for (int dashBoardIndex = 1; dashBoardIndex <= noOfLoans; dashBoardIndex++) {

			String displayedApplicantName = driver
					.findElement(By.xpath(applicantObject.replace("%n", String.valueOf(dashBoardIndex)))).getText();
			System.out.println("displayedApplicantName>>>>"+displayedApplicantName);
			System.out.println(displayedApplicantName.toLowerCase().trim());
			System.out.println(applicantName.toLowerCase().trim());

			if (displayedApplicantName.toLowerCase().trim().contains(applicantName.toLowerCase().trim())) {
				return dashBoardIndex;
			}

		}

		return 0;

	}

	public void testDashBoard() throws Exception{
		System.out.println("entered testDashBoard");
		Thread.sleep(20000);
		String sectionsObjXpath = "//div[@class='preview-holder']/div[@class='data-section']/div";

		WebDriver driver = DriverFactory.getDriver();

		HashMap<String,String> displayedExpectedMap =  util.fetchDataFromNotepad("PreviewData.txt");
		System.out.println("displayedExpectedMap>>"+displayedExpectedMap);
		
		int sectionCount = driver.findElements(By.xpath(sectionsObjXpath)).size();
		System.out.println("After sectionCount>>"+sectionCount);

		for(int sectionIndex= 1;sectionIndex <=sectionCount;sectionIndex++){
			getSectionFieldAndItsValues(driver,sectionIndex,displayedExpectedMap);

		}
	}

	public void getSectionFieldAndItsValues(WebDriver driver,int sectionIndex,HashMap<String,String> displayedExpectedMap) throws Exception{
		System.out.println("Inside getSectionFieldAndItsValues");
		String[] notRequiredFields = {"First Name","Current Address (City)","Current Address (State)","Current Address (County)","Email ID","City","State","County","Current Job","Insurance, Maintenance, Taxes & Misc","Owns jointly with","SSN"};

		String sectionFieldsObject = "//div[@class='preview-holder']/div[@class='data-section']/div[%s]//div[@class='qustion-holder']/div";
		String fieldNameObject = "//div[@class='preview-holder']/div[@class='data-section']/div[%s]//div[@class='qustion-holder']/div[%n]//div[@class='field-section']/div[1]";
		String fieldValueObject = "//div[@class='preview-holder']/div[@class='data-section']/div[%s]//div[@class='qustion-holder']/div[%n]//div[@class='field-section']/div[2]";
		String headerObject = "//div[@class='preview-holder']/div[@class='data-section']/div[%s]//div[@class='header']//h3";
		int fieldCount = driver.findElements(By.xpath(sectionFieldsObject.replace("%s", String.valueOf(sectionIndex)))).size();
		for(int fieldIndex = 1;fieldIndex <= fieldCount;fieldIndex++){
			String displayedFieldName = driver.findElement(By.xpath(fieldNameObject.replace("%s", String.valueOf(sectionIndex)).replace("%n", String.valueOf(fieldIndex)))).getText();
			String displayedFieldValue = driver.findElement(By.xpath(fieldValueObject.replace("%s", String.valueOf(sectionIndex)).replace("%n", String.valueOf(fieldIndex)))).getText();
			String displayedHeader = driver.findElement(By.xpath(headerObject.replace("%s", String.valueOf(sectionIndex)))).getText();
			System.out.println("Dispay Header"+displayedHeader);

			HashMap<String,String> expectedDataMap = buildExpectedMap(displayedHeader);
			/*System.out.println("Print street address:"+expectedDataMap);
			for (Entry<String, String> entry : expectedDataMap.entrySet()) {
			    String key = entry.getKey().toString();
			    String value = entry.getValue();
			    System.out.println("key, " + key + " value " + value);
			}
			System.out.println("Current Address map>>>"+expectedDataMap.get("My current address is"));
			System.out.println("Done printing");*/
			if(Arrays.asList(notRequiredFields).contains(displayedFieldName) == false){

				verifySectionMap(expectedDataMap,displayedExpectedMap,displayedFieldName,displayedFieldValue,displayedHeader);
			}

		}
	}

	public HashMap<String, String> buildDataMap(String testData) {
		HashMap<String, String> qaMap = new HashMap<String, String>();
		for (String qA : testData.split("&&")) {
			qaMap.put(qA.split(";")[0].trim(), qA.split(";")[1].trim());
		}
		return qaMap;
	}

	public HashMap<String,String> buildExpectedMap(String header){
		System.out.println("Inside buildExpectedMap");

		HashMap<String,String> expectedDataMap = new HashMap<String,String>();
		String refColumn = null;
		String addd = "";
		switch (header.trim().toLowerCase()) {
		case "get started":
			refColumn = "question_ans";
			expectedDataMap = Ten0Three.getStartedDataMap;
			break;

		case "property":
			refColumn = "PropertyQuestionAnswer";
			break;

		case "declarations":
			refColumn = "DeclarationQuestionAnswer";
			break;

		case "income":
			refColumn = "1003_Income_Business";
			break;

		case "assets":
			refColumn = "1003_Asset_Manual";
			break;

		case "real estate":
			refColumn = "RealEstateQuestionAnswer";
			break;

		default:
			break;
		}
		
		if(expectedDataMap.isEmpty()){
			String expectedQuestions = step.getDataValue(refColumn);
			expectedDataMap = buildDataMap(expectedQuestions);
		}
		return expectedDataMap;
		
		
		//return buildDataMap(expectedQuestions);

	}
	public  void verifySectionMap(HashMap<String,String> expectedMap,HashMap<String,String> displayeExpectedMap,String fieldName,String fieldValue,String header) throws Exception{
		String[] ignoreFields = {"In my own property","Employer Phone","Starting Date of Employment","End Date of Employment","Gross Income"};
		//String[] ignoreCharacters = {"%","$"};

		fieldName = fieldName+"_"+header.toLowerCase();
		String expQtn = displayeExpectedMap.get(fieldName).split("&")[0];
        String expFieldName = displayeExpectedMap.get(fieldName).split("&")[1].trim();
        String expectedValue = "";
        String expectedValues = expectedMap.get(expQtn);
        if(expectedValues.contains("%")){
              String[] expectedValArray = expectedValues.split(":");
              HashMap<String,String> multiOptionMap = new HashMap<String,String>();
              for(String expectedVal:expectedValArray){
                     String optFldName = expectedVal.split("%")[1].split("_")[0].trim();
                     String optFldValue = expectedVal.split("%")[1].split("_")[1].trim();
                     multiOptionMap.put(optFldName, optFldValue);
              }

              expectedValue = multiOptionMap.get(expFieldName);
        }else{
              expectedValue = expectedValues;
        }
//".00" --->
        if(fieldValue.contains("$") ||fieldValue.contains("%")||fieldValue.contains(",")||fieldValue.contains(".00"))
		{
			fieldValue=fieldValue.replace("%","").replace("$", "").replace(",", "").replace(".00", "");
		}

        System.out.println("Displayed Field Name>>>>"+fieldName+" and displayed value>>>"+fieldValue+" and expected field name>>>"+expFieldName+" and its expected value>>>"+expectedValue);




        if(expectedValue.trim().equalsIgnoreCase(fieldValue)==false){
        	if(Arrays.asList(ignoreFields).contains(fieldValue) == false){
              addExceptionToReport("Expected value "+expectedValue+" is not matching with the displayed value "+fieldValue+" under the field "+fieldName+ " under the section "+ header, "", "");
        }
        }
	}

	/**
	 * verifyDashboardlandinPage
	 * @param status
	 * @throws TwfException
	 */
	public void verifyDashboardlandinPage(String status) throws TwfException {
		WebDriver driver = DriverFactory.getDriver();
		String statusObject = "//div[@class='steppers completed active-proccessing']/div[contains(.,'%s')]";

		if (!driver.findElement(By.xpath(statusObject.replace("%s", status))).isDisplayed())
			addExceptionToReport(status + " is not present in Your Jorney", "", "");

	}
	public String getLoanId(WebDriver driver, String applicantName) throws Exception {
		System.out.println("Inside getLoanId>> and name>>>"+applicantName);
		String loanIdDisplayed =  "";
		String loanId = "//div[contains(@class,'loan-list-section')]/div[2]/div[1]/div[%n]/div[1]//following-sibling::div[@class='loan-number']";
		String applicantObject = "//div[contains(@class,'loan-list-section')]/div[2]/div[1]/div[%n]/div[1]//following-sibling::div[contains(text(),'Applicant')]/following-sibling::div";
		String loanCount = "//div[contains(@class,'loan-status-card-details-section active')]";
		int noOfLoans = driver.findElements(By.xpath(loanCount)).size();
		for (int quesIndex = 1; quesIndex <= noOfLoans; quesIndex++) {

				String displayedApplicantFullName = driver
						.findElement(By.xpath(applicantObject.replace("%n", String.valueOf(quesIndex)))).getText();
				String[] displayedApplicantName = displayedApplicantFullName.split(" ");
				if (displayedApplicantName[0].toLowerCase().trim().contains(applicantName.toLowerCase().trim())) {
					loanIdDisplayed = driver.findElement(By.xpath(loanId.replace("%n", String.valueOf(quesIndex))))
							.getText();
					System.out.println("LoanNumber>>>>>"+loanIdDisplayed.split("#")[1].trim());
					if (loanIdDisplayed.trim().contains("IN")) {
						addExceptionToReport("Displayed Loan number" + loanIdDisplayed + "will not be there in encompass"
								+ ">>>>It's refrence ID", " ", " ");
					}
					return loanIdDisplayed.split("#")[1].trim();
				}

			}

			return null;

		}

//Sweata_Updated
	
	public String getLoanId1( String applicantName) throws Exception {
		WebDriver driver=DriverFactory.getDriver();
		System.out.println("Inside getLoanId>> and name>>>"+applicantName);
		String loanIdDisplayed =  "";
		String loanId = "//div[contains(@class,'loan-list-section')]/div[2]/div[1]/div[%n]/div[1]//following-sibling::div[@class='loan-number']";
		String applicantObject = "//div[contains(@class,'loan-list-section')]/div[2]/div[1]/div[%n]/div[1]//following-sibling::div[contains(text(),'Applicant')]/following-sibling::div";
		String loanCount = "//div[contains(@class,'loan-status-card-details-section active')]";
		int noOfLoans = driver.findElements(By.xpath(loanCount)).size();
		for (int quesIndex = 1; quesIndex <= noOfLoans; quesIndex++) {

				String displayedApplicantFullName = driver
						.findElement(By.xpath(applicantObject.replace("%n", String.valueOf(quesIndex)))).getText();
				String[] displayedApplicantName = displayedApplicantFullName.split(" ");
				if (displayedApplicantName[0].toLowerCase().trim().contains(applicantName.toLowerCase().trim())) {
					loanIdDisplayed = driver.findElement(By.xpath(loanId.replace("%n", String.valueOf(quesIndex))))
							.getText();
					System.out.println("LoanNumber>>>>>"+loanIdDisplayed.split("#")[1].trim());
					if (loanIdDisplayed.trim().contains("IN")) {
						addExceptionToReport("Displayed Loan number" + loanIdDisplayed + "will not be there in encompass"
								+ ">>>>It's refrence ID", " ", " ");
					}
					return loanIdDisplayed.split("#")[1].trim();
				}

			}

			return null;

		}
	
	
public String naviagteToDashBoardAndFetchLoanNumber() throws Exception{
	WebDriver driver = DriverFactory.getDriver();
	TimeUnit.MINUTES.sleep(1);
	getElementByUsing("FinExp_Login_HeaderButton").click();
	//driver.findElement(By.xpath("//button[@id='headerdropdown']")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//span[@class='home-text']")).click();
	Thread.sleep(2000);
	String name = Ten0Three.fname;
//	String name = "aaaa";
	System.out.println("TENOTHREE FNAME>>>"+name);
	System.out.println("name in dashboard Page");
//	driver.navigate().refresh();
	Thread.sleep(4000);
	return getLoanId(driver, name);
}

	public void validateAfterLoanlock(String expectedLoanNumber) throws TwfException, InterruptedException{
		WebDriver driver = DriverFactory.getDriver();
		driver.navigate().refresh();
		   Thread.sleep(3000);
		   String loanSize="(//div[contains(@class,'loan-status-card-details')])";
	
	       for(int i=1;i<=driver.findElements(By.xpath(loanSize)).size();i++){    	  
	    	   String displayedLoan=driver.findElement(By.xpath((loanSize+"[%s]//div[@class='loan-number']".replace("%s",String.valueOf(i))))).getText().split("Loan # ")[1];
	    	   String loanStatus=driver.findElement(By.xpath((loanSize+"[%s]//div[text()='Status']//following-sibling::div".replace("%s",String.valueOf(i))))).getText();
	    	   String loanLockButton=driver.findElement(By.xpath((loanSize+"[%s]//button".replace("%s",String.valueOf(i))))).getText();
	    	   
	    	   if(expectedLoanNumber.equalsIgnoreCase(displayedLoan)&&(!loanStatus.equalsIgnoreCase("locked")&&!loanLockButton.equalsIgnoreCase("locked"))){
	    		   addExceptionToReport("Mismatch in status for loan number"+expectedLoanNumber,loanStatus,loanLockButton); 
	    	   }	   
	      }
		
	}



	@Override
	public void checkPage() {
		// TODO Auto-generated method stub

	}
}

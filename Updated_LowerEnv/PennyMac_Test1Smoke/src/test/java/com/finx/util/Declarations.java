package com.finx.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;
import com.tavant.base.DriverFactory;
import com.tavant.kwutils.CustomStep;
import com.tavant.kwutils.KWVariables;
import com.tavant.utils.TwfException;

public class Declarations extends CustomStep {
	String attribute1 = "(.//div[@class='form-container tf-block-%s active-section']//dynamic-form//dynamic-control/div/div/div/div/div)[1]";
	String radioAns = "(.//div[@class='form-container tf-block-%s active-section']//dynamic-control//radio-button-selection//button[contains(.,'%placeholder')])[1]";
	String sub_ans_text = ".//div[@class[contains(.,'active-section')]]//dynamic-control//md-input-container//input[@placeholder='%placeholder']";

	String URL = "http://10.131.148.191:9090/#/loan-application";
	String question = "(//div[@class[contains(.,'active-section')]]//dynamic-form/form//div[@class[contains(.,'title-section')]]//label)[%s]";
	String answerNo = "(//div[@class[contains(.,'active-section')]]//dynamic-form/form//div[@data-type='input-switch']//div[@class[contains(.,'ui-inputswitch-off')]])[%s]";
	String answerYes = "(//div[@class='ui-inputswitch-on'])[%s]/span";
	String OkayBUtton1 = "//div[@class[contains(.,'form-container tf-block-%s active')]]//div[@class='form-group']//form/div[%n]//button";
	String activeBlock = "//div[@class='ps-content']/div[@class[contains(.,'tf-block-%s active-section')]]";
	String multiDrop = "//div[@class[contains(.,'active-section')]]//dynamic-form/form//multidropdown//p-multiselect//div/label[contains(text(),'%placeholder')]";
	String multiDropvalue = "//div[@class[contains(.,'active-section')]]//dynamic-form/form//multidropdown//p-multiselect//div/label[contains(text(),'%placeholder')]";
	String Qustion = "(//div[@class='form-container tf-block-%s active-section']//div/label)[1]";

	// *******************************************************************************************************************************************************************************
	String qtnCountObject = "//div[@class='ps-content']/div";
	String qtnCountObjectUnderEachSection = "//div[@class[contains(.,'form-container tf-block-%s active-section')]]//form/div";
	String qtnLabelObject = "//div[@class[contains(.,'form-container tf-block-%s active-section')]]//form/div[%n]//label";
	String qtnCountObjectUnderEachQuestionInASection = "//div[@class[contains(.,'form-container tf-block-%s active-section')]]//form/div[%n]/div";
	String childQtnUnderEachSectionObject = "//div[@class[contains(.,'form-container tf-block-%s active-section')]]//form/div[%n]/div";
	String qtnLabelUnderEachSectionObject = "//div[@class[contains(.,'form-container tf-block-%s active-section')]]//form/div[%n]/div[%t]//label";
	String qtnLabelUnderEachSectionObject1 = "//div[@class[contains(.,'form-container tf-block-%s active-section')]]//form/div[%n]]//label";
	String dataTypeObject = "//div[@class[contains(.,'form-container tf-block-%s active-section')]]//form/div[%n]/div[%t]//div[@class[contains(.,'col')]]";
	String subQtnObject = "(//div[@class[contains(.,'form-container tf-block-%s active-section')]]//form/div[%n]//div[@class[contains(.,'col')]])[1]";

	String[] declarationSummartPageQtns = { "Are you a U.S. citizen?:US Citizen",
			"Are there any outstanding judgments against you?:Judgment",
			"Have you had property foreclosed upon or given title or deed in lieu thereof in the last 7 years?:Foreclosure",
			"Have you been declared bankrupt within the past 7 years?:Bankruptcy",
			"Are you presently delinquent or in default on any Federal debt or any other loan, mortgage, financial obligation, bond, or loan guarantee?:Federal debt default" };
	// String[] declarationSummartPageQtns = {"US
	// Citizen:No","Judgment:No","Foreclosure:No","Bankruptcy:No","Federal debt
	// default:No"};
	GenerateData2 dummyData = new GenerateData2();
	Ten0Three ten0Three = new Ten0Three();
	TenOThreeSectionSummary tenOThreeSectionSummary = new TenOThreeSectionSummary();
	Util utill = new Util();

	/*
	 * public HashMap<String, String> buildDataMap(String testData) {
	 * HashMap<String, String> qaMap = new HashMap<String, String>(); String[]
	 * queAns = testData.split("&&"); for (String qA : queAns) {
	 * qaMap.put(qA.split(";")[0].trim(), qA.split(";")[1].trim()); } return
	 * qaMap;
	 * 
	 * }
	 */

	public void beforePageLoad() throws TwfException {
		WebDriver driver = DriverFactory.getDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.mpPageReloaded='notYet';");
	}

	public void validateDeclarationsQuestionAnswer() throws InterruptedException, TwfException, Exception {
		String DeclarationsQuestionAnswer = step.getDataValue("DeclarationQuestionAnswer");
		Thread.sleep(2000);
		utill.click(getElementByUsing("1003_Declarations_Button"));
		validateDeclarationIn1003(DeclarationsQuestionAnswer);

	}
	
	public void validateCoapplicantDeclarationsQuestionAnswer() throws InterruptedException, TwfException, Exception {
		String DeclarationsQuestionAnswer = step.getDataValue("Coapplicant_Declarations");
		Thread.sleep(2000);
		utill.click(getElementByUsing("1003_Declarations_Button"));
		validateDeclarationIn1003(DeclarationsQuestionAnswer);

	}

	public void validateDeclarationIn1003(String quesAnsString) throws Exception {
		HashMap<String, String> dataMap = new HashMap<String, String>();
		WebDriver driver = DriverFactory.getDriver();
		dataMap = ten0Three.buildDataMap(quesAnsString);
		String val = step.getDataValue("DeclarationQuestionAnswer");
		if(KWVariables.getVariables().get("Env").equalsIgnoreCase("taurus")||KWVariables.getVariables().get("Env").equalsIgnoreCase("pisces")){
			selectQuestion2AnswerInDeclarationMultiBorrower(driver, val);
		}
		else
		selectQuestion2AnswerInDeclaration(dataMap, quesAnsString);

	}

	private void clickOnOkButtonAfterEachQuestionNew(WebDriver driver, int sectionIndex) {
		String okayButtonXpath = "//div[@class='form-container tf-block-%s active-section']//button[contains(.,'Okay')]";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			WebElement we = driver.findElement(By.xpath(okayButtonXpath.replace("%s", String.valueOf(sectionIndex))));
			utill.scrollToElement(we);
			we.click();
		} catch (Exception e) {

		}
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
	
	
	public void selectQuestion2AnswerInDeclarationMultiBorrower(WebDriver driver, String quesAnsString)
			throws Exception {
		HashMap<String, String> dataMap = new HashMap<String, String>();
		dataMap = ten0Three.buildDataMap(quesAnsString);
		int totalDeclarationQtnCount = getQuestionCount(driver);
		int qtnIndex = 0;

		while (qtnIndex < totalDeclarationQtnCount) {
			System.out.println("Entered wjile lopp");
			setValuesNew(driver, qtnIndex, dataMap);
			clickOnOkButtonAfterEachQuestionNew(driver, qtnIndex);
			Thread.sleep(5000);
			qtnIndex = qtnIndex + 1;
			totalDeclarationQtnCount = getQuestionCount(driver);
			System.out.println("Total count>>>" + totalDeclarationQtnCount);
		}
	
	}
	
	
	public void validateEditedValuesInDeclarationPage(HashMap<String, String> beforeEditDataMap,
			HashMap<String, String> aferEditDataMap) throws Exception {

		WebDriver driver = DriverFactory.getDriver();
		int totalDeclarationQtnCount = 0;
		int qtnIndex = 0;

		totalDeclarationQtnCount = getQuestionCount(driver);
		while (qtnIndex < totalDeclarationQtnCount) {
			int qtnCount = getQuestionCountUnderEachSection(driver, qtnIndex);
			getValues(driver, aferEditDataMap, beforeEditDataMap, qtnIndex, qtnCount);
			totalDeclarationQtnCount = getQuestionCount(driver);
			qtnIndex = qtnIndex + 1;

		}

	}
	static int prevSectionQtnCount ;
	private void selectQuestion2AnswerInDeclaration(HashMap<String, String> dataMap, String quesAnsString)
			throws Exception {
		WebDriver driver = DriverFactory.getDriver();
		int totalDeclarationQtnCount = 0;
		totalDeclarationQtnCount = getQuestionCount(driver);
		
		int qtnIndex = 0;
		while (qtnIndex < totalDeclarationQtnCount) {
			if(!driver.findElement(By.xpath("//div[@class[contains(.,'form-container tf-block-"+qtnIndex+"')]]")).getAttribute("class").contains("active")){
				driver.findElement(By.xpath("//div[@class[contains(.,'form-container tf-block-"+qtnIndex+"')]]")).click();
			}
			
			prevSectionQtnCount  = getQuestionCountUnderEachSection(driver, qtnIndex);
			//prevSectionQtnCount = qtnCount;
			setValues(driver, dataMap, qtnIndex, prevSectionQtnCount, totalDeclarationQtnCount);
			Thread.sleep(1000);
			totalDeclarationQtnCount = getQuestionCount(driver);
			qtnIndex = qtnIndex + 1;
		}

		// String quesAnsString =
		// step.getDataValue("DeclarationQuestionAnswer");
		// HashMap<String, String> summarySheetMap =
		// ten0Three.buildSummarySheetMap(dataMap,
		// declarationSummartPageQtns,"DECLARATIONS",quesAnsString);
		// commented due to defect
		// tenOThreeSectionSummary.validateSummarySheetDetails(summarySheetMap,true);
	}


	private int getNumberOfQuestionsUnderEachQuestionInASection(WebDriver driver, int sectionIndex, int qtnIndex) {
		return driver.findElements(By.xpath(qtnCountObjectUnderEachQuestionInASection
				.replace("%s", String.valueOf(sectionIndex)).replace("%n", String.valueOf(qtnIndex)))).size();
	}

	private boolean scrollTillActiveQtnIsDisplayed(WebDriver driver, int sectionIndex) throws Exception {
		boolean isDisplayed = false;

		try {
			String qtn = "//div[@class[contains(.,'form-container tf-block-%s active-section')]]//form";
			if (driver.findElement(By.xpath(qtn.replace("%s", String.valueOf(sectionIndex)))).isDisplayed()) {
				System.out.println("active qtn is displayed");
				return true;
			}

		} catch (Exception e) {
			try {
				while (isDisplayed == false) {
					String qtn = "//div[@class='form-container tf-block-%s']//form";
					Actions action = new Actions(driver);
					action.moveToElement(driver.findElement(By.xpath(qtn.replace("%s", String.valueOf(sectionIndex)))))
							.doubleClick().build().perform();

					isDisplayed = true;
				}
			} catch (Exception e1) {
				return false;
			}

		}
		return true;
	}

	private int getSubQtnCount(WebDriver driver, int sectionIndex, int subQtnIndex) {
		int subQtnCount = 0;
		try {
			subQtnCount = driver
					.findElements(By.xpath(qtnCountObjectUnderEachQuestionInASection
							.replace("%s", String.valueOf(sectionIndex)).replace("%n", String.valueOf(subQtnIndex))))
					.size();
		} catch (Exception e) {

		}
		return subQtnCount;
	}

	private void getValuesForSubQtns(WebDriver driver, HashMap<String, String> dataMap,
			HashMap<String, String> beforeEditDataMap, int sectionIndex, int qtnCount) throws Exception {
		String subDataTypeObject = "(//div[@class[contains(.,'form-container tf-block-%s active-section')]]//form/div[%n]//div[@class[contains(.,'col')]])[1]";
		int startSubQtnIndex = 2;
		if (qtnCount > 4) {
			startSubQtnIndex = 3;
		}

		for (int subQtnIndex = startSubQtnIndex; subQtnIndex < qtnCount; subQtnIndex++) {
			String displayedQtnLabel = null;
			String dataType = null;
			String expectedAnswer = null;
			if (getSubQtnCount(driver, sectionIndex, subQtnIndex) > 0) {
				displayedQtnLabel = getSubQtnQuestionLabel(driver, sectionIndex, subQtnIndex);
				expectedAnswer = dataMap.get(displayedQtnLabel);
				if (displayedQtnLabel.length() > 0) {
					if (displayedQtnLabel.contains("demographic information")) {
						subQtnIndex = subQtnIndex + 1;
						String labelObject = subQtnObject.replace("%s", String.valueOf(sectionIndex)).replaceAll("%n",
								String.valueOf(subQtnIndex)) + "//p";
						if (expectedAnswer.trim().equalsIgnoreCase(
								driver.findElement(By.xpath(labelObject)).getText().trim()) == false) {
							addExceptionToReport("Expected answer is not displayed under question " + displayedQtnLabel,
									"", "");
						}

					} else {
						dataType = driver
								.findElement(By.xpath(subDataTypeObject.replace("%s", String.valueOf(sectionIndex))
										.replace("%n", String.valueOf(subQtnIndex))))
								.getAttribute("data-type");
						validateEditedDeclarationValues(driver, sectionIndex, subQtnIndex, dataType, displayedQtnLabel,
								dataMap, beforeEditDataMap);
					}

				}
			}
		}
	}

	private void setValuesForSubQtns(WebDriver driver, HashMap<String, String> dataMap, int sectionIndex, int qtnCount)
			throws Exception {
		String subDataTypeObject = "(//div[@class[contains(.,'form-container tf-block-%s active-section')]]//form/div[%n]//div[@class[contains(.,'col')]])[1]";
		int startSubQtnIndex = 2;
		if (qtnCount > 4) {
			startSubQtnIndex = 3;
		}
		if(dataMap.keySet().contains("Have you been declared bankrupt within the past 7 years?")){
			System.out.println("Section Index >>>"+sectionIndex);
			if(sectionIndex == 1){
				if(dataMap.get("Have you been declared bankrupt within the past 7 years?").equalsIgnoreCase("No")){
					startSubQtnIndex = 2;
				}
				System.out.println("startSubQtnIndex >>>"+startSubQtnIndex);
			}
		}
		Thread.sleep(1000);
		for (int subQtnIndex = startSubQtnIndex; subQtnIndex < qtnCount; subQtnIndex++) {
			String displayedQtnLabel = null;
			String dataType = null;
			String expectedAnswer = null;
			if (getSubQtnCount(driver, sectionIndex, subQtnIndex) > 0) {
				displayedQtnLabel = getSubQtnQuestionLabel(driver, sectionIndex, subQtnIndex);
				
				expectedAnswer = dataMap.get(displayedQtnLabel);
				
				System.out.println("displayedQtnLabel>>>"+displayedQtnLabel);
				System.out.println("expectedAnswer>>>>>"+expectedAnswer);
				Thread.sleep(1000);
				if (displayedQtnLabel.length() > 0) {
					if (displayedQtnLabel.contains("demographic information")) {
						subQtnIndex = subQtnIndex + 1;
						String labelObject = subQtnObject.replace("%s", String.valueOf(sectionIndex)).replaceAll("%n",
								String.valueOf(subQtnIndex)) + "//p";
						if (expectedAnswer.trim().equalsIgnoreCase(
								driver.findElement(By.xpath(labelObject)).getText().trim()) == false) {
							addExceptionToReport("Expected answer is not displayed under question " + displayedQtnLabel,
									"", "");
						}

					} else {
						dataType = driver
								.findElement(By.xpath(subDataTypeObject.replace("%s", String.valueOf(sectionIndex))
										.replace("%n", String.valueOf(subQtnIndex))))
								.getAttribute("data-type");
						setDeclarationAnswer(driver, sectionIndex, subQtnIndex, dataType, expectedAnswer);
					}

				}
			}
		}
	}

	private String getSubQtnQuestionLabel(WebDriver driver, int sectionIndex, int subQtnIndex) {

		String subQtnLabelObject = "(//div[@class[contains(.,'form-container tf-block-%s active-section')]]//form/div[%n]//div[@class[contains(.,'col')]])[1]//label";
		String subQtnLabel = "";
		try {
			String classAttr = driver.findElement(By.xpath(subQtnObject.replace("%s", String.valueOf(sectionIndex))
					.replace("%n", String.valueOf(subQtnIndex)))).getAttribute("class");
			if (!classAttr.contains("hide")) {		
				subQtnLabel = driver.findElement(By.xpath(subQtnLabelObject.replace("%s", String.valueOf(sectionIndex))
						.replace("%n", String.valueOf(subQtnIndex)))).getText();
			}
		} catch (Exception e) {
			subQtnLabel = "";
		}
		return subQtnLabel;

	}

	private void setValues(WebDriver driver, HashMap<String, String> dataMap, int sectionIndex,
			int qtnCountUnderEachSection, int qtnCount) throws Exception {
		String displayedQtnLabel = "";
		String expectedAnswer = "";
		String dataType = "";

		if (qtnCountUnderEachSection == 2) {
			displayedQtnLabel = driver
					.findElement(By.xpath(qtnLabelUnderEachSectionObject.replace("%s", String.valueOf(sectionIndex))
							.replace("%n", String.valueOf(2)).replace("%t", String.valueOf(1))))
					.getText();
			dataType = driver
					.findElement(By.xpath(dataTypeObject.replace("%s", String.valueOf(sectionIndex))
							.replace("%n", String.valueOf(2)).replace("%t", String.valueOf(1))))
					.getAttribute("data-type");
			expectedAnswer = dataMap.get(displayedQtnLabel);
			setDeclarationAnswer(driver, sectionIndex, 2, dataType, expectedAnswer);
		} else {
			setValuesForSubQtns(driver, dataMap, sectionIndex, qtnCountUnderEachSection);
			clickOnOkButtonAfterEachQuestion(driver, sectionIndex, qtnCountUnderEachSection);

		}

	}

	private void getValues(WebDriver driver, HashMap<String, String> dataMap, HashMap<String, String> beforeEditMap,
			int sectionIndex, int qtnCountUnderEachSection) throws Exception {
		String displayedQtnLabel = "";
		String expectedAnswer = "";
		String dataType = "";
		if (qtnCountUnderEachSection == 2) {

			displayedQtnLabel = driver
					.findElement(By.xpath(qtnLabelUnderEachSectionObject.replace("%s", String.valueOf(sectionIndex))
							.replace("%n", String.valueOf(2)).replace("%t", String.valueOf(1))))
					.getText();
			dataType = driver
					.findElement(By.xpath(dataTypeObject.replace("%s", String.valueOf(sectionIndex))
							.replace("%n", String.valueOf(2)).replace("%t", String.valueOf(1))))
					.getAttribute("data-type");
			expectedAnswer = dataMap.get(displayedQtnLabel);
			validateEditedDeclarationValues(driver, sectionIndex, 2, dataType, displayedQtnLabel, dataMap,
					beforeEditMap);
		} else {
			getValuesForSubQtns(driver, dataMap, beforeEditMap, sectionIndex, qtnCountUnderEachSection);
			clickOnOkButtonAfterEachQuestion(driver, sectionIndex, qtnCountUnderEachSection);
			Thread.sleep(1000);
		}

	}

	private void setDeclarationAnswer(WebDriver driver, int sectionIndex, int qtnIndex, String dataType,
			String expectedAnswer) throws Exception {
		String qtnLabelUnderEachSectionObject2 = "(//div[@class[contains(.,'form-container tf-block-%s active-section')]]//form/div[%n]//div[@class[contains(.,'col')]])[1]";
		switch (dataType.toLowerCase()) {
		case "radiobtn":	
			String radioButtonObject = qtnLabelUnderEachSectionObject2
					+ "//following-sibling::radio-button-selection//button[contains(.,'%v')]";
			System.out.println("Radio button object>>>" + radioButtonObject);

			driver.findElement(By.xpath(radioButtonObject.replace("%s", String.valueOf(sectionIndex))
					.replace("%n", String.valueOf(qtnIndex)).replace("%v", expectedAnswer))).click();
			System.out.println("Radio button object after clickk>>>" + radioButtonObject.replace("%s", String.valueOf(sectionIndex))
			.replace("%n", String.valueOf(qtnIndex)).replace("%v", expectedAnswer));
			break;

		case "multiselect":
			String multiSelectObject = qtnLabelUnderEachSectionObject2
					+ "//following-sibling::div/button[contains(.,'%v')]";
			String expectedMutilSelectOpt = expectedAnswer;
			String expectedTextOpt = "";
			String expectedTextVal = "";

			if (expectedAnswer.contains(":")) {
				expectedMutilSelectOpt = expectedAnswer.split(":")[0];
				expectedTextOpt = expectedAnswer.split(":")[1].split("%")[1].split("_")[0];
				expectedTextVal = expectedAnswer.split(":")[1].split("%")[1].split("_")[1];
			}

			for (String val : expectedMutilSelectOpt.split("~")) {
				driver.findElement(By.xpath(multiSelectObject.replace("%s", String.valueOf(sectionIndex))
						.replace("%n", String.valueOf(qtnIndex)).replace("%v", val))).click();
				Thread.sleep(1000);
			}

			if (expectedTextVal.length() > 0) {
				String optionTextObject = "(//div[@class[contains(.,'form-container tf-block-%s active-section')]]//form/div[%n]//div[@data-Type='text'])[1]//label";
				String optionTextValObject = "(//div[@class[contains(.,'form-container tf-block-%s active-section')]]//form/div[%n]//div[@data-Type='text'])[1]//input";
				String displayedQtnLabel = driver.findElement(By.xpath(optionTextObject
						.replace("%s", String.valueOf(sectionIndex)).replace("%n", String.valueOf(qtnIndex))))
						.getText();
				if (!displayedQtnLabel.trim().equalsIgnoreCase(expectedTextOpt.trim())) {
					addExceptionToReport("Text Option field label " + displayedQtnLabel
							+ " is not matching with expected value " + expectedTextOpt, "", "");
				}
				driver.findElement(By.xpath(optionTextValObject.replace("%s", String.valueOf(sectionIndex))
						.replace("%n", String.valueOf(qtnIndex)))).clear();
				driver.findElement(By.xpath(optionTextValObject.replace("%s", String.valueOf(sectionIndex))
						.replace("%n", String.valueOf(qtnIndex)))).sendKeys(expectedTextVal);
			}
			break;

		case "label":
			String labelObject = qtnLabelUnderEachSectionObject2 + "//p";
			String questionLabel = getQuestionLabel(driver, sectionIndex, qtnIndex);
			if (questionLabel.contains(expectedAnswer) == false) {
				addExceptionToReport("Expected answer is not displayed under question " + questionLabel, "", "");
			}
			break;
		case "text":
			String textFieldObject = qtnLabelUnderEachSectionObject2 + "//parent::span//parent::div//input";
			driver.findElement(By.xpath(textFieldObject.replace("%s", String.valueOf(sectionIndex)).replace("%n",
					String.valueOf(qtnIndex)))).sendKeys(expectedAnswer);
			break;
			
		case "currency":
			String currencyFieldObject = qtnLabelUnderEachSectionObject2 + "//parent::span//parent::div//input";
			driver.findElement(By.xpath(currencyFieldObject.replace("%s", String.valueOf(sectionIndex)).replace("%n",
					String.valueOf(qtnIndex)))).sendKeys(expectedAnswer);
			break;

		default:
			break;
		}

	}

	private void validateEditedDeclarationValues(WebDriver driver, int sectionIndex, int qtnIndex, String dataType,
			String qtnLabel, HashMap<String, String> afterEditMap, HashMap<String, String> beforeEditMap)
			throws Exception {
		String qtnLabelUnderEachSectionObject2 = "(//div[@class[contains(.,'form-container tf-block-%s active-section')]]//form/div[%n]//div[@class[contains(.,'col')]])[1]";
		String expectedAnswer = "";
		switch (dataType.toLowerCase()) {
		case "radiobtn":
			String radioButtonObject = qtnLabelUnderEachSectionObject2
					+ "//following-sibling::radio-button-selection//button[@class[contains(.,'active')]]";
			String selectedRadioButton = driver.findElement(By.xpath(radioButtonObject
					.replace("%s", String.valueOf(sectionIndex)).replace("%n", String.valueOf(qtnIndex)))).getText();
			expectedAnswer = afterEditMap.get(qtnLabel);
			/*
			 * if(!selectedRadioButton.trim().equalsIgnoreCase(expectedAnswer)){
			 * addExceptionToReport("Expected Radio button "
			 * +expectedAnswer+" is not selected for the question whose index is "
			 * +qtnIndex+" under section "+sectionIndex, "", ""); }
			 */
			break;

		case "multiselect":
			String multiSelectObject = qtnLabelUnderEachSectionObject2 + "//following-sibling::div/button";
			String revisedMutliSelectObject = multiSelectObject.replace("%s", String.valueOf(sectionIndex))
					.replace("%n", String.valueOf(qtnIndex));
			String displayedSelectedOption = "";
			int displayedSelectOptionCount = driver.findElements(By.xpath(revisedMutliSelectObject)).size();
			if (qtnLabel.contains("Race")) {
				expectedAnswer = afterEditMap.get("Race");
			} else {
				expectedAnswer = afterEditMap.get(qtnLabel);
			}

			String expectedMutilSelectOpt = expectedAnswer;
			String expectedTextOpt = "";
			String expectedTextVal = "";

			if (expectedAnswer.contains(":")) {
				expectedMutilSelectOpt = expectedAnswer.split(":")[0];
				expectedTextOpt = expectedAnswer.split(":")[1].split("%")[1].split("_")[0];
				expectedTextVal = expectedAnswer.split(":")[1].split("%")[1].split("_")[1];
			}

			if (beforeEditMap.keySet().contains(qtnLabel)) {
				expectedMutilSelectOpt = expectedMutilSelectOpt + "~" + beforeEditMap.get(qtnLabel).split(":")[0];
			}

			List<String> selectOpts = new ArrayList<String>();
			for (String opt : expectedMutilSelectOpt.split("~")) {
				selectOpts.add(opt);
			}

			for (int selectOptionIndex = 1; selectOptionIndex <= displayedSelectOptionCount; selectOptionIndex++) {
				if (driver
						.findElement(By.xpath(revisedMutliSelectObject + "[" + String.valueOf(selectOptionIndex) + "]"))
						.getAttribute("class").contains("selected")) {
					displayedSelectedOption = driver
							.findElement(
									By.xpath(revisedMutliSelectObject + "[" + String.valueOf(selectOptionIndex) + "]"))
							.getText();

					if (!selectOpts.contains(displayedSelectedOption)) {
						// addExceptionToReport("Option
						// "+displayedSelectedOption+" is not expected to be
						// selected under the question whose index is
						// "+qtnIndex, "", "");
					} else {
						selectOpts.remove(displayedSelectedOption);
					}
				}
			}

			if (selectOpts.size() > 0) {
				String opt = "";
				for (int optIndex = 0; optIndex > selectOpts.size(); optIndex++) {
					opt = opt + "&" + selectOpts.get(optIndex);
				}
				// addExceptionToReport("Expected options "+opt+" is not
				// selected under question whose index is "+qtnIndex, "", "");
			}

			if (expectedTextVal.length() > 0) {
				String optionTextObject = "(//div[@class[contains(.,'form-container tf-block-%s active-section')]]//form/div[%n]//div[@data-Type='text'])[1]//label";
				String optionTextValObject = "(//div[@class[contains(.,'form-container tf-block-%s active-section')]]//form/div[%n]//div[@data-Type='text'])[1]//input";
				String displayedQtnLabel = driver.findElement(By.xpath(optionTextObject
						.replace("%s", String.valueOf(sectionIndex)).replace("%n", String.valueOf(qtnIndex))))
						.getText();
				if (!displayedQtnLabel.trim().equalsIgnoreCase(expectedTextOpt.trim())) {
					// addExceptionToReport("Text Option field label
					// "+displayedQtnLabel+" is not matching with expected value
					// "+expectedTextOpt, "", "");
				}
				String displayedValue = driver.findElement(By.xpath(optionTextValObject
						.replace("%s", String.valueOf(sectionIndex)).replace("%n", String.valueOf(qtnIndex))))
						.getText();
				if (!displayedValue.trim().equalsIgnoreCase(expectedTextVal)) {
					// addExceptionToReport("Expected Text Value
					// "+expectedTextVal+" is not matching with displayed value
					// "+displayedValue+" under the section "+sectionIndex, "",
					// "");
				}
			}
			break;

		/*case "label":
			// String labelObject = "(//div[@class[contains(.,'form-container
			// tf-block-%s
			// active-section')]]//form//div[@class[contains(.,'question')]]/div/div/div/div)[%n]//label/p";
			String labelObject = qtnLabelUnderEachSectionObject2 + "//p";
			String questionLabel = getQuestionLabel(driver, sectionIndex, qtnIndex);
			/*
			 * qtnIndex = qtnIndex+1; String displayedValue =
			 * driver.findElement(By.xpath(labelObject.replace("%s",
			 * String.valueOf(sectionIndex)).replace("%n",
			 * String.valueOf(qtnIndex)))).getText();
			 * System.out.println("Displayed value under label?????"
			 * +displayedValue);
			 */
			/*if (questionLabel.contains(expectedAnswer) == false) {
				addExceptionToReport("Expected answer is not displayed under question " + questionLabel, "", "");
			}
			break;*/
		case "text":
			String questionLabel = getQuestionLabel(driver, sectionIndex, qtnIndex);
			String textFieldObject = qtnLabelUnderEachSectionObject2 + "//parent::span//parent::div//input";
			if (!driver.findElement(By.xpath(textFieldObject.replace("%s", String.valueOf(sectionIndex)).replace("%n",
					String.valueOf(qtnIndex)))).getText().trim().equalsIgnoreCase(expectedAnswer)) {
				// addExceptionToReport("Expected value "+expectedAnswer+" is
				// not displayed under question whose index is "+questionLabel,
				// "", "");
			}
			break;

		default:
			break;
		}

	}

	private void clickOnOkButtonAfterEachQuestion(WebDriver driver, Integer qusIndex, Integer sectionQtnCount)
			throws Exception {

		try {
			WebElement okButton = driver.findElement(By.xpath(OkayBUtton1.replace("%s", String.valueOf(qusIndex))
					.replace("%n", String.valueOf(sectionQtnCount))));
			if (okButton.isDisplayed() == false) {
				utill.scrollToElement(okButton);
			}
			okButton.click();
			System.out.println("Clicked Okay Button"+qusIndex+"sectionQtnCount"+sectionQtnCount);
			Thread.sleep(2000);
			System.out.println("Clicked>>>>");
//			Util.waitTimeForSpinner(driver);
		} catch (Exception e) {
			// addExceptionToReport("OK Button is not displayed after question
			// whose Index is "+qusIndex, "", "");
		}

	}

	private int getQuestionCount(WebDriver driver) {
		// return
		// driver.findElements(By.xpath("//div[@class='ps-content']/div")).size();
		return driver.findElements(By.xpath(qtnCountObject)).size();
	}

	private int getQuestionCountUnderEachSection(WebDriver driver, int sectionIndex) {
		// String qtnCountObject = "//div[@class[contains(.,'form-container
		// tf-block-%s
		// active-section')]]//form//div[@class[contains(.,'question')]]/div/div/div/div";
		return driver.findElements(By.xpath(qtnCountObjectUnderEachSection.replace("%s", String.valueOf(sectionIndex))))
				.size();
	}

	private String getQuestionLabel(WebDriver driver, int sectionIndex, int qtnIndex) {
		// String qtnLabelObject = "//div[@class[contains(.,'form-container
		// tf-block-%s active-section')]]//form/div[%n]//label";
		return driver.findElement(By.xpath(
				qtnLabelObject.replace("%s", String.valueOf(sectionIndex)).replace("%n", String.valueOf(qtnIndex))))
				.getText();
	}



	public static WebElement clickOnElementWhenElementIsReady(final WebElement locator) throws Exception {
		WebDriver driver = DriverFactory.getDriver();
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(180, TimeUnit.SECONDS)
				.pollingEvery(250, TimeUnit.MILLISECONDS).ignoring(Exception.class);

		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {

				locator.click();
				return locator;
			}
		});

		return element;
	};

	private boolean isDataTypeAsAttribute(WebDriver driver, WebElement ele) throws Exception {
		if (utill.fetchAllAttributesOfWebElement(driver, ele).contains("data-type")) {
			return true;
		}
		return false;
	}
	
	private void verifyDemographicQtn(WebDriver driver, String expectedContent, int qtnIndex, int subQtnIndex)
			throws Exception {
		String demoQtn = "(//div[@class='form-container tf-block-%s active-section']//form//label)[%n]//p";
		WebElement we = driver.findElement(
				By.xpath(demoQtn.replace("%s", String.valueOf(qtnIndex)).replace("%n", String.valueOf(subQtnIndex))));
		utill.scrollToElement(we);
		String displayedContent = we.getText();
		System.out.println("Displayed Demo content>>>" + displayedContent);
		if (!displayedContent.trim().equalsIgnoreCase(expectedContent)) {
			addExceptionToReport("Expected Demographic content " + expectedContent
					+ " is not matching with displaye content " + displayedContent, "", "");
		} else
			System.out.println("DEmographic tested static text");

	}

	
	private void selectRadioButton(WebDriver driver, String radioButtonXpath) throws Exception {
		try {
			driver.findElement(By.xpath(radioButtonXpath)).click();
			Thread.sleep(2000);
		} catch (Exception e) {
			addExceptionToReport("Exception seen while clicking radio button " + e.toString(), "", "");
		}
	}
	
	
	private void setValuesNew(WebDriver driver, int qtnIndex, HashMap<String, String> answerMap) throws Exception {
		String qtnLabelXpath = "//div[@class='form-container tf-block-%s active-section']//form//label";
		String qtnIndexObject = "(//div[@class='form-container tf-block-%s active-section']//form//label)[%q]";
		String qtnDataTypeXpath = "(//div[@class='form-container tf-block-%s active-section']//form//label)[%q]/ancestor::div[@data-type]";
		String radioButtonXpath = qtnDataTypeXpath + "//button[contains(.,'%v')]";
		int subQtnCount = driver.findElements(By.xpath(qtnLabelXpath.replace("%s", String.valueOf(qtnIndex)))).size();
		System.out.println("Qbqtn Count>>>" + subQtnCount);
		for (int subQtnIndex = 1; subQtnIndex <= subQtnCount; subQtnIndex++) {

			utill.scrollToElement(driver.findElement(By.xpath(qtnIndexObject.replace("%s", String.valueOf(qtnIndex))
					.replace("%q", String.valueOf(subQtnIndex)))));
			// scrollTillActiveQtnIsDisplayed(driver,qtnIndex,subQtnIndex);
			String qtnLabel = driver.findElement(By.xpath(
					qtnIndexObject.replace("%s", String.valueOf(qtnIndex)).replace("%q", String.valueOf(subQtnIndex))))
					.getText();
//			System.out.println("QtnLabel Declartion>>"+qtnLabel);
			WebElement dataTypeElement = driver.findElement(By.xpath(qtnDataTypeXpath
					.replace("%s", String.valueOf(qtnIndex)).replace("%q", String.valueOf(subQtnIndex))));
			String dataType = "";
			if (isDataTypeAsAttribute(driver, dataTypeElement)) {
				dataType = driver.findElement(By.xpath(qtnDataTypeXpath.replace("%s", String.valueOf(qtnIndex))
						.replace("%q", String.valueOf(subQtnIndex)))).getAttribute("data-type");
			} else {
				dataType = "checkbox";
			}

//			System.out.println("AnswerMap>>>>"+answerMap);
			
			String answer = answerMap.get(qtnLabel);

			if (qtnLabel.trim().contains("Your demographic information")) {
				verifyDemographicQtn(driver, answerMap.get(qtnLabel), qtnIndex, subQtnIndex + 1);
				break;
			} else {
				/*
				 * if(answer == null || answer.length() == 0){ answer = "No"; }
				 */
				switch (dataType.toLowerCase()) {
				case "radiobtn":
					selectRadioButton(driver, radioButtonXpath.replace("%s", String.valueOf(qtnIndex))
							.replace("%q", String.valueOf(subQtnIndex)).replace("%v", String.valueOf(answer)));
					break;

				case "multiselect":
				case "checkbox":
					selectCheckBox(driver, qtnIndex, answer);
					break;

				case "text":
				case "currency":
					subQtnIndex = subQtnIndex + 1;
					enterValuesInTextField(driver, qtnIndex, answer);
					break;

				// selectMultiSelect(driver,qtnIndex,answer);
				}
			}

		}
		// clickOnOkButtonAfterEachQuestion(driver,qtnIndex,subQtnCount);
	}

	private void enterValuesInTextField(WebDriver driver, int sectionIndex, String value) {
		String textXpath = "//div[@class='form-container tf-block-%s active-section']//input";
		driver.findElement(By.xpath(textXpath.replace("%s", String.valueOf(sectionIndex)))).sendKeys(value);
	}
	
	private void selectCheckBox(WebDriver driver, int sectionIndex, String expectedMutilSelectOpt) {
		String buttonXpath = "//div[@class='form-container tf-block-%s active-section']//button[text()='%v']";
		for (String opt : expectedMutilSelectOpt.split("~")) {
			driver.findElement(By.xpath(buttonXpath.replace("%s", String.valueOf(sectionIndex)).replace("%v", opt)))
					.click();
		}
	}
	
	@Override
	public void checkPage() {

		// TODO Auto-generated method stub

	}

}
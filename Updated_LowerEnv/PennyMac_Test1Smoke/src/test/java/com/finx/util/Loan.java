package com.finx.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Loan {

	HashMap<String,String> loansMap = new HashMap<String,String>();
	private Map<String, String> addressDetails = new HashMap<>();
	
	@JsonProperty("loan.LoanNumber")
	String loanNumber;

	public String getLoanNumber() {
		return loanNumber;
	}

	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
	}

//	@JsonProperty("4158")
//	String pacificIslander ;
//	public String getDeclationPacific() {
//		// TODO Auto-generated method stub
//		System.out.println("Ncompass Data==>" + this.pacificIslander);
//		return pacificIslander;
//	}
//	public void setDeclationPacific(String pacificIslander) {
//		this.pacificIslander = pacificIslander;
//	}

	@JsonProperty("loan.Guid")
	String loanGuid;

	public String getLoanGuid() {
		return loanGuid;
	}

	public void setLoanGuid(String loanGuid) {
		this.loanGuid = loanGuid;
	}

	@JsonProperty("11")
	String address11;

	public String getaddress11() {
		return address11;
	}

	public void setaddress11(String address) {
		this.address11 = address;
	}

//	@JsonProperty("12")
//	String address12;
//
//	public String getaddress12() {
//		return address12;
//	}
//
//	public void setaddress12(String address12) {
//		this.address12 = address12;
//	}

/*	@JsonProperty("14")
	String address14;

	public String getaddress14() {
		return address14;
	}

	public void setaddress14(String address14) {
		this.address14 = address14;
	}

/*	@JsonProperty("15")
	String address15;

	public String getaddress15() {
		return address15;
	}

	public void setaddress15(String address15) {
		this.address15 = address15;
	}

	/*@JsonProperty("13")
	String address13;

	public String getaddress13() {
		try {
			return address13;
		} catch (Exception e) {
			System.out.println("catch");
			return " ";
		}

	}

	public void setaddress13(String address13) {
		this.address13 = address13;
	}

	@JsonProperty("cX.BROKERLOANSTATUS")
	String BROKERLOANSTATUS;

	public String getBROKERLOANSTATUS() {
		return BROKERLOANSTATUS;

	}

	public void setBROKERLOANSTATUS(String BROKERLOANSTATUS) {
		this.BROKERLOANSTATUS = BROKERLOANSTATUS;
	}

	@JsonProperty("1109")
	String LoanAmount;

	public String getLoanAmount() {
		return LoanAmount;

	}

	public void setLoanAmount(String LoanAmount) {
		this.LoanAmount = LoanAmount;
	}

/*	@JsonProperty("4000")
	String FirstName;

	public String getFirstName() {
		return FirstName;

	}/

	public void setFirstName(String FirstName) {
		this.FirstName = FirstName;
	}*/

/*	@JsonProperty("4004")
	String BorroweFirstName;

	public String getBorrowerFirstName() {
		return BorroweFirstName;

	}

	public void setBorrowerFirstName(String FirstName) {
		this.BorroweFirstName = FirstName;
	}*/

/*	@JsonProperty("4001")
	String MiddleName;

	public String getMiddleName() {
		return MiddleName;

	}

	public void setMiddleName(String MiddleName) {
		this.MiddleName = MiddleName;
	}

	@JsonProperty("4005")
	String BorroweMiddleName;

	public String getBorrowerMiddleName() {
		return BorroweMiddleName;

	}

	public void setBorrowerMiddleName(String MiddleName) {
		this.BorroweMiddleName = MiddleName;
	}

	/*@JsonProperty("4002")
	String LastName;

	public String getLastName() {
		return LastName;

	}

	public void setLastName(String LastName) {
		this.LastName = LastName;
	}*/

/*	@JsonProperty("4006")
	String BorrowerLastName;

	public String getBorrowerLastName() {
		return BorrowerLastName;

	}

	public void setBorrowerLastName(String LastName) {
		this.BorrowerLastName = LastName;
	}*

	/*@JsonProperty("4003")
	String suffix;

	public String getsuffix() {
		return suffix;

	}

	public void setsuffix(String suffix) {
		this.suffix = suffix;
	}

	@JsonProperty("4007")
	String borrowerSuffix;

	public String getBorrowerSuffix() {
		return borrowerSuffix;

	}

	public void setBorrowersuffix(String suffix) {
		this.borrowerSuffix = suffix;
	}

	@JsonProperty("65")
	String SSN;

	public String getSSN() {
		return SSN;

	}

	public void setSSN(String SSN) {
		this.SSN = SSN;
	}

	@JsonProperty("66")
	String phoneNumber;

	public String getphoneNumber() {
		return phoneNumber;

	}

	public void setphoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/*@JsonProperty("1402")
	String DOB;

	public String getDOB() {
		return DOB;

	}

	public void setDOB(String DOB) {
		this.DOB = DOB;
	}

	@JsonProperty("1240")
	String email;

	public String getEmail() {
		return email;

	}

	public void setEmail(String email) {
		this.email = email;
	}*/

	/*@JsonProperty("52")
	String MaritalStatus;

	public String getMaritalStatus() {
		return MaritalStatus;

	}

	public void setMaritalStatus(String MaritalStatus) {
		this.MaritalStatus = MaritalStatus;
	}

	/*@JsonProperty("54")
	String Ages;

	public String getAges() {
		return Ages;

	}

	public void setAges(String Ages) {
		this.Ages = Ages;
	}*/

	/*@JsonProperty("53")
	String Dependents;

	public String getDependents() {
		return Dependents;

	}

	public void setDependents(String Dependents) {
		this.Dependents = Dependents;
	}

	@JsonProperty("749")
	String StatusDate;

	public String getStatusDate() {
		return StatusDate;

	}

	public void setStatusDate(String StatusDate) {
		this.StatusDate = StatusDate;
	}

	/*@JsonProperty("136")
	String PurchasePrice;

	public String getPurchasePrice() {
		return PurchasePrice;

	}

	public void setPurchasePrice(String PurchasePrice) {
		this.PurchasePrice = PurchasePrice;
	}*/

	/*@JsonProperty("356")
	String AppraisedValue;

	public String getAppraisedValue() {
		return AppraisedValue;

	}

	public void setAppraisedValue(String AppraisedValue) {
		this.AppraisedValue = AppraisedValue;
	}

	@JsonProperty("3")
	String InterestRate;

	public String getInterestRate() {
		return InterestRate;

	}

	public void setInterestRate(String InterestRate) {
		this.InterestRate = InterestRate;
	}

	@JsonProperty("608")
	String AmortizationType;

	public String getAmortizationType() {
		return AmortizationType;

	}

	public void setAmortizationType(String AmortizationType) {
		this.AmortizationType = AmortizationType;
	}

	@JsonProperty("1172")
	String MortgageAppliedfor;

	public String getMortgageAppliedfor() {
		return MortgageAppliedfor;

	}

	public void setMortgageAppliedfor(String MortgageAppliedfor) {
		this.MortgageAppliedfor = MortgageAppliedfor;
	}

	@JsonProperty("4")
	String NOofMonths;

	public String getNOofMonths() {
		return NOofMonths;

	}

	public void setNOofMonths(String NOofMonths) {
		this.NOofMonths = NOofMonths;
	}

	@JsonProperty("995")
	String ArmType;

	public String getArmType() {
		return ArmType;

	}

	public void setArmType(String ArmType) {
		this.ArmType = ArmType;
	}

	/*@JsonProperty("16")
	String NoofUnits;

	public String getStringNoofUnits() {
		return NoofUnits;
	}

	public void setStringNoofUnits(String StringNoofUnits) {
		this.NoofUnits = StringNoofUnits;
	}

	@JsonProperty("19")
	String PurposeofLoan;

	public String getPurposeofLoan() {
		return PurposeofLoan;
	}

	public void setPurposeofLoan(String PurposeofLoan) {
		this.PurposeofLoan = PurposeofLoan;
	}*/

	/*@JsonProperty("1041")
	String SubjectPropertyType;

	public String getSubjectPropertyType() {
		return SubjectPropertyType;
	}

	public void setSubjectPropertyType(String SubjectPropertyType) {
		this.SubjectPropertyType = SubjectPropertyType;
	}

	@JsonProperty("18")
	String YearBuilt;

	public String getYearBuilt() {
		return YearBuilt;
	}

	public void setYearBuilt(String YearBuilt) {
		this.YearBuilt = YearBuilt;
	}

	/*@JsonProperty("1811")
	String Propertywillbeusedas;

	public String getPropertywillbeusedas() {
		return Propertywillbeusedas;
	}

	public void setPropertywillbeusedas(String Propertywillbeusedas) {
		this.Propertywillbeusedas = Propertywillbeusedas;
	}*/

	// Adding new fields for 1003 Income and Expense

//	@JsonProperty("101")
//	String BaseEmployementBorrower;
//
//	public String getBaseEmployementBorrower() {
//		return BaseEmployementBorrower;
//	}
//
//	public void setBaseEmployementBorrower(String BaseEmployementBorrower) {
//		this.BaseEmployementBorrower = BaseEmployementBorrower;
//	}
//
//	@JsonProperty("110")
//	String BaseEmployementCoBorrower;
//
//	public String getBaseEmployementCoBorrower() {
//		return BaseEmployementCoBorrower;
//	}
//
//	public void setBaseEmployementCoBorrower(String BaseEmployementCoBorrower) {
//		this.BaseEmployementCoBorrower = BaseEmployementCoBorrower;
//	}
//
//	@JsonProperty("102")
//	String OvertimeBorrower;
//
//	public String getOvertimeBorrower() {
//		return OvertimeBorrower;
//	}
//
//	public void setOvertimeBorrower(String OvertimeBorrower) {
//		this.OvertimeBorrower = OvertimeBorrower;
//	}
//
//	@JsonProperty("111")
//	String OvertimeCoBorrower;
//
//	public String getOvertimeCoBorrower() {
//		return OvertimeCoBorrower;
//	}
//
//	public void setOvertimeCoBorrower(String OvertimeCoBorrower) {
//		this.OvertimeCoBorrower = OvertimeCoBorrower;
//	}
//
//	@JsonProperty("103")
//	String BonusesBorrower;
//
//	public String getBonusesBorrower() {
//		return BonusesBorrower;
//	}
//
//	public void setBonusesBorrower(String BonusesBorrower) {
//		this.BonusesBorrower = BonusesBorrower;
//	}
//
//	@JsonProperty("112")
//	String BonusesCoBorrower;
//
//	public String getBonusesCoBorrower() {
//		return BonusesCoBorrower;
//	}
//
//	public void setBonusesCoBorrower(String BonusesCoBorrower) {
//		this.BonusesCoBorrower = BonusesCoBorrower;
//	}
//
//	@JsonProperty("104")
//	String CommissionsBorrower;
//
//	public String getCommissionsBorrower() {
//		return CommissionsBorrower;
//	}
//
//	public void setCommissionsBorrower(String CommissionsBorrower) {
//		this.CommissionsBorrower = CommissionsBorrower;
//	}
//
//	@JsonProperty("113")
//	String CommissionsCoBorrower;
//
//	public String getCommissionsCoBorrower() {
//		return CommissionsCoBorrower;
//	}
//
//	public void setCommissionsCoBorrower(String CommissionsCoBorrower) {
//		this.CommissionsCoBorrower = CommissionsCoBorrower;
//	}
//
//	@JsonProperty("105")
//	String DevidendsBorrower;
//
//	public String getDevidendsBorrower() {
//		return DevidendsBorrower;
//	}
//
//	public void setDevidendsBorrower(String DevidendsBorrower) {
//		this.DevidendsBorrower = DevidendsBorrower;
//	}

	/*@JsonProperty("114")
	String DevidendsCoBorrower;

	public String getDevidendsCoBorrower() {
		return DevidendsCoBorrower;
	}

	public void setDevidendsCoBorrower(String DevidendsCoBorrower) {
		this.DevidendsCoBorrower = DevidendsCoBorrower;
	}

	@JsonProperty("106")
	String NetRentalIncomeBorrower;

	public String getNetRentalIncomeBorrower() {
		return NetRentalIncomeBorrower;
	}

	public void setNetRentalIncomeBorrower(String NetRentalIncomeBorrower) {
		this.NetRentalIncomeBorrower = NetRentalIncomeBorrower;
	}

	@JsonProperty("115")
	String NetRentalIncomeCoBorrower;

	public String getNetRentalIncomeCoBorrower() {
		return NetRentalIncomeCoBorrower;
	}

	public void setNetRentalIncomeCoBorrower(String NetRentalIncomeCoBorrower) {
		this.NetRentalIncomeCoBorrower = NetRentalIncomeCoBorrower;
	}

	@JsonProperty("119")
	String Rent;

	public String getRent() {
		return Rent;
	}

	public void setRent(String Rent) {
		this.Rent = Rent;
	}

	@JsonProperty("120")
	String FirstMortgagePresent;

	public String getFirstMortgagePresent() {
		return FirstMortgagePresent;
	}

	public void setFirstMortgagePresent(String FirstMortgagePresent) {
		this.FirstMortgagePresent = FirstMortgagePresent;
	}

	@JsonProperty("121")
	String OtherFinancingPresent;

	public String getOtherFinancingPresent() {
		return OtherFinancingPresent;
	}

	public void setOtherFinancingPresent(String OtherFinancingPresent) {
		this.OtherFinancingPresent = OtherFinancingPresent;
	}

	@JsonProperty("122")
	String HazardInsurancePresent;

	public String getHazardInsurancePresent() {
		return HazardInsurancePresent;
	}

	public void setHazardInsurancePresent(String HazardInsurancePresent) {
		this.HazardInsurancePresent = HazardInsurancePresent;
	}

	@JsonProperty("230")
	String HazardInsuranceProposed;

	public String getHazardInsuranceProposed() {
		return HazardInsuranceProposed;
	}

	public void setHazardInsuranceProposed(String HazardInsuranceProposed) {
		this.HazardInsuranceProposed = HazardInsuranceProposed;
	}

	@JsonProperty("123")
	String RealEstateTaxesPresent;

	public String getRealEstateTaxesPresent() {
		return RealEstateTaxesPresent;
	}

	public void setRealEstateTaxesPresent(String RealEstateTaxesPresent) {
		this.RealEstateTaxesPresent = RealEstateTaxesPresent;
	}

	@JsonProperty("1405")
	String RealEstateTaxesProposed;

	public String getRealEstateTaxesProposed() {
		return RealEstateTaxesProposed;
	}

	public void setRealEstateTaxesProposed(String RealEstateTaxesProposed) {
		this.RealEstateTaxesProposed = RealEstateTaxesProposed;
	}

	@JsonProperty("124")
	String MortgageInsurancePresent;

	public String getMortgageInsurancePresent() {
		return MortgageInsurancePresent;
	}

	public void setMortgageInsurancePresent(String MortgageInsurancePresent) {
		this.MortgageInsurancePresent = MortgageInsurancePresent;
	}

	@JsonProperty("232")
	String MortgageInsuranceProposed;

	public String getMortgageInsuranceProposed() {
		return MortgageInsuranceProposed;
	}

	public void setMortgageInsuranceProposed(String MortgageInsuranceProposed) {
		this.MortgageInsuranceProposed = MortgageInsuranceProposed;
	}

	@JsonProperty("125")
	String HOADuesPresent;

	public String getHOADuesPresent() {
		return HOADuesPresent;
	}

	public void setHOADuesPresent(String HOADuesPresent) {
		this.HOADuesPresent = HOADuesPresent;
	}

	@JsonProperty("126")
	String OtherPresent;

	public String getOtherPresent() {
		return OtherPresent;
	}

	public void setOtherPresent(String OtherPresent) {
		this.OtherPresent = OtherPresent;
	}

	public String getMethodName(String string) {

		HashMap<String, String> fieldAndGetterMethod = new HashMap<String, String>();
		fieldAndGetterMethod.put("Street Address (Optional)", "getaddress11()");
		fieldAndGetterMethod.put("City (Optional)", "getaddress12()");
		fieldAndGetterMethod.put("Zip (Optional)", "getaddress14()");
		fieldAndGetterMethod.put("County (Optional)", "getaddress15()");
		fieldAndGetterMethod.put("No. of Units (Optional)", "getaddress13()");

		return fieldAndGetterMethod.get(string).trim();

	}

	// Declarations Set and Get properties

	@JsonProperty("169")
	String hasOutstandingJudgementsBorrower;

	public String gethasOutstandingJudgementsBorrower() {
		return hasOutstandingJudgementsBorrower;
	}

	public void sethasOutstandingJudgementsBorrower(String hasOutstandingJudgementsBorrower) {
		this.hasOutstandingJudgementsBorrower = hasOutstandingJudgementsBorrower;
	}

	@JsonProperty("175")
	String hasOutstandingJudgementsCoBorrower;

	public String gethasOutstandingJudgementsCoBorrower() {
		return hasOutstandingJudgementsCoBorrower;
	}

	public void sethasOutstandingJudgementsCoBorrower(String hasOutstandingJudgementsCoBorrower) {
		this.hasOutstandingJudgementsCoBorrower = hasOutstandingJudgementsCoBorrower;
	}

	@JsonProperty("265")
	String isDeclaredBankruptBorrower;

	public String getisDeclaredBankruptBorrower() {
		return isDeclaredBankruptBorrower;
	}

	public void setisDeclaredBankruptBorrower(String isDeclaredBankruptBorrower) {
		this.isDeclaredBankruptBorrower = isDeclaredBankruptBorrower;
	}

	@JsonProperty("266")
	String isDeclaredBankruptCoBorrower;

	public String getisDeclaredBankruptCoBorrower() {
		return isDeclaredBankruptCoBorrower;
	}

	public void setisDeclaredBankruptCoBorrower(String isDeclaredBankruptCoBorrower) {
		this.isDeclaredBankruptCoBorrower = isDeclaredBankruptCoBorrower;
	}

	/*@JsonProperty("170")
	String hasForeclosureBorrower;

	public String gethasForeclosureBorrower() {
		return hasForeclosureBorrower;
	}

	public void sethasForeclosureBorrower(String hasForeclosureBorrower) {
		this.hasForeclosureBorrower = hasForeclosureBorrower;
	}*/

//	@JsonProperty("176")
//	String hasForeclosureCoBorrower;
//
//	public String gethasForeclosureCoBorrower() {
//		return hasForeclosureCoBorrower;
//	}
//
//	public void sethasForeclosureCoBorrower(String hasForeclosureCoBorrower) {
//		this.hasForeclosureCoBorrower = hasForeclosureCoBorrower;
//	}

	/*@JsonProperty("172")
	String isPartyLawsuitBorrower;

	public String getisPartyLawsuitBorrower() {
		return isPartyLawsuitBorrower;
	}

	public void setisPartyLawsuitBorrower(String isPartyLawsuitBorrower) {
		this.isPartyLawsuitBorrower = isPartyLawsuitBorrower;
	}

	@JsonProperty("178")
	String isPartyLawsuitCoBorrower;

	public String getisPartyLawsuitCoBorrower() {
		return isPartyLawsuitCoBorrower;
	}

	public void setisPartyLawsuitCoBorrower(String isPartyLawsuitCoBorrower) {
		this.isPartyLawsuitCoBorrower = isPartyLawsuitCoBorrower;
	}

	@JsonProperty("1057")
	String hasForeclosureJudgmentBorrower;

	public String gethasForeclosureJudgmentBorrower() {
		return hasForeclosureJudgmentBorrower;
	}

	public void sethasForeclosureJudgmentBorrower(String hasForeclosureJudgmentBorrower) {
		this.hasForeclosureJudgmentBorrower = hasForeclosureJudgmentBorrower;

	}

	@JsonProperty("1197")
	String hasForeclosureJudgmentCoBorrower;

	public String gethasForeclosureJudgmentCoBorrower() {
		return hasForeclosureJudgmentCoBorrower;
	}

	public void sethasForeclosureJudgmentCoBorrower(String hasForeclosureJudgmentCoBorrower) {
		this.hasForeclosureJudgmentCoBorrower = hasForeclosureJudgmentCoBorrower;

	}

	@JsonProperty("463")
	String isLoanGuaranteeBorrower;

	public String getisLoanGuaranteeBorrower() {
		return isLoanGuaranteeBorrower;
	}

	public void setisLoanGuaranteeBorrower(String isLoanGuaranteeBorrower) {
		this.isLoanGuaranteeBorrower = isLoanGuaranteeBorrower;

	}

	@JsonProperty("464")
	String isLoanGuaranteeCoBorrower;

	public String getisLoanGuaranteeCoBorrower() {
		return isLoanGuaranteeCoBorrower;
	}

	public void setisLoanGuaranteeCoBorrower(String isLoanGuaranteeCoBorrower) {
		this.isLoanGuaranteeCoBorrower = isLoanGuaranteeCoBorrower;

	}

	@JsonProperty("173")
	String isObligatedToPayMaintenanceBorrower;

	public String getisObligatedToPayMaintenanceBorrower() {
		return isObligatedToPayMaintenanceBorrower;
	}

	public void setisObligatedToPayMaintenanceBorrower(String isObligatedToPayMaintenanceBorrower) {
		this.isObligatedToPayMaintenanceBorrower = isObligatedToPayMaintenanceBorrower;

	}

	@JsonProperty("179")
	String isObligatedToPayMaintenanceCoBorrower;

	public String getisObligatedToPayMaintenanceCoBorrower() {
		System.out.println("Ncompass Data==>" + this.isObligatedToPayMaintenanceCoBorrower);

		return this.isObligatedToPayMaintenanceCoBorrower;
	}

	public void setisObligatedToPayMaintenanceCoBorrower(String isObligatedToPayMaintenanceCoBorrower) {
		this.isObligatedToPayMaintenanceCoBorrower = isObligatedToPayMaintenanceCoBorrower;

	}

	@JsonProperty("174")
	String isDownPaymentBorrowedBorrower;

	public String getisDownPaymentBorrowedBorrower() {
		return isDownPaymentBorrowedBorrower;
	}

	public void setisDownPaymentBorrowedBorrower(String isDownPaymentBorrowedBorrower) {
		this.isDownPaymentBorrowedBorrower = isDownPaymentBorrowedBorrower;

	}

	@JsonProperty("180")
	String isDownPaymentBorrowedCoBorrower;

	public String getisDownPaymentBorrowedCoBorrower() {
		return isDownPaymentBorrowedCoBorrower;
	}

	public void setisDownPaymentBorrowedCoBorrower(String isDownPaymentBorrowedCoBorrower) {
		this.isDownPaymentBorrowedCoBorrower = isDownPaymentBorrowedCoBorrower;

	}

	@JsonProperty("171")
	String isComakerEndorserBorrower;

	public String getisComakerEndorserBorrower() {
		System.out.println("isComakerEndorserBorrower:"+isComakerEndorserBorrower);
		return isComakerEndorserBorrower;
	}

	public void setisComakerEndorserBorrower(String isComakerEndorserBorrower) {
		this.isComakerEndorserBorrower = isComakerEndorserBorrower;

	}

	@JsonProperty("177")
	String isComakerEndorserCoBorrower;

	public String getisComakerEndorserCoBorrower() {
		return isComakerEndorserCoBorrower;
	}

	public void setisComakerEndorserCoBorrower(String isComakerEndorserCoBorrower) {
		this.isComakerEndorserCoBorrower = isComakerEndorserCoBorrower;

	}

	/*@JsonProperty("965")
	String isUSCitizenBorrower;

	public String getisUSCitizenBorrower() {
		return isUSCitizenBorrower;
	}

	public void setisUSCitizenBorrower(String isUSCitizenBorrower) {
		this.isUSCitizenBorrower = isUSCitizenBorrower;

	}*/

//	@JsonProperty("985")
//	String isUSCitizenCoBorrower;
//
//	public String getisUSCitizenCoBorrower() {
//		return isUSCitizenCoBorrower;
//	}
//
//	public void setisUSCitizenCoBorrower(String isUSCitizenCoBorrower) {
//		this.isUSCitizenCoBorrower = isUSCitizenCoBorrower;
//
//	}

	/*@JsonProperty("466")
	String isPermanentResidentBorrower;

	public String getisPermanentResidentBorrower() {
		return isPermanentResidentBorrower;
	}

	public void setisPermanentResidentBorrower(String isPermanentResidentBorrower) {
		this.isPermanentResidentBorrower = isPermanentResidentBorrower;

	}

	@JsonProperty("467")
	String isPermanentResidentCoBorrower;

	public String getisPermanentResidentCoBorrower() {
		return isPermanentResidentCoBorrower;
	}

	public void setisPermanentResidentCoBorrower(String isPermanentResidentCoBorrower) {
		this.isPermanentResidentCoBorrower = isPermanentResidentCoBorrower;

	}

	@JsonProperty("418")
	String isPrimaryResidenceBorrower;

	public String getisPrimaryResidenceBorrower() {
		return isPrimaryResidenceBorrower;
	}

	public void setisPrimaryResidenceBorrower(String isPrimaryResidenceBorrower) {
		this.isPrimaryResidenceBorrower = isPrimaryResidenceBorrower;

	}*/

//	@JsonProperty("1343")
//	String isPrimaryResidenceCoBorrower;
//
//	public String getisPrimaryResidenceCoBorrower() {
//		return isPrimaryResidenceCoBorrower;
//	}
//
//	public void setisPrimaryResidenceCoBorrower(String isPrimaryResidenceCoBorrower) {
//		this.isPrimaryResidenceCoBorrower = isPrimaryResidenceCoBorrower;
//
//	}

	/*@JsonProperty("403")
	String hasOwnershipInterestBorrower;

	public String gethasOwnershipInterestBorrower() {
		return hasOwnershipInterestBorrower;
	}

	public void sethasOwnershipInterestBorrower(String hasOwnershipInterestBorrower) {
		this.hasOwnershipInterestBorrower = hasOwnershipInterestBorrower;

	}

	@JsonProperty("1108")
	String hasOwnershipInterestCoBorrower;

	public String gethasOwnershipInterestCoBorrower() {
		return hasOwnershipInterestCoBorrower;
	}

	public void sethasOwnershipInterestCoBorrower(String hasOwnershipInterestCoBorrower) {
		this.hasOwnershipInterestCoBorrower = hasOwnershipInterestCoBorrower;

	}

	@JsonProperty("981")
	String propertyTypeBorrower;

	public String getpropertyTypeBorrower() {
		return propertyTypeBorrower;
	}

	public void setpropertyTypeBorrower(String propertyTypeBorrower) {
		this.propertyTypeBorrower = propertyTypeBorrower;

	}*/

//	@JsonProperty("1015")
//	String propertyTypeCoBorrower;
//
//	public String getpropertyTypeCoBorrower() {
//		return propertyTypeCoBorrower;
//	}
//
//	public void setpropertyTypeCoBorrower(String propertyTypeCoBorrower) {
//		this.propertyTypeCoBorrower = propertyTypeCoBorrower;
//
//	}

	/*@JsonProperty("1069")
	String homeTitleBorrower;

	public String gethomeTitleBorrower() {
		return homeTitleBorrower;
	}

	public void sethomeTitleBorrower(String homeTitleBorrower) {
		this.homeTitleBorrower = homeTitleBorrower;

	}*/

//	@JsonProperty("1070")
//	String homeTitleCoBorrower;
//
//	public String gethomeTitleCoBorrower() {
//		return homeTitleCoBorrower;
//	}
//
//	public void sethomeTitleCoBorrower(String homeTitleCoBorrower) {
//		this.homeTitleCoBorrower = homeTitleCoBorrower;
//
//	}
	// Transactions Part

//	@JsonProperty("2")
//	String loanAmountTransaction;
//
//	public String getloanAmountTransaction() {
//		return loanAmountTransaction;
//	}
//
//	public void setloanAmountTransaction(String loanAmountTransaction) {
//		this.loanAmountTransaction = loanAmountTransaction;
//
//	}

	/*@JsonProperty("136")
	String purchasePriceTransaction;

	public String getpurchasePriceTransaction() {
		return purchasePriceTransaction;
	}

	public void setpurchasePriceTransaction(String purchasePriceTransaction) {
		this.purchasePriceTransaction = purchasePriceTransaction;

	}*/

	@JsonProperty("137")
	String estimatedClosingCostTransaction;

	public String getestimatedClosingCost() {
		return estimatedClosingCostTransaction;
	}

	public void setestimatedClosingCost(String estimatedClosingCostTransaction) {
		this.estimatedClosingCostTransaction = estimatedClosingCostTransaction;

	}

	/*@JsonProperty("140")
	String subOrdinateFinancingTransaction;

	public String getsubOrdinateFinancingTransaction() {
		return subOrdinateFinancingTransaction;
	}

	public void setsubOrdinateFinancingTransaction(String subOrdinateFinancingTransaction) {
		this.subOrdinateFinancingTransaction = subOrdinateFinancingTransaction;

	}*/

	@JsonProperty("141")
	String otherCreditsTransaction;

	public String getotherCreditsTransaction() {
		return otherCreditsTransaction;
	}

	public void setotherCreditsTransaction(String otherCreditsTransaction) {
		this.otherCreditsTransaction = otherCreditsTransaction;

	}

	@JsonProperty("142")
	String cashFromToBorrowerTransaction;

	public String getcashFromToBorrowerTransaction() {
		return cashFromToBorrowerTransaction;
	}

	public void setcashFromToBorrowerTransaction(String cashFromToBorrowerTransaction) {
		this.cashFromToBorrowerTransaction = cashFromToBorrowerTransaction;

	}

	@JsonProperty("1045")
	String pmiMipFundingFeeTransaction;

	public String getpmiMipFundingFeeTransaction() {
		return pmiMipFundingFeeTransaction;
	}

	public void setpmiMipFundingFeeTransaction(String pmiMipFundingFeeTransaction) {
		this.pmiMipFundingFeeTransaction = pmiMipFundingFeeTransaction;

	}

	@JsonProperty("1092")
	String refinanceTransaction;

	public String getrefinanceTransaction() {
		return refinanceTransaction;
	}

	public void setrefinanceTransaction(String refinanceTransaction) {
		this.refinanceTransaction = refinanceTransaction;

	}

	@JsonProperty("1093")
	String discountTransaction;

	public String getdiscountTransaction() {
		return discountTransaction;
	}

	public void setdiscountTransaction(String discountTransaction) {
		this.discountTransaction = discountTransaction;

	}

	/*@JsonProperty("1109")
	String loanAmountExcludeTransaction;

	public String getloanAmountExcludeTransaction() {
		return loanAmountExcludeTransaction;
	}

	public void setloanAmountExcludeTransaction(String loanAmountExcludeTransaction) {
		this.loanAmountExcludeTransaction = loanAmountExcludeTransaction;

	}*/

	/// ********** Field ids of GMI HMDA ***********///

	/*@JsonProperty("1523")
	String ethnicityBorrower;

	public String getethnicityBorrower() {
		return ethnicityBorrower;
	}

	public void setethnicityBorrower(String ethnicityBorrower) {
		this.ethnicityBorrower = ethnicityBorrower;

	}

	@JsonProperty("1524")
	String racesBorrower;

	public String getracesBorrower() {
		return racesBorrower;
	}

	public void setracesBorrower(String racesBorrower) {
		this.racesBorrower = racesBorrower;

	}

	@JsonProperty("4144")
	String ethnicityHispanicOrLatinoBorrower;

	public String getethnicityHispanicOrLatinoBorrower() {
		return ethnicityHispanicOrLatinoBorrower;
	}

	public void setethnicityHispanicOrLatinoBorrower(String ethnicityHispanicOrLatinoBorrower) {
		this.ethnicityHispanicOrLatinoBorrower = ethnicityHispanicOrLatinoBorrower;

	}*/

//	@JsonProperty("4159")
//	String ethnicityHispanicOrLatinoCoBorrower;
//
//	public String getethnicityHispanicOrLatinoCoBorrower() {
//		return ethnicityHispanicOrLatinoCoBorrower;
//	}
//
//	public void setethnicityHispanicOrLatinoCoBorrower(String ethnicityHispanicOrLatinoCoBorrower) {
//		this.ethnicityHispanicOrLatinoCoBorrower = ethnicityHispanicOrLatinoCoBorrower;
//
//	}

	/*@JsonProperty("4194")
	String genderMaleBorrower;

	public String getgenderMaleBorrower() {
		return genderMaleBorrower;
	}

	public void setgenderMaleBorrower(String genderMaleBorrower) {
		this.genderMaleBorrower = genderMaleBorrower;

	}*/

	/*@JsonProperty("4198")
	String genderMaleCoBorrower;

	public String getgenderMaleCoBorrower() {
		return genderMaleCoBorrower;
	}

	public void setgenderMaleCoBorrower(String genderMaleCoBorrower) {
		this.genderMaleCoBorrower = genderMaleCoBorrower;

	}*/

//	@JsonProperty("1532")
//	String racesCoBorrower;
//
//	public String getracesCoBorrower() {
//		return racesCoBorrower;
//	}
//
//	public void setracesCoBorrower(String racesCoBorrower) {
//		this.racesCoBorrower = racesCoBorrower;
//
//	}
//
//	@JsonProperty("1531")
//	String ethnicityCoBorrower;
//
//	public String getethnicityCoBorrower() {
//		return ethnicityCoBorrower;
//	}
//
//	public void setethnicityCoBorrower(String ethnicityCoBorrower) {
//		this.ethnicityCoBorrower = ethnicityCoBorrower;
//
//	}

//	@JsonProperty("4126")
//	String raceAmericanIndianOrAlaskaNativeBorrower;
//
//	public String getraceAmericanIndianOrAlaskaNativeBorrower() {
//		return raceAmericanIndianOrAlaskaNativeBorrower;
//	}
//
//	public void setraceAmericanIndianOrAlaskaNativeBorrower(String raceAmericanIndianOrAlaskaNativeBorrower) {
//		this.raceAmericanIndianOrAlaskaNativeBorrower = raceAmericanIndianOrAlaskaNativeBorrower;
//
//	}

//	@JsonProperty("4137")
//	String raceAmericanIndianOrAlaskaNativeCoBorrower;
//
//	public String getraceAmericanIndianOrAlaskaNativeCoBorrower() {
//		return raceAmericanIndianOrAlaskaNativeCoBorrower;
//	}
//
//	public void setraceAmericanIndianOrAlaskaNativeCoBorrower(String raceAmericanIndianOrAlaskaNativeCoBorrower) {
//		this.raceAmericanIndianOrAlaskaNativeCoBorrower = raceAmericanIndianOrAlaskaNativeCoBorrower;
//
//	}
//
//	/// HUD field ids
//
//	@JsonProperty("28")
//	String HUD_loanPurposeDeclaration;
//
//	public String getHUD_loanPurposeDeclaration() {
//		return HUD_loanPurposeDeclaration;
//	}
//
//	public void setHUD_loanPurposeDeclaration(String HUD_loanPurposeDeclaration) {
//		this.HUD_loanPurposeDeclaration = HUD_loanPurposeDeclaration;
//
//	}

	/*
	 * @JsonProperty("356") String HUD_appraisedValue;
	 *
	 * public String getHUD_appraisedValue() { return HUD_appraisedValue; }
	 *
	 * public void setHUD_appraisedValue(String HUD_appraisedValue) {
	 * this.HUD_appraisedValue= HUD_appraisedValue;
	 *
	 * }
	 */

	@JsonProperty("687")
	String HUD_salesPrice;

	public String getHUD_salesPrice() {
		return HUD_salesPrice;
	}

	public void setHUD_salesPrice(String HUD_salesPrice) {
		this.HUD_salesPrice = HUD_salesPrice;

	}

	@JsonProperty("1065")
	String HUD_Occupancy;

	public String getHUD_Occupancy() {
		return HUD_Occupancy;
	}

	public void setHUD_Occupancy(String HUD_Occupancy) {
		this.HUD_Occupancy = HUD_Occupancy;

	}

	@JsonProperty("1399")
	String HUD_ResonableValue;

	public String getHUD_ResonableValue() {
		return HUD_ResonableValue;
	}

	public void setHUD_ResonableValue(String HUD_ResonableValue) {
		this.HUD_ResonableValue = HUD_ResonableValue;

	}

	@JsonProperty("1711")
	String HUD_appraisedValueInformationFor;

	public String getHUD_appraisedValueInformationFor() {
		return HUD_appraisedValueInformationFor;
	}

	public void setHUD_appraisedValueInformationFor(String HUD_appraisedValueInformationFor) {
		this.HUD_appraisedValueInformationFor = HUD_appraisedValueInformationFor;

	}

	/// Pricing

	@JsonProperty("CX.CUSTDISPLAYLOANPRGMNAME")
	String ProductName;

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String ProductName) {
		this.ProductName = ProductName;

	}

	@JsonProperty("cX.LONGLOANPROGNAME")
	String GeminiLoanProgramName;

	public String getGeminiLoanProgramName() {
		return GeminiLoanProgramName;
	}

	public void setGeminiLoanProgramName(String GeminiLoanProgramName) {
		this.GeminiLoanProgramName = GeminiLoanProgramName;

	}

	@JsonProperty("1401")
	String EncompassProductName;

	public String getEncompassProductName() {
		return EncompassProductName;
	}

	public void setEncompassProductName(String EncompassProductName) {
		this.EncompassProductName = EncompassProductName;

	}

	@JsonProperty("432")
	String LockDays;

	public String getLockDays() {
		return LockDays;
	}

	public void setLockDays(String LockDays) {
		this.LockDays = LockDays;

	}

	@JsonProperty("761")
	String LockDateAndTime;

	public String getLockDateAndTime() {
		return LockDateAndTime;
	}

	public void setLockDateAndTime(String LockDateAndTime) {
		this.LockDateAndTime = LockDateAndTime;

	}

	@JsonProperty("762")
	String ExpirationDateTime;

	public String getExpirationDateTime() {
		return ExpirationDateTime;
	}

	public void setExpirationDateTime(String ExpirationDateTime) {
		this.ExpirationDateTime = ExpirationDateTime;

	}

	@JsonProperty("cX.PNMACLOANPROGID")
	String GeminiLoanProgramReference;

	public String getGeminiLoanProgramReference() {
		return GeminiLoanProgramReference;
	}

	public void setGeminiLoanProgramReference(String GeminiLoanProgramReference) {
		this.GeminiLoanProgramReference = GeminiLoanProgramReference;

	}

	@JsonProperty("CX.RATESHEETDATETIME")
	String RateSheetDateAndTime;

	public String getRateSheetDateAndTime() {
		return RateSheetDateAndTime;
	}

	public void setRateSheetDateAndTime(String RateSheetDateAndTime) {
		this.RateSheetDateAndTime = RateSheetDateAndTime;

	}

	// Pricing input

	@JsonProperty("696")
	String ARM;

	public String getARM() {
		return ARM;

	}

	public void setARM(String ARM) {
		this.ARM = ARM;
	}

	@JsonProperty("MORNET.X67")
	String DocType;

	public String getDocType() {
		return DocType;

	}

	public void setDocType(String DocType) {
		this.DocType = DocType;
	}

	/*@JsonProperty("1821")
	String EstimatedValue;

	public String getEstimatedValue() {
		return EstimatedValue;

	}

	public void setEstimatedValue(String EstimatedValue) {
		this.EstimatedValue = EstimatedValue;
	}*/

	@JsonProperty("VASUMM.X23")
	String fico;

	public String getfico() {
		return fico;

	}

	public void setfico(String fico) {
		this.fico = fico;
	}

	@JsonProperty("CX.PRICING.IMPOUNDS")
	String EscrowWaiverType;

	public String getEscrowWaiverType() {
		return EscrowWaiverType;

	}

	public void setEscrowWaiverType(String EscrowWaiverType) {
		this.EscrowWaiverType = EscrowWaiverType;
	}

	@JsonProperty("TPO.X15")
	String CompId;

	public String getCompId() {
		return CompId;
	}

	public void setCompId(String CompId) {
		this.CompId = CompId;
	}

	@JsonProperty("TPO.X14")
	String CompName;

	public String getCompName() {
		return CompName;
	}

	public void setCompName(String CompName) {
		this.CompName = CompName;
	}

	@JsonProperty("tpO.X39")
	String BranchId;

	public String getBranchId() {
		return BranchId;
	}

	public void setBranchId(String BranchId) {
		this.BranchId = BranchId;
	}

	@JsonProperty("TPO.X38")
	String BranchName;

	public String getBranchName() {
		return BranchName;
	}

	public void setBranchName(String BranchName) {
		this.BranchName = BranchName;
	}

	@JsonProperty("742")
	String DebtRatio;

	public String getDebtRatio() {
		return DebtRatio;
	}

	public void setDebtRatio(String DebtRatio) {
		this.DebtRatio = DebtRatio;
	}

	@JsonProperty("1543")
	String AUSType;

	public String getAUSType() {
		return AUSType;
	}

	public void setAUSType(String AUSType) {
		this.AUSType = AUSType;
	}

	@JsonProperty("1544")
	String AUSRecomm;

	public String getAUSRecomm() {
		return AUSRecomm;
	}

	public void setAUSRecomm(String AUSRecomm) {
		this.AUSRecomm = AUSRecomm;
	}

	@JsonProperty("3142")
	String AppDate;

	public String getAppDate() {
		return AppDate;
	}

	public void setAppDate(String AppDate) {
		this.AppDate = AppDate;
	}

	@JsonProperty("VASUMM.X3")
	String VAEntAmount;

	public String getVAEntAmount() {
		return VAEntAmount;
	}

	public void setVAEntAmount(String VAEntAmount) {
		this.VAEntAmount = VAEntAmount;
	}

	/*@JsonProperty("VAVOB.X72")
	String militaryStatus;

	public String getmilitaryStatus() {
		return militaryStatus;
	}

	public void setmilitaryStatus(String militaryStatus) {
		this.militaryStatus = militaryStatus;
	}*/

	@JsonProperty("FE0115")
	String selfEmployed;

	public String getselfEmployed() {
		return selfEmployed;
	}

	public void setselfEmployed(String selfEmployed) {
		this.selfEmployed = selfEmployed;
	}

	@JsonProperty("CX.LOCKDAYS")
	String encompasslockDays;

	public String getencompasslockDays() {
		return encompasslockDays;
	}

	public void setencompasslockDays(String encompasslockDays) {
		this.encompasslockDays = encompasslockDays;
	}

	@JsonProperty("1612")
	String loanOfficer;

	public String getloanOfficer() {
		return loanOfficer;
	}

	public void setloanOfficer(String loanOfficer) {
		this.loanOfficer = loanOfficer;
	}

	@JsonProperty("cX.TPO.PROCESSOR.NAME")
	String loanProcessor;

	public String getloanProcessor() {
		return loanProcessor;
	}

	public void setloanProcessor(String loanProcessor) {
		this.loanProcessor = loanProcessor;
	}

	// 1003Employment Encompass Id's for Borrower

	@JsonProperty("fE0102")
	String employerName;

	public String getemployerName() {
		return employerName;
	}

	public void setemployerName(String employerName) {
		this.employerName = employerName;
	}

	@JsonProperty("fE0104")
	String streetAddress;

	public String getstreetAddress() {
		return streetAddress;
	}

	public void setstreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	@JsonProperty("fE0105")
	String city;

	public String getcity() {
		return city;
	}

	public void setcity(String city) {
		this.city = city;
	}

	@JsonProperty("fE0106")
	String state;

	public String getstate() {
		return state;
	}

	public void setstate(String state) {
		this.state = state;
	}

	@JsonProperty("fE0107")
	String zip;

	public String getzip() {
		return zip;
	}

	public void setzip(String zip) {
		this.zip = zip;
	}

	@JsonProperty("fE0110")
	String position;

	public String getposition() {
		return position;
	}

	public void setposition(String position) {
		this.position = position;
	}

	@JsonProperty("fE0117")
	String businessPhone;

	public String getbusinessPhone() {
		return businessPhone;
	}

	public void setbusinessPhone(String businessPhone) {
		this.businessPhone = businessPhone;
	}

	@JsonProperty("fE0113")
	String years;

	public String getyears() {
		return years;
	}

	public void setyears(String years) {
		this.years = years;
	}

	@JsonProperty("fE0116")
	String yrsInThisLineOfWork;

	public String getyrsInThisLineOfWork() {
		return yrsInThisLineOfWork;
	}

	public void setyrsInThisLineOfWork(String yrsInThisLineOfWork) {
		this.yrsInThisLineOfWork = yrsInThisLineOfWork;
	}

	/*@JsonProperty("fE0115")
	String CurrentEmployerIsSelfEmployed;

	public String getCurrentEmployerIsSelfEmployed() {
		return CurrentEmployerIsSelfEmployed;
	}

	public void setCurrentEmployerIsSelfEmployed(String CurrentEmployerIsSelfEmployed) {
		this.CurrentEmployerIsSelfEmployed = CurrentEmployerIsSelfEmployed;
	}

	// 1003Employment Encompass Id's for Co-Borrower

	@JsonProperty("fE0202")
	String CoBorroweremployerName;

	public String getCoBorroweremployerName() {
		return CoBorroweremployerName;
	}

	public void setCoBorroweremployerName(String CoBorroweremployerName) {
		this.CoBorroweremployerName = CoBorroweremployerName;
	}

	@JsonProperty("fE0204")
	String CoBorrowerstreetAddress;

	public String getCoBorrowerstreetAddress() {
		return CoBorrowerstreetAddress;
	}

	public void setCoBorrowerstreetAddress(String CoBorrowerstreetAddress) {
		this.CoBorrowerstreetAddress = CoBorrowerstreetAddress;
	}

	@JsonProperty("fE0205")
	String CoBorrowercity;

	public String getCoBorrowercity() {
		return CoBorrowercity;
	}

	public void setCoBorrowercity(String CoBorrowercity) {
		this.CoBorrowercity = CoBorrowercity;
	}

	@JsonProperty("fE0206")
	String CoBorrowerstate;

	public String getCoBorrowerstate() {
		return CoBorrowerstate;
	}

	public void setCoBorrowerstate(String CoBorrowerstate) {
		this.CoBorrowerstate = CoBorrowerstate;
	}

	@JsonProperty("fE0207")
	String CoBorrowerzip;

	public String getCoBorrowerzip() {
		return CoBorrowerzip;
	}

	public void setCoBorrowerzip(String CoBorrowerzip) {
		this.CoBorrowerzip = CoBorrowerzip;
	}

	@JsonProperty("fE0210")
	String CoBorrowerposition;

	public String getCoBorrowerposition() {
		return CoBorrowerposition;
	}

	public void setCoBorrowerposition(String CoBorrowerposition) {
		this.CoBorrowerposition = CoBorrowerposition;
	}

	@JsonProperty("fE0217")
	String CoBorrowerbusinessPhone;

	public String getCoBorrowerbusinessPhone() {
		return CoBorrowerbusinessPhone;
	}

	public void setCoBorrowerbusinessPhone(String CoBorrowerbusinessPhone) {
		this.CoBorrowerbusinessPhone = CoBorrowerbusinessPhone;
	}

	@JsonProperty("fE0213")
	String CoBorroweryears;

	public String getCoBorroweryears() {
		return CoBorroweryears;
	}

	public void setCoBorroweryears(String CoBorroweryears) {
		this.CoBorroweryears = CoBorroweryears;
	}

	@JsonProperty("fE0216")
	String CoBorroweryrsInThisLineOfWork;

	public String getCoBorroweryrsInThisLineOfWork() {
		return CoBorroweryrsInThisLineOfWork;
	}

	public void setCoBorroweryrsInThisLineOfWork(String CoBorroweryrsInThisLineOfWork) {
		this.CoBorroweryrsInThisLineOfWork = CoBorroweryrsInThisLineOfWork;
	}

	@JsonProperty("fE0215")
	String CoBorrowerCurrentEmployerIsSelfEmployed;

	public String getCoBorrowerCurrentEmployerIsSelfEmployed() {
		return CoBorrowerCurrentEmployerIsSelfEmployed;
	}

	public void setCoBorrowerCurrentEmployerIsSelfEmployed(String CoBorrowerCurrentEmployerIsSelfEmployed) {
		this.CoBorrowerCurrentEmployerIsSelfEmployed = CoBorrowerCurrentEmployerIsSelfEmployed;
	}

	// Pricing Output values

	@JsonProperty("228")
	String PrincipalandInterest;

	public String getPrincipalandInterest() {
		return PrincipalandInterest;
	}

	public void setPrincipalandInterest(String PrincipalandInterest) {
		this.PrincipalandInterest = PrincipalandInterest;
	}

	@JsonProperty("353")
	String TotalLTV;

	public String getTotalLTV() {
		return TotalLTV;
	}

	public void setTotalLTV(String TotalLTV) {
		this.TotalLTV = TotalLTV;
	}

	@JsonProperty("912")
	String TotalMonthlyPayment;

	public String getTotalMonthlyPayment() {
		return TotalMonthlyPayment;
	}

	public void setTotalMonthlyPayment(String TotalMonthlyPayment) {
		this.TotalMonthlyPayment = TotalMonthlyPayment;
	}

	@JsonProperty("976")
	String TotalCLTV;

	public String getTotalCLTV() {
		return TotalCLTV;
	}

	public void setTotalCLTV(String TotalCLTV) {
		this.TotalCLTV = TotalCLTV;
	}

	@JsonProperty("1540")
	String TotalHCLTV;

	public String getTotalHCLTV() {
		return TotalHCLTV;
	}

	public void setTotalHCLTV(String TotalHCLTV) {
		this.TotalHCLTV = TotalHCLTV;
	}

	@JsonProperty("morneT.X75")
	String BaseLTV;

	public String getBaseLTV() {
		return BaseLTV;
	}

	public void setBaseLTV(String BaseLTV) {
		this.BaseLTV = BaseLTV;
	}

	@JsonProperty("morneT.X76")
	String BaseCLTV;

	public String getBaseCLTV() {
		return BaseCLTV;
	}

	public void setBaseCLTV(String BaseCLTV) {
		this.BaseCLTV = BaseCLTV;
	}

	@JsonProperty("morneT.X77")
	String BaseHCLTV;

	public String getBaseHCLTV() {
		return BaseHCLTV;
	}

	public void setBaseHCLTV(String BaseHCLTV) {
		this.BaseHCLTV = BaseHCLTV;
	}
//**************************************************************************************************************
	@JsonProperty("1490")
	String getStartedMobileNumber;

	public String getGetStartedMobileNumber() {
		return getStartedMobileNumber;
	}

	public void setGetStartedMobileNumber(String getStartedMobileNumber) {
		this.getStartedMobileNumber = getStartedMobileNumber;
	}

	@JsonProperty("1240")
	String getStartedEmailAddress;

	public String getGetStartedEmailAddress() {
		return getStartedEmailAddress;

	}

	public void setGetStartedEmailAddress(String getStartedEmailAddress) {
		this.getStartedEmailAddress = getStartedEmailAddress;
	}


	@JsonProperty("1822")
	String getStartedReferralSource;

	public String getGetStartedReferralSource() {
		return getStartedReferralSource;

	}

	public void setGetStartedReferralSource(String getStartedReferralSource) {
		this.getStartedReferralSource = getStartedReferralSource;
	}

/*	@JsonProperty("1402")
	String getStartedDOB;

	public String getGetStartedDOB() {
		return getStartedDOB;

	}

	public void setGetStartedDOB(String DOB) {
		this.getStartedDOB = DOB;
	}

	@JsonProperty("934")
	String getStartedFirstTimeHomeBuyer;

	public String getGetStarteFirstTimeHomeBuyer() {
		return getStartedFirstTimeHomeBuyer;

	}

	public void setGetStartedFirstTimeHomeBuyer(String firstTimeHomeBuyer) {
		this.getStartedFirstTimeHomeBuyer = firstTimeHomeBuyer;
	}

	@JsonProperty("19")
	String getStartedRefinanceMyMortgage;

	public String getGetStartedLoanPurpose() {
		return getStartedRefinanceMyMortgage;
	}

	public void setGetStartedLoanPurpose(String refinanceMyMortgage) {
		this.getStartedRefinanceMyMortgage = refinanceMyMortgage;
	}

	@JsonProperty("156")
	String getStartedWorkingInArmedForces;

	public String getGetStartedWorkingInArmedForces() {
		return getStartedWorkingInArmedForces;
	}

	public void setGetStartedWorkingInArmedForces(String workingInArmedForces) {
		this.getStartedWorkingInArmedForces = workingInArmedForces;
	}

	@JsonProperty("954")
	String getStartedBranchOFService;

	public String getGetStartedBranchOfService() {
		return getStartedBranchOFService;
	}

	public void setGetStartedBranchOfService(String getStartedBranchOfService) {
		this.getStartedBranchOFService = getStartedBranchOfService;
	}


	@JsonProperty("955")
	String getStartedServiceStatus;

	public String getGetStartedServiceStatus() {
		return getStartedServiceStatus;
	}

	public void setGetStartedServiceStatus(String getStartedServiceStatus) {
		this.getStartedServiceStatus = getStartedServiceStatus;
	}

	@JsonProperty("vavoB.X72")
	String getStartedServiceRelatedTo;

	public String getGetStartedServiceRelatedTo() {
		return getStartedServiceRelatedTo;
	}

	public void setGetStartedServiceRelatedTo(String getStartedServiceRelated) {
		this.getStartedServiceRelatedTo = getStartedServiceRelated;
	}

	@JsonProperty("54")
	String getStartedDependentsAge;

	public String getGetStartedDependentsAge() {
		return getStartedDependentsAge;

	}

	public void setGetStartedDependentsAge(String dependentsAge) {
		this.getStartedDependentsAge = dependentsAge;
	}


	@JsonProperty("fR0115")
	String getStartedILive;

	public String getGetStartedILive() {
		return getStartedILive;

	}

	public void setGetStartedILive(String iLive) {
		this.getStartedILive = iLive;
	}

	@JsonProperty("fR0104")
	String getStartedStreetAddress;

	public String getGetStartedStreetAddress() {
		return getStartedStreetAddress;

	}

	public void setGetStartedStreetAddress(String streetAddress) {
		this.getStartedStreetAddress = streetAddress;
	}


	@JsonProperty("fR0108")
	String getStartedZipCode;

	public String getGetStartedZipCode() {
		return getStartedZipCode;

	}

	public void setGetStartedZipCode(String zipCode) {
		this.getStartedZipCode = zipCode;
	}

	@JsonProperty("fR0107")
	String getStartedState;

	public String getGetStartedState() {
		return getStartedState;

	}

	public void setGetStartedState(String state) {
		this.getStartedState = state;
	}

	@JsonProperty("fR0106")
	String getStartedCity;

	public String getGetStartedCity() {
		return getStartedCity;

	}

	public void setGetStartedCity(String city) {
		this.getStartedCity = city;
	}

	@JsonProperty("bR0022")
	String getStartedCounty;

	public String getGetStartedCounty() {
		return getStartedCounty;

	}

	public void setGetStartedCounty(String county) {
		this.getStartedCounty = county;
	}

	@JsonProperty("1819")
	String getStartedMailingAddressCheckBox;

	public String getGetStartedMailingAddressCheckBox() {
		return getStartedMailingAddressCheckBox;

	}

	public void setGetStartedMailingAddressCheckBox(String mailingAddressCheckBox) {
		this.getStartedMailingAddressCheckBox = mailingAddressCheckBox;
	}


	@JsonProperty("1416")
	String getStartedMailingAddressStreet;

	public String getGetStartedMailingAddressStreet() {
		return getStartedMailingAddressStreet;

	}

	public void setGetStartedMailingAddressStreet(String streetMailingAddress) {
		this.getStartedMailingAddressStreet = streetMailingAddress;
	}

	@JsonProperty("1417")
	String getStartedMailingAddressStreetCity;

	public String getGetStartedMailingAddressCity() {
		return getStartedMailingAddressStreetCity;

	}

	public void setGetStartedMailingAddressCity(String cityMailingAddress) {
		this.getStartedMailingAddressStreetCity = cityMailingAddress;
	}
	@JsonProperty("1418")
	String getStartedMailingAddressState;

	public String getGetStartedMailingAddressState() {
		return getStartedMailingAddressState;

	}

	public void setGetStartedMailingAddressState(String stateMailingAddress) {
		this.getStartedMailingAddressState = stateMailingAddress;
	}

	@JsonProperty("1419")
	String getStartedMailingAddressZip;

	public String getGetStartedMailingAddressZip() {
		return getStartedMailingAddressZip;

	}

	public void setGetStartedMailingAddressZip(String zipMailingAddress) {
		this.getStartedMailingAddressZip = zipMailingAddress;
	}


	@JsonProperty("fR0112")
	String getStartedNumberOfYearsOfStay;

	public String getGetStartedNumberOfYearsOfStay() {
		return getStartedNumberOfYearsOfStay;

	}

	public void setGetStartedNumberOfYearsOfStay(String numberOfYearsOfStay) {
		this.getStartedNumberOfYearsOfStay = numberOfYearsOfStay;
	}

	@JsonProperty("fR0124")
	String getStartedMonthsOfStay;

	public String getGetStartedMonthsOfStay() {
		System.out.println("getStartedMonthsOfStay"+getStartedMonthsOfStay);
		return getStartedMonthsOfStay;

	}

	public void setGetStartedMonthOfStay(String monthsOfStay) {
		this.getStartedMonthsOfStay = monthsOfStay;
	}


	@JsonProperty("fR0315")
	String getStartedPreviousPropertyStatus;

	public String getGetStartedPreviousPropertyStatus() {
		return getStartedPreviousPropertyStatus;

	}

	public void setGetStartedPreviousPropertyStatus(String previousPropertyStatus) {
		this.getStartedPreviousPropertyStatus = previousPropertyStatus;
	}




	@JsonProperty("fR0304")
	String getStartedPreviousAddressStreet;

	public String getGetStartedPreviousAddressStreet() {
		return getStartedPreviousAddressStreet;

	}

	public void setGetStartedPreviousAddressStreet(String previousAddress) {
		this.getStartedPreviousAddressStreet = previousAddress;
	}

	@JsonProperty("fR0308")
	String getStartedPreviousAddressZip;

	public String getGetStartedPreviousAddressZip() {
		return getStartedPreviousAddressZip;

	}

	public void setGetStartedPreviousAddressZip(String prevZip) {
		this.getStartedPreviousAddressZip = prevZip;
	}
	@JsonProperty("fR0307")
	String getStartedPreviousAddressState;

	public String getGetStartedPreviousAddressState() {
		return getStartedPreviousAddressState;

	}

	public void setGetStartedPreviousAddressState(String prevState) {
		this.getStartedPreviousAddressState = prevState;
	}

	@JsonProperty("fR0306")
	String getStartedPreviousAddressCity;

	public String getGetStartedPreviousAddressCity() {
		return getStartedPreviousAddressCity;

	}

	public void setGetStartedPreviousAddressCity(String prevAddressCity) {
		this.getStartedPreviousAddressCity = prevAddressCity;
	}

	@JsonProperty("fR0312")
	String getStartedPreviousAddressStayInYears;

	public String getGetStartedPreviousAddressStayInYears() {
		return getStartedPreviousAddressStayInYears;

	}

	public void setGetStartedPreviousAddressStayInYears(String stayInYears) {
		this.getStartedPreviousAddressStayInYears = stayInYears;
	}


	@JsonProperty("fR0324")
	String getStartedPreviousAddressStayInMonths;

	public String getGetStartedPreviousAddressStayInMonths() {
		return getStartedPreviousAddressStayInMonths;

	}

	public void setGetStartedPreviousAddressStayInMonths(String stayInMonths) {
		this.getStartedPreviousAddressStayInMonths = stayInMonths;
	}

	@JsonProperty("52")
	String getStartedMaritialStatus;

	public String getGetStartedMaritialStatus() {
		return getStartedMaritialStatus;

	}

	public void setGetStartedMaritialStatus(String maritialStatus) {
		this.getStartedMaritialStatus = maritialStatus;
	}


	@JsonProperty("CX.NBS.NAME")
	String getStartedSpouseFullName;

	public String getGetStartedSpouseFullName() {
		return getStartedSpouseFullName;

	}

	public void setGetStartedSpouseFullName(String spouseFullName) {
		this.getStartedSpouseFullName = spouseFullName;
	}


	@JsonProperty("CX.NBS.PHONE")
	String getStartedSpouseHomePhone;

	public String getGetStartedSpouseHomePhone() {
		return getStartedSpouseHomePhone;

	}

	public void setGetStartedSpouseHomephone(String spouseHomePhone) {
		this.getStartedSpouseHomePhone = spouseHomePhone;
	}

	@JsonProperty("CX.NBS.CELL")
	String getStartedSpouseMobile;

	public String getGetStartedSpouseMobile() {
		return getStartedSpouseMobile;

	}

	public void setGetStartedSpouseMobile(String spouseCell) {
		this.getStartedSpouseMobile = spouseCell;
	}

	@JsonProperty("CX.NBS.STATE")
	String getStartedSpouseStateName;

	public String getGetStartedSpouseStateName() {
		return getStartedSpouseStateName;

	}

	public void setGetStartedSpouseStateName(String spouseStateName) {
		this.getStartedSpouseStateName = spouseStateName;
	}

	@JsonProperty("4114")
	String loanDetailsClosingDate;

	public String getLoanDetailsEstimatedClosingDate() {
		return loanDetailsClosingDate;

	}

	public void setLoanDetailsEstimatedClosingDate(String closingDate) {
		this.loanDetailsClosingDate = closingDate;
	}

	@JsonProperty("LE1.X87")
	String loanDetailsCashToClose;

	public String getLoanDetailsEstimatedCashToClose() {
		return loanDetailsClosingDate;

	}

	public void setLoanDetailsEstimatedCashToClose(String closingCash) {
		this.loanDetailsCashToClose = closingCash;
	}


	@JsonProperty("1109")
	String loanDetailsLoanAmount;

	public String getLoanDetailsLoanAmount() {
		return loanDetailsLoanAmount;

	}

	public void setLoanDetailsLoanAmount(String loanAmount) {
		this.loanDetailsLoanAmount = loanAmount;
	}

	@JsonProperty("1014")
	String loanDetailsNoteRate;

	public String getLoanDetailsNoteRate() {
		return loanDetailsNoteRate;

	}

	public void setLoanDetailsNoteRate(String noteRate) {
		this.loanDetailsNoteRate = noteRate;
	}

	@JsonProperty("4073")
	String creditIntegration;

	public String getAuthorisationCreditIntegration() {
		return creditIntegration;

	}

	public void setAuthorisationCreditIntegration(String creditNoteIntegration) {
		this.creditIntegration = creditNoteIntegration;
	}

	@JsonProperty("300")
	String orderId;

	public String getOrderId() {
		return orderId;

	}

	public void setOrderId(String orderID) {
		this.orderId = orderID;
	}

	@JsonProperty("4195")
	String declarationDoNotWishToDisclose;

	public String getDeclarationDoNotWishToDisclose() {
		return declarationDoNotWishToDisclose;

	}

	public void setDeclarationDoNotWishToDisclose(String disclose) {
		this.declarationDoNotWishToDisclose = disclose;
	}

	@JsonProperty("4193")
	String declarationFemale;

	public String getDeclarationFemale() {
		return declarationFemale;

	}

	public void setDeclarationFemale(String female) {
		this.declarationFemale = female;
	}

	@JsonProperty("4194")
	String declarationMale;

	public String getDeclarationMale() {
		return declarationMale;

	}

	public void setDeclarationMale(String male) {
		this.declarationMale = male;
	}

	@JsonProperty("4155")
	String declarationNativeHawaian;

	public String getDeclarationNativeHawaian() {
		return declarationNativeHawaian;

	}

	public void setDeclarationNativeHawaian(String hawaian) {
		this.declarationNativeHawaian = hawaian;
	}


	@JsonProperty("4156")
	String guamanian;

	public String getDeclarationGuamanain() {
		return guamanian;

	}

	public void setDeclarationGuamanian(String guamanian) {
		this.guamanian = guamanian;
	}

	@JsonProperty("4157")
	String samoan;

	public String getDeclarationSamoan() {
		return samoan;

	}

	public void setDeclarationSamoan(String samoan) {
		this.samoan = samoan;
	}

	@JsonProperty("4158")
	String otherPacificIslander;

	public String getOtherPacificIslander() {
		return otherPacificIslander;

	}

	public void setOtherPacificIslander(String pacificIslander) {
		this.otherPacificIslander = pacificIslander;
	}

	@JsonProperty("4130")
	String otherPacificIslanderDesc;

	public String getOtherPacificIslanderDesc() {
		return otherPacificIslanderDesc;

	}

	public void setOtherPacificIslanderDesc(String desc) {
		this.otherPacificIslanderDesc = desc;
	}

	@JsonProperty("4148")
	String asianIndian;

	public String getAsianIndian() {
		return asianIndian;

	}

	public void setAsianIndian(String country) {
		this.asianIndian = country;
	}

	@JsonProperty("4149")
	String chinese;

	public String getChinese() {
		return chinese;

	}

	public void setChinese(String country) {
		this.chinese = country;
	}
	@JsonProperty("4150")
	String filipino;

	public String getFilipino() {
		return filipino;

	}

	public void setFilipino(String country) {
		this.filipino = country;
	}

	@JsonProperty("4151")
	String japanese;

	public String getJapanese() {
		return japanese;

	}

	public void setJapanese(String country) {
		this.japanese = country;
	}

	@JsonProperty("4152")
	String korean;

	public String getKorean() {
		return korean;

	}

	public void setKorean(String country) {
		this.korean = country;
	}

	@JsonProperty("4153")
	String vietanamese;

	public String getVietanamese() {
		return vietanamese;

	}

	public void setVietanamese(String country) {
		this.vietanamese = country;
	}

	@JsonProperty("4154")
	String otherAsian;

	public String getOtherAsian() {
		return otherAsian;

	}

	public void setOtherAsian(String country) {
		this.otherAsian = country;
	}

	@JsonProperty("4128")
	String otherAsianDesc;

	public String getOtherAsianDesc() {
		return otherAsianDesc;

	}

	public void setOtherAsianDesc(String country) {
		this.otherAsianDesc = country;
	}

	@JsonProperty("169")
	String outstandingJudgements;

	public String getDeclarationAnyJudgements() {
		return outstandingJudgements;

	}

	public void setDeclarationAnyJudgements(String declarations) {
		this.outstandingJudgements = declarations;
	}

	@JsonProperty("265")
	String declarationBankrupt;

	public String getDeclarationDeclaredBankrupt() {
		return declarationBankrupt;

	}

	public void getDeclarationDeclaredBankrupt(String bankrupt) {
		this.declarationBankrupt = bankrupt;
	}

	@JsonProperty("2568")
	String typesOfBankruptcy;

	public String getDeclarationTypesOfBankruptcy() {
		return typesOfBankruptcy;

	}

	public void setDeclarationTypesOfBankruptcy(String types) {
		this.typesOfBankruptcy = types;
	}

	@JsonProperty("170")
	String declarationPropertyForeclosed;

	public String getDeclarationPropertyForeclosed() {
		return declarationPropertyForeclosed;

	}

	public void setDeclarationPropertyForeclosed(String types) {
		this.declarationPropertyForeclosed = types;
	}

	@JsonProperty("172")
	String declarationLawSuitParty;

	public String getDeclarationPartyLawSuit() {
		return declarationLawSuitParty;

	}

	public void setDeclarationPartyLawSuit(String lawSuit) {
		this.declarationLawSuitParty = lawSuit;
	}

	@JsonProperty("1057")
	String declarationDirectlyIndirectlyObligated;

	public String getDeclarationDirectlyIndirectlyObligatedToAnyLoan() {
		return declarationDirectlyIndirectlyObligated;

	}

	public void setDeclarationDirectlyIndirectlyObligatedToAnyLoan(String isObligated) {
		this.declarationDirectlyIndirectlyObligated = isObligated;
	}

	@JsonProperty("463")
	String declarationDeliquentDefaultOnFederalLoan;

	public String getDeclarationDelinquentDefaultOnFederalLoan() {
		return declarationDeliquentDefaultOnFederalLoan;

	}

	public void setDeclarationDelinquentDefaultOnFederalLoan(String isDelinquent) {
		this.declarationDeliquentDefaultOnFederalLoan = isDelinquent;
	}

	@JsonProperty("173")
	String obligatedToPayAlimony;

	public String getDeclarationObligatedToPayAlimony() {
		return obligatedToPayAlimony;

	}

	public void setDeclarationObligatedToPayAlimony(String toPayAlimony) {
		this.obligatedToPayAlimony = toPayAlimony;
	}

	@JsonProperty("174")
	String downPaymentBorrowed;

	public String getDeclarationDownPaymentBorrowed() {
		return downPaymentBorrowed;

	}

	public void setDeclarationDownPaymentBorrowed(String downPaymentBorrowed) {
		this.downPaymentBorrowed = downPaymentBorrowed;
	}

	@JsonProperty("171")
	String noteEndorser;

	public String getDeclarationNoteEndorser() {
		return noteEndorser;

	}

	public void setDeclarationNoteEndorser(String isNoteEndorser) {
		this.noteEndorser = isNoteEndorser;
	}

	@JsonProperty("965")
	String declarationUSCitizen;

	public String getDeclarationUSCitizen() {
		return declarationUSCitizen;

	}

	public void setDeclarationUSCitizen(String isUSCitizen) {
		this.declarationUSCitizen = isUSCitizen;
	}

	@JsonProperty("466")
	String declarationPermanentResidentAlien;

	public String getDeclarationPermanentResidentAlien() {
		return declarationPermanentResidentAlien;

	}

	public void setDeclarationPermanentResidentAlien(String isAlien) {
		this.declarationPermanentResidentAlien = isAlien;
	}

	@JsonProperty("418")
	String intendToOccupyPropertyAsPermanent;

	public String getDeclarationIntendToOccupyProperty() {
		return intendToOccupyPropertyAsPermanent;

	}

	public void setDeclarationIntendToOccupyProperty(String intendToOccupy) {
		this.intendToOccupyPropertyAsPermanent = intendToOccupy;
	}

	@JsonProperty("403")
	String ownershipInterestInPropInLast3years;

	public String getDeclarationOwnerShipInterestInPropInLast3Years() {
		return ownershipInterestInPropInLast3years;

	}

	public void setDeclarationOwnerShipInterestInPropInLast3Years(String isInterested) {
		this.ownershipInterestInPropInLast3years = isInterested;
	}

	@JsonProperty("981")
	String typeOfPropOwned;

	public String getDeclarationTypeOfPropOwned() {
		return typeOfPropOwned;

	}

	public void setDeclarationTypeOfPropOwned(String propOwned) {
		this.typeOfPropOwned = propOwned;
	}

	@JsonProperty("1069")
	String titleHolding;

	public String getDeclationTitleHolding() {
		return titleHolding;

	}

	public void setDeclationTitleHolding(String titleHolding) {
		this.titleHolding = titleHolding;
	}

	@JsonProperty("4210")
	String hispanic;

	public String getDeclationHispanic() {
		return hispanic;

	}

	public void setDeclationHispanic(String hispanic) {
		this.hispanic = hispanic;
	}
	@JsonProperty("4158")
	String OtherHispanic;

	public String getDeclationOtherHispanic() {
		return OtherHispanic;

	}

	public void setDeclationOtherHispanic(String OtherHispanic) {
		this.OtherHispanic = OtherHispanic;
	}
	@JsonProperty("4211")
	String notHispanic;

	public String getDeclationNotHispanic() {
		return notHispanic;

	}

	public void setDeclationNotHispanic(String notHispanic) {
		this.notHispanic = notHispanic;
	}

	@JsonProperty("4205")
	String doNotWishToProvideInfo;

	public String getDeclationDoNotWishToProvideInfo() {
		return doNotWishToProvideInfo;

	}

	public void setDeclationDoNotWishToProvideInfo(String noInfo) {
		this.doNotWishToProvideInfo = noInfo;
	}

	@JsonProperty("4144")
	String mexican;

	public String getDeclationMexican() {
		return mexican;

	}

	public void setDeclationMexican(String info) {
		this.mexican = info;
	}

	@JsonProperty("4145")
	String puertoRican;

	public String getDeclationPuertoRican() {
		return puertoRican;

	}

	public void setDeclationPuertoRican(String info) {
		this.puertoRican = info;
	}

	@JsonProperty("4146")
	String cuban;

	public String getDeclationCuban() {
		return cuban;

	}

	public void setDeclationCuban(String info) {
		this.cuban = info;
	}
	@JsonProperty("4147")
	String otherHispanicLatino;

	public String getDeclationOtherHispanicLatino() {

		System.out.println("otherhispanic-->"+this.otherHispanicLatino);
		return otherHispanicLatino;

	}

	public void setDeclationOtherHispanicLatino(String info) {
		this.otherHispanicLatino = info;
	}

	@JsonProperty("4125")
	String otherHispanicLatinoDesc;

	public String getDeclationOtherHispanicLatinoDesc() {
		return otherHispanicLatinoDesc;

	}

	public void setDeclationOtherHispanicLatinoDesc(String info) {
		this.otherHispanicLatinoDesc = info;
	}

	@JsonProperty("1524")
	String americanIndian;

	public String getDeclationAmericanIndianAlaskaNative() {
		return americanIndian;

	}

	public void setDeclationAmericanIndianAlaskaNative(String info) {
		this.americanIndian = info;
	}

	@JsonProperty("1525")
	String asian;

	public String getDeclationAsian() {
		return asian;

	}

	public void setDeclationAsian(String info) {
		this.asian = info;
	}

	@JsonProperty("1526")
	String blackAfricanAmericsn;

	public String getDeclationBlackAfricanAmerican() {
		return blackAfricanAmericsn;

	}

	public void setDeclationBlackAfricanAmerican(String info) {
		this.blackAfricanAmericsn = info;
	}

	@JsonProperty("1527")
	String nativeHawaian;

	public String getDeclationNativeHawianOtherPacificIslander() {
		return nativeHawaian;

	}

	public void setDeclationNativeHawianOtherPacificIslander(String info) {
		this.nativeHawaian = info;
	}

	@JsonProperty("1528")
	String white;

	public String getDeclationWhite() {
		return white;

	}

	public void setDeclationWhite(String info) {
		this.white = info;
	}

	@JsonProperty("1529")
	String doNotWshToProvideInfo;

	public String getDeclationRaceDoNotWishToProvideInfo() {
		return doNotWshToProvideInfo;

	}

	public void setDeclationRaceDoNotWishToProvideInfo(String info) {
		this.doNotWishToProvideInfo = info;
	}


	@JsonProperty("136")
	String propertyPropertyWorth;

	public String getPropertyPropertyPurchaseFor() {
		return propertyPropertyWorth;
	}

	public void setPropertyPropertyPurchaseFor(String propertyPropertyWorth) {
		this.propertyPropertyWorth = propertyPropertyWorth;
	}


	@JsonProperty("1821")
	String PropertyMyPropertyIsWorth;

	public String getPropertyMyPropertyIsWorth() {
		return PropertyMyPropertyIsWorth;
	}

	public void setPropertyMyPropertyIsWorth(String PropertyMyPropertyIsWorth) {
		this.PropertyMyPropertyIsWorth = PropertyMyPropertyIsWorth;
	}



	@JsonProperty("1553")
	String PropertyPurchasing;

	public String getPropertyPurchasing() {
		System.out.println("PropertyPurchasing:"+PropertyPurchasing);
		return PropertyPurchasing;
	}

	public void setPropertyPurchasing(String PropertyPurchasing) {
		this.PropertyPurchasing = PropertyPurchasing;
	}

	@JsonProperty("16")
	String PropertyNumberOfUnits;

	public String getPropertyNumberOfUnits() {
		return PropertyNumberOfUnits;
	}

	public void setPropertyNumberOfUnits(String PropertyNumberOfUnits) {
		this.PropertyNumberOfUnits = PropertyNumberOfUnits;
	}


	@JsonProperty("3052")
	String PropertyMortgageToBePaid;

	public String getPropertyMortgageToBePaid() {
		return PropertyMortgageToBePaid;
	}

	public void setPropertyMortgageToBePaid(String PropertyMortgageToBePaid) {
		this.PropertyMortgageToBePaid = PropertyMortgageToBePaid;
	}


	@JsonProperty("CX.CASHOUTREQUESTED")
	String PropertyAdditionalCash;

	public String getPropertyAdditionalCash() {
		return PropertyAdditionalCash;
	}

	public void setPropertyAdditionalCash(String PropertyAdditionalCash) {
		this.PropertyAdditionalCash = PropertyAdditionalCash;
	}


	@JsonProperty("1335")
	String PropertyDownPayment;

	public String getPropertyDownPayment() {
		return PropertyDownPayment;
	}

	public void setPropertyDownPayment(String PropertyDownPayment) {
		this.PropertyDownPayment = PropertyDownPayment;
	}

	@JsonProperty("1811")
	String PropertyUsingAs;

	public String getPropertyUsingAs() {
		return PropertyUsingAs;
	}

	public void setPropertyUsingAs(String PropertyUsingAs) {
		this.PropertyUsingAs = PropertyUsingAs;
	}


	@JsonProperty("15")
	String PropertyZipCode;

	public String getPropertyZipCode() {
		return PropertyZipCode;
	}

	public void setPropertyZipCode(String PropertyZipCode) {
		this.PropertyZipCode = PropertyZipCode;
	}


	@JsonProperty("14")
	String PropertyState;

	public String getPropertyState() {
		return PropertyState;
	}

	public void setPropertyState(String PropertyState) {
		this.PropertyState = PropertyState;
	}

	@JsonProperty("12")
	String PropertyCity;

	public String getPropertyCity() {
		return PropertyCity;
	}

	public void setPropertyCity(String PropertyCity) {
		this.PropertyCity = PropertyCity;
	}

	@JsonProperty("13")
	String PropertyCounty;

	public String getPropertyCounty() {
		return PropertyCounty;
	}

	public void setPropertyCounty(String PropertyCounty) {
		this.PropertyCounty = PropertyCounty;
	}


	@JsonProperty("140")
	String PropertyBalanceOnMortgageOrHELOC;

	public String getPropertyBalanceOnMortgageOrHELOC() {
		return PropertyBalanceOnMortgageOrHELOC;
	}

	public void setPropertyBalanceOnMortgageOrHELOC(String PropertyBalanceOnMortgageOrHELOC) {
		this.PropertyBalanceOnMortgageOrHELOC = PropertyBalanceOnMortgageOrHELOC;
	}


	@JsonProperty("bE0202")
	String IncomeEmployerName;

	public String getIncomeEmployerName() {
		return IncomeEmployerName;
	}

	public void setIncomeEmployerName(String IncomeEmployerName) {
		this.IncomeEmployerName = IncomeEmployerName;
	}

	@JsonProperty("bE0004")
	String IncomeAddressLine1;

	public String getIncomeAddressLine1() {
		return IncomeAddressLine1;
	}

	public void setIncomeAddressLine1(String IncomeAddressLine1) {
		this.IncomeAddressLine1 = IncomeAddressLine1;
	}

	@JsonProperty("bE0204")
	String IncomeMilitaryAddressLine1;

	public String getIncomeMilitaryAddressLine1() {
		return IncomeMilitaryAddressLine1;
	}

	public void setIncomeMilitaryAddressLine1(String IncomeMilitaryAddressLine1) {
		this.IncomeMilitaryAddressLine1 = IncomeMilitaryAddressLine1;
	}

	@JsonProperty("bE0007")
	String IncomeZipCode;

	public String getIncomeZipCode() {
		return IncomeZipCode;
	}

	public void setIncomeZipCode(String IncomeZipCode) {
		this.IncomeZipCode = IncomeZipCode;
	}
	/*@JsonProperty("cX.SOURCEOFLOAN")
	String SourceOfLoan;

	public String getSourceOfLoan() {
		return SourceOfLoan;
	}

	public void setSourceOfLoan(String SourceOfLoan) {
		this.SourceOfLoan = SourceOfLoan;
	}/*

	@JsonProperty("bE0207")
	String IncomeMilitaryZipCode;

	public String getIncomeMilitaryZipCode() {
		return IncomeMilitaryZipCode;
	}

	public void setIncomeMilitaryZipCode(String IncomeMilitaryZipCode) {
		this.IncomeMilitaryZipCode = IncomeMilitaryZipCode;
	}

	@JsonProperty("bE0006")
	String IncomeState;

	public String getIncomeState() {
		return IncomeState;
	}

	public void setIncomeState(String IncomeState) {
		this.IncomeState = IncomeState;
	}

	@JsonProperty("bE0005")
	String IncomeCity;

	public String getIncomeCity() {
		return IncomeCity;
	}

	public void setIncomeCity(String IncomeCity) {
		this.IncomeCity = IncomeCity;
	}


	@JsonProperty("bE0017")
	String IncomeEmployerPhone;

	public String getIncomeEmployerPhone() {
		return IncomeEmployerPhone;
	}

	public void setIncomeEmployerPhone(String IncomeEmployerPhone) {
		this.IncomeEmployerPhone = IncomeEmployerPhone;
	}

	@JsonProperty("bE0217")
	String IncomeMilitaryEmployerPhone;

	public String getIncomeMilitaryEmployerPhone() {
		return IncomeMilitaryEmployerPhone;
	}

	public void setIncomeMilitaryEmployerPhone(String IncomeMilitaryEmployerPhone) {
		this.IncomeMilitaryEmployerPhone = IncomeMilitaryEmployerPhone;
	}
	@JsonProperty("bE0010")
	String IncomeJobTitle;

	public String getIncomeJobTitle() {
		return IncomeJobTitle;
	}

	public void setIncomeJobTitle(String IncomeJobTitle) {
		this.IncomeJobTitle = IncomeJobTitle;
	}


	@JsonProperty("bE0113")
	String IncomeEmploymentYear;

	public String getIncomeEmploymentYear() {
		return IncomeEmploymentYear;
	}

	public void setIncomeEmploymentYear(String IncomeEmploymentYear) {
		this.IncomeEmploymentYear = IncomeEmploymentYear;
	}


	@JsonProperty("bE0133")
	String IncomeEmploymentMonth;

	public String getIncomeEmploymentMonth() {
		return IncomeEmploymentMonth;
	}

	public void setIncomeEmploymentMonth(String IncomeEmploymentMonth) {
		this.IncomeEmploymentMonth = IncomeEmploymentMonth;
	}



	@JsonProperty("bE0016")
	String IncomeDurationInProfession ;

	public String getIncomeDurationInProfession() {
		return IncomeDurationInProfession;
	}

	public void setIncomeDurationInProfession(String IncomeDurationInProfession) {
		this.IncomeDurationInProfession = IncomeDurationInProfession;
	}



	/*@JsonProperty("bE0002")
	String IncomeBusinessName ;

	public String getIncomeBusinessName() {
		return IncomeBusinessName;
	}

	public void setIncomeBusinessName(String IncomeBusinessName) {
		this.IncomeBusinessName = IncomeBusinessName;
	}*/

	@JsonProperty("736")
	String TotalMonthlyIncome ;

	public String getTotalMonthlyIncome() {
		return TotalMonthlyIncome;
	}

	public void setTotalMonthlyIncome(String TotalMonthlyIncome) {
		this.TotalMonthlyIncome = TotalMonthlyIncome;
	}




	@JsonProperty("qM.X264")
	String IncomeRetirementSource ;

	public String getIncomeRetirementSource() {
		return IncomeRetirementSource;
	}

	public void setIncomeRetirementSource(String IncomeRetirementSource) {
		this.IncomeRetirementSource = IncomeRetirementSource;
	}

//	@JsonProperty("dD0124")
//	String AssetsHoldsJointlyWith ;
//
//	public String getAssetsHoldsJointlyWith() {
//		return AssetsHoldsJointlyWith;
//	}
//
//	public void setAssetsHoldsJointlyWith(String AssetsHoldsJointlyWith) {
//		this.AssetsHoldsJointlyWith = AssetsHoldsJointlyWith;
//	}

	@JsonProperty("fL0002")
	String LiabilitiesLenderName ;

	public String getLiabilitiesLenderName() {
		return LiabilitiesLenderName;
	}

	public void setLiabilitiesLenderName(String LiabilitiesLenderName) {
		this.LiabilitiesLenderName = LiabilitiesLenderName;
	}

	@JsonProperty("fL0010")
	String LiabilityAccountNumber;

	public String getLiabilityAccountNumber() {
		return LiabilityAccountNumber;
	}

	public void setLiabilityAccountNumber(String LiabilityAccountNumber) {
		this.LiabilityAccountNumber = LiabilityAccountNumber;
	}


	@JsonProperty("fL0013")
	String LiabilityOutstandingBalance;

	public String getLiabilityOutstandingBalance() {
		return LiabilityOutstandingBalance;
	}

	public void setLiabilityOutstandingBalance(String LiabilityOutstandingBalance) {
		this.LiabilityOutstandingBalance = LiabilityOutstandingBalance;
	}

	@JsonProperty("fL0011")
	String LiabilityMonthlyPayment;

	public String getLiabilityMonthlyPayment() {
		return LiabilityMonthlyPayment;
	}

	public void setLiabilityMonthlyPayment(String LiabilityMonthlyPayment) {
		this.LiabilityMonthlyPayment = LiabilityMonthlyPayment;
	}


	@JsonProperty("fL0015")
	String LiabilityHoldsJointlyWith;

	public String getLiabilityHoldsJointlyWith() {
		return LiabilityHoldsJointlyWith;
	}

	public void setLiabilityHoldsJointlyWith(String LiabilityHoldsJointlyWith) {
		this.LiabilityHoldsJointlyWith = LiabilityHoldsJointlyWith;
	}


/*	@JsonProperty("fM0118")
	String RealEstatePropertyType;

	public String getRealEstatePropertyType() {
		return RealEstatePropertyType;
	}

	public void setRealEstatePropertyType(String RealEstatePropertyType) {
		this.RealEstatePropertyType = RealEstatePropertyType;
	}

	@JsonProperty("fM0104")
	String RealEstateAddress;

	public String getRealEstateAddress() {
		return RealEstateAddress;
	}

	public void setRealEstateAddress(String RealEstateAddress) {
		this.RealEstateAddress = RealEstateAddress;
	}



	@JsonProperty("fM0108")
	String RealEstateZipcode;

	public String getRealEstateZipcode() {
		return RealEstateZipcode;
	}

	public void setRealEstateZipcode(String RealEstateZipcode) {
		this.RealEstateZipcode = RealEstateZipcode;
	}


	@JsonProperty("fM0007")
	String RealEstateState;

	public String getRealEstateState() {
		return RealEstateState;
	}

	public void setRealEstateState(String RealEstateState) {
		this.RealEstateState = RealEstateState;
	}


	@JsonProperty("fM0006")
	String RealEstateCity;

	public String getRealEstateCity() {
		return RealEstateCity;
	}

	public void setRealEstateCity(String RealEstateCity) {
		this.RealEstateCity = RealEstateCity;
	}


	@JsonProperty("fM0024")
	String RealEstatePropertyStatus;

	public String getRealEstatePropertyStatus() {
		return RealEstatePropertyStatus;
	}

	public void setRealEstatePropertyStatus(String RealEstatePropertyStatus) {
		this.RealEstatePropertyStatus = RealEstatePropertyStatus;
	}


	@JsonProperty("fM0119")
	String RealEstatePropertyValue;

	public String getRealEstatePropertyValue() {
		return RealEstatePropertyValue;
	}

	public void setRealEstatePropertyValue(String RealEstatePropertyValue) {
		this.RealEstatePropertyValue = RealEstatePropertyValue;
	}

	@JsonProperty("fM0117")
	String RealEstateOutstandingBalance;

	public String getRealEstateOutstandingBalance() {
		return RealEstateOutstandingBalance;
	}

	public void setRealEstateOutstandingBalance(String RealEstateOutstandingBalance) {
		this.RealEstateOutstandingBalance = RealEstateOutstandingBalance;
	}

	@JsonProperty("fM0141")
	String RealEstatePropertyIs;

	public String getRealEstatePropertyIs() {
		return RealEstatePropertyIs;
	}

	public void setRealEstatePropertyIs(String RealEstatePropertyIs) {
		this.RealEstatePropertyIs = RealEstatePropertyIs;
	}*/

	@JsonProperty("fL0108")
	String LiabailitiesType;

	public String getLiabailitiesType() {
		System.out.println("LiabailitiesType-->"+this.LiabailitiesType);
		return LiabailitiesType;
	}

	public void setLiabailitiesType(String LiabailitiesType) {
		this.LiabailitiesType = LiabailitiesType;
	}
	@JsonProperty("fL0102")
	String LiabailitiesLenderName;

	public String getLiabailitiesLenderName() {
		return LiabailitiesLenderName;
	}

	public void setLiabailitiesLenderName(String LiabailitiesLenderName) {
		this.LiabailitiesLenderName = LiabailitiesLenderName;
	}

	@JsonProperty("fL0110")
	String LiabailitiesAccountNumber;

	public String getLiabailitiesAccountNumber() {
		return LiabailitiesAccountNumber;
	}

	public void setLiabailitiesAccountNumber(String LiabailitiesAccountNumber) {
		this.LiabailitiesAccountNumber = LiabailitiesAccountNumber;
	}

	@JsonProperty("fL0113")
	String LiabailitiesOutstandingBalance;

	public String getLiabailitiesOutstandingBalance() {
		return LiabailitiesAccountNumber;
	}

	public void setLiabailitiesOutstandingBalance(String LiabailitiesOutstandingBalance) {
		this.LiabailitiesOutstandingBalance = LiabailitiesOutstandingBalance;
	}

	@JsonProperty("fL0111")
	String LiabailitiesMonthlyPayment;

	public String getLiabailitiesMonthlyPayment() {
		return LiabailitiesMonthlyPayment;
	}

	public void setLiabailitiesMonthlyPayment(String LiabailitiesMonthlyPayment) {
		this.LiabailitiesMonthlyPayment = LiabailitiesMonthlyPayment;
	}

	@JsonProperty("fL0117")
	String LiabailitiesStatus;

	public String getLiabailitiesStatus() {
		return LiabailitiesStatus;
	}

	public void setLiabailitiesStatus(String LiabailitiesStatus) {
		this.LiabailitiesStatus = LiabailitiesStatus;
	}

	@JsonProperty("fL0115")
	String LiabailitiesHoldsJointlyWith;

	public String getLiabailitiesHoldsJointlyWith() {
		return LiabailitiesStatus;
	}

	public void setLiabailitiesHoldsJointlyWith(String LiabailitiesHoldsJointlyWith) {
		this.LiabailitiesHoldsJointlyWith = LiabailitiesHoldsJointlyWith;
	}
	@JsonProperty("fM0020")
	String RealEstateMonthlyRentalIncome;

	public String getRealEstateMonthlyRentalIncome() {
		return RealEstateMonthlyRentalIncome;
	}

	public void setRealEstateMonthlyRentalIncome(String RealEstateMonthlyRentalIncome) {
		this.RealEstateMonthlyRentalIncome = RealEstateMonthlyRentalIncome;
	}

	/*@JsonProperty("fM0116")
	String RealEstateMonthlyMortgagePayments;

	public String getRealEstateMonthlyMortgagePayments() {
		System.out.println("RealEstateMonthlyMortgagePayments:"+this.RealEstateMonthlyMortgagePayments);
		return RealEstateMonthlyMortgagePayments;
	}

	public void setRealEstateMonthlyMortgagePpayments(String RealEstateMonthlyMortgagePayments) {
		this.RealEstateMonthlyMortgagePayments = RealEstateMonthlyMortgagePayments;
	}*/

	@JsonProperty("fM0021")
	String RealEstateInsuranceMaintenanceTaxes ;

	public String getRealEstateInsuranceMaintenanceTaxes() {
		return RealEstateInsuranceMaintenanceTaxes;
	}

	public void setRealEstateInsuranceMaintenanceTaxes(String RealEstateInsuranceMaintenanceTaxes) {
		this.RealEstateInsuranceMaintenanceTaxes = RealEstateInsuranceMaintenanceTaxes;
	}


//	@JsonProperty("dD0108")
//	String assetType ;
//
//	public String getAssetType() {
//		return assetType;
//	}
//
//	public void setAssetType(String asset) {
//		this.assetType = asset;
//	}


//	@JsonProperty("dD0102")
//	String assetAccountWith ;
//
//	public String getAssetAccountWith() {
//		return assetAccountWith;
//	}
//
//	public void setAssetAccountWith(String asset) {
//		this.assetAccountWith = asset;
//	}



//	@JsonProperty("dD0110")
//	String assetAccountNumber ;
//
//	public String getAssetAccountNumber() {
//		System.out.println("Account-->"+this.assetAccountNumber);
//		return assetAccountNumber;
//	}

//	public void setAssetAccountNumber(String asset) {
//		this.assetAccountNumber = asset;
//	}
//
//	public String getAssetDescription() {
//		return assetAccountNumber;
//	}
//
//	public void getAssetDescription(String asset) {
//		this.assetAccountNumber = asset;
//	}
//	@JsonProperty("dD0111")
//	String assetAmount ;
//
//	public String getAssetAmount() {
//		return assetAmount;
//	}
//
//	public void setAssetAmount(String asset) {
//		this.assetAmount = asset;
//	}

//	@JsonProperty("bE0115")
//	String businessSelfEmployment ;
//
//	public String getIncomeBusinessOrSelfEmployment() {
//		return businessSelfEmployment;
//	}
//
//	public void setIncomeBusinessOrSelfEmployment(String asset) {
//		this.businessSelfEmployment = asset;
//	}

//	@JsonProperty("bE0102")
//	String incomeSectionEmployment ;
//
//	public String getIncomeSectionEmployment() {
//		return incomeSectionEmployment;
//	}
//
//	public void setIncomeSectionEmployment(String asset) {
//		this.incomeSectionEmployment = asset;
//	}

//	@JsonProperty("145")
//	String incomeSourceOfIncome ;
//
//	public String getIncomeSourceOfIncome() {
//		return incomeSourceOfIncome;
//	}
//
//	public void setIncomeSourceOfIncome(String asset) {
//		this.incomeSourceOfIncome = asset;
//	}

//	@JsonProperty("bE0019")
//	String incomeSectionBaseIncome ;
//
//	public String getIncomeSectionBaseIncome() {
//		return incomeSectionBaseIncome;
//	}
//
//	public void setIncomeSectionBaseIncome(String asset) {
//		this.incomeSectionBaseIncome = asset;
//	}

//	@JsonProperty("bE0020")
//	String incomeSectionOverTime ;
//
//	public String getIncomeSectionOverTime() {
//		return incomeSectionOverTime;
//	}
//
//	public void setIncomeSectionOverTime(String asset) {
//		this.incomeSectionOverTime = asset;
//	}
//
//	@JsonProperty("bE0021")
//	String incomeSectionBonus ;
//
//	public String getIncomeSectionBonus() {
//		return incomeSectionBonus;
//	}
//
//	public void setIncomeSectionBonus(String asset) {
//		this.incomeSectionBonus = asset;
//	}
//
//	@JsonProperty("bE0022")
//	String incomeSectionComission ;
//
//	public String getIncomeSectionComission() {
//		return incomeSectionComission;
//	}
//
//	public void setIncomeSectionComission(String asset) {
//		this.incomeSectionComission = asset;
//	}
//
//	@JsonProperty("bE0023")
//	String incomeSectionOther ;
//
//	public String getIncomeSectionOther() {
//		return incomeSectionOther;
//	}
//
//	public void setIncomeSectionOther(String asset) {
//		this.incomeSectionOther = asset;
//	}
//
//	@JsonProperty("bE0002")
//	String incomeSectionBusinessName ;
//
//	public String getIncomeSectionBusinessName() {
//		return incomeSectionBusinessName;
//	}
//
//	public void setIncomeSectionBusinessName(String asset) {
//		this.incomeSectionBusinessName = asset;
//	}
//
//	@JsonProperty("bE0017")
//	String incomeSectionBusinessPhone ;
//
//	public String getIncomeSectionBusinessPhone() {
//		return incomeSectionBusinessPhone;
//	}
//
//	public void setIncomeSectionBusinessPhone(String asset) {
//		this.incomeSectionBusinessPhone = asset;
//	}
//
//	@JsonProperty("bE0027")
//	String incomeSectionPercentageOwnership ;
//
//	public String getIncomeSectionPercentageOwnerShip() {
//		return incomeSectionPercentageOwnership;
//	}
//
//	public void setIncomeSectionPercentageOwnerShip(String asset) {
//		this.incomeSectionPercentageOwnership = asset;
//	}
//
//
//	@JsonProperty("bE0011")
//	String incomeSectionBusinessFrom ;
//
//	public String getIncomeSectionBusinessFrom() {
//		return incomeSectionBusinessFrom;
//	}
//
//	public void setIncomeSectionBusinessFrom(String asset) {
//		this.incomeSectionBusinessFrom = asset;
//	}
//	@JsonProperty("bE0211")
//	String incomeSectionMilitaryBusinessFrom ;
//
//	public String getIncomeSectionMilitaryBusinessFrom() {
//		return incomeSectionMilitaryBusinessFrom;
//	}
//
//	public void setIncomeSectionMilitaryBusinessFrom(String incomeSectionMilitaryBusinessFrom) {
//		this.incomeSectionMilitaryBusinessFrom = incomeSectionMilitaryBusinessFrom;
//	}
//	@JsonProperty("bE0014")
//	String incomeSectionBusinessTo ;
//
//	public String getIncomeSectionBusinessTo() {
//		return incomeSectionMilitaryBusinessTo;
//	}

//	public void setIncomeSectionBusinessTo(String incomeSectionBusinessTo) {
//		this.incomeSectionBusinessTo = incomeSectionBusinessTo;
//	}


//	@JsonProperty("bE0214")
//	String incomeSectionMilitaryBusinessTo ;
//
//	public String getIncomeSectionMilitaryBusinessTo() {
//		return incomeSectionMilitaryBusinessTo;
//	}

//	public void setIncomeSectionMilitaryBusinessTo(String asset) {
//		this.incomeSectionMilitaryBusinessTo = asset;
//	}

	@JsonProperty("bE0019")
	String incomeSectionMonthlyEarnings ;

	public String getIncomeSectionMonthlyEarnings() {
		return incomeSectionMonthlyEarnings;
	}

	public void setIncomeSectionBusinessMonthlyEarnings(String asset) {
		this.incomeSectionMonthlyEarnings = asset;
	}

//	@JsonProperty("bE0219")
//	String incomeSectionMilitaryMonthlyEarnings ;
//
//	public String getIncomeSectionMilitaryMonthlyEarnings() {
//		return incomeSectionMilitaryMonthlyEarnings;
//	}


	public void setIncomeSectionBusinessMilitaryMonthlyEarnings(String incomeSectionMilitaryMonthlyEarnings) {
		this.incomeSectionMonthlyEarnings = incomeSectionMilitaryMonthlyEarnings;
	}
	@JsonProperty("loan.applicationIds")
	List<String> loanApplicationIds ;
	public Object encompassMap;

	public List<String> getLoanApplicationIds() {
		return loanApplicationIds;
	}

	public void setLoanApplicationIds(List<String> ids) {
		this.loanApplicationIds = ids;
	}
	@JsonAnyGetter(enabled=false)
	public Map<String, String> getAddress() {
	 return this.addressDetails;
	}
	@JsonAnySetter
	public void setAddress(String name, String value){
		this.addressDetails.put(name, value);
	}

}


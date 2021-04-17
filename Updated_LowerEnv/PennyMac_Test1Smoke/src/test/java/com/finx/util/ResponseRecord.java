package com.finx.util;


import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter; 
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseRecord {
	HashMap<String,String> loansMap = new HashMap<String,String>();
	private Map<String, String> addressDetails = new HashMap<>();
	@JsonProperty("loan.Guid")
	String loanGuid;

	public String getLoanGuid() {
		return loanGuid;
	}

	public void setLoanGuid(String loanGuid) {
		this.loanGuid = loanGuid;
	}

//	@JsonProperty("21")
//	String address11;
//
//	public String getaddress11() {
//		return address11;
//	}
//
//	public void setaddress11(String address) {
//		this.address11 = address;
//	}
//
//	@JsonProperty("22")
//	String address12;
//
//	public String getaddress12() {
//		return address12;
//	}
//
//	public void setaddress12(String address12) {
//		this.address12 = address12;
//	}
//
//	@JsonProperty("24")
//	String address14;
//
//	public String getaddress14() {
//		return address14;
//	}

//	public void setaddress14(String address14) {
//		this.address14 = address14;
//	}

//	@JsonProperty("25")
//	String address15;
//
//	public String getaddress15() {
//		return address15;
//	}
//
//	public void setaddress15(String address15) {
//		this.address15 = address15;
//	}
//
//	@JsonProperty("23")
//	String address13;
//
//	public String getaddress13() {
//		try {
//			return address13;
//		} catch (Exception e) {
//			System.out.println("catch");
//			return " ";
//		}
//
//	}
//
//	public void setaddress13(String address13) {
//		this.address13 = address13;
//	}
//
//	@JsonProperty("cX.BROKERLOANSTATUSES")
//	String BROKERLOANSTATUS;
//
//	public String getBROKERLOANSTATUS() {
//		return BROKERLOANSTATUS;
//
//	}

//	public void setBROKERLOANSTATUS(String BROKERLOANSTATUS) {
//		this.BROKERLOANSTATUS = BROKERLOANSTATUS;
//	}

	/*@JsonProperty("2109")
	String LoanAmount;	

	public String getLoanAmount() {
		return LoanAmount;

	}

	public void setLoanAmount(String LoanAmount) {
		this.LoanAmount = LoanAmount;
	}

	@JsonProperty("40000")
	String FirstName;

	public String getFirstName() {
		return FirstName;

	}

	public void setFirstName(String FirstName) {
		this.FirstName = FirstName;
	}

	@JsonProperty("40010")
	String MiddleName;

	public String getMiddleName() {
		return MiddleName;

	}

	public void setMiddleName(String MiddleName) {
		this.MiddleName = MiddleName;
	}

	@JsonProperty("40020")
	String LastName;

	public String getLastName() {
		return LastName;

	}

	public void setLastName(String LastName) {
		this.LastName = LastName;
	}

	@JsonProperty("40030")
	String suffix;

	public String getsuffix() {
		return suffix;

	}

	public void setsuffix(String suffix) {
		this.suffix = suffix;
	}

	@JsonProperty("650")
	String SSN;

	public String getSSN() {
		return SSN;

	}

	public void setSSN(String SSN) {
		this.SSN = SSN;
	}

	@JsonProperty("660")
	String phoneNumber;

	public String getphoneNumber() {
		return phoneNumber;

	}

	public void setphoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@JsonProperty("14020")
	String DOB;

	public String getDOB() {
		return DOB;

	}

	public void setDOB(String DOB) {
		this.DOB = DOB;
	}

	@JsonProperty("12400")
	String email;

	public String getEmail() {
		return email;

	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonProperty("520")
	String MaritalStatus;

	public String getMaritalStatus() {
		return MaritalStatus;

	}

	public void setMaritalStatus(String MaritalStatus) {
		this.MaritalStatus = MaritalStatus;
	}

	@JsonProperty("540")
	String Ages;

	public String getAges() {
		return Ages;

	}

	public void setAges(String Ages) {
		this.Ages = Ages;
	}

	@JsonProperty("530")
	String Dependents;

	public String getDependents() {
		return Dependents;

	}

	public void setDependents(String Dependents) {
		this.Dependents = Dependents;
	}

	@JsonProperty("7490")
	String StatusDate;

	public String getStatusDate() {
		return StatusDate;

	}

	public void setStatusDate(String StatusDate) {
		this.StatusDate = StatusDate;
	}

	@JsonProperty("1360")
	String PurchasePrice;

	public String getPurchasePrice() {
		return PurchasePrice;

	}

	public void setPurchasePrice(String PurchasePrice) {
		this.PurchasePrice = PurchasePrice;
	}

	@JsonProperty("3560")
	String AppraisedValue;

	public String getAppraisedValue() {
		return AppraisedValue;

	}

	public void setAppraisedValue(String AppraisedValue) {
		this.AppraisedValue = AppraisedValue;
	}

	@JsonProperty("30")
	String InterestRate;

	public String getInterestRate() {
		return InterestRate;

	}

	public void setInterestRate(String InterestRate) {
		this.InterestRate = InterestRate;
	}

	@JsonProperty("6080")
	String AmortizationType;

	public String getAmortizationType() {
		return AmortizationType;

	}

	public void setAmortizationType(String AmortizationType) {
		this.AmortizationType = AmortizationType;
	}

	@JsonProperty("11720")
	String MortgageAppliedfor;

	public String getMortgageAppliedfor() {
		return MortgageAppliedfor;

	}

	public void setMortgageAppliedfor(String MortgageAppliedfor) {
		this.MortgageAppliedfor = MortgageAppliedfor;
	}

	@JsonProperty("40")
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

	@JsonProperty("160")
	String NoofUnits;

	public String getStringNoofUnits() {
		return NoofUnits;
	}

	public void setStringNoofUnits(String StringNoofUnits) {
		this.NoofUnits = StringNoofUnits;
	}

	@JsonProperty("190")
	String PurposeofLoan;

	public String getPurposeofLoan() {
		return PurposeofLoan;
	}

	public void setPurposeofLoan(String PurposeofLoan) {
		this.PurposeofLoan = PurposeofLoan;
	}

	@JsonProperty("10410")
	String SubjectPropertyType;

	public String getSubjectPropertyType() {
		return SubjectPropertyType;
	}

	public void setSubjectPropertyType(String SubjectPropertyType) {
		this.SubjectPropertyType = SubjectPropertyType;
	}

	@JsonProperty("1800")
	String YearBuilt;

	public String getYearBuilt() {
		return YearBuilt;
	}

	public void setYearBuilt(String YearBuilt) {
		this.YearBuilt = YearBuilt;
	}

	@JsonProperty("18110")
	String Propertywillbeusedas;

	public String getPropertywillbeusedas() {
		return Propertywillbeusedas;
	}

	public void setPropertywillbeusedas(String Propertywillbeusedas) {
		this.Propertywillbeusedas = Propertywillbeusedas;
	}

	// Adding new fields for 1003 Income and Expense

	@JsonProperty("1010")
	String BaseEmployementBorrower;

	public String getBaseEmployementBorrower() {
		return BaseEmployementBorrower;
	}

	public void setBaseEmployementBorrower(String BaseEmployementBorrower) {
		this.BaseEmployementBorrower = BaseEmployementBorrower;
	}

	@JsonProperty("1100")
	String BaseEmployementCoBorrower;

	public String getBaseEmployementCoBorrower() {
		return BaseEmployementCoBorrower;
	}

	public void setBaseEmployementCoBorrower(String BaseEmployementCoBorrower) {
		this.BaseEmployementCoBorrower = BaseEmployementCoBorrower;
	}

	@JsonProperty("102")
	String OvertimeBorrower;

	public String getOvertimeBorrower() {
		return OvertimeBorrower;
	}

	public void setOvertimeBorrower(String OvertimeBorrower) {
		this.OvertimeBorrower = OvertimeBorrower;
	}

	@JsonProperty("111")
	String OvertimeCoBorrower;

	public String getOvertimeCoBorrower() {
		return OvertimeCoBorrower;
	}

	public void setOvertimeCoBorrower(String OvertimeCoBorrower) {
		this.OvertimeCoBorrower = OvertimeCoBorrower;
	}

	@JsonProperty("1030")
	String BonusesBorrower;

	public String getBonusesBorrower() {
		return BonusesBorrower;
	}

	public void setBonusesBorrower(String BonusesBorrower) {
		this.BonusesBorrower = BonusesBorrower;
	}

	@JsonProperty("1120")
	String BonusesCoBorrower;

	public String getBonusesCoBorrower() {
		return BonusesCoBorrower;
	}

	public void setBonusesCoBorrower(String BonusesCoBorrower) {
		this.BonusesCoBorrower = BonusesCoBorrower;
	}

	@JsonProperty("1040")
	String CommissionsBorrower;

	public String getCommissionsBorrower() {
		return CommissionsBorrower;
	}

	public void setCommissionsBorrower(String CommissionsBorrower) {
		this.CommissionsBorrower = CommissionsBorrower;
	}

	@JsonProperty("1130")
	String CommissionsCoBorrower;

	public String getCommissionsCoBorrower() {
		return CommissionsCoBorrower;
	}

	public void setCommissionsCoBorrower(String CommissionsCoBorrower) {
		this.CommissionsCoBorrower = CommissionsCoBorrower;
	}

	@JsonProperty("105")
	String DevidendsBorrower;

	public String getDevidendsBorrower() {
		return DevidendsBorrower;
	}

	public void setDevidendsBorrower(String DevidendsBorrower) {
		this.DevidendsBorrower = DevidendsBorrower;
	}

	@JsonProperty("114")
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

	@JsonProperty("1190")
	String Rent;

	public String getRent() {
		return Rent;
	}

	public void setRent(String Rent) {
		this.Rent = Rent;
	}

	@JsonProperty("1200")
	String FirstMortgagePresent;

	public String getFirstMortgagePresent() {
		return FirstMortgagePresent;
	}

	public void setFirstMortgagePresent(String FirstMortgagePresent) {
		this.FirstMortgagePresent = FirstMortgagePresent;
	}

	@JsonProperty("1210")
	String OtherFinancingPresent;

	public String getOtherFinancingPresent() {
		return OtherFinancingPresent;
	}

	public void setOtherFinancingPresent(String OtherFinancingPresent) {
		this.OtherFinancingPresent = OtherFinancingPresent;
	}

	@JsonProperty("1220")
	String HazardInsurancePresent;

	public String getHazardInsurancePresent() {
		return HazardInsurancePresent;
	}

	public void setHazardInsurancePresent(String HazardInsurancePresent) {
		this.HazardInsurancePresent = HazardInsurancePresent;
	}

	@JsonProperty("2300")
	String HazardInsuranceProposed;

	public String getHazardInsuranceProposed() {
		return HazardInsuranceProposed;
	}

	public void setHazardInsuranceProposed(String HazardInsuranceProposed) {
		this.HazardInsuranceProposed = HazardInsuranceProposed;
	}

	@JsonProperty("1230")
	String RealEstateTaxesPresent;

	public String getRealEstateTaxesPresent() {
		return RealEstateTaxesPresent;
	}

	public void setRealEstateTaxesPresent(String RealEstateTaxesPresent) {
		this.RealEstateTaxesPresent = RealEstateTaxesPresent;
	}

	@JsonProperty("14050")
	String RealEstateTaxesProposed;

	public String getRealEstateTaxesProposed() {
		return RealEstateTaxesProposed;
	}

	public void setRealEstateTaxesProposed(String RealEstateTaxesProposed) {
		this.RealEstateTaxesProposed = RealEstateTaxesProposed;
	}

	@JsonProperty("12400")
	String MortgageInsurancePresent;

	public String getMortgageInsurancePresent() {
		return MortgageInsurancePresent;
	}

	public void setMortgageInsurancePresent(String MortgageInsurancePresent) {
		this.MortgageInsurancePresent = MortgageInsurancePresent;
	}

	@JsonProperty("2320")
	String MortgageInsuranceProposed;

	public String getMortgageInsuranceProposed() {
		return MortgageInsuranceProposed;
	}

	public void setMortgageInsuranceProposed(String MortgageInsuranceProposed) {
		this.MortgageInsuranceProposed = MortgageInsuranceProposed;
	}

	@JsonProperty("1250")
	String HOADuesPresent;

	public String getHOADuesPresent() {
		return HOADuesPresent;
	}

	public void setHOADuesPresent(String HOADuesPresent) {
		this.HOADuesPresent = HOADuesPresent;
	}

	@JsonProperty("1260")
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

	@JsonProperty("1690")
	String hasOutstandingJudgementsBorrower;

	public String gethasOutstandingJudgementsBorrower() {
		return hasOutstandingJudgementsBorrower;
	}

	public void sethasOutstandingJudgementsBorrower(String hasOutstandingJudgementsBorrower) {
		this.hasOutstandingJudgementsBorrower = hasOutstandingJudgementsBorrower;
	}

	@JsonProperty("1750")
	String hasOutstandingJudgementsCoBorrower;

	public String gethasOutstandingJudgementsCoBorrower() {
		return hasOutstandingJudgementsCoBorrower;
	}

	public void sethasOutstandingJudgementsCoBorrower(String hasOutstandingJudgementsCoBorrower) {
		this.hasOutstandingJudgementsCoBorrower = hasOutstandingJudgementsCoBorrower;
	}

	@JsonProperty("2650")
	String isDeclaredBankruptBorrower;

	public String getisDeclaredBankruptBorrower() {
		return isDeclaredBankruptBorrower;
	}

	public void setisDeclaredBankruptBorrower(String isDeclaredBankruptBorrower) {
		this.isDeclaredBankruptBorrower = isDeclaredBankruptBorrower;
	}

	@JsonProperty("2660")
	String isDeclaredBankruptCoBorrower;

	public String getisDeclaredBankruptCoBorrower() {
		return isDeclaredBankruptCoBorrower;
	}

	public void setisDeclaredBankruptCoBorrower(String isDeclaredBankruptCoBorrower) {
		this.isDeclaredBankruptCoBorrower = isDeclaredBankruptCoBorrower;
	}

	@JsonProperty("1700")
	String hasForeclosureBorrower;

	public String gethasForeclosureBorrower() {
		return hasForeclosureBorrower;
	}

	public void sethasForeclosureBorrower(String hasForeclosureBorrower) {
		this.hasForeclosureBorrower = hasForeclosureBorrower;
	}

	@JsonProperty("1760")
	String hasForeclosureCoBorrower;

	public String gethasForeclosureCoBorrower() {
		return hasForeclosureCoBorrower;
	}

	public void sethasForeclosureCoBorrower(String hasForeclosureCoBorrower) {
		this.hasForeclosureCoBorrower = hasForeclosureCoBorrower;
	}

	@JsonProperty("1720")
	String isPartyLawsuitBorrower;

	public String getisPartyLawsuitBorrower() {
		return isPartyLawsuitBorrower;
	}

	public void setisPartyLawsuitBorrower(String isPartyLawsuitBorrower) {
		this.isPartyLawsuitBorrower = isPartyLawsuitBorrower;
	}

	@JsonProperty("1780")
	String isPartyLawsuitCoBorrower;

	public String getisPartyLawsuitCoBorrower() {
		return isPartyLawsuitCoBorrower;
	}

	public void setisPartyLawsuitCoBorrower(String isPartyLawsuitCoBorrower) {
		this.isPartyLawsuitCoBorrower = isPartyLawsuitCoBorrower;
	}

	@JsonProperty("10570")
	String hasForeclosureJudgmentBorrower;

	public String gethasForeclosureJudgmentBorrower() {
		return hasForeclosureJudgmentBorrower;
	}

	public void sethasForeclosureJudgmentBorrower(String hasForeclosureJudgmentBorrower) {
		this.hasForeclosureJudgmentBorrower = hasForeclosureJudgmentBorrower;

	}

	@JsonProperty("11970")
	String hasForeclosureJudgmentCoBorrower;

	public String gethasForeclosureJudgmentCoBorrower() {
		return hasForeclosureJudgmentCoBorrower;
	}

	public void sethasForeclosureJudgmentCoBorrower(String hasForeclosureJudgmentCoBorrower) {
		this.hasForeclosureJudgmentCoBorrower = hasForeclosureJudgmentCoBorrower;

	}

	@JsonProperty("4630")
	String isLoanGuaranteeBorrower;

	public String getisLoanGuaranteeBorrower() {
		return isLoanGuaranteeBorrower;
	}

	public void setisLoanGuaranteeBorrower(String isLoanGuaranteeBorrower) {
		this.isLoanGuaranteeBorrower = isLoanGuaranteeBorrower;

	}

	@JsonProperty("4640")
	String isLoanGuaranteeCoBorrower;

	public String getisLoanGuaranteeCoBorrower() {
		return isLoanGuaranteeCoBorrower;
	}

	public void setisLoanGuaranteeCoBorrower(String isLoanGuaranteeCoBorrower) {
		this.isLoanGuaranteeCoBorrower = isLoanGuaranteeCoBorrower;

	}

	@JsonProperty("1730")
	String isObligatedToPayMaintenanceBorrower;

	public String getisObligatedToPayMaintenanceBorrower() {
		return isObligatedToPayMaintenanceBorrower;
	}

	public void setisObligatedToPayMaintenanceBorrower(String isObligatedToPayMaintenanceBorrower) {
		this.isObligatedToPayMaintenanceBorrower = isObligatedToPayMaintenanceBorrower;

	}

	@JsonProperty("1790")
	String isObligatedToPayMaintenanceCoBorrower;

	public String getisObligatedToPayMaintenanceCoBorrower() {
		System.out.println("Ncompass Data==>" + this.isObligatedToPayMaintenanceCoBorrower);

		return this.isObligatedToPayMaintenanceCoBorrower;
	}

	public void setisObligatedToPayMaintenanceCoBorrower(String isObligatedToPayMaintenanceCoBorrower) {
		this.isObligatedToPayMaintenanceCoBorrower = isObligatedToPayMaintenanceCoBorrower;

	}

	@JsonProperty("1740")
	String isDownPaymentBorrowedBorrower;

	public String getisDownPaymentBorrowedBorrower() {
		return isDownPaymentBorrowedBorrower;
	}

	public void setisDownPaymentBorrowedBorrower(String isDownPaymentBorrowedBorrower) {
		this.isDownPaymentBorrowedBorrower = isDownPaymentBorrowedBorrower;

	}

	@JsonProperty("1800")
	String isDownPaymentBorrowedCoBorrower;

	public String getisDownPaymentBorrowedCoBorrower() {
		return isDownPaymentBorrowedCoBorrower;
	}

	public void setisDownPaymentBorrowedCoBorrower(String isDownPaymentBorrowedCoBorrower) {
		this.isDownPaymentBorrowedCoBorrower = isDownPaymentBorrowedCoBorrower;

	}

	@JsonProperty("1710")
	String isComakerEndorserBorrower;

	public String getisComakerEndorserBorrower() {
		return isComakerEndorserBorrower;
	}

	public void setisComakerEndorserBorrower(String isComakerEndorserBorrower) {
		this.isComakerEndorserBorrower = isComakerEndorserBorrower;

	}

	@JsonProperty("1770")
	String isComakerEndorserCoBorrower;

	public String getisComakerEndorserCoBorrower() {
		return isComakerEndorserCoBorrower;
	}

	public void setisComakerEndorserCoBorrower(String isComakerEndorserCoBorrower) {
		this.isComakerEndorserCoBorrower = isComakerEndorserCoBorrower;

	}

	@JsonProperty("9650")
	String isUSCitizenBorrower;

	public String getisUSCitizenBorrower() {
		return isUSCitizenBorrower;
	}

	public void setisUSCitizenBorrower(String isUSCitizenBorrower) {
		this.isUSCitizenBorrower = isUSCitizenBorrower;

	}

	@JsonProperty("9850")
	String isUSCitizenCoBorrower;

	public String getisUSCitizenCoBorrower() {
		return isUSCitizenCoBorrower;
	}

	public void setisUSCitizenCoBorrower(String isUSCitizenCoBorrower) {
		this.isUSCitizenCoBorrower = isUSCitizenCoBorrower;

	}

	@JsonProperty("4660")
	String isPermanentResidentBorrower;

	public String getisPermanentResidentBorrower() {
		return isPermanentResidentBorrower;
	}

	public void setisPermanentResidentBorrower(String isPermanentResidentBorrower) {
		this.isPermanentResidentBorrower = isPermanentResidentBorrower;

	}

	@JsonProperty("4670")
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

	}

	@JsonProperty("13430")
	String isPrimaryResidenceCoBorrower;

	public String getisPrimaryResidenceCoBorrower() {
		return isPrimaryResidenceCoBorrower;
	}

	public void setisPrimaryResidenceCoBorrower(String isPrimaryResidenceCoBorrower) {
		this.isPrimaryResidenceCoBorrower = isPrimaryResidenceCoBorrower;

	}

	@JsonProperty("4030")
	String hasOwnershipInterestBorrower;

	public String gethasOwnershipInterestBorrower() {
		return hasOwnershipInterestBorrower;
	}

	public void sethasOwnershipInterestBorrower(String hasOwnershipInterestBorrower) {
		this.hasOwnershipInterestBorrower = hasOwnershipInterestBorrower;

	}

	@JsonProperty("11080")
	String hasOwnershipInterestCoBorrower;

	public String gethasOwnershipInterestCoBorrower() {
		return hasOwnershipInterestCoBorrower;
	}

	public void sethasOwnershipInterestCoBorrower(String hasOwnershipInterestCoBorrower) {
		this.hasOwnershipInterestCoBorrower = hasOwnershipInterestCoBorrower;

	}

	@JsonProperty("9810")
	String propertyTypeBorrower;

	public String getpropertyTypeBorrower() {
		return propertyTypeBorrower;
	}

	public void setpropertyTypeBorrower(String propertyTypeBorrower) {
		this.propertyTypeBorrower = propertyTypeBorrower;

	}

	@JsonProperty("10150")
	String propertyTypeCoBorrower;

	public String getpropertyTypeCoBorrower() {
		return propertyTypeCoBorrower;
	}

	public void setpropertyTypeCoBorrower(String propertyTypeCoBorrower) {
		this.propertyTypeCoBorrower = propertyTypeCoBorrower;

	}

	@JsonProperty("10690")
	String homeTitleBorrower;

	public String gethomeTitleBorrower() {
		return homeTitleBorrower;
	}

	public void sethomeTitleBorrower(String homeTitleBorrower) {
		this.homeTitleBorrower = homeTitleBorrower;

	}

	@JsonProperty("10700")
	String homeTitleCoBorrower;

	public String gethomeTitleCoBorrower() {
		return homeTitleCoBorrower;
	}

	public void sethomeTitleCoBorrower(String homeTitleCoBorrower) {
		this.homeTitleCoBorrower = homeTitleCoBorrower;

	}
	// Transactions Part

	@JsonProperty("2")
	String loanAmountTransaction;

	public String getloanAmountTransaction() {
		return loanAmountTransaction;
	}

	public void setloanAmountTransaction(String loanAmountTransaction) {
		this.loanAmountTransaction = loanAmountTransaction;

	}

	@JsonProperty("1360")
	String purchasePriceTransaction;

	public String getpurchasePriceTransaction() {
		return purchasePriceTransaction;
	}

	public void setpurchasePriceTransaction(String purchasePriceTransaction) {
		this.purchasePriceTransaction = purchasePriceTransaction;

	}

	@JsonProperty("137")
	String estimatedClosingCostTransaction;

	public String getestimatedClosingCost() {
		return estimatedClosingCostTransaction;
	}

	public void setestimatedClosingCost(String estimatedClosingCostTransaction) {
		this.estimatedClosingCostTransaction = estimatedClosingCostTransaction;

	}

//	@JsonProperty("140")
//	String subOrdinateFinancingTransaction;
//
//	public String getsubOrdinateFinancingTransaction() {
//		return subOrdinateFinancingTransaction;
//	}
//
//	public void setsubOrdinateFinancingTransaction(String subOrdinateFinancingTransaction) {
//		this.subOrdinateFinancingTransaction = subOrdinateFinancingTransaction;
//
//	}

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

	@JsonProperty("11090")
	String loanAmountExcludeTransaction;

	public String getloanAmountExcludeTransaction() {
		return loanAmountExcludeTransaction;
	}

	public void setloanAmountExcludeTransaction(String loanAmountExcludeTransaction) {
		this.loanAmountExcludeTransaction = loanAmountExcludeTransaction;

	}

	/// ********** Field ids of GMI HMDA ***********///

/*	@JsonProperty("15230")
	String ethnicityBorrower;

	public String getethnicityBorrower() {
		return ethnicityBorrower;
	}

	public void setethnicityBorrower(String ethnicityBorrower) {
		this.ethnicityBorrower = ethnicityBorrower;

	}

	@JsonProperty("15240")
	String racesBorrower;

	public String getracesBorrower() {
		return racesBorrower;
	}

	public void setracesBorrower(String racesBorrower) {
		this.racesBorrower = racesBorrower;

	}

	@JsonProperty("41440")
	String ethnicityHispanicOrLatinoBorrower;

	public String getethnicityHispanicOrLatinoBorrower() {
		return ethnicityHispanicOrLatinoBorrower;
	}

	public void setethnicityHispanicOrLatinoBorrower(String ethnicityHispanicOrLatinoBorrower) {
		this.ethnicityHispanicOrLatinoBorrower = ethnicityHispanicOrLatinoBorrower;

	}

	@JsonProperty("41590")
	String ethnicityHispanicOrLatinoCoBorrower;

	public String getethnicityHispanicOrLatinoCoBorrower() {
		return ethnicityHispanicOrLatinoCoBorrower;
	}

	public void setethnicityHispanicOrLatinoCoBorrower(String ethnicityHispanicOrLatinoCoBorrower) {
		this.ethnicityHispanicOrLatinoCoBorrower = ethnicityHispanicOrLatinoCoBorrower;

	}

	@JsonProperty("41940")
	String genderMaleBorrower;

	public String getgenderMaleBorrower() {
		return genderMaleBorrower;
	}

	public void setgenderMaleBorrower(String genderMaleBorrower) {
		this.genderMaleBorrower = genderMaleBorrower;

	}

	@JsonProperty("41980")
	String genderMaleCoBorrower;

	public String getgenderMaleCoBorrower() {
		return genderMaleCoBorrower;
	}

	public void setgenderMaleCoBorrower(String genderMaleCoBorrower) {
		this.genderMaleCoBorrower = genderMaleCoBorrower;

	}

	@JsonProperty("15320")
	String racesCoBorrower;

	public String getracesCoBorrower() {
		return racesCoBorrower;
	}

	public void setracesCoBorrower(String racesCoBorrower) {
		this.racesCoBorrower = racesCoBorrower;

	}

	@JsonProperty("15310")
	String ethnicityCoBorrower;

	public String getethnicityCoBorrower() {
		return ethnicityCoBorrower;
	}

	public void setethnicityCoBorrower(String ethnicityCoBorrower) {
		this.ethnicityCoBorrower = ethnicityCoBorrower;

	}

	@JsonProperty("41260")
	String raceAmericanIndianOrAlaskaNativeBorrower;

	public String getraceAmericanIndianOrAlaskaNativeBorrower() {
		return raceAmericanIndianOrAlaskaNativeBorrower;
	}

	public void setraceAmericanIndianOrAlaskaNativeBorrower(String raceAmericanIndianOrAlaskaNativeBorrower) {
		this.raceAmericanIndianOrAlaskaNativeBorrower = raceAmericanIndianOrAlaskaNativeBorrower;

	}

	@JsonProperty("41370")
	String raceAmericanIndianOrAlaskaNativeCoBorrower;

	public String getraceAmericanIndianOrAlaskaNativeCoBorrower() {
		return raceAmericanIndianOrAlaskaNativeCoBorrower;
	}

	public void setraceAmericanIndianOrAlaskaNativeCoBorrower(String raceAmericanIndianOrAlaskaNativeCoBorrower) {
		this.raceAmericanIndianOrAlaskaNativeCoBorrower = raceAmericanIndianOrAlaskaNativeCoBorrower;

	}

	/// HUD field ids

	@JsonProperty("280")
	String HUD_loanPurposeDeclaration;

	public String getHUD_loanPurposeDeclaration() {
		return HUD_loanPurposeDeclaration;
	}

	public void setHUD_loanPurposeDeclaration(String HUD_loanPurposeDeclaration) {
		this.HUD_loanPurposeDeclaration = HUD_loanPurposeDeclaration;

	}

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

	/* @JsonProperty("6870")
	String HUD_salesPrice;

	public String getHUD_salesPrice() {
		return HUD_salesPrice;
	}

	public void setHUD_salesPrice(String HUD_salesPrice) {
		this.HUD_salesPrice = HUD_salesPrice;

	}

	@JsonProperty("10650")
	String HUD_Occupancy;

	public String getHUD_Occupancy() {
		return HUD_Occupancy;
	}

	public void setHUD_Occupancy(String HUD_Occupancy) {
		this.HUD_Occupancy = HUD_Occupancy;

	}

	@JsonProperty("13990")
	String HUD_ResonableValue;

	public String getHUD_ResonableValue() {
		return HUD_ResonableValue;
	}

	public void setHUD_ResonableValue(String HUD_ResonableValue) {
		this.HUD_ResonableValue = HUD_ResonableValue;

	}

	@JsonProperty("17110")
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

//	@JsonProperty("18210")
//	String EstimatedValue;
//
//	public String getEstimatedValue() {
//		return EstimatedValue;
//
//	}
//
//	public void setEstimatedValue(String EstimatedValue) {
//		this.EstimatedValue = EstimatedValue;
//	}

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

	@JsonProperty("VAVOB.X72")
	String militaryStatus;

	public String getmilitaryStatus() {
		return militaryStatus;
	}

	public void setmilitaryStatus(String militaryStatus) {
		this.militaryStatus = militaryStatus;
	}

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

	@JsonProperty("16120")
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

	@JsonProperty("fE01020")
	String employerName;

	public String getemployerName() {
		return employerName;
	}

	public void setemployerName(String employerName) {
		this.employerName = employerName;
	}

	@JsonProperty("fE01040")
	String streetAddress;

	public String getstreetAddress() {
		return streetAddress;
	}

	public void setstreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	@JsonProperty("fE01050")
	String city;

	public String getcity() {
		return city;
	}

	public void setcity(String city) {
		this.city = city;
	}

	@JsonProperty("fE01060")
	String state;

	public String getstate() {
		return state;
	}

	public void setstate(String state) {
		this.state = state;
	}

	@JsonProperty("fE01070")
	String zip;

	public String getzip() {

		return zip;
	}

	public void setzip(String zip) {
		this.zip = zip;
	}

	@JsonProperty("fE01100")
	String position;

	public String getposition() {
		return position;
	}

	public void setposition(String position) {
		this.position = position;
	}

	@JsonProperty("fE01170")
	String businessPhone;

	public String getbusinessPhone() {
		return businessPhone;
	}

	public void setbusinessPhone(String businessPhone) {
		this.businessPhone = businessPhone;
	}

	@JsonProperty("fE01130")
	String years;

	public String getyears() {
		return years;
	}

	public void setyears(String years) {
		this.years = years;
	}

	@JsonProperty("fE01160")
	String yrsInThisLineOfWork;

	public String getyrsInThisLineOfWork() {
		return yrsInThisLineOfWork;
	}

	public void setyrsInThisLineOfWork(String yrsInThisLineOfWork) {
		this.yrsInThisLineOfWork = yrsInThisLineOfWork;
	}
//
//	@JsonProperty("fE0115")
//	String CurrentEmployerIsSelfEmployed;
//
//	public String getCurrentEmployerIsSelfEmployed() {
//		return CurrentEmployerIsSelfEmployed;
//	}
//
//	public void setCurrentEmployerIsSelfEmployed(String CurrentEmployerIsSelfEmployed) {
//		this.CurrentEmployerIsSelfEmployed = CurrentEmployerIsSelfEmployed;
//	}

	// 1003Employment Encompass Id's for Co-Borrower

	@JsonProperty("fE02020")
	String CoBorroweremployerName;

	public String getCoBorroweremployerName() {
		return CoBorroweremployerName;
	}

	public void setCoBorroweremployerName(String CoBorroweremployerName) {
		this.CoBorroweremployerName = CoBorroweremployerName;
	}

	@JsonProperty("fE02040")
	String CoBorrowerstreetAddress;

	public String getCoBorrowerstreetAddress() {
		return CoBorrowerstreetAddress;
	}

	public void setCoBorrowerstreetAddress(String CoBorrowerstreetAddress) {
		this.CoBorrowerstreetAddress = CoBorrowerstreetAddress;
	}

	@JsonProperty("fE02050")
	String CoBorrowercity;

	public String getCoBorrowercity() {
		return CoBorrowercity;
	}

	public void setCoBorrowercity(String CoBorrowercity) {
		this.CoBorrowercity = CoBorrowercity;
	}

	@JsonProperty("fE02060")
	String CoBorrowerstate;

	public String getCoBorrowerstate() {
		return CoBorrowerstate;
	}

	public void setCoBorrowerstate(String CoBorrowerstate) {
		this.CoBorrowerstate = CoBorrowerstate;
	}

	@JsonProperty("fE02070")
	String CoBorrowerzip;

	public String getCoBorrowerzip() {

		return CoBorrowerzip;
	}

	public void setCoBorrowerzip(String CoBorrowerzip) {
		this.CoBorrowerzip = CoBorrowerzip;
	}

	@JsonProperty("fE02100")
	String CoBorrowerposition;

	public String getCoBorrowerposition() {
		return CoBorrowerposition;
	}

	public void setCoBorrowerposition(String CoBorrowerposition) {
		this.CoBorrowerposition = CoBorrowerposition;
	}

	@JsonProperty("fE02170")
	String CoBorrowerbusinessPhone;

	public String getCoBorrowerbusinessPhone() {
		return CoBorrowerbusinessPhone;
	}

	public void setCoBorrowerbusinessPhone(String CoBorrowerbusinessPhone) {
		this.CoBorrowerbusinessPhone = CoBorrowerbusinessPhone;
	}

	@JsonProperty("fE02130")
	String CoBorroweryears;

	public String getCoBorroweryears() {
		return CoBorroweryears;
	}

	public void setCoBorroweryears(String CoBorroweryears) {
		this.CoBorroweryears = CoBorroweryears;
	}

	@JsonProperty("fE02160")
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

	// HUD extra fields added only in product

	@JsonProperty("QM.X1660")
	String BorrowerCertificateAmount;

	public String getBorrowerCertificateAmount() {
		return BorrowerCertificateAmount;
	}

	public void setBorrowerCertificateAmount(String BorrowerCertificateAmount) {
		this.BorrowerCertificateAmount = BorrowerCertificateAmount;
	}

	@JsonProperty("QM.X1710")
	String CoBorrowerCertificateAmount;

	public String getCoBorrowerCertificateAmount() {
		return CoBorrowerCertificateAmount;
	}

	public void setCoBorrowerCertificateAmount(String CoBorrowerCertificateAmount) {
		this.CoBorrowerCertificateAmount = CoBorrowerCertificateAmount;
	}
	///// ********* Field Ids for AUS

	@JsonProperty("300")
	String CreditRefNumber;

	public String getCreditRefNumber() {
		return CreditRefNumber;
	}

	public void setCreditRefNumber(String CreditRefNumber) {
		this.CreditRefNumber = CreditRefNumber;
	}

	@JsonProperty("morneT.X4")
	String DUCasefileID;

	public String getDUCasefileID() {
		return DUCasefileID;
	}

	public void setDUCasefileID(String DUCasefileID) {
		this.DUCasefileID = DUCasefileID;
	}

	@JsonProperty("morneT.X67")
	String DocumentationType;

	public String getDocumentationType() {
		return DocumentationType;
	}

	public void setDocumentationType(String DocumentationType) {
		this.DocumentationType = DocumentationType;
	}
	//////*************Net Rental Income Field
	@JsonProperty("9060")
	String BorrowerNetRentalIncome;

	public String getBorrowerNetRentalIncome() {
		return BorrowerNetRentalIncome;
	}
	
	public void setBorrowerNetRentalIncome(String BorrowerNetRentalIncome) {
		this.BorrowerNetRentalIncome = BorrowerNetRentalIncome;
	}
	/////**************Multiple REOs
	
	////Type Of Property
	@JsonProperty("fM01180")
	String TypeOfPropertyREO1;

	public String getTypeOfPropertyREO1() {
		return TypeOfPropertyREO1;
	}
	
	public void setTypeOfPropertyREO1(String TypeOfPropertyREO1) {
		this.TypeOfPropertyREO1 = TypeOfPropertyREO1;
	}
	@JsonProperty("fM02180")
	String TypeOfPropertyREO2;

	public String getTypeOfPropertyREO2() {
		return TypeOfPropertyREO2;
	}
	
	public void setTypeOfPropertyREO2(String TypeOfPropertyREO2) {
		this.TypeOfPropertyREO2 = TypeOfPropertyREO2;
	}
	@JsonProperty("fM03180")
	String TypeOfPropertyREO3;

	public String getTypeOfPropertyREO3() {
		return TypeOfPropertyREO3;
	}
	
	public void setTypeOfPropertyREO3(String TypeOfPropertyREO3) {
		this.TypeOfPropertyREO3 = TypeOfPropertyREO3;
	}
	@JsonProperty("fM04180")
	String TypeOfPropertyREO4;

	public String getTypeOfPropertyREO4() {
		return TypeOfPropertyREO4;
	}
	
	public void setTypeOfPropertyREO4(String TypeOfPropertyREO4) {
		this.TypeOfPropertyREO4 = TypeOfPropertyREO4;
	}
	@JsonProperty("fM05180")
	String TypeOfPropertyREO5;

	public String getTypeOfPropertyREO5() {
		return TypeOfPropertyREO5;
	}
	
	public void setTypeOfPropertyREO5(String TypeOfPropertyREO5) {
		this.TypeOfPropertyREO5 = TypeOfPropertyREO5;
	}
	////Is this the Subject Property?
	@JsonProperty("fM01280")
	String IsThisSubjectPropertyREO1;

	public String getIsThisSubjectPropertyREO1() {
		return IsThisSubjectPropertyREO1;
	}
	
	public void setIsThisSubjectPropertyREO1(String IsThisSubjectPropertyREO1) {
		this.IsThisSubjectPropertyREO1 = IsThisSubjectPropertyREO1;
	}
	
	@JsonProperty("fM02280")
	String IsThisSubjectPropertyREO2;

	public String getIsThisSubjectPropertyREO2() {
		return IsThisSubjectPropertyREO2;
	}
	
	public void setIsThisSubjectPropertyREO2(String IsThisSubjectPropertyREO2) {
		this.IsThisSubjectPropertyREO2 = IsThisSubjectPropertyREO2;
	}
	
	@JsonProperty("fM03280")
	String IsThisSubjectPropertyREO3;

	public String getIsThisSubjectPropertyREO3() {
		return IsThisSubjectPropertyREO3;
	}
	
	public void setIsThisSubjectPropertyREO3(String IsThisSubjectPropertyREO3) {
		this.IsThisSubjectPropertyREO3 = IsThisSubjectPropertyREO3;
	}
	
	@JsonProperty("fM04280")
	String IsThisSubjectPropertyREO4;

	public String getIsThisSubjectPropertyREO4() {
		return IsThisSubjectPropertyREO4;
	}
	
	public void setIsThisSubjectPropertyREO4(String IsThisSubjectPropertyREO4) {
		this.IsThisSubjectPropertyREO4 = IsThisSubjectPropertyREO4;
	}
	
	@JsonProperty("fM05280")
	String IsThisSubjectPropertyREO5;

	public String getIsThisSubjectPropertyREO5() {
		return IsThisSubjectPropertyREO5;
	}
	
	public void setIsThisSubjectPropertyREO5(String IsThisSubjectPropertyREO5) {
		this.IsThisSubjectPropertyREO5 = IsThisSubjectPropertyREO5;
	}
	
	
	////Property will be used as
	@JsonProperty("fM01410")
	String PropertyWillBeUsedAsREO1;

	public String getPropertyWillBeUsedAsREO1() {
		return PropertyWillBeUsedAsREO1;
	}
	
	public void setPropertyWillBeUsedAsREO1(String PropertyWillBeUsedAsREO1) {
		this.PropertyWillBeUsedAsREO1 = PropertyWillBeUsedAsREO1;
	}
	@JsonProperty("fM02410")
	String PropertyWillBeUsedAsREO2;

	public String getPropertyWillBeUsedAsREO2() {
		return PropertyWillBeUsedAsREO2;
	}
	
	public void setPropertyWillBeUsedAsREO2(String PropertyWillBeUsedAsREO2) {
		this.PropertyWillBeUsedAsREO2 = PropertyWillBeUsedAsREO2;
	}
	@JsonProperty("fM03410")
	String PropertyWillBeUsedAsREO3;

	public String getPropertyWillBeUsedAsREO3() {
		return PropertyWillBeUsedAsREO3;
	}
	
	public void setPropertyWillBeUsedAsREO3(String PropertyWillBeUsedAsREO3) {
		this.PropertyWillBeUsedAsREO3 = PropertyWillBeUsedAsREO3;
	}
	@JsonProperty("fM04410")
	String PropertyWillBeUsedAsREO4;

	public String getPropertyWillBeUsedAsREO4() {
		return PropertyWillBeUsedAsREO4;
	}
	
	public void setPropertyWillBeUsedAsREO4(String PropertyWillBeUsedAsREO4) {
		this.PropertyWillBeUsedAsREO4 = PropertyWillBeUsedAsREO4;
	}
	@JsonProperty("fM05410")
	String PropertyWillBeUsedAsREO5;

	public String getPropertyWillBeUsedAsREO5() {
		return PropertyWillBeUsedAsREO5;
	}
	
	public void setPropertyWillBeUsedAsREO5(String PropertyWillBeUsedAsREO5) {
		this.PropertyWillBeUsedAsREO5 = PropertyWillBeUsedAsREO5;
	}
	
	////Street Address
	@JsonProperty("fM01040")
	String StreetAddressREO1;

	public String getStreetAddressREO1() {
		return StreetAddressREO1;
	}
	
	public void setStreetAddressREO1(String StreetAddressREO1) {
		this.StreetAddressREO1 = StreetAddressREO1;
	}
	@JsonProperty("fM02040")
	String StreetAddressREO2;

	public String getStreetAddressREO2() {
		return StreetAddressREO2;
	}
	
	public void setStreetAddressREO2(String StreetAddressREO2) {
		this.StreetAddressREO2 = StreetAddressREO2;
	}
	@JsonProperty("fM03040")
	String StreetAddressREO3;

	public String getStreetAddressREO3() {
		return StreetAddressREO3;
	}
	
	public void setStreetAddressREO3(String StreetAddressREO3) {
		this.StreetAddressREO3 = StreetAddressREO3;
	}
	@JsonProperty("fM04040")
	String StreetAddressREO4;

	public String getStreetAddressREO4() {
		return StreetAddressREO4;
	}
	
	public void setStreetAddressREO4(String StreetAddressREO4) {
		this.StreetAddressREO4 = StreetAddressREO4;
	}
	@JsonProperty("fM05040")
	String StreetAddressREO5;

	public String getStreetAddressREO5() {
		return StreetAddressREO5;
	}
	
	public void setStreetAddressREO5(String StreetAddressREO5) {
		this.StreetAddressREO5 = StreetAddressREO5;
	}
	
	////City
	@JsonProperty("fM01060")
	String CityREO1;

	public String getCityREO1() {
		return CityREO1;
	}
	
	public void setCityREO1(String CityREO1) {
		this.CityREO1 = CityREO1;
	}
	@JsonProperty("fM02060")
	String CityREO2;

	public String getCityREO2() {
		return CityREO2;
	}
	
	public void setCityREO2(String CityREO2) {
		this.CityREO2 = CityREO2;
	}
	@JsonProperty("fM03060")
	String CityREO3;

	public String getCityREO3() {
		return CityREO3;
	}
	
	public void setCityREO3(String CityREO3) {
		this.CityREO3 = CityREO3;
	}
	@JsonProperty("fM04060")
	String CityREO4;

	public String getCityREO4() {
		return CityREO4;
	}
	
	public void setCityREO4(String CityREO4) {
		this.CityREO4 = CityREO4;
	}
	@JsonProperty("fM05060")
	String CityREO5;

	public String getCityREO5() {
		return CityREO5;
	}
	
	public void setCityREO5(String CityREO5) {
		this.CityREO5 = CityREO5;
	}
	
	////State
	@JsonProperty("fM01070")
	String StateREO1;

	public String getStateREO1() {
		return StateREO1;
	}
	
	public void setStateREO1(String StateREO1) {
		this.StateREO1 = StateREO1;
	}
	@JsonProperty("fM02070")
	String StateREO2;

	public String getStateREO2() {
		return StateREO2;
	}
	
	public void setStateREO2(String StateREO2) {
		this.StateREO2 = StateREO2;
	}
	@JsonProperty("fM03070")
	String StateREO3;

	public String getStateREO3() {
		return StateREO3;
	}
	
	public void setStateREO3(String StateREO3) {
		this.StateREO3 = StateREO3;
	}
	@JsonProperty("fM04070")
	String StateREO4;

	public String getStateREO4() {
		return StateREO4;
	}
	
	public void setStateREO4(String StateREO4) {
		this.StateREO4 = StateREO4;
	}
	@JsonProperty("fM05070")
	String StateREO5;

	public String getStateREO5() {
		return StateREO5;
	}
	
	public void setStateREO5(String StateREO5) {
		this.StateREO5 = StateREO5;
	}
	
	////Zip
	@JsonProperty("fM01080")
	String ZipREO1;

	public String getZipREO1() {
		return ZipREO1;
	}
	
	public void setZipREO1(String ZipREO1) {
		this.ZipREO1 = ZipREO1;
	}
	@JsonProperty("fM02080")
	String ZipREO2;

	public String getZipREO2() {
		return ZipREO2;
	}
	
	public void setZipREO2(String ZipREO2) {
		this.ZipREO2 = ZipREO2;
	}
	@JsonProperty("fM03080")
	String ZipREO3;

	public String getZipREO3() {
		return ZipREO3;
	}
	
	public void setZipREO3(String ZipREO3) {
		this.ZipREO3 = ZipREO3;
	}
	@JsonProperty("fM04080")
	String ZipREO4;

	public String getZipREO4() {
		return ZipREO4;
	}
	
	public void setZipREO4(String ZipREO4) {
		this.ZipREO4 = ZipREO4;
	}
	@JsonProperty("fM05080")
	String ZipREO5;

	public String getZipREO5() {
		return ZipREO5;
	}
	
	public void setZipREO5(String ZipREO5) {
		this.ZipREO5 = ZipREO5;
	}
	////Present Market Value
	@JsonProperty("fM01190")
	String PresentMarketValueREO1;

	public String getPresentMarketValueREO1() {
		return PresentMarketValueREO1;
	}
	
	public void setPresentMarketValueREO1(String PresentMarketValueREO1) {
		this.PresentMarketValueREO1 = PresentMarketValueREO1;
	}
	@JsonProperty("fM02190")
	String PresentMarketValueREO2;

	public String getPresentMarketValueREO2() {
		return PresentMarketValueREO2;
	}
	
	public void setPresentMarketValueREO2(String PresentMarketValueREO2) {
		this.PresentMarketValueREO2 = PresentMarketValueREO2;
	}
	@JsonProperty("fM03190")
	String PresentMarketValueREO3;

	public String getPresentMarketValueREO3() {
		return PresentMarketValueREO3;
	}
	
	public void setPresentMarketValueREO3(String PresentMarketValueREO3) {
		this.PresentMarketValueREO3 = PresentMarketValueREO3;
	}
	@JsonProperty("fM04190")
	String PresentMarketValueREO4;

	public String getPresentMarketValueREO4() {
		return PresentMarketValueREO4;
	}
	
	public void setPresentMarketValueREO4(String PresentMarketValueREO4) {
		this.PresentMarketValueREO4 = PresentMarketValueREO4;
	}
	@JsonProperty("fM05190")
	String PresentMarketValueREO5;

	public String getPresentMarketValueREO5() {
		return PresentMarketValueREO5;
	}
	
	public void setPresentMarketValueREO5(String PresentMarketValueREO5) {
		this.PresentMarketValueREO5 = PresentMarketValueREO5;
	}
	////Property Status
	@JsonProperty("fM012400")
	String PropertyStatusREO1;

	public String getPropertyStatusREO1() {
		return PropertyStatusREO1;
	}
	
	public void setPropertyStatusREO1(String PropertyStatusREO1) {
		this.PropertyStatusREO1 = PropertyStatusREO1;
	}
	@JsonProperty("fM02240")
	String PropertyStatusREO2;

	public String getPropertyStatusREO2() {
		return PropertyStatusREO2;
	}
	
	public void setPropertyStatusREO2(String PropertyStatusREO2) {
		this.PropertyStatusREO2 = PropertyStatusREO2;
	}
	@JsonProperty("fM03240")
	String PropertyStatusREO3;

	public String getPropertyStatusREO3() {
		return PropertyStatusREO3;
	}
	
	public void setPropertyStatusREO3(String PropertyStatusREO3) {
		this.PropertyStatusREO3 = PropertyStatusREO3;
	}
	@JsonProperty("fM04240")
	String PropertyStatusREO4;

	public String getPropertyStatusREO4() {
		return PropertyStatusREO4;
	}
	
	public void setPropertyStatusREO4(String PropertyStatusREO4) {
		this.PropertyStatusREO4 = PropertyStatusREO4;
	}
	@JsonProperty("fM05240")
	String PropertyStatusREO5;

	public String getPropertyStatusREO5() {
		return PropertyStatusREO5;
	}
	
	public void setPropertyStatusREO5(String PropertyStatusREO5) {
		this.PropertyStatusREO5 = PropertyStatusREO5;
	}
	////Amount of Mortgage & Liens
	@JsonProperty("fM01170")
	String AmountOfMortgageLiensREO1;

	public String getAmountOfMortgageLiensREO1() {
		return AmountOfMortgageLiensREO1;
	}
	
	public void setAmountOfMortgageLiensREO1(String AmountOfMortgageLiensREO1) {
		this.AmountOfMortgageLiensREO1 = AmountOfMortgageLiensREO1;
	}
	@JsonProperty("fM02170")
	String AmountOfMortgageLiensREO2;

	public String getAmountOfMortgageLiensREO2() {
		return AmountOfMortgageLiensREO2;
	}
	
	public void setAmountOfMortgageLiensREO2(String AmountOfMortgageLiensREO2) {
		this.AmountOfMortgageLiensREO2 = AmountOfMortgageLiensREO2;
	}
	@JsonProperty("fM03170")
	String AmountOfMortgageLiensREO3;

	public String getAmountOfMortgageLiensREO3() {
		return AmountOfMortgageLiensREO3;
	}
	
	public void setAmountOfMortgageLiensREO3(String AmountOfMortgageLiensREO3) {
		this.AmountOfMortgageLiensREO3 = AmountOfMortgageLiensREO3;
	}
	@JsonProperty("fM04170")
	String AmountOfMortgageLiensREO4;

	public String getAmountOfMortgageLiensREO4() {
		return AmountOfMortgageLiensREO4;
	}
	
	public void setAmountOfMortgageLiensREO4(String AmountOfMortgageLiensREO4) {
		this.AmountOfMortgageLiensREO4 = AmountOfMortgageLiensREO4;
	}
	@JsonProperty("fM05170")
	String AmountOfMortgageLiensREO5;

	public String getAmountOfMortgageLiensREO5() {
		return AmountOfMortgageLiensREO5;
	}
	
	public void setAmountOfMortgageLiensREO5(String AmountOfMortgageLiensREO5) {
		this.AmountOfMortgageLiensREO5 = AmountOfMortgageLiensREO5;
	}
	
	////Mortgage Payments
	@JsonProperty("fM01160")
	String MortgagePaymentsREO1;

	public String getMortgagePaymentsREO1() {
		return MortgagePaymentsREO1;
	}
	
	public void setMortgagePaymentsREO1(String MortgagePaymentsREO1) {
		this.MortgagePaymentsREO1 = MortgagePaymentsREO1;
	}
	@JsonProperty("fM02160")
	String MortgagePaymentsREO2;

	public String getMortgagePaymentsREO2() {
		return MortgagePaymentsREO2;
	}
	
	public void setMortgagePaymentsREO2(String MortgagePaymentsREO2) {
		this.MortgagePaymentsREO2 = MortgagePaymentsREO2;
	}
	@JsonProperty("fM03160")
	String MortgagePaymentsREO3;

	public String getMortgagePaymentsREO3() {
		return MortgagePaymentsREO3;
	}
	
	public void setMortgagePaymentsREO3(String MortgagePaymentsREO3) {
		this.MortgagePaymentsREO3 = MortgagePaymentsREO3;
	}
	@JsonProperty("fM0416")
	String MortgagePaymentsREO4;

	public String getMortgagePaymentsREO4() {
		return MortgagePaymentsREO4;
	}
	
	public void setMortgagePaymentsREO4(String MortgagePaymentsREO4) {
		this.MortgagePaymentsREO4 = MortgagePaymentsREO4;
	}
	@JsonProperty("fM05160")
	String MortgagePaymentsREO5;

	public String getMortgagePaymentsREO5() {
		return MortgagePaymentsREO1;
	}
	
	public void setMortgagePaymentsREO5(String MortgagePaymentsREO5) {
		this.MortgagePaymentsREO5 = MortgagePaymentsREO5;
	}
	
	//Gross Rental Income
	@JsonProperty("fM01200")
	String GrossRentalIncomeREO1;

	public String getGrossRentalIncomeREO1() {
		return GrossRentalIncomeREO1;
	}
	
	public void setGrossRentalIncomeREO1(String GrossRentalIncomeREO1) {
		this.GrossRentalIncomeREO1 = GrossRentalIncomeREO1;
	}
	@JsonProperty("fM02200")
	String GrossRentalIncomeREO2;

	public String getGrossRentalIncomeREO2() {
		return GrossRentalIncomeREO2;
	}
	
	public void setGrossRentalIncomeREO2(String GrossRentalIncomeREO2) {
		this.GrossRentalIncomeREO2 = GrossRentalIncomeREO2;
	}
	@JsonProperty("fM03200")
	String GrossRentalIncomeREO3;

	public String getGrossRentalIncomeREO3() {
		return GrossRentalIncomeREO3;
	}
	
	public void setGrossRentalIncomeREO3(String GrossRentalIncomeREO3) {
		this.GrossRentalIncomeREO3 = GrossRentalIncomeREO3;
	}
	@JsonProperty("fM04200")
	String GrossRentalIncomeREO4;

	public String getGrossRentalIncomeREO4() {
		return GrossRentalIncomeREO4;
	}
	
	public void setGrossRentalIncomeREO4(String GrossRentalIncomeREO4) {
		this.GrossRentalIncomeREO4 = GrossRentalIncomeREO4;
	}
	@JsonProperty("fM05200")
	String GrossRentalIncomeREO5;

	public String getGrossRentalIncomeREO5() {
		return GrossRentalIncomeREO5;
	}
	
	public void setGrossRentalIncomeREO5(String GrossRentalIncomeREO5) {
		this.GrossRentalIncomeREO5 = GrossRentalIncomeREO5;
	}
	//Insurance, Maintenance, Taxes and Misc
	@JsonProperty("fM01210")
	String InsuranceREO1;

	public String getInsuranceREO1() {
		return InsuranceREO1;
	}
	
	public void setInsuranceREO1(String InsuranceREO1) {
		this.InsuranceREO1 = InsuranceREO1;
	}
	@JsonProperty("fM02210")
	String InsuranceREO2;

	public String getInsuranceREO2() {
		return InsuranceREO2;
	}
	
	public void setInsuranceREO2(String InsuranceREO2) {
		this.InsuranceREO2 = InsuranceREO2;
	}
	@JsonProperty("fM03210")
	String InsuranceREO3;

	public String getInsuranceREO3() {
		return InsuranceREO3;
	}
	
	public void setInsuranceREO3(String InsuranceREO3) {
		this.InsuranceREO3 = InsuranceREO3;
	}
	@JsonProperty("fM04210")
	String InsuranceREO4;

	public String getInsuranceREO4() {
		return InsuranceREO4;
	}
	
	public void setInsuranceREO4(String InsuranceREO4) {
		this.InsuranceREO4 = InsuranceREO4;
	}
	@JsonProperty("fM05210")
	String InsuranceREO5;

	public String getInsuranceREO5() {
		return InsuranceREO5;
	}
	
	public void setInsuranceREO5(String InsuranceREO5) {
		this.InsuranceREO5 = InsuranceREO5;
	}
	//Net Rental Income
	@JsonProperty("fM01320")
	String NetRentalIncomeREO1;

	public String getNetRentalIncomeREO1() {
		return NetRentalIncomeREO1;
	}
	
	public void setNetRentalIncomeREO1(String NetRentalIncomeREO1) {
		this.NetRentalIncomeREO1 = NetRentalIncomeREO1;
	}
	@JsonProperty("fM02320")
	String NetRentalIncomeREO2;

	public String getNetRentalIncomeREO2() {
		return NetRentalIncomeREO2;
	}
	
	public void setNetRentalIncomeREO2(String NetRentalIncomeREO2) {
		this.NetRentalIncomeREO2 = NetRentalIncomeREO2;
	}
	@JsonProperty("fM03320")
	String NetRentalIncomeREO3;

	public String getNetRentalIncomeREO3() {
		return NetRentalIncomeREO3;
	}
	
	public void setNetRentalIncomeREO3(String NetRentalIncomeREO3) {
		this.NetRentalIncomeREO3 = NetRentalIncomeREO3;
	}
	@JsonProperty("fM04320")
	String NetRentalIncomeREO4;

	public String getNetRentalIncomeREO4() {
		return NetRentalIncomeREO4;
	}
	
	public void setNetRentalIncomeREO4(String NetRentalIncomeREO4) {
		this.NetRentalIncomeREO4 = NetRentalIncomeREO4;
	}
	@JsonProperty("fM05320")
	String NetRentalIncomeREO5;

	public String getNetRentalIncomeREO5() {
		return NetRentalIncomeREO5;
	}
	
	public void setNetRentalIncomeREO5(String NetRentalIncomeREO5) {
		this.NetRentalIncomeREO5 = NetRentalIncomeREO5;
	}
	
	
	////////////////////**************Multiple Liabilities
	//Company Name
	@JsonProperty("fL01020")
	String CompanyNameLiability1;

	public String getCompanyNameLiability1() {
		return CompanyNameLiability1;
	}
	
	public void setCompanyNameLiability1(String CompanyNameLiability1) {
		this.CompanyNameLiability1 = CompanyNameLiability1;
	}
	@JsonProperty("fL02020")
	String CompanyNameLiability2;

	public String getCompanyNameLiability2() {
		return CompanyNameLiability2;
	}
	
	public void setCompanyNameLiability2(String CompanyNameLiability2) {
		this.CompanyNameLiability2 = CompanyNameLiability2;
	}
	@JsonProperty("fL03020")
	String CompanyNameLiability3;

	public String getCompanyNameLiability3() {
		return CompanyNameLiability3;
	}
	
	public void setCompanyNameLiability3(String CompanyNameLiability3) {
		this.CompanyNameLiability3 = CompanyNameLiability3;
	}
	@JsonProperty("fL04020")
	String CompanyNameLiability4;

	public String getCompanyNameLiability4() {
		return CompanyNameLiability4;
	}
	
	public void setCompanyNameLiability4(String CompanyNameLiability4) {
		this.CompanyNameLiability4 = CompanyNameLiability4;
	}
	@JsonProperty("fL05020")
	String CompanyNameLiability5;

	public String getCompanyNameLiability5() {
		return CompanyNameLiability5;
	}
	
	public void setCompanyNameLiability5(String CompanyNameLiability5) {
		this.CompanyNameLiability5 = CompanyNameLiability5;
	}
	//Liability Type
	@JsonProperty("fL01080")
	String LiabilityTypeLiability1;
	public String getLiabilityTypeLiability1() {
		return LiabilityTypeLiability1;
	}
	public void setLiabilityTypeLiability1(String LiabilityTypeLiability1) {
		this.LiabilityTypeLiability1 = LiabilityTypeLiability1;
	}
	@JsonProperty("fL02080")
	String LiabilityTypeLiability2;
	public String getLiabilityTypeLiability2() {
		return LiabilityTypeLiability2;
	}
	public void setLiabilityTypeLiability2(String LiabilityTypeLiability2) {
		this.LiabilityTypeLiability2 = LiabilityTypeLiability2;
	}
	@JsonProperty("fL03080")
	String LiabilityTypeLiability3;
	public String getLiabilityTypeLiability3() {
		return LiabilityTypeLiability3;
	}
	public void setLiabilityTypeLiability3(String LiabilityTypeLiability3) {
		this.LiabilityTypeLiability3 = LiabilityTypeLiability3;
	}
	@JsonProperty("fL04080")
	String LiabilityTypeLiability4;
	public String getLiabilityTypeLiability4() {
		return LiabilityTypeLiability4;
	}
	public void setLiabilityTypeLiability4(String LiabilityTypeLiability4) {
		this.LiabilityTypeLiability4 = LiabilityTypeLiability4;
	}
	@JsonProperty("fL05080")
	String LiabilityTypeLiability5;
	public String getLiabilityTypeLiability5() {
		return LiabilityTypeLiability5;
	}
	public void setLiabilityTypeLiability5(String LiabilityTypeLiability5) {
		this.LiabilityTypeLiability5 = LiabilityTypeLiability5;
	}
	//City
	@JsonProperty("fL01050")
	String CityLiability1;
	public String getCityLiability1() {
		return CityLiability1;
	}
	public void setCityLiability1(String CityLiability1) {
		this.CityLiability1 = CityLiability1;
	}
	@JsonProperty("fL02050")
	String CityLiability2;
	public String getCityLiability2() {
		return CityLiability2;
	}
	public void setCityLiability2(String CityLiability2) {
		this.CityLiability2 = CityLiability2;
	}
	@JsonProperty("fL03050")
	String CityLiability3;
	public String getCityLiability3() {
		return CityLiability3;
	}
	public void setCityLiability3(String CityLiability3) {
		this.CityLiability3 = CityLiability3;
	}
	@JsonProperty("fL04050")
	String CityLiability4;
	public String getCityLiability4() {
		return CityLiability4;
	}
	public void setCityLiability4(String CityLiability4) {
		this.CityLiability4 = CityLiability4;
	}
	@JsonProperty("fL05050")
	String CityLiability5;
	public String getCityLiability5() {
		return CityLiability5;
	}
	public void setCityLiability5(String CityLiability5) {
		this.CityLiability5 = CityLiability5;
	}
	//Zip
	@JsonProperty("fL01070")
	String ZipLiability1;
	public String getZipLiability1() {
		return ZipLiability1;
	}
	public void setZipLiability1(String ZipLiability1) {
		this.ZipLiability1 = ZipLiability1;
	}
	@JsonProperty("fL02070")
	String ZipLiability2;
	public String getZipLiability2() {
		return ZipLiability2;
	}
	public void setZipLiability2(String ZipLiability2) {
		this.ZipLiability2 = ZipLiability2;
	}
	@JsonProperty("fL03070")
	String ZipLiability3;
	public String getZipLiability3() {
		return ZipLiability3;
	}
	public void setZipLiability3(String ZipLiability3) {
		this.ZipLiability3 = ZipLiability3;
	}
	@JsonProperty("fL04070")
	String ZipLiability4;
	public String getZipLiability4() {
		return ZipLiability4;
	}
	public void setZipLiability4(String ZipLiability4) {
		this.ZipLiability4 = ZipLiability4;
	}
	@JsonProperty("fL05070")
	String ZipLiability5;
	public String getZipLiability5() {
		return ZipLiability5;
	}
	public void setZipLiability5(String ZipLiability5) {
		this.ZipLiability5 = ZipLiability5;
	}
	//Account Number
	@JsonProperty("fL01100")
	String AccountNumberLiability1;
	public String getAccountNumberLiability1() {
		return AccountNumberLiability1;
	}
	public void setAccountNumberLiability1(String AccountNumberLiability1) {
		this.AccountNumberLiability1 = AccountNumberLiability1;
	}
	@JsonProperty("fL02100")
	String AccountNumberLiability2;
	public String getAccountNumberLiability2() {
		return AccountNumberLiability2;
	}
	public void setAccountNumberLiability2(String AccountNumberLiability2) {
		this.AccountNumberLiability2 = AccountNumberLiability2;
	}
	@JsonProperty("fL03100")
	String AccountNumberLiability3;
	public String getAccountNumberLiability3() {
		return AccountNumberLiability3;
	}
	public void setAccountNumberLiability3(String AccountNumberLiability3) {
		this.AccountNumberLiability3 = AccountNumberLiability3;
	}
	@JsonProperty("fL04100")
	String AccountNumberLiability4;
	public String getAccountNumberLiability4() {
		return AccountNumberLiability4;
	}
	public void setAccountNumberLiability4(String AccountNumberLiability4) {
		this.AccountNumberLiability4 = AccountNumberLiability4;
	}
	@JsonProperty("fL05100")
	String AccountNumberLiability5;
	public String getAccountNumberLiability5() {
		return AccountNumberLiability5;
	}
	public void setAccountNumberLiability5(String AccountNumberLiability5) {
		this.AccountNumberLiability5 = AccountNumberLiability5;
	}
	
	//Monthly Payment
	@JsonProperty("fL01110")
	String MonthlyPaymentLiability1;
	public String getMonthlyPaymentLiability1() {
		return MonthlyPaymentLiability1;
	}
	public void setMonthlyPaymentLiability1(String MonthlyPaymentLiability1) {
		this.MonthlyPaymentLiability1 = MonthlyPaymentLiability1;
	}
	@JsonProperty("fL02110")
	String MonthlyPaymentLiability2;
	public String getMonthlyPaymentLiability2() {
		return MonthlyPaymentLiability2;
	}
	public void setMonthlyPaymentLiability2(String MonthlyPaymentLiability2) {
		this.MonthlyPaymentLiability2 = MonthlyPaymentLiability2;
	}
	@JsonProperty("fL03110")
	String MonthlyPaymentLiability3;
	public String getMonthlyPaymentLiability3() {
		return MonthlyPaymentLiability3;
	}
	public void setMonthlyPaymentLiability3(String MonthlyPaymentLiability3) {
		this.MonthlyPaymentLiability3 = MonthlyPaymentLiability3;
	}
	@JsonProperty("fL04110")
	String MonthlyPaymentLiability4;
	public String getMonthlyPaymentLiability4() {
		return MonthlyPaymentLiability4;
	}
	public void setMonthlyPaymentLiability4(String MonthlyPaymentLiability4) {
		this.MonthlyPaymentLiability4 = MonthlyPaymentLiability4;
	}
	@JsonProperty("fL05110")
	String MonthlyPaymentLiability5;
	public String getMonthlyPaymentLiability5() {
		return MonthlyPaymentLiability5;
	}
	public void setMonthlyPaymentLiability5(String MonthlyPaymentLiability5) {
		this.MonthlyPaymentLiability5 = MonthlyPaymentLiability5;
	}
	
	//Unpaid Balance
	@JsonProperty("fL01130")
	String UnpaidBalanceLiability1;
	public String getUnpaidBalanceLiability1() {
		return UnpaidBalanceLiability1;
	}
	public void setUnpaidBalanceLiability1(String UnpaidBalanceLiability1) {
		this.UnpaidBalanceLiability1 = UnpaidBalanceLiability1;
	}
	@JsonProperty("fL02130")
	String UnpaidBalanceLiability2;
	public String getUnpaidBalanceLiability2() {
		return UnpaidBalanceLiability2;
	}
	public void setUnpaidBalanceLiability2(String UnpaidBalanceLiability2) {
		this.UnpaidBalanceLiability2 = UnpaidBalanceLiability2;
	}
	@JsonProperty("fL03130")
	String UnpaidBalanceLiability3;
	public String getUnpaidBalanceLiability3() {
		return UnpaidBalanceLiability3;
	}
	public void setUnpaidBalanceLiability3(String UnpaidBalanceLiability3) {
		this.UnpaidBalanceLiability3 = UnpaidBalanceLiability3;
	}
	@JsonProperty("fL04130")
	String UnpaidBalanceLiability4;
	public String getUnpaidBalanceLiability4() {
		return UnpaidBalanceLiability4;
	}
	public void setUnpaidBalanceLiability4(String UnpaidBalanceLiability4) {
		this.UnpaidBalanceLiability4 = UnpaidBalanceLiability4;
	}
	@JsonProperty("fL05130")
	String UnpaidBalanceLiability5;
	public String getUnpaidBalanceLiability5() {
		return UnpaidBalanceLiability5;
	}
	public void setUnpaidBalanceLiability5(String UnpaidBalanceLiability5) {
		this.UnpaidBalanceLiability5 = UnpaidBalanceLiability5;
	}
	//Months left to pay
	@JsonProperty("fL01120")
	String MonthsLeftToPayLiability1;
	public String getMonthsLeftToPayLiability1() {
		return MonthsLeftToPayLiability1;
	}
	public void setMonthsLeftToPayLiability1(String MonthsLeftToPayLiability1) {
		this.MonthsLeftToPayLiability1 = MonthsLeftToPayLiability1;
	}
	@JsonProperty("fL02120")
	String MonthsLeftToPayLiability2;
	public String getMonthsLeftToPayLiability2() {
		return MonthsLeftToPayLiability2;
	}
	public void setMonthsLeftToPayLiability2(String MonthsLeftToPayLiability2) {
		this.MonthsLeftToPayLiability2 = MonthsLeftToPayLiability2;
	}
	@JsonProperty("fL03120")
	String MonthsLeftToPayLiability3;
	public String getMonthsLeftToPayLiability3() {
		return MonthsLeftToPayLiability3;
	}
	public void setMonthsLeftToPayLiability3(String MonthsLeftToPayLiability3) {
		this.MonthsLeftToPayLiability3 = MonthsLeftToPayLiability3;
	}
	@JsonProperty("fL04120")
	String MonthsLeftToPayLiability4;
	public String getMonthsLeftToPayLiability4() {
		return MonthsLeftToPayLiability4;
	}
	public void setMonthsLeftToPayLiability4(String MonthsLeftToPayLiability4) {
		this.MonthsLeftToPayLiability4 = MonthsLeftToPayLiability4;
	}
	@JsonProperty("fL05120")
	String MonthsLeftToPayLiability5;
	public String getMonthsLeftToPayLiability5() {
		return MonthsLeftToPayLiability5;
	}
	public void setMonthsLeftToPayLiability5(String MonthsLeftToPayLiability5) {
		this.MonthsLeftToPayLiability5 = MonthsLeftToPayLiability5;
	}
	
	
	//HUD fields
	@JsonProperty("11560")
	String FederalIncTaxBorrower;
	public String getFederalIncTaxBorrower() {
		return FederalIncTaxBorrower;
	}
	public void setFederalIncTaxBorrower(String FederalIncTaxBorrower) {
		this.FederalIncTaxBorrower = FederalIncTaxBorrower;
	}
	@JsonProperty("13060")
	String FederalIncTaxCoBorrower;
	public String getFederalIncTaxCoBorrower() {
		return FederalIncTaxCoBorrower;
	}
	public void setFederalIncTaxCoBorrower(String FederalIncTaxCoBorrower) {
		this.FederalIncTaxCoBorrower = FederalIncTaxCoBorrower;
	}
	@JsonProperty("11580")
	String StateIncTaxBorrower;
	public String getStateIncTaxBorrower() {
		return StateIncTaxBorrower;
	}
	public void setStateIncTaxBorrower(String StateIncTaxBorrower) {
		this.StateIncTaxBorrower = StateIncTaxBorrower;
	}
	@JsonProperty("13070")
	String StateIncTaxCoBorrower;
	public String getStateIncTaxCoBorrower() {
		return StateIncTaxCoBorrower;
	}
	public void setStateIncTaxCoBorrower(String StateIncTaxCoBorrower) {
		this.StateIncTaxCoBorrower = StateIncTaxCoBorrower;
	}
	@JsonProperty("11590")
	String SocialSecurityTaxBorrower;
	public String getSocialSecurityTaxBorrower() {
		return SocialSecurityTaxBorrower;
	}
	public void setSocialSecurityTaxBorrower(String SocialSecurityTaxBorrower) {
		this.SocialSecurityTaxBorrower = SocialSecurityTaxBorrower;
	}
	@JsonProperty("13080")
	String SocialSecurityTaxCoBorrower;
	public String getSocialSecurityTaxCoBorrower() {
		return SocialSecurityTaxCoBorrower;
	}
	public void setSocialSecurityTaxCoBorrower(String SocialSecurityTaxCoBorrower) {
		this.SocialSecurityTaxCoBorrower = SocialSecurityTaxCoBorrower;
	}
	@JsonProperty("valA.X190")
	String OtherTaxBorrower;
	public String getOtherTaxBorrower() {
		return OtherTaxBorrower;
	}
	public void setOtherTaxBorrower(String OtherTaxBorrower) {
		this.OtherTaxBorrower = OtherTaxBorrower;
	}
	@JsonProperty("13090")
	String OtherTaxCoBorrower;
	public String getOtherTaxCoBorrower() {
		return OtherTaxCoBorrower;
	}
	public void setOtherTaxCoBorrower(String OtherTaxCoBorrower) {
		this.OtherTaxCoBorrower = OtherTaxCoBorrower;
	}
	
	//Property Page 
	@JsonProperty("340")
	String SourceOfDownPayment;
	public String getSourceOfDownPayment() {
		return SourceOfDownPayment;
	}
	public void setSourceOfDownPayment(String SourceOfDownPayment) {
		this.SourceOfDownPayment = SourceOfDownPayment;
	}
	@JsonProperty("2990")
	String RefinancePurposeType;
	public String getRefinancePurposeType() {
		return RefinancePurposeType;
	}
	public void setRefinancePurposeType(String RefinancePurposeType) {
		this.RefinancePurposeType = RefinancePurposeType;
	}
	@JsonProperty("6010")
	String BuildingStatusType;
	public String getBuildingStatusType() {
		return BuildingStatusType;
	}
	public void setBuildingStatusType(String BuildingStatusType) {
		this.BuildingStatusType = BuildingStatusType;
	}
	
	//Loan Info Page
	@JsonProperty("4250")
	String TemporarySubsidyBuydownIndicator;
	public String getTemporarySubsidyBuydownIndicator() {
		return TemporarySubsidyBuydownIndicator;
	}
	public void setTemporarySubsidyBuydownIndicator(String TemporarySubsidyBuydownIndicator) {
		this.TemporarySubsidyBuydownIndicator = TemporarySubsidyBuydownIndicator;
	}
	@JsonProperty("casasrN.X1410")
	String BuydownContributorType;
	public String getBuydownContributorType() {
		return BuydownContributorType;
	}
	public void setBuydownContributorType(String BuydownContributorType) {
		this.BuydownContributorType = BuydownContributorType;
	}
//	@JsonProperty("casasrN.X1680")
//	String HELOCCreditLimit;
//	public String getHELOCCreditLimit() {
//		return HELOCCreditLimit;
//	}
//	public void setHELOCCreditLimit(String HELOCCreditLimit) {
//		this.HELOCCreditLimit = HELOCCreditLimit;
//	}
	@JsonProperty("casasrN.X1670")
	String HELOCDrawAmount;
	public String getHELOCDrawAmount() {
		return HELOCDrawAmount;
	}
	public void setHELOCDrawAmount(String HELOCDrawAmount) {
		this.HELOCDrawAmount = HELOCDrawAmount;
	}*/
	
	public HashMap<String,String> getLoansMap(){
		return loansMap;
	}
	public void setLoanMap(HashMap<String,String> loansMap){
		this.loansMap = loansMap;
	}
	
	
	@JsonAnyGetter(enabled=false)
	public Map<String, String> getAddress() {
	 return this.addressDetails;
	}
	@JsonAnySetter
	public void setAddress(String name, String value){
		this.addressDetails.put(name, value);
	}
	
	@JsonProperty("loan.applicationIds")
	String[] loanApplicationIds;
	public String[] getLoanApplicationIds() {
		return loanApplicationIds;
	}
	public void setLoanApplicationIds(String[] loanApplicationIds) {
		this.loanApplicationIds = loanApplicationIds;
	}
	
	@JsonProperty("mortgage/3")
	String[] mortgage;
	public String[] getMortgage() {
		return mortgage;
	}
	public void setMortgage(String[] mortgage) {
		this.mortgage = mortgage;
	}
	
	
	
	
}

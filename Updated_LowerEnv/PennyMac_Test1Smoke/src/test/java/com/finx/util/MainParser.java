package com.finx.util;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MainParser {
	String access_token;
	String entityName;
	String entityType;
	String id;
	String buttonText;	

	Integer totalNumberOfLoans;

	List loans = new ArrayList<Loan>();
	private Map<String, String> addressDetails = new HashMap<>();
	@JsonProperty("access_token")
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getEntityType() {
		return entityType;
	}
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getButtonText() {
		return buttonText;
	}
	public void setButtonText(String buttonText) {
		this.buttonText = buttonText;
	}

	public Integer getTotalNumberOfLoans() {
		return totalNumberOfLoans;
	}
	public void setTotalNumberOfLoans(Integer totalNumberOfLoans) {
		this.totalNumberOfLoans = totalNumberOfLoans;
	}
	public List<Loan> getLoans() {
		return loans;
	}
	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}
	
	/*@JsonProperty("loan.LoanNumber")
	String loanNumber;

	public String getLoanNumber() {
		return loanNumber;
	}

	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
	}*/
	
	@JsonAnyGetter(enabled= false)
	public Map<String, String> getAddress() {
	 return this.addressDetails;
	}
	@JsonAnySetter
	public void setAddress(String name, String value){
		this.addressDetails.put(name, value);
	}




}

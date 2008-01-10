package org.kuali.kra.budget.bo;

import java.util.LinkedHashMap;
import java.sql.Date;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.budget.BudgetDecimal;

public class BudgetProposalRate extends KraPersistableBusinessObjectBase {
	private String fiscalYear;
	private Boolean onOffCampusFlag;
	private String proposalNumber;
	private String rateClassCode;
	private String rateTypeCode;
	private Date startDate;
	private Integer budgetVersionNumber;
	private Integer activityTypeCode;
	private BudgetDecimal applicableRate;
	private BudgetDecimal instituteRate;

	public String getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	public Boolean getOnOffCampusFlag() {
		return onOffCampusFlag;
	}

	public void setOnOffCampusFlag(Boolean onOffCampusFlag) {
		this.onOffCampusFlag = onOffCampusFlag;
	}

	public String getProposalNumber() {
		return proposalNumber;
	}

	public void setProposalNumber(String proposalNumber) {
		this.proposalNumber = proposalNumber;
	}

	public String getRateClassCode() {
		return rateClassCode;
	}

	public void setRateClassCode(String rateClassCode) {
		this.rateClassCode = rateClassCode;
	}

	public String getRateTypeCode() {
		return rateTypeCode;
	}

	public void setRateTypeCode(String rateTypeCode) {
		this.rateTypeCode = rateTypeCode;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Integer getBudgetVersionNumber() {
		return budgetVersionNumber;
	}

	public void setBudgetVersionNumber(Integer budgetVersionNumber) {
		this.budgetVersionNumber = budgetVersionNumber;
	}

	public Integer getActivityTypeCode() {
		return activityTypeCode;
	}

	public void setActivityTypeCode(Integer activityTypeCode) {
		this.activityTypeCode = activityTypeCode;
	}

	public BudgetDecimal getApplicableRate() {
		return applicableRate;
	}

	public void setApplicableRate(BudgetDecimal applicableRate) {
		this.applicableRate = applicableRate;
	}

	public BudgetDecimal getInstituteRate() {
		return instituteRate;
	}

	public void setInstituteRate(BudgetDecimal instituteRate) {
		this.instituteRate = instituteRate;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("fiscalYear", getFiscalYear());
		hashMap.put("onOffCampusFlag", getOnOffCampusFlag());
		hashMap.put("proposalNumber", getProposalNumber());
		hashMap.put("rateClassCode", getRateClassCode());
		hashMap.put("rateTypeCode", getRateTypeCode());
		hashMap.put("startDate", getStartDate());
		hashMap.put("budgetVersionNumber", getBudgetVersionNumber());
		hashMap.put("activityTypeCode", getActivityTypeCode());
		hashMap.put("applicableRate", getApplicableRate());
		hashMap.put("instituteRate", getInstituteRate());
		return hashMap;
	}
}

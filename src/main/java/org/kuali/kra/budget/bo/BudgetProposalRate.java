package org.kuali.kra.budget.bo;

import java.util.LinkedHashMap;
import java.sql.Date;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class BudgetProposalRate extends KraPersistableBusinessObjectBase {
	private String fiscalYear;
	private Boolean onOffCampusFlag;
	private String proposalNumber;
	private Integer rateClassCode;
	private Integer rateTypeCode;
	private Date startDate;
	private Integer budgetVersionNumber;
	private Integer activityTypeCode;
	private Double applicableRate;
	private Double instituteRate;

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

	public Integer getRateClassCode() {
		return rateClassCode;
	}

	public void setRateClassCode(Integer rateClassCode) {
		this.rateClassCode = rateClassCode;
	}

	public Integer getRateTypeCode() {
		return rateTypeCode;
	}

	public void setRateTypeCode(Integer rateTypeCode) {
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

	public Double getApplicableRate() {
		return applicableRate;
	}

	public void setApplicableRate(Double applicableRate) {
		this.applicableRate = applicableRate;
	}

	public Double getInstituteRate() {
		return instituteRate;
	}

	public void setInstituteRate(Double instituteRate) {
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

package org.kuali.kra.budget.bo;

import java.util.LinkedHashMap;
import java.sql.Date;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.budget.BudgetDecimal;

public class BudgetPersonnelDetails extends KraPersistableBusinessObjectBase {
	private Integer budgetPeriod;
	private Integer lineItemNumber;
	private Integer personNumber;
	private String proposalNumber;
	private Integer budgetVersionNumber;
	private Boolean applyInRateFlag;
	private String budgetJustification;
	private BudgetDecimal costSharingAmount;
	private BudgetDecimal costSharingPercent;
	private Date endDate;
	private String jobCode;
	private String lineItemDescription;
	private Boolean onOffCampusFlag;
	private BudgetDecimal percentCharged;
	private BudgetDecimal percentEffort;
	private String periodType;
	private String personId;
	private BudgetDecimal salaryRequested;
	private Integer sequenceNumber;
	private Date startDate;
	private BudgetDecimal underrecoveryAmount;

	public Integer getBudgetPeriod() {
		return budgetPeriod;
	}

	public void setBudgetPeriod(Integer budgetPeriod) {
		this.budgetPeriod = budgetPeriod;
	}

	public Integer getLineItemNumber() {
		return lineItemNumber;
	}

	public void setLineItemNumber(Integer lineItemNumber) {
		this.lineItemNumber = lineItemNumber;
	}

	public Integer getPersonNumber() {
		return personNumber;
	}

	public void setPersonNumber(Integer personNumber) {
		this.personNumber = personNumber;
	}

	public String getProposalNumber() {
		return proposalNumber;
	}

	public void setProposalNumber(String proposalNumber) {
		this.proposalNumber = proposalNumber;
	}

	public Integer getBudgetVersionNumber() {
		return budgetVersionNumber;
	}

	public void setBudgetVersionNumber(Integer budgetVersionNumber) {
		this.budgetVersionNumber = budgetVersionNumber;
	}

	public Boolean getApplyInRateFlag() {
		return applyInRateFlag;
	}

	public void setApplyInRateFlag(Boolean applyInRateFlag) {
		this.applyInRateFlag = applyInRateFlag;
	}

	public String getBudgetJustification() {
		return budgetJustification;
	}

	public void setBudgetJustification(String budgetJustification) {
		this.budgetJustification = budgetJustification;
	}

	public BudgetDecimal getCostSharingAmount() {
		return costSharingAmount;
	}

	public void setCostSharingAmount(BudgetDecimal costSharingAmount) {
		this.costSharingAmount = costSharingAmount;
	}

	public BudgetDecimal getCostSharingPercent() {
		return costSharingPercent;
	}

	public void setCostSharingPercent(BudgetDecimal costSharingPercent) {
		this.costSharingPercent = costSharingPercent;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getLineItemDescription() {
		return lineItemDescription;
	}

	public void setLineItemDescription(String lineItemDescription) {
		this.lineItemDescription = lineItemDescription;
	}

	public Boolean getOnOffCampusFlag() {
		return onOffCampusFlag;
	}

	public void setOnOffCampusFlag(Boolean onOffCampusFlag) {
		this.onOffCampusFlag = onOffCampusFlag;
	}

	public BudgetDecimal getPercentCharged() {
		return percentCharged;
	}

	public void setPercentCharged(BudgetDecimal percentCharged) {
		this.percentCharged = percentCharged;
	}

	public BudgetDecimal getPercentEffort() {
		return percentEffort;
	}

	public void setPercentEffort(BudgetDecimal percentEffort) {
		this.percentEffort = percentEffort;
	}

	public String getPeriodType() {
		return periodType;
	}

	public void setPeriodType(String periodType) {
		this.periodType = periodType;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public BudgetDecimal getSalaryRequested() {
		return salaryRequested;
	}

	public void setSalaryRequested(BudgetDecimal salaryRequested) {
		this.salaryRequested = salaryRequested;
	}

	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public BudgetDecimal getUnderrecoveryAmount() {
		return underrecoveryAmount;
	}

	public void setUnderrecoveryAmount(BudgetDecimal underrecoveryAmount) {
		this.underrecoveryAmount = underrecoveryAmount;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("budgetPeriod", getBudgetPeriod());
		hashMap.put("lineItemNumber", getLineItemNumber());
		hashMap.put("personNumber", getPersonNumber());
		hashMap.put("proposalNumber", getProposalNumber());
		hashMap.put("budgetVersionNumber", getBudgetVersionNumber());
		hashMap.put("applyInRateFlag", getApplyInRateFlag());
		hashMap.put("budgetJustification", getBudgetJustification());
		hashMap.put("costSharingAmount", getCostSharingAmount());
		hashMap.put("costSharingPercent", getCostSharingPercent());
		hashMap.put("endDate", getEndDate());
		hashMap.put("jobCode", getJobCode());
		hashMap.put("lineItemDescription", getLineItemDescription());
		hashMap.put("onOffCampusFlag", getOnOffCampusFlag());
		hashMap.put("percentCharged", getPercentCharged());
		hashMap.put("percentEffort", getPercentEffort());
		hashMap.put("periodType", getPeriodType());
		hashMap.put("personId", getPersonId());
		hashMap.put("salaryRequested", getSalaryRequested());
		hashMap.put("sequenceNumber", getSequenceNumber());
		hashMap.put("startDate", getStartDate());
		hashMap.put("underrecoveryAmount", getUnderrecoveryAmount());
		return hashMap;
	}
}

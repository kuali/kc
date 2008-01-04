package org.kuali.kra.budget.bo;

import java.util.LinkedHashMap;
import java.sql.Date;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class BudgetRateAndBase extends KraPersistableBusinessObjectBase {
	private Integer budgetPeriod;
	private Integer lineItemNumber;
	private String proposalNumber;
	private Integer rateClassCode;
	private Integer rateNumber;
	private Integer rateTypeCode;
	private Integer budgetVersionNumber;
	private Double appliedRate;
	private Double baseCost;
	private Double baseCostSharing;
	private Double calculatedCost;
	private Double calculatedCostSharing;
	private Date endDate;
	private Boolean onOffCampusFlag;
	private Date startDate;

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

	public Integer getRateNumber() {
		return rateNumber;
	}

	public void setRateNumber(Integer rateNumber) {
		this.rateNumber = rateNumber;
	}

	public Integer getRateTypeCode() {
		return rateTypeCode;
	}

	public void setRateTypeCode(Integer rateTypeCode) {
		this.rateTypeCode = rateTypeCode;
	}

	public Integer getBudgetVersionNumber() {
		return budgetVersionNumber;
	}

	public void setBudgetVersionNumber(Integer budgetVersionNumber) {
		this.budgetVersionNumber = budgetVersionNumber;
	}

	public Double getAppliedRate() {
		return appliedRate;
	}

	public void setAppliedRate(Double appliedRate) {
		this.appliedRate = appliedRate;
	}

	public Double getBaseCost() {
		return baseCost;
	}

	public void setBaseCost(Double baseCost) {
		this.baseCost = baseCost;
	}

	public Double getBaseCostSharing() {
		return baseCostSharing;
	}

	public void setBaseCostSharing(Double baseCostSharing) {
		this.baseCostSharing = baseCostSharing;
	}

	public Double getCalculatedCost() {
		return calculatedCost;
	}

	public void setCalculatedCost(Double calculatedCost) {
		this.calculatedCost = calculatedCost;
	}

	public Double getCalculatedCostSharing() {
		return calculatedCostSharing;
	}

	public void setCalculatedCostSharing(Double calculatedCostSharing) {
		this.calculatedCostSharing = calculatedCostSharing;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Boolean getOnOffCampusFlag() {
		return onOffCampusFlag;
	}

	public void setOnOffCampusFlag(Boolean onOffCampusFlag) {
		this.onOffCampusFlag = onOffCampusFlag;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("budgetPeriod", getBudgetPeriod());
		hashMap.put("lineItemNumber", getLineItemNumber());
		hashMap.put("proposalNumber", getProposalNumber());
		hashMap.put("rateClassCode", getRateClassCode());
		hashMap.put("rateNumber", getRateNumber());
		hashMap.put("rateTypeCode", getRateTypeCode());
		hashMap.put("budgetVersionNumber", getBudgetVersionNumber());
		hashMap.put("appliedRate", getAppliedRate());
		hashMap.put("baseCost", getBaseCost());
		hashMap.put("baseCostSharing", getBaseCostSharing());
		hashMap.put("calculatedCost", getCalculatedCost());
		hashMap.put("calculatedCostSharing", getCalculatedCostSharing());
		hashMap.put("endDate", getEndDate());
		hashMap.put("onOffCampusFlag", getOnOffCampusFlag());
		hashMap.put("startDate", getStartDate());
		return hashMap;
	}
}

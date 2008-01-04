package org.kuali.kra.budget.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class BudgetPersonnelCalculatedAmount extends KraPersistableBusinessObjectBase {
	private Integer budgetPeriod;
	private Integer lineItemNumber;
	private Integer personNumber;
	private String proposalNumber;
	private Integer rateClassCode;
	private Integer rateTypeCode;
	private Integer budgetBudgetVersionNumber;
	private Boolean applyRateFlag;
	private Double calculatedCost;
	private Double calculatedCostSharing;

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

	public Integer getBudgetVersionNumber() {
		return budgetBudgetVersionNumber;
	}

	public void setBudgetVersionNumber(Integer budgetBudgetVersionNumber) {
		this.budgetBudgetVersionNumber = budgetBudgetVersionNumber;
	}

	public Boolean getApplyRateFlag() {
		return applyRateFlag;
	}

	public void setApplyRateFlag(Boolean applyRateFlag) {
		this.applyRateFlag = applyRateFlag;
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


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("budgetPeriod", getBudgetPeriod());
		hashMap.put("lineItemNumber", getLineItemNumber());
		hashMap.put("personNumber", getPersonNumber());
		hashMap.put("proposalNumber", getProposalNumber());
		hashMap.put("rateClassCode", getRateClassCode());
		hashMap.put("rateTypeCode", getRateTypeCode());
		hashMap.put("budgetBudgetVersionNumber", getBudgetVersionNumber());
		hashMap.put("applyRateFlag", getApplyRateFlag());
		hashMap.put("calculatedCost", getCalculatedCost());
		hashMap.put("calculatedCostSharing", getCalculatedCostSharing());
		return hashMap;
	}
}

package org.kuali.kra.budget.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.budget.BudgetDecimal;

public class BudgetLineItemCalculatedAmount extends KraPersistableBusinessObjectBase {
	private Integer budgetPeriod;
	private Integer lineItemNumber;
	private String proposalNumber;
	private String rateClassCode;
	private String rateTypeCode;
	private Integer budgetVersionNumber;
	private Boolean applyRateFlag;
	private BudgetDecimal calculatedCost;
	private BudgetDecimal calculatedCostSharing;

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

	public Integer getBudgetVersionNumber() {
		return budgetVersionNumber;
	}

	public void setBudgetVersionNumber(Integer budgetVersionNumber) {
		this.budgetVersionNumber = budgetVersionNumber;
	}

	public Boolean getApplyRateFlag() {
		return applyRateFlag;
	}

	public void setApplyRateFlag(Boolean applyRateFlag) {
		this.applyRateFlag = applyRateFlag;
	}

	public BudgetDecimal getCalculatedCost() {
		return calculatedCost;
	}

	public void setCalculatedCost(BudgetDecimal calculatedCost) {
		this.calculatedCost = calculatedCost;
	}

	public BudgetDecimal getCalculatedCostSharing() {
		return calculatedCostSharing;
	}

	public void setCalculatedCostSharing(BudgetDecimal calculatedCostSharing) {
		this.calculatedCostSharing = calculatedCostSharing;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("budgetPeriod", getBudgetPeriod());
		hashMap.put("lineItemNumber", getLineItemNumber());
		hashMap.put("proposalNumber", getProposalNumber());
		hashMap.put("rateClassCode", getRateClassCode());
		hashMap.put("rateTypeCode", getRateTypeCode());
		hashMap.put("versionNumber", getVersionNumber());
		hashMap.put("applyRateFlag", getApplyRateFlag());
		hashMap.put("calculatedCost", getCalculatedCost());
		hashMap.put("calculatedCostSharing", getCalculatedCostSharing());
		return hashMap;
	}
}

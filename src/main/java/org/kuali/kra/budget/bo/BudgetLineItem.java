package org.kuali.kra.budget.bo;

import java.util.LinkedHashMap;
import java.util.List;
import java.sql.Date;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class BudgetLineItem extends KraPersistableBusinessObjectBase {
	private Integer budgetPeriod;
	private Integer lineItemNumber;
	private String proposalNumber;
	private Integer budgetVersionNumber;
	private Boolean applyInRateFlag;
	private Integer basedOnLineItem;
	private Integer budgetCategoryCode;
	private String budgetJustification;
	private String costElement;
	private Double costSharingAmount;
	private Date endDate;
	private Double lineItemCost;
	private String lineItemDescription;
	private Integer lineItemSequence;
	private Boolean onOffCampusFlag;
	private Integer quantity;
	private Date startDate;
	private Double underrecoveryAmount;
	private List<BudgetLineItemCalculatedAmount> budgetLineItemCalculatedAmounts;
	private List<BudgetPersonnelDetails> budgetPersonnelDetailsList;
	private BudgetCategory budgetCategory;
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

	public Integer getBasedOnLineItem() {
		return basedOnLineItem;
	}

	public void setBasedOnLineItem(Integer basedOnLineItem) {
		this.basedOnLineItem = basedOnLineItem;
	}

	public Integer getBudgetCategoryCode() {
		return budgetCategoryCode;
	}

	public void setBudgetCategoryCode(Integer budgetCategoryCode) {
		this.budgetCategoryCode = budgetCategoryCode;
	}

	public String getBudgetJustification() {
		return budgetJustification;
	}

	public void setBudgetJustification(String budgetJustification) {
		this.budgetJustification = budgetJustification;
	}

	public String getCostElement() {
		return costElement;
	}

	public void setCostElement(String costElement) {
		this.costElement = costElement;
	}

	public Double getCostSharingAmount() {
		return costSharingAmount;
	}

	public void setCostSharingAmount(Double costSharingAmount) {
		this.costSharingAmount = costSharingAmount;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Double getLineItemCost() {
		return lineItemCost;
	}

	public void setLineItemCost(Double lineItemCost) {
		this.lineItemCost = lineItemCost;
	}

	public String getLineItemDescription() {
		return lineItemDescription;
	}

	public void setLineItemDescription(String lineItemDescription) {
		this.lineItemDescription = lineItemDescription;
	}

	public Integer getLineItemSequence() {
		return lineItemSequence;
	}

	public void setLineItemSequence(Integer lineItemSequence) {
		this.lineItemSequence = lineItemSequence;
	}

	public Boolean getOnOffCampusFlag() {
		return onOffCampusFlag;
	}

	public void setOnOffCampusFlag(Boolean onOffCampusFlag) {
		this.onOffCampusFlag = onOffCampusFlag;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Double getUnderrecoveryAmount() {
		return underrecoveryAmount;
	}

	public void setUnderrecoveryAmount(Double underrecoveryAmount) {
		this.underrecoveryAmount = underrecoveryAmount;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("budgetPeriod", getBudgetPeriod());
		hashMap.put("lineItemNumber", getLineItemNumber());
		hashMap.put("proposalNumber", getProposalNumber());
		hashMap.put("versionNumber", getVersionNumber());
		hashMap.put("applyInRateFlag", getApplyInRateFlag());
		hashMap.put("basedOnLineItem", getBasedOnLineItem());
		hashMap.put("budgetCategoryCode", getBudgetCategoryCode());
		hashMap.put("budgetJustification", getBudgetJustification());
		hashMap.put("costElement", getCostElement());
		hashMap.put("costSharingAmount", getCostSharingAmount());
		hashMap.put("endDate", getEndDate());
		hashMap.put("lineItemCost", getLineItemCost());
		hashMap.put("lineItemDescription", getLineItemDescription());
		hashMap.put("lineItemSequence", getLineItemSequence());
		hashMap.put("onOffCampusFlag", getOnOffCampusFlag());
		hashMap.put("quantity", getQuantity());
		hashMap.put("startDate", getStartDate());
		hashMap.put("underrecoveryAmount", getUnderrecoveryAmount());
		return hashMap;
	}

    public List<BudgetLineItemCalculatedAmount> getBudgetLineItemCalculatedAmounts() {
        return budgetLineItemCalculatedAmounts;
    }

    public void setBudgetLineItemCalculatedAmounts(List<BudgetLineItemCalculatedAmount> budgetLineItemCalculatedAmounts) {
        this.budgetLineItemCalculatedAmounts = budgetLineItemCalculatedAmounts;
    }

    public List<BudgetPersonnelDetails> getBudgetPersonnelDetailsList() {
        return budgetPersonnelDetailsList;
    }

    public void setBudgetPersonnelDetailsList(List<BudgetPersonnelDetails> budgetPersonnelDetailsList) {
        this.budgetPersonnelDetailsList = budgetPersonnelDetailsList;
    }

    public BudgetCategory getBudgetCategory() {
        return budgetCategory;
    }

    public void setBudgetCategory(BudgetCategory budgetCategory) {
        this.budgetCategory = budgetCategory;
    }
}

package org.kuali.kra.budget.bo;

import java.util.LinkedHashMap;
import java.util.List;
import java.sql.Date;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.budget.BudgetDecimal;

public class BudgetLineItem extends BudgetLineItemBase {
	private List<BudgetPersonnelDetails> budgetPersonnelDetailsList;


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

    public List<BudgetPersonnelDetails> getBudgetPersonnelDetailsList() {
        return budgetPersonnelDetailsList;
    }

    public void setBudgetPersonnelDetailsList(List<BudgetPersonnelDetails> budgetPersonnelDetailsList) {
        this.budgetPersonnelDetailsList = budgetPersonnelDetailsList;
    }

}

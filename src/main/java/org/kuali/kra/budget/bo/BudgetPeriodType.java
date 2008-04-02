package org.kuali.kra.budget.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class BudgetPeriodType extends KraPersistableBusinessObjectBase {
	private String budgetPeriodTypeCode;
	private String description;

	public String getBudgetPeriodTypeCode() {
		return budgetPeriodTypeCode;
	}

	public void setBudgetPeriodTypeCode(String budgetPeriodTypeCode) {
		this.budgetPeriodTypeCode = budgetPeriodTypeCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@SuppressWarnings("unchecked")
	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("budgetPeriodTypeCode", getBudgetPeriodTypeCode());
		hashMap.put("description", getDescription());
		return hashMap;
	}
}

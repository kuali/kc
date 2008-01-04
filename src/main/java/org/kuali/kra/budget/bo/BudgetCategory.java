package org.kuali.kra.budget.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class BudgetCategory extends KraPersistableBusinessObjectBase {
	private Integer budgetCategoryCode;
	private Boolean categoryType;
	private String description;

	public Integer getBudgetCategoryCode() {
		return budgetCategoryCode;
	}

	public void setBudgetCategoryCode(Integer budgetCategoryCode) {
		this.budgetCategoryCode = budgetCategoryCode;
	}

	public Boolean getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(Boolean categoryType) {
		this.categoryType = categoryType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("budgetCategoryCode", getBudgetCategoryCode());
		hashMap.put("categoryType", getCategoryType());
		hashMap.put("description", getDescription());
		return hashMap;
	}
}

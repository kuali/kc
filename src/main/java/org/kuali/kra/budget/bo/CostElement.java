package org.kuali.kra.budget.bo;

import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class CostElement extends KraPersistableBusinessObjectBase {
	private String costElement;
	private String budgetCategoryCode;
	private String description;
	private Boolean onOffCampusFlag;
	private List<ValidCeRateType> validCeRateTypes;

	public String getCostElement() {
		return costElement;
	}

	public void setCostElement(String costElement) {
		this.costElement = costElement;
	}

	public String getBudgetCategoryCode() {
		return budgetCategoryCode;
	}

	public void setBudgetCategoryCode(String budgetCategoryCode) {
		this.budgetCategoryCode = budgetCategoryCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getOnOffCampusFlag() {
		return onOffCampusFlag;
	}

	public void setOnOffCampusFlag(Boolean onOffCampusFlag) {
		this.onOffCampusFlag = onOffCampusFlag;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("costElement", getCostElement());
		hashMap.put("budgetCategoryCode", getBudgetCategoryCode());
		hashMap.put("description", getDescription());
		hashMap.put("onOffCampusFlag", getOnOffCampusFlag());
		return hashMap;
	}

    public List<ValidCeRateType> getValidCeRateTypes() {
        return validCeRateTypes;
    }

    public void setValidaCRateTypes(List<ValidCeRateType> validCeRateTypes) {
        this.validCeRateTypes = validCeRateTypes;
    }
}

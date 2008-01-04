package org.kuali.kra.budget.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class BudgetCategoryMap extends KraPersistableBusinessObjectBase {
	private String mappingName;
	private String targetCategoryCode;
	private String categoryType;
	private String description;

	public String getMappingName() {
		return mappingName;
	}

	public void setMappingName(String mappingName) {
		this.mappingName = mappingName;
	}

	public String getTargetCategoryCode() {
		return targetCategoryCode;
	}

	public void setTargetCategoryCode(String targetCategoryCode) {
		this.targetCategoryCode = targetCategoryCode;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
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
		hashMap.put("mappingName", getMappingName());
		hashMap.put("targetCategoryCode", getTargetCategoryCode());
		hashMap.put("categoryType", getCategoryType());
		hashMap.put("description", getDescription());
		return hashMap;
	}
}

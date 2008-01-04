package org.kuali.kra.budget.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class BudgetCategoryMapping extends KraPersistableBusinessObjectBase {
	private Integer coeusCategoryCode;
	private String mappingName;
	private String targetCategoryCode;

	public Integer getCoeusCategoryCode() {
		return coeusCategoryCode;
	}

	public void setCoeusCategoryCode(Integer coeusCategoryCode) {
		this.coeusCategoryCode = coeusCategoryCode;
	}

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


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("coeusCategoryCode", getCoeusCategoryCode());
		hashMap.put("mappingName", getMappingName());
		hashMap.put("targetCategoryCode", getTargetCategoryCode());
		return hashMap;
	}
}

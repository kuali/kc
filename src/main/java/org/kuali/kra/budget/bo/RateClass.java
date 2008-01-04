package org.kuali.kra.budget.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class RateClass extends KraPersistableBusinessObjectBase {
	private Integer rateClassCode;
	private String description;
	private Boolean rateClassType;

	public Integer getRateClassCode() {
		return rateClassCode;
	}

	public void setRateClassCode(Integer rateClassCode) {
		this.rateClassCode = rateClassCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getRateClassType() {
		return rateClassType;
	}

	public void setRateClassType(Boolean rateClassType) {
		this.rateClassType = rateClassType;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("rateClassCode", getRateClassCode());
		hashMap.put("description", getDescription());
		hashMap.put("rateClassType", getRateClassType());
		return hashMap;
	}
}

package org.kuali.kra.budget.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class RateClassType extends KraPersistableBusinessObjectBase {

	private String rateClassType;
	private String description;

	public RateClassType(){
		super();
	}

	public String getRateClassType() {
		return rateClassType;
	}

	public void setRateClassType(String rateClassType) {
		this.rateClassType = rateClassType;
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
		hashMap.put("rateClassType", getRateClassType());
		hashMap.put("description", getDescription());
		return hashMap;
	}
}

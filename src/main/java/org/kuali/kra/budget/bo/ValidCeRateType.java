package org.kuali.kra.budget.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class ValidCeRateType extends KraPersistableBusinessObjectBase {
	private String costElement;
	private Integer rateClassCode;
	private Integer rateTypeCode;

	public String getCostElement() {
		return costElement;
	}

	public void setCostElement(String costElement) {
		this.costElement = costElement;
	}

	public Integer getRateClassCode() {
		return rateClassCode;
	}

	public void setRateClassCode(Integer rateClassCode) {
		this.rateClassCode = rateClassCode;
	}

	public Integer getRateTypeCode() {
		return rateTypeCode;
	}

	public void setRateTypeCode(Integer rateTypeCode) {
		this.rateTypeCode = rateTypeCode;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("costElement", getCostElement());
		hashMap.put("rateClassCode", getRateClassCode());
		hashMap.put("rateTypeCode", getRateTypeCode());
		return hashMap;
	}
}

package org.kuali.kra.budget.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class ValidCalcType extends KraPersistableBusinessObjectBase {
	private String calcTypeId;
	private Integer dependentSeqNumber;
	private Boolean rateClassType;
	private Boolean dependentRateClassType;
	private Integer rateClassCode;
	private Integer rateTypeCode;

	public String getCalcTypeId() {
		return calcTypeId;
	}

	public void setCalcTypeId(String calcTypeId) {
		this.calcTypeId = calcTypeId;
	}

	public Integer getDependentSeqNumber() {
		return dependentSeqNumber;
	}

	public void setDependentSeqNumber(Integer dependentSeqNumber) {
		this.dependentSeqNumber = dependentSeqNumber;
	}

	public Boolean getRateClassType() {
		return rateClassType;
	}

	public void setRateClassType(Boolean rateClassType) {
		this.rateClassType = rateClassType;
	}

	public Boolean getDependentRateClassType() {
		return dependentRateClassType;
	}

	public void setDependentRateClassType(Boolean dependentRateClassType) {
		this.dependentRateClassType = dependentRateClassType;
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
		hashMap.put("calcTypeId", getCalcTypeId());
		hashMap.put("dependentSeqNumber", getDependentSeqNumber());
		hashMap.put("rateClassType", getRateClassType());
		hashMap.put("dependentRateClassType", getDependentRateClassType());
		hashMap.put("rateClassCode", getRateClassCode());
		hashMap.put("rateTypeCode", getRateTypeCode());
		return hashMap;
	}
}

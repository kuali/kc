package org.kuali.kra.budget.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class ValidCalcType extends KraPersistableBusinessObjectBase {
	private String calcTypeId;
	private Integer dependentSeqNumber;
	private String rateClassType;
	private String dependentRateClassType;
	private String rateClassCode;
	private String rateTypeCode;

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

	public String getRateClassType() {
		return rateClassType;
	}

	public void setRateClassType(String rateClassType) {
		this.rateClassType = rateClassType;
	}

	public String getDependentRateClassType() {
		return dependentRateClassType;
	}

	public void setDependentRateClassType(String dependentRateClassType) {
		this.dependentRateClassType = dependentRateClassType;
	}

	public String getRateClassCode() {
		return rateClassCode;
	}

	public void setRateClassCode(String rateClassCode) {
		this.rateClassCode = rateClassCode;
	}

	public String getRateTypeCode() {
		return rateTypeCode;
	}

	public void setRateTypeCode(String rateTypeCode) {
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

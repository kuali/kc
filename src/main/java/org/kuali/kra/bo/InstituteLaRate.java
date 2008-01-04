package org.kuali.kra.bo;

import java.util.LinkedHashMap;
import java.sql.Date;

public class InstituteLaRate extends KraPersistableBusinessObjectBase {
	private String fiscalYear;
	private Boolean onOffCampusFlag;
	private Integer rateClassCode;
	private Integer rateTypeCode;
	private Date startDate;
	private String unitNumber;
	private Long rate;

	public String getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	public Boolean getOnOffCampusFlag() {
		return onOffCampusFlag;
	}

	public void setOnOffCampusFlag(Boolean onOffCampusFlag) {
		this.onOffCampusFlag = onOffCampusFlag;
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getUnitNumber() {
		return unitNumber;
	}

	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber;
	}

	public Long getRate() {
		return rate;
	}

	public void setRate(Long rate) {
		this.rate = rate;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("fiscalYear", getFiscalYear());
		hashMap.put("onOffCampusFlag", getOnOffCampusFlag());
		hashMap.put("rateClassCode", getRateClassCode());
		hashMap.put("rateTypeCode", getRateTypeCode());
		hashMap.put("startDate", getStartDate());
		hashMap.put("unitNumber", getUnitNumber());
		hashMap.put("rate", getRate());
		return hashMap;
	}
}

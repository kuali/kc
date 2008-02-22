package org.kuali.kra.bo;

import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.RateClass;
import org.kuali.kra.budget.bo.RateType;

public class InstituteLaRate extends KraPersistableBusinessObjectBase {
	private String fiscalYear;
	private Boolean onOffCampusFlag;
	private String rateClassCode;
	private String rateTypeCode;
	private Date startDate;
	private String unitNumber;
	private BudgetDecimal instituteRate;

	private RateClass rateClass;
	private RateType rateType;
	
	public RateClass getRateClass() {
        return rateClass;
    }

    public void setRateClass(RateClass rateClass) {
        this.rateClass = rateClass;
    }

    public RateType getRateType() {
        return rateType;
    }

    public void setRateType(RateType rateType) {
        this.rateType = rateType;
    }

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

	public BudgetDecimal getInstituteRate() {
		return instituteRate;
	}

	public void setInstituteRate(BudgetDecimal rate) {
		this.instituteRate = rate;
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
		hashMap.put("instituterate", getInstituteRate());
		return hashMap;
	}
}

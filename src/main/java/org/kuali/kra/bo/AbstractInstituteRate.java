/*
 * Copyright 2005-2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.bo;

import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.core.web.format.Formatter;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.RateClass;
import org.kuali.kra.budget.bo.RateType;
import org.kuali.kra.infrastructure.BudgetDecimalFormatter;

public abstract class AbstractInstituteRate extends KraPersistableBusinessObjectBase implements Comparable<AbstractInstituteRate>{
	private String fiscalYear;
	private Boolean onOffCampusFlag;
	private String rateClassCode;
	private String rateTypeCode;
	private Date startDate;
	private String unitNumber;
	private BudgetDecimal instituteRate;

	private RateClass rateClass;
	private RateType rateType;
	private Unit unit;
	
    public AbstractInstituteRate(){
        Formatter.registerFormatter(BudgetDecimal.class, BudgetDecimalFormatter.class);
    }

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
	
	public int compareTo(AbstractInstituteRate instituteLaRate) {
        int dataCmp = getRateType().getDescription().compareTo(instituteLaRate.getRateType().getDescription());
        return (dataCmp != 0 ? dataCmp : getFiscalYear().compareTo(instituteLaRate.getFiscalYear()));
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
	
}

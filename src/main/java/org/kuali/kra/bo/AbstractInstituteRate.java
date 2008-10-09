/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
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

import javax.naming.OperationNotSupportedException;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.kuali.core.web.format.Formatter;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.RateDecimal;
import org.kuali.kra.budget.bo.AbstractBudgetRate;
import org.kuali.kra.budget.bo.RateClass;
import org.kuali.kra.budget.bo.RateType;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.infrastructure.BudgetDecimalFormatter;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.RateDecimalFormatter;

@MappedSuperclass
public abstract class AbstractInstituteRate extends KraPersistableBusinessObjectBase implements Comparable<AbstractInstituteRate>, AbstractInstituteRateKey {
	
    @Id
    @Column(name="FISCAL_YEAR")
    private String fiscalYear;
    
    @Id
    @Column(name="ON_OFF_CAMPUS_FLAG")
	private Boolean onOffCampusFlag;
	
    @Id
    @Column(name="RATE_CLASS_CODE")
	private String rateClassCode;
    
    @Id
    @Column(name="RATE_TYPE_CODE")
	private String rateTypeCode;
	
    @Id
	@Column(name="START_DATE")
	private Date startDate;
	
    @Id
	@Column(name="UNIT_NUMBER")
	private String unitNumber;
	
	@Column(name="RATE")
	private BudgetDecimal instituteRate;

	private RateClass rateClass;
	private RateType rateType;
	private Unit unit;
	
	@Column(name="ACTIVE_FLAG")
    private Boolean active = true;
	
    
	public final Boolean getActive() {
        return active;
    }

    public final void setActive(Boolean active) {
        this.active = active;
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


	@SuppressWarnings("unchecked")
    @Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("versionNumber", getVersionNumber());
		hashMap.put("updateTimestamp", getUpdateTimestamp());
		hashMap.put("fiscalYear", getFiscalYear());
		hashMap.put("onOffCampusFlag", getOnOffCampusFlag());
		hashMap.put("rateClassCode", getRateClassCode());
		hashMap.put("rateTypeCode", getRateTypeCode());
		hashMap.put("startDate", getStartDate());
		hashMap.put("unitNumber", getUnitNumber());
		hashMap.put("instituterate", getInstituteRate());
		return hashMap;
	}
	
	public int compareTo(AbstractInstituteRate abstractInstituteRate) {
        int result = getRateType().getDescription().compareTo(abstractInstituteRate.getRateType().getDescription());
        result = result != 0 ? result : getFiscalYear().compareTo(abstractInstituteRate.getFiscalYear());
        result = result != 0 ? result : getOnOffCampusFlag().compareTo(abstractInstituteRate.getOnOffCampusFlag());
        return result;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
    
    public String getRateKeyAsString() {
        return new StringBuilder(getRateClassCode())
                    .append(getRateTypeCode())
                    .append(getLocationFlagAsString(getOnOffCampusFlag()))
                    .append(getStartDate())
                    .toString();
    }
    
    protected AbstractBudgetRate createBudgetRate() {
        throw new RuntimeException(new OperationNotSupportedException("Cannot create BudgetRate."));
    }
    
    private String getLocationFlagAsString(boolean onOffCampusFlag) {
        return onOffCampusFlag ? Constants.ON_CAMUS_FLAG : Constants.OFF_CAMUS_FLAG;
    }
}

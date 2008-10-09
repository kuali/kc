/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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

package org.kuali.kra.bo.id;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Abstract Primary Key for Institute Rate BOs.
 */
public abstract class AbstractInstituteRateId implements Serializable {
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
    
    public String getFiscalYear() {
        return this.fiscalYear;
    }
    
    public Boolean getOnOffCampusFlag() {
        return this.onOffCampusFlag;
    }
    
    public String getRateClassCode() {
        return this.rateClassCode;
    }
    
    public String getRateTypeCode() {
        return this.rateTypeCode;
    }
    
    public Date getStartDate() {
        return this.startDate;
    }
    
    public String getUnitNumber() {
        return this.unitNumber;
    }
    
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof AbstractInstituteRateId)) return false;
        if (obj == null) return false;
        AbstractInstituteRateId other = (AbstractInstituteRateId) obj;
        return StringUtils.equals(fiscalYear, other.fiscalYear) &&
               ObjectUtils.equals(onOffCampusFlag, other.onOffCampusFlag) &&
               StringUtils.equals(rateClassCode, other.rateClassCode) &&
               StringUtils.equals(rateTypeCode, other.rateTypeCode) &&
               ObjectUtils.equals(startDate, other.startDate) &&
               StringUtils.equals(unitNumber, other.unitNumber);
    }
    
    protected HashCodeBuilder getHashCodeBuilder() {
        return new HashCodeBuilder().append(fiscalYear).append(onOffCampusFlag).
                                     append(rateClassCode).append(rateTypeCode).
                                     append(startDate).append(unitNumber);
    }
    
}

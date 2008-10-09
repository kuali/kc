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

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * Primary Key for the InstituteLaRate BO.
 */
@SuppressWarnings("serial")
public class InstituteLaRateId extends AbstractInstituteRateId {
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

    public boolean equals(Object obj) {
        return (super.equals(obj)) &&
               (obj instanceof InstituteLaRateId);
    }
    
    public int hashCode() {
        return getHashCodeBuilder().toHashCode();
    }
} 

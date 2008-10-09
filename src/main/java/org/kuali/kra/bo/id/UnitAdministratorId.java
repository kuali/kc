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

import javax.persistence.Column;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Primary Key for the UnitAdministrator BO.
 */
@SuppressWarnings("serial")
public class UnitAdministratorId implements Serializable {
    
    @Column(name="PERSON_ID")
    private String personId;

    @Column(name="UNIT_ADMINISTRATOR_TYPE_CODE")
    private String unitAdministratorTypeCode;

    @Column(name="UNIT_NUMBER")
    private String unitNumber;
    
    public String getPersonId() {
        return this.personId;
    }
    
    public String getUnitAdministratorTypeCode() {
        return this.unitAdministratorTypeCode;
    }
    
    public String getUnitNumber() {
        return this.unitNumber;
    }
    
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof UnitAdministratorId)) return false;
        if (obj == null) return false;
        UnitAdministratorId other = (UnitAdministratorId) obj;
        return StringUtils.equals(personId, other.personId) &&
               StringUtils.equals(unitAdministratorTypeCode, other.unitAdministratorTypeCode) &&
               StringUtils.equals(unitNumber, other.unitNumber);
    }
    
    public int hashCode() {
        return new HashCodeBuilder().append(personId).append(unitAdministratorTypeCode).append(unitNumber).toHashCode();
    }
}

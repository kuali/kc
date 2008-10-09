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
 * Primary Key for OrganizationAudit BO.
 */
@SuppressWarnings("serial")
public class OrganizationAuditId implements Serializable {
    
    @Column(name="FISCAL_YEAR")
    private String fiscalYear;
    
    @Column(name="ORGANIZATION_ID")
    private String organizationId;
    
    public String getFiscalYear() {
        return this.fiscalYear;
    }
    
    public String getOrganizationId() {
        return this.organizationId;
    }
    
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof OrganizationAuditId)) return false;
        if (obj == null) return false;
        OrganizationAuditId other = (OrganizationAuditId) obj;
        return StringUtils.equals(fiscalYear, other.fiscalYear) &&
               StringUtils.equals(organizationId, other.organizationId);
    }
    
    public int hashCode() {
        return new HashCodeBuilder().append(fiscalYear).append(organizationId).toHashCode();
    }
}

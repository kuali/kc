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
 * Primary Key for the SponsorHierarchy BO.
 */
@SuppressWarnings("serial")
public class SponsorHierarchyId implements Serializable {
    
    @Column(name="HIERARCHY_NAME")
    private String hierarchyName;

    @Column(name="SPONSOR_CODE")
    private String sponsorCode;
    
    public String getHierarchyName() {
        return this.hierarchyName;
    }
    
    public String getSponsorCode() {
        return this.sponsorCode;
    }
    
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof SponsorHierarchyId)) return false;
        if (obj == null) return false;
        SponsorHierarchyId other = (SponsorHierarchyId) obj;
        return StringUtils.equals(hierarchyName, other.hierarchyName) &&
               StringUtils.equals(sponsorCode, other.sponsorCode);
    }
    
    public int hashCode() {
        return new HashCodeBuilder().append(hierarchyName).append(sponsorCode).toHashCode();
    }
}

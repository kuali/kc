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

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Primary Key for SponsorForms BO.
 */
@SuppressWarnings("serial")
public class SponsorFormsId implements Serializable {

    @Column(name="PACKAGE_NUMBER")
    private Integer packageNumber;

    @Column(name="SPONSOR_CODE")
    private String sponsorCode;
    
    public Integer getPackageNumber() {
        return this.packageNumber;
    }
    
    public String getSponsorCode() {
        return this.sponsorCode;
    }
    
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof SponsorFormsId)) return false;
        if (obj == null) return false;
        SponsorFormsId other = (SponsorFormsId) obj;
        return ObjectUtils.equals(packageNumber, other.packageNumber) &&
               StringUtils.equals(sponsorCode, other.sponsorCode);
    }
    
    public int hashCode() {
        return new HashCodeBuilder().append(packageNumber).append(sponsorCode).toHashCode();
    }
}

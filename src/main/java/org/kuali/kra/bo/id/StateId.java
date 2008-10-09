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
 * Primary Key for the State BO.
 */
@SuppressWarnings("serial")
public class StateId implements Serializable {
    
    @Column(name="COUNTRY_CODE")
    private String countryCode;

    @Column(name="STATE_CODE")
    private String stateCode;
    
    public String getCountryCode() {
        return this.countryCode;
    }
    
    public String getStateCode() {
        return this.stateCode;
    }
    
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof StateId)) return false;
        if (obj == null) return false;
        StateId other = (StateId) obj;
        return StringUtils.equals(countryCode, other.countryCode) &&
               StringUtils.equals(stateCode, other.stateCode);
    }
    
    public int hashCode() {
        return new HashCodeBuilder().append(countryCode).append(stateCode).toHashCode();
    }
}

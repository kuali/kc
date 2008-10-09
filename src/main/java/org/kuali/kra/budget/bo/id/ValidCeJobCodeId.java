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
package org.kuali.kra.budget.bo.id;

import java.io.Serializable;

import javax.persistence.Column;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Primary Key for the ValidCeJobCode BO.
 */
@SuppressWarnings("serial")
public class ValidCeJobCodeId implements Serializable {
    
    @Column(name="COST_ELEMENT")
    private String costElement;

    @Column(name="JOB_CODE")
    private String jobCode;

    public String getCostElement() {
        return costElement;
    }

    public String getJobCode() {
        return jobCode;
    }
    
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof ValidCeJobCodeId)) return false;
        if (obj == null) return false;
        ValidCeJobCodeId other = (ValidCeJobCodeId) obj;
        return StringUtils.equals(costElement, other.costElement) &&
               StringUtils.equals(jobCode, other.jobCode);
    }
    
    public int hashCode() {
        return new HashCodeBuilder().append(costElement).append(jobCode).toHashCode();
    }
}

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
 * Primary Key for the BudgetCategoryMap BO.
 */
@SuppressWarnings("serial")
public class BudgetCategoryMapId implements Serializable {
    
    @Column(name="MAPPING_NAME")
    private String mappingName;

    @Column(name="TARGET_CATEGORY_CODE")
    private String targetCategoryCode;
    
    public String getMappingName() {
        return this.mappingName;
    }
    
    public String getTargetCategoryCode() {
        return this.targetCategoryCode;
    }
    
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof BudgetCategoryMapId)) return false;
        if (obj == null) return false;
        BudgetCategoryMapId other = (BudgetCategoryMapId) obj;
        return StringUtils.equals(mappingName, other.mappingName) &&
               StringUtils.equals(targetCategoryCode, other.targetCategoryCode);
    }
    
    public int hashCode() {
        return new HashCodeBuilder().append(mappingName).append(targetCategoryCode).toHashCode();
    }
}

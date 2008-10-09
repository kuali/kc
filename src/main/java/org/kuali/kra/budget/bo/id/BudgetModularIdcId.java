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
import javax.persistence.Id;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Primary Key for the BudgetModularIdc BO.
 */
@SuppressWarnings("serial")
public class BudgetModularIdcId implements Serializable {
    @Id
    @Column(name="BUDGET_PERIOD_NUMBER")
    private Long budgetPeriodId;

    @Id
    @Column(name="RATE_NUMBER")
    private Integer rateNumber;
    
    public Long getBudgetPeriodId() {
        return this.budgetPeriodId;
    }
    
    public Integer getTargetCategoryCode() {
        return this.rateNumber;
    }
    
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof BudgetModularIdcId)) return false;
        if (obj == null) return false;
        BudgetModularIdcId other = (BudgetModularIdcId) obj;
        return ObjectUtils.equals(budgetPeriodId, other.budgetPeriodId) &&
               ObjectUtils.equals(rateNumber, other.rateNumber);
    }
    
    public int hashCode() {
        return new HashCodeBuilder().append(budgetPeriodId).append(rateNumber).toHashCode();
    }
}

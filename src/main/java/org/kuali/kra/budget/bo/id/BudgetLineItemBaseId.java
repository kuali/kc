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
 * Abstract Primary Key for the BudgetLineItemBase BOs.
 */
public abstract class BudgetLineItemBaseId implements Serializable {
    @Id
    @Column(name="BUDGET_PERIOD_NUMBER")
    private Long budgetPeriodId;
  
    @Id
    @Column(name="LINE_ITEM_NUMBER")
    private Integer lineItemNumber;
    
    public Long getBudgetPeriodId() {
        return this.budgetPeriodId;
    }
    
    public Integer getLineItemNumber() {
        return this.lineItemNumber;
    }
    
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof BudgetLineItemBaseId)) return false;
        if (obj == null) return false;
        BudgetLineItemBaseId other = (BudgetLineItemBaseId) obj;
        return ObjectUtils.equals(budgetPeriodId, other.budgetPeriodId) &&
               ObjectUtils.equals(lineItemNumber, other.lineItemNumber);
    }
    
    protected HashCodeBuilder getHashCodeBuilder() {
        return new HashCodeBuilder().append(budgetPeriodId).append(lineItemNumber);
    }
}

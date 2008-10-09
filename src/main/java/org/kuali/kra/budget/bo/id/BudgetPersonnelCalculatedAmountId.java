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

import javax.persistence.Column;
import javax.persistence.Id;

import org.apache.commons.lang.ObjectUtils;

/**
 * Primary Key for the BudgetPersonnelCalculatedAmount BO.
 */
@SuppressWarnings("serial")
public class BudgetPersonnelCalculatedAmountId extends AbstractBudgetCalculatedAmountId {
    @Id
    @Column(name="BUDGET_PERIOD_NUMBER")
    private Long budgetPeriodId;

    @Id
    @Column(name="LINE_ITEM_NUMBER")
    private Integer lineItemNumber;
    
    @Id
    @Column(name="RATE_CLASS_CODE")
    private String rateClassCode;
    
    @Id
    @Column(name="RATE_TYPE_CODE")
    private String rateTypeCode;

    @Column(name="PERSON_NUMBER")
    private Integer personNumber;
    
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof BudgetPersonnelCalculatedAmountId)) return false;
        BudgetPersonnelCalculatedAmountId other = (BudgetPersonnelCalculatedAmountId) obj;
        return ObjectUtils.equals(personNumber, other.personNumber);
    }
    
    public int hashCode() {
        return getHashCodeBuilder().toHashCode();
    }
}

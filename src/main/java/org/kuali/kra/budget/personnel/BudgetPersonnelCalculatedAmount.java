/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.budget.personnel;

import org.kuali.kra.budget.nonpersonnel.AbstractBudgetCalculatedAmount;
import org.kuali.kra.infrastructure.DeepCopyIgnore;

public class BudgetPersonnelCalculatedAmount extends AbstractBudgetCalculatedAmount {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 3100896964798965084L;

    private Integer personNumber;

    @DeepCopyIgnore
    private Long budgetPersonnelCalculatedAmountId;

    private Long budgetPersonnelLineItemId;

    public Integer getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(Integer personNumber) {
        this.personNumber = personNumber;
    }

    /**
     * Gets the budgetPersonnelCalculatedAmountId attribute. 
     * @return Returns the budgetPersonnelCalculatedAmountId.
     */
    public Long getBudgetPersonnelCalculatedAmountId() {
        return budgetPersonnelCalculatedAmountId;
    }

    /**
     * Sets the budgetPersonnelCalculatedAmountId attribute value.
     * @param budgetPersonnelCalculatedAmountId The budgetPersonnelCalculatedAmountId to set.
     */
    public void setBudgetPersonnelCalculatedAmountId(Long budgetPersonnelCalculatedAmountId) {
        this.budgetPersonnelCalculatedAmountId = budgetPersonnelCalculatedAmountId;
    }

    /**
     * Gets the budgetPersonnelLineItemId attribute. 
     * @return Returns the budgetPersonnelLineItemId.
     */
    public Long getBudgetPersonnelLineItemId() {
        return budgetPersonnelLineItemId;
    }

    /**
     * Sets the budgetPersonnelLineItemId attribute value.
     * @param budgetPersonnelLineItemId The budgetPersonnelLineItemId to set.
     */
    public void setBudgetPersonnelLineItemId(Long budgetPersonnelLineItemId) {
        this.budgetPersonnelLineItemId = budgetPersonnelLineItemId;
    }
}

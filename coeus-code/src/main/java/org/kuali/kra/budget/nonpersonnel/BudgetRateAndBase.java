/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.budget.nonpersonnel;

import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.kra.budget.deepcopy.DeepCopyIgnore;

public class BudgetRateAndBase extends AbstractBudgetRateAndBase {


    private static final long serialVersionUID = -6003003851261499575L;

    private ScaleTwoDecimal baseCost;

    @DeepCopyIgnore
    private Long budgetRateAndBaseId;

    private Long budgetLineItemCalculatedAmountId;

    private Long budgetLineItemId;

    public ScaleTwoDecimal getBaseCost() {
        return baseCost;
    }

    public void setBaseCost(ScaleTwoDecimal baseCost) {
        this.baseCost = baseCost;
    }

    /**
     * Gets the budgetRateAndBaseId attribute. 
     * @return Returns the budgetRateAndBaseId.
     */
    public Long getBudgetRateAndBaseId() {
        return budgetRateAndBaseId;
    }

    /**
     * Sets the budgetRateAndBaseId attribute value.
     * @param budgetRateAndBaseId The budgetRateAndBaseId to set.
     */
    public void setBudgetRateAndBaseId(Long budgetRateAndBaseId) {
        this.budgetRateAndBaseId = budgetRateAndBaseId;
    }

    /**
     * Gets the budgetLineItemCalculatedAmountId attribute. 
     * @return Returns the budgetLineItemCalculatedAmountId.
     */
    public Long getBudgetLineItemCalculatedAmountId() {
        return budgetLineItemCalculatedAmountId;
    }

    /**
     * Sets the budgetLineItemCalculatedAmountId attribute value.
     * @param budgetLineItemCalculatedAmountId The budgetLineItemCalculatedAmountId to set.
     */
    public void setBudgetLineItemCalculatedAmountId(Long budgetLineItemCalculatedAmountId) {
        this.budgetLineItemCalculatedAmountId = budgetLineItemCalculatedAmountId;
    }

    /**
     * Gets the budgetLineItemId attribute. 
     * @return Returns the budgetLineItemId.
     */
    public Long getBudgetLineItemId() {
        return budgetLineItemId;
    }

    /**
     * Sets the budgetLineItemId attribute value.
     * @param budgetLineItemId The budgetLineItemId to set.
     */
    public void setBudgetLineItemId(Long budgetLineItemId) {
        this.budgetLineItemId = budgetLineItemId;
    }
}

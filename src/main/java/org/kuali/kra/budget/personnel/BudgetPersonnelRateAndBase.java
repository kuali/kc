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

import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.nonpersonnel.AbstractBudgetRateAndBase;
import org.kuali.kra.infrastructure.DeepCopyIgnore;

public class BudgetPersonnelRateAndBase extends AbstractBudgetRateAndBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -3822394019599765292L;

    @DeepCopyIgnore
    private Long budgetPersonnelRateAndBaseId;

    private Long budgetPersonnelCalculatedAmountId;

    private Long budgetPersonnelLineItemId;

    private String personId;

    private Integer personNumber;

    private BudgetDecimal salaryRequested;

    /**
     * Gets the salaryRequested attribute. 
     * @return Returns the salaryRequested.
     */
    public BudgetDecimal getSalaryRequested() {
        return salaryRequested;
    }

    /**
     * Sets the salaryRequested attribute value.
     * @param salaryRequested The salaryRequested to set.
     */
    public void setSalaryRequested(BudgetDecimal salaryRequested) {
        this.salaryRequested = salaryRequested;
    }

    /**
     * Gets the personNumber attribute. 
     * @return Returns the personNumber.
     */
    public Integer getPersonNumber() {
        return personNumber;
    }

    /**
     * Sets the personNumber attribute value.
     * @param personNumber The personNumber to set.
     */
    public void setPersonNumber(Integer personNumber) {
        this.personNumber = personNumber;
    }

    /**
     * Gets the personId attribute. 
     * @return Returns the personId.
     */
    public String getPersonId() {
        return personId;
    }

    /**
     * Sets the personId attribute value.
     * @param personId The personId to set.
     */
    public void setPersonId(String personId) {
        this.personId = personId;
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

    /**
     * Gets the budgetPersonnelRateAndBaseId attribute. 
     * @return Returns the budgetPersonnelRateAndBaseId.
     */
    public Long getBudgetPersonnelRateAndBaseId() {
        return budgetPersonnelRateAndBaseId;
    }

    /**
     * Sets the budgetPersonnelRateAndBaseId attribute value.
     * @param budgetPersonnelRateAndBaseId The budgetPersonnelRateAndBaseId to set.
     */
    public void setBudgetPersonnelRateAndBaseId(Long budgetPersonnelRateAndBaseId) {
        this.budgetPersonnelRateAndBaseId = budgetPersonnelRateAndBaseId;
    }
}

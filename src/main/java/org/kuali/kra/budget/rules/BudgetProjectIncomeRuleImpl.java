/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.budget.rules;

import org.kuali.kra.budget.bo.BudgetProjectIncome;
import org.kuali.kra.budget.rule.AddBudgetProjectIncomeRule;
import org.kuali.kra.budget.rule.event.AddBudgetProjectIncomeEvent;

/**
 * Processes Budget Project Income rules
 */
public class BudgetProjectIncomeRuleImpl implements AddBudgetProjectIncomeRule {

    /**
     * Provides general validation support
     */
    private ValidationHelper validationHelper;
    
    /**
     * Constructs a BudgetProjectIncomeRuleImpl
     */
    public BudgetProjectIncomeRuleImpl() {
        validationHelper = new ValidationHelper();
    }
    
    /**
     * @see org.kuali.kra.budget.rule.AddBudgetProjectIncomeRule#processAddBudgetProjectIncomeBusinessRules(org.kuali.kra.budget.rule.event.AddBudgetProjectIncomeEvent)
     */
    public boolean processAddBudgetProjectIncomeBusinessRules(AddBudgetProjectIncomeEvent addBudgetIncomeEvent) {
        return areRequiredRulesSatisfied(addBudgetIncomeEvent.getBudgetProjectIncome());
    }

    /**
     * This method checks each required field, tracking validation state
     * @param budgetProjectIncome The Budget Project Income
     * @return Validation state; true if all required fields are not null, and if String, not empty
     */
    private boolean areRequiredRulesSatisfied(BudgetProjectIncome budgetProjectIncome) {
        boolean valid = validationHelper.checkRequiredField(budgetProjectIncome.getBudgetPeriodNumber(), "projectIncome.newBudgetPeriod", "Budget Period");
        valid &= validationHelper.checkRequiredField(budgetProjectIncome.getProjectIncome(), "projectIncome.newProjectIncome", "Project Income");
        valid &= validationHelper.checkRequiredField(budgetProjectIncome.getDescription(), "projectIncome.newDescription", "Description");
        
        return valid;
    }

}

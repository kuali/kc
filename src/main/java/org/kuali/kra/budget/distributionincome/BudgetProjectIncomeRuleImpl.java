/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.budget.distributionincome;


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
     * @see org.kuali.kra.budget.distributionincome.AddBudgetProjectIncomeRule#processAddBudgetProjectIncomeBusinessRules(org.kuali.kra.budget.distributionincome.AddBudgetProjectIncomeEvent)
     */
    public boolean processAddBudgetProjectIncomeBusinessRules(AddBudgetProjectIncomeEvent addBudgetIncomeEvent) {
        BudgetProjectIncome projectIncome = addBudgetIncomeEvent.getBudgetProjectIncome();
        return areRequiredRulesSatisfied(projectIncome) && isProjectIncomeAmountValid(projectIncome);
    }

    private boolean isProjectIncomeAmountValid(BudgetProjectIncome budgetProjectIncome) {
        return validationHelper.checkValuePositive(budgetProjectIncome.getProjectIncome(), "newBudgetProjectIncome.projectIncome", "error.projectIncome.negativeOrZero", (String[])null);
    }

    /**
     * This method checks each required field, tracking validation state
     * @param budgetProjectIncome The Budget Project Income
     * @return Validation state; true if all required fields are not null, and if String, not empty
     */
    private boolean areRequiredRulesSatisfied(BudgetProjectIncome budgetProjectIncome) {
        boolean valid = validationHelper.checkRequiredField(budgetProjectIncome.getBudgetPeriodNumber(), "newBudgetProjectIncome.budgetPeriod", "Budget Period");
        valid &= validationHelper.checkRequiredField(budgetProjectIncome.getProjectIncome(), "newBudgetProjectIncome.projectIncome", "Project Income");
        valid &= validationHelper.checkRequiredField(budgetProjectIncome.getDescription(), "newBudgetProjectIncome.description", "Description");
        
        return valid;
    }

}

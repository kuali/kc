/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.common.budget.framework.income;


import org.kuali.coeus.common.budget.impl.core.ValidationHelper;
import org.kuali.coeus.common.framework.ruleengine.KcBusinessRule;
import org.kuali.coeus.common.framework.ruleengine.KcEventMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@KcBusinessRule
public class BudgetProjectIncomeRule {

	@Autowired
	@Qualifier("validationHelper")
    private ValidationHelper validationHelper;

    
    @KcEventMethod
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

	protected ValidationHelper getValidationHelper() {
		return validationHelper;
	}

	public void setValidationHelper(ValidationHelper validationHelper) {
		this.validationHelper = validationHelper;
	}

}

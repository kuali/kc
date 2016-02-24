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
package org.kuali.coeus.common.budget.impl.distribution;

import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.distribution.BudgetCostShare;
import org.kuali.coeus.common.framework.costshare.CostShareRuleResearchDocumentBase;
import org.kuali.coeus.common.framework.ruleengine.KcBusinessRule;
import org.kuali.coeus.common.framework.ruleengine.KcEventMethod;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * 
 * This class ProcessDefinitionDefinitiones Budget Project Income rules.
 */
@KcBusinessRule("budgetCostShareRule")
public class BudgetCostShareRule extends CostShareRuleResearchDocumentBase {

    private static final String ADD_ERROR_KEY = "error.custom";
    
    @KcEventMethod
    public boolean processAddBudgetCostShareBusinessRules(AddBudgetCostShareEvent budgetCostShareEvent) {
        boolean retVal = !areDuplicatesPresent(budgetCostShareEvent.getBudget(), budgetCostShareEvent.getBudgetCostShare());
        retVal &= validateProjectPeriod(budgetCostShareEvent);
        return retVal;
    }

    /**
     * This method ensures that an added BudgetCostShare won't duplicate another. A duplicate record would have the same source
     * account, share amount, and fiscal year as another already in the list.
     * 
     * @param testBudgetCostShare
     * @return
     */
    protected boolean areDuplicatesPresent(Budget budget, BudgetCostShare testBudgetCostShare) {
        boolean duplicate = false;

        if (testBudgetCostShare == null) {
            return duplicate;
        }
        for (BudgetCostShare budgetCostShare : budget.getBudgetCostShares()) {
            duplicate = checkForDuplicateFields(testBudgetCostShare, budgetCostShare);
            if (duplicate) {
                break;
            }
        }
        return duplicate;
    }

    /**
     * This method checks if two BudgetCostShare objects are duplicates, meaning the fiscalYear, shareAmount, and sourceAccount are
     * equal
     * 
     * @param testBudgetCostShare
     * @param budgetCostShare
     * @return
     */
    private boolean checkForDuplicateFields(BudgetCostShare testBudgetCostShare,
                                            BudgetCostShare budgetCostShare) {
        boolean duplicate = testBudgetCostShare.equals(budgetCostShare);
        if (duplicate) {
            GlobalVariables.getMessageMap().putError("newCostShare.*", ADD_ERROR_KEY,
                    "A Cost Share with the same Fiscal Year, Source Account and Amount exists in the table");
        }
        return duplicate;
    }

    private boolean validateProjectPeriod(AddBudgetCostShareEvent budgetCostShareEvent) {
        String projectPeriodField = "newBudgetCostShare.projectPeriod";
        int numberOfProjectPeriods = budgetCostShareEvent.getBudget().getBudgetPeriods().size();
        return this.validateProjectPeriod(budgetCostShareEvent.getBudgetCostShare().getProjectPeriod(), projectPeriodField, numberOfProjectPeriods);
    }
}

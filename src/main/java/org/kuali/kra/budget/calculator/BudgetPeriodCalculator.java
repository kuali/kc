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
package org.kuali.kra.budget.calculator;

import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.util.List;

import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.service.BudgetCalculationService;

public class BudgetPeriodCalculator {
    private BudgetCalculationService budgetCalculationService;
    public BudgetPeriodCalculator(){
        budgetCalculationService = getService(BudgetCalculationService.class);
    }
    /**
     * 
     * This method calculates and sync the budget period
     * @param budgetDocument
     * @param budgetPeriod
     */
    public void calculate(BudgetDocument budgetDocument, BudgetPeriod budgetPeriod){
        budgetPeriod.setTotalDirectCost(BudgetDecimal.ZERO);
        budgetPeriod.setTotalIndirectCost(BudgetDecimal.ZERO);
        budgetPeriod.setCostSharingAmount(BudgetDecimal.ZERO);
        budgetPeriod.setTotalCost(BudgetDecimal.ZERO);
        budgetPeriod.setUnderrecoveryAmount(BudgetDecimal.ZERO);
        List<BudgetLineItem> cvLineItemDetails = budgetPeriod.getBudgetLineItems();
        for (BudgetLineItem budgetLineItem : cvLineItemDetails) {
            budgetCalculationService.calculateBudgetLineItem(budgetDocument, budgetLineItem);
            budgetPeriod.setTotalDirectCost(budgetPeriod.getTotalDirectCost().add(budgetLineItem.getDirectCost()));
            budgetPeriod.setTotalIndirectCost(budgetPeriod.getTotalIndirectCost().add(budgetLineItem.getIndirectCost()));
            budgetPeriod.setTotalCost(budgetPeriod.getTotalIndirectCost().add(budgetPeriod.getTotalDirectCost()));
        }
//        syncBudgetTotals(budgetDocument);
    }
}

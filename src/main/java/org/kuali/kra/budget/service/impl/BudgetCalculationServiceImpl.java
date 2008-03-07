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
package org.kuali.kra.budget.service.impl;

import java.util.List;

import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetLineItemBase;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetPersonnelDetails;
import org.kuali.kra.budget.calculator.BudgetPeriodCalculator;
import org.kuali.kra.budget.calculator.LineItemCalculator;
import org.kuali.kra.budget.calculator.PersonnelLineItemCalculator;
import org.kuali.kra.budget.calculator.QueryList;
import org.kuali.kra.budget.calculator.SalaryCalculator;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.service.BudgetCalculationService;


/**
 * This class implements all methods declared in BudgetCalculationService
 */
public class BudgetCalculationServiceImpl implements BudgetCalculationService {

    /**
     * @see org.kuali.kra.budget.service.BudgetCalculationService#calculateBudget(java.lang.String, java.lang.Integer)
     */
    public void calculateBudget(BudgetDocument budgetDocument){
        List<BudgetPeriod> budgetPeriods = budgetDocument.getBudgetPeriods();
        for (BudgetPeriod budgetPeriod : budgetPeriods) {
            calculateBudgetPeriod(budgetDocument, budgetPeriod);
        }
        syncCostsToBudgetDocument(budgetDocument);
    }

    /**
     * @see org.kuali.kra.budget.service.BudgetCalculationService#calculateBudgetLineItem(org.kuali.kra.budget.bo.BudgetLineItem)
     */
    public void calculateBudgetLineItem(BudgetDocument budgetDocument,BudgetLineItemBase budgetLineItem){
        if(budgetLineItem instanceof BudgetLineItem){
            new LineItemCalculator(budgetDocument,(BudgetLineItem)budgetLineItem).calculate();
        }else if(budgetLineItem instanceof BudgetPersonnelDetails){
            new PersonnelLineItemCalculator(budgetDocument,budgetLineItem).calculate();
        }
    }
    public void calculateAndSyncBudgetLineItem(BudgetDocument budgetDocument,BudgetLineItem budgetLineItem){
        new LineItemCalculator(budgetDocument,budgetLineItem).calculate();
        syncCostsToBudgetDocument(budgetDocument);
    }

    /**
     * @see org.kuali.kra.budget.service.BudgetCalculationService#calculateCalculatedAmount(org.kuali.kra.budget.bo.BudgetLineItem)
     */
    public void populateCalculatedAmount(BudgetDocument budgetDocument,BudgetLineItem budgetLineItem){
        new LineItemCalculator(budgetDocument,budgetLineItem).populateCalculatedAmountLineItems();
    }

    /**
     * @see org.kuali.kra.budget.service.BudgetCalculationService#calculateSalary(org.kuali.kra.budget.bo.BudgetPersonnelDetails)
     */
    public void calculateSalary(BudgetDocument budgetDocument,BudgetPersonnelDetails budgetPersonnelLineItem){
        new SalaryCalculator(budgetDocument,budgetPersonnelLineItem).calculate();
    }

    public void calculateBudgetPeriod(BudgetDocument budgetDocument, BudgetPeriod budgetPeriod){
        new BudgetPeriodCalculator().calculate(budgetDocument, budgetPeriod);
    }
    
    public void calculateAndSyncBudgetPeriod(BudgetDocument budgetDocument, BudgetPeriod budgetPeriod){
        calculateBudgetPeriod(budgetDocument, budgetPeriod);
        syncCostsToBudgetDocument(budgetDocument);
    }
    private void syncCostsToBudgetDocument(BudgetDocument budgetDocument){
        List<BudgetPeriod> budgetPeriods = budgetDocument.getBudgetPeriods();
        BudgetDecimal totalDirectCost = BudgetDecimal.ZERO;
        BudgetDecimal totalIndirectCost = BudgetDecimal.ZERO;
        BudgetDecimal totalCost = BudgetDecimal.ZERO;
        BudgetDecimal totalUnderrecoveryAmount = BudgetDecimal.ZERO; 
        BudgetDecimal totalCostSharingAmount = BudgetDecimal.ZERO;
        for (BudgetPeriod budgetPeriod : budgetPeriods) {
            List<BudgetLineItem> budgetLineItems = budgetPeriod.getBudgetLineItems();
            QueryList<BudgetLineItem> qlBudgetLineItems = new QueryList<BudgetLineItem>(budgetLineItems);
            BudgetDecimal directCost = qlBudgetLineItems.sumObjects("directCost");
            BudgetDecimal indirectCost = qlBudgetLineItems.sumObjects("indirectCost");
            BudgetDecimal costSharingAmount = qlBudgetLineItems.sumObjects("totalCostSharing");
            BudgetDecimal underrecoveryAmount = qlBudgetLineItems.sumObjects("underRecoveryAmount");
            budgetPeriod.setTotalDirectCost(directCost);
            budgetPeriod.setTotalIndirectCost(indirectCost);
            budgetPeriod.setTotalCost(directCost.add(indirectCost));
            budgetPeriod.setUnderrecoveryAmount(underrecoveryAmount);
            budgetPeriod.setCostSharingAmount(costSharingAmount);
            totalDirectCost = totalDirectCost.add(directCost);
            totalIndirectCost = totalIndirectCost.add(totalIndirectCost);
            totalCost = totalCost.add(totalCost);
            totalUnderrecoveryAmount = totalUnderrecoveryAmount.add(totalUnderrecoveryAmount);
            totalCostSharingAmount = totalCostSharingAmount.add(costSharingAmount);
        }
        budgetDocument.setTotalDirectCost(totalDirectCost);
        budgetDocument.setTotalIndirectCost(totalIndirectCost);
        budgetDocument.setTotalCost(totalCost);
        budgetDocument.setUnderrecoveryAmount(totalUnderrecoveryAmount);
        budgetDocument.setCostSharingAmount(totalCostSharingAmount);
    }

}

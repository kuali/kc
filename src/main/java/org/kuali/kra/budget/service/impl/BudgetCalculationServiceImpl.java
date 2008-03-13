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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetLineItemBase;
import org.kuali.kra.budget.bo.BudgetLineItemCalculatedAmount;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetPersonnelDetails;
import org.kuali.kra.budget.bo.CostElement;
import org.kuali.kra.budget.bo.RateType;
import org.kuali.kra.budget.calculator.BudgetPeriodCalculator;
import org.kuali.kra.budget.calculator.LineItemCalculator;
import org.kuali.kra.budget.calculator.PersonnelLineItemCalculator;
import org.kuali.kra.budget.calculator.QueryList;
import org.kuali.kra.budget.calculator.SalaryCalculator;
import org.kuali.kra.budget.calculator.query.And;
import org.kuali.kra.budget.calculator.query.Equals;
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

    /**
     * 
     * @see org.kuali.kra.budget.service.BudgetCalculationService#calculateBudgetTotals(org.kuali.kra.budget.document.BudgetDocument)
     */
    public void calculateBudgetTotals(BudgetDocument budgetDocument){
        // do we need to cache the totals ?
        SortedMap<CostElement, List> objectCodeTotals = new TreeMap <CostElement, List> ();
        SortedMap<RateType, List> calculatedExpenseTotals = new TreeMap <RateType, List> ();
        for (BudgetPeriod budgetPeriod: budgetDocument.getBudgetPeriods()) {
            List <CostElement> objectCodes = new ArrayList<CostElement>();
            QueryList lineItemQueryList = new QueryList();
            lineItemQueryList.addAll(budgetPeriod.getBudgetLineItems());
            budgetPeriod.setExpenseTotal(BudgetDecimal.ZERO);
            // probably need to add '0' to the period that has no such object code or ratetype ?
            for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()) {
                if (budgetLineItem.getCostElementBO() == null) {
                    budgetLineItem.refreshReferenceObject("costElementBO");
                }
                CostElement costElement = budgetLineItem.getCostElementBO();
                if (!objectCodes.contains(costElement)) {
                    objectCodes.add(costElement);
                    Equals equalsCostElement = new Equals("costElement", budgetLineItem.getCostElement());
                    BudgetDecimal objectCodeTotalInThisPeriod = lineItemQueryList.sumObjects("lineItemCost", equalsCostElement);
                    if (!objectCodeTotals.containsKey(costElement)) {
                        // set up for all periods and put into map
                        List <BudgetDecimal> periodTotals = new ArrayList<BudgetDecimal>();
                        for (int i = 0; i < budgetDocument.getBudgetPeriods().size(); i++) {
                            periodTotals.add(i,BudgetDecimal.ZERO);
                        }
                        objectCodeTotals.put(costElement, periodTotals);
                    }
                    objectCodeTotals.get(costElement).set(budgetPeriod.getBudgetPeriod() - 1, objectCodeTotalInThisPeriod);
                    budgetPeriod.setExpenseTotal(budgetPeriod.getExpenseTotal().add(objectCodeTotalInThisPeriod));
                }
                // get calculated expenses
                QueryList lineItemCalcAmtQueryList = new QueryList();
                lineItemCalcAmtQueryList.addAll(budgetLineItem.getBudgetLineItemCalculatedAmounts());
                List <RateType> rateTypes = new ArrayList<RateType>();

                for ( Object item : budgetLineItem.getBudgetLineItemCalculatedAmounts()) {
                    BudgetLineItemCalculatedAmount budgetLineItemCalculatedAmount = (BudgetLineItemCalculatedAmount) item;
                    if (budgetLineItemCalculatedAmount.getRateType() == null) {
                        budgetLineItemCalculatedAmount.refreshReferenceObject("rateType");
                    }
                    RateType rateType = budgetLineItemCalculatedAmount.getRateType();
                    if (!rateTypes.contains(rateType)) {
                        rateTypes.add(rateType);
                        Equals equalsRC = new Equals("rateClassCode", budgetLineItemCalculatedAmount.getRateClassCode());
                        Equals equalsRT = new Equals("rateTypeCode", budgetLineItemCalculatedAmount.getRateTypeCode());
                        And RCandRT = new And(equalsRC, equalsRT);
                        BudgetDecimal rateTypeTotalInThisPeriod = lineItemCalcAmtQueryList.sumObjects("calculatedCost", RCandRT);
                        if (!calculatedExpenseTotals.containsKey(rateType)) {
                            List <BudgetDecimal> rateTypePeriodTotals = new ArrayList<BudgetDecimal>();
                            for (int i = 0; i < budgetDocument.getBudgetPeriods().size(); i++) {
                                rateTypePeriodTotals.add(i,BudgetDecimal.ZERO);
                            }
                            calculatedExpenseTotals.put(budgetLineItemCalculatedAmount.getRateType(), rateTypePeriodTotals);
                        }
                        calculatedExpenseTotals.get(rateType).set(budgetPeriod.getBudgetPeriod() - 1,((BudgetDecimal)calculatedExpenseTotals.get(rateType).get(budgetPeriod.getBudgetPeriod() - 1)).add(rateTypeTotalInThisPeriod));
                        budgetPeriod.setExpenseTotal(budgetPeriod.getExpenseTotal().add(rateTypeTotalInThisPeriod));
                   }
                }
            }
        }
        budgetDocument.setObjectCodeTotals(objectCodeTotals);
        budgetDocument.setCalculatedExpenseTotals(calculatedExpenseTotals);

    }

}

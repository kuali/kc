/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.award.budget.calculator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.kuali.kra.award.budget.AwardBudgetExt;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.calculator.BudgetCalculationServiceImpl;
import org.kuali.kra.budget.core.BudgetCategoryType;
import org.kuali.kra.budget.core.CostElement;
import org.kuali.kra.budget.rates.RateType;
import org.kuali.kra.budget.versions.BudgetVersionOverview;

/**
 * Subclass for Award budget calculations needs
 */
public class AwardBudgetCalculationServiceImpl extends BudgetCalculationServiceImpl implements AwardBudgetCalculationService {

    /**
     * 
     * @see org.kuali.kra.award.budget.calculator.AwardBudgetCalculationService#calculateBudgetSummaryTotals(org.kuali.kra.award.budget.AwardBudgetExt, boolean)
     */
    public void calculateBudgetSummaryTotals(AwardBudgetExt budget, boolean includePrevious) {
        calculateBudgetSummaryTotals(budget);
        aggregatePeriodSummaryTotals(budget);
        if (includePrevious) {
            AwardBudgetExt prevBudget;
            for (BudgetVersionOverview prevBudgetOverview = budget.getPrevBudget();
                    prevBudgetOverview != null && prevBudgetOverview.getBudgetId() != null; 
                    prevBudgetOverview = prevBudget.getPrevBudget()) {
                prevBudget = getBusinessObjectService().findBySinglePrimaryKey(AwardBudgetExt.class, 
                        prevBudgetOverview.getBudgetId());
                if (prevBudget == null) {
                    break;
                }
                calculateBudgetSummaryTotals(prevBudget);
                aggregatePeriodSummaryTotals(prevBudget);
                mergePrevSummary(budget, prevBudget);
            }
        }
    }
    
    /**
     * Aggregate period totals to the budget for summary items on the budget.
     * @param budget
     */
    @SuppressWarnings("unchecked")
    protected void aggregatePeriodSummaryTotals(AwardBudgetExt budget) {
        budget.setObjectCodeBudgetTotals((SortedMap<CostElement, BudgetDecimal>) getSumOfElements(budget.getObjectCodeTotals()));
        budget.setCalculatedExpenseBudgetTotals((SortedMap<RateType, BudgetDecimal>) getSumOfElements(budget.getCalculatedExpenseTotals()));
        budget.setTotalBudgetSummaryTotals((SortedMap<String, BudgetDecimal>) getSumOfElements(budget.getBudgetSummaryTotals()));
        budget.setObjectCodePersonnelFringeBudgetTotals((SortedMap<String, BudgetDecimal>) getSumOfElements(budget.getObjectCodePersonnelFringeTotals()));
        budget.setPersonnelCalculatedExpenseBudgetTotals((SortedMap<RateType, BudgetDecimal>) getSumOfElements(budget.getPersonnelCalculatedExpenseTotals()));
        budget.setNonPersonnelCalculatedExpenseBudgetTotals((SortedMap<RateType, BudgetDecimal>) getSumOfElements(budget.getNonPersonnelCalculatedExpenseTotals()));
    }
    
    /**
     * Merge and sum up the summary items from the previous budget to the current budget.
     * @param budget
     * @param prevBudget
     */
    @SuppressWarnings("unchecked")
    protected void mergePrevSummary(AwardBudgetExt budget, AwardBudgetExt prevBudget) {
        for (Map.Entry<BudgetCategoryType, List<CostElement>> entry : prevBudget.getObjectCodeListByBudgetCategoryType().entrySet()) {
            List<CostElement> curElements = budget.getObjectCodeListByBudgetCategoryType().get(entry.getKey());
            if (curElements == null) {
                budget.getObjectCodeListByBudgetCategoryType().put(entry.getKey(), new ArrayList<CostElement>(entry.getValue()));
            } else {
                HashSet uniqueSet = new HashSet<CostElement>(curElements);
                uniqueSet.addAll(entry.getValue());
                budget.getObjectCodeListByBudgetCategoryType().put(entry.getKey(), new ArrayList<CostElement>(uniqueSet));
            }            
        }
        budget.setObjectCodeBudgetTotals((SortedMap<CostElement, BudgetDecimal>) mergeMaps(budget.getObjectCodeBudgetTotals(), 
                prevBudget.getObjectCodeBudgetTotals()));
        budget.setCalculatedExpenseBudgetTotals((SortedMap<RateType, BudgetDecimal>) mergeMaps(budget.getCalculatedExpenseBudgetTotals(), 
                prevBudget.getCalculatedExpenseBudgetTotals()));
        budget.setTotalBudgetSummaryTotals((SortedMap<String, BudgetDecimal>) mergeMaps(budget.getTotalBudgetSummaryTotals(), 
                prevBudget.getTotalBudgetSummaryTotals()));
        budget.setObjectCodePersonnelFringeBudgetTotals((SortedMap<String, BudgetDecimal>) mergeMaps(budget.getObjectCodePersonnelFringeBudgetTotals(),
                prevBudget.getObjectCodePersonnelFringeBudgetTotals()));
        budget.setPersonnelCalculatedExpenseBudgetTotals((SortedMap<RateType, BudgetDecimal>) mergeMaps(budget.getPersonnelCalculatedExpenseBudgetTotals(),
                prevBudget.getPersonnelCalculatedExpenseBudgetTotals()));
        budget.setNonPersonnelCalculatedExpenseBudgetTotals((SortedMap<RateType, BudgetDecimal>) mergeMaps(budget.getNonPersonnelCalculatedExpenseBudgetTotals(),
                prevBudget.getNonPersonnelCalculatedExpenseBudgetTotals()));
        budget.setTotalDirectCost(budget.getTotalDirectCost().add(prevBudget.getTotalDirectCost()));
        budget.setTotalIndirectCost(budget.getTotalIndirectCost().add(prevBudget.getTotalIndirectCost()));
        budget.setTotalCost(budget.getTotalCost().add(prevBudget.getTotalCost())); 
    }
    
    /**
     * Merges or adds the 2 maps into the return value.
     * @param totals
     * @param previous
     * @return
     */
    protected SortedMap<? extends Object, BudgetDecimal> mergeMaps(Map<? extends Object, BudgetDecimal> totals, Map<? extends Object, BudgetDecimal> previous) {
        SortedMap<Object, BudgetDecimal> result = new TreeMap<Object, BudgetDecimal>();
        for (Map.Entry<? extends Object, BudgetDecimal> entry : totals.entrySet()) {
            result.put(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<? extends Object, BudgetDecimal> entry : previous.entrySet()) {
            Object key = entry.getKey();
            BudgetDecimal total = totals.get(key);
            if (total != null) {
                if (total == null) {
                    result.put(key, entry.getValue());
                } else {
                    result.put(key, total.add(entry.getValue()));
                }
            }
        }
        return result;
    }
    
    /**
     * Sums up the list of budget decimals included in the map and returns those totals.
     * This is used to sum up the summary totals included in the budget which is a  list
     * of totals per budget period
     * @param map
     * @return
     */
    protected SortedMap<? extends Object, BudgetDecimal> getSumOfElements(Map<? extends Object, List<BudgetDecimal>> map) {
        SortedMap<Object, BudgetDecimal> result = new TreeMap<Object, BudgetDecimal>();
        for (Map.Entry<? extends Object, List<BudgetDecimal>> entry : map.entrySet()) {
            BudgetDecimal total = BudgetDecimal.ZERO;
            for (BudgetDecimal amt : entry.getValue()) {
                total = total.add(amt);
            }
            //if the key is a string and has a comma, remove everything after the comma
            //this is for items aggregated by object code and person id. For this summary
            //we only care about the object code
            Object key = entry.getKey();
            if (key instanceof String) {
                String strKey = (String) entry.getKey();
                key = strKey.replaceAll(",.*", "");
            }
            result.put(key, total);
        }
        return result;
    }
}

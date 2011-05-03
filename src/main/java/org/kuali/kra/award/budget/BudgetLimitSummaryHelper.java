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
package org.kuali.kra.award.budget;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.BudgetCategoryType;
import org.kuali.kra.budget.core.CostElement;
import org.kuali.kra.budget.rates.RateType;

/**
 * Helper class that simplifies building the award budget limits summary panel.
 */
public class BudgetLimitSummaryHelper implements Serializable {
    
    private static final long serialVersionUID = -3504648775976043270L;
    private Budget currentBudget;
    private Budget previousBudget;
    
    /**
     * Returns a map of costelements by category type from both current and previous budgets.
     * @return
     */
    public Map<BudgetCategoryType, Set<CostElement>> getCombinedObjectCodeListByCategory() {
        TreeMap<BudgetCategoryType, Set<CostElement>> result = new TreeMap<BudgetCategoryType, Set<CostElement>>();
        for (Map.Entry<BudgetCategoryType, List<CostElement>> entry : currentBudget.getObjectCodeListByBudgetCategoryType().entrySet()) {
            result.put(entry.getKey(), new TreeSet<CostElement>(entry.getValue()));
        }
        for (Map.Entry<BudgetCategoryType, List<CostElement>> entry : previousBudget.getObjectCodeListByBudgetCategoryType().entrySet()) {
            Set<CostElement> curElements = result.get(entry.getKey());
            if (curElements == null) {
                result.put(entry.getKey(), new TreeSet<CostElement>(entry.getValue()));
            } else {
                curElements.addAll(entry.getValue());
            }
        }
        return result;
    }
    
    /**
     * Returns a unique set of personnel object codes from both current previous budgets
     * @return
     */
    public Set<CostElement> getCombinedPersonnelObjectCodes() {
        for (Map.Entry<BudgetCategoryType, Set<CostElement>> entry : getCombinedObjectCodeListByCategory().entrySet()) {
            if (StringUtils.equals(entry.getKey().getBudgetCategoryTypeCode(), "P")) {
                return entry.getValue();
            }
        }
        return new TreeSet<CostElement>();
    }
    
    
    
    /**
     * Returns a map of costelement totals for the current budget.
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map<CostElement, BudgetDecimal> getCurrentObjectCodeTotals() {
        return (Map<CostElement, BudgetDecimal>) getSumOfElements(getCurrentBudget().getObjectCodeTotals());
    }
    
    /**
     * Returns a map of costelement totals for the past budget.
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map<CostElement, BudgetDecimal> getPreviousObjectCodeTotals() {
        return (Map<CostElement, BudgetDecimal>) getSumOfElements(getPreviousBudget().getObjectCodeTotals());
    }
    
    /**
     * Returns a map of summary totals for the current budget.
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map<String, BudgetDecimal> getCurrentSummaryTotals() {
        return (Map<String, BudgetDecimal>) getSumOfElements(getCurrentBudget().getBudgetSummaryTotals());
    }    

    /**
     * Returns a map of summary totals for the previous budget.
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map<String, BudgetDecimal> getPreviousSummaryTotals() {
        return (Map<String, BudgetDecimal>) getSumOfElements(getPreviousBudget().getBudgetSummaryTotals());
    }  
    
    /**
     * Returns a map of personnel fringe totals for the current budget.
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map<String, BudgetDecimal> getCurrentObjectCodePersonnelFringeTotals() {
        return (Map<String, BudgetDecimal>) getSumOfElements(getCurrentBudget().getObjectCodePersonnelFringeTotals());
    }
    
    /**
     * Returns a map of personnel fringe totals for the pervious budget.
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map<String, BudgetDecimal> getPreviousObjectCodePersonnelFringeTotals() {
        return (Map<String, BudgetDecimal>) getSumOfElements(getPreviousBudget().getObjectCodePersonnelFringeTotals());
    }
    
    /**
     * Returns a unique set of rate types for personnel found in both current and previous budgets.
     * @return
     */
    public Set<RateType> getCombinedPersonnelCalculatedExpenseRates() {
        TreeSet<RateType> result = new TreeSet<RateType>();
        for (RateType type : getCurrentBudget().getPersonnelCalculatedExpenseTotals().keySet()) {
            if (type.getRateClass().getRateClassType() != null && !StringUtils.equals(type.getRateClass().getRateClassType(), "O")) {
                result.add(type);
            }
        }
        for (RateType type : getPreviousBudget().getPersonnelCalculatedExpenseTotals().keySet()) {
            if (type.getRateClass().getRateClassType() != null && !StringUtils.equals(type.getRateClass().getRateClassType(), "O")) {
                result.add(type);
            }
        }
        return result;
    }
    
    /**
     * Return a map of personnel calculated expense totals for the current budget.
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map<RateType, BudgetDecimal> getCurrentPersonnelCalculatedExpenseTotals() {
        return (Map<RateType, BudgetDecimal>) getSumOfElements(getCurrentBudget().getPersonnelCalculatedExpenseTotals());
    }     
    
    /**
     * Returns a map of personnel calculated expense totals for the previous budget.
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map<RateType, BudgetDecimal> getPreviousPersonnelCalculatedExpenseTotals() {
        return (Map<RateType, BudgetDecimal>) getSumOfElements(getPreviousBudget().getPersonnelCalculatedExpenseTotals());
    }
    
    /**
     * Returns a unique set of the combinded non-personnel calculated expense ratetypes.
     * @return
     */
    public Set<RateType> getCombinedNonPersonnelCalculatedExpenseRates() {
        TreeSet<RateType> result = new TreeSet<RateType>();
        for (RateType type : getCurrentBudget().getNonPersonnelCalculatedExpenseTotals().keySet()) {
            if (type.getRateClass().getRateClassType() != null && !StringUtils.equals(type.getRateClass().getRateClassType(), "O")) {
                result.add(type);
            }
        }
        for (RateType type : getPreviousBudget().getNonPersonnelCalculatedExpenseTotals().keySet()) {
            if (type.getRateClass().getRateClassType() != null && !StringUtils.equals(type.getRateClass().getRateClassType(), "O")) {
                result.add(type);
            }
        }
        return result;
    }    
    
    /**
     * Return the non-personnel calculated expense totals for the current budget.
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map<RateType, BudgetDecimal> getCurrentNonPersonnelCalculatedExpenseTotals() {
        return (Map<RateType, BudgetDecimal>) getSumOfElements(getCurrentBudget().getNonPersonnelCalculatedExpenseTotals());        
    }
    
    /**
     * Return the non-personnel calculated expense totals for the previous budget.
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map<RateType, BudgetDecimal> getPreviousNonPersonnelCalculatedExpenseTotals() {
        return (Map<RateType, BudgetDecimal>) getSumOfElements(getPreviousBudget().getNonPersonnelCalculatedExpenseTotals());        
    }
    
    /**
     * Sums the personnel totals for the current budget.
     * @return
     */
    public BudgetDecimal getCurrentPersonnelTotal() {
        Map<String, BudgetDecimal> summaryTotals = getCurrentSummaryTotals();
        BudgetDecimal total = BudgetDecimal.ZERO;
        total = total.add(summaryTotals.get("personnelSalaryTotals"));
        total = total.add(summaryTotals.get("personnelFringeTotals"));
        total = total.add(summaryTotals.get("personnelCalculatedExpenseSummaryTotals"));
        return total;
    }
    
    /**
     * Sums the personnel totals for the previous budget.
     * @return
     */
    public BudgetDecimal getPreviousPersonnelTotal() {
        Map<String, BudgetDecimal> summaryTotals = getPreviousSummaryTotals();
        BudgetDecimal total = BudgetDecimal.ZERO;
        total = total.add(summaryTotals.get("personnelSalaryTotals"));
        total = total.add(summaryTotals.get("personnelFringeTotals"));
        total = total.add(summaryTotals.get("personnelCalculatedExpenseSummaryTotals"));
        return total;
    }    
    
    /**
     * Sums up the list of budget decimals included in the map and returns those totals.
     * This is used to sum up the summary totals included in the budget which is a  list
     * of totals per budget period
     * @param map
     * @return
     */
    protected Map<? extends Object, BudgetDecimal> getSumOfElements(Map<? extends Object, List<BudgetDecimal>> map) {
        Map<Object, BudgetDecimal> result = new TreeMap<Object, BudgetDecimal>();
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
    
    public Budget getCurrentBudget() {
        return currentBudget;
    }
    public void setCurrentBudget(Budget currentBudget) {
        this.currentBudget = currentBudget;
    }
    public Budget getPreviousBudget() {
        return previousBudget;
    }
    public void setPreviousBudget(Budget previousBudget) {
        this.previousBudget = previousBudget;
    }
    
    

}

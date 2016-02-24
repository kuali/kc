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
package org.kuali.kra.award.budget;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.core.category.BudgetCategoryType;
import org.kuali.coeus.common.budget.framework.core.CostElement;
import org.kuali.coeus.common.budget.framework.rate.RateType;

import java.io.Serializable;
import java.util.*;

/**
 * Helper class that simplifies building the award budget limits summary panel.
 */
public class BudgetLimitSummaryHelper implements Serializable {
    
    private static final long serialVersionUID = -3504648775976043270L;
    private AwardBudgetExt currentBudget;
    private AwardBudgetExt previousBudget;
    
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
            if (StringUtils.equals(entry.getKey().getCode(), "P")) {
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
    public Map<CostElement, ScaleTwoDecimal> getCurrentObjectCodeTotals() {
        return getCurrentBudget().getObjectCodeBudgetTotals();
    }
    
    /**
     * Returns a map of costelement totals for the past budget.
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map<CostElement, ScaleTwoDecimal> getPreviousObjectCodeTotals() {
        return getPreviousBudget().getObjectCodeBudgetTotals();
    }
    
    /**
     * Returns a map of summary totals for the current budget.
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map<String, ScaleTwoDecimal> getCurrentSummaryTotals() {
        return getCurrentBudget().getTotalBudgetSummaryTotals();
    }    

    /**
     * Returns a map of summary totals for the previous budget.
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map<String, ScaleTwoDecimal> getPreviousSummaryTotals() {
        return getPreviousBudget().getTotalBudgetSummaryTotals();
    }  
    
    /**
     * Returns a map of personnel fringe totals for the current budget.
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map<String, ScaleTwoDecimal> getCurrentObjectCodePersonnelFringeTotals() {
        return getCurrentBudget().getObjectCodePersonnelFringeBudgetTotals();
    }
    
    /**
     * Returns a map of personnel fringe totals for the pervious budget.
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map<String, ScaleTwoDecimal> getPreviousObjectCodePersonnelFringeTotals() {
        return getPreviousBudget().getObjectCodePersonnelFringeBudgetTotals();
    }
    
    /**
     * Returns a unique set of rate types for personnel found in both current and previous budgets.
     * @return
     */
    public Set<RateType> getCombinedPersonnelCalculatedExpenseRates() {
        TreeSet<RateType> result = new TreeSet<RateType>();
        for (RateType type : getCurrentBudget().getPersonnelCalculatedExpenseTotals().keySet()) {
            if (type.getRateClass().getRateClassTypeCode() != null && !StringUtils.equals(type.getRateClass().getRateClassTypeCode(), "O")) {
                result.add(type);
            }
        }
        for (RateType type : getPreviousBudget().getPersonnelCalculatedExpenseTotals().keySet()) {
            if (type.getRateClass().getRateClassTypeCode() != null && !StringUtils.equals(type.getRateClass().getRateClassTypeCode(), "O")) {
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
    public Map<RateType, ScaleTwoDecimal> getCurrentPersonnelCalculatedExpenseTotals() {
        return getCurrentBudget().getPersonnelCalculatedExpenseBudgetTotals();
    }     
    
    /**
     * Returns a map of personnel calculated expense totals for the previous budget.
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map<RateType, ScaleTwoDecimal> getPreviousPersonnelCalculatedExpenseTotals() {
        return getPreviousBudget().getPersonnelCalculatedExpenseBudgetTotals();
    }
    
    /**
     * Returns a unique set of the combinded non-personnel calculated expense ratetypes.
     * @return
     */
    public Set<RateType> getCombinedNonPersonnelCalculatedExpenseRates() {
        TreeSet<RateType> result = new TreeSet<RateType>();
        for (RateType type : getCurrentBudget().getNonPersonnelCalculatedExpenseTotals().keySet()) {
            if (type.getRateClass().getRateClassTypeCode() != null && !StringUtils.equals(type.getRateClass().getRateClassTypeCode(), "O")) {
                result.add(type);
            }
        }
        for (RateType type : getPreviousBudget().getNonPersonnelCalculatedExpenseTotals().keySet()) {
            if (type.getRateClass().getRateClassTypeCode() != null && !StringUtils.equals(type.getRateClass().getRateClassTypeCode(), "O")) {
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
    public Map<RateType, ScaleTwoDecimal> getCurrentNonPersonnelCalculatedExpenseTotals() {
        return getCurrentBudget().getNonPersonnelCalculatedExpenseBudgetTotals();        
    }
    
    /**
     * Return the non-personnel calculated expense totals for the previous budget.
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map<RateType, ScaleTwoDecimal> getPreviousNonPersonnelCalculatedExpenseTotals() {
        return getPreviousBudget().getNonPersonnelCalculatedExpenseBudgetTotals();        
    }
    
    /**
     * Sums the personnel totals for the current budget.
     * @return
     */
    public ScaleTwoDecimal getCurrentPersonnelTotal() {
        Map<String, ScaleTwoDecimal> summaryTotals = getCurrentSummaryTotals();
        ScaleTwoDecimal total = ScaleTwoDecimal.ZERO;
        total = total.add(summaryTotals.get("personnelSalaryTotals"));
        total = total.add(summaryTotals.get("personnelFringeTotals"));
        total = total.add(summaryTotals.get("personnelCalculatedExpenseSummaryTotals"));
        return total;
    }
    
    /**
     * Sums the personnel totals for the previous budget.
     * @return
     */
    public ScaleTwoDecimal getPreviousPersonnelTotal() {
        Map<String, ScaleTwoDecimal> summaryTotals = getPreviousSummaryTotals();
        ScaleTwoDecimal total = ScaleTwoDecimal.ZERO;
        total = total.add(summaryTotals.get("personnelSalaryTotals"));
        total = total.add(summaryTotals.get("personnelFringeTotals"));
        total = total.add(summaryTotals.get("personnelCalculatedExpenseSummaryTotals"));
        return total;
    }    
    
    public AwardBudgetExt getCurrentBudget() {
        return currentBudget;
    }
    public void setCurrentBudget(AwardBudgetExt currentBudget) {
        this.currentBudget = currentBudget;
    }
    public AwardBudgetExt getPreviousBudget() {
        return previousBudget;
    }
    public void setPreviousBudget(AwardBudgetExt previousBudget) {
        this.previousBudget = previousBudget;
    }
    
    

}

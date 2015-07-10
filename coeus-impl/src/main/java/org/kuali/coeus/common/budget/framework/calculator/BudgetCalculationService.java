/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.common.budget.framework.calculator;

import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItemCalculatedAmount;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelCalculatedAmount;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelDetails;
/**
 * 
 * This class is for serving all calculations for a proposal budget.
 */
public interface BudgetCalculationService {
    /**
     * 
     * This method is for calculating the entire budget version and populate the appropriate values 
     * to session (Budget).
     */
    public void calculateBudget(Budget budget) ;
    /**
     * 
     * This method is for calculating the entire budget version and populate the appropriate values 
     * to session (Budget).
     */
    public void calculateBudgetPeriod(Budget budget,BudgetPeriod budgetPeriod) ;
    /**
     * 
     * This method for calculating non-personnel budget line item. This calculates all calculated amounts
     * and sum it up to cost of the the line item. It populates the appropriate values to session as well.
     * (BudgetLineItemCalculatedAmount, BudgetLineItem)
     */
    public void calculateBudgetLineItem(Budget budget,BudgetPersonnelDetails budgetPersonnelDetails);
    /**
     * 
     * This method for calculating non-personnel budget line item. This calculates all calculated amounts
     * and sum it up to cost of the the line item. It populates the appropriate values to session as well.
     * (BudgetLineItemCalculatedAmount, BudgetLineItem)
     */
    public void calculateBudgetLineItem(Budget budget,BudgetLineItem budgetLineItem);
    /**
     * 
     * This method is for calculating calculated amounts for each cost element. It looks at all
     * applicable rates for a cost element and calculate all direct and indirect costs.
     */
    public void populateCalculatedAmount(Budget budget,BudgetLineItem budgetLineItem);
    /**
     * 
     * This method is for calculating calculated amounts for each cost element. It looks at all
     * applicable rates for a cost element and calculate all direct and indirect costs.
     */
    public void rePopulateCalculatedAmount(Budget budget,BudgetLineItem budgetLineItem);

    /**
     * 
     * This method is to calculate the budget totals for budget total page.
     */
    public void calculateBudgetSummaryTotals(Budget budget);

    /**
     * This method is to apply budgetlineitem details to all later periods.
     */
    public void applyToLaterPeriods(Budget budget, BudgetPeriod budgetPeriod, BudgetLineItem budgetLineItem);
    /**
     * This method is to adjust the line item cost to total cost limit of a period.
     */
    public boolean syncToPeriodCostLimit(Budget budget, BudgetPeriod budgetPeriod, BudgetLineItem budgetLineItem);
    /**
     * 
     * This method is for calculating calculated amounts for each cost element. It looks at all
     * applicable rates for a cost element and calculate all direct and indirect costs.
     */
    public void populateCalculatedAmount(Budget budget, BudgetPersonnelDetails newBudgetPersonnelDetails);
    /**
     * 
     * This method is for calculating calculated amounts for each cost element. It looks at all
     * applicable rates for a cost element and calculate all direct and indirect costs.
     */
    public void rePopulateCalculatedAmount(Budget budget, BudgetPersonnelDetails newBudgetPersonnelDetails);

    /**
     * Synchronize rates between {@link BudgetLineItemCalculatedAmount} instances and {@link BudgetPersonnelCalculatedAmount} instances
     * in a {@link Budget}.
     * 
     *
     * @param budgetLineItem {@link BudgetLineItem} instance to examine amounts for synchronization
     */
    public void updatePersonnelBudgetRate(BudgetLineItem budgetLineItem);    
    /**
     * This method is to adjust the line item cost to total direct cost limit of a period.
     */
    public boolean syncToPeriodDirectCostLimit(Budget budget, BudgetPeriod budgetPeriod, BudgetLineItem budgetLineItem);
    
    public void populateBudgetSummaryTotals(Budget budget);
    
    public void updateBudgetTotalCost(Budget budget);
    
    public String getPersonnelBudgetCategoryTypeCode();
    
    public void resetBudgetLineItemCalculatedAmounts(Budget budget);
    
    public void calculateAndUpdateFormulatedCost(BudgetLineItem budgetLineItem);
    
}

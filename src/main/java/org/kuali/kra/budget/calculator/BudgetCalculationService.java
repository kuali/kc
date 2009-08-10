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
package org.kuali.kra.budget.calculator;

import java.util.List;

import org.kuali.kra.budget.BudgetException;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItemBase;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItemCalculatedAmount;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.personnel.BudgetPersonnelCalculatedAmount;
import org.kuali.kra.budget.personnel.BudgetPersonnelDetails;
/**
 * 
 * This class is for serving all calculations for a proposal budget.
 */
public interface BudgetCalculationService {
    /**
     * 
     * This method is for calculating the entire budget version and populate the appropriate values 
     * to session (BudgetVersionOverView).
     * @param proposalNumber
     * @param budgetVersionNumber
     */
    public void calculateBudget(Budget budget) ;
    /**
     * 
     * This method is for calculating the entire budget version and populate the appropriate values 
     * to session (BudgetVersionOverView).
     * @param proposalNumber
     * @param budgetVersionNumber
     */
    public void calculateBudgetPeriod(Budget budget,BudgetPeriod budgetPeriod) ;
    /**
     * 
     * This method for calculating non-personnel budget line item. This calculates all calculated amounts
     * and sum it up to cost of the the line item. It populates the appropriate values to session as well.
     * (BudgetLineItemCalculatedAmount, BudgetLineItem) 
     * @param budgetLineItem
     * @throws BudgetException
     */
    public void calculateBudgetLineItem(Budget budget,BudgetPersonnelDetails budgetPersonnelDetails);
    /**
     * 
     * This method for calculating non-personnel budget line item. This calculates all calculated amounts
     * and sum it up to cost of the the line item. It populates the appropriate values to session as well.
     * (BudgetLineItemCalculatedAmount, BudgetLineItem) 
     * @param budgetLineItem
     * @throws BudgetException
     */
    public void calculateBudgetLineItem(Budget budget,BudgetLineItem budgetLineItem);
    /**
     * 
     * This method is for calculating calculated amounts for each cost element. It looks at all
     * applicable rates for a cost element and calculate all direct and indirect costs.
     * @param budgetLineItem
     * @throws BudgetException
     */
    public void populateCalculatedAmount(Budget budget,BudgetLineItem budgetLineItem);
    /**
     * 
     * This method is for calculating calculated amounts for each cost element. It looks at all
     * applicable rates for a cost element and calculate all direct and indirect costs.
     * @param budgetLineItem
     * @throws BudgetException
     */
    public void rePopulateCalculatedAmount(Budget budget,BudgetLineItem budgetLineItem);
    /**
     * 
     * This method...
     * @param budgetPersnnelLineItem
     * @throws BudgetException
     */
    public void calculateSalary(Budget budget,BudgetPersonnelDetails budgetPersnnelLineItem) ;
    
    /**
     * 
     * This method is to calculate the budget totals for budget total page
     * @param budget
     */
    public void calculateBudgetTotals(Budget budget);

    /**
     * 
     * This method is to calculate the budget totals for budget total page
     * @param budget
     */
    public void calculateBudgetSummaryTotals(Budget budget);

    /**
     * This method is to apply budgetlineitem details to all later periods
     * @param budget
     * @param budgetPeriod
     * @param budgetLineItem
     */
    public void applyToLaterPeriods(Budget budget, BudgetPeriod budgetPeriod, BudgetLineItem budgetLineItem);
    /**
     * This method is to adjust the line item cost to total cost limit of a period
     * @param budget
     * @param budgetPeriod
     * @param budgetLineItem
     */
    public void syncToPeriodCostLimit(Budget budget, BudgetPeriod budgetPeriod, BudgetLineItem budgetLineItem);
    /**
     * 
     * This method is for calculating calculated amounts for each cost element. It looks at all
     * applicable rates for a cost element and calculate all direct and indirect costs.
     * @param budgetLineItem
     * @throws BudgetException
     */
    public void populateCalculatedAmount(Budget budget, BudgetPersonnelDetails newBudgetPersonnelDetails);
    /**
     * 
     * This method is for calculating calculated amounts for each cost element. It looks at all
     * applicable rates for a cost element and calculate all direct and indirect costs.
     * @param budgetLineItem
     * @throws BudgetException
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
}

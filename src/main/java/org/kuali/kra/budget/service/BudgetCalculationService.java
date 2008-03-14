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
package org.kuali.kra.budget.service;

import java.util.List;

import org.kuali.kra.budget.BudgetException;
import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetLineItemBase;
import org.kuali.kra.budget.bo.BudgetLineItemCalculatedAmount;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetPersonnelDetails;
import org.kuali.kra.budget.document.BudgetDocument;
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
    public void calculateBudget(BudgetDocument budgetDocument) ;
    /**
     * 
     * This method is for calculating the entire budget version and populate the appropriate values 
     * to session (BudgetVersionOverView).
     * @param proposalNumber
     * @param budgetVersionNumber
     */
    public void calculateBudgetPeriod(BudgetDocument budgetDocument,BudgetPeriod budgetPeriod) ;
    /**
     * 
     * This method for calculating non-personnel budget line item. This calculates all calculated amounts
     * and sum it up to cost of the the line item. It populates the appropriate values to session as well.
     * (BudgetLineItemCalculatedAmount, BudgetLineItem) 
     * @param budgetLineItem
     * @throws BudgetException
     */
    public void calculateBudgetLineItem(BudgetDocument budgetDocument,BudgetLineItemBase budgetLineItem);
    /**
     * 
     * This method is for calculating calculated amounts for each cost element. It looks at all
     * applicable rates for a cost element and calculate all direct and indirect costs.
     * @param budgetLineItem
     * @throws BudgetException
     */
    public void populateCalculatedAmount(BudgetDocument budgetDocument,BudgetLineItem budgetLineItem);
    /**
     * 
     * This method...
     * @param budgetPersnnelLineItem
     * @throws BudgetException
     */
    public void calculateSalary(BudgetDocument budgetDocument,BudgetPersonnelDetails budgetPersnnelLineItem) ;
    
    /**
     * 
     * This method is to calculate the budget totals for budget total page
     * @param budgetDocument
     */
    public void calculateBudgetTotals(BudgetDocument budgetDocument);

    /**
     * This method is to apply budgetlineitem details to all later periods
     * @param budgetDocument
     * @param budgetPeriod
     * @param budgetLineItem
     */
    public void applyToLaterPeriods(BudgetDocument budgetDocument, BudgetPeriod budgetPeriod, BudgetLineItem budgetLineItem);
    /**
     * This method is to adjust the line item cost to total cost limit of a period
     * @param budgetDocument
     * @param budgetPeriod
     * @param budgetLineItem
     */
    public void syncToPeriodCostLimit(BudgetDocument budgetDocument, BudgetPeriod budgetPeriod, BudgetLineItem budgetLineItem);
    
}

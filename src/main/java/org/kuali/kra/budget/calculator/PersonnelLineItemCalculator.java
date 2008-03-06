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

import java.util.List;

import org.kuali.kra.budget.bo.BudgetLineItemBase;
import org.kuali.kra.budget.bo.BudgetPersonnelCalculatedAmount;
import org.kuali.kra.budget.bo.BudgetPersonnelDetails;
import org.kuali.kra.budget.document.BudgetDocument;

/**
 * This class is for calculating personnel line items.
 */
public class PersonnelLineItemCalculator extends CalculatorBase {

    private BudgetPersonnelDetails budgetPersonnelLineItem;
    private BudgetDocument budgetDocument;
    private SalaryCalculator salaryCalculator; 

    public PersonnelLineItemCalculator(BudgetDocument bd, BudgetLineItemBase bli) {
        super(bd, bli);
        this.budgetPersonnelLineItem = (BudgetPersonnelDetails)bli;
        this.budgetDocument = bd;
        salaryCalculator = new SalaryCalculator(budgetDocument,budgetPersonnelLineItem);
    }

    /**
     * @see org.kuali.kra.budget.calculator.CalculatorBase#populateApplicableCosts(Boundary)
     */
    @Override
    public void populateApplicableCosts(Boundary boundary) {
        salaryCalculator.setInflationRates(getInflationRates());
        salaryCalculator.calculate(boundary);
    }
    @Override
    protected void populateCalculatedAmountLineItems() {
        if (budgetPersonnelLineItem.getBudgetLineItemCalculatedAmounts().size() <= 0) {
            budgetPersonnelLineItem.refreshReferenceObject("budgetLineItemCalculatedAmounts");
        }
        if (budgetPersonnelLineItem.getBudgetLineItemCalculatedAmounts().size() <= 0) {
            setCalculatedAmounts(budgetDocument,budgetPersonnelLineItem);
            List<BudgetPersonnelCalculatedAmount> budgetPerosnnelCalcAmts = budgetPersonnelLineItem.getBudgetLineItemCalculatedAmounts();
            for (BudgetPersonnelCalculatedAmount budgetPersonnelCalculatedAmount : budgetPerosnnelCalcAmts) {
                budgetPersonnelCalculatedAmount.setPersonNumber(budgetPersonnelLineItem.getPersonNumber());
            }
        }
    }
    @Override
    protected void updateBudgetLineItemCalculatedAmounts() {
        salaryCalculator.calculate();
        budgetPersonnelLineItem.setLineItemCost(budgetPersonnelLineItem.getSalaryRequested());
        super.updateBudgetLineItemCalculatedAmounts();
    }
}

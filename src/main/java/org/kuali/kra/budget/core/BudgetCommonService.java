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
package org.kuali.kra.budget.core;

import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.document.BudgetParentDocument;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.rice.kew.api.exception.WorkflowException;

/**
 * This interface has services which required different implementations in AwardBudget and ProposalBudget modules 
 */
public interface BudgetCommonService<T extends BudgetParent> {
    /**
     * Returns a new finalized BudgetDocument based on the given ProposalDevelopmentDocument and documentDescription.
     * 
     * @param pdDoc
     * @param documentDescription
     * @return BudgetDocument
     * @throws WorkflowException
     */
    public BudgetDocument<T> getNewBudgetVersion(BudgetParentDocument<T> parentDocument, String documentDescription) throws WorkflowException;
    /**
     * Returns a new finalized BudgetDocument with the data from the given BudgetDocument copied over.
     * @param budgetDocument
     * @return BudgetDocument
     * @throws WorkflowException
     */
    public BudgetDocument<T> copyBudgetVersion(BudgetDocument<T> budgetDocument) throws WorkflowException;
    
    /**
     * This method is to check whether Budget Summary calculated amounts for a BudgetPeriod 
     * have been modified on AwardBudgetSummary screen
     * @param budgetPeriod
     * @return true if there is any change
     */
    public boolean isRateOverridden(BudgetPeriod budgetPeriod);
    
    /**
     * 
     * This method is recalculate the budget. For Proposal Budget, recalcuate is same as calculate. 
     * For Award Budget, it removes Award Budget Sumamry Calc Amounts before the calculation 
     * @param budget
     */
    public void recalculateBudget(Budget budget);
    /**
     * 
     * This method will take care of summing up all line item costs or summary line items to Period totals
     * @param budget
     */
    public void calculateBudgetOnSave(Budget budget);
    /**
     * 
     * This method is to check whether Budget Summary calculated amounts have been modified on AwardBudgetSummary screen
     * @param budget
     * @return true if there is any change
     */
    public boolean isRateOverridden(Budget budget);
    
    /**
     * 
     * This method will clear the BudgetSumamryPeriodCalcAmounts
     * @param budgetPeriod
     */
    public void removeBudgetSummaryPeriodCalcAmounts(BudgetPeriod budgetPeriod);
    
    public void populateSummaryCalcAmounts(Budget budget,BudgetPeriod budgetPeriod);
    
    /**
     * Make sure its ok to add a new budget version. This is primarily used for the award budgets
     * as the other budgets status affects whether a new budget may be created. This method
     * should add error messages as to why the budget may not be created.
     * @param parentDocument
     * @return true if ok to add new budget, false otherwise
     */
    boolean validateAddingNewBudget(BudgetParentDocument<T> parentDocument);
    public void recalculateBudgetPeriod(Budget budget, BudgetPeriod budgetPeriod);

}

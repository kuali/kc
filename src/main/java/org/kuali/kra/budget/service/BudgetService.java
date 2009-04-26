/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.budget.service;

import org.kuali.kra.budget.bo.BudgetLineItemBase;
import org.kuali.kra.budget.bo.BudgetVersionOverview;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.kew.exception.WorkflowException;

/**
 * Budget Service interface
 */
public interface BudgetService {
    
    /**
     * Returns a new finalized BudgetDocument based on the given ProposalDevelopmentDocument and documentDescription.
     * 
     * @param pdDoc
     * @param documentDescription
     * @return BudgetDocument
     * @throws WorkflowException
     */
    public BudgetDocument getNewBudgetVersion(ProposalDevelopmentDocument pdDoc, String documentDescription) throws WorkflowException;
    
    /**
     * Returns a new finalized BudgetDocument with the data from the given BudgetDocument copied over.
     * @param budgetDocument
     * @return BudgetDocument
     * @throws WorkflowException
     */
    public BudgetDocument copyBudgetVersion(BudgetDocument budgetDocument) throws WorkflowException;
    
    public void updateDocumentDescription(BudgetVersionOverview budgetVersion);
    
    /**
     * 
     * This method check if activity type changed, then display confirmation message on 'open' budget version.
     * @param budgetDocument
     * @return
     */
    public boolean checkActivityTypeChange(ProposalDevelopmentDocument pdDoc, String budgetVersionNumbe);
    
    /**
     * check if this line item CE has inflation rate
     * This method...
     * @param budgetDocument
     * @param budgetLineItem
     * @return
     */
    public boolean ValidInflationCeRate(BudgetLineItemBase budgetLineItem);
    
    public String getActivityTypeForBudget(BudgetDocument budgetDocument);
    

}

/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.budget.core;

import java.util.Collection;
import java.util.List;

import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.document.BudgetParentDocument;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItemBase;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.personnel.ValidCeJobCode;
import org.kuali.kra.budget.rates.BudgetRate;
import org.kuali.kra.budget.versions.BudgetVersionOverview;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.kew.exception.WorkflowException;

/**
 * Budget Service interface
 */
public interface BudgetService<T extends BudgetParent>  {
    
    
    /**
     * Service method for adding a {@link BudgetVersionOverview} to a {@link ProposalDevelopmentDocument}. If a 
     * {@link BudgetVersionOverview} instance with the  <code>versionName</code> already exists 
     * in the {@link ProposalDevelopmentDocument}, then a hard error will occur. Try it and you'll see what I mean.
     * 
     * @param document instance to add {@link BudgetVersionOverview} to
     * @param versionName of the {@link BudgetVersionOverview}
     */
    public BudgetDocument<T> addBudgetVersion(BudgetParentDocument<T> document, String versionName) throws WorkflowException;
    
    public void updateDocumentDescription(BudgetVersionOverview budgetVersion);
    
    /**
     * 
     * This method check if activity type changed, then display confirmation message on 'open' budget version.
     * @param pdDoc
     * @param budgetVersionNumbe
     * @return
     */
    public boolean checkActivityTypeChange(BudgetParentDocument<T> pdDoc, Budget budget);
  
    /**
     * 
     * This method checks if activity type changed, then display confirmation message on 'open' budget version.
     * @param allPropRates
     * @param proposalActivityTypeCode
     * @return
     */
    public boolean checkActivityTypeChange(Collection<BudgetRate> allPropRates, String proposalActivityTypeCode);
   
    /**
     * 
     * This method returns the saved Proposal Rates collection.
     * @param pdDoc
     * @param budgetVersionNumbe
     * @return Collection<BudgetRate>
     */

    public Collection<BudgetRate> getSavedProposalRates(Budget budget);
    
    /**
     * check if this line item CE has inflation rate
     * This method...
     * @param budgetDocument
     * @param budgetLineItem
     * @return
     */
    public boolean ValidInflationCeRate(BudgetLineItemBase budgetLineItem);
    
    public String getActivityTypeForBudget(BudgetDocument<T> budgetDocument);
    
    /**
     * This method returns the applicable Object Codes (Cost Elements) for a given Budget Person 
     * based on his Job Code
     * @param proposalNumber
     * @param budgetVersionNumber
     * @param personSequenceNumber
     * @return List of Cost Elements
     */
    public List<ValidCeJobCode> getApplicableCostElements(Long budgetId, String personSequenceNumber);
    
    /**
     * 
     * This method returns the applicable Object Codes (Cost Elements) for a given Budget Person, converted to string separated by ",".
     * @param proposalNumber
     * @param budgetVersionNumber
     * @param personSequenceNumber
     * @return List of Cost Elements
     */
    public String getApplicableCostElementsForAjaxCall(Long budgetId, String personSequenceNumber, String budgetCategoryTypeCode);

    /**
     * This method returns the existing Personnel Line Item Group Names 
     * based on his Job Code
     * @param proposalNumber
     * @param budgetVersionNumber
     * @param budgetPeriod
     * @return List of existing Group Names
     */
    public List<String> getExistingGroupNames(String budgetId, String budgetPeriod);
    
    /**
     * 
     * This method returns the existing Personnel Line Item Group Names , converted to string separated by ",".
     * @param proposalNumber
     * @param budgetVersionNumber
     * @param budgetPeriod
     * @return List of existing Group Names
     */
    public String getExistingGroupNamesForAjaxCall(String budgetId, String budgetPeriod);

    /**
     * 
     * This method returns the Non-Personnel Panel Name (based on the variables).
     * @param budgetPeriod
     * @param budgetLineItem
     * @return Non-Personnel Panel Name for the passed in Line Item
     */
    public String getBudgetExpensePanelName(BudgetPeriod budgetPeriod, BudgetLineItem budgetLineItem);

    public Collection<BudgetRate> getSavedProposalRates(BudgetVersionOverview budgetToOpen);

    /**
     * 
     * This method to check the audit rule when try to save 'budgetversion' and with 'complete' status.
     * @param proposalDevelopmentDocument
     * @return
     * @throws Exception
     */
    public boolean validateBudgetAuditRuleBeforeSaveBudgetVersion(BudgetParentDocument<T> proposalDevelopmentDocument) throws Exception;

    /**
     * Determine if the names of a {@link BudgetVersionOverview} instances in the given {@link  ProposalDevelopmentDocument} instance is valid
     *
     * @param document {@link ProposalDevelopmentDocument} instance to get {@link BudgetVersionOverview} instances from
     * @param versionName to check
     * @return true for valid false otherwie
     */
    public boolean isBudgetVersionNameValid(BudgetParentDocument<T> document, String versionName);

    /**
     * 
     * This method to invoke audit rule check for budget if status is final only
     * This is called by PD's turnon validation
     * @param proposalDevelopmentDocument
     * @return
     */
    public boolean validateBudgetAuditRule(BudgetParentDocument<T> parentDocument) throws Exception;

    /**
     * Returns a new finalized BudgetDocument with the data from the given BudgetDocument copied over.
     * @param budgetDocument
     * @return BudgetDocument
     * @throws WorkflowException
     */
    public BudgetDocument<T> copyBudgetVersion(BudgetDocument<T> budgetDocument) throws WorkflowException;

}

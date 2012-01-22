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

import java.util.Collection;
import java.util.List;

import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.award.commitments.AwardFandaRate;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.core.BudgetCommonService;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.document.BudgetParentDocument;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.rates.BudgetRate;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.rice.kew.api.exception.WorkflowException;

public interface AwardBudgetService extends BudgetCommonService<Award> {

    /**
     * 
     */
    public void processSubmision(AwardBudgetDocument awardBudgetDocument);
    
    /**
     * 
     */
    public void processApproval(AwardBudgetDocument awardBudgetDocument);
    
    /**
     * 
     */
    public void processDisapproval(AwardBudgetDocument awardBudgetDocument);
    
    /**
     * 
     */
    public void post(AwardBudgetDocument awardBudgetDocument);

    /**
     * 
     */
    public void toggleStatus(AwardBudgetDocument awardBudgetDocument);
 
    /**
     * 
     */
    public AwardBudgetDocument rebudget(AwardDocument awardDocument,String documentDescription) throws WorkflowException;

    /**
     * 
     * Copies all line items from the BudgetPeriods included in rawValues into awardBudgetPeriod fixing
     * dates and making sure personnel referenced are also added to the awardBudget.
     * @param rawValues Collection of BudgetPeriods with line items to be copied to the awardBudgetPeriod
     * @param awardBudgetPeriod
     */
    public void copyLineItemsFromProposalPeriods(Collection rawValues, BudgetPeriod awardBudgetPeriod) throws WorkflowException;
    
    /**
     * Gets all budget periods from proposals that are funding this award.
     * @param awardNumber
     * @return
     */
    public List<BudgetPeriod> findBudgetPeriodsFromLinkedProposal(String awardNumber);
    
    /**
     * Return a list of the award budget status codes that are considered inactive,
     * currently cancelled, rejected and do not post. This is used to determine
     * which budgets to display by default.
     * @return
     */
    public List<String> getInactiveBudgetStatus();
    
    /**
     * Populates the passed in limit summary given the award document. Will not overwrite or recalculate
     * previously stored budgets in the summary if they are the same budget as in the award document.
     * @param limitSummary
     * @param awardDocument
     */
    void populateBudgetLimitSummary(BudgetLimitSummaryHelper limitSummary, AwardDocument awardDocument);
    
    List<BudgetDocumentVersion> getAllBudgetsForAward(AwardDocument awardDocument);

    /**
     * 
     * Get the total cost limit from the award. Returns the less of the obligated distributable amount or the total cost limit.
     * @param awardDocument
     * @return
     */
    BudgetDecimal getTotalCostLimit(AwardDocument awardDocument);
    
    /**
     * Populates the budget limits from the award. This includes total cost limit and
     * specific budget limits (direct and F&A currently)
     * @param awardBudgetDocument
     * @param parentDocument
     */
    void setBudgetLimits(AwardBudgetDocument awardBudgetDocument, AwardDocument parentDocument); 
    
    /**
     * Returns the active award or if none exist, the newest non-cancelled award.
     * @param awardNumber
     * @return
     */
    Award getActiveOrNewestAward(String awardNumber);

//    public boolean isSummaryPeriodCalcAmountChanged(BudgetPeriod budgetPeriod);
    /**
     * Checks for budgets that have not been posted, cancelled or rejected.
     * @param event
     * @param award
     * @return true if any unfinalized budgets are found
     * @throws WorkflowException
     */
    boolean checkForOutstandingBudgets(BudgetParentDocument parentDoc);
    
    /**
     * 
     * This method checks if Award rates changed, then display confirmation message on 'open' budget version.
     * @param saved award budget rates
     * @param Award award
     * @return
     */
    public boolean checkRateChange(Collection<BudgetRate> allPropRates,Award award);
    
    
}

/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General License for more details.
 * 
 * You should have received a copy of the GNU Affero General License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.award.budget;

import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.core.BudgetCommonService;
import org.kuali.coeus.common.budget.framework.core.BudgetParentDocument;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.rate.BudgetRate;
import org.kuali.rice.kew.api.exception.WorkflowException;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface AwardBudgetService extends BudgetCommonService<Award> {


    void processSubmision(AwardBudgetDocument awardBudgetDocument);

    void processApproval(AwardBudgetDocument awardBudgetDocument);

    void processDisapproval(AwardBudgetDocument awardBudgetDocument);

    boolean isFinancialIntegrationOn();

    void post(AwardBudgetDocument awardBudgetDocument);

    void postWithFinancialIntegration(AwardBudgetDocument awardBudgetDocument) throws Exception;

    void toggleStatus(AwardBudgetDocument awardBudgetDocument);

    AwardBudgetDocument rebudget(AwardDocument awardDocument,String documentDescription) throws WorkflowException;

    /**
     * 
     * Copies all line items from the BudgetPeriods included in rawValues into awardBudgetPeriod fixing
     * dates and making sure personnel referenced are also added to the awardBudget.
     * @param rawValues Collection of BudgetPeriods with line items to be copied to the awardBudgetPeriod
     */
    void copyLineItemsFromProposalPeriods(Collection<BudgetPeriod> rawValues, BudgetPeriod awardBudgetPeriod) throws WorkflowException;
    
    /**
     * Gets all budget periods from proposals that are funding this award.
     */
    List<BudgetPeriod> findBudgetPeriodsFromLinkedProposal(String awardNumber);
    List<BudgetPeriod> findBudgetPeriodsFromLinkedProposal(Award award);
    
    /**
     * Return a list of the award budget status codes that are considered inactive,
     * currently cancelled, rejected and do not post. This is used to determine
     * which budgets to display by default.
     */
    List<String> getInactiveBudgetStatus();
    
    /**
     * Populates the passed in limit summary given the award document. Will not overwrite or recalculate
     * previously stored budgets in the summary if they are the same budget as in the award document.
     */
    void populateBudgetLimitSummary(BudgetLimitSummaryHelper limitSummary, Award award);
    
    List<AwardBudgetExt> getAllBudgetsForAward(Award award);

    /**
     * 
     * Get the total cost limit from the award. Returns the less of the obligated distributable amount or the total cost limit.
     */
    ScaleTwoDecimal getTotalCostLimit(Award award);
    
    /**
     * Populates the budget limits from the award. This includes total cost limit and
     * specific budget limits (direct and F&amp;A currently)
     */
    void setBudgetLimits(AwardBudgetDocument awardBudgetDocument, Award award); 
    
    /**
     * Returns the active award or if none exist, the newest non-cancelled award.

     */
    Award getActiveOrNewestAward(String awardNumber);


    /**
     * Checks for budgets that have not been posted, cancelled or rejected.
     * @return true if any unfinalized budgets are found
     */
    boolean checkForOutstandingBudgets(Award award);
    
    /**
     * 
     * This method checks if Award rates changed, then display confirmation message on 'open' budget version.
     */
    boolean checkRateChange(Collection<BudgetRate> allPropRates,Award award);
    
    AwardBudgetDocument getNewBudgetVersionDocument(BudgetParentDocument<Award> parentBudgetDocument, String documentDescription, Map<String, Object> options)
    	    throws WorkflowException;
    /**
     *
     * This method will clear the BudgetSumamryPeriodCalcAmounts
     */
    void removeBudgetSummaryPeriodCalcAmounts(BudgetPeriod budgetPeriod);

    /**
     *
     * Compares the budget limit lists to make sure they match.
     */
    public boolean limitsMatch(List<AwardBudgetLimit> awardLimits, List<AwardBudgetLimit> budgetLimits);



    void populateSummaryCalcAmounts(Budget budget,BudgetPeriod budgetPeriod);
    
    AwardBudgetDocument copyBudgetVersion(AwardBudgetDocument budgetDocument, boolean onlyOnePeriod) throws WorkflowException;
}

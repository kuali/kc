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

import org.kuali.kra.award.budget.AwardBudgetService;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.budget.document.BudgetParentDocument;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.budget.service.ProposalBudgetService;


/**
 * This class creates BudgetCommonService instance
 */
public class BudgetCommonServiceFactory {
    private ProposalBudgetService proposalBudgetService;
    private AwardBudgetService awardBudgetService;
    /**
     * 
     * This method creates BudgetCommonService instance by looking at the classname
     * @return
     */
    @SuppressWarnings("unchecked")
    public static BudgetCommonService createInstance(BudgetParentDocument parentBudgetDocument){
        if(parentBudgetDocument.getClass().equals(AwardDocument.class))
            return KraServiceLocator.getService(AwardBudgetService.class);
        else
            return KraServiceLocator.getService(ProposalBudgetService.class);
    }
    
    /**
     * Gets the awardBudgetService attribute. 
     * @return Returns the awardBudgetService.
     */
    @SuppressWarnings("unchecked")
    public AwardBudgetService getAwardBudgetService() {
        return awardBudgetService;
    }
    /**
     * Sets the awardBudgetService attribute value.
     * @param awardBudgetService The awardBudgetService to set.
     */
    @SuppressWarnings("unchecked")
    public void setAwardBudgetService(AwardBudgetService awardBudgetService) {
        this.awardBudgetService = awardBudgetService;
    }

    /**
     * Gets the proposalBudgetService attribute. 
     * @return Returns the proposalBudgetService.
     */
    public ProposalBudgetService getProposalBudgetService() {
        return proposalBudgetService;
    }

    /**
     * Sets the proposalBudgetService attribute value.
     * @param proposalBudgetService The proposalBudgetService to set.
     */
    public void setProposalBudgetService(ProposalBudgetService proposalBudgetService) {
        this.proposalBudgetService = proposalBudgetService;
    }
}

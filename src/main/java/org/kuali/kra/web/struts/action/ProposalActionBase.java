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
package org.kuali.kra.web.struts.action;

import java.util.List;

import org.kuali.core.service.KualiConfigurationService;
import org.kuali.kra.budget.bo.BudgetVersionOverview;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

/**
 * This class contains methods common to ProposalDevelopment and Budget actions.
 */
public class ProposalActionBase extends KraTransactionalDocumentActionBase {
    
    /**
     * This method looks for the version corresponding to finalBudgetVersion in the list, then marks that version as final.
     * 
     * @param finalBudgetVersion
     * @param budgetVersions
     */
    protected void setFinalBudgetVersion(Integer finalBudgetVersion, List<BudgetVersionOverview> budgetVersions) {
        for (BudgetVersionOverview budgetVersion: budgetVersions) {
            if (budgetVersion.getBudgetVersionNumber().equals(finalBudgetVersion)) {
                budgetVersion.setFinalVersionFlag(true);
            } else {
                budgetVersion.setFinalVersionFlag(false);
            }
        }
    }
    
    /**
     * This method looks at the list of budgetVersions for the final version, then returns the version number.
     * 
     * @param budgetVersions
     * @return
     */
    protected Integer getFinalBudgetVersion(List<BudgetVersionOverview> budgetVersions) {
        for (BudgetVersionOverview budgetVersion: budgetVersions) {
            if (budgetVersion.isFinalVersionFlag()) {
                return budgetVersion.getBudgetVersionNumber();
            }
        }
        return null;
    }
    
    /**
     * This method sets the proposal budget status to the status of the final budget version.  If there is no final version, do nothing.
     * 
     * @param proposalDevelopmentDocument
     */
    protected void setProposalStatus(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        for (BudgetVersionOverview budgetVersion: proposalDevelopmentDocument.getBudgetVersionOverviews()) {
            if (budgetVersion.isFinalVersionFlag()) {
                proposalDevelopmentDocument.setBudgetStatus(budgetVersion.getBudgetStatus());
                return;
            }
        }
    }
    
    /**
     * This method sets the budget status of the 'final' budget version (if it exists) to the proposal budget status
     * as indicated in the proposal development document.
     * 
     * @param proposalDevelopmentDocument
     */
    protected void setProposalStatuses(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        
        KualiConfigurationService kualiConfigurationService = KraServiceLocator.getService(KualiConfigurationService.class);
        String budgetStatusIncompleteCode = kualiConfigurationService.getParameter(
                Constants.PARAMETER_MODULE_BUDGET, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.BUDGET_STATUS_INCOMPLETE_CODE).getParameterValue();
        
        for (BudgetVersionOverview budgetVersion: proposalDevelopmentDocument.getBudgetVersionOverviews()) {
            if (budgetVersion.isFinalVersionFlag()) {
                budgetVersion.setBudgetStatus(proposalDevelopmentDocument.getBudgetStatus());
            }
            else {
                budgetVersion.setBudgetStatus(budgetStatusIncompleteCode);
            }
        }
    }

}

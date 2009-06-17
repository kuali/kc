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
package org.kuali.kra.budget.document.authorizer;

import org.kuali.kra.authorization.Task;
import org.kuali.kra.budget.bo.BudgetVersionOverview;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.document.authorization.BudgetTask;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

/**
 * The Budget Modify Authorizer checks to see if the user has 
 * the necessary permission to modify a specific budget.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class BudgetModifyAuthorizer extends BudgetAuthorizer {
 
    /**
     * @see org.kuali.kra.proposaldevelopment.document.authorizer.ProposalAuthorizer#isAuthorized(org.kuali.rice.kns.bo.user.UniversalUser, org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm)
     */
    public boolean isAuthorized(String username, Task task) {
        
        BudgetTask budgetTask = (BudgetTask) task;
        
        BudgetDocument budgetDocument = budgetTask.getBudgetDocument();
        ProposalDevelopmentDocument doc = budgetDocument.getProposal();
        
        return !kraWorkflowService.isInWorkflow(doc) &&
               hasProposalPermission(username, doc, PermissionConstants.MODIFY_BUDGET) &&
              !doc.getSubmitFlag();        
    }
    
    /**
     * If the Budget Document completed or not?
     * @param proposalDoc the proposal development document
     * @param budgetDocument the budget document
     * @return true if completed; otherwise false
     */
    private boolean isBudgetCompleted(ProposalDevelopmentDocument proposalDoc, BudgetDocument budgetDocument) {
        if (!proposalDoc.isProposalComplete()) {
            return false;
        }
        for (BudgetVersionOverview budgetVersion: proposalDoc.getBudgetVersionOverviews()) {
            if (budgetVersion.isFinalVersionFlag() && budgetVersion.getBudgetVersionNumber().equals(budgetDocument.getBudgetVersionNumber())) {
                return true;
            }
        }
        return false;
    }
}

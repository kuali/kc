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
package org.kuali.kra.proposaldevelopment.budget.document.authorizer;

import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.document.authorization.BudgetTask;
import org.kuali.kra.budget.document.authorizer.BudgetAuthorizer;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

/**
 * The Budget View Authorizer checks to see if the user has 
 * the necessary permission to view a specific budget.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ViewProposalBudgetAuthorizer extends BudgetAuthorizer {
 
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.budget.document.authorizer.BudgetAuthorizer#isAuthorized(java.lang.String, org.kuali.kra.budget.document.authorization.BudgetTask)
     */
    public boolean isAuthorized(String userId, BudgetTask task) {
        
        BudgetDocument budgetDocument = task.getBudgetDocument();
        ProposalDevelopmentDocument doc = (ProposalDevelopmentDocument) budgetDocument.getParentDocument();
        
        return hasParentPermission(userId, doc, PermissionConstants.VIEW_BUDGET) 
            || kraWorkflowService.hasWorkflowPermission(userId, doc);
            
    }
    
}
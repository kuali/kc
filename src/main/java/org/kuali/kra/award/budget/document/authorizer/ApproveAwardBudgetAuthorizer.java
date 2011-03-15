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
package org.kuali.kra.award.budget.document.authorizer;

import org.kuali.kra.authorization.Task;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.award.budget.document.authorization.AwardBudgetTask;
import org.kuali.kra.budget.document.authorizer.BudgetAuthorizer;

/**
 * The AwardBudget Modify Authorizer checks to see if the user has 
 * the necessary permission to modify a specific budget.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ApproveAwardBudgetAuthorizer extends BudgetAuthorizer {
 
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.budget.document.authorizer.BudgetAuthorizer#isAuthorized(java.lang.String, org.kuali.kra.authorization.Task)
     */
    public boolean isAuthorized(String userId, Task task) {
        AwardBudgetTask budgetTask = (AwardBudgetTask) task;
        AwardBudgetDocument budgetDocument = budgetTask.getAwardBudgetDocument();
        
        // AwardDocument doc = (AwardDocument) budgetDocument.getParentDocument();
        // return hasUnitPermission(userId, doc.getLeadUnitNumber(), Constants.MODULE_NAMESPACE_AWARD_BUDGET, AwardPermissionConstants.APPROVE_AWARD_BUDGET.getAwardPermission());
        
        return kraWorkflowService.isEnRoute(budgetDocument) && kraWorkflowService.hasWorkflowPermission(userId, budgetDocument);
    }

}
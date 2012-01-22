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
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.budget.document.authorizer.BudgetAuthorizer;
import org.kuali.kra.infrastructure.AwardPermissionConstants;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.coreservice.framework.CoreFrameworkServiceLocator;
import org.kuali.rice.kew.api.WorkflowDocument;

/**
 * The AwardBudget Modify Authorizer checks to see if the user has 
 * the necessary permission to modify a specific budget.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class PostAwardBudgetAuthorizer extends BudgetAuthorizer {
 
    private static final String POST_ENABLED_PARAM_VALUE_1 = "1";

    /**
     * @see org.kuali.kra.proposaldevelopment.document.authorizer.BudgetAuthorizer#isAuthorized(org.kuali.rice.kns.bo.user.UniversalUser, org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm)
     */
    public boolean isAuthorized(String userId, Task task) {
        AwardBudgetTask budgetTask = (AwardBudgetTask) task;
        
        AwardBudgetDocument budgetDocument = budgetTask.getAwardBudgetDocument();
        AwardDocument doc = (AwardDocument) budgetDocument.getParentDocument();
        WorkflowDocument workflowDoc = getWorkflowDocument(budgetDocument);
        return workflowDoc.isFinal() && isAwardBudgetToBePosted(budgetDocument) && canPost() &&
            hasUnitPermission(userId, doc.getLeadUnitNumber(), Constants.MODULE_NAMESPACE_AWARD_BUDGET, 
                AwardPermissionConstants.POST_AWARD_BUDGET.getAwardPermission());
    }

    private boolean canPost() {
        String postEnabled = getParameterService().getParameterValueAsString(AwardBudgetDocument.class, KeyConstants.AWARD_BUDGET_POST_ENABLED);
        return postEnabled.equals(POST_ENABLED_PARAM_VALUE_1);
    }

    private boolean isAwardBudgetToBePosted(AwardBudgetDocument budgetDocument) {
        String toBePostedStatusCode = getParameterService().getParameterValueAsString(AwardBudgetDocument.class, KeyConstants.AWARD_BUDGET_STATUS_TO_BE_POSTED);
        return budgetDocument.getAwardBudget().getAwardBudgetStatusCode().equals(toBePostedStatusCode);
    }

    /**
     * Gets the parameterService attribute. 
     * @return Returns the parameterService.
     */
    public ParameterService getParameterService() {
        return CoreFrameworkServiceLocator.getParameterService();
    }

}

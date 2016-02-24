/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.award.budget.document.authorizer;

import org.kuali.coeus.common.framework.auth.task.Task;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.award.budget.document.authorization.AwardBudgetTask;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.infrastructure.AwardPermissionConstants;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.coreservice.framework.CoreFrameworkServiceLocator;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.WorkflowDocument;

/**
 * The AwardBudget Modify Authorizer checks to see if the user has 
 * the necessary permission to modify a specific budget.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ToggleAwardBudgetStatusAuthorizer extends BudgetAuthorizer {

    public boolean isAuthorized(String userId, Task task) {
        AwardBudgetTask budgetTask = (AwardBudgetTask) task;
        
        AwardBudgetDocument budgetDocument = budgetTask.getAwardBudgetDocument();
        AwardDocument doc = (AwardDocument) budgetDocument.getBudget().getBudgetParent().getDocument();
        WorkflowDocument workflowDoc = getWorkflowDocument(budgetDocument);
        return workflowDoc.isFinal() && isToggleAwardBudgetStatusValid(budgetDocument) &&
            hasUnitPermission(userId, doc.getLeadUnitNumber(), Constants.MODULE_NAMESPACE_AWARD_BUDGET, 
                AwardPermissionConstants.MAINTAIN_AWARD_BUDGET_ROUTING.getAwardPermission());
    }

    private boolean isToggleAwardBudgetStatusValid(AwardBudgetDocument budgetDocument) {
        String toBePostedStatusCode = getParameterService().getParameterValueAsString(AwardBudgetDocument.class, KeyConstants.AWARD_BUDGET_STATUS_TO_BE_POSTED);
        String doNotPostStatusCode = getParameterService().getParameterValueAsString(AwardBudgetDocument.class, KeyConstants.AWARD_BUDGET_STATUS_DO_NOT_POST);
        String budgetStatusCode = budgetDocument.getAwardBudget().getAwardBudgetStatusCode();
        return budgetStatusCode.equals(toBePostedStatusCode) || budgetStatusCode.equals(doNotPostStatusCode);
    }

    /**
     * Gets the parameterService attribute. 
     * @return Returns the parameterService.
     */
    public ParameterService getParameterService() {
        return CoreFrameworkServiceLocator.getParameterService();
    }

}

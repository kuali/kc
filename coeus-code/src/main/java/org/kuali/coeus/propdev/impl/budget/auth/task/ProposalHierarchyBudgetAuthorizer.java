/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.propdev.impl.budget.auth.task;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.common.budget.framework.core.BudgetDocument;
import org.kuali.coeus.common.budget.framework.auth.task.BudgetTask;
import org.kuali.coeus.common.budget.framework.auth.task.BudgetAuthorizer;
import org.kuali.kra.infrastructure.PermissionConstants;


/**
 * The Budget Modify Authorizer checks to see if the user has 
 * the necessary permission to modify a specific budget.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProposalHierarchyBudgetAuthorizer extends BudgetAuthorizer {

    public boolean isAuthorized(String userId, BudgetTask task) {
        BudgetDocument budgetDocument = task.getBudgetDocument();
        ProposalDevelopmentDocument doc = (ProposalDevelopmentDocument)budgetDocument.getBudget().getBudgetParent().getDocument();

        return !doc.isViewOnly() && hasParentPermission(userId, doc, PermissionConstants.MAINTAIN_PROPOSAL_HIERARCHY);
    }
}

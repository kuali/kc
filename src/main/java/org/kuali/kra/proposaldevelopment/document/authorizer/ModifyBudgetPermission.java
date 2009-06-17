/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.document.authorizer;

import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.document.authorization.ProposalTask;

public class ModifyBudgetPermission extends ProposalAuthorizer {

    @Override
    public boolean isAuthorized(String username, ProposalTask task) {

        ProposalDevelopmentDocument doc = task.getDocument();

        boolean hasPermission = false;
        if (!kraWorkflowService.isInWorkflow(doc)) {
            hasPermission = hasProposalPermission(username, doc, PermissionConstants.MODIFY_BUDGET);
        }
        return hasPermission;
    }
}



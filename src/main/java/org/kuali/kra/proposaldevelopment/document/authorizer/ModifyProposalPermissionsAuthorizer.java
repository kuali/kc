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

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.kim.service.PersonService;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.document.authorization.ProposalTask;

/**
 * The Modify Proposal Permissions Authorizer checks to see if the user has permission to maintain proposal access, i.e. assign
 * Users to Proposal Roles & the document cannot be in workflow.
 */
public class ModifyProposalPermissionsAuthorizer extends ProposalAuthorizer {

    @Override
    /**
     * @see
     * org.kuali.kra.proposaldevelopment.document.authorizer.ProposalAuthorizer#isAuthorized(org.kuali.rice.kns.bo.user.UniversalUser,
     * org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm)
     */
    public boolean isAuthorized(String username, ProposalTask task) {
        return (hasFullAuthorization(username, task) || hasAddViewerAuthorization(username, task));
    }
    /**
     * This method checks if the user has full (pre-workflow/pre-submission) proposal access maintenance rights
     * @param username the name of the user requesting access
     * @param task the task object
     * @return true if the user has full (pre-workflow/pre-submission) proposal access maintenance rights
     */
    private boolean hasFullAuthorization(String username, ProposalTask task) {
        ProposalDevelopmentDocument doc = task.getDocument();
        boolean hasPermission = hasProposalPermission(username, doc, PermissionConstants.MAINTAIN_PROPOSAL_ACCESS)
                && !kraWorkflowService.isInWorkflow(doc) 
                && !doc.getSubmitFlag();
        return hasPermission;
    }
    
    /**
     * This method checks if the user has rights to add proposal viewers.
     * @param username the name of the user requesting access
     * @param task the task object
     * @return
     */
    private boolean hasAddViewerAuthorization (String username, ProposalTask task) {
        boolean hasPermission = false;
        ProposalDevelopmentDocument doc = task.getDocument();

        if (hasProposalPermission(username, doc, PermissionConstants.ADD_PROPOSAL_VIEWER) 
                && kraWorkflowService.isInWorkflow(doc)) {
            // once workflowed (OSP Administrator and Aggregator have ADD_PROPOSAL_VIEWER permission)
            hasPermission = true;
        }
        else if (kraWorkflowService.hasWorkflowPermission(username, doc) 
                && kraWorkflowService.isEnRoute(doc)) {
            // Approvers (users in workflow) have permission while EnRoute
            hasPermission = true;
        }

        return hasPermission;
         
    }
}

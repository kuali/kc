/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.propdev.impl.docperm;

import org.kuali.coeus.propdev.impl.auth.task.ProposalAuthorizer;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.coeus.propdev.impl.auth.task.ProposalTask;

/**
 * The Modify Proposal Permissions Authorizer checks to see if the user has permission to maintain proposal access, i.e. assign
 * Users to Proposal Roles & the document cannot be in workflow.
 */
public class ModifyProposalPermissionsAuthorizer extends ProposalAuthorizer {

    private KcWorkflowService kraWorkflowService;

    @Override
    public boolean isAuthorized(String userId, ProposalTask task) {
        return (hasFullAuthorization(userId, task) || hasAddViewerAuthorization(userId, task));
    }
    
    /**
     * This method checks if the user has full (pre-workflow/pre-submission) proposal access maintenance rights
     * @param userId the name of the user requesting access
     * @param task the task object
     * @return true if the user has full (pre-workflow/pre-submission) proposal access maintenance rights
     */
    private boolean hasFullAuthorization(String userId, ProposalTask task) {
        ProposalDevelopmentDocument doc = task.getDocument();
        boolean hasPermission = !doc.isViewOnly() 
                                && hasProposalPermission(userId, doc, PermissionConstants.MAINTAIN_PROPOSAL_ACCESS)
                                && !kraWorkflowService.isInWorkflow(doc) 
                                && !doc.getDevelopmentProposal().getSubmitFlag();
        return hasPermission;
    }
    
    /**
     * This method checks if the user has rights to add proposal viewers.
     * @param userId the name of the user requesting access
     * @param task the task object
     * @return
     */
    private boolean hasAddViewerAuthorization (String userId, ProposalTask task) {
        boolean hasPermission = false;
        ProposalDevelopmentDocument doc = task.getDocument();

        if (hasProposalPermission(userId, doc, PermissionConstants.ADD_PROPOSAL_VIEWER) 
                && kraWorkflowService.isInWorkflow(doc)) {
            // once workflowed (OSP Administrator and Aggregator have ADD_PROPOSAL_VIEWER permission)
            hasPermission = true;
        } else if (kraWorkflowService.hasWorkflowPermission(userId, doc) 
                && kraWorkflowService.isEnRoute(doc)) {
            // Approvers (users in workflow) have permission while EnRoute
            hasPermission = true;
        }

        return hasPermission;
         
    }

    public KcWorkflowService getKraWorkflowService() {
        return kraWorkflowService;
    }

    public void setKraWorkflowService(KcWorkflowService kraWorkflowService) {
        this.kraWorkflowService = kraWorkflowService;
    }
}

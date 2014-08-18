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
package org.kuali.coeus.propdev.impl.core;

import org.kuali.coeus.propdev.impl.auth.task.ProposalAuthorizer;
import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.coeus.propdev.impl.auth.task.ProposalTask;

/**
 * The Submit to Workflow Authorizer determines if the user can
 * submit a proposal to workflow.  This is only allowed if the
 * proposal is not already in workflow and the person has the
 * necessary permission and the proposal is not a child in a 
 * hierarchy.
 */
public class SubmitToWorkflowAuthorizer extends ProposalAuthorizer {
    private KcWorkflowService kraWorkflowService;

    public boolean isAuthorized(String userId, ProposalTask task) {
        ProposalDevelopmentDocument doc = task.getDocument();
        return !kraWorkflowService.isInWorkflow(doc) &&
               hasProposalPermission(userId, doc, PermissionConstants.SUBMIT_PROPOSAL) &&
               !doc.getDevelopmentProposal().isChild();
    }

    public KcWorkflowService getKraWorkflowService() {
        return kraWorkflowService;
    }

    public void setKraWorkflowService(KcWorkflowService kraWorkflowService) {
        this.kraWorkflowService = kraWorkflowService;
    }
}

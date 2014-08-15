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
import org.kuali.coeus.common.framework.auth.UnitAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.workflow.KcDocumentRejectionService;
import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.coeus.propdev.impl.auth.task.ProposalTask;
import org.kuali.rice.kew.api.WorkflowDocument;


/**
 * The Modify Proposal Authorizer checks to see if the user has 
 * permission to modify a proposal. Authorization depends upon whether
 * the proposal is being created or modified.  For creation, the
 * user needs the CREATE_PROPOSAL permission in the Lead Unit for
 * that proposal.  If the proposal is being modified, the user only
 * needs to have the MODIFY_PROPOSAL permission for that proposal and
 * the document cannot be in workflow.
 */
public class ModifyProposalAuthorizer extends ProposalAuthorizer {

    private KcWorkflowService kraWorkflowService;

    public boolean isAuthorized(String userId, ProposalTask task) {
        boolean hasPermission = true;
        ProposalDevelopmentDocument doc = task.getDocument();
        String proposalNbr = doc.getDevelopmentProposal().getProposalNumber();

        if (proposalNbr == null) {
            String unitNumber = doc.getDevelopmentProposal().getOwnedByUnitNumber();
            
            // If the unit number is not specified, we will let the save operation continue because it
            // will fail with an error.  But if the user tries to save a proposal for a wrong unit, then
            // we will indicate that the user does not have permission to do that. 
            
            if (unitNumber != null) {
                UnitAuthorizationService auth = KcServiceLocator.getService(UnitAuthorizationService.class);
                hasPermission = auth.hasPermission(userId, unitNumber, Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, PermissionConstants.CREATE_PROPOSAL);
            }
        } else {
            /*
             * After the initial save, the proposal can only be modified if it is not in workflow
             * and the user has the require permission.
             */
            WorkflowDocument wfd=doc.getDocumentHeader().getWorkflowDocument();

            boolean hasBeenRejected= KcServiceLocator.getService(KcDocumentRejectionService.class).isDocumentOnInitialNode(doc);
            hasPermission = !doc.isViewOnly() &&
                            hasProposalPermission(userId, doc, PermissionConstants.MODIFY_PROPOSAL) &&
                            (!kraWorkflowService.isInWorkflow(doc) || hasBeenRejected) &&
                            !doc.getDevelopmentProposal().getSubmitFlag();
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

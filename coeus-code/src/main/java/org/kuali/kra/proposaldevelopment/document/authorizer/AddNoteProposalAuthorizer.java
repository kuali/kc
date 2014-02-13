/*
 * Copyright 2005-2013 The Kuali Foundation
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

import org.kuali.coeus.sys.framework.auth.UnitAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.document.authorization.ProposalTask;

public class AddNoteProposalAuthorizer extends ProposalAuthorizer {

    private KcWorkflowService kraWorkflowService;

    @Override
    public boolean isAuthorized(String userId, ProposalTask task) {
        boolean hasPermission = true;
        ProposalDevelopmentDocument doc = task.getDocument();
        String proposalNbr = doc.getDevelopmentProposal().getProposalNumber();

        if (proposalNbr == null) {
            
            // We have to consider the case when we are saving the document for the first time.
            
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
             * After the initial save, the proposal can have new notes added by users with the modify proposal role.
             */
            
            hasPermission = hasProposalPermission(userId, doc, PermissionConstants.VIEW_PROPOSAL)
                || kraWorkflowService.hasWorkflowPermission(userId, doc);
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

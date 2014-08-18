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
package org.kuali.coeus.propdev.impl.core;

import org.kuali.coeus.propdev.impl.auth.task.ProposalAuthorizer;
import org.kuali.coeus.common.framework.auth.UnitAuthorizationService;
import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.coeus.propdev.impl.auth.task.ProposalTask;

public class AddNoteProposalAuthorizer extends ProposalAuthorizer {

    private KcWorkflowService kraWorkflowService;
    private UnitAuthorizationService unitAuthorizationService;

    public void setUnitAuhorizationService (UnitAuthorizationService unitAuthorizationService){
        this.unitAuthorizationService = unitAuthorizationService;
    }

    protected UnitAuthorizationService getUnitAuthorizationService () {
        return unitAuthorizationService;
    }

    @Override
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
                UnitAuthorizationService auth = getUnitAuthorizationService();
                hasPermission = auth.hasPermission(userId, unitNumber, Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, PermissionConstants.CREATE_PROPOSAL);
            }
        } else {

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

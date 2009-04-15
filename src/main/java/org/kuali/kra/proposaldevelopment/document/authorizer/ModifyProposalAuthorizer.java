/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.document.authorizer;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.document.authorization.ProposalTask;
import org.kuali.kra.service.UnitAuthorizationService;

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

    /**
     * @see org.kuali.kra.proposaldevelopment.document.authorizer.ProposalAuthorizer#isAuthorized(org.kuali.core.bo.user.UniversalUser, org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm)
     */
    public boolean isAuthorized(String username, ProposalTask task) {
        boolean hasPermission = true;
        ProposalDevelopmentDocument doc = task.getProposalDevelopmentDocument();
        String proposalNbr = doc.getProposalNumber();
        if (proposalNbr == null) {
            
            // We have to consider the case when we are saving the document for the first time.
            
            String unitNumber = doc.getOwnedByUnitNumber();
            
            // If the unit number is not specified, we will let the save operation continue because it
            // will fail with an error.  But if the user tries to save a proposal for a wrong unit, then
            // we will indicate that the user does not have permission to do that.
            
            if (unitNumber != null) {
                UnitAuthorizationService auth = KraServiceLocator.getService(UnitAuthorizationService.class);
                hasPermission = auth.hasPermission(username, unitNumber, PermissionConstants.CREATE_PROPOSAL);
            }
        } else {
            /*
             * After the initial save, the proposal can only be modified if it is not in workflow
             * and the user has the require permission.
             */
            hasPermission = hasProposalPermission(username, doc, PermissionConstants.MODIFY_PROPOSAL) &&
                            !kraWorkflowService.isInWorkflow(doc) &&
                            !doc.getSubmitFlag();
        }
        return hasPermission;
    }
}

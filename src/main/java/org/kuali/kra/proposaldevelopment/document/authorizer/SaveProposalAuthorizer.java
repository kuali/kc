/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.document.authorization.ProposalAuthorizer;
import org.kuali.kra.proposaldevelopment.document.authorization.ProposalTask;
import org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService;
import org.kuali.kra.service.UnitAuthorizationService;

/**
 * The Save Proposal Authorizer checks to see if the user has 
 * permission to save a proposal. Authorization depends upon whether
 * the proposal is being created or modified.  For creation, the
 * user needs the CREATE_PROPOSAL permission in the Lead Unit for
 * that proposal.  If the proposal is being modified, the user only
 * needs to have the MODIFY_PROPOSAL permission for that proposal.
 */
public class SaveProposalAuthorizer extends ProposalAuthorizer {

    /**
     * @see org.kuali.kra.proposaldevelopment.document.authorization.ProposalAuthorizer#isResponsible(org.kuali.kra.proposaldevelopment.document.authorization.ProposalTask)
     */
    public boolean isResponsible(ProposalTask task) {
        return StringUtils.equals("save", task.getTaskName());
    }
    
    /**
     * @see org.kuali.kra.proposaldevelopment.document.authorization.ProposalAuthorizer#isAuthorized(org.kuali.core.bo.user.UniversalUser, org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm)
     */
    public boolean isAuthorized(String username, ProposalTask task) {
        boolean hasPermission = true;
        ProposalDevelopmentDocument doc = task.getProposalDevelopmentDocument();
        String proposalNbr = doc.getProposalNumber();
        if (proposalNbr == null) {
            String unitNumber = doc.getOwnedByUnitNumber();
            
            // If the unit number is not specified, we will let the save operation continue because it
            // will fail with an error.  But if the user tries to save a proposal for a wrong unit, then
            // we will indicate that the user does not have permission to do that.
            
            if (unitNumber != null) {
                UnitAuthorizationService auth = KraServiceLocator.getService(UnitAuthorizationService.class);
                hasPermission = auth.hasPermission(username, unitNumber, PermissionConstants.CREATE_PROPOSAL);
            }
        } else {
            ProposalAuthorizationService auth = KraServiceLocator.getService(ProposalAuthorizationService.class);
            hasPermission = auth.hasPermission(username, doc, PermissionConstants.MODIFY_PROPOSAL);
        }
        return hasPermission;
    }
}

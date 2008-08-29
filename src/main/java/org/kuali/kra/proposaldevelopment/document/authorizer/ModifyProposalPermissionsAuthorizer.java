/*
 * Copyright 2006-2008 The Kuali Foundation
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

/**
 * The Modify Proposal Permissions Authorizer checks to see if the user has 
 * permission to maintain proposal access, i.e. assign Users to Proposal Roles & 
 * the document cannot be in workflow.
 */
public class ModifyProposalPermissionsAuthorizer extends ProposalAuthorizer {

    @Override
    /**
     * @see org.kuali.kra.proposaldevelopment.document.authorizer.ProposalAuthorizer#isAuthorized(org.kuali.core.bo.user.UniversalUser, org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm)
     */
    public boolean isAuthorized(String username, ProposalTask task) {
        boolean hasPermission = true;
        ProposalDevelopmentDocument doc = task.getProposalDevelopmentDocument();

        hasPermission =  hasProposalPermission(username, doc, PermissionConstants.MAINTAIN_PROPOSAL_ACCESS) && 
                         !kraWorkflowService.isInWorkflow(doc) && 
                         !doc.getSubmitFlag();   
        
        return hasPermission;
    }
}

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

import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.document.authorization.ProposalTask;

/**
 * The Modify Narratives Authorizer checks to see if the user has 
 * permission to modify the Narratives tab. Authorization depends upon whether
 * the user has MODIFY_NARRATIVES permission.  
 */
 
public class ModifyNarrativesAuthorizer extends ProposalAuthorizer {

    public boolean isAuthorized(String username, ProposalTask task) {
        
        ProposalDevelopmentDocument doc = task.getDocument();
        
        boolean hasPermission = false;
        if (!kraWorkflowService.isInWorkflow(doc) && !doc.getSubmitFlag()) {
            hasPermission = hasProposalPermission(username, doc, PermissionConstants.MODIFY_NARRATIVE);
        }
        return hasPermission;
    }
}

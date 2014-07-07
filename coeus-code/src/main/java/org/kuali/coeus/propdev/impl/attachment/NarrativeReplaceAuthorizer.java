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
package org.kuali.coeus.propdev.impl.attachment;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.kra.infrastructure.PermissionConstants;

/**
 * The Narrative Replace Authorizer checks to see if the user has 
 * the necessary permission to replace a specific narrative.  Narrative
 * attachments can be replaced when they are in workflow.  Once a proposal
 * has entered workflow, narratives cannot be added or deleted, but 
 * replacement is allowed.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class NarrativeReplaceAuthorizer extends NarrativeAuthorizer {

    public boolean isAuthorized(String userId, NarrativeTask task) {
        
        ProposalDevelopmentDocument doc = task.getDocument();
        Narrative narrative = task.getNarrative();
       
        boolean hasPermission = false;
        if (!doc.getDevelopmentProposal().getSubmitFlag() && hasProposalPermission(userId, doc, PermissionConstants.MODIFY_NARRATIVE)) {
            hasPermission = hasNarrativeRight(userId, narrative, NarrativeRight.MODIFY_NARRATIVE_RIGHT);
        }
        
        return hasPermission;
    }
}

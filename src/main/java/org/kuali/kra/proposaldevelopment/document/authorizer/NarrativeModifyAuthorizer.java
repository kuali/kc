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

import org.kuali.kra.authorization.Task;
import org.kuali.kra.infrastructure.NarrativeRight;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.document.authorization.NarrativeTask;

/**
 * The Narrative Modify Authorizer checks to see if the user has 
 * the necessary permission to modify a specific narrative.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class NarrativeModifyAuthorizer extends NarrativeAuthorizer {
 
    /**
     * @see org.kuali.kra.proposaldevelopment.document.authorizer.ProposalAuthorizer#isAuthorized(org.kuali.core.bo.user.UniversalUser, org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm)
     */
    public boolean isAuthorized(String username, Task task) {
        
        NarrativeTask narrativeTask = (NarrativeTask) task;
        
        ProposalDevelopmentDocument doc = narrativeTask.getProposalDevelopmentDocument();
        Narrative narrative = narrativeTask.getNarrative();
       
        // First, the user must have the MODIFY_NARRATIVE permission.  This is really
        // a sanity check.  If they have the MODIFY_NARRATIVE_RIGHT, then they are
        // required to have the MODIFY_NARRATIVE permission.
        
        boolean hasPermission = false;
        if (!kraWorkflowService.isInWorkflow(doc) && !doc.getSubmitFlag()) {
            if (hasProposalPermission(username, doc, PermissionConstants.MODIFY_NARRATIVE)) {
                hasPermission = hasNarrativeRight(username, narrative, NarrativeRight.MODIFY_NARRATIVE_RIGHT);
            }
        }
        return hasPermission;
    }
}

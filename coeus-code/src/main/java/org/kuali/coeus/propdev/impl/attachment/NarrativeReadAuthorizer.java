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
import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;

/**
 * The Narrative Read Authorizer checks to see if the user has 
 * the necessary permission to read/view a specific narrative.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */ 
public class NarrativeReadAuthorizer extends NarrativeAuthorizer {

    private KcWorkflowService kraWorkflowService;

    @Override
    public boolean isAuthorized(String userId, NarrativeTask task) {
          
        ProposalDevelopmentDocument doc = task.getDocument();
        Narrative narrative = task.getNarrative();
        
        // First, the user must have the VIEW_NARRATIVE permission.  This is really
        // a sanity check.  If they have the VIEW or MODIFY_NARRATIVE_RIGHT, then they are
        // required to have the VIEW_NARRATIVE permission.
          
        boolean hasPermission = false;
        if (hasProposalPermission(userId, doc, PermissionConstants.VIEW_NARRATIVE)) {
            hasPermission = hasNarrativeRight(userId, narrative, NarrativeRight.VIEW_NARRATIVE_RIGHT)
                         || hasNarrativeRight(userId, narrative, NarrativeRight.MODIFY_NARRATIVE_RIGHT);
        }
        
        if (!hasPermission) {
            hasPermission = hasUnitPermission(userId, doc.getDevelopmentProposal().getOwnedByUnitNumber(), Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, 
                                PermissionConstants.VIEW_NARRATIVE) 
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
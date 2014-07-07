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
import org.kuali.coeus.sys.framework.workflow.KcDocumentRejectionService;
import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.infrastructure.PermissionConstants;

/**
 * The Narrative Modify Authorizer checks to see if the user has 
 * the necessary permission to modify a specific narrative.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class NarrativeModifyAuthorizer extends NarrativeAuthorizer {

    private KcWorkflowService kraWorkflowService;
    private KcDocumentRejectionService kcDocumentRejectionService;

    public boolean isAuthorized(String userId, NarrativeTask task) {
        
        ProposalDevelopmentDocument doc = task.getDocument();
        Narrative narrative = task.getNarrative();
       
        // First, the user must have the MODIFY_NARRATIVE permission.  This is really
        // a sanity check.  If they have the MODIFY_NARRATIVE_RIGHT, then they are
        // required to have the MODIFY_NARRATIVE permission.
        
        KcDocumentRejectionService documentRejectionService = getKcDocumentRejectionService();
        boolean rejectedDocument = documentRejectionService.isDocumentOnInitialNode(doc.getDocumentNumber());
        boolean hasPermission = false;
        
        boolean inWorkflow = kraWorkflowService.isInWorkflow(doc);
        
		if ((!inWorkflow || rejectedDocument) && !doc.getDevelopmentProposal().getSubmitFlag()) {
            if (hasProposalPermission(userId, doc, PermissionConstants.MODIFY_NARRATIVE)) {
                hasPermission = hasNarrativeRight(userId, narrative, NarrativeRight.MODIFY_NARRATIVE_RIGHT);
            }
        }
        return hasPermission;
    }

    public KcWorkflowService getKraWorkflowService() {
        return kraWorkflowService;
    }

    public void setKraWorkflowService(KcWorkflowService kraWorkflowService) {
        this.kraWorkflowService = kraWorkflowService;
    }
    public void setKcDocumentRejectionService (KcDocumentRejectionService kcDocumentRejectionService){
        this.kcDocumentRejectionService = kcDocumentRejectionService;
    }
    protected KcDocumentRejectionService getKcDocumentRejectionService(){
        return kcDocumentRejectionService;
    }
}

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

import org.kuali.coeus.propdev.impl.auth.task.ProposalAuthorizer;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.workflow.KcDocumentRejectionService;
import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.coeus.propdev.impl.auth.task.ProposalTask;


/**
 * The Modify Narratives Authorizer checks to see if the user has 
 * permission to modify the Narratives tab. Authorization depends upon whether
 * the user has MODIFY_NARRATIVES permission.  
 */
 
public class ModifyNarrativesAuthorizer extends ProposalAuthorizer {

    private KcWorkflowService kraWorkflowService;
    private KcDocumentRejectionService kcDocumentRejectionService;

    public boolean isAuthorized(String userId, ProposalTask task) {
        KcDocumentRejectionService documentRejectionService =getKcDocumentRejectionService();
        ProposalDevelopmentDocument doc = task.getDocument();
        boolean rejectedDocument = documentRejectionService.isDocumentOnInitialNode(doc.getDocumentNumber());
        boolean hasPermission = false;
        boolean inWorkflow = kraWorkflowService.isInWorkflow(doc);
        if ((!inWorkflow || rejectedDocument) && !doc.getDevelopmentProposal().getSubmitFlag()) {
            hasPermission = hasProposalPermission(userId, doc, PermissionConstants.MODIFY_NARRATIVE);
        } else if(inWorkflow && !rejectedDocument && !doc.getDevelopmentProposal().getSubmitFlag()) {
            if(hasProposalPermission(userId, doc, PermissionConstants.MODIFY_NARRATIVE)) {
                hasPermission = hasProposalPermission(userId, doc, PermissionConstants.MODIFY_NARRATIVE);
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
    public KcDocumentRejectionService getKcDocumentRejectionService (){
        return kcDocumentRejectionService;
    }
}

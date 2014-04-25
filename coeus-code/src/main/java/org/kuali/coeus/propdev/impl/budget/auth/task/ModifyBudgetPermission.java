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
package org.kuali.coeus.propdev.impl.budget.auth.task;

import org.kuali.coeus.propdev.impl.auth.task.ProposalAuthorizer;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.workflow.KcDocumentRejectionService;
import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.coeus.propdev.impl.auth.task.ProposalTask;


public class ModifyBudgetPermission extends ProposalAuthorizer {

    private KcWorkflowService kraWorkflowService;
    private KcDocumentRejectionService kcDocumentRejectionService;

    @Override
    public boolean isAuthorized(String userId, ProposalTask task) {
        boolean hasPermission = false;
        KcDocumentRejectionService documentRejectionService = getKcDocumentRejectionService();
        ProposalDevelopmentDocument doc = task.getDocument();
        boolean rejectedDocument = documentRejectionService.isDocumentOnInitialNode(doc.getDocumentNumber());

        hasPermission = ( (!kraWorkflowService.isInWorkflow(doc) || rejectedDocument) &&
                hasProposalPermission(userId, doc, PermissionConstants.MODIFY_BUDGET));

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



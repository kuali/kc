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
package org.kuali.coeus.propdev.impl.budget.auth.task;

import org.kuali.coeus.propdev.impl.auth.task.ProposalAuthorizer;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.workflow.KcDocumentRejectionService;
import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.coeus.propdev.impl.auth.task.ProposalTask;


/**
 * The Budget Add Authorizer checks to see if the user has 
 * the necessary permission to add a budget.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class BudgetAddAuthorizer extends ProposalAuthorizer {

    private KcWorkflowService kraWorkflowService;
    private KcDocumentRejectionService kcDocumentRejectionService;

    public boolean isAuthorized(String userId, ProposalTask task) {
        boolean hasPermission = false;
        ProposalDevelopmentDocument doc = task.getDocument();
        KcDocumentRejectionService documentRejectionService = getKcDocumentRejectionService();

        boolean rejectedDocument = documentRejectionService.isDocumentOnInitialNode(doc.getDocumentNumber());

        if ((!kraWorkflowService.isInWorkflow(doc) || rejectedDocument) && !doc.isViewOnly() && !doc.getDevelopmentProposal().getSubmitFlag() && !doc.getDevelopmentProposal().isParent()) {
            hasPermission = hasProposalPermission(userId, doc, PermissionConstants.MODIFY_BUDGET);
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

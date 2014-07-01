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

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.workflow.KcDocumentRejectionService;
import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.coeus.common.budget.framework.core.BudgetDocument;
import org.kuali.coeus.common.budget.framework.auth.task.BudgetTask;
import org.kuali.coeus.common.budget.framework.auth.task.BudgetAuthorizer;
import org.kuali.kra.infrastructure.PermissionConstants;

public class ModifyProposalBudgetRatesAuthorizer extends BudgetAuthorizer {

    private KcWorkflowService kraWorkflowService;

    public boolean isAuthorized(String userId, BudgetTask task) {
        KcDocumentRejectionService documentRejectionService = KcServiceLocator.getService(KcDocumentRejectionService.class);
        BudgetDocument budgetDocument = task.getBudgetDocument();
        ProposalDevelopmentDocument doc = (ProposalDevelopmentDocument)budgetDocument.getBudget().getBudgetParent().getDocument();
        boolean rejectedDocument = documentRejectionService.isDocumentOnInitialNode(doc.getDocumentNumber());
             
        return (!kraWorkflowService.isInWorkflow(doc) || rejectedDocument) &&
                hasParentPermission(userId, doc, PermissionConstants.MODIFY_PROPOSAL_RATES) &&!doc.getDevelopmentProposal().getSubmitFlag(); 
    }

    public KcWorkflowService getKraWorkflowService() {
        return kraWorkflowService;
    }

    public void setKraWorkflowService(KcWorkflowService kraWorkflowService) {
        this.kraWorkflowService = kraWorkflowService;
    }
}

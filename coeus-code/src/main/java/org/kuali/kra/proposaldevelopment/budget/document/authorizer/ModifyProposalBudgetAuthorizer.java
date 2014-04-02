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
package org.kuali.kra.proposaldevelopment.budget.document.authorizer;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.workflow.KcDocumentRejectionService;
import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.document.authorization.BudgetTask;
import org.kuali.kra.budget.document.authorizer.BudgetAuthorizer;
import org.kuali.kra.infrastructure.PermissionConstants;


/**
 * The Budget Modify Authorizer checks to see if the user has 
 * the necessary permission to modify a specific budget.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ModifyProposalBudgetAuthorizer extends BudgetAuthorizer {

    private KcWorkflowService kraWorkflowService;

    public boolean isAuthorized(String userId, BudgetTask task) {
        KcDocumentRejectionService documentRejectionService = KcServiceLocator.getService(KcDocumentRejectionService.class);
        BudgetDocument budgetDocument = task.getBudgetDocument();
        ProposalDevelopmentDocument doc = (ProposalDevelopmentDocument)budgetDocument.getParentDocument();
        boolean rejectedDocument = documentRejectionService.isDocumentOnInitialNode(doc.getDocumentNumber());
        
        return (!kraWorkflowService.isInWorkflow(doc) || rejectedDocument) &&
                hasParentPermission(userId, doc, PermissionConstants.MODIFY_BUDGET) &&!doc.getDevelopmentProposal().getSubmitFlag(); 
    }

    public KcWorkflowService getKraWorkflowService() {
        return kraWorkflowService;
    }

    public void setKraWorkflowService(KcWorkflowService kraWorkflowService) {
        this.kraWorkflowService = kraWorkflowService;
    }
}

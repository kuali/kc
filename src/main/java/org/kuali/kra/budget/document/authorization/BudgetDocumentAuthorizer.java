/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.budget.document.authorization;

import java.util.HashMap;
import java.util.Map;

import org.kuali.core.authorization.AuthorizationConstants;
import org.kuali.core.bo.user.UniversalUser;
import org.kuali.core.document.Document;
import org.kuali.core.document.authorization.DocumentActionFlags;
import org.kuali.core.document.authorization.TransactionalDocumentAuthorizerBase;
import org.kuali.core.workflow.service.KualiWorkflowDocument;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService;

import edu.iu.uis.eden.clientapp.WorkflowInfo;
import edu.iu.uis.eden.clientapp.vo.NetworkIdVO;
import edu.iu.uis.eden.exception.WorkflowException;

public class BudgetDocumentAuthorizer extends TransactionalDocumentAuthorizerBase {
    
    private static final String TRUE = "TRUE";
    
    /**
     * @see org.kuali.core.authorization.DocumentAuthorizer#getEditMode(org.kuali.core.document.Document,
     *      org.kuali.core.bo.user.KualiUser)
     */
    @Override
    public Map getEditMode(Document d, UniversalUser u) {
        Map editModeMap = new HashMap();
        
        ProposalAuthorizationService proposalAuthService = (ProposalAuthorizationService) KraServiceLocator.getService(ProposalAuthorizationService.class);
        
        BudgetDocument budgetDoc = (BudgetDocument) d;
        ProposalDevelopmentDocument proposalDoc = budgetDoc.getProposal();
        String username = u.getPersonUserIdentifier();
        
        if (proposalAuthService.hasPermission(username, proposalDoc, PermissionConstants.MODIFY_BUDGET)) {
            editModeMap.put(AuthorizationConstants.EditMode.FULL_ENTRY, TRUE);
            editModeMap.put("modifyBudgets", TRUE);
            editModeMap.put("viewBudgets", TRUE);
        }
        else if (proposalAuthService.hasPermission(username, proposalDoc, PermissionConstants.VIEW_BUDGET)) {
            editModeMap.put(AuthorizationConstants.EditMode.VIEW_ONLY, TRUE);
            editModeMap.put("viewBudgets", TRUE);
        }
        else if (hasWorkflowPermission(username, budgetDoc)) {
            editModeMap.put(AuthorizationConstants.EditMode.VIEW_ONLY, TRUE);
            editModeMap.put("viewBudgets", TRUE);
        } 
        else {
            editModeMap.put(AuthorizationConstants.EditMode.UNVIEWABLE, TRUE);
        }
        
        return editModeMap;
    }
    
    /**
     * Is the user in the budget's workflow?  If so, then that user has 
     * permission to access the budget even if they are not given explicit
     * permissions in KIM.
     * @param username the user's username
     * @param doc the document
     * @return true if the user is in the workflow; otherwise false
     */
    private boolean hasWorkflowPermission(String username, Document doc) {
        boolean isInWorkflow = false;
        KualiWorkflowDocument workflowDoc = doc.getDocumentHeader().getWorkflowDocument();
        if (workflowDoc != null) {
            Long routeHeaderId = workflowDoc.getRouteHeader().getRouteHeaderId();
            NetworkIdVO userId = new NetworkIdVO(username);
            WorkflowInfo info = new WorkflowInfo();
            try {
                isInWorkflow = info.isUserAuthenticatedByRouteLog(routeHeaderId, userId, false);
            }
            catch (WorkflowException e) {
            }
        }
        return isInWorkflow;
    }

    /**
     * Adds settings for transactional-document-specific flags.
     * 
     * @see org.kuali.core.document.authorization.DocumentAuthorizer#getDocumentActionFlags(Document, UniversalUser)
     */
    @Override
    public DocumentActionFlags getDocumentActionFlags(Document document, UniversalUser user) {
        DocumentActionFlags flags = super.getDocumentActionFlags(document, user);
        KualiWorkflowDocument workflowDocument = document.getDocumentHeader().getWorkflowDocument();
        boolean hasInitiateAuthorization = hasInitiateAuthorization(document, user);
        
        // Allow finalized budgets to be edited
        if (workflowDocument.stateIsFinal()) {
            flags.setCanSave(hasInitiateAuthorization);
            flags.setCanCancel(hasInitiateAuthorization);
            flags.setCanCopy(false);
        }
        
        return flags;
    }

}

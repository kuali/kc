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
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.authorization.AuthorizationConstants;
import org.kuali.core.bo.user.UniversalUser;
import org.kuali.core.document.Document;
import org.kuali.core.document.authorization.DocumentActionFlags;
import org.kuali.core.document.authorization.PessimisticLock;
import org.kuali.core.document.authorization.TransactionalDocumentAuthorizerBase;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.ObjectUtils;
import org.kuali.core.workflow.service.KualiWorkflowDocument;
import org.kuali.core.workflow.service.WorkflowDocumentService;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.budget.bo.BudgetVersionOverview;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService;
import org.kuali.rice.KNSServiceLocator;

import edu.iu.uis.eden.clientapp.WorkflowInfo;
import edu.iu.uis.eden.clientapp.vo.NetworkIdVO;
import edu.iu.uis.eden.exception.WorkflowException;

public class BudgetDocumentAuthorizer extends TransactionalDocumentAuthorizerBase {
    
    private static final String TRUE = "TRUE";
    private static final String FALSE = "FALSE";
    private static Map<String, String> entryEditModeReplacementMap = new HashMap<String, String>();
    
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
            if (isRouted(proposalDoc)) {
                editModeMap.put(AuthorizationConstants.EditMode.VIEW_ONLY, TRUE);
                editModeMap.put("modifyBudgets", FALSE);
                editModeMap.put("viewBudgets", TRUE);
                entryEditModeReplacementMap.put(KraAuthorizationConstants.BudgetEditMode.MODIFY_BUDGET, KraAuthorizationConstants.BudgetEditMode.VIEW_BUDGET);
            } else if (isBudgetComplete(proposalDoc, budgetDoc)) {
                editModeMap.put(AuthorizationConstants.EditMode.VIEW_ONLY, TRUE);
                editModeMap.put("modifyBudgets", FALSE);
                editModeMap.put("viewBudgets", TRUE);
                entryEditModeReplacementMap.put(KraAuthorizationConstants.BudgetEditMode.MODIFY_BUDGET, KraAuthorizationConstants.BudgetEditMode.VIEW_BUDGET);
            }
            else {
                editModeMap.put(AuthorizationConstants.EditMode.FULL_ENTRY, TRUE);
                editModeMap.put("modifyBudgets", TRUE);
                editModeMap.put("viewBudgets", TRUE);
                entryEditModeReplacementMap.put(KraAuthorizationConstants.BudgetEditMode.MODIFY_BUDGET, KraAuthorizationConstants.BudgetEditMode.VIEW_BUDGET);
            }
            editModeMap.put("printProposal", hasPermission(username, proposalDoc, PermissionConstants.PRINT_PROPOSAL));
        }
        else if (proposalAuthService.hasPermission(username, proposalDoc, PermissionConstants.VIEW_BUDGET)) {
            editModeMap.put(AuthorizationConstants.EditMode.VIEW_ONLY, TRUE);
            editModeMap.put("viewBudgets", TRUE);
            editModeMap.put("printProposal", hasPermission(username, proposalDoc, PermissionConstants.PRINT_PROPOSAL));
        }
        else if (hasWorkflowPermission(username, budgetDoc)) {
            editModeMap.put(AuthorizationConstants.EditMode.VIEW_ONLY, TRUE);
            editModeMap.put("viewBudgets", TRUE);
            editModeMap.put("printProposal", hasPermission(username, proposalDoc, PermissionConstants.PRINT_PROPOSAL));
        } 
        else {
            editModeMap.put(AuthorizationConstants.EditMode.UNVIEWABLE, TRUE);
        }
        
        return editModeMap;
    }
    
    /**
     * Does the user have the given permission for the given proposal?
     * @param username the user's username
     * @param doc the proposal development document
     * @param permissionName the name of the permission
     * @return "TRUE" if has permission; otherwise "FALSE"
     */
    private String hasPermission(String username, ProposalDevelopmentDocument doc, String permissionName) {
        ProposalAuthorizationService proposalAuthService = (ProposalAuthorizationService) KraServiceLocator.getService(ProposalAuthorizationService.class);
        return proposalAuthService.hasPermission(username, doc, permissionName) ? TRUE : FALSE;
    }
    
    /**
     * Has the document been routed to the workflow system?
     * @param doc the document
     * @return true if routed; otherwise false
     */
    private boolean isRouted(Document doc) {
        KualiWorkflowDocument workflowDocument = GlobalVariables.getUserSession().getWorkflowDocument(doc.getDocumentNumber());
        if (workflowDocument == null) {
            return false;
        }
        else {
            String status = workflowDocument.getStatusDisplayValue();
            return !(StringUtils.equals("INITIATED", status) ||  StringUtils.equals("SAVED", status));
    	}
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
        flags.setCanRoute(hasInitiateAuthorization);
        
        // Allow finalized budgets to be edited
        if (workflowDocument.stateIsFinal()) {
            flags.setCanSave(hasInitiateAuthorization);
            flags.setCanCancel(hasInitiateAuthorization);
            flags.setCanCopy(false);
        }
        
        return flags;
    }

    @Override
    protected boolean isLockRequiredByUser(Document document, Map editMode, UniversalUser user) {
        String activeLockRegion = (String) GlobalVariables.getUserSession().retrieveObject(KraAuthorizationConstants.ACTIVE_LOCK_REGION);
        
        // check for entry edit mode
        for (Iterator iterator = editMode.entrySet().iterator(); iterator.hasNext();) {
            Map.Entry entry = (Map.Entry) iterator.next();
            if (isEntryEditMode(entry) && StringUtils.isNotEmpty(activeLockRegion)) {
                return true;  
            }
        }
        return false;
    }

    @Override
    protected boolean useCustomLockDescriptors() {
        return true;
    }

    @Override
    protected String getCustomLockDescriptor(Document document, Map editMode, UniversalUser user) {
        String activeLockRegion = (String) GlobalVariables.getUserSession().retrieveObject(KraAuthorizationConstants.ACTIVE_LOCK_REGION);
        if(StringUtils.isNotEmpty(activeLockRegion)) {
            ProposalDevelopmentDocument parent = ((BudgetDocument) document).getProposal();
            if(parent != null) {
                return parent.getDocumentNumber()+"-"+activeLockRegion; 
            }
            return document.getDocumentNumber()+"-"+activeLockRegion;
        }
        
        return null;
    }

    @Override
    protected boolean isEntryEditMode(Map.Entry entry) {
        if (AuthorizationConstants.EditMode.FULL_ENTRY.equals(entry.getKey())
                || KraAuthorizationConstants.BudgetEditMode.MODIFY_BUDGET.equals(entry.getKey())
                ) {
            String fullEntryEditModeValue = (String)entry.getValue();
            return ( (ObjectUtils.isNotNull(fullEntryEditModeValue)) && (EDIT_MODE_DEFAULT_TRUE_VALUE.equals(fullEntryEditModeValue)) );
        }
        return false;
    }
    
    @Override
    protected Map getEntryEditModeReplacementMode(Map.Entry entry) {
        Map editMode = new HashMap(); 
        editMode.put(entryEditModeReplacementMap.get(entry.getKey()), EDIT_MODE_DEFAULT_TRUE_VALUE); 
        return editMode;
    }

    @Override
    public boolean hasPreRouteEditAuthorization(Document document, UniversalUser user) {
        BudgetDocument budgetDocument = (BudgetDocument) document;
        
        if(super.hasPreRouteEditAuthorization(document, user)) {
            return true;
        } else {
            for (Iterator iterator = budgetDocument.getProposal().getPessimisticLocks().iterator(); iterator.hasNext();) {
                PessimisticLock lock = (PessimisticLock) iterator.next();
                if (lock.getLockDescriptor().endsWith(KraAuthorizationConstants.LOCK_DESCRIPTOR_BUDGET) && lock.isOwnedByUser(user)) {
                    return true;
                }
            }
        } 
        
        return false;
    }

    protected PessimisticLock createNewPessimisticLock(Document document, Map editMode, UniversalUser user) {
        BudgetDocument budgetDocument = (BudgetDocument) document;
        if (useCustomLockDescriptors()) {
            //String lockDescriptor = getCustomLockDescriptor(budgetDocument.getProposal(), editMode, user);
            String lockDescriptor = getCustomLockDescriptor(budgetDocument, editMode, user);
            PessimisticLock budgetLockForProposal = KNSServiceLocator.getPessimisticLockService().generateNewLock(budgetDocument.getProposal().getDocumentNumber(), lockDescriptor, user);
            budgetDocument.getProposal().addPessimisticLock(budgetLockForProposal);
            return KNSServiceLocator.getPessimisticLockService().generateNewLock(document.getDocumentNumber(), lockDescriptor, user);
        } else {
            return KNSServiceLocator.getPessimisticLockService().generateNewLock(document.getDocumentNumber(), user);
        }  
    }
    
    protected boolean isBudgetComplete(ProposalDevelopmentDocument proposalDoc, BudgetDocument budgetDocument) {
        if (!proposalDoc.isProposalComplete()) {
            return false;
        }
        for (BudgetVersionOverview budgetVersion: proposalDoc.getBudgetVersionOverviews()) {
            if (budgetVersion.isFinalVersionFlag() && budgetVersion.getBudgetVersionNumber().equals(budgetDocument.getBudgetVersionNumber())) {
                return true;
            }
        }
        return false;
    }

}

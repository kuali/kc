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
package org.kuali.kra.budget.document.authorization;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.budget.bo.BudgetVersionOverview;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.document.authorization.ProposalTask;
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.document.authorization.PessimisticLock;
import org.kuali.rice.kns.document.authorization.TransactionalDocumentAuthorizerBase;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.ObjectUtils;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;

public class BudgetDocumentAuthorizer extends TransactionalDocumentAuthorizerBase {
    
    private static final String TRUE = "TRUE";
    private static final String FALSE = "FALSE";
    private static Map<String, String> entryEditModeReplacementMap = new HashMap<String, String>();
    
    public BudgetDocumentAuthorizer() {
        entryEditModeReplacementMap.put(KraAuthorizationConstants.BudgetEditMode.MODIFY_BUDGET, KraAuthorizationConstants.BudgetEditMode.VIEW_BUDGET);
    }
    
    /**
     * @see org.kuali.rice.kns.authorization.DocumentAuthorizer#getEditMode(org.kuali.rice.kns.document.Document,
     *      org.kuali.rice.kns.bo.user.KualiUser)
     */
    public Map getEditMode(Document d, UniversalUser u) {
        Map editModeMap = new HashMap();
          
        BudgetDocument budgetDoc = (BudgetDocument) d;
        ProposalDevelopmentDocument proposalDoc = budgetDoc.getProposal();
        String username = u.getPersonUserIdentifier();
        
        if (canExecuteBudgetTask(username, budgetDoc, TaskName.MODIFY_BUDGET)) {
            editModeMap.put(AuthorizationConstants.EditMode.FULL_ENTRY, TRUE);
            editModeMap.put("modifyBudgets", TRUE);
            editModeMap.put("viewBudgets", TRUE);
            setPermissions(username, proposalDoc, editModeMap);
        }
        else if (canExecuteBudgetTask(username, budgetDoc, TaskName.VIEW_BUDGET)) {
            editModeMap.put(AuthorizationConstants.EditMode.VIEW_ONLY, TRUE);
            editModeMap.put("modifyBudgets", FALSE);
            editModeMap.put("viewBudgets", TRUE);
            setPermissions(username, proposalDoc, editModeMap);
        }
        else {
            editModeMap.put(AuthorizationConstants.EditMode.UNVIEWABLE, TRUE);
        }
        
        if(isBudgetComplete(proposalDoc, budgetDoc)) {
            editModeMap.put("modifyCompletedBudgets", TRUE);
            editModeMap.put("modifyBudgets", FALSE);
            editModeMap.put("addBudget", FALSE);
            entryEditModeReplacementMap.put(KraAuthorizationConstants.BudgetEditMode.MODIFY_BUDGET, KraAuthorizationConstants.BudgetEditMode.VIEW_BUDGET);

            //Looks like addBudget is needed in EditModeMap at all times
            //entryEditModeReplacementMap.put("addBudget", "openBudgets");
        }
        
        return editModeMap;
    }
    
    /**
     * Set the permissions to be used during the creation of the web pages.  
     * The JSP files can access the editModeMap (editingMode) to determine what
     * to display to the user.  For example, a JSP file may contain the following:
     * 
     *     <kra:section permission="modifyProposal">
     *         .
     *         .
     *         .
     *     </kra:section>
     * 
     * In the above example, the contents are only rendered if the user is allowed
     * to modify the proposal.  Note that permissions are always signified as 
     * either TRUE or FALSE.
     * 
     * @param username the user's unique username
     * @param doc the Proposal Development Document
     * @param editModeMap the edit mode map
     */
    private void setPermissions(String username, ProposalDevelopmentDocument doc, Map editModeMap) {
        editModeMap.put("addBudget", canExecuteTask(username, doc, TaskName.ADD_BUDGET));
        editModeMap.put("openBudgets", canExecuteTask(username, doc, TaskName.OPEN_BUDGETS));
        editModeMap.put("printProposal", canExecuteTask(username, doc, TaskName.PRINT_PROPOSAL));
        
        entryEditModeReplacementMap.put(KraAuthorizationConstants.BudgetEditMode.MODIFY_BUDGET, KraAuthorizationConstants.BudgetEditMode.VIEW_BUDGET);
        //Looks like addBudget is needed in EditModeMap at all times
        //entryEditModeReplacementMap.put("addBudget", "openBudgets");
     } 
    
    /**
     * Can the user execute the given task?
     * @param username the user's username
     * @param doc the proposal development document
     * @param taskName the name of the task
     * @return "TRUE" if has permission; otherwise "FALSE"
     */
    private String canExecuteTask(String username, ProposalDevelopmentDocument doc, String taskName) {
        return canExecuteProposalTask(username, doc, taskName) ? TRUE : FALSE;
    }
    
    /**
     * Can the user execute the given proposal task?
     * @param username the user's username
     * @param doc the proposal development document
     * @param taskName the name of the task
     * @return true if has permission; otherwise false
     */
    private boolean canExecuteProposalTask(String username, ProposalDevelopmentDocument doc, String taskName) {
        ProposalTask task = new ProposalTask(taskName, doc);       
        TaskAuthorizationService taskAuthenticationService = KraServiceLocator.getService(TaskAuthorizationService.class);
        return taskAuthenticationService.isAuthorized(username, task);
    }

    
    /**
     * Can the user execute the given budget task?
     * @param username the user's username
     * @param doc the proposal development document
     * @param budgetDocument the budget document
     * @param taskName the name of the task
     * @return true if has permission; otherwise false
     */
    private boolean canExecuteBudgetTask(String username, BudgetDocument budgetDocument, String taskName) {
        BudgetTask task = new BudgetTask(taskName, budgetDocument);       
        TaskAuthorizationService taskAuthenticationService = KraServiceLocator.getService(TaskAuthorizationService.class);
        return taskAuthenticationService.isAuthorized(username, task);
    }
    
    /**
     * @see org.kuali.rice.kns.document.authorization.DocumentAuthorizerBase#hasInitiateAuthorization(org.kuali.rice.kns.document.Document, org.kuali.rice.kns.bo.user.UniversalUser)
     */
    // TODO Take Person instead of UniversalUser
    public boolean hasInitiateAuthorization(Document document, UniversalUser user) {

        BudgetDocument budgetDoc = (BudgetDocument) document;
        String username = user.getPersonUserIdentifier();
        
        return canExecuteBudgetTask(username, budgetDoc, TaskName.MODIFY_BUDGET);
    }
    
    /**
     * Adds settings for transactional-document-specific flags.
     * 
     * @see org.kuali.rice.kns.document.authorization.DocumentAuthorizer#getDocumentActionFlags(Document, UniversalUser)
     */
    @Override
    public Set<String> getDocumentActions(Document document, Person user, Set<String> documentActions) {
        super.getDocumentActions(document, user, documentActions);
        KualiWorkflowDocument workflowDocument = document.getDocumentHeader().getWorkflowDocument();
        boolean hasInitiateAuthorization = hasInitiateAuthorization(document, new UniversalUser(user));
        documentActions.add(KNSConstants.KUALI_ACTION_CAN_ROUTE);
        
        // Allow finalized budgets to be edited
        if (workflowDocument.stateIsFinal()) {
            if (hasInitiateAuthorization) {
                documentActions.add(KNSConstants.KUALI_ACTION_CAN_SAVE);
                documentActions.add(KNSConstants.KUALI_ACTION_CAN_CANCEL);
                documentActions.add(KNSConstants.KUALI_ACTION_CAN_RELOAD);
            }
            documentActions.remove(KNSConstants.KUALI_ACTION_CAN_COPY);
        }
        
        return documentActions;
    }

//  @Override
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

//  @Override
    protected boolean useCustomLockDescriptors() {
        return true;
    }

    //@Override
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

    //@Override
    protected boolean isEntryEditMode(Map.Entry entry) {
        if (AuthorizationConstants.EditMode.FULL_ENTRY.equals(entry.getKey())
                || KraAuthorizationConstants.BudgetEditMode.MODIFY_BUDGET.equals(entry.getKey())
                 || "addBudget".equals(entry.getKey())
                ) {
            String fullEntryEditModeValue = (String)entry.getValue();
            return ( (ObjectUtils.isNotNull(fullEntryEditModeValue)) && (EDIT_MODE_DEFAULT_TRUE_VALUE.equals(fullEntryEditModeValue)) );
        }
        return false;
    }
    
    //@Override
    protected Map getEditModeWithEditableModesRemoved(Map currentEditMode) {
        //Map editModeMap = super.getEditModeWithEditableModesRemoved(currentEditMode);
        Map editModeMap = new HashMap();
        for (Iterator iterator = editModeMap.entrySet().iterator(); iterator.hasNext();) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
            if (StringUtils.equals(entry.getKey(), "addBudget")) {
                entry.setValue(FALSE);
            }
        }
        return editModeMap;
    }
        
    
    //@Override
    protected Map getEntryEditModeReplacementMode(Map.Entry entry) {
        Map editMode = new HashMap(); 
        editMode.put(entryEditModeReplacementMap.get(entry.getKey()), EDIT_MODE_DEFAULT_TRUE_VALUE); 
        return editMode;
    }

    //@Override
    public boolean hasPreRouteEditAuthorization(Document document, UniversalUser user) {
        BudgetDocument budgetDocument = (BudgetDocument) document;
        
//        if(super.hasPreRouteEditAuthorization(document, user)) {
//            return true;
//        } else {
            for (Iterator iterator = budgetDocument.getProposal().getPessimisticLocks().iterator(); iterator.hasNext();) {
                PessimisticLock lock = (PessimisticLock) iterator.next();
                if (lock.getLockDescriptor().endsWith(KraAuthorizationConstants.LOCK_DESCRIPTOR_BUDGET) && lock.isOwnedByUser(user)) {
                    return true;
                }
            }
       // } 
        
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

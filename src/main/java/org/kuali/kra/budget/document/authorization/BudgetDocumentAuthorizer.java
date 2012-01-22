/*
 * Copyright 2005-2010 The Kuali Foundation
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

import java.util.HashSet;
import java.util.Set;

import org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase;
import org.kuali.kra.authorization.Task;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.document.BudgetParentDocument;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskGroupName;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.DocumentService;

/**
 * This class is the Budget Document Authorizer.  It determines the edit modes and
 * document actions for all budget documents.
 */
public class BudgetDocumentAuthorizer extends KcTransactionalDocumentAuthorizerBase {
    
    /**
     * @see org.kuali.rice.kns.document.authorization.TransactionalDocumentAuthorizer#getEditModes(org.kuali.rice.krad.document.Document, org.kuali.rice.kim.api.identity.Person, java.util.Set)
     */
    public Set<String> getEditModes(Document document, Person user, Set<String> currentEditModes) {
        Set<String> editModes = new HashSet<String>();
                 
        BudgetDocument budgetDoc = (BudgetDocument) document;
        BudgetParentDocument parentDocument = budgetDoc.getParentDocument();
        String userId = user.getPrincipalId(); 
        
        if (canExecuteBudgetTask(userId, budgetDoc, TaskName.MODIFY_BUDGET)) {
            editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
            editModes.add("modifyBudgets");
            editModes.add("viewBudgets");
            if (canExecuteBudgetTask(userId, budgetDoc, TaskName.MODIFY_PROPOSAL_RATE)) {
                editModes.add("modifyProposalBudgetRates");
            }
            setPermissions(userId, parentDocument, editModes);
        }
        else if (canExecuteBudgetTask(userId, budgetDoc, TaskName.VIEW_BUDGET)) {
            editModes.add(AuthorizationConstants.EditMode.VIEW_ONLY);
            editModes.add("viewBudgets");
            setPermissions(userId, parentDocument, editModes);
        }
        else {
            editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
        }
        
        if (isBudgetComplete(parentDocument, budgetDoc)) {
            editModes.remove("modifyBudgets");
            editModes.remove("addBudget");
            if (editModes.contains("modifyBudgets")) {
                editModes.add("modifyCompletedBudgets");
            }
        }
        
        return editModes;
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
    protected void setPermissions(String userId, BudgetParentDocument doc, Set<String> editModes) {
        if (canExecuteParentDocumentTask(userId, doc, TaskName.ADD_BUDGET)) {
            editModes.add("addBudget");
        }
        
        if (canExecuteParentDocumentTask(userId, doc, TaskName.OPEN_BUDGETS)) {
            editModes.add("openBudgets");
        }
        
        if (canExecuteParentDocumentTask(userId, doc, TaskName.MODIFY_BUDGET)) {
            editModes.add("modifyProposalBudget");
        }
        
        if (canExecuteParentDocumentTask(userId, doc, TaskName.PRINT_PROPOSAL)) {
            editModes.add("printProposal");
        }
    }
    
    /**
     * Can the user execute the given proposal task?
     * @param username the user's username
     * @param doc the proposal development document
     * @param taskName the name of the task
     * @return true if has permission; otherwise false
     */
    private boolean canExecuteParentDocumentTask(String userId, BudgetParentDocument doc, String taskName) {
        Task task = doc.getParentAuthZTask(taskName);       
        TaskAuthorizationService taskAuthenticationService = KraServiceLocator.getService(TaskAuthorizationService.class);
        return taskAuthenticationService.isAuthorized(userId, task);
    }

    /**
     * Can the user execute the given budget task?
     * @param username the user's username
     * @param doc the proposal development document
     * @param budgetDocument the budget document
     * @param taskName the name of the task
     * @return true if has permission; otherwise false
     */
    private boolean canExecuteBudgetTask(String userId, BudgetDocument budgetDocument, String taskName) {
        //reloads the parent using the doc service if the workflow document is null
        //this is needed as some budget tasks must check the workflow doc for perms
        reloadParentIfNoWorkflow(budgetDocument);
        String taskGroupName = getTaskGroupName();
        Task task = createNewBudgetTask(taskGroupName,taskName, budgetDocument);       
        TaskAuthorizationService taskAuthenticationService = KraServiceLocator.getService(TaskAuthorizationService.class); 
        return taskAuthenticationService.isAuthorized(userId, task);
    }
    
    protected Task createNewBudgetTask(String taskGroupName, String taskName, BudgetDocument budgetDocument) {
        return new BudgetTask(taskGroupName,taskName, budgetDocument);
    }

    protected String getTaskGroupName() {
        return TaskGroupName.PROPOSAL_BUDGET;
    }

    /**
     * @see org.kuali.rice.kns.document.authorization.DocumentAuthorizer#canInitiate(java.lang.String, org.kuali.rice.kim.api.identity.Person)
     */
    public boolean canInitiate(String documentTypeName, Person user) {
        return true;
    }
    
    /**
     * @see org.kuali.rice.kns.document.authorization.DocumentAuthorizer#canOpen(org.kuali.rice.krad.document.Document, org.kuali.rice.kim.api.identity.Person)
     */
    public boolean canOpen(Document document, Person user) {
        BudgetDocument budgetDocument = (BudgetDocument) document;
        return canExecuteBudgetTask(user.getPrincipalId(), budgetDocument, TaskName.VIEW_BUDGET);
    }
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canEdit(org.kuali.rice.krad.document.Document, org.kuali.rice.kim.api.identity.Person)
     */
    @Override
    public boolean canEdit(Document document, Person user) {
        return canExecuteBudgetTask(user.getPrincipalId(), (BudgetDocument) document, TaskName.MODIFY_BUDGET);
    }
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canSave(org.kuali.rice.krad.document.Document, org.kuali.rice.kim.api.identity.Person)
     */
    @Override
    public boolean canSave(Document document, Person user) {
        return canEdit(document, user);
    }
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canCancel(org.kuali.rice.krad.document.Document, org.kuali.rice.kim.api.identity.Person)
     */
    @Override
    public boolean canCancel(Document document, Person user) {
        //KRACOEUS-3057 THE CANCEL BUTTON SHOULD ALWAYS BE DISABLED.
        return false; 
    }
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canReload(org.kuali.rice.krad.document.Document, org.kuali.rice.kim.api.identity.Person)
     */
    @Override
    public boolean canReload(Document document, Person user) {
        return canEdit(document, user);
    }
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canRoute(org.kuali.rice.krad.document.Document, org.kuali.rice.kim.api.identity.Person)
     */
    @Override
    public boolean canRoute(Document document, Person user) {
        return true;
    }
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canCopy(org.kuali.rice.krad.document.Document, org.kuali.rice.kim.api.identity.Person)
     */
    @Override
    public boolean canCopy(Document document, Person user) {
        return false;
    }
    
    /**
     * Is the Budget in the final state?
     * @param parentDocument
     * @param budgetDocument
     * @return
     */
    private boolean isBudgetComplete(BudgetParentDocument parentDocument, BudgetDocument budgetDocument) {
        if (!parentDocument.isComplete()) {
            return false;
        }
        for (BudgetDocumentVersion budgetVersion: parentDocument.getBudgetDocumentVersions()) {
            if (budgetVersion.getBudgetVersionOverview().isFinalVersionFlag() && budgetVersion.getBudgetVersionOverview().getBudgetVersionNumber().equals(budgetDocument.getBudget().getBudgetVersionNumber())) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 
     * Checks to see if the parent document has a valid workflow document(loaded from rice, not ojb)
     * as some authorizers must check the parent docs workflow permssion.
     * @param budgetDocument
     */
    @SuppressWarnings("unchecked")
    private void reloadParentIfNoWorkflow(BudgetDocument budgetDocument) {
        BudgetParentDocument parentDoc = budgetDocument.getParentDocument();
        WorkflowDocument workflowDocument = getWorkflowDocument(parentDoc);
        if (workflowDocument == null) {
            try {
                parentDoc = 
                    (BudgetParentDocument) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(parentDoc.getDocumentNumber());
                if (parentDoc != null) {
                    budgetDocument.setParentDocument(parentDoc);
                }
            } catch (WorkflowException e) { 
                // we can't easily report or handle the error here and
                // if we can't load the parent document there are bigger problems
                // and it will be reported later
            }
        }
    } 
    
    private WorkflowDocument getWorkflowDocument(Document doc) {
        WorkflowDocument workflowDocument = null;
        if (doc != null) {
            DocumentHeader header = doc.getDocumentHeader();
            if (header != null) {
                try {
                    workflowDocument = header.getWorkflowDocument();
                } 
                catch (RuntimeException ex) {
                    // do nothing; there is no workflow document
                }
            }
        }     
        return workflowDocument;
    }

    @Override
    public boolean canSendNoteFyi(Document document, Person user) {
        return false;
    }
    
    @Override
    public boolean canFyi(Document document, Person user) {
        return false;
    }

    
}

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
package org.kuali.coeus.common.budget.framework.core;

import org.kuali.coeus.common.budget.framework.auth.task.BudgetTask;
import org.kuali.coeus.common.framework.auth.KcTransactionalDocumentAuthorizerBase;
import org.kuali.coeus.common.framework.auth.task.Task;
import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.budget.framework.version.BudgetDocumentVersion;
import org.kuali.kra.infrastructure.TaskGroupName;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.document.Document;

import java.util.HashSet;
import java.util.Set;

/**
 * This class is the Budget Document Authorizer.  It determines the edit modes and
 * document actions for all budget documents.
 */
public class BudgetDocumentAuthorizer extends KcTransactionalDocumentAuthorizerBase {
    
    @Override
    public Set<String> getEditModes(Document document, Person user, Set<String> currentEditModes) {
        Set<String> editModes = new HashSet<String>();
                 
        BudgetDocument budgetDoc = (BudgetDocument) document;
        BudgetParentDocument parentDocument = budgetDoc.getBudget().getBudgetParent().getDocument();
        String userId = user.getPrincipalId(); 
        
        if (canExecuteBudgetTask(userId, budgetDoc, TaskName.VIEW_SALARIES )) {
            editModes.add(TaskName.VIEW_SALARIES); 
            setPermissions(userId, parentDocument, editModes);
        }
      
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
        
        if (canExecuteBudgetTask(userId, budgetDoc, TaskName.MAINTAIN_PROPOSAL_HIERARCHY)) {
            editModes.add("maintainProposalHierarchy");
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
        TaskAuthorizationService taskAuthenticationService = KcServiceLocator.getService(TaskAuthorizationService.class);
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
        
        String taskGroupName = getTaskGroupName();
        Task task = createNewBudgetTask(taskGroupName,taskName, budgetDocument);       
        TaskAuthorizationService taskAuthenticationService = KcServiceLocator.getService(TaskAuthorizationService.class);
        return taskAuthenticationService.isAuthorized(userId, task);
    }
    
    protected Task createNewBudgetTask(String taskGroupName, String taskName, BudgetDocument budgetDocument) {
        return new BudgetTask(taskGroupName,taskName, budgetDocument);
    }

    protected String getTaskGroupName() {
        return TaskGroupName.PROPOSAL_BUDGET;
    }

    @Override
    public boolean canInitiate(String documentTypeName, Person user) {
        return true;
    }
    
    @Override
    public boolean canOpen(Document document, Person user) {
        BudgetDocument budgetDocument = (BudgetDocument) document;
        return canExecuteBudgetTask(user.getPrincipalId(), budgetDocument, TaskName.VIEW_BUDGET);
    }
    
    @Override
    public boolean canEdit(Document document, Person user) {
        return canExecuteBudgetTask(user.getPrincipalId(), (BudgetDocument) document, TaskName.MODIFY_BUDGET);
    }
    
    @Override
    public boolean canSave(Document document, Person user) {
        return canEdit(document, user);
    }
    
    @Override
    public boolean canCancel(Document document, Person user) {
        //KRACOEUS-3057 THE CANCEL BUTTON SHOULD ALWAYS BE DISABLED.
        return false; 
    }
    
    @Override
    public boolean canReload(Document document, Person user) {
        return canEdit(document, user);
    }
    
    @Override
    public boolean canRoute(Document document, Person user) {
        return true;
    }
    
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

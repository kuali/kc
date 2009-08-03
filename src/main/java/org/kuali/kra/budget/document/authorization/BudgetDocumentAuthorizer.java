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

import java.util.HashSet;
import java.util.Set;

import org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase;
import org.kuali.kra.budget.bo.BudgetVersionOverview;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.document.authorization.ProposalTask;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.kns.document.Document;

/**
 * This class is the Budget Document Authorizer.  It determines the edit modes and
 * document actions for all budget documents.
 */
public class BudgetDocumentAuthorizer extends KcTransactionalDocumentAuthorizerBase {
    
    /**
     * @see org.kuali.rice.kns.document.authorization.TransactionalDocumentAuthorizer#getEditModes(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person, java.util.Set)
     */
    public Set<String> getEditModes(Document document, Person user, Set<String> currentEditModes) {
        Set<String> editModes = new HashSet<String>();
         
        BudgetDocument budgetDoc = (BudgetDocument) document;
        ProposalDevelopmentDocument proposalDoc = budgetDoc.getProposal();
        String username = user.getPrincipalName();
        
        if (canExecuteBudgetTask(username, budgetDoc, TaskName.MODIFY_BUDGET)) {
            editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
            editModes.add("modifyBudgets");
            editModes.add("viewBudgets");
            setPermissions(username, proposalDoc, editModes);
        }
        else if (canExecuteBudgetTask(username, budgetDoc, TaskName.VIEW_BUDGET)) {
            editModes.add(AuthorizationConstants.EditMode.VIEW_ONLY);
            editModes.add("viewBudgets");
            setPermissions(username, proposalDoc, editModes);
        }
        else {
            editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
        }
        
        if (isBudgetComplete(proposalDoc, budgetDoc)) {
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
    private void setPermissions(String username, ProposalDevelopmentDocument doc, Set<String> editModes) {
        if (canExecuteProposalTask(username, doc, TaskName.ADD_BUDGET)) {
            editModes.add("addBudget");
        }
        
        if (canExecuteProposalTask(username, doc, TaskName.OPEN_BUDGETS)) {
            editModes.add("openBudgets");
        }
        
        if (canExecuteProposalTask(username, doc, TaskName.MODIFY_BUDGET)) {
            editModes.add("modifyProposalBudget");
        }
        
        if (canExecuteProposalTask(username, doc, TaskName.PRINT_PROPOSAL)) {
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
     * @see org.kuali.rice.kns.document.authorization.DocumentAuthorizer#canInitiate(java.lang.String, org.kuali.rice.kim.bo.Person)
     */
    public boolean canInitiate(String documentTypeName, Person user) {
        return true;
    }
    
    /**
     * @see org.kuali.rice.kns.document.authorization.DocumentAuthorizer#canOpen(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
     */
    public boolean canOpen(Document document, Person user) {
        BudgetDocument budgetDocument = (BudgetDocument) document;
        return canExecuteBudgetTask(user.getPrincipalName(), budgetDocument, TaskName.VIEW_BUDGET);
    }
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canEdit(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
     */
    @Override
    public boolean canEdit(Document document, Person user) {
        return canExecuteBudgetTask(user.getPrincipalName(), (BudgetDocument) document, TaskName.MODIFY_BUDGET);
    }
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canSave(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
     */
    @Override
    protected boolean canSave(Document document, Person user) {
        return canEdit(document, user);
    }
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canCancel(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
     */
    @Override
    protected boolean canCancel(Document document, Person user) {
        return canEdit(document, user);
    }
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canReload(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
     */
    @Override
    protected boolean canReload(Document document, Person user) {
        return canEdit(document, user);
    }
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canRoute(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
     */
    @Override
    protected boolean canRoute(Document document, Person user) {
        return true;
    }
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canCopy(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
     */
    @Override
    protected boolean canCopy(Document document, Person user) {
        return false;
    }
    
    /**
     * Is the Budget in the final state?
     * @param proposalDoc
     * @param budgetDocument
     * @return
     */
    private boolean isBudgetComplete(ProposalDevelopmentDocument proposalDoc, BudgetDocument budgetDocument) {
        if (!proposalDoc.getDevelopmentProposal().isProposalComplete()) {
            return false;
        }
        for (BudgetVersionOverview budgetVersion: proposalDoc.getDevelopmentProposal().getBudgetVersionOverviews()) {
            if (budgetVersion.isFinalVersionFlag() && budgetVersion.getBudgetVersionNumber().equals(budgetDocument.getBudgetVersionNumber())) {
                return true;
            }
        }
        return false;
    }
}

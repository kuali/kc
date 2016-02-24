/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.award.budget.document.authorization;

import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.coeus.common.budget.framework.core.BudgetParentDocument;
import org.kuali.coeus.common.framework.auth.KcTransactionalDocumentAuthorizerBase;
import org.kuali.coeus.common.framework.auth.task.Task;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.krad.document.Document;

import java.util.HashSet;
import java.util.Set;

public class AwardBudgetDocumentAuthorizer  extends KcTransactionalDocumentAuthorizerBase {

    private KcAuthorizationService kcAuthorizationService;

    @Override
    public Set<String> getEditModes(Document document, Person user, Set<String> currentEditModes) {
        Set<String> editModes = new HashSet<String>();
         
        AwardBudgetDocument awardBudgetDocument = (AwardBudgetDocument) document;
        BudgetParentDocument parentDocument = awardBudgetDocument.getBudget().getBudgetParent().getDocument();

        if (canExecuteAwardBudgetTask(user, awardBudgetDocument, TaskName.MODIFY_BUDGET)) {
            editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
            editModes.add("modifyBudgets");
            editModes.add("viewBudgets");
            setPermissions(user, parentDocument, editModes);
        }
        else if (canExecuteAwardBudgetTask(user, awardBudgetDocument, TaskName.VIEW_BUDGET)) {
            editModes.add(AuthorizationConstants.EditMode.VIEW_ONLY);
            editModes.add("viewBudgets");
            setPermissions(user, parentDocument, editModes);
        }
        else {
            editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
        }
        
        if (canExecuteAwardBudgetTask(user, awardBudgetDocument, TaskName.VIEW_SALARIES)) {
            editModes.add(TaskName.VIEW_SALARIES);
        }        
        
        return editModes;
    }
    
    
    public boolean canOpen(Document document, Person user) {
        return canExecuteAwardBudgetTask(user, (AwardBudgetDocument) document, TaskName.VIEW_BUDGET);
    }
    

    /**
     * Does the user have permission to execute the given task for a award?
     * @param user the user
     * @param doc the award document
     * @param taskName the name of the task
     * @return true if has permission; otherwise false
     */
    private boolean canExecuteAwardBudgetTask(Person user, AwardBudgetDocument doc, String taskName) {
        AwardBudgetTask task = new AwardBudgetTask(taskName, doc);
        return getTaskAuthorizationService().isAuthorized(user.getPrincipalId(), task);
    }
    
    @Override
    public boolean canInitiate(String documentTypeName, Person user) {
        return true;
    }

    @Override
    public boolean canEdit(Document document, Person user) {
        return canExecuteAwardBudgetTask(user, (AwardBudgetDocument) document, TaskName.MODIFY_BUDGET);
    }
    
    @Override
    public boolean canSave(Document document, Person user) {
        return canEdit(document, user);
    }
    
    @Override
    public boolean canReload(Document document, Person user) {
        WorkflowDocument workflow = document.getDocumentHeader().getWorkflowDocument();
        return canEdit(document, user) || workflow.isCanceled();
    }
    
    
    @Override
    public boolean canApprove( Document document, Person user ) {
        return super.canApprove(document, user) && canExecuteAwardBudgetTask( user, (AwardBudgetDocument)document, TaskName.APPROVE_AWARD_BUDGET);
    }
    
    @Override
    public boolean canDisapprove( Document document, Person user ) {
        return super.canDisapprove(document, user) && canExecuteAwardBudgetTask( user, (AwardBudgetDocument)document, TaskName.DISAPPROVE_AWARD_BUDGET);
    }
    
    @Override
    public boolean canRoute(Document document, Person user) {
        return canExecuteAwardBudgetTask(user, (AwardBudgetDocument) document, TaskName.SUBMIT_TO_WORKFLOW);
    }

    
    @Override
    public boolean canCancel(Document document, Person user) {
        WorkflowDocument workflow = document.getDocumentHeader().getWorkflowDocument();
        return super.canCancel(document)&&canEdit(document) && !workflow.isEnroute() ; 
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
     * @param user the user
     * @param doc the Proposal Development Document
     * @param editModes the edit mode map
     */
    protected void setPermissions(Person user, BudgetParentDocument doc, Set<String> editModes) {
        final String userId = user.getPrincipalId();

        if (canExecuteParentDocumentTask(userId, doc, TaskName.ADD_BUDGET)) {
            editModes.add("addBudget");
        }
        
        if (canExecuteParentDocumentTask(userId, doc, TaskName.OPEN_BUDGETS)) {
            editModes.add("openBudgets");
        }
        
        if (canExecuteParentDocumentTask(userId, doc, TaskName.MODIFY_BUDGET)) {
            editModes.add("modifyProposalBudget");
        }
    }
    
    /**
     * Can the user execute the given proposal task?
     * @param userId the user's unique user id
     * @param doc the proposal development document
     * @param taskName the name of the task
     * @return true if has permission; otherwise false
     */
    private boolean canExecuteParentDocumentTask(String userId, BudgetParentDocument doc, String taskName) {
        Task task = ((AwardDocument) doc).getParentAuthZTask(taskName);
        return getTaskAuthorizationService().isAuthorized(userId, task);
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

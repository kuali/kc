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
package org.kuali.kra.award.budget.document.authorization;

import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.coeus.common.budget.framework.core.BudgetParentDocument;
import org.kuali.coeus.common.budget.framework.core.BudgetDocumentAuthorizer;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.krad.document.Document;

import java.util.HashSet;
import java.util.Set;

public class AwardBudgetDocumentAuthorizer  extends BudgetDocumentAuthorizer {

    @Override
    public Set<String> getEditModes(Document document, Person user, Set<String> currentEditModes) {
        Set<String> editModes = new HashSet<String>();
         
        AwardBudgetDocument awardBudgetDocument = (AwardBudgetDocument) document;
        BudgetParentDocument parentDocument = awardBudgetDocument.getBudget().getBudgetParent().getDocument();
        String userId = user.getPrincipalId(); 
                
        if (canExecuteAwardBudgetTask(user, awardBudgetDocument, TaskName.MODIFY_BUDGET)) {
            editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
            editModes.add("modifyBudgets");
            editModes.add("viewBudgets");
            setPermissions(userId, parentDocument, editModes);
        }
        else if (canExecuteAwardBudgetTask(user, awardBudgetDocument, TaskName.VIEW_BUDGET)) {
            editModes.add(AuthorizationConstants.EditMode.VIEW_ONLY);
            editModes.add("viewBudgets");
            setPermissions(userId, parentDocument, editModes);
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
        return super.canApprove(document,user) && canExecuteAwardBudgetTask( user, (AwardBudgetDocument)document, TaskName.APPROVE_AWARD_BUDGET);
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
}

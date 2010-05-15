/*
 * Copyright 2005-2010 The Kuali Foundation
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

import java.util.HashSet;
import java.util.Set;

import org.kuali.kra.award.budget.AwardBudgetExt;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.budget.document.BudgetParentDocument;
import org.kuali.kra.budget.document.authorization.BudgetDocumentAuthorizer;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;

public class AwardBudgetDocumentAuthorizer  extends BudgetDocumentAuthorizer {

    /**
     * @see org.kuali.rice.kns.document.authorization.TransactionalDocumentAuthorizer#getEditModes(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person, java.util.Set)
     */
    public Set<String> getEditModes(Document document, Person user, Set<String> currentEditModes) {
        Set<String> editModes = new HashSet<String>();
         
        AwardBudgetDocument awardBudgetDocument = (AwardBudgetDocument) document;
        BudgetParentDocument<Award> parentDocument = awardBudgetDocument.getParentDocument();
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
        
        return editModes;
    }
    
    
    public boolean canOpen(Document document, Person user) {
        return canExecuteAwardBudgetTask(user, (AwardBudgetDocument) document, TaskName.VIEW_BUDGET);
    }
    

    /**
     * Does the user have permission to execute the given task for a award?
     * @param username the user's username
     * @param doc the award document
     * @param taskName the name of the task
     * @return true if has permission; otherwise false
     */
    private boolean canExecuteAwardBudgetTask(Person user, AwardBudgetDocument doc, String taskName) {
        AwardBudgetTask task = new AwardBudgetTask(taskName, doc);
        return getTaskAuthorizationService().isAuthorized(user.getPrincipalId(), task);
    }
    
    private boolean canCreateAwardBudget(Document document,Person user) {
        return canExecuteAwardBudgetTask(user, (AwardBudgetDocument) document, TaskName.ADD_BUDGET);
    }
    
    @Override
    public boolean canEdit(Document document, Person user) {
        return canExecuteAwardBudgetTask(user, (AwardBudgetDocument) document, TaskName.MODIFY_BUDGET);
    }
    
    @Override
    protected boolean canSave(Document document, Person user) {
        return canEdit(document, user);
    }
    
    @Override
    protected boolean canReload(Document document, Person user) {
        KualiWorkflowDocument workflow = document.getDocumentHeader().getWorkflowDocument();
        return canEdit(document, user) || workflow.stateIsCanceled();
    }
    
    
    @Override
    protected boolean canApprove( Document document, Person user ) {
        return super.canApprove(document,user) && canExecuteAwardBudgetTask( user, (AwardBudgetDocument)document, TaskName.APPROVE_AWARD_BUDGET);
    }
    
    @Override
    protected boolean canDisapprove( Document document, Person user ) {
        return super.canDisapprove(document, user) && canExecuteAwardBudgetTask( user, (AwardBudgetDocument)document, TaskName.DISAPPROVE_AWARD_BUDGET);
    }
    
    @Override
    protected boolean canRoute(Document document, Person user) {
        return canExecuteAwardBudgetTask(user, (AwardBudgetDocument) document, TaskName.SUBMIT_TO_WORKFLOW);
    }
    
    private boolean isRebudget(AwardBudgetDocument awardBudgetDocument){
        AwardBudgetExt budget = awardBudgetDocument.getAwardBudget();
        String rebudgetTypeCode = getParameterService().getParameterValue(AwardBudgetDocument.class, KeyConstants.AWARD_BUDGET_TYPE_REBUDGET);
        return budget.getAwardBudgetTypeCode().equals(rebudgetTypeCode);
    }
    
    @Override
    protected boolean canCancel(Document document, Person user) {
        KualiWorkflowDocument workflow = document.getDocumentHeader().getWorkflowDocument();
        return super.canCancel(document)&&canEdit(document) && !workflow.stateIsEnroute() ; 
    }
}

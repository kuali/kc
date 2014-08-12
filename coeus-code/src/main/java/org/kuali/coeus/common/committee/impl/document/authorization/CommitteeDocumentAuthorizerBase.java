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
package org.kuali.coeus.common.committee.impl.document.authorization;

import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.document.CommitteeDocumentBase;
import org.kuali.coeus.common.framework.auth.KcTransactionalDocumentAuthorizerBase;
import org.kuali.coeus.common.framework.auth.task.ApplicationTask;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.krad.document.Document;

import java.util.HashSet;
import java.util.Set;

/**
 * This class is the CommitteeBase Document Authorizer.  It determines the edit modes and
 * document actions for all committee documents.
 */
public abstract class CommitteeDocumentAuthorizerBase extends KcTransactionalDocumentAuthorizerBase {
       
    @Override
    public Set<String> getEditModes(Document document, Person user, Set<String> currentEditModes) {
        Set<String> editModes = new HashSet<String>();
        String userId = user.getPrincipalId();
        CommitteeDocumentBase committeeDocument = (CommitteeDocumentBase) document;
        if (committeeDocument.getCommittee().getCommitteeId() == null) {
            if (canCreateCommittee(user)) {
                editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
            } 
            else {
                editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
            }
        } 
        else {
            if (canExecuteCommitteeTask(userId, committeeDocument, TaskName.MODIFY_COMMITTEE)) {  
                editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
            }
            else if (canExecuteCommitteeTask(userId, committeeDocument, TaskName.VIEW_COMMITTEE)) {
                editModes.add(AuthorizationConstants.EditMode.VIEW_ONLY);
            }
            else {
                editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
            }
        }
        
        return editModes;
    }
    
    @Override
    public boolean canInitiate(String documentTypeName, Person user) { 
        return canCreateCommittee(user);
    }
    
    @Override
    public boolean canOpen(Document document, Person user) {
        CommitteeDocumentBase committeeDocument = (CommitteeDocumentBase) document;
        if (committeeDocument.getCommittee().getCommitteeId() == null) {
            return canCreateCommittee(user);
        }
        return canExecuteCommitteeTask(user.getPrincipalId(), (CommitteeDocumentBase) document, TaskName.VIEW_COMMITTEE);
    }
    
    @Override
    public boolean canEdit(Document document, Person user) {
        return !isFinal(document) &&
               canExecuteCommitteeTask(user.getPrincipalId(), (CommitteeDocumentBase) document, TaskName.MODIFY_COMMITTEE);
    }
    
    @Override
    public boolean canSave(Document document, Person user) {
        return canEdit(document, user);
    }
    
    @Override
    public boolean canRoute(Document document, Person user) {
        return !isFinal(document) && super.canRoute(document, user);
    }
    
    @Override
    public boolean canBlanketApprove(Document document, Person user) {
        return !isFinal(document) && super.canBlanketApprove(document, user);
    }
    
    @Override
    public boolean canCancel(Document document, Person user) {
        return !isFinal(document) && super.canCancel(document, user);
    }
    
    @Override
    public boolean canAcknowledge(Document document, Person user) {
        return false;
    }
    
    
    @Override
    public boolean canApprove(Document document, Person user) {
        return false;
    }
    
    @Override
    public boolean canDisapprove(Document document, Person user) {
        return false;
    }
    
    @Override
    public boolean canReload(Document document, Person user) {
        return isFinal(document);
    }
    
    @Override
    public boolean canCopy(Document document, Person user) {
        return false;
    }
    
    /**
     * Does the user have permission to create a committee?
     * @param user the user
     * @return true if the user can create a committee; otherwise false
     */
    private boolean canCreateCommittee(Person user) {
        ApplicationTask task = new ApplicationTask(getAddCommitteeTaskNameHook());
        return getTaskAuthorizationService().isAuthorized(user.getPrincipalId(), task);
    }
    
    protected abstract String getAddCommitteeTaskNameHook();

    /**
     * Does the user have permission to execute the given task for a committee?
     * @param username the user's username
     * @param doc the committee document
     * @param taskName the name of the task
     * @return true if has permission; otherwise false
     */
    private boolean canExecuteCommitteeTask(String userId, CommitteeDocumentBase doc, String taskName) {
        CommitteeTaskBase task = getNewCommitteeTaskInstanceHook(taskName, doc.getCommittee());       
        return getTaskAuthorizationService().isAuthorized(userId, task);
    }

    protected abstract CommitteeTaskBase getNewCommitteeTaskInstanceHook(String taskName, CommitteeBase committee);

    /*
    @Override
    public boolean canFyi(Document document, Person user) {
        return false;
    }
    */
    
    @Override
    public boolean canSendNoteFyi(Document document, Person user) {
        return false;
    }

}

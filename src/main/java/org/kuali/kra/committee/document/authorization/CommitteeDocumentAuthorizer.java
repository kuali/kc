/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.committee.document.authorization;

import java.util.HashSet;
import java.util.Set;

import org.kuali.kra.authorization.ApplicationTask;
import org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.kns.document.Document;

/**
 * This class is the Committee Document Authorizer.  It determines the edit modes and
 * document actions for all committee documents.
 */
public class CommitteeDocumentAuthorizer extends KcTransactionalDocumentAuthorizerBase {
       
    /**
     * @see org.kuali.rice.kns.document.authorization.TransactionalDocumentAuthorizer#getEditModes(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person, java.util.Set)
     */
    public Set<String> getEditModes(Document document, Person user, Set<String> currentEditModes) {
        Set<String> editModes = new HashSet<String>();
        
        String username = user.getPrincipalName();
        CommitteeDocument committeeDocument = (CommitteeDocument) document;
        if (committeeDocument.getCommittee().getCommitteeId() == null) {
            if (canCreateCommittee(user)) {
                editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
            } 
            else {
                editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
            }
        } 
        else {
            if (canExecuteCommitteeTask(username, committeeDocument, TaskName.MODIFY_COMMITTEE)) {  
                editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
            }
            else if (canExecuteCommitteeTask(username, committeeDocument, TaskName.VIEW_COMMITTEE)) {
                editModes.add(AuthorizationConstants.EditMode.VIEW_ONLY);
            }
            else {
                editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
            }
        }
        
        return editModes;
    }
    
    /**
     * @see org.kuali.rice.kns.document.authorization.DocumentAuthorizer#canInitiate(java.lang.String, org.kuali.rice.kim.bo.Person)
     */
    public boolean canInitiate(String documentTypeName, Person user) {
        return canCreateCommittee(user);
    }
    
    /**
     * @see org.kuali.rice.kns.document.authorization.DocumentAuthorizer#canOpen(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
     */
    public boolean canOpen(Document document, Person user) {
        CommitteeDocument committeeDocument = (CommitteeDocument) document;
        if (committeeDocument.getCommittee().getCommitteeId() == null) {
            return canCreateCommittee(user);
        }
        return canExecuteCommitteeTask(user.getPrincipalName(), (CommitteeDocument) document, TaskName.VIEW_COMMITTEE);
    }
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canEdit(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
     */
    @Override
    public boolean canEdit(Document document, Person user) {
        return !isFinal(document) &&
               canExecuteCommitteeTask(user.getPrincipalName(), (CommitteeDocument) document, TaskName.MODIFY_COMMITTEE);
    }
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canSave(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
     */
    @Override
    protected boolean canSave(Document document, Person user) {
        return canEdit(document, user);
    }
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canRoute(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
     */
    @Override
    protected boolean canRoute(Document document, Person user) {
        return !isFinal(document) && super.canRoute(document, user);
    }
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canBlanketApprove(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
     */
    @Override
    protected boolean canBlanketApprove(Document document, Person user) {
        return !isFinal(document) && super.canBlanketApprove(document, user);
    }
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canCancel(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
     */
    @Override
    protected boolean canCancel(Document document, Person user) {
        return !isFinal(document) && super.canCancel(document, user);
    }
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canAcknowledge(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
     */
    @Override
    protected boolean canAcknowledge(Document document, Person user) {
        return false;
    }
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canFYI(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
     */
    @Override
    protected boolean canFYI(Document document, Person user) {
        return false;
    }
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canApprove(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
     */
    @Override
    protected boolean canApprove(Document document, Person user) {
        return false;
    }
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canDisapprove(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
     */
    @Override
    protected boolean canDisapprove(Document document, Person user) {
        return false;
    }
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canReload(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
     */
    @Override
    protected boolean canReload(Document document, Person user) {
        return isFinal(document);
    }
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canCopy(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
     */
    @Override
    protected boolean canCopy(Document document, Person user) {
        return false;
    }
    
    /**
     * Is the document in the final route state?
     * @param document
     * @return true if in the final state; otherwise false
     */
    private boolean isFinal(Document document) {
        return document.getDocumentHeader().getWorkflowDocument().getRouteHeader().getDocRouteStatus().equals(KEWConstants.ROUTE_HEADER_FINAL_CD);
    }
    
    /**
     * Does the user have permission to create a committee?
     * @param user the user
     * @return true if the user can create a committee; otherwise false
     */
    private boolean canCreateCommittee(Person user) {
        String username = user.getPrincipalName();
        ApplicationTask task = new ApplicationTask(TaskName.ADD_COMMITTEE);       
        return getTaskAuthorizationService().isAuthorized(username, task);
    }
    
    /**
     * Does the user have permission to execute the given task for a committee?
     * @param username the user's username
     * @param doc the committee document
     * @param taskName the name of the task
     * @return true if has permission; otherwise false
     */
    private boolean canExecuteCommitteeTask(String username, CommitteeDocument doc, String taskName) {
        CommitteeTask task = new CommitteeTask(taskName, doc.getCommittee());       
        return getTaskAuthorizationService().isAuthorized(username, task);
    }
}

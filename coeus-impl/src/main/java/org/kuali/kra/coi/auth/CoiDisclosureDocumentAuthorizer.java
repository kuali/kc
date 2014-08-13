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
package org.kuali.kra.coi.auth;

import org.kuali.coeus.common.framework.auth.KcTransactionalDocumentAuthorizerBase;
import org.kuali.coeus.common.framework.auth.task.ApplicationTask;
import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.coi.CoiDisclosureDocument;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.util.KRADConstants;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * This class implements document authorizer for Coidisclosuredocument
 */
public class CoiDisclosureDocumentAuthorizer extends KcTransactionalDocumentAuthorizerBase {
    // TODO : detail need to be implemented after role and tasks are set up.
    
    @Override
    public Set<String> getEditModes(Document document, Person user, Set<String> currentEditModes) {
        Set<String> editModes = new HashSet<String>();
        
        CoiDisclosureDocument coiDisclosureDocument = (CoiDisclosureDocument) document;
        String userId = user.getPrincipalId();
        
        if (coiDisclosureDocument.getCoiDisclosure().getCoiDisclosureId() == null) {
            if (canCreateCoiDisclosure(user)) {
                editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
            } 
            else {
                editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
            }
            if (canApprove(userId, coiDisclosureDocument, TaskName.APPROVE_COI_DISCLOSURE)) {
                editModes.add(TaskName.APPROVE_COI_DISCLOSURE);
            }
        } 
        else {
            if (canExecuteCoiDisclosureTask(userId, coiDisclosureDocument, TaskName.MODIFY_COI_DISCLOSURE)) {  
                editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
            }
            else if (canExecuteCoiDisclosureTask(userId, coiDisclosureDocument, TaskName.VIEW_COI_DISCLOSURE)) {
                editModes.add(AuthorizationConstants.EditMode.VIEW_ONLY);
            }
            else {
                editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
            }
            if (canApprove(userId, coiDisclosureDocument, TaskName.APPROVE_COI_DISCLOSURE)) {
                editModes.add(TaskName.APPROVE_COI_DISCLOSURE);
            }
            
        }
        
        return editModes;
    }
    
    protected boolean canApprove(String userId, CoiDisclosureDocument doc, String taskName) {
        CoiDisclosureTask task = new CoiDisclosureTask(taskName, doc.getCoiDisclosure());       
        TaskAuthorizationService taskAuthenticationService = KcServiceLocator.getService(TaskAuthorizationService.class);
        return taskAuthenticationService.isAuthorized(userId, task);
    }
    
    @Override
    public boolean canInitiate(String documentTypeName, Person user) {
        return canCreateCoiDisclosure(user);
    }

    @Override
    public boolean canOpen(Document document, Person user) {
        CoiDisclosureDocument coiDisclosureDocument = (CoiDisclosureDocument) document;
        if (coiDisclosureDocument.getCoiDisclosure().getCoiDisclosureId() == null) {
            return canCreateCoiDisclosure(user);
        }
        return canExecuteCoiDisclosureTask(user.getPrincipalId(), (CoiDisclosureDocument) document, TaskName.VIEW_COI_DISCLOSURE);
    }
    
    /**
     * Does the user have permission to create a coi disclosure?
     * @param user the user
     * @return true if the user can create a coi disclosure; otherwise false
     */
    private boolean canCreateCoiDisclosure(Person user) {
        ApplicationTask task = new ApplicationTask(TaskName.CREATE_COI_DISCLOSURE);       
        TaskAuthorizationService taskAuthenticationService = KcServiceLocator.getService(TaskAuthorizationService.class);
        return taskAuthenticationService.isAuthorized(user.getPrincipalId(), task);
    }
    
    /**
     * Does the user have permission to execute the given task for a coi disclosure?
     * @param username the user's username
     * @param doc the coi disclosure document
     * @param taskName the name of the task
     * @return true if has permission; otherwise false
     */
    private boolean canExecuteCoiDisclosureTask(String userId, CoiDisclosureDocument doc, String taskName) {
        CoiDisclosureTask task = new CoiDisclosureTask(taskName, doc.getCoiDisclosure());       
        TaskAuthorizationService taskAuthenticationService = KcServiceLocator.getService(TaskAuthorizationService.class);
        return taskAuthenticationService.isAuthorized(userId, task);
    }
    
    @Override
    public boolean canEdit(Document document, Person user) {
        return canExecuteCoiDisclosureTask(user.getPrincipalId(), (CoiDisclosureDocument) document, TaskName.MODIFY_COI_DISCLOSURE)
                || canModifyAttachments((CoiDisclosureDocument)document, user) 
                || canModifyNotes((CoiDisclosureDocument)document, user)
                || canApprove(user.getPrincipalId(), (CoiDisclosureDocument) document, TaskName.APPROVE_COI_DISCLOSURE)
                && !((CoiDisclosureDocument) document).isViewOnly();
    }
    
    @Override
    public boolean canSave(Document document, Person user) {
        return canEdit(document, user);
    }
    
    protected boolean canModifyAttachments(CoiDisclosureDocument doc, Person user) {       
        return canExecuteCoiDisclosureTask(user.getPrincipalId(), doc, TaskName.MAINTAIN_COI_DISCLOSURE_ATTACHMENTS);                                                   
    }
    
    protected boolean canModifyNotes(CoiDisclosureDocument doc, Person user) {                                                                                                
        return canExecuteCoiDisclosureTask(user.getPrincipalId(), doc, TaskName.MAINTAIN_COI_DISCLOSURE_NOTES);                                                   
                                                                                          
    }
    
    @Override
    public boolean canCopy(Document document, Person user) {
        return false;
    }
    
    @Override
    public boolean canCancel(Document document, Person user) {
        return false;
    }
    
    @Override
    public boolean canRoute(Document document, Person user) {
        return false;
    }

    /*
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canReload(org.kuali.rice.krad.document.Document, org.kuali.rice.kim.api.identity.Person)
     */
    public boolean canReload(Document document, Person user) {
        CoiDisclosureDocument coiDisclosureDocument = (CoiDisclosureDocument) document;
        return ((coiDisclosureDocument.getCoiDisclosure().getCoiDisclosureId() != null) ||
                coiDisclosureDocument.getCoiDisclosure().isApprovedDisclosure());
    }
    
    /**
     * Can the user blanket approve the given document?
     * @param document the document
     * @param user the user
     * @return always false for CoiDisclosureDocument
     */
    @Override
    public boolean canBlanketApprove(Document document, Person user) {
        return false;
    }

    @Override
    public boolean canSendNoteFyi(Document document, Person user) {
        return false;
    }
    
    @Override
    public boolean canFyi(Document document, Person user) {
        return false;
    }
    
    protected Set<String> getDocumentActions(Document document, Person user) {
        Set<String> documentActions = super.getDocumentActions(document, user);
        
        if (document.getDocumentHeader().getWorkflowDocument().isEnroute()) {
            documentActions.remove(KRADConstants.KUALI_ACTION_CAN_EDIT_DOCUMENT_OVERVIEW);
        }
        
        return documentActions;
    }
    
}

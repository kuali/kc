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
package org.kuali.kra.coi.auth;

import java.util.HashSet;
import java.util.Set;

import org.kuali.kra.authorization.ApplicationTask;
import org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase;
import org.kuali.kra.coi.CoiDisclosureDocument;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.kns.document.Document;

/**
 * 
 * This class implements document authorizer for Coidisclosuredocument
 */
public class CoiDisclosureDocumentAuthorizer extends KcTransactionalDocumentAuthorizerBase {
    // TODO : detail need to be implemented after role and tasks are set up.
    
    /**
     * @see org.kuali.rice.kns.document.authorization.TransactionalDocumentAuthorizer#getEditModes(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person, java.util.Set)
     */
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
            
        }
        
        return editModes;
    }
    
    /**
     * @see org.kuali.rice.kns.document.authorization.DocumentAuthorizer#canInitiate(java.lang.String, org.kuali.rice.kim.bo.Person)
     */
    public boolean canInitiate(String documentTypeName, Person user) {
        return canCreateCoiDisclosure(user);
    }

    /**
     * @see org.kuali.rice.kns.document.authorization.DocumentAuthorizer#canOpen(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
     */
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
        // TODO : to be implemented later
//        return true;
        ApplicationTask task = new ApplicationTask(TaskName.CREATE_COI_DISCLOSURE);       
        TaskAuthorizationService taskAuthenticationService = KraServiceLocator.getService(TaskAuthorizationService.class);
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
        // TODO : to be implemented later
//        return true;
        CoiDisclosureTask task = new CoiDisclosureTask(taskName, doc.getCoiDisclosure());       
        TaskAuthorizationService taskAuthenticationService = KraServiceLocator.getService(TaskAuthorizationService.class);
        return taskAuthenticationService.isAuthorized(userId, task);
    }
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canEdit(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
     */
    @Override
    public boolean canEdit(Document document, Person user) {
        return canExecuteCoiDisclosureTask(user.getPrincipalId(), (CoiDisclosureDocument) document, TaskName.MODIFY_COI_DISCLOSURE);
    }
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canSave(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
     */
    @Override
    protected boolean canSave(Document document, Person user) {
        return canEdit(document, user);
    }
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canCopy(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
     */
    @Override
    protected boolean canCopy(Document document, Person user) {
        return false;
    }
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canCancel(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
     */
    @Override
    protected boolean canCancel(Document document, Person user) {
        return false;
    }
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canRoute(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
     */
    @Override
    protected boolean canRoute(Document document, Person user) {
        return false;
    }
    
    /**
     * Can the user blanket approve the given document?
     * @param document the document
     * @param user the user
     * @return always false for CoiDisclosureDocument
     */
    @Override
    protected boolean canBlanketApprove(Document document, Person user) {
        return false;
    }

}

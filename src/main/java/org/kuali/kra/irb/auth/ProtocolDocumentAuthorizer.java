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
package org.kuali.kra.irb.auth;

import java.util.HashSet;
import java.util.Set;

import org.kuali.kra.authorization.ApplicationTask;
import org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.kns.document.Document;

/**
 * This class is the Proposal Document Authorizer.  It determines the edit modes and
 * document actions for all proposal development documents.
 */
public class ProtocolDocumentAuthorizer extends KcTransactionalDocumentAuthorizerBase {
    
    /**
     * @see org.kuali.rice.kns.document.authorization.TransactionalDocumentAuthorizer#getEditModes(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person, java.util.Set)
     */
    public Set<String> getEditModes(Document document, Person user, Set<String> currentEditModes) {
        Set<String> editModes = new HashSet<String>();
        
        ProtocolDocument protocolDocument = (ProtocolDocument) document;
        
        String username = user.getPrincipalName();
        if (protocolDocument.getProtocol().getProtocolId() == null) {
            if (canCreateProtocol(user)) {
                editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
            } 
            else {
                editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
            }
        } 
        else {
            if (canExecuteProtocolTask(username, protocolDocument, TaskName.MODIFY_PROTOCOL)) {  
                editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
            }
            else if (canExecuteProtocolTask(username, protocolDocument, TaskName.VIEW_PROTOCOL)) {
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
        return canCreateProtocol(user);
    }

    /**
     * @see org.kuali.rice.kns.document.authorization.DocumentAuthorizer#canOpen(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
     */
    public boolean canOpen(Document document, Person user) {
        ProtocolDocument protocolDocument = (ProtocolDocument) document;
        if (protocolDocument.getProtocol().getProtocolId() == null) {
            return canCreateProtocol(user);
        }
        return canExecuteProtocolTask(user.getPrincipalName(), (ProtocolDocument) document, TaskName.VIEW_PROTOCOL);
    }
    
    /**
     * Does the user have permission to create a protocol?
     * @param user the user
     * @return true if the user can create a protocol; otherwise false
     */
    private boolean canCreateProtocol(Person user) {
        String username = user.getPrincipalName();
        ApplicationTask task = new ApplicationTask(TaskName.CREATE_PROTOCOL);       
        TaskAuthorizationService taskAuthenticationService = KraServiceLocator.getService(TaskAuthorizationService.class);
        return taskAuthenticationService.isAuthorized(username, task);
    }
    
    /**
     * Does the user have permission to execute the given task for a protocol?
     * @param username the user's username
     * @param doc the protocol document
     * @param taskName the name of the task
     * @return true if has permission; otherwise false
     */
    private boolean canExecuteProtocolTask(String username, ProtocolDocument doc, String taskName) {
        ProtocolTask task = new ProtocolTask(taskName, doc.getProtocol());       
        TaskAuthorizationService taskAuthenticationService = KraServiceLocator.getService(TaskAuthorizationService.class);
        return taskAuthenticationService.isAuthorized(username, task);
    }
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canEdit(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
     */
    @Override
    public boolean canEdit(Document document, Person user) {
        return canExecuteProtocolTask(user.getPrincipalName(), (ProtocolDocument) document, TaskName.MODIFY_PROTOCOL);
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
}

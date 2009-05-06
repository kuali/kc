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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.kuali.kra.authorization.ApplicationTask;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.rice.shim.DocumentInitiationAuthorizationException;
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.document.authorization.TransactionalDocumentAuthorizerBase;
import org.kuali.rice.kns.util.KNSConstants;

/**
 * The Protocol Document Authorizer controls creation and access to protocol documents.
 */
public class ProtocolDocumentAuthorizer extends TransactionalDocumentAuthorizerBase {
    
    private static final String TRUE = "TRUE";
    private static final String FALSE = "FALSE";
    
    /**
     * Used for permissions
     * @see org.kuali.core.document.authorization.DocumentAuthorizerBase#getEditMode(org.kuali.rice.kns.document.Document, org.kuali.core.bo.user.UniversalUser)
     */
    @SuppressWarnings("unchecked")
    public Map getEditMode(Document doc, UniversalUser user) {
                 
        Map editModeMap = new HashMap();
        
        ProtocolDocument protocolDocument = (ProtocolDocument) doc;
        
        String username = user.getPersonUserIdentifier();
        if (protocolDocument.getProtocol().getProtocolId() == null) {
            if (canCreateProtocol(user)) {
                editModeMap.put(AuthorizationConstants.EditMode.FULL_ENTRY, TRUE);
            } 
            else {
                editModeMap.put(AuthorizationConstants.EditMode.UNVIEWABLE, TRUE);
            }
        } 
        else {
            if (canExecuteProtocolTask(username, protocolDocument, TaskName.MODIFY_PROTOCOL)) {  
                editModeMap.put(AuthorizationConstants.EditMode.FULL_ENTRY, TRUE);
            }
            else if (canExecuteProtocolTask(username, protocolDocument, TaskName.VIEW_PROTOCOL)) {
                editModeMap.put(AuthorizationConstants.EditMode.VIEW_ONLY, TRUE);
            }
            else {
                editModeMap.put(AuthorizationConstants.EditMode.UNVIEWABLE, TRUE);
            }
        }
        
        return editModeMap;
    }
    
    /**
     * @see org.kuali.core.document.authorization.DocumentAuthorizerBase#canInitiate(java.lang.String,
     *      org.kuali.core.bo.user.UniversalUser)
     */
    //@Override
    public void canInitiate(String documentTypeName, UniversalUser user) {
        super.canInitiate(documentTypeName, user);
        if (!canCreateProtocol(user)) {
            throw new DocumentInitiationAuthorizationException(KeyConstants.ERROR_AUTHORIZATION_DOCUMENT_INITIATION, 
                                                               new String[] { user.getPersonUserIdentifier(), documentTypeName });
        }
    }
    
    /**
     * @see org.kuali.core.document.authorization.DocumentAuthorizerBase#hasInitiateAuthorization(org.kuali.rice.kns.document.Document, org.kuali.core.bo.user.UniversalUser)
     */
    //@Override
    public boolean hasInitiateAuthorization(Document document, UniversalUser user) {
   
        ProtocolDocument protocolDocument = (ProtocolDocument) document;
        
        boolean permission;
        if (protocolDocument.getProtocol().getProtocolId() == null) {
            permission = canCreateProtocol(user);
        }
        else {
            String username = user.getPersonUserIdentifier();
            permission = canExecuteProtocolTask(username, protocolDocument, TaskName.MODIFY_PROTOCOL);
        }
        return permission;
    }
   
    /**
     * @see org.kuali.core.document.authorization.TransactionalDocumentAuthorizerBase#getDocumentActionFlags(org.kuali.rice.kns.document.Document, org.kuali.core.bo.user.UniversalUser)
     */
    @Override
    public Set<String> getDocumentActions(Document document, Person user, Set<String> documentActions) {
       
        // no copy button
        super.getDocumentActions(document, user, documentActions);
        documentActions.remove(KNSConstants.KUALI_ACTION_CAN_COPY);
       
        // NEED TO REDO ANNOTATE CHECK SINCE CHANGED THE VALUE OF FLAGS
        if (documentActions.contains(KNSConstants.KUALI_ACTION_CAN_ROUTE)) {
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_ANNOTATE);
        }
       
        // Any user who has the Initiate Authorization can save and cancel.
        if (this.hasInitiateAuthorization(document, new UniversalUser(user))) {
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_SAVE);
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_CANCEL);
        }
        else {
            documentActions.remove(KNSConstants.KUALI_ACTION_CAN_SAVE);
            documentActions.remove(KNSConstants.KUALI_ACTION_CAN_CANCEL);
        }
        
        return documentActions;
    }
    
    /**
     * Does the user have permission to create a protocol?
     * @param user the user
     * @return true if the user can create a protocol; otherwise false
     */
    private boolean canCreateProtocol(UniversalUser user) {
        String username = user.getPersonUserIdentifier();
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
}

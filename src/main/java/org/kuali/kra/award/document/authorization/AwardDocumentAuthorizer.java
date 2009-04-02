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
package org.kuali.kra.award.document.authorization;

import java.util.HashMap;
import java.util.Map;

import org.kuali.core.authorization.AuthorizationConstants;
import org.kuali.core.bo.user.UniversalUser;
import org.kuali.core.document.Document;
import org.kuali.core.document.authorization.DocumentActionFlags;
import org.kuali.core.document.authorization.TransactionalDocumentAuthorizerBase;
import org.kuali.core.exceptions.DocumentInitiationAuthorizationException;
import org.kuali.kra.authorization.ApplicationTask;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.service.TaskAuthorizationService;

/**
 * 
 * This class represents the Award Document Authorizer.
 */
public class AwardDocumentAuthorizer extends TransactionalDocumentAuthorizerBase {
    
    private static final String TRUE = "TRUE";
    private static final String FALSE = "FALSE";
    
    /**
     * Used for permissions
     * @see org.kuali.core.document.authorization.DocumentAuthorizerBase#getEditMode(org.kuali.core.document.Document, org.kuali.core.bo.user.UniversalUser)
     */
    @SuppressWarnings("unchecked")
    public Map getEditMode(Document doc, UniversalUser user) {
                 
        Map editModeMap = new HashMap();
        
        AwardDocument awardDocument = (AwardDocument) doc;
        
        String username = user.getPersonUserIdentifier();
        if (awardDocument.getAward().getAwardId() == null) {
            if (canCreateAward(user)) {
                editModeMap.put(AuthorizationConstants.EditMode.FULL_ENTRY, TRUE);
            } 
            else {
                editModeMap.put(AuthorizationConstants.EditMode.UNVIEWABLE, TRUE);
            }
        } 
        else {
            if (canExecuteAwardTask(username, awardDocument, TaskName.MODIFY_AWARD)) {  
                editModeMap.put(AuthorizationConstants.EditMode.FULL_ENTRY, TRUE);
            }
            else if (canExecuteAwardTask(username, awardDocument, TaskName.VIEW_AWARD)) {
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
    @Override
    public void canInitiate(String documentTypeName, UniversalUser user) {
        super.canInitiate(documentTypeName, user);
        if (!canCreateAward(user)) {
            throw new DocumentInitiationAuthorizationException(KeyConstants.ERROR_AUTHORIZATION_DOCUMENT_INITIATION, 
                                                               new String[] { user.getPersonUserIdentifier(), documentTypeName });
        }
    }
    
    /**
     * @see org.kuali.core.document.authorization.DocumentAuthorizerBase#hasInitiateAuthorization(org.kuali.core.document.Document, org.kuali.core.bo.user.UniversalUser)
     */
    @Override
    public boolean hasInitiateAuthorization(Document document, UniversalUser user) {
   
        AwardDocument awardDocument = (AwardDocument) document;
        
        boolean permission;
        if (awardDocument.getAward().getAwardId() == null) {
            permission = canCreateAward(user);
        }
        else {
            String username = user.getPersonUserIdentifier();
            permission = canExecuteAwardTask(username, awardDocument, TaskName.MODIFY_AWARD);
        }
        return permission;
    }
   
    /**
     * @see org.kuali.core.document.authorization.TransactionalDocumentAuthorizerBase#getDocumentActionFlags(org.kuali.core.document.Document, org.kuali.core.bo.user.UniversalUser)
     */
    @Override
    public DocumentActionFlags getDocumentActionFlags(Document document, UniversalUser user) {
       
        // no copy button
        DocumentActionFlags flags = super.getDocumentActionFlags(document, user);
        flags.setCanCopy(false);
       
        // NEED TO REDO ANNOTATE CHECK SINCE CHANGED THE VALUE OF FLAGS
        this.setAnnotateFlag(flags);
       
        // Any user who has the Initiate Authorization can save and cancel.
       
        if (this.hasInitiateAuthorization(document, user)) {
            flags.setCanSave(true);
            flags.setCanCancel(true);
        }
        else {
            flags.setCanSave(false);
            flags.setCanCancel(false);
        }
        
        return flags;
    }
    
    /**
     * Does the user have permission to create a award?
     * @param user the user
     * @return true if the user can create a award; otherwise false
     */
    private boolean canCreateAward(UniversalUser user) {
        String username = user.getPersonUserIdentifier();
        ApplicationTask task = new ApplicationTask(TaskName.CREATE_AWARD);       
        TaskAuthorizationService taskAuthenticationService = KraServiceLocator.getService(TaskAuthorizationService.class);
        return taskAuthenticationService.isAuthorized(username, task);
    }
    
    /**
     * Does the user have permission to execute the given task for a award?
     * @param username the user's username
     * @param doc the award document
     * @param taskName the name of the task
     * @return true if has permission; otherwise false
     */
    private boolean canExecuteAwardTask(String username, AwardDocument doc, String taskName) {
        AwardTask task = new AwardTask(taskName, doc.getAward());       
        TaskAuthorizationService taskAuthenticationService = KraServiceLocator.getService(TaskAuthorizationService.class);
        return taskAuthenticationService.isAuthorized(username, task);
    }    
}

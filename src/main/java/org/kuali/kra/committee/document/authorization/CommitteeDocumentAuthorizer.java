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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.kuali.kra.authorization.ApplicationTask;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.document.authorization.TransactionalDocumentAuthorizerBase;
import org.kuali.rice.kns.util.KNSConstants;

/**
 * The document authorizer controls access to the Committee as well as
 * other document associated authorizations.
 */
public class CommitteeDocumentAuthorizer extends TransactionalDocumentAuthorizerBase {
    
    private static final String TRUE = "TRUE";
    private static final String FALSE = "FALSE";
    
    /**
     * @see org.kuali.core.document.authorization.DocumentAuthorizerBase#getEditMode(org.kuali.rice.kns.document.Document, org.kuali.core.bo.user.UniversalUser)
     */
    @SuppressWarnings("unchecked")
    public Map getEditMode(Document doc, UniversalUser user) {
                 
        Map editModeMap = new HashMap();
        
        CommitteeDocument committeeDocument = (CommitteeDocument) doc;
        
        String username = user.getPersonUserIdentifier();
        if (committeeDocument.getCommittee().getCommitteeId() == null) {
            if (canCreateCommittee(user)) {
                editModeMap.put(AuthorizationConstants.EditMode.FULL_ENTRY, TRUE);
            } 
            else {
                editModeMap.put(AuthorizationConstants.EditMode.UNVIEWABLE, TRUE);
            }
        } 
        else {
            if (canExecuteCommitteeTask(username, committeeDocument, TaskName.MODIFY_COMMITTEE)) {  
                editModeMap.put(AuthorizationConstants.EditMode.FULL_ENTRY, TRUE);
            }
            else if (canExecuteCommitteeTask(username, committeeDocument, TaskName.VIEW_COMMITTEE)) {
                editModeMap.put(AuthorizationConstants.EditMode.VIEW_ONLY, TRUE);
            }
            else {
                editModeMap.put(AuthorizationConstants.EditMode.UNVIEWABLE, TRUE);
            }
        }
        
        return editModeMap;
    }
    
    /**
     * @see org.kuali.core.document.authorization.DocumentAuthorizerBase#hasInitiateAuthorization(org.kuali.core.document.Document, org.kuali.core.bo.user.UniversalUser)
     */
    public boolean hasInitiateAuthorization(Document document, UniversalUser user) {
   
        CommitteeDocument committeeDocument = (CommitteeDocument) document;
        
        boolean permission;
        if (committeeDocument.getCommittee().getCommitteeId() == null) {
            permission = canCreateCommittee(user);
        }
        else {
            String username = user.getPersonUserIdentifier();
            permission = canExecuteCommitteeTask(username, committeeDocument, TaskName.MODIFY_COMMITTEE);
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
        
        // Any user who has the Initiate Authorization can save and cancel.
        if (this.hasInitiateAuthorization(document, (new UniversalUser(user)))) {
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_SAVE);
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_CANCEL);
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_RELOAD);
        }
        
        if (canExecuteCommitteeTask((new UniversalUser(user)).getPersonUserIdentifier(), (CommitteeDocument) document, TaskName.SUBMIT_TO_WORKFLOW)) {
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_ROUTE);
//          NEED TO REDO ANNOTATE CHECK SINCE CHANGED THE VALUE OF FLAGS
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_ANNOTATE);
        }

        return documentActions;
    }
    
    /**
     * Does the user have permission to create a committee?
     * @param user the user
     * @return true if the user can create a committee; otherwise false
     */
    private boolean canCreateCommittee(UniversalUser user) {
        String username = user.getPersonUserIdentifier();
        ApplicationTask task = new ApplicationTask(TaskName.ADD_COMMITTEE);       
        TaskAuthorizationService taskAuthenticationService = KraServiceLocator.getService(TaskAuthorizationService.class);
        return taskAuthenticationService.isAuthorized(username, task);
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
        TaskAuthorizationService taskAuthenticationService = KraServiceLocator.getService(TaskAuthorizationService.class);
        return taskAuthenticationService.isAuthorized(username, task);
    }
}

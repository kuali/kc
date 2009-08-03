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

import java.util.HashSet;
import java.util.Set;

import org.kuali.kra.authorization.ApplicationTask;
import org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.infrastructure.AwardTaskNames;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.kns.document.Document;

/**
 * This class is the Award Document Authorizer.  It determines the edit modes and
 * document actions for all award documents.
 */
public class AwardDocumentAuthorizer extends KcTransactionalDocumentAuthorizerBase {
    
    /**
     * @see org.kuali.rice.kns.document.authorization.TransactionalDocumentAuthorizer#getEditModes(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person, java.util.Set)
     */
    public Set<String> getEditModes(Document document, Person user, Set<String> currentEditModes) {
        Set<String> editModes = new HashSet<String>();
        
        String username = user.getPrincipalName();
        AwardDocument awardDocument = (AwardDocument) document;
        
        if (awardDocument.getAward().getAwardId() == null) {
            if (canCreateAward(user)) {
                editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
            }
            else {
                editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
            }
        }
        else {
            if (canExecuteAwardTask(username, awardDocument, AwardTaskNames.MODIFY_AWARD.getAwardTaskName())) {  
                editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
            }
            else if (canExecuteAwardTask(username, awardDocument, AwardTaskNames.VIEW_AWARD.getAwardTaskName())) {
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
        return canCreateAward(user);
    }
  
    /**
     * @see org.kuali.rice.kns.document.authorization.DocumentAuthorizer#canOpen(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
     */
    public boolean canOpen(Document document, Person user) {
        AwardDocument awardDocument = (AwardDocument) document;
        if (awardDocument.getAward().getAwardId() == null) {
            return canCreateAward(user);
        }
        return canExecuteAwardTask(user.getPrincipalName(), (AwardDocument) document, AwardTaskNames.VIEW_AWARD.getAwardTaskName());
    }
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canEdit(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
     */
    @Override
    public boolean canEdit(Document document, Person user) {
        return canExecuteAwardTask(user.getPrincipalName(), (AwardDocument) document, AwardTaskNames.MODIFY_AWARD.getAwardTaskName());
    }
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canSave(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
     */
    @Override
    protected boolean canSave(Document document, Person user) {
        return canEdit(document, user);
    }
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canReload(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
     */
    @Override
    protected boolean canReload(Document document, Person user) {
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
     * Does the user have permission to create a award?
     * @param user the user
     * @return true if the user can create a award; otherwise false
     */
    private boolean canCreateAward(Person user) {
        String username = user.getPrincipalName();
        ApplicationTask task = new ApplicationTask(TaskName.CREATE_AWARD);
        return getTaskAuthorizationService().isAuthorized(username, task);
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
        return getTaskAuthorizationService().isAuthorized(username, task);
    }
}

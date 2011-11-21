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
package org.kuali.kra.subaward.document.authorization;
import java.util.HashSet;
import java.util.Set;

import org.kuali.kra.authorization.ApplicationTask;
import org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.subaward.document.SubAwardDocument;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.kns.document.Document;
public class SubAwardDocumentAuthorizer extends KcTransactionalDocumentAuthorizerBase {
    /**
     * @see org.kuali.rice.kns.document.authorization.TransactionalDocumentAuthorizer#getEditModes(
     * org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person, java.util.Set)
     */
    public Set<String> getEditModes(Document document, Person user, Set<String> currentEditModes) {
        Set<String> editModes = new HashSet<String>();
        String userId = user.getPrincipalId();
        
        SubAwardDocument subawardDocument = (SubAwardDocument) document;
        
        if(subawardDocument.getSubAward().getSubAwardId()== null){
            if (canCreateSubAward(user.getPrincipalId())) {
                editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);         
            }
            else {
                editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
            }
        } else {
            if (canExecuteSubAwardTask(userId, subawardDocument, TaskName.MODIFY_SUBAWARD) ) {  
                editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
            } else if (canExecuteSubAwardTask(userId, subawardDocument, TaskName.VIEW_SUBAWARD)) {
                editModes.add(AuthorizationConstants.EditMode.VIEW_ONLY);
            } else {
                editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
            }  
            if (canExecuteSubAwardTask(userId, subawardDocument, TaskName.CREATE_SUBAWARD) ) {  
                editModes.add("createSubaward");
            } 
        }
        
        return editModes;
    }
    
    
    
    private boolean canExecuteSubAwardTask(String userId, SubAwardDocument subawardDocument, String taskName) {
        SubAwardTask task = new SubAwardTask(taskName, subawardDocument);
        return this.getTaskAuthorizationService().isAuthorized(userId, task);
    }
    
    
    /**
     * Does the user have permission to create a Subaward?
     * @param user the user
     * @return true if the user can create a award; otherwise false
     */
    private boolean canCreateSubAward(String userId) {
        ApplicationTask task = new ApplicationTask(TaskName.CREATE_SUBAWARD);
        return getTaskAuthorizationService().isAuthorized(userId, task);
    }
    
    
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canReload(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person)
     */
    @Override
    protected boolean canReload(Document document, Person user) {
        return canEdit(document, user);
    }

    public boolean canOpen(Document document, Person user) {
        SubAwardDocument subAwardDocument = (SubAwardDocument) document;
        if (subAwardDocument.getSubAward().getSubAwardId() == null) {
            return canCreateSubAward(user.getPrincipalId());
        }
        return canExecuteSubAwardTask(user.getPrincipalId(), (SubAwardDocument) document, TaskName.VIEW_SUBAWARD);
    }

    public boolean canInitiate(String documentTypeName, Person user) {
        return canCreateSubAward(user.getPrincipalId());
    }
    public boolean canEdit(Document document, Person user) {
        return canExecuteSubAwardTask(user.getPrincipalId(), (SubAwardDocument) document, TaskName.MODIFY_SUBAWARD);
    }
}

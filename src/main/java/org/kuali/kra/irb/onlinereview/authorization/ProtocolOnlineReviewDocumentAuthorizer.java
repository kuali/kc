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
package org.kuali.kra.irb.onlinereview.authorization;

import java.util.HashSet;
import java.util.Set;

import org.kuali.kra.authorization.ApplicationTask;
import org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.ProtocolOnlineReviewDocument;
import org.kuali.kra.service.KraWorkflowService;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.util.GlobalVariables;

public class ProtocolOnlineReviewDocumentAuthorizer extends KcTransactionalDocumentAuthorizerBase {

    private transient KraWorkflowService kraWorkflowService;
    
    public Set<String> getEditModes(Document document, Person user, Set<String> currentEditModes) {
        Set<String> editModes = new HashSet<String>();
        
        ProtocolOnlineReviewDocument protocolOnlineReviewDocument = (ProtocolOnlineReviewDocument) document;
        String userId = user.getPrincipalId();
        
        if (canExecuteProtocolOnlineReviewTask(userId, protocolOnlineReviewDocument, TaskName.MAINTAIN_PROTOCOL_ONLINEREVIEWS)) {  
            editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
        } else if (canExecuteProtocolOnlineReviewTask( userId, protocolOnlineReviewDocument, TaskName.MODIFY_PROTOCOL_ONLINEREVIEW)
                   && getKraWorkflowService().isUserApprovalRequested(protocolOnlineReviewDocument, GlobalVariables.getUserSession().getPrincipalId())) {
            editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
        } else if (canExecuteProtocolOnlineReviewTask(userId, protocolOnlineReviewDocument, TaskName.VIEW_PROTOCOL_ONLINEREVIEW)) {
            editModes.add(AuthorizationConstants.EditMode.VIEW_ONLY);
        } else {
            editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
        }
            
        return editModes;
    }

    public boolean canInitiate(String documentTypeName, Person user) {
        return true;
    }


    /**
     * @see org.kuali.rice.kns.document.authorization.DocumentAuthorizer#canOpen(org.kuali.rice.krad.document.Document, org.kuali.rice.kim.api.identity.Person)
     */
    public boolean canOpen(Document document, Person user) {
        ProtocolOnlineReviewDocument protocolOnlineReviewDocument = (ProtocolOnlineReviewDocument) document;
        if (protocolOnlineReviewDocument.getProtocolOnlineReview() == null) {
            return canCreateProtocolOnlineReview(user);
        }
        return canExecuteProtocolOnlineReviewTask(user.getPrincipalId(), (ProtocolOnlineReviewDocument) document, TaskName.VIEW_PROTOCOL_ONLINEREVIEW);
    }
    
    /**
     * Does the user have permission to create a protocol?
     * @param user the user
     * @return true if the user can create a protocol; otherwise false
     */
    private boolean canCreateProtocolOnlineReview(Person user) {
        ApplicationTask task = new ApplicationTask(TaskName.CREATE_PROTOCOL_ONLINEREVIEW);       
        TaskAuthorizationService taskAuthenticationService = KraServiceLocator.getService(TaskAuthorizationService.class);
        return taskAuthenticationService.isAuthorized(user.getPrincipalId(), task);
    }
    
    /**
     * Does the user have permission to execute the given task for a protocol?
     * @param username the user's username
     * @param doc the protocol document
     * @param taskName the name of the task
     * @return true if has permission; otherwise false
     */
    private boolean canExecuteProtocolOnlineReviewTask(String userId, ProtocolOnlineReviewDocument doc, String taskName) {
        ProtocolOnlineReviewTask task = new ProtocolOnlineReviewTask(taskName, doc.getProtocolOnlineReview());       
        TaskAuthorizationService taskAuthenticationService = KraServiceLocator.getService(TaskAuthorizationService.class);
        return taskAuthenticationService.isAuthorized(userId, task);
    }
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canEdit(org.kuali.rice.krad.document.Document, org.kuali.rice.kim.api.identity.Person)
     */
    @Override
    public boolean canEdit(Document document, Person user) {
        return canExecuteProtocolOnlineReviewTask(user.getPrincipalId(), (ProtocolOnlineReviewDocument) document, TaskName.MODIFY_PROTOCOL_ONLINEREVIEW) 
               || canExecuteProtocolOnlineReviewTask(user.getPrincipalId(), (ProtocolOnlineReviewDocument) document, TaskName.MAINTAIN_PROTOCOL_ONLINEREVIEWS); 
    }
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canSave(org.kuali.rice.krad.document.Document, org.kuali.rice.kim.api.identity.Person)
     */
    @Override
    public boolean canSave(Document document, Person user) {
        return canEdit(document, user);
    }
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canCopy(org.kuali.rice.krad.document.Document, org.kuali.rice.kim.api.identity.Person)
     */
    @Override
    public boolean canCopy(Document document, Person user) {
        return false;
    }
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canCancel(org.kuali.rice.krad.document.Document, org.kuali.rice.kim.api.identity.Person)
     */
    @Override
    public boolean canCancel(Document document, Person user) {
        return false;
    }
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canRoute(org.kuali.rice.krad.document.Document, org.kuali.rice.kim.api.identity.Person)
     */
    @Override
    public boolean canRoute(Document document, Person user) {
        return false;
    }
    
    /**
     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canRoute(org.kuali.rice.krad.document.Document, org.kuali.rice.kim.api.identity.Person)
     */
    @Override
    public boolean canApprove(Document document, Person user) {
       return super.canApprove(document, user);
    } 
    
    //we only let the IRB Admin disapprove these documents.
    public boolean canDisapprove(Document document, Person user) {
        boolean result = super.canDisapprove(document, user);
        result &= canExecuteProtocolOnlineReviewTask(user.getPrincipalId(), (ProtocolOnlineReviewDocument) document, TaskName.MAINTAIN_PROTOCOL_ONLINEREVIEWS); 
        return result;
    }
    
    private KraWorkflowService getKraWorkflowService() {
        if (kraWorkflowService==null) {
            kraWorkflowService = KraServiceLocator.getService(KraWorkflowService.class);
        }
        return kraWorkflowService;
    }

    @Override
    public boolean canSendNoteFyi(Document document, Person user) {
        return false;
    }
    
    @Override
    public boolean canFyi(Document document, Person user) {
        return false;
    }

    
}

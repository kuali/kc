/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.irb.onlinereview.authorization;

import org.kuali.coeus.common.framework.auth.KcTransactionalDocumentAuthorizerBase;
import org.kuali.coeus.common.framework.auth.task.ApplicationTask;
import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.ProtocolOnlineReviewDocument;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.HashSet;
import java.util.Set;

public class ProtocolOnlineReviewDocumentAuthorizer extends KcTransactionalDocumentAuthorizerBase {

    private transient KcWorkflowService kraWorkflowService;
    
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


    @Override
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
        TaskAuthorizationService taskAuthenticationService = KcServiceLocator.getService(TaskAuthorizationService.class);
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
        ProtocolOnlineReviewTask task = new ProtocolOnlineReviewTask(taskName, (ProtocolOnlineReview)doc.getProtocolOnlineReview());       
        TaskAuthorizationService taskAuthenticationService = KcServiceLocator.getService(TaskAuthorizationService.class);
        return taskAuthenticationService.isAuthorized(userId, task);
    }
    
    @Override
    public boolean canEdit(Document document, Person user) {
        return canExecuteProtocolOnlineReviewTask(user.getPrincipalId(), (ProtocolOnlineReviewDocument) document, TaskName.MODIFY_PROTOCOL_ONLINEREVIEW) 
               || canExecuteProtocolOnlineReviewTask(user.getPrincipalId(), (ProtocolOnlineReviewDocument) document, TaskName.MAINTAIN_PROTOCOL_ONLINEREVIEWS); 
    }
    
    @Override
    public boolean canSave(Document document, Person user) {
        return canEdit(document, user);
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
    
    private KcWorkflowService getKraWorkflowService() {
        if (kraWorkflowService==null) {
            kraWorkflowService = KcServiceLocator.getService(KcWorkflowService.class);
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

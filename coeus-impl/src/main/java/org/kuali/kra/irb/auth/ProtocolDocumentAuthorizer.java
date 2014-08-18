/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.auth.KcTransactionalDocumentAuthorizerBase;
import org.kuali.coeus.common.framework.auth.task.ApplicationTask;
import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewService;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class is the Proposal Document Authorizer.  It determines the edit modes and
 * document actions for all proposal development documents.
 */
public class ProtocolDocumentAuthorizer extends KcTransactionalDocumentAuthorizerBase {
    

    private static final long serialVersionUID = -8742664470188406956L;

    @Override
    public Set<String> getEditModes(Document document, Person user, Set<String> currentEditModes) {
        Set<String> editModes = new HashSet<String>();
        
        ProtocolDocument protocolDocument = (ProtocolDocument) document;
        String userId = user.getPrincipalId();
        
        if (protocolDocument.getProtocol().getProtocolId() == null) {
            if (canCreateProtocol(user)) {
                editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
            } 
            else {
                editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
            }
        } 
        else {
            if (canExecuteProtocolTask(userId, protocolDocument, TaskName.MODIFY_PROTOCOL)) {  
                editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
            }
            else if (canExecuteProtocolTask(userId, protocolDocument, TaskName.VIEW_PROTOCOL)) {
                editModes.add(AuthorizationConstants.EditMode.VIEW_ONLY);
            }
            else {
                editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
            }
            
            if( canExecuteProtocolTask(userId,protocolDocument,TaskName.MAINTAIN_PROTOCOL_ONLINEREVIEWS)) {
                editModes.add(TaskName.MAINTAIN_PROTOCOL_ONLINEREVIEWS);
            }
            if (canViewReviewComments(protocolDocument, user)) {
                editModes.add(Constants.CAN_VIEW_REVIEW_COMMENTS);
            }
        }
        
        return editModes;
    }
    
    /**
     * This method determines if a person can view the review comments.
     * @param document
     * @param user
     * @return boolean
     */
    public boolean canViewReviewComments(Document document, Person user) {
        ProtocolDocument protocolDoc = (ProtocolDocument)document;
        List<ProtocolPersonBase> participants = protocolDoc.getProtocol().getProtocolPersons();
        for (ProtocolPersonBase participant : participants) {
            if (StringUtils.equalsIgnoreCase(participant.getPersonId() + "", user.getPrincipalId())) {
                String statusCode = protocolDoc.getProtocol().getProtocolStatusCode();
                if (statusCode.equalsIgnoreCase(ProtocolStatus.SUBMITTED_TO_IRB)) {
                    return false;
                }
            }
        }
        return true;
    }
    @Override
    public boolean canInitiate(String documentTypeName, Person user) {
        if (GlobalVariables.getUserSession().getObjectMap().get(ProtocolAmendRenewService.AMEND_RENEW_ALLOW_NEW_PROTOCOL_DOCUMENT) != null) {
            GlobalVariables.getUserSession().removeObject(ProtocolAmendRenewService.AMEND_RENEW_ALLOW_NEW_PROTOCOL_DOCUMENT);
            return true;
        }
        return canCreateProtocol(user);
    }

    @Override
    public boolean canOpen(Document document, Person user) {
        ProtocolDocument protocolDocument = (ProtocolDocument) document;
        if (protocolDocument.getProtocol().getProtocolId() == null) {
            return canCreateProtocol(user);
        }
        return canExecuteProtocolTask(user.getPrincipalId(), (ProtocolDocument) document, TaskName.VIEW_PROTOCOL);
    }
    
    /**
     * Does the user have permission to create a protocol?
     * @param user the user
     * @return true if the user can create a protocol; otherwise false
     */
    private boolean canCreateProtocol(Person user) {
        ApplicationTask task = new ApplicationTask(TaskName.CREATE_PROTOCOL);       
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
    private boolean canExecuteProtocolTask(String userId, ProtocolDocument doc, String taskName) {
        ProtocolTask task = new ProtocolTask(taskName, (Protocol) doc.getProtocol());       
        TaskAuthorizationService taskAuthenticationService = KcServiceLocator.getService(TaskAuthorizationService.class);
        return taskAuthenticationService.isAuthorized(userId, task);
    }
    
    @Override
    public boolean canEdit(Document document, Person user) {
        return canExecuteProtocolTask(user.getPrincipalId(), (ProtocolDocument) document, TaskName.MODIFY_PROTOCOL);
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
    
    /**
     * Can the user blanket approve the given document?
     * @param document the document
     * @param user the user
     * @return always false for ProtocolDocument
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
    
    @Override
    public boolean canRecall(Document document, Person user) {
        return canExecuteProtocolTask(user.getPrincipalId(), (ProtocolDocument)document, TaskName.RECALL_PROTOCOL);
    }

    
}

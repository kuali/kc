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
package org.kuali.kra.iacuc.auth;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.auth.KcTransactionalDocumentAuthorizerBase;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.common.framework.auth.task.ApplicationTask;
import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.actions.IacucProtocolStatus;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendRenewService;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.util.GlobalVariables;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IacucProtocolDocumentAuthorizer extends KcTransactionalDocumentAuthorizerBase {

    private static final long serialVersionUID = -5078229085592345997L;

    public Set<String> getEditModes(Document document, Person user, Set<String> currentEditModes) {
        Set<String> editModes = new HashSet<>();
        
        IacucProtocolDocument iacucProtocolDocument = (IacucProtocolDocument) document;
        String userId = user.getPrincipalId();
        
        if (iacucProtocolDocument.getProtocol().getProtocolId() == null) {
            if (canCreateIacucProtocol(user)) {
                editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
            } else {
                editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
            }
        } else {
            if (canExecuteIacucProtocolTask(userId, iacucProtocolDocument, TaskName.MODIFY_IACUC_PROTOCOL)) {
                editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
            } else if (canExecuteIacucProtocolTask(userId, iacucProtocolDocument, TaskName.VIEW_IACUC_PROTOCOL)) {
                editModes.add(AuthorizationConstants.EditMode.VIEW_ONLY);
            } else {
                editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
            }

            if(canExecuteIacucProtocolTask(userId,iacucProtocolDocument,TaskName.MAINTAIN_IACUC_PROTOCOL_ONLINEREVIEWS)) {
                editModes.add(TaskName.MAINTAIN_IACUC_PROTOCOL_ONLINEREVIEWS);
            }

            if (canViewReviewComments(iacucProtocolDocument, user)) {
                editModes.add(Constants.CAN_VIEW_REVIEW_COMMENTS);
            }

            if (canModify(iacucProtocolDocument,user)) {
                editModes.add(KraAuthorizationConstants.CAN_MODIFY);
            }
           
        }
        return editModes;
    }
    
    public boolean canViewReviewComments(Document document, Person user) {
        ProtocolDocumentBase protocolDoc = (ProtocolDocumentBase)document;
        List<ProtocolPersonBase> participants = protocolDoc.getProtocol().getProtocolPersons();
        for (ProtocolPersonBase participant : participants) {
            if (StringUtils.equalsIgnoreCase(participant.getPersonId() + "", user.getPrincipalId())) {
                String statusCode = protocolDoc.getProtocol().getProtocolStatusCode();
                if (statusCode.equalsIgnoreCase(IacucProtocolStatus.SUBMITTED_TO_IACUC)) {
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
        return canCreateIacucProtocol(user);
    }

    @Override
    public boolean canOpen(Document document, Person user) {
        IacucProtocolDocument protocolDocument = (IacucProtocolDocument) document;
        if (protocolDocument.getProtocol().getProtocolId() == null) {
            return canCreateIacucProtocol(user);
        }
        return canExecuteIacucProtocolTask(user.getPrincipalId(), (IacucProtocolDocument) document, TaskName.VIEW_IACUC_PROTOCOL);
    }
    
    /**
     * Does the user have permission to create a Iacuc Protocol?
     * @param user the user
     * @return true if the user can create a Iacuc Protocol; otherwise false
     */
    private boolean canCreateIacucProtocol(Person user) {
        ApplicationTask task = new ApplicationTask(TaskName.CREATE_IACUC_PROTOCOL);       
        TaskAuthorizationService taskAuthenticationService = KcServiceLocator.getService(TaskAuthorizationService.class);
        return taskAuthenticationService.isAuthorized(user.getPrincipalId(), task);
    }
    
    /**
     * Does the user have permission to execute the given task for a Iacuc Protocol?
     * @param userId the user's userId
     * @param doc the Iacuc Protocol document
     * @param taskName the name of the task
     * @return true if has permission; otherwise false
     */
    private boolean canExecuteIacucProtocolTask(String userId, IacucProtocolDocument doc, String taskName) {
        IacucProtocolTask task = new IacucProtocolTask(taskName, doc.getIacucProtocol());       
        TaskAuthorizationService taskAuthenticationService = KcServiceLocator.getService(TaskAuthorizationService.class);
        return taskAuthenticationService.isAuthorized(userId, task);
    }
    
    @Override
    public boolean canEdit(Document document, Person user) {
        return canExecuteIacucProtocolTask(user.getPrincipalId(), (IacucProtocolDocument) document, TaskName.MODIFY_IACUC_PROTOCOL);
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
     * @return always false for IacucProtocolDocument
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

    public boolean canModify(IacucProtocolDocument document, Person user) {
        IacucProtocol iacucProtocol = document.getIacucProtocol();
        return (!getWorkFlowService().isInWorkflow(document) || iacucProtocol.isCorrectionMode()) &&
        getAuthorizationService().hasPermission(user.getPrincipalId(), iacucProtocol, PermissionConstants.MODIFY_IACUC_PROTOCOL);
    }

    protected KcWorkflowService getWorkFlowService() {
        return KcServiceLocator.getService(KcWorkflowService.class);
    }

    protected KcAuthorizationService getAuthorizationService() {
        return KcServiceLocator.getService(KcAuthorizationService.class);
    }

}

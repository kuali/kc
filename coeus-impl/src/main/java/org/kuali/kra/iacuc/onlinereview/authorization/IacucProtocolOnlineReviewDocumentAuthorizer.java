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
package org.kuali.kra.iacuc.onlinereview.authorization;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.auth.KcTransactionalDocumentAuthorizerBase;
import org.kuali.coeus.common.framework.auth.task.ApplicationTask;
import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.IacucProtocolOnlineReviewDocument;
import org.kuali.kra.iacuc.auth.IacucProtocolTask;
import org.kuali.kra.iacuc.committee.meeting.IacucCommitteeScheduleMinute;
import org.kuali.kra.iacuc.committee.service.IacucCommitteeScheduleService;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.HashSet;
import java.util.Set;

public class IacucProtocolOnlineReviewDocumentAuthorizer extends KcTransactionalDocumentAuthorizerBase {
        

    private static final long serialVersionUID = -5887926854171754314L;
    
    public static final String CAN_EDIT_REVIEW_TYPE = "canEditReviewType";
    public static final String CAN_EDIT_DETERMINATION = "canEditDetermination";
    public static final String CAN_SAVE = "canSave";

    private transient IacucCommitteeScheduleService committeeScheduleService;
    
    public Set<String> getEditModes(Document document, Person user, Set<String> currentEditModes) {
        Set<String> editModes = new HashSet<String>();
        
        IacucProtocolOnlineReviewDocument protocolOnlineReviewDocument = (IacucProtocolOnlineReviewDocument) document;
        String userId = user.getPrincipalId();
        
        if (canExecuteProtocolOnlineReviewTask(userId, protocolOnlineReviewDocument, TaskName.MAINTAIN_IACUC_PROTOCOL_ONLINEREVIEWS)) {  
            editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
            editModes.add(CAN_SAVE);
            editModes.add(CAN_EDIT_REVIEW_TYPE);
            editModes.add(CAN_EDIT_DETERMINATION);
        } else if (canExecuteProtocolOnlineReviewTask( userId, protocolOnlineReviewDocument, TaskName.MODIFY_IACUC_PROTOCOL_ONLINEREVIEW)) {
            editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
            editModes.add(CAN_SAVE);
            if (canExecuteProtocolOnlineReviewTask(userId, protocolOnlineReviewDocument, TaskName.MODIFY_IACUC_PROTOCOL_ONLINEREVIEW_TYPE)) {
                editModes.add(CAN_EDIT_REVIEW_TYPE);
            }
            if (canExecuteProtocolOnlineReviewTask(userId, protocolOnlineReviewDocument, TaskName.MODIFY_IACUC_PROTOCOL_ONLINEREVIEW_DETERMINATION)) {
                editModes.add(CAN_EDIT_DETERMINATION);
            }            
        } else if (canExecuteProtocolOnlineReviewTask(userId, protocolOnlineReviewDocument, TaskName.VIEW_IACUC_PROTOCOL_ONLINEREVIEW)) {
            editModes.add(AuthorizationConstants.EditMode.VIEW_ONLY);
        } else {
            editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
        }
        
        IacucProtocolDocument iacucProtocolDocument = (IacucProtocolDocument) protocolOnlineReviewDocument.getProtocolOnlineReview().getProtocol().getProtocolDocument();
        boolean canAdministerCommitteeScheduleMinutes = canExecuteIacucProtocolTask(userId,iacucProtocolDocument,TaskName.MAINTAIN_IACUC_PROTOCOL_ONLINEREVIEWS);
        /**
         * The service returns all the minutes for the assoicated Iacuc protocol, however, the permissions described above are only for the specific review being iterated 
         * at this time.  So, we set the minute's read only for the minutes associated with this online review.
         */
        for (IacucCommitteeScheduleMinute minute : this.getCommitteeScheduleService().getMinutesByProtocol(protocolOnlineReviewDocument.getProtocolOnlineReview().getProtocolId())) {
            Long minuteOnlineReviewId =  minute.getProtocolOnlineReviewIdFk();
            Long onlineReviewId = protocolOnlineReviewDocument.getProtocolOnlineReview().getProtocolOnlineReviewId();
            if (ObjectUtils.equals(minuteOnlineReviewId, onlineReviewId)) {
                boolean isCreator = StringUtils.equalsIgnoreCase(minute.getCreateUser(), GlobalVariables.getUserSession().getPrincipalName());
                minute.setReadOnly(!(editModes.contains(CAN_SAVE) && (canAdministerCommitteeScheduleMinutes || isCreator)));
            }
            
        }
        
            
        return editModes;
    }
    
    /**
     * Does the user have permission to execute the given task for a Iacuc Protocol?
     * @param username the user's username
     * @param doc the Iacuc Protocol document
     * @param taskName the name of the task
     * @return true if has permission; otherwise false
     */
    private boolean canExecuteIacucProtocolTask(String userId, IacucProtocolDocument doc, String taskName) {
        // TODO : to be implemented later
        IacucProtocolTask task = new IacucProtocolTask(taskName, doc.getIacucProtocol());       
        TaskAuthorizationService taskAuthenticationService = KcServiceLocator.getService(TaskAuthorizationService.class);
        return taskAuthenticationService.isAuthorized(userId, task);
    }

    public boolean canInitiate(String documentTypeName, Person user) {
        return true;
    }


    @Override
    public boolean canOpen(Document document, Person user) {
        IacucProtocolOnlineReviewDocument protocolOnlineReviewDocument = (IacucProtocolOnlineReviewDocument) document;
        if (protocolOnlineReviewDocument.getProtocolOnlineReview() == null) {
            return canCreateProtocolOnlineReview(user);
        }
        return canExecuteProtocolOnlineReviewTask(user.getPrincipalId(), (IacucProtocolOnlineReviewDocument) document, TaskName.VIEW_PROTOCOL_ONLINEREVIEW);
    }
    
    /**
     * Does the user have permission to create a protocol?
     * @param user the user
     * @return true if the user can create a protocol; otherwise false
     */
    private boolean canCreateProtocolOnlineReview(Person user) {
        ApplicationTask task = new ApplicationTask(TaskName.CREATE_IACUC_PROTOCOL_ONLINEREVIEW);       
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
    private boolean canExecuteProtocolOnlineReviewTask(String userId, IacucProtocolOnlineReviewDocument doc, String taskName) {
        IacucProtocolOnlineReviewTask task = new IacucProtocolOnlineReviewTask(taskName, doc.getProtocolOnlineReview());       
        TaskAuthorizationService taskAuthenticationService = KcServiceLocator.getService(TaskAuthorizationService.class);
        return taskAuthenticationService.isAuthorized(userId, task);
    }
    
    @Override
    public boolean canEdit(Document document, Person user) {
        return canExecuteProtocolOnlineReviewTask(user.getPrincipalId(), (IacucProtocolOnlineReviewDocument) document, TaskName.MODIFY_IACUC_PROTOCOL_ONLINEREVIEW) 
               || canExecuteProtocolOnlineReviewTask(user.getPrincipalId(), (IacucProtocolOnlineReviewDocument) document, TaskName.MAINTAIN_IACUC_PROTOCOL_ONLINEREVIEWS); 
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
    
    //we only let the Iacuc Admin disapprove these documents.
    public boolean canDisapprove(Document document, Person user) {
        boolean result = super.canDisapprove(document, user);
        result &= canExecuteProtocolOnlineReviewTask(user.getPrincipalId(), (IacucProtocolOnlineReviewDocument) document, TaskName.MAINTAIN_IACUC_PROTOCOL_ONLINEREVIEWS); 
        return result;
    }

    @Override
    public boolean canSendNoteFyi(Document document, Person user) {
        return false;
    }
    
    @Override
    public boolean canFyi(Document document, Person user) {
        return false;
    }

    public IacucCommitteeScheduleService getCommitteeScheduleService() {
        if (committeeScheduleService == null) {
            committeeScheduleService = KcServiceLocator.getService(IacucCommitteeScheduleService.class);
        }
        return committeeScheduleService;
    }

    public void setCommitteeScheduleService(IacucCommitteeScheduleService committeeScheduleService) {
        this.committeeScheduleService = committeeScheduleService;
    }

    

}

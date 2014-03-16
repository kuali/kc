/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.iacuc.onlinereview.authorization;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.auth.KcTransactionalDocumentAuthorizerBase;
import org.kuali.coeus.sys.framework.auth.task.ApplicationTask;
import org.kuali.coeus.sys.framework.auth.task.TaskAuthorizationService;
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
        
    /**
     * Comment for <code>serialVersionUID</code>
     */
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
        boolean canEdit = editModes.contains(CAN_SAVE);
        /**
         * The service returns all the minutes for the assoicated Iacuc protocol, however, the permissions described above are only for the specific review being iterated 
         * at this time.  So, we set the minute's read only for the minutes associated with this online review.
         */
        for (IacucCommitteeScheduleMinute minute : this.getCommitteeScheduleService().getMinutesByProtocol(protocolOnlineReviewDocument.getProtocolOnlineReview().getProtocolId())) {
            Long minuteOnlineReviewId =  minute.getProtocolOnlineReviewIdFk();
            Long onlineReviewId = protocolOnlineReviewDocument.getProtocolOnlineReview().getProtocolOnlineReviewId();
            if (ObjectUtils.equals(minuteOnlineReviewId, onlineReviewId)) {
                boolean isCreator = StringUtils.equalsIgnoreCase(minute.getCreateUser(), GlobalVariables.getUserSession().getPrincipalName());
                minute.setReadOnly(!(canEdit && (canAdministerCommitteeScheduleMinutes || isCreator)));
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


    /**
     * @see org.kuali.rice.kns.document.authorization.DocumentAuthorizer#canOpen(org.kuali.rice.krad.document.Document, org.kuali.rice.kim.api.identity.Person)
     */
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
    
    /**
     * @see org.kuali.coeus.sys.framework.auth.KcTransactionalDocumentAuthorizerBase#canEdit(org.kuali.rice.krad.document.Document, org.kuali.rice.kim.api.identity.Person)
     */
    @Override
    public boolean canEdit(Document document, Person user) {
        return canExecuteProtocolOnlineReviewTask(user.getPrincipalId(), (IacucProtocolOnlineReviewDocument) document, TaskName.MODIFY_IACUC_PROTOCOL_ONLINEREVIEW) 
               || canExecuteProtocolOnlineReviewTask(user.getPrincipalId(), (IacucProtocolOnlineReviewDocument) document, TaskName.MAINTAIN_IACUC_PROTOCOL_ONLINEREVIEWS); 
    }
    
    /**
     * @see org.kuali.coeus.sys.framework.auth.KcTransactionalDocumentAuthorizerBase#canSave(org.kuali.rice.krad.document.Document, org.kuali.rice.kim.api.identity.Person)
     */
    @Override
    public boolean canSave(Document document, Person user) {
        return canEdit(document, user);
    }
    
    /**
     * @see org.kuali.coeus.sys.framework.auth.KcTransactionalDocumentAuthorizerBase#canCopy(org.kuali.rice.krad.document.Document, org.kuali.rice.kim.api.identity.Person)
     */
    @Override
    public boolean canCopy(Document document, Person user) {
        return false;
    }
    
    /**
     * @see org.kuali.coeus.sys.framework.auth.KcTransactionalDocumentAuthorizerBase#canCancel(org.kuali.rice.krad.document.Document, org.kuali.rice.kim.api.identity.Person)
     */
    @Override
    public boolean canCancel(Document document, Person user) {
        return false;
    }
    
    /**
     * @see org.kuali.coeus.sys.framework.auth.KcTransactionalDocumentAuthorizerBase#canRoute(org.kuali.rice.krad.document.Document, org.kuali.rice.kim.api.identity.Person)
     */
    @Override
    public boolean canRoute(Document document, Person user) {
        return false;
    }
    
    /**
     * @see org.kuali.coeus.sys.framework.auth.KcTransactionalDocumentAuthorizerBase#canRoute(org.kuali.rice.krad.document.Document, org.kuali.rice.kim.api.identity.Person)
     */
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

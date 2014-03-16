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
package org.kuali.kra.iacuc.auth;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.auth.KcTransactionalDocumentAuthorizerBase;
import org.kuali.coeus.sys.framework.auth.task.ApplicationTask;
import org.kuali.coeus.sys.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.actions.IacucProtocolStatus;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolReviewerType;
import org.kuali.kra.iacuc.onlinereview.IacucProtocolOnlineReview;
import org.kuali.kra.iacuc.onlinereview.IacucProtocolOnlineReviewService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendRenewService;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewer;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.util.GlobalVariables;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IacucProtocolDocumentAuthorizer extends KcTransactionalDocumentAuthorizerBase {
    // TODO : detail need to be implemented after role and tasks are set up.
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -5078229085592345997L;

    /**
     * @see org.kuali.rice.kns.document.authorization.TransactionalDocumentAuthorizer#getEditModes(org.kuali.rice.krad.document.Document, org.kuali.rice.kim.api.identity.Person, java.util.Set)
     */
    public Set<String> getEditModes(Document document, Person user, Set<String> currentEditModes) {
        Set<String> editModes = new HashSet<String>();
        
        IacucProtocolDocument iacucProtocolDocument = (IacucProtocolDocument) document;
        String userId = user.getPrincipalId();
        
        if (iacucProtocolDocument.getProtocol().getProtocolId() == null) {
            if (canCreateIacucProtocol(user)) {
                editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
            } 
            else {
                editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
            }
        } 
        else {
            if (canExecuteIacucProtocolTask(userId, iacucProtocolDocument, TaskName.MODIFY_IACUC_PROTOCOL)) {  
                editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
            }
            else if (canExecuteIacucProtocolTask(userId, iacucProtocolDocument, TaskName.VIEW_IACUC_PROTOCOL)) {
                editModes.add(AuthorizationConstants.EditMode.VIEW_ONLY);
            }
            else {
                editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
            }
            if( canExecuteIacucProtocolTask(userId,iacucProtocolDocument,TaskName.MAINTAIN_IACUC_PROTOCOL_ONLINEREVIEWS)) {
                editModes.add(TaskName.MAINTAIN_IACUC_PROTOCOL_ONLINEREVIEWS);
            }
            if (canViewReviewComments(iacucProtocolDocument, user)) {
                editModes.add(Constants.CAN_VIEW_REVIEW_COMMENTS);
            }
            if (canEditReviewComments(iacucProtocolDocument, user)) {
                editModes.add(Constants.CAN_EDIT_REVIEW_COMMENTS);
            }
            if (canEditReviewAttachments(iacucProtocolDocument, user)) {
                editModes.add(Constants.CAN_EDIT_REVIEW_ATTACHMENTS);
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
    
    public boolean canEditReviewComments(Document document, Person user) {
        IacucProtocolDocument protocolDoc = (IacucProtocolDocument)document;
        List<ProtocolOnlineReviewBase> reviews = getIacucProtocolOnlineReviewService().getProtocolReviews(protocolDoc.getProtocol().getProtocolNumber());
        if (reviews != null && reviews.size() > 0) {
            for (ProtocolOnlineReviewBase review : reviews) {
                ProtocolReviewer reviewer = review.getProtocolReviewer();
                if (StringUtils.equalsIgnoreCase(reviewer.getPerson().getPersonId(), user.getPrincipalId())) {
                    if (isPrimarySecondary(reviewer)) {
                        return true;
                    } else if(isCommitteeAndPastDeterminationDueDate(reviewer, review)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public boolean canEditReviewAttachments(Document document, Person user) {
        IacucProtocolDocument protocolDoc = (IacucProtocolDocument)document;
        List<ProtocolOnlineReviewBase> reviews = getIacucProtocolOnlineReviewService().getProtocolReviews(protocolDoc.getProtocol().getProtocolNumber());
        if (reviews != null && reviews.size() > 0) {
            for (ProtocolOnlineReviewBase review : reviews) {
                ProtocolReviewer reviewer = review.getProtocolReviewer();
                if (StringUtils.equalsIgnoreCase(reviewer.getPerson().getPersonId(), user.getPrincipalId())) {
                    if (isPrimarySecondary(reviewer)) {
                        return true;
                    } else if(isCommitteeAndPastDeterminationDueDate(reviewer, review)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    protected boolean isPrimarySecondary(ProtocolReviewer reviewer) {
        return StringUtils.equalsIgnoreCase(reviewer.getReviewerTypeCode(), IacucProtocolReviewerType.PRIMARY) || 
                StringUtils.equalsIgnoreCase(reviewer.getReviewerTypeCode(), IacucProtocolReviewerType.SECONDARY);
    }
    
    protected boolean isCommitteeAndPastDeterminationDueDate(ProtocolReviewer reviewer, ProtocolOnlineReviewBase review) {
        if(StringUtils.equalsIgnoreCase(reviewer.getReviewerTypeCode(), IacucProtocolReviewerType.COMMITTEE)) {
            Date determinationDueDate = ((IacucProtocolOnlineReview) review.getProtocolOnlineReviewDocument().getProtocolOnlineReview()).getDeterminationReviewDateDue();
            if (determinationDueDate != null && Calendar.getInstance().getTime().after(determinationDueDate)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * @see org.kuali.rice.kns.document.authorization.DocumentAuthorizer#canInitiate(java.lang.String, org.kuali.rice.kim.api.identity.Person)
     */
    public boolean canInitiate(String documentTypeName, Person user) {
        if (GlobalVariables.getUserSession().getObjectMap().get(ProtocolAmendRenewService.AMEND_RENEW_ALLOW_NEW_PROTOCOL_DOCUMENT) != null) {
            GlobalVariables.getUserSession().removeObject(ProtocolAmendRenewService.AMEND_RENEW_ALLOW_NEW_PROTOCOL_DOCUMENT);
            return true;
        }
        return canCreateIacucProtocol(user);
    }

    /**
     * @see org.kuali.rice.kns.document.authorization.DocumentAuthorizer#canOpen(org.kuali.rice.krad.document.Document, org.kuali.rice.kim.api.identity.Person)
     */
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
     * @param username the user's username
     * @param doc the Iacuc Protocol document
     * @param taskName the name of the task
     * @return true if has permission; otherwise false
     */
    private boolean canExecuteIacucProtocolTask(String userId, IacucProtocolDocument doc, String taskName) {
        IacucProtocolTask task = new IacucProtocolTask(taskName, doc.getIacucProtocol());       
        TaskAuthorizationService taskAuthenticationService = KcServiceLocator.getService(TaskAuthorizationService.class);
        return taskAuthenticationService.isAuthorized(userId, task);
    }
    
    /**
     * @see org.kuali.coeus.sys.framework.auth.KcTransactionalDocumentAuthorizerBase#canEdit(org.kuali.rice.krad.document.Document, org.kuali.rice.kim.api.identity.Person)
     */
    @Override
    public boolean canEdit(Document document, Person user) {
        return canExecuteIacucProtocolTask(user.getPrincipalId(), (IacucProtocolDocument) document, TaskName.MODIFY_IACUC_PROTOCOL);
    }
    
    /**
     * @see org.kuali.coeus.sys.framework.auth.KcTransactionalDocumentAuthorizerBase#canSave(org.kuali.rice.krad.document.Document, org.kuali.rice.kim.api.identity.Person)
     */
    @Override
    public boolean canSave(Document document, Person user) {
        return canEdit(document, user);
    }
    
    @Override
    public boolean canClose(Document document, Person user) {
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
    
    protected IacucProtocolOnlineReviewService getIacucProtocolOnlineReviewService() {
        return KcServiceLocator.getService(IacucProtocolOnlineReviewService.class);
    }
}

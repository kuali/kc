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
package org.kuali.kra.irb.actions.genericactions;

import java.sql.Timestamp;

import org.kuali.kra.common.notification.service.KcNotificationService;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolVersionService;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.assignagenda.ProtocolAssignToAgendaService;
import org.kuali.kra.irb.actions.correspondence.ProtocolActionCorrespondenceGenerationService;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.notification.IRBNotificationContext;
import org.kuali.kra.irb.notification.IRBNotificationRenderer;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService;
import org.kuali.kra.printing.PrintingException;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

/**
 * This class handles the generic actions that can be made to a protocol.  A generic action contain a comment, action date, and a 
 * state change.
 */
public class ProtocolGenericActionServiceImpl implements ProtocolGenericActionService {
    
    private ProtocolActionService protocolActionService;
    private DocumentService documentService;
    private ProtocolActionCorrespondenceGenerationService protocolActionCorrespondenceGenerationService;
    private ProtocolOnlineReviewService protocolOnlineReviewService;
    private ProtocolVersionService protocolVersionService;
    private ProtocolAssignToAgendaService protocolAssignToAgendaService;
    private BusinessObjectService businessObjectService;
    private KcNotificationService kcNotificationService;
    
    /**{@inheritDoc}**/
    public void close(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        if (ProtocolActionType.REQUEST_TO_CLOSE.equals(protocol.getLastProtocolAction().getProtocolActionType().getProtocolActionTypeCode())) {
          //if previous action is request to close then the new status is closed by investigator
            performGenericAction(protocol, actionBean, ProtocolActionType.CLOSED_ADMINISTRATIVELY_CLOSED, ProtocolStatus.CLOSED_BY_INVESTIGATOR);
        } else {
          //else closed administratively
            performGenericAction(protocol, actionBean, ProtocolActionType.CLOSED_ADMINISTRATIVELY_CLOSED, ProtocolStatus.CLOSED_ADMINISTRATIVELY);
        }
    }
    
    /**{@inheritDoc}**/
    public void closeEnrollment(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        performGenericAction(protocol, actionBean, ProtocolActionType.CLOSED_FOR_ENROLLMENT, ProtocolStatus.ACTIVE_CLOSED_TO_ENROLLMENT);
    }
    
    /**{@inheritDoc}**/
    public ProtocolDocument defer(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        performGenericAction(protocol, actionBean, ProtocolActionType.DEFERRED, ProtocolStatus.DEFERRED);
//        IRBNotificationRenderer renderer = new IRBNotificationRenderer(protocol);
//        IRBNotificationContext context = new IRBNotificationContext(protocol, ProtocolActionType.DEFERRED, "Deferred", renderer);
//        if (!isPromptUserForNotification) {
//            kcNotificationService.sendNotification(context);
//        }
        
        return getDeferredVersionedDocument(protocol);
    }
    
    /**{@inheritDoc}**/
    public void disapprove(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        performGenericAction(protocol, actionBean, ProtocolActionType.DISAPPROVED, ProtocolStatus.DISAPPROVED);
        performDisapprove(protocol);
    }
    
    /**{@inheritDoc}**/
    public void expire(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        performGenericAction(protocol, actionBean, ProtocolActionType.EXPIRED, ProtocolStatus.EXPIRED);
    }
    
    /**{@inheritDoc}**/
    public void irbAcknowledgement(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        performGenericAction(protocol, actionBean, ProtocolActionType.IRB_ACKNOWLEDGEMENT);
//        IRBNotificationRenderer renderer = new IRBNotificationRenderer(protocol);
//        IRBNotificationContext context = new IRBNotificationContext(protocol, ProtocolActionType.IRB_ACKNOWLEDGEMENT, "IRB Acknowledgement", renderer);
//        kcNotificationService.sendNotification(context);
    }

    /**{@inheritDoc}**/
    public void permitDataAnalysis(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        performGenericAction(protocol, actionBean, ProtocolActionType.DATA_ANALYSIS_ONLY, ProtocolStatus.ACTIVE_DATA_ANALYSIS_ONLY);
    }

    /**{@inheritDoc}**/
    public void reopenEnrollment(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        performGenericAction(protocol, actionBean, ProtocolActionType.REOPEN_ENROLLMENT, ProtocolStatus.ACTIVE_OPEN_TO_ENROLLMENT);
    }
    
    /**{@inheritDoc}**/
    public ProtocolDocument returnForSMR(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        performGenericAction(protocol, actionBean, ProtocolActionType.SPECIFIC_MINOR_REVISIONS_REQUIRED, ProtocolStatus.SPECIFIC_MINOR_REVISIONS_REQUIRED);
//        IRBNotificationRenderer renderer = new IRBNotificationRenderer(protocol);
//        IRBNotificationContext context = new IRBNotificationContext(protocol, ProtocolActionType.SPECIFIC_MINOR_REVISIONS_REQUIRED, "Specific Minor Revisions Required", renderer);
//        kcNotificationService.sendNotification(context);

        return getReturnedVersionedDocument(protocol);
    }
    
    /**{@inheritDoc}**/
    public ProtocolDocument returnForSRR(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        performGenericAction(protocol, actionBean, ProtocolActionType.SUBSTANTIVE_REVISIONS_REQUIRED, ProtocolStatus.SUBSTANTIVE_REVISIONS_REQUIRED);
//        IRBNotificationRenderer renderer = new IRBNotificationRenderer(protocol);
//        IRBNotificationContext context = new IRBNotificationContext(protocol, ProtocolActionType.SUBSTANTIVE_REVISIONS_REQUIRED, "Substantive Revisions Required", renderer);
//        kcNotificationService.sendNotification(context);

        return getReturnedVersionedDocument(protocol);
    }
    
    /**{@inheritDoc}**/
    public void suspend(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        if (ProtocolActionType.REQUEST_FOR_SUSPENSION.equals(protocol.getLastProtocolAction().getProtocolActionType().getProtocolActionTypeCode())) {
            //if previous action is request to suspend then the new status is suspend by investigator
            performGenericAction(protocol, actionBean, ProtocolActionType.SUSPENDED, ProtocolStatus.SUSPENDED_BY_PI);
        } else {
            //else suspend by IRB
            performGenericAction(protocol, actionBean, ProtocolActionType.SUSPENDED, ProtocolStatus.SUSPENDED_BY_IRB);
        }
    }
    
    /**{@inheritDoc}**/
    public void suspendByDsmb(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        performGenericAction(protocol, actionBean, ProtocolActionType.SUSPENDED_BY_DSMB, ProtocolStatus.SUSPENDED_BY_DSMB);
    }
    
    /**{@inheritDoc}**/
    public void terminate(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        performGenericAction(protocol, actionBean, ProtocolActionType.TERMINATED, ProtocolStatus.TERMINATED_BY_IRB);
    }
    
    /**
     * Performs the generic action, which includes a state change, action date, and a comment, that's it!
     * @param protocol
     * @param actionBean
     * @param protocolActionType
     * @param newProtocolStatus
     * @throws Exception
     */
    private void performGenericAction(Protocol protocol, ProtocolGenericActionBean actionBean, String protocolActionType, String newProtocolStatus) 
        throws Exception {
        
        ProtocolAction protocolAction = createProtocolActionAndAttach(protocol, actionBean, protocolActionType);
        
        protocolActionService.updateProtocolStatus(protocolAction, protocol);
        protocol.setProtocolStatusCode(newProtocolStatus);
        protocol.refreshReferenceObject("protocolStatus");
        documentService.saveDocument(protocol.getProtocolDocument());
        createCorrespondenceAndAttach(protocol, protocolActionType);
    }
    
    private void performGenericAction(Protocol protocol, ProtocolGenericActionBean actionBean, String protocolActionType) throws Exception {
        ProtocolAction protocolAction = createProtocolActionAndAttach(protocol, actionBean, protocolActionType);
        
        if (protocol.getNotifyIrbSubmissionId() == null) {
            protocolActionService.updateProtocolStatus(protocolAction, protocol);
        } else {
            for (ProtocolSubmission submission : protocol.getProtocolSubmissions()) {
                if (submission.getSubmissionId().equals(protocol.getNotifyIrbSubmissionId())) {
                    submission.setSubmissionStatusCode(ProtocolSubmissionStatus.IRB_ACKNOWLEDGEMENT);
                }
            }
        }
        
        protocol.refreshReferenceObject("protocolStatus");
        documentService.saveDocument(protocol.getProtocolDocument());
    }
    
    private ProtocolAction createProtocolActionAndAttach(Protocol protocol, ProtocolGenericActionBean actionBean, String protocolActionType) {
        ProtocolAction protocolAction = new ProtocolAction(protocol, null, protocolActionType);
        protocolAction.setComments(actionBean.getComments());
        protocolAction.setActionDate(new Timestamp(actionBean.getActionDate().getTime()));
        protocol.getProtocolActions().add(protocolAction);
        
        return protocolAction;
    }
    
    private void createCorrespondenceAndAttach(Protocol protocol, String protocolActionType) throws PrintingException {
        ProtocolGenericCorrespondence correspondence = new ProtocolGenericCorrespondence(protocolActionType);
        correspondence.setPrintableBusinessObject(protocol);
        correspondence.setProtocol(protocol);
        protocolActionCorrespondenceGenerationService.generateCorrespondenceDocumentAndAttach(correspondence);
    }
        
    private void performDisapprove(Protocol protocol) throws Exception {
        if (protocol.getProtocolDocument() != null) {
            WorkflowDocument currentWorkflowDocument = protocol.getProtocolDocument().getDocumentHeader().getWorkflowDocument();
            if (currentWorkflowDocument != null) {
                currentWorkflowDocument.disapprove("Protocol document disapproved after committee decision");
            }
        }
        protocolOnlineReviewService.cancelOnlineReviews(protocol.getProtocolSubmission(), "Protocol Review cancelled - protocol has been disapproved.");
    }
    
    private ProtocolDocument getDeferredVersionedDocument(Protocol protocol) throws Exception {
        documentService.cancelDocument(protocol.getProtocolDocument(), "Protocol document cancelled - protocol has been deferred.");
        protocolOnlineReviewService.cancelOnlineReviews(protocol.getProtocolSubmission(), "Protocol Review cancelled - protocol has been deferred.");
        
        ProtocolDocument newDocument = getVersionedDocument(protocol);

        ProtocolAction assignToAgendaProtocolAction = protocolAssignToAgendaService.getAssignedToAgendaProtocolAction(newDocument.getProtocol());
        if (assignToAgendaProtocolAction != null) {
            newDocument.getProtocol().getProtocolActions().remove(assignToAgendaProtocolAction);
            businessObjectService.delete(assignToAgendaProtocolAction);
        }
        newDocument.getProtocol().refreshReferenceObject("protocolStatus");
        documentService.saveDocument(newDocument);
        
        return newDocument;
    }
    
    private ProtocolDocument getReturnedVersionedDocument(Protocol protocol) throws Exception {
        documentService.cancelDocument(protocol.getProtocolDocument(), "Protocol document cancelled - protocol has been returned for revisions.");
        protocolOnlineReviewService.finalizeOnlineReviews(protocol.getProtocolSubmission(), 
                "Protocol Review finalized - protocol has been returned for revisions.");
        
        return getVersionedDocument(protocol);
    }
    
    private ProtocolDocument getVersionedDocument(Protocol protocol) throws Exception {
        ProtocolDocument newDocument = protocolVersionService.versionProtocolDocument(protocol.getProtocolDocument());
//        protocolOnlineReviewService.moveOnlineReviews(protocol.getProtocolSubmission(), newDocument.getProtocol().getProtocolSubmission());
        newDocument.getProtocol().setProtocolSubmission(null);
        newDocument.getProtocol().setApprovalDate(null);
        newDocument.getProtocol().setLastApprovalDate(null);
        newDocument.getProtocol().setExpirationDate(null);
        
        newDocument.getProtocol().refreshReferenceObject("protocolStatus");
        documentService.saveDocument(newDocument);
        
        return newDocument;
    }

    public void setProtocolActionService(ProtocolActionService protocolActionService) {
        this.protocolActionService = protocolActionService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    public void setProtocolActionCorrespondenceGenerationService(ProtocolActionCorrespondenceGenerationService protocolActionCorrespondenceGenerationService) {
        this.protocolActionCorrespondenceGenerationService = protocolActionCorrespondenceGenerationService;
    }

    public void setProtocolOnlineReviewService(ProtocolOnlineReviewService protocolOnlineReviewService) {
        this.protocolOnlineReviewService = protocolOnlineReviewService;
    }

    public void setProtocolVersionService(ProtocolVersionService protocolVersionService) {
        this.protocolVersionService = protocolVersionService;
    }

    public void setProtocolAssignToAgendaService(ProtocolAssignToAgendaService protocolAssignToAgendaService) {
        this.protocolAssignToAgendaService = protocolAssignToAgendaService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    public void setKcNotificationService(KcNotificationService kcNotificationService) {
        this.kcNotificationService = kcNotificationService;
    }

}
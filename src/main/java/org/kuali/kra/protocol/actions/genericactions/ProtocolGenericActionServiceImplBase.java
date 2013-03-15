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
package org.kuali.kra.protocol.actions.genericactions;

import java.sql.Timestamp;
import org.kuali.kra.common.notification.service.KcNotificationService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.ProtocolVersionService;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.assignagenda.ProtocolAssignToAgendaService;
import org.kuali.kra.protocol.actions.correspondence.ProtocolActionCorrespondenceGenerationService;
import org.kuali.kra.protocol.actions.correspondence.ProtocolActionsCorrespondenceBase;
import org.kuali.kra.protocol.actions.submit.ProtocolActionService;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewService;
import org.kuali.rice.kew.actiontaken.ActionTakenValue;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

/**
 * This class handles the generic actions that can be made to a protocol.  A generic action contain a comment, action date, and a 
 * state change.
 */
public abstract class ProtocolGenericActionServiceImplBase implements ProtocolGenericActionService {
    
    private static final String PROTOCOL_SUBMISSION = "protocolSubmission";
    private ProtocolActionService protocolActionService;
    private DocumentService documentService;
      
    private ProtocolActionCorrespondenceGenerationService protocolActionCorrespondenceGenerationService;
    
    
    private ProtocolOnlineReviewService protocolOnlineReviewService;
    private ProtocolVersionService protocolVersionService;
    private ProtocolAssignToAgendaService protocolAssignToAgendaService;
    private BusinessObjectService businessObjectService;
    private KcNotificationService kcNotificationService;
    
// TODO *********commented the code below during IACUC refactoring*********     
//    
//  All of these service methods have been pushed down to the subclasses since they are quite closely tied to the type of protocol, and 
//  would require too many hooks to customize and reuse.  
//    
//    /**{@inheritDoc}**/
//    public void close(ProtocolBase protocol, ProtocolGenericActionBean actionBean) throws Exception {
//        if (ProtocolActionType.REQUEST_TO_CLOSE.equals(protocol.getLastProtocolAction().getProtocolActionType().getProtocolActionTypeCode())) {
//          //if previous action is request to close then the new status is closed by investigator
//            performGenericAction(protocol, actionBean, ProtocolActionType.CLOSED_ADMINISTRATIVELY_CLOSED, ProtocolStatusBase.CLOSED_BY_INVESTIGATOR);
//        } else {
//          //else closed administratively
//            performGenericAction(protocol, actionBean, ProtocolActionType.CLOSED_ADMINISTRATIVELY_CLOSED, ProtocolStatusBase.CLOSED_ADMINISTRATIVELY);
//        }
//    }
//    
//    /**{@inheritDoc}**/
//    public void closeEnrollment(ProtocolBase protocol, ProtocolGenericActionBean actionBean) throws Exception {
//        performGenericAction(protocol, actionBean, ProtocolActionType.CLOSED_FOR_ENROLLMENT, ProtocolStatusBase.ACTIVE_CLOSED_TO_ENROLLMENT);
//    }
//    
//    /**{@inheritDoc}**/
//    public ProtocolDocumentBase defer(ProtocolBase protocol, ProtocolGenericActionBean actionBean) throws Exception {
//        performGenericAction(protocol, actionBean, ProtocolActionType.DEFERRED, ProtocolStatusBase.DEFERRED);
////        IRBNotificationRenderer renderer = new IRBNotificationRenderer(protocol);
////        IRBNotificationContext context = new IRBNotificationContext(protocol, ProtocolActionType.DEFERRED, "Deferred", renderer);
////        if (!isPromptUserForNotification) {
////            kcNotificationService.sendNotification(context);
////        }
//        
//        return getDeferredVersionedDocument(protocol);
//    }
//    
//    /**{@inheritDoc}**/
//    public void disapprove(ProtocolBase protocol, ProtocolGenericActionBean actionBean) throws Exception {
//        performGenericAction(protocol, actionBean, ProtocolActionType.DISAPPROVED, ProtocolStatusBase.DISAPPROVED);
//        performDisapprove(protocol);
//    }
//    
//    /**{@inheritDoc}**/
//    public void expire(ProtocolBase protocol, ProtocolGenericActionBean actionBean) throws Exception {
//        performGenericAction(protocol, actionBean, ProtocolActionType.EXPIRED, ProtocolStatusBase.EXPIRED);
//    }
//    
//    /**{@inheritDoc}**/
//    public void irbAcknowledgement(ProtocolBase protocol, ProtocolGenericActionBean actionBean) throws Exception {
//        performGenericAction(protocol, actionBean, ProtocolActionType.IRB_ACKNOWLEDGEMENT);
////        IRBNotificationRenderer renderer = new IRBNotificationRenderer(protocol);
////        IRBNotificationContext context = new IRBNotificationContext(protocol, ProtocolActionType.IRB_ACKNOWLEDGEMENT, "IRB Acknowledgement", renderer);
////        kcNotificationService.sendNotification(context);
//    }
//
//    /**{@inheritDoc}**/
//    public void permitDataAnalysis(ProtocolBase protocol, ProtocolGenericActionBean actionBean) throws Exception {
//        performGenericAction(protocol, actionBean, ProtocolActionType.DATA_ANALYSIS_ONLY, ProtocolStatusBase.ACTIVE_DATA_ANALYSIS_ONLY);
//    }
//
//    /**{@inheritDoc}**/
//    public void reopenEnrollment(ProtocolBase protocol, ProtocolGenericActionBean actionBean) throws Exception {
//        performGenericAction(protocol, actionBean, ProtocolActionType.REOPEN_ENROLLMENT, ProtocolStatusBase.ACTIVE_OPEN_TO_ENROLLMENT);
//    }
//    
//    /**{@inheritDoc}**/
//    public ProtocolDocumentBase returnForSMR(ProtocolBase protocol, ProtocolGenericActionBean actionBean) throws Exception {
//        performGenericAction(protocol, actionBean, ProtocolActionType.SPECIFIC_MINOR_REVISIONS_REQUIRED, ProtocolStatusBase.SPECIFIC_MINOR_REVISIONS_REQUIRED);
////        IRBNotificationRenderer renderer = new IRBNotificationRenderer(protocol);
////        IRBNotificationContext context = new IRBNotificationContext(protocol, ProtocolActionType.SPECIFIC_MINOR_REVISIONS_REQUIRED, "Specific Minor Revisions Required", renderer);
////        kcNotificationService.sendNotification(context);
//
//        return getReturnedVersionedDocument(protocol);
//    }
//    
//    /**{@inheritDoc}**/
//    public ProtocolDocumentBase returnForSRR(ProtocolBase protocol, ProtocolGenericActionBean actionBean) throws Exception {
//        performGenericAction(protocol, actionBean, ProtocolActionType.SUBSTANTIVE_REVISIONS_REQUIRED, ProtocolStatusBase.SUBSTANTIVE_REVISIONS_REQUIRED);
////        IRBNotificationRenderer renderer = new IRBNotificationRenderer(protocol);
////        IRBNotificationContext context = new IRBNotificationContext(protocol, ProtocolActionType.SUBSTANTIVE_REVISIONS_REQUIRED, "Substantive Revisions Required", renderer);
////        kcNotificationService.sendNotification(context);
//
//        return getReturnedVersionedDocument(protocol);
//    }
//    
//    /**{@inheritDoc}**/
//    public void suspend(ProtocolBase protocol, ProtocolGenericActionBean actionBean) throws Exception {
//        if (ProtocolActionType.REQUEST_FOR_SUSPENSION.equals(protocol.getLastProtocolAction().getProtocolActionType().getProtocolActionTypeCode())) {
//            //if previous action is request to suspend then the new status is suspend by investigator
//            performGenericAction(protocol, actionBean, ProtocolActionType.SUSPENDED, ProtocolStatusBase.SUSPENDED_BY_PI);
//        } else {
//            //else suspend by IRB
//            performGenericAction(protocol, actionBean, ProtocolActionType.SUSPENDED, ProtocolStatusBase.SUSPENDED_BY_IRB);
//        }
//    }
//    
//    /**{@inheritDoc}**/
//    public void suspendByDsmb(ProtocolBase protocol, ProtocolGenericActionBean actionBean) throws Exception {
//        performGenericAction(protocol, actionBean, ProtocolActionType.SUSPENDED_BY_DSMB, ProtocolStatusBase.SUSPENDED_BY_DSMB);
//    }
//    
//    /**{@inheritDoc}**/
//    public void terminate(ProtocolBase protocol, ProtocolGenericActionBean actionBean) throws Exception {
//        performGenericAction(protocol, actionBean, ProtocolActionType.TERMINATED, ProtocolStatusBase.TERMINATED_BY_IRB);
//    }
    
    /**
     * Performs the generic action, which includes a state change, action date, and a comment, that's it!
     * @param protocol
     * @param actionBean
     * @param protocolActionType
     * @param newProtocolStatus
     * @throws Exception
     */
    protected void performGenericAction(ProtocolBase protocol, ProtocolGenericActionBean actionBean, String protocolActionType, String newProtocolStatus) throws Exception {
        
        ProtocolActionBase protocolAction = createProtocolActionAndAttach(protocol, actionBean, protocolActionType);
        
        protocolActionService.updateProtocolStatus(protocolAction, protocol);
        protocol.setProtocolStatusCode(newProtocolStatus);
        protocol.refreshReferenceObject("protocolStatus");
        documentService.saveDocument(protocol.getProtocolDocument());
                 
        createCorrespondenceAndAttach(protocol, protocolActionType);
    }

    
    
    
// TODO *********commented the code below during IACUC refactoring*********     
//    private void performGenericAction(ProtocolBase protocol, ProtocolGenericActionBean actionBean, String protocolActionType) throws Exception {
//        ProtocolActionBase protocolAction = createProtocolActionAndAttach(protocol, actionBean, protocolActionType);
//        
//        if (protocol.getNotifyIrbSubmissionId() == null) {
//            protocolActionService.updateProtocolStatus(protocolAction, protocol);
//        } else {
//            for (ProtocolSubmissionBase submission : protocol.getProtocolSubmissions()) {
//                if (submission.getSubmissionId().equals(protocol.getNotifyIrbSubmissionId())) {
//                    submission.setSubmissionStatusCode(ProtocolSubmissionStatus.IRB_ACKNOWLEDGEMENT);
//                }
//            }
//        }
//        
//        protocol.refreshReferenceObject("protocolStatus");
//        documentService.saveDocument(protocol.getProtocolDocument());
//    }
    
    
    
    
    protected ProtocolActionBase createProtocolActionAndAttach(ProtocolBase protocol, ProtocolGenericActionBean actionBean, String protocolActionType) {
     // TODO ********************** added or modified during IRB backfit merge BEGIN ********************** 
        protocol.refreshReferenceObject("protocolSubmission");
     // TODO ********************** added or modified during IRB backfit merge END ************************
        ProtocolActionBase protocolAction = getNewProtocolActionInstanceHook(protocol, null, protocolActionType);
        protocolAction.setComments(actionBean.getComments());
        protocolAction.setActionDate(new Timestamp(actionBean.getActionDate().getTime()));
        protocol.getProtocolActions().add(protocolAction);
        
        return protocolAction;
    }
    
    protected abstract ProtocolActionBase getNewProtocolActionInstanceHook(ProtocolBase protocol, ProtocolSubmissionBase submission, String protocolActionType);
    
    protected abstract ProtocolActionsCorrespondenceBase getNewProtocolActionsCorrespondenceHook(String protocolActionType);

    protected void createCorrespondenceAndAttach(ProtocolBase protocol, String protocolActionType) throws PrintingException {
        ProtocolActionsCorrespondenceBase correspondence = getNewProtocolActionsCorrespondenceHook(protocolActionType);
        correspondence.setPrintableBusinessObject(protocol);
        correspondence.setProtocol(protocol);
        protocolActionCorrespondenceGenerationService.generateCorrespondenceDocumentAndAttach(correspondence);
    }
       
    
    
    protected void performDisapprove(ProtocolBase protocol) throws Exception {
        if (protocol.getProtocolDocument() != null) {
            WorkflowDocument currentWorkflowDocument = protocol.getProtocolDocument().getDocumentHeader().getWorkflowDocument();
            if (currentWorkflowDocument != null) {
                currentWorkflowDocument.disapprove("ProtocolBase document disapproved after committee decision");
            }
        }
// TODO *********commented the code below during IACUC refactoring*********         
//        protocolOnlineReviewService.cancelOnlineReviews(protocol.getProtocolSubmission(), "ProtocolBase Review cancelled - protocol has been disapproved.");
        
    }
    
    
// TODO *********commented the code below during IACUC refactoring*********     
//    protected ProtocolDocumentBase getDeferredVersionedDocument(ProtocolBase protocol) throws Exception {
//        documentService.cancelDocument(protocol.getProtocolDocument(), "ProtocolBase document cancelled - protocol has been deferred.");
//        protocolOnlineReviewService.cancelOnlineReviews(protocol.getProtocolSubmission(), "ProtocolBase Review cancelled - protocol has been deferred.");
//        ProtocolDocumentBase newDocument = getVersionedDocument(protocol);
//
//        ProtocolActionBase assignToAgendaProtocolAction = protocolAssignToAgendaService.getAssignedToAgendaProtocolAction(newDocument.getProtocol());
//        if (assignToAgendaProtocolAction != null) {
//            newDocument.getProtocol().getProtocolActions().remove(assignToAgendaProtocolAction);
//            businessObjectService.delete(assignToAgendaProtocolAction);
//        }
//        newDocument.getProtocol().refreshReferenceObject("protocolStatus");
//        documentService.saveDocument(newDocument);
//        
//        return newDocument;
//    }
    
    
    
    protected ProtocolDocumentBase getReturnedVersionedDocument(ProtocolBase protocol) throws Exception {
        documentService.cancelDocument(protocol.getProtocolDocument(), "ProtocolBase document cancelled - protocol has been returned for revisions.");
        
// TODO *********commented the code below during IACUC refactoring*********         
//        protocolOnlineReviewService.finalizeOnlineReviews(protocol.getProtocolSubmission(), 
//                "ProtocolBase Review finalized - protocol has been returned for revisions.");
        
        return getVersionedDocument(protocol);
    }
    
    


    @Override
    public void recordDisapprovedInRoutingActionAndUpdateStatuses(ProtocolBase protocol, ActionTakenValue latestCurrentActionTakenVal) {
        // add the action to the action history
        ProtocolActionBase protocolAction = getNewDisapprovedInRoutingProtocolActionInstanceHook(protocol);
        protocolAction.setComments(latestCurrentActionTakenVal.getAnnotation());
        protocolAction.setActionDate(latestCurrentActionTakenVal.getActionDate());
        protocol.getProtocolActions().add(protocolAction);
        // update the statuses and persist the protocol
        protocol.setProtocolStatusCode(getDisapprovedProtocolStatusCodeHook());
        protocol.refreshReferenceObject(Constants.PROPERTY_PROTOCOL_STATUS);
        protocol.getProtocolSubmission().setSubmissionStatusCode(getProtocolSubmissionStatusRejectedInRoutingCodeHook());
        protocol.refreshReferenceObject(PROTOCOL_SUBMISSION);
        this.getBusinessObjectService().save(protocol);
    }
    
    protected abstract String getProtocolSubmissionStatusRejectedInRoutingCodeHook();

    protected abstract String getDisapprovedProtocolStatusCodeHook();

    protected abstract ProtocolActionBase getNewDisapprovedInRoutingProtocolActionInstanceHook(ProtocolBase protocol);


    @Override
    public ProtocolDocumentBase versionAfterDisapprovalInRouting(ProtocolBase oldProtocol) throws Exception {
        // the new document version will be persisted along with the new version of the old protocol instance
        ProtocolDocumentBase newDocument = getVersionedDocument(oldProtocol);
        
        // set the 'pending/in progress' status for the new protocol 
        ProtocolBase newProtocol = newDocument.getProtocol();
        newProtocol.setProtocolStatusCode(getProtocolPendingInProgressStatusCodeHook());
        newProtocol.refreshReferenceObject(Constants.PROPERTY_PROTOCOL_STATUS);
        
        // set the submission status to disapproved in routing
        newProtocol.getProtocolSubmission().setSubmissionStatusCode(getProtocolSubmissionStatusRejectedInRoutingCodeHook());
        newProtocol.refreshReferenceObject(PROTOCOL_SUBMISSION);
        // finally save the protocol (and its submission)
        this.getBusinessObjectService().save(newProtocol);
        documentService.saveDocument(newDocument);
        
        return newDocument;
    }
    

    protected abstract String getProtocolPendingInProgressStatusCodeHook();


    
    protected ProtocolDocumentBase getVersionedDocument(ProtocolBase protocol) throws Exception {
        ProtocolDocumentBase newDocument = protocolVersionService.versionProtocolDocument(protocol.getProtocolDocument());
//        protocolOnlineReviewService.moveOnlineReviews(protocol.getProtocolSubmission(), newDocument.getProtocol().getProtocolSubmission());
        newDocument.getProtocol().setProtocolSubmission(null);
        newDocument.getProtocol().setApprovalDate(null);
        newDocument.getProtocol().setLastApprovalDate(null);
        newDocument.getProtocol().setExpirationDate(null);
        
        newDocument.getProtocol().refreshReferenceObject(Constants.PROPERTY_PROTOCOL_STATUS);
     // TODO ********************** added or modified during IRB backfit merge BEGIN ********************** 
        newDocument.getProtocol().refreshReferenceObject(PROTOCOL_SUBMISSION);
     // TODO ********************** added or modified during IRB backfit merge END ************************
        documentService.saveDocument(newDocument);
        
        return newDocument;
    }
    
    protected abstract String getRecallProtocolActionTypeCodeHook();
    
    public void recall(ProtocolBase protocol) {
        ProtocolActionBase protocolAction = getNewProtocolActionInstanceHook(protocol, null, getRecallProtocolActionTypeCodeHook());
        protocolAction.setComments("Recalled in Routing");
        protocol.getProtocolActions().add(protocolAction);
        getProtocolActionService().updateProtocolStatus(protocolAction, protocol);
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




    protected ProtocolActionService getProtocolActionService() {
        return protocolActionService;
    }




    protected DocumentService getDocumentService() {
        return documentService;
    }




    protected ProtocolActionCorrespondenceGenerationService getProtocolActionCorrespondenceGenerationService() {
        return protocolActionCorrespondenceGenerationService;
    }




    protected ProtocolOnlineReviewService getProtocolOnlineReviewService() {
        return protocolOnlineReviewService;
    }




    protected ProtocolVersionService getProtocolVersionService() {
        return protocolVersionService;
    }




    protected ProtocolAssignToAgendaService getProtocolAssignToAgendaService() {
        return protocolAssignToAgendaService;
    }




    protected BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }




    protected KcNotificationService getKcNotificationService() {
        return kcNotificationService;
    }

}

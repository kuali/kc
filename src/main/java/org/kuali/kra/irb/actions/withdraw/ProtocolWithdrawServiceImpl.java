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
package org.kuali.kra.irb.actions.withdraw;

import java.sql.Date;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.common.notification.service.KcNotificationService;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.assignagenda.ProtocolAssignToAgendaService;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.actions.withdraw.ProtocolAdministrativelyIncompleteBean;
import org.kuali.kra.protocol.actions.withdraw.ProtocolAdministrativelyWithdrawBean;
import org.kuali.kra.protocol.actions.withdraw.ProtocolWithdrawBean;
import org.kuali.kra.protocol.actions.withdraw.ProtocolWithdrawServiceImplBase;

/**
 * The ProtocolWithdrawService implementation.
 */
public class ProtocolWithdrawServiceImpl extends ProtocolWithdrawServiceImplBase implements ProtocolWithdrawService {

    private ProtocolAssignToAgendaService protocolAssignToAgendaService;
    private KcNotificationService kcNotificationService;
    
    @Override
    public ProtocolDocumentBase administrativelyMarkIncomplete(ProtocolBase protocol, ProtocolAdministrativelyIncompleteBean markIncompleteBean) throws Exception {
        // TODO yet to implement this functionality in IRB, maybe after backfit we can refactor the withdraw method into a commonWithdrawLogic as is done for IACUC service impl.
        return null;
    }

    
    @Override
    public ProtocolDocumentBase administrativelyWithdraw(ProtocolBase protocol, ProtocolAdministrativelyWithdrawBean administrativelyWithdrawBean) throws Exception {
        // TODO yet to implement this functionality in IRB, maybe after backfit we can refactor the withdraw method into a commonWithdrawLogic as is done for IACUC service impl.
        return null;
    }
    
    
    @Override
    public ProtocolDocumentBase withdraw(ProtocolBase protocol, ProtocolWithdrawBean withdrawBean) throws Exception {
        ProtocolSubmission submission = (ProtocolSubmission)getSubmission(protocol);
        ProtocolAction protocolAction = new ProtocolAction((Protocol)protocol, ProtocolActionType.WITHDRAWN);
        protocolAction.setComments(withdrawBean.getReason());
        protocol.getProtocolActions().add(protocolAction);

        boolean isVersion = ProtocolStatus.IN_PROGRESS.equals(protocol.getProtocolStatusCode())
              || ProtocolStatus.SUBMITTED_TO_IRB.equals(protocol.getProtocolStatusCode());
        protocolActionService.updateProtocolStatus(protocolAction, protocol);

       
        if (submission != null) {
            submission.setSubmissionDate(new Date(System.currentTimeMillis()));
            submission.setSubmissionStatusCode(ProtocolSubmissionStatus.WITHDRAWN);
            // need to finalize any outstanding review documents.
            protocolOnlineReviewService.finalizeOnlineReviews(submission, WITHDRAW_FINALIZE_OLR_ANNOTATION);
        }
        businessObjectService.save(protocol.getProtocolDocument());
//        IRBNotificationRenderer renderer = new IRBNotificationRenderer(protocol);
//        IRBNotificationContext context = new IRBNotificationContext(protocol, ProtocolActionType.WITHDRAWN, "Withdrawn", renderer);
//        /*
//         * TODO : has to pass notificationHelper in this method call because the 'getContext'
//         * method is based on protocol last action.  Here is the point that last action is created.
//         * There are other action is similar to this use case.  'assign to agenda' is different because 
//         * it is calling sendnotification in action class
//         */
//        if (!isPromptUserForNotification) {
//            kcNotificationService.sendNotification(context);
//        }
        
        if (isVersion) {
            /*
             * Cancelling the workflow document is how we withdraw it.
             */
            cancelWorkflow(protocol);

            
            /*
             * Create a new protocol document for the user to edit so they can re-submit at a later time.
             */
            ProtocolDocument newProtocolDocument = (ProtocolDocument)protocolVersionService.versionProtocolDocument(protocol.getProtocolDocument());
            newProtocolDocument.getProtocol().setProtocolStatusCode(ProtocolStatus.WITHDRAWN);
            // to force it to retrieve from list.
            newProtocolDocument.getProtocol().setProtocolSubmission(null);
            // update some info
            newProtocolDocument.getProtocol().setApprovalDate(null);
            newProtocolDocument.getProtocol().setLastApprovalDate(null);
            newProtocolDocument.getProtocol().setExpirationDate(null);

            // COEUS does not set these values to null for 'withdraw action
            // newProtocolDocument.getProtocol().getProtocolSubmission().setScheduleId(null);
            // newProtocolDocument.getProtocol().getProtocolSubmission().setCommitteeSchedule(null);
            // newProtocolDocument.getProtocol().getProtocolSubmission().setScheduleIdFk(null);
            // newProtocolDocument.getProtocol().getProtocolSubmission().setCommittee(null);
            // newProtocolDocument.getProtocol().getProtocolSubmission().setCommitteeId(null);
            // newProtocolDocument.getProtocol().getProtocolSubmission().setCommitteeIdFk(null);

            newProtocolDocument.getProtocol().refreshReferenceObject("protocolStatus");
            documentService.saveDocument(newProtocolDocument);

            // if there is an assign to agenda protocol action, remove it.
            ProtocolAction assignToAgendaProtocolAction = (ProtocolAction) protocolAssignToAgendaService
                    .getAssignedToAgendaProtocolAction(newProtocolDocument.getProtocol());
            if (assignToAgendaProtocolAction != null) {
                newProtocolDocument.getProtocol().getProtocolActions().remove(assignToAgendaProtocolAction);
                businessObjectService.delete(assignToAgendaProtocolAction);
            }
            newProtocolDocument.getProtocol().refreshReferenceObject("protocolStatus");
            documentService.saveDocument(newProtocolDocument);
            generateCorrespondenceDocumentAndAttach(newProtocolDocument.getProtocol(), withdrawBean);
            return newProtocolDocument;
        }
        //This is withdraw submission not protocol.  the withdraw correspondence is for 'protocol' now.
        //it's not suitable for withdraw protocol submission.        
        else {
            generateCorrespondenceDocumentAndAttach(protocol, withdrawBean);
        }
        return protocol.getProtocolDocument();
    }
    
    
    /**
     * Does the submission status allow us to withdraw the protocol?
     * @param submission
     * @return true if withdrawable; otherwise false
     */
    protected boolean isAllowedStatus(ProtocolSubmissionBase submission) {
        return StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.PENDING) ||
               StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
    }
    
    /**
     * Does the submission type allow us to withdraw the protocol?
     * @param submission
     * @return true if withdrawable; otherwise false
     */
    protected boolean isNormalSubmission(ProtocolSubmissionBase submission) {
        return StringUtils.equals(submission.getSubmissionTypeCode(), ProtocolSubmissionType.AMENDMENT) ||
               StringUtils.equals(submission.getSubmissionTypeCode(), ProtocolSubmissionType.INITIAL_SUBMISSION) ||
               StringUtils.equals(submission.getSubmissionTypeCode(), ProtocolSubmissionType.CONTINUATION) ||
               StringUtils.equals(submission.getSubmissionTypeCode(), ProtocolSubmissionType.CONTINUATION_WITH_AMENDMENT);
    }


    public ProtocolAssignToAgendaService getProtocolAssignToAgendaService() {
        return protocolAssignToAgendaService;
    }


    public void setProtocolAssignToAgendaService(ProtocolAssignToAgendaService protocolAssignToAgendaService) {
        this.protocolAssignToAgendaService = protocolAssignToAgendaService;
    }

    
 // TODO ********************** commented out during IRB backfit ************************
    
//    private static final Log LOG = LogFactory.getLog(ProtocolWithdrawServiceImpl.class);
//    private DocumentService documentService;
//    private BusinessObjectService businessObjectService;
//    private ProtocolActionService protocolActionService;
//    private ProtocolVersionService protocolVersionService;
//    private ProtocolAssignToAgendaService protocolAssignToAgendaService;
//    private ProtocolActionCorrespondenceGenerationService protocolActionCorrespondenceGenerationService;
//    private ProtocolOnlineReviewService protocolOnlineReviewService;
//    private IdentityService identityManagementService;
//    private KcNotificationService kcNotificationService;
//    
//    private static final String WITHDRAW_FINALIZE_OLR_ANNOTATION = "Online Review finalized as part of withdraw action on protocol.";
//    
//    /**
//     * Set the document service.
//     * @param documentService
//     */
//    public void setDocumentService(DocumentService documentService) {
//        this.documentService = documentService;
//    }
//    
//    /**
//     * Set the business object service.
//     * @param businessObjectService the business object service
//     */
//    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
//        this.businessObjectService = businessObjectService;
//    }
//    
//    public void setProtocolAssignToAgendaService(ProtocolAssignToAgendaService protocolAssignToAgendaService) {
//        this.protocolAssignToAgendaService = protocolAssignToAgendaService;
//    }
//    
//    public void setProtocolActionCorrespondenceGenerationService(ProtocolActionCorrespondenceGenerationService protocolActionCorrespondenceGenerationService) {
//        this.protocolActionCorrespondenceGenerationService = protocolActionCorrespondenceGenerationService;
//    }
//    
//    /**
//     * Set the Protocol Action Service.
//     * @param protocolActionService
//     */
//    public void setProtocolActionService(ProtocolActionService protocolActionService) {
//        this.protocolActionService = protocolActionService;
//    }
//    
//    /**
//     * Inject Protocol Version Service.
//     * @param protocolVersionService
//     */
//    public void setProtocolVersionService(ProtocolVersionService protocolVersionService) {
//        this.protocolVersionService = protocolVersionService;
//    }
//
//    /**
//     * @see org.kuali.kra.irb.actions.withdraw.ProtocolWithdrawService#withdraw(org.kuali.kra.irb.Protocol, org.kuali.kra.irb.actions.withdraw.ProtocolWithdrawBean)
//     */
//    public ProtocolDocument withdraw(Protocol protocol, ProtocolWithdrawBean withdrawBean) throws Exception {
//        ProtocolSubmission submission = getSubmission(protocol);
//        ProtocolAction protocolAction = new ProtocolAction(protocol, null, ProtocolActionType.WITHDRAWN);
//        protocolAction.setComments(withdrawBean.getReason());
//        protocol.getProtocolActions().add(protocolAction);
//
//        boolean isVersion = ProtocolStatus.IN_PROGRESS.equals(protocol.getProtocolStatusCode())
//              || ProtocolStatus.SUBMITTED_TO_IRB.equals(protocol.getProtocolStatusCode());
//        protocolActionService.updateProtocolStatus(protocolAction, protocol);
//
//       
//        if (submission != null) {
//            submission.setSubmissionDate(new Date(System.currentTimeMillis()));
//            submission.setSubmissionStatusCode(ProtocolSubmissionStatus.WITHDRAWN);
//            // need to finalize any outstanding review documents.
//            protocolOnlineReviewService.finalizeOnlineReviews(submission, WITHDRAW_FINALIZE_OLR_ANNOTATION);
//        }
//        businessObjectService.save(protocol.getProtocolDocument());
////        IRBNotificationRenderer renderer = new IRBNotificationRenderer(protocol);
////        IRBNotificationContext context = new IRBNotificationContext(protocol, ProtocolActionType.WITHDRAWN, "Withdrawn", renderer);
////        /*
////         * TODO : has to pass notificationHelper in this method call because the 'getContext'
////         * method is based on protocol last action.  Here is the point that last action is created.
////         * There are other action is similar to this use case.  'assign to agenda' is different because 
////         * it is calling sendnotification in action class
////         */
////        if (!isPromptUserForNotification) {
////            kcNotificationService.sendNotification(context);
////        }
//        
//        if (isVersion) {
//            /*
//             * Cancelling the workflow document is how we withdraw it.
//             */
//            cancelWorkflow(protocol);
//
//            
//            /*
//             * Create a new protocol document for the user to edit so they can re-submit at a later time.
//             */
//            ProtocolDocument newProtocolDocument = protocolVersionService.versionProtocolDocument(protocol.getProtocolDocument());
//            newProtocolDocument.getProtocol().setProtocolStatusCode(ProtocolStatus.WITHDRAWN);
//            // to force it to retrieve from list.
//            newProtocolDocument.getProtocol().setProtocolSubmission(null);
//            // update some info
//            newProtocolDocument.getProtocol().setApprovalDate(null);
//            newProtocolDocument.getProtocol().setLastApprovalDate(null);
//            newProtocolDocument.getProtocol().setExpirationDate(null);
//
//            // COEUS does not set these values to null for 'withdraw action
//            // newProtocolDocument.getProtocol().getProtocolSubmission().setScheduleId(null);
//            // newProtocolDocument.getProtocol().getProtocolSubmission().setCommitteeSchedule(null);
//            // newProtocolDocument.getProtocol().getProtocolSubmission().setScheduleIdFk(null);
//            // newProtocolDocument.getProtocol().getProtocolSubmission().setCommittee(null);
//            // newProtocolDocument.getProtocol().getProtocolSubmission().setCommitteeId(null);
//            // newProtocolDocument.getProtocol().getProtocolSubmission().setCommitteeIdFk(null);
//
//            newProtocolDocument.getProtocol().refreshReferenceObject("protocolStatus");
//            documentService.saveDocument(newProtocolDocument);
//
//            // if there is an assign to agenda protocol action, remove it.
//            ProtocolAction assignToAgendaProtocolAction = protocolAssignToAgendaService
//                    .getAssignedToAgendaProtocolAction(newProtocolDocument.getProtocol());
//            if (assignToAgendaProtocolAction != null) {
//                newProtocolDocument.getProtocol().getProtocolActions().remove(assignToAgendaProtocolAction);
//                businessObjectService.delete(assignToAgendaProtocolAction);
//            }
//            newProtocolDocument.getProtocol().refreshReferenceObject("protocolStatus");
//            documentService.saveDocument(newProtocolDocument);
//            generateCorrespondenceDocumentAndAttach(newProtocolDocument.getProtocol(), withdrawBean);
//            return newProtocolDocument;
//        }
//// This is withdraw submission not protocol.  the withdraw correspondence is for 'protocol' now.
//// it's not suitable for withdraw protocol submission.        
//        else {
//            generateCorrespondenceDocumentAndAttach(protocol, withdrawBean);
//        }
//        return protocol.getProtocolDocument();
//    }
//    
//    /**
//     * 
//     * This method will generate a correspondence document, and attach it to the protocol.
//     * @param protocol a Protocol object.
//     */
//    protected void generateCorrespondenceDocumentAndAttach(Protocol protocol, ProtocolWithdrawBean withdrawBean) throws PrintingException {
//        WithdrawCorrespondence correspondence = withdrawBean.getCorrespondence();
//        correspondence.setProtocol(protocol);
//        protocolActionCorrespondenceGenerationService.generateCorrespondenceDocumentAndAttach(correspondence);
//    } 
//
//    /**
//     * By canceling the protocol in workflow, we are preventing it from being
//     * reviewed by the IRB office.  A user will then be able to continue editing
//     * the protocol in order to submit it again at a later time.
//     * @param protocol
//     * @throws WorkflowException
//     */
//    protected void cancelWorkflow(Protocol protocol) throws WorkflowException {
//        documentService.cancelDocument(protocol.getProtocolDocument(), null);
//    }
//  
////    private void cancelWorkflow(ProtocolOnlineReview review) {
////        final String principalId = identityManagementService.getPrincipalByPrincipalName(KRADConstants.SYSTEM_USER).getPrincipalId();
////        try {
////            WorkflowDocument workflowDocument = WorkflowDocumentFactory.loadDocument(principalId, review.getProtocolOnlineReviewDocument().getDocumentHeader().getWorkflowDocument().getDocumentId());
////            if (workflowDocument.isEnroute()) {
////                workflowDocument.superUserBlanketApprove("Review finalized - protocol has been withdrawn.");
////            }
////            
////        }
////        catch (WorkflowException e) {
////           LOG.error(String.format("WorkflowException generated when super user approve called on protocolOnlineReview document number:%s", review.getProtocolOnlineReviewDocument().getDocumentNumber(), e));
////           throw new RuntimeException(String.format("WorkflowException generated when super user approve called on protocolOnlineReview document number:%s", review.getProtocolOnlineReviewDocument().getDocumentNumber(), e));
////        }
////    }
////    
//
//    /**
//     * Get the submission that is being withdrawn.  Since a protocol can have
//     * multiple submissions, go backwards until we find a submission that can
//     * be withdrawn
//     * @param protocol
//     * @return
//     */
//    protected ProtocolSubmission getSubmission(Protocol protocol) {
//        for (ProtocolSubmission submission : protocol.getProtocolSubmissions()) {
//            if (isWithdrawable(submission)) {
//                return submission;
//            }
//        }
//        return null;
//    }
//
//    /**
//     * A submission is only withdrawable if it corresponds to a request to review
//     * the submission.  The submissions that meet this criteria are the initial 
//     * request for review, amendments, and renewals that are still in the pending
//     * or submitted to committee states.   Submissions such as Notify IRB
//     * cannot be withdrawn.
//     * @param submission
//     * @return
//     */
//    protected boolean isWithdrawable(ProtocolSubmission submission) {
//        return isAllowedStatus(submission) && isNormalSubmission(submission);
//    }
//    
//    /**
//     * Does the submission status allow us to withdraw the protocol?
//     * @param submission
//     * @return true if withdrawable; otherwise false
//     */
//    protected boolean isAllowedStatus(ProtocolSubmission submission) {
//        return StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.PENDING) ||
//               StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
//    }
//    
//    /**
//     * Does the submission type allow us to withdraw the protocol?
//     * @param submission
//     * @return true if withdrawable; otherwise false
//     */
//    protected boolean isNormalSubmission(ProtocolSubmission submission) {
//        return StringUtils.equals(submission.getSubmissionTypeCode(), ProtocolSubmissionType.AMENDMENT) ||
//               StringUtils.equals(submission.getSubmissionTypeCode(), ProtocolSubmissionType.INITIAL_SUBMISSION) ||
//               StringUtils.equals(submission.getSubmissionTypeCode(), ProtocolSubmissionType.CONTINUATION) ||
//               StringUtils.equals(submission.getSubmissionTypeCode(), ProtocolSubmissionType.CONTINUATION_WITH_AMENDMENT);
//    }
//
//
//    public void setIdentityManagementService(IdentityService identityManagementService) {
//        this.identityManagementService = identityManagementService;
//    }
//
//    public ProtocolOnlineReviewService getProtocolOnlineReviewService() {
//        return protocolOnlineReviewService;
//    }
//
//    public void setProtocolOnlineReviewService(ProtocolOnlineReviewService protocolOnlineReviewService) {
//        this.protocolOnlineReviewService = protocolOnlineReviewService;
//    }
//    
    public void setKcNotificationService(KcNotificationService kcNotificationService) {
        this.kcNotificationService = kcNotificationService;
    }
}

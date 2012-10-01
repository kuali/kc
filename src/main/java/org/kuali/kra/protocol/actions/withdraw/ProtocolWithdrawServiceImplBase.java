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
package org.kuali.kra.protocol.actions.withdraw;

import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.ProtocolVersionService;
import org.kuali.kra.protocol.actions.withdraw.ProtocolWithdrawBean;
import org.kuali.kra.protocol.actions.withdraw.ProtocolWithdrawServiceImplBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewService;
import org.kuali.kra.protocol.actions.correspondence.ProtocolActionCorrespondenceGenerationService;
import org.kuali.kra.protocol.actions.correspondence.ProtocolActionsCorrespondenceBase;
import org.kuali.kra.protocol.actions.submit.ProtocolActionService;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.printing.PrintingException;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

/**
 * The ProtocolWithdrawService implementation.
 */
public abstract class ProtocolWithdrawServiceImplBase implements ProtocolWithdrawService {

// TODO *********commented the code below during IACUC refactoring*********     
//    private static final Log LOG = LogFactory.getLog(ProtocolWithdrawServiceImplBase.class);
    
    protected DocumentService documentService;
    protected BusinessObjectService businessObjectService;
    protected ProtocolActionService protocolActionService;
    protected ProtocolVersionService protocolVersionService;
    
// TODO *********commented the code below during IACUC refactoring*********     
//    protected ProtocolAssignToAgendaService protocolAssignToAgendaService;
    protected ProtocolActionCorrespondenceGenerationService protocolActionCorrespondenceGenerationService;
    
    protected ProtocolOnlineReviewService protocolOnlineReviewService;
 
    
    protected static final String WITHDRAW_FINALIZE_OLR_ANNOTATION = "Online Review finalized as part of withdraw action on protocol.";
    
    /**
     * Set the document service.
     * @param documentService
     */
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    
    /**
     * Set the business object service.
     * @param businessObjectService the business object service
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

// TODO *********commented the code below during IACUC refactoring*********     
//    public void setProtocolAssignToAgendaService(ProtocolAssignToAgendaService protocolAssignToAgendaService) {
//        this.protocolAssignToAgendaService = protocolAssignToAgendaService;
//    }
//    
    public void setProtocolActionCorrespondenceGenerationService(ProtocolActionCorrespondenceGenerationService protocolActionCorrespondenceGenerationService) {
        this.protocolActionCorrespondenceGenerationService = protocolActionCorrespondenceGenerationService;
    }
    
    /**
     * Set the ProtocolBase Action Service.
     * @param protocolActionService
     */
    public void setProtocolActionService(ProtocolActionService protocolActionService) {
        this.protocolActionService = protocolActionService;
    }
    
    /**
     * Inject ProtocolBase Version Service.
     * @param protocolVersionService
     */
    public void setProtocolVersionService(ProtocolVersionService protocolVersionService) {
        this.protocolVersionService = protocolVersionService;
    }

    
    public abstract ProtocolDocumentBase withdraw(ProtocolBase protocol, ProtocolWithdrawBean withdrawBean) throws Exception;
        
// This method is generating too many hooks (and hence seems fragile), so making it abstract and letting subclasses define it.        
//    /**
//     * @see org.kuali.kra.protocol.actions.withdraw.ProtocolWithdrawService#withdraw(org.kuali.kra.protocol.ProtocolBase, org.kuali.kra.protocol.actions.withdraw.ProtocolWithdrawBean)
//     */
//    public ProtocolDocumentBase withdraw(ProtocolBase protocol, ProtocolWithdrawBean withdrawBean) throws Exception {
//        ProtocolSubmissionBase submission = getSubmission(protocol);
//        ProtocolActionBase protocolAction = new ProtocolActionBase(protocol, null, ProtocolActionType.WITHDRAWN);
//        protocolAction.setComments(withdrawBean.getReason());
//        protocol.getProtocolActions().add(protocolAction);
//
//        boolean isVersion = ProtocolStatusBase.IN_PROGRESS.equals(protocol.getProtocolStatusCode())
//              || ProtocolStatusBase.SUBMITTED_TO_IRB.equals(protocol.getProtocolStatusCode());
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
//            ProtocolDocumentBase newProtocolDocument = protocolVersionService.versionProtocolDocument(protocol.getProtocolDocument());
//            newProtocolDocument.getProtocol().setProtocolStatusCode(ProtocolStatusBase.WITHDRAWN);
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
//            ProtocolActionBase assignToAgendaProtocolAction = protocolAssignToAgendaService
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
    
    /**
     * 
     * This method will generate a correspondence document, and attach it to the protocol.
     * @param protocol a ProtocolBase object.
     */
    protected void generateCorrespondenceDocumentAndAttach(ProtocolBase protocol, ProtocolWithdrawBean withdrawBean) throws PrintingException {
        ProtocolActionsCorrespondenceBase correspondence = withdrawBean.getCorrespondence();         
        correspondence.setProtocol(protocol);
        protocolActionCorrespondenceGenerationService.generateCorrespondenceDocumentAndAttach(correspondence);
    } 

    /**
     * By canceling the protocol in workflow, we are preventing it from being
     * reviewed by the IRB office.  A user will then be able to continue editing
     * the protocol in order to submit it again at a later time.
     * @param protocol
     * @throws WorkflowException
     */
    protected void cancelWorkflow(ProtocolBase protocol) throws WorkflowException {
        documentService.cancelDocument(protocol.getProtocolDocument(), null);
    }
  

    /**
     * Get the submission that is being withdrawn.  Since a protocol can have
     * multiple submissions, go backwards until we find a submission that can
     * be withdrawn
     * @param protocol
     * @return
     */
    protected ProtocolSubmissionBase getSubmission(ProtocolBase protocol) {
        for (ProtocolSubmissionBase submission : protocol.getProtocolSubmissions()) {
            if (isWithdrawable(submission)) {
                return submission;
            }
        }
        return null;
    }

    /**
     * A submission is only withdrawable if it corresponds to a request to review
     * the submission.  The submissions that meet this criteria are the initial 
     * request for review, amendments, and renewals that are still in the pending
     * or submitted to committee states.   Submissions such as Notify IRB
     * cannot be withdrawn.
     * @param submission
     * @return
     */
    protected boolean isWithdrawable(ProtocolSubmissionBase submission) {
        return isAllowedStatus(submission) && isNormalSubmission(submission);
    }
    
    protected abstract boolean isAllowedStatus(ProtocolSubmissionBase submission);
    
    protected abstract boolean isNormalSubmission(ProtocolSubmissionBase submission);
    
// The two methods below were commnented out and declared abstract thus pushing them to subclasses 
// TODO *********commented the code below during IACUC refactoring*********
//    /**
//     * Does the submission status allow us to withdraw the protocol?
//     * @param submission
//     * @return true if withdrawable; otherwise false
//     */
//    protected boolean isAllowedStatus(ProtocolSubmissionBase submission) {
//        return StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.PENDING) ||
//               StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
//    }
//    
//    /**
//     * Does the submission type allow us to withdraw the protocol?
//     * @param submission
//     * @return true if withdrawable; otherwise false
//     */
//    protected boolean isNormalSubmission(ProtocolSubmissionBase submission) {
//        return StringUtils.equals(submission.getSubmissionTypeCode(), ProtocolSubmissionType.AMENDMENT) ||
//               StringUtils.equals(submission.getSubmissionTypeCode(), ProtocolSubmissionType.INITIAL_SUBMISSION) ||
//               StringUtils.equals(submission.getSubmissionTypeCode(), ProtocolSubmissionType.CONTINUATION) ||
//               StringUtils.equals(submission.getSubmissionTypeCode(), ProtocolSubmissionType.CONTINUATION_WITH_AMENDMENT);
//    }



    public void setProtocolOnlineReviewService(ProtocolOnlineReviewService protocolOnlineReviewService) {
        this.protocolOnlineReviewService = protocolOnlineReviewService;
    }

    // new addition developed for IACUC, has to be backfitted to IRB
    public abstract ProtocolDocumentBase administrativelyWithdraw(ProtocolBase protocol, ProtocolAdministrativelyWithdrawBean administrativelyWithdrawBean) throws Exception;
    
    // new addition developed for IACUC, has to be backfitted to IRB
    public abstract ProtocolDocumentBase administrativelyMarkIncomplete(ProtocolBase protocol, ProtocolAdministrativelyIncompleteBean markIncompleteBean) throws Exception;
 
 
}

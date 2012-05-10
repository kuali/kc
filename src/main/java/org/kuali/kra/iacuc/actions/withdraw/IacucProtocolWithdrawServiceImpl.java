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
package org.kuali.kra.iacuc.actions.withdraw;

import java.sql.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.common.notification.service.KcNotificationService;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.IacucProtocolVersionServiceImpl;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.IacucProtocolStatus;
import org.kuali.kra.iacuc.actions.assignagenda.IacucProtocolAssignToAgendaService;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionStatus;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionType;
import org.kuali.kra.protocol.actions.submit.ProtocolActionService;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmission;
import org.kuali.kra.printing.PrintingException;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

/**
 * The ProtocolWithdrawService implementation.
 */
public class IacucProtocolWithdrawServiceImpl implements IacucProtocolWithdrawService {

    private static final Log LOG = LogFactory.getLog(IacucProtocolWithdrawServiceImpl.class);
    private DocumentService documentService;
    private BusinessObjectService businessObjectService;
    private ProtocolActionService protocolActionService;
    private IacucProtocolVersionServiceImpl protocolVersionService;
    private IacucProtocolAssignToAgendaService protocolAssignToAgendaService;
//TODO IACUC    private IacucProtocolActionCorrespondenceGenerationService protocolActionCorrespondenceGenerationService;
  //TODO IACUC    private IacucProtocolOnlineReviewService protocolOnlineReviewService;
    private IdentityService identityManagementService;
    private KcNotificationService kcNotificationService;
    
    private static final String WITHDRAW_FINALIZE_OLR_ANNOTATION = "Online Review finalized as part of withdraw action on protocol.";
    
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

    public void setProtocolAssignToAgendaService(IacucProtocolAssignToAgendaService protocolAssignToAgendaService) {
        this.protocolAssignToAgendaService = protocolAssignToAgendaService;
    }
    
//TODO: IACUC    
//    public void setProtocolActionCorrespondenceGenerationService(IacucProtocolActionCorrespondenceGenerationService protocolActionCorrespondenceGenerationService) {
//        this.protocolActionCorrespondenceGenerationService = protocolActionCorrespondenceGenerationService;
//    }
    
    /**
     * Set the Protocol Action Service.
     * @param protocolActionService
     */
    public void setProtocolActionService(ProtocolActionService protocolActionService) {
        this.protocolActionService = protocolActionService;
    }
    
    /**
     * Inject Protocol Version Service.
     * @param protocolVersionService
     */
    public void setProtocolVersionService(IacucProtocolVersionServiceImpl protocolVersionService) {
        this.protocolVersionService = protocolVersionService;
    }

    /**
     * @see org.kuali.kra.protocol.actions.withdraw.IacucProtocolWithdrawService#withdraw(org.kuali.kra.protocol.Protocol, org.kuali.kra.protocol.actions.withdraw.IacucProtocolWithdrawBean)
     */
    public IacucProtocolDocument withdraw(IacucProtocol protocol, IacucProtocolWithdrawBean withdrawBean) throws Exception {
        IacucProtocolSubmission submission = getSubmission(protocol);
        IacucProtocolAction protocolAction = new IacucProtocolAction(protocol, null, IacucProtocolActionType.IACUC_WITHDRAWN);
        protocolAction.setComments(withdrawBean.getReason());
        protocol.getProtocolActions().add(protocolAction);

        boolean isVersion = IacucProtocolStatus.IN_PROGRESS.equals(protocol.getProtocolStatusCode())
              || IacucProtocolStatus.SUBMITTED_TO_IACUC.equals(protocol.getProtocolStatusCode());
        protocolActionService.updateProtocolStatus(protocolAction, protocol);

       
        if (submission != null) {
            submission.setSubmissionDate(new Date(System.currentTimeMillis()));
            submission.setSubmissionStatusCode(IacucProtocolSubmissionStatus.WITHDRAWN);
            // need to finalize any outstanding review documents.
//TODO: IACUC            protocolOnlineReviewService.finalizeOnlineReviews(submission, WITHDRAW_FINALIZE_OLR_ANNOTATION);
        }
        businessObjectService.save(protocol.getProtocolDocument());

// This was previously commented out of IRB implementation.        
//        IRBNotificationRenderer renderer = new IRBNotificationRenderer(protocol);
//        IRBNotificationContext context = new IRBNotificationContext(protocol, IacucProtocolActionType.WITHDRAWN, "Withdrawn", renderer);
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
            IacucProtocolDocument newProtocolDocument = (IacucProtocolDocument)protocolVersionService.versionProtocolDocument(protocol.getProtocolDocument());
            newProtocolDocument.getProtocol().setProtocolStatusCode(IacucProtocolStatus.WITHDRAWN);
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
//TODO IACUC            IacucProtocolAction assignToAgendaProtocolAction = protocolAssignToAgendaService.getAssignedToAgendaProtocolAction(newProtocolDocument.getProtocol());
//TODO IACUC            if (assignToAgendaProtocolAction != null) {
//TODO IACUC                newProtocolDocument.getProtocol().getProtocolActions().remove(assignToAgendaProtocolAction);
//TODO IACUC                businessObjectService.delete(assignToAgendaProtocolAction);
//TODO IACUC            }
            newProtocolDocument.getProtocol().refreshReferenceObject("protocolStatus");
            documentService.saveDocument(newProtocolDocument);
//TODO IACUC            generateCorrespondenceDocumentAndAttach(newProtocolDocument.getProtocol(), withdrawBean);
            return newProtocolDocument;
        }
// This is withdraw submission not protocol.  the withdraw correspondence is for 'protocol' now.
// it's not suitable for withdraw protocol submission.        
        else {
            generateCorrespondenceDocumentAndAttach(protocol, withdrawBean);
        }
        return protocol.getIacucProtocolDocument();
    }

    /**
     * 
     * This method will generate a correspondence document, and attach it to the protocol.
     * @param protocol a Protocol object.
     */
    protected void generateCorrespondenceDocumentAndAttach(IacucProtocol protocol, IacucProtocolWithdrawBean withdrawBean) throws PrintingException {
//TODO IACUC        IacucWithdrawCorrespondence correspondence = withdrawBean.getCorrespondence();
//TODO IACUC        correspondence.setProtocol(protocol);
//TODO IACUC        protocolActionCorrespondenceGenerationService.generateCorrespondenceDocumentAndAttach(correspondence);
    } 

    /**
     * By canceling the protocol in workflow, we are preventing it from being
     * reviewed by the IRB office.  A user will then be able to continue editing
     * the protocol in order to submit it again at a later time.
     * @param protocol
     * @throws WorkflowException
     */
    protected void cancelWorkflow(IacucProtocol protocol) throws WorkflowException {
        documentService.cancelDocument(protocol.getProtocolDocument(), null);
    }

// Following was already commented out in IRB    
//    private void cancelWorkflow(IacucProtocolOnlineReview review) {
//        final String principalId = identityManagementService.getPrincipalByPrincipalName(KRADConstants.SYSTEM_USER).getPrincipalId();
//        try {
//            WorkflowDocument workflowDocument = WorkflowDocumentFactory.loadDocument(principalId, review.getProtocolOnlineReviewDocument().getDocumentHeader().getWorkflowDocument().getDocumentId());
//            if (workflowDocument.isEnroute()) {
//                workflowDocument.superUserBlanketApprove("Review finalized - protocol has been withdrawn.");
//            }
//            
//        }
//        catch (WorkflowException e) {
//           LOG.error(String.format("WorkflowException generated when super user approve called on protocolOnlineReview document number:%s", review.getProtocolOnlineReviewDocument().getDocumentNumber(), e));
//           throw new RuntimeException(String.format("WorkflowException generated when super user approve called on protocolOnlineReview document number:%s", review.getProtocolOnlineReviewDocument().getDocumentNumber(), e));
//        }
//    }
//    

    /**
     * Get the submission that is being withdrawn.  Since a protocol can have
     * multiple submissions, go backwards until we find a submission that can
     * be withdrawn
     * @param protocol
     * @return
     */
    protected IacucProtocolSubmission getSubmission(IacucProtocol protocol) {
        for (ProtocolSubmission submission : protocol.getProtocolSubmissions()) {
            if (isWithdrawable((IacucProtocolSubmission)submission)) {
                return (IacucProtocolSubmission)submission;
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
    protected boolean isWithdrawable(IacucProtocolSubmission submission) {
        return isAllowedStatus(submission) && isNormalSubmission(submission);
    }
    
    /**
     * Does the submission status allow us to withdraw the protocol?
     * @param submission
     * @return true if withdrawable; otherwise false
     */
    protected boolean isAllowedStatus(IacucProtocolSubmission submission) {
        return StringUtils.equals(submission.getSubmissionStatusCode(), IacucProtocolSubmissionStatus.PENDING) ||
               StringUtils.equals(submission.getSubmissionStatusCode(), IacucProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
    }
    
    /**
     * Does the submission type allow us to withdraw the protocol?
     * @param submission
     * @return true if withdrawable; otherwise false
     */
    protected boolean isNormalSubmission(IacucProtocolSubmission submission) {
        return StringUtils.equals(submission.getSubmissionTypeCode(), IacucProtocolSubmissionType.AMENDMENT) ||
               StringUtils.equals(submission.getSubmissionTypeCode(), IacucProtocolSubmissionType.INITIAL_SUBMISSION) ||
               StringUtils.equals(submission.getSubmissionTypeCode(), IacucProtocolSubmissionType.CONTINUATION) ||
               StringUtils.equals(submission.getSubmissionTypeCode(), IacucProtocolSubmissionType.CONTINUATION_WITH_AMENDMENT);
    }


    public void setIdentityManagementService(IdentityService identityManagementService) {
        this.identityManagementService = identityManagementService;
    }

//TODO: IACUC    
//    public IacucProtocolOnlineReviewService getProtocolOnlineReviewService() {
//        return protocolOnlineReviewService;
//    }
//
//    public void setProtocolOnlineReviewService(IacucProtocolOnlineReviewService protocolOnlineReviewService) {
//        this.protocolOnlineReviewService = protocolOnlineReviewService;
//    }
    
    public void setKcNotificationService(KcNotificationService kcNotificationService) {
        this.kcNotificationService = kcNotificationService;
    }
}

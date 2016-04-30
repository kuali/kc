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
package org.kuali.kra.protocol.actions.undo;

import org.apache.commons.collections4.CollectionUtils;
import org.kuali.coeus.common.committee.impl.meeting.CommitteeScheduleMinuteBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.ProtocolOnlineReviewDocumentBase;
import org.kuali.kra.protocol.ProtocolVersionService;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.copy.ProtocolCopyService;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsService;
import org.kuali.kra.protocol.actions.submit.ProtocolActionService;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewer;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewService;
import org.kuali.kra.protocol.onlinereview.ProtocolReviewAttachmentBase;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.WorkflowDocumentFactory;
import org.kuali.rice.kew.api.action.ActionTaken;
import org.kuali.rice.kew.api.action.ActionType;
import org.kuali.rice.kew.routeheader.DocumentRouteHeaderValue;
import org.kuali.rice.kew.routeheader.service.RouteHeaderService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;

import java.sql.Date;
import java.util.List;

public abstract class UndoLastActionServiceImplBase implements UndoLastActionService {
    
    private ProtocolActionService protocolActionService;
    private DocumentService documentService;
    private BusinessObjectService businessObjectService;
    private ProtocolVersionService protocolVersionService;
    private ReviewCommentsService reviewCommentsService;
    private ProtocolOnlineReviewService protocolOnlineReviewService;
    private RouteHeaderService routeHeaderService;
    private ProtocolCopyService protocolCopyService;
    public void setProtocolActionService(ProtocolActionService protocolActionService) {
        this.protocolActionService = protocolActionService;
    }
    
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    
    public void setProtocolVersionService(ProtocolVersionService protocolVersionService) {
        this.protocolVersionService = protocolVersionService;
    }

    protected abstract void removeAttachedCorrespondences(ProtocolActionBase protocolAction);

    public ProtocolDocumentBase undoLastAction(ProtocolDocumentBase protocolDocument, UndoLastActionBean undoLastActionBean) throws Exception {
        //Undo ProtocolBase Status and Submission Status update
        ProtocolBase protocol = protocolDocument.getProtocol();
        ProtocolDocumentBase updatedDocument = null;
        
        ProtocolActionBase lastActionPerformed = undoLastActionBean.getLastAction();
        if(lastActionPerformed != null) {
            protocolActionService.resetProtocolStatus(lastActionPerformed, protocol);
            protocolDocument.setProtocol(protocol);
            
            //Revert any correspondence that was sent out
            removeAttachedCorrespondences(lastActionPerformed); 
            
            //Clear the Audit trail - Action history created
            if(!protocolDocument.getDocumentHeader().getWorkflowDocument().isCanceled()) {
                protocol.getProtocolActions().remove(undoLastActionBean.getLastAction());
            }
            
            //Undo possible workflow actions
            updatedDocument = undoWorkflowRouting(protocolDocument, lastActionPerformed);
            
            //Save the updated ProtocolBase object
            documentService.saveDocument(updatedDocument);
        }
        
        return (updatedDocument != null? updatedDocument : protocolDocument); 
    }
    
    protected abstract String getAmendmentInProgressStatusHook();
    
    protected abstract String getRenewalInProgressStatusHook();

    protected abstract String getFyiInProgressStatusHook();

    protected abstract String getInProgressStatusHook();
    
    protected abstract boolean isApprovedActionTypeCode(String actionTypeCode);
    
    protected abstract boolean isRevisionsRequiredActionTypeCode(String actionTypeCode);
    
    protected void resetProtocolStatus(ProtocolBase protocol) {

        String prevProtocolStatusCode = getInProgressStatusHook();
        if (protocol.isAmendment()) {
            prevProtocolStatusCode = getAmendmentInProgressStatusHook();
        } else if (protocol.isRenewal()) {
            prevProtocolStatusCode = getRenewalInProgressStatusHook();
        } else if (protocol.isFYI()) {
            prevProtocolStatusCode = getFyiInProgressStatusHook();
        }
        protocol.setProtocolStatusCode(prevProtocolStatusCode);
        protocol.setActive(true);
    }
    
    protected ProtocolDocumentBase undoWorkflowRouting(ProtocolDocumentBase protocolDocument, ProtocolActionBase lastPerformedAction) throws Exception {
        WorkflowDocument currentWorkflowDocument = protocolDocument.getDocumentHeader().getWorkflowDocument();
        
        //Do we need additional check to see if this is not a Renewal/Amendment Approval? since we already eliminated those options within Authz Logic
        if (currentWorkflowDocument.isCanceled()) {
            protocolDocument = protocolCopyService.copyProtocol(protocolDocument);
            resetProtocolStatus(protocolDocument.getProtocol());
        } else if(currentWorkflowDocument != null && currentWorkflowDocument.isFinal() && lastPerformedAction != null 
                  && isApprovedActionTypeCode(lastPerformedAction.getProtocolActionTypeCode())) {
            ProtocolSubmissionBase oldSubmission = protocolDocument.getProtocol().getProtocolSubmission();
            protocolDocument = protocolVersionService.versionProtocolDocument(protocolDocument);
            protocolDocument.getProtocol().refreshReferenceObject("protocolStatus");
            
            // to force it to retrieve from list.
            protocolDocument.getProtocol().setProtocolSubmission(null);
            // update some info
            protocolDocument.getProtocol().setApprovalDate(null);
            protocolDocument.getProtocol().setLastApprovalDate(null);
            protocolDocument.getProtocol().setExpirationDate(null);
            
            protocolDocument.setReRouted(true);
            documentService.saveDocument(protocolDocument);
            convertReviewComments(protocolDocument.getProtocol());            
            documentService.routeDocument(protocolDocument, Constants.PROTOCOL_UNDO_APPROVE_ANNOTATION, null);
            updateProtocolReviews(protocolDocument.getProtocol());
       } else if (currentWorkflowDocument != null && currentWorkflowDocument.isSaved() && lastPerformedAction != null 
                && isRevisionsRequiredActionTypeCode(lastPerformedAction.getProtocolActionTypeCode())) {
            convertReviewComments(protocolDocument.getProtocol());            
            documentService.routeDocument(protocolDocument, Constants.PROTOCOL_UNDO_APPROVE_ANNOTATION, null);            
            updateProtocolReviews(protocolDocument.getProtocol());
        } 
        
        return protocolDocument;
    }

    /*
     * create onlr review as needed
     */
    protected void updateProtocolReviews(ProtocolBase protocol) throws Exception {
        ProtocolBase oldProtocol = getOldProtocol(protocol);
        if (CollectionUtils.isNotEmpty(oldProtocol.getProtocolSubmission().getProtocolOnlineReviews())) {
            createOnlnReviews(protocol, oldProtocol);
        }
        
    }

    /**
     * 
     * Get the last protocol version before the action we are trying to undo.
     * @param protocol
     * @return
     */
    protected abstract ProtocolBase getOldProtocol(ProtocolBase protocol);

    /*
     * create &amp; route onlnreview doc for the new versioned protocol
     */
    protected void createOnlnReviews(ProtocolBase protocol, ProtocolBase oldProtocol) throws Exception {
        String routeAnnotation = "Recreate Online Review for undo action.";
        boolean initialApproval = false;
        Date dateRequested = null;
        Date dateDue = null;
        String sessionPrincipalId = GlobalVariables.getUserSession().getPrincipalId();
        ProtocolSubmissionBase protocolSubmission = protocol.getProtocolSubmission();
        for (ProtocolOnlineReviewBase onlineReview : oldProtocol.getProtocolSubmission().getProtocolOnlineReviews()) {
            ProtocolOnlineReviewDocumentBase oldDoc = (ProtocolOnlineReviewDocumentBase) documentService.getByDocumentHeaderId(onlineReview
                    .getProtocolOnlineReviewDocument().getDocumentNumber());
            if (!KewApiConstants.ROUTE_HEADER_DISAPPROVED_CD.equals(oldDoc.getDocumentHeader().getWorkflowDocument().getStatus().getCode())) {
                ProtocolOnlineReviewBase copiedReview = (ProtocolOnlineReviewBase) ObjectUtils.deepCopy(onlineReview);
                ProtocolReviewer reviewer = getReviewer(onlineReview, protocol);
                setNewOnlnReview(copiedReview, reviewer, protocol);
                ProtocolOnlineReviewDocumentBase document = protocolOnlineReviewService.createAndRouteProtocolOnlineReviewDocument(
                        protocolSubmission, reviewer, oldDoc.getDocumentHeader().getDocumentDescription(), oldDoc
                                .getDocumentHeader().getExplanation(), oldDoc.getDocumentHeader().getOrganizationDocumentNumber(),
                        routeAnnotation, initialApproval, dateRequested, dateDue, sessionPrincipalId);
                copiedReview.setProtocolOnlineReviewDocument(document);
                if (isAsyncComplete(document.getDocumentNumber())) {
                    documentService.saveDocument(document);
                }
                resetOnlineReviewStatus(oldDoc, document);
                protocolSubmission.getProtocolOnlineReviews().add(document.getProtocolOnlineReview());
            }
        }

    }

    /*
   * copy old onlnreview, and reset key &amp; fk fields.
   */
    protected void setNewOnlnReview(ProtocolOnlineReviewBase copiedReview, ProtocolReviewer reviewer, ProtocolBase protocol) {
        ProtocolSubmissionBase protocolSubmission = protocol.getProtocolSubmission();
        copiedReview.setProtocolReviewer(reviewer);
        copiedReview.setProtocolReviewerId(reviewer.getProtocolReviewerId());
        copiedReview.setProtocolOnlineReviewId(null);
        copiedReview.setSubmissionIdFk(protocolSubmission.getSubmissionId());
        copiedReview.setProtocolId(protocol.getProtocolId());
        if (CollectionUtils.isNotEmpty(copiedReview.getCommitteeScheduleMinutes())) {
            for (CommitteeScheduleMinuteBase comment : copiedReview.getCommitteeScheduleMinutes()) {
                comment.setProtocolIdFk(protocol.getProtocolId());
                comment.setProtocolOnlineReviewIdFk(null);
            }
        }
        if (CollectionUtils.isNotEmpty(copiedReview.getReviewAttachments())) {
            for (ProtocolReviewAttachmentBase reviewAttachment : copiedReview.getReviewAttachments()) {
                reviewAttachment.setProtocolIdFk(protocol.getProtocolId());
                reviewAttachment.setProtocolOnlineReviewIdFk(null);
                reviewAttachment.setSubmissionIdFk(protocolSubmission.getSubmissionId());
            }
        }
    }

    /*
     * Restore ProtocolBase OLR doc status to the status before it was being finalized.
     * OLR doc is finalized when protocol is versioned.
     */
    //replay the
    protected void resetOnlineReviewStatus(ProtocolOnlineReviewDocumentBase oldDocument, ProtocolOnlineReviewDocumentBase document) throws Exception {
        List<ActionTaken> actionsTaken = oldDocument.getDocumentHeader().getWorkflowDocument().getActionsTaken();
        //replay all actions except for the most recent if it is a blanket approve
        for (int i = 0; i < actionsTaken.size(); i++) {
            if (isAsyncComplete(document.getDocumentNumber())) {
                ActionTaken action = actionsTaken.get(i);
                WorkflowDocument workflowDocument = WorkflowDocumentFactory.loadDocument(action.getPrincipalId(), document.getDocumentNumber());
                if (action.getActionTaken() == ActionType.APPROVE) {
                    workflowDocument.approve("approve for undo");
                } else if (action.getActionTaken() == ActionType.BLANKET_APPROVE && actionsTaken.indexOf(action) != actionsTaken.size()-1) {
                    workflowDocument.superUserBlanketApprove("Finalize for undo");
                } else if (action.getActionTaken() == ActionType.RETURN_TO_PREVIOUS) {
                    getProtocolOnlineReviewService().returnProtocolOnlineReviewDocumentToReviewer(document, "Reject for Undo", action.getPrincipalId());
                }
            }
        }
        document.getProtocolOnlineReview().removeLastAction();
    }

    /*
     * try to check if async wkflw process is completely.  Mainly, route olr doc in this case.
     */
    protected boolean isAsyncComplete(String docId) {
        boolean isComplete = false;
        int numberOfWaits = 0;
        while (numberOfWaits++ < 20 && !isComplete) {
            try {
                DocumentRouteHeaderValue document = routeHeaderService.getRouteHeader(docId);
                if (!document.isFinal() && document.getActionRequests().isEmpty()) {
                    Thread.sleep(1000);
                } else {
                    isComplete = true;
                }
            } catch (Exception e) {
            }
        }
        return isComplete;
    }

    
    /*
     * set up reviewer for onlnreview document creation.  reviewer is based on old onlnreview
     */
    protected ProtocolReviewer getReviewer(ProtocolOnlineReviewBase onlineReview, ProtocolBase protocol) {
        ProtocolSubmissionBase protocolSubmission = protocol.getProtocolSubmission();
        ProtocolReviewer reviewer = onlineReview.getProtocolReviewer();
        reviewer.setProtocolIdFk(protocol.getProtocolId());
        reviewer.setProtocolReviewerId(null);
        reviewer.setProtocol(protocol);
        reviewer.setSubmissionIdFk(protocolSubmission.getSubmissionId());
        reviewer.setSequenceNumber(protocolSubmission.getSequenceNumber());
        businessObjectService.save(reviewer);
        return reviewer;

    }
    
    /*
     * reset comment's protocolid to the new versioned protocolid.
     */
    protected void convertReviewComments(ProtocolBase protocol) {
        ProtocolBase oldProtocol = getOldProtocol(protocol);
        List<CommitteeScheduleMinuteBase> comments = reviewCommentsService.getReviewerComments(protocol.getProtocolNumber(), protocol.getProtocolSubmission().getSubmissionNumber());
        for (CommitteeScheduleMinuteBase comment :  comments) {
            if (comment.getProtocolIdFk().equals(oldProtocol.getProtocolId())) {
                comment.setProtocolIdFk(protocol.getProtocolId());
            }
        }
        businessObjectService.save(comments);
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public void setReviewCommentsService(ReviewCommentsService reviewCommentsService) {
        this.reviewCommentsService = reviewCommentsService;
    }

    public void setProtocolOnlineReviewService(ProtocolOnlineReviewService protocolOnlineReviewService) {
        this.protocolOnlineReviewService = protocolOnlineReviewService;
    }

    public void setRouteHeaderService(RouteHeaderService routeHeaderService) {
        this.routeHeaderService = routeHeaderService;
    }

    protected ProtocolCopyService getProtocolCopyService() {
        return protocolCopyService;
    }

    public void setProtocolCopyService(ProtocolCopyService protocolCopyService) {
        this.protocolCopyService = protocolCopyService;
    }

    protected ProtocolActionService getProtocolActionService() {
        return protocolActionService;
    }

    protected DocumentService getDocumentService() {
        return documentService;
    }

    protected BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    protected ProtocolVersionService getProtocolVersionService() {
        return protocolVersionService;
    }

    protected ReviewCommentsService getReviewCommentsService() {
        return reviewCommentsService;
    }

    protected ProtocolOnlineReviewService getProtocolOnlineReviewService() {
        return protocolOnlineReviewService;
    }

    protected RouteHeaderService getRouteHeaderService() {
        return routeHeaderService;
    }
    
}

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
package org.kuali.kra.irb.actions.undo;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolOnlineReviewDocument;
import org.kuali.kra.irb.ProtocolVersionService;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.copy.ProtocolCopyService;
import org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsService;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.actions.submit.ProtocolReviewer;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondence;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.ObjectUtils;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;

public class UndoLastActionServiceImpl implements UndoLastActionService {
    private static final String AMEND = "A";
    private static final String RENEW = "R";
    
    private ProtocolActionService protocolActionService;
    private DocumentService documentService;
    private BusinessObjectService businessObjectService;
    private ProtocolVersionService protocolVersionService;
    private ReviewCommentsService reviewCommentsService;
    private ProtocolOnlineReviewService protocolOnlineReviewService;

    public void setProtocolActionService(ProtocolActionService protocolActionService) {
        this.protocolActionService = protocolActionService;
    }
    
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    
    public void setProtocolVersionService(ProtocolVersionService protocolVersionService) {
        this.protocolVersionService = protocolVersionService;
    }

    protected void removeAttachedCorrespondences(ProtocolAction protocolAction) {
        if(protocolAction != null) {
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put("actionIdFk", protocolAction.getProtocolActionId().toString());
            fieldValues.put("protocolNumber", protocolAction.getProtocolNumber());
            fieldValues.put("sequenceNumber", protocolAction.getSequenceNumber().toString());
            
            businessObjectService.deleteMatching(ProtocolCorrespondence.class, fieldValues);
        }
    }

    public ProtocolDocument undoLastAction(ProtocolDocument protocolDocument, UndoLastActionBean undoLastActionBean) throws Exception {
        //Undo Protocol Status and Submission Status update
        Protocol protocol = protocolDocument.getProtocol();
        undoLastActionBean.setActionsPerformed(protocol.getProtocolActions());
        ProtocolDocument updatedDocument = null;
        
        ProtocolAction lastActionPerformed = undoLastActionBean.getLastPerformedAction();
        if(lastActionPerformed != null) {
            protocolActionService.resetProtocolStatus(lastActionPerformed, protocol);
            protocolDocument.setProtocol(protocol);
            
            //Revert any correspondence that was sent out
            removeAttachedCorrespondences(lastActionPerformed); 
            
            //Clear the Audit trail - Action history created
            if(!protocolDocument.getDocumentHeader().getWorkflowDocument().stateIsCanceled()) {
                protocol.getProtocolActions().remove(undoLastActionBean.getLastPerformedAction());
            }
            
            //Undo possible workflow actions
            updatedDocument = undoWorkflowRouting(protocolDocument, lastActionPerformed);
            
            //Save the updated Protocol object
            documentService.saveDocument(updatedDocument);
        }
        
        return (updatedDocument != null? updatedDocument : protocolDocument); 
    }
    
    protected void resetProtocolStatus(Protocol protocol) {
        String protocolNumberUpper = protocol.getProtocolNumber().toUpperCase();
        String prevProtocolStatusCode = (protocolNumberUpper.contains(AMEND) ? ProtocolStatus.AMENDMENT_IN_PROGRESS : (protocolNumberUpper.contains(RENEW) ? ProtocolStatus.RENEWAL_IN_PROGRESS
                : ProtocolStatus.IN_PROGRESS));
        protocol.setProtocolStatusCode(prevProtocolStatusCode);
        protocol.setActive(true);
    }
    
    protected ProtocolDocument undoWorkflowRouting(ProtocolDocument protocolDocument, ProtocolAction lastPerformedAction) throws Exception {
        KualiWorkflowDocument currentWorkflowDocument = protocolDocument.getDocumentHeader().getWorkflowDocument();
        ProtocolCopyService protocolCopyService = KraServiceLocator.getService(ProtocolCopyService.class);
        
        //Do we need additional check to see if this is not a Renewal/Amendment Approval? since we already eliminated those options within Authz Logic
        if (currentWorkflowDocument.stateIsCanceled()) {
            protocolDocument = protocolCopyService.copyProtocol(protocolDocument);
            resetProtocolStatus(protocolDocument.getProtocol());
        } else if(currentWorkflowDocument != null && currentWorkflowDocument.stateIsFinal() && lastPerformedAction != null 
                  && (ProtocolActionType.APPROVED.equals(lastPerformedAction.getProtocolActionTypeCode()) 
                          || ProtocolActionType.EXPEDITE_APPROVAL.equals(lastPerformedAction.getProtocolActionTypeCode()))) {
            //currentWorkflowDocument.returnToPreviousRouteLevel("Undo Last Action", currentWorkflowDocument.getDocRouteLevel() - 1);
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
            documentService.routeDocument(protocolDocument, Constants.PROTOCOL_UNDO_APPROVE_ANNOTATION, null);
        } else if (currentWorkflowDocument != null && currentWorkflowDocument.stateIsSaved() && lastPerformedAction != null 
                && (ProtocolActionType.SPECIFIC_MINOR_REVISIONS_REQUIRED.equals(lastPerformedAction.getProtocolActionTypeCode()) 
                        || ProtocolActionType.SUBSTANTIVE_REVISIONS_REQUIRED.equals(lastPerformedAction.getProtocolActionTypeCode()))) {
            convertReviewComments(protocolDocument.getProtocol());            
            documentService.routeDocument(protocolDocument, Constants.PROTOCOL_UNDO_APPROVE_ANNOTATION, null);            
//            updateProtocolReviews(protocolDocument.getProtocol());
        } 
        
        return protocolDocument;
    }

    /*
     * create onlr review as needed
     */
    private void updateProtocolReviews(Protocol protocol) {
        Protocol oldProtocol = getOldProtocol(protocol);
        if (CollectionUtils.isNotEmpty(oldProtocol.getProtocolSubmission().getProtocolOnlineReviews())) {
            createOnlnReviews(protocol, oldProtocol);
        }
        
    }

    /*
     * get the protocol before SMR/SRR
     */
    private Protocol getOldProtocol(Protocol protocol) {
         Map<String, Object> fieldValues = new HashMap<String, Object>();
         fieldValues.put("protocolNumber", protocol.getProtocolNumber());
         fieldValues.put("sequenceNumber", protocol.getSequenceNumber() - 1);
         return ((List<Protocol>) businessObjectService.findMatching(Protocol.class, fieldValues)).get(0);
    }

    /*
     * create & route onlnreview doc for the new versioned protocol
     */
    private void createOnlnReviews(Protocol protocol, Protocol oldProtocol) {
        String routeAnnotation = "Online Review Requested by PI during protocol submission.";
        boolean initialApproval = false;
        Date dateRequested = null;
        Date dateDue = null;
        String sessionPrincipalId = GlobalVariables.getUserSession().getPrincipalId();
        ProtocolSubmission protocolSubmission = protocol.getProtocolSubmission();
        try {
            for (ProtocolOnlineReview onlineReview : oldProtocol.getProtocolSubmission().getProtocolOnlineReviews()) {
                ProtocolOnlineReviewDocument oldDoc = (ProtocolOnlineReviewDocument) documentService
                        .getByDocumentHeaderId(onlineReview.getProtocolOnlineReviewDocument().getDocumentNumber());
                if (!KEWConstants.ROUTE_HEADER_DISAPPROVED_CD.equals(oldDoc.getDocumentHeader().getWorkflowDocument()
                        .getRouteHeader().getDocRouteStatus())) {
                    ProtocolOnlineReview copiedReview = (ProtocolOnlineReview) ObjectUtils.deepCopy(onlineReview);
                    ProtocolReviewer reviewer = getReviewer(onlineReview, protocol);
                    setNewOnlnReview(copiedReview, reviewer, protocol);

                    ProtocolOnlineReviewDocument document = protocolOnlineReviewService.createAndRouteProtocolOnlineReviewDocument(
                            protocolSubmission, reviewer, oldDoc.getDocumentHeader().getDocumentDescription(), oldDoc
                                    .getDocumentHeader().getExplanation(), oldDoc.getDocumentHeader()
                                    .getOrganizationDocumentNumber(), routeAnnotation, initialApproval, dateRequested, dateDue,
                            sessionPrincipalId);
                    copiedReview.setProtocolOnlineReviewDocument(document);
                    document.setProtocolOnlineReview(copiedReview);
                    documentService.saveDocument(document);
                    // updateOlrComments(onlineReview.getProtocolOnlineReviewId(), copiedReview.getProtocolOnlineReviewId(),
                    // protocol.getProtocolId());
                    protocolSubmission.getProtocolOnlineReviews().add(document.getProtocolOnlineReview());
                }
            }
        }
        catch (Exception e) {

        }
        // protocol.getProtocolSubmission().setScheduleIdFk(oldProtocol.getProtocolSubmission().getScheduleIdFk());
        // businessObjectService.save(protocol);

    }

    /*
     * set up reviewer for onlnreview document creation.  reviewer is based on old onlnreview
     */
    private ProtocolReviewer getReviewer(ProtocolOnlineReview onlineReview, Protocol protocol) {
        ProtocolSubmission protocolSubmission = protocol.getProtocolSubmission();
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
     * copy old onlnreview, and reset key & fk fields.
     */
    private void setNewOnlnReview(ProtocolOnlineReview copiedReview, ProtocolReviewer reviewer, Protocol protocol) {
        ProtocolSubmission protocolSubmission = protocol.getProtocolSubmission();
        copiedReview.setProtocolReviewer(reviewer);
        copiedReview.setProtocolReviewerId(reviewer.getProtocolReviewerId());
        copiedReview.setProtocolOnlineReviewId(null);
        copiedReview.setSubmissionIdFk(protocolSubmission.getSubmissionId());
        copiedReview.setProtocolId(protocol.getProtocolId());
        if (CollectionUtils.isNotEmpty(copiedReview.getCommitteeScheduleMinutes())) {
            for (CommitteeScheduleMinute comment : copiedReview.getCommitteeScheduleMinutes()) {
                comment.setProtocolIdFk(protocol.getProtocolId());
                comment.setProtocolOnlineReviewIdFk(null);
            }
        }

       
    }
    
    /*
     * reset comment's protocolid to the new versioned protocolid.
     */
    private void convertReviewComments(Protocol protocol) {
        Protocol oldProtocol = getOldProtocol(protocol);
        List<CommitteeScheduleMinute> comments = reviewCommentsService.getReviewerComments(protocol.getProtocolNumber(), protocol.getProtocolSubmission().getSubmissionNumber());
        for (CommitteeScheduleMinute comment :  comments) {
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
    
}

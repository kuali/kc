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
package org.kuali.kra.iacuc.onlinereview;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.committee.impl.meeting.CommitteeScheduleMinuteBase;
import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.iacuc.IacucProtocolOnlineReviewDocument;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolReviewer;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionStatus;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionType;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.ProtocolOnlineReviewDocumentBase;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewer;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewServiceImplBase;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.bo.AdHocRouteRecipient;
import org.kuali.rice.krad.bo.DocumentHeader;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class IacucProtocolOnlineReviewServiceImpl extends ProtocolOnlineReviewServiceImplBase implements IacucProtocolOnlineReviewService {
    
    private static final Log LOG = LogFactory.getLog(IacucProtocolOnlineReviewServiceImpl.class);
    private KcWorkflowService kraWorkflowService;
  
    public boolean isProtocolInStateToBeReviewed(ProtocolBase protocol) {
        boolean isReviewable = false;
        ProtocolSubmissionBase submission = (IacucProtocolSubmission)((IacucProtocol)protocol).getProtocolSubmission();
        
        if (submission != null) {
            try {
                isReviewable = (StringUtils.equals(submission.getSubmissionStatusCode(), IacucProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE)
                        || StringUtils.equals(submission.getSubmissionStatusCode(), IacucProtocolSubmissionStatus.IN_AGENDA));
                ProtocolDocumentBase protocolDocument = (ProtocolDocumentBase) documentService.getByDocumentHeaderId(protocol.getProtocolDocument().getDocumentNumber());
                isReviewable &= kraWorkflowService.isCurrentNode(protocolDocument, Constants.IACUC_PROTOCOL_IACUCREVIEW_ROUTE_NODE_NAME);
            }
            catch (WorkflowException e) {
                String errorString = String.format(
                        "WorkflowException checking route node for creating new ProtocolOnlineReviewDocumentBase " + "for protocol %s",
                        submission.getProtocolNumber());
                LOG.error(errorString, e);
                throw new RuntimeException(errorString, e);
            }
        }
        return isReviewable;
    }

    public void setKraWorkflowService(KcWorkflowService kraWorkflowService) {
        this.kraWorkflowService = kraWorkflowService;
    }

    @Override
    public ProtocolOnlineReviewDocumentBase createAndRouteProtocolOnlineReviewDocument(ProtocolSubmissionBase protocolSubmission, 
            ProtocolReviewer protocolReviewer,
            String documentDescription,
            String documentExplanation,
            String documentOrganizationDocumentNumber,
            String documentRouteAnnotation,
            boolean initialApproval,
            Date dateRequested,
            Date dateDue, 
            String principalId) {
        ProtocolOnlineReviewDocumentBase document = null;

        try {
            if (LOG.isDebugEnabled()) {
                String protocolNumber = protocolSubmission.getProtocolNumber();
                Integer submissionNumber = protocolSubmission.getSubmissionNumber();
                LOG.debug(String.format("Assigning online reviewer [%s] to protocol [%s].", protocolReviewer, protocolNumber));
                LOG.debug(String.format("Current submission for protocol %s is %s.", protocolNumber, submissionNumber));
            }

            document = createProtocolOnlineReviewDocument(protocolSubmission, protocolReviewer, documentDescription,
                    documentExplanation, documentOrganizationDocumentNumber, dateRequested, dateDue, principalId);

            documentService.routeDocument(document, "Review Requested by PI during protocol submission.",
                    new ArrayList<AdHocRouteRecipient>());

            if (initialApproval) {
                documentService.approveDocument(document, "", new ArrayList<AdHocRouteRecipient>());
            }
        }
        catch (WorkflowException e) {
            String errorString = String.format(
                    "WorkflowException creating new ProtocolOnlineReviewDocumentBase for reviewer %s, protocol %s",
                    protocolReviewer.getPersonId(), protocolSubmission.getProtocolNumber());
            LOG.error(errorString, e);
            throw new RuntimeException(errorString, e);
        }

        return document;
    }


    public ProtocolOnlineReviewDocumentBase createProtocolOnlineReviewDocument(ProtocolSubmissionBase protocolSubmission,
            ProtocolReviewer protocolReviewer, String documentDescription, String documentExplanation,
            String documentOrganizationDocumentNumber, Date dateRequested, Date dateDue, String principalId)
            throws WorkflowException {

        IacucProtocolOnlineReviewDocument protocolReviewDocument;

        Person person = personService.getPerson(principalId);
        WorkflowDocument workflowDocument = workflowDocumentService.createWorkflowDocument(IACUC_PROTOCOL_ONLINE_REVIEW_DOCUMENT_TYPE,
                person);

        DocumentHeader docHeader = new DocumentHeader();
        docHeader.setWorkflowDocument(workflowDocument);
        docHeader.setDocumentNumber(workflowDocument.getDocumentId().toString());
        protocolReviewDocument = new IacucProtocolOnlineReviewDocument();
        protocolReviewDocument.setDocumentNumber(docHeader.getDocumentNumber());
        protocolReviewDocument.setDocumentHeader(docHeader);


        protocolReviewDocument.getProtocolOnlineReview().setProtocol(protocolSubmission.getProtocol());


        protocolReviewDocument.getProtocolOnlineReview().setProtocolId(protocolSubmission.getProtocolId());

        protocolReviewDocument.getProtocolOnlineReview().setProtocolSubmission(protocolSubmission);
        protocolReviewDocument.getProtocolOnlineReview().setSubmissionIdFk(protocolSubmission.getSubmissionId());
        protocolReviewDocument.getProtocolOnlineReview().setProtocolOnlineReviewStatusCode(IacucProtocolOnlineReviewStatus.SAVED_STATUS_CD);
        protocolReviewDocument.getProtocolOnlineReview().setDateRequested(
                dateRequested == null ? new Date((new java.util.Date()).getTime()) : dateRequested);
        protocolReviewDocument.getProtocolOnlineReview().setDateDue(dateDue);

        protocolReviewDocument.getProtocolOnlineReview().setProtocolReviewerId(protocolReviewer.getProtocolReviewerId());
        protocolReviewDocument.getProtocolOnlineReview().setProtocolReviewer(protocolReviewer);

        docHeader.setDocumentDescription(documentDescription);
        docHeader.setOrganizationDocumentNumber(documentOrganizationDocumentNumber);
        docHeader.setExplanation(documentExplanation);

        documentService.saveDocument(protocolReviewDocument);
        return protocolReviewDocument;
    }

    
    public void removeOnlineReviewDocument(String personId, boolean nonEmployeeFlag, ProtocolSubmissionBase submission, String annotation) {
        ProtocolOnlineReviewDocumentBase protocolOnlineReviewDocument = this.getProtocolOnlineReviewDocument(personId, nonEmployeeFlag, submission);
        
        ProtocolOnlineReviewBase submissionsProtocolOnlineReview = null;
 
        for (ProtocolOnlineReviewBase rev : submission.getProtocolOnlineReviews()) {
            if (rev.getProtocolOnlineReviewId().equals(protocolOnlineReviewDocument.getProtocolOnlineReview().getProtocolOnlineReviewId())) {
                submissionsProtocolOnlineReview = rev;
                break;
            }
        }
        
        
        if (submissionsProtocolOnlineReview == null) {
            throw new IllegalStateException("Could not match OnlineReview document being removed to a protocolOnlineReview in the submission.");
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug(String.format("Processing request to remove online review for (personId=%s,nonEmployeeFlag=%s) from (protocol=%s,submission=%s)",personId,nonEmployeeFlag,submission.getProtocol().getProtocolNumber(),submission.getSubmissionNumber()));
        }
        
        if (protocolOnlineReviewDocument != null) {
            if(LOG.isDebugEnabled()) {
                LOG.debug(String.format("Found protocolOnlineReviewDocument %s, removing it.",protocolOnlineReviewDocument.getDocumentNumber()));
            }
            cancelOnlineReviewDocument(protocolOnlineReviewDocument, submission, annotation);
            submissionsProtocolOnlineReview.setProtocolOnlineReviewStatusCode(IacucProtocolOnlineReviewStatus.REMOVED_CANCELLED_STATUS_CD);
            
            List<CommitteeScheduleMinuteBase> reviewComments = protocolOnlineReviewDocument.getProtocolOnlineReview().getCommitteeScheduleMinutes();
            List<CommitteeScheduleMinuteBase> deletedReviewComments = new ArrayList<CommitteeScheduleMinuteBase>();
            reviewCommentsService.deleteAllReviewComments(reviewComments, deletedReviewComments);
            reviewCommentsService.saveReviewComments(reviewComments, deletedReviewComments);
            
            businessObjectService.save(submissionsProtocolOnlineReview);
        
        } else {
            LOG.warn(String.format("ProtocolBase Online Review document could not be found for (personId=%s,nonEmployeeFlag=%s) from (protocol=%s,submission=%s)",personId,nonEmployeeFlag,submission.getProtocol().getProtocolNumber(),submission.getSubmissionNumber()));
        }
    }
    

  
    @Override
    protected String getProtocolOLRSavedStatusCodeHook() {
        return IacucProtocolOnlineReviewStatus.SAVED_STATUS_CD;
    }

    @Override
    protected String getProtocolOLRRemovedCancelledStatusCodeHook() {
        return IacucProtocolOnlineReviewStatus.REMOVED_CANCELLED_STATUS_CD;
    }

    @Override
    protected ProtocolOnlineReviewDocumentBase getNewProtocolOnlineReviewDocumentInstanceHook() {
        return new IacucProtocolOnlineReviewDocument();
    }

    @Override
    protected String getProtocolOLRDocumentTypeHook() {
        return IACUC_PROTOCOL_ONLINE_REVIEW_DOCUMENT_TYPE;
    }

    @Override
    protected ProtocolReviewer createNewProtocolReviewerInstanceHook() {
        return new IacucProtocolReviewer();
    }

    @Override
    protected Class<? extends ProtocolOnlineReviewBase> getProtocolOnlineReviewBOClassHook() {
        return IacucProtocolOnlineReview.class;
    }

    @Override
    protected Class<? extends ProtocolSubmissionBase> getProtocolSubmissionBOClassHook() {
        return IacucProtocolSubmission.class;
    }
    
}

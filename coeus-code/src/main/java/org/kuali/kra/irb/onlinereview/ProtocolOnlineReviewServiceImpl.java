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
package org.kuali.kra.irb.onlinereview;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.committee.impl.meeting.CommitteeScheduleMinuteBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolOnlineReviewDocument;
import org.kuali.kra.irb.actions.submit.ProtocolReviewType;
import org.kuali.kra.irb.actions.submit.ProtocolReviewer;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolOnlineReviewDocumentBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewServiceImplBase;
import org.kuali.rice.kew.api.exception.WorkflowException;

import java.util.ArrayList;
import java.util.List;

public class ProtocolOnlineReviewServiceImpl extends ProtocolOnlineReviewServiceImplBase implements ProtocolOnlineReviewService {

    private static final Log LOG = LogFactory.getLog(ProtocolOnlineReviewServiceImpl.class);
    private static final String PROTOCOL_ONLINE_REVIEW_DOCUMENT_TYPE = "ProtocolOnlineReviewDocument";

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService#isProtocolInStateToBeReviewed(org.kuali.kra.irb.Protocol)
     */
    public boolean isProtocolInStateToBeReviewed(ProtocolBase protocol) {
        boolean isReviewable = false;
        ProtocolSubmission submission = (ProtocolSubmission)((Protocol) protocol).getProtocolSubmission();
        if (submission != null) {
            try {
                isReviewable = StringUtils.isNotEmpty(submission.getScheduleId()) || ProtocolReviewType.EXPEDITED_REVIEW_TYPE_CODE.equals(submission.getProtocolReviewTypeCode()); 
                isReviewable &= (StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE) 
                        || StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.IN_AGENDA));
                ProtocolDocument protocolDocument = (ProtocolDocument) documentService.getByDocumentHeaderId(protocol.getProtocolDocument().getDocumentNumber());
                isReviewable &= getKraWorkflowService().isDocumentOnNode(protocolDocument, Constants.PROTOCOL_IRBREVIEW_ROUTE_NODE_NAME);
            } catch (WorkflowException e) {
                String errorString = String.format("WorkflowException checking route node for creating new ProtocolOnlineReviewDocument " +
                		"for protocol %s", submission.getProtocolNumber());
                LOG.error(errorString, e);
                throw new RuntimeException(errorString, e);
            }
        }
        return isReviewable;
    }
    
    protected void removeOnlineReviewDocument(ProtocolOnlineReviewDocumentBase protocolOnlineReviewDocument, ProtocolSubmissionBase submission, String annotation) {

        if (protocolOnlineReviewDocument != null) {
            if(LOG.isDebugEnabled()) {
                LOG.debug(String.format("Found protocolOnlineReviewDocument %s, removing it.",protocolOnlineReviewDocument.getDocumentNumber()));
            }
            cancelOnlineReviewDocument(protocolOnlineReviewDocument, submission, annotation);
            protocolOnlineReviewDocument.getProtocolOnlineReview().setProtocolOnlineReviewStatusCode(ProtocolOnlineReviewStatus.REMOVED_CANCELLED_STATUS_CD);
            
            List<CommitteeScheduleMinuteBase> reviewComments = protocolOnlineReviewDocument.getProtocolOnlineReview().getCommitteeScheduleMinutes();
            List<CommitteeScheduleMinuteBase> deletedReviewComments = new ArrayList<CommitteeScheduleMinuteBase>();
            getReviewerCommentsService().deleteAllReviewComments(reviewComments, deletedReviewComments);
            getReviewerCommentsService().saveReviewComments(reviewComments, deletedReviewComments);

            submission.getProtocolReviewers().remove(protocolOnlineReviewDocument.getProtocolOnlineReview().getProtocolReviewer());
            getBusinessObjectService().save(protocolOnlineReviewDocument.getProtocolOnlineReview());
        } 
                
    }
   
    
    @Override
    protected ProtocolOnlineReviewDocumentBase getNewProtocolOnlineReviewDocumentInstanceHook() {
        return new ProtocolOnlineReviewDocument();
    }

    @Override
    protected String getProtocolOLRSavedStatusCodeHook() {
        return ProtocolOnlineReviewStatus.SAVED_STATUS_CD;
    }

    @Override
    protected String getProtocolOLRRemovedCancelledStatusCodeHook() {
        return ProtocolOnlineReviewStatus.REMOVED_CANCELLED_STATUS_CD;
    }

    @Override
    protected String getProtocolOLRDocumentTypeHook() {
        return PROTOCOL_ONLINE_REVIEW_DOCUMENT_TYPE;
    }

    @Override
    protected ProtocolReviewer createNewProtocolReviewerInstanceHook() {
        return new ProtocolReviewer();
    }

    @Override
    protected Class<? extends ProtocolOnlineReviewBase> getProtocolOnlineReviewBOClassHook() {
        return ProtocolOnlineReview.class;
    }

    @Override
    protected Class<? extends ProtocolSubmissionBase> getProtocolSubmissionBOClassHook() {
        return ProtocolSubmission.class;
    }
}
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
package org.kuali.kra.iacuc.actions.reviewcomments;

import org.kuali.coeus.common.committee.impl.meeting.CommitteeScheduleMinuteBase;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolReviewer;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionStatus;
import org.kuali.kra.iacuc.committee.meeting.IacucCommitteeScheduleMinute;
import org.kuali.kra.iacuc.onlinereview.IacucProtocolOnlineReview;
import org.kuali.kra.iacuc.onlinereview.IacucProtocolReviewAttachment;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsServiceImplBase;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewer;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewBase;
import org.kuali.kra.protocol.onlinereview.ProtocolReviewAttachmentBase;

import java.util.List;

public class IacucReviewCommentsServiceImpl extends ReviewCommentsServiceImplBase<IacucProtocolReviewAttachment> implements IacucReviewCommentsService {
    
  private static final String[] PROTOCOL_SUBMISSION_COMPLETE_STATUSES = { IacucProtocolSubmissionStatus.ADMINISTRATIVELY_APPROVED,
                                                                          IacucProtocolSubmissionStatus.APPROVED,                                                                           
                                                                          IacucProtocolSubmissionStatus.MINOR_REVISIONS_REQUIRED,
                                                                          IacucProtocolSubmissionStatus.MAJOR_REVISIONS_REQUIRED,
                                                                          IacucProtocolSubmissionStatus.DISAPPROVED,
                                                                          IacucProtocolSubmissionStatus.TABLED,
                                                                          IacucProtocolSubmissionStatus.RETURNED_TO_PI};

    public void saveReviewAttachments(List<IacucProtocolReviewAttachment> reviewAttachments, List<IacucProtocolReviewAttachment> deletedReviewAttachments) {
        for (ProtocolReviewAttachmentBase reviewAttachment : reviewAttachments) {
            boolean doUpdate = true;
            if (doUpdate) {
                reviewAttachment.setPrivateFlag(!reviewAttachment.isProtocolPersonCanView());
                businessObjectService.save(reviewAttachment);
            }
        }

        if (!deletedReviewAttachments.isEmpty()) {
            // TODO : bos expecting the object defined in repository
            businessObjectService.delete(deletedReviewAttachments);
        }
    }

    @Override
    protected ProtocolSubmissionBase getSubmission(ProtocolBase protocol) {
        ProtocolSubmissionBase protocolSubmission = protocol.getProtocolSubmission();
        if (protocol.getNotifyIrbSubmissionId() != null) {
            // not the current submission, then check programically
            for (ProtocolSubmissionBase submission : protocol.getProtocolSubmissions()) {
                if (submission.getSubmissionId().equals(protocol.getNotifyIrbSubmissionId())) {
                    protocolSubmission = submission;
                    break;
                }
            }
        }
        return protocolSubmission;
    }

    @Override
    protected Class<IacucProtocolReviewAttachment> getProtocolReviewAttachmentClassHook() {
        return IacucProtocolReviewAttachment.class;
    }

    @Override
    protected String getAdministratorRoleHook() {
        return RoleConstants.IACUC_ADMINISTRATOR;
    }

    @Override
    protected Class<? extends ProtocolReviewer> getProtocolReviewClassHook() {
        return IacucProtocolReviewer.class;

    }

    @Override
    protected String getAggregatorRoleNameHook() {
        return RoleConstants.IACUC_PROTOCOL_AGGREGATOR;
    }

    @Override
    protected String getNamespaceHook() {
        return Constants.MODULE_NAMESPACE_IACUC;
    }

    @Override
    protected String getProtocolViewerRoleNameHook() {
        return RoleConstants.IACUC_PROTOCOL_VIEWER;
    }


    @Override
    protected String getDisplayRevNameToActiveCmtMembersHook() {
        return Constants.PARAMETER_IACUC_DISPLAY_REVIEWER_NAME_TO_ACTIVE_COMMITTEE_MEMBERS;
    }

    @Override
    protected String getDisplayRevNameToProtocolPersonnelHook() {
        return Constants.PARAMETER_IACUC_DISPLAY_REVIEWER_NAME_TO_PROTOCOL_PERSONNEL;
    }

    @Override
    protected String getDisplayRevNameToReviewersHook() {
        return Constants.PARAMETER_IACUC_DISPLAY_REVIEWER_NAME_TO_REVIEWERS;
    }

    @Override
    protected String[] getProtocolSubmissionCompleteStatusCodeArrayHook() {
        return IacucReviewCommentsServiceImpl.PROTOCOL_SUBMISSION_COMPLETE_STATUSES;
    }

    @Override
    protected Class<? extends ProtocolOnlineReviewBase> getProtocolOnlineReviewClassHook() {
        return IacucProtocolOnlineReview.class;
    }

    @Override
    protected Class<? extends CommitteeScheduleMinuteBase> getCommitteeScheduleMinuteBOClassHook() {
        return IacucCommitteeScheduleMinute.class;
    }

    @Override
    protected Class<? extends ProtocolDocumentBase> getProtocolDocumentBOClassHook() {
        return IacucProtocolDocument.class;
    }

}

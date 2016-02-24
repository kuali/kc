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

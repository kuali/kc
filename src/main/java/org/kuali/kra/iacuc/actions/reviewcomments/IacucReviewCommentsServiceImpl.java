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
package org.kuali.kra.iacuc.actions.reviewcomments;

import java.util.List;

import org.kuali.kra.iacuc.actions.submit.IacucProtocolReviewer;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionStatus;
import org.kuali.kra.iacuc.onlinereview.IacucProtocolOnlineReview;
import org.kuali.kra.iacuc.onlinereview.IacucProtocolReviewAttachment;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsServiceImpl;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewer;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmission;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReview;
import org.kuali.kra.protocol.onlinereview.ProtocolReviewAttachment;

public class IacucReviewCommentsServiceImpl extends ReviewCommentsServiceImpl<IacucProtocolReviewAttachment> implements IacucReviewCommentsService {
    
  private static final String[] PROTOCOL_SUBMISSION_COMPLETE_STATUSES = { IacucProtocolSubmissionStatus.APPROVED,                                                                           
                                                                          IacucProtocolSubmissionStatus.MINOR_REVISIONS_REQUIRED,
                                                                          IacucProtocolSubmissionStatus.MAJOR_REVISIONS_REQUIRED,
                                                                          IacucProtocolSubmissionStatus.DISAPPROVED };

    public void saveReviewAttachments(List<IacucProtocolReviewAttachment> reviewAttachments, List<IacucProtocolReviewAttachment> deletedReviewAttachments) {
        for (ProtocolReviewAttachment reviewAttachment : reviewAttachments) {
            boolean doUpdate = true;
            // if (reviewAttachment.getReviewerAttachmentId() != null) {
            // ProtocolOnlineReviewAttachment existing =
            // committeeScheduleService.getCommitteeScheduleMinute(reviewAttachment.getCommScheduleMinutesId());
            // doUpdate = !reviewAttachment.equals(existing);
            // }
            if (doUpdate) {
                reviewAttachment.setPrivateFlag(!reviewAttachment.isProtocolPersonCanView());
                businessObjectService.save(reviewAttachment);
            }
        }

        if (!deletedReviewAttachments.isEmpty()) {
            // for (ProtocolReviewAttachment reviewAttachment : deletedReviewAttachments) {
            // businessObjectService.delete((IacucProtocolReviewAttachment)reviewAttachment);
            // }
            // TODO : bos expecting the object defined in repository
            businessObjectService.delete(deletedReviewAttachments);
        }
    }

    @Override
    protected ProtocolSubmission getSubmission(Protocol protocol) {
        ProtocolSubmission protocolSubmission = protocol.getProtocolSubmission();
        if (protocol.getNotifyIrbSubmissionId() != null) {
            // not the current submission, then check programically
            for (ProtocolSubmission submission : protocol.getProtocolSubmissions()) {
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
    protected Class<? extends ProtocolOnlineReview> getProtocolOnlineReviewClassHook() {
        return IacucProtocolOnlineReview.class;
    }

}

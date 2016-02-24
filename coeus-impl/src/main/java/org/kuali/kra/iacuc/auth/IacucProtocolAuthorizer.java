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
package org.kuali.kra.iacuc.auth;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeDecisionMotionType;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.IacucProtocolStatus;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolReviewType;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionStatus;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.auth.ProtocolAuthorizerBase;
import org.kuali.kra.protocol.auth.ProtocolTaskBase;
import org.kuali.rice.krad.util.ObjectUtils;

public abstract class IacucProtocolAuthorizer extends ProtocolAuthorizerBase {
    
    
    public final boolean isAuthorized(String userId, ProtocolTaskBase task) {
        return isAuthorized(userId, (IacucProtocolTask) task);
    }
    

    public abstract boolean isAuthorized(String userId, IacucProtocolTask task);

    protected boolean canPerformApproval(IacucProtocolAction lastAction, IacucProtocolSubmission lastSubmission) {
        boolean canPerform = false;
        if (lastAction != null && lastSubmission != null) {
            canPerform = ((isRecordCommitteeDecision(lastAction) && isMotionTypeApprove(lastSubmission)) ||
                    isDesignatedMemberReview(lastAction, lastSubmission));
        }
        return canPerform;
    }

    
    protected boolean canPerformSMR(IacucProtocolAction lastAction, IacucProtocolSubmission lastSubmission) {
        boolean canPerform = false;
        if (lastAction != null && lastSubmission != null) {
            canPerform = ((isRecordCommitteeDecision(lastAction) && isMotionTypeMinorRevision(lastSubmission)) ||
                    isDesignatedMemberReview(lastAction, lastSubmission));
        }
        return canPerform;
    }

    protected boolean canPerformSRR(IacucProtocolAction lastAction, IacucProtocolSubmission lastSubmission) {
        boolean canPerform = false;
        if (lastAction != null && lastSubmission != null) {
            canPerform = ((isRecordCommitteeDecision(lastAction) && isMotionTypeSubstantiveRevision(lastSubmission)) ||
                    isDesignatedMemberReview(lastAction, lastSubmission));
        }
        return canPerform;
    }
    
    private boolean isMotionTypeMinorRevision(IacucProtocolSubmission lastSubmission) {
        return CommitteeDecisionMotionType.SPECIFIC_MINOR_REVISIONS.equals(lastSubmission.getCommitteeDecisionMotionTypeCode());
    }

    private boolean isMotionTypeSubstantiveRevision(IacucProtocolSubmission lastSubmission) {
        return CommitteeDecisionMotionType.SUBSTANTIVE_REVISIONS_REQUIRED.equals(lastSubmission.getCommitteeDecisionMotionTypeCode());
    }

    private boolean isMotionTypeApprove(IacucProtocolSubmission lastSubmission) {
        return CommitteeDecisionMotionType.APPROVE.equals(lastSubmission.getCommitteeDecisionMotionTypeCode());
    }
    
    private boolean isRecordCommitteeDecision(IacucProtocolAction lastAction) {
        return IacucProtocolActionType.RECORD_COMMITTEE_DECISION.equals(lastAction.getProtocolActionTypeCode());
    }
    
    /**
     * This method is to check whether submission is DMR
     * User should be able to directly perform these actions (approve, minor and major revisions) 
     * when it is DMR rather than going through record committee decision.  
     * @param lastAction
     * @param lastSubmission
     * @return
     */
    private boolean isDesignatedMemberReview(IacucProtocolAction lastAction, IacucProtocolSubmission lastSubmission) {
        if(ObjectUtils.isNull(lastAction.getProtocol())) {
            lastAction.refreshReferenceObject("protocol");
        }
        return (IacucProtocolStatus.SUBMITTED_TO_IACUC.equals(lastAction.getProtocol().getProtocolStatusCode()) 
                && IacucProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE.equals(lastSubmission.getSubmissionStatusCode())
                && IacucProtocolReviewType.DESIGNATED_MEMBER_REVIEW.equals(lastSubmission.getProtocolReviewTypeCode()));
    }

    protected ProtocolSubmissionBase findSubmission(ProtocolBase protocol) {
        // need to loop thru to find the last submission.
        // it may have submit/Wd/notify iacuc/submit, and this will cause problem if don't loop thru.
        ProtocolSubmissionBase protocolSubmission = null;
        for (ProtocolSubmissionBase submission : protocol.getProtocolSubmissions()) {
            if (StringUtils.equals(submission.getSubmissionStatusCode(), IacucProtocolSubmissionStatus.PENDING) || 
                StringUtils.equals(submission.getSubmissionStatusCode(), IacucProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE) || 
                (StringUtils.equals(protocol.getProtocolStatusCode(), IacucProtocolStatus.TABLED) &&
                  StringUtils.equals(submission.getSubmissionStatusCode(), IacucProtocolSubmissionStatus.TABLED))) {
                protocolSubmission = submission;
            }
        }
        return protocolSubmission;
    }

}

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

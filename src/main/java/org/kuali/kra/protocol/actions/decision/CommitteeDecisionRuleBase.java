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
package org.kuali.kra.protocol.actions.decision;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.common.committee.bo.CommitteeDecisionMotionType;
import org.kuali.kra.common.committee.meeting.CommitteeScheduleMinuteBase;
import org.kuali.kra.common.committee.service.CommitteeScheduleAttendanceServiceBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsBeanBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class runs the rules needed for committee decision recording.
 */
public abstract class CommitteeDecisionRuleBase<CD extends CommitteeDecision<?>> extends ResearchDocumentRuleBase implements ExecuteCommitteeDecisionRule<CD> {
    
    private static final String DOT = ".";
    private static final String MOTION_FIELD = "motionTypeCode";
    private static final String YES_COUNT_FIELD = "yesCount";
    private static final String NO_COUNT_FIELD = "noCount";
    
    private CommitteeScheduleAttendanceServiceBase attendanceService;
    
    protected final CommitteeScheduleAttendanceServiceBase getAttendanceService() {
        if (attendanceService == null) {
            attendanceService = KraServiceLocator.getService(getCommitteeScheduleAttendanceServiceClassHook());
        }
        return attendanceService;
    }
    
    protected abstract Class<? extends CommitteeScheduleAttendanceServiceBase> getCommitteeScheduleAttendanceServiceClassHook();
    

    /**
     * This is a convenience method to use jmock to set the businessObjectService for unit testing.
     * @param businessObjectService
     */
    public void setAttendanceService(CommitteeScheduleAttendanceServiceBase attendanceService){
        this.attendanceService = attendanceService;
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.protocol.actions.decision.ExecuteCommitteeDecisionRule#proccessCommitteeDecisionRule(org.kuali.kra.protocol.ProtocolDocumentBase, 
     *                                                                                                    org.kuali.kra.protocol.actions.decision.CommitteeDecision)
     */
    public boolean proccessCommitteeDecisionRule(ProtocolDocumentBase document, CD committeeDecision) {
        boolean isValid = true;

        isValid &= processMotion(committeeDecision);
        isValid &= processCounts(document.getProtocol().getProtocolSubmission(), committeeDecision);
        
        return isValid;
    }
    
    private boolean processMotion(CD committeeDecision) {
        boolean retVal = true;
        
        if (StringUtils.isBlank(committeeDecision.getMotionTypeCode())) { 
            reportError(Constants.PROTOCOL_COMMITTEE_DECISION_ACTION_PROPERTY_KEY + DOT + MOTION_FIELD, KeyConstants.ERROR_PROTOCOL_RECORD_COMMITEE_NO_MOTION);
            retVal = false;
        } else {
            String motionTypeCode = committeeDecision.getMotionTypeCode();
            ReviewCommentsBeanBase reviewerCommentsBean = committeeDecision.getReviewCommentsBean();
            List<CommitteeScheduleMinuteBase> reviewComments = reviewerCommentsBean.getReviewComments();
            ProtocolBase protocol = committeeDecision.getProtocol();
            if ((CommitteeDecisionMotionType.SPECIFIC_MINOR_REVISIONS.equals(motionTypeCode) 
                    || CommitteeDecisionMotionType.SUBSTANTIVE_REVISIONS_REQUIRED.equals(motionTypeCode)
                    || CommitteeDecisionMotionType.DISAPPROVE.equals(motionTypeCode)) 
                    && CollectionUtils.isEmpty(filterReviewComments(reviewComments, protocol))) {
                reportError(Constants.PROTOCOL_COMMITTEE_DECISION_ACTION_PROPERTY_KEY + DOT + MOTION_FIELD, getNoCommentsForRevisionsErrorMessageHook());
                retVal = false;
            }
        }
        
        return retVal;
    }
    
    // this hook can be overridden to change the error message; this is a rare case of a non-abstract (default) hook introduced during IACUC refactoring
    protected String getNoCommentsForRevisionsErrorMessageHook() {
        return KeyConstants.ERROR_PROTOCOL_RECORD_COMMITEE_NO_SMR_SRR_DISAPPROVE_REVIEWER_COMMENTS;
    }

    private List<CommitteeScheduleMinuteBase> filterReviewComments(List<CommitteeScheduleMinuteBase> reviewComments, ProtocolBase protocol) {
        List<CommitteeScheduleMinuteBase> filteredComments = new ArrayList<CommitteeScheduleMinuteBase>();
        for (CommitteeScheduleMinuteBase comment : reviewComments) {
            if (protocol.getProtocolId().equals(comment.getProtocolId())) {
                filteredComments.add(comment);
            }
        }
        return filteredComments;
    }
    
    protected boolean processCounts(ProtocolSubmissionBase submission, CD committeeDecision) {
        boolean retVal = true;
        
        String committeeId = submission.getCommittee() != null ? submission.getCommittee().getCommitteeId() : null;
        String scheduleId = submission.getScheduleId();
        int membersPresent = getAttendanceService().getActualVotingMembersCount(committeeId, scheduleId);
        
        if (membersPresent < committeeDecision.getTotalVoteCount()) {
            reportError(Constants.PROTOCOL_COMMITTEE_DECISION_ACTION_PROPERTY_KEY, KeyConstants.ERROR_PROTOCOL_RECORD_COMMITEE_INVALID_VOTE_COUNT);
            retVal = false;
        }
       
        if(committeeDecision.getYesCount() == null) {
            reportError(Constants.PROTOCOL_COMMITTEE_DECISION_ACTION_PROPERTY_KEY + DOT + YES_COUNT_FIELD, KeyConstants.ERROR_PROTOCOL_RECORD_COMMITEE_NO_YES_VOTES);
            retVal = false;
        }
        else if(committeeDecision.getYesCountValue() <= 0) {
            reportError(Constants.PROTOCOL_COMMITTEE_DECISION_ACTION_PROPERTY_KEY + DOT + YES_COUNT_FIELD, KeyConstants.ERROR_PROTOCOL_RECORD_COMMITEE_YES_VOTES_NOT_POSITIVE);
            retVal = false;
        }
        else if(!(committeeDecision.getYesCountValue() > committeeDecision.getNoCountValue())) {
            reportError(Constants.PROTOCOL_COMMITTEE_DECISION_ACTION_PROPERTY_KEY, KeyConstants.ERROR_PROTOCOL_RECORD_COMMITEE_VOTE_YES_NOT_GREATER);
            retVal = false;
        }

        if(committeeDecision.getNoCountValue() < 0) {
            reportError(Constants.PROTOCOL_COMMITTEE_DECISION_ACTION_PROPERTY_KEY + DOT + NO_COUNT_FIELD, KeyConstants.ERROR_PROTOCOL_RECORD_COMMITEE_NO_VOTES_NOT_NONNEGATIVE);
            retVal = false;
        }
        
        return retVal;
    }
    
}

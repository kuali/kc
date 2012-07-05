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
package org.kuali.kra.protocol.actions.decision;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.common.committee.bo.CommitteeDecisionMotionType;
import org.kuali.kra.common.committee.service.CommonCommitteeScheduleAttendanceService;
import org.kuali.kra.common.committee.meeting.CommitteeScheduleMinute;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.ProtocolDocument;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsBean;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmission;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

/**
 * 
 * This class runs the rules needed for committee decision recording.
 */
public class CommitteeDecisionRule<CD extends CommitteeDecision<?>> extends ResearchDocumentRuleBase implements ExecuteCommitteeDecisionRule<CD> {
    
    private static final String DOT = ".";
    private static final String MOTION_FIELD = "motionTypeCode";
    private static final String YES_COUNT_FIELD = "yesCount";
    private static final String NO_COUNT_FIELD = "noCount";
    
    private CommonCommitteeScheduleAttendanceService attendanceService;
    
    protected final CommonCommitteeScheduleAttendanceService getAttendanceService() {
        if (attendanceService == null) {
            attendanceService = KraServiceLocator.getService(CommonCommitteeScheduleAttendanceService.class);
        }
        return attendanceService;
    }
    
    /**
     * This is a convenience method to use jmock to set the businessObjectService for unit testing.
     * @param businessObjectService
     */
    public void setAttendanceService(CommonCommitteeScheduleAttendanceService attendanceService){
        this.attendanceService = attendanceService;
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.protocol.actions.decision.ExecuteCommitteeDecisionRule#proccessCommitteeDecisionRule(org.kuali.kra.protocol.ProtocolDocument, 
     *                                                                                                    org.kuali.kra.protocol.actions.decision.CommitteeDecision)
     */
    public boolean proccessCommitteeDecisionRule(ProtocolDocument document, CD committeeDecision) {
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
            ReviewCommentsBean reviewerCommentsBean = committeeDecision.getReviewCommentsBean();
            List<CommitteeScheduleMinute> reviewComments = reviewerCommentsBean.getReviewComments();
            Protocol protocol = committeeDecision.getProtocol();
            if ((CommitteeDecisionMotionType.SPECIFIC_MINOR_REVISIONS.equals(motionTypeCode) 
                    || CommitteeDecisionMotionType.SUBSTANTIVE_REVISIONS_REQUIRED.equals(motionTypeCode)) 
                    && CollectionUtils.isEmpty(filterReviewComments(reviewComments, protocol))) {
                reportError(Constants.PROTOCOL_COMMITTEE_DECISION_ACTION_PROPERTY_KEY + DOT + MOTION_FIELD, 
                            KeyConstants.ERROR_PROTOCOL_RECORD_COMMITEE_NO_SMR_SRR_REVIEWER_COMMENTS);
                retVal = false;
            }
        }
        
        return retVal;
    }
    
    private List<CommitteeScheduleMinute> filterReviewComments(List<CommitteeScheduleMinute> reviewComments, Protocol protocol) {
        List<CommitteeScheduleMinute> filteredComments = new ArrayList<CommitteeScheduleMinute>();
        for (CommitteeScheduleMinute comment : reviewComments) {
            if (protocol.getProtocolId().equals(comment.getProtocolId())) {
                filteredComments.add(comment);
            }
        }
        return filteredComments;
    }
    
    private boolean processCounts(ProtocolSubmission submission, CD committeeDecision) {
        boolean retVal = true;
        
        String committeeId = submission.getCommittee() != null ? submission.getCommittee().getCommitteeId() : null;
        String scheduleId = submission.getScheduleId();
        int membersPresent = getAttendanceService().getActualVotingMembersCount(committeeId, scheduleId);
        
        if (membersPresent < committeeDecision.getTotalVoteCount()) {
            reportError(Constants.PROTOCOL_COMMITTEE_DECISION_ACTION_PROPERTY_KEY, 
                    KeyConstants.ERROR_PROTOCOL_RECORD_COMMITEE_INVALID_VOTE_COUNT);
            retVal = false;
        }
        if (CommitteeDecisionMotionType.APPROVE.equals(committeeDecision.getMotionTypeCode()) && committeeDecision.getYesCount() == null) {
            reportError(Constants.PROTOCOL_COMMITTEE_DECISION_ACTION_PROPERTY_KEY + DOT + YES_COUNT_FIELD, 
                    KeyConstants.ERROR_PROTOCOL_RECORD_COMMITEE_NO_YES_VOTES);
            retVal = false;
        }
        if (CommitteeDecisionMotionType.DISAPPROVE.equals(committeeDecision.getMotionTypeCode()) && committeeDecision.getNoCount() == null) {
            reportError(Constants.PROTOCOL_COMMITTEE_DECISION_ACTION_PROPERTY_KEY + DOT + NO_COUNT_FIELD, 
                    KeyConstants.ERROR_PROTOCOL_RECORD_COMMITEE_NO_NO_VOTES);
            retVal = false;
        }
        return retVal;
    }
    
}
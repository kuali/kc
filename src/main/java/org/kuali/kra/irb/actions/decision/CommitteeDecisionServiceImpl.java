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
package org.kuali.kra.irb.actions.decision;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.reviewcomments.ReviewerComments;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.kra.meeting.MinuteEntryType;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * The CommitteeDecisionService implementation.
 */
public class CommitteeDecisionServiceImpl implements CommitteeDecisionService {

    private BusinessObjectService businessObjectService;
    private ProtocolActionService protocolActionService;
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    public void setProtocolActionService(ProtocolActionService protocolActionService){
        this.protocolActionService = protocolActionService;
    }
    
    /**
     * @see org.kuali.kra.irb.actions.decision.CommitteeDecisionService#setCommitteeDecision(org.kuali.kra.irb.Protocol, org.kuali.kra.irb.actions.decision.CommitteeDecision)
     */
    public void setCommitteeDecision(Protocol protocol, CommitteeDecision committeeDecision) {
        ProtocolSubmission submission = getSubmission(protocol);
        if (submission != null) {
            submission.setYesVoteCount(committeeDecision.getYesCount());
            submission.setNoVoteCount(committeeDecision.getNoCount());
            submission.setAbstainerCount(committeeDecision.getAbstainCount());
            submission.setVotingComments(committeeDecision.getVotingComments());
            addReviewerComments(submission, committeeDecision.getReviewComments());
            
            String protocolActionTypeToUse = "";
            boolean doAddProtocolAction = false;
            String motionToCompareFrom = committeeDecision.getMotion() == null ? committeeDecision.getMotion() : committeeDecision.getMotion().trim(); 
            if (MotionValuesFinder.APPROVE.equals(motionToCompareFrom)) {
                protocolActionTypeToUse = ProtocolActionType.APPROVED;
                doAddProtocolAction = true;
            } else if (MotionValuesFinder.DISAPPROVE.equals(committeeDecision.getMotion().trim())) {
                protocolActionTypeToUse = ProtocolActionType.DISAPPROVED;
                doAddProtocolAction = true;
            }
            
            if (doAddProtocolAction) {
                ProtocolAction protocolAction = new ProtocolAction(protocol, submission, protocolActionTypeToUse);
                protocolAction.setComments(committeeDecision.getVotingComments());
                this.protocolActionService.updateProtocolStatus(protocolAction, protocol);
                protocol.getProtocolActions().add(protocolAction);
                businessObjectService.save(protocolAction);
            }
            
            businessObjectService.save(submission);
            businessObjectService.save(protocol);
            
            protocol.refresh();
        }
    }

    private ProtocolSubmission getSubmission(Protocol protocol) {
        for (ProtocolSubmission submission : protocol.getProtocolSubmissions()) {
            if (StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.IN_AGENDA)) {
                return submission;
            }
        }
        return null;
    }
    
    private void addReviewerComments(ProtocolSubmission submission, ReviewerComments reviewComments) {
        int nextEntryNumber = 0;
        for (CommitteeScheduleMinute minute : reviewComments.getComments()) {
            minute.setEntryNumber(nextEntryNumber);
            minute.setMinuteEntryTypeCode(MinuteEntryType.PROTOCOL);
            minute.setSubmissionIdFk(submission.getSubmissionId());
            minute.setProtocolIdFk(submission.getProtocolId());
            minute.setScheduleIdFk(submission.getScheduleIdFk());
            nextEntryNumber++;
        }
    }
}

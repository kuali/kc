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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.reviewcomments.ReviewerComments;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.kra.meeting.MinuteEntryType;
import org.kuali.kra.meeting.ProtocolMeetingVoter;
import org.kuali.kra.meeting.ProtocolVoteAbstainee;
import org.kuali.kra.meeting.ProtocolVoteRecused;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * The CommitteeDecisionService implementation.
 */
public class CommitteeDecisionServiceImpl implements CommitteeDecisionService {

    private BusinessObjectService businessObjectService;
    private ProtocolActionService protocolActionService;
    private CommitteeService committeeService;
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    public void setProtocolActionService(ProtocolActionService protocolActionService) {
        this.protocolActionService = protocolActionService;
    }
    public void setCommitteeService(CommitteeService committeeService) {
        this.committeeService = committeeService;
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
            submission.setRecusedCount(committeeDecision.getRecusedCount());
            submission.setVotingComments(committeeDecision.getVotingComments());
            
            addReviewerComments(submission, committeeDecision.getReviewComments());
            
            String protocolActionTypeToUse = "";
            boolean doAddProtocolAction = false;
            String motionToCompareFrom = committeeDecision.getMotion() == null ? committeeDecision.getMotion() : committeeDecision.getMotion().trim();
            
            if (MotionValuesFinder.APPROVE.equals(motionToCompareFrom)) {
                protocolActionTypeToUse = ProtocolActionType.APPROVED;
                doAddProtocolAction = true;
            } else if (MotionValuesFinder.DISAPPROVE.equals(motionToCompareFrom)) {
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
            
            List<CommitteeMembership> committeeMemberships =  
                committeeService.getAvailableMembers(protocol.getProtocolSubmission().getCommittee().getCommitteeId(), 
                        protocol.getProtocolSubmission().getScheduleId());
            
            proccessAbstainers(committeeDecision, committeeMemberships, protocol, submission.getScheduleIdFk(), submission.getSubmissionId());
            proccessRecusers(committeeDecision, committeeMemberships, protocol, submission.getScheduleIdFk(), submission.getSubmissionId());

            businessObjectService.save(submission);
            businessObjectService.save(protocol);
            
            protocol.refresh();
        }
    }
    
    private void proccessAbstainers(CommitteeDecision committeeDecision, List<CommitteeMembership> committeeMemberships, 
            Protocol protocol, Long scheduleIdFk, Long submissionIdFk) {       
        if (!committeeDecision.getAbstainers().isEmpty()) {
            //there are abstainers, lets save them
            for (CommitteePerson person : committeeDecision.getAbstainers()) {
                for (CommitteeMembership membership : committeeMemberships) {
                    if (membership.getCommitteeMembershipId().equals(person.getMembershipId())) {
                        //check to see if it is already been persisted
                        Map fieldValues = getFieldValuesMap(protocol.getProtocolId(), scheduleIdFk, membership.getPersonId(), submissionIdFk);
                        if (businessObjectService.findMatching(ProtocolVoteAbstainee.class, fieldValues).size() == 0) {
                            //we found a match, and has not been saved, lets make a ProtocolVoteAbstainee and save it
                            saveProtocolMeetingVoter(new ProtocolVoteAbstainee(), protocol, scheduleIdFk, membership.getPersonId(), submissionIdFk);
                        }
                        break;
                    }
                }
            }
        }       
        if (!committeeDecision.getAbstainersToDelete().isEmpty()) {
            for (CommitteePerson person : committeeDecision.getAbstainersToDelete()) {
                for (CommitteeMembership membership : committeeMemberships) {
                    if (membership.getCommitteeMembershipId().equals(person.getMembershipId())) {
                        Map fieldValues = getFieldValuesMap(protocol.getProtocolId(), scheduleIdFk, membership.getPersonId(), submissionIdFk);
                        businessObjectService.deleteMatching(ProtocolVoteAbstainee.class, fieldValues);
                    }
                }
            }
            committeeDecision.getAbstainersToDelete().clear();
        }
        
    }
    
    private void proccessRecusers(CommitteeDecision committeeDecision, List<CommitteeMembership> committeeMemberships, 
            Protocol protocol, Long scheduleIdFk, Long submissionIdFk) {     
        
        if (!committeeDecision.getRecused().isEmpty()) {
            //there are recusers, lets save them
            for (CommitteePerson person : committeeDecision.getRecused()) {
                for (CommitteeMembership membership : committeeMemberships) {
                    if (membership.getCommitteeMembershipId().equals(person.getMembershipId())) {
                        //check to see if it is already been persisted
                        Map fieldValues = getFieldValuesMap(protocol.getProtocolId(), scheduleIdFk, membership.getPersonId(), submissionIdFk);
                        if (businessObjectService.findMatching(ProtocolVoteRecused.class, fieldValues).size() == 0) {
                            //we found a match, and has not been saved, lets make a ProtocolVoteAbstainee and save it
                            saveProtocolMeetingVoter(new ProtocolVoteRecused(), protocol, scheduleIdFk, membership.getPersonId(), submissionIdFk);
                        }
                        break;
                    }
                }
            }
        }
        
        if (!committeeDecision.getRecusedToDelete().isEmpty()) {
            for (CommitteePerson person : committeeDecision.getRecusedToDelete()) {
                for (CommitteeMembership membership : committeeMemberships) {
                    if (membership.getCommitteeMembershipId().equals(person.getMembershipId())) {
                        Map fieldValues = getFieldValuesMap(protocol.getProtocolId(), scheduleIdFk, membership.getPersonId(), submissionIdFk);
                        businessObjectService.deleteMatching(ProtocolVoteRecused.class, fieldValues);
                    }
                }
            }
            committeeDecision.getRecusedToDelete().clear();
        }
    }
    
    private void saveProtocolMeetingVoter(ProtocolMeetingVoter voter, Protocol protocol, Long scheduleIdFk, String personId, Long submissionIdFk) {
        voter.setProtocol(protocol);
        voter.setProtocolIdFk(protocol.getProtocolId());
        voter.setSubmissionIdFk(submissionIdFk);
        //voter.setScheduleIdFk(scheduleIdFk);
        voter.setPersonId(personId);
        businessObjectService.save(voter);
    }
    
    private Map<String, String> getFieldValuesMap(Long protocolId, Long scheduleIdFk, String personId, Long submissionIdFk) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("PROTOCOL_ID_FK", protocolId.toString());
        //fieldValues.put("SCHEDULE_ID_FK", scheduleIdFk.toString());
        fieldValues.put("PERSON_ID", personId);
        fieldValues.put("SUBMISSION_ID_FK", submissionIdFk.toString());
        return fieldValues;
    }

    private ProtocolSubmission getSubmission(Protocol protocol) {
        for (ProtocolSubmission submission : protocol.getProtocolSubmissions()) {
            if (StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.IN_AGENDA)
                    || StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE)) {
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
    
    /**
     * 
     * @see org.kuali.kra.irb.actions.decision.CommitteeDecisionService#getMeetingVoters(java.lang.Long, java.lang.Long, java.lang.Class)
     */
    @SuppressWarnings("unchecked")
    public List<? extends ProtocolMeetingVoter> getMeetingVoters(Long protocolId, Long submissionIdFk, Class<? extends ProtocolMeetingVoter> clazz) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("protocolIdFk", protocolId.toString());
        fieldValues.put("submissionIdFk", submissionIdFk.toString());
        return (List<? extends ProtocolMeetingVoter>) businessObjectService.findMatching(clazz, fieldValues);

    }
}


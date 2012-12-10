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
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.common.committee.meeting.ProtocolVoteAbstaineeBase;
import org.kuali.kra.common.committee.meeting.ProtocolVoteRecusedBase;
import org.kuali.kra.meeting.ProtocolVoteAbstainee;
import org.kuali.kra.meeting.ProtocolVoteRecused;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.decision.CommitteeDecisionServiceImplBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;

/**
 * The CommitteeDecisionService implementation.
 */
public class CommitteeDecisionServiceImpl extends CommitteeDecisionServiceImplBase<CommitteeDecision> implements CommitteeDecisionService {

// TODO ********************** commented out during IRB backfit ************************    
//    private BusinessObjectService businessObjectService;
//    private ProtocolActionService protocolActionService;
//    private CommitteeService committeeService;
//    private DocumentService documentService;
//    private ProtocolFinderDao protocolFinderDao;
//
//    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
//        this.businessObjectService = businessObjectService;
//    }
//    
//    public void setProtocolActionService(ProtocolActionService protocolActionService) {
//        this.protocolActionService = protocolActionService;
//    }
//    
//    public void setCommitteeService(CommitteeService committeeService) {
//        this.committeeService = committeeService;
//    }
//
//    public void setDocumentService(DocumentService documentService) {
//        this.documentService = documentService;
//    }
//
//    /**
//     * {@inheritDoc}
//     * @see org.kuali.kra.irb.actions.decision.CommitteeDecisionService#setCommitteeDecision(org.kuali.kra.irb.Protocol, 
//     *      org.kuali.kra.irb.actions.decision.CommitteeDecision)
//     */
//    public void processCommitteeDecision(Protocol protocol, CommitteeDecision committeeDecision) throws Exception {
//        ProtocolSubmission submission = getSubmission(protocol);
//        
//        if (submission != null) {
//            submission.setCommitteeDecisionMotionTypeCode(committeeDecision.getMotionTypeCode());
//            submission.setYesVoteCount(committeeDecision.getYesCount());
//            submission.setNoVoteCount(committeeDecision.getNoCount());
//            submission.setAbstainerCount(committeeDecision.getAbstainCount());
//            submission.setRecusedCount(committeeDecision.getRecusedCount());
//            submission.setVotingComments(committeeDecision.getVotingComments());
//            
//            addReviewComments(submission, committeeDecision.getReviewCommentsBean());
//
//            ProtocolAction protocolAction = new ProtocolAction(protocol, submission, ProtocolActionType.RECORD_COMMITTEE_DECISION);
//            protocolAction.setComments(committeeDecision.getVotingComments());
//            protocolActionService.updateProtocolStatus(protocolAction, protocol);
//            protocol.getProtocolActions().add(protocolAction);
//            businessObjectService.save(protocolAction);
//            
//            List<CommitteeMembership> committeeMemberships =  
//                committeeService.getAvailableMembers(protocol.getProtocolSubmission().getCommitteeId(), protocol.getProtocolSubmission().getScheduleId());
//            
//            proccessAbstainers(committeeDecision, committeeMemberships, protocol, submission.getScheduleIdFk(), submission.getSubmissionId());
//            proccessRecusers(committeeDecision, committeeMemberships, protocol, submission.getScheduleIdFk(), submission.getSubmissionId());
//            
//            documentService.saveDocument(protocol.getProtocolDocument());
//            protocol.refresh();
//        }
//    }
    
    protected String getProtocolActionTypeCodeForRecordCommitteeDecisionHook() {
        return ProtocolActionType.RECORD_COMMITTEE_DECISION;
    }

    protected ProtocolActionBase getNewProtocolActionInstanceHook(ProtocolBase protocol, ProtocolSubmissionBase submission, String recordCommitteeDecisionActionCode) {
        return new ProtocolAction((Protocol) protocol, (ProtocolSubmission) submission, recordCommitteeDecisionActionCode);
    }
    
    
// TODO ********************** commented out during IRB backfit ************************
//    protected void proccessAbstainers(CommitteeDecision committeeDecision, List<CommitteeMembership> committeeMemberships, 
//            Protocol protocol, Long scheduleIdFk, Long submissionIdFk) {       
//        if (!committeeDecision.getAbstainers().isEmpty()) {
//            //there are abstainers, lets save them
//            for (CommitteePerson person : committeeDecision.getAbstainers()) {
//                for (CommitteeMembership membership : committeeMemberships) {
//                    if (membership.getCommitteeMembershipId().equals(person.getMembershipId())) {
//                        //check to see if it is already been persisted
//                        Map fieldValues = getFieldValuesMap(protocol.getProtocolId(), scheduleIdFk, membership.getPersonId(), membership.getRolodexId(), submissionIdFk);
//                        if (businessObjectService.findMatching(ProtocolVoteAbstainee.class, fieldValues).size() == 0) {
//                            //we found a match, and has not been saved, lets make a ProtocolVoteAbstainee and save it
//                            saveProtocolMeetingVoter(new ProtocolVoteAbstainee(), protocol, scheduleIdFk, membership.getPersonId(), membership.getRolodexId(), submissionIdFk);
//                        }
//                        break;
//                    }
//                }
//            }
//        }       
//        if (!committeeDecision.getAbstainersToDelete().isEmpty()) {
//            for (CommitteePerson person : committeeDecision.getAbstainersToDelete()) {
//                for (CommitteeMembership membership : committeeMemberships) {
//                    if (membership.getCommitteeMembershipId().equals(person.getMembershipId())) {
//                        Map fieldValues = getFieldValuesMap(protocol.getProtocolId(), scheduleIdFk, membership.getPersonId(), membership.getRolodexId(), submissionIdFk);
//                        businessObjectService.deleteMatching(ProtocolVoteAbstainee.class, fieldValues);
//                    }
//                }
//            }
//            committeeDecision.getAbstainersToDelete().clear();
//        }
//        
//    }
    
    
    protected Class<? extends ProtocolVoteAbstaineeBase> getProtocolVoteAbstaineeBOClassHook() {
        return ProtocolVoteAbstainee.class;
    }

    protected ProtocolVoteAbstaineeBase getNewProtocolVoteAbstaineeInstanceHook() {
        return new ProtocolVoteAbstainee();
    }
    
    
    
// TODO ********************** commented out during IRB backfit ************************    
//    protected void proccessRecusers(CommitteeDecision committeeDecision, List<CommitteeMembership> committeeMemberships, 
//            Protocol protocol, Long scheduleIdFk, Long submissionIdFk) {     
//        
//        if (!committeeDecision.getRecused().isEmpty()) {
//            //there are recusers, lets save them
//            for (CommitteePerson person : committeeDecision.getRecused()) {
//                for (CommitteeMembership membership : committeeMemberships) {
//                    if (membership.getCommitteeMembershipId().equals(person.getMembershipId())) {
//                        //check to see if it is already been persisted
//                        Map fieldValues = getFieldValuesMap(protocol.getProtocolId(), scheduleIdFk, membership.getPersonId(), membership.getRolodexId(), submissionIdFk);
//                        if (businessObjectService.findMatching(ProtocolVoteRecused.class, fieldValues).size() == 0) {
//                            //we found a match, and has not been saved, lets make a ProtocolVoteAbstainee and save it
//                            saveProtocolMeetingVoter(new ProtocolVoteRecused(), protocol, scheduleIdFk, membership.getPersonId(), membership.getRolodexId(), submissionIdFk);
//                        }
//                        break;
//                    }
//                }
//            }
//        }
//        
//        if (!committeeDecision.getRecusedToDelete().isEmpty()) {
//            for (CommitteePerson person : committeeDecision.getRecusedToDelete()) {
//                for (CommitteeMembership membership : committeeMemberships) {
//                    if (membership.getCommitteeMembershipId().equals(person.getMembershipId())) {
//                        Map fieldValues = getFieldValuesMap(protocol.getProtocolId(), scheduleIdFk, membership.getPersonId(), membership.getRolodexId(),submissionIdFk);
//                        businessObjectService.deleteMatching(ProtocolVoteRecused.class, fieldValues);
//                    }
//                }
//            }
//            committeeDecision.getRecusedToDelete().clear();
//        }
//    }
    
    
    
    protected ProtocolVoteRecusedBase getNewProtocolVoteRecusedInstanceHook() {
        return new ProtocolVoteRecused();
    }
  
    protected Class<? extends ProtocolVoteRecusedBase> getProtocolVoteRecusedBOClassHook() {
        return ProtocolVoteRecused.class;
    }
    
    
    
// TODO ********************** commented out during IRB backfit ************************    
//    protected void saveProtocolMeetingVoter(ProtocolMeetingVoter voter, Protocol protocol, Long scheduleIdFk, String personId, Integer rolodexId, Long submissionIdFk) {
//        voter.setProtocol(protocol);
//        voter.setProtocolIdFk(protocol.getProtocolId());
//        voter.setSubmissionIdFk(submissionIdFk);
//        voter.setRolodexId( rolodexId );
//        voter.setPersonId(personId);
//        voter.setNonEmployeeFlag(personId==null);
//        businessObjectService.save(voter);
//    }
    
    
    protected Map<String, Object> getFieldValuesMap(Long protocolId, Long scheduleIdFk, String personId, Integer rolodexId, Long submissionIdFk) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("PROTOCOL_ID_FK", protocolId.toString());
        //fieldValues.put("SCHEDULE_ID_FK", scheduleIdFk.toString());
        fieldValues.put("PERSON_ID", personId);
        fieldValues.put("ROLODEX_ID", rolodexId);
        fieldValues.put("SUBMISSION_ID_FK", submissionIdFk.toString());
        return fieldValues;
    }

    protected ProtocolSubmission getSubmission(ProtocolBase protocol) {
        // There are 'findCommission' in other classes.  Consider to create a utility static method for this
        // need to loop thru to find the last submission.
        // it may have submit/Wd/notify irb/submit, and this will cause problem if don't loop thru.
        ProtocolSubmission protocolSubmission = null;
        for (ProtocolSubmissionBase submission : protocol.getProtocolSubmissions()) {
            if (StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.IN_AGENDA)
                    || StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE)) {
                protocolSubmission = (ProtocolSubmission) submission;
            }
        }
        return protocolSubmission;
    }
    
    
// TODO ********************** commented out during IRB backfit ************************    
//    protected void addReviewComments(ProtocolSubmission submission, ReviewCommentsBean reviewCommentsBean) {
//        int nextEntryNumber = 0;
//        for (CommitteeScheduleMinute minute : reviewCommentsBean.getReviewComments()) {
//            minute.setEntryNumber(nextEntryNumber);
//            // comments are retrieved based on schedule, so should not change other protocol's review comments
//            if (StringUtils.isBlank(minute.getMinuteEntryTypeCode())) {
//                minute.setMinuteEntryTypeCode(MinuteEntryType.PROTOCOL);
//            }
//            if (StringUtils.isNotBlank(minute.getMinuteEntryTypeCode())
//                    && MinuteEntryType.PROTOCOL.equals(minute.getMinuteEntryTypeCode())) {
//                // only "protocol" typpe should be populated with submissionid & protocolid
//                if (minute.getSubmissionIdFk() == null) {
//                    minute.setSubmissionIdFk(submission.getSubmissionId());
//                }
//                if (minute.getProtocolIdFk() == null) {
//                    minute.setProtocolIdFk(submission.getProtocolId());
//                }
//            }
//            if (minute.getScheduleIdFk() == null) {
//                minute.setScheduleIdFk(submission.getScheduleIdFk());
//            }
//            nextEntryNumber++;
//        }
//    }
//    
//    /**
//     * {@inheritDoc}
//     * @see org.kuali.kra.irb.actions.decision.CommitteeDecisionService#getAbstainers(java.lang.String, int)
//     */
//    public List<ProtocolVoteAbstainee> getAbstainers(String protocolNumber, int submissionNumber) {
//        List<ProtocolVoteAbstainee> protocolVoteAbstainers = new ArrayList<ProtocolVoteAbstainee>();
//
//        for (ProtocolSubmissionBase protocolSubmission : protocolFinderDao.findProtocolSubmissions(protocolNumber, submissionNumber)) {
//            protocolVoteAbstainers.addAll((List)protocolSubmission.getAbstainers());
//        }
//        
//        return protocolVoteAbstainers;
//    }
//    
//    /**
//     * {@inheritDoc}
//     * @see org.kuali.kra.irb.actions.decision.CommitteeDecisionService#getRecusers(java.lang.String, int)
//     */
//    public List<ProtocolVoteRecused> getRecusers(String protocolNumber, int submissionNumber) {
//        List<ProtocolVoteRecused> protocolVoteRecusers = new ArrayList<ProtocolVoteRecused>();
//        
//        for (ProtocolSubmission protocolSubmission : protocolFinderDao.findProtocolSubmissions(protocolNumber, submissionNumber)) {
//            protocolSubmission.refreshReferenceObject("recusers");
//            protocolVoteRecusers.addAll(protocolSubmission.getRecusers());
//        }
//        
//        return protocolVoteRecusers;
//    }
//
//    public void setProtocolFinderDao(ProtocolFinderDao protocolFinderDao) {
//        this.protocolFinderDao = protocolFinderDao;
//    }
    
    
}
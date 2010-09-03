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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolVersionService;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.assignagenda.ProtocolAssignToAgendaService;
import org.kuali.kra.irb.actions.correspondence.ProtocolActionCorrespondenceGenerationService;
import org.kuali.kra.irb.actions.genericactions.ProtocolGenericCorrespondence;
import org.kuali.kra.irb.actions.reviewcomments.ReviewerComments;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitActionService;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.kra.meeting.MinuteEntryType;
import org.kuali.kra.meeting.ProtocolMeetingVoter;
import org.kuali.kra.meeting.ProtocolVoteAbstainee;
import org.kuali.kra.meeting.ProtocolVoteRecused;
import org.kuali.kra.printing.PrintingException;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;

/**
 * The CommitteeDecisionService implementation.
 */
public class CommitteeDecisionServiceImpl implements CommitteeDecisionService {

    private BusinessObjectService businessObjectService;
    private ProtocolActionService protocolActionService;
    private ProtocolSubmitActionService protocolSubmitActionService;
    private CommitteeService committeeService;
    private ProtocolVersionService protocolVersionService;
    private DocumentService documentService;
    private ProtocolAssignToAgendaService protocolAssignToAgendaService;
    private ProtocolActionCorrespondenceGenerationService protocolActionCorrespondenceGenerationService;
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    public void setProtocolActionService(ProtocolActionService protocolActionService) {
        this.protocolActionService = protocolActionService;
    }
    
    public void setProtocolSubmitActionService(ProtocolSubmitActionService protocolSubmitActionService) {
        this.protocolSubmitActionService = protocolSubmitActionService;
    }
    
    public void setCommitteeService(CommitteeService committeeService) {
        this.committeeService = committeeService;
    }
    
    public void setProtocolVersionService(ProtocolVersionService protocolVersionService) {
        this.protocolVersionService = protocolVersionService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    public void setProtocolAssignToAgendaService(ProtocolAssignToAgendaService protocolAssignToAgendaService) {
        this.protocolAssignToAgendaService = protocolAssignToAgendaService;
    }

    public void setProtocolActionCorrespondenceGenerationService(
            ProtocolActionCorrespondenceGenerationService protocolActionCorrespondenceGenerationService) {
        this.protocolActionCorrespondenceGenerationService = protocolActionCorrespondenceGenerationService;
    }

    /**
     * @see org.kuali.kra.irb.actions.decision.CommitteeDecisionService#setCommitteeDecision(org.kuali.kra.irb.Protocol, org.kuali.kra.irb.actions.decision.CommitteeDecision)
     */
    public ProtocolDocument processCommitteeDecision(Protocol protocol, CommitteeDecision committeeDecision) throws Exception {
        ProtocolSubmission submission = getSubmission(protocol);
        ProtocolDocument documentVersionForRevisions = null;
        
        if (submission != null) {
            submission.setYesVoteCount(committeeDecision.getYesCount());
            submission.setNoVoteCount(committeeDecision.getNoCount());
            submission.setAbstainerCount(committeeDecision.getAbstainCount());
            submission.setRecusedCount(committeeDecision.getRecusedCount());
            submission.setVotingComments(committeeDecision.getVotingComments());
            
            addReviewerComments(submission, committeeDecision.getReviewComments());
            
            String protocolActionTypeToUse = "";
            boolean doAddProtocolAction = false;
            boolean revisionsRequested = false;
            String motionToCompareFrom = committeeDecision.getMotion() == null ? committeeDecision.getMotion() : committeeDecision.getMotion().trim();
            
            if (MotionValuesFinder.APPROVE.equals(motionToCompareFrom)) {
                protocolActionTypeToUse = ProtocolActionType.RECORD_COMMITTEE_DECISION;
                doAddProtocolAction = true;
            } else if (MotionValuesFinder.DISAPPROVE.equals(motionToCompareFrom)) {
                protocolActionTypeToUse = ProtocolActionType.DISAPPROVED;
                disapproveDocument(protocol);
                doAddProtocolAction = true;
            } else if (MotionValuesFinder.SMR.equals(motionToCompareFrom)) {
                protocolActionTypeToUse = ProtocolActionType.SPECIFIC_MINOR_REVISIONS_REQUIRED;
                revisionsRequested = true;
                doAddProtocolAction = true; 
            } else if (MotionValuesFinder.SRR.equals(motionToCompareFrom)) {
                protocolActionTypeToUse = ProtocolActionType.SUBSTANTIVE_REVISIONS_REQUIRED;
                revisionsRequested = true;
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

            //businessObjectService.save(submission);
            //businessObjectService.save(protocol);
            
            documentService.saveDocument(protocol.getProtocolDocument());
            protocol.refresh();
            
            if(revisionsRequested) { 
                generateCorrespondenceDocumentAndAttach(protocol, protocolActionTypeToUse);
                documentVersionForRevisions = versionDocument(protocol);
                documentService.cancelDocument(protocol.getProtocolDocument(), "Canceling the Original Protocol Workflow Document since a new Document is created for revisions.");
            }
        }
        return (documentVersionForRevisions != null) ? documentVersionForRevisions : protocol.getProtocolDocument();
    }
    
    private void generateCorrespondenceDocumentAndAttach(Protocol protocol, String protocolActionType) throws PrintingException {
        ProtocolGenericCorrespondence correspondence = new ProtocolGenericCorrespondence(protocolActionType);
        correspondence.setPrintableBusinessObject(protocol);
        correspondence.setProtocol(protocol);
        protocolActionCorrespondenceGenerationService.generateCorrespondenceDocumentAndAttach(correspondence);
    } 
    
    private void disapproveDocument(Protocol protocol) throws Exception {
        KualiWorkflowDocument currentWorkflowDocument = null;
        if(protocol.getProtocolDocument() != null) {
            currentWorkflowDocument = protocol.getProtocolDocument().getDocumentHeader().getWorkflowDocument();
            if(currentWorkflowDocument != null) {
                currentWorkflowDocument.disapprove("Disapproving Protocol Document after Committee Decision");
            }
        }    
    }
    
    private void returnDocumentForRevisions(ProtocolDocument protocolDocument) throws Exception {
        KualiWorkflowDocument currentWorkflowDocument = null;
        if(protocolDocument != null) {
            currentWorkflowDocument = protocolDocument.getDocumentHeader().getWorkflowDocument();
            if(currentWorkflowDocument != null) {
                currentWorkflowDocument.returnToPreviousRouteLevel("Revisions required", currentWorkflowDocument.getDocRouteLevel() - 1);
            }
        }
    }
    
    private ProtocolDocument versionDocument(Protocol protocol) throws Exception { 
        ProtocolDocument newDocument = protocolVersionService.versionProtocolDocument(protocol.getProtocolDocument());
        newDocument.getProtocol().setApprovalDate(null);
        newDocument.getProtocol().setLastApprovalDate(null);
        newDocument.getProtocol().setExpirationDate(null);
       
        newDocument.getProtocol().refreshReferenceObject("protocolStatus");
        documentService.saveDocument(newDocument);
        newDocument.getProtocol().setProtocolSubmission(null);
        
        return newDocument;
    }
    
    private void proccessAbstainers(CommitteeDecision committeeDecision, List<CommitteeMembership> committeeMemberships, 
            Protocol protocol, Long scheduleIdFk, Long submissionIdFk) {       
        if (!committeeDecision.getAbstainers().isEmpty()) {
            //there are abstainers, lets save them
            for (CommitteePerson person : committeeDecision.getAbstainers()) {
                for (CommitteeMembership membership : committeeMemberships) {
                    if (membership.getCommitteeMembershipId().equals(person.getMembershipId())) {
                        //check to see if it is already been persisted
                        Map fieldValues = getFieldValuesMap(protocol.getProtocolId(), scheduleIdFk, membership.getPersonId(), membership.getRolodexId(), submissionIdFk);
                        if (businessObjectService.findMatching(ProtocolVoteAbstainee.class, fieldValues).size() == 0) {
                            //we found a match, and has not been saved, lets make a ProtocolVoteAbstainee and save it
                            saveProtocolMeetingVoter(new ProtocolVoteAbstainee(), protocol, scheduleIdFk, membership.getPersonId(), membership.getRolodexId(), submissionIdFk);
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
                        Map fieldValues = getFieldValuesMap(protocol.getProtocolId(), scheduleIdFk, membership.getPersonId(), membership.getRolodexId(), submissionIdFk);
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
                        Map fieldValues = getFieldValuesMap(protocol.getProtocolId(), scheduleIdFk, membership.getPersonId(), membership.getRolodexId(), submissionIdFk);
                        if (businessObjectService.findMatching(ProtocolVoteRecused.class, fieldValues).size() == 0) {
                            //we found a match, and has not been saved, lets make a ProtocolVoteAbstainee and save it
                            saveProtocolMeetingVoter(new ProtocolVoteRecused(), protocol, scheduleIdFk, membership.getPersonId(), membership.getRolodexId(), submissionIdFk);
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
                        Map fieldValues = getFieldValuesMap(protocol.getProtocolId(), scheduleIdFk, membership.getPersonId(), membership.getRolodexId(),submissionIdFk);
                        businessObjectService.deleteMatching(ProtocolVoteRecused.class, fieldValues);
                    }
                }
            }
            committeeDecision.getRecusedToDelete().clear();
        }
    }
    
    private void saveProtocolMeetingVoter(ProtocolMeetingVoter voter, Protocol protocol, Long scheduleIdFk, String personId, Integer rolodexId, Long submissionIdFk) {
        voter.setProtocol(protocol);
        voter.setProtocolIdFk(protocol.getProtocolId());
        voter.setSubmissionIdFk(submissionIdFk);
        voter.setRolodexId( rolodexId );
        voter.setPersonId(personId);
        voter.setNonEmployeeFlag(personId==null);
        businessObjectService.save(voter);
    }
    
    private Map<String, Object> getFieldValuesMap(Long protocolId, Long scheduleIdFk, String personId, Integer rolodexId, Long submissionIdFk) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("PROTOCOL_ID_FK", protocolId.toString());
        //fieldValues.put("SCHEDULE_ID_FK", scheduleIdFk.toString());
        fieldValues.put("PERSON_ID", personId);
        fieldValues.put("ROLODEX_ID", rolodexId);
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
            // comments are retrieved based on schedule, so should not change other protocol's review comments
            if (StringUtils.isBlank(minute.getMinuteEntryTypeCode())) {
                minute.setMinuteEntryTypeCode(MinuteEntryType.PROTOCOL);
            }
            if (StringUtils.isNotBlank(minute.getMinuteEntryTypeCode())
                    && MinuteEntryType.PROTOCOL.equals(minute.getMinuteEntryTypeCode())) {
                // only "protocol" typpe should be populated with submissionid & protocolid
                if (minute.getSubmissionIdFk() == null) {
                    minute.setSubmissionIdFk(submission.getSubmissionId());
                }
                if (minute.getProtocolIdFk() == null) {
                    minute.setProtocolIdFk(submission.getProtocolId());
                }
            }
            if (minute.getScheduleIdFk() == null) {
                minute.setScheduleIdFk(submission.getScheduleIdFk());
            }
            nextEntryNumber++;
        }
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.actions.decision.CommitteeDecisionService#getAbstainers(java.lang.String, int)
     */
    public List<ProtocolVoteAbstainee> getAbstainers(String protocolNumber, int submissionNumber) {
        List<ProtocolVoteAbstainee> protocolVoteAbstainers = new ArrayList<ProtocolVoteAbstainee>();
        
        for (ProtocolSubmission protocolSubmission : protocolSubmitActionService.getProtocolSubmissions(protocolNumber, submissionNumber)) {
            protocolVoteAbstainers.addAll(protocolSubmission.getAbstainers());
        }
        
        return protocolVoteAbstainers;
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.actions.decision.CommitteeDecisionService#getRecusers(java.lang.String, int)
     */
    public List<ProtocolVoteRecused> getRecusers(String protocolNumber, int submissionNumber) {
        List<ProtocolVoteRecused> protocolVoteRecusers = new ArrayList<ProtocolVoteRecused>();
        
        for (ProtocolSubmission protocolSubmission : protocolSubmitActionService.getProtocolSubmissions(protocolNumber, submissionNumber)) {
            protocolVoteRecusers.addAll(protocolSubmission.getRecusers());
        }
        
        return protocolVoteRecusers;
    }
    
}
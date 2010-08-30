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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ActionHelper;
import org.kuali.kra.irb.actions.reviewcomments.ReviewerCommentsBean;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.meeting.ProtocolVoteAbstainee;
import org.kuali.kra.meeting.ProtocolVoteRecused;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * This class is a bean for managing the input for a committee decision.
 */
@SuppressWarnings("serial")
public class CommitteeDecision extends ReviewerCommentsBean implements Serializable {

    private String motion;
    private Integer noCount;
    private Integer yesCount;
    private Integer abstainCount;
    private Integer recusedCount;
    private String votingComments;
    
    private CommitteePerson newAbstainer = new CommitteePerson();
    private CommitteePerson newRecused = new CommitteePerson();
    
    private List<CommitteePerson> abstainers = new ArrayList<CommitteePerson>();
    private List<CommitteePerson> recused = new ArrayList<CommitteePerson>();
    private List<CommitteePerson> abstainersToDelete = new ArrayList<CommitteePerson>();
    private List<CommitteePerson> recusedToDelete = new ArrayList<CommitteePerson>();
    
    /**
     * Constructs a CommitteeDecision.
     * @param actionHelper Reference back to the parent ActionHelper
     */
    public CommitteeDecision(ActionHelper actionHelper) {
        super(actionHelper);
    }
    
    /**
     * This method initializes the class.
     */
    public void init() {
        Protocol protocol = getActionHelper().getProtocol();
        ProtocolSubmission submission = getSubmission(protocol);
        if (submission != null) {
            this.noCount = submission.getNoVoteCount();
            this.yesCount = submission.getYesVoteCount();
            this.abstainCount = submission.getAbstainerCount();
            this.recusedCount = submission.getRecusedCount();
            this.votingComments = submission.getVotingComments();
            
            //not sure if I really need to deal with protocol actions    
            //ES: Please remove condition before checking in.
            if (submission.getScheduleIdFk() != null) {
                initializeAbstainees(protocol, submission);
                initializeRecused(protocol, submission);
            }
        }
        initComments();
    }
    
    public Integer getRecusedCount() {
        //return recusedCount;
        return this.getRecused().size();
    }

    public void setRecusedCount(Integer recusedCount) {
        this.recusedCount = recusedCount;
    }

    private Map<String, Long> getLookUpFields(Long protocolId, Long submissionIdFk) {
        Map<String, Long> lookUpFields = new HashMap<String, Long>();
        lookUpFields.put("PROTOCOL_ID_FK", protocolId);
        lookUpFields.put("SUBMISSION_ID_FK", submissionIdFk);
        return lookUpFields;
    }
    
    private List<CommitteeMembership> getCommitteeMemberships(Protocol protocol) {
        List<CommitteeMembership> committeeMemberships =  
            KraServiceLocator.getService(CommitteeService.class).getAvailableMembers(protocol.getProtocolSubmission().getCommittee().getCommitteeId(), 
                    protocol.getProtocolSubmission().getScheduleId());
        return committeeMemberships;
    }
    
    private void initializeAbstainees(Protocol protocol, ProtocolSubmission submission) {
        Map<String, Long> absenteeLookFields = getLookUpFields(protocol.getProtocolId(), submission.getSubmissionId());
        
        Collection<ProtocolVoteAbstainee> protocolVoteAbstainees = KraServiceLocator.getService(BusinessObjectService.class).findMatching(ProtocolVoteAbstainee.class, absenteeLookFields);
        
        List<CommitteeMembership> committeeMemberships = getCommitteeMemberships(protocol);
        
        for (ProtocolVoteAbstainee abstainee : protocolVoteAbstainees) {
            for (CommitteeMembership membership : committeeMemberships) {
                if (abstainee.getPersonId().equals(membership.getPersonId())) {
                    //this committee person is an abstainee
                    CommitteePerson person = new CommitteePerson();
                    person.setMembershipId(membership.getCommitteeMembershipId());
                    this.abstainers.add(person);
                    break;
                }
            }
        }
    }
    
    private void initializeRecused(Protocol protocol, ProtocolSubmission submission) {
        Map<String, Long> absenteeLookFields = getLookUpFields(protocol.getProtocolId(), submission.getSubmissionId());
        
        Collection<ProtocolVoteRecused> protocolVoteRecused = KraServiceLocator.getService(BusinessObjectService.class).findMatching(ProtocolVoteRecused.class, absenteeLookFields);
        
        List<CommitteeMembership> committeeMemberships = getCommitteeMemberships(protocol);
        
        for (ProtocolVoteRecused recusee : protocolVoteRecused) {
            for (CommitteeMembership membership : committeeMemberships) {
                if (recusee.getPersonId().equals(membership.getPersonId())) {
                    //this committee person is an recusee
                    CommitteePerson person = new CommitteePerson();
                    person.setMembershipId(membership.getCommitteeMembershipId());
                    this.recused.add(person);
                    break;
                }
            }
        }
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

    public String getMotion() {
        return motion;
    }

    public void setMotion(String motion) {
        this.motion = motion;
    }

    public Integer getNoCount() {
        return noCount;
    }

    public void setNoCount(Integer noCount) {
        this.noCount = noCount;
    }

    public Integer getYesCount() {
        return yesCount;
    }

    public void setYesCount(Integer yesCount) {
        this.yesCount = yesCount;
    }

    public Integer getAbstainCount() {
        //return abstainCount;
        return this.getAbstainers().size();
    }

    public void setAbstainCount(Integer abstainCount) {
        this.abstainCount = abstainCount;
    }

    public String getVotingComments() {
        return votingComments;
    }

    public void setVotingComments(String votingComments) {
        this.votingComments = votingComments;
    }

    public List<CommitteePerson> getAbstainers() {
        return abstainers;
    }

    public void setAbstainers(List<CommitteePerson> abstainers) {
        this.abstainers = abstainers;
    }
    
    public List<CommitteePerson> getAbstainersToDelete() {
        return abstainersToDelete;
    }

    public List<CommitteePerson> getRecused() {
        return recused;
    }

    public void setRecused(List<CommitteePerson> recused) {
        this.recused = recused;
    }
    
    public List<CommitteePerson> getRecusedToDelete() {
        return recusedToDelete;
    }

    public CommitteePerson getNewAbstainer() {
        return newAbstainer;
    }

    public void setNewAbstainer(CommitteePerson newAbstainer) {
        this.newAbstainer = newAbstainer;
    }

    public CommitteePerson getNewRecused() {
        return newRecused;
    }

    public void setNewRecused(CommitteePerson newRecused) {
        this.newRecused = newRecused;
    }
    
    public int getTotalVoteCount() {
        return (this.getYesCount() != null ? this.getYesCount() : 0) + 
                (this.getNoCount() != null ? this.getNoCount() : 0) + 
                (this.getAbstainCount() != null ? this.getAbstainCount() : 0) + 
                (this.getRecusedCount() != null ? this.getRecusedCount() : 0);
    }
}
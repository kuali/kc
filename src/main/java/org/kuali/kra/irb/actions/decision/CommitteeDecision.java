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

import org.kuali.kra.committee.bo.CommitteeDecisionMotionType;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.actions.ActionHelper;
import org.kuali.kra.irb.actions.ProtocolActionBean;
import org.kuali.kra.irb.actions.ProtocolOnlineReviewCommentable;
import org.kuali.kra.irb.actions.reviewcomments.ReviewAttachmentsBean;
import org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsBean;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.meeting.ProtocolVoteAbstainee;
import org.kuali.kra.meeting.ProtocolVoteRecused;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * This class is a bean for managing the input for a committee decision.
 */
public class CommitteeDecision extends ProtocolActionBean implements ProtocolOnlineReviewCommentable, Serializable {

    private static final long serialVersionUID = -8052093280852074307L;
    
    private String motionTypeCode;
    private Integer noCount;
    private Integer yesCount;
    private Integer abstainCount;
    private Integer recusedCount;
    private String votingComments;
    
    private CommitteeDecisionMotionType motionType;
    
    private CommitteePerson newAbstainer = new CommitteePerson();
    private CommitteePerson newRecused = new CommitteePerson();
    
    private List<CommitteePerson> abstainers = new ArrayList<CommitteePerson>();
    private List<CommitteePerson> recused = new ArrayList<CommitteePerson>();
    private List<CommitteePerson> abstainersToDelete = new ArrayList<CommitteePerson>();
    private List<CommitteePerson> recusedToDelete = new ArrayList<CommitteePerson>();
    
    private ReviewCommentsBean reviewCommentsBean;
    
    /**
     * Constructs a CommitteeDecision.
     * @param actionHelper Reference back to the action helper for this bean
     */
    public CommitteeDecision(ActionHelper actionHelper) {
        super(actionHelper);
        
        reviewCommentsBean = new ReviewCommentsBean(Constants.PROTOCOL_COMMITTEE_DECISION_ACTION_PROPERTY_KEY);
    }
    
    /**
     * This method initializes the class.
     */
    public void init() {
        // getSubmission(protocol) is not necessary the most recent one.
        // this may cause problem later if the most recent submission does not have schedule, then
        // npe when try to getavailable member
        // TODO : check with Jay
        //ProtocolSubmission submission = getSubmission(protocol);
        ProtocolSubmission submission = getProtocol().getProtocolSubmission();
        if (submission != null) {
            this.motionTypeCode = submission.getCommitteeDecisionMotionTypeCode();
            this.noCount = submission.getNoVoteCount();
            this.yesCount = submission.getYesVoteCount();
            this.abstainCount = submission.getAbstainerCount();
            this.recusedCount = submission.getRecusedCount();
            this.votingComments = submission.getVotingComments();
            this.setMotionType(submission.getCommitteeDecisionMotionType());
            
            //not sure if I really need to deal with protocol actions    
            //ES: Please remove condition before checking in.
            if (submission.getScheduleIdFk() != null) {
                initializeAbstainees(submission);
                initializeRecused(submission);
            }
        }
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
    
    private List<CommitteeMembership> getCommitteeMemberships() {
        String committeeId = getProtocol().getProtocolSubmission().getCommittee().getCommitteeId();
        String scheduleId = getProtocol().getProtocolSubmission().getScheduleId();
        List<CommitteeMembership> committeeMemberships = KraServiceLocator.getService(CommitteeService.class).getAvailableMembers(committeeId, scheduleId);
        return committeeMemberships;
    }
    
    private void initializeAbstainees(ProtocolSubmission submission) {
        Map<String, Long> absenteeLookFields = getLookUpFields(getProtocol().getProtocolId(), submission.getSubmissionId());
        
        Collection<ProtocolVoteAbstainee> protocolVoteAbstainees = KraServiceLocator.getService(BusinessObjectService.class).findMatching(ProtocolVoteAbstainee.class, absenteeLookFields);
        
        List<CommitteeMembership> committeeMemberships = getCommitteeMemberships();
        
        for (ProtocolVoteAbstainee abstainee : protocolVoteAbstainees) {
            for (CommitteeMembership membership : committeeMemberships) {
                if (abstainee.isProtocolReviewerFromCommitteeMembership(membership)) {
                    //this committee person is an abstainee
                    CommitteePerson person = new CommitteePerson();
                    person.setMembershipId(membership.getCommitteeMembershipId());
                    this.abstainers.add(person);
                    break;
                }
            }
        }
    }
    
    private void initializeRecused(ProtocolSubmission submission) {
        Map<String, Long> absenteeLookFields = getLookUpFields(getProtocol().getProtocolId(), submission.getSubmissionId());
        
        Collection<ProtocolVoteRecused> protocolVoteRecused = KraServiceLocator.getService(BusinessObjectService.class).findMatching(ProtocolVoteRecused.class, absenteeLookFields);
        
        List<CommitteeMembership> committeeMemberships = getCommitteeMemberships();
        
        for (ProtocolVoteRecused recusee : protocolVoteRecused) {
            for (CommitteeMembership membership : committeeMemberships) {
                if (recusee.isProtocolReviewerFromCommitteeMembership(membership)) {
                    //this committee person is an recusee
                    CommitteePerson person = new CommitteePerson();
                    person.setMembershipId(membership.getCommitteeMembershipId());
                    this.recused.add(person);
                    break;
                }
            }
        }
    }

    public String getMotionTypeCode() {
        return motionTypeCode;
    }

    public void setMotionTypeCode(String commDecisionMotionTypeCode) {
        this.motionTypeCode = commDecisionMotionTypeCode;
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
    
    public CommitteeDecisionMotionType getMotionType() {
        return motionType;
    }

    public void setMotionType(CommitteeDecisionMotionType motionType) {
        this.motionType = motionType;
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

    public ReviewCommentsBean getReviewCommentsBean() {
        return reviewCommentsBean;
    }

    public void setReviewCommentsBean(ReviewCommentsBean reviewCommentsBean) {
        this.reviewCommentsBean = reviewCommentsBean;
    }

    public ReviewAttachmentsBean getReviewAttachmentsBean() {
        // TODO Auto-generated method stub
        return null;
    }

}
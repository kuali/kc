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
package org.kuali.kra.irb.actions.decision;

import org.kuali.coeus.common.committee.impl.bo.CommitteeDecisionMotionType;
import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.actions.ActionHelper;
import org.kuali.kra.irb.actions.ProtocolActionBean;
import org.kuali.kra.irb.actions.reviewcomments.ReviewAttachmentsBean;
import org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsBean;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.meeting.ProtocolVoteAbstainee;
import org.kuali.kra.meeting.ProtocolVoteRecused;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsBeanBase;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.*;

/**
 * This class is a bean for managing the input for a committee decision.
 */
public class CommitteeDecision extends ProtocolActionBean implements org.kuali.kra.protocol.actions.decision.CommitteeDecision<CommitteePerson> {

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
        ProtocolSubmission submission = (ProtocolSubmission) getProtocol().getProtocolSubmission();
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
    
    private List<CommitteeMembershipBase> getCommitteeMemberships() {
        String committeeId = getProtocol().getProtocolSubmission().getCommittee().getCommitteeId();
        String scheduleId = getProtocol().getProtocolSubmission().getScheduleId();
        List<CommitteeMembershipBase> committeeMemberships = KcServiceLocator.getService(CommitteeService.class).getAvailableMembers(committeeId, scheduleId);
        return committeeMemberships;
    }
    
    private void initializeAbstainees(ProtocolSubmission submission) {
        Map<String, Long> absenteeLookFields = getLookUpFields(getProtocol().getProtocolId(), submission.getSubmissionId());
        
        Collection<ProtocolVoteAbstainee> protocolVoteAbstainees = KcServiceLocator.getService(BusinessObjectService.class).findMatching(ProtocolVoteAbstainee.class, absenteeLookFields);
        
        List<CommitteeMembershipBase> committeeMemberships = getCommitteeMemberships();
        
        for (ProtocolVoteAbstainee abstainee : protocolVoteAbstainees) {
            for (CommitteeMembershipBase membership : committeeMemberships) {
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
        
        Collection<ProtocolVoteRecused> protocolVoteRecused = KcServiceLocator.getService(BusinessObjectService.class).findMatching(ProtocolVoteRecused.class, absenteeLookFields);
        
        List<CommitteeMembershipBase> committeeMemberships = getCommitteeMemberships();
        
        for (ProtocolVoteRecused recusee : protocolVoteRecused) {
            for (CommitteeMembershipBase membership : committeeMemberships) {
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

    public void setReviewCommentsBean(ReviewCommentsBeanBase reviewCommentsBean) {
        this.reviewCommentsBean = (ReviewCommentsBean) reviewCommentsBean;
    }

    public ReviewAttachmentsBean getReviewAttachmentsBean() {

        return null;
    }
    
    public int getYesCountValue() {
        return (this.getYesCount() != null) ? this.getYesCount() : 0;
    }
    
    public int getNoCountValue() {
        return (this.getNoCount() != null) ? this.getNoCount() : 0;
    }

}

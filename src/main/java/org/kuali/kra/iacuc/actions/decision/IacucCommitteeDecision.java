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
package org.kuali.kra.iacuc.actions.decision;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.common.committee.bo.CommitteeDecisionMotionType;
import org.kuali.kra.common.committee.bo.CommitteeMembership;
import org.kuali.kra.common.committee.meeting.ProtocolVoteAbstainee;
import org.kuali.kra.common.committee.meeting.ProtocolVoteRecused;
import org.kuali.kra.common.committee.service.CommonCommitteeService;
import org.kuali.kra.iacuc.actions.IacucActionHelper;
import org.kuali.kra.iacuc.actions.IacucProtocolActionBean;
import org.kuali.kra.iacuc.actions.reviewcomments.IacucReviewCommentsBean;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.protocol.actions.decision.CommitteeDecision;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewAttachmentsBean;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsBean;
import org.kuali.rice.krad.service.BusinessObjectService;

public class IacucCommitteeDecision extends IacucProtocolActionBean implements CommitteeDecision<IacucCommitteePerson> {
    
    private static final long serialVersionUID = -8052093280852074307L;
    
    private String motionTypeCode;
    private Integer noCount;
    private Integer yesCount;
    private Integer abstainCount;
    private Integer recusedCount;
    private String votingComments;
    
    private CommitteeDecisionMotionType motionType;
    
    private IacucCommitteePerson newAbstainer = new IacucCommitteePerson();
    private IacucCommitteePerson newRecused = new IacucCommitteePerson();
    
    private List<IacucCommitteePerson> abstainers = new ArrayList<IacucCommitteePerson>();
    private List<IacucCommitteePerson> recused = new ArrayList<IacucCommitteePerson>();
    private List<IacucCommitteePerson> abstainersToDelete = new ArrayList<IacucCommitteePerson>();
    private List<IacucCommitteePerson> recusedToDelete = new ArrayList<IacucCommitteePerson>();
    
    private IacucReviewCommentsBean reviewCommentsBean;
    
    /**
     * Constructs a CommitteeDecision.
     * @param actionHelper Reference back to the action helper for this bean
     */
    public IacucCommitteeDecision(IacucActionHelper actionHelper) {
        super(actionHelper);
        reviewCommentsBean = new IacucReviewCommentsBean(Constants.PROTOCOL_COMMITTEE_DECISION_ACTION_PROPERTY_KEY);
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
        IacucProtocolSubmission submission = (IacucProtocolSubmission) getProtocol().getProtocolSubmission();
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
        lookUpFields.put("protocolIdFk", protocolId);
        lookUpFields.put("submissionIdFk", submissionIdFk);
        return lookUpFields;
    }
    
    private List<CommitteeMembership> getCommitteeMemberships() {
        String committeeId = getProtocol().getProtocolSubmission().getCommittee().getCommitteeId();
        String scheduleId = getProtocol().getProtocolSubmission().getScheduleId();
        List<CommitteeMembership> committeeMemberships = KraServiceLocator.getService(CommonCommitteeService.class).getAvailableMembers(committeeId, scheduleId);
        return committeeMemberships;
    }
    
    private void initializeAbstainees(IacucProtocolSubmission submission) {
        Map<String, Long> absenteeLookFields = getLookUpFields(getProtocol().getProtocolId(), submission.getSubmissionId());
        
        Collection<ProtocolVoteAbstainee> protocolVoteAbstainees = KraServiceLocator.getService(BusinessObjectService.class).findMatching(ProtocolVoteAbstainee.class, absenteeLookFields);
        
        List<CommitteeMembership> committeeMemberships = getCommitteeMemberships();
        
        for (ProtocolVoteAbstainee abstainee : protocolVoteAbstainees) {
            for (CommitteeMembership membership : committeeMemberships) {
                if (abstainee.isProtocolReviewerFromCommitteeMembership(membership)) {
                    //this committee person is an abstainee
                    IacucCommitteePerson person = new IacucCommitteePerson();
                    person.setMembershipId(membership.getCommitteeMembershipId());
                    this.abstainers.add(person);
                    break;
                }
            }
        }
    }
    
    private void initializeRecused(IacucProtocolSubmission submission) {
        Map<String, Long> absenteeLookFields = getLookUpFields(getProtocol().getProtocolId(), submission.getSubmissionId());
        
        Collection<ProtocolVoteRecused> protocolVoteRecused = KraServiceLocator.getService(BusinessObjectService.class).findMatching(ProtocolVoteRecused.class, absenteeLookFields);
        
        List<CommitteeMembership> committeeMemberships = getCommitteeMemberships();
        
        for (ProtocolVoteRecused recusee : protocolVoteRecused) {
            for (CommitteeMembership membership : committeeMemberships) {
                if (recusee.isProtocolReviewerFromCommitteeMembership(membership)) {
                    //this committee person is an recusee
                    IacucCommitteePerson person = new IacucCommitteePerson();
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

    public List<IacucCommitteePerson> getAbstainers() {
        return abstainers;
    }

    public void setAbstainers(List<IacucCommitteePerson> abstainers) {
        this.abstainers = abstainers;
    }
    
    public List<IacucCommitteePerson> getAbstainersToDelete() {
        return abstainersToDelete;
    }

    public List<IacucCommitteePerson> getRecused() {
        return recused;
    }

    public void setRecused(List<IacucCommitteePerson> recused) {
        this.recused = recused;
    }
    
    public List<IacucCommitteePerson> getRecusedToDelete() {
        return recusedToDelete;
    }

    public IacucCommitteePerson getNewAbstainer() {
        return newAbstainer;
    }

    public void setNewAbstainer(IacucCommitteePerson newAbstainer) {
        this.newAbstainer = newAbstainer;
    }

    public IacucCommitteePerson getNewRecused() {
        return newRecused;
    }

    public void setNewRecused(IacucCommitteePerson newRecused) {
        this.newRecused = newRecused;
    }
    
    public int getTotalVoteCount() {
        return (this.getYesCount() != null ? this.getYesCount() : 0) + 
                (this.getNoCount() != null ? this.getNoCount() : 0) + 
                (this.getAbstainCount() != null ? this.getAbstainCount() : 0) + 
                (this.getRecusedCount() != null ? this.getRecusedCount() : 0);
    }

    public IacucReviewCommentsBean getReviewCommentsBean() {
        return reviewCommentsBean;
    }

    public void setReviewCommentsBean(ReviewCommentsBean reviewCommentsBean) {
        this.reviewCommentsBean = (IacucReviewCommentsBean) reviewCommentsBean;
    }

    public ReviewAttachmentsBean getReviewAttachmentsBean() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getYesCountValue() {
        return (this.getYesCount() != null) ? this.getYesCount() : 0;
    }
    
    @Override
    public int getNoCountValue() {
        return (this.getNoCount() != null) ? this.getNoCount() : 0;
    }
}

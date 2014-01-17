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
package org.kuali.kra.common.committee.service.impl;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.ResearchAreaBase;
import org.kuali.kra.common.committee.bo.*;
import org.kuali.kra.common.committee.meeting.CommitteeScheduleAttendanceBase;
import org.kuali.kra.common.committee.service.CommitteeMembershipServiceBase;
import org.kuali.kra.common.committee.service.CommitteeServiceBase;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewer;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.*;

/**
 * This class...
 */
public abstract class CommitteeMembershipServiceImplBase<CMT extends CommitteeBase<CMT, ?, ?>, 
                                                     CSRV extends CommitteeServiceBase<CMT, ?>> 
        
                                                     implements CommitteeMembershipServiceBase<CMT> {

    @SuppressWarnings("unused")
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(CommitteeScheduleServiceImplBase.class);

    private static final String REFERENCE_ROLODEX = "rolodex";
    private static final String REFERENCE_MEMBERSHIP_TYPE = "membershipType";
    private static final String REFERENCE_MEMBERSHIP_ROLE = "membershipRole";
    private static final String REFERENCE_RESEARCH_AREA = "researchArea";
    private BusinessObjectService businessObjectService;
    private CSRV committeeService;

    /**
     * @see org.kuali.kra.common.committee.service.CommitteeMembershipServiceBase#addCommitteeMembership(org.kuali.kra.common.committee.bo.CommitteeBase, org.kuali.kra.common.committee.bo.CommitteeMembershipBase)
     */
    public void addCommitteeMembership(CMT committee, CommitteeMembershipBase committeeMembership) {
        
        committeeMembership.setCommitteeIdFk(committee.getId());
        committeeMembership.setMembershipId("0");

        if(StringUtils.isBlank(committeeMembership.getPersonId())) {
            committeeMembership.refreshReferenceObject(REFERENCE_ROLODEX);
        }
        committeeMembership.refreshReferenceObject(REFERENCE_MEMBERSHIP_TYPE);

        
        committee.getCommitteeMemberships().add(committeeMembership);
    }

    /**
     * @see org.kuali.kra.common.committee.service.CommitteeMembershipServiceBase#deleteCommitteeMembership(org.kuali.kra.common.committee.bo.CommitteeBase)
     */
    public void deleteCommitteeMembership(CMT committee) {
        List<CommitteeMembershipBase> deletedCommitteememberships = new ArrayList<CommitteeMembershipBase>();
        for(CommitteeMembershipBase committeeMembership : committee.getCommitteeMemberships()) {
            if(committeeMembership.isDelete()) {
                deletedCommitteememberships.add(committeeMembership);
            }
        }
        committee.getCommitteeMemberships().removeAll(deletedCommitteememberships);
    }

    /**
     * @see org.kuali.kra.common.committee.service.CommitteeMembershipServiceBase#addCommitteeMembershipRole(org.kuali.kra.common.committee.bo.CommitteeMembershipBase, org.kuali.kra.common.committee.bo.CommitteeMembershipRole)
     */
    public void addCommitteeMembershipRole(CMT committee, int selectedMembershipIndex, CommitteeMembershipRole committeeMembershipRole) {
        CommitteeMembershipBase committeeMembership = committee.getCommitteeMemberships().get(selectedMembershipIndex);
        
        committeeMembershipRole.setCommitteeMembershipIdFk(committeeMembership.getCommitteeMembershipId());

        committeeMembershipRole.refreshReferenceObject(REFERENCE_MEMBERSHIP_ROLE);
        
        committeeMembership.getMembershipRoles().add(committeeMembershipRole);
    }
   
    /**
     * @see org.kuali.kra.common.committee.service.CommitteeMembershipServiceBase#deleteCommitteeMembershipRole(org.kuali.kra.common.committee.bo.CommitteeBase, int, int)
     */
    public void deleteCommitteeMembershipRole(CMT committee, int selectedMembershipIndex, int lineNumber) {
        CommitteeMembershipBase committeeMembership = committee.getCommitteeMemberships().get(selectedMembershipIndex);
        CommitteeMembershipRole membershipRole = committeeMembership.getMembershipRoles().get(lineNumber);
        committeeMembership.getMembershipRoles().remove(membershipRole);
    }
    
    /**
     * @see org.kuali.kra.common.committee.service.CommitteeMembershipServiceBase#addCommitteeMembershipExpertise(org.kuali.kra.common.committee.bo.CommitteeMembershipBase, java.util.Collection)
     */
    public void addCommitteeMembershipExpertise(CommitteeMembershipBase committeeMembership, Collection<ResearchAreaBase> researchAreas) {
        for (ResearchAreaBase researchArea: researchAreas) {
            // check if the research area is not already included in expertise for the committee member
            if(!isDuplicateResearchArea(committeeMembership, researchArea)) {
                CommitteeMembershipExpertiseBase membershipExpertise = getNewCommitteeMembershipExpertiseInstanceHook();
                membershipExpertise.setResearchAreaCode(researchArea.getResearchAreaCode());
                membershipExpertise.setCommitteeMembershipIdFk(committeeMembership.getCommitteeMembershipId());
                membershipExpertise.refreshReferenceObject(REFERENCE_RESEARCH_AREA);
                
                committeeMembership.getMembershipExpertise().add(membershipExpertise);
            }
        }
    }
    
    protected abstract CommitteeMembershipExpertiseBase getNewCommitteeMembershipExpertiseInstanceHook();
    
    /**
     * This method is a private helper method to prevent duplicate research areas in committee member's expertise
     * @param committeeMembership
     * @param researchArea
     * @return
     */
    private boolean isDuplicateResearchArea(CommitteeMembershipBase committeeMembership, ResearchAreaBase researchArea){
        for(CommitteeMembershipExpertiseBase cme: (committeeMembership.getMembershipExpertise())){
            if(researchArea.equals(cme.getResearchArea())){
                return true;
            }
        }
        return false;
    }
   
    /**
     * @see org.kuali.kra.common.committee.service.CommitteeMembershipServiceBase#deleteCommitteeMembershipExpertise(org.kuali.kra.common.committee.bo.CommitteeBase, int, int)
     */
    public void deleteCommitteeMembershipExpertise(CMT committee, int selectedMembershipIndex, int lineNumber) {
        CommitteeMembershipBase committeeMembership = committee.getCommitteeMemberships().get(selectedMembershipIndex);
        CommitteeMembershipExpertiseBase committeeMembershipExpertise = committeeMembership.getMembershipExpertise().get(lineNumber);
        committeeMembership.getMembershipExpertise().remove(committeeMembershipExpertise);
    }

    /**
     * 
     * @see org.kuali.kra.common.committee.service.CommitteeMembershipServiceBase#isMemberAssignedToReviewer(org.kuali.kra.common.committee.bo.CommitteeMembershipBase, java.lang.String)
     */
    public boolean isMemberAssignedToReviewer(CommitteeMembershipBase member, String committeeId) {
        boolean isReviewer = false;
        for (ProtocolSubmissionBase submission : getProtocolSubmissionsForCommittee(committeeId)) {
            for (ProtocolReviewer reviewer : submission.getProtocolReviewers()) {
                if ((member.getPersonId()!=null && StringUtils.equals(reviewer.getPersonId(), member.getPersonId()))
                        || (member.getRolodexId() != null && member.getRolodexId().equals(reviewer.getRolodexId()))) {
                    isReviewer = true;
                }
            }

        }
        return isReviewer;
    }
    
    /**
     * 
     * @see org.kuali.kra.common.committee.service.CommitteeMembershipServiceBase#isMemberAttendedMeeting(org.kuali.kra.common.committee.bo.CommitteeMembershipBase, java.lang.String)
     */
    public boolean isMemberAttendedMeeting(CommitteeMembershipBase member, String committeeId) {
        boolean isAttendance = false;
        CMT committee = committeeService.getCommitteeById(committeeId);
        if (committee != null) {
            for (CommitteeScheduleBase<?, CMT, ?, ?> committeeSchedule : committee.getCommitteeSchedules()) {
                for (CommitteeScheduleAttendanceBase attendance : committeeSchedule.getCommitteeScheduleAttendances()) {
                    if (StringUtils.equals(attendance.getPersonId(), member.getPersonId()) 
                            || (member.getRolodexId() != null && StringUtils.equals(attendance.getPersonId(), member.getRolodexId().toString()))) {
                        isAttendance = true;
                    }
                }
            }
        }
        return isAttendance;
    }

    /*
     * get protocolsubmissions that are assigned to this committee.
     */
    @SuppressWarnings("unchecked")
    protected List<ProtocolSubmissionBase> getProtocolSubmissionsForCommittee(String committeeId) {
        Map<String, String> fieldMap = new HashMap<String, String>();
        fieldMap.put("committeeId",committeeId);
        return (List<ProtocolSubmissionBase>) businessObjectService.findMatching(getProtocolSubmissionBOClassHook(), fieldMap);
    }
    
    
    protected abstract Class<? extends ProtocolSubmissionBase> getProtocolSubmissionBOClassHook();

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public void setCommitteeService(CSRV committeeService) {
        this.committeeService = committeeService;
    }

}

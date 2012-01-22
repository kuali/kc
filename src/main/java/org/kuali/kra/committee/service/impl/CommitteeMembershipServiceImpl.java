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
package org.kuali.kra.committee.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.ResearchArea;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.bo.CommitteeMembershipExpertise;
import org.kuali.kra.committee.bo.CommitteeMembershipRole;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.service.CommitteeMembershipService;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.irb.actions.submit.ProtocolReviewer;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.meeting.CommitteeScheduleAttendance;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * This class...
 */
public class CommitteeMembershipServiceImpl implements CommitteeMembershipService {

    @SuppressWarnings("unused")
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(CommitteeScheduleServiceImpl.class);

    private static final String REFERENCE_ROLODEX = "rolodex";
    private static final String REFERENCE_MEMBERSHIP_TYPE = "membershipType";
    private static final String REFERENCE_MEMBERSHIP_ROLE = "membershipRole";
    private static final String REFERENCE_RESEARCH_AREA = "researchArea";
    private BusinessObjectService businessObjectService;
    private CommitteeService committeeService;

    /**
     * @see org.kuali.kra.committee.service.CommitteeMembershipService#addCommitteeMembership(org.kuali.kra.committee.bo.Committee, org.kuali.kra.committee.bo.CommitteeMembership)
     */
    public void addCommitteeMembership(Committee committee, CommitteeMembership committeeMembership) {
        
        committeeMembership.setCommitteeIdFk(committee.getId());
        committeeMembership.setMembershipId("0");

        if(StringUtils.isBlank(committeeMembership.getPersonId())) {
            committeeMembership.refreshReferenceObject(REFERENCE_ROLODEX);
        }
        committeeMembership.refreshReferenceObject(REFERENCE_MEMBERSHIP_TYPE);

        
        committee.getCommitteeMemberships().add(committeeMembership);
    }

    /**
     * @see org.kuali.kra.committee.service.CommitteeMembershipService#deleteCommitteeMembership(org.kuali.kra.committee.bo.Committee)
     */
    public void deleteCommitteeMembership(Committee committee) {
        List<CommitteeMembership> deletedCommitteememberships = new ArrayList<CommitteeMembership>();
        for(CommitteeMembership committeeMembership : committee.getCommitteeMemberships()) {
            if(committeeMembership.isDelete()) {
                deletedCommitteememberships.add(committeeMembership);
            }
        }
        committee.getCommitteeMemberships().removeAll(deletedCommitteememberships);
    }

    /**
     * @see org.kuali.kra.committee.service.CommitteeMembershipService#addCommitteeMembershipRole(org.kuali.kra.committee.bo.CommitteeMembership, org.kuali.kra.committee.bo.CommitteeMembershipRole)
     */
    public void addCommitteeMembershipRole(Committee committee, int selectedMembershipIndex, CommitteeMembershipRole committeeMembershipRole) {
        CommitteeMembership committeeMembership = committee.getCommitteeMemberships().get(selectedMembershipIndex);
        
        committeeMembershipRole.setCommitteeMembershipIdFk(committeeMembership.getCommitteeMembershipId());

        committeeMembershipRole.refreshReferenceObject(REFERENCE_MEMBERSHIP_ROLE);
        
        committeeMembership.getMembershipRoles().add(committeeMembershipRole);
    }
   
    /**
     * @see org.kuali.kra.committee.service.CommitteeMembershipService#deleteCommitteeMembershipRole(org.kuali.kra.committee.bo.Committee, int, int)
     */
    public void deleteCommitteeMembershipRole(Committee committee, int selectedMembershipIndex, int lineNumber) {
        CommitteeMembership committeeMembership = committee.getCommitteeMemberships().get(selectedMembershipIndex);
        CommitteeMembershipRole membershipRole = committeeMembership.getMembershipRoles().get(lineNumber);
        committeeMembership.getMembershipRoles().remove(membershipRole);
    }
    
    /**
     * @see org.kuali.kra.committee.service.CommitteeMembershipService#addCommitteeMembershipExpertise(org.kuali.kra.committee.bo.CommitteeMembership, java.util.Collection)
     */
    public void addCommitteeMembershipExpertise(CommitteeMembership committeeMembership, Collection<ResearchArea> researchAreas) {
        for (ResearchArea researchArea: researchAreas) {
            // check if the research area is not already included in expertise for the committee member
            if(!isDuplicateResearchArea(committeeMembership, researchArea)) {
                CommitteeMembershipExpertise membershipExpertise = new CommitteeMembershipExpertise();
                membershipExpertise.setResearchAreaCode(researchArea.getResearchAreaCode());
                membershipExpertise.setCommitteeMembershipIdFk(committeeMembership.getCommitteeMembershipId());
                membershipExpertise.refreshReferenceObject(REFERENCE_RESEARCH_AREA);
                
                committeeMembership.getMembershipExpertise().add(membershipExpertise);
            }
        }
    }
    
    /**
     * This method is a private helper method to prevent duplicate research areas in committee member's expertise
     * @param committeeMembership
     * @param researchArea
     * @return
     */
    private boolean isDuplicateResearchArea(CommitteeMembership committeeMembership, ResearchArea researchArea){
        for(CommitteeMembershipExpertise cme: (committeeMembership.getMembershipExpertise())){
            if(researchArea.equals(cme.getResearchArea())){
                return true;
            }
        }
        return false;
    }
   
    /**
     * @see org.kuali.kra.committee.service.CommitteeMembershipService#deleteCommitteeMembershipExpertise(org.kuali.kra.committee.bo.Committee, int, int)
     */
    public void deleteCommitteeMembershipExpertise(Committee committee, int selectedMembershipIndex, int lineNumber) {
        CommitteeMembership committeeMembership = committee.getCommitteeMemberships().get(selectedMembershipIndex);
        CommitteeMembershipExpertise committeeMembershipExpertise = committeeMembership.getMembershipExpertise().get(lineNumber);
        committeeMembership.getMembershipExpertise().remove(committeeMembershipExpertise);
    }

    /**
     * 
     * @see org.kuali.kra.committee.service.CommitteeMembershipService#isMemberAssignedToReviewer(org.kuali.kra.committee.bo.CommitteeMembership, java.lang.String)
     */
    public boolean isMemberAssignedToReviewer(CommitteeMembership member, String committeeId) {
        boolean isReviewer = false;
        for (ProtocolSubmission submission : getProtocolSubmissionsForCommittee(committeeId)) {
            for (ProtocolReviewer reviewer : submission.getProtocolReviewers()) {
                if ((member.getPersonId()!=null && StringUtils.equals(reviewer.getPersonId(), member.getPersonId()))
                        || (member.getRolodexId() != null && reviewer.getRolodexId().equals(member.getRolodexId()))) {
                    isReviewer = true;
                }
            }

        }

        return isReviewer;
    }
    
    /**
     * 
     * @see org.kuali.kra.committee.service.CommitteeMembershipService#isMemberAttendedMeeting(org.kuali.kra.committee.bo.CommitteeMembership, java.lang.String)
     */
    public boolean isMemberAttendedMeeting(CommitteeMembership member, String committeeId) {
        boolean isAttendance = false;
        Committee committee = committeeService.getCommitteeById(committeeId);
        if (committee != null) {
            for (CommitteeSchedule committeeSchedule : committee.getCommitteeSchedules()) {
                for (CommitteeScheduleAttendance attendance : committeeSchedule.getCommitteeScheduleAttendances()) {
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
    protected List<ProtocolSubmission> getProtocolSubmissionsForCommittee(String committeeId) {
        Map fieldMap = new HashMap();
        fieldMap.put("committeeId",committeeId);
        return (List<ProtocolSubmission>) businessObjectService.findMatching(ProtocolSubmission.class, fieldMap);
    }
    
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public void setCommitteeService(CommitteeService committeeService) {
        this.committeeService = committeeService;
    }

}

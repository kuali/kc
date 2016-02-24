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
package org.kuali.coeus.common.committee.impl.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.bo.*;
import org.kuali.coeus.common.committee.impl.meeting.CommitteeScheduleAttendanceBase;
import org.kuali.coeus.common.committee.impl.service.CommitteeMembershipServiceBase;
import org.kuali.coeus.common.committee.impl.service.CommitteeServiceBase;
import org.kuali.kra.bo.ResearchAreaBase;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewer;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.*;

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

    @Override
    public void addCommitteeMembership(CMT committee, CommitteeMembershipBase committeeMembership) {
        
        committeeMembership.setCommitteeIdFk(committee.getId());
        committeeMembership.setMembershipId("0");

        if(StringUtils.isBlank(committeeMembership.getPersonId())) {
            committeeMembership.refreshReferenceObject(REFERENCE_ROLODEX);
        }
        committeeMembership.refreshReferenceObject(REFERENCE_MEMBERSHIP_TYPE);

        
        committee.getCommitteeMemberships().add(committeeMembership);
    }

    @Override
    public void deleteCommitteeMembership(CMT committee) {
        List<CommitteeMembershipBase> deletedCommitteememberships = new ArrayList<CommitteeMembershipBase>();
        for(CommitteeMembershipBase committeeMembership : committee.getCommitteeMemberships()) {
            if(committeeMembership.isDelete()) {
                deletedCommitteememberships.add(committeeMembership);
            }
        }
        committee.getCommitteeMemberships().removeAll(deletedCommitteememberships);
    }

    @Override
    public void addCommitteeMembershipRole(CMT committee, int selectedMembershipIndex, CommitteeMembershipRole committeeMembershipRole) {
        CommitteeMembershipBase committeeMembership = committee.getCommitteeMemberships().get(selectedMembershipIndex);
        
        committeeMembershipRole.setCommitteeMembershipIdFk(committeeMembership.getCommitteeMembershipId());

        committeeMembershipRole.refreshReferenceObject(REFERENCE_MEMBERSHIP_ROLE);
        
        committeeMembership.getMembershipRoles().add(committeeMembershipRole);
    }
   
    @Override
    public void deleteCommitteeMembershipRole(CMT committee, int selectedMembershipIndex, int lineNumber) {
        CommitteeMembershipBase committeeMembership = committee.getCommitteeMemberships().get(selectedMembershipIndex);
        CommitteeMembershipRole membershipRole = committeeMembership.getMembershipRoles().get(lineNumber);
        committeeMembership.getMembershipRoles().remove(membershipRole);
    }
    
    @Override
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
   
    @Override
    public void deleteCommitteeMembershipExpertise(CMT committee, int selectedMembershipIndex, int lineNumber) {
        CommitteeMembershipBase committeeMembership = committee.getCommitteeMemberships().get(selectedMembershipIndex);
        CommitteeMembershipExpertiseBase committeeMembershipExpertise = committeeMembership.getMembershipExpertise().get(lineNumber);
        committeeMembership.getMembershipExpertise().remove(committeeMembershipExpertise);
    }

    @Override
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
    
    @Override
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

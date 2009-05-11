/*
 * Copyright 2006-2008 The Kuali Foundation
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
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.ResearchArea;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.bo.CommitteeMembershipExpertise;
import org.kuali.kra.committee.bo.CommitteeMembershipRole;
import org.kuali.kra.committee.service.CommitteeMembershipService;
import org.kuali.rice.kns.service.BusinessObjectService;

public class CommitteeMembershipServiceImpl implements CommitteeMembershipService {

    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(CommitteeScheduleServiceImpl.class);

    private static final String REFERENCE_PERSON = "person";
    private static final String REFERENCE_ROLODEX = "rolodex";
    private static final String REFERENCE_MEMBERSHIP_TYPE = "membershipType";
    private static final String REFERENCE_MEMBERSHIP_ROLE = "membershipRole";
    private static final String REFERENCE_RESEARCH_AREA = "researchArea";

    private BusinessObjectService businessObjectService;
    
    /**
     * Set the Business Object Service. Injected by the Spring Framework.
     * 
     * @param businessObjectService the business object service
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    /**
     * @see org.kuali.kra.committee.service.CommitteeMembershipService#addCommitteeMembership(org.kuali.kra.committee.bo.Committee, org.kuali.kra.committee.bo.CommitteeMembership)
     */
    public void addCommitteeMembership(Committee committee, CommitteeMembership committeeMembership) {
        
        committeeMembership.setCommitteeIdFk(committee.getId());
        committeeMembership.setCommitteeId(committee.getCommitteeId());
        committeeMembership.setMembershipId("0");
        committeeMembership.setSequenceNumber(0);

        //Refresh Person or Rolodex
        if(!StringUtils.isBlank(committeeMembership.getPersonId())) {
            committeeMembership.refreshReferenceObject(REFERENCE_PERSON);
        }else {
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
        committeeMembershipRole.setMembershipId(committeeMembership.getMembershipId());
        committeeMembershipRole.setSequenceNumber(0);

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
            CommitteeMembershipExpertise membershipExpertise = new CommitteeMembershipExpertise();
            membershipExpertise.setResearchAreaCode(researchArea.getResearchAreaCode());
            membershipExpertise.setCommitteeMembershipIdFk(committeeMembership.getCommitteeMembershipId());
            membershipExpertise.setMembershipId(committeeMembership.getMembershipId());
            membershipExpertise.setSequenceNumber(0);

            membershipExpertise.refreshReferenceObject(REFERENCE_RESEARCH_AREA);
            
            if (!committeeMembership.getMembershipExpertise().contains(membershipExpertise)) {
                committeeMembership.getMembershipExpertise().add(membershipExpertise);
            }
        }
    }
   
    /**
     * @see org.kuali.kra.committee.service.CommitteeMembershipService#deleteCommitteeMembershipExpertise(org.kuali.kra.committee.bo.Committee, int, int)
     */
    public void deleteCommitteeMembershipExpertise(Committee committee, int selectedMembershipIndex, int lineNumber) {
        CommitteeMembership committeeMembership = committee.getCommitteeMemberships().get(selectedMembershipIndex);
        CommitteeMembershipExpertise committeeMembershipExpertise = committeeMembership.getMembershipExpertise().get(lineNumber);
        committeeMembership.getMembershipExpertise().remove(committeeMembershipExpertise);
    }
}

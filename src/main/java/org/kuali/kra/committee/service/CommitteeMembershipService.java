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
package org.kuali.kra.committee.service;

import java.util.Collection;

import org.kuali.kra.bo.ResearchArea;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.bo.CommitteeMembershipRole;

public interface CommitteeMembershipService {
    /**
     * This method adds a CommitteeMembership to the List of CommitteeMemberships.
     * @param committee which contains list of CommitteeeMembership.
     * @param committeeMembership object is added to CommitteeMemberhip list.
     */
    void addCommitteeMembership(Committee committee, CommitteeMembership committeeMembership);
    
    /**
     * This method deletes CommitteeMembership(s) - those marked as delete
     * @param committee which contains list of CommitteeMemberhips
     */
    void deleteCommitteeMembership(Committee committee);
    
    /**
     * This method adds a CommitteeMembershipRole to the list of CommitteeMembershipRoles of a 
     * committee member.
     * @param committee - the committee that contains the CommitteeMembership to which the role is to be added.
     * @param selectedmembershipIndex - the index position of the CommitteeMembership to which the role is to be added. 
     * @param committeeMembershipRole - the role that is to be added
     */
    void addCommitteeMembershipRole(Committee committee, int selectedMembershipIndex, CommitteeMembershipRole committeeMembershipRole);

    /**
     * This method deletes a CommitteeMembershipRole from the list of CommitteeMembershipRoles
     * @param committee - the committee that contains the CommitteeMembership from which the role is to be deleted.
     * @param selectedMembershipIndex - the index position of the CommitteeMembership from which the role is to be deleted.
     * @param lineNumber - the position of the ComitteeMembershipRole to be deleted
     */
    void deleteCommitteeMembershipRole(Committee committee, int selectedMembershipIndex, int lineNumber);

    /**
     * This method adds CommitteeMembershipExpertise to a committeeMembership
     * @param committeeMembership - the CommitteeMembership to which the expertise is to be added.
     * @param committeeMembershipExpertise - collection of expertise that is to be added
     */
    void addCommitteeMembershipExpertise(CommitteeMembership committeeMembership, Collection<ResearchArea> researchAreas);

    /**
     * This method deletes a CommitteeMembershipExpertise from the list of CommitteeMembershipExpertise
     * @param committee - the committee that contains the CommitteeMembership from which the role is to be deleted.
     * @param selectedMembershipIndex - the index position of the CommitteeMembership from which the role is to be deleted.
     * @param lineNumber - the position of the CommitteeMembershipExpertise to be deleted
     */
    void deleteCommitteeMembershipExpertise(Committee committee, int selectedMembershipIndex, int lineNumber);
}

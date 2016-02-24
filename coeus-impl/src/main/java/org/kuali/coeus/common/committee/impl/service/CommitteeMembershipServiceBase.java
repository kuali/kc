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
package org.kuali.coeus.common.committee.impl.service;

import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipRole;
import org.kuali.kra.bo.ResearchAreaBase;

import java.util.Collection;

public interface CommitteeMembershipServiceBase<CMT extends CommitteeBase<CMT, ?, ?>> {
    /**
     * This method adds a CommitteeMembershipBase to the List of CommitteeMemberships.
     * @param committee which contains list of CommitteeeMembership.
     * @param committeeMembership object is added to CommitteeMemberhip list.
     */
    void addCommitteeMembership(CMT committee, CommitteeMembershipBase committeeMembership);
    
    /**
     * This method deletes CommitteeMembershipBase(s) - those marked as delete
     * @param committee which contains list of CommitteeMemberhips
     */
    void deleteCommitteeMembership(CMT committee);
    
    /**
     * This method adds a CommitteeMembershipRole to the list of CommitteeMembershipRoles of a 
     * committee member.
     * @param committee - the committee that contains the CommitteeMembershipBase to which the role is to be added.
     * @param selectedmembershipIndex - the index position of the CommitteeMembershipBase to which the role is to be added. 
     * @param committeeMembershipRole - the role that is to be added
     */
    void addCommitteeMembershipRole(CMT committee, int selectedMembershipIndex, CommitteeMembershipRole committeeMembershipRole);

    /**
     * This method deletes a CommitteeMembershipRole from the list of CommitteeMembershipRoles
     * @param committee - the committee that contains the CommitteeMembershipBase from which the role is to be deleted.
     * @param selectedMembershipIndex - the index position of the CommitteeMembershipBase from which the role is to be deleted.
     * @param lineNumber - the position of the ComitteeMembershipRole to be deleted
     */
    void deleteCommitteeMembershipRole(CMT committee, int selectedMembershipIndex, int lineNumber);

    /**
     * This method adds CommitteeMembershipExpertise to a committeeMembership
     * @param committeeMembership - the CommitteeMembershipBase to which the expertise is to be added.
     * @param committeeMembershipExpertise - collection of expertise that is to be added
     */
    void addCommitteeMembershipExpertise(CommitteeMembershipBase committeeMembership, Collection<ResearchAreaBase> researchAreas);

    /**
     * This method deletes a CommitteeMembershipExpertise from the list of CommitteeMembershipExpertise
     * @param committee - the committee that contains the CommitteeMembershipBase from which the role is to be deleted.
     * @param selectedMembershipIndex - the index position of the CommitteeMembershipBase from which the role is to be deleted.
     * @param lineNumber - the position of the CommitteeMembershipExpertise to be deleted
     */
    void deleteCommitteeMembershipExpertise(CMT committee, int selectedMembershipIndex, int lineNumber);

    /**
     * 
     * This method is to check whether this committee member is assigned as reviewer of a protocol
     * which is submitted to this committee.
     * 
     * @param member
     * @param committeeId
     * @return
     */
    boolean isMemberAssignedToReviewer(CommitteeMembershipBase member, String committeeId);

    /**
     * 
     * This method is to check whether this committee member has attended a scheduled meeting
     * which is submitted to this committee.
     * 
     * @param member
     * @param committeeId
     * @return
     */
    boolean isMemberAttendedMeeting(CommitteeMembershipBase member, String committeeId);
}

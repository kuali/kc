/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.committee.service;

import org.kuali.coeus.common.committee.impl.service.CommitteeServiceBase;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;

/**
 * The Committee Service provides a set of methods for
 * working with committees.
 */
public interface CommitteeService extends CommitteeServiceBase<Committee, CommitteeSchedule> {

//    /**
//     * Retrieve a committee from the database based upon its Committee ID.
//     * @param committeeId the committee ID
//     * @return the committee or null if not found
//     */
//    public Committee getCommitteeById(String committeeId);
//   
//    /**
//     * Add the research areas to the committee.  Duplicates are not added.
//     * @param committee the committee to add to
//     * @param researchAreas the research areas to add
//     */
//    public void addResearchAreas(Committee committee, Collection<ResearchArea> researchAreas);
//
//    /**
//     * Get the valid upcoming committee dates for scheduling a protocol.
//     * This method is used by CommitteeScheduleValuesFinder.
//     * @param id the committee's unique id
//     * @return list of key/value pairs with the dates  
//     */
//    public List<KeyValue> getAvailableCommitteeDates(String committeeId);
//    
//    /**
//     * Get the active members scheduled for a future meeting (schedule) in a committee.
//     * @param committeeId the committee's id
//     * @param scheduleId the schedule's id
//     * @return the list of active members who will be at the meeting
//     */
//    public List<CommitteeMembership> getAvailableMembers(String committeeId, String scheduleId);
//    
//    /**
//     * Get the active members in a committee.
//     * @param committeeId the committee's id
//     * @return the list of active members on the committee now
//     */
//    public List<CommitteeMembership> getAvailableMembersNow(String committeeId);
//    
//    /**
//     * Get the committee schedule.
//     * @param committee the committee to search
//     * @param scheduleId the id of the schedule to find
//     * @return the schedule or null if not found
//     */
//    public CommitteeSchedule getCommitteeSchedule(Committee committee, String scheduleId);
//    
//    /**
//     * 
//     * This method to merge existing meeting data to the new approved committee.
//     * @param committeeId
//     * @return
//     */
//    List<CommitteeSchedule>  mergeCommitteeSchedule(String committeeId);
}

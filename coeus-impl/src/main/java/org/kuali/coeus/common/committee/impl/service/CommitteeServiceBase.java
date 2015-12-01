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
package org.kuali.coeus.common.committee.impl.service;

import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.kra.bo.ResearchAreaBase;
import org.kuali.rice.core.api.util.KeyValue;

import java.util.Collection;
import java.util.List;

/**
 * The CommitteeBase Service provides a set of methods for
 * working with committees.
 */
public interface CommitteeServiceBase<CMT extends CommitteeBase<CMT, ?, CS>, 
                                        CS extends CommitteeScheduleBase<CS, CMT, ?, ?>> {

    /**
     * Retrieve a committee from the database based upon its CommitteeBase ID.
     * @param committeeId the committee ID
     * @return the committee or null if not found
     */
    public CMT getCommitteeById(String committeeId);
   
    /**
     * Add the research areas to the committee.  Duplicates are not added.
     * @param committee the committee to add to
     * @param researchAreas the research areas to add
     */
    public void addResearchAreas(CMT committee, Collection<ResearchAreaBase> researchAreas);

    /**
     * Get the valid upcoming committee dates for scheduling a protocol.
     * This method is used by CommitteeScheduleValuesFinderBase.
     * @param id the committee's unique id
     * @return list of key/value pairs with the dates  
     */
    public List<KeyValue> getAvailableCommitteeDates(String committeeId);
    
    /**
     * Get the active members scheduled for a future meeting (schedule) in a committee.
     * @param committeeId the committee's id
     * @param scheduleId the schedule's id
     * @return the list of active members who will be at the meeting
     */
    public List<CommitteeMembershipBase> getAvailableMembers(String committeeId, String scheduleId);

    /**
     * Get the committee schedule.
     * @param committee the committee to search
     * @param scheduleId the id of the schedule to find
     * @return the schedule or null if not found
     */
    public CS getCommitteeSchedule(CMT committee, String scheduleId);
    
    /**
     * 
     * This method will merge existing meeting data into the new approved committee to create a new list of
     * master schedules. It will also delete from the db all the other schedules for that committee 
     * that are not in the master list.
     * @param committeeId
     * @return
     */
    List<CS> mergeCommitteeSchedule(CMT committee);
    
    
    
    /**
     * This method will ensure that the committee back-links from each of the protocol submissions associated with the committee schedules 
     * of the given committee are indeed pointing back to that committee. 
     *  
     * @param committeeId;
     */
    void updateCommitteeForProtocolSubmissions(CMT committee);
    
    
    /**
     * This method will return a light-weight version of the committee instance returned by 
     * getCommitteeById(committeeId) above. Each of the schedules of this light-weight committee version
     * will preserve the 'light' reference links like location, deadline etc, from the original commitee schedule,
     * but the 'heavy' links like submissions, review comments etc will be nullified.
     * 
     * Typically this method should be invoked when allowing edits to be made only to the light-weight portion 
     * of the committee and schedule data.
     * 
     * @param committeeId
     * @return
     * @throws Exception 
     */
    public CMT getLightVersion(String committeeId) throws Exception;
}

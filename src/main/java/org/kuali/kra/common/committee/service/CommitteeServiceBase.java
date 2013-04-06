/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.common.committee.service;

import java.util.Collection;
import java.util.List;

import org.kuali.kra.bo.ResearchAreaBase;
import org.kuali.kra.common.committee.bo.CommitteeBase;
import org.kuali.kra.common.committee.bo.CommitteeMembershipBase;
import org.kuali.kra.common.committee.bo.CommitteeScheduleBase;
import org.kuali.rice.core.api.util.KeyValue;

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
     * Get the active members in a committee.
     * @param committeeId the committee's id
     * @return the list of active members on the committee now
     */
    public List<CommitteeMembershipBase> getAvailableMembersNow(String committeeId);
    
    /**
     * Get the committee schedule.
     * @param committee the committee to search
     * @param scheduleId the id of the schedule to find
     * @return the schedule or null if not found
     */
    public CS getCommitteeSchedule(CMT committee, String scheduleId);
    
    /**
     * 
     * This method to merge existing meeting data to the new approved committee.
     * @param committeeId
     * @return
     */
    List<CS>  mergeCommitteeSchedule(String committeeId);
}

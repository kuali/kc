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
package org.kuali.kra.committee.service;

import java.text.ParseException;

import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.kra.committee.web.struts.form.schedule.*;

import java.util.List;


public interface CommitteeScheduleService {
    
    /**
     * This method implementer must check if passed CommitteeSchedule is deleteable.
     * <br/>
     * Rule:
     * <br/>
     * Any past schedule date must not be allowed to delete.
     * <br/>
     * Allow delete if NO Protocol is assigned to CommitteeSchedule.
     * @param committeeSchedule
     * @return
     */
    public Boolean isCommitteeScheduleDeletable(CommitteeSchedule committeeSchedule);
    
    /**
     * This method implementer must add new non conflicting, non-repeating schedule dates to existing Committee.CommitteeSchedule list.
     * @param scheduleData
     * @param committee
     * @throws ParseException
     */
    public void addSchedule(ScheduleData scheduleData, Committee committee) throws ParseException;
    
    /**
     * 
     * This method returns a list of minutes based on a protocol ID
     * @param protocolId
     * @return
     */
    public List<CommitteeScheduleMinute> getMinutesByProtocol(Long protocolId);
    
    /**
     * 
     * This method returns a list of minutes based on a protocol submission ID
     * @param submissionID
     * @return
     */
    public List<CommitteeScheduleMinute> getMinutesByProtocolSubmission(Long submissionID);
    
    /**
     * 
     * This method returns a list of minutes based on a schedule ID
     * @param scheduleId
     * @return
     */
    public List<CommitteeScheduleMinute> getMinutesBySchedule(Long scheduleId);
    
    /**
     * 
     * This method returns a specific schedule minute based on a committee id
     * NOTE: a null is returned if the ID doesn't exist in the DB.
     * @param committeeId
     * @return
     */
    public CommitteeScheduleMinute getCommitteeScheduleMinute(Long committeeScheduleId);
    
}

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
package org.kuali.kra.committee.rule.event;

import java.util.List;

import org.kuali.core.rule.event.KualiDocumentEvent;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.web.struts.form.schedule.ScheduleData;

public interface CommitteeScheduleEvent extends KualiDocumentEvent {
    
    /**
     * Enum helps identify type of error to respond.
     */
    public enum Event {HARDERROR, SOFTERROR};
    
    /**
     * This method should return instance of ScheduleDate.
     * @return
     */
    public ScheduleData getScheduleData();
    
    /**
     * This method should return instance of CommitteeSchedules.
     * @return
     */
    public List<CommitteeSchedule> getCommitteeSchedules();
    
    /**
     * This method should return CommitteeScheduleEvent.event.
     * @return
     */
    public Event getType();
    
}

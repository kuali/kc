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

import org.kuali.core.document.Document;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.web.struts.form.schedule.ScheduleData;
import org.kuali.kra.rule.event.KraDocumentEventBase;

public abstract class CommitteeScheduleEventBase extends KraDocumentEventBase implements CommitteeScheduleEvent {
    
    private ScheduleData scheduleData;
    
    private List<CommitteeSchedule> committeeSchedules;
    
    private Event type;
    
    protected CommitteeScheduleEventBase(String description, String errorPathPrefix, Document document, ScheduleData scheduleData, List<CommitteeSchedule> committeeSchedules, Event type) {
        super(description, errorPathPrefix, document);
        this.scheduleData = scheduleData;
        this.committeeSchedules = committeeSchedules;
        this.type = type;
    }
    
    /**
     * @see org.kuali.kra.rule.event.KraDocumentEventBase#logEvent()
     */
    @Override
    protected void logEvent() {
    }
    
    /**
     * @see org.kuali.kra.committee.rule.event.CommitteeScheduleEvent#getScheduleData()
     */
    public ScheduleData getScheduleData() {
        return this.scheduleData;
    }
    
    /**
     * @see org.kuali.kra.committee.rule.event.CommitteeScheduleEvent#getCommitteeSchedules()
     */
    public List<CommitteeSchedule> getCommitteeSchedules(){
        return this.committeeSchedules;
    }
    
    /**
     * @see org.kuali.kra.committee.rule.event.CommitteeScheduleEvent#getType()
     */
    public Event getType() {
        return this.type;
    }
}

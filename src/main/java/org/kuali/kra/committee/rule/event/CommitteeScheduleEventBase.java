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
    
    private event type;
    
    protected CommitteeScheduleEventBase(String description, String errorPathPrefix, Document document, ScheduleData scheduleData, List<CommitteeSchedule> committeeSchedules, event type) {
        super(description, errorPathPrefix, document);
        this.scheduleData = scheduleData;
        this.committeeSchedules = committeeSchedules;
        this.type = type;
    }
    
    @Override
    protected void logEvent() {
    }
    
    public ScheduleData getScheduleData() {
        return this.scheduleData;
    }
    
    public List<CommitteeSchedule> getCommitteeSchedules(){
        return this.committeeSchedules;
    }
    
    public event getType() {
        return this.type;
    }
}

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
package org.kuali.kra.personmasschange.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class SchedulePersonMassChange extends KraPersistableBusinessObjectBase {

    private static final long serialVersionUID = 3301259355335554038L;
    
    private long schedulePersonMassChangeId;
    private long personMassChangeId;
    private boolean attendees;
    
    private PersonMassChange personMassChange;

    public long getSchedulePersonMassChangeId() {
        return schedulePersonMassChangeId;
    }

    public void setSchedulePersonMassChangeId(long schedulePersonMassChangeId) {
        this.schedulePersonMassChangeId = schedulePersonMassChangeId;
    }

    public long getPersonMassChangeId() {
        return personMassChangeId;
    }

    public void setPersonMassChangeId(long personMassChangeId) {
        this.personMassChangeId = personMassChangeId;
    }

    public boolean isAttendees() {
        return attendees;
    }

    public void setAttendees(boolean attendees) {
        this.attendees = attendees;
    }
    
    public PersonMassChange getPersonMassChange() {
        return personMassChange;
    }

    public void setPersonMassChange(PersonMassChange personMassChange) {
        this.personMassChange = personMassChange;
    }

}
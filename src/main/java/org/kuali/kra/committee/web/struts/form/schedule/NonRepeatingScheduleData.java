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
package org.kuali.kra.committee.web.struts.form.schedule;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.kuali.kra.committee.web.struts.form.schedule.Time12HrFmt.MERIDIEM;
import org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsService;
import org.kuali.kra.scheduling.service.ScheduleService;
import org.kuali.kra.scheduling.util.Time24HrFmt;

/**
 * This class is form data helper class used to store UI based date for recurrence.
 */
public class NonRepeatingScheduleData extends ScheduleData {
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;


    public NonRepeatingScheduleData() {
        super();
        this.setRecurrenceType(StyleKey.NEVER.toString());
    }
    
    public Date getScheduleDate() {
        return getScheduleStartDate();
    }
    
    public void setScheduleDate(Date scheduleDate) {
        this.setScheduleStartDate(scheduleDate);       
    }

    @Override
    public List<java.util.Date> getScheduleDates(ScheduleService scheduleService, java.sql.Date dt, Time24HrFmt time) throws ParseException {
        List<java.util.Date> dates = scheduleService.getScheduledDates(dt, dt, time, null);
    
        List<java.sql.Date> skippedDates = new ArrayList<java.sql.Date>();
        setDatesInConflict(skippedDates);
        return dates;
    }

    public String toString() {
        return "Non-Repeating Schedule Date:" +
                " Scheduled date = " + getScheduleStartDate().toString() + 
                ", start time = " + getTime().getTime() + getTime().getMeridiem() +
                ", location = " + getPlace();
    }
}

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

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.kuali.kra.scheduling.expr.util.CronSpecialChars;
import org.kuali.kra.scheduling.sequence.DefaultScheduleSequence;
import org.kuali.kra.scheduling.sequence.ScheduleSequence;
import org.kuali.kra.scheduling.sequence.TrimDatesScheduleSequenceDecorator;
import org.kuali.kra.scheduling.sequence.WeekScheduleSequenceDecorator;
import org.kuali.kra.scheduling.service.ScheduleService;
import org.kuali.kra.scheduling.util.Time24HrFmt;

/**
 * This class is form data helper class used to store UI based date for recurrence.
 */
public class WeeklyScheduleData extends ScheduleData {
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;

    public WeeklyScheduleData() {
        super();
        this.setRecurrenceType(StyleKey.WEEKLY.toString());
        this.setWeeklySchedule(new WeeklyScheduleDetails());
    }
    
    public void setWeeklySchedule(WeeklyScheduleDetails weeklySchedule) {
        this.weeklySchedule = weeklySchedule;
    }

    public WeeklyScheduleDetails getWeeklySchedule() {
        return weeklySchedule;
    }

    public String toString() {
        return "Weekly Schedule Dates:" +
        " Scheduled start date = " + getScheduleStartDate().toString() + 
        ", scheduled end date = " + getWeeklySchedule().getScheduleEndDate().toString() + 
        ", week = " + getWeeklySchedule().getWeek() +
        ", days = " + getWeeklySchedule().getDaysOfWeek().toString() +
        ", start time = " + getTime().getTime() + getTime().getMeridiem() +
        ", location = " + getPlace();
    }

    @Override
    public List<java.util.Date> getScheduleDates(ScheduleService scheduleService, java.sql.Date dt, Time24HrFmt time) throws ParseException {
        Date dtEnd = null;
        CronSpecialChars[] weekdays = null;
    
        dtEnd = getWeeklySchedule().getScheduleEndDate();
        if(CollectionUtils.isNotEmpty(getWeeklySchedule().getDaysOfWeek())) {
            weekdays = ScheduleData.convertToWeekdays(getWeeklySchedule().getDaysOfWeek().toArray(new String[getWeeklySchedule().getDaysOfWeek().size()]));
        }
            
        ScheduleSequence scheduleSequence = new WeekScheduleSequenceDecorator(new TrimDatesScheduleSequenceDecorator(new DefaultScheduleSequence()),getWeeklySchedule().getWeek(),weekdays.length);
        return scheduleService.getScheduledDates(dt, dtEnd, time, weekdays, scheduleSequence);
    }
}

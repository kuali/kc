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
import java.util.List;

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
public class DailyScheduleData extends ScheduleData {
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;

    private DailyScheduleDetails dailySchedule;

    public DailyScheduleData() {
        super();
        this.setRecurrenceType(StyleKey.DAILY.toString());
        this.setDailySchedule(new DailyScheduleDetails());
    }
    
    public void setDailySchedule(DailyScheduleDetails dailySchedule) {
        this.dailySchedule = dailySchedule;
    }

    public DailyScheduleDetails getDailySchedule() {
        return dailySchedule;
    }


    public String toString() {
        return "Daily Schedule Dates:" +
        " Scheduled start date = " + getScheduleStartDate().toString() + 
        ", scheduled end date = " + getDailySchedule().getScheduleEndDate().toString() + 
        ", day = " + getDailySchedule().getDay() +
        ", start time = " + getTime().getTime() + getTime().getMeridiem() +
        ", location = " + getPlace() +
        ", radio button = " + getDailySchedule().getDayOption();
    }
    
    public List<java.util.Date> getScheduleDates(ScheduleService scheduleService, java.sql.Date dt, Time24HrFmt time) throws ParseException {
System.out.println("\ntime = " + time);        
        
        List<java.util.Date> dates = null;
        Date dtEnd = null;
        int day = 0;
        CronSpecialChars[] weekdays = null;
        DailyScheduleDetails.optionValues dailyoption = DailyScheduleDetails.optionValues.valueOf(getDailySchedule().getDayOption());
        switch (dailyoption) {
            case XDAY: 
                dtEnd = getDailySchedule().getScheduleEndDate();
                day = getDailySchedule().getDay();
System.out.println("\ndt = " + dt + ", edt = " + dtEnd + ", day = " + day);                
                dates = scheduleService.getScheduledDates(dt, dtEnd, time, day, null);
System.out.println("\ndates = " + dates + ", size = " + dates.size());                
                break;
            case WEEKDAY : 
                dtEnd = getDailySchedule().getScheduleEndDate();                        
                weekdays = ScheduleData.convertToWeekdays(getDailySchedule().getDaysOfWeek());
                ScheduleSequence scheduleSequence = new WeekScheduleSequenceDecorator(new TrimDatesScheduleSequenceDecorator(new DefaultScheduleSequence()),1,weekdays.length);
                dates = scheduleService.getScheduledDates(dt, dtEnd, time, weekdays, scheduleSequence);
                break;
        }
        return dates;
    }
}

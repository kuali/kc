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

import org.kuali.kra.scheduling.expr.util.CronSpecialChars;
import org.kuali.kra.scheduling.service.ScheduleService;
import org.kuali.kra.scheduling.util.Time24HrFmt;

/**
 * This class is form data helper class used to store UI based date for recurrence.
 */
public class MonthlyScheduleData extends ScheduleData {
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;

    public MonthlyScheduleData() {
        super();
        this.setRecurrenceType(StyleKey.MONTHLY.toString());
        this.setMonthlySchedule(new MonthlyScheduleDetails());
    }
    
    public void setWeeklySchedule(WeeklyScheduleDetails weeklySchedule) {
        this.weeklySchedule = weeklySchedule;
    }

    public WeeklyScheduleDetails getWeeklySchedule() {
        return weeklySchedule;
    }

    public void setMonthlySchedule(MonthlyScheduleDetails monthlySchedule) {
        this.monthlySchedule = monthlySchedule;
    }

    public MonthlyScheduleDetails getMonthlySchedule() {
        return monthlySchedule;
    }

    public String toString() {
        return "Monthly Schedule Dates:" +
        " Scheduled start date = " + getScheduleStartDate().toString() + 
        ", scheduled end date = " + getMonthlySchedule().getScheduleEndDate().toString() + 
        ", month Option1 = " + getMonthlySchedule().getOption1Month() + 
        ", month Option2 = " + getMonthlySchedule().getOption2Month() +
        ", month's Week = " + getMonthlySchedule().getSelectedMonthsWeek() +
        ", day Of Week = " + getMonthlySchedule().getSelectedDayOfWeek() +
        ", start time = " + getTime().getTime() + getTime().getMeridiem() +
        ", location = " + getPlace();
    }

    @Override
    public List<java.util.Date> getScheduleDates(ScheduleService scheduleService, java.sql.Date dt, Time24HrFmt time) throws ParseException {
        List<Date> dates = null;
        Date dtEnd = null;
        int frequency = 0;
        int day = 0;
        CronSpecialChars weekOfMonth = null;
        CronSpecialChars dayOfWeek = null;
        
        MonthlyScheduleDetails.optionValues monthOption = MonthlyScheduleDetails.optionValues.valueOf(getMonthlySchedule().getMonthOption());
        switch(monthOption) {
            case XDAYANDXMONTH :
                dtEnd = getMonthlySchedule().getScheduleEndDate();
                day = getMonthlySchedule().getDay();
                frequency = getMonthlySchedule().getOption1Month();
                dates = scheduleService.getScheduledDates(dt, dtEnd, time, day, frequency, null);
                break;
            case XDAYOFWEEKANDXMONTH :
                dtEnd = getMonthlySchedule().getScheduleEndDate();
                weekOfMonth = ScheduleData.getWeekOfMonth(getMonthlySchedule().getSelectedMonthsWeek());
                dayOfWeek = ScheduleData.getDayOfWeek(getMonthlySchedule().getSelectedDayOfWeek());
                frequency = getMonthlySchedule().getOption2Month();
                dates = scheduleService.getScheduledDates(dt, dtEnd, time, dayOfWeek, weekOfMonth, frequency, null);
                break;
            }

        return dates;
    }
}

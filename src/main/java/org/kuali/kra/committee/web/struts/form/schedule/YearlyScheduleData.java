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
public class YearlyScheduleData extends ScheduleData {
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    
    public YearlyScheduleData() {
        super();
        this.setRecurrenceType(StyleKey.YEARLY.toString());
        this.setYearlySchedule(new YearlyScheduleDetails());
    }
    
    public void setYearlySchedule(YearlyScheduleDetails yearlySchedule) {
        this.yearlySchedule = yearlySchedule;
    }

    public YearlyScheduleDetails getYearlySchedule() {
        return yearlySchedule;
    }
    
    public String toString() {
        return "Monthly Schedule Dates:" +
        " Scheduled start date = " + getScheduleStartDate().toString() + 
        ", scheduled end date = " + getYearlySchedule().getScheduleEndDate().toString() + 
        ", day = " + getYearlySchedule().getDay() + 
        ", year Option1 = " + getYearlySchedule().getOption1Year() +
        ", year Option2 = " + getYearlySchedule().getOption2Year() +
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
        CronSpecialChars month = null;
        
        YearlyScheduleDetails.yearOptionValues yearOption = YearlyScheduleDetails.yearOptionValues.valueOf(getYearlySchedule().getYearOption());
        switch(yearOption) {
            case XDAY :
                dtEnd = getYearlySchedule().getScheduleEndDate();
                month = ScheduleData.getMonthOfWeek(getYearlySchedule().getSelectedOption1Month());
                day = getYearlySchedule().getDay();
                frequency = getYearlySchedule().getOption1Year();
                dates = scheduleService.getScheduledDates(dt, dtEnd, time, month, day, frequency, null);
                break;
            case CMPLX:
                dtEnd = getYearlySchedule().getScheduleEndDate();
                weekOfMonth = ScheduleData.getWeekOfMonth(getYearlySchedule().getSelectedMonthsWeek());
                dayOfWeek = ScheduleData.getDayOfWeek(getYearlySchedule().getSelectedDayOfWeek());
                month = ScheduleData.getMonthOfWeek(getYearlySchedule().getSelectedOption2Month());
                frequency = getYearlySchedule().getOption2Year();
                dates = scheduleService.getScheduledDates(dt, dtEnd, time, weekOfMonth, dayOfWeek, month, frequency, null);
                break;
        }
        return dates;
    }

}

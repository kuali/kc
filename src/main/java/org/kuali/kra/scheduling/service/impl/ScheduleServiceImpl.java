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
package org.kuali.kra.scheduling.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.kuali.kra.scheduling.DefaultScheduleSequence;
import org.kuali.kra.scheduling.ScheduleSequence;
import org.kuali.kra.scheduling.expr.CronExpression;
import org.kuali.kra.scheduling.expr.CronSpecialChars;
import org.kuali.kra.scheduling.expr.DayCronExpression;
import org.kuali.kra.scheduling.expr.MonthDayCronExpression;
import org.kuali.kra.scheduling.expr.MonthlyWeekDayCronExpression;
import org.kuali.kra.scheduling.expr.NeverCronExpression;
import org.kuali.kra.scheduling.expr.WeekCronExpression;
import org.kuali.kra.scheduling.expr.YearMonthDayCronExpression;
import org.kuali.kra.scheduling.expr.YearMonthDayOfWeekCronExpression;

public class ScheduleServiceImpl {

    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ScheduleServiceImpl.class);

    public List<Date> getScheduledDates(Date startDate, Date endDate, String time24h, ScheduleSequence scheduleSequence)
            throws ParseException {

        CronExpression expr = new NeverCronExpression(startDate, time24h);
        scheduleSequence = getScheduleSequence(scheduleSequence);
        return scheduleSequence.getScheduleSequence(expr.getExpression(), startDate, endDate);
    }

    public List<Date> getScheduledDates(Date startDate, Date endDate, String time24h, Integer day, ScheduleSequence scheduleSequence)
            throws ParseException {

        CronExpression expr = new DayCronExpression(startDate, time24h, day);
        scheduleSequence = getScheduleSequence(scheduleSequence);
        return scheduleSequence.getScheduleSequence(expr.getExpression(), startDate, endDate);
    }

    public List<Date> getScheduledDates(Date startDate, Date endDate, String time24h, CronSpecialChars[] weekdays,
            ScheduleSequence scheduleSequence) throws ParseException {

        CronExpression expr = new WeekCronExpression(startDate, time24h, weekdays);
        scheduleSequence = getScheduleSequence(scheduleSequence);
        return scheduleSequence.getScheduleSequence(expr.getExpression(), startDate, endDate);
    }

    public List<Date> getScheduledDates(Date startDate, Date endDate, String time24h, Integer day, Integer frequencyInMonth,
            ScheduleSequence scheduleSequence) throws ParseException {

        CronExpression expr = new MonthDayCronExpression(startDate, time24h, day, frequencyInMonth);
        scheduleSequence = getScheduleSequence(scheduleSequence);
        return scheduleSequence.getScheduleSequence(expr.getExpression(), startDate, endDate);
    }

    public List<Date> getScheduledDates(Date startDate, Date endDate, String time24h, CronSpecialChars dayOfWeek,
            CronSpecialChars weekOfMonth, Integer frequencyInMonth, ScheduleSequence scheduleSequence) throws ParseException {

        CronExpression expr = new MonthlyWeekDayCronExpression(startDate, time24h, dayOfWeek, weekOfMonth, frequencyInMonth);
        scheduleSequence = getScheduleSequence(scheduleSequence);
        return scheduleSequence.getScheduleSequence(expr.getExpression(), startDate, endDate);
    }

    public List<Date> getScheduledDates(Date startDate, Date endDate, String time24h, CronSpecialChars month, Integer day,
            Integer frequencyInYear, ScheduleSequence scheduleSequence) throws ParseException {

        CronExpression expr = new YearMonthDayCronExpression(startDate, time24h, month, day, frequencyInYear);
        scheduleSequence = getScheduleSequence(scheduleSequence);
        return scheduleSequence.getScheduleSequence(expr.getExpression(), startDate, endDate);
    }

    public List<Date> getScheduledDates(Date startDate, Date endDate, String time24h, CronSpecialChars weekOfMonth,
            CronSpecialChars dayOfWeek, CronSpecialChars month, Integer frequencyInYear, ScheduleSequence scheduleSequence)
            throws ParseException {

        CronExpression expr = new YearMonthDayOfWeekCronExpression(startDate, time24h, weekOfMonth, dayOfWeek, month,
            frequencyInYear);
        scheduleSequence = getScheduleSequence(scheduleSequence);
        return scheduleSequence.getScheduleSequence(expr.getExpression(), startDate, endDate);
    }

    private ScheduleSequence getScheduleSequence(ScheduleSequence scheduleSequence) {
        if (null == scheduleSequence) {
            scheduleSequence = new DefaultScheduleSequence();
        }
        return scheduleSequence;
    }

}

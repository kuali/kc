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

import org.apache.commons.lang.time.DateUtils;
import org.kuali.kra.scheduling.DefaultScheduleSequence;
import org.kuali.kra.scheduling.ScheduleSequence;
import org.kuali.kra.scheduling.Time24HrFmt;
import org.kuali.kra.scheduling.expr.CronExpression;
import org.kuali.kra.scheduling.expr.CronSpecialChars;
import org.kuali.kra.scheduling.expr.DayCronExpression;
import org.kuali.kra.scheduling.expr.MonthDayCronExpression;
import org.kuali.kra.scheduling.expr.MonthlyWeekDayCronExpression;
import org.kuali.kra.scheduling.expr.NeverCronExpression;
import org.kuali.kra.scheduling.expr.WeekCronExpression;
import org.kuali.kra.scheduling.expr.YearMonthDayCronExpression;
import org.kuali.kra.scheduling.expr.YearMonthDayOfWeekCronExpression;
import org.kuali.kra.scheduling.service.ScheduleService;

public class ScheduleServiceImpl implements ScheduleService {

    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ScheduleServiceImpl.class);

    /**
     * @see org.kuali.kra.scheduling.service.impl.ScheduleService#getScheduledDates(java.util.Date, java.util.Date, org.kuali.kra.scheduling.Time24HrFmt, org.kuali.kra.scheduling.ScheduleSequence)
     */
    public List<Date> getScheduledDates(Date startDate, Date endDate, Time24HrFmt time, ScheduleSequence scheduleSequence)
            throws ParseException {

        CronExpression expr = new NeverCronExpression(startDate, time);
        scheduleSequence = getScheduleSequence(scheduleSequence);
        startDate = wrapTime(startDate, null);
        endDate = wrapTime(endDate, time);
        return scheduleSequence.getScheduleSequence(expr.getExpression(), startDate, endDate);
    }

    /**
     * @see org.kuali.kra.scheduling.service.impl.ScheduleService#getScheduledDates(java.util.Date, java.util.Date, org.kuali.kra.scheduling.Time24HrFmt, java.lang.Integer, org.kuali.kra.scheduling.ScheduleSequence)
     */
    public List<Date> getScheduledDates(Date startDate, Date endDate, Time24HrFmt time, Integer day, ScheduleSequence scheduleSequence)
            throws ParseException {

        CronExpression expr = new DayCronExpression(startDate, time, day);
        scheduleSequence = getScheduleSequence(scheduleSequence);
        startDate = wrapTime(startDate, null);
        endDate = wrapTime(endDate, time);
        return scheduleSequence.getScheduleSequence(expr.getExpression(), startDate, endDate);
    }

    /**
     * @see org.kuali.kra.scheduling.service.impl.ScheduleService#getScheduledDates(java.util.Date, java.util.Date, org.kuali.kra.scheduling.Time24HrFmt, org.kuali.kra.scheduling.expr.CronSpecialChars[], org.kuali.kra.scheduling.ScheduleSequence)
     */
    public List<Date> getScheduledDates(Date startDate, Date endDate, Time24HrFmt time, CronSpecialChars[] weekdays,
            ScheduleSequence scheduleSequence) throws ParseException {

        CronExpression expr = new WeekCronExpression(startDate, time, weekdays);
        scheduleSequence = getScheduleSequence(scheduleSequence);
        startDate = wrapTime(startDate, null);
        endDate = wrapTime(endDate, time);
        return scheduleSequence.getScheduleSequence(expr.getExpression(), startDate, endDate);
    }

    /**
     * @see org.kuali.kra.scheduling.service.impl.ScheduleService#getScheduledDates(java.util.Date, java.util.Date, org.kuali.kra.scheduling.Time24HrFmt, java.lang.Integer, java.lang.Integer, org.kuali.kra.scheduling.ScheduleSequence)
     */
    public List<Date> getScheduledDates(Date startDate, Date endDate, Time24HrFmt time, Integer day, Integer frequencyInMonth,
            ScheduleSequence scheduleSequence) throws ParseException {

        CronExpression expr = new MonthDayCronExpression(startDate, time, day, frequencyInMonth);
        scheduleSequence = getScheduleSequence(scheduleSequence);
        startDate = wrapTime(startDate, null);
        endDate = wrapTime(endDate, time);
        return scheduleSequence.getScheduleSequence(expr.getExpression(), startDate, endDate);
    }

    /**
     * @see org.kuali.kra.scheduling.service.impl.ScheduleService#getScheduledDates(java.util.Date, java.util.Date, org.kuali.kra.scheduling.Time24HrFmt, org.kuali.kra.scheduling.expr.CronSpecialChars, org.kuali.kra.scheduling.expr.CronSpecialChars, java.lang.Integer, org.kuali.kra.scheduling.ScheduleSequence)
     */
    public List<Date> getScheduledDates(Date startDate, Date endDate, Time24HrFmt time, CronSpecialChars dayOfWeek,
            CronSpecialChars weekOfMonth, Integer frequencyInMonth, ScheduleSequence scheduleSequence) throws ParseException {

        CronExpression expr = new MonthlyWeekDayCronExpression(startDate, time, dayOfWeek, weekOfMonth, frequencyInMonth);
        scheduleSequence = getScheduleSequence(scheduleSequence);
        startDate = wrapTime(startDate, null);
        endDate = wrapTime(endDate, time);
        return scheduleSequence.getScheduleSequence(expr.getExpression(), startDate, endDate);
    }

    /**
     * @see org.kuali.kra.scheduling.service.impl.ScheduleService#getScheduledDates(java.util.Date, java.util.Date, org.kuali.kra.scheduling.Time24HrFmt, org.kuali.kra.scheduling.expr.CronSpecialChars, java.lang.Integer, java.lang.Integer, org.kuali.kra.scheduling.ScheduleSequence)
     */
    public List<Date> getScheduledDates(Date startDate, Date endDate, Time24HrFmt time, CronSpecialChars month, Integer day,
            Integer frequencyInYear, ScheduleSequence scheduleSequence) throws ParseException {

        CronExpression expr = new YearMonthDayCronExpression(startDate, time, month, day, frequencyInYear);
        scheduleSequence = getScheduleSequence(scheduleSequence);
        startDate = wrapTime(startDate, null);
        endDate = wrapTime(endDate, time);
        return scheduleSequence.getScheduleSequence(expr.getExpression(), startDate, endDate);
    }

    /**
     * @see org.kuali.kra.scheduling.service.impl.ScheduleService#getScheduledDates(java.util.Date, java.util.Date, org.kuali.kra.scheduling.Time24HrFmt, org.kuali.kra.scheduling.expr.CronSpecialChars, org.kuali.kra.scheduling.expr.CronSpecialChars, org.kuali.kra.scheduling.expr.CronSpecialChars, java.lang.Integer, org.kuali.kra.scheduling.ScheduleSequence)
     */
    public List<Date> getScheduledDates(Date startDate, Date endDate, Time24HrFmt time, CronSpecialChars weekOfMonth,
            CronSpecialChars dayOfWeek, CronSpecialChars month, Integer frequencyInYear, ScheduleSequence scheduleSequence)
            throws ParseException {

        CronExpression expr = new YearMonthDayOfWeekCronExpression(startDate, time, weekOfMonth, dayOfWeek, month,
            frequencyInYear);
        scheduleSequence = getScheduleSequence(scheduleSequence);
        startDate = wrapTime(startDate, null);
        endDate = wrapTime(endDate, time);
        return scheduleSequence.getScheduleSequence(expr.getExpression(), startDate, endDate);
    }
    
    private Date wrapTime(Date date, Time24HrFmt time) {
        java.sql.Date dt = new java.sql.Date(date.getTime());
        Date utDt = new Date(dt.getTime());
        if (null != time) {
            utDt = DateUtils.addHours(utDt, new Integer(time.getHours()));
            utDt = DateUtils.addHours(utDt, new Integer(time.getMinutes()));
        }
        return utDt;
    }
    
    private ScheduleSequence getScheduleSequence(ScheduleSequence scheduleSequence) {
        if (null == scheduleSequence) {
            scheduleSequence = new DefaultScheduleSequence();
        }
        return scheduleSequence;
    }

}

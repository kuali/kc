/*
 * Copyright 2006-2009 The Kuali Foundation
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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.kuali.kra.scheduling.expr.CronExpression;
import org.kuali.kra.scheduling.expr.DayCronExpression;
import org.kuali.kra.scheduling.expr.MonthDayCronExpression;
import org.kuali.kra.scheduling.expr.MonthDayMultipleYearsCronExpression;
import org.kuali.kra.scheduling.expr.MonthlyWeekDayCronExpression;
import org.kuali.kra.scheduling.expr.NeverCronExpression;
import org.kuali.kra.scheduling.expr.WeekCronExpression;
import org.kuali.kra.scheduling.expr.YearMonthDayCronExpression;
import org.kuali.kra.scheduling.expr.YearMonthDayOfWeekCronExpression;
import org.kuali.kra.scheduling.expr.util.CronSpecialChars;
import org.kuali.kra.scheduling.sequence.DefaultScheduleSequence;
import org.kuali.kra.scheduling.sequence.ScheduleSequence;
import org.kuali.kra.scheduling.sequence.TrimDatesScheduleSequenceDecorator;
import org.kuali.kra.scheduling.service.ScheduleService;
import org.kuali.kra.scheduling.util.Time24HrFmt;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class is thread safe implementation of ScheduleService interface. Primary function of this service implementation is to
 * return generated schedule dates using parameters passed.
 */
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ScheduleServiceImpl.class);

    /**
     * This overloaded implementation uses NeverCronExpression, which generates schedule for specific DAY only.
     * 
     * Note: Start date's day is used while building NeverCronExpression as specific DAY.
     * 
     * @see org.kuali.kra.scheduling.service.impl.ScheduleService#getScheduledDates(java.util.Date, java.util.Date,
     *      org.kuali.kra.scheduling.util.Time24HrFmt, org.kuali.kra.scheduling.sequence.ScheduleSequence)
     */
    public List<Date> getScheduledDates(Date startDate, Date endDate, Time24HrFmt time, ScheduleSequence scheduleSequence)
            throws ParseException {

        CronExpression expr = new NeverCronExpression(startDate, time);
        return getScheduledDates(expr, startDate, endDate, time, scheduleSequence);
    }

    /**
     * This overloaded implementation uses DayCronExpression targeting daily types of schedule generation.
     * 
     * @see org.kuali.kra.scheduling.service.impl.ScheduleService#getScheduledDates(java.util.Date, java.util.Date,
     *      org.kuali.kra.scheduling.util.Time24HrFmt, java.lang.Integer, org.kuali.kra.scheduling.sequence.ScheduleSequence)
     */
    public List<Date> getScheduledDates(Date startDate, Date endDate, Time24HrFmt time, Integer frequencyInDay,
            ScheduleSequence scheduleSequence) throws ParseException {

        CronExpression expr = new DayCronExpression(startDate, time, frequencyInDay);
        return getScheduledDates(expr, startDate, endDate, time, scheduleSequence);
    }

    /**
     * This overloaded implementation uses WeekCronExpression targeting weekly types of schedule generation.
     * 
     * @see org.kuali.kra.scheduling.service.impl.ScheduleService#getScheduledDates(java.util.Date, java.util.Date,
     *      org.kuali.kra.scheduling.util.Time24HrFmt, org.kuali.kra.scheduling.expr.util.CronSpecialChars[],
     *      org.kuali.kra.scheduling.sequence.ScheduleSequence)
     */
    public List<Date> getScheduledDates(Date startDate, Date endDate, Time24HrFmt time, CronSpecialChars[] weekdays,
            ScheduleSequence scheduleSequence) throws ParseException {

        CronExpression expr = new WeekCronExpression(startDate, time, weekdays);
        return getScheduledDates(expr, startDate, endDate, time, scheduleSequence);
    }

    /**
     * This overloaded implementation uses MonthDayCronExpression targeting monthly types of schedule generation.
     * 
     * @see org.kuali.kra.scheduling.service.impl.ScheduleService#getScheduledDates(java.util.Date, java.util.Date,
     *      org.kuali.kra.scheduling.util.Time24HrFmt, java.lang.Integer, java.lang.Integer,
     *      org.kuali.kra.scheduling.sequence.ScheduleSequence)
     */
    public List<Date> getScheduledDates(Date startDate, Date endDate, Time24HrFmt time, Integer day, Integer frequencyInMonth,
            ScheduleSequence scheduleSequence) throws ParseException {

        CronExpression expr = new MonthDayCronExpression(startDate, time, day, frequencyInMonth);
        return getScheduledDates(expr, startDate, endDate, time, scheduleSequence);
    }
    
    /**
     * This overloaded implementation uses MonthDayMultipleYearsCronExpression targeting monthly types of schedule generation.
     * 
     * @see org.kuali.kra.scheduling.service.ScheduleService#getScheduledDates(java.util.Date, java.util.Date, org.kuali.kra.scheduling.util.Time24HrFmt,
     * org.kuali.kra.scheduling.sequence.ScheduleSequence, java.lang.Integer)
     */
    public List<Date> getScheduledDates(Date startDate, Date endDate, Time24HrFmt time, ScheduleSequence scheduleSequence
            , Integer dayOfMonth) throws ParseException {

        CronExpression expr = new MonthDayMultipleYearsCronExpression(startDate, time, dayOfMonth);
        return getScheduledDates(expr, startDate, endDate, time, scheduleSequence);
    }

    /**
     * This overloaded implementation uses MonthDayCronExpression targeting monthly types of schedule generation.
     * 
     * @see org.kuali.kra.scheduling.service.impl.ScheduleService#getScheduledDates(java.util.Date, java.util.Date,
     *      org.kuali.kra.scheduling.util.Time24HrFmt, org.kuali.kra.scheduling.expr.util.CronSpecialChars,
     *      org.kuali.kra.scheduling.expr.util.CronSpecialChars, java.lang.Integer,
     *      org.kuali.kra.scheduling.sequence.ScheduleSequence)
     */
    public List<Date> getScheduledDates(Date startDate, Date endDate, Time24HrFmt time, CronSpecialChars dayOfWeek,
            CronSpecialChars weekOfMonth, Integer frequencyInMonth, ScheduleSequence scheduleSequence) throws ParseException {

        CronExpression expr = new MonthlyWeekDayCronExpression(startDate, time, dayOfWeek, weekOfMonth, frequencyInMonth);
        return getScheduledDates(expr, startDate, endDate, time, scheduleSequence);
    }

    /**
     * This overloaded implementation uses YearMonthDayCronExpression targeting yearly types of schedule generation.
     * 
     * @see org.kuali.kra.scheduling.service.impl.ScheduleService#getScheduledDates(java.util.Date, java.util.Date,
     *      org.kuali.kra.scheduling.util.Time24HrFmt, org.kuali.kra.scheduling.expr.util.CronSpecialChars, java.lang.Integer,
     *      java.lang.Integer, org.kuali.kra.scheduling.sequence.ScheduleSequence)
     */
    public List<Date> getScheduledDates(Date startDate, Date endDate, Time24HrFmt time, CronSpecialChars month, Integer day,
            Integer frequencyInYear, ScheduleSequence scheduleSequence) throws ParseException {

        CronExpression expr = new YearMonthDayCronExpression(startDate, time, month, day, frequencyInYear);
        return getScheduledDates(expr, startDate, endDate, time, scheduleSequence);
    }

    /**
     * This overloaded implementation uses YearMonthDayCronExpression targeting yearly types of schedule generation.
     * 
     * @see org.kuali.kra.scheduling.service.impl.ScheduleService#getScheduledDates(java.util.Date, java.util.Date,
     *      org.kuali.kra.scheduling.util.Time24HrFmt, org.kuali.kra.scheduling.expr.util.CronSpecialChars,
     *      org.kuali.kra.scheduling.expr.util.CronSpecialChars, org.kuali.kra.scheduling.expr.util.CronSpecialChars,
     *      java.lang.Integer, org.kuali.kra.scheduling.sequence.ScheduleSequence)
     */
    public List<Date> getScheduledDates(Date startDate, Date endDate, Time24HrFmt time, CronSpecialChars weekOfMonth,
            CronSpecialChars dayOfWeek, CronSpecialChars month, Integer frequencyInYear, ScheduleSequence scheduleSequence)
            throws ParseException {

        CronExpression expr = new YearMonthDayOfWeekCronExpression(startDate, time, weekOfMonth, dayOfWeek, month, frequencyInYear);
        return getScheduledDates(expr, startDate, endDate, time, scheduleSequence);
    }

    /**
     * This is helper method, gets appropriate ScheduleSequence in case of null, wraps time accurately and executes schedule
     * sequence.
     * 
     * @param expression expects valid cron expression.
     * @param startDate is begin date of sequence.
     * @param endDate is end date of sequence.
     * @param time at which schedule is held.
     * @param scheduleSequence executes schedule sequence based on implementation of the ScheduleSequence.
     * @return List<Date> of valid schedule dates between start and end date.
     * @throws ParseException
     */
    private List<Date> getScheduledDates(CronExpression expression, Date startDate, Date endDate, Time24HrFmt time,
            ScheduleSequence scheduleSequence) throws ParseException {
        scheduleSequence = getScheduleSequence(scheduleSequence);
        startDate = wrapTime(startDate, null);
        endDate = wrapTime(endDate, time);
        return scheduleSequence.executeScheduleSequence(expression.getExpression(), startDate, endDate);
    }

    /**
     * This is helper method, wraps date with time accurately in java.util.Date (milliseconds).
     * 
     * @param date to be wrapped.
     * @param time to be added to date.
     * @return wrapped date & time.
     */
    private Date wrapTime(Date date, Time24HrFmt time) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR);
        int min = calendar.get(Calendar.MINUTE);
        int am_pm = calendar.get(Calendar.AM_PM);
        if(am_pm == Calendar.AM) {
            date = DateUtils.addHours(date, -hour);
            date = DateUtils.addMinutes(date, -min);
        } else {
            date = DateUtils.addHours(date, -hour-12);
            date = DateUtils.addMinutes(date, -min);    
        }
        if (null != time) {
            date = DateUtils.addHours(date, new Integer(time.getHours()));
            date = DateUtils.addMinutes(date, new Integer(time.getMinutes()));
        }
        return date;
    }

    /**
     * This is helper method, if ScheduleSequence passed is null it will construct new DefaultScheduleSequence.
     * 
     * @param scheduleSequence
     * @return ScheduleSequence after sanity check.
     */
    private ScheduleSequence getScheduleSequence(ScheduleSequence scheduleSequence) {
        if (null == scheduleSequence) {
            scheduleSequence = new TrimDatesScheduleSequenceDecorator(new DefaultScheduleSequence());
        }
        return scheduleSequence;
    }

}

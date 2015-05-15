/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.sys.framework.scheduling;

import org.kuali.coeus.sys.framework.scheduling.seq.ScheduleSequence;
import org.kuali.coeus.sys.framework.scheduling.util.CronSpecialChars;
import org.kuali.coeus.sys.framework.scheduling.util.Time24HrFmt;

import java.text.ParseException;
import java.util.Date;
import java.util.List;


public interface ScheduleService {

    /**
     * This method must return schedule dates generated between provided parameters.
     * @param startDate is begin date.
     * @param endDate is end date.
     * @param time is time.
     * @param scheduleSequence to used for generating sequence. If value passed is null, DefaultScheduleSequnce will be used.
     * @return List&lt;Date&gt; of date sequence generated.
     * @throws ParseException
     */
    public List<Date> getScheduledDates(Date startDate, Date endDate, Time24HrFmt time, ScheduleSequence scheduleSequence)
            throws ParseException;

    /**
     * This method must return schedule dates generated between provided parameters.
     * @param startDate is begin date.
     * @param endDate is end date.
     * @param time is time.
     * @param frequencyInDay
     * @param scheduleSequence to used for generating sequence. If value passed is null, DefaultScheduleSequnce will be used.
     * @return List&lt;Date&gt; of date sequence generated.
     * @throws ParseException
     */
    public List<Date> getScheduledDates(Date startDate, Date endDate, Time24HrFmt time, Integer frequencyInDay,
            ScheduleSequence scheduleSequence) throws ParseException;
    
    /**
     * This method must return schedule dates generated between provided parameters.
     * @param startDate is begin date.
     * @param endDate is end date.
     * @param time is time.
     * @param intervalInDays is the number of days in each repeating interval
     * @return List&lt;Date&gt; of date sequence generated.
     * @throws ParseException
     */
    public List<Date> getIntervalInDaysScheduledDates(Date startDate, Date endDate, Time24HrFmt time, Integer intervalInDays) throws ParseException;

    /**
     * This method must return schedule dates generated between provided parameters.
     * @param startDate is begin date.
     * @param endDate is end date.
     * @param time is time.
     * @param weekdays is array of CronSpecialChars containing week day values.
     * @param scheduleSequence to used for generating sequence. If value passed is null, DefaultScheduleSequnce will be used.
     * @return List&lt;Date&gt; of date sequence generated.
     * @throws ParseException
     */
    public List<Date> getScheduledDates(Date startDate, Date endDate, Time24HrFmt time, CronSpecialChars[] weekdays,
            ScheduleSequence scheduleSequence) throws ParseException;

    /**
     * This method must return schedule dates generated between provided parameters.
     * @param startDate is begin date.
     * @param endDate is end date.
     * @param time is time.
     * @param day is day of month.
     * @param frequencyInMonth
     * @param scheduleSequence to used for generating sequence. If value passed is null, DefaultScheduleSequnce will be used.
     * @return List&lt;Date&gt; of date sequence generated.
     * @throws ParseException
     */
    public List<Date> getScheduledDates(Date startDate, Date endDate, Time24HrFmt time, Integer day, Integer frequencyInMonth,
            ScheduleSequence scheduleSequence) throws ParseException;
            
    /**
     * This method must return schedule dates generated between provided parameters.
     * @param startDate is begin date.
     * @param endDate is end date.
     * @param time is time.
     * @param scheduleSequence to used for generating sequence. If value passed is null, DefaultScheduleSequnce will be used.
     * @return List&lt;Date&gt; of date sequence generated.
     * @throws ParseException
     */
    public List<Date> getScheduledDates(Date startDate, Date endDate, Time24HrFmt time, ScheduleSequence scheduleSequence, Integer dayOfMonth) throws ParseException;

    /**
     * This method must return schedule dates generated between provided parameters.
     * @param startDate is begin date.
     * @param endDate is end date.
     * @param time is time.
     * @param dayOfWeek is CronSpecialChars defining day of week.
     * @param weekOfMonth is CronSpecialChars defining week of month.
     * @param frequencyInMonth
     * @param scheduleSequence to used for generating sequence. If value passed is null, DefaultScheduleSequnce will be used.
     * @return List&lt;Date&gt; of date sequence generated.
     * @throws ParseException
     */
    public List<Date> getScheduledDates(Date startDate, Date endDate, Time24HrFmt time, CronSpecialChars dayOfWeek,
            CronSpecialChars weekOfMonth, Integer frequencyInMonth, ScheduleSequence scheduleSequence) throws ParseException;

    /**
     * This method must return schedule dates generated between provided parameters.
     * @param startDate is begin date.
     * @param endDate is end date.
     * @param time is time.
     * @param month is CronSpecialChars defining month.
     * @param day is day of month.
     * @param frequencyInYear
     * @param scheduleSequence to used for generating sequence. If value passed is null, DefaultScheduleSequnce will be used.
     * @return List&lt;Date&gt; of date sequence generated.
     * @throws ParseException
     */
    public List<Date> getScheduledDates(Date startDate, Date endDate, Time24HrFmt time, CronSpecialChars month, Integer day,
            Integer frequencyInYear, ScheduleSequence scheduleSequence) throws ParseException;

    /**
     * This method must return schedule dates generated between provided parameters.
     * @param startDate is begin date.
     * @param endDate is end date.
     * @param time is time.
     * @param weekOfMonth is CronSpecialChars defining week of month.
     * @param dayOfWeek is CronSpecialChars defining day of week.
     * @param month is CronSpecialChars defining month.
     * @param frequencyInYear
     * @param scheduleSequence to used for generating sequence. If value passed is null, DefaultScheduleSequnce will be used.
     * @return List&lt;Date&gt; of date sequence generated.
     * @throws ParseException
     */
    public List<Date> getScheduledDates(Date startDate, Date endDate, Time24HrFmt time, CronSpecialChars weekOfMonth,
            CronSpecialChars dayOfWeek, CronSpecialChars month, Integer frequencyInYear, ScheduleSequence scheduleSequence)
            throws ParseException;

}

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
package org.kuali.kra.scheduling.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.kuali.kra.scheduling.expr.util.CronSpecialChars;
import org.kuali.kra.scheduling.sequence.ScheduleSequence;
import org.kuali.kra.scheduling.util.Time24HrFmt;


public interface ScheduleService {

    /**
     * This method must return schedule dates generated between provided parameters.
     * @param startDate is begin date.
     * @param endDate is end date.
     * @param time is time.
     * @param scheduleSequence to used for generating sequence. If value passed is null, DefaultScheduleSequnce will be used.
     * @return List<Date> of date sequence generated.
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
     * @return List<Date> of date sequence generated.
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
     * @return List<Date> of date sequence generated.
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
     * @return List<Date> of date sequence generated.
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
     * @return List<Date> of date sequence generated.
     * @throws ParseException
     */
    public List<Date> getScheduledDates(Date startDate, Date endDate, Time24HrFmt time, Integer day, Integer frequencyInMonth,
            ScheduleSequence scheduleSequence) throws ParseException;
            
    /**
     * This method must return schedule dates generated between provided parameters.
     * @param startDate is begin date.
     * @param endDate is end date.
     * @param time is time.
     * @param day is day of month.
     * @param frequencyInMonth
     * @param scheduleSequence to used for generating sequence. If value passed is null, DefaultScheduleSequnce will be used.
     * @return List<Date> of date sequence generated.
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
     * @return List<Date> of date sequence generated.
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
     * @return List<Date> of date sequence generated.
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
     * @return List<Date> of date sequence generated.
     * @throws ParseException
     */
    public List<Date> getScheduledDates(Date startDate, Date endDate, Time24HrFmt time, CronSpecialChars weekOfMonth,
            CronSpecialChars dayOfWeek, CronSpecialChars month, Integer frequencyInYear, ScheduleSequence scheduleSequence)
            throws ParseException;

}

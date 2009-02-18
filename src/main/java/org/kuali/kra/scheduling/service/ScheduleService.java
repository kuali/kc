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
package org.kuali.kra.scheduling.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.kuali.kra.scheduling.ScheduleSequence;
import org.kuali.kra.scheduling.Time24HrFmt;
import org.kuali.kra.scheduling.expr.CronSpecialChars;


public interface ScheduleService {

    public abstract List<Date> getScheduledDates(Date startDate, Date endDate, Time24HrFmt time, ScheduleSequence scheduleSequence)
            throws ParseException;

    public abstract List<Date> getScheduledDates(Date startDate, Date endDate, Time24HrFmt time, Integer day,
            ScheduleSequence scheduleSequence) throws ParseException;

    public abstract List<Date> getScheduledDates(Date startDate, Date endDate, Time24HrFmt time, CronSpecialChars[] weekdays,
            ScheduleSequence scheduleSequence) throws ParseException;

    public abstract List<Date> getScheduledDates(Date startDate, Date endDate, Time24HrFmt time, Integer day,
            Integer frequencyInMonth, ScheduleSequence scheduleSequence) throws ParseException;

    public abstract List<Date> getScheduledDates(Date startDate, Date endDate, Time24HrFmt time, CronSpecialChars dayOfWeek,
            CronSpecialChars weekOfMonth, Integer frequencyInMonth, ScheduleSequence scheduleSequence) throws ParseException;

    public abstract List<Date> getScheduledDates(Date startDate, Date endDate, Time24HrFmt time, CronSpecialChars month,
            Integer day, Integer frequencyInYear, ScheduleSequence scheduleSequence) throws ParseException;

    public abstract List<Date> getScheduledDates(Date startDate, Date endDate, Time24HrFmt time, CronSpecialChars weekOfMonth,
            CronSpecialChars dayOfWeek, CronSpecialChars month, Integer frequencyInYear, ScheduleSequence scheduleSequence)
            throws ParseException;

}
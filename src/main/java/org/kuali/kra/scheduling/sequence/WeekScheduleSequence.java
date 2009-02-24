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
package org.kuali.kra.scheduling.sequence;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import org.quartz.CronTrigger;
import org.quartz.TriggerUtils;

/**
 * This class is variation of DefaultSchduleSequence, it adds logic wrapper to dates returned by Quartz util.
 * <p>
 * Logic wrapper filters dates, to generate schedule for every week, every other week, etc ...
 * <p>
 * This implementation is very specifically addressed to deal with week, biweekly, etc date filtering requirements. Implementation
 * uses org.quartz.TriggerUtils class to generate List of dates from Cron expression.
 * <p>
 * It uses current time zone, where application is hosted.
 * <p>
 * Note: Dates used in generating schedule must be wrapped with required time accuracy.
 * e.g.  Start Date: 02/01/09 10:10 End Date: 02/05/09 10:10 
 * Generated Dates will be in between 02/01/09 10:10 and 02/05/09 10:10.
 * Any date expected before 02/01/09 10:09 will be ignored. Date 02/01/09 10:00 will be ignored.
 * Any date expected after  02/05/09 10:11 will be ignored. Date 02/05/09 12:00 will be ignored.
 */
public class WeekScheduleSequence implements ScheduleSequence {

    private Integer frequency;

    private Integer dayCount;

    /**
     * Constructs a WeekScheduleSequence.java.
     * @param frequency can be weekly, biweekly, etc...
     * @param dayCount can be no of days in week meeting is scheduled.
     */
    public WeekScheduleSequence(Integer frequency, Integer dayCount) {
        super();
        this.frequency = frequency;
        this.dayCount = dayCount;
    }

    /**
     * @see org.kuali.kra.scheduling.sequence.ScheduleSequence#executeScheduleSequence(java.lang.String, java.util.Date, java.util.Date)
     */
    @SuppressWarnings("unchecked")
    public List<Date> executeScheduleSequence(String expression, Date startDate, Date endDate) throws ParseException {

        CronTrigger ct = new CronTrigger("t", "g", "j", "g", new Date(), null, expression);
        ct.setTimeZone(TimeZone.getDefault());
        List<Date> dates = TriggerUtils.computeFireTimesBetween(ct, null, startDate, endDate);

        if (frequency != 1) {

            List<Date> filteredDates = new ArrayList<Date>();
            // TODO see if u can replace iterator
            Iterator<Date> it = dates.iterator();
            while (it.hasNext()) {
                for (int i = 0; i < dayCount && it.hasNext(); i++) {
                    Date dt = it.next();
                    filteredDates.add(dt);
                }
                for (int i = 0; i < (dayCount * (frequency - 1)) && it.hasNext(); i++) {
                    it.next();
                }
            }
            dates = filteredDates;
        }

        return dates;
    }
}

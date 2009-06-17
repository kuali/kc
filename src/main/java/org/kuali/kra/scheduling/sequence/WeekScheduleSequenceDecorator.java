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
package org.kuali.kra.scheduling.sequence;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.ObjectUtils;

/**
 * This class decorates ScheduleSequence to add logic wrapper to filter dates, to generate schedule for every week, every other week, etc ...
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
public class WeekScheduleSequenceDecorator extends ScheduleSequenceDecorator {

    private Integer frequency;

    private Integer dayCount;

    /**
     * Constructs a WeekScheduleSequence.java.
     * @param frequency can be weekly, biweekly, etc...
     * @param dayCount can be no of days in week meeting is scheduled.
     */
    public WeekScheduleSequenceDecorator(ScheduleSequence scheduleSequence, Integer frequency, Integer dayCount) {
        super(scheduleSequence);
        this.frequency = frequency;
        this.dayCount = dayCount;
    }

    /**
     * @see org.kuali.kra.scheduling.sequence.ScheduleSequence#executeScheduleSequence(java.lang.String, java.util.Date, java.util.Date)
     */
    public List<Date> executeScheduleSequence(String expression, Date startDate, Date endDate) throws ParseException {

        List<Date> dates = scheduleSequence.executeScheduleSequence(expression, startDate, endDate);

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dayCount == null) ? 0 : dayCount.hashCode());
        result = prime * result + ((frequency == null) ? 0 : frequency.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        WeekScheduleSequenceDecorator other = (WeekScheduleSequenceDecorator) obj;        
        return ObjectUtils.equals(dayCount, other.dayCount) &&
        ObjectUtils.equals(frequency, other.frequency);
    }
}

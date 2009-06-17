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
import java.util.List;

/**
 * This class decorates ScheduleSequence to trim dates outside start and end dates passed as parameter.
 */
public class TrimDatesScheduleSequenceDecorator extends ScheduleSequenceDecorator {

    /**
     * Constructs a TrimDatesScheduleSequenceDecorator.java.
     * @param scheduleSequence
     */
    public TrimDatesScheduleSequenceDecorator(ScheduleSequence scheduleSequence) {
        super(scheduleSequence);
    }
    
    /**
     * @see org.kuali.kra.scheduling.sequence.ScheduleSequence#executeScheduleSequence(java.lang.String, java.util.Date, java.util.Date)
     */
    public List<Date> executeScheduleSequence(String expression, Date startDate, Date endDate) throws ParseException {
        List<Date> dates = scheduleSequence.executeScheduleSequence(expression, startDate, endDate);
        List<Date> trimmedDates = new ArrayList<Date>();
        for(Date date : dates) {
            if(!date.before(startDate) && !date.after(endDate)) {
                trimmedDates.add(date);
            }      
        }    
        return trimmedDates;
    }
}

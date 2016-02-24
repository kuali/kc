/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
package org.kuali.coeus.sys.framework.scheduling.seq;

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
    
    @Override
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

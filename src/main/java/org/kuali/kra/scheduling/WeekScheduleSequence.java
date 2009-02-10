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
package org.kuali.kra.scheduling;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import org.kuali.kra.scheduling.service.impl.ScheduleServiceImpl;
import org.quartz.CronTrigger;
import org.quartz.TriggerUtils;

public class WeekScheduleSequence implements ScheduleSequence {
    
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(WeekScheduleSequence.class);
    
    private Integer frequency;
    
    private Integer dayCount;

    public WeekScheduleSequence(Integer frequency, Integer dayCount) {
        super();
        this.frequency = frequency;
        this.dayCount = dayCount;
    }

    @SuppressWarnings("unchecked")
    public List<Date> getScheduleSequence(String expr, Date startDate, Date endDate) throws ParseException {
        
        CronTrigger ct = new CronTrigger("t", "g", "j", "g", new Date(), null, expr);
        ct.setTimeZone(TimeZone.getDefault());
        System.err.println(ct.getExpressionSummary());//TODO remove
        
        List<Date> dates = TriggerUtils.computeFireTimesBetween(ct, null, startDate, endDate);
        if(frequency == 1) {
            return dates;
        }
        
        List<Date> filteredDates = new ArrayList<Date>();
      
        Iterator<Date> it = dates.iterator();
        while(it.hasNext()) {
            for(int i = 0; i < dayCount && it.hasNext(); i++) {
                Date dt = it.next();
                filteredDates.add(dt);
            }
            for(int i = 0; i < (dayCount * (frequency - 1)) && it.hasNext(); i++){
                it.next();
            }
        }

        return filteredDates;
        
    }

}

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
package org.kuali.kra.scheduling.expr;

import java.text.ParseException;
import java.util.Date;

import org.kuali.kra.scheduling.Time24HrFmt;

public abstract class CronExpression {
    
    private Date startDate;
    
    //private String hours;
    
    //private String minutes;    
    
    private Time24HrFmt time;
    
    public final String SECONDS = "0";
    
    public CronExpression(Date startDate, Time24HrFmt time) throws ParseException {
        super();
        this.startDate = startDate;
        this.time = time;
        //parseTime(time);
    }
    
/*    private void parseTime(String time) throws ParseException {
        
        String[] result = time.split(":");
        
        if(result.length != 2)
            throw new ParseException("Time format exception, expects hh:mm", 0);
        
        Integer hrs;
        Integer mins;
        
        try {
            hrs = new Integer(result[0]);
            mins = new Integer(result[1]);
        }catch (NumberFormatException e) {
            throw new ParseException("Time format exception, expects hh as 0-23 & mm as 0-59", 0);
        }
        
        if(!(hrs >= 0 && hrs <= 23)) {
            throw new ParseException("Time format exception, expects hh as 0-23", 0);
        }
        
        if(!(mins >= 0 && mins <= 59)) {
            throw new ParseException("Time format exception, expects mm as 0-59", 0);
        }
        this.hours = result[0];
        this.minutes = result[1];
        
    }*/
    
    public abstract String getExpression();

    protected Date getStartDate() {
        return startDate;
    }
/*
    protected String getHours() {
        return hours;
    }

    protected String getMinutes() {
        return minutes;
    }*/
    
    protected Time24HrFmt getTime() {
        return time;
    }

}

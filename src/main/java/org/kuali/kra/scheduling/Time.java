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

public class Time {
    
    private String hours;
    
    private String minutes;
    
    public Time(String time) throws ParseException {
        parseTime(time);
    }
    
    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }
    
    private void parseTime(String time) throws ParseException {
        
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
        
    }
}

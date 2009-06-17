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
package org.kuali.kra.scheduling.util;

import java.io.Serializable;
import java.text.ParseException;

import org.apache.commons.lang.StringUtils;

/**
 * This is util class helps facade time in 24 hour format.
 */
public class Time24HrFmt implements Serializable{

    private static final long serialVersionUID = 2554984023134603437L;

    private String hours;
    
    private String minutes;
    
    public static final String splitChr = ":";
    
    public static final String MSG1 = "Time format exception, expects hh:mm";
    
    public static final String MSG2 = "Time format exception, expects hh as 0-23 & mm as 0-59";
    
    public static final String MSG3 = "Time format exception, expects hh as 0-23";
    
    public static final String MSG4 = "Time format exception, expects mm as 0-59";
    
    /**
     * Constructs a Time24HrFmt.java.
     * @param time is expected in 24hr format along with minutes, separated by colon (hh:mm).
     * @throws ParseException
     */
    public Time24HrFmt(String time) throws ParseException {
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
        
        String[] result = time.split(splitChr);
        
        if(result.length != 2)
            throw new ParseException(MSG1, 0);
        
        Integer hrs;
        Integer mins;
        
        try {
            hrs = new Integer(result[0]);
            mins = new Integer(result[1]);
        }catch (NumberFormatException e) {
            throw new ParseException(MSG2, 0);
        }
        
        if(!(hrs >= 0 && hrs <= 23)) {
            throw new ParseException(MSG3, 0);
        }
        
        if(!(mins >= 0 && mins <= 59)) {
            throw new ParseException(MSG4, 0);
        }
        this.hours = result[0];
        this.minutes = result[1];
        
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((hours == null) ? 0 : hours.hashCode());
        result = prime * result + ((minutes == null) ? 0 : minutes.hashCode());
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
        Time24HrFmt other = (Time24HrFmt) obj;
        return StringUtils.equals(hours, other.hours) &&
        StringUtils.equals(minutes, other.minutes);
    }
    
    @Override
    public String toString() {
       return hours + ":" + minutes; 
    }
}

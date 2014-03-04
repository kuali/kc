/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.sys.framework.scheduling.util;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.io.Serializable;

/**
 * This is util class helps facade time in 24 hour format.
 */
public class Time24HrFmt implements Serializable{

    private static final long serialVersionUID = 2554984023134603437L;

    private String hours;
    private String minutes;
    
    /**
     * Constructs a Time24HrFmt.
     * @param time is expected in 24hr format along with minutes, separated by colon (hh:mm).
     * IllegalArgumentException if the time to parse is invalid
     */
    public Time24HrFmt(String time) {
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
    
    private void parseTime(String time) {
        final DateTimeFormatter fmt = ISODateTimeFormat.hourMinute();
        final LocalTime localTime = fmt.parseLocalTime(time);
        hours = String.valueOf(localTime.getHourOfDay());
        minutes = String.valueOf(localTime.getMinuteOfHour());
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

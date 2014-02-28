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
package org.kuali.coeus.common.committee.impl.web.struts.form.schedule;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Time12HrFmt implements Serializable {

    private static final long serialVersionUID = -5569353959041715547L;
    
    public static final String COLON = ":";
    
    public static final String ZERO = "0";
    
    public enum MERIDIEM {AM, PM};
    
    private String time;
    
    private String meridiem;
    
    public Time12HrFmt(Timestamp day) {                
        parseTimeTo12HrFmt(day);        
    }
    
    public Time12HrFmt(String time, MERIDIEM meridiem) {
        this.time = time;
        this.meridiem = meridiem.toString();
    }
    
    public String getTime() {
        return time;
    }

    public void setTime(String time) {     
        this.time = time; 
    }

    public String getMeridiem() {
        return meridiem;
    }

    public void setMeridiem(String meridiem) {
        this.meridiem = meridiem;
    }
    
    private void parseTimeTo12HrFmt(Timestamp day) {
        Calendar cl = new GregorianCalendar();
        cl.setTime(day);
        
        if(cl.get(Calendar.AM_PM) == 0) 
            this.meridiem = MERIDIEM.AM.name();
        else
            this.meridiem = MERIDIEM.PM.name();
        
        int hr = cl.get(Calendar.HOUR_OF_DAY);
        
        hr = (hr==0?12:hr);        
        hr = (hr>12?hr-12:hr);
        
        int min = cl.get(Calendar.MINUTE);
        
        String str = buildDisplayView(hr, min);
        
        this.time = str;
    }
    
    private String buildDisplayView(int hrs, int mins) {
        StringBuilder str = new StringBuilder();
        
        if (hrs < 10) 
            str.append(ZERO).append(hrs);
        else
            str.append(hrs);
       
        str.append(COLON);
        
        if(mins < 10) 
            str.append(ZERO).append(mins);
        else
            str.append(mins);
        
        return str.toString();
    }
    
    public int findMinutes() {        
        
        String[] result = time.split(COLON);
                
        int hrs = new Integer(result[0]);
        int min = new Integer(result[1]);
                
        boolean am_pm = false;
        if(meridiem.equalsIgnoreCase(MERIDIEM.AM.toString()))
            am_pm = true;          
        
        int mins = 0;
        if(hrs == 12) 
            mins = 0 + min + (am_pm?0:12*60); 
        else
            mins = (hrs * 60)  + min + (am_pm?0:12*60);

        return mins;
    }
    
    @Override
    public String toString() {
    	return time;
    }
}

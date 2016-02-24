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
        if(day != null) {
        cl.setTime(day);
        }
        
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

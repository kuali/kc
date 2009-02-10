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
package org.kuali.kra.committee.web.struts.form;

import java.text.ParseException;
import java.util.List;

import org.apache.struts.util.LabelValueBean;

public class ScheduleOptionsUtil {
    
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ScheduleOptionsUtil.class);
    
    public static final String[] mthsweek = {"first", "second", "third", "fourth", "last"};
    
    public static final String[] dyofweek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Staurday" };
    
    public static final String[] mths = {"JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"};
    
    public static final String[] time = {"select", "12:00 AM", "12:30 AM", "1:00 AM", "1:30 AM", "2:00 AM", "2:30 AM", "3:00 AM", "3:30 AM", 
                                        "4:00 AM", "4:30 AM", "5:00 AM", "5:30 AM", "6:00 AM", "6:30 AM", "7:00 AM", "8:00 AM", "8:30 AM", 
                                        "9:00 AM", "9:30 AM", "10:00 AM", "10:30 AM", "11:00 AM", "11:30 AM", 
                                        "12:00 PM", "12:30 PM", "1:00 PM", "1:30 PM", "2:00 PM", "2:30 PM", "3:00 PM", "3:30 PM", 
                                        "4:00 PM", "4:30 PM", "5:00 PM", "5:30 PM", "6:00 PM", "6:30 PM", "7:00 PM", "8:00 PM", "8:30 PM", 
                                        "9:00 PM", "9:30 PM", "10:00 PM", "10:30 PM","11:00 PM", "11:30 PM",};
    
    public static void populate(List<LabelValueBean> list, String [] values) {        
        for(String value: values) {
            list.add(new LabelValueBean(value, value));
        }
    }
    
    public static int findMinutes(String time, String meridiem) {        
        
        String[] result = time.split(":");
        int hrs = new Integer(result[0]);
        int min = new Integer(result[1]);
                
        boolean am_pm = false;
        if(meridiem.equalsIgnoreCase("AM"))
            am_pm = true;
          
        LOG.info("DATE hrs:" + hrs + " :min: " + min + " :AM: " + am_pm);
        
        if(hrs == 12) {
            return 0 + min + (am_pm?0:12*60); 
        }
        return (hrs * 60)  + min + (am_pm?0:12*60);
    }

    public static String convert24HourFmt(String time, String meridiem) {        
        
        String[] result = time.split(":");
        int hrs = new Integer(result[0]);
        if(meridiem.equalsIgnoreCase("PM") && hrs != 12) {
            hrs = hrs + 12;
            result[0] = new Integer(hrs).toString();
        }
        StringBuilder sb = new StringBuilder();
        return sb.append(result[0]).append(":").append(result[1]).toString();
    }
}

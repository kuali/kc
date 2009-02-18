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
package org.kuali.kra.committee.web.struts.form.schedule.util;

import java.util.List;

import org.apache.struts.util.LabelValueBean;
import org.kuali.kra.committee.web.struts.form.schedule.DayOfWeek;
import org.kuali.kra.committee.web.struts.form.schedule.Months;
import org.kuali.kra.committee.web.struts.form.schedule.WeekOfMonth;
import org.kuali.kra.scheduling.expr.CronSpecialChars;

public class ScheduleOptionsUtil {

    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ScheduleOptionsUtil.class);
    
    public static void populate(List<LabelValueBean> list, String [] values) {        
        for(String value: values) {
            list.add(new LabelValueBean(value, value));
        }
    }

    public static CronSpecialChars getMonthOfWeek(String month) {
        String abbr = Months.valueOf(month).getAbbr();        
        return CronSpecialChars.valueOf(abbr);
    }
    
    public static CronSpecialChars getDayOfWeek(String dayOfWeek) {
        String abbr = DayOfWeek.valueOf(dayOfWeek).getAbbr();
        return CronSpecialChars.valueOf(abbr);
    } 
    
    public static CronSpecialChars getWeekOfMonth(String monthsWeek) {
        String number = WeekOfMonth.valueOf(monthsWeek).getNumber();
        return CronSpecialChars.valueOf(number);
    }
    
    public static CronSpecialChars[] convertToWeekdays(String [] daysOfWeek){        
        CronSpecialChars [] weekdays = new CronSpecialChars[daysOfWeek.length];
        int i = 0;
        for(String str: daysOfWeek) {
            if(null != str){
                String abbr = DayOfWeek.valueOf(str).getAbbr();
                weekdays[i++] = CronSpecialChars.valueOf(abbr);
            }
        }
        return weekdays;
    }
}

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
package org.kuali.coeus.common.committee.impl.web.struts.form.schedule.util;

import org.kuali.coeus.common.committee.impl.web.struts.form.schedule.DayOfWeek;
import org.kuali.coeus.common.committee.impl.web.struts.form.schedule.Months;
import org.kuali.coeus.common.committee.impl.web.struts.form.schedule.WeekOfMonth;
import org.kuali.coeus.sys.framework.scheduling.util.CronSpecialChars;

public class ScheduleOptionsUtil {

    @SuppressWarnings("unused")
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ScheduleOptionsUtil.class);

    /**
     * This method returns CronSpecialChars of a month.
     * @param month
     * @return
     */
    public static CronSpecialChars getMonthOfWeek(String month) {
        String abbr = Months.valueOf(month).getAbbr();        
        return CronSpecialChars.valueOf(abbr);
    }
    
    /**
     * This method returns CronSpecialChars of a day of week.
     * @param dayOfWeek
     * @return
     */
    public static CronSpecialChars getDayOfWeek(String dayOfWeek) {
        String abbr = DayOfWeek.valueOf(dayOfWeek).getAbbr();
        return CronSpecialChars.valueOf(abbr);
    } 
    
    /**
     * This method returns CronSpecialChars of month of week.
     * @param monthsWeek
     * @return
     */
    public static CronSpecialChars getWeekOfMonth(String monthsWeek) {
        String number = WeekOfMonth.valueOf(monthsWeek).getNumber();
        return CronSpecialChars.valueOf(number);
    }
    
    /**
     * This method returns CronSpecialChars[] of days of week.
     * @param daysOfWeek
     * @return
     */
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

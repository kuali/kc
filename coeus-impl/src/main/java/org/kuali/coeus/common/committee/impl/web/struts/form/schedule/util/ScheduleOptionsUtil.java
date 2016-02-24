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

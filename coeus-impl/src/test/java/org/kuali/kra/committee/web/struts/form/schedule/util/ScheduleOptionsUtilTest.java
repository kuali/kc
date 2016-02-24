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
package org.kuali.kra.committee.web.struts.form.schedule.util;

import org.junit.Test;
import org.kuali.coeus.common.committee.impl.web.struts.form.schedule.util.ScheduleOptionsUtil;
import org.kuali.coeus.sys.framework.scheduling.util.CronSpecialChars;

import static org.junit.Assert.assertEquals;


public class ScheduleOptionsUtilTest {
    
    /**
     * This method test's MonthOfWeek method.
     * @throws Exception
     */
    @Test
    public void testMonthOfWeek() throws Exception {
        
        CronSpecialChars spChar1 = ScheduleOptionsUtil.getMonthOfWeek("MARCH");
        CronSpecialChars spChar2 = ScheduleOptionsUtil.getMonthOfWeek("AUGUST");
        
        assertEquals(CronSpecialChars.MAR,spChar1);  
        assertEquals(CronSpecialChars.AUG,spChar2);
    }
    
    /**
     * This method test's DayOfWeek method.
     * @throws Exception
     */
    @Test
    public void testDayOfWeek() throws Exception {
        CronSpecialChars spChar1 = ScheduleOptionsUtil.getDayOfWeek("Monday");
        CronSpecialChars spChar2 = ScheduleOptionsUtil.getDayOfWeek("Thursday");
        
        assertEquals(CronSpecialChars.MON,spChar1);
        assertEquals(CronSpecialChars.THU,spChar2);
    }
    
    /**
     * This method test's WeekOfMonth method.
     * @throws Exception
     */
    @Test
    public void testWeekOfMonth() throws Exception {
        CronSpecialChars spChar1 = ScheduleOptionsUtil.getWeekOfMonth("second");
        CronSpecialChars spChar2 = ScheduleOptionsUtil.getWeekOfMonth("last");
        
        assertEquals(CronSpecialChars.SECOND,spChar1);
        assertEquals(CronSpecialChars.LAST,spChar2);        
    }
    
    /**
     * This method test's ConvertToWeekDays method.
     * @throws Exception
     */
    @Test
    public void testConvertToWeekdays() throws Exception {
        String [] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday"};
        CronSpecialChars[] weekdays= ScheduleOptionsUtil.convertToWeekdays(daysOfWeek);
        
        assertEquals(CronSpecialChars.SUN,weekdays[0]);
        assertEquals(CronSpecialChars.MON,weekdays[1]);  
        assertEquals(CronSpecialChars.TUE,weekdays[2]);
        assertEquals(CronSpecialChars.WED,weekdays[3]);  
    }
}

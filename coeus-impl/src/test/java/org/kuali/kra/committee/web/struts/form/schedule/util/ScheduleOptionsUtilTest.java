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

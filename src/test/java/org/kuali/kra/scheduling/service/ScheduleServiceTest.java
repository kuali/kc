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
package org.kuali.kra.scheduling.service;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.kuali.kra.scheduling.expr.util.CronSpecialChars;
import org.kuali.kra.scheduling.service.impl.ScheduleServiceImpl;
import org.kuali.kra.scheduling.util.Time24HrFmt;

public class ScheduleServiceTest {
    
    private static final String time_23_59 = "23:59";
    
    @Test
    public void testNeverExpression() throws Exception {
        ScheduleServiceImpl service = new ScheduleServiceImpl();
        Date stDate = new Date();
        Date endDate = new Date();
        List<Date> list = service.getScheduledDates(stDate, endDate, new Time24HrFmt(time_23_59), null);
        assertEquals(1, list.size());
    }
    
    @Test
    public void testDayExpression() throws Exception {
        ScheduleServiceImpl service = new ScheduleServiceImpl();
        Date stDate = new Date();
        Date endDate = DateUtils.addDays(stDate, 2);
        
        List<Date> list = service.getScheduledDates(stDate, endDate, new Time24HrFmt(time_23_59), 1, null);
        assertEquals(3, list.size());
        
        list = service.getScheduledDates(stDate, endDate, new Time24HrFmt(time_23_59), 3, null);
        assertEquals(1, list.size());
    }
    
    @Test
    public void testWeekExpression() throws Exception {
        ScheduleServiceImpl service = new ScheduleServiceImpl();
        Date stDate = new Date();
        Date endDate = DateUtils.addDays(stDate, 6);
        CronSpecialChars [] chars = new CronSpecialChars[1];
        chars[0] = findDayOfWeek(stDate);
        
        List<Date> list = service.getScheduledDates(stDate, endDate, new Time24HrFmt(time_23_59), chars, null);
        assertEquals(1, list.size());
        
        endDate = DateUtils.addDays(stDate, 7);
        list = service.getScheduledDates(stDate, endDate, new Time24HrFmt(time_23_59), chars, null);
        assertEquals(2, list.size());
    }

    @Test
    public void testMonthDayExpression() throws Exception {
        ScheduleServiceImpl service = new ScheduleServiceImpl();
        Date stDate = new Date();
        Date endDate = DateUtils.addDays(stDate, 60);
        Calendar cl = new GregorianCalendar();
        cl.setTime(stDate);
        int day = cl.get(Calendar.DATE);
        
        List<Date> list = service.getScheduledDates(stDate, endDate, new Time24HrFmt(time_23_59), day, 1, null);
        assertEquals(2, list.size());
        
        list = service.getScheduledDates(stDate, endDate, new Time24HrFmt(time_23_59), day, 2, null);
        assertEquals(1, list.size());
    }

    @Test
    public void testMonthlyWeekDayExpression() throws Exception {
        ScheduleServiceImpl service = new ScheduleServiceImpl();
        Date stDate = new Date();
        Date endDate = DateUtils.addDays(stDate, 60);        
        
        CronSpecialChars dayOfWeek = findDayOfWeek(stDate);
        CronSpecialChars weekOfMonth = findWeekOfMonth(stDate);
        
        List<Date> list = service.getScheduledDates(stDate, endDate, new Time24HrFmt(time_23_59), dayOfWeek, weekOfMonth, 1, null);
        assertEquals(2, list.size());
        
        list = service.getScheduledDates(stDate, endDate, new Time24HrFmt(time_23_59), dayOfWeek, weekOfMonth, 2, null);
        assertEquals(1, list.size());
    }
    
    @Test
    public void testYearMonthDayExpression() throws Exception {
        ScheduleServiceImpl service = new ScheduleServiceImpl();
        Date stDate = new Date();
        Date endDate = DateUtils.addDays(stDate, 400);        
        Calendar cl = new GregorianCalendar();
        cl.setTime(stDate);
        int day = cl.get(Calendar.DATE);
        
        CronSpecialChars  month = findMonth(stDate);
        
        List<Date> list = service.getScheduledDates(stDate, endDate, new Time24HrFmt(time_23_59), month, day, 1, null);
        assertEquals(2, list.size());
        
        list = service.getScheduledDates(stDate, endDate, new Time24HrFmt(time_23_59), month, day, 2, null);
        assertEquals(1, list.size());
    }
    
    @Test
    public void testYearMonthDayOfWeekExpression() throws Exception {
        ScheduleServiceImpl service = new ScheduleServiceImpl();
        Date stDate = new Date();
        Date endDate = DateUtils.addDays(stDate, 400);        
        
        CronSpecialChars weekOfMonth = findWeekOfMonth(stDate);
        CronSpecialChars  dayOfWeek = findDayOfWeek(stDate);
        CronSpecialChars  month = findMonth(stDate);
        
        List<Date> list = service.getScheduledDates(stDate, endDate, new Time24HrFmt(time_23_59), weekOfMonth, dayOfWeek, month, 1, null);
        assertEquals(2, list.size());
        
        list = service.getScheduledDates(stDate, endDate, new Time24HrFmt(time_23_59), weekOfMonth, dayOfWeek, month, 2, null);
        assertEquals(1, list.size());  
    }
    
    /**
     * This method finds week of month.
     * @param date
     * @return
     */
    private CronSpecialChars findWeekOfMonth(Date date) {
        Calendar cl = new GregorianCalendar();
        cl.setTime(date);
        CronSpecialChars weekOfMonth = null;
        
        switch(cl.get(Calendar.WEEK_OF_MONTH)) {
            case 1:
                weekOfMonth = CronSpecialChars.FIRST;
                break;
            case 2:
                weekOfMonth = CronSpecialChars.SECOND;
                break;
            case 3:
                weekOfMonth = CronSpecialChars.THIRD;
                break;
            case 4:
                weekOfMonth = CronSpecialChars.FOURTH;
                break;
            case 5:
                weekOfMonth = CronSpecialChars.FIFTH;
                break;
        }
        return weekOfMonth;
    }
    
    /**
     * This method finds week's day of date.
     * @param date
     * @return
     */
    private CronSpecialChars findDayOfWeek(Date date) {
        Calendar cl = new GregorianCalendar();
        cl.setTime(date);
        CronSpecialChars dayOfWeek = null;        
        switch (cl.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SUNDAY:
                dayOfWeek = CronSpecialChars.SUN;
                break;
            case Calendar.MONDAY:
                dayOfWeek = CronSpecialChars.MON;
                break;
            case Calendar.TUESDAY:
                dayOfWeek = CronSpecialChars.TUE;
                break;
            case Calendar.WEDNESDAY:
                dayOfWeek = CronSpecialChars.WED;
                break;
            case Calendar.THURSDAY:
                dayOfWeek = CronSpecialChars.THU;
                break;
            case Calendar.FRIDAY:
                dayOfWeek = CronSpecialChars.FRI;
                break;
            case Calendar.SATURDAY:
                dayOfWeek = CronSpecialChars.SAT;
                break;
        }
        return dayOfWeek;
    }
    
    /**
     * This method finds month of year for date.
     * @param date
     * @return
     */
    private CronSpecialChars findMonth(Date date) {
        Calendar cl = new GregorianCalendar();
        cl.setTime(date);
        CronSpecialChars month = null;
        switch (cl.get(Calendar.MONTH)) {
            case 0:
                month = CronSpecialChars.JAN;
                break;
            case 1:
                month = CronSpecialChars.FEB;
                break;
            case 2:
                month = CronSpecialChars.MAR;
                break;
            case 3:
                month = CronSpecialChars.APR;
                break;    
            case 4:
                month = CronSpecialChars.MAY;
                break; 
            case 5:
                month = CronSpecialChars.JUN;
                break;
            case 6:
                month = CronSpecialChars.JUL;
                break;
            case 7:
                month = CronSpecialChars.AUG;
                break;
            case 8:
                month = CronSpecialChars.SEP;
                break;
            case 9:
                month = CronSpecialChars.OCT;
                break;    
            case 10:
                month = CronSpecialChars.NOV;
                break;
            case 11:
                month = CronSpecialChars.DEC;
                break;
        }        
        return month;
    }
}

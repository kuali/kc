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
package org.kuali.kra.committee.web;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.time.DateUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class CommitteeScheduleWebAddSchedulePanelTest extends CommitteeScheduleWebTestBase {
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }
    
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    @Test
    public void testCommitteeScheduleNeverRecurrance() throws Exception {
        
        HtmlPage schedulePage =  prerequisite();        
        Date dt = new Date();        
        String scheduleDate = formatDate(dt);        
        setFields(schedulePage, scheduleDate);
        
        HtmlPage pageAfterAdd = clickOnByName(schedulePage,"methodToCall.addEvent.anchor", true);
        
        assertFalse(hasError(pageAfterAdd));        
        assertRecord(pageAfterAdd, dt);  
    }

    @Test
    public void testCommitteeScheduleDailyRecurranceOption1() throws Exception {
        
        HtmlPage schedulePage =  prerequisite();       
        Date dt = new Date();       
        String scheduleDate = formatDate(dt);      
        setFields(schedulePage, scheduleDate);    
        String endDate = formatDate(DateUtils.addDays(new Date(), 3));     
        setFieldValue(schedulePage, "scheduleData.recurrenceType", "DAILY");      
        setFieldValue(schedulePage, "scheduleData.dailySchedule.scheduleEndDate", endDate);
        
        HtmlPage pageAfterAdd = clickOnByName(schedulePage,"methodToCall.addEvent.anchor", true);
        
        assertFalse(hasError(pageAfterAdd));      
        assertRecord(pageAfterAdd, dt);    
        assertRecord(pageAfterAdd, DateUtils.addDays(dt, 1));     
        assertRecord(pageAfterAdd, DateUtils.addDays(dt, 2));
    }

    @Test
    public void testCommitteeScheduleDailyRecurranceOption2() throws Exception {
        
        HtmlPage schedulePage =  prerequisite();  
        Date dt = new Date();
        String scheduleDate = formatDate(dt);
        setFields(schedulePage, scheduleDate);
        String endDate = formatDate(DateUtils.addDays(new Date(), 3));
        setFieldValue(schedulePage, "scheduleData.recurrenceType", "DAILY");
        setFieldValue(schedulePage, "scheduleData.dailySchedule.dayOption", "XDAY");
        setFieldValue(schedulePage, "scheduleData.dailySchedule.scheduleEndDate", endDate);
        
        HtmlPage pageAfterAdd = clickOnByName(schedulePage,"methodToCall.addEvent.anchor", true);
        
        assertFalse(hasError(pageAfterAdd));
        assertRecord(pageAfterAdd, dt);
        assertRecord(pageAfterAdd, DateUtils.addDays(dt, 1));
        assertRecord(pageAfterAdd, DateUtils.addDays(dt, 2));
    }
    
    @Test
    public void testCommitteeScheduleWeeklyRecurrance() throws Exception {
        
        HtmlPage schedulePage =  prerequisite(); 
        Date dt = new Date();
        String scheduleDate = formatDate(dt);
        setFields(schedulePage, scheduleDate);
        String endDate = formatDate(DateUtils.addDays(new Date(), 7));
        setFieldValue(schedulePage, "scheduleData.recurrenceType", "WEEKLY");
        setFieldValue(schedulePage, "scheduleData.weeklySchedule.scheduleEndDate", endDate);
        
        HtmlPage pageAfterAdd = clickOnByName(schedulePage,"methodToCall.addEvent.anchor", true);
        
        assertFalse(hasError(pageAfterAdd));

        Date monday = null;
        for(int i=0;i<7; i++) {
            monday = DateUtils.addDays(new Date(), i);
            if (isMonday(monday))
                break;
        }
        
        assertRecord(pageAfterAdd, monday);

    }
    
    @Test
    public void testCommitteeScheduleMonthlyRecurranceOption1() throws Exception {
        
        HtmlPage schedulePage =  prerequisite();   
        Date dt = new Date();        
        String scheduleDate = formatDate(dt);        
        setFields(schedulePage, scheduleDate);       
        String endDate = formatDate(DateUtils.addDays(new Date(), 7));      
        setFieldValue(schedulePage, "scheduleData.recurrenceType", "MONTHLY");
        
        Calendar cl = new GregorianCalendar();
        cl.setTime(dt);
        Integer date = cl.get(Calendar.DATE);        
        
        setFieldValue(schedulePage, "scheduleData.monthlySchedule.day", date.toString());       
        setFieldValue(schedulePage, "scheduleData.monthlySchedule.scheduleEndDate", endDate);
        
        HtmlPage pageAfterAdd = clickOnByName(schedulePage,"methodToCall.addEvent.anchor", true);
        
        assertFalse(hasError(pageAfterAdd));        
        assertRecord(pageAfterAdd, dt);

    }

    @Test
    public void testCommitteeScheduleMonthlyRecurranceOption2() throws Exception {
        
        HtmlPage schedulePage =  prerequisite();        
        Date dt = new Date();
        
        Calendar cl = new GregorianCalendar();
        cl.setTime(dt);
        Integer date = cl.get(Calendar.DATE);  
        
        dt = DateUtils.addDays(new Date(), -date);        
        String scheduleDate = formatDate(dt);        
        setFields(schedulePage, scheduleDate);      
        String endDate = formatDate(DateUtils.addDays(dt, 10));       
        setFieldValue(schedulePage, "scheduleData.recurrenceType", "MONTHLY");       
        setFieldValue(schedulePage, "scheduleData.monthlySchedule.monthOption", "XDAYOFWEEKANDXMONTH");             
        setFieldValue(schedulePage, "scheduleData.monthlySchedule.scheduleEndDate", endDate);
        
        HtmlPage pageAfterAdd = clickOnByName(schedulePage,"methodToCall.addEvent.anchor", true);
        
        assertFalse(hasError(pageAfterAdd));  
        
        Date monday = dt;
        for(int i=0;i<date+10; i++) {
            monday = DateUtils.addDays(dt, i);
            if (isMonday(monday))
                break;
        }
        
        assertRecord(pageAfterAdd, monday);

    }

    @Test
    public void testCommitteeScheduleYearlyRecurranceOption1() throws Exception {
        
        HtmlPage schedulePage =  prerequisite();        
        Date dt = new Date();
        
        Calendar cl = new GregorianCalendar();
        cl.setTime(dt);
        Integer date = cl.get(Calendar.DATE);  
        
        String month = findMonth(dt);       
        String scheduleDate = formatDate(dt);        
        setFields(schedulePage, scheduleDate);      
        String endDate = formatDate(DateUtils.addDays(dt, 10));    
        setFieldValue(schedulePage, "scheduleData.recurrenceType", "YEARLY");       
        setFieldValue(schedulePage, "scheduleData.yearlySchedule.yearOption", "XDAY");              
        setFieldValue(schedulePage, "scheduleData.yearlySchedule.selectedOption1Month", month);  
        setFieldValue(schedulePage, "scheduleData.yearlySchedule.day", date.toString());        
        setFieldValue(schedulePage, "scheduleData.yearlySchedule.scheduleEndDate", endDate);
        
        HtmlPage pageAfterAdd = clickOnByName(schedulePage,"methodToCall.addEvent.anchor", true);
        
        assertFalse(hasError(pageAfterAdd));             
        assertRecord(pageAfterAdd, dt);

    }
    
    @Test
    public void testCommitteeScheduleYearlyRecurranceOption2() throws Exception {
        
        HtmlPage schedulePage =  prerequisite();        
        Date dt = new Date();
        
        Calendar cl = new GregorianCalendar();
        cl.setTime(dt);
        Integer date = cl.get(Calendar.DATE);  
        
        String month = findMonth(dt);       
        dt = DateUtils.addDays(dt, -date);       
        String scheduleDate = formatDate(dt);       
        setFields(schedulePage, scheduleDate);       
        String endDate = formatDate(DateUtils.addDays(dt, 10));       
        setFieldValue(schedulePage, "scheduleData.recurrenceType", "YEARLY");        
        setFieldValue(schedulePage, "scheduleData.yearlySchedule.yearOption", "CMPLX");                 
        setFieldValue(schedulePage, "scheduleData.yearlySchedule.selectedOption2Month", month);      
        setFieldValue(schedulePage, "scheduleData.yearlySchedule.scheduleEndDate", endDate);
        
        HtmlPage pageAfterAdd = clickOnByName(schedulePage,"methodToCall.addEvent.anchor", true);
        
        assertFalse(hasError(pageAfterAdd));     
        
        Date monday = dt;       
        for(int i=0;i<10; i++) {
            monday = DateUtils.addDays(dt, i);
            if (isMonday(monday))
                break;
        }
        
        assertRecord(pageAfterAdd, monday);

    }

    @Test
    public void testCommitteeScheduleReload() throws Exception {
        
        HtmlPage schedulePage =  prerequisite();        
        Date dt = new Date();        
        String scheduleDate = formatDate(dt);        
        setFields(schedulePage, scheduleDate);
        setFieldValue(schedulePage, "scheduleData.recurrenceType", "YEARLY");  
        
        HtmlPage pageAfterAdd1 = clickOnByName(schedulePage,"methodToCall.loadRecurrence.anchorSchedule", true);
        assertFalse(hasError(pageAfterAdd1));
        
        setFieldValue(schedulePage, "scheduleData.recurrenceType", "MONTHLY");
        HtmlPage pageAfterAdd2 = clickOnByName(schedulePage,"methodToCall.loadRecurrence.anchorSchedule", true);
        assertFalse(hasError(pageAfterAdd2));          
    }

}

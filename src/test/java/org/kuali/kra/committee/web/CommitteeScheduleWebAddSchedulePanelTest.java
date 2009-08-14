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
    
    public static final String METHODTOCALL_ADDEVENT_ANCHOR = "methodToCall.addEvent.anchor";
    
    public static final String SCHEDULEDATA_RECURRENCECTYPE = "committeeScheduleHelper.scheduleData.recurrenceType";
    
    public static final String SCHEDULEDATA_DAILYSCHEDULE_SCHEDULEENDDATE = "committeeScheduleHelper.scheduleData.dailySchedule.scheduleEndDate";
    
    public static final String DAILY = "DAILY";
    
    public static final String SCHEDULEDATA_DAILYSCHEDULE_DAYOPTION = "committeeScheduleHelper.scheduleData.dailySchedule.dayOption";
    
    public static final String XDAY = "XDAY";
    
    public static final String WEEKLY = "WEEKLY";
    
    public static final String  SCHEDULEDATA_WEEKLYSCHEDULE_SCHEDULEENDDATE = "committeeScheduleHelper.scheduleData.weeklySchedule.scheduleEndDate";
    
    public static final String MONTHLY = "MONTHLY";
    
    public static final String SCHEDULEDATE_MONTHLYSCHEDULE_DAY = "committeeScheduleHelper.scheduleData.monthlySchedule.day";
    
    public static final String SCHEDULEDATA_MONTHLYSCHEDULE_SHCEDULEENDDATE = "committeeScheduleHelper.scheduleData.monthlySchedule.scheduleEndDate";
    
    public static final String SCHEDULEDATA_MONTHLYSCHEDULE_MONTHOPTION = "committeeScheduleHelper.scheduleData.monthlySchedule.monthOption";
    
    public static final String XDAYOFWEEKANDXMONTH = "XDAYOFWEEKANDXMONTH";
    
    public static final String YEARLY = "YEARLY";
    
    public static final String SCHEDULEDATA_YEARLYSCHEDULE_YEAROPTION = "committeeScheduleHelper.scheduleData.yearlySchedule.yearOption";
    
    public static final String SCHEDULEDATA_YEARLYSCHEDULE_SELECCTEDOPTION1MONTH = "committeeScheduleHelper.scheduleData.yearlySchedule.selectedOption1Month";
    
    public static final String SCHEDULEDATA_YEARLYSCHEDULE_SELECCTEDOPTION2MONTH = "committeeScheduleHelper.scheduleData.yearlySchedule.selectedOption2Month";
    
    public static final String SCHEDULEDATA_YEARLYSCHEDULE_DAY = "committeeScheduleHelper.scheduleData.yearlySchedule.day";
    
    public static final String SCHEDULEDATA_YEARLYSCHEDULE_SCHEDULEENDDATE = "committeeScheduleHelper.scheduleData.yearlySchedule.scheduleEndDate";
    
    public static final String CMPLX = "CMPLX";
    
    public static final String METHODTOCALL_LOADRECURRENCE_ANCHORSCHEDULE = "methodToCall.loadRecurrence.anchorSchedule"; 
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }
    
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    /**
     * This method test Never recurrence.
     * @throws Exception
     */
    @Test
    public void testCommitteeScheduleNeverRecurrance() throws Exception {
        
        HtmlPage schedulePage =  prerequisite();        
        Date dt = new Date();        
        String scheduleDate = formatDate(dt);        
        setFields(schedulePage, scheduleDate);
        
        HtmlPage pageAfterAdd = clickOnByName(schedulePage,METHODTOCALL_ADDEVENT_ANCHOR, true);
        
        assertFalse(hasError(pageAfterAdd));        
        assertRecord(pageAfterAdd, dt);  
    }

    /**
     * This method test's Daily recurrence option1.
     * @throws Exception
     */
    @Test
    public void testCommitteeScheduleDailyRecurranceOption1() throws Exception {
        
        HtmlPage schedulePage =  prerequisite();       
        Date dt = new Date();       
        String scheduleDate = formatDate(dt);      
        setFields(schedulePage, scheduleDate);    
        String endDate = formatDate(DateUtils.addDays(new Date(), 3));     
        setFieldValue(schedulePage, SCHEDULEDATA_RECURRENCECTYPE, DAILY);      
        setFieldValue(schedulePage, SCHEDULEDATA_DAILYSCHEDULE_SCHEDULEENDDATE, endDate);
        
        HtmlPage pageAfterAdd = clickOnByName(schedulePage,METHODTOCALL_ADDEVENT_ANCHOR, true);
        
        assertFalse(hasError(pageAfterAdd));      
        assertRecord(pageAfterAdd, dt);    
        assertRecord(pageAfterAdd, DateUtils.addDays(dt, 1));     
        assertRecord(pageAfterAdd, DateUtils.addDays(dt, 2));
    }

    /**
     * This method test Daily recurrence option 2.
     * @throws Exception
     */
    @Test
    public void testCommitteeScheduleDailyRecurranceOption2() throws Exception {
        
        HtmlPage schedulePage =  prerequisite();  
        Date dt = new Date();
        String scheduleDate = formatDate(dt);
        setFields(schedulePage, scheduleDate);
        String endDate = formatDate(DateUtils.addDays(new Date(), 3));
        setFieldValue(schedulePage, SCHEDULEDATA_RECURRENCECTYPE, DAILY);
        setFieldValue(schedulePage, SCHEDULEDATA_DAILYSCHEDULE_DAYOPTION, XDAY);
        setFieldValue(schedulePage, SCHEDULEDATA_DAILYSCHEDULE_SCHEDULEENDDATE, endDate);
        
        HtmlPage pageAfterAdd = clickOnByName(schedulePage,METHODTOCALL_ADDEVENT_ANCHOR, true);
        
        assertFalse(hasError(pageAfterAdd));
        assertRecord(pageAfterAdd, dt);
        assertRecord(pageAfterAdd, DateUtils.addDays(dt, 1));
        assertRecord(pageAfterAdd, DateUtils.addDays(dt, 2));
    }
    
    /**
     * This method test's Weekly recurrence.
     * @throws Exception
     */
    @Test
    public void testCommitteeScheduleWeeklyRecurrance() throws Exception {
        
        HtmlPage schedulePage =  prerequisite(); 
        Date dt = new Date();
        String scheduleDate = formatDate(dt);
        setFields(schedulePage, scheduleDate);
        String endDate = formatDate(DateUtils.addDays(new Date(), 7));
        setFieldValue(schedulePage, SCHEDULEDATA_RECURRENCECTYPE, WEEKLY);
        setFieldValue(schedulePage, SCHEDULEDATA_WEEKLYSCHEDULE_SCHEDULEENDDATE, endDate);
        
        HtmlPage pageAfterAdd = clickOnByName(schedulePage,METHODTOCALL_ADDEVENT_ANCHOR, true);
        
        assertFalse(hasError(pageAfterAdd));

        Date monday = null;
        for(int i=0;i<7; i++) {
            monday = DateUtils.addDays(new Date(), i);
            if (isMonday(monday))
                break;
        }
        
        assertRecord(pageAfterAdd, monday);

    }
    
    /**
     * This method test's Monthly recurrence option 1.
     * @throws Exception
     */
    @Test
    public void testCommitteeScheduleMonthlyRecurranceOption1() throws Exception {
        
        HtmlPage schedulePage =  prerequisite();   
        Date dt = new Date();        
        String scheduleDate = formatDate(dt);        
        setFields(schedulePage, scheduleDate);       
        String endDate = formatDate(DateUtils.addDays(new Date(), 7));      
        setFieldValue(schedulePage, SCHEDULEDATA_RECURRENCECTYPE, MONTHLY);
        
        Calendar cl = new GregorianCalendar();
        cl.setTime(dt);
        Integer date = cl.get(Calendar.DATE);        
        
        setFieldValue(schedulePage, SCHEDULEDATE_MONTHLYSCHEDULE_DAY, date.toString());       
        setFieldValue(schedulePage, SCHEDULEDATA_MONTHLYSCHEDULE_SHCEDULEENDDATE, endDate);
        
        HtmlPage pageAfterAdd = clickOnByName(schedulePage,METHODTOCALL_ADDEVENT_ANCHOR, true);
        
        assertFalse(hasError(pageAfterAdd));        
        assertRecord(pageAfterAdd, dt);

    }

    /**
     * This method test's Monthly recurrence option 2.
     * @throws Exception
     */
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
        setFieldValue(schedulePage, SCHEDULEDATA_RECURRENCECTYPE, MONTHLY);       
        setFieldValue(schedulePage, SCHEDULEDATA_MONTHLYSCHEDULE_MONTHOPTION, XDAYOFWEEKANDXMONTH);             
        setFieldValue(schedulePage, SCHEDULEDATA_MONTHLYSCHEDULE_SHCEDULEENDDATE, endDate);
        
        HtmlPage pageAfterAdd = clickOnByName(schedulePage,METHODTOCALL_ADDEVENT_ANCHOR, true);
        
        assertFalse(hasError(pageAfterAdd));  
        
        Date monday = dt;
        for(int i=0;i<date+10; i++) {
            monday = DateUtils.addDays(dt, i);
            if (isMonday(monday))
                break;
        }
        
        assertRecord(pageAfterAdd, monday);

    }

    /**
     * This method test Yearly recurrence option 1.
     * @throws Exception
     */
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
        setFieldValue(schedulePage, SCHEDULEDATA_RECURRENCECTYPE, YEARLY);       
        setFieldValue(schedulePage, SCHEDULEDATA_YEARLYSCHEDULE_YEAROPTION, XDAY);              
        setFieldValue(schedulePage, SCHEDULEDATA_YEARLYSCHEDULE_SELECCTEDOPTION1MONTH, month);  
        setFieldValue(schedulePage, SCHEDULEDATA_YEARLYSCHEDULE_DAY, date.toString());        
        setFieldValue(schedulePage, SCHEDULEDATA_YEARLYSCHEDULE_SCHEDULEENDDATE, endDate);
        
        HtmlPage pageAfterAdd = clickOnByName(schedulePage,METHODTOCALL_ADDEVENT_ANCHOR, true);
        
        assertFalse(hasError(pageAfterAdd));             
        assertRecord(pageAfterAdd, dt);

    }
    
    /**
     * This method test's Yearly recurrence option 2.
     * @throws Exception
     */
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
        setFieldValue(schedulePage, SCHEDULEDATA_RECURRENCECTYPE, YEARLY);        
        setFieldValue(schedulePage, SCHEDULEDATA_YEARLYSCHEDULE_YEAROPTION, CMPLX);                 
        setFieldValue(schedulePage, SCHEDULEDATA_YEARLYSCHEDULE_SELECCTEDOPTION2MONTH, month);      
        setFieldValue(schedulePage, SCHEDULEDATA_YEARLYSCHEDULE_SCHEDULEENDDATE, endDate);
        
        HtmlPage pageAfterAdd = clickOnByName(schedulePage,METHODTOCALL_ADDEVENT_ANCHOR, true);
        
        assertFalse(hasError(pageAfterAdd));     
        
        Date monday = dt;       
        for(int i=0;i<10; i++) {
            monday = DateUtils.addDays(dt, i);
            if (isMonday(monday))
                break;
        }
        
        assertRecord(pageAfterAdd, monday);

    }
}

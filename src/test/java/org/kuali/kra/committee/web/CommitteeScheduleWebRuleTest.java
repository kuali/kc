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

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class CommitteeScheduleWebRuleTest extends CommitteeScheduleWebTestBase {
    
    public static final String DAILY = "DAILY";
    
    public static final String SCHEDULEDATA_RECURRENCECTYPE = "committeeScheduleHelper.scheduleData.recurrenceType";
    
    public static final String SCHEDULEDATA_DAILYSCHEDULE_SCHEDULEENDDATE = "committeeScheduleHelper.scheduleData.dailySchedule.scheduleEndDate";
    
    public static final String METHODTOCALL_ADDEVENT_ANCHOR = "methodToCall.addEvent.anchor";
    
    public static final String DOCUMENT_COMMITTEE_COMMITTEESCHEDULE_0_SCHEDULEDDATE = "document.committeeList[0].committeeSchedules[0].scheduledDate";
    
    public static final String IS_IN_CONFLICT_WITH_OTHER_MEETING_SCHEDULE = " is in conflict with other meeting schedule";
    
    public static final String DATE_MUST_BE_BEFORE_ENDING_ON_DATE = "End date must be after Start Date.";
    
    public static final String IS_SKIPPED_IN_RECURRENCE = " is skipped in recurrence, meeting already scheduled for the date.";
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }
    
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    /**
     * This method test's date conflict rule in Schedule panel.
     * @throws Exception
     */
    @Test
    public void testSchedulePanelDateConflict() throws Exception {
        
        HtmlPage schedulePage =  prerequisite();        
        Date dt = DateUtils.addDays(new Date(), -1);        
        String scheduleDate = formatDate(dt);        
        setFields(schedulePage, scheduleDate);        
        String endDate = formatDate(DateUtils.addDays(dt, 2));        
        setFieldValue(schedulePage, SCHEDULEDATA_RECURRENCECTYPE, DAILY);       
        setFieldValue(schedulePage, SCHEDULEDATA_DAILYSCHEDULE_SCHEDULEENDDATE, endDate);
        
        HtmlPage pageAfterAdd = clickOnByName(schedulePage,METHODTOCALL_ADDEVENT_ANCHOR, true);
        
        assertFalse(hasError(pageAfterAdd));        
        assertRecord(pageAfterAdd, DateUtils.addDays(dt, 0));        
        assertRecord(pageAfterAdd, DateUtils.addDays(dt, 1));        
        assertRecord(pageAfterAdd, DateUtils.addDays(dt, 2));
        
        Date testDate = DateUtils.addDays(new Date(), 0);        
        String strDate = formatDate(testDate);        
        setFieldValue(pageAfterAdd, DOCUMENT_COMMITTEE_COMMITTEESCHEDULE_0_SCHEDULEDDATE, strDate);              
        
        HtmlPage pageAfterSave = saveDoc(pageAfterAdd);
        
        StringBuilder sb = new StringBuilder();
        java.sql.Date sqlDate = new java.sql.Date(testDate.getTime());
        sb.append(sqlDate.toString()).append(IS_IN_CONFLICT_WITH_OTHER_MEETING_SCHEDULE);
        assertContains(pageAfterSave, sb.toString());
    }     

    /**
     * This method test's start and end date rule.
     * @throws Exception
     */
     @Test
     public void testStartDateEndDateRule() throws Exception {
                 
         HtmlPage schedulePage =  prerequisite();       
         Date dt = new Date();       
         String scheduleDate = formatDate(dt);       
         setFields(schedulePage, scheduleDate);       
         String endDate = scheduleDate;       
         setFieldValue(schedulePage, SCHEDULEDATA_RECURRENCECTYPE, DAILY);      
         setFieldValue(schedulePage, SCHEDULEDATA_DAILYSCHEDULE_SCHEDULEENDDATE, endDate);

         HtmlPage pageAfterAdd = clickOnByName(schedulePage,METHODTOCALL_ADDEVENT_ANCHOR, true);

         assertContains(pageAfterAdd, DATE_MUST_BE_BEFORE_ENDING_ON_DATE);
     }
        
    /**
     * This method test's soft error message during date conflict in Add to Schedule panel.
     * @throws Exception
     */
    @Test
    public void testDateWarnings() throws Exception {
        
        HtmlPage schedulePage =  prerequisite();        
        Date dt = DateUtils.addDays(new Date(), -1);        
        String scheduleDate = formatDate(dt);        
        setFields(schedulePage, scheduleDate);        
        String endDate = formatDate(DateUtils.addDays(dt, 2));        
        setFieldValue(schedulePage, SCHEDULEDATA_RECURRENCECTYPE, DAILY);       
        setFieldValue(schedulePage, SCHEDULEDATA_DAILYSCHEDULE_SCHEDULEENDDATE, endDate);
        
        HtmlPage pageAfterAdd = clickOnByName(schedulePage,METHODTOCALL_ADDEVENT_ANCHOR, true);
        
        assertFalse(hasError(pageAfterAdd));        
        assertRecord(pageAfterAdd, DateUtils.addDays(dt, 0));        
        assertRecord(pageAfterAdd, DateUtils.addDays(dt, 1));        
        assertRecord(pageAfterAdd, DateUtils.addDays(dt, 2));
               
        HtmlPage pageAfterSecondAdd = clickOnByName(pageAfterAdd,METHODTOCALL_ADDEVENT_ANCHOR, true);
        
        java.sql.Date sqlDate = new java.sql.Date(dt.getTime());
        StringBuffer sb = new StringBuffer();
        sb.append(sqlDate.toString()).append(IS_SKIPPED_IN_RECURRENCE);
        assertContains(pageAfterSecondAdd, sb.toString());
    }
   
}

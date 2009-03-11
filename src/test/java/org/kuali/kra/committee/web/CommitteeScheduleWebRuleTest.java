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
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }
    
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    @Test
    public void testSchedulePanelDateConflict() throws Exception {
        
        HtmlPage schedulePage =  prerequisite();        
        Date dt = DateUtils.addDays(new Date(), -1);        
        String scheduleDate = formatDate(dt);        
        setFields(schedulePage, scheduleDate);        
        String endDate = formatDate(DateUtils.addDays(dt, 2));        
        setFieldValue(schedulePage, "scheduleData.recurrenceType", "DAILY");       
        setFieldValue(schedulePage, "scheduleData.dailySchedule.scheduleEndDate", endDate);
        
        HtmlPage pageAfterAdd = clickOnByName(schedulePage,"methodToCall.addEvent.anchor", true);
        
        assertFalse(hasError(pageAfterAdd));        
        assertRecord(pageAfterAdd, DateUtils.addDays(dt, 0));        
        assertRecord(pageAfterAdd, DateUtils.addDays(dt, 1));        
        assertRecord(pageAfterAdd, DateUtils.addDays(dt, 2));
        
        Date testDate = DateUtils.addDays(new Date(), 0);        
        String strDate = formatDate(testDate);        
        setFieldValue(pageAfterAdd, "document.committee.committeeSchedules[0].scheduledDate", strDate);              
        
        HtmlPage pageAfterSave = saveDoc(pageAfterAdd);
        
        StringBuilder sb = new StringBuilder();
        java.sql.Date sqlDate = new java.sql.Date(testDate.getTime());
        sb.append(sqlDate.toString()).append(" is in conflict with other meeting schedule");
        assertContains(pageAfterSave, sb.toString());
    }     

    @Test
    public void testStartDateEndDateRule() throws Exception {
        
        HtmlPage schedulePage =  prerequisite();        
        Date dt = new Date();        
        String scheduleDate = formatDate(dt);        
        setFields(schedulePage, scheduleDate);        
        String endDate = scheduleDate;        
        setFieldValue(schedulePage, "scheduleData.recurrenceType", "DAILY");       
        setFieldValue(schedulePage, "scheduleData.dailySchedule.scheduleEndDate", endDate);
        
        HtmlPage pageAfterAdd = clickOnByName(schedulePage,"methodToCall.addEvent.anchor", true);
        
        assertContains(pageAfterAdd, "Date must be before Ending On date");
    }
 
    @Test
    public void testDateWarnings() throws Exception {
        
        HtmlPage schedulePage =  prerequisite();        
        Date dt = DateUtils.addDays(new Date(), -1);        
        String scheduleDate = formatDate(dt);        
        setFields(schedulePage, scheduleDate);        
        String endDate = formatDate(DateUtils.addDays(dt, 2));        
        setFieldValue(schedulePage, "scheduleData.recurrenceType", "DAILY");       
        setFieldValue(schedulePage, "scheduleData.dailySchedule.scheduleEndDate", endDate);
        
        HtmlPage pageAfterAdd = clickOnByName(schedulePage,"methodToCall.addEvent.anchor", true);
        
        assertFalse(hasError(pageAfterAdd));        
        assertRecord(pageAfterAdd, DateUtils.addDays(dt, 0));        
        assertRecord(pageAfterAdd, DateUtils.addDays(dt, 1));        
        assertRecord(pageAfterAdd, DateUtils.addDays(dt, 2));
               
        HtmlPage pageAfterSecondAdd = clickOnByName(pageAfterAdd,"methodToCall.addEvent.anchor", true);
        
        java.sql.Date sqlDate = new java.sql.Date(dt.getTime());
        StringBuffer sb = new StringBuffer();
        sb.append(sqlDate.toString()).append(" is skipped in recurrence, meeting already scheduled for the date.");
        assertContains(pageAfterSecondAdd, sb.toString());
    }
   
}

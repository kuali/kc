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

public class CommitteeScheduleWebCommitteeSchedulePanel extends CommitteeScheduleWebTestBase {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }
    
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testSchedulePanelSave() throws Exception {
        
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
        
        Date testDate = DateUtils.addDays(new Date(), 5);        
        String strDate = formatDate(testDate);        
        setFieldValue(pageAfterAdd, "document.committee.committeeSchedules[0].scheduledDate", strDate);        
        String deadlineDate = formatDate(DateUtils.addDays(testDate, -1));        
        setFieldValue(pageAfterAdd, "document.committee.committeeSchedules[0].protocolSubDeadline", deadlineDate);        
        setFieldValue(pageAfterAdd, "document.committee.committeeSchedules[0].scheduleStatusCode", "3");        
        setFieldValue(pageAfterAdd, "document.committee.committeeSchedules[0].place", "Sacramento");        
        setFieldValue(pageAfterAdd, "document.committee.committeeSchedules[0].viewTime.time", "10:30");
        
        HtmlPage pageAfterSave = saveDoc(pageAfterAdd);

        assertRecord(pageAfterSave, testDate);        
        assertContains(pageAfterSave,"Agenda Closed");
        assertContains(pageAfterSave,"Sacramento");
        assertContains(pageAfterSave,"10:30");
    }

    @Test
    public void testScheduleDelete() throws Exception {
        
        HtmlPage schedulePage =  prerequisite();        
        Date dt = DateUtils.addDays(new Date(), -1);        
        String scheduleDate = formatDate(dt);        
        setFields(schedulePage, scheduleDate);        
        String endDate = formatDate(DateUtils.addDays(dt, 3));        
        setFieldValue(schedulePage, "scheduleData.recurrenceType", "DAILY");       
        setFieldValue(schedulePage, "scheduleData.dailySchedule.scheduleEndDate", endDate);
        
        HtmlPage pageAfterAdd = clickOnByName(schedulePage,"methodToCall.addEvent.anchor", true);
        
        assertFalse(hasError(pageAfterAdd));        
        assertRecord(pageAfterAdd, DateUtils.addDays(dt, 0));        
        assertRecord(pageAfterAdd, DateUtils.addDays(dt, 1));        
        assertRecord(pageAfterAdd, DateUtils.addDays(dt, 2));
        assertRecord(pageAfterAdd, DateUtils.addDays(dt, 3));
        
        HtmlPage deletePage = clickOnByName(pageAfterAdd,"methodToCall.deleteCommitteeSchedule.line2.anchor0", true);        
        HtmlPage confirmPage = clickOnByName(deletePage,"methodToCall.processAnswer.button0", true);
        HtmlPage savePage = saveDoc(confirmPage);
        
        assertRecord(savePage, DateUtils.addDays(dt, 0));        
        assertRecord(savePage, DateUtils.addDays(dt, 1));        
        assertRecord(savePage, DateUtils.addDays(dt, 3));       
    }
}

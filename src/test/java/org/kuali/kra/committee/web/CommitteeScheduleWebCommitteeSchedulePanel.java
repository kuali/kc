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
    
    public static final String DAILY = "DAILY";
    
    public static final String SCHEDULEDATA_RECURRENCECTYPE = "committeeScheduleHelper.scheduleData.recurrenceType";
    
    public static final String SCHEDULEDATA_DAILYSCHEDULE_SCHEDULEENDDATE = "committeeScheduleHelper.scheduleData.dailySchedule.scheduleEndDate";
    
    public static final String METHODTOCALL_ADDEVENT_ANCHOR = "methodToCall.addEvent.anchor";
    
    public static final String DOCUMENT_COMMITTEE_COMMITTEESCHEDULES_0_SCHEDULEDDATE = "document.committeeList[0].committeeSchedules[0].scheduledDate";
    
    public static final String DOCUMENT_COMMITTEE_COMMITTEESCHEDULES_0_PROTOCOLSUBDEADLINE = "document.committeeList[0].committeeSchedules[0].protocolSubDeadline";
    
    public static final String DOCUMENT_COMMITTEE_COMMITTEESCHEDULES_0_SCHEDULESTATUSCODE = "document.committeeList[0].committeeSchedules[0].scheduleStatusCode";
    
    public static final String DOCUMENT_COMMITTEE_COMMITTEESCHEDULES_0_PLACE = "document.committeeList[0].committeeSchedules[0].place";
    
    public static final String DOCUMENT_COMMITTEE_COMMITTEESCHEDULES_0_VIWETIME_TIME = "document.committeeList[0].committeeSchedules[0].viewTime.time";
   
    public static final String THREE = "3";
    
    public static final String SACRAMENTO = "Sacramento";
    
    public static final String TIME_10_30 = "10:30";
    
    public static final String METHODTOCALL_DELETECOMMITTEESCHEDULE_ANCHOR_0 = "methodToCall.deleteCommitteeSchedule.anchor0";
    
    public static final String METHODTOCALL_PROCESSANSWER_BUTTON = "methodToCall.processAnswer.button0";
    
    public static final String AGENDA_CLOSED = "Agenda Closed";
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }
    
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * This method test's form save. 
     * @throws Exception
     */
    @Test
    public void testSchedulePanelSave() throws Exception {
        
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
        
        Date testDate = DateUtils.addDays(new Date(), 5);        
        String strDate = formatDate(testDate);        
        setFieldValue(pageAfterAdd, DOCUMENT_COMMITTEE_COMMITTEESCHEDULES_0_SCHEDULEDDATE, strDate);        
        String deadlineDate = formatDate(DateUtils.addDays(testDate, -1));        
        setFieldValue(pageAfterAdd, DOCUMENT_COMMITTEE_COMMITTEESCHEDULES_0_PROTOCOLSUBDEADLINE, deadlineDate);        
        setFieldValue(pageAfterAdd, DOCUMENT_COMMITTEE_COMMITTEESCHEDULES_0_SCHEDULESTATUSCODE, THREE);        
        setFieldValue(pageAfterAdd, DOCUMENT_COMMITTEE_COMMITTEESCHEDULES_0_PLACE, SACRAMENTO);        
        setFieldValue(pageAfterAdd, DOCUMENT_COMMITTEE_COMMITTEESCHEDULES_0_VIWETIME_TIME, TIME_10_30);
        
        HtmlPage pageAfterSave = saveDoc(pageAfterAdd);

        assertRecord(pageAfterSave, testDate);        
        assertContains(pageAfterSave,AGENDA_CLOSED);
        assertContains(pageAfterSave,SACRAMENTO);
        assertContains(pageAfterSave,TIME_10_30);
    }

    /**
     * This method test's delete button in schedule panel.
     * @throws Exception
     */
    @Test
    public void testScheduleDelete() throws Exception {
        
        HtmlPage schedulePage =  prerequisite();        
        Date dt = DateUtils.addDays(new Date(), -1);        
        String scheduleDate = formatDate(dt);        
        setFields(schedulePage, scheduleDate);        
        String endDate = formatDate(DateUtils.addDays(dt, 3));        
        setFieldValue(schedulePage, SCHEDULEDATA_RECURRENCECTYPE, DAILY);       
        setFieldValue(schedulePage, SCHEDULEDATA_DAILYSCHEDULE_SCHEDULEENDDATE, endDate);
        
        HtmlPage pageAfterAdd = clickOnByName(schedulePage,METHODTOCALL_ADDEVENT_ANCHOR, true);
        
        assertFalse(hasError(pageAfterAdd));        
        assertRecord(pageAfterAdd, DateUtils.addDays(dt, 0));        
        assertRecord(pageAfterAdd, DateUtils.addDays(dt, 1));        
        assertRecord(pageAfterAdd, DateUtils.addDays(dt, 2));
        assertRecord(pageAfterAdd, DateUtils.addDays(dt, 3));
        assertScheduleRowCount(pageAfterAdd, 4);
        
        setFieldValue(pageAfterAdd, "document.committeeList[0].committeeSchedules[1].selected", "on");
        HtmlPage deletePage = clickOnByName(pageAfterAdd,METHODTOCALL_DELETECOMMITTEESCHEDULE_ANCHOR_0, true);        
        HtmlPage confirmPage = clickOnByName(deletePage,METHODTOCALL_PROCESSANSWER_BUTTON, true);
        HtmlPage savePage = saveDoc(confirmPage);
        
        assertRecord(savePage, DateUtils.addDays(dt, 0));        
        assertRecord(savePage, DateUtils.addDays(dt, 2));        
        assertRecord(savePage, DateUtils.addDays(dt, 3));
        assertScheduleRowCount(savePage, 3);
    }
}

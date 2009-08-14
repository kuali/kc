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

public class CommitteeScheduleWebFilterTest extends CommitteeScheduleWebTestBase  {
    
    public static final String DAILY = "DAILY";
    
    public static final String SCHEDULEDATA_RECURRENCECTYPE = "committeeScheduleHelper.scheduleData.recurrenceType";
    
    public static final String SCHEDULEDATA_DAILYSCHEDULE_SCHEDULEENDDATE = "committeeScheduleHelper.scheduleData.dailySchedule.scheduleEndDate";
    
    public static final String METHODTOCALL_ADDEVENT_ANCHOR = "methodToCall.addEvent.anchor";
    
    public static final String SCHEDULEDATA_FILTERSTARTDATE = "committeeScheduleHelper.scheduleData.filterStartDate";
    
    public static final String SCHEDULEDATA_FILERENDDATE = "committeeScheduleHelper.scheduleData.filerEndDate";
    
    public static final String METHODTOCALL_FILTER_COMMITTEESCHEDULEDATES_ANCHORSCHEDULE = "methodToCall.filterCommitteeScheduleDates.anchorSchedule";
    
    public static final String METHODTOCALL_RESETCOMMITTEESCHEDULEDATES_ANCHORSCHEDULE = "methodToCall.resetCommitteeScheduleDates.anchorSchedule";
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }
    
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    /**
     * This method is test's filter dates.
     * @throws Exception
     */
    @Test
    public void testFilter() throws Exception {
        
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
        assertScheduleRowCount(pageAfterAdd, 3);
        
        setFieldValue(pageAfterAdd, SCHEDULEDATA_FILTERSTARTDATE, scheduleDate);    
        setFieldValue(pageAfterAdd, SCHEDULEDATA_FILERENDDATE, formatDate(DateUtils.addDays(dt, 1))); 
        
        HtmlPage filteredPage = clickOnByName(pageAfterAdd,METHODTOCALL_FILTER_COMMITTEESCHEDULEDATES_ANCHORSCHEDULE, true);
                                                           
        //assertFalse(hasError(filteredPage));        

        assertRecord(filteredPage, DateUtils.addDays(dt, 0));
        assertScheduleRowCount(filteredPage, 2);
                      
        HtmlPage resetFilter = clickOnByName(filteredPage,METHODTOCALL_RESETCOMMITTEESCHEDULEDATES_ANCHORSCHEDULE, true);
        
        assertRecord(resetFilter, DateUtils.addDays(dt, 0));        
        assertRecord(resetFilter, DateUtils.addDays(dt, 1));        
        assertRecord(resetFilter, DateUtils.addDays(dt, 2));
        assertScheduleRowCount(resetFilter, 3);
    }
}

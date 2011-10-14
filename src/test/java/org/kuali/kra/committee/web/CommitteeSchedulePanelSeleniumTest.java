/*
 * Copyright 2005-2010 The Kuali Foundation
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

/**
 * Tests the add and delete capabilities on the Schedule page of a Committee.
 */
public class CommitteeSchedulePanelSeleniumTest extends KcSeleniumTestBase {
    
    private static final String TABLE_ID = "schedule-table";
    
    private static final String HELPER_PREFIX = "committeeHelper.scheduleData.";
    private static final String LIST_PREFIX = "document.committeeList[0].committeeSchedules[%d].";
    
    private static final String SCHEDULE_START_DATE_ID = "scheduleStartDate";
    private static final String TIME_ID = "time.time";
    private static final String PLACE_ID = "place";
    private static final String RECURRENCE_TYPE_ID = "recurrenceType";
    private static final String DAILY_SCHEDULE_SCHEDULE_END_DATE_ID = "dailySchedule.scheduleEndDate";
    private static final String SCHEDULE_DATE_ID = "scheduledDate";
    private static final String PROTOCOL_SUB_DEADLINE_ID = "protocolSubDeadline";
    private static final String SCHEDULE_STATUS_CODE_ID = "scheduleStatusCode";
    private static final String VIEW_TIME_TIME_ID = "viewTime.time";
    private static final String SELECTED_ID = "selected";
    private static final String HELPER_SCHEDULE_START_DATE_ID = HELPER_PREFIX + SCHEDULE_START_DATE_ID;
    private static final String HELPER_TIME_ID = HELPER_PREFIX + TIME_ID;
    private static final String HELPER_PLACE_ID = HELPER_PREFIX + PLACE_ID;
    private static final String HELPER_RECURRENCE_TYPE_ID = HELPER_PREFIX + RECURRENCE_TYPE_ID;
    private static final String HELPER_DAILY_SCHEDULE_SCHEDULE_END_DATE_ID = HELPER_PREFIX + DAILY_SCHEDULE_SCHEDULE_END_DATE_ID;
    private static final String LIST_SCHEDULE_DATE_ID = LIST_PREFIX + SCHEDULE_DATE_ID;
    private static final String LIST_PROTOCOL_SUB_DEADLINE_ID = LIST_PREFIX + PROTOCOL_SUB_DEADLINE_ID; 
    private static final String LIST_SCHEDULE_STATUS_CODE_ID = LIST_PREFIX + SCHEDULE_STATUS_CODE_ID;
    private static final String LIST_PLACE_ID = LIST_PREFIX + PLACE_ID;
    private static final String LIST_VIEW_TIME_TIME_ID = LIST_PREFIX + VIEW_TIME_TIME_ID;
    private static final String LIST_SELECTED_ID = LIST_PREFIX + SELECTED_ID;
   
    private static final String TIME = "10:30";
    private static final String PLACE = "Sacramento";
    private static final String RECURRENCE_TYPE = "DAILY";
    private static final String SCHEDULE_STATUS_CODE = "Agenda Closed";
    private static final String SELECTED = "on";
    
    private static final String ADD_EVENT_BUTTON = "methodToCall.addEvent";
    private static final String DELETE_COMMITTEE_SCHEDULE_BUTTON = "methodToCall.deleteCommitteeSchedule";
    
    private static final DateFormat fullFormatter = new SimpleDateFormat("MM/dd/yyyy");
    private static final DateFormat dayFormatter = new SimpleDateFormat("EEEE");
    
    private CommitteeSeleniumHelper helper;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        helper = CommitteeSeleniumHelper.instance(driver);
    }
    
    @After
    public void tearDown() throws Exception {
        helper = null;
        
        super.tearDown();
    }

    /**
     * Test the addition items in the schedule.
     * 
     * @throws Exception
     */
    @Test
    public void testAddToSchedule() throws Exception {
        helper.createCommittee();
        helper.clickCommitteeSchedulePage();
        
        Date scheduleStartDate = DateUtils.addDays(new Date(), -1);
        Date scheduleEndDate = DateUtils.addDays(scheduleStartDate, 2);
          
        helper.set(HELPER_SCHEDULE_START_DATE_ID, fullFormatter.format(scheduleStartDate));
        helper.set(HELPER_TIME_ID, TIME);      
        helper.set(HELPER_PLACE_ID, PLACE);
        helper.set(HELPER_RECURRENCE_TYPE_ID, RECURRENCE_TYPE);       
        helper.set(HELPER_DAILY_SCHEDULE_SCHEDULE_END_DATE_ID, fullFormatter.format(scheduleEndDate));
        
        helper.click(ADD_EVENT_BUTTON);
        helper.assertNoPageErrors();
        
        Date firstScheduleDate = DateUtils.addDays(scheduleStartDate, 0);
        Date firstDeadlineDate = DateUtils.addDays(firstScheduleDate, -1);
        Date secondScheduleDate = DateUtils.addDays(scheduleStartDate, 1);
        Date secondDeadlineDate = DateUtils.addDays(secondScheduleDate, -1);
        Date thirdScheduleDate = DateUtils.addDays(scheduleStartDate, 2);
        Date thirdDeadlineDate = DateUtils.addDays(thirdScheduleDate, -1);
        
        helper.assertTableRowCount(TABLE_ID, 6);
        
        helper.assertElementContains(String.format(LIST_SCHEDULE_DATE_ID, 0), fullFormatter.format(firstScheduleDate));
        helper.assertTableCellValue(TABLE_ID, 2, 1, dayFormatter.format(firstScheduleDate).toUpperCase());
        helper.assertElementContains(String.format(LIST_PROTOCOL_SUB_DEADLINE_ID, 0), fullFormatter.format(firstDeadlineDate));
        
        helper.assertElementContains(String.format(LIST_SCHEDULE_DATE_ID, 1), fullFormatter.format(secondScheduleDate));
        helper.assertTableCellValue(TABLE_ID, 3, 1, dayFormatter.format(secondScheduleDate).toUpperCase());
        helper.assertElementContains(String.format(LIST_PROTOCOL_SUB_DEADLINE_ID, 1), fullFormatter.format(secondDeadlineDate));
        
        helper.assertElementContains(String.format(LIST_SCHEDULE_DATE_ID, 2), fullFormatter.format(thirdScheduleDate));
        helper.assertTableCellValue(TABLE_ID, 4, 1, dayFormatter.format(thirdScheduleDate).toUpperCase());
        helper.assertElementContains(String.format(LIST_PROTOCOL_SUB_DEADLINE_ID, 2), fullFormatter.format(thirdDeadlineDate));
        
        Date newScheduleDate = DateUtils.addDays(new Date(), 5);
        Date newDeadlineDate = DateUtils.addDays(newScheduleDate, -1);

        helper.set(String.format(LIST_SCHEDULE_DATE_ID, 0), fullFormatter.format(newScheduleDate));        
        helper.set(String.format(LIST_PROTOCOL_SUB_DEADLINE_ID, 0), fullFormatter.format(newDeadlineDate));        
        helper.set(String.format(LIST_SCHEDULE_STATUS_CODE_ID, 0), SCHEDULE_STATUS_CODE);
        helper.set(String.format(LIST_PLACE_ID, 0), PLACE);        
        helper.set(String.format(LIST_VIEW_TIME_TIME_ID, 0), TIME);
        
        helper.saveDocument();
        helper.assertNoPageErrors();

        helper.assertTableRowCount(TABLE_ID, 6);
        
        helper.assertElementContains(String.format(LIST_SCHEDULE_DATE_ID, 2), fullFormatter.format(newScheduleDate));
        helper.assertTableCellValue(TABLE_ID, 4, 1, dayFormatter.format(newScheduleDate).toUpperCase());
        helper.assertElementContains(String.format(LIST_PROTOCOL_SUB_DEADLINE_ID, 2), fullFormatter.format(newDeadlineDate));
        helper.assertElementContains(String.format(LIST_SCHEDULE_STATUS_CODE_ID, 2), SCHEDULE_STATUS_CODE);
        helper.assertElementContains(String.format(LIST_PLACE_ID, 2), PLACE);
        helper.assertElementContains(String.format(LIST_VIEW_TIME_TIME_ID, 2), TIME);
    }

    /**
     * Test the deletion of an item in the schedule.
     * 
     * @throws Exception
     */
    @Test
    public void testDeleteFromSchedule() throws Exception {
        helper.createCommittee();
        helper.clickCommitteeSchedulePage();
        
        Date scheduleStartDate = DateUtils.addDays(new Date(), -1);
        Date scheduleEndDate = DateUtils.addDays(scheduleStartDate, 3);
        
        helper.set(HELPER_SCHEDULE_START_DATE_ID, fullFormatter.format(scheduleStartDate));
        helper.set(HELPER_TIME_ID, TIME);      
        helper.set(HELPER_PLACE_ID, PLACE);
        helper.set(HELPER_RECURRENCE_TYPE_ID, RECURRENCE_TYPE);       
        helper.set(HELPER_DAILY_SCHEDULE_SCHEDULE_END_DATE_ID, fullFormatter.format(scheduleEndDate));
        
        helper.click(ADD_EVENT_BUTTON);
        helper.assertNoPageErrors();
        
        Date firstScheduleDate = DateUtils.addDays(scheduleStartDate, 0);
        Date firstDeadlineDate = DateUtils.addDays(firstScheduleDate, -1);
        Date secondScheduleDate = DateUtils.addDays(scheduleStartDate, 1);
        Date secondDeadlineDate = DateUtils.addDays(secondScheduleDate, -1);
        Date thirdScheduleDate = DateUtils.addDays(scheduleStartDate, 2);
        Date thirdDeadlineDate = DateUtils.addDays(thirdScheduleDate, -1);
        Date fourthScheduleDate = DateUtils.addDays(scheduleStartDate, 3);
        Date fourthDeadlineDate = DateUtils.addDays(fourthScheduleDate, -1);
        
        helper.assertTableRowCount(TABLE_ID, 7);
        
        helper.assertElementContains(String.format(LIST_SCHEDULE_DATE_ID, 0), fullFormatter.format(firstScheduleDate));
        helper.assertTableCellValue(TABLE_ID, 2, 1, dayFormatter.format(firstScheduleDate).toUpperCase());
        helper.assertElementContains(String.format(LIST_PROTOCOL_SUB_DEADLINE_ID, 0), fullFormatter.format(firstDeadlineDate));
        
        helper.assertElementContains(String.format(LIST_SCHEDULE_DATE_ID, 1), fullFormatter.format(secondScheduleDate));
        helper.assertTableCellValue(TABLE_ID, 3, 1, dayFormatter.format(secondScheduleDate).toUpperCase());
        helper.assertElementContains(String.format(LIST_PROTOCOL_SUB_DEADLINE_ID, 1), fullFormatter.format(secondDeadlineDate));
        
        helper.assertElementContains(String.format(LIST_SCHEDULE_DATE_ID, 2), fullFormatter.format(thirdScheduleDate));
        helper.assertTableCellValue(TABLE_ID, 4, 1, dayFormatter.format(thirdScheduleDate).toUpperCase());
        helper.assertElementContains(String.format(LIST_PROTOCOL_SUB_DEADLINE_ID, 2), fullFormatter.format(thirdDeadlineDate));
        
        helper.assertElementContains(String.format(LIST_SCHEDULE_DATE_ID, 3), fullFormatter.format(fourthScheduleDate));
        helper.assertTableCellValue(TABLE_ID, 5, 1, dayFormatter.format(fourthScheduleDate).toUpperCase());
        helper.assertElementContains(String.format(LIST_PROTOCOL_SUB_DEADLINE_ID, 3), fullFormatter.format(fourthDeadlineDate));
        
        helper.set(String.format(LIST_SELECTED_ID, 1), SELECTED);
        
        helper.click(DELETE_COMMITTEE_SCHEDULE_BUTTON);
        helper.clickYesAnswer();
        
        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertTableRowCount(TABLE_ID, 6);
        
        helper.assertElementContains(String.format(LIST_SCHEDULE_DATE_ID, 0), fullFormatter.format(firstScheduleDate));
        helper.assertTableCellValue(TABLE_ID, 2, 1, dayFormatter.format(firstScheduleDate).toUpperCase());
        helper.assertElementContains(String.format(LIST_PROTOCOL_SUB_DEADLINE_ID, 0), fullFormatter.format(firstDeadlineDate));
        
        helper.assertElementContains(String.format(LIST_SCHEDULE_DATE_ID, 1), fullFormatter.format(thirdScheduleDate));
        helper.assertTableCellValue(TABLE_ID, 3, 1, dayFormatter.format(thirdScheduleDate).toUpperCase());
        helper.assertElementContains(String.format(LIST_PROTOCOL_SUB_DEADLINE_ID, 1), fullFormatter.format(thirdDeadlineDate));
        
        helper.assertElementContains(String.format(LIST_SCHEDULE_DATE_ID, 2), fullFormatter.format(fourthScheduleDate));
        helper.assertTableCellValue(TABLE_ID, 4, 1, dayFormatter.format(fourthScheduleDate).toUpperCase());
        helper.assertElementContains(String.format(LIST_PROTOCOL_SUB_DEADLINE_ID, 2), fullFormatter.format(fourthDeadlineDate));
    }
    
}
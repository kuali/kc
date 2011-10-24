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
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.lang.time.DateUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

/**
 * Tests the add capability on the Schedule page of a Committee.
 */
public class CommitteeScheduleAddSeleniumTest extends KcSeleniumTestBase {
    
    private static final String TABLE_ID = "schedule-table";
    
    private static final String HELPER_PREFIX = "committeeHelper.scheduleData.";
    private static final String LIST_PREFIX = "document.committeeList[0].committeeSchedules[%d].";
    
    private static final String SCHEDULE_START_DATE_ID = "scheduleStartDate";
    private static final String TIME_ID = "time.time";
    private static final String PLACE_ID = "place";
    private static final String RECURRENCE_TYPE_ID = "recurrenceType";
    private static final String DAILY_SCHEDULE_SCHEDULE_END_DATE_ID = "dailySchedule.scheduleEndDate";
    private static final String DAILY_SCHEDULE_DAY_OPTION_ID = "dailySchedule.dayOption";
    private static final String WEEKLY_SCHEDULE_SCHEDULE_END_DATE_ID = "weeklySchedule.scheduleEndDate";
    private static final String MONTHLY_SCHEDULE_MONTH_OPTION_ID = "monthlySchedule.monthOption";
    private static final String MONTHLY_SCHEDULE_DAY_ID = "monthlySchedule.day";
    private static final String MONTHLY_SCHEDULE_SCHEDULE_END_DATE_ID = "monthlySchedule.scheduleEndDate";
    private static final String YEARLY_SCHEDULE_YEAR_OPTION_ID = "yearlySchedule.yearOption";
    private static final String YEARLY_SCHEDULE_SELECTED_OPTION_1_MONTH_ID = "yearlySchedule.selectedOption1Month";
    private static final String YEARLY_SCHEDULE_SELECTED_OPTION_2_MONTH_ID = "yearlySchedule.selectedOption2Month";
    private static final String YEARLY_SCHEDULE_DAY_ID = "yearlySchedule.day";
    private static final String YEARLY_SCHEDULE_SCHEDULE_END_DATE_ID = "yearlySchedule.scheduleEndDate";
    private static final String SCHEDULE_DATE_ID = "scheduledDate";
    private static final String PROTOCOL_SUB_DEADLINE_ID = "protocolSubDeadline";
    private static final String HELPER_SCHEDULE_START_DATE_ID = HELPER_PREFIX + SCHEDULE_START_DATE_ID;
    private static final String HELPER_TIME_ID = HELPER_PREFIX + TIME_ID;
    private static final String HELPER_PLACE_ID = HELPER_PREFIX + PLACE_ID;
    private static final String HELPER_RECURRENCE_TYPE_ID = HELPER_PREFIX + RECURRENCE_TYPE_ID;
    private static final String HELPER_DAILY_SCHEDULE_DAY_OPTION_ID = HELPER_PREFIX + DAILY_SCHEDULE_DAY_OPTION_ID;
    private static final String HELPER_DAILY_SCHEDULE_SCHEDULE_END_DATE_ID = HELPER_PREFIX + DAILY_SCHEDULE_SCHEDULE_END_DATE_ID;
    private static final String HELPER_WEEKLY_SCHEDULE_SCHEDULE_END_DATE_ID = HELPER_PREFIX + WEEKLY_SCHEDULE_SCHEDULE_END_DATE_ID;
    private static final String HELPER_MONTHLY_SCHEDULE_MONTH_OPTION_ID = HELPER_PREFIX + MONTHLY_SCHEDULE_MONTH_OPTION_ID;
    private static final String HELPER_MONTHLY_SCHEDULE_DAY_ID = HELPER_PREFIX + MONTHLY_SCHEDULE_DAY_ID;
    private static final String HELPER_MONTHLY_SCHEDULE_SCHEDULE_END_DATE_ID = HELPER_PREFIX + MONTHLY_SCHEDULE_SCHEDULE_END_DATE_ID;
    private static final String HELPER_YEARLY_SCHEDULE_YEAR_OPTION_ID = HELPER_PREFIX + YEARLY_SCHEDULE_YEAR_OPTION_ID;
    private static final String HELPER_YEARLY_SCHEDULE_SELECTED_OPTION_1_MONTH_ID = HELPER_PREFIX + YEARLY_SCHEDULE_SELECTED_OPTION_1_MONTH_ID;
    private static final String HELPER_YEARLY_SCHEDULE_SELECTED_OPTION_2_MONTH_ID = HELPER_PREFIX + YEARLY_SCHEDULE_SELECTED_OPTION_2_MONTH_ID;
    private static final String HELPER_YEARLY_SCHEDULE_DAY_ID = HELPER_PREFIX + YEARLY_SCHEDULE_DAY_ID;
    private static final String HELPER_YEARLY_SCHEDULE_SCHEDULE_END_DATE_ID = HELPER_PREFIX + YEARLY_SCHEDULE_SCHEDULE_END_DATE_ID;
    private static final String LIST_SCHEDULE_DATE_ID = LIST_PREFIX + SCHEDULE_DATE_ID;
    private static final String LIST_PROTOCOL_SUB_DEADLINE_ID = LIST_PREFIX + PROTOCOL_SUB_DEADLINE_ID;
    
    private static final String TIME = "10:10";
    private static final String PLACE = "Davis 103";
    private static final String RECURRENCE_TYPE_DAILY = "DAILY";
    private static final String RECURRENCE_TYPE_WEEKLY = "WEEKLY";
    private static final String RECURRENCE_TYPE_MONTHLY = "MONTHLY";
    private static final String RECURRENCE_TYPE_YEARLY = "YEARLY";
    private static final String SCHEDULE_OPTION_XDAY = "XDAY";
    private static final String SCHEDULE_OPTION_WEEKDAY = "WEEKDAY";
    private static final String SCHEDULE_OPTION_XDAYANDXMONTH = "XDAYANDXMONTH";
    private static final String SCHEDULE_OPTION_XDAYOFWEEKANDXMONTH = "XDAYOFWEEKANDXMONTH";
    private static final String SCHEDULE_OPTION_CMPLX = "CMPLX";
    
    private static final String ADD_EVENT_BUTTON = "methodToCall.addEvent";
    
    private static final DateFormat fullFormatter = new SimpleDateFormat("MM/dd/yyyy");
    private static final DateFormat monthFormatter = new SimpleDateFormat("MMMM");
    private static final DateFormat dayOfMonthFormatter = new SimpleDateFormat("d");
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
     * Test for user deletion of start date.
     * 
     * @throws Exception
     */
    @Test
    public void testCommitteScheduleEmptyStartDate() throws Exception {
        helper.createCommittee();
        helper.clickCommitteeSchedulePage();
             
        helper.set(HELPER_SCHEDULE_START_DATE_ID, Constants.EMPTY_STRING);
        helper.set(HELPER_TIME_ID, TIME);
        helper.set(HELPER_PLACE_ID, PLACE);
        
        helper.click(ADD_EVENT_BUTTON);
        
        helper.assertPageErrors();
        
    }
    
    /**
     * Test the never recurrence.
     * 
     * @throws Exception
     */
    @Test
    public void testCommitteeScheduleNeverRecurrance() throws Exception {
        helper.createCommittee();
        helper.clickCommitteeSchedulePage();
        
        Date scheduleStartDate = new Date();
        Date deadlineDate = DateUtils.addDays(scheduleStartDate, -1);
       
        helper.set(HELPER_SCHEDULE_START_DATE_ID, fullFormatter.format(scheduleStartDate));
        helper.set(HELPER_TIME_ID, TIME);
        helper.set(HELPER_PLACE_ID, PLACE);
        
        helper.click(ADD_EVENT_BUTTON);
        
        helper.assertNoPageErrors();
        
        helper.assertTableRowCount(TABLE_ID, 4);
        
        helper.assertElementContains(String.format(LIST_SCHEDULE_DATE_ID, 0), fullFormatter.format(scheduleStartDate));
        helper.assertTableCellValueContains(TABLE_ID, 2, 1, dayFormatter.format(scheduleStartDate).toUpperCase());
        helper.assertElementContains(String.format(LIST_PROTOCOL_SUB_DEADLINE_ID, 0), fullFormatter.format(deadlineDate));
    }

    /**
     * Test the daily recurrence option for "every x day".
     * 
     * @throws Exception
     */
    @Test
    public void testCommitteeScheduleDailyRecurranceOptionEveryXDay() throws Exception {
        helper.createCommittee();
        helper.clickCommitteeSchedulePage();
        
        Date scheduleStartDate = new Date();
        Date scheduleEndDate = DateUtils.addDays(scheduleStartDate, 3);
             
        helper.set(HELPER_SCHEDULE_START_DATE_ID, fullFormatter.format(scheduleStartDate));
        helper.set(HELPER_TIME_ID, TIME);
        helper.set(HELPER_PLACE_ID, PLACE);
        helper.set(HELPER_RECURRENCE_TYPE_ID, RECURRENCE_TYPE_DAILY);
        helper.set(HELPER_DAILY_SCHEDULE_DAY_OPTION_ID, SCHEDULE_OPTION_XDAY);
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
        helper.assertTableCellValueContains(TABLE_ID, 2, 1, dayFormatter.format(firstScheduleDate).toUpperCase());
        helper.assertElementContains(String.format(LIST_PROTOCOL_SUB_DEADLINE_ID, 0), fullFormatter.format(firstDeadlineDate));
        
        helper.assertElementContains(String.format(LIST_SCHEDULE_DATE_ID, 1), fullFormatter.format(secondScheduleDate));
        helper.assertTableCellValueContains(TABLE_ID, 3, 1, dayFormatter.format(secondScheduleDate).toUpperCase());
        helper.assertElementContains(String.format(LIST_PROTOCOL_SUB_DEADLINE_ID, 1), fullFormatter.format(secondDeadlineDate));
        
        helper.assertElementContains(String.format(LIST_SCHEDULE_DATE_ID, 2), fullFormatter.format(thirdScheduleDate));
        helper.assertTableCellValueContains(TABLE_ID, 4, 1, dayFormatter.format(thirdScheduleDate).toUpperCase());
        helper.assertElementContains(String.format(LIST_PROTOCOL_SUB_DEADLINE_ID, 2), fullFormatter.format(thirdDeadlineDate));
        
        helper.assertElementContains(String.format(LIST_SCHEDULE_DATE_ID, 3), fullFormatter.format(fourthScheduleDate));
        helper.assertTableCellValueContains(TABLE_ID, 5, 1, dayFormatter.format(fourthScheduleDate).toUpperCase());
        helper.assertElementContains(String.format(LIST_PROTOCOL_SUB_DEADLINE_ID, 3), fullFormatter.format(fourthDeadlineDate));
    }

    /**
     * Test the daily recurrence option for "every weekday".
     * 
     * @throws Exception
     */
    @Test
    public void testCommitteeScheduleDailyRecurranceOptionEveryWeekday() throws Exception {
        helper.createCommittee();
        helper.clickCommitteeSchedulePage();
        
        Date scheduleStartDate = new Date();
        Date scheduleEndDate = DateUtils.addDays(scheduleStartDate, 3);
             
        helper.set(HELPER_SCHEDULE_START_DATE_ID, fullFormatter.format(scheduleStartDate));
        helper.set(HELPER_TIME_ID, TIME);
        helper.set(HELPER_PLACE_ID, PLACE);
        helper.set(HELPER_RECURRENCE_TYPE_ID, RECURRENCE_TYPE_DAILY);
        helper.set(HELPER_DAILY_SCHEDULE_DAY_OPTION_ID, SCHEDULE_OPTION_WEEKDAY);
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
        
        int tableRowCount = 3;
        if (isWeekday(firstScheduleDate)) {
            tableRowCount++;
        }
        if (isWeekday(secondScheduleDate)) {
            tableRowCount++;
        }
        if (isWeekday(thirdScheduleDate)) {
            tableRowCount++;
        }
        if (isWeekday(fourthScheduleDate)) {
            tableRowCount++;
        }
        
        helper.assertTableRowCount(TABLE_ID, tableRowCount);
        
        int index = 0;
        if (isWeekday(firstScheduleDate)) {
            helper.assertElementContains(String.format(LIST_SCHEDULE_DATE_ID, index), fullFormatter.format(firstScheduleDate));
            helper.assertTableCellValueContains(TABLE_ID, index + 2, 1, dayFormatter.format(firstScheduleDate).toUpperCase());
            helper.assertElementContains(String.format(LIST_PROTOCOL_SUB_DEADLINE_ID, index), fullFormatter.format(firstDeadlineDate));
            index++;
        }
        if (isWeekday(secondScheduleDate)) {
            helper.assertElementContains(String.format(LIST_SCHEDULE_DATE_ID, index), fullFormatter.format(secondScheduleDate));
            helper.assertTableCellValueContains(TABLE_ID, index + 2, 1, dayFormatter.format(secondScheduleDate).toUpperCase());
            helper.assertElementContains(String.format(LIST_PROTOCOL_SUB_DEADLINE_ID, index), fullFormatter.format(secondDeadlineDate));
            index++;
        }
        if (isWeekday(thirdScheduleDate)) {
            helper.assertElementContains(String.format(LIST_SCHEDULE_DATE_ID, index), fullFormatter.format(thirdScheduleDate));
            helper.assertTableCellValueContains(TABLE_ID, index + 2, 1, dayFormatter.format(thirdScheduleDate).toUpperCase());
            helper.assertElementContains(String.format(LIST_PROTOCOL_SUB_DEADLINE_ID, index), fullFormatter.format(thirdDeadlineDate));
            index++;
        }
        if (isWeekday(fourthScheduleDate)) {
            helper.assertElementContains(String.format(LIST_SCHEDULE_DATE_ID, index), fullFormatter.format(fourthScheduleDate));
            helper.assertTableCellValueContains(TABLE_ID, index + 2, 1, dayFormatter.format(fourthScheduleDate).toUpperCase());
            helper.assertElementContains(String.format(LIST_PROTOCOL_SUB_DEADLINE_ID, index), fullFormatter.format(fourthDeadlineDate));
        }
    }
    
    /**
     * Test the weekly recurrence option for "every Monday".
     * 
     * @throws Exception
     */
    @Test
    public void testCommitteeScheduleWeeklyRecurranceMonday() throws Exception {
        helper.createCommittee();
        helper.clickCommitteeSchedulePage();
        
        Date scheduleStartDate = new Date();
        Date scheduleEndDate = DateUtils.addDays(scheduleStartDate, 7);
        
        helper.set(HELPER_SCHEDULE_START_DATE_ID, fullFormatter.format(scheduleStartDate));
        helper.set(HELPER_TIME_ID, TIME);
        helper.set(HELPER_PLACE_ID, PLACE);
        helper.set(HELPER_RECURRENCE_TYPE_ID, RECURRENCE_TYPE_WEEKLY);
        helper.set(HELPER_WEEKLY_SCHEDULE_SCHEDULE_END_DATE_ID, fullFormatter.format(scheduleEndDate));
        
        helper.click(ADD_EVENT_BUTTON);
        
        helper.assertNoPageErrors();

        Date scheduleMonday = getMonday(scheduleStartDate);
        Date deadlineDateMonday = DateUtils.addDays(scheduleMonday, -1);
        
        if (DateUtils.isSameDay(scheduleStartDate, scheduleMonday)) {
            Date scheduleNextMonday = DateUtils.addDays(scheduleMonday, 7);
            Date deadlineDateNextMonday = DateUtils.addDays(scheduleNextMonday, -1);
            
            helper.assertTableRowCount(TABLE_ID, 5);
            
            helper.assertElementContains(String.format(LIST_SCHEDULE_DATE_ID, 0), fullFormatter.format(scheduleMonday));
            helper.assertTableCellValueContains(TABLE_ID, 2, 1, dayFormatter.format(scheduleMonday).toUpperCase());
            helper.assertElementContains(String.format(LIST_PROTOCOL_SUB_DEADLINE_ID, 0), fullFormatter.format(deadlineDateMonday));
            
            helper.assertElementContains(String.format(LIST_SCHEDULE_DATE_ID, 1), fullFormatter.format(scheduleNextMonday));
            helper.assertTableCellValueContains(TABLE_ID, 3, 1, dayFormatter.format(scheduleNextMonday).toUpperCase());
            helper.assertElementContains(String.format(LIST_PROTOCOL_SUB_DEADLINE_ID, 1), fullFormatter.format(deadlineDateNextMonday));
        } else {
            helper.assertTableRowCount(TABLE_ID, 4);
            
            helper.assertElementContains(String.format(LIST_SCHEDULE_DATE_ID, 0), fullFormatter.format(scheduleMonday));
            helper.assertTableCellValueContains(TABLE_ID, 2, 1, dayFormatter.format(scheduleMonday).toUpperCase());
            helper.assertElementContains(String.format(LIST_PROTOCOL_SUB_DEADLINE_ID, 0), fullFormatter.format(deadlineDateMonday));
        }
    }
    
    /**
     * Test the monthly recurrence option for "x day and x month".
     * 
     * @throws Exception
     */
    @Test
    public void testCommitteeScheduleMonthlyRecurranceOptionXDayAndXMonth() throws Exception {
        helper.createCommittee();
        helper.clickCommitteeSchedulePage();
        
        Date scheduleStartDate = new Date();
        Date scheduleEndDate = DateUtils.addDays(scheduleStartDate, 7);
        Date scheduleDeadlineDate = DateUtils.addDays(scheduleStartDate, -1);
        
        helper.set(HELPER_SCHEDULE_START_DATE_ID, fullFormatter.format(scheduleStartDate));
        helper.set(HELPER_TIME_ID, TIME);
        helper.set(HELPER_PLACE_ID, PLACE);
        helper.set(HELPER_RECURRENCE_TYPE_ID, RECURRENCE_TYPE_MONTHLY);
        helper.set(HELPER_MONTHLY_SCHEDULE_MONTH_OPTION_ID, SCHEDULE_OPTION_XDAYANDXMONTH); 
        helper.set(HELPER_MONTHLY_SCHEDULE_DAY_ID, dayOfMonthFormatter.format(scheduleStartDate));       
        helper.set(HELPER_MONTHLY_SCHEDULE_SCHEDULE_END_DATE_ID, fullFormatter.format(scheduleEndDate));
        
        helper.click(ADD_EVENT_BUTTON);
        
        helper.assertNoPageErrors();
        
        helper.assertTableRowCount(TABLE_ID, 4);
        
        helper.assertElementContains(String.format(LIST_SCHEDULE_DATE_ID, 0), fullFormatter.format(scheduleStartDate));
        helper.assertTableCellValueContains(TABLE_ID, 2, 1, dayFormatter.format(scheduleStartDate).toUpperCase());
        helper.assertElementContains(String.format(LIST_PROTOCOL_SUB_DEADLINE_ID, 0), fullFormatter.format(scheduleDeadlineDate));
    }

    /**
     * Test the monthly recurrence option for "x day of the week and x month".
     * 
     * @throws Exception
     */
    @Test
    public void testCommitteeScheduleMonthlyRecurranceOptionXDayOfWeekAndXMonth() throws Exception {
        helper.createCommittee();
        helper.clickCommitteeSchedulePage();
        
        Date scheduleStartDate = DateUtils.truncate(new Date(), Calendar.MONTH);
        Date scheduleEndDate = DateUtils.addDays(scheduleStartDate, 10);

        helper.set(HELPER_SCHEDULE_START_DATE_ID, fullFormatter.format(scheduleStartDate));
        helper.set(HELPER_TIME_ID, TIME);
        helper.set(HELPER_PLACE_ID, PLACE);
        helper.set(HELPER_RECURRENCE_TYPE_ID, RECURRENCE_TYPE_MONTHLY);       
        helper.set(HELPER_MONTHLY_SCHEDULE_MONTH_OPTION_ID, SCHEDULE_OPTION_XDAYOFWEEKANDXMONTH);             
        helper.set(HELPER_MONTHLY_SCHEDULE_SCHEDULE_END_DATE_ID, fullFormatter.format(scheduleEndDate));
        
        helper.click(ADD_EVENT_BUTTON);
        
        helper.assertNoPageErrors();
        
        Date scheduleMonday = getMonday(scheduleStartDate);
        Date deadlineDateMonday = DateUtils.addDays(scheduleMonday, -1);
        
        helper.assertTableRowCount(TABLE_ID, 4);
        
        helper.assertElementContains(String.format(LIST_SCHEDULE_DATE_ID, 0), fullFormatter.format(scheduleMonday));
        helper.assertTableCellValueContains(TABLE_ID, 2, 1, dayFormatter.format(scheduleMonday).toUpperCase());
        helper.assertElementContains(String.format(LIST_PROTOCOL_SUB_DEADLINE_ID, 0), fullFormatter.format(deadlineDateMonday));
    }

    /**
     * Test the yearly recurrence option for "every x day".
     * 
     * @throws Exception
     */
    @Test
    public void testCommitteeScheduleYearlyRecurranceOptionEveryXDay() throws Exception {
        helper.createCommittee();
        helper.clickCommitteeSchedulePage();
        
        Date scheduleStartDate = new Date();
        Date scheduleEndDate = DateUtils.addDays(scheduleStartDate, 10);
        Date scheduleDeadlineDate = DateUtils.addDays(scheduleStartDate, -1);
        
        helper.set(HELPER_SCHEDULE_START_DATE_ID, fullFormatter.format(scheduleStartDate));
        helper.set(HELPER_TIME_ID, TIME);
        helper.set(HELPER_PLACE_ID, PLACE);
        helper.set(HELPER_RECURRENCE_TYPE_ID, RECURRENCE_TYPE_YEARLY);       
        helper.set(HELPER_YEARLY_SCHEDULE_YEAR_OPTION_ID, SCHEDULE_OPTION_XDAY);              
        helper.set(HELPER_YEARLY_SCHEDULE_SELECTED_OPTION_1_MONTH_ID, monthFormatter.format(scheduleStartDate).toUpperCase());  
        helper.set(HELPER_YEARLY_SCHEDULE_DAY_ID, dayOfMonthFormatter.format(scheduleStartDate));
        helper.set(HELPER_YEARLY_SCHEDULE_SCHEDULE_END_DATE_ID, fullFormatter.format(scheduleEndDate));
        
        helper.click(ADD_EVENT_BUTTON);
        
        helper.assertNoPageErrors();
        
        helper.assertTableRowCount(TABLE_ID, 4);
        
        helper.assertElementContains(String.format(LIST_SCHEDULE_DATE_ID, 0), fullFormatter.format(scheduleStartDate));
        helper.assertTableCellValueContains(TABLE_ID, 2, 1, dayFormatter.format(scheduleStartDate).toUpperCase());
        helper.assertElementContains(String.format(LIST_PROTOCOL_SUB_DEADLINE_ID, 0), fullFormatter.format(scheduleDeadlineDate));
    }
    
    /**
     * Test the yearly recurrence option for complex schedules.
     * 
     * @throws Exception
     */
    @Test
    public void testCommitteeScheduleYearlyRecurranceOptionCmplx() throws Exception {
        helper.createCommittee();
        helper.clickCommitteeSchedulePage();
        
        Date scheduleStartDate = DateUtils.truncate(new Date(), Calendar.MONTH);
        Date scheduleEndDate = DateUtils.addDays(scheduleStartDate, 10);
        
        helper.set(HELPER_SCHEDULE_START_DATE_ID, fullFormatter.format(scheduleStartDate));
        helper.set(HELPER_TIME_ID, TIME);
        helper.set(HELPER_PLACE_ID, PLACE);
        helper.set(HELPER_RECURRENCE_TYPE_ID, RECURRENCE_TYPE_YEARLY);
        helper.set(HELPER_YEARLY_SCHEDULE_YEAR_OPTION_ID, SCHEDULE_OPTION_CMPLX);
        helper.set(HELPER_YEARLY_SCHEDULE_SELECTED_OPTION_2_MONTH_ID, monthFormatter.format(scheduleStartDate).toUpperCase());
        helper.set(HELPER_YEARLY_SCHEDULE_SCHEDULE_END_DATE_ID, fullFormatter.format(scheduleEndDate));
        
        helper.click(ADD_EVENT_BUTTON);
        
        helper.assertNoPageErrors();
        
        Date scheduleMonday = getMonday(scheduleStartDate);
        Date deadlineDateMonday = DateUtils.addDays(scheduleMonday, -1);
        
        helper.assertTableRowCount(TABLE_ID, 4);
        
        helper.assertElementContains(String.format(LIST_SCHEDULE_DATE_ID, 0), fullFormatter.format(scheduleMonday));
        helper.assertTableCellValueContains(TABLE_ID, 2, 1, dayFormatter.format(scheduleMonday).toUpperCase());
        helper.assertElementContains(String.format(LIST_PROTOCOL_SUB_DEADLINE_ID, 0), fullFormatter.format(deadlineDateMonday));
    }
    
    /**
     * Determines whether the given {@code date} is on a weekday.
     * 
     * @param date the date to check
     * @return true if {@code date} is on a weekday, false if it is on a weekend
     */
    private boolean isWeekday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        
        boolean isSaturday = calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY;
        boolean isSunday = calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
        
        return !isSaturday && !isSunday;
    }
    
    /**
     * Gets the next Monday date occurring after the given {@code startDate}.
     * 
     * @param startDate the date to start at
     * @return the next Monday date occurring after the given {@code startDate}
     */
    @SuppressWarnings("unchecked")
    private Date getMonday(Date startDate) {
        Date monday = startDate;
        
        for (Iterator<Calendar> iter = DateUtils.iterator(startDate, DateUtils.RANGE_WEEK_RELATIVE); iter.hasNext(); ) {
            Calendar calendar = iter.next();
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                monday = calendar.getTime();
                break;
            }
        }
        
        return monday;
    }

}
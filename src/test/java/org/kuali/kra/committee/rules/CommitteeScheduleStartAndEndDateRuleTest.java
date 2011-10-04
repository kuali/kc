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
package org.kuali.kra.committee.rules;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.HashMap;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.rule.event.CommitteeScheduleStartAndEndDateEvent;
import org.kuali.kra.committee.web.struts.form.schedule.*;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;

public class CommitteeScheduleStartAndEndDateRuleTest  {
    
    private CommitteeDocument document;
    
    private NonRepeatingScheduleData nonRepeatingScheduleData = new NonRepeatingScheduleData();
    private DailyScheduleData dailyScheduleData = new DailyScheduleData();
    private WeeklyScheduleData weeklyScheduleData = new WeeklyScheduleData();
    private MonthlyScheduleData monthlyScheduleData = new MonthlyScheduleData();
    private YearlyScheduleData yearlyScheduleData = new YearlyScheduleData();
    
    private CommitteeScheduleStartAndEndDateEvent event;
    
    public static String DAILY = "scheduleData.dailySchedule.scheduleEndDate";
    
    public static String WEEKLY = "scheduleData.weeklySchedule.scheduleEndDate";
    
    public static String MONTHLY = "scheduleData.monthlySchedule.scheduleEndDate";
    
    public static String YEARLY = "scheduleData.yearlySchedule.scheduleEndDate";
    
    public static String START_DATE = "scheduleData.scheduleEndDate";
    
    @SuppressWarnings("unchecked")
    @Before
    public void setUp(){
        GlobalVariables.setErrorMap(new ErrorMap());
        GlobalVariables.setAuditErrorMap(new HashMap()); 
    }
    
    /**
     * This method tests for user deletion of start date
     * @throws Exception
     */
    @Test
    public void testEmptyStartDate() throws Exception {
        
        prerequisite(nonRepeatingScheduleData);
        
        event.getScheduleData().setScheduleStartDate(null);
        
        testAssertFalse();
    }
    
    /**
     * This method test's rule for user's NEVER selection. 
     * @throws Exception
     */
    @Test
    public void testNever() throws Exception {
        
        prerequisite(nonRepeatingScheduleData);
        
        testAssertTrue();
    }
    
    /**
     * This method test's rule for user's DAILY selection. 
     * @throws Exception
     */
    @Test
    public void testDailyForTrue() throws Exception {
        
        prerequisite(nonRepeatingScheduleData);
        
        dailyScheduleData.setDailySchedule(new DailyScheduleDetails());
        Date dt = DateUtils.addDays(new Date(), 1);
        dailyScheduleData.getDailySchedule().setScheduleEndDate(new java.sql.Date(dt.getTime()));
        
        testAssertTrue();
    }
    
    /**
     * This method test's rule for user's DAILY selection. 
     * @throws Exception
     */
    @Test
    public void testDailyForFalse() throws Exception {
        
        prerequisite(dailyScheduleData);
        
        dailyScheduleData.setDailySchedule(new DailyScheduleDetails());
        dailyScheduleData.getDailySchedule().setScheduleEndDate(dailyScheduleData.getScheduleStartDate());       
        
        testAssertFalse();
    }
    
    /**
     * This method test's rule for user's WEEKLY selection.
     * @throws Exception
     */
    @Test
    public void testWeeklyForTrue() throws Exception {
        
        prerequisite(weeklyScheduleData);
        
        weeklyScheduleData.setWeeklySchedule(new WeeklyScheduleDetails());
        Date dt = DateUtils.addDays(new Date(), 1);
        weeklyScheduleData.getWeeklySchedule().setScheduleEndDate(new java.sql.Date(dt.getTime()));
        
        testAssertTrue();
    }

    /**
     * This method test's rule for user's WEEKLY selection.
     * @throws Exception
     */
    @Test
    public void testWeeklyForFalse() throws Exception {
        
        prerequisite(weeklyScheduleData);
        
        weeklyScheduleData.setWeeklySchedule(new WeeklyScheduleDetails());
        weeklyScheduleData.getWeeklySchedule().setScheduleEndDate(weeklyScheduleData.getScheduleStartDate());
        
        testAssertFalse();
    }

    /**
     * This method test's rule for user's MONTHLY selection.
     * @throws Exception
     */
    @Test
    public void testMonthlyForTrue() throws Exception {
        
        prerequisite(monthlyScheduleData);
        
        monthlyScheduleData.setMonthlySchedule(new MonthlyScheduleDetails());
        Date dt = DateUtils.addDays(new Date(), 1);
        monthlyScheduleData.getMonthlySchedule().setScheduleEndDate(new java.sql.Date(dt.getTime()));
        
        testAssertTrue();
    }

    /**
     * This method test's rule for user's MONTHLY selection.
     * @throws Exception
     */
    @Test
    public void testMonthlyForFalse() throws Exception {
        
        prerequisite(monthlyScheduleData);
        
        monthlyScheduleData.setMonthlySchedule(new MonthlyScheduleDetails());
        monthlyScheduleData.getMonthlySchedule().setScheduleEndDate(monthlyScheduleData.getScheduleStartDate());
        
        testAssertFalse();
    }    

    /**
     * This method test's rule for user's YEARLY selection.
     * @throws Exception
     */
    @Test
    public void testYearlyForTrue() throws Exception {
        
        prerequisite(yearlyScheduleData);
        
        yearlyScheduleData.setYearlySchedule(new YearlyScheduleDetails());
        Date dt = DateUtils.addDays(new Date(), 1);
        yearlyScheduleData.getYearlySchedule().setScheduleEndDate(new java.sql.Date(dt.getTime()));
        
        testAssertTrue();
    }

    /**
     * This method test's rule for user's YEARLY selection.
     * @throws Exception
     */
    @Test
    public void testYearlyForFalse() throws Exception {
        
        prerequisite(yearlyScheduleData);
        
        yearlyScheduleData.setYearlySchedule(new YearlyScheduleDetails());
        yearlyScheduleData.getYearlySchedule().setScheduleEndDate(yearlyScheduleData.getScheduleStartDate());
        
        testAssertFalse();
    } 
    
    /**
     * This method is helper method to assert true condition.
     */
    private void testAssertTrue() {
        boolean val = new CommitteeScheduleStartAndEndDateRule().processRules(event);
        assertTrue(val);
    }
    
    /**
     * This method is helper method to assert false condition.
     */
    private void testAssertFalse() {
        boolean val = new CommitteeScheduleStartAndEndDateRule().processRules(event);
        assertFalse(val);
    }
    
    /**
     * This method is prerequisite helper method.
     * @param key
     */
    private void prerequisite(ScheduleData scheduleData) {
        scheduleData.setScheduleStartDate(new java.sql.Date(new Date().getTime()));
        event = new CommitteeScheduleStartAndEndDateEvent("", (CommitteeDocument)document, scheduleData, null, null);
    }
}

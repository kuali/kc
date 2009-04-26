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
import org.kuali.kra.committee.web.struts.form.schedule.DailyScheduleDetails;
import org.kuali.kra.committee.web.struts.form.schedule.MonthlyScheduleDetails;
import org.kuali.kra.committee.web.struts.form.schedule.ScheduleData;
import org.kuali.kra.committee.web.struts.form.schedule.StyleKey;
import org.kuali.kra.committee.web.struts.form.schedule.WeeklyScheduleDetails;
import org.kuali.kra.committee.web.struts.form.schedule.YearlyScheduleDetails;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;

public class CommitteeScheduleStartAndEndDateRuleTest  {
    
    private CommitteeDocument document;
    
    private ScheduleData scheduleData;
    
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
     * This method test's rule for user's NEVER selection. 
     * @throws Exception
     */
    @Test
    public void testNever() throws Exception {
        
        prerequisite(StyleKey.NEVER);
        
        testAssertTrue();
    }
    
    /**
     * This method test's rule for user's DAILY selection. 
     * @throws Exception
     */
    @Test
    public void testDailyForTrue() throws Exception {
        
        prerequisite(StyleKey.DAILY);
        
        scheduleData.setDailySchedule(new DailyScheduleDetails());
        Date dt = DateUtils.addDays(new Date(), 1);
        scheduleData.getDailySchedule().setScheduleEndDate(new java.sql.Date(dt.getTime()));
        
        testAssertTrue();
    }
    
    /**
     * This method test's rule for user's DAILY selection. 
     * @throws Exception
     */
    @Test
    public void testDailyForFalse() throws Exception {
        
        prerequisite(StyleKey.DAILY);
        
        scheduleData.setDailySchedule(new DailyScheduleDetails());
        scheduleData.getDailySchedule().setScheduleEndDate(scheduleData.getScheduleStartDate());       
        
        testAssertFalse();
    }
    
    /**
     * This method test's rule for user's WEEKLY selection.
     * @throws Exception
     */
    @Test
    public void testWeeklyForTrue() throws Exception {
        
        prerequisite(StyleKey.WEEKLY);
        
        scheduleData.setWeeklySchedule(new WeeklyScheduleDetails());
        Date dt = DateUtils.addDays(new Date(), 1);
        scheduleData.getWeeklySchedule().setScheduleEndDate(new java.sql.Date(dt.getTime()));
        
        testAssertTrue();
    }

    /**
     * This method test's rule for user's WEEKLY selection.
     * @throws Exception
     */
    @Test
    public void testWeeklyForFalse() throws Exception {
        
        prerequisite(StyleKey.WEEKLY);
        
        scheduleData.setWeeklySchedule(new WeeklyScheduleDetails());
        scheduleData.getWeeklySchedule().setScheduleEndDate(scheduleData.getScheduleStartDate());
        
        testAssertFalse();
    }

    /**
     * This method test's rule for user's MONTHLY selection.
     * @throws Exception
     */
    @Test
    public void testMonthlyForTrue() throws Exception {
        
        prerequisite(StyleKey.MONTHLY);
        
        scheduleData.setMonthlySchedule(new MonthlyScheduleDetails());
        Date dt = DateUtils.addDays(new Date(), 1);
        scheduleData.getMonthlySchedule().setScheduleEndDate(new java.sql.Date(dt.getTime()));
        
        testAssertTrue();
    }

    /**
     * This method test's rule for user's MONTHLY selection.
     * @throws Exception
     */
    @Test
    public void testMonthlyForFalse() throws Exception {
        
        prerequisite(StyleKey.MONTHLY);
        
        scheduleData.setMonthlySchedule(new MonthlyScheduleDetails());
        scheduleData.getMonthlySchedule().setScheduleEndDate(scheduleData.getScheduleStartDate());
        
        testAssertFalse();
    }    

    /**
     * This method test's rule for user's YEARLY selection.
     * @throws Exception
     */
    @Test
    public void testYearlyForTrue() throws Exception {
        
        prerequisite(StyleKey.YEARLY);
        
        scheduleData.setYearlySchedule(new YearlyScheduleDetails());
        Date dt = DateUtils.addDays(new Date(), 1);
        scheduleData.getYearlySchedule().setScheduleEndDate(new java.sql.Date(dt.getTime()));
        
        testAssertTrue();
    }

    /**
     * This method test's rule for user's YEARLY selection.
     * @throws Exception
     */
    @Test
    public void testYearlyForFalse() throws Exception {
        
        prerequisite(StyleKey.YEARLY);
        
        scheduleData.setYearlySchedule(new YearlyScheduleDetails());
        scheduleData.getYearlySchedule().setScheduleEndDate(scheduleData.getScheduleStartDate());
        
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
    private void prerequisite(StyleKey key) {
        scheduleData = new ScheduleData();
        scheduleData.setRecurrenceType(key.toString());
        scheduleData.setScheduleStartDate(new java.sql.Date(new Date().getTime()));
        event = new CommitteeScheduleStartAndEndDateEvent("", (CommitteeDocument)document, scheduleData, null, null);
    }
}

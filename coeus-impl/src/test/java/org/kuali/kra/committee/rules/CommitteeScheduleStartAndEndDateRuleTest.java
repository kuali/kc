/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.committee.rules;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.committee.impl.web.struts.form.schedule.*;
import org.kuali.coeus.sys.impl.validation.ErrorReporterImpl;
import org.kuali.kra.committee.rule.event.CommitteeScheduleStartAndEndDateEvent;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CommitteeScheduleStartAndEndDateRuleTest  {
    

    private ScheduleData scheduleData;
    
    private CommitteeScheduleStartAndEndDateEvent event;
    
    @SuppressWarnings("unchecked")
    @Before
    public void setUp(){
        GlobalVariables.setMessageMap(new MessageMap());
        GlobalVariables.setAuditErrorMap(new HashMap()); 
    }
    
    /**
     * This method tests for user deletion of start date
     * @throws Exception
     */
    @Test
    public void testEmptyStartDate() throws Exception {
        
        prerequisite(StyleKey.NEVER);
        
        event.getScheduleData().setScheduleStartDate(null);
        
        testAssertFalse();
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
        CommitteeScheduleStartAndEndDateRule rule = new CommitteeScheduleStartAndEndDateRule();
        rule.setErrorReporter(new ErrorReporterImpl());
        boolean val = rule.processRules(event);
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
        event = new CommitteeScheduleStartAndEndDateEvent("", null, scheduleData, null, null);
    }
}

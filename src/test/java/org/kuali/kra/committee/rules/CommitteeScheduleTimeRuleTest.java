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

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.rule.event.CommitteeScheduleTimeEvent;
import org.kuali.kra.committee.rule.event.CommitteeScheduleEventBase.ErrorType;
import org.kuali.kra.committee.web.struts.form.schedule.ScheduleData;
import org.kuali.kra.committee.web.struts.form.schedule.Time12HrFmt;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;

public class CommitteeScheduleTimeRuleTest {
    
    private List<CommitteeSchedule> committeeSchedules;
    
    private ScheduleData scheduleData;
    
    private CommitteeScheduleTimeEvent event;
    
    private CommitteeScheduleTimeRule rule;
    
    public static String TIME_10_10 = "10:10";
    
    public static String TIME_ = "";
    
    public static String TIME_10_100 = "10:100";
    
    public static String TIME_100_10 = "100:10";
    
    public static String TIME_0_10 = "0:10";
    
    @SuppressWarnings("unchecked")
    @Before
    public void setUp(){
        GlobalVariables.setErrorMap(new ErrorMap());
        GlobalVariables.setAuditErrorMap(new HashMap());  
    }
    
    /**
     * This method is test case of time before while it adds new schedule.
     * @throws Exception
     */
    @Test
    public void testProcessRuleTrueWithScheduleData() throws Exception {        
        prerequisiteScheduleData();
        scheduleData.getTime().setTime(TIME_10_10);        
        boolean val = executeRule();
        assertTrue(val);
    }    
    
    /**
     * This method is test case of time before while it adds new schedule for false, with time as blank value.
     * @throws Exception
     */
    @Test
    public void testProcessRuleFalseTimeBlankWithScheduleData() throws Exception {        
        prerequisiteScheduleData();
        scheduleData.getTime().setTime(TIME_);
        boolean val = executeRule();
        assertFalse(val);
    } 
    
    /**
     * This method is test case of time before while it adds new schedule for false, with minutes out of range.
     * @throws Exception
     */
    @Test
    public void testProcessRuleFalseMinutesOutOfBoundsWithScheduleData() throws Exception {        
        prerequisiteScheduleData();
        scheduleData.getTime().setTime(TIME_10_100);
        boolean val = executeRule();
        assertFalse(val);
    } 
    
    /**
     * This method is test case of time before while it adds new schedule for false, with hours out of range.
     * @throws Exception
     */
    @Test
    public void testProcessRuleFalseHourOutOfBoundsWithScheduleData() throws Exception {        
        prerequisiteScheduleData();
        scheduleData.getTime().setTime(TIME_100_10);
        boolean val = executeRule();
        assertFalse(val);
    } 
    
    
    /**
     * This method is test case of time before while it adds new schedule for false, with time hours out of range.
     * @throws Exception
     */
    @Test
    public void testProcessRuleFalseHourSetTo0WithScheduleData() throws Exception {        
        prerequisiteScheduleData();
        scheduleData.getTime().setTime(TIME_0_10);
        boolean val = executeRule();
        assertFalse(val);
    } 
    
    /**
     * This method is helper method to test cases to set prerequisite for schedule data.
     * @throws ParseException 
     */
    private void prerequisiteScheduleData() {
        scheduleData = new ScheduleData();
        Time12HrFmt time = new Time12HrFmt(new Timestamp(new java.util.Date().getTime()));
        time.setMeridiem(Time12HrFmt.MERIDIEM.PM.toString());
        scheduleData.setTime(time);
        event = new CommitteeScheduleTimeEvent(Constants.EMPTY_STRING, null, scheduleData, null, ErrorType.HARDERROR);
    }
    
    /**
     * This method is test case of time before while it adds new schedule.
     * @throws Exception
     */
    @Test
    public void testProcessRuleTrueWithCommitteeSchedule() throws Exception {        
        prerequisiteCommitteeScheduleData(); 
        committeeSchedules.get(0).getViewTime().setTime(TIME_10_10);     
        boolean val = executeRule();
        assertTrue(val);
    }
    
    /**
     * This method is test case of time before while it adds new schedule with blank time.
     * @throws Exception
     */
    @Test
    public void testProcessRuleFalseTimeBlankWithCommitteeSchedule() throws Exception {        
        prerequisiteCommitteeScheduleData(); 
        committeeSchedules.get(0).getViewTime().setTime(TIME_);     
        boolean val = executeRule();
        assertFalse(val);
    }
    
    /**
     * This method is test case of time before while it adds new schedule with minutes out of range.
     * @throws Exception
     */
    @Test
    public void testProcessRuleFalseMinutesOutOfBoundsWithCommitteeSchedule() throws Exception {        
        prerequisiteCommitteeScheduleData(); 
        committeeSchedules.get(0).getViewTime().setTime(TIME_10_100);     
        boolean val = executeRule();
        assertFalse(val);
    }
    
    /**
     * This method is test case of time before while it adds new schedule with hours out of range.
     * @throws Exception
     */
    @Test
    public void testProcessRuleFalseHoursOutOfBoundsWithCommitteeSchedule() throws Exception {        
        prerequisiteCommitteeScheduleData(); 
        committeeSchedules.get(0).getViewTime().setTime(TIME_100_10);     
        boolean val = executeRule();
        assertFalse(val);
    }
    
    /**
     * This method is test case of time before while it adds new schedule with hours out of range.
     * @throws Exception
     */
    @Test
    public void testProcessRuleFalseHourSetTo0WithCommitteeSchedule() throws Exception {        
        prerequisiteCommitteeScheduleData(); 
        committeeSchedules.get(0).getViewTime().setTime(TIME_0_10);     
        boolean val = executeRule();
        assertFalse(val);
    }
    /**
     * This method is helper method to test cases to set prerequisite for committeeSchedule.
     * @throws ParseException 
     */
    private void prerequisiteCommitteeScheduleData() {
        committeeSchedules = new ArrayList<CommitteeSchedule>();
        committeeSchedules.add(new CommitteeSchedule());
        Time12HrFmt time = new Time12HrFmt(new Timestamp(new java.util.Date().getTime()));
        time.setMeridiem(Time12HrFmt.MERIDIEM.PM.toString());
        committeeSchedules.get(0).setViewTime(time);
        event = new CommitteeScheduleTimeEvent(Constants.EMPTY_STRING, null, null, committeeSchedules, ErrorType.HARDERROR);
    }
    
    /**
     * This method executes rule.
     * @return
     */
    private boolean executeRule() {
        rule = new CommitteeScheduleTimeRule();
        boolean val = rule.processRules(event);
        return val;
    }
}

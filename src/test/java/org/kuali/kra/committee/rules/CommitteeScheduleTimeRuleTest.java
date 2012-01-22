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

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.rule.event.CommitteeScheduleTimeEvent;
import org.kuali.kra.committee.rule.event.CommitteeScheduleEventBase.ErrorType;
import org.kuali.kra.committee.web.struts.form.schedule.ScheduleData;
import org.kuali.kra.committee.web.struts.form.schedule.Time12HrFmt;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

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

    public static String TIME_NON_NUMBERS = "not a number";
    
    @SuppressWarnings("unchecked")
    @Before
    public void setUp(){
        GlobalVariables.setMessageMap(new MessageMap());
        KNSGlobalVariables.setAuditErrorMap(new HashMap());  
    }
    
    /**
     * This method checks schedule data whose time is valid.
     */
    @Test
    public void testProcessRuleTrueWithScheduleData() {        
        prerequisiteScheduleData();
        scheduleData.getTime().setTime(TIME_10_10);        
        boolean val = executeRule();
        assertTrue(val);
    }    
    
    /**
     * This method checks schedule data which has blank time.
     */
    @Test
    public void testProcessRuleFalseTimeBlankWithScheduleData() {        
        prerequisiteScheduleData();
        scheduleData.getTime().setTime(TIME_);
        boolean val = executeRule();
        assertFalse(val);        
        Assert.assertEquals("error.committeeSchedule.viewTime.blank" + "()",
                GlobalVariables.getMessageMap().getErrorMessages().get("committeeHelper.scheduleData.time.time").get(0).toString());
    } 
    
    /**
     * This method checks schedule data whose time has minutes out of bounds.
     */
    @Test
    public void testProcessRuleFalseMinutesOutOfBoundsWithScheduleData() {        
        prerequisiteScheduleData();
        scheduleData.getTime().setTime(TIME_10_100);
        boolean val = executeRule();
        assertFalse(val);
        Assert.assertEquals("error.committeeSchedule.viewTime.formatting" + "(" + TIME_10_100 + ", " + "mm as 0-59" + ")", 
                GlobalVariables.getMessageMap().getErrorMessages().get("committeeHelper.scheduleData.time.time").get(0).toString());
    } 
    
    /**
     * This method checks schedule data whose time has hours out of bounds.
     */
    @Test
    public void testProcessRuleFalseHourOutOfBoundsWithScheduleData() throws Exception {        
        prerequisiteScheduleData();
        scheduleData.getTime().setTime(TIME_100_10);
        boolean val = executeRule();
        assertFalse(val);
        Assert.assertEquals("error.committeeSchedule.viewTime.formatting" + "(" + TIME_100_10 + ", " + "hh as 1-12" + ")", 
                GlobalVariables.getMessageMap().getErrorMessages().get("committeeHelper.scheduleData.time.time").get(0).toString());
    } 
    
    
    /**
     * This method checks schedule data whose time has hours set to zero.
     */
    @Test
    public void testProcessRuleFalseHourSetTo0WithScheduleData() {        
        prerequisiteScheduleData();
        scheduleData.getTime().setTime(TIME_0_10);
        boolean val = executeRule();
        assertFalse(val);
        Assert.assertEquals("error.committeeSchedule.viewTime.formatting" + "(" + TIME_0_10 + ", " + "hh as 1-12" + ")", 
                GlobalVariables.getMessageMap().getErrorMessages().get("committeeHelper.scheduleData.time.time").get(0).toString());
    }
    
    /**
     * This method checks schedule data whose time has non-numbers without colon.
     */
    @Test
    public void testProcessRuleFalseHoursMinutesSetToNonNumbersWithScheduleData() {        
        prerequisiteScheduleData();
        scheduleData.getTime().setTime(TIME_NON_NUMBERS);
        boolean val = executeRule();
        assertFalse(val);
        Assert.assertEquals("error.committeeSchedule.viewTime.formatting" + "(" + TIME_NON_NUMBERS + ", " + "hh:mm" + ")", 
                GlobalVariables.getMessageMap().getErrorMessages().get("committeeHelper.scheduleData.time.time").get(0).toString());
    }
    
    /**
     * This method checks schedule data whose time has non-numbers with colon.
     */
    @Test
    public void testProcessRuleFalseHoursMinutesSetToNonNumbersWithColonWithScheduleData() {        
        prerequisiteScheduleData();
        scheduleData.getTime().setTime(TIME_NON_NUMBERS + ":" + TIME_NON_NUMBERS);
        boolean val = executeRule();
        assertFalse(val);
        Assert.assertEquals("error.committeeSchedule.viewTime.formatting" + 
                "(" + TIME_NON_NUMBERS + ":" + TIME_NON_NUMBERS + ", " + "hh as 1-12 &amp; mm as 0-59" + ")", 
                GlobalVariables.getMessageMap().getErrorMessages().get("committeeHelper.scheduleData.time.time").get(0).toString());
    }
    
    
    /**
     * This method is helper method for test cases to set prerequisite for schedule data.
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
        boolean val = executeRule();
        assertTrue(val);
        Assert.assertEquals(0, GlobalVariables.getMessageMap().getErrorMessages().size());
    }
    
    
    /**
     * This method is test case of time before while it adds new schedule with blank time.
     * @throws Exception
     */
    @Test
    public void testProcessRuleFalseTimeBlankWithCommitteeSchedule() {        
        prerequisiteCommitteeScheduleData(); 
        committeeSchedules.get(0).getViewTime().setTime(TIME_);
        committeeSchedules.get(2).getViewTime().setTime(TIME_);
        boolean val = executeRule();
        assertFalse(val);
        Assert.assertEquals(2, GlobalVariables.getMessageMap().getErrorMessages().size());
        Assert.assertTrue(GlobalVariables.getMessageMap().getErrorMessages().containsKey("document.committeeList[0].committeeSchedules[0].viewTime.time"));
        Assert.assertTrue(GlobalVariables.getMessageMap().getErrorMessages().containsKey("document.committeeList[0].committeeSchedules[2].viewTime.time"));
        Assert.assertEquals("error.committeeSchedule.viewTime.blank" +"()",
                GlobalVariables.getMessageMap().getErrorMessages().get("document.committeeList[0].committeeSchedules[0].viewTime.time").get(0).toString());
        Assert.assertEquals("error.committeeSchedule.viewTime.blank" +"()",
                GlobalVariables.getMessageMap().getErrorMessages().get("document.committeeList[0].committeeSchedules[2].viewTime.time").get(0).toString());
    }
    
    /**
     * This method is test case of time before while it adds new schedule with minutes out of range.
     * @throws Exception
     */
    @Test
    public void testProcessRuleFalseWithBlankAndMinutesOutOfBoundsWithCommitteeSchedule() {        
        prerequisiteCommitteeScheduleData();
        committeeSchedules.get(1).getViewTime().setTime(TIME_);
        committeeSchedules.get(4).getViewTime().setTime(TIME_10_100);     
        boolean val = executeRule();
        assertFalse(val);
        Assert.assertEquals(2, GlobalVariables.getMessageMap().getErrorMessages().size());
        Assert.assertTrue(GlobalVariables.getMessageMap().getErrorMessages().containsKey("document.committeeList[0].committeeSchedules[1].viewTime.time"));
        Assert.assertTrue(GlobalVariables.getMessageMap().getErrorMessages().containsKey("document.committeeList[0].committeeSchedules[4].viewTime.time"));
        Assert.assertEquals("error.committeeSchedule.viewTime.blank" +"()",
                GlobalVariables.getMessageMap().getErrorMessages().get("document.committeeList[0].committeeSchedules[1].viewTime.time").get(0).toString());
        Assert.assertEquals("error.committeeSchedule.viewTime.formatting" + "(" + TIME_10_100 + ", " + "mm as 0-59" + ")",
                GlobalVariables.getMessageMap().getErrorMessages().get("document.committeeList[0].committeeSchedules[4].viewTime.time").get(0).toString());
    }
    
    /**
     * This method is test case of time before while it adds new schedule with hours out of range.
     * @throws Exception
     */
    @Test
    public void testProcessRuleFalseHoursOutOfBoundsAndHourSetTo0AndBlankWithCommitteeSchedule() {        
        prerequisiteCommitteeScheduleData(); 
        committeeSchedules.get(0).getViewTime().setTime(TIME_100_10);
        committeeSchedules.get(2).getViewTime().setTime(TIME_0_10);  
        committeeSchedules.get(3).getViewTime().setTime(TIME_);  
        boolean val = executeRule();
        assertFalse(val);
        Assert.assertEquals(3, GlobalVariables.getMessageMap().getErrorMessages().size());
        Assert.assertTrue(GlobalVariables.getMessageMap().getErrorMessages().containsKey("document.committeeList[0].committeeSchedules[0].viewTime.time"));
        Assert.assertTrue(GlobalVariables.getMessageMap().getErrorMessages().containsKey("document.committeeList[0].committeeSchedules[2].viewTime.time"));
        Assert.assertTrue(GlobalVariables.getMessageMap().getErrorMessages().containsKey("document.committeeList[0].committeeSchedules[3].viewTime.time"));
        Assert.assertEquals("error.committeeSchedule.viewTime.formatting" + "(" + TIME_100_10 + ", " + "hh as 1-12" + ")", 
                GlobalVariables.getMessageMap().getErrorMessages().get("document.committeeList[0].committeeSchedules[0].viewTime.time").get(0).toString());
        Assert.assertEquals("error.committeeSchedule.viewTime.formatting" + "(" + TIME_0_10 + ", " + "hh as 1-12" + ")", 
                GlobalVariables.getMessageMap().getErrorMessages().get("document.committeeList[0].committeeSchedules[2].viewTime.time").get(0).toString());
        Assert.assertEquals("error.committeeSchedule.viewTime.blank" +"()", 
                GlobalVariables.getMessageMap().getErrorMessages().get("document.committeeList[0].committeeSchedules[3].viewTime.time").get(0).toString());
    }
    
    
    /**
     * This method is test case of time before while it adds new schedule with hours out of range.
     * @throws Exception
     */
    @Test
    public void testProcessRuleFalseNonNumbersWithAndWithoutColonsWithCommitteeSchedule() {        
        prerequisiteCommitteeScheduleData(); 
        committeeSchedules.get(2).getViewTime().setTime(TIME_NON_NUMBERS + ":" + TIME_NON_NUMBERS);
        committeeSchedules.get(4).getViewTime().setTime(TIME_NON_NUMBERS);
        boolean val = executeRule();
        assertFalse(val);
        Assert.assertEquals(2, GlobalVariables.getMessageMap().getErrorMessages().size());
        Assert.assertEquals("error.committeeSchedule.viewTime.formatting" + 
                "(" + TIME_NON_NUMBERS + ":" + TIME_NON_NUMBERS + ", " + "hh as 1-12 &amp; mm as 0-59" + ")", 
                GlobalVariables.getMessageMap().getErrorMessages().get("document.committeeList[0].committeeSchedules[2].viewTime.time").get(0).toString());
        Assert.assertEquals("error.committeeSchedule.viewTime.formatting" + "(" + TIME_NON_NUMBERS + ", " + "hh:mm" + ")", 
                GlobalVariables.getMessageMap().getErrorMessages().get("document.committeeList[0].committeeSchedules[4].viewTime.time").get(0).toString());
    }
    
    
    /**
     * This method is helper method to test cases to set prerequisite for committeeSchedule.
     * @throws ParseException 
     */
    private void prerequisiteCommitteeScheduleData() {
        // add 5 schedules
        committeeSchedules = new ArrayList<CommitteeSchedule>();
        committeeSchedules.add(new CommitteeSchedule());
        committeeSchedules.add(new CommitteeSchedule());
        committeeSchedules.add(new CommitteeSchedule());
        committeeSchedules.add(new CommitteeSchedule());
        committeeSchedules.add(new CommitteeSchedule());
        // set the default view time for all of them
        for(CommitteeSchedule schedule: committeeSchedules) {
            Time12HrFmt time = new Time12HrFmt(new Timestamp(new java.util.Date().getTime()));
            time.setMeridiem(Time12HrFmt.MERIDIEM.PM.toString());
            schedule.setViewTime(time);
        }
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

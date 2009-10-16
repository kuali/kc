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
package org.kuali.kra.meeting;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.web.struts.form.schedule.Time12HrFmt;
import org.kuali.kra.committee.web.struts.form.schedule.Time12HrFmt.MERIDIEM;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.util.GlobalVariables;

public class MeetingRuleTest extends KraTestBase {
    // need extends Kratestbase for businessservice called in rules.

    private MeetingDetailsRule rule;
    private Time12HrFmt viewStartTime;
    private Time12HrFmt viewEndTime;
    private Time12HrFmt viewTime;

    @Before
    public void setUp() {
        viewTime = new Time12HrFmt("01:00", MERIDIEM.PM);
        viewStartTime = new Time12HrFmt("01:00", MERIDIEM.PM);
        viewEndTime = new Time12HrFmt("01:00", MERIDIEM.PM);
        rule = new MeetingDetailsRule();
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
    }

    @Test 
    public void testRuleIsOK() throws Exception {
        CommitteeSchedule committeeSchedule = new CommitteeSchedule();
        committeeSchedule.setViewStartTime(viewStartTime);
        committeeSchedule.setViewEndTime(viewEndTime);
        committeeSchedule.setViewTime(viewTime);
        Assert.assertTrue(rule.validateMeetingDetails(committeeSchedule));
        
      viewStartTime.setTime("12:00");
      Assert.assertTrue(rule.validateMeetingDetails(committeeSchedule));

      viewStartTime.setTime("01:00");
      viewEndTime.setTime("02:30");
      Assert.assertTrue(rule.validateMeetingDetails(committeeSchedule));

    }
    
    
    @Test 
    public void testViewTimeIsNotOK() throws Exception {
        testTimeIsNotFormatOk(viewTime);
    }
    
    @Test 
    public void testViewStartTimeIsNotOK() throws Exception {
        testTimeIsNotFormatOk(viewStartTime);

    }
    @Test 
    public void testViewEndTimeIsNotOK() throws Exception {
        testTimeIsNotFormatOk(viewEndTime);

    }
    
    @Test 
    public void testViewEndTimeBeforeViewStartTime() throws Exception {
        CommitteeSchedule committeeSchedule = new CommitteeSchedule();
        committeeSchedule.setViewStartTime(viewStartTime);
        committeeSchedule.setViewEndTime(viewEndTime);
        committeeSchedule.setViewTime(viewTime);
        
        viewEndTime.setMeridiem("AM");
        Assert.assertFalse(rule.validateMeetingDetails(committeeSchedule));
        Assert.assertTrue(GlobalVariables.getMessageMap().containsMessageKey(KeyConstants.ERROR_COMMITTEESCHEDULE_ENDTIME_BEFORE_STARTTIME));       

        viewEndTime.setMeridiem("PM");
        viewEndTime.setTime("12:30");
        Assert.assertFalse(rule.validateMeetingDetails(committeeSchedule));
        Assert.assertTrue(GlobalVariables.getMessageMap().containsMessageKey(KeyConstants.ERROR_COMMITTEESCHEDULE_ENDTIME_BEFORE_STARTTIME));       
        viewEndTime.setTime("01:30");
        viewStartTime.setTime("02:30");
        Assert.assertFalse(rule.validateMeetingDetails(committeeSchedule));
        Assert.assertTrue(GlobalVariables.getMessageMap().containsMessageKey(KeyConstants.ERROR_COMMITTEESCHEDULE_ENDTIME_BEFORE_STARTTIME));       

    }

    
    @Test 
    public void testvalidateNewOther() throws Exception {
        OtherPresentBean newOtherPresentBean = new OtherPresentBean();
        CommitteeScheduleAttendance attendance = new CommitteeScheduleAttendance();
        newOtherPresentBean.setAttendance(attendance);
        
        MeetingHelper meetingHelper = new MeetingHelper(new MeetingForm());
        meetingHelper.setNewOtherPresentBean(newOtherPresentBean);
        meetingHelper.setMemberPresentBeans(new ArrayList<MemberPresentBean>());
        
        Assert.assertFalse(rule.validateNewOther(meetingHelper));
        Assert.assertTrue(GlobalVariables.getMessageMap().containsMessageKey(KeyConstants.ERROR_EMPTY_PERSON));       
        attendance.setPersonName("tester 1");
        Assert.assertTrue(rule.validateNewOther(meetingHelper));
        meetingHelper.getMemberPresentBeans().add(getMemberPresent("001", "tester 1"));
        newOtherPresentBean.getAttendance().setPersonId("001");
        // member present found
        newOtherPresentBean.getAttendance().setNonEmployeeFlag(false);
        Assert.assertFalse(rule.validateNewOther(meetingHelper));
        Assert.assertTrue(GlobalVariables.getMessageMap().containsMessageKey(KeyConstants.ERROR_ADD_MEMBER_PRESENT));       
        // personid is the same but one is non-employee, the other is not
        newOtherPresentBean.getAttendance().setNonEmployeeFlag(true);
        Assert.assertTrue(rule.validateNewOther(meetingHelper));
        // personid is not matched
        newOtherPresentBean.getAttendance().setPersonId("002");
        newOtherPresentBean.getAttendance().setNonEmployeeFlag(false);
        Assert.assertTrue(rule.validateNewOther(meetingHelper));
        
        // person matched alternatefor
        meetingHelper.getMemberPresentBeans().get(0).getAttendance().setAlternateFor("002");
        Assert.assertFalse(rule.validateNewOther(meetingHelper));
        Assert.assertTrue(GlobalVariables.getMessageMap().containsMessageKey(KeyConstants.ERROR_ADD_MEMBER_PRESENT));       
        
    }
    
    @Test 
    public void testValidateNotAlternateFor() throws Exception {
        MemberAbsentBean memberAbsentBean = new MemberAbsentBean();
        CommitteeScheduleAttendance attendance = new CommitteeScheduleAttendance();
        attendance.setPersonName("tester 2");
        attendance.setPersonId("002");
        memberAbsentBean.setAttendance(attendance);
        
        MeetingHelper meetingHelper = new MeetingHelper(new MeetingForm());
        meetingHelper.setMemberPresentBeans(new ArrayList<MemberPresentBean>());
        
        meetingHelper.getMemberPresentBeans().add(getMemberPresent("001", "tester 1"));
        Assert.assertTrue(rule.validateNotAlternateFor(meetingHelper.getMemberPresentBeans(), memberAbsentBean));
        meetingHelper.getMemberPresentBeans().get(0).getAttendance().setAlternateFor("002");
        meetingHelper.getMemberPresentBeans().get(0).getAttendance().setAlternateFlag(true);
        Assert.assertFalse(rule.validateNotAlternateFor(meetingHelper.getMemberPresentBeans(), memberAbsentBean));
        Assert.assertTrue(GlobalVariables.getMessageMap().containsMessageKey(KeyConstants.ERROR_PRESENT_MEMBER_ABSENT));       

        
        
    }
    

    @Test 
    public void testValidateDuplicateAlternateFor() throws Exception {
        
        MeetingHelper meetingHelper = new MeetingHelper(new MeetingForm());
        meetingHelper.setMemberPresentBeans(new ArrayList<MemberPresentBean>());
        
        meetingHelper.getMemberPresentBeans().add(getMemberPresent("001", "tester 1"));
        meetingHelper.getMemberPresentBeans().add(getMemberPresent("002", "tester 2"));
        // Both alternate for are null
        Assert.assertTrue(rule.validateDuplicateAlternateFor(meetingHelper.getMemberPresentBeans()));
        
        meetingHelper.getMemberPresentBeans().get(0).getAttendance().setAlternateFor("003");
        meetingHelper.getMemberPresentBeans().get(0).getAttendance().setAlternateFlag(true);
        // one is null, the otehr is '003'
        Assert.assertTrue(rule.validateDuplicateAlternateFor(meetingHelper.getMemberPresentBeans()));
        meetingHelper.getMemberPresentBeans().get(1).getAttendance().setAlternateFor("004");
        meetingHelper.getMemberPresentBeans().get(1).getAttendance().setAlternateFlag(true);
        // one  '004', the other is '003'
        Assert.assertTrue(rule.validateDuplicateAlternateFor(meetingHelper.getMemberPresentBeans()));
        meetingHelper.getMemberPresentBeans().get(1).getAttendance().setAlternateFor("003");
        // both '003'
        Assert.assertFalse(rule.validateDuplicateAlternateFor(meetingHelper.getMemberPresentBeans()));
        Assert.assertTrue(GlobalVariables.getMessageMap().containsMessageKey(KeyConstants.ERROR_PRESENT_MEMBER_ABSENT));       
        
    }

    @Test 
    public void testValidateProtocolInMinute() throws Exception {
        CommitteeScheduleMinute committeeScheduleMinute = new CommitteeScheduleMinute();
        committeeScheduleMinute.setMinuteEntryTypeCode("3");
        
        Assert.assertFalse(rule.validateProtocolInMinute(committeeScheduleMinute));
        Assert.assertTrue(GlobalVariables.getMessageMap().containsMessageKey(KeyConstants.ERROR_EMPTY_PROTOCOL));       
        committeeScheduleMinute.setProtocolContingencyCode("1");
        Assert.assertFalse(rule.validateProtocolInMinute(committeeScheduleMinute));
        Assert.assertTrue(GlobalVariables.getMessageMap().containsMessageKey(KeyConstants.ERROR_EMPTY_PROTOCOL));       
        committeeScheduleMinute.setProtocolContingencyCode("111");
        Assert.assertFalse(rule.validateProtocolInMinute(committeeScheduleMinute));
        Assert.assertTrue(GlobalVariables.getMessageMap().containsMessageKey(KeyConstants.ERROR_EMPTY_PROTOCOL));  
        Assert.assertTrue(GlobalVariables.getMessageMap().containsMessageKey(KeyConstants.ERROR_EMPTY_PROTOCOL_CONTINGENCY));       
        committeeScheduleMinute.setProtocolIdFk(1L);
        Assert.assertFalse(rule.validateProtocolInMinute(committeeScheduleMinute));
        Assert.assertTrue(GlobalVariables.getMessageMap().containsMessageKey(KeyConstants.ERROR_EMPTY_PROTOCOL_CONTINGENCY));       
        committeeScheduleMinute.setProtocolContingencyCode("1");
        Assert.assertTrue(rule.validateProtocolInMinute(committeeScheduleMinute));

        
    }

    private MemberPresentBean getMemberPresent(String personId, String personName) {
        MemberPresentBean memberPresentBean = new MemberPresentBean();
        CommitteeScheduleAttendance attendance = new CommitteeScheduleAttendance();
        attendance.setNonEmployeeFlag(false);
        attendance.setPersonId(personId);
        attendance.setPersonName(personName);
        memberPresentBean.setAttendance(attendance);
        return memberPresentBean;
        

    }
    private void testTimeIsNotFormatOk(Time12HrFmt time) {
        CommitteeSchedule committeeSchedule = new CommitteeSchedule();
        committeeSchedule.setViewStartTime(viewStartTime);
        committeeSchedule.setViewEndTime(viewEndTime);
        committeeSchedule.setViewTime(viewTime);
        
        time.setTime("13:00");
      Assert.assertFalse(rule.validateMeetingDetails(committeeSchedule));
      Assert.assertTrue(GlobalVariables.getMessageMap().containsMessageKey(KeyConstants.ERROR_COMMITTEESCHEDULE_VIEWTIME));       

      time.setTime("12:61");
      Assert.assertFalse(rule.validateMeetingDetails(committeeSchedule));
      Assert.assertTrue(GlobalVariables.getMessageMap().containsMessageKey(KeyConstants.ERROR_COMMITTEESCHEDULE_VIEWTIME));       
      time.setTime("13:61");
      Assert.assertFalse(rule.validateMeetingDetails(committeeSchedule));
      Assert.assertTrue(GlobalVariables.getMessageMap().containsMessageKey(KeyConstants.ERROR_COMMITTEESCHEDULE_VIEWTIME));       
        
    }

}

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
package org.kuali.kra.meeting;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.web.struts.form.schedule.Time12HrFmt;
import org.kuali.kra.committee.web.struts.form.schedule.Time12HrFmt.MERIDIEM;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.meeting.MeetingEventBase.ErrorType;
import org.kuali.kra.rules.TemplateRuleTest;

public class MeetingSaveRuleTest {
    
    private Time12HrFmt viewStartTime;
    private Time12HrFmt viewEndTime;
    private Time12HrFmt viewTime;
    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    @Before
    public void setUp() throws Exception {
        viewTime = new Time12HrFmt("01:00", MERIDIEM.PM);
        viewStartTime = new Time12HrFmt("01:00", MERIDIEM.PM);
        viewEndTime = new Time12HrFmt("01:00", MERIDIEM.PM);
    }

    @Test
    public void testOK() {    
        new  TemplateRuleTest<MeetingSaveEvent, MeetingSaveRule> (){            
            @Override
            protected void prerequisite() {            
                MeetingHelper meetingHelper = new MeetingHelper(new MeetingForm());
                meetingHelper.setMemberPresentBeans(new ArrayList<MemberPresentBean>());
                
                meetingHelper.getMemberPresentBeans().add(getMemberPresent("001", "tester 1"));
                meetingHelper.getMemberPresentBeans().add(getMemberPresent("002", "tester 2"));

                CommitteeSchedule committeeSchedule = new CommitteeSchedule();
                committeeSchedule.setViewStartTime(viewStartTime);
                committeeSchedule.setViewEndTime(viewEndTime);
                committeeSchedule.setViewTime(viewTime);
                meetingHelper.setCommitteeSchedule(committeeSchedule);
                event = new MeetingSaveEvent(Constants.EMPTY_STRING, null, meetingHelper,  ErrorType.HARDERROR);
                rule = new MeetingSaveRule();
                expectedReturnValue = true;
            }
       };
 
    
    }

    @Test
    public void testTimeFormatIsNotOk() {    
        new  TemplateRuleTest<MeetingSaveEvent, MeetingSaveRule> (){            
            @Override
            protected void prerequisite() {            
                MeetingHelper meetingHelper = new MeetingHelper(new MeetingForm());
                meetingHelper.setMemberPresentBeans(new ArrayList<MemberPresentBean>());
                
                meetingHelper.getMemberPresentBeans().add(getMemberPresent("001", "tester 1"));
                meetingHelper.getMemberPresentBeans().add(getMemberPresent("002", "tester 2"));

                CommitteeSchedule committeeSchedule = new CommitteeSchedule();
                committeeSchedule.setViewStartTime(viewStartTime);
                committeeSchedule.setViewEndTime(viewEndTime);
                committeeSchedule.setViewTime(viewTime);
                
                viewTime.setTime("13:00");

                viewEndTime.setTime("12:61");
                meetingHelper.setCommitteeSchedule(committeeSchedule);
                event = new MeetingSaveEvent(Constants.EMPTY_STRING, null, meetingHelper,  ErrorType.HARDERROR);
                rule = new MeetingSaveRule();
                expectedReturnValue = false;
            }
       };
 
    
    }

    @Test
    public void testEndTimeBeforeStartTime() {    
        new  TemplateRuleTest<MeetingSaveEvent, MeetingSaveRule> (){            
            @Override
            protected void prerequisite() {            
                MeetingHelper meetingHelper = new MeetingHelper(new MeetingForm());
                meetingHelper.setMemberPresentBeans(new ArrayList<MemberPresentBean>());
                
                meetingHelper.getMemberPresentBeans().add(getMemberPresent("001", "tester 1"));
                meetingHelper.getMemberPresentBeans().add(getMemberPresent("002", "tester 2"));

                CommitteeSchedule committeeSchedule = new CommitteeSchedule();
                committeeSchedule.setViewStartTime(viewStartTime);
                committeeSchedule.setViewEndTime(viewEndTime);
                committeeSchedule.setViewTime(viewTime);
                
                viewEndTime.setMeridiem("AM");

                viewEndTime.setMeridiem("PM");
                viewEndTime.setTime("12:30");
                viewEndTime.setTime("01:30");
                viewStartTime.setTime("02:30");
                meetingHelper.setCommitteeSchedule(committeeSchedule);
                event = new MeetingSaveEvent(Constants.EMPTY_STRING, null, meetingHelper,  ErrorType.HARDERROR);
                rule = new MeetingSaveRule();
                expectedReturnValue = false;
            }
       };
 
    
    }

    @Test
    public void testSubmissionDeadlineBeforeScheduledDate() {    
        new  TemplateRuleTest<MeetingSaveEvent, MeetingSaveRule> (){            
            @Override
            protected void prerequisite() {            
                MeetingHelper meetingHelper = new MeetingHelper(new MeetingForm());
                meetingHelper.setMemberPresentBeans(new ArrayList<MemberPresentBean>());
                
                meetingHelper.getMemberPresentBeans().add(getMemberPresent("001", "tester 1"));
                meetingHelper.getMemberPresentBeans().add(getMemberPresent("002", "tester 2"));

                CommitteeSchedule committeeSchedule = new CommitteeSchedule();
                committeeSchedule.setViewStartTime(viewStartTime);
                committeeSchedule.setViewEndTime(viewEndTime);
                committeeSchedule.setViewTime(viewTime);
                try {
                    committeeSchedule.setScheduledDate(new Date(dateFormat.parse("12/24/2012").getTime()));
                    committeeSchedule.setProtocolSubDeadline(new Date(dateFormat.parse("12/25/2012").getTime()));
                } catch (Exception e) {
                    
                }
                meetingHelper.setCommitteeSchedule(committeeSchedule);
                event = new MeetingSaveEvent(Constants.EMPTY_STRING, null, meetingHelper,  ErrorType.HARDERROR);
                rule = new MeetingSaveRule();
                expectedReturnValue = false;
            }
       };
 
    
    }

    @Test
    public void testDuplicateAlternateFor() {    
        new  TemplateRuleTest<MeetingSaveEvent, MeetingSaveRule> (){            
            @Override
            protected void prerequisite() {            
                MeetingHelper meetingHelper = new MeetingHelper(new MeetingForm());
                meetingHelper.setMemberPresentBeans(new ArrayList<MemberPresentBean>());
                
                meetingHelper.getMemberPresentBeans().add(getMemberPresent("001", "tester 1"));
                meetingHelper.getMemberPresentBeans().add(getMemberPresent("002", "tester 2"));
                // Both alternate for are null
                
                meetingHelper.getMemberPresentBeans().get(0).getAttendance().setAlternateFor("003");
                meetingHelper.getMemberPresentBeans().get(0).getAttendance().setAlternateFlag(true);
                // one is null, the otehr is '003'
                meetingHelper.getMemberPresentBeans().get(1).getAttendance().setAlternateFor("004");
                meetingHelper.getMemberPresentBeans().get(1).getAttendance().setAlternateFlag(true);
                // one  '004', the other is '003'
                meetingHelper.getMemberPresentBeans().get(1).getAttendance().setAlternateFor("003");

                CommitteeSchedule committeeSchedule = new CommitteeSchedule();
                committeeSchedule.setViewStartTime(viewStartTime);
                committeeSchedule.setViewEndTime(viewEndTime);
                committeeSchedule.setViewTime(viewTime);
                meetingHelper.setCommitteeSchedule(committeeSchedule);
                event = new MeetingSaveEvent(Constants.EMPTY_STRING, null, meetingHelper,  ErrorType.HARDERROR);
                rule = new MeetingSaveRule();
                expectedReturnValue = false;
            }
       };
 
    
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

}

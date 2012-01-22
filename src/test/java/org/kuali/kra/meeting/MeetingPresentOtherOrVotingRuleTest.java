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

import java.util.ArrayList;

import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.meeting.MeetingEventBase.ErrorType;
import org.kuali.kra.rules.TemplateRuleTest;

public class MeetingPresentOtherOrVotingRuleTest {
    @Test
    public void testOK() {    
        new  TemplateRuleTest<MeetingPresentOtherOrVotingEvent, MeetingPresentOtherOrVotingRule> (){            
            @Override
            protected void prerequisite() {            
                MemberAbsentBean memberAbsentBean = new MemberAbsentBean();
                CommitteeScheduleAttendance attendance = new CommitteeScheduleAttendance();
                attendance.setPersonName("tester 2");
                attendance.setPersonId("002");
                memberAbsentBean.setAttendance(attendance);
                
                MeetingHelper meetingHelper = new MeetingHelper(new MeetingForm());
                meetingHelper.setMemberPresentBeans(new ArrayList<MemberPresentBean>());
                
                meetingHelper.getMemberPresentBeans().add(getMemberPresent("001", "tester 1"));
                event = new MeetingPresentOtherOrVotingEvent(Constants.EMPTY_STRING, null, meetingHelper, memberAbsentBean, ErrorType.HARDERROR);
                rule = new MeetingPresentOtherOrVotingRule();
                expectedReturnValue = true;
            }
       };
 
    
    }
    
    @Test
    public void testNotOK() {    
        new  TemplateRuleTest<MeetingPresentOtherOrVotingEvent, MeetingPresentOtherOrVotingRule> (){            
            @Override
            protected void prerequisite() {            
                MemberAbsentBean memberAbsentBean = new MemberAbsentBean();
                CommitteeScheduleAttendance attendance = new CommitteeScheduleAttendance();
                attendance.setPersonName("tester 2");
                attendance.setPersonId("002");
                memberAbsentBean.setAttendance(attendance);
                
                MeetingHelper meetingHelper = new MeetingHelper(new MeetingForm());
                meetingHelper.setMemberPresentBeans(new ArrayList<MemberPresentBean>());
                
                meetingHelper.getMemberPresentBeans().add(getMemberPresent("001", "tester 1"));
                meetingHelper.getMemberPresentBeans().get(0).getAttendance().setAlternateFor("002");
                meetingHelper.getMemberPresentBeans().get(0).getAttendance().setAlternateFlag(true);
                event = new MeetingPresentOtherOrVotingEvent(Constants.EMPTY_STRING, null, meetingHelper, memberAbsentBean, ErrorType.HARDERROR);
                rule = new MeetingPresentOtherOrVotingRule();
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

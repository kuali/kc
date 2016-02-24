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
package org.kuali.kra.meeting;

import org.junit.Test;
import org.kuali.coeus.common.committee.impl.meeting.MeetingAddOtherEvent;
import org.kuali.coeus.common.committee.impl.meeting.MeetingAddOtherRule;
import org.kuali.coeus.common.committee.impl.meeting.MemberPresentBean;
import org.kuali.coeus.common.committee.impl.meeting.MeetingEventBase.ErrorType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.rules.TemplateRuleTest;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;

import java.util.ArrayList;


public class MeetingAddOtherRuleTest extends KcIntegrationTestBase {

    @Test
    public void testOK() {    
        new  TemplateRuleTest<MeetingAddOtherEvent, MeetingAddOtherRule> (){            
            @Override
            protected void prerequisite() {            
                OtherPresentBean newOtherPresentBean = new OtherPresentBean();
                CommitteeScheduleAttendance attendance = new CommitteeScheduleAttendance();
                newOtherPresentBean.setAttendance(attendance);
                
                MeetingHelper meetingHelper = new MeetingHelper(new MeetingForm());
                meetingHelper.setNewOtherPresentBean(newOtherPresentBean);
                meetingHelper.setMemberPresentBeans(new ArrayList<MemberPresentBean>());
                
                attendance.setPersonName("tester 1");
                event = new MeetingAddOtherEvent(Constants.EMPTY_STRING, null, meetingHelper, ErrorType.HARDERROR);
                rule = new MeetingAddOtherRule();
                expectedReturnValue = true;
            }
       };
 
    
    }

    @Test
    public void testAlternateForMatched() {    
        new  TemplateRuleTest<MeetingAddOtherEvent, MeetingAddOtherRule> (){            
            @Override
            protected void prerequisite() {            
                OtherPresentBean newOtherPresentBean = new OtherPresentBean();
                CommitteeScheduleAttendance attendance = new CommitteeScheduleAttendance();
                newOtherPresentBean.setAttendance(attendance);
                
                MeetingHelper meetingHelper = new MeetingHelper(new MeetingForm());
                meetingHelper.setNewOtherPresentBean(newOtherPresentBean);
                meetingHelper.setMemberPresentBeans(new ArrayList<MemberPresentBean>());
                
                attendance.setPersonName("tester 1");
                meetingHelper.getMemberPresentBeans().add(getMemberPresent("001", "tester 1"));
                newOtherPresentBean.getAttendance().setPersonId("001");
                // member present found
                newOtherPresentBean.getAttendance().setNonEmployeeFlag(false);
                // personid is the same but one is non-employee, the other is not
                newOtherPresentBean.getAttendance().setNonEmployeeFlag(true);
                // personid is not matched
                newOtherPresentBean.getAttendance().setPersonId("002");
                newOtherPresentBean.getAttendance().setNonEmployeeFlag(false);
                
                // person matched alternatefor
                meetingHelper.getMemberPresentBeans().get(0).getAttendance().setAlternateFor("002");
                event = new MeetingAddOtherEvent(Constants.EMPTY_STRING, null, meetingHelper, ErrorType.HARDERROR);
                rule = new MeetingAddOtherRule();
                expectedReturnValue = false;
            }
       };
 
    
    }
    @Test
    public void testMemberPresentFound() {    
        new  TemplateRuleTest<MeetingAddOtherEvent, MeetingAddOtherRule> (){            
            @Override
            protected void prerequisite() {            
                OtherPresentBean newOtherPresentBean = new OtherPresentBean();
                CommitteeScheduleAttendance attendance = new CommitteeScheduleAttendance();
                newOtherPresentBean.setAttendance(attendance);
                
                MeetingHelper meetingHelper = new MeetingHelper(new MeetingForm());
                meetingHelper.setNewOtherPresentBean(newOtherPresentBean);
                meetingHelper.setMemberPresentBeans(new ArrayList<MemberPresentBean>());
                
                attendance.setPersonName("tester 1");
                meetingHelper.getMemberPresentBeans().add(getMemberPresent("001", "tester 1"));
                newOtherPresentBean.getAttendance().setPersonId("001");
                // member present found
                newOtherPresentBean.getAttendance().setNonEmployeeFlag(false);
                event = new MeetingAddOtherEvent(Constants.EMPTY_STRING, null, meetingHelper, ErrorType.HARDERROR);
                rule = new MeetingAddOtherRule();
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

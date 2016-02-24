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
import org.kuali.coeus.common.committee.impl.meeting.MeetingPresentOtherOrVotingEvent;
import org.kuali.coeus.common.committee.impl.meeting.MeetingPresentOtherOrVotingRule;
import org.kuali.coeus.common.committee.impl.meeting.MemberAbsentBean;
import org.kuali.coeus.common.committee.impl.meeting.MemberPresentBean;
import org.kuali.coeus.common.committee.impl.meeting.MeetingEventBase.ErrorType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.rules.TemplateRuleTest;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;

import java.util.ArrayList;

public class MeetingPresentOtherOrVotingRuleTest extends KcIntegrationTestBase {
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

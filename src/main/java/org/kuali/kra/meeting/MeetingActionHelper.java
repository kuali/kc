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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.committee.bo.CommitteeSchedule;

public class MeetingActionHelper implements Serializable {

//    public void populateAttendancePreSave(MeetingHelper meetingHelper) {
//        CommitteeSchedule committeeSchedule = meetingHelper.getCommitteeSchedule();
//        List<CommitteeScheduleAttendance> attendances = new ArrayList<CommitteeScheduleAttendance>();
//        for (MemberPresentBean memberPresentBean : meetingHelper.getMemberPresentBeans()) {
//            CommitteeScheduleAttendance attendance = new CommitteeScheduleAttendance();
//            if (StringUtils.isNotBlank(memberPresentBean.getAlternateFor())) {
//                attendance.setAlternateFor(memberPresentBean.getAlternateFor());
//                attendance.setAlternateFlag(true);
//            }
//            attendance.setNonEmployeeFlag(memberPresentBean.isNonEmployeeFlag());
//            attendance.setPersonId(memberPresentBean.getPersonId());
//            attendance.setPersonName(memberPresentBean.getPersonName());
//            attendances.add(attendance);
//        }
//        for (OtherPresentBean otherPresentBean : meetingHelper.getOtherPresentBeans()) {
//            CommitteeScheduleAttendance attendance = new CommitteeScheduleAttendance();
//            attendance.setNonEmployeeFlag(otherPresentBean.isNonEmployeeFlag());
//            if (StringUtils.isNotBlank(otherPresentBean.getPersonId())) {
//                attendance.setPersonId(otherPresentBean.getPersonId());
//            }
//            else {
//                attendance.setPersonId(otherPresentBean.getRolodexId().toString());
//            }
//            attendance.setPersonName(otherPresentBean.getPersonName());
//            attendance.setGuestFlag(true);
//            attendances.add(attendance);
//        }
////        meetingHelper.setDeletedAttendances(getDeletedAttendance(committeeSchedule.getCommitteeScheduleAttendances(), attendances));
//        // TODO : just delete old one and then insert new ones
//        // need to think it again
//        meetingHelper.setDeletedAttendances(committeeSchedule.getCommitteeScheduleAttendances());
//        committeeSchedule.setCommitteeScheduleAttendances(attendances);
//    }
//
//    private List<CommitteeScheduleAttendance> getDeletedAttendance(List<CommitteeScheduleAttendance> oldAttendances,
//            List<CommitteeScheduleAttendance> newAttendances) {
//
//        List<CommitteeScheduleAttendance> deletedAttendances = new ArrayList<CommitteeScheduleAttendance>();
//        List<String> personIds = new ArrayList<String>();
//        for (CommitteeScheduleAttendance newAttendance : newAttendances) {
//            personIds.add(newAttendance.getPersonId());
//        }
//
//        // TODO : There is a flaw. What if personid=rolodexid and both person exist as members
//        for (CommitteeScheduleAttendance oldAttendance : oldAttendances) {
//            if (!personIds.contains(oldAttendance.getPersonId())) {
//                deletedAttendances.add(oldAttendance);
//            }
//        }
//        return deletedAttendances;
//    }
}

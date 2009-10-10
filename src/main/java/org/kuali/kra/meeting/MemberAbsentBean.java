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

import org.kuali.kra.committee.bo.MembershipRole;


public class MemberAbsentBean implements Serializable, Comparable<MemberAbsentBean> {
    private CommitteeScheduleAttendance attendance;
    private MembershipRole role = new MembershipRole();

    public CommitteeScheduleAttendance getAttendance() {
        return attendance;
    }

    public void setAttendance(CommitteeScheduleAttendance attendance) {
        this.attendance = attendance;
    }

    public MembershipRole getRole() {
        return role;
    }

    public void setRole(MembershipRole role) {
        this.role = role;
    }

    public int compareTo(MemberAbsentBean arg) {
        return this.getAttendance().getPersonName().compareTo(arg.getAttendance().getPersonName());

    }

}

/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.committee.impl.meeting;

import java.io.Serializable;


/**
 * 
 * This class is form data for member absent in meeting page
 */
public class MemberAbsentBean implements Serializable, Comparable<MemberAbsentBean> {

    private static final long serialVersionUID = -5220883072192174587L;
    private CommitteeScheduleAttendanceBase attendance;

    public CommitteeScheduleAttendanceBase getAttendance() {
        return attendance;
    }

    public void setAttendance(CommitteeScheduleAttendanceBase attendance) {
        this.attendance = attendance;
    }

    public int compareTo(MemberAbsentBean arg) {
        return this.getAttendance().getPersonName().compareTo(arg.getAttendance().getPersonName());

    }

}

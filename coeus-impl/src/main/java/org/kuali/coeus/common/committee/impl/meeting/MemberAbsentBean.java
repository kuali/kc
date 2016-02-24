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

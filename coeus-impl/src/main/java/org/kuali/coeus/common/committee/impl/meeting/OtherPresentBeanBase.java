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
 * This class is form data for other present.
 */
public abstract class OtherPresentBeanBase implements Serializable, Comparable<OtherPresentBeanBase> {

    private static final long serialVersionUID = 4831035284455868528L;
    private CommitteeScheduleAttendanceBase attendance;
    private boolean member;

    public CommitteeScheduleAttendanceBase getAttendance() {
        if(attendance == null) {

            attendance = getNewCommitteeScheduleAttendanceInstanceHook();
        }
        return attendance;
    }
    
    protected abstract CommitteeScheduleAttendanceBase getNewCommitteeScheduleAttendanceInstanceHook();
    
    

    public void setAttendance(CommitteeScheduleAttendanceBase attendance) {
        this.attendance = attendance;
    }

    public boolean isMember() {
        return member;
    }

    public void setMember(boolean member) {
        this.member = member;
    }

    public int compareTo(OtherPresentBeanBase arg) {
        return this.getAttendance().getPersonName().compareTo(arg.getAttendance().getPersonName());

    }

}

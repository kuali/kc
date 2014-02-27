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

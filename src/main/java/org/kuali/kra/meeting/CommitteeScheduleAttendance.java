/*
 * Copyright 2005-2010 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.meeting;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.bo.CommitteeSchedule;

/**
 * 
 * This is a Bo class for committee schedule attendance.
 */
public class CommitteeScheduleAttendance extends KraPersistableBusinessObjectBase {

    private static final long serialVersionUID = -6010677692125364332L;

    private Long commScheduleAttendanceId;

    private Long scheduleIdFk;

    private String personId;

    private boolean guestFlag;

    private boolean alternateFlag;

    private String alternateFor;

    private boolean nonEmployeeFlag;

    private String comments;

    private String personName;

    private String roleName;

    private CommitteeSchedule committeeSchedule;

    public CommitteeScheduleAttendance() {
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public boolean getGuestFlag() {
        return guestFlag;
    }

    public void setGuestFlag(boolean guestFlag) {
        this.guestFlag = guestFlag;
    }

    public boolean getAlternateFlag() {
        return alternateFlag;
    }

    public void setAlternateFlag(boolean alternateFlag) {
        this.alternateFlag = alternateFlag;
    }

    public String getAlternateFor() {
        return alternateFor;
    }

    public void setAlternateFor(String alternateFor) {
        this.alternateFor = alternateFor;
    }

    public boolean getNonEmployeeFlag() {
        return nonEmployeeFlag;
    }

    public void setNonEmployeeFlag(boolean nonEmployeeFlag) {
        this.nonEmployeeFlag = nonEmployeeFlag;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Long getScheduleIdFk() {
        return scheduleIdFk;
    }

    public void setScheduleIdFk(Long scheduleIdFk) {
        this.scheduleIdFk = scheduleIdFk;
    }

    public void setCommScheduleAttendanceId(Long commScheduleAttendanceId) {
        this.commScheduleAttendanceId = commScheduleAttendanceId;
    }

    public CommitteeSchedule getCommitteeSchedule() {
        return committeeSchedule;
    }

    public void setCommitteeSchedule(CommitteeSchedule committeeSchedule) {
        this.committeeSchedule = committeeSchedule;
    }

    public Long getCommScheduleAttendanceId() {
        return commScheduleAttendanceId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public boolean isCommitteeMember(CommitteeMembership membership) {
        if (getNonEmployeeFlag() && membership.getRolodexId() != null) {
            return StringUtils.equals(membership.getRolodexId().toString(), getPersonId());
        } else if (!getNonEmployeeFlag() && membership.getPersonId() != null) {
            return StringUtils.equals(getPersonId(), membership.getPersonId());
        }
        return false;
    }
}

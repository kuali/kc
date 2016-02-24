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

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

/**
 * 
 * This is a Bo class for committee schedule attendance.
 */
public abstract class CommitteeScheduleAttendanceBase extends KcPersistableBusinessObjectBase {

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

    private CommitteeScheduleBase committeeSchedule;

    public CommitteeScheduleAttendanceBase() {
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

    public CommitteeScheduleBase getCommitteeSchedule() {
        return committeeSchedule;
    }

    public void setCommitteeSchedule(CommitteeScheduleBase committeeSchedule) {
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

    public boolean isCommitteeMember(CommitteeMembershipBase membership) {
        if (getNonEmployeeFlag() && membership.getRolodexId() != null) {
            return StringUtils.equals(membership.getRolodexId().toString(), getPersonId());
        } else if (!getNonEmployeeFlag() && membership.getPersonId() != null) {
            return StringUtils.equals(getPersonId(), membership.getPersonId());
        }
        return false;
    }
}

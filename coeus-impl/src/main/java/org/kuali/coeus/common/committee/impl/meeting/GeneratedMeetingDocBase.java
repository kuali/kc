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

import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

import java.sql.Timestamp;

/**
 * 
 * This class is super class for meeting generated doc classes.
 */
public abstract class GeneratedMeetingDocBase extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = -5187758950400693662L;

    private Long scheduleIdFk;

    private byte[] pdfStore;

    private Timestamp createTimestamp;

    private String createUser;
    private CommitteeScheduleBase committeeSchedule;


    public byte[] getPdfStore() {
        return pdfStore;
    }

    public void setPdfStore(byte[] pdfStore) {
        this.pdfStore = pdfStore;
    }

    public Long getScheduleIdFk() {
        return scheduleIdFk;
    }

    public void setScheduleIdFk(Long scheduleIdFk) {
        this.scheduleIdFk = scheduleIdFk;
    }

    public CommitteeScheduleBase getCommitteeSchedule() {
        if (committeeSchedule == null) {
            refreshReferenceObject("committeeSchedule");
        }
        return committeeSchedule;
    }

    public void setCommitteeSchedule(CommitteeScheduleBase committeeSchedule) {
        this.committeeSchedule = committeeSchedule;
    }

    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
}

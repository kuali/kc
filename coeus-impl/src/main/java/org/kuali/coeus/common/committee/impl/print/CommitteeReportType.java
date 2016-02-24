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
package org.kuali.coeus.common.committee.impl.print;

/**
 * This class represents different types of reports for committee.
 */
public enum CommitteeReportType {
    /** Report type for the committee reports that utilize templates. */
    COMMITTEE_TEMPLATE ("committee_template"),

    /** Report type for the committee schedule reports that utilize templates. */
    SCHEDULE_TEMPLATE ("schedule_template"),

    /** Report type for the protocol correspondence reports that utilize templates. */
    PROTOCOL_CORRESPONDENCE_TEMPLATE ("protocol_correspondence_template"),

    /** Report type for the committee roster. */
    ROSTER ("roster"),

    /** Report type for the committee future scheduled meetings. */
    FUTURE_SCHEDULED_MEETINGS ("futureScheduledMeetings"),
    
    PROTOCOL_BATCH_CORRESPONDENCE("prtocolBatchCorrespondence");

    private String committeeReportType;

    /**
     * 
     * Constructs a CommitteeReportType.java.
     * @param committeeReportType
     */
    CommitteeReportType(String committeeReportType) {
        this.committeeReportType = committeeReportType;
    }
    
    public String getCommitteeReportType() {
        return committeeReportType;
    }
}

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
package org.kuali.kra.coi.print;

/**
 * This class represents different types of reports for COI.
 */
public enum CoiReportType {
    /** Report type for the COI reports that utilize templates. */
    COI_TEMPLATE ("committee_template"),

    /** Report type for the COI correspondence reports that utilize templates. */
    COI_CORRESPONDENCE_TEMPLATE ("protocol_correspondence_template"),

    COI_BATCH_CORRESPONDENCE("coiBatchCorrespondence"),
    
    COI_APPROVED_DISCLOSURE("coiApprovedDisclosure");

    private String coiReportType;

    /**
     * 
     * Constructs a CoiReportType.java.
     * @param coiReportType
     */
    CoiReportType(String coiReportType) {
        this.coiReportType = coiReportType;
    }
    
    public String getCoiReportType() {
        return coiReportType;
    }
}

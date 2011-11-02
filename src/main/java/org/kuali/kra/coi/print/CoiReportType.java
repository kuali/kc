/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.coi.print;

/**
 * This class represents different types of reports for COI.
 */
public enum CoiReportType {
    /** Report type for the COI reports that utilize templates. */
    COI_TEMPLATE ("committee_template"),

    /** Report type for the COI correspondence reports that utilize templates. */
    COI_CORRESPONDENCE_TEMPLATE ("protocol_correspondence_template"),

    COI_BATCH_CORRESPONDENCE("coiBatchCorrespondence");

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

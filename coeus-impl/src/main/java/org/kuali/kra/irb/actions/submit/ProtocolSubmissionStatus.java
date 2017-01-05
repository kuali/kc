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
package org.kuali.kra.irb.actions.submit;

import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionStatusBase;

public class ProtocolSubmissionStatus extends ProtocolSubmissionStatusBase {

    private static final long serialVersionUID = -3005754570292744646L;

    public static final String SUBMITTED_TO_COMMITTEE = "100";

    public static final String IN_AGENDA = "101";

    public static final String PENDING = "102";

    public static final String COMPLETE = "200";

    public static final String SUBSTANTIVE_REVISIONS_REQUIRED = "201";

    public static final String SPECIFIC_MINOR_REVISIONS_REQUIRED = "202";

    public static final String APPROVED = "203";

    public static final String EXEMPT = "204";

    public static final String DISAPPROVED = "205";

    public static final String DEFERRED = "206";

    public static final String CLOSED = "207";

    public static final String TERMINATED = "208";

    public static final String WITHDRAWN = "210";

    public static final String CLOSED_FOR_ENROLLMENT = "211";

    public static final String IRB_ACKNOWLEDGEMENT = "212";
    
    public static final String RETURNED_TO_PI = "213";
    
    public static final String RETURNED_IN_ROUTING = "405";
    public static final String RECALLED_IN_ROUTING = "406";

    private String protocolSubmissionStatusCode;

    private String description;

    public ProtocolSubmissionStatus() {
    }

    public String getProtocolSubmissionStatusCode() {
        return protocolSubmissionStatusCode;
    }

    public void setProtocolSubmissionStatusCode(String protocolSubmissionStatusCode) {
        this.protocolSubmissionStatusCode = protocolSubmissionStatusCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

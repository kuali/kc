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
package org.kuali.kra.irb.actions.submit;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class ProtocolSubmissionStatus extends KraPersistableBusinessObjectBase {

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

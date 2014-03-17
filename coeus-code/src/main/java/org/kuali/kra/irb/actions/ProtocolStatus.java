/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.irb.actions;

import org.kuali.kra.protocol.actions.ProtocolStatusBase;

public class ProtocolStatus extends ProtocolStatusBase {


    private static final long serialVersionUID = -5947289991209122441L;

    public static final String IN_PROGRESS = "100";

    public static final String SUBMITTED_TO_IRB = "101";

    public static final String SPECIFIC_MINOR_REVISIONS_REQUIRED = "102";

    public static final String DEFERRED = "103";

    public static final String SUBSTANTIVE_REVISIONS_REQUIRED = "104";

    public static final String AMENDMENT_IN_PROGRESS = "105";

    public static final String RENEWAL_IN_PROGRESS = "106";
    
    public static final String RETURN_TO_PI = "107";

    public static final String ACTIVE_OPEN_TO_ENROLLMENT = "200";

    public static final String ACTIVE_CLOSED_TO_ENROLLMENT = "201";

    public static final String ACTIVE_DATA_ANALYSIS_ONLY = "202";

    public static final String EXEMPT = "203";

    public static final String CLOSED_ADMINISTRATIVELY = "300";

    public static final String CLOSED_BY_INVESTIGATOR = "301";

    public static final String SUSPENDED_BY_PI = "302";

    public static final String DELETED = "303";

    public static final String WITHDRAWN = "304";

    public static final String EXPIRED = "305";

    public static final String DISAPPROVED = "306";

    public static final String TERMINATED_BY_IRB = "307";

    public static final String SUSPENDED_BY_IRB = "308";

    public static final String IRB_REVIEW_NOT_REQUIRED = "310";

    public static final String SUSPENDED_BY_DSMB = "311";

    public static final String AMENDMENT_MERGED = "400";

    public static final String RENEWAL_MERGED = "401";
    
    public static final String RECALLED_IN_ROUTING = "402";
}

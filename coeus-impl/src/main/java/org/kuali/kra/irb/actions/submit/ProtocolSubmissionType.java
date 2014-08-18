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
package org.kuali.kra.irb.actions.submit;

import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionTypeBase;

public class ProtocolSubmissionType extends ProtocolSubmissionTypeBase {


    private static final long serialVersionUID = -1232083217368314655L;

    public static final String INITIAL_SUBMISSION = "100";

    public static final String CONTINUATION = "101";

    public static final String AMENDMENT = "102";

    public static final String RESPONSE_TO_PREV_IRB_NOTIFICATION = "103";

    public static final String REQUEST_TO_CLOSE = "109";

    public static final String CONTINUATION_WITH_AMENDMENT = "115";

    public static final String REQUEST_FOR_SUSPENSION = "110";

    public static final String REQUEST_TO_CLOSE_ENROLLMENT = "111";

    public static final String REQUEST_TO_REOPEN_ENROLLMENT = "114";

    public static final String REQUEST_FOR_DATA_ANALYSIS_ONLY = "113";

    public static final String NOTIFY_IRB = "112";      // also known as FYI

    public static final String REQUEST_FOR_TERMINATION = "108";

    public static final String RESUBMISSION = "116";


    public String getSubmissionTypeCode() {
        return super.getSubmissionTypeCode();
    }

}

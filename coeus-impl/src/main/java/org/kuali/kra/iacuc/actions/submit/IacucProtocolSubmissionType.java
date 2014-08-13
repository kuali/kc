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
package org.kuali.kra.iacuc.actions.submit;

import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionTypeBase;

public class IacucProtocolSubmissionType extends ProtocolSubmissionTypeBase {

    public static final String INITIAL_SUBMISSION = "100";

    public static final String RESPONSE_TO_PREV_IACUC_NOTIFICATION = "101";

    public static final String REQUEST_TO_DEACTIVATE = "102";

    public static final String REQUEST_TO_LIFT_HOLD = "103";

    public static final String FYI = "104";

    public static final String AMENDMENT = "105";

    public static final String RENEWAL = "106";

    public static final String RENEWAL_WITH_AMENDMENT = "107";
    
    public static final String CONTINUATION = "108";

    public static final String CONTINUATION_WITH_AMENDMENT = "109";
    
    public static final String NOTIFY_IACUC = "110";
    
    public static final String REQUEST_SUSPEND = "111";

    public boolean isAmendment() {
        return AMENDMENT.equals(getSubmissionTypeCode()) || RENEWAL_WITH_AMENDMENT.equals(getSubmissionTypeCode());
    }

    public boolean isRenewal() {
        return RENEWAL.equals(getSubmissionTypeCode()) || RENEWAL_WITH_AMENDMENT.equals(getSubmissionTypeCode());
    }

    public boolean isContinuation() {
        return CONTINUATION.equals(getSubmissionTypeCode()) || CONTINUATION_WITH_AMENDMENT.equals(getSubmissionTypeCode());
    }
    
    public String getSubmissionTypeCode() {
        return super.getSubmissionTypeCode();
    }

}

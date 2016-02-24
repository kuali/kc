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

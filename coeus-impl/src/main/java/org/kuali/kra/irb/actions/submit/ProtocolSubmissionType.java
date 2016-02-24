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

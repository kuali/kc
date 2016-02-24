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
package org.kuali.coeus.propdev.impl.s2s;

public final class S2sAppSubmissionConstants {

    public static final String STATUS_GRANTS_GOV_SUBMISSION_ERROR = "S2S Submission Error";
    public static final String STATUS_AGENCY_TRACKING_NUMBER_ASSIGNED = "Agency Tracking Number Assigned";
    public static final String STATUS_NO_RESPONSE_FROM_GRANTS_GOV = "No response from S2S Server";
    public static final String GRANTS_GOV_STATUS_MESSAGE = "S2S Submission in Process";
    public static final String GRANTS_GOV_COMMENTS_MESSAGE = "Trying to submit to S2S";
    public static final String GRANTS_GOV_SUBMISSION_MESSAGE="Submitted to S2S";
    public static final String STATUS_RECEIVING = "Receiving";
    public static final String STATUS_RECEIVED = "Received";
    public static final String STATUS_PROCESSING = "Processing";
    public static final String STATUS_VALIDATED = "Validated";
    public static final String STATUS_RECEIVED_BY_AGENCY = "Received by Agency";
    public static final String STATUS_REJECTED_WITH_ERRORS = "REJECTED_WITH_ERRORS";
    public static final String STATUS_PUREGED = "PURGED";

    private S2sAppSubmissionConstants() {
        throw new UnsupportedOperationException("do not call");
    }
}

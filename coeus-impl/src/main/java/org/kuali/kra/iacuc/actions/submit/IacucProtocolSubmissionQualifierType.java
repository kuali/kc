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

import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionQualifierTypeBase;

public class IacucProtocolSubmissionQualifierType extends ProtocolSubmissionQualifierTypeBase {

    public final static String MODIFICATION_AMENDMENTS_NEW_FINDINGS = "1";
    public final static String ANNUAL_SCHEDULED_BY_IACUC = "2";
    public final static String CONTINGENT_CONDITIONAL_APPROVAL = "3";
    public final static String ELIGIBILITY_EXCEPTIONS_PROTOCOL_DEVIATIONS = "4";
    public final static String AE_UADE = "5";
    public final static String COMPLAINT = "6";
    public final static String DEVIATION = "7";
    public final static String IACUC_PROTOCOL_RELATED_COI_PROJECT = "8";
    public final static String SELF_REPORT_FOR_NONCOMPLIANCE = "9";
    public final static String REQUEST_ELIGIBILITY_EXCEPTION = "10";
    public final static String TRAINING_CERTIFICATION = "11";
    public final static String UNANTICIPATED_PROBLEMS = "12";
    public final static String ANNUAL_REPORT = "13";
    

    private static final long serialVersionUID = -418480649135759029L;

    public IacucProtocolSubmissionQualifierType() {
    }

}

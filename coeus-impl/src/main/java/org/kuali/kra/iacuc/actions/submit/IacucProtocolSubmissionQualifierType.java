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

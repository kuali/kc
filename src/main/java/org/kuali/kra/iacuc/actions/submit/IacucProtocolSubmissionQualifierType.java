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
package org.kuali.kra.iacuc.actions.submit;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class IacucProtocolSubmissionQualifierType extends KraPersistableBusinessObjectBase {

    public final String MODIFICATION_AMENDMENTS_NEW_FINDINGS = "1";
    public final String ANNUAL_SCHEDULED_BY_IACUC = "2";
    public final String CONTINGENT_CONDITIONAL_APPROVAL = "3";
    public final String ELIGIBILITY_EXCEPTIONS_PROTOCOL_DEVIATIONS = "4";
    public final String AE_UADE = "5";
    public final String COMPLAINT = "6";
    public final String DEVIATION = "7";
    public final String IACUC_PROTOCOL_RELATED_COI_PROJECT = "8";
    public final String SELF_REPORT_FOR_NONCOMPLIANCE = "9";
    public final String REQUEST_ELIGIBILITY_EXCEPTION = "10";
    public final String TRAINING_CERTIFICATION = "11";
    public final String UNANTICIPATED_PROBLEMS = "12";
    public final String ANNUAL_REPORT = "13";
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -418480649135759029L;

    private String submissionQualifierTypeCode;

    private String description;

    public IacucProtocolSubmissionQualifierType() {
    }

    public String getSubmissionQualifierTypeCode() {
        return submissionQualifierTypeCode;
    }

    public void setSubmissionQualifierTypeCode(String submissionQualifierTypeCode) {
        this.submissionQualifierTypeCode = submissionQualifierTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

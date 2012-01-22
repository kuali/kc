/*
 * Copyright 2006-2009 The Kuali Foundation
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

public class ValidProtoSubTypeQual extends KraPersistableBusinessObjectBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 6546751709369749190L;

    private Long validProtoSubTypeQualId;

    private String submissionTypeCode;

    private String submissionTypeQualCode;

    private ProtocolSubmissionType submissionType;

    private ProtocolSubmissionQualifierType submissionTypeQualifier;

    public ValidProtoSubTypeQual() {
    }

    public Long getValidProtoSubTypeQualId() {
        return validProtoSubTypeQualId;
    }

    public void setValidProtoSubTypeQualId(Long validProtoSubTypeQualId) {
        this.validProtoSubTypeQualId = validProtoSubTypeQualId;
    }

    public String getSubmissionTypeCode() {
        return submissionTypeCode;
    }

    public void setSubmissionTypeCode(String submissionTypeCode) {
        this.submissionTypeCode = submissionTypeCode;
    }

    public String getSubmissionTypeQualCode() {
        return submissionTypeQualCode;
    }

    public void setSubmissionTypeQualCode(String submissionTypeQualCode) {
        this.submissionTypeQualCode = submissionTypeQualCode;
    }

    public ProtocolSubmissionType getSubmissionType() {
        return submissionType;
    }

    public void setSubmissionType(ProtocolSubmissionType submissionType) {
        this.submissionType = submissionType;
    }

    public ProtocolSubmissionQualifierType getSubmissionTypeQualifier() {
        return submissionTypeQualifier;
    }

    public void setSubmissionTypeQualifier(ProtocolSubmissionQualifierType submissionTypeQualifier) {
        this.submissionTypeQualifier = submissionTypeQualifier;
    }
}

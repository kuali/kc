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
package org.kuali.kra.protocol.actions.submit;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

public class ValidProtoSubTypeQual extends KcPersistableBusinessObjectBase {


    private static final long serialVersionUID = 6546751709369749190L;

    private Long validProtoSubTypeQualId;

    private String submissionTypeCode;

    private String submissionTypeQualCode;

    private ProtocolSubmissionTypeBase submissionType;

    private ProtocolSubmissionQualifierTypeBase submissionTypeQualifier;

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

    public ProtocolSubmissionTypeBase getSubmissionType() {
        return submissionType;
    }

    public void setSubmissionType(ProtocolSubmissionTypeBase submissionType) {
        this.submissionType = submissionType;
    }

    public ProtocolSubmissionQualifierTypeBase getSubmissionTypeQualifier() {
        return submissionTypeQualifier;
    }

    public void setSubmissionTypeQualifier(ProtocolSubmissionQualifierTypeBase submissionTypeQualifier) {
        this.submissionTypeQualifier = submissionTypeQualifier;
    }
}

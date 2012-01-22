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

public class ValidProtoSubRevType extends KraPersistableBusinessObjectBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 3396505214377892706L;

    private Long validProtoSubRevTypeId;

    private String submissionTypeCode;

    private String protocolReviewTypeCode;

    private ProtocolReviewType protocolReviewType;

    private ProtocolSubmissionType submissionType;

    public ValidProtoSubRevType() {
    }

    public Long getValidProtoSubRevTypeId() {
        return validProtoSubRevTypeId;
    }

    public void setValidProtoSubRevTypeId(Long validProtoSubRevTypeId) {
        this.validProtoSubRevTypeId = validProtoSubRevTypeId;
    }

    public String getSubmissionTypeCode() {
        return submissionTypeCode;
    }

    public void setSubmissionTypeCode(String submissionTypeCode) {
        this.submissionTypeCode = submissionTypeCode;
    }

    public String getProtocolReviewTypeCode() {
        return protocolReviewTypeCode;
    }

    public void setProtocolReviewTypeCode(String protocolReviewTypeCode) {
        this.protocolReviewTypeCode = protocolReviewTypeCode;
    }

    public ProtocolReviewType getProtocolReviewType() {
        return protocolReviewType;
    }

    public void setProtocolReviewType(ProtocolReviewType protocolReviewType) {
        this.protocolReviewType = protocolReviewType;
    }

    public ProtocolSubmissionType getSubmissionType() {
        return submissionType;
    }

    public void setSubmissionType(ProtocolSubmissionType submissionType) {
        this.submissionType = submissionType;
    }
}

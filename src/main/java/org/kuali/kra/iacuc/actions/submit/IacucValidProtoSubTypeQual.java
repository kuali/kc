/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.iacuc.actions.submit;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class IacucValidProtoSubTypeQual  extends KraPersistableBusinessObjectBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 6546751709369749190L;

    private Long validProtoSubTypeQualId;

    private String submissionTypeCode;

    private String iacucSubmissionTypeQualCode;

    private IacucProtocolSubmissionType iacucProtocolSubmissionType;

    private IacucProtocolSubmissionQualifierType iacucSubmissionTypeQualifier;

    public IacucValidProtoSubTypeQual() {
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


    public void setIacucProtocolSubmissionType(IacucProtocolSubmissionType iacucProtocolSubmissionType) {
        this.iacucProtocolSubmissionType = iacucProtocolSubmissionType;
    }

    public IacucProtocolSubmissionType getIacucProtocolSubmissionType() {
        return iacucProtocolSubmissionType;
    }

    public IacucProtocolSubmissionQualifierType getIacucSubmissionTypeQualifier() {
        return iacucSubmissionTypeQualifier;
    }

    public void setIacucSubmissionTypeQualifier(IacucProtocolSubmissionQualifierType iacucSubmissionTypeQualifier) {
        this.iacucSubmissionTypeQualifier = iacucSubmissionTypeQualifier;
    }

    public String getIacucSubmissionTypeQualCode() {
        return iacucSubmissionTypeQualCode;
    }

    public void setIacucSubmissionTypeQualCode(String iacucSubmissionTypeQualCode) {
        this.iacucSubmissionTypeQualCode = iacucSubmissionTypeQualCode;
    }


}

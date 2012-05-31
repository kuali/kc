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


public class IacucValidProtoSubRevType extends KraPersistableBusinessObjectBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;

    private Long validProtocolSubRevTypeId;

    private String submissionTypeCode;

    private String iacucProtocolReviewTypeCode;

    private IacucProtocolReviewType iacucProtocolReviewType;

    private IacucProtocolSubmissionType iacucProtocolSubmissionType;

    public IacucValidProtoSubRevType() {
    }

    public Long getValidProtocolSubRevTypeId() {
        return validProtocolSubRevTypeId;
    }

    public void setValidProtocolSubRevTypeId(Long validProtocolSubRevTypeId) {
        this.validProtocolSubRevTypeId = validProtocolSubRevTypeId;
    }

    public String getSubmissionTypeCode() {
        return submissionTypeCode;
    }

    public void setSubmissionTypeCode(String submissionTypeCode) {
        this.submissionTypeCode = submissionTypeCode;
    }

    public String getIacucProtocolReviewTypeCode() {
        return iacucProtocolReviewTypeCode;
    }

    public void setIacucProtocolReviewTypeCode(String iacucProtocolReviewTypeCode) {
        this.iacucProtocolReviewTypeCode = iacucProtocolReviewTypeCode;
    }

    public IacucProtocolReviewType getIacucProtocolReviewType() {
        return iacucProtocolReviewType;
    }

    public void setIacucProtocolReviewType(IacucProtocolReviewType iacucProtocolReviewType) {
        this.iacucProtocolReviewType = iacucProtocolReviewType;
    }

    public IacucProtocolSubmissionType getIacucProtocolSubmissionType() {
        return iacucProtocolSubmissionType;
    }

    public void setIacucProtocolSubmissionType(IacucProtocolSubmissionType iacucProtocolSubmissionType) {
        this.iacucProtocolSubmissionType = iacucProtocolSubmissionType;
    }



   
}

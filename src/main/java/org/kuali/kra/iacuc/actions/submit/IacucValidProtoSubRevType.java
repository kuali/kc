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

import org.kuali.kra.protocol.actions.submit.ValidProtoSubRevType;

public class IacucValidProtoSubRevType extends ValidProtoSubRevType {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 8097814334863817285L;

    private Long iacucProtocolReviewTypeCode; 
    
    private Long validProtoSubRevTypeId;
    
    private IacucProtocolReviewType iacucProtocolReviewType;
    
    private IacucProtocolSubmissionType iacucProtocolSubmissionType;
    
    private String submissionTypeCode;

    private String protocolReviewTypeCode;
    
    public IacucValidProtoSubRevType() {
        super();
    }

    
    public Long getIacucProtocolReviewTypeCode() {
        return iacucProtocolReviewTypeCode;
    }

    public void setIacucProtocolReviewTypeCode(Long iacucProtocolReviewTypeCode) {
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

}

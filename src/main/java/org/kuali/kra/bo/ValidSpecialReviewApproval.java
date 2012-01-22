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
package org.kuali.kra.bo;


/**
 * Defines the ValidSpecialReviewApproval business object of the KRA domain.
 */
public class ValidSpecialReviewApproval extends KraPersistableBusinessObjectBase {

    private static final long serialVersionUID = 2223133021712448327L;

    private Long validSpecialReviewApprovalId;

    private String specialReviewTypeCode;

    private String approvalTypeCode;

    private boolean protocolNumberFlag;

    private boolean applicationDateFlag;

    private boolean approvalDateFlag;

    private boolean exemptNumberFlag;

    private SpecialReviewType specialReviewType;

    private SpecialReviewApprovalType specialReviewApprovalType;

    public Long getValidSpecialReviewApprovalId() {
        return validSpecialReviewApprovalId;
    }

    public void setValidSpecialReviewApprovalId(Long validSpecialReviewApprovalId) {
        this.validSpecialReviewApprovalId = validSpecialReviewApprovalId;
    }

    public String getSpecialReviewTypeCode() {
        return specialReviewTypeCode;
    }

    public void setSpecialReviewTypeCode(String specialReviewTypeCode) {
        this.specialReviewTypeCode = specialReviewTypeCode;
    }

    public String getApprovalTypeCode() {
        return approvalTypeCode;
    }

    public void setApprovalTypeCode(String approvalTypeCode) {
        this.approvalTypeCode = approvalTypeCode;
    }

    public boolean isProtocolNumberFlag() {
        return protocolNumberFlag;
    }

    public void setProtocolNumberFlag(boolean protocolNumberFlag) {
        this.protocolNumberFlag = protocolNumberFlag;
    }

    public boolean isApplicationDateFlag() {
        return applicationDateFlag;
    }

    public void setApplicationDateFlag(boolean applicationDateFlag) {
        this.applicationDateFlag = applicationDateFlag;
    }

    public boolean isApprovalDateFlag() {
        return approvalDateFlag;
    }

    public void setApprovalDateFlag(boolean approvalDateFlag) {
        this.approvalDateFlag = approvalDateFlag;
    }

    public boolean isExemptNumberFlag() {
        return exemptNumberFlag;
    }

    public void setExemptNumberFlag(boolean exemptNumberFlag) {
        this.exemptNumberFlag = exemptNumberFlag;
    }

    public SpecialReviewType getSpecialReviewType() {
        return specialReviewType;
    }

    public void setSpecialReviewType(SpecialReviewType specialReviewType) {
        this.specialReviewType = specialReviewType;
    }

    public SpecialReviewApprovalType getSpecialReviewApprovalType() {
        return specialReviewApprovalType;
    }

    public void setSpecialReviewApprovalType(SpecialReviewApprovalType specialReviewApprovalType) {
        this.specialReviewApprovalType = specialReviewApprovalType;
    }
}

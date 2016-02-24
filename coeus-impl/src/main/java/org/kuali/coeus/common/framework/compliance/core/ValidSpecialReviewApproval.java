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
package org.kuali.coeus.common.framework.compliance.core;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

/**
 * Defines the ValidSpecialReviewApproval business object of the KRA domain.
 */
public class ValidSpecialReviewApproval extends KcPersistableBusinessObjectBase {

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

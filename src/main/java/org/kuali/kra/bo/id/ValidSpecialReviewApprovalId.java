/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.bo.id;

import java.io.Serializable;

import javax.persistence.Column;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Primary Key for ValidSpecialReviewApproval BO.
 */
@SuppressWarnings("serial")
public class ValidSpecialReviewApprovalId implements Serializable {
    
    @Column(name="APPROVAL_TYPE_CODE")
    private String approvalTypeCode;

    @Column(name="SPECIAL_REVIEW_CODE")
    private String specialReviewCode;
    
    public String getApprovalTypeCode() {
        return this.approvalTypeCode;
    }
    
    public String getSpecialReviewCode() {
        return this.specialReviewCode;
    }
    
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof ValidSpecialReviewApprovalId)) return false;
        if (obj == null) return false;
        ValidSpecialReviewApprovalId other = (ValidSpecialReviewApprovalId) obj;
        return StringUtils.equals(approvalTypeCode, other.approvalTypeCode) &&
               StringUtils.equals(specialReviewCode, other.specialReviewCode);
    }
    
    public int hashCode() {
        return new HashCodeBuilder().append(approvalTypeCode).append(specialReviewCode).toHashCode();
    }
}

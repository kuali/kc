/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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

package org.kuali.kra.bo;

import java.util.LinkedHashMap;

public class ValidSpecialReviewApproval extends KraPersistableBusinessObjectBase {
	private String approvalTypeCode;
	private String specialReviewCode;
	private Boolean applicationDateFlag;
	private Boolean approvalDateFlag;
	private Boolean protocolNumberFlag;
	private SpecialReview specialReview;
	private SpecialReviewApprovalType specialReviewApprovalType;

	public String getApprovalTypeCode() {
		return approvalTypeCode;
	}

	public void setApprovalTypeCode(String approvalTypeCode) {
		this.approvalTypeCode = approvalTypeCode;
	}

	public String getSpecialReviewCode() {
		return specialReviewCode;
	}

	public void setSpecialReviewCode(String specialReviewCode) {
		this.specialReviewCode = specialReviewCode;
	}

	public Boolean isApplicationDateFlag() {
		return applicationDateFlag;
	}

	public void setApplicationDateFlag(Boolean applicationDateFlag) {
		this.applicationDateFlag = applicationDateFlag;
	}

	public Boolean isApprovalDateFlag() {
		return approvalDateFlag;
	}

	public void setApprovalDateFlag(Boolean approvalDateFlag) {
		this.approvalDateFlag = approvalDateFlag;
	}

	public Boolean isProtocolNumberFlag() {
		return protocolNumberFlag;
	}

	public void setProtocolNumberFlag(Boolean protocolNumberFlag) {
		this.protocolNumberFlag = protocolNumberFlag;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("approvalTypeCode", getApprovalTypeCode());
		hashMap.put("specialReviewCode", getSpecialReviewCode());
		hashMap.put("applicationDateFlag", isApplicationDateFlag());
		hashMap.put("approvalDateFlag", isApprovalDateFlag());
		hashMap.put("protocolNumberFlag", isProtocolNumberFlag());
		return hashMap;
	}

    public SpecialReview getSpecialReview() {
        return specialReview;
    }

    public void setSpecialReview(SpecialReview specialReview) {
        this.specialReview = specialReview;
    }

    public SpecialReviewApprovalType getSpecialReviewApprovalType() {
        return specialReviewApprovalType;
    }

    public void setSpecialReviewApprovalType(SpecialReviewApprovalType specialReviewApprovalType) {
        this.specialReviewApprovalType = specialReviewApprovalType;
    }
}

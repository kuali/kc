/*
 * Copyright 2006-2008 The Kuali Foundation
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

import java.util.LinkedHashMap;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Class representation of the ValidSpecialReviewApproval business object of the KRA domain
 */
@IdClass(org.kuali.kra.bo.id.ValidSpecialReviewApprovalId.class)
@Entity
@Table(name="VALID_SP_REV_APPROVAL")
public class ValidSpecialReviewApproval extends KraPersistableBusinessObjectBase {
	@Id
	@Column(name="APPROVAL_TYPE_CODE")
	private String approvalTypeCode;
	@Id
	@Column(name="SPECIAL_REVIEW_CODE")
	private String specialReviewCode;
	// change type from 'Boolean' to 'boolean'
	@Column(name="APPLICATION_DATE_FLAG")
	private boolean applicationDateFlag;
    @Column(name="APPROVAL_DATE_FLAG")
	private boolean approvalDateFlag;
    @Column(name="EXEMPT_NUMBER_FLAG")
	private boolean exemptNumberFlag;
	@Column(name="PROTOCOL_NUMBER_FLAG")
	private boolean protocolNumberFlag;
	@ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="SPECIAL_REVIEW_CODE", insertable=false, updatable=false)
	private SpecialReview specialReview;
	@ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="APPROVAL_TYPE_CODE", referencedColumnName="APPROVAL_TYPE_CODE", insertable=false, updatable=false)
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

	public boolean isProtocolNumberFlag() {
		return protocolNumberFlag;
	}

	public void setProtocolNumberFlag(boolean protocolNumberFlag) {
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
        hashMap.put("exemptNumberFlag", isExemptNumberFlag());
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

    public boolean isExemptNumberFlag() {
        return exemptNumberFlag;
    }


    public void setExemptNumberFlag(boolean exemptNumberFlag) {
        this.exemptNumberFlag = exemptNumberFlag;
    }
}


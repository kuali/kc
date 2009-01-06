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

package org.kuali.kra.award.bo;

import org.kuali.kra.bo.AbstractSpecialReview;

import java.util.LinkedHashMap;

public class AwardSpecialReview extends AbstractSpecialReview<AwardSpecialReviewExemption> { 
	
	/**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -3791915283913486492L;
    private Long awardSpecialReviewId; 
	private Award award; 

	public AwardSpecialReview() { 
        super();
    } 

	public Award getAward() {
		return award;
	}

	public void setAward(Award award) {
		this.award = award;
	}

	@SuppressWarnings("unchecked")
    @Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = super.toStringMapper();
		return hashMap;
	}

    /**
     * Gets the awardSpecialReviewId attribute. 
     * @return Returns the awardSpecialReviewId.
     */
    public Long getAwardSpecialReviewId() {
        return awardSpecialReviewId;
    }

    /**
     * Sets the awardSpecialReviewId attribute value.
     * @param awardSpecialReviewId The awardSpecialReviewId to set.
     */
    public void setAwardSpecialReviewId(Long awardSpecialReviewId) {
        this.awardSpecialReviewId = awardSpecialReviewId;
    }

    @Override
    public AwardSpecialReviewExemption newSpecialReviewExemption(String exemptionTypeCode) {
        AwardSpecialReviewExemption awardSpecialReviewExemption = new AwardSpecialReviewExemption();
        awardSpecialReviewExemption.setExemptionTypeCode(exemptionTypeCode);
        awardSpecialReviewExemption.setAwardSpecialReview(this);
        return awardSpecialReviewExemption;
    }
	
}
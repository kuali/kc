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
package org.kuali.kra.award.specialreview;

import org.kuali.kra.common.specialreview.bo.SpecialReviewExemption;

/**
 * Defines the Special Review Exemption for an Award.
 */
public class AwardSpecialReviewExemption extends SpecialReviewExemption {

    private static final long serialVersionUID = -589624827761999058L;

    private Long awardSpecialReviewExemptionId;

    private Long awardSpecialReviewId;

    private AwardSpecialReview awardSpecialReview;

    public Long getAwardSpecialReviewExemptionId() {
        return awardSpecialReviewExemptionId;
    }

    public void setAwardSpecialReviewExemptionId(Long awardSpecialReviewExemptionId) {
        this.awardSpecialReviewExemptionId = awardSpecialReviewExemptionId;
    }

    public Long getAwardSpecialReviewId() {
        return awardSpecialReviewId;
    }

    public void setAwardSpecialReviewId(Long awardSpecialReviewId) {
        this.awardSpecialReviewId = awardSpecialReviewId;
    }

    public AwardSpecialReview getAwardSpecialReview() {
        return awardSpecialReview;
    }

    public void setAwardSpecialReview(AwardSpecialReview awardSpecialReview) {
        this.awardSpecialReview = awardSpecialReview;
    }
}

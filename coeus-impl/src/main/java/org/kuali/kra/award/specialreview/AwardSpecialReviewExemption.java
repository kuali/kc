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
package org.kuali.kra.award.specialreview;

import org.kuali.coeus.common.framework.compliance.exemption.SpecialReviewExemption;

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

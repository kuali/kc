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
package org.kuali.kra.award.specialreview;

import org.kuali.kra.SequenceAssociate;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.common.specialreview.bo.SpecialReview;

/**
 * Defines the Award Special Review.
 */
public class AwardSpecialReview extends SpecialReview<AwardSpecialReviewExemption> implements SequenceAssociate<Award> {

    private static final long serialVersionUID = -414391670637651376L;

    private Long awardSpecialReviewId;

    private Long awardId;

    private Award sequenceOwner;

    public Long getAwardSpecialReviewId() {
        return awardSpecialReviewId;
    }

    public void setAwardSpecialReviewId(Long awardSpecialReviewId) {
        this.awardSpecialReviewId = awardSpecialReviewId;
    }

    public Long getAwardId() {
        return awardId;
    }

    public void setAwardId(Long awardId) {
        this.awardId = awardId;
    }

    public Award getSequenceOwner() {
        return sequenceOwner;
    }

    public void setSequenceOwner(Award sequenceOwner) {
        this.sequenceOwner = sequenceOwner;
    }

    public Integer getSequenceNumber() {
        return sequenceOwner != null ? sequenceOwner.getSequenceNumber() : null;
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.Sequenceable#resetPersistenceState()
     */
    public void resetPersistenceState() {
        awardSpecialReviewId = null;
        for (AwardSpecialReviewExemption exemption : getSpecialReviewExemptions()) {
            exemption.setAwardSpecialReviewExemptionId(null);
            exemption.setAwardSpecialReviewId(null);
        }
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.specialreview.bo.SpecialReview#createSpecialReviewExemption(java.lang.String)
     */
    @Override
    public AwardSpecialReviewExemption createSpecialReviewExemption(String exemptionTypeCode) {
        AwardSpecialReviewExemption awardSpecialReviewExemption = new AwardSpecialReviewExemption();
        awardSpecialReviewExemption.setExemptionTypeCode(exemptionTypeCode);
        awardSpecialReviewExemption.setAwardSpecialReview(this);
        return awardSpecialReviewExemption;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((awardId == null) ? 0 : awardId.hashCode());
        result = prime * result + ((awardSpecialReviewId == null) ? 0 : awardSpecialReviewId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        AwardSpecialReview other = (AwardSpecialReview) obj;
        if (awardId == null) {
            if (other.awardId != null) {
                return false;
            }
        } else if (!awardId.equals(other.awardId)) {
            return false;
        }
        if (awardSpecialReviewId == null) {
            if (other.awardSpecialReviewId != null) {
                return false;
            }
        } else if (!awardSpecialReviewId.equals(other.awardSpecialReviewId)) {
            return false;
        }
        return true;
    }
}

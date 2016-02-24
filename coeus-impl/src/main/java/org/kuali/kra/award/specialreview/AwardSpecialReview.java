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

import org.kuali.coeus.common.framework.version.sequence.associate.SequenceAssociate;
import org.kuali.coeus.common.framework.compliance.core.SpecialReview;
import org.kuali.kra.award.home.Award;

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

    @Override
    public void resetPersistenceState() {
        awardSpecialReviewId = null;
        for (AwardSpecialReviewExemption exemption : getSpecialReviewExemptions()) {
            exemption.setAwardSpecialReviewExemptionId(null);
            exemption.setAwardSpecialReviewId(null);
        }
    }

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

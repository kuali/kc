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

package org.kuali.kra.award.specialreview;

import java.util.LinkedHashMap;

import org.kuali.kra.SequenceAssociate;
import org.kuali.kra.SequenceOwner;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.AbstractSpecialReview;
/**
 * 
 * This class represents AwardSpecialReview BO
 */
public class AwardSpecialReview extends AbstractSpecialReview<AwardSpecialReviewExemption> implements SequenceAssociate { 
	//TODO: awardnumber, sequencenumber to be added
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

    /**
     * It creates new AwardSpecialReviewExemption instance
     * @see org.kuali.kra.bo.AbstractSpecialReview#newSpecialReviewExemption(java.lang.String)
     */
    @Override
    public AwardSpecialReviewExemption newSpecialReviewExemption(String exemptionTypeCode) {
        AwardSpecialReviewExemption awardSpecialReviewExemption = new AwardSpecialReviewExemption();
        awardSpecialReviewExemption.setExemptionTypeCode(exemptionTypeCode);
        awardSpecialReviewExemption.setAwardSpecialReview(this);
        return awardSpecialReviewExemption;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((award == null) ? 0 : award.hashCode());
        result = prime * result + ((awardSpecialReviewId == null) ? 0 : awardSpecialReviewId.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        AwardSpecialReview other = (AwardSpecialReview) obj;
        if (award == null) {
            if (other.award != null)
                return false;
        }
        else if (!award.equals(other.award))
            return false;
        if (awardSpecialReviewId == null) {
            if (other.awardSpecialReviewId != null)
                return false;
        }
        else if (!awardSpecialReviewId.equals(other.awardSpecialReviewId))
            return false;
        return true;
    }

    @Override
    public Long getSpecialReviewId() {
        return awardSpecialReviewId;
    }

    /**
     * @see org.kuali.kra.SequenceAssociate#getSequenceOwner()
     */
    public SequenceOwner getSequenceOwner() {
        return getAward();
    }

    /**
     * @see org.kuali.kra.SequenceAssociate#setSequenceOwner(org.kuali.kra.SequenceOwner)
     */
    public void setSequenceOwner(SequenceOwner newlyVersionedOwner) {
        setAward((Award) newlyVersionedOwner);
    }

    /**
     * @see org.kuali.kra.Sequenceable#getSequenceNumber()
     */
    public Integer getSequenceNumber() {
        return award != null ? award.getSequenceNumber() : null;
    }
    
    /**
     * @see org.kuali.kra.Sequenceable#resetPersistenceState()
     */
    public void resetPersistenceState() {
        this.awardSpecialReviewId = null;
    }
	
}
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
package org.kuali.kra.institutionalproposal.home;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.AbstractSpecialReviewExemption;

/**
 * This class represents InstitutionalProposalSpecialReviewExemption
 */
public class InstitutionalProposalSpecialReviewExemption extends AbstractSpecialReviewExemption {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 2247196757014908830L;
    
    private Long institutionalProposalSpecialReviewExemptionId;
    private InstitutionalProposalSpecialReview institutionalProposalSpecialReview;
 
    /**
     * Gets the awardSpecialReviewExemptionId attribute. 
     * @return Returns the awardSpecialReviewExemptionId.
     */
    public Long getInstitutionalProposalSpecialReviewExemptionId() {
        return institutionalProposalSpecialReviewExemptionId;
    }
    /**
     * Sets the awardSpecialReviewExemptionId attribute value.
     * @param awardSpecialReviewExemptionId The awardSpecialReviewExemptionId to set.
     */
    public void setInstitutionalProposalSpecialReviewExemptionId(Long institutionalProposalSpecialReviewExemptionId) {
        this.institutionalProposalSpecialReviewExemptionId = institutionalProposalSpecialReviewExemptionId;
    }
    /**
     * Gets the awardSpecialReview attribute. 
     * @return Returns the awardSpecialReview.
     */
    public InstitutionalProposalSpecialReview getInstitutionalProposalSpecialReview() {
        return institutionalProposalSpecialReview;
    }
    /**
     * Sets the awardSpecialReview attribute value.
     * @param awardSpecialReview The awardSpecialReview to set.
     */
    public void setInstitutionalProposalSpecialReview(InstitutionalProposalSpecialReview institutionalProposalSpecialReview) {
        this.institutionalProposalSpecialReview = institutionalProposalSpecialReview;
    }
    
    /**
     * @see org.kuali.kra.Sequenceable#resetPersistenceState()
     */
    public void resetPersistenceState() {
        this.institutionalProposalSpecialReviewExemptionId = null;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = super.toStringMapper();
        hashMap.put("institutionalProposalSpecialReviewExemptionId", getInstitutionalProposalSpecialReviewExemptionId());
        return hashMap;
    }
    
}

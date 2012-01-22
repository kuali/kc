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
package org.kuali.kra.institutionalproposal.specialreview;

import org.kuali.kra.SequenceAssociate;
import org.kuali.kra.common.specialreview.bo.SpecialReview;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;

/**
 * Defines the Institutional Proposal Special Review.
 */
public class InstitutionalProposalSpecialReview extends SpecialReview<InstitutionalProposalSpecialReviewExemption> implements SequenceAssociate<InstitutionalProposal> {

    private static final long serialVersionUID = -3351482754078003727L;

    private Long proposalSpecialReviewId;

    private Long proposalId;

    private InstitutionalProposal sequenceOwner;

    public Long getProposalSpecialReviewId() {
        return proposalSpecialReviewId;
    }

    public void setProposalSpecialReviewId(Long proposalSpecialReviewId) {
        this.proposalSpecialReviewId = proposalSpecialReviewId;
    }

    public Long getProposalId() {
        return proposalId;
    }

    public void setProposalId(Long proposalId) {
        this.proposalId = proposalId;
    }

    public InstitutionalProposal getSequenceOwner() {
        return sequenceOwner;
    }

    public void setSequenceOwner(InstitutionalProposal sequenceOwner) {
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
        proposalSpecialReviewId = null;
        for (InstitutionalProposalSpecialReviewExemption exemption : getSpecialReviewExemptions()) {
            exemption.setProposalSpecialReviewExemptionId(null);
            exemption.setProposalSpecialReviewId(null);
        }
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.specialreview.bo.SpecialReview#createSpecialReviewExemption(java.lang.String)
     */
    @Override
    public InstitutionalProposalSpecialReviewExemption createSpecialReviewExemption(String exemptionTypeCode) {
        InstitutionalProposalSpecialReviewExemption institutionalProposalSpecialReviewExemption = new InstitutionalProposalSpecialReviewExemption();
        institutionalProposalSpecialReviewExemption.setExemptionTypeCode(exemptionTypeCode);
        institutionalProposalSpecialReviewExemption.setInstitutionalProposalSpecialReview(this);
        return institutionalProposalSpecialReviewExemption;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((proposalId == null) ? 0 : proposalId.hashCode());
        result = prime * result + ((proposalSpecialReviewId == null) ? 0 : proposalSpecialReviewId.hashCode());
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
        InstitutionalProposalSpecialReview other = (InstitutionalProposalSpecialReview) obj;
        if (proposalId == null) {
            if (other.proposalId != null) {
                return false;
            }
        } else if (!proposalId.equals(other.proposalId)) {
            return false;
        }
        if (proposalSpecialReviewId == null) {
            if (other.proposalSpecialReviewId != null) {
                return false;
            }
        } else if (!proposalSpecialReviewId.equals(other.proposalSpecialReviewId)) {
            return false;
        }
        return true;
    }
}

/*
 * Copyright 2005-2010 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the License);
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
package org.kuali.kra.proposaldevelopment.specialreview;

import org.kuali.kra.common.specialreview.bo.SpecialReview;
import org.kuali.kra.proposaldevelopment.hierarchy.HierarchyMaintainable;

/**
 * Defines a Special Review for a Development Proposal.
 */
public class ProposalSpecialReview extends SpecialReview<ProposalSpecialReviewExemption> implements HierarchyMaintainable {

    private static final long serialVersionUID = 4616138222389685155L;

    private Long proposalSpecialReviewId;

    private String proposalNumber;

    private String hierarchyProposalNumber;

    private boolean hiddenInHierarchy;

    public void setProposalSpecialReviewId(Long proposalSpecialReviewId) {
        this.proposalSpecialReviewId = proposalSpecialReviewId;
    }

    public Long getProposalSpecialReviewId() {
        return proposalSpecialReviewId;
    }

    public String getProposalNumber() {
        return this.proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public String getHierarchyProposalNumber() {
        return hierarchyProposalNumber;
    }

    public void setHierarchyProposalNumber(String hierarchyProposalNumber) {
        this.hierarchyProposalNumber = hierarchyProposalNumber;
    }

    public boolean isHiddenInHierarchy() {
        return hiddenInHierarchy;
    }

    public void setHiddenInHierarchy(boolean hiddenInHierarchy) {
        this.hiddenInHierarchy = hiddenInHierarchy;
    }

    @Override
    public ProposalSpecialReviewExemption createSpecialReviewExemption(String exemptionTypeCode) {
        ProposalSpecialReviewExemption proposalSpecialReviewExemption = new ProposalSpecialReviewExemption();
        proposalSpecialReviewExemption.setExemptionTypeCode(exemptionTypeCode);
        proposalSpecialReviewExemption.setProposalSpecialReview(this);
        return proposalSpecialReviewExemption;
    }

    public int hierarchyHashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSpecialReviewNumber() == null) ? 0 : getSpecialReviewNumber().hashCode());
        result = prime * result + ((getSpecialReviewTypeCode() == null) ? 0 : getSpecialReviewTypeCode().hashCode());
        return result;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (hiddenInHierarchy ? 1231 : 1237);
        result = prime * result + ((hierarchyProposalNumber == null) ? 0 : hierarchyProposalNumber.hashCode());
        result = prime * result + ((proposalNumber == null) ? 0 : proposalNumber.hashCode());
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
        ProposalSpecialReview other = (ProposalSpecialReview) obj;
        if (hiddenInHierarchy != other.hiddenInHierarchy) {
            return false;
        }
        if (hierarchyProposalNumber == null) {
            if (other.hierarchyProposalNumber != null) {
                return false;
            }
        } else if (!hierarchyProposalNumber.equals(other.hierarchyProposalNumber)) {
            return false;
        }
        if (proposalNumber == null) {
            if (other.proposalNumber != null) {
                return false;
            }
        } else if (!proposalNumber.equals(other.proposalNumber)) {
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

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.Sequenceable#resetPersistenceState()
     */
    public void resetPersistenceState() {
        proposalSpecialReviewId = null;
        for (ProposalSpecialReviewExemption exemption : getSpecialReviewExemptions()) {
            exemption.setProposalSpecialReviewExemptionId(null);
            exemption.setProposalSpecialReviewId(null);
        }
    }
}

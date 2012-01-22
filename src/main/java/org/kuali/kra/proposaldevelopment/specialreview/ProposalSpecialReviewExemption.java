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
package org.kuali.kra.proposaldevelopment.specialreview;

import org.kuali.kra.common.specialreview.bo.SpecialReviewExemption;

/**
 * Defines a Special Review Exemption for a Development Proposal.
 */
public class ProposalSpecialReviewExemption extends SpecialReviewExemption {

    private static final long serialVersionUID = -2309851480480819783L;

    private Long proposalSpecialReviewExemptionId;

    private Long proposalSpecialReviewId;

    private ProposalSpecialReview proposalSpecialReview;

    public Long getProposalSpecialReviewExemptionId() {
        return proposalSpecialReviewExemptionId;
    }

    public void setProposalSpecialReviewExemptionId(Long proposalSpecialReviewExemptionId) {
        this.proposalSpecialReviewExemptionId = proposalSpecialReviewExemptionId;
    }

    public Long getProposalSpecialReviewId() {
        return proposalSpecialReviewId;
    }

    public void setProposalSpecialReviewId(Long proposalSpecialReviewId) {
        this.proposalSpecialReviewId = proposalSpecialReviewId;
    }

    public ProposalSpecialReview getProposalSpecialReview() {
        return proposalSpecialReview;
    }

    public void setProposalSpecialReview(ProposalSpecialReview proposalSpecialReview) {
        this.proposalSpecialReview = proposalSpecialReview;
    }
}

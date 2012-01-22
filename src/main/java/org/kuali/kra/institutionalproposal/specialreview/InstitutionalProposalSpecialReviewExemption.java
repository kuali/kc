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
package org.kuali.kra.institutionalproposal.specialreview;

import org.kuali.kra.common.specialreview.bo.SpecialReviewExemption;

/**
 * Defines the Special Review Exemption for Institutional Proposal.
 */
public class InstitutionalProposalSpecialReviewExemption extends SpecialReviewExemption {

    private static final long serialVersionUID = -329478900344140486L;

    private Long proposalSpecialReviewExemptionId;

    private Long proposalSpecialReviewId;

    private InstitutionalProposalSpecialReview institutionalProposalSpecialReview;

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

    public void setInstitutionalProposalSpecialReview(InstitutionalProposalSpecialReview institutionalProposalSpecialReview) {
        this.institutionalProposalSpecialReview = institutionalProposalSpecialReview;
    }

    public InstitutionalProposalSpecialReview getInstitutionalProposalSpecialReview() {
        return institutionalProposalSpecialReview;
    }
}

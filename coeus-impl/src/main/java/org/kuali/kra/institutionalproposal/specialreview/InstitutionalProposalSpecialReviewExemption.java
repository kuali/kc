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
package org.kuali.kra.institutionalproposal.specialreview;

import org.kuali.coeus.common.framework.compliance.exemption.SpecialReviewExemption;

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

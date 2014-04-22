/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.propdev.impl.specialreview;

import org.kuali.coeus.common.specialreview.impl.bo.SpecialReviewExemption;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

import javax.persistence.*;

/**
 * Defines a Special Review Exemption for a Development Proposal.
 */
@Entity
@Table(name = "EPS_PROP_EXEMPT_NUMBER")
public class ProposalSpecialReviewExemption extends SpecialReviewExemption {

    private static final long serialVersionUID = -2309851480480819783L;

    @PortableSequenceGenerator(name = "SEQ_EPS_PROP_EXEMPT_NUMBER_ID")
    @GeneratedValue(generator = "SEQ_EPS_PROP_EXEMPT_NUMBER_ID")
    @Id
    @Column(name = "PROPOSAL_EXEMPT_NUMBER_ID")
    private Long proposalSpecialReviewExemptionId;

    @Column(name = "PROPOSAL_SPECIAL_REVIEW_ID")
    private Long proposalSpecialReviewId;

    @ManyToOne(targetEntity = ProposalSpecialReview.class, cascade = { CascadeType.REFRESH } )
    @JoinColumn(name = "PROPOSAL_SPECIAL_REVIEW_ID", referencedColumnName = "PROPOSAL_SPECIAL_REVIEW_ID", insertable = false, updatable = false)
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

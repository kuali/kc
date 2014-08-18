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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.kuali.coeus.common.framework.compliance.exemption.SpecialReviewExemption;
import org.kuali.coeus.common.framework.compliance.exemption.ExemptionType;
import org.kuali.coeus.propdev.api.specialreview.ProposalSpecialReviewExemptionContract;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

/**
 * Defines a Special Review Exemption for a Development Proposal.
 */
@Entity
@Table(name = "EPS_PROP_EXEMPT_NUMBER")
public class ProposalSpecialReviewExemption extends SpecialReviewExemption implements ProposalSpecialReviewExemptionContract {

    private static final long serialVersionUID = -2309851480480819783L;

    @PortableSequenceGenerator(name = "SEQ_EPS_PROP_EXEMPT_NUMBER_ID")
    @GeneratedValue(generator = "SEQ_EPS_PROP_EXEMPT_NUMBER_ID")
    @Id
    @Column(name = "PROPOSAL_EXEMPT_NUMBER_ID")
    private Long id;

    @ManyToOne(cascade = { CascadeType.REFRESH } )
    @JoinColumn(name = "PROPOSAL_SPECIAL_REVIEW_ID")
    private ProposalSpecialReview proposalSpecialReview;

    public ProposalSpecialReviewExemption() {
    }
    
    public ProposalSpecialReviewExemption(ProposalSpecialReview proposalSpecialReview, ExemptionType exemptionType) {
        this.proposalSpecialReview = proposalSpecialReview;
        setExemptionType(exemptionType);
        setExemptionTypeCode(exemptionType.getCode());
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProposalSpecialReview getProposalSpecialReview() {
        return proposalSpecialReview;
    }

    public void setProposalSpecialReview(ProposalSpecialReview proposalSpecialReview) {
        this.proposalSpecialReview = proposalSpecialReview;
    }
}

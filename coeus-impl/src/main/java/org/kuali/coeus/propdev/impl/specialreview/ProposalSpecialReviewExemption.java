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

/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.propdev.impl.specialreview;

import java.util.List;

import org.kuali.coeus.common.framework.compliance.core.SpecialReview;
import org.kuali.coeus.propdev.api.specialreview.ProposalSpecialReviewContract;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.hierarchy.HierarchyMaintainable;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;

import javax.persistence.*;

/**
 * Defines a Special Review for a Development Proposal.
 */
@Entity
@Table(name = "EPS_PROP_SPECIAL_REVIEW")
public class ProposalSpecialReview extends SpecialReview<ProposalSpecialReviewExemption> implements HierarchyMaintainable, ProposalSpecialReviewContract {

    private static final long serialVersionUID = 4616138222389685155L;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "PROPOSAL_NUMBER")
    private DevelopmentProposal developmentProposal;
    
    @PortableSequenceGenerator(name = "SEQ_EPS_PROP_SPECIAL_REVIEW_ID")
    @GeneratedValue(generator = "SEQ_EPS_PROP_SPECIAL_REVIEW_ID")
    @Id
    @Column(name = "PROPOSAL_SPECIAL_REVIEW_ID")
    private Long id;

    @Column(name = "HIERARCHY_PROPOSAL_NUMBER")
    private String hierarchyProposalNumber;

    @Column(name = "HIDE_IN_HIERARCHY")
    @Convert(converter = BooleanYNConverter.class)
    private boolean hiddenInHierarchy;

    @OneToMany(mappedBy="proposalSpecialReview", orphanRemoval = true, cascade = { CascadeType.ALL })
    private List<ProposalSpecialReviewExemption> specialReviewExemptions;
    
	public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getHierarchyProposalNumber() {
        return hierarchyProposalNumber;
    }

    public void setHierarchyProposalNumber(String hierarchyProposalNumber) {
        this.hierarchyProposalNumber = hierarchyProposalNumber;
    }

    @Override
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
        result = prime * result + ((getDevelopmentProposal() == null) ? 0 : getDevelopmentProposal().getProposalNumber().hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        if (getDevelopmentProposal().getProposalNumber() == null) {
            if (other.getDevelopmentProposal().getProposalNumber() != null) {
                return false;
            }
        } else if (!getDevelopmentProposal().getProposalNumber().equals(other.getDevelopmentProposal().getProposalNumber())) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    public void resetPersistenceState() {
        id = null;
        for (ProposalSpecialReviewExemption exemption : getSpecialReviewExemptions()) {
            exemption.setId(null);
            exemption.setProposalSpecialReview(null);
        }
    }

    @Override
    public List<ProposalSpecialReviewExemption> getSpecialReviewExemptions() {
		return specialReviewExemptions;
	}

	public void setSpecialReviewExemptions(List<ProposalSpecialReviewExemption> specialReviewExemptions) {
    	if (specialReviewExemptions != null) {
    		this.specialReviewExemptions = specialReviewExemptions;
    	} else {
    		this.specialReviewExemptions.clear();
    	}
    	for(ProposalSpecialReviewExemption proposalSpecialReviewExemption : specialReviewExemptions) {
    		proposalSpecialReviewExemption.setProposalSpecialReview(this);
    	}
	}

	public DevelopmentProposal getDevelopmentProposal() {
		return developmentProposal;
	}

	public void setDevelopmentProposal(DevelopmentProposal developmentProposal) {
		this.developmentProposal = developmentProposal;
	}

    @Override
    public String getProposalNumber() {
        return getDevelopmentProposal().getProposalNumber();
    }
}

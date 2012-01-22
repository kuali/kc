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
package org.kuali.kra.award.home.fundingproposal;

import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;

/**
 * Represents the relationship between an Award and a funding Institutional Proposal.
 * The relationship is maintained from both modules, so it has to be treated specially in various 
 * parts of the application, such as versioning.
 */
public class AwardFundingProposal extends KraPersistableBusinessObjectBase {

    private static final long serialVersionUID = -8135146676358083314L;

    private Long awardFundingProposalId;

    private Award award;

    private InstitutionalProposal proposal;

    // for OJB  
    private Long awardId;

    private Long proposalId;

    private boolean active;

    /**
     * Constructs a AwardFundingProposal.java.
     */
    public AwardFundingProposal() {
        setActive(true);
    }

    /**
     * Constructs a AwardFundingProposal.java.
     * @param award
     * @param institutionalProposal
     */
    public AwardFundingProposal(Award award, InstitutionalProposal proposal) {
        this();
        setAward(award);
        setProposal(proposal);
    }

    /**
     * @return Returns the award.
     */
    public Award getAward() {
        return award;
    }

    /**
     * @return Returns the awardFundingProposalId.
     */
    public Long getAwardFundingProposalId() {
        return awardFundingProposalId;
    }

    /**
     * @return Returns the awardId.
     */
    public Long getAwardId() {
        return awardId;
    }

    /**
     * @return Returns the proposal.
     */
    public InstitutionalProposal getProposal() {
        return proposal;
    }

    /**
     * @return Returns the proposalId.
     */
    public Long getProposalId() {
        return proposalId;
    }

    /**
     * @param award The award to set.
     */
    public void setAward(Award award) {
        this.award = award;
        this.awardId = award != null ? award.getAwardId() : null;
    }

    /**
     * @param awardFundingProposalId The awardFundingProposalId to set.
     */
    public void setAwardFundingProposalId(Long awardFundingProposalId) {
        this.awardFundingProposalId = awardFundingProposalId;
    }

    /**
     * @param awardId The awardId to set.
     */
    public void setAwardId(Long awardId) {
        this.awardId = awardId;
    }

    /**
     * @param proposal The proposal to set.
     */
    public void setProposal(InstitutionalProposal proposal) {
        this.proposal = proposal;
        this.proposalId = proposal != null ? proposal.getProposalId() : null;
    }

    /**
     * @param proposalId The proposalId to set.
     */
    public void setProposalId(Long proposalId) {
        this.proposalId = proposalId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * True if this entity has ever been persisted (even if there are unpersisted in-memory modifications).
     * @return boolean
     */
    public boolean isPersisted() {
        return this.getAwardFundingProposalId() != null;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof AwardFundingProposal)) return false;
        AwardFundingProposal other = (AwardFundingProposal) obj;
        if (awardId == null) {
            if (other.awardId != null) return false;
        } else if (!awardId.equals(other.awardId)) return false;
        if (proposalId == null) {
            if (other.proposalId != null) return false;
        } else if (!proposalId.equals(other.proposalId)) return false;
        return true;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((awardId == null) ? 0 : awardId.hashCode());
        result = prime * result + ((proposalId == null) ? 0 : proposalId.hashCode());
        return result;
    }
}

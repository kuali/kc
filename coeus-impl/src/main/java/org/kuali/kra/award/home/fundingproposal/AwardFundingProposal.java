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
package org.kuali.kra.award.home.fundingproposal;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalBoLite;


/**
 * Represents the relationship between an Award and a funding Institutional Proposal.
 * The relationship is maintained from both modules, so it has to be treated specially in various 
 * parts of the application, such as versioning.
 */
public class AwardFundingProposal extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = -8135146676358083314L;

    private Long awardFundingProposalId;

    private Award award;

    private InstitutionalProposalBoLite proposal;

    // for OJB  
    private Long awardId;

    private Long proposalId;

    private boolean active;


    public AwardFundingProposal() {
        setActive(true);
    }

    /**
     * Constructs a AwardFundingProposal.java.
     * @param award
     * @param institutionalProposal
     */
    public AwardFundingProposal(Award award, InstitutionalProposalBoLite proposal) {
        this();
        setAward(award);
        setProposal(proposal);
    }
    
    public AwardFundingProposal(Award award, InstitutionalProposal proposal) {
        this();
        setAward(award);
        setProposal(liteVersion(proposal));
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
    public InstitutionalProposalBoLite getProposal() {
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
    public void setProposal(InstitutionalProposalBoLite proposal) {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((awardId == null) ? 0 : awardId.hashCode());
        result = prime * result + ((proposalId == null) ? 0 : proposalId.hashCode());
        return result;
    }
    
    protected InstitutionalProposalBoLite liteVersion(InstitutionalProposal ip) {
    	InstitutionalProposalBoLite pbl = new InstitutionalProposalBoLite();
    	pbl.setActivityTypeCode(ip.getActivityTypeCode());
    	pbl.setLeadUnit(ip.getLeadUnit());
    	pbl.setProposalId(ip.getProposalId());
    	pbl.setProposalNumber(ip.getProposalNumber());
    	pbl.setProposalTypeCode(ip.getProposalTypeCode());
    	pbl.setRequestedEndDateInitial(ip.getRequestedEndDateInitial());
    	pbl.setRequestedEndDateTotal(ip.getRequestedEndDateTotal());
    	pbl.setRequestedStartDateInitial(ip.getRequestedStartDateInitial());
    	pbl.setRequestedStartDateTotal(ip.getRequestedStartDateTotal());
    	pbl.setSequenceNumber(ip.getSequenceNumber());
    	pbl.setSponsor(ip.getSponsor());
    	pbl.setSponsorCode(ip.getSponsorCode());
    	pbl.setStatusCode(ip.getStatusCode());
    	pbl.setTitle(ip.getTitle());
    	pbl.setTotalDirectCostInitial(ip.getTotalDirectCostInitial());
    	pbl.setTotalDirectCostTotal(ip.getTotalDirectCostTotal());
    	pbl.setTotalIndirectCostInitial(ip.getTotalIndirectCostInitial());
    	pbl.setTotalIndirectCostTotal(ip.getTotalIndirectCostTotal());
    	pbl.setUnitNumber(ip.getUnitNumber());
    	pbl.setProjectPersons(ip.getProjectPersons());
    	pbl.setSponsorName(ip.getSponsorName());
    	pbl.setProposalType(ip.getProposalType());
    	pbl.setActivityType(ip.getActivityType());
    	return pbl;
    }
    
    public Integer getAwardSequenceNumber() {
    	return getAward().getSequenceNumber();
    }
}

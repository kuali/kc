/*
 * Copyright 2006-2008 The Kuali Foundation
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

import java.util.LinkedHashMap;

import org.kuali.kra.SequenceAssociate;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;

public class AwardFundingProposal extends KraPersistableBusinessObjectBase implements SequenceAssociate<Award> {
    private static final long serialVersionUID = -8135146676358083314L;
    
    private Long awardFundingProposalId;
    private Award award;
    private InstitutionalProposal proposal;

    // for OJB
    private Long awardId;
    private Long proposalId;

    /**
     * Constructs a AwardFundingProposal.java.
     */
    public AwardFundingProposal() {
        
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
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof AwardFundingProposal))
            return false;
        AwardFundingProposal other = (AwardFundingProposal) obj;
        if (awardId == null) {
            if (other.awardId != null)
                return false;
        }
        else if (!awardId.equals(other.awardId))
            return false;
        if (proposalId == null) {
            if (other.proposalId != null)
                return false;
        }
        else if (!proposalId.equals(other.proposalId))
            return false;
        return true;
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
    
    /**
     * @see org.kuali.kra.SequenceAssociate#getSequenceOwner()
     */
    public Award getSequenceOwner() {
        return award;
    }
    
    /**
     * @param newlyVersionedOwner
     */
    public void setSequenceOwner(Award newlyVersionedOwner) {
        this.award = newlyVersionedOwner;
    }
    
    /**
     * @see org.kuali.kra.Sequenceable#getSequenceNumber()
     */
    public Integer getSequenceNumber() {
        return award.getSequenceNumber();
    }
    
    /**
     * @see org.kuali.kra.Sequenceable#resetPersistenceState()
     */
    public void resetPersistenceState() {
        awardFundingProposalId = null;
        versionNumber = null;
    }

    /**
     * @see org.kuali.rice.kns.bo.BusinessObjectBase#toStringMapper()
     */
    @Override
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        
        map.put("awardFundingProposalId", awardFundingProposalId);
        map.put("award", award);
        map.put("proposal", proposal);
        
        return map;
    }
}

/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.institutionalproposal.home;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.AbstractSpecialReview;

public class InstitutionalProposalSpecialReview extends AbstractSpecialReview<InstitutionalProposalSpecialReviewExemption> { 
    
    private static final long serialVersionUID = 1L;

    private Long proposalSpecialReviewId; 
    private String proposalNumber; 
    private Integer sequenceNumber; 

    private InstitutionalProposal institutionalProposal; 
    
    public InstitutionalProposalSpecialReview() { 

    } 
    
    public Long getProposalSpecialReviewId() {
        return proposalSpecialReviewId;
    }

    public void setProposalSpecialReviewId(Long proposalSpecialReviewId) {
        this.proposalSpecialReviewId = proposalSpecialReviewId;
    }


    public InstitutionalProposal getInstitutionalProposal() {
        return institutionalProposal;
    }

    /**
     * Sets the institutionalProposal attribute value.
     * @param institutionalProposal The institutionalProposal to set.
     */
    public void setInstitutionalProposal(InstitutionalProposal institutionalProposal) {
        this.institutionalProposal = institutionalProposal;
        if(institutionalProposal != null) {
            setSequenceNumber(institutionalProposal.getSequenceNumber());
            setProposalNumber(institutionalProposal.getProposalNumber());
        } else {
            setSequenceNumber(0);
            setProposalNumber("");
        }
    }
    
    /**
     * Gets the proposalNumber attribute. 
     * @return Returns the proposalNumber.
     */
    public String getProposalNumber() {
        return proposalNumber;
    }

    /**
     * Sets the proposalNumber attribute value.
     * @param proposalNumber The proposalNumber to set.
     */
    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    /**
     * Gets the sequenceNumber attribute. 
     * @return Returns the sequenceNumber.
     */
    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * Sets the sequenceNumber attribute value.
     * @param sequenceNumber The sequenceNumber to set.
     */
    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    @SuppressWarnings("unchecked")
    @Override 
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = super.toStringMapper();
        hashMap.put("proposalNumber", this.getProposalNumber());
        hashMap.put("sequenceNumber", this.getSequenceNumber());
        hashMap.put("institutionalProposal", this.getInstitutionalProposal());
        return hashMap;
    }
    
    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((institutionalProposal == null) ? 0 : institutionalProposal.hashCode());
        result = prime * result + ((proposalNumber == null) ? 0 : proposalNumber.hashCode());
        result = prime * result + ((proposalSpecialReviewId == null) ? 0 : proposalSpecialReviewId.hashCode());
        result = prime * result + ((sequenceNumber == null) ? 0 : sequenceNumber.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        InstitutionalProposalSpecialReview other = (InstitutionalProposalSpecialReview) obj;
        if (institutionalProposal == null) {
            if (other.institutionalProposal != null)
                return false;
        }
        else if (!institutionalProposal.equals(other.institutionalProposal))
            return false;
        if (proposalNumber == null) {
            if (other.proposalNumber != null)
                return false;
        }
        else if (!proposalNumber.equals(other.proposalNumber))
            return false;
        if (proposalSpecialReviewId == null) {
            if (other.proposalSpecialReviewId != null)
                return false;
        }
        else if (!proposalSpecialReviewId.equals(other.proposalSpecialReviewId))
            return false;
        if (sequenceNumber == null) {
            if (other.sequenceNumber != null)
                return false;
        }
        else if (!sequenceNumber.equals(other.sequenceNumber))
            return false;
        return true;
    }

    /**
     * It creates new AwardSpecialReviewExemption instance
     * @see org.kuali.kra.bo.AbstractSpecialReview#newSpecialReviewExemption(java.lang.String)
     */
    @Override
    public InstitutionalProposalSpecialReviewExemption newSpecialReviewExemption(String exemptionTypeCode) {
        InstitutionalProposalSpecialReviewExemption institutionalProposalSpecialReviewExemption = new InstitutionalProposalSpecialReviewExemption();
        institutionalProposalSpecialReviewExemption.setExemptionTypeCode(exemptionTypeCode);
        institutionalProposalSpecialReviewExemption.setInstitutionalProposalSpecialReview(this);
        return institutionalProposalSpecialReviewExemption;
    }

    @Override
    public Long getSpecialReviewId() {
        return proposalSpecialReviewId;
    }

    
}
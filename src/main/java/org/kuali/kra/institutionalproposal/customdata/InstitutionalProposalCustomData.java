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
package org.kuali.kra.institutionalproposal.customdata;

import java.util.LinkedHashMap;

import org.kuali.kra.SequenceAssociate;
import org.kuali.kra.SequenceOwner;
import org.kuali.kra.bo.CustomAttribute;
import org.kuali.kra.institutionalproposal.InstitutionalProposalAssociate;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;

/**
 * This is Business Object class for IP custom data BO.
 */
public class InstitutionalProposalCustomData extends InstitutionalProposalAssociate implements SequenceAssociate { 
    
    private static final long serialVersionUID = 1L;

    private Long proposalCustomDataId; 
    private Long customAttributeId; 
    private String value; 
    
    private CustomAttribute customAttribute; 
    
    /**
     * Constructs a InstitutionalProposalCustomData.java.
     */
    public InstitutionalProposalCustomData() { 

    } 
    
    /**
     * This method...
     * @return
     */
    public Long getProposalCustomDataId() {
        return proposalCustomDataId;
    }

    /**
     * This method...
     * @param proposalCustomDataId
     */
    public void setProposalCustomDataId(Long proposalCustomDataId) {
        this.proposalCustomDataId = proposalCustomDataId;
    }

    /**
     * This method...
     * @return
     */
    public Long getCustomAttributeId() {
        return customAttributeId;
    }

    /**
     * This method...
     * @param customAttributeId
     */
    public void setCustomAttributeId(Long customAttributeId) {
        this.customAttributeId = customAttributeId;
    }

    /**
     * This method...
     * @return
     */
    public String getValue() {
        return value;
    }

    /**
     * This method...
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * This method...
     * @return
     */
    public CustomAttribute getCustomAttribute() {
        return customAttribute;
    }

    /**
     * This method...
     * @param customAttribute
     */
    public void setCustomAttribute(CustomAttribute customAttribute) {
        this.customAttribute = customAttribute;
    }
    
    /**
     * @see org.kuali.kra.SequenceAssociate#getSequenceOwner()
     */
    public SequenceOwner getSequenceOwner() {
        return getInstitutionalProposal();
    }

    /**
     * @see org.kuali.kra.SequenceAssociate#setSequenceOwner(org.kuali.kra.SequenceOwner)
     */
    public void setSequenceOwner(SequenceOwner newlyVersionedOwner) {
        setInstitutionalProposal((InstitutionalProposal) newlyVersionedOwner);
    }

    /**
     * @see org.kuali.kra.Sequenceable#resetPersistenceState()
     */
    public void resetPersistenceState() {
        this.proposalCustomDataId = null;
    }


    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("proposalCustomDataId", this.getProposalCustomDataId());
        hashMap.put("customAttributeId", this.getCustomAttributeId());
        hashMap.put("value", this.getValue());
        return hashMap;
    }
    

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((customAttribute == null) ? 0 : customAttribute.hashCode());
        result = prime * result + ((customAttributeId == null) ? 0 : customAttributeId.hashCode());
        result = prime * result + ((proposalCustomDataId == null) ? 0 : proposalCustomDataId.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
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
        if (getClass() != obj.getClass())
            return false;
        InstitutionalProposalCustomData other = (InstitutionalProposalCustomData) obj;
        if (customAttribute == null) {
            if (other.customAttribute != null)
                return false;
        }
        else if (!customAttribute.equals(other.customAttribute))
            return false;
        if (customAttributeId == null) {
            if (other.customAttributeId != null)
                return false;
        }
        else if (!customAttributeId.equals(other.customAttributeId))
            return false;
        if (proposalCustomDataId == null) {
            if (other.proposalCustomDataId != null)
                return false;
        }
        else if (!proposalCustomDataId.equals(other.proposalCustomDataId))
            return false;
        if (value == null) {
            if (other.value != null)
                return false;
        }
        else if (!value.equals(other.value))
            return false;
        return true;
    }

    
}
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

import org.kuali.kra.bo.CustomAttribute;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;

public class InstitutionalProposalCustomData extends KraPersistableBusinessObjectBase { 
    
    private static final long serialVersionUID = 1L;

    private Integer proposalCustomDataId; 
    private Integer proposalId; 
    private String proposalNumber; 
    private Integer sequenceNumber; 
    private Integer customAttributeId; 
    private String value; 
    
    private CustomAttribute customAttribute; 
    private InstitutionalProposal institutionalProposal; 
    
    public InstitutionalProposalCustomData() { 

    } 
    
    public Integer getProposalCustomDataId() {
        return proposalCustomDataId;
    }

    public void setProposalCustomDataId(Integer proposalCustomDataId) {
        this.proposalCustomDataId = proposalCustomDataId;
    }

    public Integer getProposalId() {
        return proposalId;
    }

    public void setProposalId(Integer proposalId) {
        this.proposalId = proposalId;
    }

    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Integer getCustomAttributeId() {
        return customAttributeId;
    }

    public void setCustomAttributeId(Integer customAttributeId) {
        this.customAttributeId = customAttributeId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public CustomAttribute getCustomAttribute() {
        return customAttribute;
    }

    public void setCustomAttribute(CustomAttribute customAttribute) {
        this.customAttribute = customAttribute;
    }

    public InstitutionalProposal getInstitutionalProposal() {
        return institutionalProposal;
    }

    public void setInstitutionalProposal(InstitutionalProposal institutionalProposal) {
        this.institutionalProposal = institutionalProposal;
    }

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("proposalCustomDataId", this.getProposalCustomDataId());
        hashMap.put("proposalId", this.getProposalId());
        hashMap.put("proposalNumber", this.getProposalNumber());
        hashMap.put("sequenceNumber", this.getSequenceNumber());
        hashMap.put("customAttributeId", this.getCustomAttributeId());
        hashMap.put("value", this.getValue());
        return hashMap;
    }
    
}
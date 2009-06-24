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

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.proposaldevelopment.bo.ScienceKeyword;

public class InstitutionalProposalScienceKeyword extends KraPersistableBusinessObjectBase { 
    
    private static final long serialVersionUID = 1L;

    private Integer proposalScienceKeywordId; 
    private Integer proposalId; 
    private String proposalNumber; 
    private Integer sequenceNumber; 
    private String scienceKeywordCode; 
    
    private ScienceKeyword scienceKeyword; 
    private InstitutionalProposal institutionalProposal; 
    
    public InstitutionalProposalScienceKeyword() { 

    } 
    
    public Integer getProposalScienceKeywordId() {
        return proposalScienceKeywordId;
    }

    public void setProposalScienceKeywordId(Integer proposalScienceKeywordId) {
        this.proposalScienceKeywordId = proposalScienceKeywordId;
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

    public String getScienceKeywordCode() {
        return scienceKeywordCode;
    }

    public void setScienceKeywordCode(String scienceKeywordCode) {
        this.scienceKeywordCode = scienceKeywordCode;
    }

    public ScienceKeyword getScienceKeyword() {
        return scienceKeyword;
    }

    public void setScienceKeyword(ScienceKeyword scienceKeyword) {
        this.scienceKeyword = scienceKeyword;
    }

    public InstitutionalProposal getInstitutionalProposal() {
        return institutionalProposal;
    }

    public void setProposal(InstitutionalProposal institutionalProposal) {
        this.institutionalProposal = institutionalProposal;
    }

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("proposalScienceKeywordId", this.getProposalScienceKeywordId());
        hashMap.put("proposalId", this.getProposalId());
        hashMap.put("proposalNumber", this.getProposalNumber());
        hashMap.put("sequenceNumber", this.getSequenceNumber());
        hashMap.put("scienceKeywordCode", this.getScienceKeywordCode());
        return hashMap;
    }
    
}
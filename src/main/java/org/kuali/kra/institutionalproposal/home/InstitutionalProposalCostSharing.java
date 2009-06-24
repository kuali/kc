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

public class InstitutionalProposalCostSharing extends KraPersistableBusinessObjectBase { 
    
    private static final long serialVersionUID = 1L;

    private Integer proposalCostSharingId; 
    private Integer proposalId; 
    private String proposalNumber; 
    private Integer sequenceNumber; 
    private String fiscalYear; 
    private Long costSharingPercentage; 
    private Integer costSharingTypeCode; 
    private String sourceAccount; 
    private Long amount; 
    
    private InstitutionalProposal institutionalProposal; 
    
    public InstitutionalProposalCostSharing() { 

    } 
    
    public Integer getProposalCostSharingId() {
        return proposalCostSharingId;
    }

    public void setProposalCostSharingId(Integer proposalCostSharingId) {
        this.proposalCostSharingId = proposalCostSharingId;
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

    public String getFiscalYear() {
        return fiscalYear;
    }

    public void setFiscalYear(String fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    public Long getCostSharingPercentage() {
        return costSharingPercentage;
    }

    public void setCostSharingPercentage(Long costSharingPercentage) {
        this.costSharingPercentage = costSharingPercentage;
    }

    public Integer getCostSharingTypeCode() {
        return costSharingTypeCode;
    }

    public void setCostSharingTypeCode(Integer costSharingTypeCode) {
        this.costSharingTypeCode = costSharingTypeCode;
    }

    public String getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
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
        hashMap.put("proposalCostSharingId", this.getProposalCostSharingId());
        hashMap.put("proposalId", this.getProposalId());
        hashMap.put("proposalNumber", this.getProposalNumber());
        hashMap.put("sequenceNumber", this.getSequenceNumber());
        hashMap.put("fiscalYear", this.getFiscalYear());
        hashMap.put("costSharingPercentage", this.getCostSharingPercentage());
        hashMap.put("costSharingTypeCode", this.getCostSharingTypeCode());
        hashMap.put("sourceAccount", this.getSourceAccount());
        hashMap.put("amount", this.getAmount());
        return hashMap;
    }
    
}
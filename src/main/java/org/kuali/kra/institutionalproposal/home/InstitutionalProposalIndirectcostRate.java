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
import org.kuali.kra.institutionalproposal.IndirectcostRateType;

public class InstitutionalProposalIndirectcostRate extends KraPersistableBusinessObjectBase { 
    
    private static final long serialVersionUID = 1L;

    private Integer proposalIndirectcostRateId; 
    private String proposalNumber; 
    private Integer sequenceNumber; 
    private Long applicableIndirectcostRate; 
    private Integer indirectcostRateTypeCode; 
    private String fiscalYear; 
    private boolean onCampusFlag; 
    private Long underrecoveryOfIndirectcost; 
    private String sourceAccount; 
    
    private IndirectcostRateType indirectcostRateType; 
    
    public InstitutionalProposalIndirectcostRate() { 

    } 
    
    public Integer getProposalIndirectcostRateId() {
        return proposalIndirectcostRateId;
    }

    public void setProposalIndirectcostRateId(Integer proposalIndirectcostRateId) {
        this.proposalIndirectcostRateId = proposalIndirectcostRateId;
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

    public Long getApplicableIndirectcostRate() {
        return applicableIndirectcostRate;
    }

    public void setApplicableIndirectcostRate(Long applicableIndirectcostRate) {
        this.applicableIndirectcostRate = applicableIndirectcostRate;
    }

    public Integer getIndirectcostRateTypeCode() {
        return indirectcostRateTypeCode;
    }

    public void setIndirectcostRateTypeCode(Integer indirectcostRateTypeCode) {
        this.indirectcostRateTypeCode = indirectcostRateTypeCode;
    }

    public String getFiscalYear() {
        return fiscalYear;
    }

    public void setFiscalYear(String fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    public boolean getOnCampusFlag() {
        return onCampusFlag;
    }

    public void setOnCampusFlag(boolean onCampusFlag) {
        this.onCampusFlag = onCampusFlag;
    }

    public Long getUnderrecoveryOfIndirectcost() {
        return underrecoveryOfIndirectcost;
    }

    public void setUnderrecoveryOfIndirectcost(Long underrecoveryOfIndirectcost) {
        this.underrecoveryOfIndirectcost = underrecoveryOfIndirectcost;
    }

    public String getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public IndirectcostRateType getIndirectcostRateType() {
        return indirectcostRateType;
    }

    public void setIndirectcostRateType(IndirectcostRateType idcRateType) {
        this.indirectcostRateType = indirectcostRateType;
    }

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("proposalIndirectcostRateId", this.getProposalIndirectcostRateId());
        hashMap.put("proposalNumber", this.getProposalNumber());
        hashMap.put("sequenceNumber", this.getSequenceNumber());
        hashMap.put("applicableIndirectcostRate", this.getApplicableIndirectcostRate());
        hashMap.put("indirectcostRateTypeCode", this.getIndirectcostRateTypeCode());
        hashMap.put("fiscalYear", this.getFiscalYear());
        hashMap.put("onCampusFlag", this.getOnCampusFlag());
        hashMap.put("underrecoveryOfIndirectcost", this.getUnderrecoveryOfIndirectcost());
        hashMap.put("sourceAccount", this.getSourceAccount());
        return hashMap;
    }
    
}
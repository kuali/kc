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
import org.kuali.kra.proposaldevelopment.bo.InvestigatorCreditType;

public class InstitutionalProposalUnitCreditSplit extends KraPersistableBusinessObjectBase { 
    
    private static final long serialVersionUID = 1L;

    private Integer proposalUnitCreditSplitId; 
    private Integer proposalId; 
    private String proposalNumber; 
    private Integer sequenceNumber; 
    private String personId; 
    private String unitNumber; 
    private String investigatorCreditTypeCode; 
    private Long credit; 
    
    private InstitutionalProposal institutionalProposal; 
    private InvestigatorCreditType investigatorCreditType; 
    
    public InstitutionalProposalUnitCreditSplit() { 

    } 
    
    public Integer getProposalUnitCreditSplitId() {
        return proposalUnitCreditSplitId;
    }

    public void setProposalUnitCreditSplitId(Integer proposalUnitCreditSplitId) {
        this.proposalUnitCreditSplitId = proposalUnitCreditSplitId;
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

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public String getInvestigatorCreditTypeCode() {
        return investigatorCreditTypeCode;
    }

    public void setInvestigatorCreditTypeCode(String investigatorCreditTypeCode) {
        this.investigatorCreditTypeCode = investigatorCreditTypeCode;
    }

    public Long getCredit() {
        return credit;
    }

    public void setCredit(Long credit) {
        this.credit = credit;
    }

    public InstitutionalProposal getInstitutionalProposal() {
        return institutionalProposal;
    }

    public void setInstitutionalProposal(InstitutionalProposal institutionalProposal) {
        this.institutionalProposal = institutionalProposal;
    }

    public InvestigatorCreditType getInvestigatorCreditType() {
        return investigatorCreditType;
    }

    public void setInvestigatorCreditType(InvestigatorCreditType invCreditType) {
        this.investigatorCreditType = investigatorCreditType;
    }

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("proposalUnitCreditSplitId", this.getProposalUnitCreditSplitId());
        hashMap.put("proposalId", this.getProposalId());
        hashMap.put("proposalNumber", this.getProposalNumber());
        hashMap.put("sequenceNumber", this.getSequenceNumber());
        hashMap.put("personId", this.getPersonId());
        hashMap.put("unitNumber", this.getUnitNumber());
        hashMap.put("invCreditTypeCode", this.getInvestigatorCreditTypeCode());
        hashMap.put("credit", this.getCredit());
        return hashMap;
    }
    
}
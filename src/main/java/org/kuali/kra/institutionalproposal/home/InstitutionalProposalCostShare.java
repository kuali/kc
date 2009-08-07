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

import org.kuali.kra.SequenceAssociate;
import org.kuali.kra.SequenceOwner;
import org.kuali.kra.award.home.ValuableItem;
import org.kuali.kra.bo.CostShareType;
import org.kuali.kra.institutionalproposal.InstitutionalProposalAssociate;
import org.kuali.rice.kns.util.KualiDecimal;

public class InstitutionalProposalCostShare extends InstitutionalProposalAssociate 
implements ValuableItem, SequenceAssociate { 
    
    private static final long serialVersionUID = 1L;

    private Long proposalCostShareId; 
    private String fiscalYear; 
    private KualiDecimal costSharePercentage; 
    private Integer costShareTypeCode; 
    private String sourceAccount; 
    private KualiDecimal amount; 
    
    private CostShareType costShareType;
    
    public InstitutionalProposalCostShare() { 

    } 
    
    public Long getProposalCostShareId() {
        return proposalCostShareId;
    }

    public void setProposalCostShareId(Long proposalCostShareId) {
        this.proposalCostShareId = proposalCostShareId;
    }

    public String getFiscalYear() {
        return fiscalYear;
    }

    public void setFiscalYear(String fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    public KualiDecimal getCostSharePercentage() {
        return costSharePercentage;
    }

    public void setCostSharePercentage(KualiDecimal costSharePercentage) {
        this.costSharePercentage = costSharePercentage;
    }

    public Integer getCostShareTypeCode() {
        return costShareTypeCode;
    }

    public void setCostShareTypeCode(Integer costShareTypeCode) {
        this.costShareTypeCode = costShareTypeCode;
    }

    public String getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public KualiDecimal getAmount() {
        return amount;
    }

    public void setAmount(KualiDecimal amount) {
        this.amount = amount;
    }
    
    


    /**
     * Gets the costShareType attribute. 
     * @return Returns the costShareType.
     */
    public CostShareType getCostShareType() {
        return costShareType;
    }

    /**
     * Sets the costShareType attribute value.
     * @param costShareType The costShareType to set.
     */
    public void setCostShareType(CostShareType costShareType) {
        this.costShareType = costShareType;
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
        this.proposalCostShareId = null;
    }

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("proposalCostShareId", this.getProposalCostShareId());
        hashMap.put("fiscalYear", this.getFiscalYear());
        hashMap.put("costSharePercentage", this.getCostSharePercentage());
        hashMap.put("costShareTypeCode", this.getCostShareTypeCode());
        hashMap.put("sourceAccount", this.getSourceAccount());
        hashMap.put("amount", this.getAmount());
        return hashMap;
    }
    
}
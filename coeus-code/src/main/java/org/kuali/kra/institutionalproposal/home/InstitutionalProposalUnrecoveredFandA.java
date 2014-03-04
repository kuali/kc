/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.kuali.coeus.common.framework.sequence.associate.SequenceAssociate;
import org.kuali.coeus.common.framework.sequence.owner.SequenceOwner;
import org.kuali.kra.award.home.ValuableItem;
import org.kuali.kra.institutionalproposal.IndirectcostRateType;
import org.kuali.kra.institutionalproposal.InstitutionalProposalAssociate;
import org.kuali.rice.core.api.util.type.KualiDecimal;

public class InstitutionalProposalUnrecoveredFandA extends InstitutionalProposalAssociate implements ValuableItem, SequenceAssociate {

    private static final long serialVersionUID = 1L;

    private Long proposalUnrecoveredFandAId;

    private KualiDecimal applicableIndirectcostRate;

    private Integer indirectcostRateTypeCode;

    private String fiscalYear;

    private boolean onCampusFlag;

    private KualiDecimal underrecoveryOfIndirectcost;

    private String sourceAccount;

    private IndirectcostRateType indirectcostRateType;

    public InstitutionalProposalUnrecoveredFandA() {
        onCampusFlag = true;
    }

    public Long getProposalUnrecoveredFandAId() {
        return proposalUnrecoveredFandAId;
    }

    public void setProposalUnrecoveredFandAId(Long proposalUnrecoveredFandAId) {
        this.proposalUnrecoveredFandAId = proposalUnrecoveredFandAId;
    }

    public KualiDecimal getApplicableIndirectcostRate() {
        return applicableIndirectcostRate;
    }

    public void setApplicableIndirectcostRate(KualiDecimal applicableIndirectcostRate) {
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

    public KualiDecimal getUnderrecoveryOfIndirectcost() {
        return underrecoveryOfIndirectcost;
    }

    public void setUnderrecoveryOfIndirectcost(KualiDecimal underrecoveryOfIndirectcost) {
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
        this.indirectcostRateType = idcRateType;
    }

    /**
     * @see org.kuali.coeus.common.framework.sequence.associate.SequenceAssociate#getSequenceOwner()
     */
    public SequenceOwner getSequenceOwner() {
        return getInstitutionalProposal();
    }

    /**
     * @see org.kuali.coeus.common.framework.sequence.associate.SequenceAssociate#setSequenceOwner(org.kuali.coeus.common.framework.sequence.owner.SequenceOwner)
     */
    public void setSequenceOwner(SequenceOwner newlyVersionedOwner) {
        setInstitutionalProposal((InstitutionalProposal) newlyVersionedOwner);
    }

    /**
     * @see org.kuali.coeus.common.framework.sequence.Sequenceable#resetPersistenceState()
     */
    public void resetPersistenceState() {
        this.proposalUnrecoveredFandAId = null;
    }

    public KualiDecimal getAmount() {
        return underrecoveryOfIndirectcost;
    }
}

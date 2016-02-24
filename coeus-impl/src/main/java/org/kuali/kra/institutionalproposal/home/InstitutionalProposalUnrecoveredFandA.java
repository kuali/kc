/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.institutionalproposal.home;

import org.kuali.coeus.common.framework.version.sequence.associate.SequenceAssociate;
import org.kuali.coeus.common.framework.version.sequence.owner.SequenceOwner;
import org.kuali.kra.award.home.ValuableItem;
import org.kuali.kra.institutionalproposal.IndirectcostRateType;
import org.kuali.kra.institutionalproposal.InstitutionalProposalAssociate;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

public class InstitutionalProposalUnrecoveredFandA extends InstitutionalProposalAssociate implements ValuableItem, SequenceAssociate {

    private static final long serialVersionUID = 1L;

    private Long proposalUnrecoveredFandAId;

    private ScaleTwoDecimal applicableIndirectcostRate;

    private Integer indirectcostRateTypeCode;

    private String fiscalYear;

    private boolean onCampusFlag;

    private ScaleTwoDecimal underrecoveryOfIndirectcost;

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

    public ScaleTwoDecimal getApplicableIndirectcostRate() {
        return applicableIndirectcostRate;
    }

    public void setApplicableIndirectcostRate(ScaleTwoDecimal applicableIndirectcostRate) {
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

    public ScaleTwoDecimal getUnderrecoveryOfIndirectcost() {
        return underrecoveryOfIndirectcost;
    }

    public void setUnderrecoveryOfIndirectcost(ScaleTwoDecimal underrecoveryOfIndirectcost) {
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

    @Override
    public SequenceOwner getSequenceOwner() {
        return getInstitutionalProposal();
    }

    @Override
    public void setSequenceOwner(SequenceOwner newlyVersionedOwner) {
        setInstitutionalProposal((InstitutionalProposal) newlyVersionedOwner);
    }

    @Override
    public void resetPersistenceState() {
        this.proposalUnrecoveredFandAId = null;
    }

    public ScaleTwoDecimal getAmount() {
        return underrecoveryOfIndirectcost;
    }
}

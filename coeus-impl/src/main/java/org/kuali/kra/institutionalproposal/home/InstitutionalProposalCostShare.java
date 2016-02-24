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
import org.kuali.kra.bo.CostShareType;
import org.kuali.kra.institutionalproposal.InstitutionalProposalAssociate;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

public class InstitutionalProposalCostShare extends InstitutionalProposalAssociate implements ValuableItem, SequenceAssociate {

    private static final long serialVersionUID = 1L;

    private Long proposalCostShareId;

    private String projectPeriod;

    private ScaleTwoDecimal costSharePercentage;

    private Integer costShareTypeCode;

    private String sourceAccount;

    private ScaleTwoDecimal amount;

    private CostShareType costShareType;

    public InstitutionalProposalCostShare() {
    }

    public Long getProposalCostShareId() {
        return proposalCostShareId;
    }

    public void setProposalCostShareId(Long proposalCostShareId) {
        this.proposalCostShareId = proposalCostShareId;
    }

    public String getProjectPeriod() {
        return projectPeriod;
    }

    public void setProjectPeriod(String projectPeriod) {
        this.projectPeriod = projectPeriod;
    }

    public ScaleTwoDecimal getCostSharePercentage() {
        return costSharePercentage;
    }

    public void setCostSharePercentage(ScaleTwoDecimal costSharePercentage) {
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

    public ScaleTwoDecimal getAmount() {
        return amount;
    }

    public void setAmount(ScaleTwoDecimal amount) {
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
        this.proposalCostShareId = null;
    }
}

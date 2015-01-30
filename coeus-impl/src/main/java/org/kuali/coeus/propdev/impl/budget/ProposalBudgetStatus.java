/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.propdev.impl.budget;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

import javax.persistence.*;

/**
 * This class represents a proposal status.  It maps to a separate table in
 * the database with a 1:1 relationship with the eps_proposal table.  This
 * is so the status can be updated from the Budget module or the Propsal
 * Development module without causing a wholesale collision between the two
 * documents.
 */
@Entity
@Table(name = "EPS_PROPOSAL_BUDGET_STATUS")
public class ProposalBudgetStatus extends KcPersistableBusinessObjectBase {

    @Id
    @Column(name = "PROPOSAL_NUMBER")
    private String proposalNumber;

    @Column(name = "BUDGET_STATUS_CODE")
    private String budgetStatusCode;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "BUDGET_STATUS_CODE", referencedColumnName = "BUDGET_STATUS_CODE", insertable = false, updatable = false)
    private BudgetStatus budgetStatus;

    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public String getBudgetStatusCode() {
        return budgetStatusCode;
    }

    public void setBudgetStatusCode(String budgetStatusCode) {
        this.budgetStatusCode = budgetStatusCode;
    }

    /**
     * Sets the budgetStatus attribute value.
     * @param budgetStatus The budgetStatus to set.
     */
    public void setBudgetStatus(BudgetStatus budgetStatus) {
        this.budgetStatus = budgetStatus;
    }

    /**
     * Gets the budgetStatus attribute. 
     * @return Returns the budgetStatus.
     */
    public BudgetStatus getBudgetStatus() {
        return budgetStatus;
    }
}

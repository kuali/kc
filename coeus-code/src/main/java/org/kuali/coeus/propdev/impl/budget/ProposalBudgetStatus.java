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
